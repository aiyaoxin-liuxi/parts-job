package com.zhl.job.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 结算单表实体类
 * 
 * @author 刘熙
 */

public class SettlementInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 企业id
	 */
	private String cid;
	/**
	 * 工作id
	 */
	private String workId;
	/**
	 * 结算日期
	 */
	private Date settlementDate;
	/**
	 * 工作天
	 */
	private Date workDay;
	/**
	 * 实际应付所有人总金额
	 */
	private BigDecimal accountTotal;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 结算状态
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
	
	/**
	 * 实体
	 */
	private List<SettlementInfoLog> sList;
	
	// get and set
	
//	private SettlementInfoLog settlementInfoLog;
	
	private WorkInfo workInfo;
	
	
	
	
	public WorkInfo getWorkInfo() {
		return workInfo;
	}
	public void setWorkInfo(WorkInfo workInfo) {
		this.workInfo = workInfo;
	}
//	public SettlementInfoLog getSettlementInfoLog() {
//		return settlementInfoLog;
//	}
//	public void setSettlementInfoLog(SettlementInfoLog settlementInfoLog) {
//		this.settlementInfoLog = settlementInfoLog;
//	}
	/**
	 * 获取实际应付所有人总金额 accountTotal
	 */
	public BigDecimal getAccountTotal() {
		return accountTotal;
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
	 * 设置实际应付所有人总金额 accountTotal
	 */
	public void setAccountTotal(BigDecimal accountTotal) {
		this.accountTotal = accountTotal;
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
	 * 获取结算状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置结算状态 state
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
	 * 获取实体 sList
	 */
	public List<SettlementInfoLog> getsList() {
		return sList;
	}
	/**
	 * 设置实体 sList
	 */
	public void setsList(List<SettlementInfoLog> sList) {
		this.sList = sList;
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
	 * 获取企业id cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * 设置企业id cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
    @Override
    public String toString() {
        return "SettlementInfo [id=" + id + ", cid=" + cid + ", workId=" + workId + ", settlementDate="
                + settlementDate + ", workDay=" + workDay + ", accountTotal=" + accountTotal + ", type=" + type
                + ", state=" + state + ", createdate=" + createdate + ", isdel=" + isdel + ", sList=" + sList
                + ", workInfo=" + workInfo + "]";
    }
	
}
