package com.zhl.job.pay;

import com.zhl.job.pojo.common.ResponseEntity;
/**
 * 	定义支付接口
  * @ClassName: PayInterface
  * @author zhaotisheng	
  * @date 2017年3月20日 下午3:43:28
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface IPay {

	/**
	 * 快捷支付第一步发送短信
	 */
	ResponseEntity sendSmsCode(Object[] objArr);
	/**
	 * 第二步支付确认
	 */
	ResponseEntity payConfirm(Object[] objArr);
	/**
	 * 绑卡校验 有效性验证
	 */
	ResponseEntity validCard(Object[] objArr);
}
