package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;
public enum UserState {

	
	STATE_ENABLE("01","启用")
	,STATE_DISABLE("02","禁用");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(UserState.parseOf("00").getName());	
	}
	
	private UserState(String code,String name){
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
	
	private static Map<String, UserState> valueMap = new HashMap<String, UserState>();

	static {
		for (UserState _enum : UserState.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static UserState parseOf(String code) {
		for (UserState item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("UserState异常错误代码[" + code + "]不匹配!");
	}
}
