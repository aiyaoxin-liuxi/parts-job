package com.zhl.job.pojo.enums.trans;

import java.util.HashMap;
import java.util.Map;


/**
 *  交易类型
  * @ClassName: Type
  * @author zhaotisheng	
  * @date 2017年3月17日 上午9:31:29
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum Type {
	
    RECHARGE("00","充值","CZ")
	,WITHDRAW("01","提现","TX")
	,TRANSFER("02","转账","ZZ")
	;
	
	private String code;
	private String name;
	private String prefixTranNo;//枚举交易流水号前缀
	
	public static void main(String[] args) {
	 System.out.println(Type.parseOf("00").getName());	
	}
	
	private Type(String code,String name,String prefixTranNo){
		this.code=code;
		this.name=name;
		this.prefixTranNo=prefixTranNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

	public String getPrefixTranNo() {
		return prefixTranNo;
	}

	public void setPrefixTranNo(String prefixTranNo) {
		this.prefixTranNo = prefixTranNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static Map<String, Type> valueMap = new HashMap<String, Type>();

	static {
		for (Type _enum : Type.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static Type parseOf(String code) {
		for (Type item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("Type异常错误代码[" + code + "]不匹配!");
	}

}
