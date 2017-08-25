package com.zhl.job.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.github.pagehelper.PageInfo;
import com.zhl.job.controller.common.BaseController;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.SysUser;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.SettlementInfoState;
import com.zhl.job.resovlver.exception.NoLogin4SysException;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.service.ISysUserService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 	后台用户登陆
  * @ClassName: CompanyInfoController
  * @author zhaotisheng	
  * @date 2017年3月13日 下午4:42:23
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/sys/sysuser")
public class SysUserController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(SysUserController.class);
	
	@Autowired
	ISysUserService sysUserSerive;
	

	@Resource
	private ISettlementInfoService settlementService;
	
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	
	@RequestMapping(value = "/login")
	@NoAuth
	public void loginGet(Model model, HttpServletRequest request) {
		logs.debug("##>>>login get Request OK!");
	}
	/**
	 * 
	  * loginPost登陆
	  * @Title: loginPost
	  * @Description: TODO
	  * @param @param model
	  * @param @param request
	  * @param @param sysUser
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/login",method={RequestMethod.POST})@NoAuth
	public @ResponseBody Object loginPost(Model model, HttpServletRequest request,SysUser sysUser) {
		logs.debug("##>>>login  loginPost OK!");
		String username = sysUser.getUsername();
		String pwd = sysUser.getPwd();
		if(Stringer.isNullOrEmpty(username) || Stringer.isNullOrEmpty(pwd)){
			return error("请正确填写 用户名 密码");
		}
		return sysUserSerive.login(sysUser,request);
	}
	
	/**
	 * @throws NoLogin4SysException 
	 * 
	  * index首页
	  * @Title: index
	  * @Description: TODO
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/settList")
	public void settList(Model model, HttpServletRequest request,Integer pageNo,Integer pageSize) throws NoLogin4SysException {
		super.checkLoginStatus4Sys(request);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?Constant.pageSize:pageSize;
		PageInfo<SettlementInfo> pageInfo=settlementService.queryListByCompayId(paramMap,  pageNo, pageSize);
		setworknameAndOtherAttr(pageInfo);
		Stringer.setPageInfo(model,pageInfo);
		
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.SETT_LIST.getCode());
		logs.debug("##>>> get settList OK!");
	}
	
	@RequestMapping(value = "/index")
	public void index(Model model, HttpServletRequest request,Integer pageNo,Integer pageSize) throws NoLogin4SysException {
		
		logs.debug("##>>> get index OK!");
	}
	private void setworknameAndOtherAttr(PageInfo<SettlementInfo> pageInfo) {
		if(Stringer.isNullOrEmpty(pageInfo)){
			return ;
		}
		List<SettlementInfo> list = pageInfo.getList();
		if(Stringer.isNullOrEmpty(list)){
			return ;
		}
		List<SettlementInfo> newlist=new ArrayList<SettlementInfo>();
		for(SettlementInfo each:list){
			String workId = each.getWorkId();
			WorkInfo workInfo = workInfoService.queryById(workId);
			each.setWorkInfo(workInfo);
			String state = each.getState();
			if(!Stringer.isNullOrEmpty(state)){
				String text = SettlementInfoState.getText(state);
				if(!Stringer.isNullOrEmpty(text)){
					each.setState(text);
				}
			}
			newlist.add(each);
		}
		pageInfo.setList(newlist);
	}
	
}
