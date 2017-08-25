<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="jz-left">
				<c:if test="${active == 'pub_job' }">
	                <li class="one tthree">
	                    <a href="${basePath}/work/toReleaseJobInfo" style="color: #fff;">
	                    	<p><img src="${basePath}/image/fabu.png" alt=""/>  发布兼职</p>
	                	</a>
	                </li>
	            </c:if>
	            <c:if test="${active != 'pub_job' }">
	                <li class="one onee">
	                   <a href="${basePath}/work/toReleaseJobInfo" style="color: #fff;">
	                   		<p><img src="${basePath}/image/fabu.png" alt=""/>发布兼职</p>
	                   </a>
	                </li>
	            </c:if>
                <c:if test="${active == 'job_man' }">
	                <li class="two tthree">
	                    <p>
	                        <img src="${basePath}/image/guanli.png" alt=""/>
							<a href="${basePath}/work/parttimeMannage" style="color: #fff;">兼职管理</a>
	                    </p>
	                </li>
                </c:if>
                <c:if test="${active != 'job_man' }">
	                <li class="two ">
	                    <p>
	                        <img src="${basePath}/image/guanli.png" alt=""/>
							<a href="${basePath}/work/parttimeMannage">兼职管理</a>
	                    </p>
	                </li>
                </c:if>
                <!-- 企业信息企业信息企业信息企业信息企业信息start -->
                
                <c:if test="${active == 'com_inf' }">
	                <li class="three tthree">
	                    <p>
	                        <img src="${basePath}/image/newsqiye2.png" alt=""/>
	                        <a href="${basePath}/compayinfo/fill" style="color: #fff;">企业信息</a>
	                       <!--  <span class="jianTou3 "></span> -->
	                    </p>
	                </li>
                </c:if>
                <c:if test="${active != 'com_inf' }">
	                <li class="three">
	                    <p>
	                        <img src="${basePath}/image/newsqiye.png" alt=""/>
	                        <a href="${basePath}/compayinfo/fill">企业信息</a>
	                    </p>
	                </li>
                </c:if>
                <!-- 企业信息企业信息企业信息企业信息企业信息end -->
                 <!-- 账户信息start -->
                 <c:if test="${active == 'acc_inf' }">
	                 <li class="three tthree">
                    <p>
                        <img src="${basePath}/image/zhanghu2.png" alt=""/>
                        <a href="${basePath}/account/main" style="color: #fff;">账户信息</a>
                    </p>
                </li>
                </c:if>
                 <c:if test="${active != 'acc_inf' }">
	                 <li class="three">
                    <p>
                        <img src="${basePath}/image/zhanghu.png" alt=""/>
                        <a href="${basePath}/account/main">账户信息</a>
                    </p>
                </li>
                </c:if>
               
                <!-- 账户信息end -->
               <%--  <li class="three">
                    <p>
                        <img src="${basePath}/image/bangding1.png" alt=""/>
                        <a href="${basePath}/userloginfo/bindwx">账号绑定</a>
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