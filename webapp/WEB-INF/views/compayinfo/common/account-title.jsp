<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<%@ page language="java" import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <li class="jz-title">
                    <p>|账户信息</p>
  					<input type="file" style="visibility:hidden"  id="addImageFile4title" name="file" value="上传" onChange="javascript:uploadit4title(this);">	 
                    <p class="jz-touxiang" id="photo" style="display: inline-block;">
                        <img id="yimg"  alt="" style="width: 150px;height: 110px;"/>
<%--                         <span><a href="${basePath}/compayinfo/fill?modify=modify"> 修改头像</a></span> src="${basePath}/image/app_downloadCom.jpg"--%>
                         <span onclick="changeit4title(this)">修改头像</span>
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
                           <c:if test="${not empty stars}">
		                         <%
		                        Object s= request.getAttribute("stars");
		                         int stars=Integer.valueOf(s.toString());
		                         for(int i=0;i<stars;i++){
		                        	 %>
		                        	<img src="${basePath}/image/xing.png" alt=""/>
		                        	 <%
		                         } %>
                           </c:if>
                            <a class="o">100%</a>
                            <!-- <a class="t">查看评价详情</a> -->
                        </span>
                        <span class="three">
<%--                             <img src="${basePath}/image/zhifubao.png" alt=""/> --%>
                            <img src="${basePath}/image/idcard.png" alt="" onclick="location.href='${basePath}/card/list'"/>
                        </span>
                    </p>
                </li>
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/imgupload.js"></script>               
<script type="text/javascript">
$(function(){
	var logoImg='${companyInfo.logoImg}';
// 	console.log(">>>>>>>>"+ logoImg);
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":logoImg});
		$('#logoImg').val(logoImg);
	}else{
		var defaultUrl="${basePath}/image/app_downloadCom.jpg";
		$('#yimg').attr({"src":defaultUrl});
// 		console.log("还没有图片");
	}
});
function changeit4title(this_){
	$('#addImageFile4title').click();
}
function uploadit4title(_this){
	var cid='${companyInfo.cid}';
	var logoImg=$('#logoImg').val();
	var param={cid:cid,titleFlag:'titleFlag',myFileType:'userinfo',logoImg:logoImg};
	$('#addImageFile4title').Imgupload({
		url:'${basePath}/compayinfo/upload'
		,data:param
		,succ:function(data){
        	if(data.success){
    		$('#yimg').attr({"src":data.data});
    		$('#logoImg').val(data.data);
				alert("上传成功");
			}else{
				alert("异常：注意上传格式");
			}
		}
	});

}

</script>