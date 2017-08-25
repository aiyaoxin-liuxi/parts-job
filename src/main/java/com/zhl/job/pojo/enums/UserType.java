package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 用户类型
01：兼职用户
02：企业用户
  * @ClassName: UserType
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月16日 下午5:37:40
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum UserType {

	
	PERSONAL("01","兼职用户")
	,COMPANY("02","企业用户")
	;
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(UserType.parseOf("00").getName());	
	}
	
	private UserType(String code,String name){
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
	
	private static Map<String, UserType> valueMap = new HashMap<String, UserType>();

	static {
		for (UserType _enum : UserType.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static UserType parseOf(String code) {
		for (UserType item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("UserType异常错误代码[" + code + "]不匹配!");
	}
}
