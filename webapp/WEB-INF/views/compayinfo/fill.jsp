<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/imgupload.js"></script>

<!--/parts-job/webapp/css/tab.css  -->
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<script type="text/javascript">
function changeit(this_){
	$('#addImageFile').click();
}
function uploadit(_this){
	$('#addImageFile').Imgupload({
		url:'${basePath}/compayinfo/upload'
		,data:{myFileType:'userinfo'}
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
<script type="text/javascript">

$(function(){
	var logoImg='${companyInfo.logoImg}';
	if(logoImg){
// 		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":logoImg});
		$('#logoImg').val(logoImg);
	}else{
		var defaultUrl="${basePath}/image/app_downloadCom.jpg";
		$('#yimg').attr({"src":defaultUrl});
// 		console.log("还没有图片");
	}
	$('#fillButton').on('click',function(){
		var account = $("#account").val();
		$.ajax({
			url : "${basePath}/compayinfo/fill",
			type : "POST",
			data :$('#fillForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					location.href='${basePath}/compayinfo/verify';
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});
window.onload=function(){
	var state="${companyInfo.state}";
	var modify='${modify}';
	if(!!state){
		if(state+'' == '14'){
			if(!!modify && modify== 'modify'){
			}else{
			location.href='${basePath}/compayinfo/auditSucc';
			}
		}
		
	}
}
</script>
</head>
<body>

<jsp:include page="/common/header.jsp" />

<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">companyinfo center</span>
                    <span style="font-size: 30px;">企业中心</span>
                </li>
                <li>企业信息<span class="jianTou"></span>完善资料</li>
            </ul>
        </div>
        <div class="contain_inner_center">
           <jsp:include page="../compayinfo/common/left.jsp" />
            <form action="" id="fillForm">
            <input type="hidden" name="cid" value="${companyInfo.cid}">
            <ul class="jz-center">
                <div class="con_ban_inr">
                    <ul class="qiye">
                        <li><b>企业签约，签约完成即可发布兼职！</b></li>
                        <li>
                            <span class="one">完善资料</span>
                            <span class="two">企业认证</span>
                            <span class="two">审核信息</span>
                            <span class="three">签约完成</span>
                        </li>
                    </ul>
                </div>
                <c:if test="${companyInfo.state != 14}">
                <li class="jz-title">
                    <p>|企业图标</p>
                    <p> 
                    <input type="file" style="visibility:hidden"  id="addImageFile" name="file" value="上传" onChange="javascript:uploadit(this);">	 
                    </p>
                    <p class="jz-touxiang" id="photo">
                    	<input type="hidden" name="logoImg" id="logoImg" value=""/>
                        <img id="yimg" src="" alt="" style="width: 150px;height: 110px;"/>
                        <input type="hidden"  name="logoImg">
                        <span onclick="changeit(this)">修改头像</span>
<!--  						<input type="file" name="file"  /> -->
                    </p>
                </li>
                <li class="jz-title">
                    <p>|基本信息  <span style="color: #ec3341;font-size: 16px">（企业信息只用于励志汪平台认证，并且通过加密处理，确保不会泄露）</span></p>
                </li>
                <li class="ja-news" style="border-bottom: none;">
                    <p>
                        <label for="name">企业名称</label>
                        <input type="text" id="name" name="companyName" value="${companyInfo.companyName}" placeholder="请输入企业名称，需与营业执照名称一致"/>
                    </p>
                    <p>
                        <label for="class">企业性质</label>
                        <select name="companyType"> 
							<c:forEach items="${companyTypes }" var="eachone">
								<option value="${ eachone.code}"  <c:if test="${ eachone.code eq companyInfo.companyType}">selected</c:if>  >${ eachone.name}</option>
							</c:forEach>
						</select>
<!--                         <select id="class"> -->
<!--                             <option value="saab">个人</option> -->
<!--                             <option value="opel">民企</option> -->
<!--                         </select> -->
                    </p>
                    <p>
                        <label for="class1">企业规模</label>
                       <select name="companyPeopleNum"> 
							<c:forEach items="${companyPeopleNums }" var="eachone">
								<option value="${ eachone.code}" <c:if test="${ eachone.code eq companyInfo.companyPeopleNum}">selected</c:if>   >${ eachone.name}</option>
							</c:forEach>
						</select>
                    </p>
                    <p>
                        <label for="name1">固定电话</label>
                        <input type="text" id="name1"  name="telephone" value="${companyInfo.telephone }" placeholder="请输入公司固定电话"/>
                        <span>例如：0258669xxxx</span>
                    </p>
<!--                     <p> -->
<!--                         <label>公司地址</label> -->
<!--                         <select style="width: 200px;margin-right: 20px"> -->
<!--                             <option value="volvo">请选择</option> -->
<!--                             <option value="saab">个人</option> -->
<!--                             <option value="opel">民企</option> -->
<!--                         </select> -->
<!--                         <select style="width: 200px;;margin-right: 20px"> -->
<!--                             <option value="volvo">请选择</option> -->
<!--                             <option value="saab">个人</option> -->
<!--                             <option value="opel">民企</option> -->
<!--                         </select> -->
<!--                         <select style="width: 200px"> -->
<!--                             <option value="volvo">请选择</option> -->
<!--                             <option value="saab">个人</option> -->
<!--                             <option value="opel">民企</option> -->
<!--                         </select> -->
<!--                     </p> -->
                    <p>
                        <label for="name2">具体地址</label>
                        <input type="text" id="name2" name="addressDetail" value="${companyInfo.addressDetail }" placeholder="请输入具体地址"/>
                    </p>
                    <p class="tijiao">
<!--                     	<input class="btn" id="fillButton" type="button" value="提交"> -->
                        <button class="btn" id="fillButton" type="button">提交地址</button>
                    </p>
                </li>
                </c:if>
            </ul>
            </form>
            <div class="clear"></div>
        </div>
    </div>
</div>

<!--底部-->
 <jsp:include page="/common/footer.jsp" />
 
<!-- <script src="js/jquery-1.11.3.js"></script> -->
<%-- <script src="${basePath}/js/laydate.js"></script> --%>
<%-- <script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script> --%>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>