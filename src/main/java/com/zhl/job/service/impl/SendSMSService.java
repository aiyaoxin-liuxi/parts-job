package com.zhl.job.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.pub.util.json.JsonUtil;
import org.pub.util.security.MessageSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.ReturnCode;
import com.zhl.job.pojo.enums.SmsCode;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.Utilc;
import com.zhl.job.util.http.HttpClientHelper;

/**
 * 短信发送
  * @ClassName: SendSMSService
  * @author zhaotisheng	
  * @date 2017年3月14日 上午10:53:46
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
public class SendSMSService {

	private Logger logs = LoggerFactory.getLogger(SendSMSService.class);

	public Object sendVerifyCode(String mobile,String randomDigital){
		ResponseEntity res = new ResponseEntity();
		if(Stringer.isNullOrEmpty(Constant.DEV_SWITCH)  || !Constant.DEV_SWITCH.equals("1")){//测试的时候可以用这个，不真正发短信，验证码看log
			logs.debug(">>>>>simulation模拟  发送验证码，mobile："+mobile);
			res.setData(randomDigital);
			res.setErrmsg("发送成功 \r\n\t(开发者模式-手机验证码请看控制台日志 （模式在application.properties里面dev.switch）)");
			res.setSuccess(true);
			return res.toJson();
		}
		logs.debug(">>>>>发送验证码，mobile："+mobile);
		
		Map<String,Object> paramMap =getParamMap(mobile,randomDigital);
		//**************************************
		StringBuilder json = com.zhl.job.util.JsonUtil.toJson(paramMap);
		String url=Constant.SEND_SMS_URL;
		logs.info(url+" <--上送的url，【发送短信验证码.】上送的param："+json.toString());
		String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
		if(Stringer.isNullOrEmpty(o)){
			logs.info(" 【发送短信验证码.】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
			res.setSuccess(false);
			res.setErrcode(State.ERROR_INTERFACE_NODATA_08.getCode());
			res.setErrmsg(State.ERROR_INTERFACE_NODATA_08.getName());
			return res;
		}
		//{"returnCode":"111111","returnMsg":"成功","outSmsNo":"0000001","smsNo":"PARTSJOB17032415397684180302"}
		logs.info(" 【发送短信验证码.】返回的结果"+o);
		Map<String, Object> map = com.zhl.job.util.JsonUtil.toMap(new StringBuilder(o));
		Object returnCodeObj = map.get("returnCode");
		if(Stringer.isNullOrEmpty(returnCodeObj)){
			res.setSuccess(false);
			res.setErrmsg("返回结果异常");
			return res.toJson();
		}
		
		String returnCode = returnCodeObj.toString();
		if(returnCode.equals(SmsCode.CODE111111.getCode())){
			res.setData(randomDigital);
			res.setErrmsg("发送成功,请查收验证码");
			res.setSuccess(true);
			res.setErrcode(ReturnCode.SUCCESS_000000.getCode());
			return res.toJson();
		}
		
		try {
			SmsCode parseOf = SmsCode.parseOf(returnCode);
			logs.info(" 【发送短信验证码.】返回的结果"+parseOf.getName());
			res.setData(randomDigital);
			res.setErrmsg(""+parseOf.getName());
			res.setSuccess(false);
			return res.toJson();
			
		} catch (Exception e) {
			logs.info(" 返回结果异常-未知的返回结果"+o);
			e.printStackTrace();
			res.setSuccess(false);
			res.setErrmsg("返回结果异常-未知的返回结果");
			return res.toJson();
		}
		
	}

	private Map<String, Object> getParamMap(String mobile, String randomDigital) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("applyNo", "0000004");// 商户编号
		map.put("mobile", mobile);// 接收手机号
		map.put("outSmsNo", "0000001");// 下游消息编号
		map.put("content", "您的短信验证码是："+randomDigital);// 发送内容（验证码）01
		map.put("type", "01");
		map = Utilc.sortMapByKey(map);// 排序
		String json = JsonUtil.getMapToJson(map);
		String sign = MessageSecurity.getMessageSecurity("", json, "PARTSJOB");
		map.put("sign", sign);
		return map;
	}
}
