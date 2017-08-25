package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 兼职用户表实体类
 * 
 * @author 刘熙
 */

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 兼职用户id
	 */
	private String pid;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户手机号
	 */
	private String mobile;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 身份证件类型
	 */
	private String idType;
	/**
	 * 身份证件号
	 */
	private String idNumber;
	/**
	 * 现居住省
	 */
	private String liveProvince;
	/**
	 * 现居住城市
	 */
	private String liveCity;
	/**
	 * 现居住区县
	 */
	private String liveArea;
	/**
	 * 教育状态：毕业、在读
	 */
	private String educationState;
	/**
	 * 身份证正面照片地址
	 */
	private String idFrontImg;
	/**
	 * 身份证背面照片地址
	 */
	private String idBackImg;
	/**
	 * 手持身份证照片地址
	 */
	private String idPortraitImg;
	/**
	 * 手持身份证拍摄地址
	 */
	private String idShootMv;
	/**
	 * 学校
	 */
	private String graduateSchool;
	/**
	 * 入学年份
	 */
	private String schoolYear;
	/**
	 * 系别
	 */
	private String faculty;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 学信网截图地址
	 */
	private String networkIdImg;
	/**
	 * 手持学生证照片地址
	 */
	private String sidPortraitImg;
	/**
	 * 头像地址
	 */
	private String headImg;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * 自我评价
	 */
	private String comment;
	/**
	 * 实名等级
	 */
	private String realLevel;
	/**
	 * 用户等级
	 */
	private String level;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 用户状态
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
	 * 获取兼职用户id pid
	 */
	public String getPid() {
		return pid;
	}
	
	/**
	 * 设置兼职用户id pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * 获取用户id userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置用户id userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取用户手机号 mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置用户手机号 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取真实姓名 realname
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置真实姓名 realname
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取性别 sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置性别 sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取身份证件类型 idType
	 */
	public String getIdType() {
		return idType;
	}
	/**
	 * 设置身份证件类型 idType
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}
	/**
	 * 获取身份证件号 idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * 设置身份证件号 idNumber
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	/**
	 * 获取现居住省 liveProvince
	 */
	public String getLiveProvince() {
		return liveProvince;
	}
	/**
	 * 设置现居住省 liveProvince
	 */
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}
	/**
	 * 获取现居住城市 liveCity
	 */
	public String getLiveCity() {
		return liveCity;
	}
	/**
	 * 设置现居住城市 liveCity
	 */
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	/**
	 * 获取现居住区县 liveArea
	 */
	public String getLiveArea() {
		return liveArea;
	}
	/**
	 * 设置现居住区县 liveArea
	 */
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}
	/**
	 * 获取教育状态：毕业、在读 educationState
	 */
	public String getEducationState() {
		return educationState;
	}
	/**
	 * 设置教育状态：毕业、在读 educationState
	 */
	public void setEducationState(String educationState) {
		this.educationState = educationState;
	}
	/**
	 * 获取身份证正面照片地址 idFrontImg
	 */
	public String getIdFrontImg() {
		return idFrontImg;
	}
	/**
	 * 设置身份证正面照片地址 idFrontImg
	 */
	public void setIdFrontImg(String idFrontImg) {
		this.idFrontImg = idFrontImg;
	}
	/**
	 * 获取身份证背面照片地址 idBackImg
	 */
	public String getIdBackImg() {
		return idBackImg;
	}
	/**
	 * 设置身份证背面照片地址 idBackImg
	 */
	public void setIdBackImg(String idBackImg) {
		this.idBackImg = idBackImg;
	}
	/**
	 * 获取手持身份证照片地址 idPortraitImg
	 */
	public String getIdPortraitImg() {
		return idPortraitImg;
	}
	/**
	 * 设置手持身份证照片地址 idPortraitImg
	 */
	public void setIdPortraitImg(String idPortraitImg) {
		this.idPortraitImg = idPortraitImg;
	}
	/**
	 * 获取手持身份证拍摄地址 idShootMv
	 */
	public String getIdShootMv() {
		return idShootMv;
	}
	/**
	 * 设置手持身份证拍摄地址 idShootMv
	 */
	public void setIdShootMv(String idShootMv) {
		this.idShootMv = idShootMv;
	}
	/**
	 * 获取学校 graduateSchool
	 */
	public String getGraduateSchool() {
		return graduateSchool;
	}
	/**
	 * 设置学校 graduateSchool
	 */
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	/**
	 * 获取入学年份 schoolYear
	 */
	public String getSchoolYear() {
		return schoolYear;
	}
	/**
	 * 设置入学年份 schoolYear
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	/**
	 * 获取系别 faculty
	 */
	public String getFaculty() {
		return faculty;
	}
	/**
	 * 设置系别 faculty
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	/**
	 * 获取学历 education
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * 设置学历 education
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * 获取学信网截图地址 networkIdImg
	 */
	public String getNetworkIdImg() {
		return networkIdImg;
	}
	/**
	 * 设置学信网截图地址 networkIdImg
	 */
	public void setNetworkIdImg(String networkIdImg) {
		this.networkIdImg = networkIdImg;
	}
	/**
	 * 获取手持学生证照片地址 sidPortraitImg
	 */
	public String getSidPortraitImg() {
		return sidPortraitImg;
	}
	/**
	 * 设置手持学生证照片地址 sidPortraitImg
	 */
	public void setSidPortraitImg(String sidPortraitImg) {
		this.sidPortraitImg = sidPortraitImg;
	}
	/**
	 * 获取头像地址 headImg
	 */
	public String getHeadImg() {
		return headImg;
	}
	/**
	 * 设置头像地址 headImg
	 */
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	/**
	 * 获取邮箱 email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置邮箱 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取QQ qq
	 */
	public String getQq() {
		return qq;
	}
	/**
	 * 设置QQ qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 获取微信 wechat
	 */
	public String getWechat() {
		return wechat;
	}
	/**
	 * 设置微信 wechat
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	/**
	 * 获取自我评价 comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置自我评价 comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取实名等级 realLevel
	 */
	public String getRealLevel() {
		return realLevel;
	}
	/**
	 * 设置实名等级 realLevel
	 */
	public void setRealLevel(String realLevel) {
		this.realLevel = realLevel;
	}
	/**
	 * 获取用户等级 level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * 设置用户等级 level
	 */
	public void setLevel(String level) {
		this.level = level;
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
	 * 获取用户状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置用户状态 state
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
}
