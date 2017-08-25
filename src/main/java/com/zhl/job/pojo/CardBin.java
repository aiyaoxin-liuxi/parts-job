package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * cardBin信息
 * @author 刘熙
 */
public class CardBin implements Serializable {
	
    private static final long serialVersionUID = 1L;
    /**
	 * id
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createdDate;
	private String bankinfoId;
	/**
	 * 卡bin
	 */
	private String cardBin;
	/**
	 * 卡bin长度
	 */
	private String cardBinLen;
	/**
	 * 卡长度
	 */
	private String cardLen;
	/**
	 * 卡bin类型01：借记卡;02：贷记卡;03：借贷卡
	 */
	private String cardBinType;
	/**
	 * 发卡银行编码
	 */
	private String issincCode;
	/**
	 * 发卡银行名称
	 */
	private String issincName;
	private String cardName;
	/**
	 * 状态;00:启用;01:禁用
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
	private String remark;
	/**
	 * 是否删除;0：正常;9：删除
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
	 * 获取创建时间 createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	/**
	 * 设置创建时间 createdDate
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * 获取bankinfoId bankinfoId
	 */
	public String getBankinfoId() {
		return bankinfoId;
	}
	/**
	 * 设置bankinfoId bankinfoId
	 */
	public void setBankinfoId(String bankinfoId) {
		this.bankinfoId = bankinfoId;
	}
	/**
	 * 获取卡bin cardBin
	 */
	public String getCardBin() {
		return cardBin;
	}
	/**
	 * 设置卡bin cardBin
	 */
	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}
	/**
	 * 获取卡bin长度 cardBinLen
	 */
	public String getCardBinLen() {
		return cardBinLen;
	}
	/**
	 * 设置卡bin长度 cardBinLen
	 */
	public void setCardBinLen(String cardBinLen) {
		this.cardBinLen = cardBinLen;
	}
	/**
	 * 获取卡长度 cardLen
	 */
	public String getCardLen() {
		return cardLen;
	}
	/**
	 * 设置卡长度 cardLen
	 */
	public void setCardLen(String cardLen) {
		this.cardLen = cardLen;
	}
	/**
	 * 获取卡bin类型01：借记卡;02：贷记卡;03：借贷卡 cardBinType
	 */
	public String getCardBinType() {
		return cardBinType;
	}
	/**
	 * 设置卡bin类型01：借记卡;02：贷记卡;03：借贷卡 cardBinType
	 */
	public void setCardBinType(String cardBinType) {
		this.cardBinType = cardBinType;
	}
	/**
	 * 获取发卡银行编码 issincCode
	 */
	public String getIssincCode() {
		return issincCode;
	}
	/**
	 * 设置发卡银行编码 issincCode
	 */
	public void setIssincCode(String issincCode) {
		this.issincCode = issincCode;
	}
	/**
	 * 获取发卡银行名称 issincName
	 */
	public String getIssincName() {
		return issincName;
	}
	/**
	 * 设置发卡银行名称 issincName
	 */
	public void setIssincName(String issincName) {
		this.issincName = issincName;
	}
	/**
	 * 获取cardName cardName
	 */
	public String getCardName() {
		return cardName;
	}
	/**
	 * 设置cardName cardName
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	/**
	 * 获取状态;00:启用;01:禁用 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置状态;00:启用;01:禁用 state
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
	 * 获取备注信息 remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置备注信息 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取是否删除;0：正常;9：删除 isAudit
	 */
	public String getIsAudit() {
		return isAudit;
	}
	/**
	 * 设置是否删除;0：正常;9：删除 isAudit
	 */
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}
	
}
