<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<jsp:include page="common/common.jsp"></jsp:include>
<style type="text/css">
* {margin:0;padding:0;}
html {width:100%;height:100%;font-size:12px;font-family:Arial, Helvetica, sans-serif; -webkit-text-size-adjust: none;	background:#ffffff;}
body 
{ 
    width:100%;
	height:100%;
}
.container
{
    width:712px;
	position:relative;
	top:50%;
	margin:0 auto;
}
.loginpart
{
	width:712px;
	height:341px;    
	position:absolute;
	top:-170px;
}
.loginleft
{
    float:left;
	width:390px;
	height:341px;
	background:url(${pageContext.request.contextPath}/resources/ecplatform/images/login/login_left.gif);
}
.logo
{
    width:140px;
	height:64px;
	position:absolute;
	left:10px;
	top:10px;
	background:url(${pageContext.request.contextPath}/resources/ecplatform/images/login/logo.png) no-repeat;
}
.loginright
{
    float:left;
	width:322px;
	height:341px;
	background:url(${pageContext.request.contextPath}/resources/ecplatform/images/login/login_right.gif);
}
.languagepart
{
    position:absolute;
	top:10px;
	right:9px;
	z-index:9;
}

.loginbar
{
    width:280px;
	height:100px;
	position:absolute;
	top:90px;
	left:411px;
}
.loginlabel
{
    display:inline-block;
	width:110px;
	height:26px;
	line-height:30px;
}
.loginbtn
{
    margin:5px 0 0 110px;
	width:154px;
	height:26px;
	line-height:26px;
	text-align:center;
	cursor:pointer;
	background:url(${pageContext.request.contextPath}/resources/ecplatform/images/login/loginnoraml_btn.gif) no-repeat;
}
.loginbtnon
{
    margin:5px 0 0 110px;
	width:154px;
	height:26px;
	line-height:26px;
	text-align:center;
	cursor:pointer;
	background:url(${pageContext.request.contextPath}/resources/ecplatform/images/login/loginsel_btn.gif) no-repeat;
}
.footer
{
	width:712px;
	height:341px;
	text-align:center;    
	position:absolute;
	top:185px;    
}
.checkbox
{
    vertical-align:text-bottom;
    cursor:pointer;
}
.anonymous
{
	padding-left:110px;
	height: 30px;
	display:block;
}
.logininputwidth {width:150px; font-size:12px;height: 20px;}
</style>
<script type="text/javascript">
	function onLoad() {
		var top = window.top;
		
		var self = window.self;
		if (top != self) {
			top.location.href = getUrl('');
		}
	}
	onLoad();
	function getWindow() {
		var obj=window.self;
		while(true)
		{
			if(obj.document.getElementById("myFlag"))
			{
				return obj;
			}
		   	obj=obj.window.parent;
  		};
	}
</script>
</head>
<body onkeydown="bindEnter(event)">
<div class="container">
    <div class="loginpart">
        <div class="loginleft">
	    <div class="logo"></div>
	    </div>
        <div class="loginright">
	        <div class="loginbar">
	        	<div class="anonymous"><label id="errorMsg" style="color:red;"></label></div>
	            <div><label name="lausername" class="loginlabel">用户名：</label><input name="username" id='username' class="logininputwidth" type="text" maxlength="32" /></div>
                <div><label name="lapassword" class="loginlabel">密&nbsp;&nbsp;码：</label><input name="password" id="password" class="logininputwidth" type="password" maxlength="20" /></div>
	            <div class="loginbtn" onclick="login()" onmouseover="this.className = 'loginbtnon'" onmouseout="this.className = 'loginbtn'">登&nbsp;&nbsp;录<label name="lalogin" class="mousepointer"></label></div>
	        </div>
	    </div>	
    </div>
    <div class="footer">Copyright (c) 2014, cooperlink.cn All Rights Reserved.</div>
</div>
<script type="text/javascript">
function login() {
	var username = $('#username').val();
	var password = $('#password').val();
	if (!username) {
		$('#errorMsg').html('用户名不能为空。');
		return;
	}
	if (!password) {
		$('#errorMsg').html('密码不能为空。');
		return;
	}
	$.post(
	    	getUrl('/login/loginAction'),
	   		{username:$('#username').val(), password: $('#password').val()},
	   		function(result) {
	   			if (result.constructor == Object) {
	   				var msg = '';
	   				if (result.success) {
	   					window.location.href = getUrl('/');
	   					return;
	   				} else {
	   					msg = '登陆失败。';
	   					if (result.msg) {
	   						msg = result.msg;
	   					}
	   				}
	   			} else {
	   				msg = '系统异常。';
	   			}
   				$('#errorMsg').html(msg);
	    	},
	   		'json'
	   	);
}

function bindEnter(obj) {
	if(obj.keyCode == 13) {
		var user = document.getElementById('username');
		var pwd = document.getElementById('password');
		//var yzmTF = document.getElementById('yzm_tf');
		var atv = document.activeElement;
		//if (user == atv || pwd == atv || yzmTF == atv) {
		if (user == atv || pwd == atv) {
			login();
		}
	 }
}
</script>
</body>
</html>