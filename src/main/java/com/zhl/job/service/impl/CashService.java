package com.zhl.job.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserInfo;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.service.IUserInfoService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.MD5;
import com.zhl.job.util.SingleResp;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.http.HttpClientHelper;
/**
 * 提现处理
 * @author wxw
 *
 */
@Controller
@Transactional
public class CashService{

	private Logger logs = LoggerFactory.getLogger(CashService.class);
	
	@Autowired
	IAccountflowService accountflowService;
	
	@Autowired
	IUserInfoService userInfoService;
	/**
	 * 
	 * @param amount 提现金额
	 * @param card
	 * @param paypwd  支付密码
	 * @param loginUser
	 * @return
	 */
	public Object cash(double amount,Card card,UserLogInfo loginUserDB){
		ResponseEntity res=new ResponseEntity();
		//1.判断余额
		if((Stringer.isNullOrEmpty(loginUserDB.getUseAmount()))|| (amount > loginUserDB.getUseAmount().doubleValue())){
			res.setSuccess(false);
			res.setErrmsg("可用余额不足");
			return res.toJson();
		}
		
		//3.insert记录
//	    Accountflow accountflow = accountflowService.insertForCash(amount,card,loginUser,res);
		accountflowService.insert4WaitAudit(amount, card, loginUserDB, res);
		if(!res.isSuccess()){
			res.setSuccess(false);
			res.setErrmsg("插入账户流水表失败");
			return res.toJson();
		}
		//这里不直接调用提现 要有审核
		else{
			logs.info("###>>>用户提现等待审核。。。。");
			res.setSuccess(true);
			res.setErrmsg("用户提现等待审核");
			return res.toJson();
		}
		
		//4.调提现接口
		//*******************######调用接口########***************************************
//		invokeInterfaceCash(amount,card,res,accountflow);
//		String interfaceReturn = res.getErrmsg();
//		//*******************#######调用接口#######***************************************
//		if(! res.isSuccess()){
//			//充值流水update
//			logs.debug("2.1 .调用接口确认支付【不成功】更新操作 start ..");
//			//不成功分为 1.正在支付ing  2.失败
//				if(res.getErrcode().equals(State.DOING.getCode())){
//					accountflowService.cashing(amount,card,loginUser,accountflow,res);
//					res.setSuccess(true);
//					res.setErrmsg("处理中，请稍后查看结果");
//				}else{
//					accountflowService.rechargeFail(loginUser,accountflow,res);
//					res.setSuccess(false);
//					res.setErrmsg(interfaceReturn==null?"提现失败，接口无响应信息":interfaceReturn);
//				}
//				return res.toJson();
//		}
//		
//		//3.  充值成功
//		logs.debug("3.调用接口确认提现【成功】 更新操作 start ..");
//		logs.debug("3.1.调用接口确认提现流水update start ..");//流水update   
//		accountflowService.cashSucc(amount,card,loginUser,accountflow,res);//成功会修改  accountflowNew 的state summary summarycode total useAmount
//		return res.toJson();
	}
	
	//TODO...
	/**
	 * 
	  * invokeInterfaceCash(这里用一句话描述这个方法的作用)
	  * @Title: invokeInterfaceCash
	  * @Description: TODO
	  * @param @param amount
	  * @param @param card
	  * @param @param res
	  * @param @param accountflow    设定文件
	  * @return void    返回类型
	  * //注意打印  INfO  类型的日志
		Stringer.setSuccessState("testSucc_code","testFail_msg（模拟充值成功）",res);//模拟成功
 		Stringer.setFailState("testFail_code","testFail_模拟失败",res);//模拟失败
		Stringer.setDoingState("test_Ding_code","test_Ding_正在支付",res);//模拟正在支付
	  * @throws
	 */
	private void invokeInterfaceCash(double amount, Card card, ResponseEntity res,Accountflow accountflow) {
		UserInfo userInfo = userInfoService.selectByUserId(card.getUserId());
		String merchId = Constant.CASH_MERID;
		String banlance = String.format("%.2f", amount);;
		String accNo = card.getCardNo();
		String tranNo = accountflow.getTransFlowNo();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tranNo",tranNo);
		map.put("merchId",merchId);
		//11 爱农  12 联动
		map.put("channelId","12");
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
		map.put("bankCode","01020000");//card.getBank()
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

	
}
