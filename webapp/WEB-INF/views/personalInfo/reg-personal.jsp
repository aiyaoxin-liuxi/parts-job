<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${basePath}/css/tab.css" />
<script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${basePath}/js/tab.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<title>个人注册页</title>
<script type="text/javascript">
$(function(){
	$("#sendSms").click(function(){
		var mobile = $("#mobile").val();
		$.ajax( {    
		    url:'${basePath}/personalInfo/toSendSms',// 跳转到 action    
		    data:{
	             mobile : mobile  
		    },   
		    type:'POST',    
		    success:function(data) {
		    	data=$.parseJSON(data);
		        if(data.success){    
		        	alert(data.data);
		        	$('#validCode').val(data.data);
		        }else{    
		        	alert(data.errmsg);    
		        }    
		     }   
		});  
	});
	
	$("#btnOk").click(function(){
		if (!$('#isAgree').is(':checked')){
			alert('请同意使用条款');
		    return false;
		}
		$.ajax( {    
		    url:'${basePath}/personalInfo/toSavePersonalInfo',// 跳转到 action    
		    data :$('#regForm').serialize(),
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	data=$.parseJSON(data);
		        if(data.success){    
		        	window.location.href="${basePath}/compayinfo/login";
		        }else{    
		        	alert(data.errmsg);    
		        }    
		     },    
		     error : function() {
		    	 alert("异常！");    
		     }    
		});  
	});
});
	
</script>
</head>
<body>
<div class="jz_head">
    <div class="jz_top">
        <div class="jz_top-inner">
            <ul>
                <li class="jz_service">客服热线：<span style="color: #dab866;">4006 800 360（09:00-20：30）</span></li>
                <li class="jz_in"><a href="">手机兼职</a></li>
                <i></i>
                <li class="jz_in"><a href="">我要吐槽</a></li>
                <i></i>
                <li><img src="${basePath}/image/weibo.png" alt=""/><img src="${basePath}/image/weixin.png" alt=""/></li>
            </ul>
        </div>
    </div>
    <div class="jz_nav">
        <div class="jz_nav_inner">
            <ul>
                <li class="jz_nav_innerlogo"><img src="${basePath}/image/logo11.jpg" alt=""
                                                  style="width: 185px;position: relative;top:8px;"/></li>
                <li class="jz_link "><a href="">首页</a></li>
                <li class="jz_link active"><a href="">找兼职</a></li>
                <li class="jz_link"><a href="">发布兼职</a></li>
                <li class="jz_link"><a href="">APP下载</a></li>
                <li><img src="${basePath}/image/user_logo.png" alt=""
                         style="width: 20px;position: relative;top: 3px;left: -8px;"/>
                         <a href="${basePath}/compayinfo/login">登录</a>/<a
                        href="${basePath}/compayinfo/registerselect">注册</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="jz-login">
    <div class="jz-login_inner">
        <ul>
            <li class="login_head">
                <span><b>个人注册</b></span>
                <span class="right">
                    <span><img src="${basePath}/image/login2.png" alt="" style="padding-left: 0;"/></span>
                    <span><a href="${basePath}/compayinfo/login">登录</a></span>
                </span>
            </li>
            <li class="login_contain">
                <label for="tel"><img src="${basePath}/image/tel.png" alt="" style="top:3px"/></label>
                <input name="mobile" id="mobile" type="text" placeholder="请输入手机号"/>
            </li>
            <li class="login_contain">
                <label for="tel2"><img src="${basePath}/image/yanzheng.png" alt=""/></label>
                <input type="text" placeholder="请输入验证码" id="validCode" style="width: 140px;"/>
                <span id="sendSms">获取验证码</span>
            </li>
            <li class="login_contain">
                <label for="tel1"><img src="${basePath}/image/password.png" alt=""/></label>
                <input type="password" placeholder="密码由6-12位英文字母、数字组成" name="logPassword" id="tel1"/>
            </li>
            <li class="login_contain">
                <label for="tel3"><img src="${basePath}/image/yaoiqng.png" alt=""/></label>
                <input type="text" placeholder="请输入邀请码(选填)" id="tel3"/>
            </li>
            <li class="login-chec">
               <input type="checkbox" name="isAgree" id="isAgree" />
                <span>同意<a href="" style="color: #dab866">使用条款</a></span>
            </li>
            <li class="login_footer" id="btnOk">
            	注册
            </li>
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
</body>
</html>