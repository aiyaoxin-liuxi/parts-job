/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 结算记录表状态
 */
public enum SettlementInfoLogState {

	/** 未结算 */
	CODE00("00","未结算"),
	/** 已结算 */
	CODE01("01","已结算"),
	/** 结算失败 */
	CODE02("02","结算失败"),
	/** 结算待处理 */
	CODE03("03","待处理");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private SettlementInfoLogState(String code, String cnName) {
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
		for (SettlementInfoLogState t : SettlementInfoLogState.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (SettlementInfoLogState t : SettlementInfoLogState.values()) {
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
