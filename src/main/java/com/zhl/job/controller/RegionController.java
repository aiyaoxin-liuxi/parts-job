//package com.zhl.job.controller;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.log4j.Logger;
//import org.pub.util.date.DateConverter;
//import org.pub.util.uuid.KeySn;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.pagehelper.PageInfo;
//import com.zhl.job.controller.common.BaseController;
//import com.zhl.job.pojo.SettlementInfo;
//import com.zhl.job.pojo.SettlementInfoLog;
//import com.zhl.job.pojo.WorkInfo;
//import com.zhl.job.pojo.enums.WorkInfoInterview;
//import com.zhl.job.pojo.enums.WorkInfoJobType;
//import com.zhl.job.pojo.enums.WorkInfoMeal;
//import com.zhl.job.pojo.enums.WorkInfoSexRequire;
//import com.zhl.job.service.IRegionService;
//import com.zhl.job.service.IWorkInfoService;
//import com.zhl.job.service.IWorkInfoStatisService;
//import com.zhl.job.util.DateUtil;
//import com.zhl.job.util.Stringer;
//
//@Controller
//@RequestMapping(value = "/region")
//public class RegionController extends BaseController{
//	
//	private Logger logger = Logger.getLogger(RegionController.class);
//	
//	@Resource(name = "regionService")
//	private IRegionService regionService;
//	
//	@RequestMapping(value = "/queryAll")
//	@ResponseBody
//	public Object queryAll(ModelMap model) {
//		String keySn = KeySn.getKey();
//		Map<String, Object> map = regionService.queryAll();
//        Map<String, Object> pMap = (Map<String, Object>) map.get("pMap");
//		Map<String, Object> cMap = (Map<String, Object>) map.get("cMap");
//		Map<String, Object> aMap = (Map<String, Object>) map.get("aMap");
//        System.out.println(pMap.toString());
//        System.out.println(cMap.toString());
//        System.out.println(aMap.toString());
//		return success("写入成功");
//	}
//	
//	
//}
