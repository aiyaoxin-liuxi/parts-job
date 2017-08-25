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

<script type="text/javascript" src="${basePath}/js/manager/cashList.js"></script>
<script type="text/javascript">
		function check(flag,accountId){
			$.ajax({
				url : "${basePath}/sys/cashOrderCheck",
				type : "POST",
				data :{
					flag:flag,
					accountId:accountId
				},
				dataType : 'json',
				success : function(res) 
				{
					res=$.parseJSON(res);
					if(res.success){
						alert(res.errmsg);
						window.location.href='${basePath}/sys/cashOrderList';
					}else{
						alert("异常："+res.errmsg);
						window.location.href='${basePath}/sys/cashOrderList';
					}
				}
			});
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
           <table>
           			<tr>
           				<!-- <td>序号</td> -->
           				<th>用户id</th>
           				<th>提现金额</th>
           				<th>提现时间</th>
           				<th>银行名称</th>
           				<th>操作</th>
           			</tr>	
           			<c:forEach var="item" items="${accountFlowList}" varStatus="status">	
	           			<tr>
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.userId }</td>
	           				<td>${item.amount}</td>
	           				<td><fmt:formatDate value="${item.transDate}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	           				<td>${item.oppoBank}</td>
	           				<td><a onclick="check('true','${item.accountId}')" style="cursor: pointer;">通过</a>&nbsp;&nbsp;
	           				<a onclick="check('false','${item.accountId}')" style="cursor: pointer;">拒绝</a></td>
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
