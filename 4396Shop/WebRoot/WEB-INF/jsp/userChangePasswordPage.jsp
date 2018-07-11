<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>修改密码</title>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/register.css" rel="stylesheet" type="text/css"/>
<jsp:include page="menu.jsp"/>

<script>
	
	var passwordRegex = /^\w{6,20}$/;
	

	var isPAValidate=true;
	var isRValidate=true;
	var isCValidate=true;
	function checkForm(){
	//校验用户名:
	//获得用户名文本框
	var password=document.getElementById("password").value;
	if(password==null||password==''){
		alert("密码不能为空！")
		return false;
	}
	var repassword=document.getElementById("repassword").value;
	if(repassword==null||repassword==''){
		alert("请重复密码！")
		return false;
	}
	if(!(isPAValidate==true&&isRValidate==true)){
		alert("信息不全或有误！请仔细检查！")
		return false;
	}
	}

	
	function checkPassword(node){
		var password=node.value;
		if(!passwordRegex.test(password)){
			if(password==null || password==''){
				document.getElementById("password_span").textContent="密码不能为空！"
				document.getElementById("password_span").style.color="red";
				isPAValidate=false;
			}else{
				document.getElementById("password_span").textContent="密码长度过短！密码长度6-20位"
				document.getElementById("password_span").style.color="red";
				isPAValidate=false;
			}
		}
		else{
		   	document.getElementById("password_span").textContent="√"
		 	document.getElementById("password_span").style.color="green";
		 	isPAValidate=true;
		}
	}
	function checkRepassword(node){
		repassword=document.getElementById("repassword").value;
		var password=document.getElementById("password").value;
		if(!passwordRegex.test(repassword)){		
			if(repassword==null || repassword==''){
				document.getElementById("repassword_span").textContent="密码不能为空！"
				document.getElementById("repassword_span").style.color="red";
				isRValidate=false;
			}else{
				document.getElementById("repassword_span").textContent="密码长度过短！密码长度6-20位"
				document.getElementById("repassword_span").style.color="red";
				isRValidate=false;
			}
		}
		else{
			if(password==repassword){
		   		document.getElementById("repassword_span").textContent="√"
		   		document.getElementById("repassword_span").style.color="green";
		   		isRValidate=true;
			}
			else{
				document.getElementById("repassword_span").textContent="两次输入的密码不一致！";
				document.getElementById("repassword_span").style.color="red";
				isRValidate=false;
			}	
		}
	}
	
	
	function checkcodeFunction(node){
		var checkcode=node.value;
		if(checkcode==null||checkcode==''){
			document.getElementById("checkcodeActionerror").textContent="验证码不能为空"
			isCValidate=false;
		}else{
			document.getElementById("checkcode_span").textContent=""
			isCValidate=true;
		}
	}
	
</script>
</head>
<body>
<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>修改密码</strong>USER REGISTER
						
					</div>
					<form id="registerForm" action="<%=path %>/user_userChangePwd.action?username=${username}"  method="post" novalidate="novalidate" onsubmit="return checkForm();">
						<table>
							<tbody>
							<tr>
								<th>
									<span class="requiredField">*</span>密&nbsp;&nbsp;码:
								</th>
								<td>
									<input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off" onblur="checkPassword(this);checkRepassword(document.getElementById('repassword'))"></input>
									<span id="password_span">密码由字母、小数和符号组成，密码长度6-20位</span>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>确认密码:
								</th>
								<td>
									<input type="password" id="repassword" name="repassword" class="text" maxlength="20" autocomplete="off"  onblur="checkRepassword(this)"></input>
									<span id="repassword_span"></span>
								</td>
							</tr>
									<th>
										<span class="requiredField">*</span>验证码:
									</th>
									<td>
										<span class="fieldSet">
											<input type="text" id="checkcode" name="code" class="text captcha" maxlength="5" autocomplete="off" onblur="checkcodeFunction(this)"></input>
										</span>
										<span id="checkcode_span"><font color="red"><s:actionerror id="checkcodeActionerror"/></font></span>
									</td>
								</tr>
							<tr>
								<th>&nbsp;
									
								</th>
								<td>
									<input type="submit" class="submit" value="确认修改"></input>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="<%=path%>/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"></img>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a >联系我们</a>
						|
					</li>
					<li>
						<a >招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a >友情链接</a>
						|
					</li>
					<li>
						<a  target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a >广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body>
