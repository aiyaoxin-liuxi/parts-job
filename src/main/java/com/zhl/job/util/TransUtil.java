package com.zhl.job.util;

import java.math.BigDecimal;
import java.util.Date;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserType;

/**
 * 交易辅助类
  * @ClassName: TransUtil
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月17日 上午11:42:22
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class TransUtil {
	
	private static Logger logs = LoggerFactory.getLogger(TransUtil.class);
	
	/**
	 * 
	  * generate实体
	  *
	  * @Title: generateAccountflow
	  * @param @param amount
	  * @param @param card
	  * @param @param loginUser
	  * @param @param inputOrOutPut
	  * @param @param transTyoe
	  * @param @param state
	  * @param @return    设定文件
	  * @return Accountflow    返回类型
	  * @throws
	 */
	public static Accountflow  generateAccountflow(double amount, Card card, UserLogInfo loginUser, String inputOrOutPut,String transTyoe,String state) {
		logs.debug("##>>> 生成流水实体start.... ");
		String generateId = KeySn.getKey();
		String prefix=Stringer.getPrefixTranNo(transTyoe);
		Date now = new Date();
		Accountflow accountflow = new Accountflow();
		accountflow.setUserId(loginUser.getId());
		accountflow.setAccountId(getAccountId(loginUser));
		accountflow.setCreatedate(now);
		accountflow.setIsdel(IsDel.CODE00.getCode());
//		TOTAL
		accountflow.setTotal(getDecimal(loginUser.getTotal()));
//		USE_AMOUNT（可用金额）
		accountflow.setUseAmount(/*loginUser.getUseAmount() */getDecimal(loginUser.getUseAmount()));
//		NO_USE_AMOUNT（冻结金额）
		accountflow.setNoUseAmount(getDecimal(loginUser.getNoUseAmount()));
//		AMOUNT（交易金额）
		accountflow.setAmount(new BigDecimal(amount));
//		BALANCE（账户余额）
//		OPEN_BANK（开户行）bankname
		accountflow.setOpenBank(card.getBankname());
//		TRANS_DATE（交易日期）
		accountflow.setTransDate(now);
//		TRANS_DIRECTION（交易方向:00收入，01指支出）    平台收入就是收入
		accountflow.setTransDirection(inputOrOutPut);
//		TRANS_FLOW_NO（流水号）
		accountflow.setTransFlowNo(prefix+generateId);
//		TRANS_TIME（交易时间）
		accountflow.setTransTime(now);
//		TYPE（类型）
		accountflow.setType(transTyoe);
//		state（状态）
		accountflow.setState(state);
		//20170406 add zts  提现是提现的账号，充值是充值的账号
		accountflow.setOppoNo(card.getCardNo());
		accountflow.setOppoBank(card.getBankname());
		logs.debug("##>>> 生成流水实体end.... ");
		return accountflow;
	}
	
	
	/**
	 * 转账流水实体构建
	 */
	public static Accountflow  generateAccountflow(String transFlowNo, BigDecimal amount, UserLogInfo loginUser, UserLogInfo loginUser2, String inputOrOutPut,String transTyoe,String state) {
		logs.debug("##>>> 生成流水实体start.... ");
		Date now = new Date();
		Accountflow accountflow = new Accountflow();
		accountflow.setUserId(loginUser.getId());
		accountflow.setAccountId(getAccountId(loginUser));
		accountflow.setCreatedate(now);
		accountflow.setIsdel(IsDel.CODE00.getCode());
//		TOTAL
		accountflow.setTotal(getDecimal(loginUser.getTotal()));
//		USE_AMOUNT（可用金额）
		accountflow.setUseAmount(/*loginUser.getUseAmount() */getDecimal(loginUser.getUseAmount()));
//		NO_USE_AMOUNT（冻结金额）
		accountflow.setNoUseAmount(getDecimal(loginUser.getNoUseAmount()));
//		AMOUNT（交易金额）
		accountflow.setAmount(amount);
//		BALANCE（账户余额）
//		OPPO_NO（对方账号）
		accountflow.setOppoNo(getAccountId(loginUser2));
//		TRANS_DATE（交易日期）
		accountflow.setTransDate(now);
//		TRANS_DIRECTION（交易方向:00收入，01指支出,02转账）
		accountflow.setTransDirection(inputOrOutPut);
//		TRANS_FLOW_NO（流水号）
		accountflow.setTransFlowNo(transFlowNo);
//		TRANS_TIME（交易时间）
		accountflow.setTransTime(now);
//		TYPE（类型）
		accountflow.setType(transTyoe);
//		state（状态）
		accountflow.setState(state);
		logs.debug("##>>> 生成流水实体end.... ");
		return accountflow;
	}
	
	
	private static BigDecimal getDecimal(BigDecimal decimal) {
		if(Stringer.isNullOrEmpty(decimal)){
			return new BigDecimal(0);
		}
		if(decimal.equals(new BigDecimal(0))){
			return new BigDecimal(0);
		}
		return decimal;
	}
	//组合 AccountId
	private static String getAccountId(UserLogInfo loginUser) {
		String key = KeySn.getKey();
		String userType = loginUser.getUserType();
		if(userType.equals(UserType.PERSONAL.getCode())){
			return "P_"+key;
		}else if(userType.equals(UserType.COMPANY.getCode())){
			return "C_"+key;
		}
		return null;
	}

}
