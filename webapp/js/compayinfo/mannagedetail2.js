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

function selectDate(id, date){
	alert(date)
	var state = $("#state").val();
	alert(state)
	window.location.href = "mannagedetail?id="+id+"&state="+state+"&dateStr="+date;
}

