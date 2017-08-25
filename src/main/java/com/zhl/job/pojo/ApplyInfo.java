package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.zhl.job.pojo.enums.ApplyInfoEmploy;

/**
 * 职位申请表实体
 * @author 刘熙
 */
public class ApplyInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 登录id
	 */
	private String userId;
	/**
	 * 用户表id
	 */
	private String pid;
	/**
	 * 职位表id
	 */
	private String workId;
	/**
	 * 申请内容
	 */
	private String applyDetail;
	/**
	 * 申请工作天
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date applyWorkDate;
	/**
	 * 申请工作时间
	 */
	private String applyWorkTime;
	/**
	 * 评论
	 */
	private String appComment;
	/**
	 * 是否录用
	 */
	private String employ;
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
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date createdate;
	/**
	 * 逻辑删除标识
	 */
	private String isdel;
	/**
	 * 申请的工作实体，一对一
	 */
	private WorkInfo workInfo;
	/**
	 * 申请工作时间集合(用于传参，数据库中无此字段)
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private List<Date> applyWorkDays;
	
	private UserInfo userInfo;
	
	private WorkInfoStatis workInfoStatis;
	
	private SettlementInfoLog settlementInfoLog;
	
	private CompanyInfo companyInfo;
	
	/*
	 * ==================== 以下用于页面显示=====================
	 */
	/**
	 * 是否录用显示字段
	 */
	private String employStr;
	
	/**
	 * 参加过此类工作
	 */
	private String attendedStr;
	
	
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
	 * 获取登录id userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置登录id userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * 获取职位表id workId
	 */
	public String getWorkId() {
		return workId;
	}
	/**
	 * 设置职位表id workId
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	/**
	 * 获取申请内容 applyDetail
	 */
	public String getApplyDetail() {
		return applyDetail;
	}
	/**
	 * 设置申请内容 applyDetail
	 */
	public void setApplyDetail(String applyDetail) {
		this.applyDetail = applyDetail;
	}
	/**
	 * 获取申请工作天 applyWorkDate
	 */
	public Date getApplyWorkDate() {
		return applyWorkDate;
	}
	/**
	 * 设置申请工作天 applyWorkDate
	 */
	public void setApplyWorkDate(Date applyWorkDate) {
		this.applyWorkDate = applyWorkDate;
	}
	/**
	 * 获取申请工作时间 applyWorkTime
	 */
	public String getApplyWorkTime() {
		return applyWorkTime;
	}
	/**
	 * 设置申请工作时间 applyWorkTime
	 */
	public void setApplyWorkTime(String applyWorkTime) {
		this.applyWorkTime = applyWorkTime;
	}
	/**
	 * 获取评论 appComment
	 */
	public String getAppComment() {
		return appComment;
	}
	/**
	 * 设置评论 appComment
	 */
	public void setAppComment(String appComment) {
		this.appComment = appComment;
	}
	/**
	 * 获取是否录用 employ
	 */
	public String getEmploy() {
		return employ;
	}
	/**
	 * 设置是否录用 employ
	 */
	public void setEmploy(String employ) {
		this.employ = employ;
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
	 * 获取工作日期集合
	 */
	public List<Date> getApplyWorkDays() {
		return applyWorkDays;
	}
	/**
	 * 设置工作日期集合
	 */
	public void setApplyWorkDays(List<Date> applyWorkDays) {
		this.applyWorkDays = applyWorkDays;
	}
	/**
	 * 获取workInfo workInfo
	 */
	public WorkInfo getWorkInfo() {
		return workInfo;
	}
	/**
	 * 设置workInfo workInfo
	 */
	public void setWorkInfo(WorkInfo workInfo) {
		this.workInfo = workInfo;
	}
	/**
	 * 获取userInfo userInfo
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}
	/**
	 * 设置userInfo userInfo
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	/**
	 * 获取workInfoStatis workInfoStatis
	 */
	public WorkInfoStatis getWorkInfoStatis() {
		return workInfoStatis;
	}
	/**
	 * 设置workInfoStatis workInfoStatis
	 */
	public void setWorkInfoStatis(WorkInfoStatis workInfoStatis) {
		this.workInfoStatis = workInfoStatis;
	}
	/**
	 * 获取是否录用显示字段 employStr
	 */
	public String getEmployStr() {
		if(null != employ && !"".equals(employ.trim())){
			return ApplyInfoEmploy.getText(employ);
		}
		return null;
	}
	/**
	 * 获取settlementInfoLog settlementInfoLog
	 */
	public SettlementInfoLog getSettlementInfoLog() {
		return settlementInfoLog;
	}
	/**
	 * 设置settlementInfoLog settlementInfoLog
	 */
	public void setSettlementInfoLog(SettlementInfoLog settlementInfoLog) {
		this.settlementInfoLog = settlementInfoLog;
	}
	/**
	 * 获取companyInfo companyInfo
	 */
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	/**
	 * 设置companyInfo companyInfo
	 */
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	/**
	 * 获取参加过此类工作 attendedStr
	 */
	public String getAttendedStr() {
		return attendedStr;
	}
	/**
	 * 设置参加过此类工作 attendedStr
	 */
	public void setAttendedStr(String attendedStr) {
		this.attendedStr = attendedStr;
	}
	
    @Override
    public String toString() {
        return "ApplyInfo [id=" + id + ", userId=" + userId + ", pid=" + pid + ", workId=" + workId + ", applyDetail="
                + applyDetail + ", applyWorkDate=" + applyWorkDate + ", applyWorkTime=" + applyWorkTime
                + ", appComment=" + appComment + ", employ=" + employ + ", type=" + type + ", state=" + state
                + ", createdate=" + createdate + ", isdel=" + isdel + ", workInfo=" + workInfo + ", applyWorkDays="
                + applyWorkDays + ", userInfo=" + userInfo + ", workInfoStatis=" + workInfoStatis
                + ", settlementInfoLog=" + settlementInfoLog + ", companyInfo=" + companyInfo + ", employStr="
                + employStr + ", attendedStr=" + attendedStr + "]";
    }
	

}
