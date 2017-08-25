$(document).ready(function() {
	
	$(".dianji,.guanbi").on("click",function(){
		$("body").css({overflow:""}); // 启用滚动条
        $(".jz-tanchu,.shadow").addClass("hide1");
    })
    
    $(".fabu_jz").on("click", function () {
//    	$("body").css({overflow:"hidden"}); // 禁用滚动条
    	location.href = "#specialThePoint"; 
        $(".jz-tanchu,.shadow").removeClass("hide1");
    });
	
	$("#tanchu1").mouseover(function(){
        $("#tanchu").toggle();
    });
	$("#tanchu1").mouseout(function(){
        $("#tanchu").toggle();
    });
	
	var workTitle = "";
	
	// 当选中薪资单位为小时的时候，工作时长文本框显示
	$("#moneyType00").click(function(){
		$("#hidWorkHour").css('display','block');
		$("#moneyText").text("元/时");
	});
	// 当选中薪资单位为天的时候，工作时长文本框清空隐藏
	$("#moneyType01").click(function(){
		$("#workHour").val("");
		$("#hidWorkHour").css('display','none');
		$("#moneyText").text("元/天");
	});
	
	// 检查标题
	$("#workTitle").change(function(){
		checkWorkTitle();
	});
	// 检查岗位类别
	$("#jobType").change(function(){
		checkJobType();
	});
	// 检查招聘人次
	$("#peopleNum").change(function(){
		checkPeopleNum();
	});
	// 检查工作时长
	$("#workHour").change(function(){
		checkWorkHour();
	});
	// 检查薪资待遇
	$("#money").blur(function(){
		checkMoney();
	});
	// 检查工作内容
	$("#workDetail").change(function(){
		checkWorkDetail();
	});
	// 检查任职要求
	$("#require").change(function(){
		checkRequire();
	});
	// 检查联系人
	$("#contacts").change(function(){
		checkContacts();
	});
	// 检查联系电话
	$("#contactsMobile").change(function(){
		checkContactsMobile();
	});
	// 检查集合地址
	$("#addressDetail").change(function(){
		if(checkAddressDetail()){
			$("#text_").val($("#addressDetail").val());
			searchByStationName();
		}
	});
	
	// 市
	$("#city").change(function(){
		checkCity();
	});
	// 区
	$("#area").change(function(){
		checkArea();
	});
	
	// 检查兼职日期
	$("#wStartDate").blur(function(){
		checkDay();
	});
	$("#wEndDate").blur(function(){
		checkDay();
		
		var startTime=$("#workStartDate").val();  
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
	    var endTime=$("#workEndDate").val();  
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
	    if(end - start > 0){  
	        $("#acdShow").show();
	    } else {
	    	 $("#acdShow").hide();
	    }
	});
	$("#workStartTime").blur(function(){
		checkTime();
	});
	$("#workEndTime").blur(function(){
		checkTime();
	});
	
	
	
	$("#city").change(function(){
		var params = {};
		params.cityCode = $("#city").val();
		$.ajax({
			type: "post",
			url: _basePath + "/work/selectArea",
			data: params,
			dataType:"json",
			async: true, //同步方式
			success: function(data) {
						
				data = $.parseJSON(data);
				if(data.success){
					var returnMap = data.data;
					var html = "<option value=\"\">请选择</option>";
					$.each(returnMap,function(key,values){ 
						html += "<option value=\""+key+"\">"+values+"</option>";
					});
					$("#area").html(html);
				}
			},
			error: function(data) {
				alert("获取城市信息失败");
				return;
			}
		});
	});
	
	
	// 提交
	$("#subForm").click(function(){
		
		if(!checkAll()){
			alert("有必填项没有填写");
			return;
		}
		
		var allowChooseDate = "01"
		if($("input:checkbox:checked").val() == "00"){
			allowChooseDate = $("input:checkbox:checked").val();
		}
//		console.log(allowChooseDate)
		$("#workTime").val($("#workStartTime").val() + "-" + $("#workEndTime").val())
		var params = {};
		params.workTitle = $("#workTitle").val();						// 兼职名称
		params.jobType = $("#jobType").val();							// 岗位类别
		params.peopleNum = $("#peopleNum").val();						// 招聘人次
		params.moneyType = $("input[name='moneyType']:checked").val();	// 薪资单位
		params.workStartDate = $("#workStartDate").val();				// 兼职起始日期
		params.workEndDate = $("#workEndDate").val();					// 兼职结束日期
		params.workStartTime = $("#workStartTime").val();				// 工作起始时间
		params.workEndTime = $("#workEndTime").val();					// 工作结束时间
		params.allowChooseDate = allowChooseDate;						// 是否允许用户选择日期
		params.workHour = $("#workHour").val();							// 工作时长
		params.money = $("#money").val();								// 薪资待遇
		params.sexRequire = $("#sexRequire").val();						// 性别要求
		params.workMeal = $("#workMeal").val();							// 工作餐
		params.workDetail = $("#workDetail").val();						// 工作内容
		params.require = $("#require").val();							// 任职要求
		params.contacts = $("#contacts").val();							// 联系人
		params.contactsMobile = $("#contactsMobile").val();				// 联系电话
		params.interview = $("#interview").val();						// 是否面试
		params.city = $("#city").val();									// 市
		params.cityName =  $("#city").find("option:selected").text();	// 市名称
		params.area = $("#area").val();									// 区
		params.areaName = $("#area").find("option:selected").text();    // 区名称
		params.addressDetail = $("#addressDetail").val();				// 详细地址
		params.longitude = $("#longitude").val();						// 经度
		params.latitude = $("#latitude").val();							// 纬度
		params.releaseType = $("input[name='releaseType']:checked").val(); // 发布方式
		params.workTime = $("#workTime").val();	
		
		$.ajax({
			type: "post",
			url: _basePath + "/work/releaseJobInfo",
			data: params,
			dataType:"json",
			async: true, //同步方式
			success: function(data) {
				data = $.parseJSON(data);
				if(data.success){
					window.location.href = "parttimeMannage";
				} else {
					alert(data.errmsg);
					if(data.errcode == 'login_err'){
						window.location.href = "compayinfo/login";
					}
				}
			},
			error: function(data) {
				alert("系统错误");
				return;
			}
		});
	});
});

//提交前验证检查
function checkAll(){
	
	var reB = true
	if(!checkWorkTitle()){// 标题
		reB = false;
	}
	if(!checkJobType()){// 岗位类别
		reB = false;
	}
	if(!checkPeopleNum()){// 招聘人次
		reB = false;
	}
	if(!checkMoneyType()){// 薪资单位
		reB = false;
	}
	var mt = $("input[name='moneyType']:checked").val();
	if(mt == '00'){
		if(!checkWorkHour()){// 工作时长
			reB = false;
		}
	}
	if(!checkMoney()){// 薪资待遇
		reB = false;
	}
	if(!checkSexRequire()){// 性别要求
		reB = false;
	}
	if(!checkWorkMeal()){// 工作餐
		reB = false;
	}
	if(!checkWorkDetail()){// 工作内容
		reB = false;
	}
	if(!checkRequire()){// 任职要求
		reB = false;
	}
	if(!checkContacts()){// 联系人
		reB = false;
	}
	if(!checkContactsMobile()){// 联系电话
		reB = false;
	}
	if(!checkInterview()){// 是否面试
		reB = false;
	}
	if(!checkCity()){// 市
		reB = false;
	}
	if(!checkArea()){// 区
		reB = false;
	}
	if(!checkAddressDetail()){// 集合地
		reB = false;
	}
	if(!checkDay()){// 起始结束日期检查
		reB = false;
	}
	if(!checkTime()){// 起始结束时间检查
		reB = false;
	}
	if(!checkLongitude() || !checkLatitude()){// 经度\纬度
		if(!confirm("未在地图中找到集合地址，提交后无法显示地图。是否继续提交？")){
			reB = false;
		}
	}
	return reB;
}

// 验证标题
function checkWorkTitle(){
	var reB = true;
	var workTitle = $("#workTitle").val();
	if(workTitle == '' || workTitle == null){
		$("#checkWorkTitle").text("标题不能为空");
		reB = false;
	} else if(checkTextSpace(workTitle)){
		$("#checkWorkTitle").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(workTitle, 20)){
		$("#checkWorkTitle").text("标题长度不能超过20字");
		reB = false;
	} else if(specialStr(workTitle)){
		$("#checkWorkTitle").text("标题不能存在非法字符");
		reB = false;
	} else {
		$("#checkWorkTitle").text("");
	}
	return reB;
}
// 验证岗位类别
function checkJobType(){
	var reB = true;
	var jobType = $("#jobType").val();
	if(jobType == null || jobType == ''){
		$("#checkJobType").text("岗位类别不能为空");
		reB = false;
	} else {
		$("#checkJobType").text("");
	}
	return reB;
}
// 验证招聘人次
function checkPeopleNum(){
	var reB = true;
	var peopleNum = $("#peopleNum").val();
	if(peopleNum == '' || peopleNum == null){
		$("#checkPeopleNum").text("招聘人次不能为空");
		reB = false;
	} else if(checkTextSpace(peopleNum)){
		$("#checkPeopleNum").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(peopleNum, 6)){
		$("#checkPeopleNum").text("招聘人数不能超过6位整数");
		reB = false;
	} else if(isPositiveInteger(peopleNum)){
		$("#checkPeopleNum").text("招聘人数必须为正整数");
		reB = false;
	} else {
		$("#checkPeopleNum").text("");
	}
	return reB;
}
// 验证薪资单位
function checkMoneyType(){
	var reB = true;
	var moneyType = $("input[name='moneyType']:checked").val();
	if(moneyType == null || moneyType == ''){
		$("#checkMoneyType").text("薪资单位不能为空");
		reB = false;
	}
	return reB;
}
//验证工作时长
function checkWorkHour(){
	var reB = true;
	var workHour = $("#workHour").val();
	if(workHour == '' || workHour == null){
		$("#checkWorkHour").text("工作时长不能为空");
		reB = false;
	} else if(checkTextSpace(workHour)){
		$("#checkWorkHour").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(workHour, 5)){
		$("#checkWorkHour").text("不能超过5位数");
		reB = false;
	} else if(isPositiveDouble(workHour)){
		$("#checkWorkHour").text("必须为正数（允许两位小数）");
		reB = false;
	} else if(workHour == 0){
		$("#checkWorkHour").text("工作时长不能为0");
		reB = false;
	} else {
		$("#checkWorkHour").text("");
	}
	return reB;
}
//验证薪资待遇
function checkMoney(){
	var reB = true;
	var money = $("#money").val();
	if(money == '' || money == null){
		$("#checkMoney").text("薪资待遇不能为空");
		reB = false;
	} else if(checkTextSpace(money)){
		$("#checkMoney").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(money, 10)){
		$("#checkMoney").text("不能超过10位数");
		reB = false;
	} else if(isPositiveDouble(money)){
		$("#checkMoney").text("必须为正数（允许两位小数）");
		reB = false;
	} else if(money == 0){
		$("#checkMoney").text("薪资待遇不能为0");
		reB = false;
	} else if(!moneyRule()){
//		$("#checkMoney").text("薪资待遇不能为0");
		reB = false;
	} else {
		$("#checkMoney").text("");
	}
	return reB;
}


//最低薪资规则
function moneyRule(){
	
	var re = true;
	// 薪资单位
	var moneyType = $("input[name='moneyType']:checked").val();
	// 工作时长
	var workHour = $("#workHour").val();
	// 薪资待遇
	var money = $("#money").val();
//	money = Number(money)
	if(moneyType == '00'){
		// 小时
		if(workHour != ''){
			if(workHour <= 1 && money < 25){
				$("#checkMoney").text("时薪不能低于25元/时");
				$("#money").val('');
				re = false;
			}
			if(workHour > 1 && workHour < 3 && money < 20){
				$("#checkMoney").text("时薪不能低于20元/时");
				$("#money").val('');
				re = false;
			}
			if(workHour >= 3 && workHour < 5 && money < 20){
				$("#checkMoney").text("时薪不能低于15元/时");
				$("#money").val('');
				re = false;
			}
			if(workHour >= 5 && money < 15){
				$("#checkMoney").text("时薪不能低于10元/时");
				$("#money").val('');
				re = false;
			}
		} else {
			$("#checkMoney").text("请先输入工作时长");
			$("#money").val('');
			re = false;
		}
	} else {
		// 天
		if(money < 70){
			$("#checkMoney").text("薪资待遇不能低于70元");
			$("#money").val('');
			re = false;
		}
	}
	return re;
	
	/*
	发布日薪不低于70元/天；
	工作时长 ≤ 1小时，时薪不低于25元/时；
	1小时 < 工作时长 < 3小时，时薪不低于20元/时；
	3小时 ≤ 工作时长 < 5小时，时薪不低于15元/时；
	工作时长 ≥ 5小时，时薪不低于10元/时；
	*/
	
}

//验证性别要求
function checkSexRequire(){
	var reB = true;
	var sexRequire = $("#sexRequire").val();
	if(sexRequire == null || sexRequire == ''){
		$("#checkSexRequire").text("性别要求不能为空");
		reB = false;
	} else {
		$("#checkSexRequire").text("");
	}
	return reB;
}
//验证工作餐
function checkWorkMeal(){
	var reB = true;
	var workMeal = $("#workMeal").val();
	if(workMeal == null || workMeal == ''){
		$("#checkWorkMeal").text("工作餐不能为空");
		reB = false;
	} else {
		$("#checkWorkMeal").text("");
	}
	return reB;
}
//验证工作内容
function checkWorkDetail(){
	var reB = true;
	var workDetail = $("#workDetail").val();
	if(workDetail == '' || workDetail == null){
		$("#checkWorkDetail").text("工作内容不能为空");
		reB = false;
	} else if(checkTextSpace(workDetail)){
		$("#checkWorkDetail").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(workDetail, 300)){
		$("#checkWorkDetail").text("不能超过300字");
		reB = false;
	} else if(specialStr(workDetail)){
		$("#checkWorkDetail").text("工作内容不能存在非法字符");
		reB = false;
	} else {
		$("#checkWorkDetail").text("");
	}
	return reB;
}
//验证任职要求
function checkRequire(){
	var reB = true;
	var require = $("#require").val();
	if(require == '' || require == null){
		$("#checkRequire").text("任职要求不能为空");
		reB = false;
	} else if(checkTextSpace(require)){
		$("#checkRequire").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(require, 300)){
		$("#checkRequire").text("不能超过300字");
		reB = false;
	} else if(specialStr(require)){
		$("#checkRequire").text("任职要求不能存在非法字符");
		reB = false;
	} else {
		$("#checkRequire").text("");
	}
	return reB;
}
//验证联系人
function checkContacts(){
	var reB = true;
	var contacts = $("#contacts").val();
	if(contacts == '' || contacts == null){
		$("#checkContacts").text("联系人不能为空");
		reB = false;
	} else if(checkTextSpace(contacts)){
		$("#checkContacts").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(contacts, 6)){
		$("#checkContacts").text("不能超过六个字");
		reB = false;
	} else if(specialStr(contacts)){
		$("#checkContacts").text("联系人不能存在非法字符");
		reB = false;
	} else {
		$("#checkContacts").text("");
	}
	return reB;
}
//验证联系电话
function checkContactsMobile(){
	var reB = true;
	var contactsMobile = $("#contactsMobile").val();
	if(contactsMobile == '' || contactsMobile == null){
		$("#checkContactsMobile").text("联系电话不能为空");
		reB = false;
	} else if(checkTextSpace(contactsMobile)){
		$("#checkContactsMobile").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(contactsMobile, 12)){
		$("#checkContactsMobile").text("不能超过12位");
		reB = false;
	} else if(checkPhoneAndMobile(contactsMobile)){
		$("#checkContactsMobile").text("电话号码不规范");
		reB = false;
	} else {
		$("#checkContactsMobile").text("");
	}
	return reB;
}
//验证是否面试
function checkInterview(){
	var reB = true;
	var interview = $("#interview").val();
	if(interview == null || interview == ''){
		$("#checkInterview").text("是否面试不能为空");
		reB = false;
	} else {
		$("#checkInterview").text("");
	}
	return reB;
}
//验证市
function checkCity(){
	var reB = true;
	var city = $("#city").val();
	if(city == null || city == ''){
		$("#checkCity").text("市不能为空");
		reB = false;
	} else {
		$("#checkCity").text("");
	}
	return reB;
}
//验证区
function checkArea(){
	var reB = true;
	var area = $("#area").val();
	if(area == null || area == ''){
		$("#checkArea").text("区不能为空");
		reB = false;
	} else {
		$("#checkArea").text("");
	}
	return reB;
}
//验证集合地点（详细地址）
function checkAddressDetail(){
	var reB = true
	var addressDetail = $("#addressDetail").val();
//	if(addressDetail == '' || addressDetail == null){
//		$("#checkAddressDetail").text("集合地不能为空");
//		reB = false;
//	} else 
	if(checkTextSpace(addressDetail)){
		$("#checkAddressDetail").text("不能包含空格");
		reB = false;
	} else if(overLengthStr(addressDetail, 50)){
		$("#checkAddressDetail").text("不能超过50个字");
		reB = false;
	} else if(!special_s(addressDetail)){
		$("#checkAddressDetail").text("集合地不能存在非法字符");
		reB = false;
	} else {
		$("#checkAddressDetail").text("");
	}
	return reB;
}
// 检查日期
function checkDay(){
	var reB = true
	// 兼职日期
	var workStartDate = $("#workStartDate").val();
	var workEndDate = $("#workEndDate").val();
	
	if(workStartDate == '' || workStartDate == null){
		$("#checkWorkDate").text("起始日期不能为空");
		reB = false;
	} else if(checkTextSpace(workStartDate)){
		$("#checkWorkDate").text("起始日期不能包含空格");
		reB = false;
	} else if(overLengthStr(workStartDate, 10)){
		$("#checkWorkDate").text("起始日期不能超过十个字");
		reB = false;
	} else if(specialDate(workStartDate)){
		$("#checkWorkDate").text("起始日期不能存在非法字符");
		reB = false;
	} else {
		$("#checkWorkDate").text("");
	}
	if(workEndDate == '' || workEndDate == null){
		$("#checkWorkDate").text("结束日期不能为空");
		reB = false;
	} else if(checkTextSpace(workEndDate)){
		$("#checkWorkDate").text("结束日期不能包含空格");
		reB = false;
	} else if(overLengthStr(workEndDate, 10)){
		$("#checkWorkDate").text("结束日期不能超过十个字");
		reB = false;
	} else if(specialDate(workEndDate)){
		$("#checkWorkDate").text("结束日期不能存在非法字符");
		reB = false;
	} else if(reB){
		$("#checkWorkDate").text("");
	}
	return reB;
}

// 检查时间
function checkTime(){
	var reB = true
	
	// 兼职时间
	var workStartTime = $("#workStartTime").val();
	var workEndTime = $("#workEndTime").val();
	if(workStartTime == '' || workStartTime == null){
		$("#checkWorkTime").text("起始时间不能为空");
		reB = false;
	} else if(checkTextSpace(workStartTime)){
		$("#checkWorkTime").text("起始时间不能包含空格");
		reB = false;
	} else if(overLengthStr(workStartTime, 10)){
		$("#checkWorkTime").text("起始时间不能超过十个字");
		reB = false;
	} else if(specialDate(workStartTime)){
		$("#checkWorkTime").text("起始时间不能存在非法字符");
		reB = false;
	} else if(reB){
		$("#checkWorkTime").text("");
	}
	if(workEndTime == '' || workEndTime == null){
		$("#checkWorkTime").text("结束时间不能为空");
		reB = false;
	} else if(checkTextSpace(workEndTime)){
		$("#checkWorkTime").text("结束时间不能包含空格");
		reB = false;
	} else if(overLengthStr(workEndTime, 10)){
		$("#checkWorkTime").text("结束时间不能超过十个字");
		reB = false;
	} else if(specialDate(workEndTime)){
		$("#checkWorkTime").text("结束时间不能存在非法字符");
		reB = false;
	} else if(reB){
		$("#checkWorkTime").text("");
	}
	return reB;
}
// 检查地图坐标经度
function checkLongitude(){
	var returnb = true;
	var longitude = $("#longitude").val();
	if(longitude == '' || longitude == null){
//		alert("地图经度无法获取")
		returnb = false;
	} else if(checkTextSpace(longitude)){
//		alert("地图经度存在空格")
		returnb = false;
	} else if(overLengthStr(longitude, 50)){
//		alert("地图经度超长")
		returnb = false;
	} else if(specialMap(longitude)){
//		alert("地图经度存在非法字符")
		returnb = false;
	}
	return returnb;
}
// 检查地图坐标纬度
function checkLatitude(){
	var returnb = true;
	var latitude = $("#latitude").val();
	if(latitude == '' || latitude == null){
//		alert("地图纬度无法获取")
		returnb = false;
	} else if(checkTextSpace(latitude)){
//		alert("地图纬度存在空格")
		returnb = false;
	} else if(overLengthStr(latitude, 50)){
//		alert("地图纬度超长")
		returnb = false;
	} else if(specialMap(latitude)){
//		alert("地图纬度存在非法字符")
		returnb = false;
	}
	return returnb;
}



////百度地图API功能
//function loadJScript() {
//	var script = document.createElement("script");
//	script.type = "text/javascript";
//	script.src = "http://api.map.baidu.com/api?v=2.0&ak=YSVNtKs8ZniM2lnx4ahlDTQQDr5N3CLF&callback=init";
//	document.body.appendChild(script);
//}
//function init() {
//	var map = new BMap.Map("allmap");            // 创建Map实例
////	map.centerAndZoom("上海",15);      			 // 初始化地图,用城市名设置地图中心点
//	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
//	map.enableScrollWheelZoom();                 // 启用滚轮放大缩小
//	
//	
//	// 添加带有定位的导航控件
//	var navigationControl = new BMap.NavigationControl({
//		// 靠左上角位置
//		anchor: BMAP_ANCHOR_TOP_LEFT,
//		// LARGE类型
//		type: BMAP_NAVIGATION_CONTROL_LARGE,
//		// 启用显示定位
//		enableGeolocation: true
//	});
//	map.addControl(navigationControl);
//	
//	var localSearch = new BMap.LocalSearch(map);
//	localSearch.enableAutoViewport(); //允许自动调节窗体大小
//	
//	
//}  
//window.onload = loadJScript;  //异步加载地图


