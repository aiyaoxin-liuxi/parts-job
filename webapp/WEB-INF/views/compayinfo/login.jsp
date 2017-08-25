<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/common.jsp"%> 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<script type="text/javascript">
$(function(){
	var loginOut='${loginOut}';
	if(!!loginOut && loginOut == 'loginOut'){
// 		var userType='${userType}';
// 		if(!!userType && userType == '03'){
// 			location.href='${basePath}/sys/sysuser/login';
// 			return ;
// 		}
		location.href='${basePath}/';
	}
});
	
$(document).keypress(function(event){
	if(event.keyCode==13){
			$("#loginButton").click();
		}
});

function checkaccount(){
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
		return false;
	}
}


$(function(){
	$('#loginButton').on('click',function(){
		
 		//checkaccount();
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/compayinfo/login",
			type : "POST",
			data :$('#loginForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					if(res.data.userType == '02'){
						window.location.href="${basePath}/compayinfo/fill?id="+res.data.id;
					}else if(res.data.userType == '01'){
						window.location.href="${basePath}/work/toQueryWorkList";
						//window.location.href="${basePath}/personalInfo/fillpersonalInfo?id="+res.data.id;
					}
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});

function reloadVerifyCode() {
	document.getElementById('verifyCodeImage').setAttribute('src',
			'${basePath}/compayinfo/verifyCodeImage?d='+new Date().getTime());
}

</script>
</head>
<body>
  <jsp:include page="/common/header.jsp" />
<div class="jz-login">
    <div class="jz-login_inner">
        <ul>
            <li class="login_head">
                <span><b>个人/企业登录</b></span>
                <span class="right">
                    <span><img src="${basePath}/image/login1.png" alt=""/></span>
                    <span><a href="${basePath}/compayinfo/registerselect">注册</a></span>
                </span>

            </li>
            <form action="" id="loginForm">
            <li class="login_contain">
                <label for="tel"><img src="${basePath}/image/tel.png" alt="" style="top:3px"/></label>
                <input type="text" id="account" name="mobile" placeholder="请输入手机号" />
            </li>
            <li class="login_contain">
                <label for="tel1"><img src="${basePath}/image/password.png" alt=""/></label>
                <input type="password" name="logPassword" placeholder="请输入密码" />
            </li>
            <li class="login_contain">
                <label for="tel2"><img src="${basePath}/image/yanzheng.png" alt=""/></label>
                <input type="text" placeholder="请输入验证码" id="verifyCode" name="verifyCode" style="width: 140px;" maxLength="4"/>
                <img src="${basePath}/compayinfo/verifyCodeImage" id="verifyCodeImage" onclick="reloadVerifyCode()" alt="" class="suijima"/>
            </li>
            </form>
            <li class="mima">
                <a href="">忘记密码？</a>
            </li>
            <li class="login_footer" id="loginButton">
                登录 
            </li>
            <%-- <li class="login_other">
                <span>其他登录方式
                    <a href=""><img src="${basePath}/image/wechatLogin.png" alt=""/></a>
                </span>
            </li> --%>
        </ul>
    </div>
</div>


<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 
</body>
</html>