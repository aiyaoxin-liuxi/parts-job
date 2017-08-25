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
<script type="text/javascript" src="${basePath}/js/manager/userLogInfoList.js"></script>
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

function check(flag,id){
	$.ajax({
		url : "${basePath}/sys/enDisableUserLogInfo",
		type : "POST",
		data :{
			flag:flag,
			id:id
		},
		dataType : 'json',
		success : function(res) 
		{
			res=$.parseJSON(res);
			if(res.success){
				alert(res.errmsg);
				window.location.href='${basePath}/sys/userLogInfoList';
			}else{
				alert("异常："+res.errmsg);
			}
		}
	});
}
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
           				<th>用户手机</th>
           				<th>用户类型</th>
           				<th>用户状态</th>
           				<th>创建时间</th>
           				<th>操作</th>
           			</tr>	
           			<c:forEach var="item" items="${userLogInfoList}" varStatus="status">	
	           			<tr >
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.id }</td>
	           				<td>${item.mobile}</td>
	           				<td><c:if test="${item.userType =='01' }">兼职用户</c:if> 
	           				<c:if test="${item.userType =='02' }">企业用户</c:if></td>
	           				<td><c:if test="${item.userState =='01' }">启用</c:if> 
	           				<c:if test="${item.userState =='02' }">禁用</c:if></td>
	           				<td><fmt:formatDate value="${item.createdate}" type="date" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	           				<td><c:if test="${item.userState =='02' }">
									<a onclick="check('true','${item.id}')" style="cursor: pointer;">启用</a>
								</c:if>
								<c:if test="${item.userState =='01' }">
									<a onclick="check('false','${item.id}')" style="cursor: pointer;">禁用</a>
								</c:if>
	           				</td>
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
