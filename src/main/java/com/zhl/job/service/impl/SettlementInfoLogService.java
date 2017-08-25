package com.zhl.job.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.ISettlementInfoLogDao;
import com.zhl.job.pojo.SettlementInfoLog;
import com.zhl.job.service.ISettlementInfoLogService;

@Service("settlementInfoLogService")
public class SettlementInfoLogService implements ISettlementInfoLogService {
	
	private Logger logger = Logger.getLogger(SettlementInfoLogService.class);
	
	@Resource
	private ISettlementInfoLogDao settlementInfoLogDao;

	@Override
	public SettlementInfoLog querySInfoByApplyId(String applyId) {
		return settlementInfoLogDao.querySInfoByApplyId(applyId);
	}

	@Override
	public PageInfo<SettlementInfoLog> queryListBySettleIdAndWorkDay(Map<String, Object> paramMap, Integer pageNo,
			Integer pageSize) {
		logger.debug("###>>> queryListBySettleIdAndWorkDay");
		PageHelper.startPage(pageNo, pageSize);
		List<SettlementInfoLog> settlementInfoLogs =settlementInfoLogDao.queryListBySettleIdAndWorkDay(paramMap);
		PageInfo<SettlementInfoLog> pageInfo = new PageInfo<SettlementInfoLog>(settlementInfoLogs);
		return pageInfo;
	}

	
	
}
