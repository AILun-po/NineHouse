<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="edu.ae.manager.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modifyresultjsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function check(){
	
			var form = document.getElementById("form0");
			var texts = form.getElementsByTagName("textarea");
			var value = 1;
			var character = "一二三四五六七八九";
			var index = 0;
			for(var i=0;i<10;i++){
				if(texts[i].value.length>1000){
					value = 0;
					index = i%11;
					alert(texts[i].value.length+"! "+character.charAt(index)+"型 性格描述应在1000字以内!");
				}
			}
			if(texts[10].value.length>1000){
				value = 0;
				alert(texts[10].value.length+"!  说明内容在1000字以内!");
			}
			if(value == 1){
				form.action="/NineHouse/editResultContent";
				window.location.target="main";
				form.submit();
			}
		}

	</script>
	<style type="text/css">
	.ta{
		font-size:16px;
		width:95%;
	}
	.ta1{
		font-size:16px;
		width:95%;
		height:90px;
	}
	.td1{
		align:center;
	}
	</style>
  </head>
  
  <body>
  	<% 
  		QuestionManager qsm = new QuestionManager(); 
  		List contentlist = new ArrayList();
  		contentlist = qsm.queryResultContent();
  		//System.out.println(contentlist.size());
  		String content = (String)contentlist.get(0);
  		String content9 = (String)contentlist.get(9);
  		//content = new String (content.getBytes("ISO-8859-1"),"utf-8");
  	 %>
    <div >
    	<form id="form0" method="post"><!--此处一定要记得使用post,否则会出现页面跳转错误-->
	    <table style="margin-top: 20px;" border="0.3" bgcolor="#D1DDAA" align="center" width="800px" height="365">
		    <tr align="center" background="/NineHouse/skin/images/tbg.gif">
		      <td colspan="2" bgcolor="#D1DDAA" height="25px">修改测试结果显示内容<br></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center" width="15%">开始显示内容</td>
		    	<td id="td1"  align="center"><textarea name="con11" id="con11" class="ta1"><%=(String)contentlist.get(11) %></textarea></td>
		    </tr>
			
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center" width="15%">一型</td>
		    	<td id="td1"  align="center"><textarea name="con1" id="con1" class="ta1"><%=(String)contentlist.get(1) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">二型</td>
		    	<td id="td1"  align="center"><textarea name="con2" id="con2" class="ta1"><%=(String)contentlist.get(2) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">三型</td>
		    	<td id="td1"  align="center"><textarea name="con3" id="con3" class="ta1"><%=(String)contentlist.get(3) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">四型</td>
		    	<td id="td1"  align="center"><textarea name="con4" id="con4" class="ta1"><%=(String)contentlist.get(4) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">五型</td>
		    	<td id="td1"  align="center"><textarea name="con5" id="con5" class="ta1"><%=(String)contentlist.get(5) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">六型</td>
		    	<td id="td1"  align="center"><textarea name="con6" id="con6" class="ta1"><%=(String)contentlist.get(6) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">七型</td>
		    	<td id="td1"  align="center"><textarea name="con7" id="con7" class="ta1"><%=(String)contentlist.get(7) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">八型</td>
		    	<td id="td1"  align="center"><textarea name="con8" id="con8" class="ta1"><%=(String)contentlist.get(8) %></textarea></td>
		    </tr>
		    <tr height="100px" bgcolor="#FAFAF1">
		    	<td align="center">九型</td>
		    	<td id="td1"  align="center"><textarea name="con9" id="con9" class="ta1"><%=(String)contentlist.get(9) %></textarea></td>
		    </tr>
		    
		    <tr height="200px">
		      <td align="center" bgcolor="#FAFAF1"><p>底部显示内容<br/>(1000字以内)</p></td>
		      <td align="center" bgcolor="#FAFAF1"><textarea class="ta" id="content" name="content" style="height:180px;"><%=content %></textarea></td>
		    </tr>
		    <tr height="20px">
		      <td align="center" bgcolor="#FAFAF1"><p>联系方式</p></td>
		      <td align="center" bgcolor="#FAFAF1"><textarea style="width:95%" id="con10" name="con10"><%=(String)contentlist.get(10) %></textarea></td>
		    </tr>
		    
		    <tr bgcolor="#EEF4EA" >
		      <td colspan="2" align="center" height="35px">
		          <input type="button" style="cursor:pointer" value="提交" onClick="check()"/>
		      </td>
		    </tr>
	  </table>
	  </form>
    </div>
  </body>
</html>
