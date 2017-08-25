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
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<style type="text/css">
.jz-tanchu{
    width: 820px;
    height: 900px;
    background: #fff;
    border-radius: 10px;
    position: absolute;
    left: 35%;
    top:10%;
    /*margin-top: -175px;*/
    margin-left: -150px;
    z-index: 11111;
}
</style>
<script type="text/javascript">
$(document).keypress(function(event){
	if(event.keyCode==13){
			$("#login_login").click();
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

function checkagree()
{
	if (!$('#agreeRule').is(':checked')){
		alert('请同意使用条款');
	    return false;
	}
	return true;
}
//================
var wait=60;		
function time(o) {  
	var mobile=$.trim($('#mobile').val());

        if (wait == 0) {  
            o.removeAttribute("disabled");            
           
            $(o).text("点击获取")
            wait = 60;  
        } else {  
            o.setAttribute("disabled", true);
            $(o).text("重发(" + wait + ")"); 
            wait--;  
            setTimeout(function() {  
                time(o)  
            },  
            1000)  
        }  
    }  
//================
function sendSMS(this_){
	//
	var textv=$(this_).text();
	if( textv != '重发(60)'){
		return ;
	}
	//
	var account = $("#account").val();
	$.ajax({
		url : "${basePath}/compayinfo/sendSMS?mobile="+account,
		type : "POST",
		success : function(res) 
		{
			res=$.parseJSON(res);
			if(res.success){
//					alert("请查收验证码,--目前是显示出来:"+res.data);
				alert(res.errmsg);
//					$('#registverifycode').val(res.data);
			}else{
				alert("异常："+res.errmsg);
				location.reload();
			}
		}
	});
}
$(function(){
	//$('#getCheckNum').on('click',);
	
	$('#registerButton').on('click',function(){
		if( ! checkagree()){
			return ;
		}
		$.ajax({
			url : "${basePath}/compayinfo/register",
			type : "POST",
			data :$('#registerForm').serialize(),
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert("注册成功,跳转");
					var userType='${userType}';
// 					location.reload();
				if(userType == '02'){
					location.href='${basePath}/compayinfo/fill';
				}else{
					location.href='${basePath}/personalInfo/fillpersonalInfo';
				}
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
	
	$(".dianji,.guanbi").on("click",function(){
		$(".jz-tanchu,.shadow").addClass("hide1");
	})
    
	$(".fabu_jz").on("click", function () {
		$(".jz-tanchu,.shadow").removeClass("hide1");
	});
});

</script>
</head>
<body>
 <jsp:include page="../../../common/header.jsp" />

<div class="jz-login">
    <div class="jz-login_inner">
        <ul>
            <li class="login_head">
                <span><b><c:if test="${userType == '01' }">个人</c:if><c:if test="${userType == '02' }">企业</c:if>注册</b></span>
                <span class="right">
                    <span><img src="${basePath}/image/login2.png" alt="" style="padding-left: 0;"/></span>
                    <span><a href="${basePath}/compayinfo/login">登录</a></span>
                </span>
            </li>
            <form action="${path }/compayinfo/register" id="registerForm" method="post">
            
            <li class="login_contain">
                <label for="tel"><img src="${basePath}/image/tel.png" alt="" style="top:3px"/></label>
                <input type="text" placeholder="请输入手机号" id="account" name="mobile" />
            </li>
            <li class="login_contain">
                <label for="tel2"><img src="${basePath}/image/yanzheng.png" alt=""/></label>
                <input type="text" name="verifycode" placeholder="请输入验证码" id="registverifycode" style="width: 140px;"/>
<!--                 <span id="getCheckNum">获取验证码</span> -->
                <span class="btn"  type="button" onclick='time(this);sendSMS(this);' >点击获取</span>
            </li>
            <li class="login_contain">
                <label for="tel1"><img src="${basePath}/image/password.png" alt=""/></label>
                <input type="password" name="logPassword" placeholder="密码由6-12位英文字母、数字组成" id="tel1"/>
            </li>
            <c:if test="${userType == '01' }">
            <input type="hidden" name="userType" value="01"> <!--企业用户  -->
	            <li class="login_contain">
	                <label for="tel3"><img src="${basePath}/image/yaoiqng.png" alt=""/></label>
	                <input type="text" name="inviteCode" placeholder="请输入邀请码(选填)" id="tel3"/>
	            </li>
            </c:if>
            <c:if test="${userType == '02' }">
            <input type="hidden" name="userType" value="02"> <!--企业用户  -->
            </c:if>
            <li class="login-chec">
                <input type="checkbox" id="agreeRule" name="agreeRule"> 
                <span>同意<a href="javascript:void(0);" class="fabu_jz" style="color: #dab866">使用条款</a></span>
            </li>
            </form>
            <li class="login_footer" id="registerButton">
                注册
            </li>
        </ul>
    </div>
</div>

<!--弹出框-->
	<div class="shadow hide1"></div>
	
	<div class="hide1 jz-tanchu">
		<ul>
			<li class="one">
				<img src="${basePath}/image/logo11.jpg" alt="" class="logo" />
				<img src="${basePath}/image/guanbi.png" alt="" class="guanbi" />
			</li>
			<li class="three">
				<h1>励志汪兼职服务条款</h1>
				<h2>•	| 概述</h2>
				<p style="text-indent: 2em">本网站（www.lzwjz.com）提供的给企业会员宣传公司形象、发布招聘信息，个人会员发布求职信息，查询招聘会信息，薪资行情，求职指南，培训信息等，本网站的任何用户均受本协议约束。本网站可以随时修改这些条款。我们建议您定期查看这些条款。如果您通知本网站不接受任何改变，那么您对本网站的使用就将结束，否则，您的继续使用就构成您对所有改变的接受，并受这些改变的约束。</p>
				<h3>•	| 网站使用条款</h3>
				<p style="text-indent: 2em">1.个人会员必须同意使用本网站仅用于合法的目的，已注册的个人会员可以发布个人求职信息，设置信息状态。在此种情况下，求职者个人资料仍然储存在本网站数据库中，直至其本人将其个人资料从其中删除为止。</p>
				<p style="text-indent: 2em">2.个人会员必须独自全部承担由于向本网站注册企业或其他用户发送个人资料所形成的一切风险。本网站对于任何企业与个人之间所发生的一切纠纷，概不负责。</p>
				<p style="text-indent: 2em">3.本网站保留对会员资料进行修改的权力。</p>
				<p style="text-indent: 2em">4.未经个人会员的许可，本网站不会将其个人资料随意公开。</p>
				<p style="text-indent: 2em">5.企业用户可通过网上在线注册并登录公司信息。</p>
				<p style="text-indent: 2em">6.企业会员有权进入本网站资料库进行查询，但禁止利用此项权利进行查询人才以外的其他用途。</p>
				<p style="text-indent: 2em">7.企业会员须独自对登记在本网站上及其他链接页面上的内容的正确性负责。本网站有权修改企业会员的招聘广告以符合本网站的规定，有权删除一切非法的、辱骂性的及不健康的资料。</p>
				<p style="text-indent: 2em">8.本网站有权在适当的时候调整服务收费或收取其他相关费用。对拒绝接受新的收费标准的企业，本网站有权暂停或中止该企业的会员资格。</p>
				<h3>•	| 明确禁止条款</h3>
				<p style="text-indent: 2em">会员使用本网站只能用于合法目的，即企业会员宣传公司形象、发布招聘信息，个人会员发布求职信息，查询招聘会信息，薪资行情，求职指南，培训信息使用。本网站明确禁止任何其他非法用途。</p>
				<p style="text-indent: 2em">1.禁止在个人简历中公布不完整、虚假或不准确的资料，禁止在简历中公布不属于简历内容的资料。一经发现本网站将暂停或终止对该使用者的服务。</p>
				<p style="text-indent: 2em">2.未经其许可不得随意公开本网站中的任何个人资料或企业商业信息，不得向用户发送垃圾邮件。</p>
				<p style="text-indent: 2em">3.严禁采取任何方式侵犯本网站，包括：登录没有对其授权的服务器或帐号；进入没有对其开放的数据库；探测、测试或破坏本网站系统的安全性；干扰本网站对用户提供的服务；对本网站系统或网络安全造成破坏的所有个人或实体，将依法追究其法律责任。</p>
				<p style="text-indent: 2em">4.严禁利用本网站发送、散布或储存侵犯他人版权、商标权、商业秘密或其他知识产权以及侵犯他人隐私权、违反法律法规的资料；严禁利用本网站发送、散布或储存任何诽谤、辱骂、恐吓、伤害他人和庸俗淫秽的信息。 一经发现本网站有权立即取消该用户的会员资格并禁止其再次登录本网站。</p>
				<h3>•	| 适用法律和管辖权限条款</h3>
				<p style="text-indent: 2em">用户和本网站双方都必须遵守中华人民共和国法律，服从中华人民共和国的司法管辖。使用本网站所产生的任何争议，均以中华人民共和国法律为标准解决。</p>
				<h3>•	| 特别声明</h3>
				<p style="text-indent: 2em">《励志汪兼职用户服务协议》及其修改权、解释权均属励志汪兼职网。</p>
				<h3>•	| 投诉建议</h3>
				<p style="text-indent: 2em">025-52123090</p>


			</li>
		</ul>
	</div>

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />
 
<%-- <script src="${basePath}js/tab.js"></script> --%>
</body>
</html>