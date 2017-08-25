package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.job.pojo.ApplyInfo;

public interface IApplyInfoDao {
	
//	List<ApplyInfo> selectPaging(PagingBounds bounds, Map<String, Object> map);
	
	ApplyInfo queryByApplyId(Map<String, Object> map);
	
	int insertApplyInfos(List<ApplyInfo> applyInfos);

//	int examineApplyInfos(List<ApplyInfo> applyInfo);
//	int cleanApplyInfos(List<ApplyInfo> list);
	int batchUpdateApplyInfos(List<ApplyInfo> list);
	
	int updateApplyInfo(ApplyInfo applyInfo);
	
	int delApplyInfos(String... ids);
	
	List<ApplyInfo> queryByApplyWorkId(@Param("workId") String workId);
	
	List<ApplyInfo> queryByEmployAndWorkDay(Map<String, Object> map);
	
	/**
	 * 查询当前用户是否报名
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryIsEmploy(Map<String, Object> map);
	
	/**
	 * 根据录用状态查询指定招聘中录用情况
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryByEmployAndWorkId(Map<String, Object> map);
	
	/**
	 * 分页查询该用户下所有申请（按状态区分）
	 * 
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryApplyAndWorkPageByEmploy(Map<String, Object> map);
	
	/**
	 * 按条件查询
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryWorkInfoBycondition(Map<String, Object> map);
	
	/**
	 * 查询用户指定日期是否已申请过工作 
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryWorkInfoByDate(Map<String, Object> map);
	/**
	 * 查询用户指定日期是否已申请过这个工作并被拒绝
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryWorkInfoByDateAndWorkId(Map<String, Object> map);
	
	/**
	 * 企业管理，查询职位详细信息
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @return
	 */
	List<ApplyInfo> queryApplyByWorkAndDate(Map<String, Object> map);
	
	/**
	 * 企业管理，查询职位详细信息（只查询已录用和进行中）
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @return
	 */
	List<ApplyInfo> queryApplyByWorkAndDateNotPage(Map<String, Object> map);
	
	/**
	 * 兼职申请 分页查询
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryApplyInfoPage(Map<String, Object> map);
	
	/**
	 * 查询指定用户是否参加过此类型的工作
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryOldJobType(Map<String, Object> map);

}
