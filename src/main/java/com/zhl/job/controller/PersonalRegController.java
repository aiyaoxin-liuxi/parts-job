package com.zhl.job.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IUserInfoService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.service.impl.CompanyInfoService;
import com.zhl.job.service.impl.SendSMSService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.RandomGenerator;
import com.zhl.job.util.Stringer;

/**
 * 个人注册
 * @author wxw
 *
 */
@Controller
@RequestMapping(value = "/personalInfo")
public class PersonalRegController extends BaseController{
	
	private Logger logs = LoggerFactory.getLogger(PersonalRegController.class);
	private static final String SMSCODE_KEY="smscode";
	
	@Resource
	private IUserLogInfoService userLogInfoService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private SendSMSService sendSMSService;

	@Autowired
	private CommonService commonService;
	
	
	@RequestMapping("/toPersonalRegPage")
	public String toPersonalRegPage(ModelMap model){
		return "personalInfo/reg-personal";
	}
	
	/**
	 * 发送短信
	 * @param model
	 * @return
	 */
	@RequestMapping("/toSendSms")
	@ResponseBody
	public Object toSendSms(ModelMap model,HttpServletRequest request,String mobile){
		if(!Stringer.isMobile(mobile)){
			return error("请填写正确的手机号");
		}
		UserLogInfo info = new UserLogInfo();
		info.setMobile(mobile);
		boolean flag = userLogInfoService.selectByUserLogInfoState(info);
		if(flag){
			logs.error("【个人注册发送短信验证码】用户："+mobile+",已经注册");
			return error("该手机号已注册");
		}
		String randomDigital = RandomGenerator.randomDigital(6);
		logs.info("=============="+randomDigital);
		request.getSession().setAttribute(SMSCODE_KEY, randomDigital);
		//TODO;调用短信发送方法
		return sendSMSService.sendVerifyCode(mobile,randomDigital);
	}
	/**
	 * 保存注册信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/toSavePersonalInfo")
	@ResponseBody
	public Object toSavePersonalInfo(ModelMap model,HttpServletRequest request,UserLogInfo userLogInfo,String validCode){
		if(Stringer.isNullOrEmpty(validCode)){
			return error("请填写验证码");
		}
		Object session_verifyCode = request.getSession().getAttribute(SMSCODE_KEY);
		if(Stringer.isNullOrEmpty(session_verifyCode)){
			return error("验证码失效，请重新获取验证码");
		}
		if(!session_verifyCode.equals(validCode)){
			return error("验证码错误");
		}
		try {
			request.getSession().removeAttribute(SMSCODE_KEY);
		} catch (Exception e) {
		}
		//注册
		return companyInfoService.register(userLogInfo,request);
	}
	
	/**
	 * 我的信息页
	 * @param model
	 * @param request
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/fillpersonalInfo")
	public String personalInfo(ModelMap model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		//获取登录id
		setUserInfo(model,request);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_INF.getCode());

		String loginUserId = super.getLoginUserId(request);
		UserLogInfo selectByPk = userLogInfoService.selectByPk(loginUserId);
 	    commonService.setLoginInfo(request,selectByPk);
		return "personalInfo/fillpersonalInfo";
	}
	//获取登陆用户信息
	private void setUserInfo(ModelMap model, HttpServletRequest request) {
		String userId = getLoginUserId(request);
		UserInfo userInfo = userInfoService.selectByUserId(userId);
		if(userInfo == null){
			userInfo = new UserInfo();
		}
		model.addAttribute("userInfo", userInfo);
		
	}

	@RequestMapping(value="/saveFillPersonalInfo")
	@ResponseBody
	public Object saveFillPersonalInfo(ModelMap model,HttpServletRequest request,UserInfo userInfo){
		UserInfo user = userInfoService.selectByPid(userInfo.getPid());
		String realname = userInfo.getRealname();
		if(Stringer.isNullOrEmpty(realname)){
			return error("姓名不可以为空");
		}
		if(realname.length()>10){
			return error("姓名不可以太长（<10）");
		}
		String logoImg = userInfo.getHeadImg();
		if(!Stringer.isNullOrEmpty(logoImg)){
			logoImg=logoImg.replaceAll("\\\\", "/").replace(",","");
		}
		userInfo.setHeadImg(logoImg);
		userInfo.setUserId(super.getLoginUserId(request));
		int i = 0;
		userInfo.setUpdateDate(new Date());
		userInfo.setUpdateUserId(this.getLoginUserId(request));
		if(user == null){
			userInfo.setPid(KeySn.getKey());
			userInfo.setCreatedate(new Date());
			userInfo.setIsdel(IsDel.CODE00.getCode());
			userInfo.setState(State.AUDIT_SUCC.getCode());
//			logs.debug("###>>>保存的实体是："+JsonUtil.toJson(userInfo));
			i = userInfoService.insertSelective(userInfo);
		}else{
			i = userInfoService.updateByPrimaryKeySelective(request,userInfo);
		}
		if(i!=1){
			return error("修改失败");
		}
		return success("修改成功");
	}
	
}
