package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IApplyInfoDao;
import com.zhl.job.dao.ISettlementInfoDao;
import com.zhl.job.dao.ISettlementInfoLogDao;
import com.zhl.job.dao.IWorkInfoDao;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.SettlementInfoLog;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.ApplyInfoState;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.ReturnCode;
import com.zhl.job.pojo.enums.SettlementInfoLogState;
import com.zhl.job.pojo.enums.SettlementInfoLogType;
import com.zhl.job.pojo.enums.SettlementInfoState;
import com.zhl.job.pojo.enums.SettlementInfoType;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.service.ITransferService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.util.Arith;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Stringer;

@Service("settlementService")
public class SettlementInfoService implements ISettlementInfoService {
	
	private Logger logger = Logger.getLogger(SettlementInfoService.class);
	
	@Resource
	private ISettlementInfoDao settlementInfoDao;
	@Resource
	private IWorkInfoDao workInfoDao;
	@Resource
	private ISettlementInfoLogDao settlementInfoLogDao;
	@Resource
	private IApplyInfoDao applyInfoDao;
	@Resource
	private ITransferService transferService;
	@Resource
	private IUserLogInfoService userLogInfoService;
	
	
	@Override
	public ResponseEntity addSettlementService(String keySn, ApplyInfo appInfo) {
		logger.info("生成结算信息----start...");
		ResponseEntity res = new ResponseEntity();
		
		if(null != appInfo){
			
			// 查询招聘信息是否存在
			String workId = appInfo.getWorkId();
			Date workDay = appInfo.getApplyWorkDate();
			WorkInfo workInfo = workInfoDao.queryWorkInfoDetail(workId);
			if(null != workInfo){
				SettlementInfoLog slInfo = settlementInfoLogDao.querySInfoByApplyId(appInfo.getId());
				if(null == slInfo){
					
					String settlementId = "";
					
					/*
					 * 查询结算单是否存在
					 * 		1、存在：代表之前生成过，则不生成新结算单，更新应付总额字段
					 * 		2、不存在：生成新结算单
					 */
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("workId", workId);
					map.put("workDay", workDay);
					// ！此处有可能需要同步控制（以后再加）
					SettlementInfo sInfo = settlementInfoDao.querySettlementByWorkIdAndDay(map);
					if(null == sInfo){
						// 不存在，生成结算单主键id
						settlementId = new StringBuilder().append("s").append(keySn).toString();
					} else {
						// 存在，获取结算单主键id
						settlementId = sInfo.getId();
					}
					Map<String, Object> reMap = appList2SettlementLogList(keySn, settlementId, appInfo, workInfo);
					List<SettlementInfoLog> slList = (List<SettlementInfoLog>) reMap.get("slList");
					BigDecimal accountTotal = (BigDecimal) reMap.get("accountTotal");
					
					if(null == sInfo){
						sInfo = new SettlementInfo();
						sInfo.setId(settlementId);
						sInfo.setCid(workInfo.getCid());
						sInfo.setWorkId(workId);
						sInfo.setWorkDay(workDay);
						sInfo.setAccountTotal(accountTotal);
						sInfo.setType(SettlementInfoType.CODE00.getCode());
						sInfo.setState(SettlementInfoState.CODE00.getCode());
						sInfo.setCreatedate(new Date());
						sInfo.setIsdel(IsDel.CODE00.getCode());
						
						settlementInfoDao.addSettlementInfo(sInfo);
					} else {
						sInfo.setAccountTotal(Arith.add(sInfo.getAccountTotal(), accountTotal));
						settlementInfoDao.updateAccountTotal(sInfo);
					}
					
					settlementInfoLogDao.addSettlementInfoLog(slList);
					
					// 更改申请表状态(appList2SettlementLogList方法中写入状态)
					applyInfoDao.updateApplyInfo(appInfo);
//					applyInfoDao.batchUpdateApplyInfos(appList);
					// 更改workinfo状态
					if(WorkInfoState.CODE01.getCode().equals(workInfo.getState())){
						workInfo.setState(WorkInfoState.CODE02.getCode());
						workInfoDao.updateWorkInfo(workInfo);
					}
					
					res.setErrmsg("生成结算信息完成");
					res.setSuccess(true);
					res.setErrcode(ReturnCode.SUCCESS_000000.getCode());
					
				} else {
					res.setErrmsg("已有此结算单明细");
					res.setSuccess(false);
				}
			} else {
				res.setErrmsg("没有此招聘信息");
				res.setSuccess(false);
			}
		} else {
			res.setErrmsg("参数不全");
			res.setSuccess(false);
		}
		logger.info("【生成结算信息】结果：" + res.getErrmsg());
		return res;
	}
	
	@Override
	public void addSettlementInfo(SettlementInfo settlementInfo) {
		settlementInfoDao.addSettlementInfo(settlementInfo);
	}
	
	

	@Override
	public ResponseEntity settlementConfirm(String keySn, String cUserId, SettlementInfo settlementInfo) {
		
		logger.info("开始结算----start...");
		ResponseEntity res = new ResponseEntity();
		
		// 查询结算单信息是否存在
		List<SettlementInfoLog> slList = new ArrayList<SettlementInfoLog>();
		List<SettlementInfoLog> sInfoLogList = settlementInfo.getsList();
		List<ApplyInfo> appList = new ArrayList<ApplyInfo>();
		ApplyInfo appInfo = null;
		boolean log = true;
		String check = checkSettlementInfoLog(cUserId, sInfoLogList);
		ResponseEntity rEntity = null;
		if(null == check){// 检查结算记录
			BigDecimal accTotal = new BigDecimal(0);
			for(SettlementInfoLog sInfoLog : sInfoLogList){
				SettlementInfoLog old_slInfoLog = settlementInfoLogDao.querySInfoByApplyId(sInfoLog.getApplyId());
				sInfoLog.setId(old_slInfoLog.getId());
				accTotal = BigDecimalUtil.add(accTotal, sInfoLog.getAccountPay());
				appInfo = new ApplyInfo();
				// ----------转账-------------！！！！
				rEntity = transferService.transfer(sInfoLog.getAccountPay(), old_slInfoLog.getcUserId(), old_slInfoLog.getpUserId());
				if(rEntity.isSuccess()){
					// 转账成功
					// 组成结算记录信息
					sInfoLog.setAccountPay(sInfoLog.getAccountPay());
					sInfoLog.setSettlementDate(new Date());
					sInfoLog.setState(SettlementInfoLogState.CODE01.getCode());
					
					appInfo.setState(ApplyInfoState.CODE02.getCode());
					
				} else {
					// 转账失败
					
					// 组成结算记录信息
					sInfoLog.setAccountPay(sInfoLog.getAccountPay());
					sInfoLog.setSettlementDate(new Date());
					sInfoLog.setState(SettlementInfoLogState.CODE03.getCode());
					
					appInfo.setState(ApplyInfoState.CODE03.getCode());
					log = false;
				}
				slList.add(sInfoLog);
				
				// 生成修改申请表状态的list
				appInfo.setId(sInfoLog.getApplyId());
				appList.add(appInfo);
			}
			if(log){
				settlementInfo.setState(SettlementInfoState.CODE01.getCode());
			} else {
				settlementInfo.setState(SettlementInfoState.CODE03.getCode());
			}
			settlementInfo.setSettlementDate(new Date());
			settlementInfo.setAccountTotal(accTotal);
			settlementInfoDao.updateAccountTotalAndState(settlementInfo);
			
			// 更新记录表
			settlementInfoLogDao.updateSettlementInfoLog(slList);
			
			// 更新用户申请表状态
			applyInfoDao.batchUpdateApplyInfos(appList);
			if(log){
				res.setErrmsg("结算完成");
				res.setSuccess(true);
				res.setErrcode(ReturnCode.SUCCESS_000000.getCode());
			} else {
				res = rEntity;
			}
		} else {
			res.setErrmsg(check);
			res.setSuccess(false);
		}
		return res;
	}
	
	
	/**
	 * appList 写入 settlementList
	 * @param keySn
	 * @param settlementId
	 * @param appList
	 * @param workInfo
	 * @return
	 */
	private Map<String, Object> appList2SettlementLogList(String keySn, String settlementId, ApplyInfo appInfo, WorkInfo workInfo){
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		BigDecimal accountTotal = new BigDecimal(0);
		List<SettlementInfoLog> slList = new ArrayList<SettlementInfoLog>();
		SettlementInfoLog slInfo = null;
//		for (int i = 0; i < appList.size(); i++) {
			
		slInfo = new SettlementInfoLog();
		slInfo.setId(new StringBuilder().append("sl").append(KeySn.getKey()).toString());
		slInfo.setSettlementId(settlementId);
		slInfo.setcUserId(workInfo.getUserId());
		slInfo.setpUserId(appInfo.getUserId());
		slInfo.setPid(appInfo.getPid());
		slInfo.setCid(workInfo.getCid());
		slInfo.setWorkId(appInfo.getWorkId());
		slInfo.setApplyId(appInfo.getId());
		slInfo.setWorkDay(appInfo.getApplyWorkDate());
		slInfo.setAdvancePay(workInfo.getMoney());
		slInfo.setAccountPay(new BigDecimal(0));
		slInfo.setState(SettlementInfoLogState.CODE00.getCode());
		slInfo.setType(SettlementInfoLogType.CODE00.getCode());
		slInfo.setCreatedate(new Date());
		slInfo.setIsdel(IsDel.CODE00.getCode());
		
		slList.add(slInfo);
		accountTotal = Arith.add(workInfo.getMoney(), accountTotal);
		
		appInfo.setState(ApplyInfoState.CODE01.getCode());
//		}
		
		returnMap.put("slList", slList);
		returnMap.put("accountTotal", accountTotal);
		return returnMap;
	}
	
	private String checkSettlementInfoLog(String cUserId, List<SettlementInfoLog> sInfoLogList){
		
		SettlementInfoLog slInfo = null;
		BigDecimal b = new BigDecimal(0);
		for(SettlementInfoLog sInfoLog : sInfoLogList){
			
			slInfo = settlementInfoLogDao.querySInfoByApplyId(sInfoLog.getApplyId());
			if(null == slInfo){
				return "结算记录不存在";
			}
			b = Arith.add(b, sInfoLog.getAccountPay());
		}
		UserLogInfo UserLogInfo = userLogInfoService.selectByPk(cUserId);
		
		if(BigDecimalUtil.BigDecimalGreaterThan(b, UserLogInfo.getUseAmount())){
			return "可使用额度不足，请充值";
		}
		
		return null;
		
	}
	
//	public static void main(String[] args) {
//		List<ApplyInfo> appList = new ArrayList<ApplyInfo>();
//		
//		ApplyInfo aInfo = new ApplyInfo();
//		aInfo.setId("11111");
//		aInfo.setUserId("22222");
//		aInfo.setPid("33333");
//		aInfo.setWorkId("w17031516198370194952");
//		appList.add(aInfo);
//	
//		new SettlementInfoService().addSettlementService("11122333", appList);
//	}

	@Override
	public SettlementInfo querySettlementById(String id) {
		return settlementInfoDao.querySettlementById(id);
	}

//	/**
//	 * 分页查询by companyId
//	 */
//	public PageInfo<SettlementInfo> queryListByCompayId(Map<String, Object> paramMap, Integer pageNo,
//			Integer pageSize) {
//		PageHelper.startPage(pageNo, pageSize);
//		List<SettlementInfo> list=settlementInfoDao.queryListByCompayId(paramMap);
//		PageInfo<SettlementInfo> pageInfo = new PageInfo<SettlementInfo>(list);
//		return pageInfo;
//	}
	/**
	 * 分页查询by companyId
	 */
	public PageInfo<SettlementInfo> queryListByCompayId(Map<String, Object> paramMap, Integer pageNo,
			Integer pageSize) {
		List<SettlementInfo> list=null;
		PageHelper.startPage(pageNo, pageSize);
		if(Stringer.isNullOrEmpty(paramMap) || paramMap.size()==0){
			list=settlementInfoDao.queryListAll();
		}else{
			list=settlementInfoDao.queryListByCompayId(paramMap);
		}
		PageInfo<SettlementInfo> pageInfo = new PageInfo<SettlementInfo>(list);
		return pageInfo;
	}
}
