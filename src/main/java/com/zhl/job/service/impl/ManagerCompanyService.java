package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.gson.Gson;
import com.zhl.job.dao.IAccountflowDao;
import com.zhl.job.dao.IBanknameListDao;
import com.zhl.job.dao.ICardDao;
import com.zhl.job.dao.ICompanyInfoDao;
import com.zhl.job.dao.IUserLogInfoDao;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.BanknameList;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.CompanyInfo;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.WorkInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.IsDel;
import com.zhl.job.pojo.enums.UserState;
import com.zhl.job.pojo.enums.WorkInfoState;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.IUserInfoService;
import com.zhl.job.service.IUserLogInfoService;
import com.zhl.job.service.IWorkInfoService;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.MD5;
import com.zhl.job.util.SingleResp;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.http.HttpClientHelper;

@Service
@Transactional
public class ManagerCompanyService {
	private Logger logs = LoggerFactory.getLogger(ManagerCompanyService.class);
	@Resource(name = "workInfoService")
	private IWorkInfoService workInfoService;
	@Resource
	private ICompanyInfoDao companyInfoDao;
	@Resource
	private IAccountflowDao accountflowDao;
	@Resource
	private ICardDao cardDao;
	@Resource
	private IUserLogInfoService userLogInfoService;
	@Resource
	private IAccountflowService accountflowService ;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	private IBanknameListDao banknameListDao;
	@Resource
	private IUserLogInfoDao userLogInfoDao;
	
	public Object parttimCheck(String parttimeId,String flag,String username){
		ResponseEntity res=new ResponseEntity();
		WorkInfo workInfo = workInfoService.queryById(parttimeId);
		if(workInfo == null){
			res.setErrmsg("该任务不存在");
			res.setSuccess(true);
			return res.toJson();
		}
		WorkInfo update = new WorkInfo();
		update.setId(workInfo.getId());
		if("true".equals(flag)){
			update.setState(WorkInfoState.CODE01.getCode());
		}else{
			update.setState(WorkInfoState.CODE04.getCode());
		}
		update.setUpdateDate(new Date());
		update.setUpdateName(username);
		
		int i = workInfoService.updateWorkInfoById(update);
		return Stringer.commonOperation(i,"审核work，修改",res);
	}
	
	public Object companyContractCheck(HttpServletRequest request, String cid,String flag,String username){
		ResponseEntity res=new ResponseEntity();
		CompanyInfo pinfo = new CompanyInfo();
		pinfo.setCid(cid);
		CompanyInfo compayInfo = companyInfoDao.getCompanyInfoByCid(pinfo);
		if(compayInfo == null){
			res.setErrmsg("该企业不存在");
			res.setSuccess(true);
			return res.toJson();
		}
		CompanyInfo update = new CompanyInfo();
		update.setCid(cid);
		if("true".equals(flag)){
			update.setState(State.AUDIT_SUCC.getCode());
			compayInfo.setState(State.AUDIT_SUCC.getCode());
		}else{
			update.setState(State.AUDIT_FAIL.getCode());
			compayInfo.setState(State.AUDIT_FAIL.getCode());
		}
		update.setUpdatedate(new Date());
		update.setUpdateUserId(username);
		
		int i = companyInfoDao.updateCompanyByCid(update);
		if(i==1){
			request.getSession().setAttribute(Constant.LOGIN_COMPANYINFO_ENTITY, compayInfo);
		}
		return Stringer.commonOperation(i,"审核company，修改",res);
	}
	
	public Object cashOrderCheck(String accountId,String flag){
		ResponseEntity res=new ResponseEntity();
		Accountflow flow = accountflowDao.selectByPrimaryKey(accountId);
		if(flow == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该任务不存在",res);
			accountflowService.cashFail(null,flow,res);
			res.setErrmsg("该任务不存在");
			res.setSuccess(false);
			return res.toJson();
		}
		UserLogInfo loginUser = userLogInfoService.selectByPk(flow.getUserId());
		if(loginUser == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该卡用户不存在",res);
			accountflowService.cashFail(loginUser,flow,res);
			res.setErrmsg("该卡用户不存在");
			res.setSuccess(false);
			return res.toJson();
		}
		
		Card pcard = new Card();
		pcard.setUserId(flow.getUserId());
		pcard.setCardNo(flow.getOppoNo());
		pcard.setIsdel(IsDel.CODE00.getCode());
		Card card = cardDao.getCardByUserIdAndCardNo(pcard);
		if(card == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该卡未绑定",res);
			accountflowService.cashFail(loginUser,flow,res);
			res.setErrmsg("该卡未绑定");
			res.setSuccess(false);
			return res.toJson();
		}
		
		BanknameList bank = banknameListDao.selectByPrimaryKey(card.getBank());
		if(bank == null){
			Stringer.setFailState(State.FAIL_99.getCode(),"该银行卡不支持提现",res);
			accountflowService.cashFail(loginUser,flow,res);
			res.setErrmsg("该银行卡不支持提现");
			res.setSuccess(false);
			return res.toJson();
		}
		
		if("true".equals(flag)){
			//调提现接口
			//*******************######调用接口########***************************************
			double amount = flow.getAmount().doubleValue();
			invokeInterfaceCash(amount,card,res,flow,bank);
			String interfaceReturn = res.getErrmsg();
			//*******************#######调用接口#######***************************************
			if(! res.isSuccess()){
				//充值流水update
				logs.debug("2.1 .调用接口确认支付【不成功】更新操作 start ..");
				//不成功分为 1.正在支付ing  2.失败
					if(res.getErrcode().equals(State.DOING.getCode())){
						accountflowService.cashing(amount,card,loginUser,flow,res);
						res.setSuccess(true);
						res.setErrmsg("处理中，请稍后查看结果");
					}else{
						accountflowService.cashFail(loginUser,flow,res);
						res.setSuccess(false);
						res.setErrmsg(interfaceReturn==null?"提现失败，接口无响应信息":interfaceReturn);
					}
					return res.toJson();
			}
			
			//3.  充值成功
			logs.debug("3.调用接口确认提现【成功】 更新操作 start ..");
			logs.debug("3.1.调用接口确认提现流水update start ..");//流水update   
			accountflowService.cashSucc(amount,card,loginUser,flow,res);//成功会修改  accountflowNew 的state summary summarycode total useAmount
			return res.toJson();
		}else{
			flow.setState(State.AUDIT_FAIL.getCode());
			int i = accountflowDao.updateByPrimaryKeySelective(flow);
			Stringer.commonOperation(i, "提现审核拒绝更新accountflow", res);
			if(!res.isSuccess()){
				return res.toJson();
			}
			
			doAddAndSub4user(flow,loginUser,res);
			Stringer.commonOperation(i, "提现审核拒绝更新用户余额", res);
			return res.toJson();
		}
	}
	private int doAddAndSub4user(Accountflow flow, UserLogInfo loginUser, ResponseEntity res) {
		logs.debug("###>>> 提现审核拒绝更新用户余额 start...");
		//处理用户余额
		UserLogInfo userLogInfoNew = new UserLogInfo();
		//增加可用
		BigDecimal add = BigDecimalUtil.add((flow.getAmount()), loginUser.getUseAmount());
		//减少冻结
		double sub = BigDecimalUtil.sub(loginUser.getNoUseAmount().doubleValue(), flow.getAmount().doubleValue());
		
		userLogInfoNew.setId(loginUser.getId());
		userLogInfoNew.setUseAmount(add);
		userLogInfoNew.setNoUseAmount(new BigDecimal(sub));
		userLogInfoNew.setUpdateDate(new Date());
		int i = userLogInfoDao.updateByPrimaryKeySelective(userLogInfoNew);
		Stringer.commonOperation(i, "提现审核拒绝-更新用户余额", res);
		logs.debug("###>>> 提现审核拒绝更新用户余额 end  i ..."+i);
		return i;
	}

	private void invokeInterfaceCash(double amount, Card card, ResponseEntity res,Accountflow accountflow,BanknameList bank) {
		UserInfo userInfo = userInfoService.selectByUserId(card.getUserId());
		String merchId = Constant.CASH_MERID;
		String banlance = String.format("%.2f", amount);
		String accNo = card.getCardNo();
		String tranNo = accountflow.getTransFlowNo();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tranNo",tranNo);
		map.put("merchId",merchId);
		//11 爱农  12 联动
		map.put("channelId","11");
		map.put("bankName",card.getBankname());
		map.put("accNo",accNo);
		map.put("accName",card.getCardName());
		map.put("accType","00");
		map.put("bankProvince",card.getProvince());
		map.put("bankCity",card.getCity());
		map.put("banlance",amount);
		map.put("currency","CNY");
		map.put("certType","01");
		map.put("certNo",userInfo.getIdNumber());
		map.put("mobile",userInfo.getMobile());
		map.put("bankCode",bank.getBankcode());
		map.put("remark","学生："+userInfo.getMobile()+"提现");
		String key = merchId+banlance+accNo+tranNo;
		String sign = MD5.encrypt(key,"6m0gqnng1vv0wfes");
		map.put("sign", sign);
		String url = Constant.CASH_URL;
		String paramStr = JsonUtil.toJson(map).toString();
		logs.info(url+" 《--上送的url "+"上送的参数：\r\n"+paramStr);
		String rspStr =HttpClientHelper.doHttpClient(url, "POST", "utf-8",paramStr , "60000", "application/json;charset=UTF-8", "");
		if(!Stringer.isNullOrEmpty(rspStr)){
			logs.info("\r\n提现："+tranNo+",返回报文：\r\n"+rspStr);
			Gson g = new Gson();
			SingleResp single = g.fromJson(rspStr, SingleResp.class);
			if("1".equals(single.getCode())){
				Stringer.setSuccessState(single.getCode(),single.getMessage(),res);
			}else if("0".equals(single.getCode())){
				Stringer.setDoingState(single.getCode(),single.getMessage(), res);
			}else{
				Stringer.setFailState(single.getCode(),single.getMessage(),res);
			}
		}else{
//			res.setSuccess(false);
//			res.setErrmsg("提现接口返回空，请稍等或联系管理员");
//			res.setErrcode("9999");
			Stringer.setFailState(State.ERROR_INTERFACE_05.getCode(),"提现接口返回空，请稍等或联系管理员",res);
		}
	}
	
	public Object enDisableUserLogInfo(String id,String flag,String username){
		ResponseEntity res=new ResponseEntity();
		UserLogInfo userLogInfo = userLogInfoService.selectByPk(id);
		if(userLogInfo == null){
			res.setErrmsg("该用户不存在");
			res.setSuccess(true);
			return res.toJson();
		}
		if("true".equals(flag)){//启用
			userLogInfo.setUserState(UserState.STATE_ENABLE.getCode());
		}else{//禁用
			userLogInfo.setUserState(UserState.STATE_DISABLE.getCode());
		}
		userLogInfo.setUpdateDate(new Date());
		userLogInfo.setUpdateUserId(username);
		
		int i = userLogInfoService.updateByPrimaryKeySelective(userLogInfo);
		return Stringer.commonOperation(i,"用户禁用启用，修改",res);
	}
}
