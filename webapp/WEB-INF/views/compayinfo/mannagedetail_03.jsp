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
	<script type="text/javascript" src="${basePath}/js/compayinfo/mannagedetail_03.js"></script>
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
                    <ul class="fa_detail">
                        <li>
                            <p>
                                <span style="font-size: 22px;"><b>${workInfo.workTitle }</b></span>
                                <span class="jz-jiesuan"><a href="">${workInfo.balanceTypeName}</a></span>
                            </p>
                            <p>
                                <span >工作时间：<span style="color: #dab866;"><fmt:formatDate value="${workInfo.workStartDate }" type="date" pattern="MM.dd"/>-<fmt:formatDate value="${workInfo.workEndDate }" type="date" pattern="MM.dd"/>&nbsp;${workInfo.workTime }</span></span>
                                <span style="margin-left: 30px;">招聘人数：<span style="color: #dab866;">${employCount }/${workInfo.peopleNum }</span></span>
                            </p>
                            <p >
                                <span>招聘价格： <span style="color: #dab866;">${workInfo.workInfoStatis.applyNum}</span>/<fmt:parseNumber value="${workInfo.peopleNum * workInfo.workdayNum}}" /></span>
                                <span>状态：<span style="color: #dab866;">${workInfo.stateName }</span></span>
                            </p>
                        </li>
                        <li style="padding-top: 20px;">
                            <span style="color: #000;"><b>兼职日期：</b></span>
                            <span style="color: #dab866;">
							<c:forEach items="${dateList }" var="item">
                            <b onclick="selectDate('${workInfo.id }','<fmt:formatDate value="${item }" type="date" pattern="yyyy-MM-dd"/>');">
								<fmt:formatDate value="${item }" type="date" pattern="MM.dd"/>&nbsp;
							</b>
							</c:forEach>
						</span>
                        </li>
                        <input type="text" id="state" value="${state }"/>
                        <li style="border-bottom: none">
                            <p style="color: #dab866;font-size: 20px;">|出勤状态</p>
                            <p class="tiao"><span>兼职人员</span></p>
                        </li>
                        <li style="border-bottom: none" class="audit">
                            <!--<p style="text-align: center;"><img src="image/logo11.jpg" alt=""/></p>-->
                            <!--<p style="width: 100%;text-align: center;color: #000;">暂无报名人员</p>-->
                            <c:forEach items="${appList }" var="item">
	                            <div>
	                               <p class="left">
				                    	<c:if test="${not empty item.userInfo.headImg}">
				                    		<span><img src="${item.userInfo.headImg}" alt=""/></span>
				                    	</c:if>
				                    	<c:if test="${empty item.userInfo.headImg}">
				                    		<span><img src="${basePath}/image/app_downloadCom.jpg" alt=""/></span>
				                    	</c:if>
				                        
				                    </p>
	                                <p class="center">
	                                    <span style="color: #11bd22;">
	                                        <b style="color: #000;font-size: 20px;margin-right: 15px">${item.userInfo.realname }</b><img src="${basePath}/image/co-confirmation.png" alt=""/> 已认证
	                                    </span>
	                                    <span style="color: #000;">${item.userInfo.mobile }</span>
	                                    <span >
	                                    	&nbsp;
	                                        <%-- <img src="${basePath}/image/jianli.png" alt="" style="width: 11px"/> <a href="" style="color: #00a0e9">兼职简历</a> --%>
	                                    </span>
	                                    <span >
	                                    	&nbsp;
	                                        <%-- <img src="${basePath}/image/kaoqi.png" alt="" style="width:14px"/> <a href="" style="color: #00a0e9">历史考勤</a> --%>
	                                    </span>
	                                </p>
	                            </div>
	                        </c:forEach>
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