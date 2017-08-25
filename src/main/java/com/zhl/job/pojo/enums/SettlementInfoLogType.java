/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 结算记录表类型
 */
public enum SettlementInfoLogType {

	/** 默认 */
	CODE00("00","默认");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private SettlementInfoLogType(String code, String cnName) {
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
		for (SettlementInfoLogType t : SettlementInfoLogType.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (SettlementInfoLogType t : SettlementInfoLogType.values()) {
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
