package com.zhl.job.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Accountflow {
	/**
	 * 主键id
	 */
    private String accountId;
    /**
     * 用户登录id
     */
    private String userId;
    /**
     * 子账号id：pid、cid
     */
    private String subaccnoId;
    /**
     * 电子账户号
     */
    private String accountNo;
    /**
     * 附言
     */
    private String additional;
    /**
     * 账户总额
     */
    private BigDecimal total;
    /**
     * 可用金额
     */
    private BigDecimal useAmount;
    /**
     * 冻结金额
     */
    private BigDecimal noUseAmount;
    /**
     * 交易金额
     */
    private BigDecimal amount;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 开户行
     */
    private String openBank;
    /**
     * 对方开户行
     */
    private String oppoBank;
    /**
     * 对方账号
     */
    private String oppoNo;
    /**
     * 摘要内容
     */
    private String summary;
    /**
     * 摘要代码
     */
    private String summaryCode;
    /**
     * 交易日期
     */
    private Date transDate;
    /**
     * 交易方向:00收入，01指支出，02转账
     */
    private String transDirection;
    /**
     * 流水号
     */
    private String transFlowNo;
    /**
     * 交易时间
     */
    private Date transTime;
    /**
     * 类型
     */
    private String type;
    /**
     * 状态
     */
    private String state;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 逻辑删除标识
     */
    private String isdel;
    
    
    // ----------get and set-------------
    /**
     * 通道返回的交易号
     */
    private String retTn;
	/**
	 * 获取主键id accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * 设置主键id accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * 获取用户登录id userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户登录id userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取子账号id：pid、cid subaccnoId
	 */
	public String getSubaccnoId() {
		return subaccnoId;
	}
	/**
	 * 设置子账号id：pid、cid subaccnoId
	 */
	public void setSubaccnoId(String subaccnoId) {
		this.subaccnoId = subaccnoId;
	}
	/**
	 * 获取电子账户号 accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * 设置电子账户号 accountNo
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * 获取附言 additional
	 */
	public String getAdditional() {
		return additional;
	}
	/**
	 * 设置附言 additional
	 */
	public void setAdditional(String additional) {
		this.additional = additional;
	}
	/**
	 * 获取账户总额 total
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * 设置账户总额 total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * 获取可用金额 useAmount
	 */
	public BigDecimal getUseAmount() {
		return useAmount;
	}
	/**
	 * 设置可用金额 useAmount
	 */
	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}
	/**
	 * 获取冻结金额 noUseAmount
	 */
	public BigDecimal getNoUseAmount() {
		return noUseAmount;
	}
	/**
	 * 设置冻结金额 noUseAmount
	 */
	public void setNoUseAmount(BigDecimal noUseAmount) {
		this.noUseAmount = noUseAmount;
	}
	/**
	 * 获取交易金额 amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置交易金额 amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * 获取账户余额 balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	/**
	 * 设置账户余额 balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取开户行 openBank
	 */
	public String getOpenBank() {
		return openBank;
	}
	/**
	 * 设置开户行 openBank
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	/**
	 * 获取对方开户行 oppoBank
	 */
	public String getOppoBank() {
		return oppoBank;
	}
	/**
	 * 设置对方开户行 oppoBank
	 */
	public void setOppoBank(String oppoBank) {
		this.oppoBank = oppoBank;
	}
	/**
	 * 获取对方账号 oppoNo
	 */
	public String getOppoNo() {
		return oppoNo;
	}
	/**
	 * 设置对方账号 oppoNo
	 */
	public void setOppoNo(String oppoNo) {
		this.oppoNo = oppoNo;
	}
	/**
	 * 获取摘要内容 summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * 设置摘要内容 summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * 获取摘要代码 summaryCode
	 */
	public String getSummaryCode() {
		return summaryCode;
	}
	/**
	 * 设置摘要代码 summaryCode
	 */
	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
	}
	/**
	 * 获取交易日期 transDate
	 */
	public Date getTransDate() {
		return transDate;
	}
	/**
	 * 设置交易日期 transDate
	 */
	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	/**
	 * 获取交易方向:00收入，01指支出，02转账 transDirection
	 */
	public String getTransDirection() {
		return transDirection;
	}
	/**
	 * 设置交易方向:00收入，01指支出，02转账 transDirection
	 */
	public void setTransDirection(String transDirection) {
		this.transDirection = transDirection;
	}
	/**
	 * 获取流水号 transFlowNo
	 */
	public String getTransFlowNo() {
		return transFlowNo;
	}
	/**
	 * 设置流水号 transFlowNo
	 */
	public void setTransFlowNo(String transFlowNo) {
		this.transFlowNo = transFlowNo;
	}
	/**
	 * 获取交易时间 transTime
	 */
	public Date getTransTime() {
		return transTime;
	}
	/**
	 * 设置交易时间 transTime
	 */
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
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
	 * 获取创建时间 createdate
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置创建时间 createdate
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
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
	/**
	 * 获取通道返回的交易号 retTn
	 */
	public String getRetTn() {
		return retTn;
	}
	/**
	 * 设置通道返回的交易号 retTn
	 */
	public void setRetTn(String retTn) {
		this.retTn = retTn;
	}
    
}