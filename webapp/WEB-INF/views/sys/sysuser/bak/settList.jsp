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

</style>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<%-- <script type="text/javascript" src="${basePath}/js/jquery.js"></script> --%>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
function rechargeClick(){
// 	console.log("recharechargeClick  ");
	myloadUrl('.content3','${basePath}/account/rechargeList?date='+new Date().getTime());
}
function myloadUrl(class_,url_){
	$(class_).load(url_);
}

$(function(){
	$('.content2').hide();$('.content3').hide();$('.content4').hide();
	var logoImg='${companyInfo.logoImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":"${basePath}/"+logoImg});
		$('#logoImg').val(logoImg);
	}
	//switch
	$('.onc_jz').click(function(){
		$('.onc_jz').removeClass('active_jz');
		$(this).addClass('active_jz');
		$('.con_ban_inr').hide();
		$("."+$(this).data("id")).show();
		if($(this).data("id") == 'content3'){
			rechargeClick();
		}
	});
	///////////////
	$(".pagesinner1 button").click(function(){
		var pageReg='${pageReq}';
		var text = $(this).html();
		if(text == '上一页'){
			if($("#prePage").val() == '0'){
				alert("已经是第一页");
			} else {
				text = Number($("#pageNo").val()) - 1;
			}
		} else if(text == '下一页'){
			if($("#nextPage").val() == 0){
				alert("已经是最后一页");
			} else {
				text = Number($("#pageNo").val()) + 1;
			}
		}
		$("#pageNo").val(text);
// 		var state = $("#state").val();
		var pageNo = text;
		window.location.href=pageReg+"?pageNo="+pageNo;
	});
});

</script>
</script>
</head>
<body>

<jsp:include page="/common/sys/header.jsp" />

<div class="findjob-contain">
    <div class="findjob-contain_inner">
       
        <div class="contain_inner_center">
           <jsp:include page="/common/sys/leftSys.jsp" />
            <form action="" id="fillForm">
            <input type="hidden" name="cid" value="${companyInfo.cid}">
            <ul class="jz-center">
                <div class="con_ban_inr content content1">
                   <table>
                       <tr>
                           <th>日期</th>
                           <th>发布岗位</th>
                           <th>交易金额（元）</th>
<!--                            <th>交易方式</th> -->
                           <th>交易状态</th>
                       </tr>
                       <c:if test="${appList.size() == 0 }">
							<tr><td colspan="6"><li style="text-align: center;"><p>未找到相关数据</p></li></td></tr>
						</c:if>
						<c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
							   <tr>
							   <!-- onclick="getDetail(this,'${item.id}')" -->
		                           <td ><fmt:formatDate value="${item.settlementDate}" type="both" pattern="yyyy-MM-dd"/></td>
		                           <td >
		                           <!--  -->
		                           <c:if test="${empty item.workInfo}">
		                           -
		                           </c:if>
		                           <c:if test="${not empty item.workInfo}">
		                         	  <c:if test="${empty item.workInfo.jobName}">
		                         	  --
		                         	  </c:if>
		                         	  <c:if test="${not empty item.workInfo.jobName}">
		                         	  	<c:if test="${fn:length(item.workInfo.jobName)>=13}">
		                           		${fn:substring(item.workInfo.jobName,0,13)}...
		                           		</c:if>
		                           		<c:if test="${fn:length(item.workInfo.jobName)<13}">
		                           		${item.workInfo.jobName}
		                           		</c:if>
		                           	  </c:if>
		                           </c:if>
		                           <!--  -->
		                           </td>
		                           <td>${item.accountTotal}</td>
<!-- 		                           <td>微信</td> -->
		                           <td>${item.state}</td>
		
		                       </tr>
							</c:forEach>
						</c:if>
                   </table>
                   <!--sssssssssss  -->
                    
				    <div class="jz-pages">
				    <input type="hidden" id="pageNo" value="${pageNo }"/>
				    <input type="hidden" id="prePage" value="${prePage }"/>
				    <input type="hidden" id="nextPage" value="${nextPage }"/>
				        <ul class="pagesinner pagesinner1">
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
                   <div class="clear"></div>
                </div>
                <div class="clear"></div>
                
            </ul>
            </form>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 
<!-- <script src="js/jquery-1.11.3.js"></script> -->
<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<%-- <script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script> --%>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>