<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/common.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/header.jsp" />
	<div class="container cart"
		style="margin-top: auto;padding-top: 120px;">
		<form id="userAction_save_do" name="Form1"
			action="${pageContext.request.contextPath}/productMng_update.action"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="pid"
				value="<s:property value="model.pid"/>"> <input
				type="hidden" name="image" value="<s:property value="model.image"/>">

			&nbsp;
			<table>
				<tr>
					<td style="text-align:center;vertical-align:middle;" colSpan="4">
						<STRONG>编辑商品</STRONG>
					</td>
				</tr>

				<tr>
					<td style="text-align:center;vertical-align:middle;">商品名称：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="pname" value="<s:property value="model.pname"/>"
						id="userAction_save_do_logonName" class="text" /></td>
					<td style="text-align:center;vertical-align:middle;">是否热门：</td>
					<td style="text-align:center;vertical-align:middle;"><select
						name="is_hot">
							<s:if test="model.is_hot==1">
								<option value="1" selected="selected">是</option>
								<option value="0">否</option>
							</s:if>
							<s:else>
								<option value="1">是</option>
								<option value="0" selected="selected">否</option>
							</s:else>
					</select></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">市场价格：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="market_price"
						value="<s:property value="model.market_price"/>"
						id="userAction_save_do_logonName" class="text" /></td>
					<td style="text-align:center;vertical-align:middle;">商城价格：</td>
					<td style="text-align:center;vertical-align:middle;"><input
						type="text" name="shop_price"
						value="<s:property value="model.shop_price"/>"
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
						class="text" type="text" name="pavailable"
						value="<s:property value="model.pavailable"/>"
						id="userAction_save_do_logonName" class="bg" /></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">所属的二级分类：
					</td>
					<td style="text-align:center;vertical-align:middle;"><select
						name="csid">
							<s:iterator var="cs" value="csList">
								<s:if test="#cs.csid==model.categorySecond.csid">
									<option value="<s:property value="#cs.csid"/>"
										selected="selected"><s:property value="#cs.csname"/></option>
								</s:if>
								<s:else>
									<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname"/></option>
								</s:else>
							</s:iterator>
					</select></td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品描述：</td>
					<td style="text-align:center;vertical-align:middle;"><textarea
							class="text" name="pdesc" rows="5" cols="30"><s:property value="model.pdesc"/></textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;" colSpan="4">
						<input class="myButton" type="submit"
						id="userAction_save_do_submit" value="确定" /> <input
						class="myButton" type="reset" value="重置" /> <input
						class="myButton" type="button" onclick="history.go(-1)" value="返回" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>