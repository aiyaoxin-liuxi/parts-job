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
<script type="text/javascript" src="${basePath}/js/manager/companyList.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".dianji,.guanbi").on("click",function(){
        $(".jz-tanchu,.shadow").addClass("hide1");
    })
    
});

		function check(flag,cid){
			$.ajax({
				url : "${basePath}/sys/companyContractCheck",
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
						window.location.href='${basePath}/sys/companyContractList';
					}else{
						alert("异常："+res.errmsg);
					}
				}
			});
		}
		
		function clickShowImg(companyidNo, url){
			$(".jz-tanchu,.shadow").removeClass("hide1");
			$("#userSetCompanyidNo").html(companyidNo);
			$("#showImg").attr("src", url);
			
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
           				<th>企业名称</th>
           				<th>联系手机号</th>
           				<th>企业性质</th>
           				<th>企业规模</th>
           				<th>详细地址</th>
           				<th>公司简介</th>
           				<th>营业执照号</th>
           				<th>营业执照图片</th>
           				<th>状态</th>
           				<th>操作</th>
           			</tr>	
           			<c:forEach var="item" items="${companyList}" varStatus="status">	
	           			<tr>
	           				<!-- <td><input type="checkbox" name="" /></td> -->
	           				<td>${item.companyName }</td>
	           				<td>${item.mobile }</td>
	           				<td>${item.companyTypeStr}</td>
	           				<td>${item.companyPeopleNum}人</td>
	           				<td>${item.province}&nbsp;${item.city}&nbsp;${item.addressDetail}</td>
	           				<td>${item.companyDetail}</td>
	           				<td>${item.companyidNo}</td>
	           				<td onclick="clickShowImg('${item.companyidNo}','${item.companyidImg }');"><img alt="" src="${item.companyidImg }" width="14px" height="12px"></td>
	           				<td>${item.state}</td>
	           				<td><a onclick="check('true','${item.cid}')" style="cursor: pointer;">通过</a>&nbsp;&nbsp;
	           				<a onclick="check('false','${item.cid}')" style="cursor: pointer;">拒绝</a></td>
	           			</tr>
           			</c:forEach>
           		</table>
        </ul>
        </form>
    </div>
</div>
<!--弹出框-->
<div class="shadow hide1"></div>
<div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu_2" style="width:550px ;height: 400px;">
    <ul>
    	<li class="one">
        	<img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
        </li>
        <li></li>
        <li>
			用户填写的营业执照号：<a href="javascript:void(0);" id="userSetCompanyidNo"></a>
        </li><br/>
        <li>
            <img alt="" id="showImg" src="" width="500px" height="300px">
        </li>
    </ul>
</div>

<div class="jz-pages">
    <jsp:include page="/common/page.jsp" />
</div>
 </form>
<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 
</body>
</html>
