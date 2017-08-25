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
	$('#paypwdButton').on('click',function(){
		$.ajax({
			url : "${basePath}/userloginfo/paypwd",
			type : "POST",
			data :$('#paypwdForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					//location.href=history.go(-1);//先这样写，等待需求
 					window.location.href = _basePath + "/userloginfo/paypwd";
// 					window.location.href="${basePath}/compayinfo/login";
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
                    <p>|<c:if test="${modifyFlag eq 0 }">设置</c:if><c:if test="${modifyFlag eq 1 }">修改</c:if>支付密码</p>
                </li>
               <form action="" id="paypwdForm">
	
				<input type="hidden" name="modifyFlag" value="${modifyFlag}">
				<input type="hidden" name="id" value="${id}">
                <li class="ja-news">
                
                 <p>
                <label for="name" style="margin-left: 36px;"> <c:if test="${modifyFlag eq 1 }">原</c:if>支付密码</label>
                
                <input type="password" id="payPassword" name="payPassword"  placeholder="请输入原密码" value="">
                 </p>
				<c:if test="${modifyFlag eq 0 }">
					<p>
					<label>重复支付密码</label>
					<input type="password" id="rePayPassword" name="rePayPassword" placeholder="请输入新密码" value="">
                    </p>
				</c:if> 
				
				<c:if test="${modifyFlag eq 1 }">
					<p>
					<label for="name" style="margin-left: 36px;" >新支付密码</label>
					<input type="password" id="newPayPassword" name="newPayPassword" placeholder="请输入新支付密码" value="">
					</p>	
					<p>
					<label>重复新支付密码</label>
					<input type="password" id="reNewPayPassword" name="reNewPayPassword" placeholder="请重复新支付密码" value="">
					</p>
				</c:if>
					<p class="tijiao"    style="margin-left: 140px;margin-bottom: 400px;">
                        <button class="btn" id="paypwdButton" type="button" 	>提交</button>
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