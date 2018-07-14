<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page language="java" import="javax.servlet.http.Cookie,java.net.*"%>
<%	String username="";//设置从cookie接受登录名的变量
	String password="";//设置从cookie接受密码的变量
	String check ="";//设置是否有勾选记住密码的标识
	Cookie[] cookies = request.getCookies();//从request中获取cookie 这里拿到的是一个cookie对象数组
	for(int i=0;cookies!=null&&i<cookies.length;i++){
	Cookie cookie = cookies[i];
	if(URLDecoder.decode(cookie.getName(), "UTF-8").equals("username")){
		username=URLDecoder.decode(cookie.getValue(), "UTF-8");
		check="checked";
	}
	
	if(cookie.getName().equals("password")){
		password=cookie.getValue();
	}
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>

<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/login.css" rel="stylesheet" type="text/css" />
<jsp:include page="menu.jsp" />
<script>
	function checkForm() {
		var username = document.getElementById("username").value;
		if (username == null || username == '') {
			alert("用户名不能为空！")
			return false;
		}
		var password = document.getElementById("password").value;
		if (password == null || password == '') {
			alert("密码不能为空！")
			return false;
		}
		var checkcode = document.getElementById("checkcode").value;
		if (checkcode == null || checkcode == '') {
			alert("验证码不能为空！")
			return false;
		}
	}
	function change() {
		var img1 = document.getElementById("checkImage");
		img1.src = "<%=path%>/checkImg.action?" + new Date().getTime();
	}
</script>
</head>
<body style="margin-top: 80px">
	<div class="container login">
		<div class="span12">
			<div class="ad">
				<img src="<%=path%>/image/login.jpg" width="500" height="330"
					alt="会员登录" title="会员登录">
			</div>
		</div>
		<div class="span12 last">
			<div class="wrap">
				<div class="main">
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>
					<form id="loginForm" action="<%=path%>/user_login.action"
						method="post"
						onsubmit="return checkForm();">
						<table>
							<tbody>
								<tr>
									<th>用户名/E-mail:</th>
									<td><input type="text" id="username" name="username"
										class="text" value="<%=username%>" maxlength="20"></td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20"
										value="<%=password%>"></td>
								</tr>
								<tr>
									<th>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text" maxlength="5"
											></input><img id="checkImage"
											class="captchaImage" src="<%=path%>/checkImg.action"
											onclick="change()">
									</span></td>
								</tr>
								<tr>
								</tr>
								<tr>
									<th></th>
									<td><span><font color="red"><s:actionerror /></font></span>
									</td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox" name="remember"
											id="remember" value="remember" <%=check%>>记住用户名
									</label> <label> &nbsp;&nbsp;<a
												href="<%=path%>/user_changePwdPage.action">忘记密码?</a>
									</label></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录">
									</td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！
												<a href="<%=path%>/user_registPage.action">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>
