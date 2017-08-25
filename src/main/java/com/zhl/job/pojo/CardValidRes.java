package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 *  卡有效性验证结果
  * @ClassName: CardValidRes
  * @author zhaotisheng	
  * @date 2017年4月1日 上午11:28:55
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
  * CARD_VALID_RES
  * 
  * CREATE TABLE PARTSJOB.CARD_VALID_RES (
	CID VARCHAR2(100) NOT NULL,
	USER_ID VARCHAR2(100),
	UPLOAD_STR VARCHAR2(500),
	RETURN_STR VARCHAR2(100),
	CREATEDATE TIMESTAMP,
	RES INTEGER,
	ISDEL VARCHAR2(2)
) ;

 */
public class CardValidRes implements Serializable {

	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	
	private static final long serialVersionUID = 5113636179247202918L;

	private String cid;//CID
	private String userId;//USER_ID
	private String uploadStr;//UPLOAD_STR
	private String returnStr;//RETURN_STR
	private Date createdate;  //CREATEDATE
	private int res;//0 失败  1 成功  RES
	private String isdel;//ISDEL
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUploadStr() {
		return uploadStr;
	}
	public void setUploadStr(String uploadStr) {
		this.uploadStr = uploadStr;
	}
	
	public String getReturnStr() {
		return returnStr;
	}
	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
	
	
}
