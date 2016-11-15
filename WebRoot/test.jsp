<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page  import="java.util.*" %>
<%@page import="edu.ae.entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
  
        <title>九型人格测试题目</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700'>
        <link rel="stylesheet" href="/NineHouse/assets/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="/NineHouse/assets/css/sweet-alert.css">
		
        <link rel="stylesheet" href="/NineHouse/assets/css/style.css">
        <script src="/NineHouse/assets/js/sweet-alert.min.js"></script>
		<style >
		img { max-width: 100%;}
		</style>
    <script language="javascript" type="text/javascript">
	


      
    function SelAnswer(num) {
       var selNum="radio"+num;
       var selAnsw=document.getElementById(selNum);
        var radios = selAnsw.getElementsByTagName("input");
        for(var i=0;i<3;i++)
        {
        	if(radios[i].checked==true)
        	   {radios[3].value=radios[i].value;
        	   }
        }   
    };
    
    function OnChangePage()
    {
        var Now_page=document.getElementById("Now_Page").value;
        var Next_page=document.getElementById("Next_Page");
        var Submit_result=document.getElementById("Result");
          Next_page.style.display="inline";
          Submit_result.style.display="none";
        if(Now_page>1)
          {
           document.getElementById("Now_Page").value=((Now_page%37)-1)
           Now_page=((Now_page%37)-1);
           
        var shownum=Now_page*5;
        var SelPage="question_comby"+shownum;
        var Page=document.getElementById(SelPage);
           
        for(var i=shownum+1;i<=shownum+5;i++)
        {
         SelPage="question_comby"+i;
         Page=document.getElementById(SelPage);
         Page.style.display="none";
        }
         for(var i=shownum-4;i<=shownum;i++)
        {
         SelPage="question_comby"+i;
         Page=document.getElementById(SelPage);
         Page.style.display="block";
        }
        }
    };
    
    function OffChangePage()
    {  
        var Now_page=document.getElementById("Now_Page").value;
        var Next_page=document.getElementById("Next_Page");
        var Submit_result=document.getElementById("Result");
        var temp=0;var shownum=0;
       
        if(Now_page<36) 
        {  
           for(var y=0;y<5;y++)
     		 {
     		        shownum=Now_page*5;
       				var selNum="radio"+(shownum-y);
       				var selAnsw=document.getElementById(selNum);
      			    var radios = selAnsw.getElementsByTagName("input");
   	  			  	if(radios[3].value=="")
     			  	{
     			  	var string="第"+(shownum-y)+"题未答";
					alert(string);
					temp=1;
    				}
    	     }
    	if(temp==1)
     	     return;
          Next_page.style.display="inline";
          Submit_result.style.display="none";
        document.getElementById("Now_Page").value=((Now_page%36)+1);
         Now_page=((Now_page%36)+1);       
         shownum=Now_page*5;
        var SelPage="question_comby"+shownum;
        var Page=document.getElementById(SelPage);
       
        for(var i=shownum;i>shownum-5;i--)
        {
         SelPage="question_comby"+i;
         Page=document.getElementById(SelPage);
         Page.style.display="block";
        }
         for(var i=shownum-9;i<=shownum-5;i++)
        {
         SelPage="question_comby"+i;
         Page=document.getElementById(SelPage);
         Page.style.display="none";
        }
        }
        else
        {
          Next_page.style.display="none";
          Submit_result.style.display="inline";
        }
    };
    
   function Submit_Result()
   {
   		var string="";
   		var s_sort = document.getElementById("s_sort").value;
   		for(var i=1;i<=180;i++)
   		{
   		    var selNum="radio"+i;
            var selAnsw=document.getElementById(selNum);
            var radios = selAnsw.getElementsByTagName("input");
   			string+=radios[3].value;
   		}
   		 		
   	     var userid=document.getElementById("userid").value;
		 alert("你确定提交答卷");
	     window.location.href="/NineHouse/resultServlet?userid="+userid+"&result="+string+"&s_sort="+s_sort;
   		 window.submit(); 
   }
    
</script>
  </head>
  
 <body> 
   <%  
   
      request.setCharacterEncoding("utf-8");
      int i=0;  
      int All_Page = 36;  
      int Now_Page = 1;  
      int userid = Integer.parseInt(request.getAttribute("userid").toString()); 
      //System.out.println("test.jsp id"+userid);
      List questionList = (List)request.getAttribute("questionList");
      String s_sort = (String)request.getAttribute("s_sort");
      System.out.println("test.jsp s_sort:"+s_sort);
   %>     
    
  			<input type="hidden" value="<%=userid%>" id="userid"/>
            <div  style=" width:100%;height:15%; background:#af2024;margin-top:0%;" align="center">       
			<div>&nbsp;</div>
			<div>&nbsp;</div>
			<font size="+3" style="margin-top:4%" color="#CCCCCC">九型人格测试系统</font>
             </div>
		  <div  style="width:90%;height:120%; margin-left:5%;margin-right:0">
         <%   
         for(i =1;i<questionList.size()+1;i++){
           Questions question=new Questions();
           question = (Questions)questionList.get(i-1);
          if(i<=5)
          {
          %>
			    <div style="width:100%;height:20%;" id="question_comby<%=i%>" >
			    <div  style="height:70%" id="question<%=i%>"><%=i%>&nbsp;&nbsp;<%=question.getContent()%></div>
			    <div align="center" style="height:25%" id="radio<%=i%>" >
                  <input type="radio" name="radiobutton<%=i%>" value="0"   style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:5%"  onChange="SelAnswer(<%=i%>);" />&nbsp;是 
                  <input type="radio" name="radiobutton<%=i%>" value="1"   style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:10%" onChange="SelAnswer(<%=i%>);">&nbsp;否
				  <input type="radio" name="radiobutton<%=i%>" value="2"  style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:10%" onChange="SelAnswer(<%=i%>);">&nbsp;不确定
				  <input type="hidden" value=""  id="seElect<%=i%>"> 
				</div>
                <div style="background-color:#FFFFFF; width:100%; height:5%">&nbsp; 
                </div>
                </div>
       <%
       }
       else
       {%>
       
			  <div style="width:100%;height:20%;display:none" id="question_comby<%=i%>">
			    <div  style="height:70%;width:100%" id="question<%=i%>"><%=i%>&nbsp;&nbsp;<%=question.getContent()%></div>
			    <div align="center" style="height:25%" id="radio<%=i%>" >
                  <input type="radio" name="radiobutton<%=i%>" value="0"   style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:5%" onChange="SelAnswer(<%=i%>);" />&nbsp;是 
                  <input type="radio" name="radiobutton<%=i%>" value="1"   style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:10%" onChange="SelAnswer(<%=i%>);">&nbsp;否
				  <input type="radio" name="radiobutton<%=i%>" value="2"  style="width:10%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px; margin-left:10%" onChange="SelAnswer(<%=i%>);">&nbsp;不确定
				  <input type="hidden" value=""  id="seElect<%=i%>"> 
				</div>
                <div style="background-color:#FFFFFF; width:100%; height:5%">&nbsp; 
                </div>
              </div>
       
       <%
       }
      }
       %>
        
		  </div>
		
          <div  style="width:100%;height:8%;" align="center" >
          	<input type="hidden" id="s_sort" value="<%=s_sort %>"/>
            <button type="submit"   style="border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;margin-left:4%; background-color:#ea9306;width:25%;height:80%;display:inline" onClick="OnChangePage()"><font color="#FFFFFF" align="center" size="+1">上页</font></button>
			<input type="button" style="width:10%;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;margin-left:3%;background-color:#f8f8f8" value="<%=(Now_Page)%>" id="Now_Page">/<input type="button" style="width:10%;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;background-color:#f8f8f8" value="<%=(All_Page)%>" id="All_Page">
			<button type="submit" style="border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;; margin-left: 3%; background-color: rgb(234, 147, 6); width: 25%; height: 80%;display:inline" id="Next_Page" onClick="OffChangePage()"><font size="+1" color="#ffffff" align="center">下页</font></button>
			<button type="submit" style="border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;margin-left:5%; background-color:#ea9306;width:25%;height:80%;display:none" id="Result"  onclick="Submit_Result()"><font color="#FFFFFF" align="center" size="+1">提交</font></button>
         </div>
    
     </body>
 </html>

