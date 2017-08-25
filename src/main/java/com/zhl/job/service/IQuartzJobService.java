package com.zhl.job.service;

import java.util.List;

import com.zhl.job.pojo.WorkInfo;

public interface IQuartzJobService {
	
	/**
	 * 设置工作完成状态
	 */
	String setWorkFinishState(List<WorkInfo> workList);

}
