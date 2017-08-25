package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 后台用户
  * @ClassName: SysUser
  * @author zhaotisheng	
  * @date 2017年4月9日 下午4:59:42
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class SysUser implements Serializable {

	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	
	private static final long serialVersionUID = 1L;

	private String sid;//SID
	
	private String username;//username
	private String name;//NAME
	private String pwd;//PWD
	private Date createDate;//CREATE_DATE
	private Date updateDate;//UPDATE_DATE
	private String isdel;//ISDEL
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
	
}
