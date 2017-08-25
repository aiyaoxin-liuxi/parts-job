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
		var workId = $("#workId").val();
		var pageNo = text;
		window.location.href="mannagedetail?id="+workId+"&state="+state+"&pageNo="+pageNo;
	});
	
});
// 点击录用
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
			} else {
				alert(data.errmsg);
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
// 点击兼职日期
function selectDate(id, date){
//	alert(date)
	var state = $("#state").val();
//	alert(state)
	window.location.href = "mannagedetail?id="+id+"&state="+state+"&dateStr="+date;
}

