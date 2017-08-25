package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.job.pojo.SettlementInfo;

/**
 * 结算单dao
 * @author 刘熙
 */
public interface ISettlementInfoDao {
	
	/**
	 * 写入结算单
	 * @param settlementInfo 发布实体
	 */
	void addSettlementInfo(SettlementInfo settlementInfo);
	
	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	SettlementInfo querySettlementById(@Param("id") String id);
	
	/**
	 * 查询结算表中此兼职工作指定天的结算信息是否存在
	 * @param map
	 * @return
	 */
	SettlementInfo querySettlementByWorkIdAndDay(Map<String, Object> map);
	
	/**
	 * 查询企业下所有结算
	 * @param map
	 * @return
	 */
	List<SettlementInfo> querySettlementByWorkId(Map<String, Object> map);
	

	/**
	 * 修改实付全部金额---申请
	 * @param settlementInfo
	 */
	void updateAccountTotal(SettlementInfo settlementInfo);
	
	/**
	 * 修改实付全部金额---结算
	 * @param settlementInfo
	 */
	void updateAccountTotalAndState(SettlementInfo settlementInfo);
	/**
	 * 查询企业的
	 * @param settlementInfo
	 */
	List<SettlementInfo> queryListByCompayId(Map<String, Object> map);

	List<SettlementInfo> queryListAll();
}
