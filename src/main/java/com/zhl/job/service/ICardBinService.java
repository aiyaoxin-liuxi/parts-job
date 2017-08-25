package com.zhl.job.service;

import java.util.List;

import com.zhl.job.pojo.CardBin;

public interface ICardBinService {
	
	/**
	 * 查询所有卡bin信息
	 * @return
	 */
	List<CardBin> queryCardBinAll();

}
