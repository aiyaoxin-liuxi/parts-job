package com.zhl.job.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.job.pay.IPay;
import com.zhl.job.pay.common.PayFactory;
import com.zhl.job.pay.common.PayFactoryEnum;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.pojo.enums.trans.TransDirection;
import com.zhl.job.pojo.enums.trans.Type;
import com.zhl.job.service.IAccountService;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;
/**
 * 
  * @ClassName: AccountService
  * @author zhaotisheng	
  * @date 2017年3月16日 下午5:07:17
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@Transactional
public class AccountService implements IAccountService{

	private Logger logs = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	IAccountflowService accountflowService;
	
	
	
	public Object recharge(double amount, Card card, UserLogInfo loginUser, String accountflowAccountId, String smscode) {
		ResponseEntity res=new ResponseEntity();
		if(Stringer.isNullOrEmpty(Constant.DEV_SWITCH)  || !Constant.DEV_SWITCH.equals("1")){//测试的时候可以用这个，不真正发短信，验证码看log
			logs.debug(">>>>>simulation模拟  支付成功：");
			res.setErrmsg("模拟发送成功");
			res.setSuccess(true);
			return res.toJson();
		}
		//1. 获取流水
		logs.debug("1. 获取流水流水 start ..");
		
		Accountflow accountflow = accountflowService.selectByPrimaryKey(accountflowAccountId);
		
		if(Stringer.isNullOrEmpty(accountflow)){
			res.setSuccess(false);
			res.setErrmsg("获取充值流水失败，请联系管理员");
			return res.toJson();
		}
		//2.调用接口充值
		logs.debug("2.调用接口确认支付 start ..");
		//*******************######调用接口########***************************************
		invokeInterfaceRecharge(amount,card,res,smscode,accountflow);
		String interfaceReturn=res.getErrmsg();
		//*******************#######调用接口#######***************************************
		if(! res.isSuccess()){
			//充值流水update
			logs.debug("2.1 .调用接口确认支付【不成功】更新操作 start ..");
			//不成功分为 1.正在支付ing  2.失败
				if(res.getErrcode().equals(State.DOING.getCode())){
					accountflowService.recharging(loginUser,accountflow,res);
					res.setSuccess(true);
					res.setErrmsg("支付中，请稍后查看结果");
				}else{
					if(res.getErrcode().equals(State.INVOKING_SMS_VERIFY_FAIL_34.getCode())){
						accountflowService.rechargeMyspecifyState(amount,card,loginUser,accountflow,res,State.INVOKING_SMS_VERIFY_FAIL_34.getCode());
					}else{
						accountflowService.rechargeFail(loginUser,accountflow,res);
					}
					res.setSuccess(false);
					res.setErrmsg(interfaceReturn==null?"充值失败，接口无响应信息":interfaceReturn);
				}
				return res.toJson();
			
		}
		
		//3.  充值成功
		logs.debug("3.调用接口确认支付【成功】 更新操作 start ..");
		logs.debug("3.1.调用接口确认支付 充值流水update start ..");//流水update   
		accountflowService.rechargeSucc(loginUser,accountflow,res);//成功会修改  accountflowNew 的state summary summarycode total useAmount
		return res.toJson();
	}
	
	
	/**
	 * 
	  * invokeInterfaceRecharge(这里用一句话描述这个方法的作用)
	  * @Title: invokeInterfaceRecharge
	  * @param @param amount
	  * @param @param card
	  * @param @param res
	  * @param @param smscode
	  * @param @param accountflow    设定文件
	  * @return void    返回类型
	  * //注意打印  INfO  类型的日志
		Stringer.setSuccessState("testSucc_code","testFail_msg（模拟充值成功）",res);//模拟成功
 		Stringer.setFailState("testFail_code","testFail_模拟失败",res);//模拟失败
		Stringer.setDoingState("test_Ding_code","test_Ding_正在支付",res);//模拟正在支付
	  * @throws
	 */
	private void invokeInterfaceRecharge(double amount, Card card, ResponseEntity res, String smscode, Accountflow accountflow) {
		String rechargeUrl = Constant.RECHARGE_URL;
		logs.debug("2. 充值接口url ："+rechargeUrl);
		
		Object[] objArr = new Object[]{res,smscode,accountflow};
		// ***************调用银行加密方法组装银行参数***********************
		logs.debug("###>>>调用银行加密方法组装银行参数【充值确认】 ");
		IPay instance = new PayFactory().getInstance(PayFactoryEnum.CASHIERPAY.getCode());
		ResponseEntity resRet = instance.payConfirm(objArr);
		if(resRet.isSuccess()){
			Stringer.setSuccessState(resRet.getErrcode(),resRet.getErrmsg(),res);//模拟成功
		}else{
			if(resRet.getErrcode().equals("100000")){//HANDING_100000("100000","交易处理中"),
				Stringer.setDoingState(resRet.getErrcode(), resRet.getErrmsg(), res);
			}
			//通道返回的短信验证码错误
			else if(resRet.getErrcode().equals("202015")){
				Stringer.setMySpecialState(State.INVOKING_SMS_VERIFY_FAIL_34.getCode(), resRet.getErrcode(), resRet.getErrmsg(), res);
			}
			
			else{
				Stringer.setFailState(resRet.getErrcode(),resRet.getErrmsg(),res);//模拟失败
			}
		}

	}


	/**
	 * 
	  * sendSmsCode4tran  交易之前的发送验证码
	  *
	  * @Title: sendSmsCode4tran
	  * @param @param amount
	  * @param @param card
	  * @param @param loginUser    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public Object sendSmsCode4tran(double amount, Card card, UserLogInfo loginUser) {

		ResponseEntity res=new ResponseEntity();
		//1. 记录充值流水
		logs.debug("1. 记录充值流水 start ..");
		accountflowService.insert(amount, card, loginUser, res,TransDirection.INPUT.getCode(),Type.RECHARGE.getCode(),State.INVOKING_SMS_SEND_02.getCode());
		Accountflow accountflow=(Accountflow) res.getData();
		
		if(! res.isSuccess()){
			return res.toJson();
		}
		//2.调用接口充值
		logs.debug("2.调用接口发送短信 start ..");
		//*******************######调用接口########***************************************
		invokeInterface4SendSmsCode(amount,card,res,accountflow);
		String interfaceReturn=res.getErrmsg();
		//*******************#######调用接口#######***************************************
		if(! res.isSuccess()){
			//充值流水update  短信发送分为成功和失败
			logs.debug("2.1 .调用接口发送短信【不成功】更新操作 start ..");
			accountflowService.sendSmscodeFail(amount,card,loginUser,accountflow,res);
			//无论是否更新成功，对用户来说是充值失败
			res.setSuccess(false);
			res.setErrmsg(interfaceReturn==null?"发送短信失败，接口无响应信息":interfaceReturn);
			return res.toJson();
		}
		
		//3.  发送短信成功
		logs.debug("3.调用接口发送短信【成功】 更新操作 start ..");
		logs.debug("3.1.调用接口发送短信 充值流水update start ..");//流水update   
		accountflowService.sendSmscodeSucc(amount,card,loginUser,accountflow,res);//成功会修改  accountflowNew 的state summary summarycode total useAmount
		res.setErrmsg("发送短信成功，亲注意接收验证码");//显示给前台
		res.setData(accountflow.getAccountId());
		return res.toJson();
	
		
	}


	/**
	 * @param accountflow 
	 * 
	  * invokeInterface4SendSmsCode调用接口发送短息
	  *
	  * @Title: invokeInterface4SendSmsCode
	  * @param @param amount
	  * @param @param card
	  * @param @param res    设定文件
	  * @return void    返回类型
	  * Stringer.setFailState("testsmscodeFail_code","testsmscodeFail_模拟失败",res);//模拟失败
	  * Stringer.setSuccessState("test_smscode_Succ_code","test_smscode_succ",res);//模拟成功
	  * @throws
	 */
	private void invokeInterface4SendSmsCode(double amount, Card card, ResponseEntity res, Accountflow accountflow) {

		
		Object[] objArr = new Object[]{amount,card,res,accountflow};
		// ***************调用银行加密方法组装银行参数***********************
		logs.debug("###>>>调用银行加密方法组装银行参数 ");
		IPay payInstance = new PayFactory().getInstance(PayFactoryEnum.CASHIERPAY.getCode());
		ResponseEntity resRet = payInstance.sendSmsCode(objArr);
		if(resRet.isSuccess()){
			Stringer.setSuccessState(resRet.getErrcode(),resRet.getErrmsg(),res,resRet.getData());//模拟成功
		}else{
			Stringer.setFailState(resRet.getErrcode(),resRet.getErrmsg(),res);//模拟失败
		}
		
		
	}

	
	

	
	
	
}
