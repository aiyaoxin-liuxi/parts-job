package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

import com.zhl.job.pojo.enums.company.CompanyType;

/**
 * 企业表表实体类
 * 
 * @author 刘熙
 */

public class CompanyInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 企业用户id
	 */
	private String cid;
	/**
	 * 登录id
	 */
	private String userId;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 企业logo地址
	 */
	private String logoImg;
	/**
	 * 企业性质
	 */
	private String companyType;
	/**
	 * 企业规模(公司人数)
	 */
	private String companyPeopleNum;
	/**
	 * 固定电话
	 */
	private String telephone;
	/**
	 * 联系手机号
	 */
	private String mobile;
	/**
	 * 公司邮箱
	 */
	private String email;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区县
	 */
	private String area;
	/**
	 * 详细地址
	 */
	private String addressDetail;
	/**
	 * 公司简介
	 */
	private String companyDetail;
	/**
	 * 营业执照号
	 */
	private String companyidNo;
	/**
	 * 营业执照照片地址
	 */
	private String companyidImg;
	/**
	 * 认证等级
	 */
	private String authenticationLevel;
	/**
	 * 企业等级
	 */
	private String comLevel;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 企业状态
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
	 * 修改时间
	 */
	private Date updatedate;
	/**
	 * 修改人
	 */
	private String updateUserId;
	
	// get and set
	
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
	
	
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
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
	 * 获取公司名称 companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 设置公司名称 companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 获取企业logo地址 logoImg
	 */
	public String getLogoImg() {
		return logoImg;
	}
	/**
	 * 设置企业logo地址 logoImg
	 */
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	/**
	 * 获取企业性质 companyType
	 */
	public String getCompanyType() {
		return companyType;
	}
	/**
	 * 设置企业性质 companyType
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	/**
	 * 获取企业规模(公司人数) companyPeopleNum
	 */
	public String getCompanyPeopleNum() {
		return companyPeopleNum;
	}
	/**
	 * 设置企业规模(公司人数) companyPeopleNum
	 */
	public void setCompanyPeopleNum(String companyPeopleNum) {
		this.companyPeopleNum = companyPeopleNum;
	}
	/**
	 * 获取固定电话 telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * 设置固定电话 telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * 获取联系手机号 mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置联系手机号 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取公司邮箱 email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置公司邮箱 email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取省 province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置省 province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取市 city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置市 city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取区县 area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置区县 area
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * 获取公司简介 companyDetail
	 */
	public String getCompanyDetail() {
		return companyDetail;
	}
	/**
	 * 设置公司简介 companyDetail
	 */
	public void setCompanyDetail(String companyDetail) {
		this.companyDetail = companyDetail;
	}
	/**
	 * 获取营业执照号 companyidNo
	 */
	public String getCompanyidNo() {
		return companyidNo;
	}
	/**
	 * 设置营业执照号 companyidNo
	 */
	public void setCompanyidNo(String companyidNo) {
		this.companyidNo = companyidNo;
	}
	/**
	 * 获取营业执照照片地址 companyidImg
	 */
	public String getCompanyidImg() {
		return companyidImg;
	}
	/**
	 * 设置营业执照照片地址 companyidImg
	 */
	public void setCompanyidImg(String companyidImg) {
		this.companyidImg = companyidImg;
	}
	/**
	 * 获取认证等级 authenticationLevel
	 */
	public String getAuthenticationLevel() {
		return authenticationLevel;
	}
	/**
	 * 设置认证等级 authenticationLevel
	 */
	public void setAuthenticationLevel(String authenticationLevel) {
		this.authenticationLevel = authenticationLevel;
	}
	/**
	 * 获取企业等级 level
	 */
	
	/**
	 * 获取类型 type
	 */
	public String getType() {
		return type;
	}
	public String getComLevel() {
		return comLevel;
	}
	public void setComLevel(String comLevel) {
		this.comLevel = comLevel;
	}
	/**
	 * 设置类型 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取企业状态 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置企业状态 state
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
	public String getCompanyTypeStr(){
		return CompanyType.parseOf(this.getCompanyType()).getName();
	}
}
