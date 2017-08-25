package com.zhl.job.service.impl;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.service.IUserInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;

/**
 * 通用组件
  * @ClassName: CommonComponent
  * @author zhaotisheng	
  * @date 2017年3月30日 下午4:30:46
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
//@Transactional
public class CommonService extends BaseController{

	public static final String  USER_NAME="USER_NAME";
	
	private Logger logs = LoggerFactory.getLogger(CommonService.class);

	@Autowired
	private CompanyInfoService companyInfoService;
	
	@Autowired
	private IUserInfoService userInfoService;

	/**
	 * 
	  * setCompanyInfo补全企业信息
	  *
	  * @Title: setCompanyInfo
	  * @param @param request
	  * @param @param model    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public CompanyInfo setCompanyInfo(HttpServletRequest request, Model model) {
		logs.debug("###>>>准备补全企业信息....");
		return doSetCompanyInfo(request,model,null);
	}
	
	public CompanyInfo setCompanyInfo(HttpServletRequest request, Model model,String userId) {
		logs.debug("###>>>准备补全企业信息....");
		return doSetCompanyInfo(request,model,userId);
	}
	//补全企业信息，并且看看有几个star
	private CompanyInfo doSetCompanyInfo(HttpServletRequest request, Model model, String userId) {
		if(Stringer.isNullOrEmpty(userId)){
			userId = super.getLoginUserId(request);
		}
		UserLogInfo userLogInfo=new UserLogInfo();
		userLogInfo.setId(userId);
		CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
		if(!Stringer.isNullOrEmpty(companyInfoByUserId)){
			String logoImg = companyInfoByUserId.getLogoImg();
			if(!Stringer.isNullOrEmpty(logoImg)){
				companyInfoByUserId.setLogoImg(logoImg.replace("\\", "/"));
			}
			model.addAttribute("companyInfo", companyInfoByUserId);
		}
		addStarsAttr(request,model,companyInfoByUserId);
		return companyInfoByUserId;
	}

	private void addStarsAttr(HttpServletRequest request, Model model, CompanyInfo companyInfoDb) {
		int i = 1;
		//刚刚注册，1个星星            提交审核两个星  审核通过 5个
		if(Stringer.isNullOrEmpty(companyInfoDb) || Stringer.isNullOrEmpty(companyInfoDb.getComLevel()) ){
			i=1;
		}else{
			try {
				i= Integer.valueOf(companyInfoDb.getComLevel());
			} catch (NumberFormatException e) {
				logs.debug("####>>> companyInfoDb.getLevel 不是数字");
				e.printStackTrace();
			}
		}
		request.setAttribute("stars", i);
	}

	/**
	 * 
	  * setUserInfo
	  *
	  * @Title: setUserInfo
	  * @param @param request
	  * @param @param model
	  * @param @return    设定文件
	  * @return UserInfo    返回类型
	  * @throws
	 */
	public UserInfo setUserInfo(HttpServletRequest request, Model model) {
		logs.debug("###>>>准备补全个人信息...");
		return doSetUserInfo(request,model,null);
	}
	
	public UserInfo setUserInfo(HttpServletRequest request, Model model,String userId) {
		logs.debug("###>>>准备补全个人信息...");
		return doSetUserInfo(request,model,userId);
	}	
	private UserInfo doSetUserInfo(HttpServletRequest request, Model model, String userId) {
		if(Stringer.isNullOrEmpty(userId)){
			userId = super.getLoginUserId(request);
		}
		UserInfo userInfo = userInfoService.selectByUserId(userId);
		if(userInfo == null){
			userInfo = new UserInfo();
		}
		model.addAttribute("userInfo", userInfo);
		return userInfo;
	}

	public void setCompanyInfoId(HttpServletRequest request, String userId) {
			UserLogInfo userLogInfo =new UserLogInfo();
			userLogInfo.setId(userId);//.setUserId(userId);
			CompanyInfo companyInfoByUserId = companyInfoService.getCompanyInfoByUserId(userLogInfo);
			if(!Stringer.isNullOrEmpty(companyInfoByUserId)){
				request.getSession().setAttribute(Constant.LOGIN_COMPANYINFO_ID, companyInfoByUserId.getCid());
				request.getSession().setAttribute(Constant.LOGIN_COMPANYINFO_ENTITY, companyInfoByUserId);
				logs.debug("###>>> 更新企业实体完成>>>>>>: \r\n"+JsonUtil.toJson(companyInfoByUserId));
				String companyName = companyInfoByUserId.getCompanyName();
				if(!Stringer.isNullOrEmpty(companyName)){
					request.getSession().setAttribute(USER_NAME, companyName);
				}
			}
	}

	public void setLoginInfo(HttpServletRequest request, UserLogInfo userDb) {
		logs.debug("准备放入，放入 LOGIN_USER_MOBILE： "+ userDb.getMobile() +"\t  LOGIN_USER_ID:"+userDb.getId() +" \t 放入的userType:"+userDb.getUserType());
		request.getSession().setAttribute(Constant.LOGIN_USER_MOBILE, userDb.getMobile());
		request.getSession().setAttribute(Constant.LOGIN_USER_ID, userDb.getId());
		request.getSession().setAttribute(Constant.LOGIN_USER_TYPE, userDb.getUserType());//用户类型
		request.getSession().setAttribute(USER_NAME, userDb.getMobile());
		setCompanyInfoId(request,userDb.getId());
		setPersonId(request,userDb.getId());
	}
	private void setPersonId(HttpServletRequest request, String userId) {
		
		UserInfo selectByUserId = userInfoService.selectByUserId(userId);
		if(!Stringer.isNullOrEmpty(selectByUserId)){
			request.getSession().setAttribute(Constant.LOGIN_PERSON_ID, selectByUserId.getPid());
			request.getSession().setAttribute(Constant.LOGIN_USER_ENTITY, selectByUserId);
			String realname = selectByUserId.getRealname();
			if(!Stringer.isNullOrEmpty(realname)){
				request.getSession().setAttribute(USER_NAME, realname);
			}
		}
	}
}
