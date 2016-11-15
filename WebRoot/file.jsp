<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'file.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css"/>
	-->
	<script type="text/javascript">
	
	//参数obj为input file对象  
	function getpath(obj){    
  		if(obj){    
    		if (window.navigator.userAgent.indexOf("MSIE")>=1){    
        		obj.select();    
     		 	return document.selection.createRange().text;    
      		}else if(window.navigator.userAgent.indexOf("Firefox")>=1){    
      			if(obj.files){    
        			return obj.files.item(0).getAsDataURL();    
        		}    
      			return obj.value;    
      		}    
   	 		return obj.value;
    	}   
	} 

	function getAbsolutePath(){
		var file_upl = document.getElementById("file_xls");
		alert(file_upl.value);
		file_upl.select();
		alert(file_upl.value);
		document.getElementById("filename").value = file_upl.value;
	}
	
	function getPath(){
	  //判断浏览器
	  var Sys = {};
	  var obj = document.getElementById("headImg");
	  var filename = document.getElementById("filename");
	  var ua = navigator.userAgent.toLowerCase(); 
	  var s; 
	  (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
	  (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
	  (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
	  (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
	  (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	  var path="";
	  if(Sys.ie<="6.0"){
	  	alert("ie6");
	    //ie5.5,ie6.0
	    filename.value = obj.value;
	  }else if(Sys.ie>="7.0"){
	    //ie7,ie8
	    alert("ie7");
	    obj.select();
	    //加上这一句防止报告安全问题
	    obj.blur();
	    filename.value = document.selection.createRange().text;
	  }else if(Sys.firefox){
	  	alert("firefox");
	    if (obj.files) {
	        var accept = ["xls/xlsx"];
	        if (accept.indexOf(obj.files[0].type) > -1) {
	            var reader = new FileReader();
	            reader.readAsDataURL(obj.files[0]);
	            //延迟一会等待文件读取完毕
	            var t = setTimeout(function(){
	            filename.value = reader.result;
	            clearTimeout(t);
	            },100);
	            alert(reader.result);
	        }
	    }
	    else
	    {
			filename.value = obj.value;
	    }
	  }else if(Sys.chrome){
	  	alert("chrome");
		filename.value = obj.value;
	  }
	}
	
	function test(){
		var fso = new ActiveXObject("Scripting.FileSystemObject");
		// 创建新文件
		var tf = fso.CreateTextFile("c://testfile.txt", true);
		// 填写数据，并增加换行符
		tf.WriteLine("Testing 1, 2, 3.") ;
		// 增加3个空行
		tf.WriteBlankLines(3);
		// 填写一行，不带换行符
		tf.Write ("This is a test.");
		// 关闭文件
		tf.Close();
	}
</script>
	
  </head>
    

  <body>
    
<form name="form1" action="" method="post" enctype="multipart/form-data">
	<input type="file" id="file_xls" />
	<input id="btnOk" type="button" value="ok" onclick="getAbsolutePath()"/>
	<input type="text" id="filename"/>
	<p></p>
	<input type="button" value="test" onclick="test()"/>
</form>
  </body>
</html>
