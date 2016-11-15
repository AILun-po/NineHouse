<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="edu.ae.manager.*" import="edu.ae.entity.Questions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>edit question</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<script language="javascript">
	window.onload = function setSelect(){
		var index = document.getElementById("origincharacter").value;
		var select = document.getElementById("character");
		select.value = index;
	}

	function cancle(){
		window.location.href="/NineHouse/xtmanage/question/question.jsp";
		window.submit();
	}
	
	function Submit(){
		var id = document.getElementById("Id").value;
		var content = document.getElementById("content").value;
		var character = document.getElementById("character").value;
		alert(id+content+character);
		window.location.href="/NineHouse/editQuestion?id="+id+"&content="+content+"&character="+character+"";
		window.submit();
	}
	
</script>

</head>
  
  <body>
  <%
  	int id = Integer.parseInt(request.getParameter("id"));
  	QuestionManager qsm = new QuestionManager();
  	Questions qs = qsm.queryById(id);
   %>
  
<form>
  <table width="60%" height="60%" border="0.3" bgcolor="#D1DDAA" align="center" style="margin-top:5%">
    <tr align="center"  background="/NineHouse/skin/images/tbg.gif">
      <td height="15%" colspan="2" bgcolor="#D1DDAA" >编辑题目</td>
    </tr>
    <tr style="display:none"><td colspan="2"><input type="hidden" id="Id" value="<%=id %>"/></td></tr>
    <tr>
      <td height="45%"  align="center" bgcolor="#FAFAF1"><p>题目内容：</p></td>
      <td align="center" bgcolor="#FAFAF1">
      	<textarea id="content" style="width:98%;height:90%"><%=qs.getContent() %></textarea>
      </td>
    </tr>
    <tr>
      <td height="10%"  align="center" bgcolor="#FAFAF1">性格类型：</td>
      <td align="center" bgcolor="#FAFAF1">
	  <select name="option1" id="character">
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
    <tr style="display:none"><td colspan="2"><input type="hidden" id="origincharacter" value="<%=qs.getRelatedCharacter() %>"/></td></tr>
    <tr bgcolor="#EEF4EA">
      <td height="10%" colspan="2" align="center">
          <input type="button" value="返回" onClick="cancle()"/>
          <input type="button" value="提交" onClick="Submit()"/>
      </td>
    </tr>
  </table>

</form>
</body>
</html>
