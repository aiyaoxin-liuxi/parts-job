$(document).ready(function() {
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
		var pageNo = text;
		window.location.href = "workInfoList?pageNo="+pageNo;
	});
});
// 允许报名点击
function clickAllowApply(id){
	updateApplySwitch(id, '00');
}
// 停止报名点击
function clickNotAllowApply(id){
	updateApplySwitch(id, '01');
}

// 是否允许报名updateApplySwitch
function updateApplySwitch(id, applySwitch){
	
	var params = {};
	params.id = id;
	params.applySwitch = applySwitch;
	$.ajax({
		type: "post",
		url: _basePath + "/sys/updateApplySwitch",
		data: params,
		dataType:"json",
		async: false,
		success: function(data) {
			data = $.parseJSON(data);
			if(data.success){
		    	history.go(0);
			} else {
				alert(data.errmsg);
				history.go(0);
			}
		}
	});
}

// 后台管理点击详情
function toParttimeDetail_manage(workId, pageNo, pageSize){
	
	var state = $("#state").val();
	window.location.href = _basePath + "/sys/toParttimeDetailManage?workId=" + workId +"&pageNo=" + pageNo +
							"&pageSize=" + pageSize;
	
	
}


