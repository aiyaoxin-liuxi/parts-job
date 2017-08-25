
var appWorkId = "";
var selectedArr = "";
var allowChooseDate = "";
$(function(){
	
//    // 点击参加报名
//    $(".fabu_jz").on("click", function () {
//    	appWorkId = "";
//    	selectedArr = "";
//		allowChooseDate = "";
//    	appWorkId = $(this).attr("id");
//    	
//        $(".jz-tanchu5,.shadow").removeClass("hide1");
//        $(".style1").on("click",function(){
//            $(".jz-tanchu5,.shadow").addClass("hide1");
//            $(".jz-tanchu_2,.shadow").removeClass("hide1");
//        })
//    });
	
	// 关闭日期弹出层
//    $(".style").on("click", function () {
//    	if ($(this).hasClass("active")) {
//            $(this).addClass("actived").removeClass("active").siblings().removeClass("actived").addClass("active");
//        }
//    	$(".eight span").removeClass("actived");
//    });
	
    // 选择日期弹出层
    $(document).on("click", ".style_1", function () {
    	if($(this).hasClass("actived")){
    		$(this).removeClass("actived");
    		if(selectedArr){
    			if(selectedArr.indexOf(",") >= 0){
    				if(selectedArr.split(",")[0] == $(this).text()){
    					selectedArr = selectedArr.replace($.trim($(this).text() + ","),"");
    				} else {
    					selectedArr = selectedArr.replace($.trim("," + $(this).text()),"");
    				}
    			} else {
    				selectedArr = selectedArr.replace($.trim($(this).text()),"");
    			}
    		}
    	} else {
    		$(this).addClass("actived");
    		if(selectedArr){
    			selectedArr += ",";
    		}
    		selectedArr += $.trim($(this).text());
    	}
    	
    });
    // 提交确认
    $(".dianji").on("click", function () {
        if ($(this).hasClass("ched")) {
//            $(this).addClass("che").removeClass("ched").siblings().removeClass("che").addClass("ched");
            console.log("a: "+selectedArr);
            console.log("b："+allowChooseDate);
            var params = {};
    		params.workId = appWorkId;
    		params.applyWorkDays = selectedArr;
    		params.allowChooseDate = allowChooseDate;
    		$.ajax({
    			type: "post",
    			url: _basePath + "/apply/apply",
    			data: params,
    			dataType:"json",
    			async: false,
    			success: function(data) {
    				data = $.parseJSON(data);
    				if(data.success){
//    					if ($(this).hasClass("active")) {
//    			            $(this).addClass("actived").removeClass("active").siblings().removeClass("actived").addClass("active");
//    			        }
//    			    	$(".eight span").removeClass("actived");
//    			    	history.go(0);
    			    	window.location.href = _basePath + "/apply/myParttime";
    				} else {
    					alert(data.errmsg);
    					if(data.errcode == 'login_err'){
    						window.location.href = _basePath + "/compayinfo/login";
    					} else if(data.errcode == 'not_authenticate'){
    						window.location.href = _basePath + "/personalInfo/fillpersonalInfo";
    					}
    				}
    			}
    		});
           
        }
    })
   $(".dianji,.guanbi").on("click", function () {
        $(".jz-tanchu,.shadow").addClass("hide1");
    })
});

function showSeachDate(id, dateList, allowCDate, employNum, peopleNum){
	if(employNum >= peopleNum){
		alert("此兼职已报满");
		return;
	}
	
	appWorkId = "";
	selectedArr = "";
	allowChooseDate = "";
	appWorkId = id;
	allowChooseDate = allowCDate;
	$("#showDate").html("未找到可报名的相关兼职日期");
	$(".jz-tanchu5").height(210);
	
	if(allowChooseDate == '00'){ // 允许用户选择日期
		var html = "";
		if(null != dateList && dateList != "[]" && dateList.size != 0){
			dateList = dateList.replace("[","");
			dateList = dateList.replace("]","");
			var dateLists = dateList.split(',');
			for(var i = 0; i < dateLists.length; i++){
				if(i % 4 == 0){
					html += "<br/>";
				}
				html += "<a href='javascript:void(0);'><span class='active style_1'>"+new Date(dateLists[i]).Format("yyyy-MM-dd")+"</span></a>&nbsp;";
			}
			
			console.log((dateLists.length/4)*40)
			$(".jz-tanchu5").height(268 + (dateLists.length/4)*40);
		} else {
			html += "未找到可报名的相关兼职日期";
		}
		$("#showDate").html(html);
	    $(".jz-tanchu5,.shadow").removeClass("hide1");
	    $(".style1").on("click",function(){
	        $(".jz-tanchu5,.shadow").addClass("hide1");
	        $(".jz-tanchu_2,.shadow").removeClass("hide1");
	    })
	} else {
			
			
		if(null != dateList && dateList != "[]" && dateList.size != 0){
			dateList = dateList.replace("[","");
			dateList = dateList.replace("]","");
			var dateLists = dateList.split(',');
			selectedArr = "";
			for(var i = 0; i < dateLists.length; i++){
				if(i == 0){
					selectedArr += new Date(dateLists[i]).Format("yyyy-MM-dd");
				} else {
					selectedArr += "," + new Date(dateLists[i]).Format("yyyy-MM-dd");
				}
			}
			
			$(".jz-tanchu5,.shadow").addClass("hide1");
			$(".jz-tanchu_2,.shadow").removeClass("hide1");
		} else {
			alert("此招聘工作日内您已应聘过其他工作，不能参加报名");
		}
	}
	
}

function returnList(pageNo,pageSize,city,jobType,area,type,sexRequire,jobDay,seachLike){
	
//	var seachLike = encodeURIComponent(encodeURIComponent(seachLike));
	seachLike = encodeURIComponent(seachLike);
	window.location.href = _basePath + "/work/toQueryWorkList?pageNo=" + pageNo +
							"&pageSize=" + pageSize + "&city=" + city + "&jobType=" + jobType + 
							"&area=" + area + "&type=" + type + "&sexRequire=" + sexRequire + 
							"&jobDay=" + jobDay + "&seachLike=" + seachLike;
	
	
}
