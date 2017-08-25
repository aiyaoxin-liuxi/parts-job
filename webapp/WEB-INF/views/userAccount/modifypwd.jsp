<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script src="${basePath}/js/jquery-1.11.3.js"></script>
<script src="${basePath}/js/laydate.js"></script>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
<title></title>
<script type="text/javascript">
$(function(){
	$('#updatepwdButton').on('click',function(){
		var logPassword = $("#logPassword").val();
		var newlogPassword = $("#newlogPassword").val();
		var renewlogPassword = $("#renewlogPassword").val();
		if(newlogPassword != renewlogPassword){
			alert("两次输入不一致");
			return false;
		}
		
		$.ajax({
			url : "${basePath}/userAccount/updatepwd",
			type : "POST",
			data :{
				newlogPassword:newlogPassword,
				renewlogPassword:renewlogPassword,
				mobile:$("#mobile").val(),
				logPassword:$("#logPassword").val()
			},
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					window.location.href="${basePath}/compayinfo/login";
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});

</script>
</head>
<body>
<jsp:include page="../../../common/header.jsp" />

<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>个人中心<span class="jianTou"></span>安全中心</li>
            </ul>
        </div>
        <div class="contain_inner_center">
             <ul class="jz-left">
                <li class="one onee">
                    <p>
                        <img src="${basePath}/image/jishiben.png" alt=""/>
                        <a href="">我的兼职</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/zhanghu.png" alt=""/>
                        <a href="${basePath}/personalInfo/fillpersonalInfo">我的信息</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/price.png" alt=""/>
                        <a href="${basePath}/userAccount/accountIndex">个人账户</a>
                    </p>
                </li>
                <li class="three tthree">
                    <p>
                        <img src="${basePath}/image/bangding2.png" alt=""/>
                        <a href="${basePath}/userAccount/bindwx" style="color: #fff;">账号绑定</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/anquan.png" alt=""/>
                        <a href="${basePath}/userAccount/safe">安全中心</a>
                         <span class="jianTou3"></span>
                    </p>
                </li>
            </ul>
            <ul class="jz-center">
                <li class="jz-title">
                    <p>|修改登录密码</p>
                </li>
                <li class="ja-news">
                   		<input type="hidden" name="mobile" id="mobile" value="${mobile}">
		                  	<p>
		                      <label for="name" style="margin-left: 36px;">原密码</label>
		                      <input type="password" id="logPassword" name="logPassword" placeholder="请输入原密码"/>
		                  </p>
		                  <p>
		                      <label for="number" style="margin-left: 36px;">新密码</label>
		                      <input type="password" id="newlogPassword" name="newlogPassword" placeholder="请输入新密码"/>
		                  </p>
		                  <p>
		                      <label>确认新密码</label>
		                      <input type="password" id="renewlogPassword" name="renewlogPassword" placeholder="请再次输入新密码"/>
		                  </p>
		                  <p class="tijiao" style="margin-left: 140px;margin-bottom: 400px;">
		                      <button id="updatepwdButton">确定</button>
		                  </p>
                </li>

            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

</body>
</html>