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

<link href="<%=path%>/css/slider.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css"/>


<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script src="${pageContext.request.contextPath}/js/Myapi.js"></script>

<jsp:include page="menu.jsp"/>
	
</head>
<body>

<div class="container index" >
		<div class="J_banner J_banner1" >
        <ul class="img">
            <li>
                <img src="<%=path%>/image/20180708212234_278212.jpg" >
            </li>
            <li>
                <img  src="<%=path%>/image/20180708212234_271188.jpg" >
            </li>
            <li>
                <img src="<%=path%>/image/20180710093533_307265.jpg" >
            </li>
        </ul>
        <ul class="pointer"></ul><!-- 点 -->
   		</div>	
   		
    <script>
    $(function(){
        var myApi1 = new Myapi();
        myApi1.JSON.lagout($('.J_banner1'),3000,0);
    })
</script>
	<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix">
					<div class="title">
						<strong>热门商品</strong>
						<!-- <a  target="_blank"></a> -->
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=1" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>

						<ul class="tabContent" style="display: block;">
									<s:iterator var="p" value="hList">
									
									<li>
										<a href="<%=path %>/product_findByPid.action?pid=<s:property value="#p.pid"/>" target="_blank"><img src="<%= path %>/<s:property value="#p.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg" style="display: block;"/></a>
									</li>
									</s:iterator>
						</ul>
						
			</div>
		</div>
		<div class="span24">
			<div id="newProduct" class="newProduct clearfix">
					<div class="title">
						<strong>最新商品</strong>
						<a  target="_blank"></a>
					</div>
					<ul class="tab">
							<li class="current">
								<a href="./蔬菜分类.htm?tagIds=2" target="_blank"></a>
							</li>
							<li>
								<a  target="_blank"></a>
							</li>
							<li>
								<a target="_blank"></a>
							</li>
					</ul>						
						 <ul class="tabContent" style="display: block;">
									<s:iterator var="n" value="nList">
									<li>
										<a href="<%=path %>/product_findByPid.action?pid=<s:property value="#n.pid"/>" target="_blank"><img src="<%=path%>/<s:property value="#n.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/4a51167a-89d5-4710-aca2-7c76edc355b8-thumbnail.jpg" style="display: block;"/></a>									
									</li>
									</s:iterator>
						</ul>
						
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="<%=path%>/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
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