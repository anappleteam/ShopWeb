<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>4396商城</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Baxster Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- //font-awesome icons -->
<!--webfonts-->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css' />
<!--//webfonts-->
<!-- js -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<style type="text/css">
body {
	color: white;
}
</style>
</head>
<body class="login-bg">
	<div class="login-body">
		<div class="login-heading">
			<h1>登录</h1>
		</div>
		<div class="login-info">
			<center>
				<s:actionerror />
			</center>
			<form method="post"
				action="${pageContext.request.contextPath }/adminUser_login.action"
				target="_parent" name='theForm' onsubmit="return validate()">
				<input type="text" class="user" name="username" placeholder="Username"/>
				<input type="password" name="password" class="lock" placeholder="Password"/>
				<input type="submit" value="进入管理中心" class="button" />
			</form>
		</div>
	</div>
	<div class="go-back login-go-back">
		<a href="${pageContext.request.contextPath}/index.action">返回首页</a>
	</div>
	<div class="copyright login-copyright">
		<p>
			4396商城管理中心
		</p>
	</div>
	<script language="JavaScript">
<!--
  document.forms['theForm'].elements['username'].focus();
  
  /**
   * 检查表单输入的内容
   */
  function validate()
  {
    var validator = new Validator('theForm');
    validator.required('username', user_name_empty);
    //validator.required('password', password_empty);
    if (document.forms['theForm'].elements['captcha'])
    {
      validator.required('captcha', captcha_empty);
    }
    return validator.passed();
  }
  
//-->
</script>
</body>
</html>