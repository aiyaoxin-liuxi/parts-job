package com.zhl.job.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.SettlementInfoLog;

public interface ISettlementInfoLogService {

	PageInfo<SettlementInfoLog> queryListBySettleIdAndWorkDay(Map<String, Object> paramMap, Integer pageNo, Integer pageSize);
	
	/**
	 * 根据申请单id查询对应结算单
	 * @param settlementInfoLog
	 */
	SettlementInfoLog querySInfoByApplyId(@Param("applyId") String applyId);
	

}
