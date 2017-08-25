<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<script type="text/javascript">
$(".pagesinner33 button").click(function(){
	
	var pageReg='${pageReq}';
	var text = $(this).html();
	if(text == '上一页'){
		if($("#prePage3").val() == '0'){
			alert("已经是第一页");
		} else {
			text = Number($("#pageNo3").val()) - 1;
		}
	} else if(text == '下一页'){
		if($("#nextPage3").val() == 0){
			alert("已经是最后一页");
		} else {
			text = Number($("#pageNo3").val()) + 1;
		}
	}
	$("#pageNo3").val(text);
	var pageNo = text;
	var url='${basePath}/account/'+pageReg+'?cid=${cid}&workDay=${workDay}&pageNo='+pageNo+'&date='+new Date().getTime();
	
	myloadUrl('.content3',url);
});
</script>
</script>
</head>
<body>
<c:if test="${not empty errMsg }">${errMsg }</c:if>
<c:if test="${empty errMsg }">
					<table>
                       <tr>
                           <th>日期</th>
                           <th>交易明细</th>
                           <th>交易账户</th>
                           <th>交易金额（元）</th>
<!--                            <th>交易方式</th> -->
                           <th>交易状态</th>
                       </tr>
                       <c:if test="${appList.size() == 0 }">
							<tr><td colspan="5">未找到相关数据</td></tr>
					   </c:if>
					    <c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
								<tr>
		                           <td><fmt:formatDate value="${item.transTime}" type="both" pattern="yyyy-MM-dd"/></td>
		                           <td>${item.type}</td>
		                           <td>${item.oppoNo }</td>
		                           <td>${item.amount }</td>
<!-- 		                           <td>微信</td> -->
		                           <td>${item.state }</td>
		                       </tr>
							</c:forEach>
						</c:if>
                       
                   </table>
                   <!--sssssssssss  -->
                    <input type="hidden" id="pageNo3" value="${pageNo }"/>
				    <input type="hidden" id="prePage3" value="${prePage }"/>
				    <input type="hidden" id="nextPage3" value="${nextPage }"/>
				    <div class="jz-pages">
				        <ul class="pagesinner pagesinner33">
				        	<li class="nav">
				            <button <c:if test="${prePage == 0 }">disabled</c:if>>上一页</button></li>
				            <c:forEach items="${navigatepageNums}" var="item" begin="0" end="4">
								<li><button>${item}</button></li>
							</c:forEach>
							<c:if test="${pages > 3}">
								<li><button>...</button></li>
								<li><button>${pages}</button></li>
				            </c:if>
				            <li class="nav">
				            <button <c:if test="${nextPage == 0 }">disabled</c:if>>下一页</button></li>
				        </ul>
				    </div>
                   <!-- eeeeeeeeeeee -->
</c:if>
</body>
</html>