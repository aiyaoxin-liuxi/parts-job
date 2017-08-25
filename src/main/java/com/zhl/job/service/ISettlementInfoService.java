package com.zhl.job.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.common.ResponseEntity;

public interface ISettlementInfoService {
	
	/**
	 * 写入结算单
	 * @param settlementInfo 发布实体
	 */
	void addSettlementInfo(SettlementInfo settlementInfo);
	
	/**
	 * 	增加结算信息
	 * 	根据workid和日期，查询是否存在此结算单
	 * 		存在： 不再建立新结算单，直接在结算记录中添加
	 * 		不存在：生成1个结算单，写入结算记录
	 * 
	 * @param cid			发布兼职企业id
	 * @param workId		兼职工作id
	 * @param workDay		兼职工作日期
	 * @param userId		兼职人登录id
	 * @param pid			兼职人id
	 * @param advancePay	预计工资
	 */
	ResponseEntity addSettlementService(String keySn, ApplyInfo appList);
	
	/**
	 * 结算
	 * @param keySn
	 * @param settlementInfo
	 * @return
	 */
	ResponseEntity settlementConfirm(String keySn, String cUserId, SettlementInfo settlementInfo);
	
	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	SettlementInfo querySettlementById(@Param("id") String id);
	
	PageInfo<SettlementInfo> queryListByCompayId(Map<String, Object> paramMap, Integer pageNo, Integer pageSize);

}
