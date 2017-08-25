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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="col-md-6 col-md-offset-1"><div class="input-group"><span class="input-group-addon"><span class="glyphicon glyphicon-exclamation-sign"></span></span><input type="text" id="verifycodeModal" name="verifycode" class="form-control" placeholder="请输入验证码"></div></div>

<img id="codeimageModal" class="img-responsive" src="/kaptcha.jpg?v=8930307916" onclick="changecodeModal()">

<a type="button" class="btn btn-primary sureSendVer">确定</a>
</body>
</html>