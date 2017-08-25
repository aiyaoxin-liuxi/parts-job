/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表类型
 */
public enum ApplyInfoType {

	/** 默认 */
	CODE00("00","默认");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private ApplyInfoType(String code, String cnName) {
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
		for (ApplyInfoType wState : ApplyInfoType.values()) {
			if (code.equals(wState.getCode())) {
				return wState.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (ApplyInfoType wState : ApplyInfoType.values()) {
			if (Text.equals(wState.getCnName())) {
				return wState.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
