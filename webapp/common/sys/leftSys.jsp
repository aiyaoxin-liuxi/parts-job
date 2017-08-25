<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="one">
            <li>
                <a href="${basePath}/sys/sysuser/settList" class="onc_m <c:if test="${active == 'sett_list' }">actived</c:if>">结算信息</a>
<%--                 <a href="${basePath}/sys/dict/list" class="onc_m <c:if test="${active == 'dict_list' }">actived</c:if>">字典列表</a> --%>
<%--                 <a href="${basePath}/sys/dict/add" class="onc_m <c:if test="${active == 'dict_add' }">actived</c:if>">字典add</a> --%>
                <a href="${basePath}/sys/parttimeReleaseList" class="onc_m <c:if test="${active == 'audit_check' }">actived</c:if>">兼职审核</a>
                <a href="${basePath}/sys/companyContractList" class="onc_m <c:if test="${active == 'con_list' }">actived</c:if>">企业审核</a>
                <a href="${basePath}/sys/cashOrderList" class="onc_m <c:if test="${active == 'cash_order' }">actived</c:if>">提现审核</a>
                <a href="${basePath}/sys/userLogInfoList" class="onc_m <c:if test="${active == 'userLog_list' }">actived</c:if>">用户启/禁用</a>
                <!--xxx  -->
                <a href="${basePath}/sys/applyInfoList" class="onc_m <c:if test="${active == 'apply_list' }">actived</c:if>">申请记录</a>
                <a href="${basePath}/sys/workInfoList" class="onc_m <c:if test="${active == 'workInfo_list' }">actived</c:if>">兼职列表</a>
            </li>
</ul>