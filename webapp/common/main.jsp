<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>网关支付页</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="网关支付">
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
		<script src="js/jquery.js" type="text/javascript"></script>
		<script src="js/jquery.cookie.js" type="text/javascript"></script>
		<script src="js/jquery.md5.js" type="text/javascript"></script>
		<script src="js/rsa/Barrett.js" type="text/javascript"></script>
		<script src="js/rsa/BigInt.js" type="text/javascript"></script>
		<script src="js/rsa/RSA.js" type="text/javascript"></script>
	</head>
	<body bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
		 <form action="dhb/payConfirm" id="payForm" name="payForm" method="post">
		 	<div>商品信息:${reqInfo.remark }</div>
		 	<div>商品金额：${reqInfo.moneyStr }</div>
		 	<div>支付密码:<input type="password" name="paypwd" id="paypwd"/><span id="errorMsg" style="color: red;">${errorMsg }</span></div>
		 	<input type="hidden" id="${tokenName}" name="${tokenName}" value="${newToken}" />
			<input type="hidden" id="${signName}" name="${signName}" value="" />
			
			<input type="hidden" name="userId" id="userId" value="${reqInfo.userId }" />
			<input type="hidden" name="userName" id="userName" value="${reqInfo.userName }" />
			<input type="hidden" name="remark" id="remark" value="${reqInfo.remark }" />
		 	<input type="hidden" name="orderId" id="orderId" value="${reqInfo.orderId }" />
		 	<input type="hidden" name="merchId" id="merchId" value="${reqInfo.merchId }"/>
		 	<input type="hidden" name="money" id="money" value="${reqInfo.money }"/>
		 	<input type="hidden" name="acctId" id="acctId" value="${reqInfo.acctId }"/>
		 	<input type="hidden" name="ticket" id="ticket" value="${reqInfo.ticket }"/>
		 	<input type="button" onclick="check()" id="btnPay" value="支付" />
		 </form>
	</body>
	<script type="text/javascript">
		function check(){
			var pwd = $("#paypwd").val();
			if(pwd==''){
				$("#errorMsg").html("支付密码不合法");
				return false;
			}
			var sign = $("#${signName}");
			if (sign.length==1) {
				setMaxDigits(129);
				var key = new RSAKeyPair("${publicExponent}", "", "${modulus}");
				
				var paypwd = pwd;
				if (pwd.length<32) {
					paypwd = encryptedString(key, pwd);
				}
				var t = $("#${tokenName}").val();
				var p = "${tokenName}=" + t + "paypwd=" + paypwd;
				var eh = encryptedString(key, $.md5(p));
				
				$("#paypwd").val(paypwd);
				
				$("#${signName}").val(eh);
			}
			
			$("#btnPay").attr({"disabled":"disabled"});
			$("#payForm").submit();
		}
	</script>
</html>
