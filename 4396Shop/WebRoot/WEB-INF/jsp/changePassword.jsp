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
<title>找回密码</title>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/register.css" rel="stylesheet" type="text/css"/>
<jsp:include page="menu.jsp"/>

<script>
	
	var usernameRegex = /^[\u4e00-\u9fa5_a-zA-Z0-9]+$/;
	
	var emailRegex =  /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	

	var isUValidate=true;
	var isEValidate=true;
	function checkForm(){
	//校验用户名:
	//获得用户名文本框
	var username=document.getElementById("username").value;
	if(username==null||username==''){
		alert("用户名不能为空！")
		return false;
	}
	var email=document.getElementById("email").value;
	if(email==null||email==''){
		alert("邮箱不能为空！")
		return false;
	}
	else{
		
	}
	if(!(isUValidate==true&&isEValidate==true&&isNValidate==true)){
		alert("信息不全或有误！请仔细检查！")
		return false;
	}
	}
	//收到的字符串
	var getString;
	function findEmail(node){
		var username=node.value;
		if(!usernameRegex.test(username)){
			if(username==null || username==''){
				document.getElementById("username_span").textContent="用户名不能为空！"
				document.getElementById("username_span").style.color="red";
				isUValidate=false;
			}
			else{
				document.getElementById("username_span").textContent="用户名包含特殊字符"
				document.getElementById("username_span").style.color="red";
				isUValidate=false;
			}
		}
		else{
			// 1.创建异步交互对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function(){
				if(xhr.readyState == 4){
					if(xhr.status == 200){
						if(xhr.responseText==''){
							document.getElementById("emailTh").firstChild.nextSibling.textContent="";
							document.getElementById("emailTh").firstChild.nextSibling.nextSibling.textContent="";
							document.getElementById("registEmail").textContent=xhr.responseText;
							document.getElementById("email").type="hidden";
							document.getElementById("username_span").textContent=""
							document.getElementById("email_span").textContent=""
						}else{
							getString=xhr.responseText;
							var chars=getString.split('');
							for (var i=0;i<getString.indexOf('@');i++)
							{
								chars[i]='*';
							}
							var registEmail=chars.join('');
							document.getElementById("emailTh").firstChild.nextSibling.textContent="*";
							document.getElementById("emailTh").firstChild.nextSibling.nextSibling.textContent="Email";
							document.getElementById("registEmail").textContent=registEmail+"请输入该邮箱";
							document.getElementById("email").type="email";
							document.getElementById("username_span").textContent="√"
							document.getElementById("username_span").style.color="green";
							isUValidate=true;
						}
					}
			}
			}
			// 3.打开连接
			xhr.open("GET","${pageContext.request.contextPath}/user_findEmailByUname.action?time="+new Date().getTime()+"&username="+username,true);
			// 4.发送
			xhr.send(null);
		}
		
		
	}
	
	function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
		    alert(e.message);
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			    	alert(e.message);
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){alert(e.message);}
			      }
		    }

			return xmlHttp;
		 }
	
	function checkEmail(node){
		var email=node.value;
		if(!emailRegex.test(email)){
			if(email==null || email==''){
				document.getElementById("email_span").textContent="邮箱不能为空！"
				document.getElementById("email_span").style.color="red";
				isEValidate=false;
			}
			else{
				document.getElementById("email_span").textContent="邮箱格式不正确"
				document.getElementById("email_span").style.color="red";
				isEValidate=false;
			}
		}
		else{
			if(getString==email){
			document.getElementById("email_span").textContent="√"
			document.getElementById("email_span").style.color="green";
			isEValidate=true;
			}
			else{
			document.getElementById("email_span").textContent="请输入正确邮箱！"
			document.getElementById("email_span").style.color="red";
			isEValidate=true;
			}
		}
	}
	window.onload=function(){
	checkUsername(document.getElementById("username"));
	checkEmail(document.getElementById("email"));
	}
</script>
</head>
<body>
<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>找回密码</strong>
						
					</div>
					<form id="changePasswordForm" action="<%=path %>/user_changePwd.action"  method="post" onsubmit="return checkForm();">
						<table>
							<tbody>
							<tr>
								<th>
									<span class="requiredField">*</span>用户名:
								</th>
								<td>
									<input type="text" id="username" name="username" class="text" maxlength="20" onblur="findEmail(this)" ></input>
									<span id="username_span"></span><s:actionerror/>
								</td>
							</tr>
							<tr>
								<th id="emailTh">
									<span id="required_Span" class="requiredField"></span>
								</th>
								<td>
									<span id="registEmail"></span><br/>
									<input type="hidden" id="email" name="email" class="text" maxlength="50"  onblur="checkEmail(this)"></input>
									<span id="email_span"><s:fielderror name="email"/></span>
								</td>
							</tr>
							<tr>
								<th>&nbsp;
								</th>
								<td>
									<input type="submit" class="submit" value="确认"></input>
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
