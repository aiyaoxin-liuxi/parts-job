$(document).ready(function() {
	
	if($.getUrlParam('queryState') == '' || $.getUrlParam('queryState') == null){
		$("#a1").addClass("active_jz");
	} else if($.getUrlParam('queryState') == '00'){
		$("#a1").addClass("active_jz");
	} else if($.getUrlParam('queryState') == '01'){
		$("#a2").addClass("active_jz");
	} else if($.getUrlParam('queryState') == '02'){
		$("#a3").addClass("active_jz");
	} else if($.getUrlParam('queryState') == '03'){
		$("#a4").addClass("active_jz");
	}
	
	$("#a1").click(function(){
		window.location.href="myParttime?queryState=00";
	});
	$("#a2").click(function(){
		window.location.href="myParttime?queryState=01";
	});
	$("#a3").click(function(){
		window.location.href="myParttime?queryState=02";
	});
	$("#a4").click(function(){
		window.location.href="myParttime?queryState=03";
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
		var queryState = $("#queryState").val();
		var pageNo = text;
		window.location.href="myParttime?queryState="+queryState+"&pageNo="+pageNo;
	});
	// 弹出层关闭
	$(".dianji,.guanbi").on("click",function(){
        $(".jz-tanchu,.shadow").addClass("hide1");
    });
    // 弹出层确认
    $(".dianji").on("click",function(){
	    if ($(this).hasClass("ched")) {
	        $(this).addClass("che").removeClass("ched").siblings().removeClass("che").addClass("ched");
	    }
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
		var pageNo = $("#pageNo").val();
		var pageNo = text;
		window.location.href = "myParttime?pageNo=" + pageNo + "&queryState="
		+ jobType + "&area=" + area + "&type=" + type + "&sexRequire="
		+ sexRequire + "&jobDay=" + jobDay + "&pageNo="+pageNo;
	});
});
// 
/**
 * 取消报名
 * applyId 申请id
 * applyWorkDate 工作的时间
 * employ 是否被录用
 * allowChooseDate 是否允许用户选择日期（00：允许，01不允许）
 * workStartDate 这个工作的起始时间
 * workEndDate 这个工作的结束时间
 */
function cleanEnroll(applyId, applyWorkDate, employ, allowChooseDate, workStartDate, workEndDate){
	if(new Date().Format("yyyy-M-d") == new Date(applyWorkDate).Format("yyyy-M-d") && employ == '01'){
		// 当天工作用户不能取消，显示弹出层
	    $(".jz-tanchu4,.shadow").removeClass("hide1");
	    return;
	}
	if(allowChooseDate == '01'){// 不允许用户选择日期
		if(new Date().Format("yyyy-M-d") > new Date(workStartDate).Format("yyyy-M-d") && employ == '01'){
			alert("此工作不能选择申请日期，您已开始工作，不能取消");
			return;
		} else {
			if(!confirm("此工作不允许选择日期，若想取消报名，则将取消所有此工作的报名申请。是否继续")){
				return;
			}
		}
	}
		
		
	var params = {};
	params.applyId = applyId;
	$.ajax({
		type: "post",
		url: _basePath + "/apply/cleanEnroll",
		data: params,
		dataType:"json",
		async: true,
		success: function(data) {
					
			data = $.parseJSON(data);
			if(data.success){
				history.go(0);
			} else {
				alert(data.errmsg);
			}
		},
		error: function(data) {
			alert("取消报名失败");
			return;
		}
	});
}

//点击详情
function toParttimeDetail(workId){
	
	window.location.href = _basePath + "/work/toParttimeDetail?workId=" + workId;
	
	
}

//function mandetail(workId){
//	if($("#state").val() == '00'){
//		window.location.href = "mannageWorkdetail?id="+workId;
//	} else if($("#state").val() == '02'){
//		var state = $("#state").val();
//		window.location.href = "mannagedetailNotPage?id="+workId+"&state="+state;
//	} else {
//		var state = $("#state").val();
//		window.location.href = "mannagedetail?id="+workId+"&state="+state;
//	}
//	
//}
