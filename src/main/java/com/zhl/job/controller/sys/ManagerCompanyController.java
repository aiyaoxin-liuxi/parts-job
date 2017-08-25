package com.zhl.job.controller.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pub.util.memcached.MemcacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.WorkInfoInterview;
import com.zhl.job.pojo.enums.WorkInfoJobType;
import com.zhl.job.pojo.enums.WorkInfoMeal;
import com.zhl.job.pojo.enums.WorkInfoMoneyType;
import com.zhl.job.pojo.enums.WorkInfoSexRequire;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.service.impl.CompanyInfoService;
import com.zhl.job.service.impl.ManagerCompanyService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 
 * @author wxw
 *
 */
@Controller
@RequestMapping(value = "/sys")
public class ManagerCompanyController  extends BaseController{
	
	private Logger logs = LoggerFactory.getLogger(ManagerCompanyController.class);
	
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	@Resource
	private ManagerCompanyService managerCompanyService;
	@Resource
	private CompanyInfoService companyInfoService;
	@Resource
	private IAccountflowService accountflowService;
//	@Resource
//	private IUserInfoService userInfoService; 
	@Resource
	private IUserLogInfoService userLogInfoService;
	@Resource
	private IApplyInfoService applyInfoService;


	/**
	 * 企业审核
	 * @param model
	 * @param request
	 * @throws NoLoginException
	 */
	@RequestMapping(value = "/companyContractList")
	public void companyContractList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize) throws NoLoginException {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<CompanyInfo> pageInfo = null;
		// 分页
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("state", State.WAIT_AUDIT.getCode());
		
		pageInfo = companyInfoService.queryCompanyInfoPageForManager(paramMap, pageNo, pageSize);
		
		model.addAttribute("companyList", setAttr(pageInfo.getList()));
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.CON_LIST.getCode());
	}

	private List<CompanyInfo> setAttr(List<CompanyInfo> list) {
		List<CompanyInfo> newList=new ArrayList<CompanyInfo>();
		if(!Stringer.isNullOrEmpty(list)){
			for (CompanyInfo each:list) {
				String state = each.getState();
				if(!Stringer.isNullOrEmpty(state)){
					State parseOf;
					try {
						parseOf = State.parseOf(state);
						each.setState(parseOf.getName());
					} catch (Exception e) {
						each.setState("未知状态"+state);
					}
				}
				newList.add(each);
			}
		}
		return newList;
	}

	/**
	  * 企业签约审核与驳回
	  * @param model
	  * @param request
	  * @return void
	  * @throws
	 */
	@RequestMapping(value = "/companyContractCheck")
	@ResponseBody
	public Object companyContractCheck(Model model, HttpServletRequest request,String cid,String flag) throws NoLoginException {
		logs.debug("##>>>companyContractCheck Request OK!");
		String username = (String) request.getSession().getAttribute("USER_NAME");
		if(Stringer.isNullOrEmpty(username)){
			return error("请重新登录");
		}
		if(Stringer.isNullOrEmpty(cid)){
			return error("参数为空");
		}
		if(Stringer.isNullOrEmpty(flag)){
			return error("参数为空");
		}
		return managerCompanyService.companyContractCheck(request,cid, flag,username);
	}
	/**
	 * 兼职审核
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/parttimeReleaseList")
	public void parttimeReleaseList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<WorkInfo> pageInfo = null;
		// 分页
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", WorkInfoState.CODE00.getCode());
		
		pageInfo = workInfoService.queryByPage(paramMap, pageNo, pageSize);
		
		model.addAttribute("workList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.AUDIT_CHECK.getCode());
	}
	
	/**
	 * 兼职发布审核与驳回
	 * @param model
	 * @param request
	 * @throws NoLoginException
	 */
	@RequestMapping(value = "/parttimeReleaseCheck")
	@ResponseBody
	public Object parttimeReleaseCheck(Model model, HttpServletRequest request,String parttimeId,String flag) throws NoLoginException {
		logs.debug("##>>>parttimeReleaseCheck Request OK!");
		String username = (String) request.getSession().getAttribute("USER_NAME");
		if(Stringer.isNullOrEmpty(username)){
			return error("请重新登录");
		}
		if(Stringer.isNullOrEmpty(parttimeId)){
			return error("参数为空");
		}
		if(Stringer.isNullOrEmpty(flag)){
			return error("参数为空");
		}
		return managerCompanyService.parttimCheck(parttimeId, flag,username);
	}
	
	/**
	 * 提现审核
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/cashOrderList")
	public void cashOrderList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<Accountflow> pageInfo = null;
		// 分页
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("state", State.WAIT_AUDIT.getCode());
		
		pageInfo = accountflowService.queryAccountflowPage(paramMap, pageNo, pageSize);
		
		model.addAttribute("accountFlowList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.CASH_ORDER.getCode());
	}
	
	/**
	 * 提现审核与驳回
	 * @param model
	 * @param request
	 * @throws NoLoginException
	 */
	@RequestMapping(value = "/cashOrderCheck")
	@ResponseBody
	public Object cashOrderCheck(Model model, HttpServletRequest request,String accountId,String flag) {
		if(Stringer.isNullOrEmpty(accountId)){
			return error("参数为空");
		}
		if(Stringer.isNullOrEmpty(flag)){
			return error("参数为空");
		}
		return managerCompanyService.cashOrderCheck(accountId, flag);
	}
	
	/**
	 * 个人与企业账户启用禁用
	 * @param model
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param mobile
	 */
	@RequestMapping(value = "/userLogInfoList")
	public void userInfoList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize,String mobile) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<UserLogInfo> pageInfo = null;
		// 分页
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!Stringer.isNullOrEmpty(mobile)){
			paramMap.put("mobile", mobile);
			
			model.addAttribute("mobile", mobile);
		}
		
		pageInfo = userLogInfoService.queryUserLogInfoPage(paramMap, pageNo, pageSize);
		
		model.addAttribute("userLogInfoList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.USERLOG_LIST.getCode());
	}
	
	@RequestMapping(value = "/enDisableUserLogInfo")
	@ResponseBody
	public Object enDisableUserInfo(Model model, HttpServletRequest request,String id,String flag) {
		String username = (String) request.getSession().getAttribute("USER_NAME");
		if(Stringer.isNullOrEmpty(username)){
			return error("请重新登录");
		}
		if(Stringer.isNullOrEmpty(id)){
			return error("参数为空");
		}
		if(Stringer.isNullOrEmpty(flag)){
			return error("参数为空");
		}
		return managerCompanyService.enDisableUserLogInfo(id, flag,username);
	}
	/**
	 * 兼职列表
	 * @param model
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @param state
	 */
	@RequestMapping(value = "/workInfoList")
	public void workInfoList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize,String state) {
		
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<WorkInfo> pageInfo = null;
		// 分页
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!Stringer.isNullOrEmpty(state)){
			paramMap.put("state", state);
			model.addAttribute("state", state);
		}
		
		pageInfo = workInfoService.queryByPage(paramMap, pageNo, pageSize);
		
		model.addAttribute("workList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		
		model.addAttribute("stateList", WorkInfoState.values());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.WORKINFO_LIST.getCode());
	}
	
	/**
	 * 兼职列表————允许/停止报名
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "/updateApplySwitch")
	@ResponseBody
	public Object updateApplySwitch(Model model, HttpServletRequest request, String id, String applySwitch) {
		
		String username = (String) request.getSession().getAttribute("USER_NAME");
		if(Stringer.isNullOrEmpty(username)){
			return error("请重新登录");
		}
		ResponseEntity res = new ResponseEntity();
		
		WorkInfo workInfo = new WorkInfo();
		workInfo.setId(id);
		workInfo.setApplySwitch(applySwitch);
		workInfo.setUpdateDate(new Date());
		workInfo.setUpdateName(username);
		
		int reInt = workInfoService.updateWorkInfo(workInfo);
		if(reInt == 1){
			res.setErrmsg("修改成功");
			res.setSuccess(true);
			return res.toJson();
		} else {
			res.setErrmsg("修改失败");
			res.setSuccess(false);
			return res.toJson();
		}
	}
	
	@RequestMapping(value = "/applyInfoList")
	public void applyInfoList(Model model, HttpServletRequest request,
			Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		PageInfo<ApplyInfo> pageInfo = null;
		// 分页
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		pageInfo = applyInfoService.queryApplyInfoPage(paramMap, pageNo, pageSize);
		
		model.addAttribute("applyInfoList", pageInfo.getList());
		model.addAttribute("pageNo", pageInfo.getPageNum());
		model.addAttribute("pageSize", pageInfo.getPageSize());
		model.addAttribute("total", pageInfo.getTotal());
		model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
		model.addAttribute("pages", pageInfo.getPages());
		model.addAttribute("prePage", pageInfo.getPrePage());
		model.addAttribute("nextPage", pageInfo.getNextPage());
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.APPLY_LIST.getCode());
//		model.addAttribute("stateList", ApplyInfoState.values());
	}
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/toParttimeDetailManage")
	public Object toParttimeDetailManage(Model model, HttpServletRequest request, String workId, Integer pageNo, Integer pageSize) {
		
		WorkInfo workInfo = workInfoService.queryWorkInfo(workId);
		
		if(null == workInfo){
			return error("未找到详细信息数据");
		}
		
		model.addAttribute("workInfo", workInfo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		
		Map<String, Object> sexRequireMap = WorkInfoSexRequire.getAllList();
		Map<String, Object> mealMap = WorkInfoMeal.getAllList();
		Map<String, Object> interviewMap = WorkInfoInterview.getAllList();
		Map<String, Object> jobTypeMap = WorkInfoJobType.getAllList();
		Map<String, Object> moneyTypeMap = WorkInfoMoneyType.getAllList();
		Map<String, Object> cMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.CITY_MAP);
		Map<String, Object> aMapAll = (Map<String, Object>) MemcacheManager.getInstance().get(Constant.AREA_MAP);
		model.addAttribute("aMapAll", aMapAll.get(workInfo.getCity()));
		
		model.addAttribute("sexRequireMap", sexRequireMap);
		model.addAttribute("mealMap", mealMap);
		model.addAttribute("interviewMap", interviewMap);
		model.addAttribute("jobTypeMap", jobTypeMap);
		model.addAttribute("moneyTypeMap", moneyTypeMap);
		
		model.addAttribute("cMapAll", cMapAll);
		
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.PUB_JOB.getCode());
		
		return "sys/workInfoDetail";
	}
	
	/**
	 * 后台管理修改企业发布招聘信息
	 * 
	 * @param request
	 * @param newsComment
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/updateJobInfo")
	@ResponseBody
	public Object releaseJobInfo(ModelMap model, HttpServletRequest request, WorkInfo workInfo) throws NoLoginException {
		String username = (String) request.getSession().getAttribute("USER_NAME");
		if(Stringer.isNullOrEmpty(username)){
			return error("请重新登录");
		}
		String check = checkReleaseJobInfo(workInfo);
		if(null != check){
			return error("参数验证失败：" + check);
		}	
		try {
			workInfo.setUpdateDate(new Date());
			workInfo.setUpdateName(username);
			workInfoService.updateWorkInfo(workInfo);
		} catch (Exception e) {
			logs.info(e.getMessage());
		}
		return success("修改成功");
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
//		if(!verifyParam(workInfo.getWorkStartDate())){
//			return "工作起始日不能为空";
//		}
//		if(!verifyParam(workInfo.getWorkEndDate())){
//			return "工作结束日不能为空";
//		}
//		if(DateConverter.getMillisDiff(workInfo.getWorkEndDate(), workInfo.getWorkStartDate()) < 0){
//			return "工作结束日应大于工作起始日";
//		}
//		if(!verifyParam(workInfo.getWorkTime())){
//			return "上班时间不能为空";
//		}
		if(!verifyParam(workInfo.getPeopleNum())){
			return "招聘人数不能为空";
		}
//		if(!verifyParam(workInfo.getBalanceType())){
//			return "结算方式不能为空";
//		}
//		if(!verifyParam(workInfo.getMoney())){
//			return "金额不能为空";
//		}
//		if(!verifyParam(workInfo.getMoneyType())){
//			return "金额单位不能为空";
//		}
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
//		String moneyType = workInfo.getMoneyType();	// 薪资单位
//		String workHour = workInfo.getWorkHour();	// 工作时长
//		BigDecimal money = workInfo.getMoney();		// 薪资待遇
//		
//		if(WorkInfoMoneyType.CODE00.getCode().equals(moneyType)){
//			// 小时
//			if(null != workHour && !"".equals(workHour)){
//				BigDecimal workHourB = new BigDecimal(workHour);
//				if(BigDecimalUtil.BigDecimalLessThanAndEqual(workHourB, new BigDecimal(1)) 
//						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(25)))
//				{
//					return "时薪不能低于25元/时";
//				}
//				if(BigDecimalUtil.BigDecimalGreaterThan(workHourB, new BigDecimal(1)) 
//						&& BigDecimalUtil.BigDecimalLessThan(workHourB, new BigDecimal(3))
//						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(20)))
//				{
//					return "时薪不能低于20元/时";
//				}
//				if(BigDecimalUtil.BigDecimalGreaterThanAndEqual(workHourB, new BigDecimal(3))
//						&& BigDecimalUtil.BigDecimalLessThan(workHourB, new BigDecimal(5))
//						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(20)))
//				{
//					return "时薪不能低于15元/时";
//				}
//				if(BigDecimalUtil.BigDecimalGreaterThanAndEqual(workHourB, new BigDecimal(5)) 
//						&& BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(15)))
//				{
//					return "时薪不能低于10元/时";
//				}
//			} else {
//				return "请输入工作时长";
//			}
//		} else {
//			// 天
//			if(BigDecimalUtil.BigDecimalLessThan(money, new BigDecimal(70))){
//				return "薪资待遇不能低于70元";
//			}
//		}
		
		return null;
	}
	
}

/*select u.realname,w.WORK_TITLE,w.WORK_DETAIL applyinfo a,userinfo u,workinfo w
on a.P_ID=u.P_ID and u.USER_ID=w.USER_ID*/
