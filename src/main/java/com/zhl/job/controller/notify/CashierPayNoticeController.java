package com.zhl.job.controller.notify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pub.util.security.MessageSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhl.job.interceptor.anno.NoAuth;
import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.service.ICashierPayService;
import com.zhl.job.util.JsonUtil;
import com.zhl.job.util.Stringer;

/**
 * 调用收银台集成接口的异步通知
  * @ClassName: CashierPayNoticeController
  * @author zhaotisheng	
  * @date 2017年3月21日 下午5:44:22
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
@Controller
@RequestMapping(value = "/cashierPayNotice")
public class CashierPayNoticeController {

	private Logger logs = LoggerFactory.getLogger(CashierPayNoticeController.class);
	
	@Autowired
	ICashierPayService cashierPayService;
	
	private static final String  enkey="cashier";
	
	@RequestMapping(method = RequestMethod.POST)@NoAuth
	public void home(Model model, HttpServletRequest request,@RequestBody String body,HttpServletResponse response) throws IOException {
		logs.debug("##>>>ankjNotifyAccept Request OK! 【大学生兼职接收异步通知】start...");
		Map<String,Object> noticeMap=validateNotice(body);
		if(Stringer.isNullOrEmpty(noticeMap)){
			return ;
		}
		//收银台支付通知-(目前只有支付成功通知)
		ResponseEntity res=cashierPayService.dealPayResult(noticeMap);
		if(res.isSuccess()){
			logs.debug("##>>>ankjNotifyAccept Request OK! 【大学生兼职接收异步通知】处理成功...");
			wirteToResponse(response,"success");
		}else{
			logs.debug("##>>>ankjNotifyAccept Request OK! 【大学生兼职接收异步通知】处理失败...");
		}
		
		
	}
	/**
	 * 
	  * wirteToResponse
	  * @Title: wirteToResponse
	  * @param @param response
	  * @param @param text    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void wirteToResponse(HttpServletResponse response, String text) {
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write(text);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logs.error("##>>> 【收银台集成接口的异步通知】exception如下:\t\r\n");
			e.printStackTrace();
		}
		
	}

	/**
	 * @param body 
	 * @throws IOException 
	 * 
	  * validateNotice通知的有效性判断
	  *
	  * @Title: validateNotice
	  * @param @param request
	  * @param @return    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	 */
	private Map<String, Object> validateNotice( String body) throws IOException {
		logs.debug("###>>>开始验证......");
		Map<String, Object> map = JsonUtil.toMap(new StringBuilder(body));
		boolean res = MessageSecurity.checkMapObjMessageSecurity(map,enkey);
		if(!res){
			logs.debug("###>>>验证不通过......");
			return null;
		}
		logs.debug("###>>>验证通过......");
		return map;
	}
}
