package com.zhl.job.interceptor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhl.job.resovlver.exception.NoLogin4SysException;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;

/**
 *  检查登陆状态 interceptor
  * @ClassName: LoginStatusInterceptor
  * @author zhaotisheng	
  * @date 2017年4月10日 下午6:01:34
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class LoginStatusInterceptor implements HandlerInterceptor {
//	private Logger logs = LoggerFactory.getLogger(LoginStatusInterceptor.class);
	
	
	private String scanPath;
	private String scanPath4Sys;
	
	List<String> packageNames=new ArrayList<String>();//全部的
	List<String> packageNames4Sys=new ArrayList<String>();//后台的
	
	Map<String,String> handMapping=new HashMap<String,String>();
	Map<String,String> handMapping4Sys=new HashMap<String,String>();
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		logs.debug("###>>> start.............");
		if(Stringer.isNullOrEmpty(handMapping) || handMapping.size()<=0){
			init();
		}
		String url  = request.getRequestURI().replaceAll(request.getContextPath(), "");
		String userUrl = handMapping.get(url);String sysUrl = handMapping4Sys.get(url);
		if(!Stringer.isNullOrEmpty(sysUrl)){//后台url
			removeSpecialId(request,Constant.LOGIN_USER_ID);
			if(Stringer.isNullOrEmpty(request.getSession().getAttribute(Constant.LOGIN_USER_ID4SYS))){
				throw new NoLogin4SysException();
			}
		}
		if(!Stringer.isNullOrEmpty(userUrl) ){//前台url
			removeSpecialId(request,Constant.LOGIN_USER_ID4SYS);
			if(Stringer.isNullOrEmpty(request.getSession().getAttribute(Constant.LOGIN_USER_ID))){
				throw new NoLoginException();
			}
		}
		return true;
	}
	private void removeSpecialId(HttpServletRequest request, String loginUserId) {
		Object attribute = request.getSession().getAttribute(loginUserId);
		if(!Stringer.isNullOrEmpty(attribute)){
			request.getSession().removeAttribute(attribute.toString());
		}
		
	}
	//初始化
	private void init() throws Exception {
		scanPackage(scanPath,packageNames);
		scanPackage(scanPath4Sys,packageNames4Sys);
//		logs.info("###>>> 找到全部constroller size:"+packageNames.size() +"后台controller size："+packageNames4Sys.size());
		Stringer.filterAndInstance(packageNames,handMapping);
		Stringer.filterAndInstance(packageNames4Sys,handMapping4Sys);
	}
	


	private void scanPackage(String basePackage, List<String> packageNames2) {
		URL url = this.getClass().getClassLoader().getResource(File.separator+Stringer.replaceChar(basePackage));
		String pathfile = url.getFile();
		
		File file = new File(pathfile);
		String[] list = file.list();
		for(String path:list){
			File eachFile=new File( pathfile + path);
			if(eachFile.isDirectory()){//eachFile
				String forPath=basePackage+ "." +eachFile.getName();
				if(forPath.equals(scanPath4Sys)){
//					logs.debug(forPath +"  >>>>:>>>> "+scanPath4Sys);
					continue;
				}
				scanPackage(forPath,packageNames2);
			}else{
				packageNames2.add(basePackage + "." + eachFile.getName());
			}
		}
		
	}
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	public String getScanPath() {
		return scanPath;
	}
	public void setScanPath(String scanPath) {
		this.scanPath = scanPath;
	}
	public String getScanPath4Sys() {
		return scanPath4Sys;
	}
	public void setScanPath4Sys(String scanPath4Sys) {
		this.scanPath4Sys = scanPath4Sys;
	}

	
}
