package com.zhl.job.service;
import javax.servlet.http.HttpServletRequest;

import com.zhl.job.pojo.UserInfo;


public interface IUserInfoService {
	int insertSelective(UserInfo userInfo);
	int updateByPrimaryKeySelective(HttpServletRequest request, UserInfo userInfo);
	UserInfo selectByUserId(String userId);
	UserInfo selectByPid(String pid);
}
