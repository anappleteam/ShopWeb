<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="header" style="background-color: #000000;position:fixed;z-index:9999;width: 1350px;margin-top:-115px">
	<div class="span6">
		<div class="logo">
				<img  src="${pageContext.request.contextPath}/image/headlogo.png" />
		</div>
	</div>
	<div class="span18 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.existUser == null">
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a
						href="${ pageContext.request.contextPath }/user_loginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister"
						style="display: list-item;"><a
						href="${ pageContext.request.contextPath }/user_registPage.action">注册</a>|
					</li>
				</s:if>
				<s:else>
					<li id="headerUsername" class="headerUsername"
						style="display: list-item;"><s:property
							value="#session.existUser.username" /></li>

					<li id="headerLogout" class="headerLogout"
						style="display: list-item;"><a
						href="${pageContext.request.contextPath}/user_quit.action">退出</a>|
					</li>
					<li id="headerLogin" class="headerLogin"
						style="display: list-item;"><a href="${pageContext.request.contextPath}/order_findByUid.action?page=1">我的订单</a>|</li>
				</s:else>
				<li><a href="${ pageContext.request.contextPath }/cart_myCart.action">购物车</a> |</li>
				<li><a>会员中心</a> |</li>
				<li><a>购物指南</a> |</li>
				<li><a>关于我们</a></li>
			</ul>
		</div>
	</div>

	<div class="span24">
		<ul class="mainNav">
			<li><a href="${pageContext.request.contextPath }/index.action">首页</a>
				|</li>
			<s:iterator var="c" value="#session.cList">

				<li><a href="${pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#c.cid" />&page=1"><s:property value="#c.cname" /></a> |
				</li>
			</s:iterator>

		</ul>
	</div>
</div>