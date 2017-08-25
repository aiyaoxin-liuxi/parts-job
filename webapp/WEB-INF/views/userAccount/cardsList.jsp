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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<title>银行卡管理</title>
<script type="text/javascript">
$(function(){
});
function unBind(cardId){
	if(confirm("确定删除该银行卡?")){
		$.ajax({
			url : "${basePath}/userAccount/unBind",
			type : "POST",
			data :{id:cardId},
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					location.reload();
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	}else{
		
	}
}	
</script>

</head>
<body>
	<div>
		<c:forEach items="${cardList }" var="card">
			<div style="background-color: lightgray;width: 150px;height: 60px;">
				${card.bankname }
				${card.cardNo }
				<br>
				<a href="javascript:void(0);" onclick="unBind('${card.id}')">删除</a>
			</div>
		</c:forEach>
		<br>
		<div style="background-color: lightgray;width: 150px;height: 60px;">
			<a href="${basePath}/userAccount/bindcard">去绑定银行卡</a>
		</div>
	</div>
</body>
</html>