package com.zhl.job.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.IQuartzJobService;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.DateUtil;

/**
 * 定时任务
 * @author 刘熙
 *
 */
@Controller
@RequestMapping(value = "/quartzJob")
public class QuartzJobController extends BaseController {

	private Logger logger = Logger.getLogger(QuartzJobController.class);
	
	@Resource(name = "settlementService")
	private ISettlementInfoService settlementInfoService;
	@Resource(name = "applyInfoService")
	private IApplyInfoService applyInfoService;
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	@Resource(name = "quartzJobService")
	private IQuartzJobService quartzJobService;
	
	/**
	 * 每日生成结算单和计算记录信息，修改生成结算记录的申请单状态和工作状态
	 * @param request
	 * @param newsComment
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoAuth
	@RequestMapping(value = "/buildSettlement")
	@ResponseBody
	public Object buildSettlement(ModelMap model) throws UnsupportedEncodingException {
		String reStr = "";
		logger.info("【定时任务——生成结算单】：开始......");
		String keySn = KeySn.getKey();
		try {
			// 查询需要生成结算单的信息
			List<ApplyInfo> appList = applyInfoService.queryByEmployAndWorkDay();
			String check = checkBuildSettlement(appList);
			if(null == check){
				StringBuilder successStr = new StringBuilder();
				StringBuilder falgStr = new StringBuilder();
				ResponseEntity re = null;
				for (ApplyInfo applyInfo : appList) {
					re = settlementInfoService.addSettlementService(keySn, applyInfo);
					if(re.isSuccess()){
						logger.info("【定时任务——生成结算单】："+reStr);
						successStr.append(";").append(applyInfo.getId());
					} else {
						logger.info("【定时任务——生成结算单】："+reStr);
						falgStr.append(";").append(applyInfo.getId());
					}
				}
				reStr = "生成成功："+ successStr.toString() + "生成失败:" + falgStr.toString();
				reStr = URLEncoder.encode(reStr, "utf-8");
			} else {
				logger.info("【定时任务——生成结算单】：结束，没有可生成的结算单");
				reStr = "没有可生成的结算单";
			}
		} catch (UnsupportedEncodingException e) {
			logger.info(e.getMessage());
			reStr = "生成失败"+e.getMessage();
		}
		reStr = URLEncoder.encode(reStr, "utf-8");
		return reStr;
		
	}
	
	
	/**
	 * 每日检查全部完成并结算成功的记录，改状态为已完成
	 * @param request
	 * @param newsComment
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@NoAuth
	@RequestMapping(value = "/finishJob")
	@ResponseBody
	public Object finishJob(ModelMap model) throws UnsupportedEncodingException {
		logger.info("【定时任务——检查完工】：开始......");
		String reStr = "";
		try {
			// 查询需要生成结算单的信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("state", WorkInfoState.CODE02.getCode());
			map.put("workEndDate", DateUtil.getDateBeforeDay(1));
			List<WorkInfo> workList = workInfoService.queryWorkInfoByCondition(map);
			String check = checkfinishJob(workList);
			if(null == check){
				reStr = quartzJobService.setWorkFinishState(workList);
			} else {
				reStr = "没有完工的招聘";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			reStr = "生成失败"+e.getMessage();
		}
		logger.info("【定时任务——检查完工】：结束......" + reStr);
		reStr = URLEncoder.encode(reStr, "utf-8");
		return reStr;
	}
	
	
	// -------------------------- 以下为验证------------------
	
	private String checkBuildSettlement(List<ApplyInfo> appList){
		if(null == appList || appList.size() == 0){
			return "没有需要生成结算单的申请";
		}
		return null;
	}
	private String checkfinishJob(List<WorkInfo> workList){
		if(null == workList || workList.size() == 0){
			return "没有可操作的招聘信息";
		}
		return null;
	}
}
