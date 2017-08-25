package com.zhl.job.controller.notify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.zhl.job.dao.ICardDao;
import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.MD5;
import com.zhl.job.util.SingleResp;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.http.HttpClientHelper;

/**
 * 定时查询提现状态
 * @author wxw
 *
 */
@Controller
@RequestMapping(value = "/queryPayState")
public class QueryPayStateController {

	private Logger logs = LoggerFactory.getLogger(QueryPayStateController.class);
	
	@Autowired
	private IAccountflowService accountflowService;
//	@Autowired
//	private IAccountflowDao accountflowDao;
	@Autowired
	private ICardDao cardDao;
	@Autowired
	private IUserLogInfoService userLogInfoService;
	/**
	 * 1.查询所有处理中的提现交易
	 * 2.调上游查询接口
	 * 3.修改交易状态
	 * @param model
	 * @param request
	 * @param body
	 * @param response
	 */
	@RequestMapping(value="/query",method = RequestMethod.POST)@NoAuth
	public void query(Model model, HttpServletRequest request,@RequestBody String body,HttpServletResponse response){
		
		Map<String, String> map = new HashMap<String, String>();
		List<Accountflow> list = accountflowService.queryAccountflowHanding(map);
		for(Accountflow accountflow:list){
			handle(accountflow);
		}
		
	}
	private void handle(Accountflow accountflow){
		ResponseEntity res=new ResponseEntity();
		UserLogInfo loginUser = userLogInfoService.selectByPk(accountflow.getUserId());
		if(loginUser == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该卡用户不存在",res);
			accountflowService.cashFail(loginUser,accountflow,res);
		}
		
		Card pcard = new Card();
		pcard.setUserId(accountflow.getUserId());
		pcard.setCardNo(accountflow.getOppoNo());
		pcard.setIsdel(IsDel.CODE00.getCode());
		Card card = cardDao.getCardByUserIdAndCardNo(pcard);
		if(card == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该卡未绑定",res);
			accountflowService.cashFail(loginUser,accountflow,res);
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		String merchId = Constant.CASH_MERID;
		String tranNo = accountflow.getTransFlowNo();
		map.put("tranNo",tranNo);
		map.put("merchId",merchId);
		//11 爱农  12 联动
		map.put("channelId","11");
		String key = merchId+tranNo;
		String sign = MD5.encrypt(key,"6m0gqnng1vv0wfes");
		map.put("sign", sign);
		String url = Constant.CASH_QUERY_URL;
		String paramStr = JsonUtil.toJson(map).toString();
		logs.info(url+" 《--上送的url "+"上送的参数：\r\n"+paramStr);
		String rspStr =HttpClientHelper.doHttpClient(url, "POST", "utf-8",paramStr , "60000", "application/json;charset=UTF-8", "");
		if(!Stringer.isNullOrEmpty(rspStr)){
			Gson g = new Gson();
			SingleResp single = g.fromJson(rspStr, SingleResp.class);
			accountflow.setSummaryCode(single.getCode());
			accountflow.setSummary(single.getMessage());
			if("1".equals(single.getCode())){
				Stringer.setSuccessState(single.getCode(),single.getMessage(),res);
				accountflowService.cashSucc(accountflow.getAmount().doubleValue(),card,loginUser,accountflow,res);
			}else if("0".equals(single.getCode())){
				
			}else{
				Stringer.setFailState(single.getCode(),single.getMessage(),res);
				accountflowService.cashFail(loginUser,accountflow,res);
			}
			
		}
	}
}
