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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>购物车</title>

<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css"/>
</head>
<body>
	<script>
		var flagAll = false;
		var total = 0.0;
		function selectOne(node) {
			var cartId = node.value;
			var subtotal = document.getElementById("span_" + cartId).innerText;
			if (node.checked) {
				total += Number(subtotal);
			} else {
				total -= Number(subtotal);
			}
			document.getElementById("effectivePrice").innerText = parseFloat(total).toFixed(1);
		}
	
		function selectAll() {
			var names = document.getElementsByName("selectCartItem");
			if (!flagAll) {
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
	
		function submitSelected() {
			var names = document.getElementsByName("selectCartItem");
			var cids = new Array();
			var j = 0;
			for (var i = 0; i < names.length; i++) {
				if (names[i].checked) {
					cids.push(names[i].value);
					j++;
				}
			}
			if (cids.length > 0) {
				var cidsstring = cids.join(",");
				window.location.href = "${pageContext.request.contextPath}/order_save.action?cidsstring=" + cidsstring + "&total?=" + (parseFloat(total));
				return false;
			} else {
				return false;
			}
		}
	</script>

	<%@ include file="menu.jsp"%>
	<div class="container cart">
		<s:if test="pageBean.list !=null">
			<!-- 不判断map是否为空，map一旦new出来后就有地址 -->
			<div class="span24">
				<div class="step step1">购物车信息</div>
				<table>
					<tbody>
						<tr>
							<th><input type="checkbox" id="checkAll"
								onclick="selectAll()" />全选</th>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>操作</th>
						</tr>
						<s:iterator var="cartItem" value="pageBean.list">
							<tr>
								<td><input type="checkbox" name="selectCartItem"
									value="<s:property value="#cartItem.citemid"/>"
									onclick="selectOne(this)" /></td>
								<td width="60"><a
									href="<%=path%>/product_findByPid.action?pid=<s:property value="#cartItem.product.pid"/>"
									target="_blank"> <img
										src="${pageContext.request.contextPath}/<s:property value="#cartItem.product.image" />"/></a></td>
								<td><a
									href="<%=path%>/product_findByPid.action?pid=<s:property value="#cartItem.product.pid"/>"
									target="_blank"> <s:property
											value="#cartItem.product.pname" /></a></td>
								<td>￥<s:property value="#cartItem.product.shop_price" />
								</td>
								<td class="quantity" width="60"><s:property
										value="#cartItem.count" /></td>
								<td width="140">￥<span
									id="span_<s:property value="#cartItem.citemid"/>"
									class="subtotal"><s:property value="#cartItem.subtotal" /></span></td>
								<td><a
									href="${pageContext.request.contextPath}/cart_removeCart.action?citemid=<s:property value="#cartItem.citemid"/>"
									class="delete">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<dl id="giftItems" class="hidden" style="display: none;">
				</dl>
				<div class="total">
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
</body>
</html>
