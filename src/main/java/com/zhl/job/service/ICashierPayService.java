package com.zhl.job.service;

import java.util.Map;

import com.zhl.job.pojo.common.ResponseEntity;

/**
 * 	service
  * @ClassName: ICashierPayService
  * @author zhaotisheng	
  * @date 2017年3月22日 上午9:58:08
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface ICashierPayService {

	ResponseEntity dealPayResult(Map<String, Object> noticeMap);

}
