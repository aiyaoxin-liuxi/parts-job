package com.zhl.job.pojo.enums.company;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业性质
  * @ClassName: companyType
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月14日 下午5:35:54
 */
public enum CompanyType {

	
     NATIONAL("1","国有")
	,COOPERATION("2","合作")
	,JOINTVENTURE("3","合资")
	,OWNEDCOMPANY("4","独资")
	,CONCENTRATEFUNDS("5","集资")
	,PRIVATECOMPANY("6","私营")
	,PERSONALBUSI("7","个体工商户")
	,DECLARE("8","报关")
	,OTHER("9","其它")
	;
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(CompanyType.parseOf("00").getName());	
	}
	
	private CompanyType(String code,String name){
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
	
	private static Map<String, CompanyType> valueMap = new HashMap<String, CompanyType>();

	static {
		for (CompanyType _enum : CompanyType.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static CompanyType parseOf(String code) {
		for (CompanyType item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("CompanyType异常错误代码[" + code + "]不匹配!");
	}
}
