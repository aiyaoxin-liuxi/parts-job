/**
 * 
 */
package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;


/**
 * 工作发布表岗位类别
 */
public enum WorkInfoJobType {

	/** 促销导购 */
	CODE100000("100000","促销导购"),
	/** 话务客服 */
	CODE100001("100001","话务客服"),
	/** 传单派送 */
	CODE100002("100002","传单派送"),
	/** 审核录入 */
	CODE100003("100003","审核录入"),
	/** 服务员 */
	CODE100004("100004","服务员"),
	/** 问卷调查 */
	CODE100005("100005","问卷调查"),
	/** 地推拉访 */
	CODE100006("100006","地推拉访"),
	/** 礼仪模特 */
	CODE100007("100007","礼仪模特"),
	/** 老师家教 */
	CODE100008("100008","老师家教"),
	/** 翻译 */
	CODE100009("100009","翻译"),
	/** 设计 */
	CODE100010("100010","设计"),
	/** 其他 */
	CODE100011("100011","其他");
	
	
	private String code;
	
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private WorkInfoJobType(String code, String cnName) {
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
		for (WorkInfoJobType t : WorkInfoJobType.values()) {
			if (code.equals(t.getCode())) {
				return t.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String Text) {
		for (WorkInfoJobType t : WorkInfoJobType.values()) {
			if (Text.equals(t.getCnName())) {
				return t.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
	
	public static Map<String, Object> getAllList() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (WorkInfoJobType s : WorkInfoJobType.values()){
			map.put(s.getCode(), s.getCnName());
		}
		return map;
	}
}
