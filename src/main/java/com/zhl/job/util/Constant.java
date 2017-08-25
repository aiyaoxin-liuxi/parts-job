package com.zhl.job.util;


/**
 * 常量
  * @ClassName: Constant
  * @author zhaotisheng	
  * @date 2017年3月14日 下午3:06:57
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class Constant {

	public static Integer pageSize=10;
	//页面左侧激活div标志 配合枚举类DivActive
	public static final String DIV_ACTIVE = "active";
	public static final String LOGIN_USER_TYPE="userType";
	public static final String IMAGE_RESET_PATH = "/static/img/";
	public static final String LOGIN_USER_MOBILE="LOGIN_USER_MOBILE";
	
	//个人
	public static final String LOGIN_USER_ID="LOGIN_USER_ID";
	public static final String LOGIN_USER_ENTITY="LOGIN_USER_ENTITY";
	//企业
	public static final String LOGIN_COMPANYINFO_ID="LOGIN_COMPANYINFO_ID";
	public static final String LOGIN_COMPANYINFO_ENTITY="LOGIN_COMPANYINFO_ENTITY";
	
	public static final String USER_NAME="USER_NAME";
	//后台
	public static final String LOGIN_USER_ID4SYS="LOGIN_USER_ID4SYS";
	
	//UserInfo 兼职用户表主键
	public static final String LOGIN_PERSON_ID="LOGIN_PERSON_ID";
	//verifyCode 图像码
	public static final String VERIFY_CODE="VERIFY_CODE";
	//短信码
	public static final String SMSCODE_KEY="SMSCODE_KEY";
	
	// 当前城市
	public static final String NOW_CITY="NOW_CITY";
	// 缓存存储
	// 市级、区县级
	public static final String CITY_MAP = "CITY_MAP";// 市级
	public static final String AREA_MAP = "AREA_MAP";// 区县级
	// 银行列表
	public static final String BANK_LIST = "BANK_LIST";// 银行列表
	// 卡bin
	public static final String CARD_BIN = "CARD_BIN";
	
	public static  String REPOSITORY_IMG = "";
	static{
		REPOSITORY_IMG = Constant.class.getClassLoader().getResource("/").getFile().replace("/WEB-INF/classes/", IMAGE_RESET_PATH);
	}
//	0 表示都是模拟的，  1表示都是真实的
	public static  String DEV_SWITCH = Stringer.nullToEmpty(PropsHandler.getProperty("dev.switch"));
	//项目路径
	public static final String PROJECT_CONTEXT=Stringer.nullToEmpty(PropsHandler.getProperty("project.context"));//异步通知地址
	//webyurl cashier异步通知地址
	public static final String CASHIER_WEBYURL=PROJECT_CONTEXT+Stringer.nullToEmpty(PropsHandler.getProperty("cashier.webyurl.url"));//异步通知地址
	//##########################################	
	public static final String CASHIER_URL_ROOT = Stringer.nullToEmpty(PropsHandler.getProperty("cashier.url.root"));
	//充值
	public static final String RECHARGE_URL = CASHIER_URL_ROOT+Stringer.nullToEmpty(PropsHandler.getProperty("recharge.url"));
	
	public static final String  SEND_SMS_URL  =Stringer.nullToEmpty(PropsHandler.getProperty("send.sms.url"));//异步通知地址
	
	//提现
	public static final String CASH_MERID =  Stringer.nullToEmpty(PropsHandler.getProperty("cash.merId"));
	public static final String CASH_URL = Stringer.nullToEmpty(PropsHandler.getProperty("cash.url"));
	public static final String CASH_QUERY_URL = Stringer.nullToEmpty(PropsHandler.getProperty("cash.query.url"));
	
	//
	public static final String IMAGE_SERVER_URL=Stringer.nullToEmpty(PropsHandler.getProperty4Util("image_server_url"));
	public static final String IMAGE_SERVER_URL_LOAD=Stringer.nullToEmpty(PropsHandler.getProperty4Util("image_server_url_load"));
	
}
