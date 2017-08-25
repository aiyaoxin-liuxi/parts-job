/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 发布方式
 */
public enum WorkInfoReleaseType {

	/** 直接发布 */
	CODE00("00","直接发布"),
	/** 预付 */
	CODE01("01","预付");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoReleaseType(String code, String cnName) {
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
		for (WorkInfoReleaseType t : WorkInfoReleaseType.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoReleaseType t : WorkInfoReleaseType.values()) {
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
