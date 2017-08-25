package com.zhl.job.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.WorkInfo;

public interface IWorkInfoService {
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	WorkInfo queryById(String id);
	
	/**
	 * 发布企业信息
	 * 1、WorkInfo表添加信息
	 * 2、workinfo_statis表添加信息
	 * @param workInfo 发布实体
	 */
	void addWorkInfo(String keySn, WorkInfo workInfo);
	
	/**
	 * 查询企业发布的招聘列表
	 * 
	 * @param bounds	分页实体
	 * @param map		查询参数
	 * @return
	 */
	PageInfo<WorkInfo> queryByPage(Map<String, Object> map, Integer pageNo,Integer pageSize, String logUserId);
	
	/**
	 * 查询企业发布的招聘详细信息
	 * @param map
	 * @return
	 */
	Map<String, Object> queryWorkInfoDetail(String workId, String logUserId, String pid);
	
	/**
	 * 修改发布信息
	 * 		当此招聘下有人应聘时，不可修改
	 * @param workInfo
	 */
	String updateWorkInfoCondition(WorkInfo workInfo);
	
	/**
	 * 分页按状态查询列表
	 * @return
	 */
	PageInfo<WorkInfo> queryWorkInfoPageByState(Map<String, Object> map, Integer pageNo,Integer pageSize);
	
	/**
	 * 企业管理，查询职位详细信息
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryWorkInfoApplyByDate(String workId, Date date, Integer pageNo, Integer pageSize);
	/**
	 * 企业管理，查询职位详细信息(不分页)
	 * 	根据id查询招聘详细信息和用户申请信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryWorkInfoApplyByDateNotPage(String workId, Date date);
	 
	/**
	 * 查询招聘信息
	 * @param workId
	 * @return
	 */
	WorkInfo queryWorkInfo(String workId);

	/**
	 * 管理平台分页查询兼职任务
	 */
	PageInfo<WorkInfo> queryByPage(Map<String, Object> map, Integer pageNo,Integer pageSize);
	
	int updateWorkInfoById(WorkInfo workInfo);
	
	/**
	 * 查询录用人
	 * @param workId
	 * @return
	 */
	List<ApplyInfo> queryByEmployAndWorkId(String workId);
	
	/**
	 * 按条件查询列表(不分页)
	 * @return
	 */
	List<WorkInfo> queryWorkInfoByCondition(Map<String, Object> map);
	
	/**
	 * 修改
	 * @param workInfo
	 * @return
	 */
	int updateWorkInfo(WorkInfo workInfo);
}
