package com.zhl.job.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.SettlementInfoLog;
import com.zhl.job.pojo.SettlementInfoLogForm;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.ISettlementInfoLogService;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 结算单信息
 * @author 刘熙
 *
 */
@Controller
@RequestMapping(value = "/settlement")
public class SettlementController extends BaseController{
	
//	private Logger logger = Logger.getLogger(SettlementController.class);
	
	@Resource(name = "settlementService")
	private ISettlementInfoService settlementInfoService;
	@Resource(name = "settlementInfoLogService")
	private ISettlementInfoLogService settlementInfoLogService;
	@Resource(name = "applyInfoService")
	private IApplyInfoService applyInfoService;
	@Resource(name = "userLogInfoService")
	private IUserLogInfoService userLogInfoService;
	
	
//	// 结算评价转账
//	@RequestMapping(value = "/settlementConfirm")
//	@ResponseBody
//	public Object settlementConfirm(SettlementInfo settlementInfo){
//		
//		String keySn = KeySn.getKey();
//		String check = checkSettlementConfirm(settlementInfo);
//		if(null != check){
//			return error("参数验证失败：" + check);
//		}
//		settlementInfoService.settlementConfirm(keySn, settlementInfo);
//		
//		
//		return null;
//	}
	
	// 结算评价转账
	@RequestMapping(value = "/settlementTransfer")
	public Object settlementTransfer(ModelMap model, HttpServletRequest request, RedirectAttributes attr, 
			SettlementInfoLogForm setForm, String pwd, String urlString){
		
		String keySn = KeySn.getKey();
		try {
			urlString = URLDecoder.decode(urlString, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		Map<String, Object> checkMap = checkSettlementConfirm(model, request, setForm, pwd, urlString);
		if(null != checkMap){
			alertError(attr, (String)checkMap.get("errmsg"));
			return "redirect:" + (String)checkMap.get("errUrl");
		}
		List<SettlementInfoLog> list = setForm.getSlList();
		if(null != list && list.size() > 0){
			// 根据申请单id，获取对应的结算明细
			SettlementInfoLog slInfoLog = settlementInfoLogService.querySInfoByApplyId(list.get(0).getApplyId());
			if(null != slInfoLog){
				// 获取结算单
				SettlementInfo sInfo = settlementInfoService.querySettlementById(slInfoLog.getSettlementId());
				if(null != sInfo){
					
					// 对比数据库中结算明细个数和传入个数是否一致
					List<SettlementInfoLog> dbList = sInfo.getsList();
					if(null != dbList && list.size() == dbList.size()){
						sInfo.setsList(list);
						// 开始结算
						ResponseEntity res = settlementInfoService.settlementConfirm(keySn, slInfoLog.getcUserId(), sInfo);
						if(res.isSuccess()){
							alertError(model, "结算成功");
							return "forward:/account/main";
						} else {
							alertError(model, res.getErrmsg());
							return "forward:/account/main";
						}
					} else {
						// 结算个数与录用个数不一致
						alertError(model, "结算个数与录用个数不一致");
						return new ModelAndView("forward:"+urlString);
					}
				} else {
					// 没有查询到结算单
					alertError(model, "没有查询到结算单");
					return new ModelAndView("forward:"+urlString);
				}
			} else {
				// 没有查询到结算明细
				alertError(model, "没有查询到结算明细");
				return new ModelAndView("forward:"+urlString);
			}
		} else {
			// 没有结算明细
			alertError(model, "没有选择结算明细");
			return new ModelAndView("forward:"+urlString);
		}
	}
	
	// 用户定时结算出错时，补充生成结算单
	@RequestMapping(value = "/supplyBuildSettlement")
	public Object supplyBuildSettlement(ModelMap model, HttpServletRequest request, Date date){
		// 查询结算单是否存在，不存在表示定时任务失败，补充生成结算单
		String keySn = KeySn.getKey();
		List<ApplyInfo> aList = applyInfoService.queryByEmployAndWorkDay(date);
		if(null != aList && aList.size() > 0){
			for (ApplyInfo applyInfo : aList) {
				SettlementInfoLog sil = settlementInfoLogService.querySInfoByApplyId(applyInfo.getId());
				if(null == sil){
					settlementInfoService.addSettlementService(keySn, applyInfo);
				}
			}
		}
		return null;
	}
	
	// ---------------------------- 以下为跳转 -------------------------------------
	
	/**
//	 * 查询招聘详细跳转
//	 * @return
//	 */
//	@RequestMapping(value = "/toQueryWorkDetail")
//	public Object toQueryWorkDetail() {
//		System.out.println("11");
//		return "work/workDetail";
//	}

	
	// -------------------------- 以下为验证------------------
	/**
	 * 检查结算信息
	 * @return
	 */
	private Map<String, Object> checkSettlementConfirm(ModelMap model, HttpServletRequest request, SettlementInfoLogForm setForm, String pwd, String urlString){
		
		Map<String, Object> map = null;
		// session 中获取
		Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
		if(null == attribute){
			map = new HashMap<String, Object>();
			map.put("errmsg", "请重新登录");
			map.put("errUrl", "companyInfo/login");
			return map;
		}
		if(!UserType.COMPANY.getCode().equals(getLoginUserType(request))){
			map = new HashMap<String, Object>();
			map.put("errmsg", "您不是企业用户");
			map.put("errUrl", "companyInfo/login");
			return map;
		}
		if(!verifyParam(pwd.trim())){
			map = new HashMap<String, Object>();
			map.put("errmsg", "密码不能为空");
			map.put("errUrl", "companyInfo/login");
			return map;
		}
		if(null == setForm){
			map = new HashMap<String, Object>();
			map.put("errmsg", "未找到结算单");
			map.put("errUrl", urlString);
			return map;
		}
		List<SettlementInfoLog> list = setForm.getSlList();
		if(null == list || list.size() == 0){
			map = new HashMap<String, Object>();
			map.put("errmsg", "未找到结算明细");
			map.put("errUrl", urlString);
			return map;
		}
		// 验证评语和金额
		for (SettlementInfoLog sl : list) {
			if(!Stringer.isDouble(String.valueOf(sl.getAccountPay().doubleValue()))){
				map = new HashMap<String, Object>();
				map.put("errmsg", "输入金额不规范");
				map.put("errUrl", urlString);
				break;
			}
			if(Stringer.haveSpecialCharacter(sl.getAppComment())){
				map = new HashMap<String, Object>();
				map.put("errmsg", "评语不能包含特殊字符");
				map.put("errUrl", urlString);
				break;
			}
		}
		if(null != map){
			return map;
		}
		// 验证密码
		if(!verifyParam(pwd.trim())){
			map = new HashMap<String, Object>();
			map.put("errmsg", "密码不能为空");
			map.put("errUrl", "/companyInfo/login");
			return map;
		}
		
		
		
		String userId = getLoginUserId(request);
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(userId);
		if(null == userLogInfo){
			// 用户不存在
			map = new HashMap<String, Object>();
			map.put("errmsg", "请重新登录");
			map.put("errUrl", "/companyInfo/login");
			return map;
		}
		String encryptLogPwd = Stringer.encryptPayPwd(pwd);
		if(null == userLogInfo.getPayPassword()){
			// 请先设置支付密码
			map = new HashMap<String, Object>();
			map.put("errmsg", "您未设置支付密码，请先设置支付密码");
			map.put("errUrl", "/compayinfo/safe");
			return map;
		}
		if(!encryptLogPwd.equals(userLogInfo.getPayPassword())){
			// 支付密码错误
			// 用户不存在
			map = new HashMap<String, Object>();
			map.put("errmsg", "支付密码错误");
			map.put("errUrl", urlString);
			return map;
		}
		
		return null;
	}
	
	
}
