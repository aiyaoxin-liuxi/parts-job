package com.zhl.job.pojo.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信的返回码枚举
  * @ClassName: SmsCode
  * @author zhaotisheng	
  * @date 2017年3月24日 下午3:42:52
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public enum SmsCode {


	
	/** 成功 */
	CODE111111("111111","成功"),
	
	/** 数据验证失败 */
	CODE200000("200000","数据验证失败"),
	/** 上传参数验证失败： */
	CODE200002("200002","上传参数验证失败："),
	/** 无法找到通知方法 */
	CODE200003("200003","无法找到通知方法"),
	/** 该应用没有此短信权限 */
	CODE200004("200004","该应用没有此短信权限"),
	/** 没有可用短信通道 */
	CODE200005("200005","没有可用短信通道"),
	/** 短信类型与实际发送数量不匹配 */
	CODE200006("200006","短信类型与实际发送数量不匹配"),
	/** 未配置此类型短信通道优先级 */
	CODE200007("200007","未配置此类型短信通道优先级"),
	/** 短信通道未返回数据 */
	CODE200008("200008","短信通道未返回数据"),
	/** 应用签名不正确 */
	CODE200009("200009","应用签名不正确"),
	/** 数据验签失败 */
	CODE300000("300000","数据验签失败"),
	/** 通道调用失败 */
	CODE400000("400000","通道调用失败"),
	
	/** 短信发送接口返回 */
	CODE410001("410001","用户名为空"),
	CODE410002("410002","用户名错误"),
	CODE410003("410003","密码为空"),
	CODE410004("410004","密码错误"),
	CODE410005("410005","当前时间为空"),
	CODE410006("410006","当前时间错误"),
	CODE410007("410007","签名格式错误"),
	CODE410008("410008","提交号码为空"),
	CODE410009("410009","提交号码错误"),
	CODE410010("410010","短信内容为空"),
	CODE410011("410011","短信内容超长（134个字以内）"),
	CODE410012("410012","余额不足"),
	CODE410013("410013","一分钟内一个号码允许发送两条"),
	CODE410014("410014","内容存在黑词"),
	CODE410015("410015","号码为黑号"),
	CODE410016("410016","内容不符合规则，必须是验证码短信"),
	CODE410017("410017","DES解密异常"),
	CODE410018("410018","xh(扩展号)必须是数字"),
	CODE410019("410019","xh必须小于等8位"),
	CODE410020("410020","用户停用"),
	CODE410021("410021","签名屏蔽"),
	CODE410022("410022","一个小时只允许发5条"),
	CODE410023("410023","一天一个手机号只允许发20条"),
	CODE410024("410024","该用户类型不允许发送短信"),
	CODE410025("410025","签名分配扩展失败"),
	CODE410026("410026","添加黑名单失败"),
	CODE410027("410027","签名屏蔽失败"),
	CODE410028("410028","队列找不到该用户签名资源信息"),
	CODE410029("410029","获取黑词信息失败"),
	CODE410030("410030","更新用户队列异常"),
	CODE410031("410031","保存队列失败"),
	CODE410032("410032","发送失败"),
	CODE410033("410033","产品错误"),
	CODE410034("410034","产品禁用"),
	CODE410035("410035","签名不合规"),
	CODE410036("410036","短信内容最大1000个字"),
	CODE410037("410037","请联系系统管理"),// 预付费条数不足
	CODE410038("410038","通道错误"),
	CODE410039("410039","签名最长15个字"),
	CODE410040("410040","小号错误"),
	CODE410041("410041","异常"),
	CODE410042("410042","批量短信手机号码个数2-2000"),
	
	
	/** 网络连接失败 */
	CODE500000("500000","网络连接失败"),
	/** 程序错误 */
	CODE600000("600000","程序错误"),
	/** 事件验证失败 */
	CODE888888("888888","事件验证失败"),
	/** 系统错误 */
	CODE999999("999999","系统错误");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(SmsCode.parseOf("00").getName());	
	}
	
	private SmsCode(String code,String name){
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
	
	private static Map<String, SmsCode> valueMap = new HashMap<String, SmsCode>();

	static {
		for (SmsCode _enum : SmsCode.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static SmsCode parseOf(String code) {
		for (SmsCode item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("SmsCode异常错误代码[" + code + "]不匹配!");
	}




}
