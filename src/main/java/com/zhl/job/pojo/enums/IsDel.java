/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 逻辑删除
 */
public enum IsDel {

	/** 正常 */
	CODE00("00","正常"),
	
	/** 已删除 */
	CODE01("01","已删除");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private IsDel(String code, String cnName) {
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
		for (IsDel isDel : IsDel.values()) {
			if (code.equals(isDel.getCode())) {
				return isDel.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (IsDel isDel : IsDel.values()) {
			if (Text.equals(isDel.getCnName())) {
				return isDel.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
