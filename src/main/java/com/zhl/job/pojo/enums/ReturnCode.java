package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReturnCode {


	/** 成功 */
	SUCCESS_000000("000000","操作成功"),
	/** 未找到请求对象 */
	OBJECT_NOT_FIND_111111("111111","未找到请求对象"),
	/** 对象已经存在 */
	OBJECT_ALREADY_EXIST_222222("222222","对象已经存在"),
	/** 数据验证失败 */
	DATA_VALID_FAIL_200000("200000","数据验证失败"),
	/** 短信验证码错误 2016-12-10为爱农快捷支付添加*/
	DATA_VALID_SMSCODE_FAIL_202015("202015","短信验证码错误"),
	/** 数据验签失败 */
	DATA_SIGNATURE_FAIL_300000("300000","数据验签失败"),
	/** 接口调用失败 */
	GATEWAY_EXCEPTION_400000("400000","接口调用失败"),
	/** 网络连接失败 */
	REQUEST_TIMEOUT_500000("500000","网络连接失败"),
	/** 程序错误 */
	PROGRAM_EXCEPTION_600000("600000","程序错误"),
	/** 事件验证失败 */
	EVENT_EXCEPTION_888888("888888","事件验证失败"),
	/** 系统错误 */
	SYSTEM_EXCEPTION_999999("999999","系统错误"),
	/** 操作失败 */
	FAIL_110110("110110","操作失败");
	
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(ReturnCode.parseOf("00").getName());	
	}
	
	private ReturnCode(String code,String name){
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
	
	private static Map<String, ReturnCode> valueMap = new HashMap<String, ReturnCode>();

	static {
		for (ReturnCode _enum : ReturnCode.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static ReturnCode parseOf(String code) {
		for (ReturnCode item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("ReturnCode异常错误代码[" + code + "]不匹配!");
	}

}
