<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>index</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  
  <style>
body
{
  scrollbar-base-color:#C0D586;
  scrollbar-arrow-color:#FFFFFF;
  scrollbar-shadow-color:DEEFC6;
}
</style>
</head>

<frameset rows="60,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="/NineHouse/top.jsp?username=<%=request.getParameter("username") %>" id="topFrame" scrolling="no">
  <frameset cols="180,*" frameborder="NO" border="0" framespacing="0">
    <frame src="/NineHouse/menu.jsp?username=<%=request.getParameter("username") %>" noresize id="menu" scrolling="yes">
    <frame src="/NineHouse/leaderfirst.html" noresize id="main" name="main" scrolling="yes">
  </frameset>
</frameset>
<noframes>
	<body>您的浏览器不支持框架！</body>
</noframes>
</html>
