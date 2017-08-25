package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.job.pojo.WorkInfo;

/**
 * 企业信息dao
 * @author 刘熙
 */
public interface IWorkInfoDao {
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	WorkInfo queryById(@Param("id") String id);
	
	/**
	 * 写入workinfo信息
	 * @param workInfo 发布实体
	 */
	void addWorkInfo(WorkInfo workInfo);

	/**
	 * 查询企业发布的招聘列表
	 * 
	 * @param bounds	分页实体
	 * @param map		查询参数
	 * @return
	 */
	List<WorkInfo> queryWorkInfoPage(Map<String, Object> map);
	
	/**
	 * 根据主键id查询企业发布的招聘详细信息
	 * @param map
	 * @return
	 */
	WorkInfo queryWorkInfoDetail(@Param("id") String id);
	
	/**
	 * 修改发布信息
	 * @param workInfo
	 */
	int updateWorkInfo(WorkInfo workInfo);
	
	/**
	 * 分页按状态查询列表
	 * @return
	 */
	List<WorkInfo> queryWorkInfoPageByState(Map<String, Object> map);
	
	/**
	 * 按条件查询列表(不分页)
	 * @return
	 */
	List<WorkInfo> queryWorkInfoByCondition(Map<String, Object> map);
	
	/**
	 * 审核修改特定参数
	 * @param workInfo
	 * @return
	 */
	int updateWorkInfoById(WorkInfo workInfo);
	
	/**
	 * 管理平台查询待审核的兼职任务
	 * @param map
	 * @return
	 */
	List<WorkInfo> queryWorkInfoPageForManager(Map<String, Object> map);
	
	/**
	 * 批量修改状态
	 * @param workInfo
	 * @return
	 */
	int batchUpdateWorkInfos(List<WorkInfo> workInfo);
	
}
