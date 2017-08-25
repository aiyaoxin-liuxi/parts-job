package com.zhl.job.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.controller.common.BaseController;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.SettlementInfo;
import com.zhl.job.pojo.SettlementInfoLog;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.enums.DivActive;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.SettlementInfoLogState;
import com.zhl.job.pojo.enums.SettlementInfoState;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.pojo.enums.trans.Type;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.ISettlementInfoLogService;
import com.zhl.job.service.ISettlementInfoService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.service.impl.AccountService;
import com.zhl.job.service.impl.CardService;
import com.zhl.job.service.impl.CommonService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 * 账户controller
  * @ClassName: AccountController
  * @author zhaotisheng	
  * @date 2017年3月16日 下午3:54:59
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController  extends BaseController{
	
	private Logger logs = LoggerFactory.getLogger(AccountController.class);
	
	private static final String pageReq="pageReq";
	@Resource
	private IUserLogInfoService userLogInfoService;
	
	@Resource
	private	CardService cardService;
	
	@Resource
	private	AccountService accountService;
	
//	@Autowired
//	private CompanyInfoService companyInfoService;
	
	@Autowired
	private CommonService commonService;
	
	@Resource
	private ISettlementInfoService settlementService;
	
	@Resource
	private ISettlementInfoLogService settlementInfoLogService;

	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	
	@Autowired
	IAccountflowService accountflowService;
	/**
	 * @throws NoLoginException 
	 * 
	  * main
	  *
	  * @Title: main
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/main")
	public void main(Model model, HttpServletRequest request,Integer pageNo,Integer pageSize) throws NoLoginException {
		checkLoginStatus(request);
		logs.debug("##>>>account--main Request OK!");
		setUserAttr(model,request);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.ACC_INF.getCode());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?Constant.pageSize:pageSize;
		Object loginCompanyinfoId = super.getloginCompanyinfoId(request);
		PageInfo<SettlementInfo> pageInfo=new PageInfo<SettlementInfo>();
		if(!Stringer.isNullOrEmpty(loginCompanyinfoId )){
			logs.debug("##>>>cid:"+loginCompanyinfoId.toString());
			paramMap.put("cid",loginCompanyinfoId.toString());
			pageInfo=settlementService.queryListByCompayId(paramMap,  pageNo, pageSize);
		}
		setworknameAndOtherAttr(pageInfo);
		Stringer.setPageInfo(model,pageInfo);
		commonService.setCompanyInfo(request,model);//补全企业信息
		model.addAttribute(pageReq, "main");
	}
	
	/**
	 * 
	  * rechargeList充值记录
	  *<br>(公司的只有充值记录)
	  * @Title: rechargeList
	  * @param @param model
	  * @param @param cid
	  * @param @param request
	  * @param @param pageNo
	  * @param @param pageSize
	  * @param @param workDay
	  * @param @throws NoLoginException
	  * @param @throws ParseException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/rechargeList")
	public void rechargeList(Model model,String cid, HttpServletRequest request,Integer pageNo,Integer pageSize,String workDay) throws NoLoginException, ParseException {
		checkLoginStatus(request);
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?Constant.pageSize:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Accountflow> list=accountflowService.getListByUserId(this.getLoginUserId(request));
		PageInfo<Accountflow> pageInfo = new PageInfo<Accountflow>(list);
		setAttr4recharge(pageInfo);//typ
		Stringer.setPageInfo(model,pageInfo);//(model,pageInfo);
		model.addAttribute(pageReq, "rechargeList");
	}
	

	private void setAttr4recharge(PageInfo<Accountflow> pageInfo) {
		if(Stringer.isNullOrEmpty(pageInfo)){
			return ;
		}
		List<Accountflow> list = pageInfo.getList();
		if(Stringer.isNullOrEmpty(list)){
			return ;
		}
		List<Accountflow> newlist=new ArrayList<Accountflow>();
		for (Accountflow accountflow :list) {
			String type = accountflow.getType();
			String state = accountflow.getState();
			try {
				if(Stringer.isNullOrEmpty(type) || Stringer.isNullOrEmpty(state)){
					logs.error(" 这条记录的type 或者 state为空，accountflow.getAccountId()："+accountflow.getAccountId());
					continue;
				}
				//type
				accountflow.setType(Type.parseOf(type).getName());
				//state
				accountflow.setState(State.parseOf(state).getName());
				newlist.add(accountflow);
			} catch (Exception e) {
				logs.error(" 这条记录的type 不在枚举之内，accountflow.getAccountId()："+accountflow.getAccountId());
				continue;
			}
		}
		pageInfo.setList(newlist);
	}

	/**
	 * 
	  * settDetail结算详细
	  *
	  * @Title: settDetail
	  * @param @param workDay
	  * @param @throws NoLoginException
	  * @param @throws ParseException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/settDetail")
	public void settDetail(Model model,String cid, HttpServletRequest request,Integer pageNo,Integer pageSize,String workDay) throws NoLoginException, ParseException {
		checkLoginStatus(request);
		logs.debug("##>>>account--settDetail Request OK! :"+cid);
		if(Stringer.isNullOrEmpty(workDay) || Stringer.isNullOrEmpty(cid)){
			model.addAttribute("errMsg", "参数异常,未找到相关数据");
			return;
		}
		//iSettlementInfoLogService
		pageNo=pageNo==null?1:pageNo;
		pageSize=pageSize==null?Constant.pageSize:pageSize;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("settlementId", cid);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("workDay", df.parse(workDay));
		PageInfo<SettlementInfoLog> pageInfo =settlementInfoLogService.queryListBySettleIdAndWorkDay(paramMap,  pageNo, pageSize);
		setAttr(pageInfo);
		Stringer.setPageInfo(model,pageInfo);//(model,pageInfo);
		model.addAttribute("cid", cid);
		model.addAttribute("workDay", workDay);
	}

	private void setAttr(PageInfo<SettlementInfoLog> pageInfo) {
		if(Stringer.isNullOrEmpty(pageInfo)){
			return ;
		}
		List<SettlementInfoLog> list = pageInfo.getList();
		if(Stringer.isNullOrEmpty(list)){
			return ;
		}
		List<SettlementInfoLog> newlist=new ArrayList<SettlementInfoLog>();
		for(SettlementInfoLog each:list){
			String state = each.getState();
			if(!Stringer.isNullOrEmpty(state)){
				String text = SettlementInfoLogState.getText(state);
				if(!Stringer.isNullOrEmpty(text)){
					each.setState(text);
				}
			}
			newlist.add(each);
		}
		pageInfo.setList(newlist);
	}

	private void setworknameAndOtherAttr(PageInfo<SettlementInfo> pageInfo) {
		if(Stringer.isNullOrEmpty(pageInfo)){
			return ;
		}
		List<SettlementInfo> list = pageInfo.getList();
		if(Stringer.isNullOrEmpty(list)){
			return ;
		}
		List<SettlementInfo> newlist=new ArrayList<SettlementInfo>();
		for(SettlementInfo each:list){
			String workId = each.getWorkId();
			WorkInfo workInfo = workInfoService.queryById(workId);
			each.setWorkInfo(workInfo);
			String state = each.getState();
			if(!Stringer.isNullOrEmpty(state)){
				String text = SettlementInfoState.getText(state);
				if(!Stringer.isNullOrEmpty(text)){
					each.setState(text);
				}
			}
			newlist.add(each);
		}
		pageInfo.setList(newlist);
	}

	/**
	 * @throws NoLoginException 
	 * 
	  * recharge充值
	  *
	  * @Title: recharge
	  * @param @param model
	  * @param @param request    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/recharge")
	public void recharge(Model model, HttpServletRequest request) throws NoLoginException {
		checkLoginStatus(request);
		logs.debug("##>>>recharge--main Request OK!");
		setUserAttr(model,request);
		
		Card card = new Card();
		card.setUserId(super.getLoginUserId(request));
		card.setIsdel(IsDel.CODE00.getCode());
		List<Card> cards=cardService.getCardListByUserId(card);
		logs.debug("##>>>获取银行卡条数："+cards.size());
		model.addAttribute("cards",cards);
		model.addAttribute(Constant.DIV_ACTIVE, DivActive.ACC_INF.getCode());
		
	}
	/**
	 * 
	  * rechargePost充值
	  *
	  * @Title: rechargePost
	  * @param @param model
	  * @param @param request
	  * @param @return    设定文件
	  * @return Object    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/recharge",method={RequestMethod.POST})
	public  @ResponseBody Object rechargePost(Model model, HttpServletRequest request,double amount,String bank,String sendSmsSucc,String accountflowAccountId,String smscode) {
		logs.debug("##>>>rechargePost Request OK!");
		if(Stringer.isNullOrEmpty(bank)){
			return error("请选择银行卡");
		}
		Card card=cardService.getCardById(bank);
		if(Stringer.isNullOrEmpty(card)){
			return error("此银行卡已经不可用");
		}
		UserLogInfo userLogInfo=new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo loginUser = userLogInfoService.getByMobile(userLogInfo);
		
		if(Stringer.isNullOrEmpty(sendSmsSucc)){
			logs.debug("##>>>发送短信验证码");
			return accountService.sendSmsCode4tran(amount,card,loginUser);
		}
		if(!sendSmsSucc.equals("sendSmsSucc")){
			return error("请确认短信发送成功");
		}
		if(Stringer.isNullOrEmpty(smscode)){
			return error("请填写短信验证码");
		}
		logs.debug("##>>>交易确认-充值，短信验证码："+smscode);
		return accountService.recharge(amount,card,loginUser,accountflowAccountId,smscode);
		
	}
	
	
	
	private void setUserAttr(Model model, HttpServletRequest request) {
		UserLogInfo userLogInfo=new UserLogInfo();
		userLogInfo.setMobile(super.getLoginUserMobile(request));
		UserLogInfo user = userLogInfoService.getByMobile(userLogInfo);
		model.addAttribute("user", user);		
	}

}
