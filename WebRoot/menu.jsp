<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>menu</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="/NineHouse/skin/css/base.css" type="text/css" />
	<link rel="stylesheet" href="/NineHouse/skin/css/menu.css" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script language='javascript'>var curopenItem = '1';</script>
	<script language="javascript" type="text/javascript" src="/NineHouse/skin/js/frame/menu.js"></script>
	<base target="main" />

 </head>
  
<body target="main">
<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
  <tr>
    <td style='padding-left:3px;padding-top:8px' valign="top">
	<!-- Item 1 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items1_1")' ><b>常用操作</b></dt>
        <dd style='display:block' class='sitem' id='items1_1'>
          <ul class='sitemu'>
            <li>
              <div class='items'>
                <div class='fllct'><a href='/NineHouse/index.jsp?username=<%=request.getParameter("username") %>' target="_parent">回到首页</a></div>
                <div class='flrct'> <img src='/NineHouse/skin/images/frame/gtk-sadd.png' alt='' title=''/></div>
              </div>
            </li>
            <li><a href='/NineHouse/login.jsp' target="_parent">退出系统</a> </li>
            <li>
              <div class='items'>
                <div class='fllct'><a href='/NineHouse/mypage.jsp?username=<%=request.getParameter("username") %>' target='main'>我的信息</a></div>
              </div>
            </li>
          </ul>
        </dd>
      </dl>
      <!-- Item 1 End -->
      <!-- Item 2 Strat -->
      <dl class='bitem'>
        <dt onClick='showHide("items2_1")' title="点击显示/隐藏"><b>系统管理</b></dt>
        <dd style='display:block' class='sitem' id='items2_1'>
          <ul class='sitemu'>
            <li><a href='/NineHouse/xtmanage/usermanage/userlist.jsp?segment=<%=1 %>&nowpage=<%=1 %>&eachsize=<%=10 %>' target='main'>查&nbsp;&nbsp;询</a></li>   
			<li><a href="/NineHouse/statistics.jsp" target="main">统&nbsp;&nbsp;计</a></li>
			<li><a href='/NineHouse/xtmanage/question/question.jsp' target='main'>题库管理</a></li>
			<li><a href="modifyresultjsp.jsp" target="main">修改描述</a></li>
          </ul>
        </dd>
      </dl>
      <!-- Item 2 End -->
	  </td>
  </tr>
</table>
</body>
</html>
