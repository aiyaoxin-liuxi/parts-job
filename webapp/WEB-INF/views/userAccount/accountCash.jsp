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
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript">
$(function(){
	$('#a2').click();
});
$(function(){
	$('#withdrawCashButton').on('click',function(){
		$.ajax({
			url : "${basePath}/userAccount/userCash",
			type : "POST",
			data :$('#withdrawCashForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					location.href='${basePath}/userAccount/accountIndex';
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
<jsp:include page="/common/header.jsp" />
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
                <c:if test="${userType == '01' }">
					<!-- 个人 -->
					<jsp:include page="../personalInfo/common/account-title.jsp" />
				</c:if>
				<c:if test="${userType == '02' }">
					<!--企业  -->
					<jsp:include page="../compayinfo/common/account-title.jsp" />
				</c:if>
                <li class="jz-title">
                    <p>|账户余额提现</p>
                    <span><b>账户可用余额：<span><c:if test="${empty userLogInfo.useAmount }">0</c:if>${userLogInfo.useAmount }</span>元</b></span>
                </li>
                <li class="contain_banner">
<!--                     <span class="onc_jz " id="a1"><b>支付宝</b></span> -->
                    <span class="onc_jz active_jz" id="a2"><b>银行卡</b></span>
                </li>
                <!-- <li class="ja-news content content1" style="border-bottom: none;">
                    <p>
                        <label for="name" style="display: inline-block;width: 100px">提现金额</label>
                        <input type="text" id="name" placeholder="可提现金额：0.00元"/>
                    </p>

                    <p>
                        <label for="name1" style="display: inline-block;width: 100px">支付宝账号</label>
                        <input type="text" id="name1" placeholder="请输入支付宝账号"/>
                    </p>

                    <p>
                        <label for="name2" style="display: inline-block;width: 100px">支付宝姓名</label>
                        <input type="text" id="name2" placeholder="请输入支付宝账号所属人姓名"/>
                    </p>

                    <p>
                        <label for="name3" style="display: inline-block;width: 100px">支付密码</label>
                        <input type="password" id="name3" placeholder="请输入6位数字支付密码"/>
                    <div style="font-size: 14px;margin-left: 120px;">
                        <a href="" style="color: #dab866;margin-right: 260px;">短信快捷支付</a>
                        <a href="" style="color: #dab866">忘记密码？</a>
                    </div>
                    </p>
                    <p class="tijiao">
                       <button style="width: 420px;margin-left: 120px;">完成</button>
                    </p>
                    <p style="color: #e60012;line-height: 1.5;height: 20px">
                        <span>提示：1、支付宝账号和支付宝账号姓名必须一致，否则将无法体现。</span>

                    </p>
                    <p style="color: #e60012;margin-left: 48px;">
                        <span>2、工作日：9:00-18:00之间提现，两小时之内到账，18:00以后提现，下个工作日10:30之前到账</span>
                    </p>
                </li> -->
                
                <c:if test="${ (empty cardList)|| (cardList.size()==0) }">
					您还没有绑卡，<a href="${basePath}/card/list">去绑定银行卡</a>
				</c:if>
		 		<c:if test="${ (not empty cardList)|| (cardList.size()>0) }">
					<form action="" id="withdrawCashForm">
		                <li  class="ja-news content content1" style="border-bottom: none;">
		                    <p>
		                        <label for="name4" style="display: inline-block;width: 100px">提现金额</label>
		                        <input type="text" id="name4" name="payamount" placeholder="可用金额：${userLogInfo.useAmount }元"/>(元)
		                    </p>
		                    <p>
		                        <label style="display: inline-block;width: 100px">选择银行卡</label>
		                        <select name="id">
		                            <option value="">请选择</option>
		                        	<c:forEach items="${cardList }" var="card">
			                            <option value="${card.id}">${card.bankname} [ ${card.cardNo } ]</option>
		                            </c:forEach>
		                        </select>
		                    </p>
		                    <p>
		                        <label for="name6" style="display: inline-block;width: 100px">支付密码</label>
		                        <input type="password" name="payPassword" id="name6" placeholder="请输入支付密码"/>
<!-- 		                    <div style="font-size: 14px;margin-left: 120px;"> -->
<!-- 		                        <a href="" style="color: #dab866;margin-right: 260px;">短信快捷支付</a> -->
<!-- 		                        <a href="" style="color: #dab866">忘记密码？</a> -->
<!-- 		                    </div> -->
		                    </p>
		                    <p class="tijiao">
		                        <button  class="btn" id="withdrawCashButton" type="button" style="width: 420px;margin-left: 120px;">提交</button>
		                    </p>
		                    <p style="color: #e60012;">
		                        <span>工作日：9:00-18:00之间提现，两小时之内到账，18:00以后提现，下个工作日10:30之前到账</span>
		                    </p>
		                </li>
					</form>
				</c:if>
               	
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>


<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<%-- <script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script> --%>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>