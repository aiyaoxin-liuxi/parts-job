<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/common.jsp"%> 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath}/js/imgupload/imgupload.js"></script>
<script type="text/javascript" src="${basePath}/js/userinfo/calender.js"></script>
<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<title>我的信息</title>
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
				alert("异常："+data.errmsg);
			}
		}
	});
}

$(function(){
	var logoImg='${userInfo.headImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#yimg').attr({"src":logoImg});
		$('#logoImg').val(logoImg);
	}else{
		var defaultUrl="${basePath}/image/app_downloadCom.jpg";
		$('#yimg').attr({"src":defaultUrl});
// 		console.log("还没有图片");
	}

	$("#btnOk").click(function(){
		$.ajax( {    
		    url:'${basePath}/personalInfo/saveFillPersonalInfo',// 跳转到 action    
		    data :$('#fillForm').serialize(),
		    type:'post',     
		    dataType:'json',    
		    success:function(data) {
		    	data=$.parseJSON(data);
		        if(data.success){
		        	alert('修改成功');
		        	window.location.href="${basePath}/personalInfo/fillpersonalInfo?d="+new Date().getTime();
		        }else{    
		        	alert('异常'+data.errmsg);    
		        }    
		     }  
		});  
	});
});
	
</script>

</head>
<body>
<jsp:include page="/common/header.jsp" />

<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>个人中心<span class="jianTou"></span>我的信息</li>
            </ul>
        </div>
        <div class="contain_inner_center">
            
             <jsp:include page="../personalInfo/common/left.jsp" />
              <form action="" method="post" id="fillForm">
            <ul class="jz-center">
                <li class="jz-title">
                    <p>|我的信息</p>
					<p> 
                     <input type="file" style="visibility:hidden"  id="addImageFile" name="file" value="上传" onChange="javascript:uploadit(this);">	 
                    </p>
                    <p class="jz-touxiang" id="photo" style="display: inline-block;">
                        <input type="hidden" name="headImg" id="logoImg" value=""/>
                        <img id="yimg" src="" alt="" style="width: 150px;height: 110px;"/>
                         <span onclick="changeit(this)">修改头像</span>
                        <!-- <input type="file" id="img"/> -->
                    </p>

                    <p class="right">
                        <span class="one">
                            <b>${userInfo.realname }</b>
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
                            <img src="${basePath}/image/idcard.png" alt="" onclick="location.href='${basePath}/card/list'"/>
                        </span>
                    </p>
                </li>
                <li class="jz-title">
                    <p>|基本信息</p>
                </li>
                <li class="ja-news" style="border-bottom: none;">
               
               		<input type="hidden" name="pid" id="pid" value="${userInfo.pid}">
					<input type="hidden" name="mobile" id="mobile" value="${userInfo.mobile}">
                    <p>
                        <label for="name1" style="display: inline-block;width: 80px;">姓名</label>
                        <input type="text" name="realname" value="${userInfo.realname }" id="realname" placeholder="请输入联系人姓名"/>
                    </p>

                    <p>
                        <label for="class3" style="display: inline-block;width: 80px;">性别</label>
                        <select name="sex" id="sex" style="width: 150px">
                            <option value="1">男</option>
                            <option value="2">女</option>
                        </select>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 80px;">出生日期</label>
                        <select name="year" id="year" style="width: 150px">
						</select>
						<select name="month" id="month" style="width: 150px">
						</select>
						<select name="day" id="day" style="width: 150px">
						</select>
                    </p>
                    <p>
                        <label for="name2" style="display: inline-block;width: 80px;">身份证号</label>
                        <input type="text" name="idNumber" value="${userInfo.idNumber }" id="idNumber" placeholder="请输入身份证号"/>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 80px;">在校生</label>
                        <select name="educationState" id="educationState">
                            <option value="01">是</option>
                            <option value="00">否</option>
                        </select>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 80px;">学历</label>
                        <select name="education" id="education">
                            <option value="">请选择</option>
                            <option value="1">专科</option>
                            <option value="2">本科</option>
                            <option value="3">研究生</option>
                        </select>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 80px;">学校名称</label>
                        <input type="text" name="graduateSchool" value="${userInfo.graduateSchool }" id="graduateSchool" placeholder="请输入学校名称"/>
                    </p>
                    <p>
                        <label style="display: inline-block;width: 80px;">入学年份</label>
                        <select id="schoolYear" name="schoolYear">
                            <option value="">请选择</option>
                            <option value="1">2011</option>
                            <option value="2">2012</option>
                            <option value="3">2013</option>
                        </select>
                    </p>
                    <p class="tijiao">
                        <button id="btnOk" type="button" style="width: 420px;margin-left: 100px;">修改完成.</button>
                    </p>
                    </form>
                </li>
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>


 <jsp:include page="/common/footer.jsp" />
</body>
<script type="text/javascript">
$(function(){
	var sex = '${userInfo.sex}';
	if(sex!=""){
		$("#sex").val(sex);
	}
	var educationState = '${userInfo.educationState}';
	if(educationState!=""){
		$("#educationState").val(educationState);
	}
	var education = '${userInfo.education}';
	if(education!=""){
		$("#education").val(education);
	}
	var schoolYear = '${userInfo.schoolYear}';
	if(schoolYear!=""){
		$("#schoolYear").val(schoolYear);
	}
});
</script>
</html>