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
<script type="text/javascript" src="${basePath}/js/imgupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/imgupload.js"></script>
<script type="text/javascript" src="${basePath}/js/userinfo/calender.js"></script>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<title>个人账户</title>
<script type="text/javascript">
$(function(){
// 	console.log("");
	$("#sendSms").click(function(){
		var mobile = $("#mobile").val();
		$.ajax( {    
		    url:'${basePath}/personalInfo/toSendSms',// 跳转到 action    
		    data:{
	             mobile : mobile  
		    },   
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	data=$.parseJSON(data);
		        if(data.success){    
		        	alert(data.data);
		        	$('#validCode').val(data.data);
		        }else{    
		        	alert(data.errmsg);    
		        }    
		     },    
		     error : function() {
		    	 alert("异常！");    
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
		            alert(data.errmsg);    
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
<jsp:include page="../../../common/header.jsp" />
<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>个人中心<span class="jianTou"></span>个人账户</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            <jsp:include page="../personalInfo/common/left.jsp" />
            <ul class="jz-center">
                 <jsp:include page="../personalInfo/common/account-title.jsp" />
                <li class="jz-title">
                    <p>|个人账户</p>
                </li>
                <li class="jz-paymoney" style="margin-bottom: 200px;">
                	<form action="">
                		<input type="hidden" name="id" id="id" value="${userLogInfo.id }"/>
                		<p class="h">
	                      <span><b>总余额</b></span>
	                      <span class="t"><b style="color: #dab866;">&yen <c:if test="${empty userLogInfo.total }">0</c:if>${userLogInfo.total }</b></span>
<%-- 	                      <span ><a href="${basePath}/userAccount/accountCash?id=${userLogInfo.id}" class="act" style="color: #000;">提现</a></span> --%>
	                 	  <span >&nbsp;</span>
	                 	</p>
	                 	<p class="h">
	                      <span><b>可用余额</b></span>
	                      <span class="t"><b style="color: #dab866;">&yen <c:if test="${empty userLogInfo.useAmount }">0</c:if>${userLogInfo.useAmount }</b></span>
	                      <span ><a href="${basePath}/userAccount/accountCash?id=${userLogInfo.id}" class="act" style="color: #000;">提现</a></span>
	                 	</p>
	                 	<p class="h">
	                      <span><b>冻结余额</b></span>
	                      <span class="t"><b style="color: #dab866;">&yen <c:if test="${empty userLogInfo.noUseAmount }">0</c:if>${userLogInfo.noUseAmount }</b></span>
<%-- 	                      <span ><a href="${basePath}/userAccount/accountCash?id=${userLogInfo.id}" class="act" style="color: #000;">提现</a></span> --%>
	                 	 <span >&nbsp;</span>
	                 	</p>
                	</form>
                    
                   <!--  <p class="h">
                        <span><b>可用余额</b></span>
                        <span class="t"><b style="color: #dab866;">0 张</b></span>
                        <span><a href=""  class="act" style="color: #000;">查看</a></span>
                    </p> -->
                </li>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<jsp:include page="../../../common/footer.jsp" />
</body>
</html>