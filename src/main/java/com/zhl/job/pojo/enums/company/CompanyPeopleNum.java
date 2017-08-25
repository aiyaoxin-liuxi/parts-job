package com.zhl.job.pojo.enums.company;

import java.util.HashMap;
import java.util.Map;
/**
 * 企业规模
  * @ClassName: CompanyPeopleNum
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月14日 下午5:56:38
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum CompanyPeopleNum {


    MIN("1","微型(0-10人)")
	,LITTLE("2","小型(11-100人)")
	,MIDDLE("3","中型(101-500人)")
	,BIG("4","大型(500人以上)")
	;
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(CompanyPeopleNum.parseOf("00").getName());	
	}
	
	private CompanyPeopleNum(String code,String name){
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
	
	private static Map<String, CompanyPeopleNum> valueMap = new HashMap<String, CompanyPeopleNum>();

	static {
		for (CompanyPeopleNum _enum : CompanyPeopleNum.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static CompanyPeopleNum parseOf(String code) {
		for (CompanyPeopleNum item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("CompanyPeopleNum异常错误代码[" + code + "]不匹配!");
	}
}
