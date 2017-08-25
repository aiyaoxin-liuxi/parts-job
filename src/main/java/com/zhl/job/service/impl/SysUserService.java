package com.zhl.job.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhl.job.dao.ISysUserDao;
import com.zhl.job.pojo.SysUser;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.service.ISysUserService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;

/**
 * 
  * @ClassName: SysUserService
  * @author zhaotisheng	
  * @date 2017年4月10日 上午9:14:44
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service("sysUserService")
public class SysUserService implements ISysUserService{
	

	public static final String  USER_NAME="USER_NAME";
	
	private Logger logs = LoggerFactory.getLogger(SysUserService.class);

	@Autowired
	ISysUserDao sysUserDao;
	
	public SysUser getUserById(String sid){
		return sysUserDao.getUserById(sid);
	}
	
	@Override
	public Object login(SysUser sysUser, HttpServletRequest request) {
		ResponseEntity res = new ResponseEntity();
		List<SysUser> list = getUserByUsername(sysUser.getUsername());
		if(Stringer.isNullOrEmpty(list) ){
			res.setSuccess(false);
			res.setErrmsg("用户不存在");
			return res.toJson();
		}
		if(list.size()==0){
			res.setSuccess(false);
			res.setErrmsg("用户不存在");
			return res.toJson();
		}
 		if(list.size()==1){
 			SysUser sysUserDb = list.get(0);
// 			String pwd = sysUserDb.getPwd();
 			String encryptPayPwd = Stringer.encryptPayPwd(sysUser.getPwd());
 			logs.info("登陆比对密码：encryptLogPwd ："+encryptPayPwd +" sysUserDb.getPwd():"+sysUserDb.getPwd());
 			if(encryptPayPwd.equals(sysUserDb.getPwd())){
 				request.getSession().setAttribute(Constant.LOGIN_USER_ID4SYS, sysUserDb.getSid());
				request.getSession().setAttribute(USER_NAME, sysUserDb.getName());
				request.getSession().setAttribute(Constant.LOGIN_USER_TYPE, "03");//
				logs.debug("登陆成功，放入 USER_NAME： "+ sysUserDb.getName() +"\t  LOGIN_USER_ID:"+sysUserDb.getSid());
				res.setSuccess(true);
				res.setData(sysUserDb);
				res.setErrmsg("登陆成功");
 			}else{
				res.setSuccess(false);
				res.setErrmsg("用户名或者密码错误");
			}
			return res.toJson();
 		}else{
 			logs.error("不应该有多个"+JsonUtil.toJson(sysUser));
			throw new RuntimeException("不应该有多个"+JsonUtil.toJson(sysUser));
 		}
	}

	public List<SysUser> getUserByUsername(String username) {
		return sysUserDao.getUserByUsername(username);
	}
}
