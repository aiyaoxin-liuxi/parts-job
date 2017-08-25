/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表是否可选日期
 */
public enum WorkInfoAllowChooseDate {

	/** 允许 */
	CODE00("00","允许"),
	/** 不允许 */
	CODE01("01","不允许");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoAllowChooseDate(String code, String cnName) {
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
		for (WorkInfoAllowChooseDate t : WorkInfoAllowChooseDate.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoAllowChooseDate t : WorkInfoAllowChooseDate.values()) {
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
