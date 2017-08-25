package com.zhl.job.service;

import com.zhl.job.pojo.WorkInfoStatis;

public interface IWorkInfoStatisService {
	
	/**
	 * 添加新增量信息
	 * @param workInfo 发布实体
	 */
	void addWorkInfoStatis(WorkInfoStatis workInfoStatis);
	
	/**
	 * 修改所有次数
	 * @param workInfo 发布实体
	 */
	void updateNum(WorkInfoStatis workInfoStatis);

}
