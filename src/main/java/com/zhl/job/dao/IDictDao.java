package com.zhl.job.dao;

import java.util.List;

import com.zhl.job.pojo.Dict;
/**
 * 
 * 
  * @ClassName: ICompanyInfoDao
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月14日 下午6:43:20
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface IDictDao {

	int insertOne(Dict dict);
	int deleteOne(Dict dict);
	int updateOneById(Dict dict);
	List<Dict> findAll();
	Dict findById(Dict dict);
}
