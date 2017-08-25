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
<script type="text/javascript" src="${basePath}/js/upload/ajaxfileupload.js"></script>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript">
$(document).keypress(function(event){
	if(event.keyCode==13){
			$("#goUrlImmediate").click();
		}
});
function goUrl(this_){
// 	alert("cddc");
// 	console.log(window.location);
	location.href='${basePath}/sys/sysuser/login';
}
</script>
<script type="text/javascript">
// function submitForm(){
// 	notifyDownForm.submit();
// 	goUrl
// }

var i;
function show1(){
	var remainSecond=$('#remainSecond').text();
// 	console.log("xxxxxx"+remainSecond);
	if( parseInt(remainSecond) == 0){
// 		console.log("xxxxxx"+remainSecond);
		clearInterval(i);
// 		submitForm();
		goUrl();
		return ;
	}
	$('#remainSecond').text( --remainSecond);
}

$(function(){
// 	$('#userSmsCode').hide();
	i =setInterval(show1,1000);
	
});


</script>
</head>
<body>

<jsp:include page="/common/header.jsp" />



<div class="jz_wrong">
    <p><img src="${basePath}/image/no_in1.png" alt=""/></p>
    <p>(^0^)即将飞翔登陆页面(后台) ,还剩<span id="remainSecond" style="font-size:50px;font-weight:bold;color:red">1</span>秒</p>
    <p>用户没有登陆或者登陆已经失效(后台),如果飞不过去,<a href="#" style="color: #0044cc;font-size: 22px;text-decoration:underline;" id="goUrlImmediate" onclick="goUrl(this);"> 
        手动登陆</a>
    </p>
</div>
<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
</body>
</html>