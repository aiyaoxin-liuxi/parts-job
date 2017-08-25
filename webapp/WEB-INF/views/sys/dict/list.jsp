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
function del(id_){
	console.log("sssssssssssssssss");
	$.ajax({
		url : "${basePath}/manager/dict/del",
		type : "POST",
		data :{did:id_},
		dataType : 'json',
		success : function(res) 
		{
			res=$.parseJSON(res);
			console.log("结果："+JSON.stringify(res));
			if(res.success){
				alert(res.errmsg);
				location.reload();
			}else{
				alert("异常："+res.errmsg);
			}
		}
	});
}

function edit(id_){
			console.log("edit");
			location.href='${basePath}/manager/dict/edit?did='+id_;
			
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


<div class="jz_manage">
    <div class="mange_inner">
        <jsp:include page="/common/sys/leftSys.jsp" />
         <form action="" id="fillForm">
            <input type="hidden" name="cid" value="${companyInfo.cid}">
        <ul class="two">
           <table>
                       <tr>
                           <th>日期</th>
                           <th>key</th>
                           <th>value</th>
                           <th>dgroup</th>
                           <th>remark</th>
                           <th>操作</th>
                       </tr>
                       <c:if test="${appList.size() == 0 }">
							<tr><td colspan="4"><li style="text-align: center;"><p>未找到相关数据</p></li></td></tr>
						</c:if>
						<c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
							   <tr>
		                           <td ><fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
		                           <td >
		                           <!--  -->
		                          ${item.dkey }
		                           <!--  -->
		                           </td>
		                           <td>${item.dvalue }</td>
<!-- 		                           <td>微信</td> -->
		                           <td>${item.dgroup }</td>
								   <td>${item.remark }</td>
								   <td >
								   <span onclick="del('${item.did}')"> 删除</span>
								   &nbsp;&nbsp;
								   <span onclick="edit('${item.did}')"> 编辑</span>
								   </td>
		                       </tr>
							</c:forEach>
						</c:if>
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