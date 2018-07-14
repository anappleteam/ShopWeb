<%@page import="org.apache.struts2.components.Include"%>
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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="max-age=7200"/>
<title>网上商城</title>
<link href="<%=path%>/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=path%>/css/product.css" rel="stylesheet" type="text/css">
<script>
	function saveCart(){
	document.getElementById("cartForm").submit();
	}
</script>

</head>
<body>

		<jsp:include page="menu.jsp" />
	<div class="container productContent">
		<div class="span6">
		<div class="storeTitle">
		<p class="title"><s:property value="model.store.sname"/></p>
		<a class="link" href="<%=path%>/store_findBySid.action?sid=<s:property value="model.store.sid"/>&page=1">进店逛逛</a>
		</div>
			<div class="hotProductCategory">
				<s:iterator var="c" value="#session.cList">
					<dl>
						<dt>
							<a
								href="<%=path%>/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property
									value="#c.cname" /></a>
						</dt>
						<s:iterator var="cs" value="#c.categorySeconds">
							<dd>
								<a
									href="<%=path%>/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property
										value="#cs.csname" /></a>
							</dd>
						</s:iterator>
					</dl>
				</s:iterator>
			</div>


		</div>
		<script>
			var gsFiles=JSON.parse(sessionStorage.getItem("gsFiles"))||{};
			var a=document.getElementById("pimg");
			var src1=a.getAttribute("src");
			alert(src1);
			gsFilesDate = gsFiles.date;
		    date = new Date();
		    todaysDate = (date.getMonth() + 1).toString() + date.getDate().toString();
		     function imgLoad(el){
		         var imgCanvas = document.createElement("canvas");
		         imgContext = imgCanvas.getContext("2d");
		// 确保canvas尺寸和图片一致
		         imgCanvas.width = el.width;
		         imgCanvas.height = el.height;
		// 在canvas中绘制图片
		         imgContext.drawImage(el, 0, 0, el.width, el.height);
		// 将图片保存为Data URI		      
		         gsFiles.a = imgCanvas.toDataURL("bj.png");
		         //gsFiles.el = imgCanvas.toDataURL("bj.png");
		         gsFiles.date = todaysDate;
		// 将JSON保存到本地存储中
		         try {
		             sessionStorage.setItem("gsFiles", JSON.stringify(gsFiles));
		         }
		         catch (e) {
		             console.log("Storage failed: " + e);
		         }
		     }
		    // 检查数据，如果不存在或者数据过期，则创建一个本地存储
		    if (typeof gsFilesDate === "undefined" || gsFilesDate < todaysDate) {
		        // 图片加载完成后执行
		        <!--image1-->
		        a.addEventListener("load", function () {
		            imgLoad(a)
		        }, false);
		        //设置图片
		        a.setAttribute("src", str1);
		        }else{
		        a.setAttribute("src", gsFiles.a);
		        }
		        console.log(document.cookie)
		</script>
		<div class="span16 last">

			<div class="productImage">
				<a title="" style="outline-style: none; text-decoration: none;"
					id="zoom"
					href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg"
					rel="gallery">
					<div class="zoomPad">
						<img id="pimg" style="opacity: 1;" title="" class="medium"
							src="<%=path%>/<s:property value="model.image"/>">
						<div
							style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;"
							class="zoomPup"></div>
						<div
							style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;"
							class="zoomWindow">
							<div style="width: 368px;" class="zoomWrapper">
								<div style="width: 100%; position: absolute; display: none;"
									class="zoomWrapperTitle"></div>
								<div style="width: 0%; height: 0px;" class="zoomWrapperImage">
									<img
										src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg"
										style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;">
								</div>
							</div>
						</div>
						<div
							style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;"
							class="zoomPreload">Loading zoom</div>
					</div>
				</a>

			</div>
			<div class="name">
				<s:property value="model.pname" />
			</div>
			<div class="sn">
				<div>
					编号：
					<s:property value="model.pid" />
				</div>
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong><s:property value="model.shop_price" />元</strong> 参 考 价：
						<del>
							<s:property value="model.market_price" />
							元
						</del>
					</dd>
				</dl>
				<dl>
					<dt>促销:</dt>
					<dd>
						<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dd>
						<span> </span>
					</dd>
				</dl>
			</div>
			<form id="cartForm" action="${pageContext.request.contextPath }/cart_addCart.action" method="post">
			<input type="hidden" name="pid" value="<s:property value="model.pid" />" />
 			<div class="action">

				<dl class="quantity">
					<dt>购买数量:</dt>
					<dd>
						<input id="count" name="count" value="1" maxlength="4"
							onpaste="return false;" type="text">
						<div>
							<span id="increase" class="increase">&nbsp;</span> <span
								id="decrease" class="decrease">&nbsp;</span>
						</div>
					</dd>
					<dd>件</dd>
				</dl>
				<div class="buy">
					<input id="addCart" class="addCart" value="加入购物车" type="button" onclick="saveCart()"/> 
					<!-- submit可以直接提交，普通button需要JS才可以 -->
				</div>
			</div>
			</form>
			<div id="bar" class="bar">
				<ul>
					<li id="introductionTab"><a href="#introduction">商品介绍</a></li>

				</ul>
			</div>

			<div id="introduction" name="introduction" class="introduction">
				<div class="title">
					<strong><s:property value="model.pdesc" /></strong>
				</div>
				<div>
					<img src="<%=path%>/<s:property value="model.image"/>">
				</div>
			</div>



		</div>
	</div>

	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势"
					title="我们的优势" height="52" width="950">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a href="#">关于我们</a> |</li>
				<li><a href="#">联系我们</a> |</li>
				<li><a href="#">诚聘英才</a> |</li>
				<li><a href="#">法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
		</div>
	</div>
</body>
</html>