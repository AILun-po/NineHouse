<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page  import="java.util.*" %>
<%@page import="edu.ae.entity.*"%>
<%@page import="edu.ae.manager.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>九型人格测试结果</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="/NineHouse/d3/d3.min.js" charset="utf-8"></script>  
	<script src="/NineHouse/d3/d3.js" charset="utf-8"></script> 
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
	<script language="javascript" type="text/javascript">
	function show(id)
	{	
	    id=id%20+1;
		window.location.href="/NineHouse/character.jsp?id="+id;
		window.submit();
	}
	</script>

  </head>
  
  <body>
   <%       
      AnswerRecord answer=(AnswerRecord)request.getAttribute("result");
     String Question_sort=answer.getQusetion_sort();
     //System.out.println(Question_sort);
     String result=answer.getAnswer();
          Map<String,Integer>mapData=new TreeMap<String,Integer>();
            String str[]={"10","11","12","20","21","22","30","31","32","40","41","42","50","51","52","60","61","62","70","71","72","80","81","82","90","91","92"};
            int[] sort1=new int[27];
            for(int j=0;j<27;j++)
            {
               mapData.put(str[j],0);
            }
            for(int i=0;i<result.length();i++)
            {
            char Res=result.charAt(i);
            char Srt;
			if(i<Question_sort.length()){
				Srt = Question_sort.charAt(i);
			}else{
				Srt = '2';
			}
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
             Set<String> keys = mapData.keySet();
          int i=0;
            for(String key :keys)
            { 
                sort1[i]=mapData.get(key); 
                i=i+1;
            }
            
             int[] sort2=new int[9];
             int j=0;
             for(i=0,j=0;i<27&&j<9;j++)
             {
                 sort2[j]=sort1[i];	
                 i=i+3;
             }
            List<Map.Entry<String,Integer>> mappingList = null; 
            Map<String,Integer> map = new HashMap<String,Integer>();
            String str2[]={"1","2","3","4","5","6","7","8","9"};
           for(i=0;i<9;i++)
           {
           	map.put(str2[i],sort2[i]);
           }
           mappingList = new ArrayList<Map.Entry<String,Integer>>(map.entrySet()); 
                //通过比较器实现比较排序 
           
          Collections.sort(mappingList, new Comparator<Map.Entry<String,Integer>>(){ 
          public int compare(Map.Entry<String,Integer> mapping1,Map.Entry<String,Integer> mapping2){ 
         return mapping2.getValue().compareTo(mapping1.getValue()); 
   		  } 
    	}); 
    	String[] str3=new String[9];
    	i=0;
      for(Map.Entry<String,Integer> mapping:mappingList)
       { 
     		System.out.println(mapping.getKey()+":"+mapping.getValue()); 
     		str3[i]=mapping.getKey();
     		i++;
      }   
      UserManager um=new UserManager();
      um.SetSysResult(str3,answer.getId()); 
   %>
   <div style="width:100%,height:2%" align="center">
   <br/>
   <font size="+1" face="微软雅黑"><b>九型人格测试结果柱状图</b></font> </div>
    <div style="width:80%;height:3%;margin-left:50%" align="center">
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
		var dataset=[two,three,one  ,five,six,four ,  eight,nine,seven, eleven,twelf,ten, forteen,fifteen ,thirteen, seventeen ,eightteen,sixteen, twenty ,twenty_one,nineteen,twenty_three,twenty_four,twenty_two, twenty_six , twenty_seven,twenty_five];
		//var dataset4=[sort1[0],sort1[3],sort1[6],sort1[9],sort1[12],sort1[15],sort1[18],sort1[21],sort1[24]];
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
		<div style="margin-top:0;" >
		<input type="button" style="margin-left:20%;width:10%;background:#04b413;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"> <font face="微软雅黑">是</font>
		
		<input type="button" style="margin-left:5%;width:10%;background:#ffd700;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"><font face="微软雅黑">不确定</font> 
		<input type="button" style="margin-left:5%;width:10%;background:#dc143c;border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"> <font face="微软雅黑">否</font>
		
		</div> 	
		<%
		QuestionManager qsm=new QuestionManager();
		String concent=qsm.queryResultContentById(1);
		String tel=qsm.queryResultContentById(11);
		%>	
		<div>
		<p><font size="+2" face="微软雅黑"><textarea  style="width:100%; height:35%; border-left:0px;border-top:0px;border-right:0px; border-bottom:0px;"><%=concent%></textarea>
		</font></p>
		<p><font  size="+1" face="微软雅黑" >&nbsp;&nbsp;1、如需解析结果，请点击：</font></p>
		<font face="微软雅黑">&nbsp;&nbsp;&nbsp;&nbsp;<label onClick="show(1)">1号：完美型&nbsp;&nbsp;</label><label onClick="show(2)">2号：助人型</label>&nbsp;&nbsp; <label onClick="show(3)">3号：成就型</label></br>
		<br>&nbsp;&nbsp;&nbsp;&nbsp;<label onClick="show(4)">4号：独特型</label> &nbsp;&nbsp;<label onClick="show(5)">5号：探索型</label>&nbsp;&nbsp;<label onClick="show(6)">6号：疑惑型</label></br>
		<br>&nbsp;&nbsp;&nbsp;&nbsp;<label onClick="show(1)">7号：活跃型</label> &nbsp;&nbsp;<label onClick="show(8)">8号：领袖型</label> &nbsp;&nbsp;<label onClick="show(9)">9号：和谐型</label></br></font>
		<p>
		<p><font size="+1" face="微软雅黑">&nbsp;&nbsp;2、联系方式:<%=tel%></font></p>
		</div>
  </body>
</html>
