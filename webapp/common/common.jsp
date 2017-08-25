<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
int port = request.getServerPort();
String basePath = null;
String proto=request.getHeader("X-Forwarded-Proto");
String scheme =  request.getScheme();
String sche=(String)request.getSession().getAttribute("scheme");
String userSch=(String)request.getSession().getAttribute("userScheme");

if(sche==null || sche.trim().equals("")){
    if(proto!=null){
        scheme=proto;
    }
}else{
    scheme=sche;
}
if(80 == port){
    basePath = scheme+"://"+request.getServerName() + path;
}else{
    basePath = scheme+"://"+request.getServerName()+":" + port + path;
}
request.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
    var _basePath = '${request.basePath}';
</script>
