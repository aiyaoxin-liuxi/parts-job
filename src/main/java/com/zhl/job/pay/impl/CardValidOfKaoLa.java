package com.zhl.job.pay.impl;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhl.job.pay.common.BasePay;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.MD5;
import com.zhl.job.util.PropsHandler;
import com.zhl.job.util.RandomGenerator;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.http.HttpClientHelper;
/**
 * 	考拉的卡的有效性验证
  * @ClassName: CardValid4Pay
  * @author zhaotisheng	
  * @date 2017年4月1日 上午9:17:15
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class CardValidOfKaoLa extends BasePay {
	private Logger logs = LoggerFactory.getLogger(CardValidOfKaoLa.class);

	//卡的有效性验证
	public static final String VALID_CARD_KAOLA_URL=Stringer.nullToEmpty(PropsHandler.getProperty("valid.card.kaola.url"));
	//valid.card.merchId
	public static final String VALID_CARD_MERCHID=Stringer.nullToEmpty(PropsHandler.getProperty("valid.card.kaola.merchId"));
	//valid.card.key
	public static final String VALID_CARD_KEY=Stringer.nullToEmpty(PropsHandler.getProperty("valid.card.kaola.key"));
		
	public static final String uploadStr="uploadStr";
	public static final String returnStr="returnStr";
	
	
		//卡的有效性验证 (四要素)
	
	
		public ResponseEntity validCard(Object[] objArr) {
			Map<String, String> dataMap=new HashMap<String,String>();
			
			Card card=(Card) objArr[0];
			ResponseEntity res=(ResponseEntity) objArr[1];
			String url=VALID_CARD_KAOLA_URL;String merchId=VALID_CARD_MERCHID;String key=VALID_CARD_KEY;
			
			Map<String, String> paramMap=new HashMap<String,String>();
			String tranNo = RandomGenerator.randomTimeMillis(18);
			paramMap.put("tranNo", tranNo);
			paramMap.put("merchId", merchId);
			paramMap.put("accNo", card.getCardNo());
			//accName
			paramMap.put("accName", card.getCardName());
			//certNo
			paramMap.put("certNo", card.getBindIdcard());
			//tel
			paramMap.put("tel", card.getMobile());
			//channelId
			paramMap.put("channelId", "2");
			//sign
			String sign =MD5.encrypt(merchId+tranNo,key);
			paramMap.put("sign", sign);
			StringBuilder json = JsonUtil.toJson(paramMap);
			logs.info(url+" <--上送的url，【卡的有效性验证】上送的param："+json.toString());
			dataMap.put("uploadStr", url+"##"+json.toString());
			String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
			//simulation
//			String o="{\"tranNo\":\"1490954247712193783854099210470\",\"code\":\"2\",\"message\":\"信息认证未通过(身份证号不正确)\"}";
			if(Stringer.isNullOrEmpty(o)){
				dataMap.put("returnStr", ""+State.ERROR_INTERFACE_NODATA_08.getName());
				
				logs.info(" 【卡的有效性验证】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
				res.setSuccess(false);
				res.setErrcode(State.ERROR_INTERFACE_NODATA_08.getCode());
				res.setErrmsg(State.ERROR_INTERFACE_NODATA_08.getName());
				return res;
			}
			logs.info("【卡的有效性验证】返回的结果"+o);
			dataMap.put("returnStr", ""+o);
			try {
				JsonNode rootNode = JsonUtil.toJsonNode(o);
				JsonNode codeNode = rootNode.path("code");
				if(Stringer.isNullOrEmpty(codeNode)){
					res.setSuccess(false);
					res.setErrmsg("接口返回无code");
					return res;
				}
				
				
				if(codeNode.getTextValue().equals("2")){
					res.setSuccess(false);
					res.setErrmsg("message"+rootNode.path("message").getTextValue());
					res.setData(dataMap);
					return res;
				}else if(codeNode.getTextValue().equals("1")){
					res.setSuccess(true);
					res.setErrmsg("认证成功");
					res.setData(dataMap);
					return res;
				}else{
					res.setSuccess(false);
					res.setErrmsg("未知的返回参数");
					res.setData(dataMap);
					return res;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				res.setSuccess(false);
				res.setErrmsg("解析错误");
				res.setData(dataMap);
				return res;
			}

		}

}
