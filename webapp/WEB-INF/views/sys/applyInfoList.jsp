<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<style type="text/css">
.jz_manage .mange_inner ul.two table tr td{
    border-right: 1px solid #d3d3d3;
    border-bottom: 1px solid #d3d3d3;
    /*background: #f0efef;*/
    line-height: 2;
    vertical-align: middle;
}
</style>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<%-- <script type="text/javascript" src="${basePath}/js/jquery.js"></script> --%>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath}/js/manager/applyInfoList.js"></script>
<script type="text/javascript">

		function select(){
			window.location.href='${basePath}/sys/workInfoList?state='+$("#state").val();
		}
	</script>
</script>
</head>
<body>
<jsp:include page="/common/sys/header.jsp" />
<div class="jz_manage">
    <div class="mange_inner">
        <jsp:include page="/common/sys/leftSys.jsp" />
         <form action="" id="fillForm">
            <input type="hidden" name="cid" value="${companyInfo.cid}">
        <ul class="two">
           <table >
           			<tr >
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
	           			<tr>
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
        </form>
    </div>
</div>

<div class="jz-pages">
    <jsp:include page="/common/page.jsp" />
</div>
 </form>
<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 
</body>
</html>
