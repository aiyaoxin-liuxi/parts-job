package com.zhl.job.dao;

import java.util.Map;

import com.zhl.job.pojo.WorkInfoStatis;

/**
 * 发布企业兼职的增量信息dao
 * @author 刘熙
 */
public interface IWorkInfoStatisDao {
	
	/**
	 * 添加新增量信息
	 * @param workInfo 发布实体
	 */
	void addWorkInfoStatis(WorkInfoStatis workInfoStatis);
	
	/**
	 * 修改所有次数
	 * @param workInfo 发布实体
	 */
	int updateNum(WorkInfoStatis workInfoStatis);
	
	/**
	 * 根据workid查询增量数据
	 * @param workId
	 * @return
	 */
	WorkInfoStatis queryWsInfoByWorkId(Map<String, Object> map);
	
	
}
