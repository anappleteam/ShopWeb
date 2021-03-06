<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物车</title>
<jsp:include page="menu.jsp"/>
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container cart">
		<s:if test="#session.cart.cartItems.size() != 0">
			<!-- 不判断map是否为空，map一旦new出来后就有地址 -->
			<div class="span24">
				<br /> <br />
				<table>
					<tbody>
						<tr>
							<th colspan="8" style="text-align:center;vertical-align:middle;">
								<strong>购物车信息</strong>
							</th>
						</tr>
						<tr>
							<th><input type="checkbox" id="checkAll"
								onclick="selectAll()" />全选</th>
							<th>图片</th>
							<th>商品</th>
							<th>库存</th>
							<th>单价</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
						<s:iterator var="cartItem" value="#session.cart.cartItems">
							<tr>
								<td width="60"><input type="checkbox" id="citemid"
									name="selectProduct"
									value="<s:property value="#cartItem.citemid"/>"
									onclick="selectOne(this)" /> <input
									id="<s:property value="#cartItem.citemid"/>_pid" type="hidden"
									value="<s:property
											value="#cartItem.product.pid"/>" />
								</td>
								<td width="60"><a
									href="<%=path%>/product_findByPid.action?pid=<s:property value="#cartItem.product.pid"/>"
									target="_blank"> <img
										src="${pageContext.request.contextPath}/<s:property value="#cartItem.product.image" />" /></a></td>
								<td><a
									href="<%=path%>/product_findByPid.action?pid=<s:property value="#cartItem.product.pid"/>"
									target="_blank"> <s:property
											value="#cartItem.product.pname" /></a></td>
								<td><span
									id="<s:property value="#cartItem.citemid"/>_inventory"><s:property
											value="#cartItem.product.pavailable" /></span></td>
								<td>￥<s:property value="#cartItem.product.shop_price" />
								</td>
								<td class="quantity" width="140"><input
									id="<s:property value="#cartItem.citemid"/>_itemCount"
									value="<s:property
											value="#cartItem.count"  />"
									maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')"
									type="text" /> <input
									id="<s:property value="#cartItem.citemid"/>_oldCount"
									type="hidden"
									value="<s:property
											value="#cartItem.count"/>" />
									<button class="myButton" style="font-size:12px;"
										name="changeCountButton" onclick="changeCount(this)"
										value="<s:property value="#cartItem.citemid"/>">确认修改</button></td>
								<td width="100">￥<span
									id="span_<s:property value="#cartItem.citemid"/>"
									class="subtotal"><s:property value="#cartItem.subtotal" /></span></td>
								<td width="100"><a
									href="${pageContext.request.contextPath}/cart_removeCart.action?citemid=<s:property value="#cartItem.citemid"/>"
									class="delete">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
				<span style="float:left;"><font color="red"><s:actionmessage /></font></span>
					已选金额: ￥<strong id="effectivePrice">0.0</strong>
				</div>
				<div class="bottom">
					<a
						href="${pageContext.request.contextPath}/cart_clearCart.action?uid=<s:property value="#session.existUser.uid"/>"
						id="clear" class="clear">清空购物车</a> <a href="#" id="submit"
						class="submit" onclick="submitSelected()">提交订单</a>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="span24">
				<div class="step step1" align="center">
					<span>╮(╯_╰)╭<a
						href="${pageContext.request.contextPath }/index.action"
						id="empty_cart">购物车里什么都没有~去逛逛吧</a></span>
				</div>
			</div>
		</s:else>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<script>
		var flagAll = false;
		var total = 0.0;
		function selectOne(node) {
			var citemid = node.value;
			var subtotal = document.getElementById("span_" + citemid).innerText;
			var inventory = parseInt(document.getElementById(citemid + "_inventory").innerText);
			var count = parseInt(document.getElementById(citemid + "_oldCount").value);
			if (node.checked) {
				if (count > inventory) {
					node.checked = false;
					alert("库存不足，请修改数量！");
					return;
				} else {
					total += Number(subtotal);
				}
			} else {
				total -= Number(subtotal);
			}
			document.getElementById("effectivePrice").innerText = parseFloat(total).toFixed(1);
		}
	
		function selectAll() {
			var names = document.getElementsByName("selectProduct");
			var inventory = 0;
			var count = 0;
			if (!flagAll) {
				for (var i = 0; i < names.length; i++) {
					inventory = parseInt(document.getElementById(names[i].value + "_inventory").innerText);
					count = parseInt(document.getElementById(names[i].value + "_oldCount").value);
					if (count > inventory) {
						document.getElementById("checkAll").checked = false;
						alert("请检查商品数量是否超出库存！");
						return;
					}
				}
				for (var i = 0; i < names.length; i++) {
					if (names[i].checked) {
						continue;
					} else {
						names[i].checked = true;
						selectOne(names[i]);
					}
				}
				flagAll = true;
			} else {
				for (var i = 0; i < names.length; i++) {
					names[i].checked = false;
					selectOne(names[i]);
				}
				flagAll = false;
			}
		}
	
		function unSelectAll() {
			var names = document.getElementsByName("selectProduct");
			for (var i = 0; i < names.length; i++) {
				if (names[i].checked) {
					names[i].checked = false;
				} else {
					continue;
				}
			}
			document.getElementById("effectivePrice").innerText = parseFloat(0).toFixed(1);
			total=0;
		}
	
		function submitSelected() {
			var names = document.getElementsByName("selectProduct");
			var pids = new Array();
			var j = 0;
			for (var i = 0; i < names.length; i++) {
				if (names[i].checked) {
					var pid = document.getElementById(names[i].value + "_pid").value;
					pids.push(pid);
					j++;
				}
			}
			if (pids.length > 0) {
				var pidsstring = pids.join(",");
				window.location.href = "${pageContext.request.contextPath}/cart_save.action?pidsstring=" + pidsstring;
				return false;
			} else {
				return false;
			}
		}
	
		function changeCount(node) {
			unSelectAll();
			var citemid = node.value;
			var subtotal = document.getElementById("span_" + citemid).innerText;
			var inventory = parseInt(document.getElementById(citemid + "_inventory").innerText);
			var oldCount = parseInt(document.getElementById(citemid + "_oldCount").value);
			var newCount = parseInt(document.getElementById(citemid + "_itemCount").value);
			if (newCount <= 0) {
				alert("请输入大于0的有效值！");
				document.getElementById(citemid + "_itemCount").value = oldCount;
				return;
			} else if (newCount > inventory) {
				alert("库存不足，请重新输入数量！");
				document.getElementById(citemid + "_itemCount").value = oldCount;
				return;
			} else if (newCount == oldCount) {
				total-= Number(subtotal);
				return;
			}
			// 1.创建异步交互对象
			var xhr = createXmlHttp();
			// 2.设置监听
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						if (xhr.responseText != '') {
							var returns = xhr.responseText.split(",")
							if (returns[0] == "true") {
								document.getElementById("span_" + citemid).innerText = returns[1];
								document.getElementById(citemid + "_oldCount").value = newCount;
							} else if (returns[0] == "false") {
								alert("当前库存剩余" + returns[1]);
								document.getElementById(citemid + "_itemCount").value = oldCount;
							}
						}
					}
				}
			}
			// 3.打开连接
			xhr.open("GET", "${pageContext.request.contextPath}/cart_updateCart.action?time=" + new Date().getTime() + "&citemid=" + citemid + "&newCount=" + newCount, true);
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
</body>
</html>
