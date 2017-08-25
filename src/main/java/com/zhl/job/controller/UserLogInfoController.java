package com.zhl.job.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 *  登陆用户controller
  * @ClassName: UserLogInfoController
  * @author zhaotisheng	
  * @date 2017年3月15日 下午5:17:57
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/userloginfo")
public class UserLogInfoController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(UserLogInfoController.class);
	
	@Resource
	private IUserLogInfoService userLogInfoService;
	
	@Autowired
	private CommonService commonService;
	
	
	/**
	 * @throws NoLoginException 
	 * 
	  * register修改密码
	  *
	  * @Title: register
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/updatepwd")
	public void updatepwdGet(Model model, HttpServletRequest request) throws NoLoginException {
		logs.debug("##>>>updatepwd Request OK!");
		checkLoginStatus(request);
		String m = super.getLoginUserMobile(request);
		model.addAttribute("mobile", m);
		
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.SEC_CENTER.getCode());
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model, loginUserId);
		}
	}
	/**
	 * 
	  * updatepwd
	  *
	  * @Title: updatepwdPost
	  * @param @param model
	  * @param @param request
	  * @param @param userLogInfo
	  * @param @param newlogPassword
	  * @param @param renewlogPassword
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/updatepwd",method={RequestMethod.POST})
	public @ResponseBody Object  updatepwdPost(Model model, HttpServletRequest request,UserLogInfo userLogInfo,String newlogPassword,String renewlogPassword) {
		logs.debug("##>>>updatepwd Request OK!");
		if( ! newlogPassword.equals(renewlogPassword)){
			return error("两次输入不一致");
		}
		UserLogInfo serLogInfoDb=userLogInfoService.getByMobile(userLogInfo);
		if(Stringer.isNullOrEmpty(serLogInfoDb)){
			logs.debug("##>>>updatepwd 找不到对应的用户");
			return error("找不到对应的用户");
		}
		String logPasswordDb = serLogInfoDb.getLogPassword();
//		if( !userLogInfo.getLogPassword().equals(logPasswordDb)){
		String encryptLogPwd = Stringer.encryptLogPwd(userLogInfo);
		if( !encryptLogPwd.equals(logPasswordDb)){
			return error("原密码不对");
		}
		serLogInfoDb.setLogPassword(newlogPassword);
		Stringer.encryptLogPwd(serLogInfoDb);//加密
		ResponseEntity res = new ResponseEntity();
		serLogInfoDb.setUpdateDate(new Date());
		serLogInfoDb.setUpdateUserId(super.getLoginUserId(request));
		int i = userLogInfoService.updateByPrimaryKeySelective(serLogInfoDb);
		res.setData(serLogInfoDb.getId());
		return Stringer.commonOperation(i,"修改密码",res);
		
	}
	/**
	 * @throws NoLoginException 
	 * 
	  * paypwdGet存在-修改
	  * 不存在-设置
	  * @Title: paypwdGet
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/paypwd")
	public void paypwdGet(Model model, HttpServletRequest request) throws NoLoginException {
		logs.debug("##>>>paypwd Request OK!");
		super.checkLoginStatus(request);
		//user
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		String modifyFlag=userLogInfoDb.getPayPassword() ==null?"0":"1";
		model.addAttribute("modifyFlag", modifyFlag);
		model.addAttribute("id", userLogInfoDb.getId());
		//add
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model, loginUserId);
		}
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.SEC_CENTER.getCode());
	}
	/**
	 * 
	  * paypwdPost设置  /  修改 支付密码 
	  * @Title: paypwdPost
	  * @param @param userLogInfo
	  * @param @param model
	  * @param @param request
	  * @param @param modifyFlag
	  * @param @param newPayPassword
	  * @param @param reNewPayPassword    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/paypwd",method={RequestMethod.POST})
	public @ResponseBody Object  paypwdPost(UserLogInfo userLogInfo,Model model, HttpServletRequest request,String modifyFlag
			,String rePayPassword,String newPayPassword,String reNewPayPassword) {
		logs.debug("##>>>paypwdPost Request OK! modifyFlag:"+modifyFlag);
		
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model, loginUserId);
		}
		userLogInfo.setUpdateDate(new Date());
		userLogInfo.setUpdateUserId(super.getLoginUserId(request));
		if(modifyFlag.equals("0")){//设置
			logs.debug("##>>>设置支付密码");
			if( Stringer.isNullOrEmpty(userLogInfo.getPayPassword()) || Stringer.isNullOrEmpty(rePayPassword) ){
				return error("支付密码和重复密码不可为空");
			}
			if( ! userLogInfo.getPayPassword().equals(rePayPassword)){
				return error("两次输入不一致");
			}
			ResponseEntity res = new ResponseEntity();
			Stringer.encryptPayPwd(userLogInfo);//加密
			int i = userLogInfoService.updateByPrimaryKeySelective(userLogInfo);
			return Stringer.commonOperation(i, "设置支付密码", res);
		}else if(modifyFlag.equals("1")){//修改
			logs.debug("##>>>修改支付密码");
			if( Stringer.isNullOrEmpty(userLogInfo.getPayPassword()) || Stringer.isNullOrEmpty(newPayPassword) ||Stringer.isNullOrEmpty(reNewPayPassword) ){
				return error("原密码，支付密码和重复密码不可为空");
			}
			if( ! newPayPassword.equals(reNewPayPassword)){
				return error("两次输入不一致");
			}
			String encryptPayPwd = Stringer.encryptPayPwd(userLogInfo);//加密
			//
			UserLogInfo userLogInfoNew =new UserLogInfo();
			userLogInfoNew.setMobile(super.getLoginUserMobile(request));
			UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfoNew);
			if(!userLogInfoDb.getPayPassword().equals(encryptPayPwd)){
				return error("原密码不正确");
			}
			//
			
			userLogInfo.setPayPassword(reNewPayPassword);
			Stringer.encryptPayPwd(userLogInfo);//加密
			ResponseEntity res = new ResponseEntity();
			int i = userLogInfoService.updateByPrimaryKeySelective(userLogInfo);
			return Stringer.commonOperation(i, "修改支付密码", res);
		}else{
			logs.error("##>>>paypwdPost Request error,don't know what action ");
		}
		return null;
	}
	//TODO..
	@RequestMapping(value = "/bindwx")
	public void bindwxGet(Model model, HttpServletRequest request) throws NoLoginException {
		logs.debug("##>>>paypwd bindwx OK!");
		checkLoginStatus(request);
		
		
	}
	/**
	 * 
	  * findpwd找回支付密码
	  *
	  * @Title: findpwd
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/findpaypwd")
	public void findpwd(Model model, HttpServletRequest request) throws NoLoginException{
		logs.debug("##>>>findpwd get Request OK!");
		checkLoginStatus(request);
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		model.addAttribute("user", userLogInfoDb);
		//add
		String loginUserId = super.getLoginUserId(request);
		String loginUserType = super.getLoginUserType(request);
		if(loginUserType.equals(UserType.PERSONAL.getCode())){
			commonService.setUserInfo(request, model, loginUserId);
		}else if(loginUserType.equals(UserType.COMPANY.getCode())){
			commonService.setCompanyInfo(request,model, loginUserId);
		}
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.SEC_CENTER.getCode());		
		
	}
	/**
	 * 
	  * findpwd找回支付密码
	  *
	  * @Title: findpwd
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/findpaypwd",method={RequestMethod.POST})
	public @ResponseBody Object findpaypwdPost(Model model, HttpServletRequest request,UserLogInfo userLogInfo,
			String newpaypwd,String renewpaypwd,String smscode) throws NoLoginException{
		logs.debug("##>>>findpaypwdPost get Request OK!");
		String logPassword = userLogInfo.getLogPassword();
		
		if(Stringer.isNullOrEmpty(logPassword)){
			return error("登陆密码不能为空");
		}
		UserLogInfo userLogInfoDb=getLoginuser(request);
		
		String logPassword2 = userLogInfoDb.getLogPassword();
//		if(! logPassword.equals(logPassword2)){
		String encryptLogPwd = Stringer.encryptLogPwd(userLogInfo);
		if(! encryptLogPwd.equals(logPassword2)){
			return error("登陆密码不对");
		}
		if(Stringer.isNullOrEmpty(newpaypwd) ||Stringer.isNullOrEmpty(renewpaypwd)){
			return error("请规范填写新密码");
		}
		if(! newpaypwd.equals(renewpaypwd)){
			return error("两次输入不一致");
		}
		if(Stringer.isNullOrEmpty(smscode)){
			return error("验证码不能为空");
		}
		Object smscodeObj = request.getSession().getAttribute(Constant.SMSCODE_KEY);
		if(Stringer.isNullOrEmpty(smscodeObj)){
			return error("验证码未发送或者已经过期");
		}
		if(!smscodeObj.toString().equals(smscode)){
			return error("验证码不正确");
		}
		try {
			request.getSession().removeAttribute(Constant.SMSCODE_KEY);
		} catch (Exception e) {
		}
		// check通过 修改支付密码
		
		UserLogInfo newuserLogInfo=new UserLogInfo();
		newuserLogInfo.setId(userLogInfoDb.getId());
		newuserLogInfo.setPayPassword(renewpaypwd);
		ResponseEntity res = new ResponseEntity();
		Stringer.encryptPayPwd(newuserLogInfo);
		int i = userLogInfoService.updateByPrimaryKeySelective(newuserLogInfo);
		return Stringer.commonOperation(i, "找回支付密码成功，请牢记您的支付密码", res);
		
	}
	
	
	private UserLogInfo getLoginuser(HttpServletRequest request) {
		UserLogInfo userLogInfo =new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo userLogInfoDb = userLogInfoService.getByMobile(userLogInfo);
		return userLogInfoDb;
	}
}
