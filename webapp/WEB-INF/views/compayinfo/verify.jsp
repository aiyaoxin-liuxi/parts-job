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

<link rel="stylesheet" href="${basePath}/css/tab.css"/>
<link rel="stylesheet" href="${basePath}/css/laydate.css"/>
<style type="text/css">
.jz-tanchu{
    width: 820px;
    height: 900px;
    background: #fff;
    border-radius: 10px;
    position: absolute;
    left: 35%;
    top:20%;
    /*margin-top: -175px;*/
    margin-left: -150px;
    z-index: 11111;
}
</style>
<script type="text/javascript">
function uploadit(_this){
	$('#img1').Imgupload({
		url:'${basePath}/compayinfo/upload'
		,data:{myFileType:'article'}
		,succ:function(data){
			if(data.success){
        		$('#child').attr({"src":data.data});
        		$('#companyidImg').val(data.data);
				alert("上传成功");
			}else{
				alert("异常："+data.errmsg);
			}
		}
	});
}

$(function(){
	var logoImg='${companyInfo.companyidImg}';
	if(logoImg){
		logoImg=logoImg.replace(',','');
		$('#child').attr({"src":logoImg});
		$('#companyidImg').val(logoImg);
	}
	$('#verifyButton').on('click',function(){
		$.ajax({
			url : "${basePath}/compayinfo/verify",
			type : "POST",
			data :$('#verifyForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg);
					window.location.href='${basePath}/compayinfo/audit';
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
	
//	$(".dianji,.guanbi").on("click",function(){
//        $(".jz-tanchu,.shadow").addClass("hide1");
//    })
    
//    $(".fabu_jz").on("click", function () {
//        $(".jz-tanchu,.shadow").removeClass("hide1");
//    });
});

</script>
</head>
<body>

<jsp:include page="../../../common/header.jsp" />


<div class="findjob-contain">
    <div class="findjob-contain_inner">
        <div class="contain_inner_top">
            <ul>
                <li>
                    <span style="color: #c5c5c5;">Personal center</span>
                    <span style="font-size: 30px;">个人中心</span>
                </li>
                <li>企业信息<span class="jianTou"></span>完善资料</li>
            </ul>
        </div>
        <div class="contain_inner_center">
             <jsp:include page="../compayinfo/common/left.jsp" />
            <ul class="jz-center">
                <div class="con_ban_inr">
                    <ul class="qiye">
                        <li><b>企业签约，签约完成即可发布兼职！</b></li>
                        <li>
                            <span class="four">完善资料</span>
                            <span class="five">企业认证</span>
                            <span class="two">审核信息</span>
                            <span class="three">签约完成</span>
                        </li>
                    </ul>
                </div>
                <li class="jz-title">
                    <p>|企业认证</p>
                </li>
                 <form action="" id="verifyForm">
                 <input type="hidden" name="cid" value="${companyInfo.cid}">
                 <input type="hidden" name="companyName" value="${companyInfo.companyName}">
                <li class="ja-news" style="border-bottom: none;">
                    <p>
                        <span style="margin-left: 30px;"><b>商户合作协议</b></span>
                        <span class="fabu_jz"><a href="javascript:void(0);" style="color:#4e89ec;font-size: 14px;margin-left: 10px;">励志汪商家合作协议</a></span>
                    </p>
                    <p>
                        <label for="name1">营业执照注册号</label>
                        <input type="text" name="companyidNo" id="name1" value="${companyInfo.companyidNo}" placeholder="请输入15位营业执照注册号"/>
                    </p>
                </li>
                <div>
                
                </div>
                <div class="con_ban_inr">
                    <ul class="qiyezhao">
                        <li id="photo1">
                        	<input type="hidden" id="companyidImg" name="companyidImg" value="">
                            <img src="${basePath}/image/phone.png" alt=""/>
                            <input type="file" id="img1" name="file" onChange="javascript:uploadit(this);"/>
                            <span>营业执照实例</span>
                            <img src="${basePath}/image/yingyezhao.png"  alt="" style="position: relative;left: -180px;width: 210px;height: 150px;" id="child"/>
                        </li>
                        <li>
                            <button class="btn" id="verifyButton" type="button">同意协议并签约</button>
                        </li>
                        <li style="margin-left: 110px">
                            如图片未上传成功，请联系官方客服02552123090
                        </li>
                    </ul>
                </div>
                </form>
                
            </ul>
            <div class="clear"></div>
        </div>
    </div>
</div>
	<!--弹出框-->
	<!-- <div class="shadow hide1"></div> -->
	
	<%-- <div class="hide1 jz-tanchu">
		<ul>
			<li class="one">
				<img src="${basePath}/image/logo11.jpg" alt="" class="logo" />
				<img src="${basePath}/image/guanbi.png" alt="" class="guanbi" />
			</li>
			<li class="three">
				<h1>励志汪兼职服务条款</h1>
				<h2>•	| 概述</h2>
				<p style="text-indent: 2em">本网站（www.lzwjz.com）提供的给企业会员宣传公司形象、发布招聘信息，个人会员发布求职信息，查询招聘会信息，薪资行情，求职指南，培训信息等，本网站的任何用户均受本协议约束。本网站可以随时修改这些条款。我们建议您定期查看这些条款。如果您通知本网站不接受任何改变，那么您对本网站的使用就将结束，否则，您的继续使用就构成您对所有改变的接受，并受这些改变的约束。</p>
				<h3>•	| 网站使用条款</h3>
				<p style="text-indent: 2em">1.个人会员必须同意使用本网站仅用于合法的目的，已注册的个人会员可以发布个人求职信息，设置信息状态。在此种情况下，求职者个人资料仍然储存在本网站数据库中，直至其本人将其个人资料从其中删除为止。</p>
				<p style="text-indent: 2em">2.个人会员必须独自全部承担由于向本网站注册企业或其他用户发送个人资料所形成的一切风险。本网站对于任何企业与个人之间所发生的一切纠纷，概不负责。</p>
				<p style="text-indent: 2em">3.本网站保留对会员资料进行修改的权力。</p>
				<p style="text-indent: 2em">4.未经个人会员的许可，本网站不会将其个人资料随意公开。</p>
				<p style="text-indent: 2em">5.企业用户可通过网上在线注册并登录公司信息。</p>
				<p style="text-indent: 2em">6.企业会员有权进入本网站资料库进行查询，但禁止利用此项权利进行查询人才以外的其他用途。</p>
				<p style="text-indent: 2em">7.企业会员须独自对登记在本网站上及其他链接页面上的内容的正确性负责。本网站有权修改企业会员的招聘广告以符合本网站的规定，有权删除一切非法的、辱骂性的及不健康的资料。</p>
				<p style="text-indent: 2em">8.本网站有权在适当的时候调整服务收费或收取其他相关费用。对拒绝接受新的收费标准的企业，本网站有权暂停或中止该企业的会员资格。</p>
				<h3>•	| 明确禁止条款</h3>
				<p style="text-indent: 2em">会员使用本网站只能用于合法目的，即企业会员宣传公司形象、发布招聘信息，个人会员发布求职信息，查询招聘会信息，薪资行情，求职指南，培训信息使用。本网站明确禁止任何其他非法用途。</p>
				<p style="text-indent: 2em">1.禁止在个人简历中公布不完整、虚假或不准确的资料，禁止在简历中公布不属于简历内容的资料。一经发现本网站将暂停或终止对该使用者的服务。</p>
				<p style="text-indent: 2em">2.未经其许可不得随意公开本网站中的任何个人资料或企业商业信息，不得向用户发送垃圾邮件。</p>
				<p style="text-indent: 2em">3.严禁采取任何方式侵犯本网站，包括：登录没有对其授权的服务器或帐号；进入没有对其开放的数据库；探测、测试或破坏本网站系统的安全性；干扰本网站对用户提供的服务；对本网站系统或网络安全造成破坏的所有个人或实体，将依法追究其法律责任。</p>
				<p style="text-indent: 2em">4.严禁利用本网站发送、散布或储存侵犯他人版权、商标权、商业秘密或其他知识产权以及侵犯他人隐私权、违反法律法规的资料；严禁利用本网站发送、散布或储存任何诽谤、辱骂、恐吓、伤害他人和庸俗淫秽的信息。 一经发现本网站有权立即取消该用户的会员资格并禁止其再次登录本网站。</p>
				<h3>•	| 适用法律和管辖权限条款</h3>
				<p style="text-indent: 2em">用户和本网站双方都必须遵守中华人民共和国法律，服从中华人民共和国的司法管辖。使用本网站所产生的任何争议，均以中华人民共和国法律为标准解决。</p>
				<h3>•	| 特别声明</h3>
				<p style="text-indent: 2em">《励志汪兼职用户服务协议》及其修改权、解释权均属励志汪兼职网。</p>
				<h3>•	| 投诉建议</h3>
				<p style="text-indent: 2em">025-52123090</p>


			</li>
		</ul>
	</div> --%>
<!--底部-->

<!--底部-->
 <jsp:include page="../../../common/footer.jsp" />

<script src="${basePath}/js/jquery.datetimepicker.full.min.js"></script>
<%-- <script src="${basePath}/js/tab.js"></script> --%>
</body>
</html>