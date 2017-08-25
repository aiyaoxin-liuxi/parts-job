package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行信息
 * @author 刘熙
 */
public class BankInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 创建日期
	 */
	private Date createDate;
	/**
	 * 银行父级ID
	 */
	private String pid;
	/**
	 * 银行编码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 银行logo地址
	 */
	private String bankIco;
	/**
	 * 邮编
	 */
	private String bankZipCode;
	/**
	 * 银行地址
	 */
	private String bankAddr;
	/**
	 * 经度
	 */
	private String bankLot;
	/**
	 * 纬度
	 */
	private String bankLat;
	/**
	 * 状态00:启用;01:禁用
	 */
	private String state;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 修改日期
	 */
	private Date updateDate;
	/**
	 * 修改人
	 */
	private String updateName;
	/**
	 * 备注信息
	 */
	private String reMark;
	/**
	 * 是否删除0：正常;9：删除
	 */
	private String isAudit;
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
	 * 获取创建日期 createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置创建日期 createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取银行父级ID pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置银行父级ID pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取银行编码 bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * 设置银行编码 bankCode
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * 获取银行名称 bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * 设置银行名称 bankName
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * 获取银行logo地址 bankIco
	 */
	public String getBankIco() {
		return bankIco;
	}
	/**
	 * 设置银行logo地址 bankIco
	 */
	public void setBankIco(String bankIco) {
		this.bankIco = bankIco;
	}
	/**
	 * 获取邮编 bankZipCode
	 */
	public String getBankZipCode() {
		return bankZipCode;
	}
	/**
	 * 设置邮编 bankZipCode
	 */
	public void setBankZipCode(String bankZipCode) {
		this.bankZipCode = bankZipCode;
	}
	/**
	 * 获取银行地址 bankAddr
	 */
	public String getBankAddr() {
		return bankAddr;
	}
	/**
	 * 设置银行地址 bankAddr
	 */
	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}
	/**
	 * 获取经度 bankLot
	 */
	public String getBankLot() {
		return bankLot;
	}
	/**
	 * 设置经度 bankLot
	 */
	public void setBankLot(String bankLot) {
		this.bankLot = bankLot;
	}
	/**
	 * 获取纬度 bankLat
	 */
	public String getBankLat() {
		return bankLat;
	}
	/**
	 * 设置纬度 bankLat
	 */
	public void setBankLat(String bankLat) {
		this.bankLat = bankLat;
	}
	/**
	 * 获取状态00:启用;01:禁用 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置状态00:启用;01:禁用 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取创建人 createName
	 */
	public String getCreateName() {
		return createName;
	}
	/**
	 * 设置创建人 createName
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * 获取修改日期 updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置修改日期 updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取修改人 updateName
	 */
	public String getUpdateName() {
		return updateName;
	}
	/**
	 * 设置修改人 updateName
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * 获取备注信息 reMark
	 */
	public String getReMark() {
		return reMark;
	}
	/**
	 * 设置备注信息 reMark
	 */
	public void setReMark(String reMark) {
		this.reMark = reMark;
	}
	/**
	 * 获取是否删除0：正常;9：删除 isAudit
	 */
	public String getIsAudit() {
		return isAudit;
	}
	/**
	 * 设置是否删除0：正常;9：删除 isAudit
	 */
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
}
