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
	<link type="text/css" rel="stylesheet" href="${basePath}/css/tab.css" />
	<link type="text/css" rel="stylesheet" href="${basePath}/css/swiper-3.4.1.min.css" />
    <script type="text/javascript" src="${basePath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${basePath}/js/swiper-3.4.1.jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/baseJs.js"></script>
	<script type="text/javascript" src="${basePath}/js/work/workList.js"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.SuperSlide.2.1.1.js"></script>
    <jsp:useBean id="now" class="java.util.Date" /> 
    <style type="text/css">
        /* 本例子css */
        .slideBox {
            width: 100%;
            /*height: 230px;*/
            overflow: hidden;
            position: relative;
            /*border: 1px solid #ddd;*/
        }
        .slideBox .hd {
            height: 15px;
            overflow: hidden;
            position: absolute;
            right: 5px;
            bottom: 5px;
            z-index: 1;
        }
        .slideBox .hd ul {
            overflow: hidden;
            zoom: 1;
            float: left;
        }
        .slideBox .hd ul li {
            float: left;
            margin-right: 2px;
            width: 15px;
            height: 15px;
            line-height: 14px;
            text-align: center;
            background: #fff;
            cursor: pointer;
        }
        .slideBox .hd ul li.on {
            background: #dab866;
            color: #fff;
        }
        .slideBox .bd {
            position: relative;
            /*height: 100%;*/
            z-index: 0;
            width: 100%;
        }
        .slideBox .bd li {
            zoom: 1;
            vertical-align: middle;
            width: 100%;
        }
        .slideBox .bd img {
            width:100%;
            text-align: center;
            display: block;
        }

        /* 下面是前/后按钮代码，如果不需要删除即可 */
        .slideBox .prev,
        .slideBox .next {
            position: absolute;
            left: 3%;
            top: 50%;
            margin-top: -25px;
            display: block;
            width: 32px;
            height: 40px;
            background: url(${basePath}/images/slider-arrow.png) -110px 5px no-repeat;
            filter: alpha(opacity=50);
            opacity: 0.5;
        }
        .slideBox .next {
            left: auto;
            right: 3%;
            background-position: 8px 5px;
        }
        .slideBox .prev:hover,
        .slideBox .next:hover {
            filter: alpha(opacity=100);
            opacity: 1;
        }
        .slideBox .prevStop {
            display: none;
        }
        .slideBox .nextStop {
            display: none;
        }
        .jz-positioninner ul.positioninner_detail li p.jz-right span.jz-check_lx{
		    background:#BFBFBF;
		    color: #fff;
		    width: 180px;
		    height: 30px;
		    line-height: 30px;
		    text-align: center;
		    font-size: 16px;
		    border-radius: 10px;
		}
    </style>
</head>
<body>
<jsp:include page="/common/header.jsp" />
<div class="jz-findbtn">
    <div id="slideBox" class="slideBox">
        <div class="hd">
            <ul>
                <li>1</li>
                <li>2</li>
                <li>3</li>
            </ul>
        </div>
        <div class="bd">
            <ul>
                <li>
                    <img src="${basePath}/images/findwork.jpg"/>
                </li>
                <li>
                    <img src="${basePath}/images/findwork.jpg"/>
                </li>
                <li>
                    <img src="${basePath}/images/findwork.jpg"/>
                </li>
            </ul>
        </div>

        <!-- 下面是前/后按钮代码，如果不需要删除即可 -->
        <a class="prev" href="javascript:void(0)"></a>
        <a class="next" href="javascript:void(0)"></a>

    </div>
    <script type="text/javascript">
        jQuery(".slideBox").slide({mainCell: ".bd ul", autoPlay: true});
    </script>
</div>

<div class="jz-findplace">
    <div class="jz-place">
        <div class="jz-placeinner">
            <img src="${basePath}/image/position.png" alt=""/>
            <span id="showCity">${nowCityName }兼职</span>
            <span class="cont">[</span>
            <select id="selectCity">
                <option value="" selected="selected">切换城市</option>
            	<c:forEach items="${cMapAll }" var="item">
					<option value="${item.key}">${item.value }</option>
				</c:forEach>
               <!--  <option value="beijing">北京</option>
                <option value="tianjin">天津</option>
                <option value="shanghai">上海</option> -->
            </select>
            <span class="cont">]</span>
        </div>
    </div>
    <div class="jz-class">
        <div class="jz-placeinner jz-classinner clear">
            <ul class="clear">
                <li class="one clear">
                    <img src="${basePath}/image/job.png" alt=""/>
                    <span>职位：</span>
                    <span class="jz-allwork" id="selectJobType">
						<a href="javascript:void(0);" id="" onclick="cJobType(this.id);" class="active2">全部</a>
						<c:forEach items="${jobTypeMap }" var="item">
							<a href="javascript:void(0);" id="${item.key }" onclick="cArea(this.id);" class="active2">${item.value }</a>
						</c:forEach>
                    </span>
                </li>
                <li class="one clear">
                    <img src="${basePath}/image/place.png" alt=""/>
                    <span>区域：</span>
                    <span class="jz-allwork" id="selectArea">
	                    <a href="javascript:void(0);" id="" onclick="cArea(this.id);" class="active2">全部</a>
                    	<c:forEach items="${aMapAll }" var="item">
                    		 <a href="javascript:void(0);" id="${item.key }" onclick="cArea(this.id);" class="active2">${item.value }</a>
                    	</c:forEach>
                    </span>
                </li>
                <li class="one clear">
                    <img src="${basePath}/image/class.png" alt=""/>
                    <span>类型：</span>
                    <span class="jz-allwork" id="selectType">
	                    <a href="javascript:void(0);" class="active2">全部</a>
	                    <!-- <a href="javascript:void(0);" id="putong" onclick="cType(this.id);">普通兼职</a> -->
                    </span>
                </li>
            </ul>
            <input type="hidden" id="hidCity" value="${nowCityCode }" />
            <input type="hidden" id="hidJobType" value="" />
            <input type="hidden" id="hidArea" value="" />
            <input type="hidden" id="hidType" value="" />
            <input type="hidden" id="hidSexRequire" value="" />
            <input type="hidden" id="hidJobDay" value="" />
            <input type="hidden" id="hidSeachLike" value="" />
        </div>
    </div>
    <div class="jz-position">
        <div class="jz-positioninner">
           <ul class="positioninner_find">
               <li class="all_job">全部职位</li>
               <li class="jz-data">
                   <span>兼职日期：</span>
                   <select id="selectJobDay">
                       <option value="" selected="selected">不限</option>
                       <option value="mt">明天</option>
                       <option value="ht">后天</option>
                       <option value="3t">三天后</option>
                   </select>
               </li>
               <li class="jz-sex">
                   <span>性别：</span>
                   <select id="selectSexRequire">
						<c:forEach items="${sexMap}" var="item" varStatus="status">
						<c:if test="${status.index == '0'}">
						<option value="${item.key}" selected="selected">${item.value }</option>
						</c:if>
						<c:if test="${status.index != '0'}">
						<option value="${item.key}">${item.value }</option>
						</c:if>
                       </c:forEach>
                   </select>
               </li>
               <li class="jz-search">
                   <input type="text" id="seachLike" name="seachLike" placeholder="请输入关键词"/>
                   <img src="${basePath}/image/find.png" alt="" id="seachClick"/>
               </li>
           </ul>
           <ul class="positioninner_detail" id="showTable">
           		<c:forEach var="item" items="${workList}" varStatus="status">
           			<li>
                    <p class="jz-left">
                    	<c:if test="${not empty item.companyInfo.logoImg}">
                    		<span><img src="${item.companyInfo.logoImg}" alt=""/></span>
                    	</c:if>
                    	<c:if test="${empty item.companyInfo.logoImg}">
                    		<span><img src="${basePath}/image/app_downloadCom.jpg" alt=""/></span>
                    	</c:if>
                        
                    </p>
                    <p class="jz-center" onclick="toParttimeDetail('${item.id}','${pageNo}','${pageSize }');">
                        <span style="color: #000;font-size: 20px;"><a href="javascript:void (0);" style="color: #000">${item.workTitle }</a></span>
                        <span>
                            <img src="${basePath}/image/data.png" alt=""/>
                          	 工作时间：
                          	 <fmt:formatDate value="${item.workStartDate}" type="both" pattern="MM.dd"/>-<fmt:formatDate value="${item.workEndDate}" type="both" pattern="MM.dd"/>
                          	 &nbsp;&nbsp;${item.workTime}
                        </span>
                        <span>
                            <img src="${basePath}/image/person.png" alt=""/>
                           	 招聘人数：<a style="color: #dab866;">${item.workInfoStatis.applyNum}</a>/<fmt:parseNumber value="${item.peopleNum * item.workdayNum}}" />
                        </span>
                        <span>
                            <img src="${basePath}/image/position.png" alt=""/>
                           	 工作地点：${item.cityName}&nbsp;${item.areaName}&nbsp;${item.addressDetail}
                        </span>
                        <span>
                            <img src="${basePath}/image/jiesuan.png" alt=""/>
                           	 结算方式：${item.balanceTypeName}
                            <a class="jz-jiesuan">${item.balanceTypeName}</a>
                        </span>
                    </p>
                    <p class="jz-center" style="margin-left: 100px;margin-top: 50px">
                    	<span><img alt="" src="${basePath }/image/liulan.png" />&nbsp;${item.workInfoStatis.loadNum}</span>
                    </p>
                    
                    <c:if test="${item.applySwitch == '01'}">
                    	<p class="jz-right">
	                        <span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
	                        <span class="jz-check_lx fabu_jz" style="cursor: pointer;"><a href="javascript:void(0)" style="color: #fff;">已停止报名</a></span>
                   		</p>
                    </c:if>
                     <c:if test="${item.applySwitch == '00'}">
                     	<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="nowT"/>
	                    <fmt:formatDate value="${item.workStartDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="wsdT"/>
	                    <fmt:formatDate value="${item.workEndDate}" type="both" dateStyle="long" pattern="yyyy-MM-dd" var="wedT"/>
	                    <c:if test="${item.allowChooseDate == '00' }">
	                    	<c:if test="${wedT <= nowT}">
	                    		<p class="jz-right">
			                        <span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
			                        <span class="jz-check_lx fabu_jz" style="cursor: pointer;"><a href="javascript:void(0)" style="color: #fff;">已过期</a></span>
	                    		</p>
	                    	</c:if>
	                    	<c:if test="${wedT > nowT}">
	                    		<p class="jz-right">
			                        <span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
			                        <span class="jz-check fabu_jz" id="${item.id}" style="cursor: pointer;" onclick="showSeachDate('${item.id}','${item.showDateList}','${item.allowChooseDate}','${item.workInfoStatis.employNum}','${item.peopleNum}');"><a href="javascript:void(0)" style="color: #fff;">参加报名</a></span>
	                    		</p>
	                    	</c:if>
	                    </c:if>
	                    <c:if test="${item.allowChooseDate == '01' }">
	                    	<c:if test="${wedT <= nowT}">
	                    		<p class="jz-right">
			                        <span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
			                        <span class="jz-check_lx fabu_jz" id="${item.id}" style="cursor: pointer;"><a href="javascript:void(0)" style="color: #fff;">已过期</a></span>
	                    		</p>
	                    	</c:if>
	                    	<c:if test="${wedT > nowT}">
	                    		<c:if test="${wsdT <= nowT}">
	                    		<p class="jz-right">
	                    		 	<span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
			                        <span class="jz-check fabu_jz" id="${item.id}" style="cursor: pointer;" onclick="showSeachDate('${item.id}','${item.showDateList}','${item.allowChooseDate}','${item.workInfoStatis.employNum}','${item.peopleNum}');"><a href="javascript:void(0)" style="color: #fff;">参加报名</a></span>
	                    		
	                    		</p>
		                    	</c:if>
		                    	<c:if test="${wsdT > nowT}">
		                    		<p class="jz-right">
				                        <span>${item.money}元/<a style="color: #dab866;font-size: 18px;">${item.moneyTypeName}</a></span>
				                        <span class="jz-check fabu_jz" id="${item.id}" style="cursor: pointer;" onclick="showSeachDate('${item.id}','${item.showDateList}','${item.allowChooseDate}','${item.workInfoStatis.employNum}','${item.peopleNum}');"><a href="javascript:void(0)" style="color: #fff;">参加报名</a></span>
		                    		</p>
		                    	</c:if>
	                    	</c:if>
	                    </c:if>
                    </c:if>
                </li>
           		</c:forEach>
           </ul>
        </div>
    </div>
	<input type="hidden" id="pageNo" value="${pageNo }"/>
    <input type="hidden" id="prePage" value="${prePage }"/>
    <input type="hidden" id="nextPage" value="${nextPage }"/>
    <div class="jz-pages">
        <ul class="pagesinner">
        	<li class="nav">
            <button <c:if test="${prePage == 0 }">disabled</c:if>>上一页</button></li>
            <c:forEach items="${navigatepageNums}" var="item" begin="0" end="4">
				<li><button>${item}</button></li>
			</c:forEach>
			<c:if test="${pages >= 6}">
				<li><button>...</button></li>
				<li><button>${pages}</button></li>
            </c:if>
            <li class="nav">
            <button <c:if test="${nextPage == 0 }">disabled</c:if>>下一页</button></li>
        </ul>
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

</body>
</html>
