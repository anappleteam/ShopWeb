<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
	</HEAD>
	
	<body>
		<!--  -->
		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminStore_update.action" method="post" enctype="multipart/form-data">
			
			&nbsp;
			<table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<strong><STRONG>店铺详情</STRONG>
						</strong>
					</td>
				</tr>

				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店铺名称：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="pname" value="<s:property value="store.sname"/>" id="userAction_save_do_logonName" class="bg"
						readonly="readonly"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店主姓名：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="market_price" value="<s:property value="store.owner.name"/>" id="userAction_save_do_logonName" class="bg" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店主性别：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="market_price" value="<s:property value="store.owner.sex"/>" id="userAction_save_do_logonName" class="bg" readonly="readonly"/>
					</td>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店主联系方式：
					</td>
					<td class="ta_01" bgColor="#ffffff">
						<input type="text" name="shop_price" value="<s:property value="store.owner.phone"/>" id="userAction_save_do_logonName" class="bg" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店主邮箱：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<input type="text" name="shop_price" value="<s:property value="store.owner.email"/>" id="userAction_save_do_logonName" class="bg" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						店铺描述：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						<textarea name="pdesc" rows="5" cols="30" readonly="readonly"><s:property value="store.sdesc"/></textarea>
					</td>
				</tr>
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
						<span id="Label1"></span>
					</td>
				</tr>
			</table>
		</form>
	</body>
</HTML>