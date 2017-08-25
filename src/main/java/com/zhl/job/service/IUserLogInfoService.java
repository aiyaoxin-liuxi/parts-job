package com.zhl.job.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.UserLogInfo;


public interface IUserLogInfoService {
	int insertSelective(UserLogInfo userLogInfo);
	int updateByPrimaryKeySelective(UserLogInfo userLogInfo);
	UserLogInfo selectByPk(String id);
	
	/**
	 * 判断该用户是否注册
	 * @param userLogInfo
	 * @return
	 */
	boolean selectByUserLogInfoState(UserLogInfo userLogInfo);
	UserLogInfo getByMobile(UserLogInfo userLogInfo);
	
	/**
	 * 个人用户注册
	 * @param userLogInfo
	 * @return
	 */
	Object register(UserLogInfo userLogInfo);
	
	public PageInfo<UserLogInfo> queryUserLogInfoPage(Map<String, Object> map,
			Integer pageNo, Integer pageSize);
}
