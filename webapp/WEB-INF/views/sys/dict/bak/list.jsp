<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<style type="text/css">

</style>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<%-- <script type="text/javascript" src="${basePath}/js/jquery.js"></script> --%>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
function rechargeClick(){
// 	console.log("recharechargeClick  ");
	myloadUrl('.content3','${basePath}/account/rechargeList?date='+new Date().getTime());
}
function del(id_){
	console.log("sssssssssssssssss");
	$.ajax({
		url : "${basePath}/manager/dict/del",
		type : "POST",
		data :{did:id_},
		dataType : 'json',
		success : function(res) 
		{
			res=$.parseJSON(res);
			console.log("结果："+JSON.stringify(res));
			if(res.success){
				alert(res.errmsg);
				location.reload();
			}else{
				alert("异常："+res.errmsg);
			}
		}
	});
}

function edit(id_){
			console.log("edit");
			location.href='${basePath}/manager/dict/edit?did='+id_;
			
		}		

$(function(){
	$('.content2').hide();$('.content3').hide();$('.content4').hide();
	var logoImg='${companyInfo.logoImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":"${basePath}/"+logoImg});
		$('#logoImg').val(logoImg);
	}
	//switch
	$('.onc_jz').click(function(){
		$('.onc_jz').removeClass('active_jz');
		$(this).addClass('active_jz');
		$('.con_ban_inr').hide();
		$("."+$(this).data("id")).show();
		if($(this).data("id") == 'content3'){
			rechargeClick();
		}
	});
	///////////////
	$(".pagesinner1 button").click(function(){
		var pageReg='${pageReq}';
		var text = $(this).html();
		if(text == '上一页'){
			if($("#prePage").val() == '0'){
				alert("已经是第一页");
			} else {
				text = Number($("#pageNo").val()) - 1;
			}
		} else if(text == '下一页'){
			if($("#nextPage").val() == 0){
				alert("已经是最后一页");
			} else {
				text = Number($("#pageNo").val()) + 1;
			}
		}
		$("#pageNo").val(text);
// 		var state = $("#state").val();
		var pageNo = text;
		window.location.href=pageReg+"?pageNo="+pageNo;
	});
});

</script>
</script>
</head>
<body>

<h2> <a href="${basePath}/manager/dict/list"> 字典列表</a></h2>

<h2> <a href="${basePath}/manager/dict/add"> 字典add</a></h2>
<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_center">
            <ul class="jz-center">
                <div class="con_ban_inr content content1">
                   <table>
                       <tr>
                           <th>日期</th>
                           <th>key</th>
                           <th>value</th>
                           <th>dgroup</th>
                           <th>remark</th>
                           <th>操作</th>
                       </tr>
                       <c:if test="${appList.size() == 0 }">
							<tr><td colspan="4"><li style="text-align: center;"><p>未找到相关数据</p></li></td></tr>
						</c:if>
						<c:if test="${appList.size() > 0 }">
							<c:forEach items="${appList }" var="item">
							   <tr>
		                           <td ><fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
		                           <td >
		                           <!--  -->
		                          ${item.dkey }
		                           <!--  -->
		                           </td>
		                           <td>${item.dvalue }</td>
<!-- 		                           <td>微信</td> -->
		                           <td>${item.dgroup }</td>
								   <td>${item.remark }</td>
								   <td >
								   <span onclick="del('${item.did}')"> 删除</span>
								   &nbsp;&nbsp;
								   <span onclick="edit('${item.did}')"> 编辑</span>
								   </td>
		                       </tr>
							</c:forEach>
						</c:if>
                   </table>
                   <!--sssssssssss  -->
                    <input type="hidden" id="pageNo" value="${pageNo }"/>
				    <input type="hidden" id="prePage" value="${prePage }"/>
				    <input type="hidden" id="nextPage" value="${nextPage }"/>
				    <div class="jz-pages">
				        <ul class="pagesinner pagesinner1">
				        	<li class="nav">
				            <button <c:if test="${prePage == 0 }">disabled</c:if>>上一页</button></li>
				            <c:forEach items="${navigatepageNums}" var="item" begin="0" end="4">
								<li><button>${item}</button></li>
							</c:forEach>
							<c:if test="${pages > 3}">
								<li><button>...</button></li>
								<li><button>${pages}</button></li>
				            </c:if>
				            <li class="nav">
				            <button <c:if test="${nextPage == 0 }">disabled</c:if>>下一页</button></li>
				        </ul>
				    </div>
                   <!-- eeeeeeeeeeee -->
                   <div class="clear"></div>
                </div>
              <div class="con_ban_inr content content2">
                   <table>
                       <tr>
                           <th>日期2</th>
                           <th>发布岗位2</th>
                           <th>交易金额（元）2</th>
                           <th>交易方式2</th>
                           <th>交易状态2</th>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>【审核录入】200元/天招聘兼职会电脑录...</td>
                           <td>-200.00</td>
                           <td>微信</td>
                           <td>未完成</td>

                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>【审核录入】200元/天招聘兼职会电脑录...</td>
                           <td>-200.00</td>
                           <td>支付宝</td>
                           <td>交易成功</td>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>【审核录入】200元/天招聘兼职会电脑录...</td>
                           <td>-200.00</td>
                           <td>微信</td>
                           <td>交易成功</td>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>【审核录入】200元/天招聘兼职会电脑录...</td>
                           <td>-200.00</td>
                           <td>支付宝</td>
                           <td>交易成功</td>
                       </tr>
                   </table>
                </div>
                <div class="con_ban_inr content content3">
                   <table>
                   		<tr>
                           <th>客观您骚等...</th>
                       </tr>
                       <!-- <tr>
                           <th>日期</th>
                           <th>充值/提现</th>
                           <th>交易账户</th>
                           <th>交易金额（元）</th>
                           <th>交易方式</th>
                           <th>交易状态</th>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>账户提现</td>
                           <td>**** **** **** 0000</td>
                           <td>-200.00</td>
                           <td>微信</td>
                           <td>未完成</td>

                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>账户充值</td>
                           <td>**** **** **** 0000</td>
                           <td>-200.00</td>
                           <td>支付宝</td>
                           <td>交易成功</td>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>账户提现</td>
                           <td>**** **** **** 0000</td>
                           <td>-200.00</td>
                           <td>微信</td>
                           <td>交易成功</td>
                       </tr>
                       <tr>
                           <td>2017-03-27</td>
                           <td>账户充值</td>
                           <td>**** **** **** 0000</td>
                           <td>-200.00</td>
                           <td>支付宝</td>
                           <td>交易成功</td>
                       </tr> -->
                   </table>
                </div>
                <div class="clear"></div>
                <div class="con_ban_inr content content4">
                   <table>
                       <tr>
                           <th>客观您骚等...</th>
                       </tr>
                   </table>
                </div>
            </ul>
            <div class="clear"></div>
            
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 
<!-- <script src="js/jquery-1.11.3.js"></script> -->
<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<%-- <script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script> --%>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>