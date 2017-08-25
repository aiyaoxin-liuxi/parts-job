package com.zhl.job.pojo.enums.trans;

import java.util.HashMap;
import java.util.Map;


/**
 *  交易方向:00收入，01指支出
  * @ClassName: TransDirection
  * @author zhaotisheng	
  * @date 2017年3月17日 上午9:27:33
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum TransDirection {
	
    INPUT("00","收入")
	,OUTPUT("01","支出");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(TransDirection.parseOf("00").getName());	
	}
	
	private TransDirection(String code,String name){
		this.code=code;
		this.name=name;
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
	
	private static Map<String, TransDirection> valueMap = new HashMap<String, TransDirection>();

	static {
		for (TransDirection _enum : TransDirection.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static TransDirection parseOf(String code) {
		for (TransDirection item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("TransDirection异常错误代码[" + code + "]不匹配!");
	}

}
