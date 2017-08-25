<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>

</head>
<body>
 <jsp:include page="../../../common/header.jsp" />

<div class="jz-login">
    <div class="jz-login_inner">
        <ul>
            <li class="login_head">
                <!--<span><b>企业注册</b></span>-->
                <span class="right">
                    <span><img src="${basePath}/image/login2.png" alt="" style="padding-left: 0;"/></span>
                    <span><a href="${path }/compayinfo/login">返回登录</a></span>
                </span>
            </li>

            <li class="login-register">
                <p style="font-size: 50px;">励志汪</p>
                <p>CHINAINTERNET</p>
                <p>找兼职招兼职，更加轻松，便捷</p>
            </li>
<%--             <li class="login_footer" onclick="location.href='${basePath}/personalInfo/toPersonalRegPage'"> --%>
			<a href="javascript:void(0);">
			<li class="login_footer" onclick="location.href='${basePath}/compayinfo/register?userType=01'">
               	 个人注册（找兼职）
            </li>
            </a>
            <a href="javascript:void(0);">
            <li class="login_footer" onclick="location.href='${basePath}/compayinfo/register?userType=02'">
                              企业注册（发布兼职）
            </li>
            </a>
        </ul>
    </div>
</div>

<!--底部-->
<div class="jz-footer clear">
    <div class="jz-footer-inner">
        <div class="jz-footer_1">
            <div class="jz-footer-left">
                <dl class="one">
                    <dt>关于励志汪</dt>
                    <dd><a href="">商务合作</a></dd>
                    <dd><a href="">加入我们</a></dd>
                    <dd><a href="">兼职新手指引</a></dd>
                    <dd><a href="">安全条款</a></dd>
                    <dd><a href="">服务协议</a></dd>
                    <dd><a href="">隐私保护</a></dd>
                </dl>
                <dl class="two">
                    <dt>APP下载</dt>
                    <dd><img src="${basePath}/image/pc_area1_2.png" alt=""/>&nbsp;<a href="">兼职版iphone</a></dd>
                    <dd><img src="${basePath}/image/pc_area1_3.png" alt=""/>&nbsp;<a href="">兼职版Android</a></dd>
                    <dd><img src="${basePath}/image/pc_area1_2.png" alt=""/>&nbsp;<a href="">商家版iphone</a></dd>
                    <dd><img src="${basePath}/image/pc_area1_3.png" alt=""/>&nbsp;<a href="">商家版Android</a></dd>
                </dl>
                <dl class="three">
                    <dt>客服服务</dt>
                    <dd>客服：400&nbsp;6800&nbsp;360</dd>
                    <dd>微信：106568088</dd>
                    <dd>电话：010&nbsp;5028&nbsp;0095/0096</dd>
                </dl>
                <dl class="four">
                    <dt>官方微博微信</dt>
                    <div>
                        <dd><img src="${basePath}/image/app_downloadCom.jpg" alt=""/><span>官方微博</span></dd>
                        <dd><img src="${basePath}/image/app_downloadCom.jpg" alt=""/><span>官方微信</span></dd>
                    </div>
                </dl>
            </div>
        </div>
        <p class="clear">Copyright © 北京中互联科技有限公司| 京ICP备 号</p>
    </div>
</div>
<script src="js/jquery-1.11.3.js"></script>
<script src="js/tab.js"></script>
</body>
</html>