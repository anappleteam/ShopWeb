<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table style="width:100%;border:0" >
	<tr>
		<td><img width="40" height="45" src="${ pageContext.request.contextPath }/<s:property value="orderItem.product.image"/>"></td>
		<td><s:property value="orderItem.product.pname"/></td>
		<td>电话：<s:property value="orderItem.order.phone"/></td>
		<td>地址：<s:property value="orderItem.order.addr"/></td>
	</tr>
</table>