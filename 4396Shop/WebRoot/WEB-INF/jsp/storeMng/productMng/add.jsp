<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div class="container cart"
		style="margin-top: auto;padding-top: 120px;">
		<form id="userAction_save_do" name="Form1"
			action="${pageContext.request.contextPath}/productMng_save.action"
			method="post" enctype="multipart/form-data">
			<br />
			<table>
				<tr>
					<td colspan="4" style="text-align:center;vertical-align:middle;">
						<strong>添加商品</strong>
					</td>
				</tr>

				<tr>
					<td style="text-align:center;vertical-align:middle;">商品名称：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="pname" value=""
						id="userAction_save_do_logonName" class="text" /></td>
					<td style="text-align:center;vertical-align:middle;">是否热门：</td>
					<td style="text-align:center;vertical-align:middle;"><select
						name="is_hot">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">市场价格：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="market_price" value=""
						id="userAction_save_do_logonName" class="text" /></td>
					<td style="text-align:center;vertical-align:middle;">商城价格：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="shop_price" value=""
						id="userAction_save_do_logonName" class="text" /></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品图片：</td>
					<td style="text-align:center;vertical-align:middle;"><a
						href="javascript:;" class="upload">选择图片 <input type="file"
							name="upload" class="change" multiple="multiple" class="text" />
					</a></td>
					<td style="text-align:center;vertical-align:middle;">库存：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="pavailable"
						value="<s:property value="model.pavailable"/>"
						id="userAction_save_do_logonName" class="text" /></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">所属的二级分类：
					</td>
					<td style="text-align:center;vertical-align:middle;"><select
						name="csid" class="dropdown">
							<s:iterator var="cs" value="csList">
								<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname"/></option>
							</s:iterator>
					</select></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品描述：</td>
					<td colspan="3"><textarea name="pdesc" rows="5" cols="30"
							class="text"></textarea></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;" colSpan="4">
						<input type="submit" id="userAction_save_do_submit" value="确定"
						class="myButton" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						class="myButton" type="reset" value="重置" />

						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input class="myButton"
						type="button" onclick="history.go(-1)" value="返回" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</HTML>