package com.zhl.job.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zhl.job.pojo.SysUser;

public interface ISysUserService {

	SysUser getUserById(String id);

	List<SysUser> getUserByUsername(String username);
	
	Object login(SysUser sysUser, HttpServletRequest request);

}
