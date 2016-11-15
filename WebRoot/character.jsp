<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page  import="java.util.*" %>
<%@page import="edu.ae.entity.*"%>
<%@page import="edu.ae.manager.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    
    	<title>测试结果显示</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">

</style>
 </head>
	    
<body style="background-color:#E7E7E7">
<%
        int id=Integer.parseInt(request.getParameter("id"));
		QuestionManager qsm=new QuestionManager();
		List concentlist=qsm.queryResultContent();
%>
<div>
	<textarea style="height:100%; width:100%">
	&nbsp;&nbsp;&nbsp;&nbsp;<%=(String)concentlist.get(id-1)%>
	</textarea>

</div>
</body>
</html>
