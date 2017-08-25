package com.zhl.job.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.ApplyInfo;

public interface IApplyInfoService {
	
//	List<ApplyInfo> selectPaging(PagingBounds bounds, Map<String, Object> map);
	
	ApplyInfo queryByApplyId(Map<String, Object> map);

	/**
	 * 申请职位
	 * @param applyInfo
	 * @return
	 */
	int insertApplyInfo(String keySn, ApplyInfo applyInfo);

	/**
	 * 批量取消职位申请
	 * @param List<Map<String,Object>> parms
	 * @return
	 */
	public int cleanApplyInfos(List<ApplyInfo> list);

	/**
	 * 删除申请职位
	 * @param String... ids
	 * @return
	 */
	public int delApplyInfos(String... ids);
	

	/**
	 * 更新申请职位
	 * @param applyInfo
	 * @return
	 */
	public int updateApplyInfo(ApplyInfo applyInfo_n);
	
	/**
	 * 查询跑批时所需生成结算单的信息
	 * @return
	 */
	List<ApplyInfo> queryByEmployAndWorkDay();
	
	/**
	 * 查询所需生成结算单的信息
	 * @return
	 */
	List<ApplyInfo> queryByEmployAndWorkDay(Date date);
	
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
	PageInfo<ApplyInfo> queryApplyAndWorkPageByEmploy(Map<String, Object> map, Integer pageNo,Integer pageSize);
	
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
	List<ApplyInfo> queryWorkInfoByDate(String logUserId, Date date);
	/**
	 * 查询用户指定日期是否已申请过这个工作并被拒绝
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryWorkInfoByDateAndWorkId(String logUserId, String workId, Date date);
	
	/**
	 * 筛选时间需要测试WORKINFO调用
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Date> screenDate(String logUserId, String allowChooseDate, Date startDate, Date endDate);
	
	/**
	 * 企业管理，查询职位详细信息
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @return
	 */
	List<ApplyInfo> queryApplyByWorkAndDate(Map<String, Object> map);
	
	/**
	 * 兼职申请分页查询
	 * @param map
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	PageInfo<ApplyInfo> queryApplyInfoPage(Map<String, Object> map, Integer pageNo,Integer pageSize);
	
	/**
	 * 企业管理，查询职位详细信息（只查询已录用和进行中）
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @return
	 */
	List<ApplyInfo> queryApplyByWorkAndDateNotPage(Map<String, Object> map);
	
	/**
	 * 录用
	 */
	int addEmployment(ApplyInfo applyInfo);
	/**
	 * 批量录用
	 */
	int addBatchEmployment(List<ApplyInfo> list);
	
	/**
	 * 批量拒绝
	 * @param list
	 * @return
	 */
	public int batchUpdateApplyInfos(List<ApplyInfo> list);
	/**
	 * 查询指定用户是否参加过此类型的工作
	 * @param map
	 * @return
	 */
	public List<ApplyInfo> queryOldJobType(List<ApplyInfo> appList, String state, String jobType);
	/**
	 * 查询指定用户是否参加过此类型的工作
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryOldJobType(String pid, String state, String jobType);
	
	/**
	 * 查询指定用户是否参加过此类型的工作
	 * @param map
	 * @return
	 */
	List<ApplyInfo> queryOldJobType(Map<String, Object> map);
	
}
