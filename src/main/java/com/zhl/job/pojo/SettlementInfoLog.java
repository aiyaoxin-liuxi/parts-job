package com.zhl.job.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算记录表实体类
 * 
 * @author 刘熙
 */

public class SettlementInfoLog implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 结算单id
	 */
	private String settlementId;
	/**
	 * 兼职用户登录id
	 */
	private String pUserId;
	/**
	 * 企业用户登录id
	 */
	private String cUserId;
	/**
	 * 用户表id
	 */
	private String pid;
	/**
	 * 企业用户id
	 */
	private String cid;
	/**
	 * 工作id
	 */
	private String workId;
	/**
	 * 申请id
	 */
	private String applyId;
	/**
	 * 结算日期
	 */
	private Date settlementDate;
	/**
	 * 工作天
	 */
	private Date workDay;
	/**
	 * 预计此人工资金额
	 */
	private BigDecimal advancePay;
	/**
	 * 实际应付此人工资金额
	 */
	private BigDecimal accountPay;
	/**
	 * 备注
	 */
	private String remark;
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
	
	private SettlementInfo settlementInfo;
	
	// ===================== 一下为页面传值显示，不是哭中字段实体
	
	private String appComment;
	
	
	// get and set
	
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
	 * 获取兼职用户登录id pUserId
	 */
	public String getpUserId() {
		return pUserId;
	}
	/**
	 * 设置兼职用户登录id pUserId
	 */
	public void setpUserId(String pUserId) {
		this.pUserId = pUserId;
	}
	/**
	 * 获取企业用户登录id cUserId
	 */
	public String getcUserId() {
		return cUserId;
	}
	/**
	 * 设置企业用户登录id cUserId
	 */
	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
	/**
	 * 获取结算单id settlementId
	 */
	public String getSettlementId() {
		return settlementId;
	}
	/**
	 * 设置结算单id settlementId
	 */
	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}
	/**
	 * 获取用户表id pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * 设置用户表id pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取企业用户id cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * 设置企业用户id cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
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
	 * 获取预计此人工资金额 advancePay
	 */
	public BigDecimal getAdvancePay() {
		return advancePay;
	}
	/**
	 * 设置预计此人工资金额 advancePay
	 */
	public void setAdvancePay(BigDecimal advancePay) {
		this.advancePay = advancePay;
	}
	/**
	 * 获取实际应付此人工资金额 accountPay
	 */
	public BigDecimal getAccountPay() {
		return accountPay;
	}
	/**
	 * 设置实际应付此人工资金额 accountPay
	 */
	public void setAccountPay(BigDecimal accountPay) {
		this.accountPay = accountPay;
	}
	/**
	 * 获取备注 remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 获取结算日期 settlementDate
	 */
	public Date getSettlementDate() {
		return settlementDate;
	}
	/**
	 * 设置结算日期 settlementDate
	 */
	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * 获取工作天 workDay
	 */
	public Date getWorkDay() {
		return workDay;
	}
	/**
	 * 设置工作天 workDay
	 */
	public void setWorkDay(Date workDay) {
		this.workDay = workDay;
	}
	/**
	 * 获取申请id applyId
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置申请id applyId
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取appComment appComment
	 */
	public String getAppComment() {
		return appComment;
	}
	/**
	 * 设置appComment appComment
	 */
	public void setAppComment(String appComment) {
		this.appComment = appComment;
	}
	/**
	 * 获取settlementInfo settlementInfo
	 */
	public SettlementInfo getSettlementInfo() {
		return settlementInfo;
	}
	/**
	 * 设置settlementInfo settlementInfo
	 */
	public void setSettlementInfo(SettlementInfo settlementInfo) {
		this.settlementInfo = settlementInfo;
	}
    @Override
    public String toString() {
        return "SettlementInfoLog [id=" + id + ", settlementId=" + settlementId + ", pUserId=" + pUserId + ", cUserId="
                + cUserId + ", pid=" + pid + ", cid=" + cid + ", workId=" + workId + ", applyId=" + applyId
                + ", settlementDate=" + settlementDate + ", workDay=" + workDay + ", advancePay=" + advancePay
                + ", accountPay=" + accountPay + ", remark=" + remark + ", type=" + type + ", state=" + state
                + ", createdate=" + createdate + ", isdel=" + isdel + ", settlementInfo=" + settlementInfo
                + ", appComment=" + appComment + "]";
    }
	
}
