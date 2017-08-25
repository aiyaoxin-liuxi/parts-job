package com.zhl.job.resovlver.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常类型
  * @ClassName: ExceptionType
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月24日 下午4:35:50
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum ExceptionType {

	NO_LOGIN("NO_LOGIN","用户没有登陆或者登陆已经失效","exception/nologin")
	,NO_LOGIN4_SYS("NO_LOGIN4_SYS","用户没有登陆或者登陆已经失效(后台)","exception/nologin4sys")
	;
	
	private String code;
	private String name;
	private String url;
	
	public static void main(String[] args) {
	 System.out.println(ExceptionType.parseOf("00").getName());	
	}
	
	private ExceptionType(String code,String name,String url){
		this.code=code;
		this.name=name;
		this.url=url;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	private static Map<String, ExceptionType> valueMap = new HashMap<String, ExceptionType>();

	static {
		for (ExceptionType _enum : ExceptionType.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static ExceptionType parseOf(String code) {
		for (ExceptionType item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("ExceptionType异常错误代码[" + code + "]不匹配!");
	}
}
