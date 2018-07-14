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
<title>4396购物</title>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=path%>/css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container productList">
		<div class="span6">
			<div class="storeTitle">
				<img class="size" src="<%=path%>/image/shop-icon.png" />
				<p class="title">
					<s:property value="storeInfo.sname" />
				</p>
				<p class="desc">
					<s:property value="storeInfo.sdesc" />
				</p>

			</div>
		</div>
		<div class="span18 last">

			<form id="productForm">

				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="p" value="pageBean.list">
							<li><a
								href="<%=path%>/product_findByPid.action?pid=<s:property value="#p.pid"/>">
									<img src="<%=path%>/<s:property value="#p.image"/>" width="170"
									height="170" style="display: inline-block;"/> <span
										style='color:green' >
								<s:property value="#p.pname" /> </span> <span class="price">
										商城价： ￥<s:property value="#p.shop_price" />
								</span>
							</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagination">
					<span>第<s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" />页
					</span>
					<s:if test="pageBean.page != 1">
						<a
							href="<%=path%>/store_findBySid.action?sid=<s:property value="sid"/>&page=1"
							class="firstPage">&nbsp;</a>
						<a
							href="<%=path%>/store_findBySid.action?sid=<s:property value="sid"/>&page=<s:property value="pageBean.page-1"/>"
							class="previousPage">&nbsp;</a>
					</s:if>
					<s:iterator var="i" begin="1" end="pageBean.totalPage">
						<s:if test="pageBean.page != #i">
							<a
								href="<%=path%>/store_findBySid.action?sid=<s:property value="sid"/>&page=<s:property value="#i"/>">
								<s:property value="#i" />
							</a>
						</s:if>
						<s:else>
							<span class="currentPage"><s:property value="#i" /></span>
						</s:else>
					</s:iterator>
					<s:if test="pageBean.page != pageBean.totalPage">
						<a class="nextPage"
							href="<%=path%>/store_findBySid.action?sid=<s:property value="sid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
						<a class="lastPage"
							href="<%=path%>/store_findBySid.action?sid=<s:property value="sid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
					</s:if>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>