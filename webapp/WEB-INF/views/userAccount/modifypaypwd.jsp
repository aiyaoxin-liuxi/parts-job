<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script src="${basePath}/js/jquery-1.11.3.js"></script>
<script src="${basePath}/js/laydate.js"></script>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
<title></title>
<script type="text/javascript">
$(function(){
$('#paypwdButton').on('click',function(){
		$.ajax({
			url : "${basePath}/userAccount/updatepaypwd",
			type : "POST",
			data :{
				id:$("#id").val(),
				modifyFlag:$("#modifyFlag").val(),
				payPassword:$("#payPassword").val(),
				rePayPassword:$("#rePayPassword").val(),
				newPayPassword:$("#newPayPassword").val(),
				reNewPayPassword:$("#reNewPayPassword").val()
			},
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
					//location.href=history.go(-1);//先这样写，等待需求
 					window.location.href="${basePath}/userAccount/safe";
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
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>个人中心<span class="jianTou"></span>安全中心</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            <ul class="jz-left">
                <li class="one onee">
                    <p>
                        <img src="${basePath}/image/jishiben.png" alt=""/>
                        <a href="">我的兼职</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/zhanghu.png" alt=""/>
                        <a href="${basePath}/personalInfo/fillpersonalInfo">我的信息</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/price.png" alt=""/>
                        <a href="${basePath}/userAccount/accountIndex">个人账户</a>
                    </p>
                </li>
                <li class="three tthree">
                    <p>
                        <img src="${basePath}/image/bangding2.png" alt=""/>
                        <a href="${basePath}/userAccount/bindwx" style="color: #fff;">账号绑定</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="${basePath}/image/anquan.png" alt=""/>
                        <a href="${basePath}/userAccount/safe">安全中心</a>
                         <span class="jianTou3"></span>
                    </p>
                </li>
            </ul>
            <ul class="jz-center">
                <li class="jz-title">
                    <p>|<c:if test="${modifyFlag eq 0 }">设置</c:if>  <c:if test="${modifyFlag eq 1 }">修改</c:if>支付密码</p>
                </li>
                <li class="ja-news">
                		<input type="hidden" name="modifyFlag" id="modifyFlag" value="${modifyFlag}">
						<input type="hidden" name="id" id="id" value="${id}">
	                	<p>
	                        <label for="name" style="margin-left: 36px;"><c:if test="${modifyFlag eq 1 }">原</c:if>密码</label>
	                        <input type="password" id="payPassword" name="payPassword" placeholder="请输入支付密码"/>
	                    </p>
	                    <c:if test="${modifyFlag eq 0 }">
	                    	<p>
		                        <label for="name" style="margin-left: 36px;">重复密码</label>
		                        <input type="password" id="rePayPassword" name="rePayPassword" placeholder="请输入支付密码"/>
		                    </p>
						</c:if> 
	                	<c:if test="${modifyFlag eq 1 }">
				             <p>
				                 <label for="number" style="margin-left: 36px;">新密码</label>
				                 <input type="password" id="newPayPassword" name="newPayPassword" placeholder="请输入新密码"/>
				             </p>
				             <p>
				                 <label>确认新密码</label>
				                 <input type="password" id="reNewPayPassword" name="reNewPayPassword" placeholder="请再次输入新密码"/>
				             </p>
	                	</c:if>
	                    <p class="tijiao" style="margin-left: 140px;margin-bottom: 400px;">
	                        <button id="paypwdButton">确定</button>
	                    </p>
                </li>

            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

</body>
</html>