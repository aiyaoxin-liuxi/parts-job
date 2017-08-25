package com.zhl.job.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.job.dao.ICardBinDao;
import com.zhl.job.pojo.CardBin;
import com.zhl.job.service.ICardBinService;

@Service("cardBinService")
public class CardBinService implements ICardBinService {
	
	@Resource
	private ICardBinDao cardBinDao;

	@Override
	public List<CardBin> queryCardBinAll() {
		return cardBinDao.queryCardBinAll();
	}

	

}
