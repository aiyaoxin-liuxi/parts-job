<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<script type="text/javascript">
$(".pagesinner2 button").click(function(){
	var text = $(this).html();
	if(text == '上一页'){
		if($("#prePage1").val() == '0'){
			alert("已经是第一页");
		} else {
			text = Number($("#pageNo1").val()) - 1;
		}
	} else if(text == '下一页'){
		if($("#nextPage1").val() == 0){
			alert("已经是最后一页");
		} else {
			text = Number($("#pageNo1").val()) + 1;
		}
	}
	$("#pageNo1").val(text);
	var pageNo = text;
	var url='${basePath}/account/settDetail?cid=${cid}&workDay=${workDay}&pageNo='+pageNo+'&date='+new Date().getTime();
	myloadUrl('.content4',url);
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
                           <th>实际应付此人工资金额</th>
                           <th>交易状态${appList.size()}</th>
                           <th>备注</th>
                       </tr>
                       <c:if test="${appList.size() == 0 }">
							<tr><li style="text-align: center;"><p>未找到相关数据</p></li></tr>
					   </c:if>
                       <c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
							   <tr>
		                           <td><fmt:formatDate value="${item.workDay}" type="both" pattern="yyyy-MM-dd"/></td>
		                           <td >
		                           <!--  -->
		                          ${item.accountPay}
		                           <!--  -->
		                           </td>
		                           <td>${item.state}</td>
		                           <td>${item.remark}</td>
		                       </tr>
							</c:forEach>
						</c:if>
                   </table>
                   <!--sssssssssss  -->
                    <input type="hidden" id="pageNo1" value="${pageNo }"/>
				    <input type="hidden" id="prePage1" value="${prePage }"/>
				    <input type="hidden" id="nextPage1" value="${nextPage }"/>
				    <div class="jz-pages">
				        <ul class="pagesinner pagesinner2">
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