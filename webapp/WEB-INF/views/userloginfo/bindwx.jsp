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
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript">
$(function(){
	$('#paypwdButton').on('click',function(){
		
		console.log("1.支付密码密码");
		
		$.ajax({
			url : "${basePath}/userloginfo/paypwd",
			type : "POST",
			data :$('#paypwdForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
					location.href=history.go(-1);//先这样写，等待需求
// 					window.location.href="${basePath}/compayinfo/login";
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
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">companyinfo center</span>
                    <span style="font-size: 30px;">企业中心</span>
                </li>
                <li>企业中心<span class="jianTou"></span>安全中心</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            <jsp:include page="../compayinfo/common/left.jsp" />
             <ul class="jz-center">
                <li class="jz-title">
                    <p>|<c:if test="${modifyFlag eq 0 }">设置</c:if><c:if test="${modifyFlag eq 1 }">修改</c:if>支付密码</p>
                </li>
                <div class="con_ban_inr">
                    <ul class="zhanghao">
                        <li style="width: 450px;">
                            <p>
                                <img src="${basePath}/image/weixinlogo.png" alt="" style="width: 30px;position: relative;top: 10px;"/>
                                <span><b>绑定微信账号</b></span>&nbsp;
                                <span style="color: #74da66;">已绑定</span>
                            </p>
                            <p style="font-size: 20px;color: #dab866;"><b>您正在使用励志汪账号关联1个微信号</b></p>
                            <p class="jiechu">解除绑定</p>
                        </li>
                        <li style="height: 50px;border-left: 1px solid #ddd;padding-left: 50px">
                            <img src="${basePath}/image/logo11.jpg" alt="" style="width: 100px"/>
                            <img src="${basePath}/image/zuoyou.png" alt=""/>
                            <img src="${basePath}/image/weixinlogo.png" alt="" style="width: 30px"/>
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

<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
</body>
</html>