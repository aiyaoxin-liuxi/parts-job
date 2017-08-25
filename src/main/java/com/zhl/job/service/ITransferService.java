package com.zhl.job.service;

import java.math.BigDecimal;

import com.zhl.job.pojo.common.ResponseEntity;

/**
 * 转账操作
 * @author 刘熙
 *
 */
public interface ITransferService {
	
	public ResponseEntity transfer(BigDecimal amount, String outUserId, String toUserId);

}
