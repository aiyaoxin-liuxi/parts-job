package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IApplyInfoDao;
import com.zhl.job.dao.IWorkInfoDao;
import com.zhl.job.dao.IWorkInfoStatisDao;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.WorkInfoStatis;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.ApplyInfoEmploy;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.ReturnCode;
import com.zhl.job.pojo.enums.WorkInfoApplySwitch;
import com.zhl.job.pojo.enums.WorkInfoBalanceType;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.pojo.enums.WorkInfoType;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.DateUtil;
import com.zhl.job.util.Utilc;

@Service("workInfoService")
public class WorkInfoService implements IWorkInfoService {
	
	private Logger logger = Logger.getLogger(WorkInfoService.class);
	
	@Resource
	private IWorkInfoDao workInfoDao;
	@Resource
	private IWorkInfoStatisDao workInfoStatisDao;
	@Resource
	private IApplyInfoDao applyInfoDao;
	@Resource
	private IApplyInfoService applyInfoService;
//	@Resource
//	private ISettlementInfoDao settlementInfoDao;
	
	@Override
	public WorkInfo queryById(String id){
		return workInfoDao.queryById(id);
	}

	@Override
	public void addWorkInfo(String keySn, WorkInfo workInfo) {
		
		BigDecimal day = new BigDecimal(0);
		List<Date> dateList = DateUtil.getListAll(workInfo.getWorkStartDate(), workInfo.getWorkEndDate());
		if(null != dateList){
			day = new BigDecimal(dateList.size());
		}
		// 此招聘总共工作天数
		workInfo.setWorkdayNum(String.valueOf(day));
		
		StringBuilder workId = new StringBuilder();
		workId.append("w").append(keySn);
		StringBuilder workStatisId = new StringBuilder();
		workStatisId.append("ws").append(keySn);
		
		workInfo.setId(workId.toString());
		workInfo.setBalanceType(WorkInfoBalanceType.CODE00.getCode());
		workInfo.setState(WorkInfoState.CODE00.getCode());
		workInfo.setType(WorkInfoType.CODE00.getCode());
		workInfo.setApplySwitch(WorkInfoApplySwitch.CODE00.getCode());
		workInfo.setCreatedate(new Date());
		workInfo.setIsdel(IsDel.CODE00.getCode());
		workInfoDao.addWorkInfo(workInfo);
		
		WorkInfoStatis wInfoStatis = new WorkInfoStatis();
		wInfoStatis.setId(workStatisId.toString());
		wInfoStatis.setWorkId(workId.toString());
		wInfoStatis.setLoadNum(0);
		wInfoStatis.setApplyNum(0);
		wInfoStatis.setCommentNum(0);
		wInfoStatis.setAttentionNum(0);
		wInfoStatis.setEmployNum(0);
		wInfoStatis.setType("00");
		wInfoStatis.setState("00");
		wInfoStatis.setCreatedate(new Date());
		wInfoStatis.setIsdel(IsDel.CODE00.getCode());
		workInfoStatisDao.addWorkInfoStatis(wInfoStatis);
	}


	@Override
	public PageInfo<WorkInfo> queryByPage(Map<String, Object> map, Integer pageNo,Integer pageSize, String logUserId) {
		PageHelper.startPage(pageNo, pageSize);
	    List<WorkInfo> list = workInfoDao.queryWorkInfoPage(map);
	    
	    // 获取时间list
	    for (WorkInfo workInfo : list) {
	    	List<Date> dateList = applyInfoService.screenDate(logUserId, workInfo.getAllowChooseDate(), workInfo.getWorkStartDate(), workInfo.getWorkEndDate());
	    	workInfo.setShowDateList(dateList);
	    }
	    
	    //用PageInfo对结果进行包装
	    PageInfo<WorkInfo> page = new PageInfo<WorkInfo>(list);
	    return page;
	}
	
	@Override
	public Map<String, Object> queryWorkInfoDetail(String workId, String logUserId, String pid){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 查询详细信息
		WorkInfo workInfo = workInfoDao.queryWorkInfoDetail(workId);
		
		// 查询录用报名人数
		int count = 0;
		List<ApplyInfo> aInfo = queryByEmployAndWorkId(workId);
		if(aInfo != null){
			count = aInfo.size();
		}
		
		// 查询当前人是否报名
		String em = "";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workId", workId);
		map.put("userId", logUserId);
		map.put("pid", pid);
//		map.put("employ", ApplyInfoEmploy.CODE00.getCode());
		List<ApplyInfo> apInfo = applyInfoDao.queryIsEmploy(map);
		if(apInfo != null && apInfo.size() > 0){
			em = ApplyInfoEmploy.CODE00.getCode();
		}
		
		if("".equals(em)){
			workInfo.setContactsMobile(Utilc.hideMobile(workInfo.getContactsMobile()));
		}
		
		// 增加浏览数量(暂时这样写，后期需要同步线程实现)
		WorkInfoStatis workInfoStatis = workInfo.getWorkInfoStatis();
		workInfoStatis.setLoadNum(workInfoStatis.getLoadNum() + 1);
		workInfoStatisDao.updateNum(workInfoStatis);
		
		returnMap.put("workInfo", workInfo);
		returnMap.put("employCount", count);
		returnMap.put("em", em);
		
		return returnMap;
	}

	@Override
	public String updateWorkInfoCondition(WorkInfo workInfo) {
		ResponseEntity res = new ResponseEntity();
		
		if(null != workInfo){
			WorkInfo wInfo = workInfoDao.queryWorkInfoDetail(workInfo.getId());
			if(null != wInfo){
				List<ApplyInfo> aList = applyInfoDao.queryByApplyWorkId(workInfo.getId());
				if(null == aList || aList.size() == 0){
					
					workInfoDao.updateWorkInfo(workInfo);
					
					res.setErrmsg("修改成功");
					res.setSuccess(true);
					res.setErrcode(ReturnCode.SUCCESS_000000.getCode());
				} else {
					res.setErrmsg("已有人申请此职位，不允许修改招聘信息");
					res.setSuccess(false);
				}
			} else {
				res.setErrmsg("未找到职位信息");
				res.setSuccess(false);
			}
		} else {
			res.setErrmsg("参数不全");
			res.setSuccess(false);
		}
		return res.toString();
	}

	@Override
	public PageInfo<WorkInfo> queryWorkInfoPageByState(Map<String, Object> map, Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
	    List<WorkInfo> list = workInfoDao.queryWorkInfoPageByState(map);
	    //用PageInfo对结果进行包装
	    PageInfo<WorkInfo> page = new PageInfo<WorkInfo>(list);
	    return page;
	}
	
	@Override
	public Map<String, Object> queryWorkInfoApplyByDate(String workId, Date date, Integer pageNo, Integer pageSize){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 查询详细信息
		WorkInfo workInfo = workInfoDao.queryWorkInfoDetail(workId);
		if(null != workInfo){
			List<Date> dateList = DateUtil.getListAll(workInfo.getWorkStartDate(), workInfo.getWorkEndDate());
			if(null != dateList && dateList.size() > 0){
				if(!dateList.contains(date)){
					date = dateList.get(0);
				}
				
				// 分页
				// 按日期查询申请人员信息
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("workId", workId);
				paramMap.put("applyWorkDate", date);
				PageHelper.startPage(pageNo, pageSize);
				List<ApplyInfo> appList = applyInfoDao.queryApplyByWorkAndDate(paramMap);
				PageInfo<ApplyInfo> pageInfo = new PageInfo<ApplyInfo>(appList);
				
				// 查询录用报名人数
				int count = 0;
				List<ApplyInfo> aInfo = queryByEmployAndWorkId(workId);
				if(aInfo != null){
					count = aInfo.size();
				}
				
				returnMap.put("count", count);
				returnMap.put("pageInfo", pageInfo);
				returnMap.put("workInfo", workInfo);
				returnMap.put("dateList", dateList);
				
				
			} else {
				logger.info("不能获得工作的天");
			}
		} else {
			logger.info("没找到相关招聘信息");
		}
		
		return returnMap;
	}
	
	@Override
	public Map<String, Object> queryWorkInfoApplyByDateNotPage(String workId, Date date){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		// 查询详细信息
		WorkInfo workInfo = workInfoDao.queryWorkInfoDetail(workId);
		if(null != workInfo){
			List<Date> dateList = DateUtil.getListAll(workInfo.getWorkStartDate(), workInfo.getWorkEndDate());
			if(null != dateList && dateList.size() > 0){
				if(!dateList.contains(date)){
					date = dateList.get(0);
				}
				List<ApplyInfo> appList = null;
				// 判定查询的日期是否已过或是当天
				if(DateUtil.ContrastBeforeDayAndNowDay(date)){
					// 没过期
					// 按日期查询申请人员信息
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("workId", workId);
					paramMap.put("applyWorkDate", date);
					appList = applyInfoDao.queryApplyByWorkAndDate(paramMap);
				} else {
					// 过期或当天
					// 按日期查询申请人员信息
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("workId", workId);
					paramMap.put("applyWorkDate", date);
					appList = applyInfoDao.queryApplyByWorkAndDateNotPage(paramMap);
				}
				
				// 查询录用报名人数
				int count = 0;
				List<ApplyInfo> aInfo = queryByEmployAndWorkId(workId);
				if(aInfo != null){
					count = aInfo.size();
				}
				
				// 取出结算单
//				Map<String, Object> setMap = new HashMap<String, Object>();
//				setMap.put("workId", workId);
//				setMap.put("workDay", date);
//				SettlementInfo settlementInfo = settlementInfoDao.querySettlementByWorkIdAndDay(setMap);
				
				returnMap.put("count", count);
				returnMap.put("appList", appList);
				returnMap.put("workInfo", workInfo);
				returnMap.put("dateList", dateList);
				returnMap.put("date", date);
				
				
			} else {
				logger.info("不能获得工作的天");
			}
		} else {
			logger.info("没找到相关招聘信息");
		}
		
		return returnMap;
	}

	
	@Override
	public PageInfo<WorkInfo> queryByPage(Map<String, Object> map, Integer pageNo,Integer pageSize) {
	    PageHelper.startPage(pageNo, pageSize);
	    List<WorkInfo> list = workInfoDao.queryWorkInfoPageForManager(map);
	    //用PageInfo对结果进行包装
	    PageInfo<WorkInfo> page = new PageInfo<WorkInfo>(list);
	    return page;
	}

	@Override
	public int updateWorkInfoById(WorkInfo workInfo) {
		return workInfoDao.updateWorkInfoById(workInfo);
	}
	
	@Override
	public List<ApplyInfo> queryByEmployAndWorkId(String workId){
		// 查询录用报名人数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workId", workId);
		map.put("employ", ApplyInfoEmploy.CODE01.getCode());
		return applyInfoDao.queryByEmployAndWorkId(map);
	}

	@Override
	public List<WorkInfo> queryWorkInfoByCondition(Map<String, Object> map) {
		return workInfoDao.queryWorkInfoByCondition(map);
	}
	
	@Override
	public int updateWorkInfo(WorkInfo workInfo) {
		return workInfoDao.updateWorkInfo(workInfo);
	}
	
	@Override
	public WorkInfo queryWorkInfo(String workId){
		return workInfoDao.queryWorkInfoDetail(workId);
	}

}
