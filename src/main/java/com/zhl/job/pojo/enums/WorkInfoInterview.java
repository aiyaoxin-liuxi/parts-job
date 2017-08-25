/**
 * 
 */
package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 工作发布表是否面试
 */
public enum WorkInfoInterview {

	/** 否 */
	CODE00("00","否"),
	/** 是 */
	CODE01("01","是");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoInterview(String code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCnName() {
		return cnName;
	}


	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	public static String getText(String code) {
		for (WorkInfoInterview t : WorkInfoInterview.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoInterview t : WorkInfoInterview.values()) {
			if (Text.equals(t.getCnName())) {
				return t.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
	
	public static Map<String, Object> getAllList() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (WorkInfoInterview s : WorkInfoInterview.values()){
			map.put(s.getCode(), s.getCnName());
		}
		return map;
	}
}
