<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript"	src="${basePath}/js/jquery-1.8.0.min.js"></script>
<!doctype html>
<script type="text/javascript">
$(document).keypress(function(event){
	if(event.keyCode==13){
			$("#login_login").click();
		}
});

function checkaccount(){
	$('.regist-tip').hide().find('i').empty();
	var account = $("#account").val();
	var temp =  /^((13[0-9])|(15[0-9])|147|(17[0-9])|(18[0-9]))[0-9]{8}$/;
	if(temp.test(account))
	{
		return true;
	}
	else
	{
		
		alert("请输入正确的手机号");
// 		$("#account").val('');
		return false;
	}
}


$(function(){
	$('#loginButton').on('click',function(){
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/compayinfo/login",
			type : "POST",
			data :$('#loginForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert("登陆成功");
// 					$('#registverifycode').val(res.data);
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});

</script>

<html>
<head>
<meta charset="utf-8">
<title>main.jsp</title>
</head>
<body>
	<li class="active com">找回密码</li>
	
	

</body>
</html>