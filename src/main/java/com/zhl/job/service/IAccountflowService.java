package com.zhl.job.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.Card;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;

public interface IAccountflowService {

	void insert(double amount, Card card, UserLogInfo loginUser, ResponseEntity res);

	void rechargeFail(UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);

	void recharging(UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);

	void rechargeSucc( UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);

	void insert(double amount, Card card, UserLogInfo loginUser, ResponseEntity res, String transDirection, String type,
			String state);
	//发送短信失败
	void sendSmscodeFail(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);

	void sendSmscodeSucc(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);

	Accountflow selectByPrimaryKey(String accountflowAccountId);

	void rechargeMyspecifyState(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow,
			ResponseEntity res, String code);

	Accountflow insertForCash(double amount, Card card, UserLogInfo loginUser, ResponseEntity res);
	void cashing(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);
	void cashSucc(double amount, Card card, UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);
	void cashFail(UserLogInfo loginUser, Accountflow accountflow, ResponseEntity res);
	List<Accountflow> selectByTransFlowNo(String string);

	void insert4WaitAudit(double amount, Card card, UserLogInfo loginUser, ResponseEntity res);

	/**
	 * 提现记录分页查询
	 * @param map
	 * @return
	 */
	PageInfo<Accountflow> queryAccountflowPage(Map<String,String> map, Integer pageNo,Integer pageSize);
	List<Accountflow> getListByUserId(String loginUserId);

	/**
	 * 查询正在处理中的提现订单
	 * @param map
	 * @return
	 */
	List<Accountflow> queryAccountflowHanding(Map<String,String> map);
}
