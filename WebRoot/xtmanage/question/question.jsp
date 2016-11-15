<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8" %>
<%@ page import="edu.ae.manager.*" import="edu.ae.entity.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>题库</title>
	<link rel="stylesheet" type="text/css" href="/NineHouse/skin/css/base.css"/>
	<script type="text/javascript" src="/NineHouse/js/jquery.js"></script>
	<script type="text/javascript" src="/NineHouse/js/thickbox_plus.js"></script>
	<link rel="stylesheet" type="text/css" href="/NineHouse/css/thickbox.css" />
    <link rel="stylesheet" type="text/css" href="/NineHouse/assets/css/sweet-alert.css"/>
		
      <link rel="stylesheet" href="/NineHouse/assets/css/style.css"/>
      <script src="/NineHouse/assets/js/sweet-alert.min.js"></script>
<script language="javascript">
function viewArc(aid){
	if(aid==0) aid = getOneItem();
	window.open("archives.asp?aid="+aid+"&action=viewArchives");
}
function editArc(aid){
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=editArchives";
}
function updateArc(aid){
	location.reload();
}
function checkArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=checkArchives&qstr="+qstr+"";
}
function moveArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=moveArchives&qstr="+qstr+"";
}
function addQuestion(){
	window.location.href="/NineHouse/xtmanage/question/addquestion.jsp";
	window.submit();
}
function delArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives.asp?aid="+aid+"&action=delArchives&qstr="+qstr+"";
}

//获得选中文件的文件名
function getCheckboxItem()
{
	var allSel="";
	if(document.form2.id.value) return document.form2.id.value;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			if(allSel=="")
				allSel=document.form2.id[i].value;
			else
				allSel=allSel+"`"+document.form2.id[i].value;
		}
	}
	return allSel;
}

//获得选中其中一个的id
function getOneItem()
{
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
function selAll(){
	var trs = document.getElementById("form0").getElementsByTagName("tr");
	var length = trs.length;
	for(i=2;i<length-2;i++){
		var input = trs[i].getElementsByTagName("input")[0];
		input.checked=true;
	}
}
function noSelAll(){
	var trs = document.getElementById("form0").getElementsByTagName("tr");
	var length = trs.length;
	for(i=2;i<length-2;i++){
		var input = trs[i].getElementsByTagName("input")[0];
		input.checked=false;
	}
}
function isone()
{
	 var count = 0;
	 for(i=0;i<document.form2.id.length;i++)
	 {
		if(document.form2.id[i].checked)
		{   
			count ++;
			
		}
	}
	if (count < 1) 
	{
	    alert("没有选择数据！");
		return;
    }
         
    if (count > 1) 
	{
         alert("只可选择一条信息");
		 return;
    }
	else{
	location="roleset.html?height=400;width=300";
	}
}
function noone()
{
	var count=0;
	for(i=0;i<document.form2.id.length;i++)
	{
		if(document.form2.id[i].checked)
		{
			count++;
		}
	}
	if(count==0)
	{
		alert("您没有选择删除目标！");
		return;
	}
	else{
	    alert("您已成功删除目标！");
		location.reload();

	}
}

function deleteQuestion(id){
 swal({  
       title: "你确定操作吗? ",  
       text: "你确定删除第 "+id+" 题？ ",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 ",
	   cancelButtonText: "取消"
	   },
       function(){
 		window.location.href="/NineHouse/deleteQuestion?id="+id+"";
	   window.submit();
       });
	//alert(id);

}
function editQuestion(id){
	swal({  
       title: "你确定操作吗? ",  
       text:"你确定修改第  "+ id+" 个题目吗？",  
       type: "warning",  
       showCancelButton: true,  
       confirmButtonColor: "#DD6B55",  
       confirmButtonText: "确定 ",
	   cancelButtonText: "取消"
	   },
       function(){
 			window.location.href="/NineHouse/xtmanage/question/editquestion.jsp?id="+id+"";
	window.submit();
       });
}
function turnTo(input){
	var oform = document.getElementById("form0");
	var page = document.getElementById("page");
	var size = document.getElementById("size");
	var each = document.getElementById("each");
	var allpage = size.value/each.value;
	
	var trs = oform.getElementsByTagName("tr");
	//下标从2开始为题目信息
	//alert(trs[2].innerHTML);
	if(input=="上一页"){
		if(page.value>1){
			page.value = page.value-1;
		}
	}else{
		if(page.value<allpage){
			page.value = (page.value%1000)+1;
		}
	}
	
	var j = (page.value-1)*each.value+2;
	var k = j+(each.value%1000);
	//2由form0中tr个数与题目所占行数决定
	var size = trs.length-1;
	for(var i=2;i<size;i++){
		if(i>=j&&i<k){
			trs[i].style.display="table-row";
		}else{
			trs[i].style.display="none";
		}
	}
}

function search(character){
	var form2 = document.getElementById("form0");
	var trs = form2.getElementsByTagName("tr");
	//alert(trs.length);
	//tr从第3个开始为user信息，最后一个为页码
	var length = trs.length-1;
	if(character=="0"){
		document.getElementById("page").value = 1;
		turnTo("上一页");
	}else{
		for(var i=2;i<length;i++){
			var tds = trs[i].getElementsByTagName("td");
			if(tds[3].innerHTML==character){
				trs[i].style.display="table-row";
			}else{
				trs[i].style.display="none";
			}
		}
	}
}
function searchKey(){
	var keyword = document.getElementById("keyword").value;
	//alert(keyword);
	var form0 = document.getElementById("form0");
	var trs = form0.getElementsByTagName("tr");
	//alert(trs.length);
	//tr从第3个开始为user信息，最后一个为页码
	var length = trs.length-1;
	if(keyword!=""){
		for(var i=2;i<length;i++){
			var tds = trs[i].getElementsByTagName("td");
			if(tds[2].innerHTML.indexOf(keyword)>-1){
				trs[i].style.display="table-row";
			}else{
				trs[i].style.display="none";
			}
		}
	}
	else
	{
	  alert("未输入任何关键字，请重新编辑或者点击刷新取消搜索！");
	}
}
function setAllPages(){
	var each = document.getElementById("each").value;
	var size = document.getElementById("size").value;
	var pages = document.getElementById("pages");
	if(each>0)
		pages.value = Math.ceil(size/each);
	else
		pages.value = 1;
}

function setPageSize(size){
	document.getElementById("each").value=size;
	document.getElementById("page").value=1;
	turnTo("上一页");
	setAllPages();
}


function importQuestion(){

	var form = document.getElementById("form3");
	var filename = document.getElementById("import");  
        //上次修改时间  
	if(filename.value!="")
		{
     var FileExt=filename.value.replace(/.+\./,"");   //正则表达式获取后缀
	 if(FileExt!="xls")
	 {
	    alert("请选择.xls 文件");
		return ;
	 }
	 else
	 { 
	   form.action="/NineHouse/fileUpload";
	   form.submit();
	  }
	}
	else
	{
	   alert("未选择文件！");
	   return ;
	}
}
function exportQuestion(){
     alert("请您点击浏览器右上“打开菜单”按钮，选择“选项”->“常规”，勾选下载（总是询问保存文件的位置）项，点击确定按钮关闭“选项”窗口。 以后Firefox在每次下载文件时都会提示您选择保存的位置，并且您可以更改文件的名称。若不会操作请询问孙导，这是浏览器自己的默认设置，谢谢！");
	var form = document.getElementById("form3");
	form.action="/NineHouse/exportQuestion";
	form.submit();
}

window.onload = function(){
	//turnTo("上一页");
	setAllPages();
}
</script>
</head>
<body style="margin-left:0" background='/NineHouse/skin/images/allbg.gif'>

<!--  快速转换位置按钮  -->
<table width="90%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" style="background-image:url('/NineHouse/skin/images/newlinebg3.gif')"><div align="right"> 
  </div><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  	<td align="center">
  		<input type="button" onclick="javascript:addQuestion();" value='添加新题目' style="background-color:#E7E7E7;cursor:pointer"/>
  	</td>
  	
  </tr>
</table>
</td>
</tr>
</table>
  
<!--  内容列表   -->

<%
	List qslist = new ArrayList();
	QuestionManager qsmanager = new QuestionManager();
	qslist = qsmanager.queryAllQuestion();
%>

<form id="form0">
<div style="margin-left:auto;margin-right:auto; overflow-x:auto;overflow-y:auto;max-height:520px;width:1044px;">
<table width="1044px" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px;">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="6" style="background-image:url('../../skin/images/tbg.gif')" align="center">&nbsp;题库信息列表&nbsp;</td>
	</tr>
<tr align="center" bgcolor="#FAFAF1" style="height:22">
	<td width="5%">选择</td>
	<td width="5%">序号</td>
	<td width="66%">题目内容</td>
	<td width="8%">对应类型</td>
	<td width="8%">操作</td>
	<td width="8%">操作</td>
</tr>

<%
	Questions qs;
	int size = qslist.size();
	String[] character = {"一","二","三","四","五","六","七","八","九"};
	int index;
	for(int i=0;i<size;i++){
		qs = new Questions();
		qs = (Questions)qslist.get(i);
		index = Integer.parseInt(qs.getRelatedCharacter())-1;
		if(i<10){
		%>
			<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#D1DDAA';" onMouseOut="javascript:this.bgColor='#FFFFFF';" align="center" style="height:22">
			<td><input name="id" type="checkbox" id="id" value="101" class="np"/></td>
			<td align="center"><%=qs.getId() %></td>
			<td align="left"><%=qs.getContent() %></td>
			<td><%=character[index] %>型</td>
			<td onclick="deleteQuestion(<%=qs.getId() %>)"> <input type="button" value="删除" style="cursor:pointer"/></td>
			<td onclick="editQuestion(<%=qs.getId() %>)"><input type="button" value="编辑" style="cursor:pointer"/></td>
			</tr>
	<%
		}else{
		%>
		<tr bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#D1DDAA';" onMouseOut="javascript:this.bgColor='#FFFFFF';" align="center" style="height:22;display:none">
			<td><input name="id" type="checkbox" id="id" value="101" class="np"/></td>
			<td align="center"><%=qs.getId() %></td>
			<td align="left"><%=qs.getContent() %></td>
			<td><%=character[index] %>型</td>
	<td onclick="deleteQuestion(<%=qs.getId() %>)"> <input type="button" value="删除" style="cursor:pointer"/></td>
			<td onclick="editQuestion(<%=qs.getId() %>)"><input type="button" value="编辑" style="cursor:pointer"/></td>
		</tr>
		<%
		}
	}
 %>



<tr bgcolor="#FAFAF1" style="width:30px">
  <td height="28" colspan="6">
    &nbsp;
    <a href="javascript:selAll()" class="coolbg">全选</a>
    <a href="javascript:noSelAll()" class="coolbg">取消</a>
    <a href="javascript:updateArc()" class="coolbg">&nbsp;更新&nbsp;</a>

  </td>
</tr>
</table>
</div>
</form>

<table width="90%" align="center" border="0" cellpadding="0" bgcolor="#D1DDAA" style="margin-top:1px;">
<tr align="center"><td>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#D1DDAA" align="center" style="margin-top:0px;">
<tr align="right" bgcolor="#EEF4EA">
	<td width="20%"></td>
	<td height="36" colspan="6" align="center">
		<input type="button" value="上一页" style="background-color:#E7E7E7;cursor:pointer" onclick="turnTo(this.value)"/>
		<input type="button" id="page" value="1" style="background-color:#E7E7E7"/>/
		<input type="hidden" id="size" value="<%=size %>"/>
		<input type="hidden" id="each" value="10"/>
		<input type="button" id="pages" value="1" style="background-color:#E7E7E7"/>
		<input type="button" value="下一页" style="background-color:#E7E7E7;cursor:pointer" onclick="turnTo(this.value)"/>
	</td>
	<td width="20%">每页
		<select id="pagesize" style='width:150' onchange="setPageSize(this.value)">
          <option value='5'>5</option>
          	<option value='10' selected="selected">10</option>
            <option value='15'>15</option>
            <option value='20'>20</option>
            <option value='30'>30</option>
            <option value='45'>45</option>
            <option value='60'>60</option>
            <option value='90'>90</option>
            <option value='180'>180</option>
          </select>&nbsp;题
	</td>
</tr>
</table>
</td>
</tr>
</table>



<!--  搜索表单  -->
<form id='form3' action='' method='post' enctype="multipart/form-data">
<input type='hidden' name='dopost' value='' />
<table width='90%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td style="background_image:'skin/images/frame/wbg.gif' align:center" width="100%">
      <table border='0' cellpadding='0' cellspacing='0' width="100%">
        <tr>
          <td width='90' align='center'>搜索条件：</td>
          <td width='160'>
          <select id="character" style='width:150' onchange="search(this.value)">
          <option value='0'>性格类型</option>
          	<option value='一型'>一型</option>
            <option value='二型'>二型</option>
            <option value='三型'>三型</option>
            <option value='四型'>四型</option>
            <option value='五型'>五型</option>
            <option value='六型'>六型</option>
            <option value='七型'>七型</option>
            <option value='八型'>八型</option>
            <option value='九型'>九型</option>
          </select>
        </td>
        <td width='70'>
          关键字：
        </td>
        <td width='160'>
          	<input type="text" id='keyword' style='width:150px' />
        </td>
        <td width='110'><input type="button" style="background-image:url('/NineHouse/skin/images/frame/search.gif'); width:45px; height:20px; border:0;cursor:pointer" onclick="searchKey()" /></td>
        <td align="right" >
  			<input type="file" name="file" id="import"  style="background-color:#E7E7E7;"/>
  			<input type="button" value="导入" onclick="importQuestion()" id="import1" style="background-color:#E7E7E7;cursor:pointer"/>
  			<input type="button" value="导出" onclick="exportQuestion()" style="background-color:#E7E7E7;cursor:pointer"/>
  		</td>
       </tr>
      </table>
    </td>
  </tr>
  
</table>
<div style="height:30px"></div>
</form>
</body>
</html>
