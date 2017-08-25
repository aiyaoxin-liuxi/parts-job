/**
 * 
 */
package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 工作发布表性别条件
 */
public enum WorkInfoSexRequire {

	/** 不限 */
	CODE00("00","不限"),
	/** 男 */
	CODE01("01","男"),
	/** 女 */
	CODE02("02","女");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoSexRequire(String code, String cnName) {
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
		for (WorkInfoSexRequire t : WorkInfoSexRequire.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoSexRequire t : WorkInfoSexRequire.values()) {
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
		for (WorkInfoSexRequire s : WorkInfoSexRequire.values()){
			map.put(s.getCode(), s.getCnName());
		}
		return map;
	}
}
