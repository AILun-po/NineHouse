$(document).ready(TB_launch); 

// function for adding Thickbox to elements of class .thickbox
// wrapped by Christian Montoya for uses other than $(document).ready
function TB_launch() {
$("a.thickbox").click(function(){
  var t = this.title;
  TB_show(t,this.href);
  this.blur();
  return false;
});
}

function TB_show(caption, url) { //function called when the user clicks on a thickbox link
	try {
		$("body")
		.append("<div id='TB_overlay'></div><div id='TB_window'></div>");
		$("#TB_overlay").css("opacity","0.6");
		$("#TB_overlay").css("filter","alpha(opacity=60)");
		$("#TB_overlay").css("-moz-opacity","0.6");
		$(window).resize(TB_position);
		$("body").append("<div id='TB_load'><div id='TB_loadContent'><img src='images/circle_animation.gif' /></div></div>");
		$("#TB_overlay").show();
		var urlString = /.jpg|.jpeg|.png|.gif|.html|.htm/g;
		var urlType = url.match(urlString);
	
		if(urlType == '.jpg' || urlType == '.jpeg' || urlType == '.png' || urlType == '.gif'){//code to show images

			var imgPreloader = new Image();
			imgPreloader.onload = function(){

			// Resizing large images added by Christian Montoya
			var de = document.documentElement;
			var x = (self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth) - 50;
			var y = (self.innerHeight || (de&&de.clientHeight) || document.body.clientHeight) - 80;
			if(imgPreloader.width > x) { 
				imgPreloader.height = imgPreloader.height * (x/imgPreloader.width); 
				imgPreloader.width = x; 
				if(imgPreloader.height > y) { 
					imgPreloader.width = imgPreloader.width * (y/imgPreloader.height); 
					imgPreloader.height = y; 
				}
			} 
			else if(imgPreloader.height > y) { 
				imgPreloader.width = imgPreloader.width * (y/imgPreloader.height); 
				imgPreloader.height = y; 
				if(imgPreloader.width > x) { 
					imgPreloader.height = imgPreloader.height * (x/imgPreloader.width); 
					imgPreloader.width = x;
				}
			}
			// End Resizing

			TB_WIDTH = imgPreloader.width + 30;
			TB_HEIGHT = imgPreloader.height + 60;
			$("#TB_window").append("<img id='TB_Image' src='"+url+"' width='"+imgPreloader.width+"' height='"+imgPreloader.height+"' alt='"+caption+"'/>"+ "<div id='TB_caption'>"+caption+"</div><div id='TB_closeWindow'><a href='#' id='TB_closeWindowButton'>x</a></div>"); 
			$("#TB_closeWindowButton").click(TB_remove);
			$("#TB_Image").click(TB_remove); // close when image clicked added by Christian Montoya
			TB_position();
			$("#TB_load").remove();
			$("#TB_window").slideDown("normal");
			}
	  
			imgPreloader.src = url;
		}
		
		if(urlType == '.htm' || urlType == '.html'){//code to show html pages
			
			var queryString = url.replace(/^[^\?]+\??/,'');
			var params = parseQuery( queryString );
			
			TB_WIDTH = (params['width']*1) + 30;
			TB_HEIGHT = (params['height']*1) + 40;
			ajaxContentW = TB_WIDTH - 30;
			ajaxContentH = TB_HEIGHT - 45;
			$("#TB_window").append("<div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton'>x</a></div><div id='TB_ajaxContent' style='width:"+ajaxContentW+"px;height:"+ajaxContentH+"px;'></div>");
			$("#TB_closeWindowButton").click(TB_remove);
			$("#TB_ajaxContent").load(url, function(){
			TB_position();
			$("#TB_load").remove();
			$("#TB_window").slideDown("normal");
			});
		}
		
	} catch(e) {
		alert( e );
	}
}

//helper functions below

function TB_remove() {
	// #TB_load removal added by Christian Montoya; solves bug when overlay is closed before image loads
	$("#TB_window").fadeOut("fast",function(){$('#TB_window,#TB_overlay,#TB_load').remove();}); 
	return false;
}

function TB_position() {
	var de = document.documentElement;
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var h = self.innerHeight || (de&&de.clientHeight) || document.body.clientHeight;
  
  	if (window.innerHeight && window.scrollMaxY) {	
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
		yScroll = document.body.scrollHeight;
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
		yScroll = document.body.offsetHeight;
  	}
	
	$("#TB_window").css({width:TB_WIDTH+"px",height:TB_HEIGHT+"px",
	left: ((w - TB_WIDTH)/2)+"px", top: ((h - TB_HEIGHT)/2)+"px" });
	$("#TB_overlay").css("height",yScroll +"px");
}

function parseQuery ( query ) {
   var Params = new Object ();
   if ( ! query ) return Params; // return empty object
   var Pairs = query.split(/[;&]/);
   for ( var i = 0; i < Pairs.length; i++ ) {
      var KeyVal = Pairs[i].split('=');
      if ( ! KeyVal || KeyVal.length != 2 ) continue;
      var key = unescape( KeyVal[0] );
      var val = unescape( KeyVal[1] );
      val = val.replace(/\+/g, ' ');
      Params[key] = val;
   }
   return Params;
}

function register(){
	var username = document.zhuce.username.value;
	var password = document.zhuce.password.value;
	var repassword = document.zhuce.repassword.value;
	var email = document.zhuce.email.value;
	var sex = document.zhuce.sex.value;
	var useradr = document.zhuce.useradr.value;
	
	if(username==""){
		alert("请输入昵称！");
	}
	else if(password==""){
		alert("请输入密码！");
	}
	else if(repassword==""){
		alert("请输入确认密码！");
	}
	else if(email==""){
		alert("请输入邮箱！");
	}
	else if(sex==""){
		alert("请选择性别！");
	}
	else if(useradr==""){
		alert("请输入所在地！");
	}
	else if(password!=repassword){
		alert("密码和确认密码必须相同！");
	}
	else{
		document.getElementById("zhuce").action="UserServlet";
		document.getElementById("zhuce").method="post";
		TB_remove();
	}
}
function update(){
	var username = document.xiugai.username.value;
	var password = document.xiugai.password.value;
	var repassword = document.xiugai.repassword.value;
	var sex = document.xiugai.sex.value;
	var useradr = document.xiugai.useradr.value;

	if(username==""){
		alert("请输入昵称！");
		document.getElementById("xiugai").target="center";
	}
	else if(password==""){
		alert("请输入密码！");
		document.getElementById("xiugai").target="center";
	}
	else if(repassword==""){
		alert("请输入确认密码！");
		document.getElementById("xiugai").target="center";
	}
	else if(sex==""){
		alert("请选择性别！");
		document.getElementById("xiugai").target="center";
	}
	else if(useradr==""){
		alert("请输入所在地！");
		document.getElementById("xiugai").target="center";
	}
	else if(password!=repassword){
		alert("密码和确认密码必须相同！");
		document.getElementById("xiugai").target="center";
	}
	else{
		document.getElementById("xiugai").action="UserServlet";
		document.getElementById("xiugai").method="post";
		TB_remove();
	}
}
function newLog(){
	document.getElementById("newlog").submit();
}
function del(logid) {
	alert("确定要删除吗？");
	location.href="LogServlet?logid="+logid+"&&type=delone";
}
