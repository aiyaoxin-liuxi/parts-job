package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 考勤表实体类
 * 
 * @author 刘熙
 */

public class CheckWorkInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 工作id
	 */
	private String workId;
	/**
	 * 用户登录id
	 */
	private String userId;
	/**
	 * 兼职用户id
	 */
	private String pid;
	/**
	 * 打卡日期
	 */
	private Date day;
	/**
	 * 打卡时间
	 */
	private Date startCheckTime;
	/**
	 * 打卡位置
	 */
	private String location;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 逻辑删除标识
	 */
	private String isdel;
	
	//get and set
	/**
	 * 获取用户登录id userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 获取id id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取工作id workId
	 */
	public String getWorkId() {
		return workId;
	}

	/**
	 * 设置工作id workId
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}

	/**
	 * 获取兼职用户id pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置兼职用户id pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 设置用户登录id userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取打卡日期 day
	 */
	public Date getDay() {
		return day;
	}
	/**
	 * 设置打卡日期 day
	 */
	public void setDay(Date day) {
		this.day = day;
	}
	/**
	 * 获取打卡时间 startCheckTime
	 */
	public Date getStartCheckTime() {
		return startCheckTime;
	}
	/**
	 * 设置打卡时间 startCheckTime
	 */
	public void setStartCheckTime(Date startCheckTime) {
		this.startCheckTime = startCheckTime;
	}
	/**
	 * 获取打卡位置 location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * 设置打卡位置 location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取类型 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置类型 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置状态 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取逻辑删除标识 isdel
	 */
	public String getIsdel() {
		return isdel;
	}
	/**
	 * 设置逻辑删除标识 isdel
	 */
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

}
