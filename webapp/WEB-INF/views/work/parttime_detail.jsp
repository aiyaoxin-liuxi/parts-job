<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ include file="/common/common.jsp"%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" href="${basePath}/css/tab.css"/>
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
    <script src="http://api.map.baidu.com/api?v=2.0&ak=YSVNtKs8ZniM2lnx4ahlDTQQDr5N3CLF" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
    <script type="text/javascript" src="${basePath}/js/work/parttime_detail.js"></script>
    <jsp:useBean id="now" class="java.util.Date" /> 
    <style type="text/css">
        /* 本例子css */
		.detail-inner_top ul li span.jz-baoming_lx a{
		    position: absolute;
		    right: 30px;
		    color: #fff;
		    background: #BFBFBF;
		    width: 200px;
		    height: 30px;
		    line-height: 30px;
		    display: inline-block;
		    text-align: center;
		    border-radius: 5px;
		}
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp" />
<div class="jz_detail">
    <div class="detail-inner">
        <p><span style="color: #dab866;"><a href="javascript:void(0);" onclick="returnList('${pageNo}','${pageSize }','${city }','${jobType }','${area }','${type }','${sexRequire }','${jobDay }','${seachLike }');">${workInfo.cityName }兼职</a></span>/${workInfo.workTitle }</p>

        <div class="detail-inner_top">
            <ul>
                <li>
                    <span style="font-size: 20px;color: #000;"><b>${workInfo.workTitle }</b></span>
                    <span><b>（发布日期：
                    <fmt:formatDate value="${workInfo.createdate }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
                    
                    	）</b></span>
                    <span class="jz-jiesuan"><a href="javascript:void(0);">${workInfo.balanceTypeName }</a></span>
                </li>
                <li>
                    <span><b>发布企业：</b></span>
                    
                    <span style="color: #dab866;"><b>${workInfo.companyInfo.companyName }</b></span>
                    <span><img src="${basePath}/image/xing.png" alt=""/></span>
                    <span><img src="${basePath}/image/xing.png" alt=""/></span>
                    <span><img src="${basePath}/image/xing.png" alt=""/></span>
                    <span><img src="${basePath}/image/xing.png" alt=""/></span>
                    <span><img src="${basePath}/image/xing.png" alt=""/></span>
                    
                    <c:if test="${workInfo.applySwitch == '01'}">
						<span class="jz-baoming_lx">
	                    	<a href="javascript:void(0);">已停止报名</a>
	                    </span>
                    </c:if>
                    <c:if test="${workInfo.applySwitch == '00'}">
                    	 <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="nowT"/>
	                    <fmt:formatDate value="${workInfo.workStartDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="wsdT"/>
	                    <fmt:formatDate value="${workInfo.workEndDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="wedT"/>
	                    <c:if test="${workInfo.allowChooseDate == '00' }">
	                    	<c:if test="${wedT <= nowT}">
	                    		<span class="jz-baoming_lx">
			                    	<a href="javascript:void(0);">已过期</a>
			                    </span>
	                    	</c:if>
	                    	<c:if test="${wedT > nowT}">
	                    		<span class="jz-baoming">
			                    	<a href="javascript:void(0);" onclick="showSeachDate('${workInfo.id}','${workInfo.showDateList}','${workInfo.allowChooseDate}','${employCount }','${workInfo.peopleNum }');">参加报名</a>
			                    </span>
	                    	</c:if>
	                    </c:if>
	                    <c:if test="${workInfo.allowChooseDate == '01' }">
	                    	<c:if test="${wedT <= nowT}">
	                    		<span class="jz-baoming_lx">
			                    	<a href="javascript:void(0);">已过期</a>
			                    </span>
	                    	</c:if>
	                    	<c:if test="${wedT > nowT}">
	                    		<c:if test="${wsdT <= nowT}">
	                    		 	<span class="jz-baoming">
				                    	<a href="javascript:void(0);" onclick="showSeachDate('${workInfo.id}','${workInfo.showDateList}','${workInfo.allowChooseDate}','${employCount }','${workInfo.peopleNum }');">参加报名</a>
				                    </span>
		                    	</c:if>
		                    	<c:if test="${wsdT > nowT}">
		                    		  <span class="jz-baoming">
				                    	<a href="javascript:void(0);" onclick="showSeachDate('${workInfo.id}','${workInfo.showDateList}','${workInfo.allowChooseDate}','${employCount }','${workInfo.peopleNum }');">参加报名</a>
				                    </span>
		                    	</c:if>
	                    	</c:if>
	                    </c:if>
                    </c:if>
                    <%-- <span class="jz-baoming">
                    	<a href="javascript:void(0);" onclick="showSeachDate('${workInfo.id}','${workInfo.showDateList}','${workInfo.allowChooseDate}','${employCount }','${workInfo.peopleNum }');">参加报名</a>
                    </span> --%>
                </li>
                <li>
                    <span><img src="${basePath}/image/liulan.png" alt="" style="width: 20px"/></span>
                    <span style="margin-right: 25px;">浏览次数：${workInfo.workInfoStatis.loadNum }次</span>
                    <span style="position: relative;top:4px;right: 5px;">
                    <img src="${basePath}/image/fenxiang.png" alt="" style="width: 20px"/></span>
                    <span>分享</span>
                </li>
            </ul>
        </div>
        <p></p>

        <div class="detail-inner_bottom">
            <ul>
                <li style="color: #dab866;font-size: 20px;padding-bottom: 10px;"><b>|招聘信息</b></li>
                <li>工作类型：${workInfo.jobTypeName }</li>
                <li>兼职日期：<fmt:formatDate value="${workInfo.workStartDate }" type="date" pattern="MM.dd"/>-<fmt:formatDate value="${workInfo.workEndDate }" type="date" pattern="MM.dd"/>&nbsp;${workInfo.workTime }</li>
                <li>薪资待遇：<span style="color: #dab866;">${workInfo.money }元/${workInfo.moneyTypeName }</span></li>
                <li>招聘次数：<span style="color: #dab866;">${workInfo.workInfoStatis.applyNum}</span>/<fmt:parseNumber value="${workInfo.peopleNum * workInfo.workdayNum}" /></li>
                <li>工作餐：${workInfo.workMealName }</li>
                <li>性别：${workInfo.sexRequireName }</li>
                <li>结算方式：${workInfo.balanceTypeName}</li>
                <li>联系方式：
                <c:if test="${em == '' }">
                	${workInfo.contactsMobile}
                </c:if>
                <c:if test="${em == '00' }">
                	${workInfo.contactsMobile}
                </c:if>
                <span style="color: #1e90ff;">（报名后可查看号码详情和在线咨询）</span></li>
                <li>地址：${workInfo.cityName }&nbsp;${workInfo.areaName }&nbsp;${workInfo.addressDetail }</li>
                <li class="last">
                   <%--  <img src="${basePath}/image/map_de.png" alt=""/> --%>
                    <div style="width: 300px;height: 300px;overflow: hidden;margin:0;font-family:"微软雅黑";" id="allmap"></div>
                  	<input type="hidden" id="city" value="${workInfo.cityName }"/>
                  	<input type="hidden" id="longitude" value="${workInfo.longitude }"/>
					<input type="hidden" id="latitude" value="${workInfo.latitude }" />
                    <span>（地图坐标位置仅供参考）</span>
                </li>
            </ul>
            <ul>
                <li style="color: #dab866;font-size: 20px;padding-bottom: 20px;"><b>|职位描述</b></li>
                <li style=" border-bottom: dashed 1px #ddd;margin-bottom: 20px;padding-bottom: 20px">
                    <span>岗位要求</span>
                    <span>
                    ${workInfo.require }
                    </span>
                </li>
                <li>
                    <span>工作内容</span>
                    <span>
                    ${workInfo.workDetail }
                    </span>
                </li>
                <li style="color: #e60112;margin-top: 80px;">Tips：收取费用或押金的都有欺诈嫌疑，请警惕！</li>
            </ul>
        </div>

    </div>
</div>

<!--底部-->
<jsp:include page="/common/footer.jsp" />
<!--弹出框-->
<div class="shadow hide1"></div>
<div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu_2">
    <ul>
        <li class="one">
            <img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
        </li>
        <li class="five" style="font-size: 22px;"><b>确认报名</b></li>
        <li class="five">报名成功后请按时到岗；</li>
        <li class="five">报名成功后请主动联系商家</li>
        <li class="five" style="color:#e70014;line-height: 1.2;">提示：恶意缺勤者，系统将自动封号3个月，3次以上将永久封号</li>
        <li class="four">
            <a href="javascript:void(0);"><span class="che dianji">关闭</span></a>
            <a href="javascript:void(0);"><span class="ched dianji">确定</span></a>
        </li>
    </ul>
</div>
<div class="hide1 jz-tanchu jz-tanchu2 jz-tanchu5">
    <ul>
        <li class="one">
            <img src="${basePath}/image/logo11.jpg" alt="" class="logo"/>
            <span>请选择兼职日期！</span>
            <img src="${basePath}/image/guanbi.png" alt="" class="guanbi"/>
        </li>
        <li id="showDate" class="eight">
            <span class="active style_1">未找到可报名的相关兼职日期</span>
        </li>
        <li class="nine">
            <p>
                <a href="javascript:void(0);"><span class="active  style guanbi">关闭</span></a>
                <a href="javascript:void(0);"><span class="actived  style style1">确定</span></a>
            </p>

        </li>
    </ul>
</div>
<script type="text/javascript">
	//百度地图API功能
	var map = new BMap.Map("allmap");
	map.centerAndZoom($("#city").val(), 12);
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	
	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
	map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
	var marker = new BMap.Marker(new BMap.Point($("#longitude").val(), $("#latitude").val()));  // 创建标注
	map.addOverlay(marker);              // 将标注添加到地图中
	
	</script>
</body>
</html>
