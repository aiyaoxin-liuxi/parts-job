/**
 * 
 */
package com.zhl.job.pojo.enums;


/**
 * 工作发布表状态
 */
public enum WorkInfoState {

	/** 审核中 */
	CODE00("00","审核中"),
	/** 已发布 */
	CODE01("01","已发布"),
	/** 进行中 */
	CODE02("02","进行中"),
	/** 已完工 */
	CODE03("03","已完工"),
	/** 审核拒绝 */
	CODE04("04","审核拒绝");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoState(String code, String cnName) {
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
		for (WorkInfoState wState : WorkInfoState.values()) {
			if (code.equals(wState.getCode())) {
				return wState.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoState wState : WorkInfoState.values()) {
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
