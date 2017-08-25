<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
$(function(){
	var logoImg='${companyInfo.logoImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":"${basePath}/"+logoImg});
	}
});

</script>
<script type="text/javascript">
//通过接口获取银行信息
function unBind(cardId){
	$.ajax({
		url : "${basePath}/card/unBind",
		type : "POST",
		data :{id:cardId},
		dataType : 'json',
		success : function(res) 
		{
			res=$.parseJSON(res);
			if(res.success){
				alert(res.errmsg);
				location.reload();
			}else{
				alert("异常："+res.errmsg);
			}
		}
	});
}
$(function(){
	$('#bindcardButton').on('click',function(){
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/compayinfo/bindcard",
			type : "POST",
			data :$('#bindcardForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					location.href=history.go(-1);
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});

</script>

</head>
<body>

<jsp:include page="../../../common/header.jsp" />
<div class="findjob-contain">
    <div class="findjob-contain_inner">

<c:if test="${userType == '01' }">
	<!-- 个人 -->
	<jsp:include page="../personalInfo/common/headerNext.jsp" />
</c:if>
<c:if test="${userType == '02' }">
	<!--企业  -->
	<jsp:include page="../compayinfo/common/headerNext.jsp" />
</c:if>

        <div class="contain_inner_center">
        
            	<c:if test="${userType == '01' }">
					<!-- 个人 -->
					<jsp:include page="../personalInfo/common/left.jsp" />
				</c:if>
				<c:if test="${userType == '02' }">
					<!--企业  -->
					<jsp:include page="../compayinfo/common/left.jsp" />
				</c:if>


            <ul class="jz-center">
            <!-- 44444 -->
                <c:if test="${userType == '01' }">
					<!-- 个人 -->
					<jsp:include page="../personalInfo/common/account-title.jsp" />
				</c:if>
				<c:if test="${userType == '02' }">
					<!--企业  -->
					<jsp:include page="../compayinfo/common/account-title.jsp" />
				</c:if>
				
                <li class="jz-title">
                    <p>|银行卡管理</p>
                </li>
                <div class="con_ban_inr">
                    <ul class="jz-bank">
                    
                    	<c:if test="${not empty cards }">
							<c:forEach items="${cards}" var ="eachOne">
							<li style="background: #eb6876;color: #fff;">
	                            <p>
	                                <span style="font-size: 18px;">${eachOne.bankname }</span>
	<!--                                 <span style="font-size: 12px;">浙江</span> -->
	<!--                                 <span style="font-size: 12px;">杭州</span> -->
	                                <span style="position: absolute;right: 10px;"><a href="#" onclick="unBind('${eachOne.id}')">删除</a></span>
	                            </p>
	                            <p><span>${eachOne.cardNo }</span></p>
	                        </li>
<%--${eachOne.bankname }&nbsp;&nbsp; ${eachOne.cardNo }&nbsp;&nbsp;   
<a href="#" onclick="unBind('${eachOne.id}')">解绑</a><br> --%>
							</c:forEach>
						</c:if>
						
                        <li style="background: #ddd;color: #8e8e8e;">
                            <p style="padding: 50px 88px;">
                                <span><img src="${basePath}/image/add.png" alt=""/></span>
                                <span><a href="${basePath}/compayinfo/bindcard">添加银行卡</a></span>
                            </p>
                        </li>
                        
                    </ul>
                </div>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />
 
<!-- <script src="js/jquery-1.11.3.js"></script> -->
<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<%-- <script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script> --%>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>