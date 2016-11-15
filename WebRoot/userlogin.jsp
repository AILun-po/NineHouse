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
    <title>九型人格测试系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script language="javascript">
     
	 
function show(test)
	  //function show(test)
      {	
	  //var test="测试时，凭第一感觉判断，不要长思考，180道题，30分钟内完成，保存测试结果。九型人格测试系统测试你当前状态，得分最高的不一定是你的性格类型，不要盲目贴号！";
	   alert(test);
      }
      
function myselect(){
    //var text1="";
    var myselect=document.getElementById("nativeplace");
    //alert(myselect.value);
	//var index=myselect.selectedIndex;
    //text1=myselect.options[index].value;
    document.getElementById("native").value=myselect.value;
}

function selChange(){
   var myselect=document.getElementById("gender");
	var index=myselect.selectedIndex;
	var gender=myselect.options[index].value;
    document.getElementById("gender1").value=gender;
}

function checkNumber()
{
	var number=document.getElementById("age").value;
	if(number=="")
	{
	 alert("请必须填真实年龄 ！");
	}
	else 
	{
		 if(/^(\+|-)?\d+($|\.\d+$)/.test(number))   //进去为数字
		 {
		 
		  //转换为数字格式
             var value=parseInt(number);
             if(value>=1&&value<=100){
                                           //表明输入的数字是0-100之间的
             }
             else
             {
                                           //表明输入的数字不在0-100之间
             alert("年龄1-100之间 ");
              }      
           }
            else
            {
       					                  //表明输入的不是数字
             alert("年龄必须是数字  ！"); 
            }      
       }
}

function checked()
{	
var test1="123";
	var number=document.getElementById("age").value;
	if(number=="")
	{
	alert("请输入年龄");
	swal({
	  title:"请必须填真实年龄 ", 
	   confirmButtonText:"OK"
	  }); 
	}
	else
	{
	   var form1=document.getElementById("form1");
	   form1.action="/NineHouse/testerServlet";
	   form1.submit();
	}
}

</script>
  </head>
  
  <body>

        <div class="register-container container" >
            <div class="row">
                <div class="register span6"  style="width:90%; margin-left:5%;">
                    <form  id="form1" action=""  >
                        <h4>九型人格 <span class="red">测试系统</span></h4>
                        <label for="username">姓&nbsp;名 </label>
                        <input type="text" id="name" name="username" placeholder="请输入姓名">
                        <label for="number">学&nbsp;号&nbsp;/&nbsp;工&nbsp;号</label>
                        <input type="text" id="number" name="usernumber" placeholder="请输入学号">
                        <label for="age">年&nbsp;龄<font color="red">*</font></label>
                        <input type="text" id="age" value="" name="age" placeholder="请输入年龄"  onblur="checkNumber()" >
                        <label for="nativeplace">籍&nbsp;贯<font color="red">*</font></label><input type="hidden" name="native" id="native" value="北京市" >
                        <select  id="nativeplace" style="width:60%;margin-left:5%;margin-top:0px;" onChange="myselect()">
						<option value="北京市 ">北京市</option>   
						<option value="天津市">天津市</option>
						<option value="河北省">河北省</option>
						<option value="山西省">山西省</option>
						<option value="内蒙古">内蒙古</option>
						<option value="辽宁省">辽宁省</option>
						<option value="吉林省">吉林省</option>
						<option value="黑龙江">黑龙江</option>
						<option value="上海市">上海市</option>
						<option value="江苏省">江苏省</option>
						<option value="浙江省">浙江省</option>
						<option value="安徽省">安徽省</option>
						<option value="福建省">福建省</option>
						<option value="江西省">江西省</option>
						<option value="山东省">山东省</option>
						<option value="河南省">河南省</option>
						<option value="湖北省">湖北省</option>
						<option value="湖南省">湖南省</option>
						<option value="广东省">广东省</option>
						<option value="广西">广&nbsp;西</option>
						<option value="海南省">海南省</option>
						<option value="重庆市">重庆市</option>
						<option value="四川省">四川省</option>
						<option value="贵州省">贵州省</option>
						<option value="云南省">云南省</option>
						<option value="西藏">西&nbsp;藏</option>
						<option value="陕西省">陕西省</option>
						<option value="甘肃省">甘肃省 </option>
						<option value="青海">青&nbsp;海</option>
						<option value="宁夏">宁&nbsp;夏</option>
						<option value="新疆">新&nbsp;疆  </option>
						<option value="香港">香&nbsp;港</option>
						<option value="澳门">澳&nbsp;门</option>
						<option value="台湾">台&nbsp;湾</option>
						<option value="其他">其&nbsp;他</option>
						</select>
                        <label for="gender">性&nbsp;别<font color="red">*</font></label><input type="hidden" value="0" name="gender1" id="gender1">
                        <select onChange="selChange()" style="width:40%" id="gender">
                        <option value="0">男</option>
						<option value="1">女</option>
						</select>
						<label><font color="red" >*</font>是必填信息</label>
						<p>	</p>
						<%
						  QuestionManager qsm = new QuestionManager(); 
  		                   List contentlist = new ArrayList();
  		                   contentlist = qsm.queryResultContent();
				
						%>
                        
                        <button type="submit" onClick="checked();show('<%=(String)contentlist.get(11)%>');">点击开始测试</button>
					
                    </form>
                </div>
            </div>
        </div>
		<div align="center">made by sixgod</div>
        <!-- Javascript -->
        <script src="/NineHouse/assets/js/jquery-1.8.2.min.js"></script>
        <script src="/NineHouse/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="/NineHouse/assets/js/jquery.backstretch.min.js"></script>
        <script src="/NineHouse/assets/js/scripts.js"></script>

  </body>
</html>
