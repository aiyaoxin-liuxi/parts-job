package com.zhl.job.service.impl;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import com.zhl.job.dao.IUserInfoDao;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.service.IUserInfoService;
import com.zhl.job.util.Constant;
@Service("userInfoService")
public class UserInfoService implements IUserInfoService {

	@Resource
	private IUserInfoDao userInfoDao;
	
	@Override
	public int insertSelective(UserInfo userInfo) {
		return userInfoDao.insertSelective(userInfo);
	}

	@Override
	public int updateByPrimaryKeySelective(HttpServletRequest request,UserInfo userInfo) {
		int i = userInfoDao.updateByPrimaryKeySelective(userInfo);
		if(i==1){
			request.getSession().setAttribute(Constant.LOGIN_USER_ENTITY, userInfo);
		}
		return i;
	}

	@Override
	public UserInfo selectByUserId(String userId) {
		return userInfoDao.selectByUserId(userId);
	}

	@Override
	public UserInfo selectByPid(String pid) {
		return userInfoDao.selectByPid(pid);
	}

}
