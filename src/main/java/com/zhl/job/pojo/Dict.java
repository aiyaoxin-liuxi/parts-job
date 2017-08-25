package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 *  字典表
  * @ClassName: Dict
  * @author zhaotisheng	
  * @date 2017年4月10日 下午2:18:56
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class Dict implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String did;
	private String dkey;//
	private String dvalue;//
	private String dorder;//
	private String dgroup;//
	private String rsv1;
	private String rsv2;
	private String state;
	private String dtype;
	private String remark;
	private Date createDate;
	private String isdel;
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDkey() {
		return dkey;
	}
	public void setDkey(String dkey) {
		this.dkey = dkey;
	}
	public String getDvalue() {
		return dvalue;
	}
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}
	public String getDorder() {
		return dorder;
	}
	public void setDorder(String dorder) {
		this.dorder = dorder;
	}
	public String getDgroup() {
		return dgroup;
	}
	public void setDgroup(String dgroup) {
		this.dgroup = dgroup;
	}
	public String getRsv1() {
		return rsv1;
	}
	public void setRsv1(String rsv1) {
		this.rsv1 = rsv1;
	}
	public String getRsv2() {
		return rsv2;
	}
	public void setRsv2(String rsv2) {
		this.rsv2 = rsv2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
	
}
