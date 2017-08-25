package com.zhl.job.controller.common;


import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zhl.job.pojo.common.ResponseEntity;
import com.zhl.job.resovlver.exception.NoLogin4SysException;
import com.zhl.job.resovlver.exception.NoLoginException;
import com.zhl.job.util.BigDecimalUtil;
import com.zhl.job.util.Constant;
import com.zhl.job.util.Stringer;


/**
 * 
  * @ClassName: BaseController
  * @author zhaotisheng 
  * @date 2017年3月14日 上午9:45:27
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class BaseController {

    
    private Logger logs = LoggerFactory.getLogger(BaseController.class);
    
    protected Object error(String errorCode, String errorMessage){
        return new ResponseEntity(errorCode, errorMessage).toJson();
    }

    protected Object error(String errorMessage){
        return new ResponseEntity(null, errorMessage).toJson();
    }
    protected Object success(Object data){
        return new ResponseEntity(data).toJson();
    }
    
    
    protected boolean isNullOrEmpty(Object obj) {
        return Stringer.isNullOrEmpty(obj);
    }

    
    
    /**
     * @author by  Apr 13, 2015
     *
     * @desc 获取webapp完整URL. e.g http://www.abc.com/app/a/b/c?a=b&c=d...
     * @param request
     * @return
     */
    protected final String getRequestURL(HttpServletRequest request) {

        if (request == null) {
            return "";
        }

        String url = "";
        url = "http://" + request.getServerName() // 服务器地址
        // + ":"
        // + request.getServerPort() //端口号
                + request.getContextPath() // 项目名称
                + request.getServletPath(); // 请求页面或其他地址

        try {

            // 参数
            Enumeration<?> names = request.getParameterNames();

            int i = 0;
            String queryString = request.getQueryString();
            if (null != queryString && !"".equals(queryString) && (!queryString.equals("null"))) {
                url = url + "?" + request.getQueryString();
                i++;
            }

            if (names != null) {
                while (names.hasMoreElements()) {
                    if (i == 0) {
                        url = url + "?";
                    }

                    String name = (String) names.nextElement();
                    if (url.indexOf(name) < 0) {
                        url = url + "&";

                        String value = request.getParameter(name);
                        if (value == null) {
                            value = "";
                        }
                        url = url + name + "=" + value;
                        i++;
                    }
                    // java.net.URLEncoder.encode(url, "ISO-8859");
                }
            }

            // String enUrl = java.net.URLEncoder.encode(url, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return url;
    }
    
    
    /**
     * 非空验证
     * @param obj
     * @return true 通过验证； false：未通过验证
     */
    public boolean verifyParam(Object obj){
        if(obj instanceof String){
            String str = obj.toString();
            return (null != str && !"".equals(str.trim()) && str.trim().length() != 0 && !obj.toString().trim().equals("null")) ? true : false;
        } else if(obj instanceof BigDecimal){
            if(null != obj && !"".equals(obj)){
                // 大于0
                return (BigDecimalUtil.BigDecimalGreaterThanAndEqual((BigDecimal)obj, new BigDecimal(0))) ? true : false;
            }
        } else {
            return (null != obj && !"".equals(obj)) ? true : false;
        }
        return false;
    }
    
    /**
     * BigDecimal空值填充保留两位小数（四舍五入）
     * @param obj
     * @return
     */
    public BigDecimal BigDecimalNotNull_UP(BigDecimal b){
        return b == null ? new BigDecimal(0) : b.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * BigDecimal空值填充
     * @param obj
     * @return
     */
    public BigDecimal BigDecimalNotNull(BigDecimal b){
        return b == null ? new BigDecimal(0) : b;
    }
    
    /**
     * String空值填充
     * @param obj
     * @return
     */
    public String StringNotNull(String str){
        return str == null ? "" : str;
    }
    
    /**
     * 固定长度判定
     * @param str       字符串
     * @return
     */
    public String lengthCheck(String str){
        return lengthCheck(str, 13);
    }
    /**
     * 长度判定
     * @param str       字符串
     * @param length    规定长度
     * @return
     */
    public String lengthCheck(String str, int length){
        if(str.length() > length){
            return "金额长度超限";
        }
        return null;
    }
   

    public void checkLoginStatus(HttpServletRequest request) throws NoLoginException {
        Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_MOBILE);
        if(Stringer.isNullOrEmpty(attribute)){
            logs.debug("###>>>>没有登陆");
            throw new NoLoginException();
        }
        
    }
    public void checkLoginStatus4Sys(HttpServletRequest request) throws NoLogin4SysException {
        Object attribute = request.getSession().getAttribute(Constant.LOGIN_USER_ID4SYS);
        if(Stringer.isNullOrEmpty(attribute)){
            logs.debug("###>>>>没有登陆(后台)");
            throw new NoLogin4SysException();
        }
        
    }

    //前台 公司和个人的userid
    public String getLoginUserId(HttpServletRequest request) {
        return getAttr(request,Constant.LOGIN_USER_ID);
    }
    //后台系统的userId
    public String getLoginUserId4Sys(HttpServletRequest request) {
        return getAttr(request,Constant.LOGIN_USER_ID4SYS);
    }

    public String getLoginUserType(HttpServletRequest request) {
        return getAttr(request,Constant.LOGIN_USER_TYPE);
    }
    
    public String getLoginUserMobile(HttpServletRequest request) {
        return getAttr(request,Constant.LOGIN_USER_MOBILE);
    }
    //登陆的企业的主键 (注意也可能为空，因为注册的时候么有企业表的信息)
    public Object getloginCompanyinfoId(HttpServletRequest request) {
        return request.getSession().getAttribute(Constant.LOGIN_COMPANYINFO_ID);
    }
    public Object getLoginPersonId(HttpServletRequest request){
        return request.getSession().getAttribute(Constant.LOGIN_PERSON_ID);
    }
    
    private String getAttr(HttpServletRequest request,String key) {
        Object attribute = request.getSession().getAttribute(key);
        if(Stringer.isNullOrEmpty(attribute)){
            logs.debug("###>>>>没有登陆");
            throw new RuntimeException("没有登陆");
        }
        return attribute.toString();
    }
    
    public void alertError(ModelMap model, String errMsg){
        alertError(model, "errMsg", errMsg);
    }
    
    public void alertError(ModelMap model, String errCode, String errMsg){
        model.addAttribute(errCode, "<script type=\"text/javascript\">alert('"+errMsg+"')</script>");
    }
    
    public void alertError(RedirectAttributes attr, String errMsg){
        alertError(attr, "errMsg", errMsg);
    }
    public void alertError(RedirectAttributes attr, String errCode, String errMsg){
        attr.addFlashAttribute(errCode, "<script type=\"text/javascript\">alert('"+errMsg+"')</script>");
    }
    


}
