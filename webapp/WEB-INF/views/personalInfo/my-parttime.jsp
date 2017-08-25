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
	<script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
	<script type="text/javascript" src="${basePath}/js/personalInfo/my-parttime.js"></script>
	
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
                <li>个人中心<span class="jianTou"></span>我的兼职<span class="jianTou"></span>已报名</li>
            </ul>
        </div>
        <div class="contain_inner_center">
        	<jsp:include page="../personalInfo/common/left.jsp" />
            <ul class="jz-center">
                <li class="contain_banner">
                    <a href="javascript:void(0);"><span class="onc_jz" id="a1"><b>已报名</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a2"><b>已录取</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a3"><b>进行中</b></span></a>
                    <a href="javascript:void(0);"><span class="onc_jz" id="a4"><b>已完工</b></span></a>
                </li>
                <div class="con_ban_inr content content1">
                    <ul >
                    	<c:if test="${appList.size() == 0 }">
							<li style="text-align: center;"><p>未找到相关数据</p></li>
						</c:if>
						<c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
		                        <li>
		                           <p class="jz-left">
				                    	<c:if test="${not empty item.companyInfo.logoImg}">
				                    		<span><img src="${item.companyInfo.logoImg}" alt=""/></span>
				                    	</c:if>
				                    	<c:if test="${empty item.companyInfo.logoImg}">
				                    		<span><img src="${basePath}/image/app_downloadCom.jpg" alt=""/></span>
				                    	</c:if>
				                        
				                    </p>
		                            <p class="jz-center">
		                                <span style="color: #000;font-size: 20px;" onclick="toParttimeDetail('${item.workId}');">${item.workInfo.workTitle }</span>
		                        <span>
		                            <img src="${basePath}/image/data.png" alt=""/>
		                            	工作时间：
		                            	<fmt:formatDate value="${item.applyWorkDate}" type="both" pattern="MM.dd"/>
	                            		&nbsp;${item.workInfo.workTime }
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/person.png" alt=""/>
		                            	招聘人数：<a style="color: #dab866;">${item.workInfoStatis.applyNum}</a>/<fmt:parseNumber value="${item.workInfo.peopleNum * item.workInfo.workdayNum}" />
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/position.png" alt=""/>
		                           		 工作地点：${item.workInfo.cityName }&nbsp;${item.workInfo.areaName }&nbsp;${item.workInfo.addressDetail }
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/jiesuan.png" alt=""/>
		                           		 结算方式：${item.workInfo.balanceTypeName}
		                            <%-- <a href="" style="color: #41a1fe;margin-left: 20px"><img src="${basePath}/image/zoxin.png" alt=""/>咨询商家</a> --%>
		                        </span>
		                        	<c:if test="${queryState == '00' or queryState == '01'}">
		                            </p>
		                            <p class="jz-right">
		                                <span style="font-size: 28px;margin-left: 15px;">${item.workInfo.money }元/<a style="color: #dab866;font-size: 18px;">${item.workInfo.moneyTypeName }</a></span>
		                                <c:if test="${item.employ == '00'}">
			                                <span class="jz-check fabu_jz1"><a  style="color: #fff;">等待录用</a></span>
		                                	<span class="jz-check fabu_jz2" style="cursor: pointer;"><a style="color: #fff;" onclick="cleanEnroll('${item.id}','${item.applyWorkDate}','${item.employ}','${item.workInfo.allowChooseDate}','${item.workInfo.workStartDate}','${item.workInfo.workEndDate}');">取消报名</a></span>
			                                
		                                </c:if>
		                                <c:if test="${item.employ == '01'}">
			                                <span class="jz-check fabu_jz1"><a  style="color: #fff;">已录用</a></span>
			                                <!-- <span class="jz-check fabu_jz2"><a  style="color: #fff;">取消报名</a></span> -->
		                                </c:if>
		                            </p>
		                            </c:if>
		                            <c:if test="${queryState == '02'}">
			                           	<p class="jz-right">
			                                <span style="font-size: 28px;margin-left: 15px;">进行中...</span>
			                            </p>
		                            </c:if>
		                            <c:if test="${queryState == '03'}">
		                            	<c:if test="${item.employ == '01'}">
				                           	<p class="jz-right">
				                                <span style="font-size: 28px;margin-left: 15px;">已完工</span>
				                            </p>
			                            </c:if>
			                            <c:if test="${item.employ == '02'}">
				                           	<p class="jz-right">
				                                <span style="font-size: 28px;margin-left: 15px;">未录用</span>
				                            </p>
			                            </c:if>
			                            <c:if test="${item.employ == '03'}">
				                           	<p class="jz-right">
				                                <span style="font-size: 28px;margin-left: 15px;">已取消</span>
				                            </p>
			                            </c:if>
		                            </c:if>
		                        </li>
	                        </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </ul>
            <input type="hidden" id="employ" value=""/>
            <input type="hidden" id="pageNo" value="${pageNo }"/>
            <input type="hidden" id="prePage" value="${prePage }"/>
            <input type="hidden" id="nextPage" value="${nextPage }"/>
            <input type="hidden" id="queryState" value="${queryState }"/>
            <div class="jz-pages">
                <ul class="pagesinner" style="margin: 20px auto 20px">
                    <li class="nav">
                   		<button <c:if test="${prePage == 0 }">disabled</c:if>>上一页</button>
                   	</li>
                    <c:forEach items="${navigatepageNums}" var="item" begin="0" end="4">
                    	<li><button>${item}</button></li>
                    </c:forEach>
                    <c:if test="${pages > 6}">
	                    <li><button>...</button></li>
	                    <li><button>${pages}</button></li>
                    </c:if>
                    <li class="nav">
                    <button <c:if test="${nextPage ==0 }">disabled</c:if>>下一页</button></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!--底部-->
<jsp:include page="/common/footer.jsp" />
<!--弹出框-->
<div class="shadow hide1"></div>

<!--底部弹出框-->
<div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu4">
    <ul>
        <li class="one">
            <img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
        </li>
        <li class="six">
            <img src="${basePath}/image/tishi.png" alt=""/>兼职页面已开始无法取消，请刷新页面后查看！
        </li>
        <li class="seven">
            <a href="" style="color: #fff;"><span>确定</span></a>
        </li>
    </ul>
</div>
</body>
</html>