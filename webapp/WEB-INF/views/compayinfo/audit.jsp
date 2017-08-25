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
                <li>企业信息<span class="jianTou"></span>完善资料</li>
            </ul>
        </div>
        <div class="contain_inner_center">
             <jsp:include page="../compayinfo/common/left.jsp" />
            <ul class="jz-center">
                <div class="con_ban_inr">
                    <ul class="qiye">
                        <li><b>企业签约，签约完成即可发布兼职！</b></li>
                        <li>
                            <span class="four">完善资料</span>
                            <span class="four">企业认证</span>
                            <span class="five">审核信息</span>
                            <span class="three">签约完成</span>
                        </li>
                    </ul>
                </div>
                <li class="jz-title">
                    <p>|企业认证</p>
                </li>
                <div class="con_ban_inr">
                    <ul class="qiyehe">
                       <li><img src="${basePath}/image/sun.png" alt=""/></li>
                       <li style="color: #dab866;font-size: 28px;"><b>签约信息审核中，请耐心等待！</b></li>
                       <li>我们将在1个工作日内以短信形式通知您审核结果！</li>
                    </ul>
                </div>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>


<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
</body>
</html>