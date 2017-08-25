/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表类型
 */
public enum WorkInfoType {

	/** 默认 */
	CODE00("00","默认");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoType(String code, String cnName) {
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
		for (WorkInfoType wType : WorkInfoType.values()) {
			if (code.equals(wType.getCode())) {
				return wType.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoType wType : WorkInfoType.values()) {
			if (Text.equals(wType.getCnName())) {
				return wType.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
