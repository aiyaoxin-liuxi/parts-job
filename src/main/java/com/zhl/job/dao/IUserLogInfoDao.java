package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import com.zhl.job.pojo.UserLogInfo;


public interface IUserLogInfoDao {
	int insertSelective(UserLogInfo userLogInfo);
	int updateByPrimaryKeySelective(UserLogInfo userLogInfo);
	List<UserLogInfo> selectByUserLogInfoState(UserLogInfo userLogInfo);
	UserLogInfo selectByPk(String id);
	int updateList(List<UserLogInfo> list);
	
	List<UserLogInfo> queryUserLogInfoPage(Map<String, Object> map);
}
