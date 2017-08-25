package com.zhl.job.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zhl.job.pojo.enums.ApplyInfoEmploy;
import com.zhl.job.pojo.enums.WorkInfoJobType;
import com.zhl.job.pojo.enums.WorkInfoMeal;
import com.zhl.job.pojo.enums.WorkInfoState;

public class ApplyInfoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date applyWorkDate;
	private String employ;
	private String realname;
	private String workTitle;
	private String workDetail;
	private String workMeal;
	private String addressDetail;
	private String jobType;
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getApplyWorkDate() {
		return applyWorkDate;
	}
	public void setApplyWorkDate(Date applyWorkDate) {
		this.applyWorkDate = applyWorkDate;
	}
	public String getEmploy() {
		return employ;
	}
	public void setEmploy(String employ) {
		this.employ = employ;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWorkDetail() {
		return workDetail;
	}
	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}
	public String getWorkMeal() {
		return workMeal;
	}
	public void setWorkMeal(String workMeal) {
		this.workMeal = workMeal;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getEmployStr(){
		return ApplyInfoEmploy.getText(this.getEmploy());
	}
	public String getJobTypeStr() {
		return WorkInfoJobType.getText(this.getJobType());
	}
	public String getWorkMealStr() {
		return WorkInfoMeal.getText(this.getWorkMeal());
	} 
	public String getStateStr() {
		return WorkInfoState.getText(this.getState());
	}
}
