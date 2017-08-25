package com.zhl.job.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.WorkInfoAllowChooseDate;
import com.zhl.job.pojo.enums.WorkInfoBalanceType;
import com.zhl.job.pojo.enums.WorkInfoInterview;
import com.zhl.job.pojo.enums.WorkInfoJobType;
import com.zhl.job.pojo.enums.WorkInfoMeal;
import com.zhl.job.pojo.enums.WorkInfoMoneyType;
import com.zhl.job.pojo.enums.WorkInfoSexRequire;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.pojo.enums.WorkInfoType;

/**
 * 发布工作表实体类
 * @author 刘熙
 */
public class WorkInfo implements Serializable {

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
	 * 公司表id
	 */
	private String cid;
	/**
	 * 兼职标题
	 */
	private String workTitle;
	/**
	 * 兼职详细内容
	 */
	private String workDetail;
	/**
	 * 岗位名称
	 */
	private String jobName;
	/**
	 * 岗位类别
	 */
	private String jobType;
	/**
	 * 工作要求
	 */
	private String require;
	/**
	 * 性别要求
	 */
	private String sexRequire;
	/**
	 * 有无工作餐
	 */
	private String workMeal;
	/**
	 * 工作省
	 */
	private String province;
	/**
	 * 工作市
	 */
	private String city;
	/**
	 * 市名称
	 */
	private String cityName;
	/**
	 * 工作区
	 */
	private String area;
	
	/**
	 * 区名称
	 */
	private String areaName;
	/**
	 * 详细地址
	 */
	private String addressDetail;
	/**
	 * 申请起始时间
	 */
	private Date releaseStartDate;
	/**
	 * 申请截止时间
	 */
	private Date applyEndDate;
	/**
	 * 工作起始日
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date workStartDate;
	/**
	 * 工作结束日
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date workEndDate;
	/**
	 * 每日上班时间：几点到几点，或不固定
	 */
	private String workTime;
	/**
	 * 是否允许用户选择时间
	 * 00允许；01不允许
	 */
	private String allowChooseDate;
	/**
	 * 有效工作时长（当单位为小时时候使用）
	 */
	private String workHour;
	/**
	 * 招聘人数
	 */
	private String peopleNum;
	/**
	 * 结算方式
	 */
	private String balanceType;
	/**
	 * 金额
	 */
	private BigDecimal money;
	/**
	 * 金额单位：小时、天
	 */
	private String moneyType;
	/**
	 * 人均工作天数
	 */
	private String workdayNum;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 联系人手机号
	 */
	private String contactsMobile;
	/**
	 * 联系人邮箱
	 */
	private String contactsEmail;
	/**
	 * 发布必须同意条款
	 */
	private String agreement;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 发布类型（直接发布？预付？）
	 */
	private String releaseType;
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
	 * 修改时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date updateDate;
	/**
	 * 修改人
	 */
	private String updateName;
	/**
	 * 是否面试
	 */
	private String interview;
	
	/**
	 * 是否允许报名
	 */
	private String applySwitch;
	
	/**
	 * 逻辑删除标识
	 */
	private String isdel;
	
	/**
	 * 增量表一对一关系
	 */
	private WorkInfoStatis workInfoStatis;
	/**
	 * 公司表一对多关系
	 */
	private CompanyInfo companyInfo;
	
	// -------------------------以下参数只用于页面显示-----------------------------
	/**
	 * 岗位类别中文（只用于页面显示）
	 */
	private String jobTypeName;
	/**
	 * 性别要求中文（只用于页面显示）
	 */
	private String sexRequireName;
	/**
	 * 有无工作餐中文（只用于页面显示）
	 */
	private String workMealName;
	/**
	 * 结算方式中文（只用于页面显示）
	 */
	private String balanceTypeName;
	/**
	 * 金额单位：小时、天中文（只用于页面显示）
	 */
	private String moneyTypeName;
	/**
	 * 类型中文（只用于页面显示）
	 */
	private String typeName;
	/**
	 * 状态中文（只用于页面显示）
	 */
	private String stateName;
	/**
	 * 是否面试中文（只用于页面显示）
	 */
	private String interviewName;
	/**
	 * 逻辑删除标识中文（只用于页面显示）
	 */
	private String isdelName;
	/**
	 * 页面显示可申请的时间
	 */
	private List<Date> showDateList;
	
	/**
	 * 页面显示是否可选日期
	 */
	private String allowChooseDateStr;
	
	// -----------------------------get and set--------------------------
	/**
	 * 获取登录id userId
	 */
	public String getUserId() {
		return userId;
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
	 * 设置登录id userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取公司表id cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * 设置公司表id cid
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * 获取兼职标题 workTitle
	 */
	public String getWorkTitle() {
		return workTitle;
	}
	/**
	 * 设置兼职标题 workTitle
	 */
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	/**
	 * 获取兼职详细内容 workDetail
	 */
	public String getWorkDetail() {
		return workDetail;
	}
	/**
	 * 设置兼职详细内容 workDetail
	 */
	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}
	/**
	 * 获取岗位名称 jobName
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * 设置岗位名称 jobName
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * 获取岗位类别 jobType
	 */
	public String getJobType() {
		return jobType;
	}
	/**
	 * 设置岗位类别 jobType
	 */
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	/**
	 * 获取工作要求 require
	 */
	public String getRequire() {
		return require;
	}
	/**
	 * 设置工作要求 require
	 */
	public void setRequire(String require) {
		this.require = require;
	}
	/**
	 * 获取性别要求 sexRequire
	 */
	public String getSexRequire() {
		return sexRequire;
	}
	/**
	 * 设置性别要求 sexRequire
	 */
	public void setSexRequire(String sexRequire) {
		this.sexRequire = sexRequire;
	}
	/**
	 * 获取有无工作餐 workMeal
	 */
	public String getWorkMeal() {
		return workMeal;
	}
	/**
	 * 设置有无工作餐 workMeal
	 */
	public void setWorkMeal(String workMeal) {
		this.workMeal = workMeal;
	}
	/**
	 * 获取工作省 province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置工作省 province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取工作市 city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置工作市 city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取工作区 area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置工作区 area
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取市名称 cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置市名称 cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * 获取区名称 areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置区名称 areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取详细地址 addressDetail
	 */
	public String getAddressDetail() {
		return addressDetail;
	}
	/**
	 * 设置详细地址 addressDetail
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	/**
	 * 获取申请起始时间 releaseStartDate
	 */
	public Date getReleaseStartDate() {
		return releaseStartDate;
	}
	/**
	 * 设置申请起始时间 releaseStartDate
	 */
	public void setReleaseStartDate(Date releaseStartDate) {
		this.releaseStartDate = releaseStartDate;
	}
	/**
	 * 获取申请截止时间 applyEndDate
	 */
	public Date getApplyEndDate() {
		return applyEndDate;
	}
	/**
	 * 设置申请截止时间 applyEndDate
	 */
	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}
	/**
	 * 获取工作起始日 workStartDate
	 */
	public Date getWorkStartDate() {
		return workStartDate;
	}
	/**
	 * 设置工作起始日 workStartDate
	 */
	public void setWorkStartDate(Date workStartDate) {
		this.workStartDate = workStartDate;
	}
	/**
	 * 获取工作结束日 workEndDate
	 */
	public Date getWorkEndDate() {
		return workEndDate;
	}
	/**
	 * 设置工作结束日 workEndDate
	 */
	public void setWorkEndDate(Date workEndDate) {
		this.workEndDate = workEndDate;
	}
	/**
	 * 获取每日上班时间：几点到几点，或不固定 workTime
	 */
	public String getWorkTime() {
		return workTime;
	}
	/**
	 * 设置每日上班时间：几点到几点，或不固定 workTime
	 */
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	/**
	 * 获取是否允许用户选择时间00允许；01不允许 allowChooseDate
	 */
	public String getAllowChooseDate() {
		return allowChooseDate;
	}
	/**
	 * 设置是否允许用户选择时间00允许；01不允许 allowChooseDate
	 */
	public void setAllowChooseDate(String allowChooseDate) {
		this.allowChooseDate = allowChooseDate;
	}
	/**
	 * 获取有效工作时长（当单位为小时时候使用） workHour
	 */
	public String getWorkHour() {
		return workHour;
	}
	/**
	 * 设置有效工作时长（当单位为小时时候使用） workHour
	 */
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	/**
	 * 获取招聘人数 peopleNum
	 */
	public String getPeopleNum() {
		return peopleNum;
	}
	/**
	 * 设置招聘人数 peopleNum
	 */
	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}
	/**
	 * 获取结算方式 balanceType
	 */
	public String getBalanceType() {
		return balanceType;
	}
	/**
	 * 设置结算方式 balanceType
	 */
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	/**
	 * 获取金额 money
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置金额 money
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取金额单位：小时、天 moneyType
	 */
	public String getMoneyType() {
		return moneyType;
	}
	/**
	 * 设置金额单位：小时、天 moneyType
	 */
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	/**
	 * 获取人均工作天数 workdayNum
	 */
	public String getWorkdayNum() {
		return workdayNum;
	}
	/**
	 * 设置人均工作天数 workdayNum
	 */
	public void setWorkdayNum(String workdayNum) {
		this.workdayNum = workdayNum;
	}
	/**
	 * 获取联系人 contacts
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * 设置联系人 contacts
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * 获取联系人手机号 contactsMobile
	 */
	public String getContactsMobile() {
		return contactsMobile;
	}
	/**
	 * 设置联系人手机号 contactsMobile
	 */
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}
	/**
	 * 获取联系人邮箱 contactsEmail
	 */
	public String getContactsEmail() {
		return contactsEmail;
	}
	/**
	 * 设置联系人邮箱 contactsEmail
	 */
	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}
	/**
	 * 获取发布必须同意条款 agreement
	 */
	public String getAgreement() {
		return agreement;
	}
	/**
	 * 设置发布必须同意条款 agreement
	 */
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	/**
	 * 获取经度 longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * 设置经度 longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取纬度 latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * 设置纬度 latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取预付类型 releaseType
	 */
	public String getReleaseType() {
		return releaseType;
	}
	/**
	 * 设置预付类型 releaseType
	 */
	public void setReleaseType(String releaseType) {
		this.releaseType = releaseType;
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
	 * 获取是否面试 interview
	 */
	public String getInterview() {
		return interview;
	}
	/**
	 * 设置是否面试 interview
	 */
	public void setInterview(String interview) {
		this.interview = interview;
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
	 * 获取增量表一对一关系 workInfoStatis
	 */
	public WorkInfoStatis getWorkInfoStatis() {
		return workInfoStatis;
	}
	/**
	 * 设置增量表一对一关系 workInfoStatis
	 */
	public void setWorkInfoStatis(WorkInfoStatis workInfoStatis) {
		this.workInfoStatis = workInfoStatis;
	}
	/**
	 * 获取是否允许报名 applySwitch
	 */
	public String getApplySwitch() {
		return applySwitch;
	}
	/**
	 * 设置是否允许报名 applySwitch
	 */
	public void setApplySwitch(String applySwitch) {
		this.applySwitch = applySwitch;
	}
	
	// -------------------------以下参数只用于页面显示-----------------------------
	
	/**
	 * 获取修改时间 updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置修改时间 updateDate
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
	 * 获取公司表一对多关系 companyInfo
	 */
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	/**
	 * 设置公司表一对多关系 companyInfo
	 */
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	/**
	 * 获取岗位类别中文（只用于页面显示）
	 */
	public String getJobTypeName() {
		if(null != jobType && !"".equals(jobType.trim())){
			return WorkInfoJobType.getText(jobType);
		}
		return null;
	}
	/**
	 * 获取性别要求中文（只用于页面显示） sexRequireName
	 */
	public String getSexRequireName() {
		if(null != sexRequire && !"".equals(sexRequire.trim())){
			return WorkInfoSexRequire.getText(sexRequire);
		}
		return null;
	}
	/**
	 * 获取有无工作餐中文（只用于页面显示） workMealName
	 */
	public String getWorkMealName() {
		if(null != workMeal && !"".equals(workMeal.trim())){
			return WorkInfoMeal.getText(workMeal);
		}
		return null;
	}
	/**
	 * 获取结算方式中文（只用于页面显示） balanceTypeName
	 */
	public String getBalanceTypeName() {
		if(null != balanceType && !"".equals(balanceType.trim())){
			return WorkInfoBalanceType.getText(balanceType);
		}
		return null;
	}
	/**
	 * 获取金额单位：小时、天中文（只用于页面显示） moneyTypeName
	 */
	public String getMoneyTypeName() {
		if(null != moneyType && !"".equals(moneyType.trim())){
			return WorkInfoMoneyType.getText(moneyType);
		}
		return null;
	}
	/**
	 * 获取类型中文（只用于页面显示） typeName
	 */
	public String getTypeName() {
		if(null != type && !"".equals(type.trim())){
			return WorkInfoType.getText(type);
		}
		return null;
	}
	/**
	 * 获取状态中文（只用于页面显示） stateName
	 */
	public String getStateName() {
		if(null != state && !"".equals(state.trim())){
			return WorkInfoState.getText(state);
		}
		return null;
	}
	/**
	 * 获取是否面试中文（只用于页面显示） interviewName
	 */
	public String getInterviewName() {
		if(null != interview && !"".equals(interview.trim())){
			return WorkInfoInterview.getText(interview);
		}
		return null;
	}
	/**
	 * 获取逻辑删除标识中文（只用于页面显示） isdelName
	 */
	public String getIsdelName() {
		if(null != isdel && !"".equals(isdel.trim())){
			return IsDel.getText(isdel);
		}
		return null;
	}
	/**
	 * 获取页面显示可申请的时间 showDateList
	 */
	public List<Date> getShowDateList() {
		return showDateList;
	}
	/**
	 * 设置页面显示可申请的时间 showDateList
	 */
	public void setShowDateList(List<Date> showDateList) {
		this.showDateList = showDateList;
	}
	/**
	 * 获取页面显示是否可选日期 allowChooseDateStr
	 */
	public String getAllowChooseDateStr() {
		if(null != allowChooseDate && !"".equals(allowChooseDate.trim())){
			return WorkInfoAllowChooseDate.getText(allowChooseDate);
		}
		return null;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorkInfo [id=" + id + ", userId=" + userId + ", cid=" + cid + ", workTitle=" + workTitle
                + ", workDetail=" + workDetail + ", jobName=" + jobName + ", jobType=" + jobType + ", require="
                + require + ", sexRequire=" + sexRequire + ", workMeal=" + workMeal + ", province=" + province
                + ", city=" + city + ", cityName=" + cityName + ", area=" + area + ", areaName=" + areaName
                + ", addressDetail=" + addressDetail + ", releaseStartDate=" + releaseStartDate + ", applyEndDate="
                + applyEndDate + ", workStartDate=" + workStartDate + ", workEndDate=" + workEndDate + ", workTime="
                + workTime + ", allowChooseDate=" + allowChooseDate + ", workHour=" + workHour + ", peopleNum="
                + peopleNum + ", balanceType=" + balanceType + ", money=" + money + ", moneyType=" + moneyType
                + ", workdayNum=" + workdayNum + ", contacts=" + contacts + ", contactsMobile=" + contactsMobile
                + ", contactsEmail=" + contactsEmail + ", agreement=" + agreement + ", longitude=" + longitude
                + ", latitude=" + latitude + ", releaseType=" + releaseType + ", type=" + type + ", state=" + state
                + ", createdate=" + createdate + ", updateDate=" + updateDate + ", updateName=" + updateName
                + ", interview=" + interview + ", applySwitch=" + applySwitch + ", isdel=" + isdel
                + ", workInfoStatis=" + workInfoStatis + ", companyInfo=" + companyInfo + ", jobTypeName="
                + jobTypeName + ", sexRequireName=" + sexRequireName + ", workMealName=" + workMealName
                + ", balanceTypeName=" + balanceTypeName + ", moneyTypeName=" + moneyTypeName + ", typeName="
                + typeName + ", stateName=" + stateName + ", interviewName=" + interviewName + ", isdelName="
                + isdelName + ", showDateList=" + showDateList + ", allowChooseDateStr=" + allowChooseDateStr + "]";
    }
	
	
}
