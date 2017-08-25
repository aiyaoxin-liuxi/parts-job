package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * CARD（银行卡信息表）
  * @ClassName: Card
  * @author zhaotisheng	
  * @date 2017年3月16日 上午10:45:12
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class Card implements Serializable {
	

	/**
	  */
	
	private static final long serialVersionUID = 1L;
	
	private String id;//id            
	private String userId;//i user_id     
	private String subaccnoId ;//subaccno_id      
	private String  accountId;//account_id        
	private String bankname;//   银行名称
	private String bankno;//   
	private Date binddate;//    
	private String bindIdcard;//绑定身份证号
	private String bindState;// 
	private String cardName;//  
	private String cardNo;// 
	private String cardType;// 
	private String province;//  
	private String city;//     
	private String area;//    
	private String bank;//     所属银行 
	private String branck;//         
	private Date createdate;//       
	private String type;//               
	private String state;//                
	private String isdel;//            
	
	private String mobile;
	
	
	
	
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubaccnoId() {
		return subaccnoId;
	}
	public void setSubaccnoId(String subaccnoId) {
		this.subaccnoId = subaccnoId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankno() {
		return bankno;
	}
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}
	public Date getBinddate() {
		return binddate;
	}
	public void setBinddate(Date binddate) {
		this.binddate = binddate;
	}
	public String getBindIdcard() {
		return bindIdcard;
	}
	public void setBindIdcard(String bindIdcard) {
		this.bindIdcard = bindIdcard;
	}
	public String getBindState() {
		return bindState;
	}
	public void setBindState(String bindState) {
		this.bindState = bindState;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBranck() {
		return branck;
	}
	public void setBranck(String branck) {
		this.branck = branck;
	}

	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
	
	
}
