package com.zhl.job.pay.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.pub.util.security.MessageSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhl.job.pay.common.BasePay;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.ReturnCode;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.service.impl.AccountService;
import com.zhl.job.util.AmountUtil;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.http.HttpClientHelper;
/**
 * 	收银台支付实现类
  * @ClassName: CashierPay
  * @author zhaotisheng	
  * @date 2017年3月20日 下午3:46:15
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class CashierPay extends BasePay {
	
	private Logger logs = LoggerFactory.getLogger(AccountService.class);

	public static final String enkey="cashier";//收银台的key
	public static final String webyurl=Constant.CASHIER_WEBYURL;
	
	
	
	
	//Object[] objArr = new Object[]{amount,card,res};
	/**
	 * 发送短信
	 */
	public ResponseEntity sendSmsCode(Object[] objArr) {
		logs.info("##>>> 发送短信验证码..start ");
		
		double amount =(double) objArr[0];Card card=(Card) objArr[1];ResponseEntity res=(ResponseEntity) objArr[2];
		Accountflow accountflow=(Accountflow) objArr[3];
		Map<String, String> paramMap=getParamMap(amount,card,accountflow);
		//RECHARGE_URL
		String sign = MessageSecurity.getMapMessageSecurity(paramMap,enkey);
		paramMap.put("sign", sign);
		StringBuilder json = JsonUtil.toJson(paramMap);
		String url = Constant.RECHARGE_URL;
		logs.info(url+" <--上送的url，【发送短信验证码】上送的param："+json.toString());
		String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
		if(Stringer.isNullOrEmpty(o)){
			logs.info(" 【发送短信验证码】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
			res.setSuccess(false);
			res.setErrcode(State.ERROR_INTERFACE_NODATA_08.getCode());
			res.setErrmsg(State.ERROR_INTERFACE_NODATA_08.getName());
			return res;
		}
		logs.info(" 【发送短信验证码】返回的结果：\r\n\t"+o);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = JsonUtil.toMap(new StringBuilder(o));
		Object returnCode = resMap.get("returnCode");
		if(!checkInterfaceSystemException(returnCode,res)){
			return res;
		}
		Object returnMsg = resMap.get("returnMsg");
		
		res.setErrcode(returnCode.toString());
		res.setErrmsg(returnMsg.toString());
		res.setData(resMap);//xx
		if(returnCode.toString().equals(ReturnCode.SUCCESS_000000.getCode())){
			res.setSuccess(true);
		}else{
			res.setSuccess(false);
		}
		return res;
	}

	private boolean checkInterfaceSystemException(Object returnCode, ResponseEntity res) {
		if(returnCode.toString().equals("999999")){
			res.setSuccess(false);
			res.setErrcode("999999");
			res.setErrmsg(State.ERROR_INTERFACE_05.getName());
			return false;
		}
		return true;
	}

	private Map<String, String> getParamMap(double amount, Card card, Accountflow accountflow) {
		Map<String, String> map=new HashMap<String,String>();
		map.put("certType", "01");
		map.put("accName", card.getCardName());//账户名 如张三
		map.put("certNo", card.getBindIdcard());//身份证
		map.put("accNo", card.getCardNo());//身份证号
		map.put("mobile", card.getMobile());//手机号
		map.put("platcode", "04");// Platcode   COLLEGE_PART_TIME("04","大学生兼职"),
		String odrc=org.pub.util.uuid.KeySn.getKey();
		System.out.println(odrc);
		map.put("oriordercore", accountflow.getTransFlowNo());//交易流水
		map.put("merchno", "college-part-time");//
		map.put("amount",  AmountUtil.yuan2Fen(amount));//
		map.put("cardtype", "1");//支付方式1借记卡  2信用卡 默认上送1
		map.put("webyurl",webyurl /*"http://localhost/ppayTestMer/wallet/new/payResSYNC"*/);
		map.put("tranTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		//ordertype
		map.put("ordertype", "0");//PAY("0","支付/消费"),
		return map;
	}

	public ResponseEntity payConfirm(Object[] objArr) {
		logs.info("##>>> 支付确认..start ");
		//Object[] objArr = new Object[]{res,smscode,accountflow};
		ResponseEntity res=(ResponseEntity) objArr[0];
		String smscode=(String) objArr[1];Accountflow accountflow=(Accountflow) objArr[2];
		Map<String, String> paramMap=new HashMap<String,String>();
		paramMap.put("orderTranOrderId", accountflow.getRetTn());
		paramMap.put("smsCode", smscode);
    	String sign = MessageSecurity.getMapMessageSecurity(paramMap,enkey);
    	paramMap.put("sign", sign);
		StringBuilder json = JsonUtil.toJson(paramMap);
		String url = Constant.RECHARGE_URL;
		logs.info(url+" <--上送的url，【支付确认】上送的param："+json.toString());
		String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
		if(Stringer.isNullOrEmpty(o)){
			logs.info(" 【支付确认】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
			res.setSuccess(false);
			res.setErrcode(State.ERROR_INTERFACE_NODATA_08.getCode());
			res.setErrmsg(State.ERROR_INTERFACE_NODATA_08.getName());
			return res;
		}
		logs.info(" 【支付确认】返回的结果：\r\n\t"+o);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = JsonUtil.toMap(new StringBuilder(o));
		Object returnCode = resMap.get("returnCode");
		if(!checkInterfaceSystemException(returnCode,res)){
			return res;
		}
		Object returnMsg = resMap.get("returnMsg");
		
		res.setErrcode(returnCode.toString());
		res.setErrmsg(returnMsg.toString());
		res.setData(resMap);//xx
		if(returnCode.toString().equals(ReturnCode.SUCCESS_000000.getCode())){
			res.setSuccess(true);
		}else{
			res.setSuccess(false);
		}
		return res;
		
	}

	
}
