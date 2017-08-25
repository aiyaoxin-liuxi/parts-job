$(document).ready(function() {
	
	if($.getUrlParam('employ') == '' || $.getUrlParam('employ') == null){
		$("#a1").addClass("active_jz");
		$("#employ").val("00");
	} else if($.getUrlParam('employ') == '00'){
		$("#a1").addClass("active_jz");
		$("#employ").val("00");
	} else if($.getUrlParam('employ') == '01'){
		$("#a2").addClass("active_jz");
		$("#employ").val($.getUrlParam('employ'));
	} else if($.getUrlParam('employ') == '02'){
		$("#a3").addClass("active_jz");
		$("#employ").val($.getUrlParam('employ'));
	} else if($.getUrlParam('employ') == '03'){
		$("#a4").addClass("active_jz");
		$("#employ").val($.getUrlParam('employ'));
	}
	
	$("#a1").click(function(){
		window.location.href="parttimeMannage?employ=00";
	});
	$("#a2").click(function(){
		window.location.href="parttimeMannage?employ=01";
	});
	$("#a3").click(function(){
		window.location.href="parttimeMannage?employ=02";
	});
	$("#a4").click(function(){
		window.location.href="parttimeMannage?employ=03";
	});
	
	
	$(".pagesinner button").click(function(){
		var text = $(this).html()
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
		var employ = $("#employ").val();
		var pageNo = text;
		window.location.href="parttimeMannage?employ="+employ+"&pageNo="+pageNo;
	});
});

