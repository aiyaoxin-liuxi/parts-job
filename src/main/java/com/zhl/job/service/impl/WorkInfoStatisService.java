package com.zhl.job.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.job.dao.IWorkInfoStatisDao;
import com.zhl.job.pojo.WorkInfoStatis;
import com.zhl.job.service.IWorkInfoStatisService;

@Service("workInfoStatisService")
public class WorkInfoStatisService implements IWorkInfoStatisService {
	
	@Resource
	private IWorkInfoStatisDao workInfoStatisDao;

	@Override
	public void addWorkInfoStatis(WorkInfoStatis workInfoStatis) {
		workInfoStatisDao.addWorkInfoStatis(workInfoStatis);
	}

	@Override
	public void updateNum(WorkInfoStatis workInfoStatis) {
		workInfoStatisDao.updateNum(workInfoStatis);
	}

	


	


}
