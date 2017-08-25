package com.zhl.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.job.dao.ICardValidResDao;
import com.zhl.job.pojo.CardValidRes;
/**
 * 
  * @ClassName: CardValidResService
  * @author zhaotisheng	
  * @date 2017年4月1日 下午12:00:31
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
@Transactional
public class CardValidResService {

	@Autowired
	ICardValidResDao iCardValidResDao;
	
	public int inertOne(CardValidRes cardValidRes){
		return iCardValidResDao.insertOne(cardValidRes);
	}
}
