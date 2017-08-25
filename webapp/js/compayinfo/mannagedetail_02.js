$(document).ready(function() {
	if($.getUrlParam('state') == '' || $.getUrlParam('state') == null){
		$("#a1").addClass("active_jz");
//		$("#state").val("00");
	} else if($.getUrlParam('state') == '00'){
		$("#a1").addClass("active_jz");
//		$("#state").val("00");
	} else if($.getUrlParam('state') == '01'){
		$("#a2").addClass("active_jz");
//		$("#state").val($.getUrlParam('state'));
	} else if($.getUrlParam('state') == '02'){
		$("#a3").addClass("active_jz");
//		$("#state").val($.getUrlParam('state'));
	} else if($.getUrlParam('state') == '03'){
		$("#a4").addClass("active_jz");
//		$("#state").val($.getUrlParam('state'));
	}
	
	$("#a1").click(function(){
		window.location.href="parttimeMannage?state=00";
	});
	$("#a2").click(function(){
		window.location.href="parttimeMannage?state=01";
	});
	$("#a3").click(function(){
		window.location.href="parttimeMannage?state=02";
	});
	$("#a4").click(function(){
		window.location.href="parttimeMannage?state=03";
	});
	
	$(".pagesinner button").click(function(){
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
		var state = $("#state").val();
		var pageNo = text;
		window.location.href="parttimeMannage?state="+state+"&pageNo="+pageNo;
	});
	
	
});
// 更改金额
function updateMoney(index, money){
	$("#showMoney").val(money);
    $(".jz-tanchu_2,.shadow").removeClass("hide1");
    $(".ched").on("click",function(){
    	if(checkMoney()){
    		$("#accountPay_"+index).val($("#showMoney").val());
        	$("#showAccountPay_"+index).html($("#showMoney").val());
            $(".jz-tanchu_2,.shadow").addClass("hide1");
    	}
    })
}
// 全部结算按钮
function sub(){
    $(".jz-tanchu_5,.shadow").removeClass("hide1");
    $(".ched").on("click",function(){
    	$("#password").val("");
        $(".jz-tanchu_5,.shadow").addClass("hide1");
    })
    $(".che").on("click",function(){
    	$("#password").val("");
        $(".jz-tanchu_5,.shadow").addClass("hide1");
    })
}
// 点击日期
function selectDate(id, date){
	if(new Date().Format("yyyy-M-d") < new Date(date).Format("yyyy-M-d")){
		var state = $("#state").val();
		window.location.href = "mannagedetail?id="+id+"&state="+state+"&dateStr="+date;
	} else {
		var state = $("#state").val();
		window.location.href = "mannagedetailNotPage?id="+id+"&state="+state+"&dateStr="+date;
	}
}

//点击录用
function employment(id){
	var params = {};
	params.id = id;
	$.ajax({
		type: "post",
		url: _basePath + "/apply/employment",
		data: params,
		dataType:"json",
		async: true,
		success: function(data) {
			
			data = $.parseJSON(data);
			if(data.success){
				history.go(0);
//				var state = $("#state").val();
//				var workId = $("#workId").val();
//				var pageNo = $("#pageNo").val();
//				console.log(state);
//				console.log(workId);
//				console.log(pageNo);
//				window.location.href="mannagedetail?id="+workId+"&state="+state+"&pageNo="+pageNo;
			}
		},
		error: function(data) {
//			alert("22");
			history.go(0);
			return;
		}
	});
}
// 点击拒绝
function refuse(id){
	var params = {};
	params.id = id;
	$.ajax({
		type: "post",
		url: _basePath + "/apply/refuse",
		data: params,
		dataType:"json",
		async: true,
		success: function(data) {
			
			data = $.parseJSON(data);
			if(data.success){
				var state = $("#state").val();
				var workId = $("#workId").val();
				var pageNo = $("#pageNo").val();
				window.location.href="mannagedetail?id="+workId+"&state="+state+"&pageNo="+pageNo;
			}
		},
		error: function(data) {
//			alert("22");
			return;
		}
	});
}
// 提交前检查
function subCheck(){
	// 验证密码
	if(!checkpwd()){
		return false;
	}
}

// 密码检查
function checkpwd(){
	var pwd = $("#pwd").val();
	if(isNullStr(pwd)){
		alert("支付密码不能为空");
		return false;
	}
	if(checkTextSpace(pwd)){
		alert("支付密码不能包含空格");
		return false;
	}
	if(overLengthStr(pwd, 10)){
		alert("支付密码超长");
		return false;
	}
	return true;
}
// 验证输入金额
function checkMoney(){
	
	var money = $("#showMoney").val();
	if(isNullStr(money)){
		alert("金额不能为空");
		return false;
	}
	if(checkTextSpace(money)){
		alert("金额不能包含空格");
		return false;
	}
	if(isPositiveDouble(money)){
		alert("请输入正确金额");
		return false;
	}
	if(overLengthStr(money, 6)){
		alert("金额超长");
		return false;
	}
	return true;
}






