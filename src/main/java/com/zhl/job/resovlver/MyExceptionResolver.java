package com.zhl.job.resovlver;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import com.zhl.job.resovlver.exception.ExceptionType;
import com.zhl.job.resovlver.exception.NoLogin4SysException;
import com.zhl.job.resovlver.exception.NoLoginException;

public class MyExceptionResolver implements HandlerExceptionResolver{

	private Logger logs = LoggerFactory.getLogger(MyExceptionResolver.class);
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logs.error("==============异常开始=============");
		ex.printStackTrace();
		logs.error("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		mv=handlerEx(ex,request,response,mv);
		return mv;
	}

	private ModelAndView handlerEx(Exception ex, HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {
		if(ex instanceof NoLoginException){
			mv = new ModelAndView(ExceptionType.NO_LOGIN.getUrl());
			mv.addObject("msg", ExceptionType.NO_LOGIN.getName());
		}else if(ex instanceof NoLogin4SysException){
			mv = new ModelAndView(ExceptionType.NO_LOGIN4_SYS.getUrl());
			mv.addObject("msg", ExceptionType.NO_LOGIN4_SYS.getName());
		}
		return mv;
	}

}
