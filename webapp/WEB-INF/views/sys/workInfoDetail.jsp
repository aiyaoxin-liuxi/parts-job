<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.jz_manage .mange_inner ul.two table tr td{
    border-right: 1px solid #d3d3d3;
    border-bottom: 1px solid #d3d3d3;
    /*background: #f0efef;*/
    line-height: 2;
    vertical-align: middle;
}
</style>
<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<%-- <script type="text/javascript" src="${basePath}/js/jquery.js"></script> --%>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=YSVNtKs8ZniM2lnx4ahlDTQQDr5N3CLF" type="text/javascript"></script>
<script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
<script type="text/javascript" src="${basePath}/js/manager/workInfoDetail.js"></script>
<script type="text/javascript">

		function select(){
			window.location.href='${basePath}/sys/workInfoList?state='+$("#state").val();
		}
	</script>
</head>
<body>
<jsp:include page="/common/sys/header.jsp" />
<div class="jz_manage">
    <div class="mange_inner">
        <jsp:include page="/common/sys/leftSys.jsp" />
            <%-- <input type="text" name="cid" value="${companyInfo.cid}"> --%>
            
			<div class="findjob-contain" style="padding-left: 260px;">
			<a href="javascript:void(0);" onclick="reList('${state}','${pageNo}','${pageSize }');">返回列表</a>
			
			<div class="findjob-contain_inner">
				<div class="contain_inner_center">
					<ul class="jz-center">
						<li class="jz-title">
							<p>
								
								<b>修改兼职信息</b>
							</p>
						</li>
						<li class="ja-news">
							<p>
							
        						<input type="hidden" id="id" name="id" value="${workInfo.id }" />
								<label for="name">招聘标题</label> 
								<input type="text" id="workTitle" name="workTitle" value="${workInfo.workTitle }" />
								<label style="color: #CC0033;font-size: 10px;" id="checkWorkTitle"></label>
							</p>
							<p>
								<label for="class">岗位类别</label> 
								<select id="jobType" name="jobType">
									<option value="">请选择</option>
									<c:forEach items="${jobTypeMap }" var="item">
										<option value="${item.key}" <c:if test="${item.key eq workInfo.jobType }">selected</c:if> >${item.value }</option>
									</c:forEach>
								</select>
								<label style="color: #CC0033;font-size: 10px;" id="checkJobType"></label>
							</p>
							<p>
								<label for="number">招聘人次</label> 
								<input type="text" id="peopleNum" name="peopleNum" value="${workInfo.peopleNum }" />
								<label style="color: #CC0033;font-size: 10px;" id="checkPeopleNum"></label>
							</p>
							<p>
								<label for="class1">性别要求</label> 
								<select id="sexRequire" name="sexRequire">
									<c:forEach items="${sexRequireMap}" var="item">
										<option value="${item.key}" <c:if test="${item.key eq workInfo.sexRequire}">selected</c:if> >${item.value}</option>
									</c:forEach>
								</select>
								<br/><label style="color: #CC0033" id="checkSexRequire"></label>
							</p>
							<p>
								<label for="class2" style="margin-right: 28px;">工作餐</label> 
								<select id="workMeal" name="workMeal">
									<c:forEach items="${mealMap}" var="item">
										<option value="${item.key}"<c:if test="${item.key eq workInfo.workMeal}">selected</c:if>  >${item.value}</option>
									</c:forEach>
								</select>
								<br/><label style="color: #CC0033" id="checkWorkMeal"></label>
							</p>
							<p class="wenben">
								<label style="position: relative;top:-60px;">工作内容</label>
								<textarea id="workDetail" name="workDetail" cols="80" rows="5">${workInfo.workDetail }</textarea>
								<br/><label style="color: #CC0033;font-size: 10px;" id="checkWorkDetail"></label>
							</p>
							<p class="wenben">
								<label style="position: relative;top:-60px;">任职要求</label>
								<textarea id="require" name="require" cols="80" rows="5">${workInfo.require }</textarea>
								<br/><label style="color: #CC0033;font-size: 10px;" id="checkRequire"></label>
							</p>
						</li>
						<li class="jz-title">
							<p>|联系方式</p>
						</li>
						<li class="ja-news">
							<p>
								<label for="name1" style="margin-right: 28px;">联系人</label> 
								<input type="text" id="contacts" name="contacts" value="${workInfo.contacts }"/>
								<label style="color: #CC0033;font-size: 10px;" id="checkContacts"></label>
							</p>
							<p>
								<label for="name2">联系电话</label> 
								<input type="text" id="contactsMobile" name="contactsMobile" value="${workInfo.contactsMobile }" />
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
										<option value="${item.key}" <c:if test="${item.key eq workInfo.interview}">selected</c:if> >${item.value}</option>
									</c:forEach>
								</select>
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
										<option value="${item.key}" <c:if test="${item.key eq workInfo.city }">selected</c:if> >${item.value}</option>
									</c:forEach>
								</select> 
								<select id="area" name="area" style="width: 200px;">
									<c:forEach items="${aMapAll}" var="item">
										<option value="${item.key}" <c:if test="${item.key eq workInfo.area }">selected</c:if> >${item.value}</option>
									</c:forEach>
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
								<input type="hidden" id="longitude" value="${workInfo.longitude }"/>
								<input type="hidden" id="latitude" value="${workInfo.latitude }" />
							</p>
						</li>
						
						
						<li class="jz-title">
							<p class="fabu ">
								<span class="fabu_check dian1" id="subForm">修改</span>
							</p>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</div>
 </form>
<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 <script type="text/javascript">
	
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
