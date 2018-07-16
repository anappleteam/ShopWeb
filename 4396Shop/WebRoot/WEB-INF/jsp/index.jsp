<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>4396购物</title>
<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
<jsp:include page="menu.jsp" />
</head>
<body>

	<div class="container index">
		<div class="J_banner J_banner1">
			<ul class="img">
				<li><img src="<%=path%>/image/20180708212234_278212.jpg" /></li>
				<li><img src="<%=path%>/image/20180708212234_271188.jpg" /></li>
				<li><img src="<%=path%>/image/20180710093533_307265.jpg" /></li>
			</ul>
			<ul class="pointer"></ul>
			<!-- 点 -->
		</div>

		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
				<div class="title">
					<strong>热门商品</strong>
				</div>
				<ul class="tab">
					<li class="current"><a href="./蔬菜分类.htm?tagIds=1"
						target="_blank"></a></li>
					<li><a target="_blank"></a></li>
					<li><a target="_blank"></a></li>
				</ul>

				<ul class="tabContent" style="display: block;">
					<s:iterator var="p" value="hList">

						<li><a
							href="<%=path %>/product_findByPid.action?pid=<s:property value="#p.pid"/>"
							target="_blank"><img
								src="<%= path %>/<s:property value="#p.image"/>"
								style="display: block;" /></a></li>
					</s:iterator>
				</ul>

			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
				<div class="title">
					<strong>最新商品</strong> <a target="_blank"></a>
				</div>
				<ul class="tab">
					<li class="current"><a href="./蔬菜分类.htm?tagIds=2"
						target="_blank"></a></li>
					<li><a target="_blank"></a></li>
					<li><a target="_blank"></a></li>
				</ul>
				<ul class="tabContent" style="display: block;">
					<s:iterator var="n" value="nList">
						<li><a
							href="<%=path %>/product_findByPid.action?pid=<s:property value="#n.pid"/>"
							target="_blank"><img
								src="<%=path%>/<s:property value="#n.image"/>"
								style="display: block;" /></a></li>
					</s:iterator>
				</ul>

			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Myapi.js"></script>
<script>
    $(function(){
        var myApi1 = new Myapi();
        myApi1.JSON.lagout($('.J_banner1'),3000,0);
    })
</script>
</body>
</html>