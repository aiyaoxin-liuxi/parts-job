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
	$('#updatepwdButton').on('click',function(){
		
		var newlogPassword = $("#newlogPassword").val();
		var renewlogPassword = $("#renewlogPassword").val();
		//logPassword
		var logPassword = $("#logPassword").val();
		if(! ( newlogPassword == newlogPassword)){
			//alert("两次输入不一致");
			//return ;
		}
		
		$.ajax({
			url : "${basePath}/userloginfo/updatepwd",
			type : "POST",
			data :$('#updatepwdForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
// 				console.log("结果："+JSON.stringify(res));
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
                    <p>|修改登录密码</p>
                </li>
                <form action="" id="updatepwdForm">
                <input type="hidden" name="mobile" value="${mobile}">
                <li class="ja-news">
                    <p>
                        <label for="name" style="margin-left: 36px;">原密码</label>
                        <input type="password" id="logPassword" name="logPassword"  placeholder="请输入原密码"/>
                    </p>
                    <p>
                        <label for="number" style="margin-left: 36px;">新密码</label>
                        <input type="password" id="newlogPassword" name="newlogPassword" placeholder="请输入新密码"/>
                    </p>
                    <p>
                        <label>确认新密码</label>
                        <input type="password" id="renewlogPassword" name="renewlogPassword" placeholder="请再次输入新密码"/>
                    </p>
                    <p class="tijiao"    style="margin-left: 140px;margin-bottom: 400px;">
                        <button class="btn" id="updatepwdButton" type="button" 	>确定</button>
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