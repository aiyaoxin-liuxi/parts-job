<%@page import="com.zhl.job.pojo.enums.company.CompanyType"%>
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
	<script type="text/javascript" src="${basePath}/js/manager/companyList.js"></script>
	<style type="text/css">
		.table{border:solid #add9c0; border-width:1px 0px 0px 1px;}
	</style>
	<script type="text/javascript">
		function check(flag,cid){
			$.ajax({
				url : "${basePath}/manager/companyContractCheck",
				type : "POST",
				data :{
					flag:flag,
					cid:cid
				},
				dataType : 'json',
				success : function(res) 
				{
					res=$.parseJSON(res);
					if(res.success){
						alert(res.errmsg);
						window.location.href='${basePath}/manager/companyContractList';
					}else{
						alert("异常："+res.errmsg);
					}
				}
			});
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
           <ul class="positioninner_detail" id="showTable">
           		<table style="width: 100%;">
           			<tr style="text-align: left;height: 40px;">
           				<!-- <td>序号</td> -->
           				<th>企业名称</th>
           				<th>企业性质</th>
           				<th>企业规模</th>
           				<th>详细地址</th>
           				<th>公司简介</th>
           				<th>营业执照号</th>
           				<th>营业执照图片</th>
           				<td>操作</td>
           			</tr>	
           			<c:forEach var="item" items="${companyList}" varStatus="status">	
	           			<tr style="text-align: left;height: 30px;">
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.companyName }</td>
	           				<td>${item.companyTypeStr}</td>
	           				<td>${item.companyPeopleNum}人</td>
	           				<td>${item.province}&nbsp;${item.city}&nbsp;${item.addressDetail}</td>
	           				<td>${item.companyDetail}</td>
	           				<td>${item.companyidNo}</td>
	           				<td><img alt="" src="${item.companyidImg }"> </td>
	           				<td><a onclick="check('true','${item.cid}')" style="cursor: pointer;">通过</a>&nbsp;&nbsp;
	           				<a onclick="check('false','${item.cid}')" style="cursor: pointer;">拒绝</a></td>
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
