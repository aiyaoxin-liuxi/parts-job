package com.zhl.job.dao;

import java.util.List;

import com.zhl.job.pojo.SysUser;

public interface ISysUserDao {

	public SysUser getUserById(String sid);
	
	public List<SysUser> getUserByUsername(String username);
}
