package com.zhl.job.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IDictDao;
import com.zhl.job.pojo.Dict;
import com.zhl.job.service.IDictService;
/**
 * 
  * @ClassName: DictService
  * @author zhaotisheng	
  * @date 2017年4月10日 下午2:48:12
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
@Transactional
public class DictService implements IDictService {

	@Autowired
	IDictDao dictDao;
	
	
	public int insertOne(Dict dict) {
		return dictDao.insertOne(dict);
	}

	public int deleteOne(Dict dict) {
		return dictDao.deleteOne(dict);
	}

	public int updateOneById(Dict dict) {
		return dictDao.updateOneById(dict);
	}

	public List<Dict> findAll() {
		return dictDao.findAll();
	}

	public PageInfo<Dict> queryPage(Integer pageNo, Integer pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Dict> findAll = findAll();
		PageInfo<Dict> pageInfo = new PageInfo<Dict>(findAll);
		return pageInfo;
	}

	@Override
	public Dict findById(Dict dict) {
		return dictDao.findById(dict);
	}

}
