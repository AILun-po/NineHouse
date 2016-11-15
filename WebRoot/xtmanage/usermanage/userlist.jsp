<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html;charset=utf-8" autoFlush="false" buffer="2000kb"%>
<%@ page import="edu.ae.manager.*" import="edu.ae.entity.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<title>用户列表</title>
	<link rel="stylesheet" type="text/css" href="/NineHouse/skin/css/base.css"/>
	<link rel="stylesheet" type="text/css" href="/NineHouse/css/thickbox.css" />
	<script type="text/javascript" src="/NineHouse/js/jquery.js"></script>
	<script type="text/javascript" src="/NineHouse/js/thickbox_plus.js"></script>
	<script src="/NineHouse/js/dt.js"></script>
	<link rel="stylesheet" type="text/css" href="/NineHouse/assets/css/sweet-alert.css"/>
		
    <link rel="stylesheet" href="/NineHouse/assets/css/style.css"/>
    <script src="/NineHouse/assets/js/sweet-alert.min.js"></script>
	<style type="text/css">
	input.hidden1{
	background-color:#FFFFFF;
	text-align:center;
	border-bottom:0px; 
	border-left:0px; 
	border-right:0px; 
	border-top:0px;
	width:95%;
	} 
	input.button1
	{
	background-color:#FFFFFF;	
	text-align:center;
	border-bottom:0px; 
	border-left:0px; 
	border-right:0px; 
	border-top:0px;
	}
	.buttonlike
	{
	
	border-bottom:0px;
	 border-left:0px; 
	 border-right:0px; 
	 border-top:0px;
	}
	</style>
	
<script type="text/javascript">

function show(){
	var show1=document.getElementById("div3");
	if(show1.style.display=="none")	{
    	show1.style.display="";
	}else{
    	show1.style.display="none";
	}
}

function selAll(){
	var i;
	var trs = document.getElementById("form2").getElementsByTagName("tr");
	var length = trs.length;
	for(i=1;i<length;i++){
		var input = trs[i].getElementsByTagName("input")[0];
		input.checked=true;
	}
}

function noSelAll(){
	var trs = document.getElementById("form2").getElementsByTagName("tr");
	var length = trs.length;
	for(i=1;i<length;i++){
		var input = trs[i].getElementsByTagName("input")[0];
		input.checked=false;
	}
}


function choose_address(){
	var i=0,j=0;
	var string_s="";
	var checkboxs=document.getElementsByName("province");
	
	for(i=0;i<checkboxs.length;i++){
	
	    //checkboxs[i].checked=window.event.srcElement.checked; 
		if(checkboxs[i].checked==true)
		{
		  string_s+=checkboxs[i].value+",";
		  j++;
		 }
		
	}
	//alert(checkboxs[i].value);
	string_s = string_s.substring(0,string_s.length-1);
	document.getElementById("nativeplace").value=string_s;
	show();
}


//获得选中其中一个的id
function getOneItem(){
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
				allSel = document.form2.id[i].value;
				break;
		}
	}
	return allSel;
}

function modify(input){

	var td = input.parentNode;
	var tr = td.parentNode;
	var id = tr.getElementsByTagName("input")[1].value;
	var realResult = tr.getElementsByTagName("input")[2].value;
	var name = tr.getElementsByTagName("td")[3].innerHTML;
	
	var segment = document.getElementById("segments").value;
	var nowpage = document.getElementById("page").value;
	var each = document.getElementById("each").value;
	
	document.getElementById("value2").value=realResult;
	document.getElementById("value1").value=id;
	document.getElementById("segment").value=segment;
	document.getElementById("nowpage").value=nowpage;
	document.getElementById("eachsize").value=each;
	
	segment = segment%10000;
	nowpage = nowpage%10000;
	
	var form = document.getElementById("form4");
	 swal({  
       title: "你确定操作吗? ",  
       text: "你确定修改  "+name+"  的记录吗？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 " ,
	   cancelButtonText: "取消"},
       function(){
	  form.action="/NineHouse/modifyUserInfo";
	  form.target="main";
	  form.submit();
       });


	//window.location.href="/NineHouse/modifyUserInfo?value1="+value1+"&value2="+value2+"&value3="+value3+"&value4="+value4+"&value5="+value5+"&value6="+value6+"&value7="+value7+"&value8="+value8+"";
	//window.submit();
	
}
function showUser(id,time){
	if(time=='null')
	{
		alert("该数据无效，请删除");
			return ;
	}
	var segment = document.getElementById("segments").value;
	var nowpage = document.getElementById("page").value;
	var eachsize = document.getElementById("each").value;
	segment = segment%10000;
	nowpage = nowpage%10000;
	eachsize = eachsize%10000;
	window.location.href="/NineHouse/xtmanage/usermanage/user_info_rec.jsp?id="+id+"&segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"";
	window.submit();
	//<a href="user_info_rec.jsp?id=
}

function deleteUser(id,name){
	var segment = document.getElementById("segments").value;
	var nowpage = document.getElementById("page").value;
	var eachsize = document.getElementById("each").value;
	segment = segment%10000;
	nowpage = nowpage%10000;
	eachsize = eachsize%10000;
    swal({  
       title: "你确定操作吗? ",  
       text: "你确定删除  "+name+"  的记录吗？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 " ,
	   cancelButtonText: "取消"},
       function(){
 	   window.location.href="/NineHouse/deleteUser?id="+id+"&segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"";
	   window.submit();
       });

}

function search(){
	
	var name = document.getElementById("name").value;
	var stuId = document.getElementById("stuId").value;
	var gender = document.getElementById("gender").value;
	var nativeplace = document.getElementById("nativeplace").value;
	//获得省份的列表
	var provinces = nativeplace.split(",");
	
	var low_age = document.getElementById("low_age").value%200;
	var high_age = document.getElementById("high_age").value%200;
	var sysResult = document.getElementById("sysResult").value;
	var relResult = document.getElementById("relResult").value;
	if(name==""&&stuId==""&&gender=="无"&&nativeplace==""&&low_age==""&&high_age==""&&sysResult=="无"&&relResult=="无"){
		document.getElementById("page").value = 1;
		turnTo("上一页");
		
	}else{
		
	var form2 = document.getElementById("form2");
	var trs = form2.getElementsByTagName("tr");
	//alert(trs.length);
	//tr从第3个开始为user信息，最后一个为页码
	var length = trs.length;
	
	if(stuId!=""||name!=""){
		for(var i=1;i<length;i++){
			//var input = trs[i].getElementsByTagName("input");
			var tds = trs[i].getElementsByTagName("td");
			var value1 = false,value2 = false;
			if(stuId!=""){
				value1 = tds[2].innerHTML.indexOf(stuId)>-1;
			}
			if(name!=""){
				value2 = tds[3].innerHTML.indexOf(name)>-1;
			}
			if(value1||value2){
				trs[i].style.display="table-row";
			}else{
				trs[i].style.display="none";
			}
		}
	}else{
		var flag1,flag2,flag3,flag4,flag5;
		for(var j=1;j<length;j++){
			
			var tds = trs[j].getElementsByTagName("td");
			var input = trs[j].getElementsByTagName("input");
			//匹配性别
			if(gender=="无"){
				flag1 = true;
			}else{
				flag1 = tds[4].innerHTML == gender;
			}
			
			//匹配年龄
			if(low_age==""&&high_age==""){
				flag2 = true;
			}else{
				
				if(low_age!=""&&high_age!="")
					flag2 = (tds[5].innerHTML >= low_age)&&(tds[5].innerHTML <= high_age);
				else if(low_age!="")
					flag2 = tds[5].innerHTML >= low_age;
				else
					flag2 = tds[5].innerHTML <= high_age;
			}
			
			//匹配省份
			flag3 = false;
			if(nativeplace==""){
				flag3 = true;
			}else{
				var size = provinces.length;
				for(var i=0;i<size;i++){
					if(tds[6].innerHTML.indexOf(provinces[i])>-1){
						flag3 = true;
						break;
					}
				}
			}
			
			//匹配系统测评
			if(sysResult=="无"){
				flag4 = true;
			}else{
				flag4 = tds[7].innerHTML.indexOf(sysResult)>-1;
			}
			
			//匹配实际测评
			if(relResult=="无"){
				flag5 = true;
			}else{
				flag5 = input[2].value == relResult;
			}
			
			if(flag1&flag2&flag3&flag4&flag5){
				trs[j].style.display="table-row";
			}else{
				trs[j].style.display="none";
			}
		}
	}
	}
	
	//var places = nativeplace.split(",");
	//alert(places[1]);
	
	
}

function turnTo(input){
	var oform = document.getElementById("form2");
	var page = document.getElementById("page");
	var size = document.getElementById("size");
	var each = document.getElementById("each");
	var allpage = size.value/each.value;
	
	var trs = oform.getElementsByTagName("tr");
	//下标从2开始为题目信息
	//alert(trs[2].innerHTML);
	if(input=="（分页上）"){
		if(page.value>1){
			page.value = page.value-1;
		}
	}else{
		if(page.value<allpage){
			page.value = (page.value%100)+1;
		}
	}
	
	var j = (page.value-1)*each.value+1;
	var k = j+(each.value%10000);
	
	var size = trs.length;
	for(var i=1;i<size;i++){
		//j+each.value为字符串
		if(i>=j&&i<k){
			trs[i].style.display="table-row";
		}else{
			trs[i].style.display="none";
		}
	}
}

function more(tag){
	var segment = document.getElementById("segments").value;
	var nowage = document.getElementById("page").value;
	var eachsize = document.getElementById("each").value;
	var size = document.getElementById("size").value; 
	var allsegments = document.getElementById("allsegments").value;
	segment = segment%1000;
	nowpage = nowpage%1000;
	eachsize = eachsize%1000;
	allsegments=allsegments%1000;
	tag = tag%10;
	size = size%1000;
	//alert("tag:"+tag+" segment:"+segment+" size:"+size+"!!");
	if(tag==1){
		if(segment<=1){
			segment = 1;
		}else{
			segment = segment-1;
			window.location.href="/NineHouse/xtmanage/usermanage/userlist.jsp?segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"";
			window.submit();
		}
		//alert(segment);
		
	}else{
		if(size!=0){
			if(segment<allsegments){
				segment = segment+1;
				window.location.href="/NineHouse/xtmanage/usermanage/userlist.jsp?segment="+segment+"&nowpage="+nowpage+"&eachsize="+eachsize+"";
				window.submit();
			}
		}
		//alert(segment);
		
	}
}

function setAllPages(){
	var each = document.getElementById("each").value;
	var size = document.getElementById("size").value;
	var pages = document.getElementById("pages"); 
	var allusers = document.getElementById("allusers").value;
	var allsegments = document.getElementById("allsegments");
	if(each>0)
		pages.value = Math.ceil(size/each);
	else
		pages.value = 1;
	allsegments.value = Math.ceil(allusers/180);
	
}

function setPageSize(size){
	document.getElementById("each").value=size;
	document.getElementById("page").value=1;
	turnTo("上一页");
	setAllPages();
}

function showColumn(column){
	var oform = document.getElementById("form2");
	var tb = document.getElementById("tb1");
	var rows = oform.getElementsByTagName("tr");
	if(column==191){
		tb.style.width = "8600px";
		for(var i = 0, len = rows.length; i < len; i++){	
			for(var j=0;j<191;j++){
                var cell = rows[i].cells[j];
				if(cell.style.display =='none'){
					cell.style.display='';
					//cell.style.visibility="";
				}             
               
            }
		}
	}else{
		tb.style.width = "1044px"; 
		for(var i = 0 ; i < rows.length; i++){	
			for(var j=9;j<189;j++){
                var cell2 = rows[i].cells[j];
                cell2.style.display='none';
               	//cell2.style.visibility="hidden";
            }
		}
	}
}

window.onload = function(){
	//turnTo("上一页");
	document.getElementById("pagesize").value=document.getElementById("each").value;
	setAllPages();
	//showColumn(11);
}

function exAllUserInfoAndAnswer(){
   alert("请您点击浏览器右上“打开菜单”按钮，选择“选项”->“常规”，勾选下载（总是询问保存文件的位置）项，点击确定按钮关闭“选项”窗口。 以后Firefox在每次下载文件时都会提示您选择保存的位置，并且您可以更改文件的名称。若不会操作请询问孙导，这是浏览器自己的默认设置！谢谢！");
	window.location.href="/NineHouse/exportAllUserInfoAndAnswer";
	window.submit();
}

function exPresentRows(){
	var oform = document.getElementById("form2");
	var trs = oform.getElementsByTagName("tr");
	var size = trs.length%10000;
	var idString = "";
	var i = 1%10;
	var id = 0;
	for(;i<size;i++){
		if(trs[i].style.display=="table-row"){
			id = trs[i].getElementsByTagName("input")[1].value;
			idString = idString+","+id;
		}
	}
	//alert(idString);
	  alert("请您点击浏览器右上“打开菜单”按钮，选择“选项”->“常规”，勾选下载（总是询问保存文件的位置）项，点击确定按钮关闭“选项”窗口。 以后Firefox在每次下载文件时都会提示您选择保存的位置，并且您可以更改文件的名称。若不会操作请询问孙导，这是浏览器自己的默认设置！谢谢！");
	window.location.href="/NineHouse/exportPresentRows?idString="+idString+"";
	window.submit();
}

function exPresentCols(){
	window.location.href="/NineHouse/exportPresentCols";
	window.submit();
}

function exPresent(){
  
	var oform = document.getElementById("form2");
	var trs = oform.getElementsByTagName("tr");
	var size = trs.length;
	//alert(size);
	var idString = "";
	for(var i=1;i<size;i++){
		if(trs[i].style.display=="table-row"){
			var id = trs[i].getElementsByTagName("input")[0].value;
			idString = idString+","+id;
		}
	}
	 alert("请您点击浏览器右上“打开菜单”按钮，选择“选项”->“常规”，勾选下载（总是询问保存文件的位置）项，点击确定按钮关闭“选项”窗口。 以后Firefox在每次下载文件时都会提示您选择保存的位置，并且您可以更改文件的名称。若不会操作请询问孙导，这是浏览器自己的默认设置，谢谢！");
	window.location.href="/NineHouse/exportPresent?idString="+idString+"";
	window.submit();
}

function importUserInfo(){
	var segment = document.getElementById("segments").value;
	var nowage = document.getElementById("page").value;
	var eachsize = document.getElementById("each").value;
	segment = segment%10000;
	nowpage = nowpage%10000;
	eachsize = eachsize%10000;
	
	var form = document.getElementById("form5");
	var filename = document.getElementById("import");  
        //上次修改时间  
	if(filename.value!=""){
     	var FileExt=filename.value.replace(/.+\./,"");   //正则表达式获取后缀
	 	if(FileExt!="xls"){
			alert("请选择.xls 文件");
			return ;
	 	}else{ 
			alert("hahha");
			form.action="/NineHouse/importUserInfo";
			form.submit();
	 	}
	}else{
	   alert("未选择文件！");
	   return ;
	}
}


function updateArc(){
	//window.location.href="/NineHouse/updateId";
	//window.submit();
	window.location.reload(true);
}

function deleteIllegalUser(){
swal({  
       title: "你确定操作吗? ",  
       text: "你确定删除无效记录吗？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 ",
	   cancelButtonText: "取消"},
       function(){
 	  		window.location.href="/NineHouse/deleteAllUser?type="+1+"";
	        window.submit();
       });
}

function deleteAllUser(){
    swal({  
       title: "你确定操作吗? ",  
       text: "你确定删除所有记录吗？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 ",
	   cancelButtonText: "取消"},
       function(){
 	  	window.location.href="/NineHouse/deleteAllUser?type="+2+"";
	    window.submit();
       });
}

function deleteSomeUser()
{
	var i;
	var num=0;
	var string_num="";
	var name="";
	var trs = document.getElementById("form2").getElementsByTagName("tr");
	var length = trs.length;
	
	var segment = document.getElementById("segments").value;
	var nowpage = document.getElementById("page").value;
	segment = segment%10000;
	nowpage = nowpage%10000;
	
	for(i=1;i<length;i++){
		var input = trs[i].getElementsByTagName("input")[0];
		var id=trs[i].getElementsByTagName("input")[1].value;
		if(input.checked==true)
		{
		   name=name+trs[i].getElementsByTagName("td")[3].innerHTML+",";
			string_num=string_num+id+',';
			num++;
		}
	}
	if(num!=0)
	{
	  swal({  
       title: "你确定操作吗? ",  
       text: "你确定删除 "+name+" 这些记录吗？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 ",
	   cancelButtonText: "取消"},
       function(){
 	  	window.location.href="/NineHouse/deleteSomeUser?deletenum="+string_num+"&segment="+segment+"&nowpage="+nowpage+"";
	    window.submit();
       });
     }else{
	      swal("请选择删除数据!");
	}
}


  function   overto()  
  {  
  seeme.innerHTML="一页固定是180条数据";   
  }  
  function   moveto()  
  {  
  seeme.innerHTML=" ";  
  
  }  

</script>
</head>
<body  background='../../skin/images/allbg.gif' style="margin-left:0px" >

<form name='form3' id="form0" action='' method='get'>

<table width="1044px"  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px" >
   <tr bgcolor="#E7E7E7">
	 <td width="1044px" align="center">
		<div id="div3" style="display:none;"> 
		<input type="checkbox" name="province" id="1" value="北京市"/>北京市     
		<input type="checkbox" name="province" id="2" value="天津市"/>天津市
		<input type="checkbox" name="province" id="3" value="河北省"/>河北省
		<input type="checkbox" name="province" id="4" value="山西省"/>山西省 
		<input type="checkbox" name="province" id="5" value="内蒙古"/>内蒙古 
		<input type="checkbox" name="province" id="6" value="辽宁省"/>辽宁省
		<input type="checkbox" name="province" id="7" value="吉林省"/>吉林省 
		<input type="checkbox" name="province" id="8" value="黑龙江"/>黑龙江
		<input type="checkbox" name="province" id="9" value="上海市"/>上海市
		<input type="checkbox" name="province" id="10" value="江苏省"/>江苏省 
		<input type="checkbox" name="province" id="11" value="浙江省"/>浙江省
		<input type="checkbox" name="province" id="12" value="安徽省"/>安徽省
		<p></p>
		<input type="checkbox" name="province" id="13" value="福建省"/>福建省
		<input type="checkbox" name="province" id="14" value="江西省"/>江西省
		<input type="checkbox" name="province" id="15" value="山东省"/>山东省 
		<input type="checkbox" name="province" id="16" value="河南省"/>河南省
		<input type="checkbox" name="province" id="17" value="湖北省"/>湖北省
		<input type="checkbox" name="province" id="18" value="湖南省"/>湖南省
		
		<input type="checkbox" name="province" id="19" value="广东省"/>广东省 
		<input type="checkbox" name="province" id="20" value="广西"/>广&nbsp;西
		<input type="checkbox" name="province" id="21" value="海南省"/>海南省
		<input type="checkbox" name="province" id="22" value="重庆市"/>重庆市
		<input type="checkbox" name="province" id="23" value="四川省"/>四川省
		<input type="checkbox" name="province" id="24" value="贵州省"/>贵州省
		<p></p>
		<input type="checkbox" name="province" id="25" value="云南省"/>云南省
		<input type="checkbox" name="province" id="26" value="西藏"/>西&nbsp;藏
		<input type="checkbox" name="province" id="27" value="陕西省"/>陕西省
		<input type="checkbox" name="province" id="28" value="甘肃省"/>甘肃省 
		<input type="checkbox" name="province" id="29" value="青海"/>青&nbsp;海
		<input type="checkbox" name="province" id="30" value="宁夏"/>宁&nbsp;夏
		<input type="checkbox" name="province" id="31" value="新疆"/>新&nbsp;疆  
		<input type="checkbox" name="province" id="32" value="香港"/>香&nbsp;港
		<input type="checkbox" name="province" id="33" value="澳门"/>澳&nbsp;门
		<input type="checkbox" name="province" id="34" value="台湾"/>台&nbsp;湾
		<input type="checkbox" name="province" id="35" value="其他"/>其&nbsp;他
		<input type="button" name="sumbit_province" value="确定" style="margin-left:10px;background-color:#FFFFFF;cursor:pointer" onclick="choose_address()"/>
	  </div> 
	</td>
  </tr>
  <tr bgcolor='#EEF4EA'>
    <td  align='center' width="1044px">
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='8%' align='center'>搜索条件:</td>
		   	<td width='12%'>学号  <input type="text" id="stuId" style="width:60%;" onchange="search()" /></td>
			<td width='12%'>姓名  
		    <input name="text2" type="text" id="name" style="width:60%;" onchange="search()"/></td>
          <td width='10%'>性别
          <select id="gender" style='width:50%' onchange="search()">
		  	<option value='无'>无</option>
          	<option value='男'>男</option>
          	<option value='女'>女</option>
          </select>        </td>
		 <td width='15%'>
		   籍贯
		   <input type="text" id="nativeplace" style="width:50%" value="" onclick="show()" onchange="search()"/></td>
        <td width='15%'>年龄:
        	<input type="text" id="low_age" style="width:25px" onchange="search()"/>&nbsp;--
        	<input type="text" id="high_age" style="width:25px" onchange="search()"/>        </td>
		<td width='15%'>系统测评
		 <select id="sysResult" style='width:140' onchange="search()">
          <option value='无'>无</option>
          	<option value='一'>一</option>
			<option value='二'>二</option>
			<option value='三'>三</option>
			<option value='四'>四</option>
			<option value='五'>五</option>		
			<option value='六'>六</option>
			<option value='七'>七</option>
			<option value='八'>八</option>
			<option value='九'>九</option>
          </select>型</td>
          <td width='15%'>实际评测
		 <select id="relResult" style='width:140' onchange="search()">
          <option value='无'>无</option>
          	<option value='一'>一</option>
			<option value='二'>二</option>
			<option value='三'>三</option>
			<option value='四'>四</option>
			<option value='五'>五</option>		
			<option value='六'>六</option>
			<option value='七'>七</option>
			<option value='八'>八</option>
			<option value='九'>九</option>
          </select>型</td>
        <td width='45px' align="left"><input type="button" style="width:45px; height:20px; border:0px;background-image:url('/NineHouse/skin/images/frame/search.gif');cursor:pointer" class="np" onclick="search()" /></td>
       </tr>
      </table>
    </td>
  </tr>

</table>

</form>
<!--  快速转换位置按钮  -->

<%
	int segment = Integer.parseInt(request.getParameter("segment"));
	int nowpage = Integer.parseInt(request.getParameter("nowpage"));
	int eachsize = Integer.parseInt(request.getParameter("eachsize"));
	//用户信息
	List userlist = new ArrayList();
	List userlist1 = new ArrayList();
	UserManager um = new UserManager();
	userlist1 = um.queryAllUser();
	int segmentsize = userlist1.size();
	userlist = um.querySomeUser((segment-1)*180,180);
	
	if(eachsize<=0) eachsize = 10;
	if(nowpage<1||userlist.size()<=eachsize)
		nowpage = 1;
	else if(nowpage*eachsize>userlist.size()){
		nowpage = (int)(Math.floor((userlist.size()-1)/eachsize)+1);
	}
	
	//答题记录
	List anslist  = new ArrayList();
	AnswerRecord ansrec = new AnswerRecord();
	anslist = um.getAllAnswerByUser(userlist);
 %>
 
<!--  内容列表   -->

<table width="1044px" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px;overflow-y:auto;" border="0px">
	<tr bgcolor="#E7E7E7">
		<td height="24" width="1044px" style="background-image:url('../../skin/images/tbg.gif')" align="center">所有测试用户信息列表</td>
	</tr>
</table>


<form id="form2">
<div style="margin-left:auto;margin-right:auto; overflow-x:auto;overflow-y:auto;max-height:450px;width:1042px;border:1px solid #D1DDAA;">
<table id="tb1" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="width:1044px;height:auto;margin-top:8px;" border="0px">
<tr align="center" bgcolor="#FAFAF1" style="height:22">
    <td width="30px">选择</td>
	<td width="30px">ID</td>
	<td width="100px">学号(工号)</td>
	<td width="100px">姓名</td>
	<td width="40px">性别</td>
	<td width="40px">年龄</td>
	<td width="80px">籍贯</td>
	<td width="80px">系统测评</td>
	<td width="80px">实际评测</td>
	<td width="130px">开始时间</td>
	<td width="130px">答题时间/分钟</td>
	
	<%
     	for(int i=1;i<=180;i++){
    %>
     	<td width="40px" style="display:none"><%=i %></td>
    <%
     	}
    %>
    <td width="50px">查看</td>
	<td width="100px">操作</td>
</tr>


	<%
		int size = userlist.size();
		int size1 = anslist.size();
	    DecimalFormat df = new DecimalFormat("#.00");  
		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		java.util.Date date1,date2;
		String[] gender = {"男","女"};
		String[] character = {"一","二","三","四","五","六","七","八","九",""};
		String[] answers = {"是","否","不确定","",""};
		int index1,index2,index3;
		boolean flag = false;
		int anscount=0;
		for(int i=0;i<size;i++){
			User user = new User();
			user = (User)userlist.get(i);
			if(anscount<size1){
				ansrec = (AnswerRecord)anslist.get(anscount);
				if(ansrec.getId()==user.getId()){
					flag = true;
					anscount++;
				}else{
					flag = false;
				}
			}else{
				ansrec = null;
				flag = false;
			}
			index1 = Integer.parseInt(user.getGender());
			//index2 = Integer.parseInt(user.getSysResult())-1;
			if(user.getRelResult()==null||user.getRelResult().equals("")){
				index3 = 9;
			}else{
				index3 = Integer.parseInt(user.getRelResult())-1;
			}
			if(i<eachsize*nowpage&&i>=(nowpage-1)*eachsize){
		%>
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#D1DDAA';" onMouseOut="javascript:this.bgColor='#FFFFFF';" style="height:22px;display:table-row">
			<td><input name="id" type="checkbox" id="id" value="101" class="np"/></td>
			<td onclick="showUser(<%=user.getId()%>,'<%=user.getSysResult()%>')"><%=(segment-1)*180+i+1 %><input type="text" style="width:95%;text-align:center;display:none" disabled="disabled" value="<%=user.getId()%>"/></td>
			<td><%=user.getStuId()%></td>
			<td><%=user.getName()%></td>
			<td><%=gender[index1]%></td>
			<td><%=user.getAge()%></td>
			<td><%=user.getNativeplace()%></td>
			<td><%=user.getSysResult()%>&nbsp;型</td>
			<td><input type="text" style="width:60%;text-align:center;" value="<%=character[index3]%>"/>&nbsp;型</td>
			<td><%=user.getStart_datetime()%></td>
			<%
	
           // String dstr="2008-4-24";  
		   double during_time=0.0;
		   if(user.getEnd_datetime()!=null)
		   {
           date1=format.parse(user.getStart_datetime());
		   date2=format.parse(user.getEnd_datetime());
		    long time1=date1.getTime();  
            long time2=date2.getTime();  
            during_time=Math.abs(time2-time1)/(60000.0);
	       }
			// String during_time=format.format(date);
			%>
			<td><%=df.format(during_time)%></td>
	
			<%
			if(flag){
	     		for(int k=0;k<180;k++){
	     		%>
	     		<td style="display:none"><%=answers[ansrec.getAnswer().charAt(k)-48] %></td>
	     		<%
	     		}
     		}else{
     			for(int k=0;k<180;k++){
	     		%>
	     		<td style="display:none">不确定</td>
	     		<%
	     		}
     		}
     		%>
	     	<td ><input type="button" onclick="showUser(<%=user.getId()%>,'<%=user.getSysResult()%>')" id="show2" value="查看" style="background-color:#FFFFFF;cursor:pointer" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/></td>
		 	<td><input type="button" value="修改" style="background-color:#FFFFFF;cursor:pointer" onclick="modify(this)" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/><input type="button" name="delete" value="删除" style="background-color:#FFFFFF;cursor:pointer" onclick="deleteUser(<%=user.getId()%>,'<%=user.getName()%>')" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/></td>
			</tr>
		<%
			}else{
			%>
			<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#D1DDAA';" onMouseOut="javascript:this.bgColor='#FFFFFF';" style="height:22px;display:none">
			<td><input name="id" type="checkbox" id="id" value="101" class="np"/></td>
			<td onclick="showUser(<%=user.getId()%>,'<%=user.getSysResult()%>')"><%=(segment-1)*180+i+1%><input type="text" style="width:95%;text-align:center;display:none" disabled="disabled" value="<%=user.getId()%>"/></td>
			<td><%=user.getStuId()%></td>
			<td><%=user.getName()%></td>
			<td><%=gender[index1]%></td>
			<td><%=user.getAge()%></td>
			<td><%=user.getNativeplace()%></td>
			<td><%=user.getSysResult()%>&nbsp;型</td>
			<td><input name="text" type="text" style="width:60%;text-align:center" value="<%=character[index3]%>"/>&nbsp;型</td>
			<td><%=user.getStart_datetime()%></td>
			<%
			  double during_time=0.0;
		   if(user.getEnd_datetime()!=null)
		   {
           date1=format.parse(user.getStart_datetime());
		    date2=format.parse(user.getEnd_datetime());
		    long time1=date1.getTime();  
            long time2=date2.getTime();  
            during_time=Math.abs(time2-time1)/(60000.0);
             }
			%>
		    <td><%=df.format(during_time)%></td>
	
			<%
			if(flag){
	     		for(int k=0;k<180;k++){
	     		%>
	     		<td style="display:none"><%=answers[ansrec.getAnswer().charAt(k)-48] %></td>
	     		<%
	     		}
     		}else{
     			for(int k=0;k<180;k++){
	     		%>
	     		<td style="display:none">不确定</td>
	     		<%
	     		}
     		}
     		%>
	     	<td ><input type="button" onclick="showUser(<%=user.getId()%>,'<%=user.getSysResult()%>')"  value="查看" style="background-color:#FFFFFF;cursor:pointer" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/></td>
		 	<td><input type="button" value="修改" style="background-color:#FFFFFF;cursor:pointer" onclick="modify(this)" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/><input type="button" name="delete2" value="删除" style="background-color:#FFFFFF;cursor:pointer" onclick="deleteUser(<%=user.getId() %>)" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/></td>
			</tr>
			<%
			}
		}
	%>
</table>
</div>
</form>


<form id="form4">
	<input type="hidden" id="value1" name="value1"/>
	<input type="hidden" id="value2" name="value2"/>
	<input type="hidden" id="segment" name="segment"/>
	<input type="hidden" id="nowpage" name="nowpage" />	
	<input type="hidden" id="eachsize" name="eachsize" />	
</form>

<form id="form5">
<table width="1044px" border="0.5" cellpadding="1" cellspacing="1" bgcolor="#EEF4EA" align="center" style="margin-top:2px;">

<tr align="right" bgcolor="#E7E7E7">

	<td width="20%" height="30px" colspan="2">
	<a href="javascript:selAll()" class="coolbg">全选</a> &nbsp;
    <a href="javascript:noSelAll()" class="coolbg">取消</a> &nbsp;
    <a href="javascript:deleteSomeUser()" class="coolbg" >&nbsp;批量删除&nbsp;</a>  &nbsp;
	 <a href="javascript:updateArc()"  class="coolbg" style="margin-right:10%">刷新</a>
	
  		<input type="file" name="file" id="import" style="background-color:#E7E7E7;"/>
	    <input type="button" value="导入" style="background-color:#ffffff;margin-right:20px;cursor:pointer"  onclick="importUserInfo()" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/>
		导出：
		<input type="button" value="当前" style="background-color:#ffffff;cursor:pointer" onclick="exPresentRows()" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/>
		<input type="button" value="所有" style="background-color:#ffffff; margin-right:20px;cursor:pointer" onclick="exAllUserInfoAndAnswer()" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/>
		
		删除：
		<input type="button" value="无效用户" style="background-color:#ffffff;cursor:pointer"  onclick="deleteIllegalUser()" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/>
		<input type="button" value="删除所有" style="background-color:#ffffff;margin-left:10px;cursor:pointer"  onclick="deleteAllUser()" onmouseover="this.style.backgroundColor='#D1DDAA';" onmouseout="this.style.backgroundColor='#FFFFFF';"/>
		<input type="hidden" id="segment5" name="segment5" />
		<input type="hidden" id="nowpage5" name="nowpage5" />
		<input type="hidden" id="eachsize5" name="eachsize5" />
	</td>
</tr>
<tr bgcolor="#ffffff">
	<td height="30px" align="center" >

		<input type="button" class="button1" id = "prepage" value="上一页"  style="cursor:pointer" onclick="more(1)"  onmouseover="this.style.backgroundColor='#D1DDAA';javascript:overto()"   onmouseout="this.style.backgroundColor='#FFFFFF';javascript:moveto()"/>
 
		<input type="button" class="button1" id="segments" value="<%=segment%>"/>/
		<input type="button" class="button1" id="allsegments" value=""/>
		<input type="hidden" id="allusers" value="<%=segmentsize%>"/>
		<input type="button" class="button1" id = "nextpage" value="下一页" style="cursor:pointer" onclick="more(2)"  onmouseover="this.style.backgroundColor='#D1DDAA';javascript:overto()" onmouseout="this.style.backgroundColor='#FFFFFF';javascript:moveto()"/>

		<input type="button" class="button1" id="pre" value="（分页上）" style="margin-left:40px;cursor:pointer" onclick="turnTo(this.value)" onmouseover="this.style.backgroundColor='#D1DDAA'"  onmouseout="this.style.backgroundColor='#FFFFFF'"/>
		<input type="button" id="page" value="<%=nowpage%>" class ="button1" />/
		<input type="hidden" id="size" value="<%=size %>"/>
		<input type="hidden" id="each" value="<%=eachsize%>"/>
		<input type="button" class ="button1" id="pages" value="1"/>
		<input type="button" class="button1" id="next" value="（分页下）" style="cursor:pointer" onclick="turnTo(this.value)" onmouseover="this.style.backgroundColor='#D1DDAA'" onmouseout="this.style.backgroundColor='#FFFFFF'"/>

		
	</td>
	<td width="20%" align="right">分页:
		<select id="pagesize" onchange="setPageSize(this.value)">
          <option value='5'>5</option>
          	<option value='10'>10</option>
            <option value='15'>15</option>
            <option value='20'>20</option>
            <option value='30'>30</option>
            <option value='60'>60</option>
            <option value='90'>90</option>
            <option value='120'>120</option>
            <option value='180'>180</option>
        </select>&nbsp;行
          
        <select id="column_num" onchange="showColumn(this.value)">
        	<option value="11">基本</option>
   			<option value="191">所有</option>
   		</select>列
   	</td>
</tr>
</table>

</form>

	<div style=" margin-left:25%"><font   id="seeme">&nbsp;</font></div> 

</body>
</html>
