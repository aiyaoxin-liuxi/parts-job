<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="jz_head">
    <div class="jz_top">
        <div class="jz_top-inner">
            <ul>
                <li class="jz_service">客服热线：<span style="color: #dab866;">02552123090</span>
	                <c:if test="${not empty  USER_NAME}">
	                	<img src="${basePath}/image/user_logo.png" alt="" style="width: 20px;position: relative;top: 3px;left: -8px;"/>
                         <span style="color: ;">欢迎您，
                         	<c:if test="${userType == '01' }">
                         		<a href="${basePath}/personalInfo/fillpersonalInfo">${USER_NAME}</a>
                         	</c:if>
                         	<c:if test="${userType == '02' }">
                         		<a href="${basePath}/compayinfo/fill">${USER_NAME}</a>
                         	</c:if>
                         	
                         
                         </span>
	                </c:if>
                </li>
                <!-- 
                <li class="jz_in"><a href="">我要吐槽</a></li>
                <i></i>
                <li><img src="image/weibo.png" alt=""/><img src="image/weixin.png" alt=""/></li>
                 -->
            </ul>
        </div>
    </div>
    <div class="jz_nav">
        <div class="jz_nav_inner">
            <ul>
                <li class="jz_nav_innerlogo" style="width: 700px;">
                    <a href="${basePath}/">
                    <img src="${basePath}/image/logo11.jpg" alt="" style="width: 185px;position: relative;top:3px;"/>
                    </a>
                </li>
                <li class="jz_link active"><a href="${basePath}/">首页</a></li>
                <li class="jz_link"><a href="${basePath}/work/toQueryWorkList">找兼职</a></li>
                <c:if test="${userType == '02' }">
                	<li class="jz_link "><a href="${basePath}/work/toReleaseJobInfo">发布兼职</a></li>
                </c:if>
                <c:if test="${not empty  LOGIN_USER_ID}">
                	 <c:if test="${userType == '01' }">
                	 	<li class="jz_link "><a href="${basePath}/personalInfo/fillpersonalInfo">个人中心</a></li>
                	 </c:if>
                	 <c:if test="${userType == '02' }">
                	 	<li class="jz_link "><a href="${basePath}/compayinfo/fill">个人中心</a></li>
                	 </c:if>
                </c:if>
                <li class="jz_link"><!--<a href="">APP下载</a>--> &nbsp;</li>
                
                <li>
                <img src="${basePath}/image/user_logo.png" alt="" style="width: 20px;position: relative;top: 3px;left: -8px;"/>
                <!-- start..... -->
                	
                <c:if test="${not empty  LOGIN_USER_ID}">
                	<a href="${basePath }/compayinfo/loginOut">退出</a>
                </c:if>
                <c:if test="${ empty  LOGIN_USER_ID}">
                	<a href="${basePath }/compayinfo/login">登录</a>
                	/
                	<a href="${basePath }/compayinfo/registerselect">注册</a></li>
                </c:if>
                <!-- end.... -->
            </ul>
        </div>
    </div>
    ${errMsg }
    <br><br>
</div>
