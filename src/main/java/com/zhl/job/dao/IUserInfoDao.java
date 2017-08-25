package com.zhl.job.dao;

import com.zhl.job.pojo.UserInfo;


public interface IUserInfoDao {
	int insertSelective(UserInfo userInfo);
	int updateByPrimaryKeySelective(UserInfo userInfo);
	UserInfo selectByUserId(String userId);
	UserInfo selectByPid(String userId);
}
