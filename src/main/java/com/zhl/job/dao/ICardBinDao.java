package com.zhl.job.dao;

import java.util.List;

import com.zhl.job.pojo.CardBin;

public interface ICardBinDao {
	/**
	 * 查询所有卡bin信息
	 * @return
	 */
	List<CardBin> queryCardBinAll();

}
