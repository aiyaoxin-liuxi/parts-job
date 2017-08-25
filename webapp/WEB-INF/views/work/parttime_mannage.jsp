<%-- <%@ page language="java" pageEncoding="utf-8"%>
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
                <li>兼职管理<span class="jianTou"></span>普通兼职</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            <ul class="jz-left">
                <li class="one onee">
                    <p onclick="window.location.href='toReleaseJobInfo'">
                        <img src="${basePath}/image/fabu.png" alt=""/> 发布兼职
                    </p>
                </li>
                <li class="two">
                    <p style="background: #dab866;color: #fff;" onclick="window.location.href='parttimeMannage'">
                        <img src="${basePath}/image/guanli1.png" alt=""/> 兼职管理
                    </p>
                </li>
                <li class="three ">
                    <p>
                        <img src="${basePath}/image/newsqiye1.png" alt=""/>
                        <a href="" >企业信息</a>
                        <span class="jianTou3 "></span>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/zhanghu.png" alt=""/>
                        <a href="">账户信息</a>
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
                    <span class="onc_jz" id="a1"><b>审核</b></span>
                    <span class="onc_jz" id="a2"><b>已发布</b></span>
                    <span class="onc_jz" id="a3"><b>进行中</b></span>
                    <span class="onc_jz" id="a4"><b>已完工</b></span>
                </li>
                <div class="con_ban_inr content content1">
                    <ul>
                    	<c:if test="${workList.size() == 0 }">
							<li style="text-align: center;"><p>未找到相关数据</p></li>
						</c:if>
                    	<c:if test="${workList.size() > 0 }">
	                    	<c:forEach items="${workList }" var="item">
	                        <li>
	                            <p class="jz-left">
	                                <span><img src="${basePath}/image/app_downloadCom.jpg" alt=""/></span>
	                            </p>
	
	                            <p class="jz-center">
	                                <span style="color: #000;font-size: 20px;">${item.workTitle }</span>
	                        <span>
	                            <img src="${basePath}/image/data.png" alt=""/>
	                            	工作时间：
	                            	<fmt:formatDate value="${item.workStartDate}" type="both" pattern="MM.dd"/>-<fmt:formatDate value="${item.workEndDate}" type="both" pattern="MM.dd"/>
	                            	&nbsp;${item.workTime }
	                        </span>
	                        <span>
	                            <img src="${basePath}/image/person.png" alt=""/>
	                           	 	招聘人数：${item.peopleNum }
	                        </span>
	                        <span>
	                            <img src="${basePath}/image/price.png" alt=""/>
	                            	招聘价格：${item.money }元/${item.moneyTypeName }
	                        </span>
	                        <span>
	                            <img src="${basePath}/image/mianhsi.png" alt=""/>
	                            	是否面试：${item.interviewName }
	                        </span>
	                            </p>
	                            <p class="jz-right" style="vertical-align: top;right: 50px;">
	                                <span style="font-size: 28px;margin-left: 15px;">${item.stateName }</span>
	                            </p>
	                        </li>
	                        </c:forEach>
                        </c:if>
                    </ul>
                   
                </div>
            </ul>
            <input type="hidden" id="state" value=""/>
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
</body>
</html> --%>