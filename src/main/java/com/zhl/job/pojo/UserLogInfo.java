package com.zhl.job.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户登录表实体类
 * 
 * @author 刘熙
 */
public class UserLogInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 子账号id：uid、cid
	 */
	private String subaccnoId;
	/**
	 * 电子账户号
	 */
	private String accountNo;
	/**
	 * 用户登录手机号
	 */
	private String mobile;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 微信名
	 */
	private String wechatName;
	/**
	 * 登录密码
	 */
	private String logPassword;
	/**
	 * 用户类型
	 */
	private String userType;
	/**
	 * 用户状态
	 */
	private String userState;
	/**
	 * 账户认证状态
	 */
	private String level;
	/**
	 * 身份认证状态
	 */
	private String idCheckState;
	/**
	 * 支付密码
	 */
	private String payPassword;
	/**
	 * 账户类型
	 */
	private String accountType;
	/**
	 * 账户状态
	 */
	private String accountState;
	/**
	 * 冻结状态
	 */
	private String freezeState;
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
	 * 预付款金额
	 */
	private String advanceCharge;
	/**
	 * 预留字段1
	 */
	private String rev1;
	/**
	 * 预留字段2
	 */
	private String rev2;
	/**
	 * 预留字段3
	 */
	private String rev3;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 逻辑删除标识
	 */
	private String isdel;
	
	/**
	 * 邀请码
	 */
	private String inviteCode;
	
	//修改时间，修改人
	
	private Date updateDate;//UPDATE_DATE
	
	private String updateUserId;//UPDATE_USER_ID
	
		
	// get and set
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 获取用户id 用户id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置用户id 用户id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取子账号id：uid、cid subaccnoId
	 */
	public String getSubaccnoId() {
		return subaccnoId;
	}
	/**
	 * 设置子账号id：uid、cid subaccnoId
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
	 * 获取用户登录手机号 mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置用户登录手机号 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取用户邮箱 email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置用户邮箱 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取微信名 wechatName
	 */
	public String getWechatName() {
		return wechatName;
	}
	/**
	 * 设置微信名 wechatName
	 */
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	/**
	 * 获取登录密码 logPassword
	 */
	public String getLogPassword() {
		return logPassword;
	}
	/**
	 * 设置登录密码 logPassword
	 */
	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}
	/**
	 * 获取用户类型 userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置用户类型 userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取用户状态 userState
	 */
	public String getUserState() {
		return userState;
	}
	/**
	 * 设置用户状态 userState
	 */
	public void setUserState(String userState) {
		this.userState = userState;
	}
	/**
	 * 获取账户认证状态 level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置账户认证状态 level
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取身份认证状态 idCheckState
	 */
	public String getIdCheckState() {
		return idCheckState;
	}
	/**
	 * 设置身份认证状态 idCheckState
	 */
	public void setIdCheckState(String idCheckState) {
		this.idCheckState = idCheckState;
	}
	/**
	 * 获取支付密码 payPassword
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * 设置支付密码 payPassword
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/**
	 * 获取账户类型 accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * 设置账户类型 accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * 获取账户状态 accountState
	 */
	public String getAccountState() {
		return accountState;
	}
	/**
	 * 设置账户状态 accountState
	 */
	public void setAccountState(String accountState) {
		this.accountState = accountState;
	}
	/**
	 * 获取冻结状态 freezeState
	 */
	public String getFreezeState() {
		return freezeState;
	}
	/**
	 * 设置冻结状态 freezeState
	 */
	public void setFreezeState(String freezeState) {
		this.freezeState = freezeState;
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
	 * 获取预付款金额 advanceCharge
	 */
	public String getAdvanceCharge() {
		return advanceCharge;
	}
	/**
	 * 设置预付款金额 advanceCharge
	 */
	public void setAdvanceCharge(String advanceCharge) {
		this.advanceCharge = advanceCharge;
	}
	/**
	 * 获取预留字段1 rev1
	 */
	public String getRev1() {
		return rev1;
	}
	/**
	 * 设置预留字段1 rev1
	 */
	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}
	/**
	 * 获取预留字段2 rev2
	 */
	public String getRev2() {
		return rev2;
	}
	/**
	 * 设置预留字段2 rev2
	 */
	public void setRev2(String rev2) {
		this.rev2 = rev2;
	}
	/**
	 * 获取预留字段3 rev3
	 */
	public String getRev3() {
		return rev3;
	}
	/**
	 * 设置预留字段3 rev3
	 */
	public void setRev3(String rev3) {
		this.rev3 = rev3;
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
	
}
