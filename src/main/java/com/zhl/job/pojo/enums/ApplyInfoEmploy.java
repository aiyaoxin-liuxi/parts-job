/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表状态
 */
public enum ApplyInfoEmploy {

	/** 已报名 */
	CODE00("00","已报名"),
	/** 已录取 */
	CODE01("01","已录取"),
	/** 未录用 */
	CODE02("02","未录用"),
	/** 申请取消 */
	CODE03("03","申请取消");
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private ApplyInfoEmploy(String code, String cnName) {
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
		for (ApplyInfoEmploy wState : ApplyInfoEmploy.values()) {
			if (code.equals(wState.getCode())) {
				return wState.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (ApplyInfoEmploy wState : ApplyInfoEmploy.values()) {
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
