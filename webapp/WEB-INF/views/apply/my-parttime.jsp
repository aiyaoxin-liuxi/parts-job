<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/common/common.jsp"%>
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
	<script type="text/javascript" src="${basePath}/js/work/parttime_mannage.js"></script>
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
            <ul class="jz-left">
                <li class="one">
                    <p>
                        <img src="${basePath}/image/jishiben2.png" alt=""/>
                        我的兼职
                        <span class="jianTou3 "></span>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/zhanghu.png" alt=""/>
                        <a href="">我的信息</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/price.png" alt=""/>
                        <a href="">个人账户</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/bangding1.png" alt=""/>
                        <a href="">账号绑定</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/anquan.png" alt=""/>
                        <a href="">安全中心</a>
                    </p>
                </li>
            </ul>
            <ul class="jz-center">
                <li class="contain_banner">
                    <span class="onc_jz active_jz" id="a1"><b>已报名</b></span>
                    <span class="onc_jz" id="a2"><b>已录取</b></span>
                    <span class="onc_jz" id="a3"><b>进行中</b></span>
                    <span class="onc_jz" id="a4"><b>已完工</b></span>
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
		                                <span><img src="${basePath}/image/app_downloadCom.jpg" alt=""/></span>
		                            </p>
		
		                            <p class="jz-center">
		                                <span style="color: #000;font-size: 20px;">【审核录入】200元/天招聘兼职会电脑录入数据整理员人满为止</span>
		                        <span>
		                            <img src="${basePath}/image/data.png" alt=""/>
		                            工作时间：03.08-03.31&nbsp;09:00-18:00
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/person.png" alt=""/>
		                            招聘人数：<a style="color: #dab866;">82</a>/1200
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/position.png" alt=""/>
		                            工作地点：朝阳区
		                        </span>
		                        <span>
		                            <img src="${basePath}/image/jiesuan.png" alt=""/>
		                            结算方式：当天结算
		                            <a href="" style="color: #41a1fe;margin-left: 20px"><img src="${basePath}/image/zoxin.png" alt=""/>咨询商家</a>
		                        </span>
		                            </p>
		                            <p class="jz-right">
		                                <span style="font-size: 28px;margin-left: 15px;">200.00元/<a style="color: #dab866;font-size: 18px;">天</a></span>
		                                <span class="jz-check fabu_jz1"><a  style="color: #fff;">等待录用</a></span>
		                                <span class="jz-check fabu_jz2"><a  style="color: #fff;">取消报名</a></span>
		                            </p>
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
            <div class="jz-pages">
                <ul class="pagesinner" style="margin: 20px auto 20px">
                    <li class="nav">
                   		<button <c:if test="${prePage ==0 }">disabled</c:if>>上一页</button>
                   	</li>
                    <c:forEach items="${navigatepageNums}" var="item" begin="0" end="2">
                    	<li><button>${item}</button></li>
                    </c:forEach>
                    <c:if test="${pages > 3}">
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
            <span><a href="" style="color: #fff;">确定</a></span>
        </li>
    </ul>
</div>
</body>
</html>