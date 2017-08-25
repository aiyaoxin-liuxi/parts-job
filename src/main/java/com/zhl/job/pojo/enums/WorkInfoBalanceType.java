/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表结算方式
 */
public enum WorkInfoBalanceType {

	/** 日结 */
	CODE00("00","日结"),
	/** 完成结 */
	CODE01("01","完成结");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoBalanceType(String code, String cnName) {
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
		for (WorkInfoBalanceType t : WorkInfoBalanceType.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoBalanceType t : WorkInfoBalanceType.values()) {
			if (Text.equals(t.getCnName())) {
				return t.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
