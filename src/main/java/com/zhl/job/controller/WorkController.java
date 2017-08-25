package com.zhl.job.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.pub.util.date.DateConverter;
import org.pub.util.memcached.MemcacheManager;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhl.job.controller.common.BaseController;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.enums.ApplyInfoState;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.pojo.enums.WorkInfoBalanceType;
import com.zhl.job.pojo.enums.WorkInfoInterview;
import com.zhl.job.pojo.enums.WorkInfoJobType;
import com.zhl.job.pojo.enums.WorkInfoMeal;
import com.zhl.job.pojo.enums.WorkInfoMoneyType;
import com.zhl.job.pojo.enums.WorkInfoSexRequire;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.pojo.enums.WorkInfoType;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Constant;
import com.zhl.job.util.DateUtil;
import com.zhl.job.util.Stringer;

/**
 * 企业发布信息相关
 * @author 刘熙
 *
 */
@Controller
@RequestMapping(value = "/work")
public class WorkController extends BaseController{
	
	private Logger logger = Logger.getLogger(WorkController.class);
	
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
//	@Resource(name = "workInfoStatisService")
//	private IWorkInfoStatisService workInfoStatisService;
	@Resource(name = "applyInfoService")
	private IApplyInfoService applyInfoService;
	
//	@Autowired
//	private CommonService commonService;
//	@Autowired
//	@Qualifier("userLogInfoService")
//	private UserLogInfoService userLogInfoService;
	/**
	 * 发布企业信息 1、WorkInfo表添加信息 2、workinfo_statis表添加信息
	 * 
	 * @param request
	 * @param newsComment
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/releaseJobInfo")
	@ResponseBody
	public Object releaseJobInfo(ModelMap model, HttpServletRequest request, WorkInfo workInfo) throws NoLoginException {
		// session 中获取
		Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
		Object entity = request.getSession().getAttribute(Constant.LOGIN_COMPANYINFO_ENTITY);
		if(null != attribute && null != entity){
			if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
				CompanyInfo companyInfo = (CompanyInfo) entity;
				if(State.AUDIT_SUCC.getCode().equals(companyInfo.getState())){
					// session 中获取
					String logUserId = getLoginUserId(request);
					String cId = (String) getloginCompanyinfoId(request);
					
					String keySn = KeySn.getKey();
					String check = checkReleaseJobInfo(workInfo);
					if(null != check){
						return error("参数验证失败：" + check);
					}
					try {
						workInfo.setUserId(logUserId);
						workInfo.setCid(cId);
						workInfo.setBalanceType(WorkInfoBalanceType.CODE00.getCode());
						workInfo.setType(WorkInfoType.CODE00.getCode());
						workInfo.setState(WorkInfoState.CODE00.getCode());
						workInfo.setCreatedate(new Date());
						workInfo.setIsdel(IsDel.CODE00.getCode());
						
						workInfoService.addWorkInfo(keySn, workInfo);
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					return success("发布成功");
				} else {
					// 企业未认证
					return "work/toReleaseJobInfo";
				}
			} else {
				return error("不是企业用户");
			}
		} else {
			return error("login_err", "请重新登录");
		}
	}
	
	/**
	 * 修改招聘信息
	 * @param model
	 * @param workId
	 * @return
	 */
	@RequestMapping(value = "/updateJob")
	@ResponseBody
	public Object updateWorkInfo(ModelMap model, WorkInfo workInfo) {
		
		String check = checkReleaseJobInfo(workInfo);
		if(null == check){
			try {
				workInfoService.updateWorkInfoCondition(workInfo);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			return success("已取消");
		} else {
			return error("参数验证失败：" + check);
		}
	}
	
	/**
	 * 根据市查询区县
	 * @param model
	 * @param workId
	 * @return
	 */
	@NoAuth
	@RequestMapping(value = "/selectArea")
	@ResponseBody
	public Object selectArea(ModelMap model, String cityCode) {
		Map<String, Object> aMapAll = null;
		if(null != cityCode && !"".equals(cityCode)){
			aMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.AREA_MAP);
			model.addAttribute("aMapAll", aMapAll.get(cityCode));
		}
		return success(aMapAll.get(cityCode));
	}
	
	// ---------------------------- 以下为跳转 -------------------------------------
	
	/**
	 * 发布企业信息跳转
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/toReleaseJobInfo")
	public Object toReleaseJobInfo(ModelMap model, HttpServletRequest request, String city){

		try {
//			if(null == city || "".equals(city)){
//				city = "52";
//			}
			// session 中获取
			Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
			Object entity = request.getSession().getAttribute(Constant.LOGIN_COMPANYINFO_ENTITY);
			if(null != attribute && null != entity){
				if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
					
					// session 中获取
					CompanyInfo companyInfo = (CompanyInfo) entity;
					System.out.println(companyInfo.getState());
					if(State.AUDIT_SUCC.getCode().equals(companyInfo.getState())){
						Map<String, Object> sexRequireMap = WorkInfoSexRequire.getAllList();
						Map<String, Object> mealMap = WorkInfoMeal.getAllList();
						Map<String, Object> interviewMap = WorkInfoInterview.getAllList();
						Map<String, Object> jobTypeMap = WorkInfoJobType.getAllList();
						Map<String, Object> moneyTypeMap = WorkInfoMoneyType.getAllList();
						Map<String, Object> cMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.CITY_MAP);
						
						model.addAttribute("sexRequireMap", sexRequireMap);
						model.addAttribute("mealMap", mealMap);
						model.addAttribute("interviewMap", interviewMap);
						model.addAttribute("jobTypeMap", jobTypeMap);
						model.addAttribute("moneyTypeMap", moneyTypeMap);
						
						model.addAttribute("cMapAll", cMapAll);
						
						model.addAttribute(Constant.DIV_ACTIVE, DivActive.PUB_JOB.getCode());
						
						return "compayinfo/release";
					} else {
						// 企业未认证
						model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
						alertError(model, "企业信息未审核，请先进行企业信息认证");
						return "forward:/compayinfo/fill";
					}
				} else {
					alertError(model, "当前账号不是企业用户");
					return "compayinfo/login";
				}
			} else {
				// 企业未认证
				model.addAttribute(Constant.DIV_ACTIVE, DivActive.COM_INF.getCode());
				alertError(model, "请先进行企业信息认证");
				return "forward:/compayinfo/fill";
//				return "redirect:/compayinfo/fill";
//				return "compayinfo/fill";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			alertError(model, "系统异常");
			return "forward:/compayinfo/fill";
		}
	}
	/**
	 * 查询招聘列表跳转
	 * @return
	 */
	@NoAuth
	@RequestMapping(value = "/toQueryWorkList")
	public Object toQueryWorkList(ModelMap model, HttpServletRequest request, Integer pageNo, Integer pageSize, 
			String city, String jobType, String area, String type, String sexRequire, String jobDay, String seachLike) {
		
		try {
			if(null != seachLike && !"".equals(seachLike)){
				seachLike = URLDecoder.decode(seachLike, "utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		if(null == city || "".equals(city)){
			city = (String) request.getSession().getAttribute(Constant.NOW_CITY);
		}
		if(null == city || "".equals(city)){
			city = city == null ? "220" : city;
		}
		request.getSession().setAttribute(Constant.NOW_CITY, city);
		
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		
		PageInfo<WorkInfo> pageInfo = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Date jobDayDate = null;
			if(null != jobDay && !"".equals(jobDay.trim())){
				if("mt".equals(jobDay)){
					jobDayDate = DateUtil.getDateNextDay(1);
				} else if("ht".equals(jobDay)){
					jobDayDate = DateUtil.getDateNextDay(2);
				} else if("3t".equals(jobDay)){
					jobDayDate = DateUtil.getDateNextDay(3);
				} else {
					jobDayDate = null;
				}
			}
			// session 中获取
			Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
			String logUserId = "";
			if(null != attribute){
				logUserId = attribute.toString();
			}
			
			// 分页
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("city", city);
			paramMap.put("jobType", jobType);
			paramMap.put("area", area);
			paramMap.put("type", type);
			paramMap.put("sexRequire", sexRequire);
			paramMap.put("jobDay", jobDayDate);
			paramMap.put("seachLike", seachLike);
			
			
			pageInfo = workInfoService.queryByPage(paramMap, pageNo, pageSize, logUserId);
			
			
			model.addAttribute("workList", pageInfo.getList());
			model.addAttribute("pageNo", pageInfo.getPageNum());
			model.addAttribute("pageSize", pageInfo.getPageSize());
			model.addAttribute("total", pageInfo.getTotal());
			model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
			model.addAttribute("pages", pageInfo.getPages());
			
			model.addAttribute("prePage", pageInfo.getPrePage());
			model.addAttribute("nextPage", pageInfo.getNextPage());
			
			
			// 获取所有岗位类别
			Map<String, Object> jobTypeMap = WorkInfoJobType.getAllList();
			model.addAttribute("jobTypeMap", jobTypeMap);
			
			// 所有市级
			Map<String, Object> cMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.CITY_MAP);
			model.addAttribute("cMapAll", cMapAll);
			// 当前市级
			System.out.println(city);
			System.out.println(cMapAll.get(city));
			model.addAttribute("nowCityCode", city);
			model.addAttribute("nowCityName", cMapAll.get(city));
			// 所有区
			Map<String, Object> aMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.AREA_MAP);
			model.addAttribute("aMapAll", aMapAll.get(city));
			// 性别下拉
			Map<String, Object> sexMap = WorkInfoSexRequire.getAllList();
			model.addAttribute("sexMap", sexMap);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
			alertError(model, "系统异常");
		}
//		String loginUserId = super.getLoginUserId(request);
//		UserLogInfo selectByPk = userLogInfoService.selectByPk(loginUserId);
// 	    commonService.setLoginInfo(request,selectByPk);
		return "work/workList";
	}
	
	/**
	 * 查询详细信息跳转
	 * @return
	 */
	@NoAuth
	@RequestMapping(value = "/toParttimeDetail")
	public Object toParttimeDetail(ModelMap model, HttpServletRequest request, String workId, Integer pageNo, Integer pageSize, 
			String city, String jobType, String area, String type, String sexRequire, String jobDay, String seachLike) {
		
		// session 中获取
		Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
		String logUserId = "";
		if(null != attribute){
			logUserId = attribute.toString();
		}
		Object entity = request.getSession().getAttribute(Constant.LOGIN_USER_ENTITY);
		String pid = "";
		if(null != entity){
			UserInfo userInfo = (UserInfo) entity;
			pid = userInfo.getPid();
		}
		
		String check = checkQueryWorkDetail(workId);
		if(null != check){
			return error("参数验证失败：" + check);
		}
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> workMap = null;
		String keySn = KeySn.getKey();
		try {
			workMap = workInfoService.queryWorkInfoDetail(workId, logUserId, pid);
			
			if(!workMap.containsKey("workInfo")){
				return error("未找到详细信息数据");
			}
			
			WorkInfo workInfo = (WorkInfo) workMap.get("workInfo");
			
			List<Date> dateList = applyInfoService.screenDate(logUserId, workInfo.getAllowChooseDate(), workInfo.getWorkStartDate(), workInfo.getWorkEndDate());
	    	workInfo.setShowDateList(dateList);
			
			model.addAttribute("workInfo", workMap.get("workInfo"));
			model.addAttribute("employCount", workMap.get("employCount"));
			model.addAttribute("em", workMap.get("em"));
			model.addAttribute("dateList", dateList);
			
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("city", city);
			model.addAttribute("jobType", jobType);
			model.addAttribute("area", area);
			model.addAttribute("type", type);
			model.addAttribute("sexRequire", sexRequire);
			model.addAttribute("jobDay", jobDay);
			model.addAttribute("seachLike", seachLike);
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return "work/parttime_detail";
	}
	
	
	/**
	 * 查询招聘详细跳转
	 * @return
	 */
	@NoAuth
	@RequestMapping(value = "/toQueryWorkDetail")
	public Object toQueryWorkDetail() {
		return "work/toQueryWorkDetail";
	}
	/**
	 * 企业发布信息管理跳转
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/parttimeMannage")
	public Object parttimeMannage(ModelMap model, HttpServletRequest request, Integer pageNo, Integer pageSize, String state) throws NoLoginException {
		
		checkLoginStatus(request);// 检查用户登录
		if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
			// session 中获取
			String logUserId = getLoginUserId(request);
			String cId = (String) getloginCompanyinfoId(request);
			
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? 5 : pageSize;
			state = state == null ? WorkInfoState.CODE00.getCode() : state;
			
			PageInfo<WorkInfo> pageInfo = null;
			Map<String, Object> returnMap = new HashMap<String, Object>();
			try {
				Date jobDayDate = null;
				// 分页
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userId", logUserId);
				paramMap.put("cid", cId);
				paramMap.put("state", state);
				
				pageInfo = workInfoService.queryWorkInfoPageByState(paramMap, pageNo, pageSize);
				
				model.addAttribute("workList", pageInfo.getList());
				model.addAttribute("pageNo", pageInfo.getPageNum());
				model.addAttribute("pageSize", pageInfo.getPageSize());
				model.addAttribute("total", pageInfo.getTotal());
				model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
				model.addAttribute("pages", pageInfo.getPages());
				
				model.addAttribute("prePage", pageInfo.getPrePage());
				model.addAttribute("nextPage", pageInfo.getNextPage());
				
				model.addAttribute("state", state);
				
				model.addAttribute(Constant.DIV_ACTIVE, DivActive.JOB_MAN.getCode());
				
				
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			
			return "compayinfo/parttime_mannage";
		} else {
			alertError(model, "当前账号不是企业用户");
			return "compayinfo/auditSucc";
		}
	}
	/**
	 * 企业发布信息管理————详情_00
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/mannageWorkdetail")
	public Object mannageWorkdetail(ModelMap model, HttpServletRequest request, String id, String state) throws NoLoginException {
		
		checkLoginStatus(request);// 检查用户登录
		if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
			try {
				WorkInfo workInfo = workInfoService.queryWorkInfo(id);
				model.addAttribute("workInfo", workInfo);
				model.addAttribute(Constant.DIV_ACTIVE, DivActive.JOB_MAN.getCode());
				if(null == state || "".equals(state)){
					model.addAttribute("state", "00");
				} else {
					model.addAttribute("state", state);
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
			return "compayinfo/mannagedetail_00";
		} else {
			alertError(model, "当前账号不是企业用户");
			return "compayinfo/auditSucc";
		}
	}
	
	/**
	 * 企业发布信息管理————详情02（不分页）
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/mannagedetailNotPage")
	public Object mannageDetailNoPage(ModelMap model, HttpServletRequest request, String id, String state, String dateStr) throws NoLoginException {
		String urlString = request.getServletPath() + "?" + request.getQueryString(); //请求参数;
		try {
			urlString = URLEncoder.encode(urlString, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		checkLoginStatus(request);// 检查用户登录
		if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
			
//			pageNo = pageNo == null ? 1 : pageNo;
//			pageSize = pageSize == null ? 5 : pageSize;
			
			// session 中获取
			String logUserId = getLoginUserId(request);
			String cId = (String) getloginCompanyinfoId(request);
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			Map<String, Object> returnMap = new HashMap<String, Object>();
			try {
				Date date = null;
				if(null != dateStr && !"".equals(dateStr)){
					date = DateUtil.string2Date(dateStr);
				} else {
					date = DateUtil.getDateToDay("yyyy-MM-dd");
				}
				
				reMap = workInfoService.queryWorkInfoApplyByDateNotPage(id, date);
				
				List<ApplyInfo> appList = null;
				WorkInfo workInfo = (WorkInfo) reMap.get("workInfo");
				List<ApplyInfo> list = (List<ApplyInfo>) reMap.get("appList");
				if(null != workInfo && null != list && list.size() != 0){
					appList = applyInfoService.queryOldJobType(list, ApplyInfoState.CODE02.getCode(), workInfo.getJobType());
				}
				
				if(null == state || "".equals(state)){
					model.addAttribute("state", "00");
				} else {
					model.addAttribute("state", state);
				}
				
				model.addAttribute("employCount", reMap.get("count"));
				model.addAttribute("workInfo", reMap.get("workInfo"));
				model.addAttribute("appList", list);
				model.addAttribute("dateList", reMap.get("dateList"));
				model.addAttribute("nowDate", reMap.get("date"));
				
				model.addAttribute(Constant.DIV_ACTIVE, DivActive.JOB_MAN.getCode());
				model.addAttribute("urlString", urlString);
				
			} catch (Exception e) {
				logger.info(e.getMessage());
				alertError(model, "系统异常");
			}
			return "compayinfo/mannagedetail_02";
		} else {
			alertError(model, "当前账号不是企业用户");
			return "compayinfo/auditSucc";
		}
	}
	
	/**
	 * 企业发布信息管理————详情其他
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/mannagedetail")
	public Object mannageDetail(ModelMap model, HttpServletRequest request, String id, String state, String dateStr, Integer pageNo, Integer pageSize) throws NoLoginException {
		
		checkLoginStatus(request);// 检查用户登录
		if(UserType.COMPANY.getCode().equals(getLoginUserType(request))){
			
			pageNo = pageNo == null ? 1 : pageNo;
			pageSize = pageSize == null ? 5 : pageSize;
			
			// session 中获取
			String logUserId = getLoginUserId(request);
			String cId = (String) getloginCompanyinfoId(request);
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			Map<String, Object> returnMap = new HashMap<String, Object>();
			try {
				Date date = null;
				if(null != dateStr && !"".equals(dateStr)){
					date = DateConverter.string2Date(dateStr);
				}
				
				reMap = workInfoService.queryWorkInfoApplyByDate(id, date, pageNo, pageSize);
				PageInfo<ApplyInfo> pageInfo = (PageInfo<ApplyInfo>) reMap.get("pageInfo");
				
				List<ApplyInfo> appList = null;
				WorkInfo workInfo = (WorkInfo) reMap.get("workInfo");
				List<ApplyInfo> list = pageInfo.getList();
				if(null != workInfo && null != list && list.size() != 0){
					appList = applyInfoService.queryOldJobType(list, ApplyInfoState.CODE02.getCode(), workInfo.getJobType());
				}
				
				if(null == state || "".equals(state)){
					model.addAttribute("state", "00");
				} else {
					model.addAttribute("state", state);
				}
				model.addAttribute("employCount", reMap.get("count"));
				model.addAttribute("workInfo", reMap.get("workInfo"));
				model.addAttribute("appList", appList);
				model.addAttribute("dateList", reMap.get("dateList"));
				
				model.addAttribute("pageNo", pageInfo.getPageNum());
				model.addAttribute("pageSize", pageInfo.getPageSize());
				model.addAttribute("total", pageInfo.getTotal());
				model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
				model.addAttribute("pages", pageInfo.getPages());
				
				model.addAttribute("prePage", pageInfo.getPrePage());
				model.addAttribute("nextPage", pageInfo.getNextPage());
				
				if(null == dateStr || "".equals(dateStr)){
					if(null != workInfo){
						date = workInfo.getWorkStartDate();
					} else {
						date = DateUtil.getDateToDay("yyyy-MM-dd");
					}
				}
				model.addAttribute("nowDate", date);
				
				model.addAttribute(Constant.DIV_ACTIVE, DivActive.JOB_MAN.getCode());
				
				if("01".equals(state)){
					return "compayinfo/mannagedetail_01";
				} else if("03".equals(state)){
					return "compayinfo/mannagedetail_03";
				} else if("02".equals(state)){
					return "compayinfo/mannagedetail_02";
				}
			} catch (Exception e) {
				e.printStackTrace();
				alertError(model, "系统异常");
			}
		} else {
			alertError(model, "当前账号不是企业用户");
			return "compayinfo/auditSucc";
		}
		alertError(model, "系统异常");
		return "compayinfo/auditSucc";
	}

	
	// -------------------------- 以下为验证------------------
	
	private String checkQueryWorkDetail(String workId){
		if(!verifyParam(workId)){
			return "工作id不能为空";
		}
		return null;
	}
	
	private String checkReleaseJobInfo(WorkInfo workInfo){
		if(null == workInfo){
			return "招聘信息不能为空";
		}
		if(!verifyParam(workInfo.getWorkTitle())){
			return "兼职标题不能为空";
		}
		if(!verifyParam(workInfo.getWorkDetail())){
			return "兼职详细内容不能为空";
		}
		if(!verifyParam(workInfo.getJobType())){
			return "岗位类别不能为空";
		}
		if(!verifyParam(workInfo.getRequire())){
			return "工作要求不能为空";
		}
		if(!verifyParam(workInfo.getSexRequire())){
			return "性别要求不能为空";
		}
		if(!verifyParam(workInfo.getWorkMeal())){
			return "工作餐情况不能为空";
		}
//		if(!verifyParam(workInfo.getProvince())){
//			return "省不能为空";
//		}
		if(!verifyParam(workInfo.getCity())){
			return "市不能为空";
		}
		if(!verifyParam(workInfo.getArea())){
			return "区不能为空";
		}
//		if(!verifyParam(workInfo.getAddressDetail())){
//			return "详细地址不能为空";
//		}
//		if(!verifyParam(workInfo.getReleaseStartDate())){
//			return "申请起始时间不能为空";
//		}
//		if(!verifyParam(workInfo.getApplyEndDate())){
//			return "申请截止时间不能为空";
//		}
//		if(DateConverter.getMillisDiff(workInfo.getApplyEndDate(), workInfo.getReleaseStartDate()) < 0){
//			return "申请截止时间应大于申请起始时间";
//		}
		if(!verifyParam(workInfo.getWorkStartDate())){
			return "工作起始日不能为空";
		}
		if(!verifyParam(workInfo.getWorkEndDate())){
			return "工作结束日不能为空";
		}
//		if(DateConverter.getMillisDiff(workInfo.getWorkEndDate(), workInfo.getWorkStartDate()) < 0){
//			return "工作结束日应大于工作起始日";
//		}
		if(!verifyParam(workInfo.getWorkTime())){
			return "上班时间不能为空";
		}
		if(!verifyParam(workInfo.getPeopleNum())){
			return "招聘人数不能为空";
		}
//		if(!verifyParam(workInfo.getBalanceType())){
//			return "结算方式不能为空";
//		}
		if(!verifyParam(workInfo.getMoney())){
			return "金额不能为空";
		}
		if(!verifyParam(workInfo.getMoneyType())){
			return "金额单位不能为空";
		}
		if(!verifyParam(workInfo.getContacts())){
			return "联系人姓名不能为空";
		}
		if(!verifyParam(workInfo.getContactsMobile())){
			return "联系人手机号不能为空";
		}
		if(!Stringer.isMobile(workInfo.getContactsMobile())){
			return "联系人手机号不规范";
		}
//		if(!verifyParam(workInfo.getContactsEmail())){
//			return "联系人邮箱不能为空";
//		}
//		if(!verifyParam(workInfo.getAgreement())){
//			return "协议条款不能为空";
//		}
		if(!verifyParam(workInfo.getInterview())){
			return "是否面试不能为空";
		}
		if(!verifyParam(workInfo.getLongitude())){
			return "地图参数不能为空";
		}
		if(!verifyParam(workInfo.getLatitude())){
			return "地图参数不能为空";
		}
		
		// 最低薪资规则判定
		String moneyType = workInfo.getMoneyType();	// 薪资单位
		String workHour = workInfo.getWorkHour();	// 工作时长
		BigDecimal money = workInfo.getMoney();		// 薪资待遇
		
		if(WorkInfoMoneyType.CODE00.getCode().equals(moneyType)){
			// 小时
			if(null != workHour && !"".equals(workHour)){
				BigDecimal workHourB = new BigDecimal(workHour);
				if(BigDecimalUtil.BigDecimalLessThanAndEqual(workHourB, new BigDecimal(1)) 
						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(25)))
				{
					return "时薪不能低于25元/时";
				}
				if(BigDecimalUtil.BigDecimalGreaterThan(workHourB, new BigDecimal(1)) 
						&& BigDecimalUtil.BigDecimalLessThan(workHourB, new BigDecimal(3))
						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(20)))
				{
					return "时薪不能低于20元/时";
				}
				if(BigDecimalUtil.BigDecimalGreaterThanAndEqual(workHourB, new BigDecimal(3))
						&& BigDecimalUtil.BigDecimalLessThan(workHourB, new BigDecimal(5))
						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(20)))
				{
					return "时薪不能低于15元/时";
				}
				if(BigDecimalUtil.BigDecimalGreaterThanAndEqual(workHourB, new BigDecimal(5)) 
						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(15)))
				{
					return "时薪不能低于10元/时";
				}
			} else {
				return "请输入工作时长";
			}
		} else {
			// 天
			if(BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(70))){
				return "薪资待遇不能低于70元";
			}
		}
		
		return null;
	}
	
}
