package com.zhl.job.pay.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhl.job.pay.IPay;

/**
 * 
  * @ClassName: PayFactory
  * @author zhaotisheng	
  * @date 2017年3月20日 下午3:45:57
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
  * The Open-Closed Principle，OCP
 */
public class PayFactory {
   

	private Logger logs = LoggerFactory.getLogger(PayFactory.class);
	
	private  IPay payInterface; 
    public  IPay getInstance(String bankType) { 
    	try {
    		PayFactoryEnum payenum = PayFactoryEnum.parseOf(bankType);
    		logs.info("###>>> ready to invoke :"+payenum.getDesc());
			payInterface =(IPay) Class.forName(payenum.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return payInterface; 
    } 

}
