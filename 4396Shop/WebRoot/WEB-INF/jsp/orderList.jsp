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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>我的订单页面</title>
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/evaluate.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/avgrund.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container cart ">
		<div class="span24">
			<div class="step">
				<ul>
					<li>我的所有订单</li>
				</ul>
			</div>
			<table>
				<tbody>
					<s:iterator value="pageBean.list" var="order">
						<tr>
							<th colspan="7">订单编号:<s:property value="#order.oid" />&nbsp;&nbsp;&nbsp;&nbsp;
								订单状态: <s:if test="#order.state==1">
									<a
										href="${pageContext.request.contextPath}/order_findByOid.action?oid=<s:property value="#order.oid"/>">
										<font color="red">付款</font>
									</a>
								</s:if> <s:if test="#order.state==2">
								已付款
							</s:if> <s:if test="#order.state==3">
								待收货
							</s:if> <s:if test="#order.state==4">
								交易完成
							</s:if>
							</th>
						</tr>
						<tr>
							<th>图片</th>
							<th>商品</th>
							<th>价格</th>
							<th>数量</th>
							<th>小计</th>
							<th>物流</th>
							<th>评价</th>
						</tr>
						<s:iterator value="#order.orderItems" var='orderItem'>
							<tr>
								<td width="60"><input type="hidden" name="id" value="22" />
									<img
									src="${pageContext.request.contextPath}/<s:property value="#orderItem.product.image"/>" />
								</td>
								<td width="360"><a href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#orderItem.product.pid"/>"> <s:property
											value="#orderItem.product.pname" />
								</a></td>
								<td width="180"><s:property
										value="#orderItem.product.shop_price" /></td>
								<td class="quantity" width="120"><s:property
										value="#orderItem.count" /></td>
								<td width="180"><span class="subtotal"><s:property
											value="#orderItem.subtotal" /></span></td>
								<s:if test="#order.state==3	|| #order.state==2">
									<td width="120"><s:if test="#orderItem.state==2">
											<a
												href="${pageContext.request.contextPath}/order_updateState.action?itemid=<s:property value="#orderItem.itemid"/>">
												<font color="red">确认收货</font>
											</a>
										</s:if> <s:else>
											<s:if test="#orderItem.state==0">
												<a>待发货</a>
											</s:if>
											<s:else>
												<a>已收货</a>
											</s:else>
										</s:else></td>
								</s:if>
								<s:else>
									<s:if test="#order.state==4	">
										<td><a>已收货&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
									</s:if>
									<s:else>
										<td></td>
									</s:else>
								</s:else>
								<td><s:if test="#orderItem.state==1">
										<s:if test="#orderItem.evaluate==null">
											<aside id="default-popup" class="avgrund-popup">
												<h2>对商品进行评价</h2>
												<ul class="comment">
													<li id="star1">★</li>
													<li id="star2">★</li>
													<li id="star3">★</li>
													<li id="star4">★</li>
													<li id="star5">★</li>
												</ul>
												<span class="scorenum">10</span>
												<textarea rows="5" cols="40" class="commenttext" placeholder="填写评论..."></textarea>
												<button class="btn_comment">提交评价</button>
												<span style="display:none"></span>
												<button class = "btn_evaluate" onclick="closeDialog()">返回页面</button>
											</aside>
											<button class="btn_evaluate" value="<s:property
														value="#orderItem.itemid" />" onclick="openDialog(this.value)">去评价商品</button>
										</s:if>
										<s:else>
											<ul class="commented">
												<li id="star1"></li>
												<li id="star1"></li>
												<li id="star1"></li>
												<li id="star1"></li>
												<li id="star1"></li>
											</ul>
											<span class="scorednum"><s:property
													value="#orderItem.evaluate" /></span>
											<span class="fold">展开评论</span>
											<span class="yourcom"><s:property
													value="#orderItem.comment" /></span>
										</s:else>
									</s:if></td>
							</tr>
						</s:iterator>
					</s:iterator>
					<tr>
						<td colspan="7">
							<div class="pagination">
								<span>第<s:property value="pageBean.page" />/<s:property
										value="pageBean.totalPage" />页
								</span>
								<s:if test="pageBean.page != 1">
									<a href="<%=path%>/order_findByUid.action?page=1"
										class="firstPage">&nbsp;</a>
									<a
										href="<%=path%>/order_findByUid.action?page=<s:property value="pageBean.page-1"/>"
										class="previousPage">&nbsp;</a>
								</s:if>
								<s:iterator var="i" begin="1" end="pageBean.totalPage">
									<s:if test="pageBean.page != #i">
										<a
											href="<%=path%>/order_findByUid.action?page=<s:property value="#i"/>">
											<s:property value="#i" />
										</a>
									</s:if>
									<s:else>
										<span class="currentPage"><s:property value="#i" /></span>
									</s:else>
								</s:iterator>
								<s:if test="pageBean.page != pageBean.totalPage">
									<a class="nextPage"
										href="<%=path%>/order_findByUid.action?page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
									<a class="lastPage"
										href="<%=path%>/order_findByUid.action?page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
								</s:if>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
	<div class="avgrund-cover"></div>
	<script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script src="${pageContext.request.contextPath}/js/avgrund.js"></script>
	<script>
	$(document).ready(function() {
		var shixin = "★";
		var kongxin = "☆";
		var flag = false; //没有点击*/
		$(".yourcom").hide();
		for (var j = 0; j < $(".scorednum").length; j++) {
			$(".scorednum").eq(j).prev().children().text(kongxin);
			var scored = $(".scorednum").eq(j).text();
			for (var i = 0; i < scored / 2; i++) {
				$(".scorednum").eq(j).prev().children().eq(i).text(shixin);
			}
		}
		$(".comment li").mouseenter(function() {
			if (!flag) {
				$(this).text(shixin).prevAll().text(shixin);
				$(this).nextAll().text(kongxin);
				$(this).text(shixin).prevAll().text(shixin).end().nextAll().text(kongxin);
				switch ($(this).attr("id")) {
					case "star1":
						score = 2;
						break;
					case "star2":
						score = 4;
						break;
					case "star3":
						score = 6;
						break;
					case "star4":
						score = 8;
						break;
					case "star5":
						score = 10;
						break;
				}
				$(this).parent().next().text(score);
			}
		});
		$(".comment").mouseleave(function() {
			if (!flag) {
				$("comment li").text(kongxin);
			}
			$("comment li").text(kongxin);
			$(".clicked").text(shixin).prevAll().text(shixin);
		});
		$(".comment li").on("click", function() {
			$(this).text(shixin).prevAll().text(shixin);
			$(this).nextAll().text(kongxin);
			flag = true;
			var score = $(this).attr("id");
			switch ($(this).attr("id")) {
			case "star1":
				score = 2;
				break;
			case "star2":
				score = 4;
				break;
			case "star3":
				score = 6;
				break;
			case "star4":
				score = 8;
				break;
			case "star5":
				score = 10;
				break;
			}
			$(this).parent().next().text(score);
		});
		$(".btn_comment").click(function() {
			
			$.post("${pageContext.request.contextPath}/order_submitscore.action",
				{
					evaluate : ($(this).prev().prev().text()),
					comment : ($(this).prev().val()),
					itemid : ($(this).next().text())
				},
				function() {
					window.location.reload();
				});
		});
		$(".fold").click(function() {
			if($(this).html()=="展开评论"){		
				$(this).html("收起评论");
			}else if($(this).html()=="收起评论"){
				$(this).html("展开评论");
			}	
			$(this).next().slideToggle(300)		
		});
	});
	function openDialog(e) {
			Avgrund.show( "#default-popup" );
			$("#default-popup span").eq(1).text(e);
	}
	function closeDialog() {
			Avgrund.hide();
	}
	</script>
</body>
</html>