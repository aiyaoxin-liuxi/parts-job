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
<script type="text/javascript" src="${basePath}/js/manager/workInfoList.js"></script>
<script type="text/javascript">

		function select(){
			window.location.href='${basePath}/sys/workInfoList?state='+$("#state").val();
		}
	</script>
</head>
<body>
<jsp:include page="/common/sys/header.jsp" />
<div class="jz_manage">
    <div class="mange_inner">
        <jsp:include page="/common/sys/leftSys.jsp" />
            <input type="hidden" name="cid" value="${companyInfo.cid}">
            
            
            <div class="jz-positioninner">
        	<div>
    			状态：<select id="state">
	    				<option value="">请选择</option>
		    			<c:forEach items="${stateList }" var="eachone">
							<option value="${ eachone.code}"  <c:if test="${ eachone.code eq state}">selected</c:if>  >${ eachone.cnName}</option>
						</c:forEach>
	    				<!-- <option value="">请选择</option>
	    				<option value="00">待审核</option>
	    				<option value="01">已发布</option>
	    				<option value="02">进行中</option>
	    				<option value="03">已完工</option>
	    				<option value="04">审核拒绝</option> -->
	    			</select>
    			&nbsp;&nbsp;<button class="btn" id="selectbtn" onclick="select()" type="button">查询</button>
    		</div><br>
    		
        <ul class="two">
           <table >
           			<tr style="height: 40px;">
           				<!-- <td>序号</td> -->
           				<th>名称</th>
           				<th>工作时间</th>
           				<th>招聘人数</th>
           				<th>工作地点</th>
           				<th>浏览量</th>
           				<th>结算方式</th>
           				<th>报名人数</th>
           				<th>录取人数</th>
           				<th>状态</th>
           				<th>报名状态</th>
           				<th>操作</th>
           			</tr>
           			<c:forEach var="item" items="${workList}" varStatus="status">	
	           			<tr >
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td onclick="toParttimeDetail_manage('${item.id}','${pageNo}','${pageSize }');">${item.workTitle }</td>
	           				<td><fmt:formatDate value="${item.workStartDate}" type="both" pattern="MM.dd"/>-<fmt:formatDate value="${item.workEndDate}" type="both" pattern="MM.dd"/>
                          	 &nbsp;&nbsp;${item.workTime}</td>
	           				<td>${item.peopleNum}</td>
	           				<td>${item.cityName}&nbsp;${item.areaName}</td>
	           				<td>${item.workInfoStatis.loadNum }</td>
	           				<td>${item.balanceTypeName}</td>
	           				<td>${item.workInfoStatis.applyNum}</td>
	           				<td>${item.workInfoStatis.employNum}</td>
	           				<td>
		           				<c:if test="${item.state =='00' }">审核</c:if> 
		           				<c:if test="${item.state =='01' }">已发布</c:if>
		           				<c:if test="${item.state =='02' }">进行中</c:if>
		           				<c:if test="${item.state =='03' }">已完工</c:if>
		           				<c:if test="${item.state =='04' }">审核拒绝</c:if>
	           				</td>
	           				<td>
		           				<c:if test="${item.applySwitch =='00' }">允许</c:if> 
		           				<c:if test="${item.applySwitch =='01' }">已停止</c:if>
	           				</td>
	           				<td>
	           					<c:if test="${item.applySwitch == '00' }"><a href="javascript:void(0);" onclick="clickNotAllowApply('${item.id }');">停止报名</a></c:if>
	           					<c:if test="${item.applySwitch == '01' }"><a href="javascript:void(0);" onclick="clickAllowApply('${item.id }');">允许报名</a></c:if>
	           				</td>
	           			</tr>
           			</c:forEach>
           		</table>
        </ul>
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
