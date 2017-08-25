package com.zhl.job.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.job.dao.IBankInfoDao;
import com.zhl.job.pojo.BankInfo;
import com.zhl.job.service.IBankInfoService;

@Service("bankInfoService")
public class BankInfoService implements IBankInfoService {
	
	@Resource
	private IBankInfoDao bankInfoDao;

	@Override
	public List<BankInfo> queryBankInfoAll() {
		return bankInfoDao.queryBankInfoAll();
	}

	

}
