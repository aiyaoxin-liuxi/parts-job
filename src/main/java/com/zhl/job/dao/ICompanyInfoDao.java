package com.zhl.job.dao;

import java.util.List;
import java.util.Map;

import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserLogInfo;
/**
 * 
  * @ClassName: ICompanyInfoDao
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月14日 下午6:43:20
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface ICompanyInfoDao {

	public int insertOne(CompanyInfo companyInfo);

	public CompanyInfo getCompanyInfoByCid(CompanyInfo companyInfo);

	public int updateOneByCid(CompanyInfo companyInfo);

	public CompanyInfo getCompanyInfoByUserId(UserLogInfo userLogInfo);
	
	
	/**
	 * 管理平台查询待审核的企业信息
	 * @param map
	 * @return
	 */
	List<CompanyInfo> queryCompanyInfoPageForManager(Map<String, Object> map);
	
	int updateCompanyByCid(CompanyInfo companyInfo);
}
