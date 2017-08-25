package com.zhl.job.service;

import java.util.List;

import com.zhl.job.pojo.BankInfo;

public interface IBankInfoService {
	
	/**
	 * 查询所有银行卡信息
	 * @return
	 */
	List<BankInfo> queryBankInfoAll();

}
