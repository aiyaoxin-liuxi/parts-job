package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.job.dao.IAccountflowDao;
import com.zhl.job.dao.IUserLogInfoDao;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.pojo.enums.trans.TransDirection;
import com.zhl.job.pojo.enums.trans.Type;
import com.zhl.job.service.IAccountflowService;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.TransUtil;

/**
 * 
  * @ClassName: AccountflowService
  * @author zhaotisheng	
  * @date 2017年3月16日 下午5:23:38
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Service
@Transactional
public class AccountflowService implements IAccountflowService{

	private Logger logs = LoggerFactory.getLogger(AccountflowService.class);
	
	@Autowired
	IAccountflowDao accountflowDao;
	@Resource
	private IUserLogInfoDao userLogInfoDao;
	
	public Accountflow selectByPrimaryKey(String accountId){
		
		return accountflowDao.selectByPrimaryKey(accountId);
	}
	/**
	 * 插入流水
	 */
	public void insert(double amount, Card card, UserLogInfo loginUser, ResponseEntity res) {
		//收入
		Accountflow accountflow=
				TransUtil.generateAccountflow(amount,card,loginUser,TransDirection.INPUT.getCode(),Type.RECHARGE.getCode(),State.INVOKING_SMS_SEND_02.getCode());
		
		insertEntity(accountflow,res);
	}

	/**
	 * 重构insert
	 */
	public void insert(double amount, Card card, UserLogInfo loginUser, ResponseEntity res, String transDirection,
			String type, String state) {

		Accountflow accountflow=
				TransUtil.generateAccountflow(amount,card,loginUser,transDirection,type,state);
		insertEntity(accountflow,res);
		
	}
	/**
	 * 插入实体
	  * insertEntity
	  * @Title: insertEntity
	  * @param @param accountflow
	  * @param @param res    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void insertEntity(Accountflow accountflow, ResponseEntity res) {
		int i = accountflowDao.insertSelective(accountflow);
		logs.debug("##>>>插入流水实体id:"+accountflow.getAccountId()+" 插入结果i:"+i);
		if (i == 1) {
			res.setSuccess(true);
			res.setData(accountflow);
		}else{
			res.setSuccess(false);
			res.setErrmsg("插入流水实体失败");
		}
	}


	//充值失败
	public void rechargeFail(UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res) {
		logs.debug("##>>> 充值失败更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.FAIL_99.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "充值失败更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
		
	}

	//充值进行中...
	public void recharging(UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 充值ing 更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.DOING.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "充值ing更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
	}
	
	//充值succ
	public void rechargeSucc(UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 充值成功 更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.SUCCESS_66.getCode());
		
		logs.debug("3.2.调用接口充值成功 余额add start ..");//更新余额
		////////////////
		UserLogInfo olduser = userLogInfoDao.selectByPk(loginUser.getId());
		
		UserLogInfo UserLogInfonew=new UserLogInfo();
		UserLogInfonew.setId(olduser.getId());
		/****************/
		BigDecimal addTotal = BigDecimalUtil.add(accountflow.getAmount(), olduser.getTotal());
		UserLogInfonew.setTotal(addTotal);
		accountflowNew.setTotal(addTotal);
		BigDecimal addUseAmount = BigDecimalUtil.add(accountflow.getAmount(), olduser.getUseAmount());
		UserLogInfonew.setUseAmount(addUseAmount);
		accountflowNew.setUseAmount(addUseAmount);
		/****************/
		int i = userLogInfoDao.updateByPrimaryKeySelective(UserLogInfonew);
		logs.debug("3.2.调用接口充值成功 余额add end ..");//更新余额
		Stringer.commonOperation(i, "增加余额", res);
		logs.debug("###>>>"+res.getErrmsg());
		//////////////////
		
		int i2 = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i2, "充值成功更新accountflow", res);
		logs.debug("##>>> "+res.getErrmsg());
		
		if( !(i==1&& i2==1)){
			logs.error("###>>> 充值成功，但是增加余额或者修改 accountflow失败，"+JsonUtil.toJson(accountflow));
			throw new RuntimeException("充值成功，但是增加余额或者修改 accountflow失败");
		}
		res.setSuccess(true);
		res.setErrmsg("充值成功");
	}

	private Accountflow getAccountFlowNew(Accountflow accountflow, ResponseEntity res, String state) {
		Accountflow accountflowNew = new Accountflow();
		dealSpecialOperation(res,accountflowNew);
		accountflowNew.setAccountId(accountflow.getAccountId());
		accountflowNew.setState(state);
		setInterfaceReturnInfo(accountflowNew,res);
		return accountflowNew;
	}

	private void setInterfaceReturnInfo(Accountflow accountflowNew, ResponseEntity res) {
		if(Stringer.isNullOrEmpty(res)){
			return ;
		}
		Object data = res.getData();
		if(Stringer.isNullOrEmpty(data)){
			return ;
		}
		try {
			ResponseEntity resRet=(ResponseEntity)res.getData();
			accountflowNew.setSummary(resRet.getErrmsg() ==null?"接口无返回":resRet.getErrmsg() );
			accountflowNew.setSummaryCode(resRet.getErrcode()==null?State.ERROR_INTERFACE_NODATA_08.getCode():resRet.getErrcode());
		} catch (Exception e) {
			logs.info("accountflowNew无法保存接口返回信息,TransFlowNo: "+accountflowNew.getTransFlowNo());
		}
	}

	/**
	 *  发送短信失败
	 */
	public void sendSmscodeFail(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 发送短信失败更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.INVOKING_SMS_SEND_FAIL_03.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "充值失败更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
		
	}

	/**
	 *  发送短信成功
	 */
	@SuppressWarnings("unchecked")
	public void sendSmscodeSucc(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 发送短信成功更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.INVOKING_SMS_SEND_SUCC_33.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "发送短信成功 更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
		
	}
	
	//收银台发送短信的返回 的处理
	@SuppressWarnings("unchecked")
	private void dealSpecialOperation(ResponseEntity res, Accountflow accountflow) {
		Object data = res.getData();
		if(Stringer.isNullOrEmpty(data)){
			return;
		}
		ResponseEntity interfaceReturn = (ResponseEntity)data ;
		Object data2 = interfaceReturn.getData();
		if(Stringer.isNullOrEmpty(data2) ||  !(data2 instanceof Map)){
			return ;
		}
		Map<String,Object> interfaceReturnData = (Map<String, Object>) data2;
		if(Stringer.isNullOrEmpty(interfaceReturnData)){
			return;
		}
		Object tnObject = interfaceReturnData.get("orderid");
		if(!Stringer.isNullOrEmpty(tnObject)){
			accountflow.setRetTn(tnObject.toString());
		}
	}
	
	
	/**
	 * 自定义state
	 */
	public void rechargeMyspecifyState(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res, String mystate) {
		logs.debug("##>>> 自定义state更新start.... "+mystate);
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,mystate);
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "自定义state 更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
		
	}
	
	
	public List<Accountflow> selectByTransFlowNo(String transFlowNo) {
		return accountflowDao.selectByTransFlowNo(transFlowNo);
	}

	public Accountflow insertForCash(double amount, Card card, UserLogInfo loginUser, ResponseEntity res) {
		//收入
		Accountflow accountflow=
				TransUtil.generateAccountflow(amount,card,loginUser,TransDirection.OUTPUT.getCode(),Type.WITHDRAW.getCode(),State.INVOKING_01.getCode());
		
		insertEntity(accountflow,res);
		return accountflow;
	}

	public void cashing(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 提现ing 更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.DOING.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "提现ing更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
	}
	public void cashSucc(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res) {
		logs.debug("##>>> 提现成功 更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.SUCCESS_66.getCode());
		
		logs.debug("3.2.调用接口提现成功 余额sub start ..");//更新余额
		////////////////
		UserLogInfo olduser = userLogInfoDao.selectByPk(loginUser.getId());
		
		UserLogInfo UserLogInfonew=new UserLogInfo();
		UserLogInfonew.setId(olduser.getId());
		/****************/
		double addTotal = BigDecimalUtil.sub(olduser.getTotal().doubleValue(),amount);
		UserLogInfonew.setTotal(new BigDecimal(addTotal));
		accountflowNew.setTotal(new BigDecimal(addTotal));
		double addNoUseAmount = BigDecimalUtil.sub(olduser.getNoUseAmount().doubleValue(),amount);
		UserLogInfonew.setNoUseAmount(new BigDecimal(addNoUseAmount));
		accountflowNew.setNoUseAmount(new BigDecimal(addNoUseAmount));
		/****************/
		int i = userLogInfoDao.updateByPrimaryKeySelective(UserLogInfonew);
		logs.debug("3.2.调用接口提现成功 余额sub end ..");//更新余额
		Stringer.commonOperation(i, "余额减", res);
		logs.debug("###>>>"+res.getErrmsg());
		//////////////////
		
		int i2 = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i2, "提现成功更新accountflow", res);
		logs.debug("##>>> "+res.getErrmsg());
		
		if( !(i==1&& i2==1)){
			logs.error("###>>> 提现成功，但是减少余额或者修改 accountflow失败，"+JsonUtil.toJson(accountflow));
			throw new RuntimeException("提现成功，但是减少余额或者修改 accountflow失败");
		}
		res.setSuccess(true);
		res.setErrmsg("提现成功");
	}
	//失败
	public void cashFail(UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res) {
		logs.debug("##>>> 提现失败更新start.... ");
		Accountflow accountflowNew =getAccountFlowNew(accountflow,res,State.FAIL_99.getCode());
		int i = accountflowDao.updateByPrimaryKeySelective(accountflowNew);
		Stringer.commonOperation(i, "提现失败更新db", res);
		logs.debug("##>>> "+res.getErrmsg());
		
	}

	/**
	 * /提现的时候  --提交审核--
	 */
	public void insert4WaitAudit(double amount, Card card, UserLogInfo loginUserDb, ResponseEntity res) {
		// TODO Auto-generated method stub
		Accountflow accountflow=
				TransUtil.generateAccountflow(amount,card,loginUserDb,TransDirection.OUTPUT.getCode(),Type.WITHDRAW.getCode(),State.WAIT_AUDIT.getCode());
		insertEntity(accountflow,res);
		if(!res.isSuccess()){
			logs.error("###>>>插入流水实体失败");
			return;
		}
		UserLogInfo UserLogInfonew=new UserLogInfo();
		UserLogInfonew.setId(loginUserDb.getId());
		//可用减去
		double resSub = BigDecimalUtil.sub(getDecimal(loginUserDb.getUseAmount()).doubleValue(),amount);
		UserLogInfonew.setUseAmount(new BigDecimal(resSub));
		//冻结增加
		BigDecimal add = BigDecimalUtil.add(getDecimal(loginUserDb.getNoUseAmount()), new BigDecimal(amount));
		UserLogInfonew.setNoUseAmount(add);
		
		int i = userLogInfoDao.updateByPrimaryKeySelective(UserLogInfonew);
		Stringer.commonOperation(i, "提交审核", res);
	}


	private static BigDecimal getDecimal(BigDecimal decimal) {
		if(Stringer.isNullOrEmpty(decimal)){
			return new BigDecimal(0);
		}
		if(decimal.equals(new BigDecimal(0))){
			return new BigDecimal(0);
		}
		return decimal;
	}
	@Override
	public PageInfo<Accountflow> queryAccountflowPage(Map<String, String> map, Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
	    List<Accountflow> list = accountflowDao.queryAccountflowPage(map);
	    //用PageInfo对结果进行包装
	    PageInfo<Accountflow> page = new PageInfo<Accountflow>(list);
	    return page;
	}
	public List<Accountflow> getListByUserId(String loginUserId) {
		return accountflowDao.selectByUserId(loginUserId);
	}
	@Override
	public List<Accountflow> queryAccountflowHanding(Map<String, String> map) {
		return accountflowDao.queryAccountflowHanding(map);
	}
	
}
