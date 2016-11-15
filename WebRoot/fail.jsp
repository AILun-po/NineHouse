<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 
<script type="text/javascript"> 
function my_sleep() {
setTimeout("Login()", 3000);
}

function Login()
{
	window.location.href="index.jsp";
}
my_sleep();
</script>

  </head>
 

<body>
<table  width="500"  align="center"  onclick ="Login()">
  <tr>
    <td align="center">密码错误，请重新输入</td>
  </tr>
  <tr>
   	<td align="center">页面将在3秒后跳转，如果没有响应，请点击"</td>
  </tr>
</table>
</body>
</html>
