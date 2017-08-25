<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/common/common.jsp"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" href="${basePath}/css/tab.css"/>
    <link rel="stylesheet" href="${basePath}/css/laydate.css"/>
	<script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/laydate.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/tab.js"></script>
	<script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
	<script type="text/javascript" src="${basePath}/js/compayinfo/mannagedetail_00.js"></script>
</head>
<body>
<jsp:include page="/common/header.jsp" />
<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>兼职管理<span class="jianTou"></span>普通兼职</li>
            </ul>
        </div>
        <div class="contain_inner_center">
        	<jsp:include page="../compayinfo/common/left.jsp" />
            <ul class="jz-center">
                <li class="contain_banner">
                    <a href="javascript:void(0);"><span class="onc_jz" id="a1"><b>审核</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a2"><b>已发布</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a3"><b>进行中</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a4"><b>已完工</b></span></a>
                </li>
                <div class="con_ban_inr content content1">
                    <p style="color:#dab866;font-size: 20px;"><b>|兼职详情</b></p>
                    <ul class="mannage_one">
                        <li>职位名称：<span style="color:#dab866;font-size: 18px;">${workInfo.workTitle }</span></li>
                        <li>职位编号：<span>${workInfo.id }</span></li>
                        <li>状态：<span style=" color:#dab866;">${workInfo.stateName }</span></li>
                        <li>发布日期：<span><fmt:formatDate value="${workInfo.createdate }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></span></li>
                    </ul>
                    <p style="color:#dab866;font-size: 20px;"><b>|兼职信息</b></p>
                    <ul class="mannage_one">
                        <li>工作类型：<span>${workInfo.jobTypeName }</span></li>
                        <li>招聘人次： <span style="color: #dab866;">${workInfo.workInfoStatis.applyNum}</span>/<fmt:parseNumber value="${workInfo.peopleNum * workInfo.workdayNum}}" /></li>
                        <li>招聘价格：<span style=" color:#dab866;">${workInfo.money }元/${workInfo.moneyTypeName }</span></li>
                        <li>兼职日期：<span><fmt:formatDate value="${workInfo.workStartDate }" type="date" pattern="MM.dd"/>-<fmt:formatDate value="${workInfo.workEndDate }" type="date" pattern="MM.dd"/>&nbsp;${workInfo.workTime }</span></li>
                        <li>可选日期：<span>${workInfo.allowChooseDateStr }</span></li>
                        <li>工作餐：<span>${workInfo.workMealName }</span></li>
                        <li>性别：<span>${workInfo.sexRequireName }</span></li>
                        <li>结算方式：<span>${workInfo.balanceTypeName}</span></li>
                        <li>联系方式：<span>${workInfo.contactsMobile}&nbsp;${workInfo.contacts}</span></li>
                        <li>地址：<span>${workInfo.cityName }&nbsp;${workInfo.areaName }&nbsp;${workInfo.addressDetail }</span></li>
                    </ul>
                    <p style="color:#dab866;font-size: 20px;"><b>|职位描述</b></p>
                    <ul class="mannage_one">
                        <li>
                           	 岗位要求：
                        </li>
                        <li style="padding-left: 20px;border-bottom: dashed 1px #ddd;margin-bottom: 20px;padding-bottom: 20px; ">
                            ${workInfo.require }
                        </li>
                        <li>
                           	 工作内容：
                        </li>
                        <li style="padding-left: 20px">
                           	${workInfo.workDetail }
                        </li>
                    </ul>
                </div>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!--底部-->
<jsp:include page="/common/footer.jsp" />
</body>
</html>