package com.zhl.job.resovlver.exception;
/**
 *  异常基类
  * @ClassName: BaseException
  * @author zhaotisheng	
  * @date 2017年3月24日 下午4:40:57
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class BaseException  extends Exception{

	private  boolean effect=true;
	
	private String errcode;
	
	private String msg;
	
	private static final long serialVersionUID = 1L;
	public BaseException(){
		
	}
	public BaseException(String msg){
		super(msg);
		this.msg=msg;
	}
	public BaseException(String errcode,String msg){
		this.errcode=errcode;
		this.msg=msg;
	}
	
	public boolean isEffect() {
		return effect;
	}
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
