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
<title>4396购物</title>
<link href="<%=path%>/css/product.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container productList">
		<div class="span6">
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
		<div class="span18 last">

			<form id="productForm"
				action="<%=path%>/image/蔬菜 - Powered By Mango Team.htm" method="get">

				<div id="result" class="result table clearfix">
					<ul>
						<s:iterator var="p" value="pageBean.list">
							<li><a
									href="<%=path%>/product_findByPid.action?pid=<s:property value="#p.pid"/>">
									<img src="<%=path%>/<s:property value="#p.image"/>" width="170"
										height="170" style="display: inline-block;"/> <span
										style='color:green'> <s:property value="#p.pname" />
									</span> <span class="price"> 商城价： ￥<s:property
											value="#p.shop_price" />
									</span>

								</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="pagination">
					<span>第<s:property value="pageBean.page" />/<s:property
							value="pageBean.totalPage" />页
					</span>
					<s:if test="cid != null">
						<s:if test="pageBean.page != 1">
							<a
								href="<%=path%>/product_findByCid.action?cid=<s:property value="cid"/>&page=1"
								class="firstPage">&nbsp;</a>
							<a
								href="<%=path%>/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page-1"/>"
								class="previousPage">&nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a
									href="<%=path%>/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="#i"/>">
									<s:property value="#i" />
								</a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /></span>
							</s:else>
						</s:iterator>
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage"
								href="<%=path%>/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
							<a class="lastPage"
								href="<%=path%>/product_findByCid.action?cid=<s:property value="cid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
					<s:if test="csid != null">
						<s:if test="pageBean.page != 1">
							<a
								href="<%=path%>/product_findByCsid.action?csid=<s:property value="csid"/>&page=1"
								class="firstPage">&nbsp;</a>
							<a
								href="<%=path%>/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page-1"/>"
								class="previousPage">&nbsp;</a>
						</s:if>
						<s:iterator var="i" begin="1" end="pageBean.totalPage">
							<s:if test="pageBean.page != #i">
								<a
									href="<%=path%>/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>">
									<s:property value="#i" />
								</a>
							</s:if>
							<s:else>
								<span class="currentPage"><s:property value="#i" /></span>
							</s:else>
						</s:iterator>
						<s:if test="pageBean.page != pageBean.totalPage">
							<a class="nextPage"
								href="<%=path%>/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
							<a class="lastPage"
								href="<%=path%>/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
						</s:if>
					</s:if>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/footer.jsp" />
</body>
</html>