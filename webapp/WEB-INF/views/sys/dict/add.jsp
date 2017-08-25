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
$(function(){
	$('#dictButton').on('click',function(){
		
		console.log("1,dict add");
// 		checkaccount();
		$.ajax({
			url : "${basePath}/sys/dict/add",
			type : "POST",
			data :$('#dictForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
					location.href='${basePath}/sys/dict/list';
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
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
            <ul class="jz-center">
           <li>
           <form action="" id="dictForm">
<input type="hidden"  name="did" class="form-control" value="${dict.did}" >


	key:<input type="text"  name="dkey" class="form-control" value="${dict.dkey}" >
	<br><br>
	value:<input type="text"  name="dvalue" class="form-control" value="${dict.dvalue}" placeholder="请输入dvalue" >
	<br><br>
	
	dorder:<input type="text"  name="dorder" value="${dict.dorder}" class="form-control" placeholder="请输入dorder" >
	<br><br>
	
	dgroup:<input type="text"  name="dgroup" value="${dict.dgroup}"  class="form-control" placeholder="请输入dvalue" >
	<br><br>
	rsv1:<input type="text"  name="rsv1"  value="${dict.rsv1}" class="form-control" placeholder="请输入rsv1" >
	<br><br>
	rsv2:<input type="text"  name="rsv2" value="${dict.rsv2}"  class="form-control" placeholder="请输入rsv2" >
	<br><br>
	<!--state  	private String remark;-->
	state:<input type="text"  name="state" value="${dict.state}" class="form-control" placeholder="请输入state" >
	<br><br>
	
	dtype:<input type="text"  name="dtype"  value="${dict.dtype}" class="form-control" placeholder="请输入dtype" >
	<br><br>
	remark:<input type="text"  name="remark" value="${dict.remark}" class="form-control" placeholder="remark" >
	<br><br>
	

	<input type="button" id="dictButton" value="提交"/> 
				
	</form>
           </li>
            </ul>
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