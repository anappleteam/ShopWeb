<%@page import="org.apache.struts2.components.Include"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=7200" />
<title>网上商城</title>
<link href="<%=path%>/css/product.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/evaluate.css"
	rel="stylesheet" type="text/css" />
</head>
<body>

	<jsp:include page="menu.jsp" />
	<div class="container productContent">
		<div class="span6">
			<div class="storeTitle">
				<p class="title">
					<s:property value="model.store.sname" />
				</p>
				<a class="link"
					href="<%=path%>/store_findBySid.action?sid=<s:property value="model.store.sid"/>&page=1">进店逛逛</a>
			</div>
			<div class="hotProductCategory">
				<s:iterator var="c" value="#session.cList">
					<dl>
						<dt>
							<a
								href="<%=path%>/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1">
								<s:property value="#c.cname" />
							</a>
						</dt>
						<s:iterator var="cs" value="#c.categorySeconds">
							<dd>
								<a
									href="<%=path%>/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1">
									<s:property value="#cs.csname" />
								</a>
							</dd>
						</s:iterator>
					</dl>
				</s:iterator>
			</div>


		</div>

		<div class="span16 last">

			<div class="productImage">
				<a style="outline-style: none; text-decoration: none;" id="zoom"
					href="<%=basePath%><s:property value="model.image"/>" rel="gallery">
					<img style="opacity: 1;" class="medium"
					src="<%=path%>/<s:property value="model.image"/>" />
				</a>
			</div>
			<div class="name">
				<s:property value="model.pname" />
			</div>
			<div class="sn">
				<div>
					编号：
					<s:property value="model.pid" />
				</div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong><s:property value="model.shop_price" />元</strong> 参 考 价：
						<del>
							<s:property value="model.market_price" />
							元
						</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			<form id="cartForm"
				action="${pageContext.request.contextPath }/cart_addCart.action"
				method="post">
				<input type="hidden" name="pid"
					value="<s:property value="model.pid" />" />
				<div class="action">

					<dl class="quantity">
						<dt>购买数量:</dt>
						<dd>
							<span id="decrease" class="decrease">
								<button class="myButton" style="padding: 2px 8px" type="button"
									onclick="decrease()">-</button>
							</span> <input id="count" name="count" value="1" maxlength="2"
								onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkNull()"
								type="text" /> <span id="increase" class="increase">
								<button class="myButton" style="padding: 2px 6px" type="button"
									onclick="increase()">+</button>
							</span>
						</dd>
						<dd>件</dd>
					</dl>
					<dl class="quantity">
						<dt>库存：</dt>
						<dd>
							<span id="p_inventory"><s:property
									value="model.pavailable" /></span>
						</dd>
					</dl>
					<div class="buy">
						<input id="addCart_currentUser" type="hidden"
							value="${session.existUser}" /> <input id="addCart"
							class="addCart" value="加入购物车" type="button" onclick="saveCart()" />
					</div>
				</div>
			</form>
			<div id="bar" class="bar">
				<ul class="qtabs">
					<li id="introductionTab" class="active"><a href="#introduction">商品介绍</a></li>
					<li id="commentTab"><a href="#comment">评论</a></li>
				</ul>
			</div>
				<div class="tab_containers">			
				<div id="introduction" class="introduction" style="display:block;">
					<div class="title">
						<strong><s:property value="model.pdesc" /></strong>
					</div>
					<div>
						<img src="<%=path%>/<s:property value="model.image"/>" />
					</div>
				</div>
				<div id="comment" class="introduction" style="display:none;">
				<s:if test="conments==null">
					<div class="title">
					<strong>此商品暂无评论</strong>
					</div>
				</s:if>
				<s:else>
					<s:iterator var="con" value="conments">
					<div class="title">
						<strong><s:property value="#con.username"/>评分：</strong>					
						<div class="commentedlist">
							<div id="star1" class="commentedlist"></div>
							<div id="star1" class="commentedlist"></div>
							<div id="star1" class="commentedlist"></div>
							<div id="star1" class="commentedlist"></div>
							<div id="star1" class="commentedlist"></div>
						</div>
						<span class="scorednumlist"><s:property
							value="#con.evaluate" /></span>
					</div>
					<div style="padding:10px;">
						<strong>内容：</strong><br/><br/>
						<s:property value="#con.content"/>
					</div>
					</s:iterator>
				</s:else>				
				</div>
			    </div>			
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<script>
		function decrease() {
			var c = document.getElementById("count").value;
			if (c > 1) {
				document.getElementById("count").value--;
			}
		}
		function increase() {
			var c = document.getElementById("count").value;
			if (c > 0) {
				document.getElementById("count").value++;
			}
		}
		function checkNull() {
			var c = document.getElementById("count").value;
			if (c == '') {
				document.getElementById("count").value = 1;
			}
		}
		function saveCart() {
			var user = document.getElementById("addCart_currentUser").value;
			var inventory = document.getElementById("p_inventory").innerText;
			if (user
				== "") {
				alert("请先登录！");
			} else {
				if
				(document.getElementById("count").value > inventory) {
					alert("库存不足，请重新输入数量！");
					document.getElementById("count").value = 1;
				} else {
					document.getElementById("cartForm").submit();
				}
			}
		}
	</script>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script>
		$(document).ready(function() {
			$(".introduction").hide();
			$("ul.tabs li:first").addClass("active").show();
			$(".introduction:first").show();
			
			$("ul.qtabs li").click(function() {
				$("ul.qtabs li").removeClass("active");
				$(this).addClass("active");
				$(".introduction").hide();
				var activeTab =$(this).find("a").attr("href");
				$(activeTab).fadeIn();
				return false;
			});
		});
	</script>
<script>
	$(document).ready(function() {
		var shixin = "★";
		var kongxin = "☆";
		var flag = false; //没有点击*/
		for (var j = 0; j < $(".scorednumlist").length; j++) {
			$(".scorednumlist").eq(j).prev().children().text(kongxin);
			var scored = $(".scorednumlist").eq(j).text();
			for (var i = 0; i < scored / 2; i++) {
				$(".scorednumlist").eq(j).prev().children().eq(i).text(shixin);
			}
		}
		});
</script>
</html>

