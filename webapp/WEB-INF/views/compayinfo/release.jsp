<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/common/common.jsp"%>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	<link rel="stylesheet" href="${basePath}/css/tab.css" />
	<link rel="stylesheet" href="${basePath}/css/laydate.css" />
	<link type="text/css" rel="stylesheet" href="${basePath}/js/jedate/skin/jedate.css">
	
	<script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/laydate.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
	<script type="text/JavaScript" src="${basePath}/js/My97DatePicker/WdatePicker.js"></script>
	<script src="http://api.map.baidu.com/api?v=2.0&ak=YSVNtKs8ZniM2lnx4ahlDTQQDr5N3CLF" type="text/javascript"></script>
	<script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
	<script type="text/javascript" src="${basePath}/js/compayinfo/release.js"></script>
	
	<script type="text/javascript" src="${basePath}/js/jedate/jquery.jedate.js"></script>
	
	
	<style type="text/css">
		/* css 注释说明：设置了浏览器宽度不小于1366px时 abc 显示180 */ 
		@media screen and (min-width: 1367px) { 
		.jz-tanchu{top:180%;}  
		}
		/* 设置了浏览器宽度不大于1367px时 .jz-tanchu 显示210 */ 
		@media screen and (max-width: 1366px) { 
		.jz-tanchu{top:210%;}  
		} 
	</style>
</head>
<body>
<jsp:include page="/common/header.jsp" />
	<form id="inputSub" action="" method="POST">
		<div class="findjob-contain">
			<div class="findjob-contain_inner">
				<div class="contain_inner_top">
					<ul>
						<li><span style="color: #c5c5c5;">Personal center</span> <span
							style="font-size: 30px;">个人中心</span>
						</li>
						<li>发布兼职<span class="jianTou"></span>发布普通兼职</li>
					</ul>
				</div>
				<div class="contain_inner_center">
					<jsp:include page="../compayinfo/common/left.jsp" />
					<ul class="jz-center">
						<li class="jz-title">
							<p>
								<b>发布兼职</b>
							</p>
							<p>|岗位详情</p>
						</li>
						<li class="ja-news">
							<p>
								<label for="name">兼职名称</label> 
								<input type="text" id="workTitle" name="workTitle" placeholder="请输入兼职名称" />
								<label style="color: #CC0033;font-size: 10px;" id="checkWorkTitle"></label>
							</p>
							<p>
								<label for="class">岗位类别</label> 
								<select id="jobType" name="jobType">
									<option value="">请选择</option>
									<c:forEach items="${jobTypeMap }" var="item">
										<option value="${item.key}">${item.value }</option>
									</c:forEach>
								</select>
								<label style="color: #CC0033;font-size: 10px;" id="checkJobType"></label>
							</p>
							<p>
								<label for="number">招聘人次</label> 
								<input type="text" id="peopleNum" name="peopleNum" placeholder="请输入招聘人次" />
								<label style="color: #CC0033;font-size: 10px;" id="checkPeopleNum"></label>
							</p>
							<p>
								<label>薪资单位</label> 
								<c:forEach items="${moneyTypeMap }" var="item">
									<input type="radio" id="moneyType${item.key }" value="${item.key }" name="moneyType" checked="checked" />元/${item.value }&nbsp;
								</c:forEach>
								&nbsp;<a style="color: #1e90ff;" id="tanchu1">最低薪资说明</a>
								<br/><label style="color: #CC0033" id="checkMoneyType"></label>
							</p>
							<ul id="tanchu">
								<li style="color: #EE0000;font-size: 20px;font-weight:bold;"> 薪资标准</li>
								<li>发布日薪不低于70元/天；</li>
								<li>工作时长 ≤ 1小时，时薪不低于25元/时；</li>
								<li>1小时 < 工作时长 < 3小时，时薪不低于20元/时；</li>
								<li>3小时 ≤ 工作时长 < 5小时，时薪不低于15元/时；</li>
								<li>工作时长 ≥ 5小时，时薪不低于10元/时；</li>
			                </ul>
							<p>
								<label>兼职日期</label>
								<input id="wStartDate" placeholder="起始日期" class="laydate-icon data" onFocus="var endDate=$dp.$('wEndDate');WdatePicker({onpicked:function(){wEndDate.focus();$dp.$('workStartDate').value=this.value;},maxDate:'#F{$dp.$D(\'wEndDate\')}',minDate:'%y-%M-{%d+1}',readOnly:true})"/>
								<img src="${basePath}/image/data1.png" alt="" style="position: relative;left:-40px;top:5px;" />
								<input type="hidden" id="workStartDate" name="workStartDate" value="" />
								<span style="margin-right: 5px;"><b>至</b> </span>
								<input id="wEndDate" placeholder="结束日期" class="laydate-icon data" onFocus="WdatePicker({onpicked:function(){$dp.$('workEndDate').value=this.value;},minDate:'#F{$dp.$D(\'wStartDate\')}',readOnly:true})" />
								<img src="${basePath}/image/data1.png" alt="" style="position: relative;left:-40px;top:5px;" />
								<input type="hidden" id="workEndDate" name="workEndDate" value="" />
								<label style="color: #CC0033;font-size: 10px;" id="checkWorkDate"></label>
								
							</p>
							<p id="acdShow" style="display: none"><input type="checkbox" name="allowChooseDate" value='00'/>&nbsp;允许兼职者挑选兼职日期 </p>
							<p>
								<label>兼职时间</label> 
								<input class="laydate-icon data" style="width: 200px" id="workStartTime"  type="text" placeholder="起始时间" onclick="jeDateShow(this)" readonly>
								<!-- <input id="wStartTime" placeholder="起始时间" class="laydate-icon data" onFocus="WdatePicker({onpicked:function(){$dp.$('workStartTime').value=this.value;}, dateFmt:'HH:mm',readOnly:true,maxDate:'#F{$dp.$D(\'wEndTime\')}'})" /> -->
								<!-- <input type="text" id="workStartTime" name="workStartTime" value="" /> -->
								<b>至</b> </span> 
								<input class="laydate-icon data" style="width: 200px" id="workEndTime"  type="text" placeholder="结束时间" onclick="jeDateShow(this)"  readonly >
								<!-- <input id="wEndTime" placeholder="结束时间" class="laydate-icon data" onFocus="WdatePicker({onpicked:function(){$dp.$('workEndTime').value=this.value;},dateFmt:'HH:mm',minDate:'#F{$dp.$D(\'wStartTime\')}',readOnly:true})" /> -->
								<!-- <input type="text" id="workEndTime" name="workEndTime" value="" /> -->
								<input type="hidden" id="workTime" name="workTime" value="" />
								<label style="color: #CC0033;font-size: 10px;" id="checkWorkTime"></label>
							</p>
							<p id="hidWorkHour" style="display: none">
								<label>工作时长</label> 
								<input type="text" id="workHour" name="workHour" placeholder="请输入有效的工作时长" />
								<span class="jz-daymoey">小时</span>
								<label style="color: #CC0033;font-size: 10px;" id="checkWorkHour"></label>
							</p>
							<p>
								<label>薪资待遇</label> 
								<input type="text" id="money" name="money" placeholder="请输入薪资待遇" />
								<span class="jz-daymoey" id="moneyText" name="moneyText">元/天</span>
								<label style="color: #CC0033;font-size: 10px;" id="checkMoney"></label>
							</p>
							<p>
								<label for="class1">性别要求</label> 
								<select id="sexRequire" name="sexRequire">
									<c:forEach items="${sexRequireMap}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
								<br/><label style="color: #CC0033" id="checkSexRequire"></label>
							</p>
							<p>
								<label for="class2" style="margin-right: 28px;">工作餐</label> 
								<select id="workMeal" name="workMeal">
									<c:forEach items="${mealMap}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
								<br/><label style="color: #CC0033" id="checkWorkMeal"></label>
							</p>
							<p class="wenben">
								<label style="position: relative;top:-60px;">工作内容</label>
								<textarea id="workDetail" name="workDetail" cols="80" rows="5" placeholder="请描述兼职的主要内容。例如：在商业圈发传单"></textarea>
								<br/><label style="color: #CC0033;font-size: 10px;" id="checkWorkDetail"></label>
							</p>
							<p class="wenben">
								<label style="position: relative;top:-60px;">任职要求</label>
								<textarea id="require" name="require" cols="80" rows="5" placeholder="请描述兼职的具体要求。例如：兼职工作态度积极"></textarea>
								<br/><label style="color: #CC0033;font-size: 10px;" id="checkRequire"></label>
							</p>
						</li>
						<li class="jz-title">
							<p>|联系方式</p>
						</li>
						<li class="ja-news" id="specialThePoint">
							<p>
								<label for="name1" style="margin-right: 28px;">联系人</label> 
								<input type="text" id="contacts" name="contacts" placeholder="请输入联系人姓名" />
								<label style="color: #CC0033;font-size: 10px;" id="checkContacts"></label>
							</p>
							<p>
								<label for="name2">联系电话</label> 
								<input type="text" id="contactsMobile" name="contactsMobile" placeholder="请输入联系人电话" />
								<label style="color: #CC0033;font-size: 10px;" id="checkContactsMobile"></label>
							</p>
						</li>
						<li class="jz-title">
							<p>|面试</p>
						</li>
						<li class="ja-news">
							<p>
								<label for="class3">是否面试</label> 
								<select id="interview" name="interview" style="width: 100px;">
									<c:forEach items="${interviewMap}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select> <span>(如对兼职人员有特殊要求，建议进行面试。不预付工资的岗位，不支持面试。)</span>
								<br/><label style="color: #CC0033;font-size: 10px;" id="checkInterview"></label>
							</p>
						</li>
						<li class="jz-title">
							<p>|集合地点</p>
						</li>
						<li class="ja-news">
							<p>
								<label>工作区域</label> 
								<select id="city" name="city" style="width: 200px;margin-right: 50px;">
									<option value="">请选择</option>
									<c:forEach items="${cMapAll}" var="item">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select> 
								<select id="area" name="area" style="width: 200px;">
									<option value="">请选择</option>
								</select>
								<label style="color: #CC0033;font-size: 10px;" id="checkCity"></label>
								<label style="color: #CC0033;font-size: 10px;" id="checkArea"></label>
							</p>
							<p style="height: 100px">
								<label for="name3">集合地点</label> 
								<input type="text" id="addressDetail" name="addressDetail" placeholder="请输入集合地点的详细地址" />
								<label style="color: #CC0033;font-size: 10px;" id="checkAddressDetail"></label>
								<span style="display: block;margin-left: 90px;margin-top: -10px;">(请输入准确的集合地点，以便兼职人员寻找)</span>
								<div style="width: 60%;height: 60%;overflow: hidden;margin:0;font-family:"微软雅黑";" id="allmap"></div>
								<!-- 要查询的地址： --><input id="text_" type="hidden" value="宁波天一广场" style="margin-right:100px;"/>
        						<!-- 查询结果(经纬度)： --><input id="result_" type="hidden" />
        						<!-- <input type="button" value="查询" onclick="searchByStationName();"/> -->
								<input type="hidden" id="longitude" value=""/>
								<input type="hidden" id="latitude" value="" />
							</p>
						</li>
						
						
						<li class="jz-title">
							<p class="fabu ">
								<span style="margin-right: 50px;cursor: pointer;" class="fabu_checked dian1 fabu_jz">发布</span>
								<!-- <span class="fabu_check dian1">预览</span> -->
							</p>
							<p class="link">友情提示：信息发布后将无法修改，请核对无误后发布！</p>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
		<!--弹出框-->
		<div class="shadow hide1"></div>
		<div class="hide1 jz-tanchu">
			<ul>
				<li class="one">
					<img src="${basePath}/image/logo11.jpg" alt="" class="logo" />
					<img src="${basePath}/image/guanbi.png" alt="" class="guanbi" />
				</li>
				<li class="two">
					<p style="color:#848484;">请选择发布方式</p>
					<p>
						<!-- <input type="radio" name="money" />预付工资  -->
						<input type="radio" id="releaseType" name="releaseType" value="00" checked="checked"/>直接发布
					</p>
				</li>
				<!-- <li class="three">预付工资：给学生提供工资保障，提高学生的上岗率与兼职质量</li> -->
				<li class="three">
					直接发布：不预付工资，减少商家资金压力，学生兼职完成，由商家自行支付工资（不在励志汪平台支付）</li>
				<li class="four">
					<a href="javascript:void(0);"><span class="che dianji">关闭</span></a>
					<a href="javascript:void(0);"><span id="subForm"  class="ched dianji">确定</span></a>
				</li>
			</ul>
		</div>
	</form>
	
	<!--底部-->
	<jsp:include page="/common/footer.jsp" />
	<script type="text/javascript">
	
	function jeDateShow(elem){
        $.jeDate(elem,{
            insTrigger:false,
            isinitVal:true,
            festival:true,
            ishmsVal:false,
            minDate: '2016-06-16 23:59:59',
            maxDate: $.nowDate(0),
            format:"hh:mm",
            zIndex:3000,
        });
    }
    
	
	$("#city").change(function(){
		$("#text_").val($("#city").find("option:selected").text());
		searchByStationName();
	});
	$("#area").change(function(){
		$("#text_").val($("#area").find("option:selected").text());
		searchByStationName();
	});
	//$("#addressDetail").change(function(){
	//	$("#text_").val($("#addressDetail").val());
	//	searchByStationName();
	//});
	
	//百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom($("#city").find("option:selected").text(), 12);
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开
	
	var localSearch = new BMap.LocalSearch(map);
	localSearch.enableAutoViewport(); //允许自动调节窗体大小
	function searchByStationName() {
	    map.clearOverlays();//清空原来的标注
	    var keyword = document.getElementById("text_").value;
	    localSearch.setSearchCompleteCallback(function (searchResult) {
	        var poi = searchResult.getPoi(0);
	        document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
	        $("#longitude").val(poi.point.lng);
	        $("#latitude").val(poi.point.lat);
	        map.centerAndZoom(poi.point, 13);
	        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度
	        map.addOverlay(marker);
	    });
	    localSearch.search(keyword);
	} 
	
	</script>
</body>
</html>
