<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ include file="/common/common.jsp"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<link type="text/css" rel="stylesheet" href="${basePath}/css/tab.css" />
	<link type="text/css" rel="stylesheet" href="${basePath}/css/swiper-3.4.1.min.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/swiper-3.4.1.jquery.min.js"></script>
	<script type="text/javascript" src="${basePath}/js/manager/applyInfoList.js"></script>
	<style type="text/css">
		.table{border:solid #add9c0; border-width:1px 0px 0px 1px;}
	</style>
	<script type="text/javascript">

		function select(){
			window.location.href='${basePath}/manager/workInfoList?state='+$("#state").val();
		}
	</script>
</head>
<body>
<jsp:include page="/common/managerheader.jsp" />
<jsp:include page="/common/sys/leftSys.jsp" />
   <div class="clear"></div>
<div class="jz-findplace">
    <div class="jz-position">
        <div class="jz-positioninner">
        	<div>
    			<%-- 状态：<select id="state">
	    				<option value="">请选择</option>
		    			<c:forEach items="${stateList }" var="eachone">
							<option value="${ eachone.code}"  <c:if test="${ eachone.code eq state}">selected</c:if>  >${ eachone.cnName}</option>
						</c:forEach>
	    			</select>
    			&nbsp;&nbsp;<button class="btn" id="selectbtn" onclick="select()" type="button">查询</button> --%>
    		</div><br>
           <ul class="positioninner_detail" id="showTable">
           		<table style="width: 100%;">
           			<tr style="text-align: left;height: 40px;">
           				<!-- <td>序号</td> -->
           				<th>用户id</th>
           				<th>用户姓名</th>
           				<th>申请时间</th>
           				<th>是否录用</th>
           				<th>兼职标题</th>
           				<th>兼职详情</th>
           				<th>兼职类型</th>
           				<th>状态</th> 
           			</tr>	
           			<c:forEach var="item" items="${applyInfoList}" varStatus="status">	
	           			<tr style="text-align: left;height: 30px;">
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.userId }</td>
	           				<td>${item.realname }</td>
	           				<td><fmt:formatDate value="${item.applyWorkDate }" type="date" pattern="yyyy-MM-dd"/></td>
	           				<td>${item.employStr}</td>
	           				<td>${item.workTitle}</td>
	           				<td>${item.workDetail}</td>
	           				<td>${item.jobTypeStr}</td>
	           				<td>${item.stateStr}</td>
	           			</tr>
           			</c:forEach>
           		</table>
           </ul>
        </div>
    </div>
	<input type="hidden" id="pageNo" value="${pageNo }"/>
    <input type="hidden" id="prePage" value="${prePage }"/>
    <input type="hidden" id="nextPage" value="${nextPage }"/>
    <div class="jz-pages">
        <ul class="pagesinner">
        	<li class="nav">
            <button <c:if test="${prePage == 0 }">disabled</c:if>>上一页</button></li>
            <c:forEach items="${navigatepageNums}" var="item" begin="0" end="4">
				<li><button>${item}</button></li>
			</c:forEach>
			<c:if test="${pages >= 6}">
				<li><button>...</button></li>
				<li><button>${pages}</button></li>
            </c:if>
            <li class="nav">
            <button <c:if test="${nextPage == 0 }">disabled</c:if>>下一页</button></li>
        </ul>
    </div>
</div>
<!--底部-->
<jsp:include page="/common/footer.jsp" />

</body>
</html>
