package com.zhl.job.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.zhl.job.interceptor.mybatis.PagingBounds;
//import com.zhl.job.pojo.ApplyInfo;
//import com.zhl.job.service.ITestService;
//
//
//@Controller
//@RequestMapping(value = "/test")
//public class TestController {
//
//	@Resource(name = "testService")
//	private ITestService testService;
//
//	/**
//	 * 添加
//	 * 
//	 * @param request
//	 * @param newsComment
//	 * @return
//	 */
//	@RequestMapping(value = "/addNewsComment")
//	@ResponseBody
//	public Object addNewsComment(HttpServletRequest request) {
//		try {
//			// 分页
//			PagingBounds bounds = new PagingBounds(1, 2);
//			Map<String, Object> map = new HashMap<String, Object>();
//
//			List<ApplyInfo> list = testService.selectPaging(bounds, map);
//			
//			System.out.println(list);
//	        //总记录数
//	        System.out.println(bounds.getTotal());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "sre";
//	}
//	
//    
//
//}
