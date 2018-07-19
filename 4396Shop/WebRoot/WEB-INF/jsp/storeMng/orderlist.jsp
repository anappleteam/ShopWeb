<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/header.jsp"/>
		<link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css" />	
	</head>
	<body>
		<div class="container cart" style="margin-top: auto;padding-top: 120px;">
		<br/>
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table style="text-align:center;vertical-align:middle;">
				<tbody>
					<tr>
						<th style="text-align:center;vertical-align:middle;">
							<strong>订单列表</strong>
						</th>
					</tr>
					<tr>
						<td style="text-align:center;vertical-align:middle;">
							<table style="text-align:center;vertical-align:middle;">
								<tr>
									<th style="text-align:center;vertical-align:middle;">
										序号
									</th>
									<th style="text-align:center;vertical-align:middle;">
										订单编号
									</th>
									<th style="text-align:center;vertical-align:middle;">
										商品编号
									</th>
									<th style="text-align:center;vertical-align:middle;">
										数量
									</th>
									<th style="text-align:center;vertical-align:middle;">
										总金额
									</th>
									<th style="text-align:center;vertical-align:middle;">
										收货人
									</th>
									<th style="text-align:center;vertical-align:middle;">
										订单状态
									</th>
									<th style="text-align:center;vertical-align:middle;">
										订单详情
									</th>
								</tr>
									<s:iterator var="orderItem" value="pageBean.list" status="status">
										<tr onmouseout="this.style.backgroundColor = 'white'"
											onmouseover="this.style.backgroundColor = '#F5FAFE';">
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#status.count"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#orderItem.order.oid"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#orderItem.product.pid"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#orderItem.count"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#orderItem.subtotal"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:property value="#orderItem.order.name"/>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<s:if test="#orderItem.state==1">
													已收货
												</s:if>
												<s:if test="#orderItem.state==0">
													<a href="${ pageContext.request.contextPath }/store_updateState.action?itemid=<s:property value="#orderItem.itemid"/>" style="color: blue;">发货</a>
												</s:if>
												<s:if test="#orderItem.state==2">
													已发货
												</s:if>
											</td>
											<td style="text-align:center;vertical-align:middle;">
												<input type="button" class="myButton" value="订单详情" id="but<s:property value="#orderItem.itemid"/>" onclick="showDetail(<s:property value="#orderItem.itemid"/>)"/>
												
											</td>
										</tr>
										<tr>
											<td colspan="8">
												<div id="div<s:property value="#orderItem.itemid"/>">
												</div>
											</td>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					<tr style="text-align:center;vertical-align:middle;">
						<td colspan="8">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页 
							<s:if test="pageBean.page != 1">
								<a href="${ pageContext.request.contextPath }/store_orderMng.action?page=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/store_orderMng.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${ pageContext.request.contextPath }/store_orderMng.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${ pageContext.request.contextPath }/store_orderMng.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>|
							</s:if>
						</td>
					</tr>
				</TBODY>
			</table>
		</form>
		</div>
		<script src="${pageContext.request.contextPath}/js/public.js"></script>
		<script>
			function showDetail(oid){
				var but = document.getElementById("but"+oid);
				var div1 = document.getElementById("div"+oid);
				if(but.value == "订单详情"){
					// 1.创建异步对象
					var xhr = createXmlHttp();
					// 2.设置监听
					xhr.onreadystatechange = function(){
						if(xhr.readyState == 4){
							if(xhr.status == 200){
								div1.innerHTML = xhr.responseText;
							}
						}
					}
					// 3.打开连接
					xhr.open("GET","${pageContext.request.contextPath}/store_findOrderItem.action?itemid="+oid+"&time="+new Date().getTime(),true);
					// 4.发送
					xhr.send(null);
					but.value = "关闭";
				}else{
					div1.innerHTML = "";
					but.value="订单详情";
				}
				
			}
			function createXmlHttp(){
				   var xmlHttp;
				   try{ // Firefox, Opera 8.0+, Safari
				        xmlHttp=new XMLHttpRequest();
				    }
				    catch (e){
					   try{// Internet Explorer
					         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
					      }
					    catch (e){
					      try{
					         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
					      }
					      catch (e){}
					      }
				    }

					return xmlHttp;
				 }
		</script>
	</body>
</HTML>


