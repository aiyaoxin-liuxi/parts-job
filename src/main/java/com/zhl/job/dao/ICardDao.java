package com.zhl.job.dao;

import java.util.List;

import com.zhl.job.pojo.Card;
/**
 * 
  * @ClassName: 银行卡信息表 Dao
  * @author zhaotisheng	
  * @date 2017年3月16日 上午10:50:42
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface ICardDao {

	public int insertOne(Card card);
	
	public Card getCardById(Card card);
	
	public Card getCardByUserId(Card card);
	
	public int updateIsdelOneById(Card card);
	
	public List<Card> selectCardsByUserIdAndIsdel(Card card);

	public Card getCardByUserIdAndCardNo(Card card);

	public Card getCardByIdAndIsdel(Card card);
}
