//package com.zhl.job.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.zhl.job.dao.ITestDao;
//import com.zhl.job.interceptor.mybatis.PagingBounds;
//import com.zhl.job.pojo.ApplyInfo;
//import com.zhl.job.service.ITestService;
//
//@Service("testService")
//public class TestService implements ITestService {
//	
//	@Resource
//	private ITestDao testDao;
//
//	@Override
//	public List<ApplyInfo> selectPaging(PagingBounds bounds, Map<String, Object> map) {
//		return testDao.selectPaging(bounds, map);
//	}
//
//	@Override
//	public ApplyInfo queryByApplyId(Map<String, Object> map) {
//		return testDao.queryByApplyId(map);
//	}
//
//
//}
