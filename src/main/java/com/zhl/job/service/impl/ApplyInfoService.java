package com.zhl.job.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.pub.util.date.DateConverter;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IApplyInfoDao;
import com.zhl.job.dao.ISettlementInfoLogDao;
import com.zhl.job.dao.IWorkInfoStatisDao;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.WorkInfoStatis;
import com.zhl.job.pojo.enums.ApplyInfoEmploy;
import com.zhl.job.pojo.enums.ApplyInfoState;
import com.zhl.job.pojo.enums.ApplyInfoType;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.util.DateUtil;

@Service("applyInfoService")
public class ApplyInfoService implements IApplyInfoService {
	
	private Logger logger = Logger.getLogger(ApplyInfoService.class);
	
	@Resource
	private IApplyInfoDao applyInfoDao;
	@Resource
	private ISettlementInfoService settlementService;
	@Resource
	private IWorkInfoStatisDao workInfoStatisDao;
	@Resource
	private ISettlementInfoLogDao settlementInfoLogDao;
	@Resource(name = "settlementService")
	private ISettlementInfoService settlementInfoService;

//	@Override
//	public List<ApplyInfo> selectPaging(PagingBounds bounds, Map<String, Object> map) {
//		return applyInfoDao.selectPaging(bounds, map);
//	}

	@Override
	public ApplyInfo queryByApplyId(Map<String, Object> map) {
		return applyInfoDao.queryByApplyId(map);
	}

	/**
	 * 用户申请兼职任务  支持 多天
	 */
	@Override
	public int insertApplyInfo(String keySn, ApplyInfo applyInfo) {
		// 若有今天当天日期，则生成结算单
		List<ApplyInfo> settlementList = new ArrayList<ApplyInfo>();
		List<ApplyInfo> applyInfos = new ArrayList<ApplyInfo>();
		ApplyInfo applyInfo_n = null;
		for(Date applyDay : applyInfo.getApplyWorkDays()){
			applyInfo_n = new ApplyInfo();
			applyInfo_n.setId("ap_"+KeySn.getKey());
			applyInfo_n.setUserId(applyInfo.getUserId());
			applyInfo_n.setPid(applyInfo.getPid());
			applyInfo_n.setWorkId(applyInfo.getWorkId());
			applyInfo_n.setApplyDetail(applyInfo.getApplyDetail());
			applyInfo_n.setApplyWorkDate(applyDay);
			applyInfo_n.setApplyWorkTime(applyInfo.getApplyWorkTime());
			applyInfo_n.setAppComment(applyInfo.getAppComment());
			applyInfo_n.setEmploy(ApplyInfoEmploy.CODE00.getCode());
			applyInfo_n.setType(ApplyInfoType.CODE00.getCode());
			applyInfo_n.setState(ApplyInfoState.CODE00.getCode());
			applyInfo_n.setCreatedate(new Date());
			applyInfo_n.setIsdel(IsDel.CODE00.getCode());
			applyInfos.add(applyInfo_n);
			
			if(DateUtil.ContrastDay(applyDay)){
				// 当天日期，需要生成结算单
				settlementList.add(applyInfo_n);
			}
		}
		int insertB = applyInfoDao.insertApplyInfos(applyInfos);
		if(insertB == 1){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("workId", applyInfo.getWorkId());
			WorkInfoStatis wsInfo = workInfoStatisDao.queryWsInfoByWorkId(map);
			wsInfo.setApplyNum(wsInfo.getApplyNum() + applyInfo.getApplyWorkDays().size());// 申请个数每天都算（需求）
			workInfoStatisDao.updateNum(wsInfo);
		}
		// 写入结算单
		if(null != settlementList && settlementList.size() > 0){
			settlementService.addSettlementService(keySn, settlementList.get(0));
		}
		return insertB;
	}

	/**
	 * 更新申请职位
	 * @param applyInfo
	 * @return
	 */
	public int updateApplyInfo(ApplyInfo applyInfo_n) {
		return applyInfoDao.updateApplyInfo(applyInfo_n);
	}

	/**
	 * 删除申请职位
	 * @param String... ids
	 * @return
	 */
	public int delApplyInfos(String... ids) {
		return applyInfoDao.delApplyInfos(ids);
	}

	@Override
	public List<ApplyInfo> queryByEmployAndWorkDay() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyWorkDate", DateConverter.string2Date(DateConverter.date2String(new Date())));
		
		map.put("employ", ApplyInfoEmploy.CODE01.getCode());
		map.put("isdel", IsDel.CODE00.getCode());
		return applyInfoDao.queryByEmployAndWorkDay(map);
	}
	
	@Override
	public List<ApplyInfo> queryByEmployAndWorkDay(Date date) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyWorkDate", date);
		
		map.put("employ", ApplyInfoEmploy.CODE01.getCode());
		map.put("isdel", IsDel.CODE00.getCode());
		return applyInfoDao.queryByEmployAndWorkDay(map);
	}

	
	@Override
	public PageInfo<ApplyInfo> queryApplyAndWorkPageByEmploy(Map<String, Object> map, Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
	    List<ApplyInfo> list = applyInfoDao.queryApplyAndWorkPageByEmploy(map);
	    //用PageInfo对结果进行包装
	    PageInfo<ApplyInfo> page = new PageInfo<ApplyInfo>(list);
	    return page;
	}

	@Override
	public List<ApplyInfo> queryByEmployAndWorkId(Map<String, Object> map) {
		return applyInfoDao.queryByEmployAndWorkId(map);
	}

	@Override
	public List<ApplyInfo> queryIsEmploy(Map<String, Object> map) {
		return applyInfoDao.queryIsEmploy(map);
	}

	@Override
	public List<ApplyInfo> queryWorkInfoBycondition(Map<String, Object> map) {
		return applyInfoDao.queryWorkInfoBycondition(map);
	}

	@Override
	public List<ApplyInfo> queryWorkInfoByDate(String logUserId, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", logUserId);
		map.put("applyWorkDate", date);
		return applyInfoDao.queryWorkInfoByDate(map);
	}
	
	@Override
	public List<ApplyInfo> queryWorkInfoByDateAndWorkId(String logUserId, String workId, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", logUserId);
		map.put("workId", workId);
		map.put("applyWorkDate", date);
		return applyInfoDao.queryWorkInfoByDateAndWorkId(map);
	}
	
	/**
	 * 筛选时间
	 * 	1、筛选过期时间
	 * 	2、筛选用户申请过时间
	 * @return
	 */
	public List<Date> screenDate(String logUserId, String allowChooseDate, Date startDate, Date endDate){
		logger.debug("起始日期：" +DateUtil.date2String(startDate)+"结束："+DateUtil.date2String(endDate));
		List<Date> returnList = new ArrayList<Date>();
		List<Date> list = DateUtil.getListAll(startDate, endDate);
		System.out.println("listsize:"+list.size());
		// 首先刨除过期的
		for(int i = 0; i < list.size(); i++){
			logger.debug(i+"、判定:"+DateUtil.date2String(list.get(i)));
			if(!DateUtil.ContrastBeforeDayAndNowDay(list.get(i))){// 过期
				System.out.println("过期:"+DateUtil.date2String(list.get(i)));
			} else {
				if("00".equals(allowChooseDate)){// 允许用户选择日期
					logger.debug("没过期~~"+ DateUtil.date2String(list.get(i)));
					if(!"".equals(logUserId)){ // 非游客
						List<ApplyInfo> aList  = queryWorkInfoByDate(logUserId, list.get(i));
						if(aList.size() <= 0){
							returnList.add(list.get(i));
						}
					} else {
						returnList.add(list.get(i));
					}
					logger.debug("没报名过"+ DateUtil.date2String(list.get(i)));
				} else {
					// 不允许用户选日期，必须每天都来
					logger.debug("没过期~~"+ DateUtil.date2String(list.get(i)));
					if(!"".equals(logUserId)){ // 非游客
						List<ApplyInfo> aList  = queryWorkInfoByDate(logUserId, list.get(i));
						if(aList.size() == 0){
							returnList.add(list.get(i));
							logger.debug("没报名过"+ DateUtil.date2String(list.get(i)));
						} else {
							logger.debug("报名过，要求每天都来"+ DateUtil.date2String(list.get(i)));
							returnList = new ArrayList<Date>();
							break;
						}
					} else {
						returnList.add(list.get(i));
					}
				}
				
			}
		}
		logger.debug("结束："+returnList.size());
		return returnList;
	}

	@Override
	public List<ApplyInfo> queryApplyByWorkAndDate(Map<String, Object> map) {
		return applyInfoDao.queryApplyByWorkAndDate(map);
	}

	@Override
	public PageInfo<ApplyInfo> queryApplyInfoPage(Map<String, Object> map,
			Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
	    List<ApplyInfo> list = applyInfoDao.queryApplyInfoPage(map);
	    //用PageInfo对结果进行包装
	    PageInfo<ApplyInfo> page = new PageInfo<ApplyInfo>(list);
	    return page;
	}

	@Override
	public int addEmployment(ApplyInfo applyInfo) {
		int reInt = applyInfoDao.updateApplyInfo(applyInfo);
		if(reInt == 1){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("workId", applyInfo.getWorkId());
			WorkInfoStatis workInfoStatis = workInfoStatisDao.queryWsInfoByWorkId(map);
			workInfoStatis.setEmployNum(workInfoStatis.getEmployNum() + 1);
			reInt = workInfoStatisDao.updateNum(workInfoStatis);
		}
		return reInt;
	}
	
	@Override
	public int addBatchEmployment(List<ApplyInfo> list) {
		int reInt = applyInfoDao.batchUpdateApplyInfos(list);
		if(reInt == -1){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("workId", list.get(0).getWorkId());
			WorkInfoStatis workInfoStatis = workInfoStatisDao.queryWsInfoByWorkId(map);
			workInfoStatis.setEmployNum(workInfoStatis.getEmployNum() + list.size());
			reInt = workInfoStatisDao.updateNum(workInfoStatis);
		}
		return reInt;
	}
	
	@Override
	public List<ApplyInfo> queryOldJobType(List<ApplyInfo> appList, String state, String jobType) {
		
		Map<String, Object> map = null;
		List<ApplyInfo> list = null;
		for(ApplyInfo appInfo : appList){
			
			map = new HashMap<String, Object>();
			list = new ArrayList<ApplyInfo>();
			map.put("pid", appInfo.getPid());
			map.put("state", state);
			map.put("jobType", jobType);
			
			list = queryOldJobType(map);
			if(null != list && list.size() > 0){
				appInfo.setAttendedStr("同经验");
			}
		}
		return appList;
	}
	
	
	@Override
	public List<ApplyInfo> queryOldJobType(String pid, String state, String jobType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("state", state);
		map.put("jobType", jobType);
		return queryOldJobType(map);
	}
	
	@Override
	public int cleanApplyInfos(List<ApplyInfo> list) {
		return applyInfoDao.batchUpdateApplyInfos(list);
	}
	
	@Override
	public int batchUpdateApplyInfos(List<ApplyInfo> list) {
		return applyInfoDao.batchUpdateApplyInfos(list);
	}

	@Override
	public List<ApplyInfo> queryApplyByWorkAndDateNotPage(Map<String, Object> map) {
		return applyInfoDao.queryApplyByWorkAndDateNotPage(map);
	}
	
	@Override
	public List<ApplyInfo> queryOldJobType(Map<String, Object> map) {
		return applyInfoDao.queryOldJobType(map);
	}
}
