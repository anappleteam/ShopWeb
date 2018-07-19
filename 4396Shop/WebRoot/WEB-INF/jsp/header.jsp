<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="header"
	style="background-color: #000000;position:fixed;z-index:9999;width:100%">
	<div class="span24 last">
		<div class="logo">
			<a href="${pageContext.request.contextPath }/index">
				<img style="margin-top: 18px"
					src="${pageContext.request.contextPath}/image/headlogo.png" />
			</a>
		</div>
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
						style="display: list-item;"><a
							href="${pageContext.request.contextPath}/order_findByUid.action?page=1">我的订单</a>|</li>
					<s:if
						test="#session.existUser.state==1 ||#session.existUser.state==2 ">
						<li id="headerLogin" class="headerLogin"
							style="display: list-item;"><a
								href="${pageContext.request.contextPath}/user_merchantsettle.action">商家入驻</a>|</li>
					</s:if>
					<s:elseif test="#session.existUser.state==3">
						<li id="headerLogin" class="headerLogin"
							style="display: list-item;">
							<div class="dropdown">
								<span>我的店铺</span>
								<div class="dropdown-content">
									<a
										href="${pageContext.request.contextPath}/productMng_findByCurStore.action?page=1">商品管理</a>
									<br>
									<a
										href="${pageContext.request.contextPath}/store_orderMng?page=1">订单管理</a>
								</div>
							</div> |
						</li>
					</s:elseif>
				</s:else>
				<li><a
						href="${ pageContext.request.contextPath }/cart_myCart.action">购物车</a>
					|</li>
				<li><a>关于我们</a></li>
			</ul>
		</div>
	</div>
</div>
<script>
	window.onload = function() {
		// 1.创建异步交互对象
		var xhr = createXmlHttp();
		// 2.设置监听

		// 3.打开连接
		xhr.open("GET", "${pageContext.request.contextPath}/user_fresh.action?time=" + new Date().getTime(), true);
		// 4.发送

		xhr.send(null);


	}
	function createXmlHttp() {
		var xmlHttp;
		try { // Firefox, Opera 8.0+, Safari
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			alert(e.message);
			try { // Internet Explorer
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				alert(e.message);
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert(e.message);
				}
			}
		}

		return xmlHttp;
	}
</script>