package com.zhl.job.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.Dict;

public interface IDictService {

	int insertOne(Dict dict);
	int deleteOne(Dict dict);
	int updateOneById(Dict dict);
	List<Dict> findAll();
	PageInfo<Dict> queryPage(Integer pageNo, Integer pageSize);
	Dict findById(Dict dict);
}
