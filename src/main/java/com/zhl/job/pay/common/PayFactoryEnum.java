package com.zhl.job.pay.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 	支付相关的常量枚举
  * @ClassName: PayFactoryEnum
  * @author zhaotisheng	
  * @date 2017年4月1日 上午9:25:16
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum PayFactoryEnum {

	CASHIERPAY("CASHIERPAY","com.zhl.job.pay.impl.CashierPay","新钱包的接口（在南京写的那个）")
	,CARDVALID_KAOLA("CARDVALID_KAOLA","com.zhl.job.pay.impl.CardValidOfKaoLa","考拉的卡有效性验证")
	;
	private String code;
	private String name;
	private String desc;
	
	public static void main(String[] args) {
	 System.out.println(PayFactoryEnum.parseOf("00").getName());	
	}
	
	private PayFactoryEnum(String code,String name,String desc){
		this.code=code;
		this.name=name;
		this.desc=desc;
	}

	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String, PayFactoryEnum> valueMap = new HashMap<String, PayFactoryEnum>();

	static {
		for (PayFactoryEnum _enum : PayFactoryEnum.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static PayFactoryEnum parseOf(String code) {
		for (PayFactoryEnum item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("PayFactoryEnum异常错误代码[" + code + "]不匹配!");
	}

}
