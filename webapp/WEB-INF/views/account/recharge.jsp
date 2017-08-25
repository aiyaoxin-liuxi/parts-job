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
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">


function checkrecharge(){
	$('.regist-tip').hide().find('i').empty();
	var account = $("#account").val();
	var temp =  /^((13[0-9])|(15[0-9])|147|(17[0-9])|(18[0-9]))[0-9]{8}$/;
	if(temp.test(account))
	{
		return true;
	}
	else
	{
		
		alert("请输入正确的手机号");
// 		$("#account").val('');
		return false;
	}
}

/**
 * 校验金额
 * @returns {Boolean}
 */
function checkmoney(){
	var account = $("#recharge").val();
	
	if(account == "")
	{
		alert('请输入正确的充值金额');
		return false;
	}
	
	var temp =  /^(([1-9]{1}[0-9]*)|([0]{1}))(\.([0-9]){0,2})?$/;
	if(!temp.test(account))
	{
		alert('请输入正确的充值金额(最多两位小数或者整数)');
		return false;
	}
	
	if(account < 0.01)
	{
		alert('充值金额太少');
		return false;
	}
	
	if(account > 999999.99)
	{
		alert('充值金额最多为999,999.99元');
		return false;
	}
	
	return true;
}

 
$(function(){
	$('#smsCodeDiv').hide();
	//短信验证码的
	$('#smscodeButton').on('click',function(){
		var smscode=$('#smscode').val();
		var sendSmsSucc=$('#sendSmsSucc').val();//页面标记发送成功
// 		$('#verifyFlag').val('verifyFlag');
		console.log("1.短信验证码的");
		if(!smscode ){
			alert('请填写短信验证码');
			return ;
		}
		$(this).attr({disabled:true});
		$.ajax({
			url : "${basePath}/account/recharge",
			type : "POST",
			data :$('#rechargeForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
					if(sendSmsSucc == 'sendSmsSucc'){
					     location.href="${basePath}/account/main";//跳转账户信息主页面
					}
				}else{
					alert("异常："+res.errmsg);
					$("#smscodeButton").removeAttr('disabled');
				}
			}
		});
	});
	///////////////////////////
	$('#rechargeButton').on('click',function(){
		var smscode=$('#smscode').val();
// 		$('#verifyFlag').val('');
		
		
		$(this).attr({disabled:true});
// 		console.log("1.充值");
		if(! checkmoney() ){
			return ;
		}
		
		$.ajax({
			url : "${basePath}/account/recharge",
			type : "POST",
			data :$('#rechargeForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
					$('#sendSmsSucc').val('sendSmsSucc');//页面标记发送成功
					//accountId 
					$('#accountflowAccountId').val(res.data);//流水的id
					$('#smsCodeDiv').show();//显示
				}else{
					alert("异常："+res.errmsg);
					$('#sendSmsSucc').val('');
					$("#rechargeButton").removeAttr('disabled');
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
                <li>账户信息</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            <jsp:include page="../compayinfo/common/left.jsp" />
            <ul class="jz-center">
                <jsp:include page="../account/common/account-title.jsp" />
                <li class="jz-title">
                    <p>|账户余额提现</p>
                    <span style="margin-left: 80px;"><b>账户可用余额：<span><c:if test="${empty user.useAmount }">0</c:if> <c:if test="${not empty user.useAmount }">${user.useAmount  }</c:if></span>元</b></span>
                </li>
                <form action="" id="rechargeForm">
				<input type="hidden" class="form-control" id="sendSmsSucc" name="sendSmsSucc" value=""  ><br>
				<input type="hidden" class="form-control" id="accountflowAccountId" name="accountflowAccountId" value=""  ><br>
	
					<c:if test="${ empty cards }">
						请先 <a href="${basePath}/compayinfo/bindcard">绑定银行卡</a>
					</c:if>
		
		<c:if test="${not empty cards }">
                <li class="ja-news " style="border-bottom: none;margin: 30px 0 50px">
                    <p>
                        <label for="name" style="display: inline-block;width: 100px">充值金额</label>
                        <input type="text" id="recharge" name="amount"  placeholder="请输入金额，最多保留两位小数"/>(元)
                    </p>
                    <p>
                        <label style="display: inline-block;width: 100px">付款方式</label>
                        <c:forEach items="${cards}" var ="eachOne">
                        <p>
						 <input name="bank" type="radio" value="${eachOne.id }"/> ${eachOne.bankname }&nbsp;&nbsp; ${eachOne.cardNo }&nbsp;&nbsp; <br>
						</p>
						</c:forEach>
			
                       <%--  <span class="xuanze onca"><img src="${basePath}/image/pay.png" alt=""/></span>
                        <span class="xuanze1 onca"><img src="${basePath}/image/weixin11.png" style="width: 20px;left: 10px;" alt=""/></span> --%>
                   <!--  <div style="font-size: 14px;margin-left: 160px;">
                        <a href="" style="color: #000">支付宝支付</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="" style="color: #000">微信支付</a>
                    </div> -->
                    </p>
                    
                     <div class="clear"></div>
                    <p class="tijiao clear" >
<!--                   		  <input class="btn" id="rechargeButton" type="button" value="提交"> -->
                        <button style="width: 420px;margin-left: 120px;"  class="btn" id="rechargeButton" type="button" >提交</button>
                    </p>
                </li>
        </c:if>
        <!-- 弹框start.... -->
            <div id="smsCodeDiv" style="background:yellow">
				<fieldset>
				
					<legend>这是模拟一个弹框 </legend>
					短信验证码1：<br>
		<!-- 			<input type="text" class="form-control" id="smscode" name="smscode" value="" onblur="checkmoney()" placeholder="请输入金额，最多保留两位小数"><br> -->
					
					<input type="text" class="form-control" id="smscode" name="smscode" value=""  placeholder="请输入您收到的短信验证码"><br>
		<!-- 			<input type="hidden" class="form-control" id="verifyFlag" name="verifyFlag" value=""  ><br> -->
					<br>
					<input class="btn" id="smscodeButton" type="button" value="提交验证码">
				</fieldset>
				</div>
            
            <!-- 弹框end .... -->
            
                </form>
            
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