package com.zhl.job.pojo.enums.trans;

import java.util.HashMap;
import java.util.Map;
/**
 * 	状态
  * @ClassName: State
  * @author zhaotisheng	
  * @date 2017年3月17日 上午10:01:31
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum State {

	
     READY_00("00","准备充值")
     
	,INVOKING_01("01","调用接口中")
	,INVOKING_SMS_SEND_02("02","调用发送短信接口中")//细化调用接口，指明是调用短信接口
	,INVOKING_SMS_SEND_FAIL_03("03","发送短信失败")//细化失败，发送短信失败
	,ERROR_SIGN_04("04","签名失败")
	,ERROR_INTERFACE_05("05","接口异常")
	,ERROR_PROGRAM_06("06","程序（系统）异常")
	,ERROR_TIMEOUT_07("07","超时")
	,ERROR_INTERFACE_NODATA_08("08","接口无返回")
	
	,DOING("09","交易中")
	,ERROR_OBJECT_NOT_FIND_11("11","对象不存在")
	//12 等待审核，13审核不通过，14审核通过
	,WAIT_AUDIT("12","申请已提交，等待审核")
	,AUDIT_FAIL("13","审核失败")
	,AUDIT_SUCC("14","审核成功")
	
	,ERROR_SMSCODE_15("15","短信验证码错误")
	,ERROR_DATA_VALID_20("20","数据验证失败")
	,ERROR_OBJECT_EXISTS_22("22","对象已经存在")
	,INVOKING_SMS_SEND_SUCC_33("33","发送短信成功，等待确认")//细化成功为发送短信成功，等待输入验证码确认交易
	,INVOKING_SMS_VERIFY_FAIL_34("34","短信验证失败")
	
	,SUCCESS_66("66","交易/操作成功")
	,FAIL_99("99","交易/操作失败")
	;
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(State.parseOf("00").getName());	
	}
	
	private State(String code,String name){
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
	
	private static Map<String, State> valueMap = new HashMap<String, State>();

	static {
		for (State _enum : State.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static State parseOf(String code) {
		for (State item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("State异常错误代码[" + code + "]不匹配!");
	}


}
