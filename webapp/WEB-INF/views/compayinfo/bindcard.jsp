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

//通过接口获取银行信息
function getBankName(this_){
	$.ajax({
		url : "${basePath}/compayinfo/bankName",
		type : "POST",
		data :$('#bindcardForm').serialize(),
		dataType : 'json',
		success : function(res) 
		{
			res=$.parseJSON(res);
			if(res.success){
				$("#bank").val(res.data.bankId);$("#bankname").val(res.data.bankName);
			}else{
				alert("异常："+res.errmsg);
			}
		}
	});
}
$(function(){
	$('#bindcardButton').on('click',function(){
		
// 		console.log("1.绑定银行卡");
// 		checkaccount();
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/compayinfo/bindcard",
			type : "POST",
			data :$('#bindcardForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
// 				console.log("结果："+JSON.stringify(res));
				if(res.success){
					alert(res.errmsg);
// 					location.reload();
					window.location.href="${basePath}/card/list";
// 					$('#registverifycode').val(res.data);
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
                <c:if test="${userType == '01' }">
					<!-- 个人 -->
					<jsp:include page="../personalInfo/common/account-title.jsp" />
				</c:if>
				<c:if test="${userType == '02' }">
					<!--企业  -->
					<jsp:include page="../compayinfo/common/account-title.jsp" />
				</c:if>
				
                <li class="jz-title">
                    <p>|绑定银行卡</p>
                </li>
          <form action="" id="bindcardForm">
                <li  class="ja-news " style="border-bottom: none;">
                    <p>
                        <label for="name4" style="display: inline-block;width: 100px">姓名</label>
                        <input type="text" name="cardName" id="name4" placeholder="请输入银行卡所属人姓名"/>
                    </p>
                    <p>
                        <label for="name4" style="display: inline-block;width: 100px">身份证号</label>
                        <input type="text" name="bindIdcard" id="name4" placeholder="请输入身份证号"/>
                    </p>
                    <p>
                        <label for="name6" style="display: inline-block;width: 100px">银行卡号</label>
                        <input type="text" id="name6" name="cardNo"  onblur="getBankName(this)" placeholder="请输入银行卡号"/>
                    </p>
                    <!--  
                      ：<input type="text" name="mobile" value="13555555555"><br><br>
                    
                    -->
                    <p>
                        <label for="name6" style="display: inline-block;width: 100px">手机号</label>
                        <input type="text" id="name6" name="mobile"  placeholder="请输入手机号"/>
                    </p>
                    <input type="hidden" id="bank" name="bank" value=""><!-- 所属银行  --><br><br>
                     <p>
                        <label for="name6" style="display: inline-block;width: 100px">  银行名称</label>
                        <input type="text" id="bankname" name="bankname" value="${card.bankname}" readonly placeholder=""/>
                    </p>
                    <!-- <p>
                        <label style="display: inline-block;width: 100px">开户行</label>
                        <select name="" >
                            <option value="">请选择</option>
                            <option value="">招商银行 </option>
                            <option value="">建设银行</option>
                        </select>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 100px">开户省</label>
                        <select name="" >
                            <option value="">请选择</option>
                            <option value="">河南</option>
                            <option value="">河北</option>
                        </select>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 100px">开户市</label>
                        <select name="" >
                            <option value="">请选择</option>
                            <option value="">北京</option>
                            <option value="">上海</option>
                        </select>
                    </p> -->
                    <p class="tijiao">
                        <button style="width: 420px;margin-left: 120px;" class="btn" id="bindcardButton" type="button">完成</button>
                    </p>
               </form>     
                    <p style="color: #e60012;margin-left: 120px;">
                        <span>提示：银行卡号、开户市、姓名必须一致，否则将无法绑定</span>
                    </p>
                </li>
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