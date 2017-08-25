package com.zhl.job.pay.common;

import com.zhl.job.pay.IPay;
import com.zhl.job.pojo.common.ResponseEntity;
/**
 * 
  * @ClassName: 定义支付基类
  * @author zhaotisheng	
  * @date 2017年3月20日 下午3:43:57
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public abstract class BasePay implements IPay {

	public ResponseEntity sendSmsCode(Object[] objArr) {
		return null;
	}

	public ResponseEntity payConfirm(Object[] objArr) {
		return null;
	}

	public ResponseEntity validCard(Object[] objArr) {
		return null;
	}

	
}
