package com.zhl.job.dao;

import com.zhl.job.pojo.BanknameList;

public interface IBanknameListDao {
	
	public BanknameList selectByPrimaryKey(String bankid);

	public int insertSelective(BanknameList banknameList);
	
	public int updateByPrimaryKeySelective(BanknameList banknameList);
	
}
