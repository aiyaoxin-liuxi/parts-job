package com.zhl.job.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.job.pojo.SettlementInfoLog;

/**
 * 结算记录表dao
 * @author 刘熙
 */
public interface ISettlementInfoLogDao {
	
	/**
	 * 添加结算记录
	 * @param settlementInfoLog
	 */
	void addSettlementInfoLog(List<SettlementInfoLog> list);
	
	/**
	 * 根据主键id、工作时间和结算单id查询
	 * @param map
	 * @return
	 */
	SettlementInfoLog querySInfoLogByIdAndTimeAndSetId(@Param("id") String id, 
													   @Param("settlementId") String settlementId, 
													   @Param("workDay") Date date);
	
	/**
	 * 根据申请单id查询对应结算单
	 * @param settlementInfoLog
	 */
	SettlementInfoLog querySInfoByApplyId(@Param("applyId") String applyId);
	
	/**
	 * 更新结算记录---结算
	 * @param settlementInfoLog
	 */
	void updateSettlementInfoLog(List<SettlementInfoLog> list);


	List<SettlementInfoLog> queryListBySettleIdAndWorkDay(Map<String, Object> paramMap);
}
