/**
 * 
 */
package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 工作发布表有无工作餐
 */
public enum WorkInfoMeal {

	/** 无 */
	CODE00("00","无"),
	/** 有 */
	CODE01("01","有");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoMeal(String code, String cnName) {
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
		for (WorkInfoMeal t : WorkInfoMeal.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoMeal t : WorkInfoMeal.values()) {
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
		for (WorkInfoMeal s : WorkInfoMeal.values()){
			map.put(s.getCode(), s.getCnName());
		}
		return map;
	}
}
