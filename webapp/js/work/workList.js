$(document).ready(function() {

	// 获取url参数
	getUrl();

	// 设置选择样式
	setPitch();

	// 更改市
	$("#selectCity").change(function() {
		// 清空所有选择条件
		$("#hidCity").val($("#selectCity").val())
		$("#hidJobType").val("");
		$("#hidArea").val("");
		$("#hidType").val("");
		$("#hidSexRequire").val("");
		queryList();
	});
	// 选中职位
	$("#selectJobType a").click(function(e) {
		$("#hidJobType").val($(this).attr("id"));
		queryList();
	});
	// 获取区县
	$("#selectArea a").click(function(e) {
		$("#hidArea").val($(this).attr("id"));
		queryList();
	});
	// 获取类型
	$("#selectType a").click(function(e) {
		$("#hidType").val($(this).attr("id"));
		queryList();
	});
	// 获取性别
	$("#selectSexRequire").change(function(e) {
		$("#hidSexRequire").val($("#selectSexRequire").val());
		queryList();
	});
	// 获取兼职日期
	$("#selectJobDay").change(function(e) {
		$("#hidJobDay").val($("#selectJobDay").val());
		queryList();
	});
	// 获取模糊查询信息——点击事件
	$("#seachClick").click(function(e) {
		seachLikeSub();
	});
	// 获取模糊查询信息——回车事件
	$("#seachLike").bind('keydown',function(e){  
		if(event.keyCode == "13"){  
			seachLikeSub();
		}
	});
	
	$(".pagesinner button").click(function(){
		var city = $("#hidCity").val();
		var jobType = $("#hidJobType").val();
		var area = $("#hidArea").val();
		var type = $("#hidType").val();
		var sexRequire = $("#hidSexRequire").val();
		var jobDay = $("#hidJobDay").val();
		var seachLike = $("#seachLike").val();
		seachLike = encodeURIComponent(encodeURIComponent(seachLike));
		
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
		window.location.href = "toQueryWorkList?city=" + city + "&jobType="
						+ jobType + "&area=" + area + "&type=" + type + "&sexRequire="
						+ sexRequire + "&jobDay=" + jobDay + "&pageNo="+pageNo+"&seachLike="+seachLike;
	});
	
});

function testqq(){
	
}


// 模糊查找
function seachLikeSub(){
	var seachLike = $("#seachLike").val()
	if(special_s(seachLike)){
		$("#hidSeachLike").val(seachLike);
		queryList();
	} else {
		alert("模糊查询中不能存在非法字符！")
		return;
	}
}

// 获取url中参数并记录
function getUrl() {
	if($.getUrlParam('city') != null && $.getUrlParam('city') != ''){
		$("#hidCity").val($.getUrlParam('city'));
	}
	$("#hidJobType").val($.getUrlParam('jobType'));
	$("#hidArea").val($.getUrlParam('area'));
	$("#hidType").val($.getUrlParam('type'));
	$("#hidSexRequire").val($.getUrlParam('sexRequire'));
	$("#hidJobDay").val($.getUrlParam('jobDay'));
	if('null' != decodeURIComponent($.getUrlParam('seachLike'))){
		$("#hidSeachLike").val(decodeURIComponent($.getUrlParam('seachLike')));
	}
	if('null' != decodeURIComponent($.getUrlParam('seachLike'))){
		$("#seachLike").val(decodeURIComponent($.getUrlParam('seachLike')));
	}
	
}

// 按用户查询条件回填选中信息
function setPitch() {
	// 市样式
//	if (null == $.getUrlParam('city') || '' == $.getUrlParam('city')) {
////		$("#selectCity").val("beijing");
//		$("#showCity").html("北京兼职");
//	} else {
////		$("#selectCity").val($.getUrlParam('city'));
////		$("#showCity").html($.getUrlParam('city')+"兼职");
//		$("#hidCity").val($.getUrlParam('city'));
//	}
	// 职位样式
	$("#selectJobType a").removeClass("active2");
	if (null == $.getUrlParam('jobType') || '' == $.getUrlParam('jobType')) {
		$("#selectJobType a:first-child").addClass("active2");
	} else {
		$("#" + $.getUrlParam('jobType') + "").addClass("active2");
	}
	// 区县样式
	$("#selectArea a").removeClass("active2");
	if (null == $.getUrlParam('area') || '' == $.getUrlParam('area')) {
		$("#selectArea a:first-child").addClass("active2");
	} else {
		$("#" + $.getUrlParam('area') + "").addClass("active2");
	}
	// 类型样式
	$("#selectType a").removeClass("active2");
	if (null == $.getUrlParam('type') || '' == $.getUrlParam('type')) {
		$("#selectType a:first-child").addClass("active2");
	} else {
		$("#" + $.getUrlParam('type') + "").addClass("active2");
	}
	// 性别样式
	if (null == $.getUrlParam('sexRequire') || '' == $.getUrlParam('sexRequire')) {
//		$("#selectSexRequire").val("");	
	} else {
		$("#selectSexRequire").val($.getUrlParam('sexRequire'));
	}
	// 兼职日期样式
	if (null == $.getUrlParam('jobDay') || '' == $.getUrlParam('jobDay')) {
		$("#selectJobDay").val("");
	} else {
		$("#selectJobDay").val($.getUrlParam('jobDay'));
	}
}

// 按条件查询
function queryList() {

	var city = $("#hidCity").val();
	var jobType = $("#hidJobType").val();
	var area = $("#hidArea").val();
	var type = $("#hidType").val();
	var sexRequire = $("#hidSexRequire").val();
	var jobDay = $("#hidJobDay").val();
	var seachLike = $("#seachLike").val();
	seachLike = encodeURIComponent(encodeURIComponent(seachLike));
	window.location.href = "toQueryWorkList?city=" + city + "&jobType="
			+ jobType + "&area=" + area + "&type=" + type + "&sexRequire="
			+ sexRequire + "&jobDay=" + jobDay + "&seachLike="+seachLike;
}

var swiper = new Swiper('.swiper-container', {
	pagination : '.swiper-pagination',
	paginationClickable : '.swiper-pagination',
	nextButton : '.swiper-button-next',
	prevButton : '.swiper-button-prev',
	// spaceBetween: 30,
	// effect: 'fade',//轮播的效果
	autoplay : 3000,
	// autoplayDisableOnInteraction : false,//用手滑动后继续自动
	// //grabCursor : true,//出现小手效果
	// speed:300
	loop : true
// 到头可以继续滑动
});

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
    					} else {
    						history.go(0);
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
// 点击详情
function toParttimeDetail(workId, pageNo,pageSize){
	
	var city = $("#hidCity").val();
	var jobType = $("#hidJobType").val();
	var area = $("#hidArea").val();
	var type = $("#hidType").val();
	var sexRequire = $("#hidSexRequire").val();
	var jobDay = $("#hidJobDay").val();
	var seachLike = $("#seachLike").val();
	seachLike = encodeURIComponent(encodeURIComponent(seachLike));
	window.location.href = _basePath + "/work/toParttimeDetail?workId=" + workId +"&pageNo=" + pageNo +
							"&pageSize=" + pageSize + "&city=" + city + "&jobType=" + jobType + 
							"&area=" + area + "&type=" + type + "&sexRequire=" + sexRequire + 
							"&jobDay=" + jobDay + "&seachLike=" + seachLike;
	
	
}

// var city = ""; // 市（必填）
// var jobType= ""; // 工作类型
// var area= ""; // 区县
// var type= ""; // 类型
// var sexRequire = "";// 性别
// var jobDay = ""; // 兼职日期
//
// $(document).ready(function(){
// city = $('#selectCity').val();
//	
// $("#hidJobDay").val(jobDay);
// $("#hidSexRequire").val(sexRequire);
// queryList();
// });
//
// function queryList(){
// city = $('#selectCity').val();
// jobDay = $('#hidJobDay').val();
// sexRequire = $('#hidSexRequire').val();
//	
// var params = {};
// params.pageNo = "";
// params.pageSize = "";
// params.city = city;
// params.area = "";
// params.type = "";
// params.sexRequire = "";
// params.jobDay = "";
//	
// $.ajax({
// type: "post",
// url: _basePath + "/work/queryWorkList",
// data: params,
// dataType:"json",
// async: false, //同步方式
// success: function(data) {
//			
// data = $.parseJSON(data);
//			
// if(data.success){
//				
// var returnMap = data.data;
//
// var j = 1;
// var table = document.getElementById("showTable");
// var html = "";
// $.each(returnMap.workList, function (i, item) {
//	            
//	            
// html += "<li>";
// html += "<p class=\"jz-left\"><span><img
// src=\"../image/app_downloadCom.jpg\"/></span></p>";
// html += "<p class=\"jz-center\">";
// html += "<span style=\"color: #000;font-size: 20px;\">" +
// returnMap.workList[i].workTitle + "</span>";
// html += "<span><img
// src=\"../image/data.png\"/>工作时间："+returnMap.workList[i].workStartDate+"-"+returnMap.workList[i].workEndDate+"&nbsp;"+returnMap.workList[i].workTime+"</span>";
// html += "<span><img src=\"../image/person.png\" />招聘人数：<a style=\"color:
// #dab866;\">82</a>/"+returnMap.workList[i].peopleNum+"</span>";
// html += "<span><img
// src=\"../image/position.png\"/>工作地点："+returnMap.workList[i].city+"&nbsp;"+returnMap.workList[i].area+"&nbsp;"+returnMap.workList[i].addressDetail+"</span>";
// html += "<span><img
// src=\"../image/jiesuan.png\"/>结算方式："+returnMap.workList[i].balanceTypeName+"<a
// class=\"jz-jiesuan\">当天结算</a></span></p>";
// html += "<p class=\"jz-right\"><span>"+returnMap.workList[i].money+"元/<a
// style=\"color: #dab866;font-size:
// 18px;\">"+returnMap.workList[i].moneyTypeName+"</a></span>";
// html += "<span class=\"jz-check\"><a href=\"\" style='color:
// #fff;'>参加报名</a></span></p>";
//	            	
// html += "</li>";
//	            	
// });
// $("#showTable").html(html);
// return;
// } else {
// alert(data.errmsg);
// return;
// }
//			
// },
// error: function(data) {
// alert(data.errmsg);
// $("#showTable").html("<h3>未找到相关数据</h3>");
// return;
// }
// });
// }
// //点击职位
// function cJobType(id){
// $("#hidJobType").val(id);
// }
// // 点击区县
// function cArea(id){
// $("#hidArea").val(id);
// }
// // 点击类型
// function cType(id){
// $("#hidType").val(id);
// }
//
//
//
//

