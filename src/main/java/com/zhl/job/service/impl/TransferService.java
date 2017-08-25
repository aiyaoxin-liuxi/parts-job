package com.zhl.job.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.job.dao.IAccountflowDao;
import com.zhl.job.dao.IUserLogInfoDao;
import com.zhl.job.pojo.Accountflow;
import com.zhl.job.pojo.UserLogInfo;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.pojo.enums.trans.State;
import com.zhl.job.pojo.enums.trans.TransDirection;
import com.zhl.job.pojo.enums.trans.Type;
import com.zhl.job.service.ITransferService;
import com.zhl.job.util.Arith;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Stringer;
import com.zhl.job.util.TransUtil;

@Transactional
@Service("transferService")
public class TransferService implements ITransferService {

	private Logger logger = LoggerFactory.getLogger(TransferService.class);
	
	@Autowired
	private IAccountflowDao accountflowDao;
	@Resource
	private IUserLogInfoDao userLogInfoDao;
	
	@Override
	public ResponseEntity transfer(BigDecimal amount, String outUserId, String toUserId) {
		ResponseEntity res = new ResponseEntity();
		
		
		UserLogInfo outUser = userLogInfoDao.selectByPk(outUserId); // 转出用户
		UserLogInfo toUser = userLogInfoDao.selectByPk(toUserId); // 转入用户
		String check = checkTransfer(amount, outUser, toUser);
		if(null == check){
			
			String transFlowNo = Stringer.getPrefixTranNo(Type.TRANSFER.getCode()) + KeySn.getKey();
			logger.info("【转账】开始：流水号:" + transFlowNo);
			
			
			logger.debug("【转账】流水号:" + transFlowNo + "开始生成新用户信息....");
			List<UserLogInfo> userLoglist = getUserLogInfoList(amount, outUser, toUser);
			
			logger.info("【转账】流水号:" + transFlowNo + "转账开始....");
			// 转账（修改用户余额）
			String state = "";
			int insertRe = userLogInfoDao.updateList(userLoglist);
			if (insertRe == -1) {
				logger.info("【转账】流水号:" + transFlowNo + "转账成功");
				state = State.SUCCESS_66.getCode();
				res.setSuccess(true);
				res.setErrmsg("转账成功");
			}else{
				logger.info("【转账】流水号:" + transFlowNo + "转账失败");
				state = State.FAIL_99.getCode();
				res.setSuccess(false);
				res.setErrmsg("转账失败");
			}
			
			List<Accountflow> accList = new ArrayList<Accountflow>();
			// 创建2个转账实体（转出流水记录、转入流水记录）
			// 转出人outUser流水实体
			Accountflow accountflow = 
					TransUtil.generateAccountflow(transFlowNo, amount,outUser,toUser,TransDirection.OUTPUT.getCode(),Type.TRANSFER.getCode(),state);
			// 转入人toUser流水实体
			Accountflow toAccountflow = 
					TransUtil.generateAccountflow(transFlowNo, amount,toUser,outUser,TransDirection.INPUT.getCode(),Type.TRANSFER.getCode(),state);
			accList.add(accountflow);
			accList.add(toAccountflow);
			accountflowDao.insertList(accList);
		} else {
			// 检查出错
			res.setSuccess(false);
			res.setErrmsg("验证错误：" + check);
		}
		return res;
	}
	
	/**
	 * 计算转账后金额，生成需要修改的两个UserLogInfo
	 * @param amount
	 * @param outUser
	 * @param toUser
	 * @return
	 */
	private List<UserLogInfo> getUserLogInfoList(BigDecimal amount, UserLogInfo outUser, UserLogInfo toUser){
		
		List<UserLogInfo> userLoglist = new ArrayList<UserLogInfo>();
		// 修改用户余额
		// 转出账户
		outUser.setTotal(Arith.sub(outUser.getTotal(), amount));
		outUser.setUseAmount(Arith.sub(outUser.getUseAmount(), amount));
		// 转入账户
		toUser.setTotal(Arith.add(toUser.getTotal(), amount));
		toUser.setUseAmount(Arith.add(toUser.getUseAmount(), amount));
		
		userLoglist.add(outUser);
		userLoglist.add(toUser);
		
		return userLoglist;
	}
	
	
	private String checkTransfer(BigDecimal amount, UserLogInfo outUser, UserLogInfo toUser){
		if(null == outUser){
			return "未找到转出用户";
		}
		if(null == toUser){
			return "未找到转入用户";
		}
		if(null != outUser.getUseAmount() && BigDecimalUtil.BigDecimalLessThan(outUser.getUseAmount(), amount)){
			return "转出用户可用余额不足";
		}
		if(BigDecimalUtil.BigDecimalLessThan(amount, new BigDecimal(0))){
			return "交易金额不能小于0元";
		}
		return null;
	}
	
}
