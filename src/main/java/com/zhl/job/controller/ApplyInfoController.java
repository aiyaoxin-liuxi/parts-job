package com.zhl.job.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.ApplyInfo;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.WorkInfoStatis;
import com.zhl.job.pojo.enums.ApplyInfoEmploy;
import com.zhl.job.pojo.enums.ApplyInfoState;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.UserType;
import com.zhl.job.pojo.enums.WorkInfoAllowChooseDate;
import com.zhl.job.pojo.enums.WorkInfoApplySwitch;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IApplyInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Constant;
import com.zhl.job.util.DateUtil;

/**
 * 职位申请审核表
  * @ClassName: ApplyInfoController
  * @Description: TODO
  * @author hys	
  * @date 2017年3月14日 下午4:42:23
 */
@Controller
@RequestMapping(value = "/apply")
public class ApplyInfoController extends BaseController{

	
	private Logger logs = LoggerFactory.getLogger(ApplyInfoController.class);

	@Resource(name = "applyInfoService")
	private IApplyInfoService applyInfoService;
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	/**
	 * @throws NoLoginException 
	 * 
	  * 职位申请
	  *
	  * @Title: main0
	  * @Description: TODO
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/apply")
	@ResponseBody
	public Object apply(ModelMap model, HttpServletRequest request, ApplyInfo applyInfo, String allowChooseDate) throws NoLoginException {
		logs.debug("##>>>apply Request OK!");
		
		// session 中获取
		Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
		Object entity = request.getSession().getAttribute(Constant.LOGIN_USER_ENTITY);
		if(null != attribute){
			if(null != entity){
				if(UserType.PERSONAL.getCode().equals(getLoginUserType(request))){
					
					UserInfo userInfo = (UserInfo) entity;
					if(null != userInfo.getRealname() && !"".equals(userInfo.getRealname()) && 
							null != userInfo.getIdNumber() && !"".equals(userInfo.getIdNumber()))
					{
						// 参数检查
						String check = null;
						try {
							check = checkApply(applyInfo, allowChooseDate);
							if(null != check){
								return error(check);
							}
							
							//数据合法验证
							String keySn = KeySn.getKey();
							// session 中获取
							String logUserId = getLoginUserId(request);
							String pid = (String) getLoginPersonId(request);
							
							// 查询是否存在此招聘信息
							WorkInfo workInfo = workInfoService.queryWorkInfo(applyInfo.getWorkId());
							if(null == workInfo){
								return error("未找到招聘信息");
							}
							if(WorkInfoApplySwitch.CODE01.getCode().equals(workInfo.getApplySwitch())){
								return error("已停止报名");
							}
							WorkInfoStatis workInfoStatis = workInfo.getWorkInfoStatis();
							if(workInfoStatis.getEmployNum() >= Integer.parseInt(workInfo.getPeopleNum())){
								return error("此招聘已招满");
							}
							
							// 查询是否申请过此日
							List<Date> list = applyInfo.getApplyWorkDays();
							for (Date date : list) {
								if(!DateUtil.ContrastBeforeDayAndNowDay(date)){
									return error(DateUtil.date2String(date) + "不能申请当天的工作");
								}
								if(applyInfoService.queryWorkInfoByDate(logUserId, date).size() > 0){
									return error(DateUtil.date2String(date) + "已申请过工作，不能在同一天重复申请");
								}
								if(applyInfoService.queryWorkInfoByDateAndWorkId(logUserId, applyInfo.getWorkId(), date).size() > 0){
									return error(DateUtil.date2String(date) + "已申请过此工作，企业未录用");
								}
								
							}
							if(list.size() == 2){
								if(list.get(0).equals(list.get(1))){
									list.remove(1);
									applyInfo.setApplyWorkDays(list);
								}
							}

							
							applyInfo.setUserId(logUserId);
							applyInfo.setPid(pid);
							/***save***/
							//用户日期选择集合
							applyInfoService.insertApplyInfo(keySn, applyInfo);
							
						} catch (Exception e) {
							logs.debug(e.getMessage());
							return error("系统异常");
						}
						return success("申请成功");
					} else {
						// 用户未认证
						return error("用户未认证,请完善用户信息");
					}
				} else {
					return error("不是个人用户");
				}
			} else {
				// 用户未认证
				return error("用户未认证,请完善用户信息");
			}
		} else {
			return error("login_err", "请重新登录");
		}
	}
	
	/**
	 * @throws NoLoginException 
	 * 
	  * 取消报名
	  *
	  * @Title: main0
	  * @Description: TODO
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/cleanEnroll")
	@ResponseBody
	public Object cleanEnroll(ModelMap model, HttpServletRequest request, String applyId) throws NoLoginException {
		
		// session 中获取
		Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID);
		if(null != attribute){
			if(UserType.PERSONAL.getCode().equals(getLoginUserType(request))){
				// session 中获取
				String logUserId = getLoginUserId(request);
				String pid = (String) getLoginPersonId(request);
				// 参数检查
				String check = null;
				try {
					check = checkcleanEnroll(applyId);
					if(check != null){
						return check;
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", applyId);
					map.put("userId", logUserId);
					map.put("pid", pid);
					ApplyInfo applyInfo = applyInfoService.queryByApplyId(map);
					if(null != applyInfo && !ApplyInfoEmploy.CODE01.getCode().equals(applyInfo.getEmploy())){
						if("00".equals(applyInfo.getEmploy()) || DateUtil.ContrastBeforeDayAndNowDay(applyInfo.getApplyWorkDate())){
							
							// 查询是否为不允许选择时间，如果是。需要取消全部
							WorkInfo workInfo = workInfoService.queryById(applyInfo.getWorkId());
							if(null != workInfo){
								
								if(WorkInfoAllowChooseDate.CODE00.getCode().equals(workInfo.getAllowChooseDate())){
									// 允许选择日期的招聘
									applyInfo.setEmploy(ApplyInfoEmploy.CODE03.getCode());
									applyInfo.setState(ApplyInfoState.CODE02.getCode());
									applyInfoService.updateApplyInfo(applyInfo);
								} else {
									// 不允许选择日期的招聘
									if(!DateUtil.ContrastBeforeDayAndNowDay(workInfo.getWorkStartDate()) 
											&& ApplyInfoEmploy.CODE01.getCode().equals(applyInfo.getEmploy()))
									{
										// 已经开始了，而且是被录用的，不能取消了
										return error("您已被录用，不能取消报名。");
									} else {
										// 取消此用户这个招聘的所有申请
//										map = new HashMap<String, Object>();
										map.put("workId", workInfo.getId());
										List<ApplyInfo> cleanAppList = applyInfoService.queryWorkInfoBycondition(map);
										for(ApplyInfo appInfo : cleanAppList){
											appInfo.setEmploy(ApplyInfoEmploy.CODE03.getCode());
											appInfo.setState(ApplyInfoState.CODE02.getCode());
										}
										applyInfoService.cleanApplyInfos(cleanAppList);
									}
								}
							} else {
								return error("找不到相关兼职");
							}
						} else {
							return error("不能取消工作当天的兼职");
						}
					} else {
						return error("不能取消已被录取的申请！");
					}
				} catch (Exception e) {
					logs.debug(e.getMessage());
					return error("系统异常");
				}
				return success("取消成功");
			} else {
				return error("不是个人用户");
			}
		} else {
			return error("login_err", "请重新登录");
		}
	}
	
	/**
	 * 
	  * 录用
	  *
	  * @Title: verifycode
	  * @Description: TODO
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/employment")
	public @ResponseBody Object employment(Model model, HttpServletRequest request, ApplyInfo applyInfo) {
		
		
		// 查询是否为不允许选择时间，如果是。需要取消全部
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", applyInfo.getId());
		List<ApplyInfo> applyInfoList = applyInfoService.queryWorkInfoBycondition(map);
		if(null == applyInfoList || applyInfoList.size() == 0){
			return error("找不到相关申请");
		}
		applyInfo = applyInfoList.get(0);
		WorkInfo workInfo = workInfoService.queryById(applyInfo.getWorkId());
		if(null == workInfo){
			return error("找不到相关兼职");
		}
		// 查询录用人数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("workId", workInfo.getId());
		paramMap.put("applyWorkDate", applyInfo.getApplyWorkDate());
		List<ApplyInfo> appList = applyInfoService.queryApplyByWorkAndDateNotPage(paramMap);
		if(null != appList && appList.size() != 0){
			BigDecimal b = new BigDecimal(appList.size());
			BigDecimal b2 = new BigDecimal(workInfo.getPeopleNum());
			if(BigDecimalUtil.BigDecimalGreaterThanAndEqual(b, b2)){
				return error("当天已录用满员");
			}
		}
		
		if(WorkInfoAllowChooseDate.CODE00.getCode().equals(workInfo.getAllowChooseDate())){
			// 允许选择日期的招聘
			map = new HashMap<String, Object>();
			map.put("id", applyInfo.getId());
			map.put("employ", ApplyInfoEmploy.CODE00.getCode());
			List<ApplyInfo> list = applyInfoService.queryWorkInfoBycondition(map);
			if(null == applyInfo && list.size() != 1){
				return error("未找到申请单信息");
			}
			applyInfo = list.get(0);
			applyInfo.setEmploy(ApplyInfoEmploy.CODE01.getCode());
			int reInt = applyInfoService.addEmployment(applyInfo);
			if(reInt == 1){
				return success("录用成功");
			} else {
				return error("录用失败");
			}
			
		} else {
			// 不允许选择日期的招聘
			if(!DateUtil.ContrastBeforeDayAndNowDay(workInfo.getWorkStartDate()) 
					&& ApplyInfoEmploy.CODE01.getCode().equals(applyInfo.getEmploy()))
			{
				// 已经开始了，从当天开始录用
				// 允许选择日期的招聘
				map = new HashMap<String, Object>();
				map.put("userId", applyInfo.getUserId());
				map.put("pid", applyInfo.getPid());
				map.put("workId", applyInfo.getWorkId());
				map.put("employ", ApplyInfoEmploy.CODE00.getCode());
				List<ApplyInfo> list = applyInfoService.queryWorkInfoBycondition(map);
				if(null == applyInfo && list.size() != 1){
					return error("未找到申请单信息");
				}
				for (ApplyInfo appInfo : list) {
					if(DateUtil.ContrastBeforeDayAndNowDay(appInfo.getApplyWorkDate())){
						appInfo.setEmploy(ApplyInfoEmploy.CODE01.getCode());
					}
				}
				int reInt = applyInfoService.addBatchEmployment(list);
				if(reInt == -1){
					return success("录用成功");
				} else {
					return error("录用失败");
				}
				
			} else {
				// 没开始，全部录用
				map = new HashMap<String, Object>();
				map.put("userId", applyInfo.getUserId());
				map.put("pid", applyInfo.getPid());
				map.put("workId", applyInfo.getWorkId());
				map.put("employ", ApplyInfoEmploy.CODE00.getCode());
				List<ApplyInfo> list = applyInfoService.queryWorkInfoBycondition(map);
				if(null == applyInfo && list.size() != 1){
					return error("未找到申请单信息");
				}
				for (ApplyInfo appInfo : list) {
					appInfo.setEmploy(ApplyInfoEmploy.CODE01.getCode());
				}
				int reInt = applyInfoService.addBatchEmployment(list);
				if(reInt == 1){
					return success("录用成功");
				} else {
					return error("录用失败");
				}
				
			}
		}
	}
	/**
	 * 
	 * 拒绝
	 *
	 * @Title: verifycode
	 * @Description: TODO
	 * @param @param model
	 * @param @param request    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/refuse")
	public @ResponseBody Object refuse(Model model, HttpServletRequest request, ApplyInfo applyInfo) {
		
		
		// 查询是否为不允许选择时间，如果是。需要取消全部
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", applyInfo.getId());
		List<ApplyInfo> applyInfoList = applyInfoService.queryWorkInfoBycondition(map);
		if(null == applyInfoList || applyInfoList.size() == 0){
			return error("找不到相关申请");
		}
		applyInfo = applyInfoList.get(0);
		WorkInfo workInfo = workInfoService.queryById(applyInfo.getWorkId());
		if(null == workInfo){
			return error("找不到相关兼职");
		}
		
		if(WorkInfoAllowChooseDate.CODE00.getCode().equals(workInfo.getAllowChooseDate())){
			// 允许选择日期的招聘
			applyInfo.setEmploy(ApplyInfoEmploy.CODE02.getCode());
			applyInfoService.updateApplyInfo(applyInfo);
			
		} else {
			// 不允许选择日期的招聘
			// 全部申请回绝
			map = new HashMap<String, Object>();
			map.put("userId", applyInfo.getUserId());
			map.put("pid", applyInfo.getPid());
			map.put("workId", applyInfo.getWorkId());
			map.put("employ", ApplyInfoEmploy.CODE00.getCode());
			List<ApplyInfo> list = applyInfoService.queryWorkInfoBycondition(map);
			if(null == applyInfo && list.size() != 1){
				return error("未找到申请单信息");
			}
			for (ApplyInfo appInfo : list) {
				appInfo.setEmploy(ApplyInfoEmploy.CODE02.getCode());
			}
			int reInt = applyInfoService.batchUpdateApplyInfos(list);
			if(reInt == -1){
				return success("拒绝成功");
			} else {
				return error("拒绝失败");
			}
		}
		return success("拒绝成功");
	}
	
//	/**
//	 * 
//	  * 职位审核
//	  *
//	  * @Title: verifycode
//	  * @Description: TODO
//	  * @param @param model
//	  * @param @param request    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	@RequestMapping(value = "/examine")
//	public @ResponseBody Object examine(Model model, HttpServletRequest request,String mobile) {
//		logs.debug("##>>>verifycode Request OK!");
//		if(!Stringer.isMobile(mobile)){
//			return ("请填写正确的手机号");
//		}
//		
//		return success("发送成功");
//	}
//
//	/**
//	 * 
//	  * 申请职位取消
//	  *
//	  * @Title: verifycode
//	  * @Description: TODO
//	  * @param @param model
//	  * @param @param request    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	@RequestMapping(value = "/applyCancel")
//	public @ResponseBody Object applyCancel(Model model, HttpServletRequest request,String mobile) {
//		logs.debug("##>>>verifycode Request OK!");
//		if(!Stringer.isMobile(mobile)){
//			return ("请填写正确的手机号");
//		}
//		
//		return success("发送成功");
//	}
//	/**
//	 * 
//	  * 审核职位取消
//	  *
//	  * @Title: verifycode
//	  * @Description: TODO
//	  * @param @param model
//	  * @param @param request    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	@RequestMapping(value = "/examineCancel")
//	public @ResponseBody Object examineCancel(Model model, HttpServletRequest request,String mobile) {
//		logs.debug("##>>>verifycode Request OK!");
//		if(!Stringer.isMobile(mobile)){
//			return ("请填写正确的手机号");
//		}
//		
//		return success("发送成功");
//	}
	
	
	// =================================以下跳转==============================
	
	/**
	 * 个人申请信息管理跳转
	 * @return
	 * @throws NoLoginException 
	 */
	@RequestMapping(value = "/myParttime")
	public Object toMyparttime(ModelMap model, HttpServletRequest request, Integer pageNo, Integer pageSize, String queryState) throws NoLoginException {
		
		checkLoginStatus(request);
		
		// 获取session
		String logUserId = getLoginUserId(request);
		String pid = (String) getLoginPersonId(request);
		
		String employ = null; 
		String state = null;
		if("01".equals(queryState)){// 已录取
			employ = employ == null ? ApplyInfoEmploy.CODE01.getCode() : employ;
			state = state == null ? ApplyInfoState.CODE00.getCode() : state;
		} else if("02".equals(queryState)){// 进行中
			employ = employ == null ? ApplyInfoEmploy.CODE01.getCode() : employ;
			state = state == null ? ApplyInfoState.CODE01.getCode() : state;
		} else if("03".equals(queryState)){
			// 已驳回的在已完成中显示，暂先差所有已完成的
			state = state == null ? ApplyInfoState.CODE02.getCode() : state;
		} else {
			// 已报名
			queryState = "00";
			employ = employ == null ? ApplyInfoEmploy.CODE00.getCode() : employ;
			state = state == null ? ApplyInfoState.CODE00.getCode() : state;
		}
		
		pageNo = pageNo == null ? 1 : pageNo;
		pageSize = pageSize == null ? 5 : pageSize;
		
		PageInfo<ApplyInfo> pageInfo = null;
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			// 分页
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", logUserId);
			paramMap.put("pid", pid);
			paramMap.put("employ", employ);
			paramMap.put("state", state);
			
			pageInfo = applyInfoService.queryApplyAndWorkPageByEmploy(paramMap, pageNo, pageSize);
			
			model.addAttribute("appList", pageInfo.getList());
			model.addAttribute("pageNo", pageInfo.getPageNum());
			model.addAttribute("pageSize", pageInfo.getPageSize());
			model.addAttribute("total", pageInfo.getTotal());
			model.addAttribute("navigatepageNums", pageInfo.getNavigatepageNums());
			model.addAttribute("pages", pageInfo.getPages());
			
			model.addAttribute("prePage", pageInfo.getPrePage());
			model.addAttribute("nextPage", pageInfo.getNextPage());
			
			model.addAttribute("queryState", queryState);
			
			model.addAttribute(Constant.DIV_ACTIVE, DivActive.MY_JOB.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			alertError(model, "系统异常");
		}
		
		return "personalInfo/my-parttime";
	}
	
	
	/**
	 * 申请时的验证
	 * @return
	 * @throws ParseException 
	 */
	private String checkApply(ApplyInfo applyInfo, String allowChooseDate) throws ParseException{
		if(null == applyInfo){
			return "未找到参数";
		}
		if(!verifyParam(applyInfo.getWorkId())){
			return "招聘信息id不能为空";
		}
		if(!verifyParam(allowChooseDate)){
			return "是否可选日期不能为空";
		}
		if("00".equals(allowChooseDate)){
			List<Date> list = applyInfo.getApplyWorkDays();
			if(null == list && list.size() == 0){
				return "申请工作日期不能为空";
			}
			for (Date date : list) {
				if(!DateUtil.ContrastBeforeDay(date)){
					return "申请时间已经过期";
				}
			}
		}
		
		
		return null;
		
	}
	
	/**
	 * 申请时的验证
	 * @return
	 * @throws ParseException 
	 */
	private String checkcleanEnroll(String workId) throws ParseException{
		if(null == workId || "".equals(workId)){
			return "id不能为空";
		}
		
		return null;
		
	}
	
	
}
