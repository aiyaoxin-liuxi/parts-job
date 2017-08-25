/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表状态
 */
public enum ApplyInfoState {

	/** 未开始 */
	CODE00("00","未开始"),
	/** 进行中 */
	CODE01("01","进行中"),
	/** 已完成 */
	CODE02("02","已完成"),
	/** 结算失败 */
	CODE03("03","结算失败");
	
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private ApplyInfoState(String code, String cnName) {
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
		for (ApplyInfoState wState : ApplyInfoState.values()) {
			if (code.equals(wState.getCode())) {
				return wState.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (ApplyInfoState wState : ApplyInfoState.values()) {
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
