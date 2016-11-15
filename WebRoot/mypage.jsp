<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="edu.ae.manager.*" import="edu.ae.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<title>my Info</title>
	<link rel="stylesheet" type="text/css" href="/NineHouse/skin/css/base.css"/>
	<script type="text/javascript" src="/NineHouse/js/jquery.js"></script>
	<script type="text/javascript" src="/NineHouse/js/thickbox_plus.js"></script>
	<link rel="stylesheet" type="text/css" href="/NineHouse/css/thickbox.css" />
<script type="text/javascript">
	function modify(){
		var username = document.getElementById("username").value;
		window.location.href="/NineHouse/modifyMaInfo.jsp?username="+username+"";
		window.submit();
	}
</script>
</head>
<body style="margin-left:0px" background='/NineHouse/skin/images/allbg.gif'>

<%
	String username = request.getParameter("username");
	UserManager um = new UserManager();
	Managers ma = new Managers();
	ma = um.queryManager(username);
 %>
<form name="form2">
  <table width="50%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:20px">
	<tr bgcolor="#E7E7E7" >
		<td height="30" colspan="2" style="background-image:url('/NineHouse/skin/images/tbg.gif')" align="center">&nbsp;我的信息&nbsp;</td>
  	</tr>
	<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">用户名</td>
		<td><%=ma.getUsername() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">密码</td>
		<td><input type="password" style=" width:50px;" value="<%=ma.getPassword() %>"/></td>
	</tr>
	<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">姓名</td>
		<td><%=ma.getName() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">工号</td>
		<td><%=ma.getWorkId() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">学校</td>
		<td><%=ma.getSchool() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">学院</td>
		<td><%=ma.getCollege() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">专业</td>
		<td><%=ma.getMajor() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">年级</td>
		<td><%=ma.getGread() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">班级</td>
		<td><%=ma.getClasses() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="7%" height="25px">性别</td>
		<td><%=ma.getGender() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="7%" height="25px">年龄</td>
		<td><%=ma.getAge() %></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="10%" height="25px">籍贯</td>
		<td><%=ma.getNativeplace() %></td>
	</tr>

	<tr bgcolor="#E7E7E7" height="22">
		<td colspan="5" align="right">
			<input type="hidden" id="username" value="<%=username %>"/>
			<input type="button" style="background:#E7E7E7;cursor:pointer" value="修改" onClick="modify()"/>
		</td>
	</tr>

</table>
</form>

</body>
</html>

