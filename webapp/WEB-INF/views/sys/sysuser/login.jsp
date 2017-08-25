<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/common.jsp"%> 
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--/parts-job/webapp/css/tab.css  -->
<%-- <link rel="stylesheet" href="${basePath}/css/tab.css"/> --%>
<link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
<link id="bs-css" href="${basePath}/css/sys/bootstrap-cerulean.min.css" rel="stylesheet">
<link href="${basePath}/css/sys/charisma-app.css" rel="stylesheet">

<script type="text/javascript" src="${basePath}/js/jquery.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery-1.8.0.min.js"></script>



<script type="text/javascript">
$(document).keypress(function(event){
	if(event.keyCode==13){
			$("#loginButton").click();
		}
});
$(function(){
	$('#loginButton').on('click',function(){
		$.ajax({
			url : "${basePath}/sys/sysuser/login",
			type : "POST",
			data :$('#loginForm').serialize(),
			dataType : 'json',
			success : function(res) 
			{
				res=$.parseJSON(res);
				if(res.success){
					alert(res.errmsg + "");
					window.location.href="${basePath}/sys/sysuser/index";
				}else{
					alert("异常："+res.errmsg);
				}
			}
		});
	});
});

</script>
</head>
<body>
 <div class="ch-container">
    <div class="row">
        
    <div class="row">
        <div class="col-md-12 center login-header">
            <h2>Welcome to ZHL </h2>
        </div>
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div class="well col-md-5 center login-box">
            <div class="alert alert-info">
                Please login with your Username and Password.
            </div>
<!--             <form class="form-horizontal" action="index.html" method="post"> -->
            <form class="form-horizontal" action="" id="loginForm">
                <fieldset>
                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
                        <input type="text" name="username" class="form-control" placeholder="Username" value="1">
                    </div>
                    <div class="clearfix"></div><br>

                    <div class="input-group input-group-lg">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
                        <input type="password" name="pwd" class="form-control" placeholder="Password"  value="1">
                    </div>
                    <div class="clearfix"></div>

                    <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="remember"> Remember me</label>
                    </div>
                    <div class="clearfix"></div>

                    <p class="center col-md-5">
                        <button type="button"  id="loginButton" class="btn btn-primary">Login</button>
                    </p>
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->

</div>
</body>
</html>