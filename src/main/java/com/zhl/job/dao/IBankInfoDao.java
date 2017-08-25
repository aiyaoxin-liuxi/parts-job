package com.zhl.job.dao;

import java.util.List;

import com.zhl.job.pojo.BankInfo;

public interface IBankInfoDao {
	/**
	 * 查询所有银行卡信息
	 * @return
	 */
	List<BankInfo> queryBankInfoAll();

}
