package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.job.pojo.Accountflow;

public interface IAccountflowDao {
	//selectByPrimaryKey
	Accountflow selectByPrimaryKey(String accountId);
	int deleteByPrimaryKey(String accountId);
	int insertSelective(Accountflow accountflow);
	int updateByPrimaryKeySelective(Accountflow accountflow);
	List<Accountflow> selectByUserId(String userId);
	int insertList(List<Accountflow> list);
	List<Accountflow> selectByTransFlowNo(@Param("transFlowNo")String transFlowNo);
	List<Accountflow> queryAccountflowPage(Map<String,String> map);
	List<Accountflow> queryAccountflowHanding(Map<String,String> map);
}
