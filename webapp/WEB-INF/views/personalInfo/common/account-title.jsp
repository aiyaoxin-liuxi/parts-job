<%@ page pageEncoding="UTF-8" %>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
$(function(){
	var logoImg='${userInfo.headImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#child').attr({"src":logoImg});
	}else{
		var defaultUrl="${basePath}/image/app_downloadCom.jpg";
		$('#child').attr({"src":defaultUrl});
// 		console.log("还没有图片");
	}
});
</script>
<li class="jz-title">
                    <p>|我的信息</p>

                    <p class="jz-touxiang" id="photo" style="display: inline-block;">
                        <img id="child" src="" alt="" style="width: 150px;height: 110px;"/>
                        <span><a href="${basePath }/personalInfo/fillpersonalInfo">修改头像</a></span>
<!--                         <input type="file" id="img"/> -->
                    </p>

                    <p class="right">
                        <span class="one">
                            <b>${userInfo.realname}</b>
                            <img src="${basePath}/image/co-confirmation.png" alt=""/>
                            <a style="color: #74da66;">初级认证</a>
                        </span>
                        <span class="two">
                            <b>信誉等级：</b>
                            <img src="${basePath}/image/account-level.png" alt=""/>
<!--                             <a>VIP1</a> -->
                        </span>
                        <span class="three">
<%--                             <img src="${basePath}/image/zhifubao.png" alt=""/> --%>
                            <img src="${basePath}/image/idcard.png" alt="" onclick="location.href='${path }/card/list'"/>
                        </span>
                    </p>
                </li>