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
<title>4396购物</title>

<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/settle.css" rel="stylesheet" type="text/css"/>

<jsp:include page="menu.jsp"/>
	
</head>
<body>
<div class="container settle">
	<div class="span24">
		<div class="main">
		<div class="title">
			<strong>入驻信息填写</strong>
		</div>
		<form id="settleform" action="<%=path %>/user_merchantRequest.action" method="post" >
			<table>
			<tr>
				<th>店铺名称<span class="requiredField">*</span></th>
				<td><input type="text" id="storename" name="storename" class="text" maxlength="20"></td>
			</tr>
			<tr>
				<th>店铺介绍<span class="requiredField">*</span></th>
				<td><textarea type="text" id="storeinfo" name="storeinfo" rows="5" class="txt"></textarea></td>
			</tr>
			</table>
			<button type="submit" class="btn_settle" >我要入驻</button>
		</form>
		</div>
	</div>
</div>
<div class="container footer" style="margin-top:50px">
	<div class="span24">
		<div class="footerAd">
					<img src="<%=path%>/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a>关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a>招贤纳士</a>
						|
					</li>
					<li>
						<a>法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a>服务声明</a>
						|
					</li>
					<li>
						<a>广告声明</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © An Apple Team 网上商城 版权所有</div>
		
	</div>
</div>
</body></html>