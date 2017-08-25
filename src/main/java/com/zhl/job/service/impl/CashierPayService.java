package com.zhl.job.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.ICashierPayService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.util.Stringer;

/**
 *  收银台支付通知service
  * @ClassName: CashierPayService
  * @author zhaotisheng	
  * @date 2017年3月22日 上午9:59:08
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@Transactional
public class CashierPayService implements ICashierPayService {

	
	private Logger logs = LoggerFactory.getLogger(CashierPayService.class);
	
	@Autowired
	IAccountflowService accountflowService;
	@Resource
	private IUserLogInfoService userLogInfoService;
	
	/*
	 * 
	  * 处理支付结果 (目前是只有支付成功通知)
	  * <p>Description: </p>
	  * @param noticeMap
	  * @return
	  * @see com.zhl.job.service.ICashierPayService#dealPayResult(java.util.Map)
	 */
	public ResponseEntity dealPayResult(Map<String, Object> noticeMap) {
		logs.debug("###>>> 【收银台支付通知】处理 ");
		ResponseEntity res = new ResponseEntity();
		if(Stringer.isNullOrEmpty(noticeMap)){
			res.setSuccess(false);
			res.setErrmsg("noticeMap is null");
			return res;
		}
		Object oriordercore = noticeMap.get("oriordercore");
		if(Stringer.isNullOrEmpty(oriordercore)){
			res.setSuccess(false);
			res.setErrmsg("oriordercore is null");
			return res;
		}
		List<Accountflow> accountflows= accountflowService.selectByTransFlowNo(oriordercore.toString());
		if(Stringer.isNullOrEmpty(accountflows) || accountflows.size() !=1){
			logs.error("###>>> 【收银台支付通知】selectBytransFlowNo 找流水出异常 transFlowNo ："+oriordercore);
			res.setSuccess(false);
			res.setErrmsg("selectBytransFlowNo 找流水出异常 transFlowNo ："+oriordercore);
			return res;
		}
		
		//
		Accountflow accountflow = accountflows.get(0);
		UserLogInfo user = new UserLogInfo();
		user.setId(accountflow.getUserId());
		Object paystatus = noticeMap.get("paystatus");
		if(Stringer.isNullOrEmpty(paystatus)){
			logs.error("###>>> 【收银台支付通知】paystatus 为空 异常 transFlowNo ："+oriordercore);
			res.setSuccess(false);
			res.setErrmsg("paystatus 为空 ："+oriordercore);
			return res;
		}
		if(paystatus.toString().equals("50")){
			if(accountflow.getState().equals(State.SUCCESS_66.getCode())){
				logs.debug("###>>> 状态已经是支付成功"+accountflow.getAccountId());
				res.setSuccess(true);
				res.setErrmsg("状态已经是支付成功 ："+oriordercore);
				return res;
			}
			accountflowService.rechargeSucc(user,accountflow,res);//成功会修改  accountflowNew 的state summary summarycode total useAmount
		}
		
		return res;
	}

}
