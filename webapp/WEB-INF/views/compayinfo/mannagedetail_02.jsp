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
	<script type="text/javascript" src="${basePath}/js/compayinfo/mannagedetail_02.js"></script>
	<jsp:useBean id="now" class="java.util.Date" /> 
	<style type="text/css">
	.findjob-contain_inner .contain_inner_center ul.jz-center div.con_ban_inr ul{
	    width: 90%;
	    padding: 20px 10px;
	    background: #fcfcfc;
	    position: relative;
	}
	</style>
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
                                <span style="margin-left: 30px;">招聘人数： <span style="color: #dab866;">${workInfo.workInfoStatis.applyNum}</span>/<fmt:parseNumber value="${workInfo.peopleNum * workInfo.workdayNum}}" /></span>
                            </p>
                            <p >
                                <span>招聘价格：<span style="color: #dab866;">${workInfo.money }元/${workInfo.moneyTypeName }</span></span>
                                <span>状态：<span style="color: #dab866;">${workInfo.stateName }</span></span>
                            </p>
                        </li>
                        <li style="padding-top: 20px;">
                            <span style="color: #000;"><b>兼职日期：</b></span>
                            <span>
							<c:forEach items="${dateList }" var="item">
	                    		<fmt:formatDate value="${nowDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="showDateT"/>
	                    		<fmt:formatDate value="${item }" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="showDateT2"/>
								<c:if test="${showDateT == showDateT2 }">
		                            <b style="cursor: pointer;color: #fff;background:#dab866; display: inline-block;border-radius:5px; width: 70px;height: 30px;line-height:30px;text-align:center;padding-left: 15px;" onclick="selectDate('${workInfo.id }','<fmt:formatDate value="${item }" type="date" pattern="yyyy-MM-dd"/>');">
										<fmt:formatDate value="${item }" type="date" pattern="MM.dd"/>&nbsp;
									</b>
								</c:if>
								<c:if test="${showDateT != showDateT2 }">
									<b style="cursor: pointer;color: #dab866;padding-left: 15px;" onclick="selectDate('${workInfo.id }','<fmt:formatDate value="${item }" type="date" pattern="yyyy-MM-dd"/>');">
										<fmt:formatDate value="${item }" type="date" pattern="MM.dd"/>&nbsp;
									</b>
								</c:if>
							</c:forEach>
						</span>
                        </li>
                        <input type="hidden" id="state" value="${state }"/>
                        <li style="border-bottom: none">
                            <p style="color: #dab866;font-size: 20px;">|出勤状态</p>
                            <p class="tiao"><span>兼职人员</span></p>
                        </li>
                        <form action="${basePath }/settlement/settlementTransfer" method="post" id="testForm" onsubmit="return subCheck()">
                        <input type="hidden" id="urlString" name="urlString" value="${urlString}"/>
                        <li style="border-bottom: none" class="audit">
                            <!--<p style="text-align: center;"><img src="image/logo11.jpg" alt=""/></p>-->
                            <!--<p style="width: 100%;text-align: center;color: #000;">暂无报名人员</p>-->
                            <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="nowT"/>
	                    	<fmt:formatDate value="${nowDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="nowDateT"/>
	                    	<c:if test="${nowDateT <= nowT}">
	                    		<c:forEach items="${appList }" var="item" varStatus="index">
	                    			<c:if test="${item.state != '02' }">
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
			                                    <span>
			                                    	实发工资：<b id="showAccountPay_${index.index}">${workInfo.money }</b>元/${workInfo.moneyTypeName }
			                                    	<a href="javascript:void(0);" onclick="updateMoney('${index.index}','${workInfo.money }');">修改</a>
			                                    	<input type="hidden" id="accountPay_${index.index}" name="slList[${index.index}].accountPay" value="${workInfo.money }"/>
			                                    	<input type="hidden" id="applyId_${index.index}" name="slList[${index.index}].applyId" value="${item.id }"/>
			                                    </span>
			                                    <span >
			                                        <img src="${basePath}/image/kaoqi.png" alt="" style="width:14px"/> <a href="" style="color: #00a0e9">历史考勤</a>
			                                    </span>
			                                </p>
			                                <p class="right">
			                                	<span><textarea rows="3" cols="20" name="slList[${index.index}].appComment"></textarea></span>
			                                </p>
			                            </div>
		                            </c:if>
	                    			<c:if test="${item.state == '02' }">
			                            <div>
			                               <p class="left">
						                    	<c:if test="${not empty item.userInfo.headImg}">
						                    		<span><img src="${basePath}/${item.userInfo.headImg}" alt=""/></span>
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
			                                    <span>
			                                    	实发工资：<b id="showAccountPay_${index.index}">${item.settlementInfoLog.accountPay }</b>元/${workInfo.moneyTypeName }
			                                    	<input type="hidden" id="accountPay_${index.index}" name="slList[${index.index}].accountPay" value="${workInfo.money }"/>
			                                    	<input type="hidden" id="applyId_${index.index}" name="slList[${index.index}].applyId" value="${item.id }"/>
			                                    </span>
			                                    <span >
			                                        <img src="${basePath}/image/kaoqi.png" alt="" style="width:14px"/> <a href="" style="color: #00a0e9">历史考勤</a>
			                                    </span>
			                                </p>
			                                <p class="right">
			                                	<span>已结算</span>
			                                </p>
			                            </div>
		                            </c:if>
		                        </c:forEach>
		                        <c:if test="${appList.size() > 0 }">
			                        <div>
			                        	<span class="right">
			                        		<a href="javascript:void(0);" style="background: #dadada;" onclick="sub();">全部结算</a>
			                       		</span>
			                        </div>
		                        </c:if>
	                    	</c:if>
                            <c:if test="${nowDateT > nowT}">
                            	<c:forEach items="${appList }" var="item">
		                            <div>
		                                <p class="left">
					                    	<c:if test="${not empty item.userInfo.headImg}">
					                    		<span><img src="${basePath}/${item.userInfo.headImg}" alt=""/></span>
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
			                                <span>
												<c:if test="${ not empty item.attendedStr }">
			                                    	<span class="jz-jiesuan-lx"><a>${item.attendedStr }</a></span>
		                                    	</c:if>
		                                    	<c:if test="${ empty item.attendedStr }">&nbsp;</c:if>
		                                    	
		                                        <%-- <img src="${basePath}/image/jianli.png" alt="" style="width: 11px"/> <a href="" style="color: #00a0e9">兼职简历</a> --%>
		                                    </span>
		                                    <span >
		                                   		&nbsp;
		                                        <%-- <img src="${basePath}/image/kaoqi.png" alt="" style="width:14px"/> <a href="" style="color: #00a0e9">历史考勤</a> --%>
		                                    </span>
		                                </p>
		                                <c:if test="${item.employ == '00'}">
			                                <p class="right">
			                                    <a href="javascript:void(0);" style="background: #dab866;" onclick="employment('${item.id}');">录用</a>
			                                    <a href="javascript:void(0);" style="background: #dadada;" onclick="refuse('${item.id}');">拒绝</a>
			                                </p>
		                                </c:if>
		                                <c:if test="${item.employ != '00' }">
			                                <p class="right">
			                                	${item.employStr }
			                                </p>
		                                </c:if>
		                            </div>
		                        </c:forEach>
		                        
                            </c:if>
                        </li>
                        <c:if test="${nowDateT > nowT}">
                        	<input type="hidden" id="workId" value="${workInfo.id }"/>
				            <input type="hidden" id="pageNo" value="${pageNo }"/>
						    <input type="hidden" id="prePage" value="${prePage }"/>
						    <input type="hidden" id="nextPage" value="${nextPage }"/>
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
						            	<button <c:if test="${nextPage == 0 }">disabled</c:if>>下一页</button>
						            </li>
				                </ul>
				            </div>
                        </c:if>
                         <div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu_5">
						    <ul>
						        <li class="one">
						            <img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
						        </li>
						        <li class="five" style="font-size: 22px;"><b>支付密码</b></li>
						        <li class="five"><input type="password" id="pwd" name="pwd" style="width: 100px"  placeholder="请输入支付密码"/></li>
						        <li class="four">
						            <span class="che dianji">关闭</span>
						            <input class="ched dianji" type="submit" id="sub" value="支付"/>
						        </li>
						    </ul>
						</div>
                        </form>
                    </ul>
                </div>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>
<!--底部-->
<jsp:include page="/common/footer.jsp" />
<div class="shadow hide1"></div>
<div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu_2">
    <ul>
        <li class="one">
            <img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
        </li>
        <li class="five" style="font-size: 22px;"><b>实发工资</b></li>
        <li class="five"><input type="text" id="showMoney" style="width: 60px" value=""/>&nbsp;元</li>
        <li class="four">
            <a href="javascript:void(0);"><span class="che dianji">关闭</span></a>
            <a href="javascript:void(0);"><span class="ched dianji">确定</span></a>
        </li>
    </ul>
</div>
</body>
</html>