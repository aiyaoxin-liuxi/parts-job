package com.zhl.job.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhl.job.dao.ISettlementInfoDao;
import com.zhl.job.dao.IWorkInfoDao;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.enums.SettlementInfoState;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.service.IQuartzJobService;

@Service("quartzJobService")
public class QuartzJobService implements IQuartzJobService{
	
	private Logger logger = Logger.getLogger(QuartzJobService.class);
	
	@Resource
	private ISettlementInfoDao settlementInfoDao;
	@Resource
	private IWorkInfoDao workInfoDao;
	
	@Override
	public String setWorkFinishState(List<WorkInfo> workList) {
		String reStr = "";
		List<WorkInfo> resList = setInsertList(workList);
		if(null != resList && resList.size() > 0){
			int reInt = workInfoDao.batchUpdateWorkInfos(resList);
			if(reInt == -1){
				logger.info("【定时任务——检查完工】:修改成功");
				reStr = "修改成功";
			} else {
				logger.info("【定时任务——检查完工】:修改失败");
				reStr = "修改失败";
			}
		} else {
			logger.info("【定时任务——检查完工】:完成，没有完工的招聘");
			reStr = "没有完工的招聘";
		}
		return reStr;
		
	}
	
	/**
	 * 获取完工的list
	 * @return
	 */
	private List<WorkInfo> setInsertList(List<WorkInfo> workList){
		
		List<WorkInfo> resList = new ArrayList<WorkInfo>();
		for(WorkInfo workInfo : workList){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("workId", workInfo.getId());
			List<SettlementInfo> setList = settlementInfoDao.querySettlementByWorkId(map);
			if(null != setList && setList.size() > 0){
				boolean bAll = true;
				for(SettlementInfo sInfo : setList){
					if(!SettlementInfoState.CODE01.getCode().equals(sInfo.getState())){
						bAll = false;
						break;
					}
				}
				if(bAll){
					workInfo.setState(WorkInfoState.CODE03.getCode());
					logger.info("【定时任务——检查完工】:招聘id--->" + workInfo.getId() + "招聘标题--->" + workInfo.getWorkTitle() + "已完工");
					resList.add(workInfo);
				}
			} else {
				continue;
			}
		}
		
		return resList;
	}


}
