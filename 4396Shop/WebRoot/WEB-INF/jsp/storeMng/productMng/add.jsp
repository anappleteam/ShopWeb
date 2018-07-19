<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<link href="${pageContext.request.contextPath}/css/cart.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container cart"
		style="margin-top: auto;padding-top: 120px;">
		<form id="userAction_save_do" name="Form1"
			action="${pageContext.request.contextPath}/productMng_save.action"
			method="post" onsubmit="return checkProductAttr();" onreset="resetBorderStyle(this)"
			enctype="multipart/form-data">
			<br />
			<s:if test="hasActionErrors()">
				<s:actionerror />
				<br />
			</s:if>
			<table>
				<tr>
					<td colspan="4" style="text-align:center;vertical-align:middle;">
						<strong>添加商品</strong>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品名称：</td>
					<td style="text-align:center;vertical-align:middle;">
						<input type="text" name="pname"
							onchange="checkInputLabel(this)" class="text" />
					</td>
					<td style="text-align:center;vertical-align:middle;">是否热门：</td>
					<td style="text-align:center;vertical-align:middle;">
						<select name="is_hot">
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">市场价格：</td>
					<td style="text-align:center;vertical-align:middle;">
						<input type="text" name="market_price"
							onchange="checkInputFloat(this)"
							id="userAction_save_do_logonName" class="text" />
					</td>
					<td style="text-align:center;vertical-align:middle;">商城价格：</td>
					<td style="text-align:center;vertical-align:middle;">
						<input type="text" name="shop_price"
							onchange="checkInputFloat(this)"
							id="userAction_save_do_logonName" class="text" />
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品图片：</td>
					<td style="text-align:center;vertical-align:middle;">
						<input type="file" name="upload" accept="image/*"
							onchange="changeFile(this.files[0])" class="change" />
						<a href="javascript:;" class="myButton" id="file_button"
							onclick="document.Form1.upload.click()">选择图片</a>
						<input class="text" id="file_container"
							onchange="checkInputLabel(this)" readonly="readonly" />
						<img id="img_preview" src="" />
					</td>
					<td style="text-align:center;vertical-align:middle;">库存：</td>
					<td style="text-align:center;vertical-align:middle;">
						<input type="text" name="pavailable"
							onchange="checkInputInt(this)"
							id="userAction_save_do_logonName" class="text" />
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">所属的二级分类：
					</td>
					<td style="text-align:center;vertical-align:middle;">
						<select name="csid">
							<s:iterator var="cs" value="csList">
								<option value="<s:property value="#cs.csid"/>"><s:property value="#cs.csname"/></option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;">商品描述：</td>
					<td colspan="3">
						<textarea name="pdesc" onchange="checkInputLabel(this)"
							rows="5" cols="30" class="text"></textarea>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;vertical-align:middle;" colSpan="4">
						<input type="submit" id="userAction_save_do_submit" value="确定"
							class="myButton" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="myButton" type="reset" value="重置" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input class="myButton" type="button" onclick="history.go(-1)"
							value="返回" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
		function checkInputLabel(inputLabel){
			if(inputLabel.value==""){
				inputLabel.style.borderColor="red";
				return false;
			}else{
				inputLabel.style.borderColor="#66ee66";
				return true;
			}
		}
		function checkInputInt(inputLabel){
			var n=parseInt(inputLabel.value);
			if(n.toString()=="NaN"||n.toString().length!=inputLabel.value.length||n<0){
				inputLabel.value="";
				inputLabel.style.borderColor="red";
				return false;
			}else{
				inputLabel.value=n;
				inputLabel.style.borderColor="#66ee66";
				return true;
			}
		}
		function checkInputFloat(inputLabel){
			var n=parseFloat(inputLabel.value);
			if(n.toString()=="NaN"||n.toString().length!=inputLabel.value.length||n<0){
				inputLabel.value="";
				inputLabel.style.borderColor="red";
				return false;
			}else{
				inputLabel.value=n;
				inputLabel.style.borderColor="#66ee66";
				return true;
			}
		}
		function resetBorderStyle(form){
			form.pname.style.borderColor="";
			form.market_price.style.borderColor="";
			form.shop_price.style.borderColor="";
			form.pavailable.style.borderColor="";
			document.getElementById("file_container").style.borderColor="";
			form.pdesc.style.borderColor="";
		}
		function checkProductAttr(){
			var ele_pname=document.Form1.pname;
			var ele_market_price=document.Form1.market_price;
			var ele_shop_price=document.Form1.shop_price;
			var ele_upload=document.Form1.upload;
			var ele_pavailable=document.Form1.pavailable;
			var ele_pdesc=document.Form1.pdesc;
			var check=true;
			if(ele_pname.value==""){
				ele_pname.style.borderColor="red";
				check=false;
			}
			if(!checkInputFloat(ele_market_price)){
				//ele_market_price.style.borderColor="red";
				check=false;
			}
			if(!checkInputFloat(ele_shop_price)){
				//ele_shop_price.style.borderColor="red";
				check=false;
			}
			if(ele_upload.value==""){
				document.getElementById("file_container").style.borderColor="red";
				check=false;
			}
			if(!checkInputInt(ele_pavailable)){
				//ele_pavailable.style.borderColor="red";
				check=false;
			}
			if(ele_pdesc.value==""){
				ele_pdesc.style.borderColor="red";
				check=false;
			}
			return check;
		}
		function changeFile(img){
			var fc=document.getElementById("file_container");
			if((img.type).indexOf("image/")==-1){
				fc.value="非图片格式";
				fc.style.borderColor="red";
			}
			else{
				var fr=new FileReader();
				fr.readAsDataURL(img);
				fr.onload=function(){
					document.getElementById("img_preview").src=fr.result;
				}
				fc.value=document.Form1.upload.value;
				fc.style.borderColor="#66ee66";
			}
		}
	</script>
</HTML>