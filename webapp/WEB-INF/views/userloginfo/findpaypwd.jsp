<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript">
$(function(){
$('#findpaypwdButton').on('click',function(){
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/userloginfo/findpaypwd",
			type : "POST",
			data :$('#findpaypwdForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					location.href=history.go(-1);
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});
function sendSMS(this_){
	//
	var textv=$(this_).text();
	if( textv != '重发(60)'){
		return ;
	}
	//
	var account = '${user.mobile}';
	$.ajax({
		url : "${basePath}/compayinfo/sendSMS?mobile="+account,
		type : "POST",
		success : function(res) 
		{
			res=$.parseJSON(res);
			if(res.success){
//					alert("请查收验证码,--目前是显示出来:"+res.data);
				alert(res.errmsg);
//					$('#registverifycode').val(res.data);
			}else{
				alert("异常："+res.errmsg);
				location.reload();
			}
		}
	});
}
//================
var wait=60;		
function time(o) {  
	var mobile=$.trim($('#mobile').val());

        if (wait == 0) {  
            o.removeAttribute("disabled");            
           
            $(o).text("点击获取")
            wait = 60;  
        } else {  
            o.setAttribute("disabled", true);
            $(o).text("重发(" + wait + ")"); 
            wait--;  
            setTimeout(function() {  
                time(o)  
            },  
            1000)  
        }  
    }  
//================

</script>
</head>
<body>

<jsp:include page="../../../common/header.jsp" />

<div class="findjob-contain">
    <div class="findjob-contain_inner">
<c:if test="${userType == '01' }">
	<!-- 个人 -->
	<jsp:include page="../personalInfo/common/headerNext.jsp" />
</c:if>
<c:if test="${userType == '02' }">
	<!--企业  -->
	<jsp:include page="../compayinfo/common/headerNext.jsp" />
</c:if>
        <div class="contain_inner_center">
	            <c:if test="${userType == '01' }">
				<!-- 个人 -->
					<jsp:include page="../personalInfo/common/left.jsp" />
				</c:if>
				<c:if test="${userType == '02' }">
					<!--企业  -->
					<jsp:include page="../compayinfo/common/left.jsp" />
				</c:if>
            <ul class="jz-center">
                <li class="jz-title">
                    <p>|找回支付密码</p>
                </li>
                <form action="" id="findpaypwdForm">
                <li class="ja-news">
                    <p style="font-size: 20px;"><b>您的励志汪账户名：${user.mobile }</b></p>
                    <p>
                        <label for="name" style="width: 100px;display: inline-block;">登录密码</label>
                        <input type="password" id="logPassword" name="logPassword" placeholder="请输入登录密码"/>
                    </p>
                    <p>
                        <label for="number" style="width: 100px;display: inline-block;">短信验证码</label>
                        <input type="text" id="number"  name="smscode" placeholder="请输入短信验证码"/>
                        <span class="yanzheng" onclick='time(this);sendSMS(this);' >获取验证码</span>
                    </p>
                    <p>
                        <label style="width: 100px;display: inline-block;">新密码</label>
                        <input name="newpaypwd" type="password" placeholder="请输入新密码"/>
                    </p>
                    <p>
                        <label style="width: 100px;display: inline-block;">确认新密码</label>
                        <input name="renewpaypwd" type="password" placeholder="请再次输入新密码"/>
                    </p>
                    <p class="tijiao" style="margin-left: 140px;margin-bottom: 400px;">
                        <button  class="btn" id="findpaypwdButton" type="button">确定</button>
                    </p>
                </li>
                </form>

            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
</body>
</html>