<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addquestion.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function addQuestion(){
			var content = document.getElementById("content").value;
			var character = document.getElementById("character").value;
			window.location.href="/NineHouse/addQuestion?content="+content+"&character="+character+"";
			window.submit();
		}
		function back(){
			window.location.href="/NineHouse/xtmanage/question/question.jsp";
			window.submit();
		}
	</script>

  </head>
  
  <body>
<form >
  <table style="width:60%;margin-top:20px" border="0.3" bgcolor="#D1DDAA" align="center">
    <tr align="center" background="/NineHouse/skin/images/tbg.gif">
      <td colspan="2" bgcolor="#D1DDAA" >添加题目</td>
    </tr>
    <tr height="200px">
      <td  align="center" width="20%" bgcolor="#FAFAF1"><p>题目内容：</p></td>
      <td align="center" bgcolor="#FAFAF1"><textarea id="content" name="content"style="width:95%;height:150px"></textarea></td>
    </tr>
    <tr height="40px">
      <td align="center" bgcolor="#FAFAF1">题目类型：</td>
      <td align="center" bgcolor="#FAFAF1">
	  <select id="character">
	  <option value="1">一</option>
	  <option value="2">二</option>
	  <option value="3">三</option>
	  <option value="4">四</option>
	  <option value="5">五</option>
	  <option value="6">六</option>
	  <option value="7">七</option>
	  <option value="8">八</option>
	  <option value="9">九</option>
	  </select>&nbsp;&nbsp;型</td>
    </tr>
    <tr bgcolor="#EEF4EA" >
      <td colspan="2" align="center" height="35px">
          <input type="button" value="返回" onClick="back()" />
          <input type="reset" value="重置" />
          <input type="button" value="提交" onClick="addQuestion()"/>
     </td>
    </tr>
  </table>

</form>
</body>
</html>
