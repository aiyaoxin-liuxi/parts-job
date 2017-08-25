package com.zhl.job.pojo.common;

import com.zhl.job.util.JsonUtil;

public class ResponseEntity {



	private boolean success; // 是否成功
	private String errmsg; // 错误内容
	private String errcode; // 错误代码
	private Object data; // 数据对象
	
	public ResponseEntity(){
		
	}

	/**
	 * @desc 构造函数.
	 * @param success
	 * @param data
	 * @param errcode
	 * @param errmsg
	 */
	public ResponseEntity(boolean success, Object data, String errcode, String errmsg) {
		this.success = success;
		this.data = data;
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	/**
	 *
	 * @desc 构造函数.
	 * @param errcode
	 * @param errmsg
	 */
	public ResponseEntity(String errcode, String errmsg) {
		this.success = false;
		this.data = null;
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	/**
	 *
	 * @desc 构造函数.
	 * @param data
	 */
	public ResponseEntity(Object data) {
		this.success = true;
		this.data = data;
	}

	/**
	 *
	 * @desc 将ResponseEntity转换成json格式对象.
	 * @return
	 */
	public Object toJson(){
		return JsonUtil.toJson(this);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



}
