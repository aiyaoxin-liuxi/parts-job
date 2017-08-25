package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *  左侧列表
  * @ClassName: DivActive
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月28日 下午5:52:33
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum DivActive {

	PUB_JOB("pub_job","发布兼职")
	,JOB_MAN("job_man","兼职管理")
	,COM_INF("com_inf","企业信息")
	,ACC_INF("acc_inf","账户信息")
	,ACC_BIND("acc_bind","账号绑定")
	,SEC_CENTER("sec_center","安全中心")
	//个人
	,MY_JOB("my_job","我的兼职")
	,MY_INF("my_inf","我的信息")
	,MY_ACC("my_acc","个人账户")
	//后台(目前7个)
	,SETT_LIST("sett_list","结算信息")
	,DICT_LIST("dict_list","字典列表")
	,DICT_ADD("dict_add","字典add")
	,AUDIT_CHECK("audit_check","兼职的审核和驳回")//兼职的审核和驳回
	,CON_LIST("con_list","companyContractList")//companyContractList
	,CASH_ORDER("cash_order","cashOrderList")//cashOrderList
	,USERLOG_LIST("userLog_list","userLogInfoList")//userLogInfoList
	//
	,APPLY_LIST("apply_list","apply_list")//
	,WORKINFO_LIST("workInfo_list","workInfo_list")//u
	;
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(DivActive.parseOf("00").getName());	
	}
	
	private DivActive(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String, DivActive> valueMap = new HashMap<String, DivActive>();

	static {
		for (DivActive _enum : DivActive.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static DivActive parseOf(String code) {
		for (DivActive item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("DivActive异常错误代码[" + code + "]不匹配!");
	}

}
