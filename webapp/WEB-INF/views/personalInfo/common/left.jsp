<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="jz-left">
					<c:if test="${active == 'my_job' }">
	                <li class="three tthree">
	                    <p>
	                        <img src="${basePath}/image/jishiben.png" alt=""/>
	                        <a href="${basePath}/apply/myParttime" style="color: #fff;">我的兼职</a>
	                    </p>
	                </li>
	                </c:if>
	                <c:if test="${active != 'my_job' }">
	                <li class="three ">
	                    <p>
	                        <img src="${basePath}/image/jishiben.png" alt=""/>
	                        <a href="${basePath}/apply/myParttime">我的兼职</a>
	                    </p>
	                </li>
	                </c:if>
                <!-- 我的信息我的信息我的信息start -->
	                <c:if test="${active == 'my_inf' }">
	                <li class="three tthree">
	                    <p>
	                        <img src="${basePath}/image/zhanghu2.png" alt=""/>
	                        <a href="${basePath}/personalInfo/fillpersonalInfo" style="color: #fff;">我的信息</a>
	                        <span class="jianTou3 "></span>
	                    </p>
	                </li>
	                </c:if>
	                 <c:if test="${active != 'my_inf' }">
	                <li class="three">
	                    <p>
	                        <img src="${basePath}/image/zhanghu.png" alt=""/>
	                        <a href="${basePath}/personalInfo/fillpersonalInfo">我的信息</a>
	                        <span class="jianTou3 "></span>
	                    </p>
	                </li>
	                </c:if>
                <!-- 我的信息我的信息我的信息end -->
                
                <!-- 个人账户个人账户个人账户start -->
	                <c:if test="${active != 'my_acc' }">
	                <li class="three">
	                    <p>
	                        <img src="${basePath}/image/price.png" alt=""/>
	                        <a href="${basePath}/userAccount/accountIndex">个人账户</a>
	                    </p>
	                </li>
	                </c:if>
	                <c:if test="${active == 'my_acc'}">
	              		 <li class="three tthree">
		                    <p>
		                        <img src="${basePath}/image/price2.png" alt=""/>
		                        <a href="${basePath}/userAccount/accountIndex" style="color: #fff;">个人账户</a>
		                    </p>
		                </li>
	                </c:if>
                <!-- 个人账户个人账户个人账户end -->
               <%--  <li class="three">
                    <p>
                        <img src="${basePath}/image/bangding1.png" alt=""/>
                        <a href="${basePath}/userAccount/bindwx">账号绑定</a>
                    </p>
                </li> --%>
                
                <!-- 安全中心安全中心安全中心安全中心安全中心 -->
                <c:if test="${active == 'sec_center' }">
	                <li class="three tthree">
	                    <p>
	                        <img src="${basePath}/image/anquan2.png" alt=""/>
	                        <a href="${basePath}/compayinfo/safe" style="color: #fff;">安全中心</a>
	                    </p>
	                </li>
                </c:if>
                 <c:if test="${active != 'sec_center' }">
	                <li class="three">
	                    <p>
	                        <img src="${basePath}/image/anquan.png" alt=""/>
	                        <a href="${basePath}/compayinfo/safe">安全中心</a>
	                    </p>
	                </li>
                </c:if>
                 <!-- 安全中心安全中心安全中心安全中心安全中心 -->
            </ul>