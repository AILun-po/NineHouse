<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*" import="edu.ae.manager.*" import="edu.ae.entity.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<base href=<%=basePath %>/>
<title>机构信息列表</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" type="text/css" href="/NineHouse/skin/css/base.css"/>
		<script  src="/NineHouse/d3/d3.min.js" charset="utf-8"></script>  
	<script  src="/NineHouse/d3/d3.js" charset="utf-8"></script> 
	<script type="text/javascript" src="/NineHouse/js/jquery.js"></script>
	<script type="text/javascript" src="/NineHouse/js/thickbox_plus.js"></script>
	<link rel="stylesheet" type="text/css" href="/NineHouse/css/thickbox.css" />
<style>
	.axis path,
	.axis line{
		fill: none;
		stroke: black;
		shape-rendering: crispEdges;
		}

	.axis text {
		font-family: sans-serif;
		font-size: 11px;
		}
	</style>
<script language="javascript">

function back(){
	var segment = document.getElementById("segment").value;
	var nowpage = document.getElementById("nowpage").value;
	var eachsize = document.getElementById("eachsize").value;
	window.location.href="/NineHouse/xtmanage/usermanage/userlist.jsp?segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"";
	window.submit();
}

function modifyAnswer(id){
	var oForm = document.getElementById("form0");
	var answer = "";
	for(var i=0;i<180;i++){
		var input =  oForm.getElementsByTagName("input")[i];
		if(input.value=="是"){
			answer = answer + "0";
		}else if(input.value=="否"){
			answer = answer + "1";
		}else{
			answer = answer + "2";
		}
		
	}
	//alert(answer);
	window.location.href="/NineHouse/modifyUserAnswer?id="+id+"&answer="+answer+"";
	window.location.target="main";
	window.submit();
	
}

function turnTo(input){
	var oform = document.getElementById("form0");
	var page = document.getElementById("page");
	var size = document.getElementById("size");
	var each = document.getElementById("each");
	var pages = document.getElementById("pages");
	
	var trs = oform.getElementsByTagName("tr");
	//下标从2开始为题目信息
	//alert(trs[2].innerHTML);
	page.value = page.value%100;
	pages.value = pages.value%100;
	if(input=="上一页"){
		if(page.value>1){
			page.value = page.value-1;
		}
	}else{
		if(page.value<pages.value){
			page.value = (page.value%100)+1;
		}else{
			return;
		}
	}
	
	var j = (page.value-1)*each.value+2;
	var k = j+(each.value%100);
	
	var size = trs.length-2;
	for(var i=2;i<size;i++){
		//j+each.value为字符串
		if(i>=j&&i<k){
			trs[i].style.display="table-row";
		}else{
			trs[i].style.display="none";
		}
	}
}

function turnToPage(page){
	if(page==0){
		document.getElementById("each").value = 5;
		document.getElementById("page").value = 1;
		document.getElementById("pages").value = 9;
		turnTo("上一页");
	}else if(page==9){
		document.getElementById("each").value = 45;
		document.getElementById("page").value = 1;
		document.getElementById("pages").value = 1;
		turnTo("上一页");
	}else{
		document.getElementById("each").value = 5;
		document.getElementById("page").value = page;
		document.getElementById("pages").value = 9;
		turnTo("下一页");
	}
}

function setAllPages(){
	var each = document.getElementById("each").value*4;
	var size = document.getElementById("size").value;
	var pages = document.getElementById("pages");
	if(each>0){
		pages.value = Math.ceil(size/each);
	}
	else{
		pages.value = 1;
	}
}

function showAnswers(){
	var oform = document.getElementById("form0");
	var trs = oform.getElementsByTagName("tr");
	var show = document.getElementById("show");
	if(show.value==1){
		show.value = 0;
		for(var i=1;i<48;i++){
			trs[i].style.display="none";	
		}
	}else{
		show.value = 1;
		trs[1].style.display="table-row";
		turnToPage(document.getElementById("page").value-1);
	}
}

window.onload = function(){
	setAllPages();
	turnTo("上一页");
}

</script>
</head>
<body style="margin-left:0px" background='/NineHouse/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<!--  内容列表   -->

<%
	int segment = Integer.parseInt(request.getParameter("segment"));
	int nowpage = Integer.parseInt(request.getParameter("nowpage"));
	int eachsize = Integer.parseInt(request.getParameter("eachsize"));
	int id = Integer.parseInt(request.getParameter("id"));
	//System.out.println("id"+id);
	User user = new User();
	UserManager um = new UserManager();
	user = um.queryById(id);
	
	AnswerRecord ansrec = new AnswerRecord();
	ansrec = um.getAnswer(id);
	String ans = null;
	if(ansrec!=null){
		ans = ansrec.getAnswer();
	}
	int size = ans.length();
	int xuhao=0;
	if(user.getRelResult()!=null)
	{
	xuhao=Integer.parseInt(user.getRelResult());
	}
	String RelResult[]={"","一","二","三","四","五","六","七","八","九"};
	String sex="";
	if(Integer.parseInt(user.getGender())==0)
	    sex="男";
	else
		sex="女";
	
%>

<%	
            Map<String,Integer>mapData=new TreeMap<String,Integer>();
            String str[]={"10","11","12","20","21","22","30","31","32","40","41","42","50","51","52","60","61","62","70","71","72","80","81","82","90","91","92"};
            int[] sort1=new int[27];
            for(int j=0;j<27;j++)
            {
               mapData.put(str[j],0);
            }
            char Srt;
            for(int i=0;i<ans.length();i++)
            {
            char Res=ans.charAt(i);
            Srt=(char)((i/20)+49);
            String sort=""+Srt+Res;
            if(mapData.containsKey(sort)){
      				//如果已经包含则数量+1
      			int val = mapData.get(sort)+1; 
      				//将+1后的值存放回map
      			mapData.put(sort, val);
      			}else{
      			//如果未包含则初始化为1
      			mapData.put(sort, 1);
      			}
           }
             
            int i=0;
            for(i=0;i<27;i++)
            { 
                sort1[i]=mapData.get(str[i]);      
            }
%>
<form name="form2">
  <table width="80%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
	<tr bgcolor="#E7E7E7" >
		<td height="24" colspan="8" background="../../skin/images/tbg.gif" align="center">&nbsp;测试用户详细信息&nbsp;</td>
  	</tr>
	<tr align="center" bgcolor="#FAFAF1" height="22">
		<td width="14%">姓名</td>
		<td width="22%">学号/工号</td>
		<td width="7%">性别</td>
		<td width="7%">年龄</td>
		<td width="10%">籍贯</td>
		<td width="17%">系统评测</td>
		<td width="10%">实际评测</td>
	</tr>

	<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22" >
		<td><%=user.getName()%></td>
		<td><%=user.getStuId()%></td>
		<td><%=sex%></td>
		<td><%=user.getAge()%></td>
		<td><%=user.getNativeplace()%></td>
		<td><%=user.getSysResult()%></td>
		<td><%=RelResult[xuhao]%></td>
	</tr>

</table>
</form>

<p></p>
<%
	if(size>0){
%>
<form id="form0">
<table width="80%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
	<tr bgcolor="#E7E7E7" align="center" onclick="showAnswers()" title="点击显示/隐藏答题记录">
		<td height="24" colspan="10" background="../../skin/images/tbg.gif" align="center">&nbsp;用户答题记录&nbsp;</td>
  	</tr>
  	<tr bgcolor="#FAFAF1" align="right">
  		<td colspan="10">
  		<select id="answers" onchange="turnToPage(this.value)">
  			<option value='9'>所有</option>
          	<option value='0' selected="selected">1-20</option>
            <option value='1'>21-40</option>
            <option value='2'>41-60</option>
            <option value='3'>61-80</option>
            <option value='4'>81-100</option>
            <option value='5'>101-120</option>
            <option value='6'>121-140</option>
            <option value='7'>141-160</option>
            <option value='8'>161-180</option>
          </select>&nbsp;题
  		</td>
  	</tr>
  	


<%
	String[] answer = {"是","否","不确定"};
	for(i=0;i<18;i++){
		if(i%2==0){
		%>
			<tr align="center" bgcolor="#FAFAF1" style="height:22;display:none"><td colspan="10"><%=i*10+1 %>-<%=i*10+20 %></td></tr>
		<%
		}
		%>

		<tr align="center" bgcolor="#FAFAF1" style="width:100%;height:22;display:none">
		<%
			for(int j=0;j<10;j++){
		%>
			<td width="10%" id="1"><%=i*10+j+1 %></td>
		<%
			}
		%>
		</tr>
		<tr align='center' bgcolor="#FFFFFF" style="width:10%;height=22px;display:none" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';"  >
		<%
			int index;
			int start = i*10;
			for(int j=0;j<10;j++){
				index = ans.charAt(start+j)-48;
		%>
			<td width="10%"><input type="text" name="answer" style="width:90%;text-align:center;" readOnly="true" value="<%=answer[index] %>"/></td>
		<%
			}
		%>
		</tr>
<%
	}
%>

<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center" >
		<input type="button" style="background-color:#E7E7E7" value="上一页" onclick="turnTo(this.value)"/>
		<input type="button" style="background-color:#E7E7E7" id="page" value="1"/>/
		<input type="hidden" style="background-color:#E7E7E7" id="size" value="<%=size %>"/>
		<input type="hidden" style="background-color:#E7E7E7" id="each" value="5"/>
		<input type="button" style="background-color:#E7E7E7" id="pages" value="1"/>
		<input type="button" style="background-color:#E7E7E7" value="下一页" onclick="turnTo(this.value)"/>
		<input type="hidden" style="background-color:#E7E7E7" id="show" value="1"/>
	</td>
  </tr>
  <tr align="center">
  	<td align="center" colspan="10" >
  		<input type="button" value="返回" style="background-color:#FFFFFF" onclick="back()"/>
  	</td>
  </tr>

</table>
</form>

<form id="form4">
	<input type="hidden" id="segment" name="segment" value="<%=segment%>"/>
	<input type="hidden" id="nowpage" name="nowpage" value="<%=nowpage%>"/>	
	<input type="hidden" id="eachsize" name="eachsize" value="<%=eachsize%>"/>	
</form>

<p></p>

   <div style="margin-top:1%; font-size:20px;"  align="center">
   九型人格测试结果柱状图
   </div>
    <div style="margin-left:100px;" >
  <div style="float:left; margin-left:28%;">
  &nbsp;
  </div>
	<div style="float:left;">
		<script>

        var width = 600;	//画布的宽度
		var height = 340;	//画布的高度
		var svg = d3.select("body")				//选择文档中的body元素
					.append("svg")				//添加一个svg元素
					.attr("width", width)		//设定宽度
					.attr("height", height)	   //设定高度
					.attr('transform', 'translate(20,20)');
        var one=<%=(sort1[0]*1.0/(sort1[0]+sort1[1]+sort1[2]))%>;
        var two=<%=(sort1[1]*1.0/(sort1[0]+sort1[1]+sort1[2]))%>;
        var three=<%=(sort1[2]*1.0/(sort1[0]+sort1[1]+sort1[2]))%>;
        var four=<%=(sort1[3]*1.0/(sort1[3]+sort1[4]+sort1[5]))%>;
        var five=<%=(sort1[4]*1.0/(sort1[3]+sort1[4]+sort1[5]))%>;
        var six=<%=(sort1[5]*1.0/(sort1[3]+sort1[4]+sort1[5]))%>; 
        var seven=<%=(sort1[6]*1.0/(sort1[6]+sort1[7]+sort1[8]))%>;
        var eight=<%=(sort1[7]*1.0/(sort1[6]+sort1[7]+sort1[8]))%>;
        var nine=<%=(sort1[8]*1.0/(sort1[6]+sort1[7]+sort1[8]))%>; 
         var ten=<%=(sort1[9]*1.0/(sort1[9]+sort1[10]+sort1[11]))%>;
        var eleven=<%=(sort1[10]*1.0/(sort1[9]+sort1[10]+sort1[11]))%>;
        var twelf=<%=(sort1[11]*1.0/(sort1[9]+sort1[10]+sort1[11]))%>;
        var thirteen=<%=(sort1[12]*1.0/(sort1[12]+sort1[13]+sort1[14]))%>;
        var forteen=<%=(sort1[13]*1.0/(sort1[12]+sort1[13]+sort1[14]))%>;
        var fifteen=<%=(sort1[14]*1.0/(sort1[12]+sort1[13]+sort1[14]))%>;
        var sixteen=<%=(sort1[15]*1.0/(sort1[15]+sort1[16]+sort1[17]))%>;
        var seventeen=<%=(sort1[16]*1.0/(sort1[15]+sort1[16]+sort1[17]))%>;
        var eightteen=<%=(sort1[17]*1.0/(sort1[15]+sort1[16]+sort1[17]))%>;
        var nineteen=<%=(sort1[18]*1.0/(sort1[18]+sort1[19]+sort1[20]))%>;
        var twenty=<%=(sort1[19]*1.0/(sort1[18]+sort1[19]+sort1[20]))%>;
        var twenty_one=<%=(sort1[20]*1.0/(sort1[18]+sort1[19]+sort1[20]))%>;
        var twenty_two=<%=(sort1[21]*1.0/(sort1[21]+sort1[22]+sort1[23]))%>;
        var twenty_three=<%=(sort1[22]*1.0/(sort1[21]+sort1[22]+sort1[23]))%>;
        var twenty_four=<%=(sort1[23]*1.0/(sort1[21]+sort1[22]+sort1[23]))%>;
        var twenty_five=<%=(sort1[24]*1.0/(sort1[24]+sort1[25]+sort1[26]))%>;
        var twenty_six=<%=(sort1[25]*1.0/(sort1[24]+sort1[25]+sort1[26]))%>; 
        var twenty_seven=<%=(sort1[26]*1.0/(sort1[24]+sort1[25]+sort1[26]))%>;
		//var dataset1 = [ 0.5 , 0.3 , 0.2 , 0.3 , 0.2,0.5 , 0.3 , 0.2,0.5 , 0.3 , 0.2,0.5, 0.3 , 0.2,0.5, 0.3 , 0.2,0.5, 0.3 , 0.2,0.5,0.3,0.2,0.5, 0.3 , 0.2,0.5];
		var dataset=[two,three,one,five,six,four ,  eight,nine,seven, eleven,twelf,ten, forteen,fifteen ,thirteen, seventeen ,eightteen,sixteen, twenty ,twenty_one,nineteen,twenty_three,twenty_four,twenty_two, twenty_six , twenty_seven,twenty_five];
		
		var index=[0,1,2];
		var color1 = d3.rgb("rgb(220,20,59)");
		var color2 = d3.rgb("rgb(254,215,0)");
		var color3 = d3.rgb("rgb(4,180,19)");
        var color=[color1,color2,color3];       //设置颜色
		var ordinal = d3.scale.ordinal()
        .domain(index)
        .range(color);
		var linear = d3.scale.linear()
				.domain([0, 0.8])
				.range([0, 200]);
		var rectHeight = 25;	//每个矩形所占的像素高度(包括空白)
		
		
        var dataset2 = [1,2,3,4,5,6,7,8,9];
 
        var linear1 = d3.scale.linear()
              .domain([d3.min(dataset2), d3.max(dataset2)])
              .range([0, 240]);
		var dataset3=[0,20];
		var linear2 = d3.scale.linear()
              .domain([d3.min(dataset3), d3.max(dataset3)])
              .range([250,0]);
		
        var num=0;
		  
                              
		var axis = d3.svg.axis()
					.scale(linear1)		//指定比例尺
					.orient("bottom")	//指定刻度的方向
					.ticks(9);			//指定刻度的数量
	   	var yaxis = d3.svg.axis()
  					.scale(linear2)
 	   				.orient('left')
 	  			    .ticks(4);
		svg.selectAll("rect")
			.data(dataset)
			.enter()
			.append("rect")
			.transition()
			.duration(1000)
			.delay(500)
			.attr("y",function(d,i){
			 if(i%3==0)
			   return 20;
			 else if(i%3==1)
			 { 
			   return (20+linear(dataset[i-1])+2.5);
			 }
			 else
			   return (20+5+linear(dataset[i-1])+linear(dataset[i-2]));
			})
			
			.attr("x",function(d,i){
			    if(i>=3)
				  return (i/3-(i%3)/3)*(rectHeight+5)+40;
                else
				   return 40;
			})
			
			.attr("height",function(d){
				return linear(d);
			})
			.attr("width",rectHeight-2)
			.attr("fill",function(d,i){
			return ordinal(i%3);
			});
		
 	    
 	    svg.append("g")
		.attr("class","y axis")
		.attr("transform","translate(30,20)")
		.call(yaxis)
		.append('text');
        //.text('概率 ')
        //.attr('transform', 'translate(-10,-10)');


		svg.append("g")
			.attr("class","axis")
			.attr("transform","translate(50,300)")
			.call(axis)
			.append('text')
            .text('(型) ')
            .attr('transform', 'translate(250,15)');
		

		</script> 
	    </div>
		</div>
			
		<%
		}
		%>
<p>
	<div style="margin-top:0;"  align="center">
		<input type="button" style="margin-left:2%;width:3%;background:#04b413;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"> <font face="微软雅黑">是</font>
		
		<input type="button" style="margin-left:3%;width:3%;background:#ffd700;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"><font face="微软雅黑">不确定</font> 
		<input type="button" style="margin-left:3%;width:3%;background:#dc143c;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"> <font face="微软雅黑">否</font>
		
		</div> 	
<p>
</body>
</html>

