<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <li class="jz-title">
                    <p>|账户信息</p>
  					<input type="file" style="visibility:hidden"  id="addImageFile" name="file" value="上传" onChange="javascript:uploadit(this);">	 
                    <p class="jz-touxiang" id="photo" style="display: inline-block;">
                        <img id="yimg" src="" alt="" style="width: 150px;height: 110px;"/>
                        <span><a href="${basePath}/compayinfo/fill"> 修改头像</a></span>
<!--                         <input type="file" id="img"/> -->
                    </p>

                    <p class="right">
                        <span class="one">
                        
                            <b>${companyInfo.companyName }</b>
                        </span>
                        <span class="two"><!--co-confirmation.png  -->
                        <c:if test="${not empty companyInfo.companyidNo}">
                            <img src="${basePath}/image/co-confirmation.png" alt=""/>
				        </c:if> 
                        <c:if test="${empty companyInfo.companyidNo}">
                       	 	<img src="${basePath}/image/co-noconfirmation.png" alt=""/>
                        </c:if>
                            
                            <a href="${basePath}/compayinfo/fill" style="width: 120px;background: #fff;color: #000;">（点击去认证）</a>
                        </span>
                        <span class="four">
                            等级：
                            <img src="${basePath}/image/xing.png" alt=""/>  <img src="${basePath}/image/xing.png" alt=""/>
                            <a class="o">100%</a>
                            <!-- <a class="t">查看评价详情</a> -->
                        </span>
                        <span class="three">
<%--                             <img src="${basePath}/image/zhifubao.png" alt=""/> --%>
                            <img src="${basePath}/image/idcard.png" alt="" onclick="location.href='${basePath}/card/list'"/>
                        </span>
                    </p>
                </li>