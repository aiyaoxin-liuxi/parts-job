package com.zhl.job.controller.sys;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.pub.util.uuid.KeySn;
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
import com.zhl.job.pojo.Dict;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.resovlver.exception.NoLogin4SysException;
import com.zhl.job.service.IDictService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 
  * @ClassName: DictController
  * @author zhaotisheng	
  * @date 2017年4月10日 下午2:16:04
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/sys/dict")
public class DictController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(DictController.class);
	
	@Autowired
	IDictService dictService;
	
	

	@RequestMapping(value = "/add")
	public void addGet(Model model, HttpServletRequest request) throws NoLogin4SysException {
		super.checkLoginStatus4Sys(request);
		logs.debug("##>>>add get Request OK!");
	}
	/**
	 * 添加dict
	  * @Title: addPost
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/add",method={RequestMethod.POST})
	public @ResponseBody Object addPost(Model model, HttpServletRequest request,Dict dict) {
		logs.debug("##>>>add get Request OK!");
		
		int i = 0;
		String msg="";
		if(Stringer.isNullOrEmpty(dict.getDid())){
			String key = KeySn.getKey();
			dict.setDid(key);
			dict.setCreateDate(new Date());
			dict.setIsdel(IsDel.CODE00.getCode());
			i=dictService.insertOne(dict);
			msg="保存";
		}else{
			i= dictService.updateOneById(dict);
			msg="修改";
		}
				
		ResponseEntity res = new ResponseEntity();
		Stringer.commonOperation(i, msg, res);
		return res.toJson();
	}
	@RequestMapping(value = "/list")
	public void list(Model model, HttpServletRequest request,Integer pageNo,Integer pageSize) throws NoLogin4SysException {
		super.checkLoginStatus4Sys(request);
		logs.debug("##>>>add get Request OK!");
		
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?Constant.pageSize:pageSize;
		PageInfo<Dict> pageInfo=dictService.queryPage( pageNo, pageSize);
		Stringer.setPageInfo(model,pageInfo);
	}
	
	@RequestMapping(value = "/del",method={RequestMethod.POST})
	public @ResponseBody Object del(Model model, HttpServletRequest request,Dict dict) {
		logs.debug("##>>>add del Request OK!");
		if(Stringer.isNullOrEmpty(dict.getDid())){
			return error("did should not be empty..");
		}
		int i = dictService.deleteOne(dict);
		ResponseEntity res = new ResponseEntity();
		Stringer.commonOperation(i, "删除", res);
		return res.toJson();
	}
	
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, HttpServletRequest request,Dict dict) throws NoLogin4SysException {
		super.checkLoginStatus4Sys(request);
		logs.debug("##>>>add edit Request OK!");
		Dict dictDb=dictService.findById(dict);
		model.addAttribute("dict", dictDb);
		return "/manager/dict/add";
	}

}
