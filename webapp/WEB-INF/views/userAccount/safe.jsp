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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script src="${basePath}/js/jquery-1.11.3.js"></script>
<script src="${basePath}/js/laydate.js"></script>
<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<script src="${basePath}/js/tab.js"></script>
<title>安全中心</title>
<script type="text/javascript">
$(function(){
});
function unBind(cardId){
	if(confirm("确定删除该银行卡?")){
		$.ajax({
			url : "${basePath}/userAccount/unBind",
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
	}else{
		
	}
}	
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
                        <img src="image/jishiben.png" alt=""/>
                        我的兼职
                        <span class="jianTou3 "></span>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="image/zhanghu.png" alt=""/>
                        <a href="${basePath}/personalInfo/fillpersonalInfo">我的信息</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="image/price.png" alt=""/>
                        <a href="${basePath}/userAccount/accountIndex">个人账户</a>
                    </p>
                </li>
                <li class="three">
                    <p>
                        <img src="image/bangding1.png" alt=""/>
                        <a href="${basePath}/userAccount/bindwx">账号绑定</a>
                    </p>
                </li>
                <li class="three tthree">
                    <p>
                        <img src="image/anquan2.png" alt=""/>
                        <a href="${basePath}/userAccount/safe" style="color: #fff;">安全中心</a>
                        <span class="jianTou3"></span>
                    </p>
                </li>
            </ul>
            <ul class="jz-center">
                <li class="jz-title">
                    <p>|安全中心</p>
                </li>
                <div class="con_ban_inr">
                    <ul class="safe">
                        <li >
                            <p class="one">
                                <span><img src="${basePath}/image/anquan3.png" alt=""/></span>
                                <span>保护中</span>
                            </p>
                            <p class="two">登录密码</p>
                            <p class="three">登录励志汪账户时需要输入的密码</p>
                            <p><a href="${basePath}/userAccount/modifypwd" style="color: #dab866;">修改</a></p>
                        </li>
                        <li >
                            <p class="one">
                                <span><img src="${basePath}/image/anquan3.png" alt=""/></span>
                                <span>保护中</span>
                            </p>
                            <p class="two">支付密码</p>
                            <p class="three">提现或付款时输入，保护资金安全</p>
                            <p>
                        		<a href="${basePath}/userAccount/modifypaypwd" style="color: #dab866;">        
                                <c:if test="${modifyFlag eq 0 }">设置</c:if>  
                                <c:if test="${modifyFlag eq 1 }">修改</c:if></a>&nbsp;
                                <a href="${basePath}/userloginfo/findpaypwd" style="color: #dab866;">找回</a>
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

</body>
</html>