<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/productMng_add.action";
			}
		</script>
		<jsp:include page="/WEB-INF/jsp/header.jsp"></jsp:include>
	</head>
	<body>
		<div class="container cart" style="margin-top: auto;padding-top: 120px;">
		<br/>
		<form id="Form1" name="Form1">
			<table>
				<tbody>
					<tr>
						<th colspan="9" style="text-align:center;vertical-align:middle;">
							<strong>商品列表</strong>
							<a href="#" class="clear" id="add" style="float: right;" onclick="addProduct()">添加</a>
						</th>
					</tr>
					<tr>
									<th style="text-align:center;vertical-align:middle;">
										序号
									</th>
									<th style="text-align:center;vertical-align:middle;">
										编号
									</th>
									<th style="text-align:center;vertical-align:middle;">
										商品图片
									</th>
									<th style="text-align:center;vertical-align:middle;">
										商品名称
									</th>
									<th style="text-align:center;vertical-align:middle;">
										商品价格
									</th>
									<th style="text-align:center;vertical-align:middle;">
										库存
									</th>
									<th style="text-align:center;vertical-align:middle;">
										是否热门
									</th>
									<th style="text-align:center;vertical-align:middle;">
										编辑
									</th>
									<th style="text-align:center;vertical-align:middle;">
										删除
									</th>
								</tr>
									<s:iterator var="p" value="pageBean.list" status="status">
										<tr onmouseout="this.style.backgroundColor = 'white'"
											onmouseover="this.style.backgroundColor = '#F5FAFE'"
											>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#status.count"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#p.pid"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<img alt="商品图标"width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="#p.image"/>">
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#p.pname"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#p.shop_price"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#p.pavailable"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:if test="#p.is_hot==1">
													是
												</s:if>
												<s:else>
													否
												</s:else>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<a href="${ pageContext.request.contextPath }/productMng_edit.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/images/edit.png" style="width:25px;height:25px;"/>
												</a>
											</td>
									
											<td style="text-align:center;vertical-align:middle;">
												<a href="${ pageContext.request.contextPath }/productMng_delete.action?pid=<s:property value="#p.pid"/>">
													<img src="${pageContext.request.contextPath}/images/delete.png" style="width:30px;height:30px;"/>
												</a>
											</td>
										</tr>
									</s:iterator>	
					<tr style="text-align:center;vertical-align:middle;">
						<td colspan="9">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页 
							<s:if test="pageBean.page != 1">
								<a href="${ pageContext.request.contextPath }/productMng_findByCurStore.action?page=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/productMng_findByCurStore.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${ pageContext.request.contextPath }/productMng_findByCurStore.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${ pageContext.request.contextPath }/productMng_findByCurStore.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		</div>
	</body>
</html>

