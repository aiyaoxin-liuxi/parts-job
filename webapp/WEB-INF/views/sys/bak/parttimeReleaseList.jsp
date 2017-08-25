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
	<script type="text/javascript" src="${basePath}/js/manager/parttimeList.js"></script>
	<style type="text/css">
		.table{border:solid #add9c0; border-width:1px 0px 0px 1px;}
	</style>
	<script type="text/javascript">
		function check(flag,id){
			$.ajax({
				url : "${basePath}/manager/parttimeReleaseCheck",
				type : "POST",
				data :{
					flag:flag,
					parttimeId:id
				},
				dataType : 'json',
				success : function(res) 
				{
					res=$.parseJSON(res);
					if(res.success){
						alert(res.errmsg);
						window.location.href="${basePath}/manager/parttimeReleaseList";
					}else{
						alert("异常："+res.errmsg);
					}
				}
			});
		}
	</script>
</head>
<body>

 <jsp:include page="/common/sys/leftSys.jsp" />
   <div class="clear"></div>
<div class="jz-findplace">
    <div class="jz-position">
        <div class="jz-positioninner">
           <ul class="positioninner_detail" id="showTable">
           		<table style="width: 100%;">
           			<tr style="text-align: left;height: 40px;">
           				<!-- <td>序号</td> -->
           				<th>名称</th>
           				<th>工作时间</th>
           				<th>招聘人数</th>
           				<th>工作地点</th>
           				<th>结算方式</th>
           				<th>操作</th>
           			</tr>	
           			<c:forEach var="item" items="${workList}" varStatus="status">	
	           			<tr style="text-align: left;height: 30px;">
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.workTitle }</td>
	           				<td><fmt:formatDate value="${item.workStartDate}" type="both" pattern="MM.dd"/>-<fmt:formatDate value="${item.workEndDate}" type="both" pattern="MM.dd"/>
                          	 &nbsp;&nbsp;${item.workTime}</td>
	           				<td>${item.peopleNum}</td>
	           				<td>${item.cityName}&nbsp;${item.areaName}&nbsp;${item.addressDetail}</td>
	           				<td>${item.balanceTypeName}</td>
	           				<td><a onclick="check('true','${item.id}')" style="cursor: pointer;">通过</a>&nbsp;
	           				<a onclick="check('false','${item.id}')" style="cursor: pointer;">拒绝</a></td>
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
