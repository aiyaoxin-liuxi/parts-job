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
<%-- <script type="text/javascript" src="${basePath}/js/upload/ajaxfileupload.js"></script> --%>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>

</head>
<body>

<jsp:include page="/common/header.jsp" />

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
                    <p>|安全中心</p>
                </li>
                <div class="con_ban_inr">
                    <ul class="safe">
                        <li >
                            <p class="one">
                                <span><img src="${basePath}/image/anquan3.png" alt=""/></span>
                                <span>保护中</span>
                            </p>
                            <p class="two">登录密码</p>
                            <p class="three">登录励志汪账户时需要输入的密码</p>
                            <p><a href="${basePath}/userloginfo/updatepwd" style="color: #dab866;">修改</a></p>
                        </li>
                        <li >
                            <p class="one">
                                <span><img src="${basePath}/image/anquan3.png" alt=""/></span>
                                <span>保护中</span>
                            </p>
                            <p class="two">支付密码</p>
                            <p class="three">提现或付款时输入，保护资金安全</p>
                            <p>
                                <a href="${basePath}/userloginfo/paypwd" style="color: #dab866;">修改</a>&nbsp;
                                <a href="${basePath}/userloginfo/findpaypwd" style="color: #dab866;">找回</a>
                            </p>
                        </li>
                    </ul>
                </div>

            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>


<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<script src="${basePath}/js/laydate.js"></script>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
</body>
</html>