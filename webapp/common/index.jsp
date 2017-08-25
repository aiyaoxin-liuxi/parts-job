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
		<script type="text/javascript">
			function getUrlParam(name) {
			  	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
			  	var r = window.location.search.substr(1).match(reg); //匹配目标参数
			  	if (r != null) 
			  		return unescape(r[2]); 
			  	return null; //返回参数值
			}
			//流水号 商户号 钱包账户号 金额 描述 同步地址 ticket 签名
			function appPay(tranNo,merchId,acctId,money,remark,synchUrl,ticket,sign){
				/* var tranNo = getUrlParam('tranNo');
				var merchId = getUrlParam('merchId');
				var userId = getUrlParam('userId');
				var money = getUrlParam('money');
				var remark = getUrlParam('remark');
				var synchUrl = getUrlParam('synchUrl');
				var transfer = getUrlParam('transfer');
				var sign = getUrlParam('sign'); */
				$("#tranNo").val(tranNo);
				$("#merchId").val(merchId);
				$("#acctId").val(acctId);
				$("#money").val(money);
				$("#remark").val(remark);
				$("#synchUrl").val(synchUrl);
				$("#ticket").val(ticket);
				$("#sign").val(sign);
				$("#payForm").submit();
			}
		</script>
	</head>
	<body bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
		<form action="dhb/walletPay" method="post" id="payForm">
			<input type="hidden" name="tranNo" id="tranNo" />
			<input type="hidden" name="merchId" id="merchId" />
			<input type="hidden" name="acctId" id="acctId" />
			<input type="hidden" name="money" id="money" />
			<input type="hidden" name="remark" id="remark" />
			<input type="hidden" name="synchUrl" id="synchUrl" />
			<input type="hidden" name="ticket" id="ticket" />
			<input type="hidden" name="sign" id="sign" />
		</form>
	</body>
	
</html>
