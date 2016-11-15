<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="edu.ae.manager.*" import="edu.ae.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<title>修改信息</title>
	<link rel="stylesheet" type="text/css" href="/NineHouse/skin/css/base.css"/>
	<script type="text/javascript" src="/NineHouse/js/jquery.js"></script>
	<script type="text/javascript" src="/NineHouse/js/thickbox_plus.js"></script>
	<link rel="stylesheet" type="text/css" href="/NineHouse/css/thickbox.css" />
<script type="text/javascript">
	function modify(old_password){
	    var passwd = document.getElementById("passwd").value;
		var passwd1 = document.getElementById("passwd1").value;
		var passwd2 = document.getElementById("passwd2").value;
		var username = document.getElementById("username").value;
		if(username==""||passwd==""||passwd1==""||passwd2==" "||passwd2=="")
		{
		 alert("请输入必填信息！");
		 return ;
		 }
		if(passwd2!=old_password)
		{
		  alert("输入原密码错误！");
		  return ;
		}
		if(passwd==passwd1)
		{
		var form = document.getElementById("form0");
		form.action = "/NineHouse/modifyManagerInfo";
		form.submit();
		alert("修改成功，请记住新密码！");
		}
		else
		{
		alert("请输入相同的密码");
		return ;
		}
	}
	function back(){
		var username = document.getElementById("username").value;
		window.location.href="/NineHouse/mypage.jsp?username="+username+"";
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
<form id="form0">
  <table width="50%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:20px">
	<tr bgcolor="#E7E7E7" >
		<td height="30" colspan="2" style="background-image:url('/NineHouse/skin/images/tbg.gif')" align="center">&nbsp;我的信息&nbsp;</td>
  	</tr>
		<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">账户名*</td>
		<td>
			<input type="text" name="username" id="username" value="<%=username %>"/>
		</td>
	</tr>
	<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">输入原密码*</td>
		<td>
			<input type="password" name="passwd2" id="passwd2" value=" "/>
		</td>
	</tr>
	<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">新密码*</td>
		<td>
			<input type="password" name="passwd" id="passwd" value=""/>
		</td>
	</tr>
		<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">确认新密码*</td>
		<td>
			<input type="password" name="passwd1" id="passwd1" value=""/>
		</td>
	</tr>
	<tr align="center" bgcolor="#FAFAF1" >
		<td height="25px">姓名</td>
		<td>
			<input type="text" name="name" id="name" value="<%=ma.getName() %>" />
		</td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">工号</td>
		<td><input type="text" name="workId" id="workId" value="<%=ma.getWorkId() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">学校</td>
		<td><input type="text" name="school" id="shcool" value="<%=ma.getSchool() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">学院</td>
		<td><input type="text" name="college" id="college" value="<%=ma.getCollege() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">专业</td>
		<td><input type="text" name="major" id="major" value="<%=ma.getMajor() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">年级</td>
		<td><input type="text" name="gread" id="gread" value="<%=ma.getGread() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="22%" height="25px">班级</td>
		<td><input type="text" name="classes" id="classes" value="<%=ma.getClasses() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="7%" height="25px">性别</td>
		<td><input type="text" name="gender" id="gender" value="<%=ma.getGender() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="7%" height="25px">年龄</td>
		<td><input type="text" name="age" id="age" value="<%=ma.getAge() %>"/></td>
	</tr>
	<tr bgcolor="#FAFAF1" align="center">
		<td width="10%" height="25px">籍贯</td>
		<td><input type="text" name="nativeplace" id="nativeplace" value="<%=ma.getNativeplace() %>"/></td>
	</tr>

	<tr bgcolor="#E7E7E7">
	<td align="center">（*是必填信息）</td>
		<td colspan="2" align="center">
			<input type="button" style="background:#E7E7E7;cursor:pointer" value="返回" onClick="back()"/>
			<input type="button" style="background:#E7E7E7;cursor:pointer" value="保存" onClick="modify('<%=ma.getPassword()%>')"/>
		</td>
	</tr>
	
</table>
</form>

</body>
</html>

