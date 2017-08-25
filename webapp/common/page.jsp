<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    				<input type="hidden" id="pageNo" value="${pageNo }"/>
				    <input type="hidden" id="prePage" value="${prePage }"/>
				    <input type="hidden" id="nextPage" value="${nextPage }"/>
				        <ul class="pagesinner">
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