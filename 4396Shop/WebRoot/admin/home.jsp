<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- <html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
		body
		{
			SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
		}
    </style>
  </head>
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
  <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/admin/left.jsp" name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/admin/welcome.jsp" name="mainFrame">
  </frameset>
  <frame src="${pageContext.request.contextPath}/admin/bottom.jsp" name="bottomFrame" scrolling="NO"  noresize>
</frameset>
</html> -->
<!DOCTYPE HTML>
<html>
<head>
<title>4396商城管理中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Baxster Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<link rel="icon" href="favicon.ico" type="image/x-icon">
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- chart -->
<script src="js/Chart.js"></script>
<!-- //chart -->
<!-- js-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/modernizr.custom.js"></script>
<!--webfonts-->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
<!--animate-->
<link href="css/animate.css" rel="stylesheet" type="text/css"
	media="all">
<script src="js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!-- Metis Menu -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
<!--//Metis Menu -->
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class="sidebar" role="navigation">
			<div class="navbar-collapse">
				<nav
					class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right dev-page-sidebar mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
					id="cbp-spmenu-s1">
					<div class="scrollbar scrollbar1">
						<ul class="nav" id="side-menu">
						<div>
							<a href="javascript: d.openAll();">展开所有</a> | <a
								href="javascript: d.closeAll();">关闭所有</a>

							<script type="text/javascript"
								src="${pageContext.request.contextPath}/js/dtree.js"></script>
							<script type="text/javascript">
							<!--
							d = new dTree('d');
							d.add('01',-1,'系统菜单树');
							d.add('0101','01','用户管理','','','mainFrame');
							d.add('010101','0101','用户管理','${pageContext.request.contextPath}/userAdmin_findAll.action?page=1','','mainFrame');
							d.add('0102','01','一级分类管理','','','mainFrame');
							d.add('010201','0102','一级分类管理','${pageContext.request.contextPath}/adminCategory_findAll.action','','mainFrame');
							d.add('0103','01','二级分类管理');
							d.add('010301','0103','二级分类管理','${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1','','mainFrame');
							d.add('0104','01','商品管理');
							d.add('010401','0104','商品管理','${pageContext.request.contextPath}/adminProduct_findAll.action?page=1','','mainFrame');
							d.add('0105','01','订单管理');
							d.add('010501','0105','订单管理','${pageContext.request.contextPath}/adminOrder_findAll.action?page=1','','mainFrame');
							document.write(d);
							//-->
							</script>
						</div>
						</ul>
					</div>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--logo -->
				<div class="logo">
					<a href="index.html">
						<ul>
							<li><img src="images/logo1.png" alt="" /></li>
							<li><h1>4396商城</h1></li>
							<div class="clearfix"></div>
						</ul>
					</a>
				</div>
				<!--//logo-->


				<div class="clearfix"></div>
			</div>
			<div class="header-right">

				<!--notification menu end -->
				<div class="profile_details">
					<ul>
						<li class="dropdown profile_details_drop">
							<div class="profile_img">
								<div class="clearfix"></div>
								<h4>
									管理员:
									<s:property value="#session.existAdminUser.username" />
								</h4>
							</div>
						</li>
					</ul>
				</div>
				<!--toggle button start-->
				<button id="showLeftPush">
					<i class="fa fa-bars"></i>
				</button>
				<!--toggle button end-->
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- //header-ends -->
		<!-- main contents -->
		<div id="page-wrapper">
			<div class="main-page">
				<!--grids-->
				<div class="grids">
					<div class="panel panel-widget">
						<div class="tables">
							<iframe
								src="${pageContext.request.contextPath}/admin/welcome.jsp"
								name="mainFrame"></iframe>
						</div>
					</div>
				</div>
				<!--//grids-->

			</div>
		</div>
	</div>

	<script src="js/classie.js"></script>
	<script>
		var menuLeft = document.getElementById('cbp-spmenu-s1'),
			showLeftPush = document.getElementById('showLeftPush'),
			body = document.body;
	
		showLeftPush.onclick = function() {
			classie.toggle(this, 'active');
			classie.toggle(body, 'cbp-spmenu-push-toright');
			classie.toggle(menuLeft, 'cbp-spmenu-open');
			disableOther('showLeftPush');
		};
	
	
		function disableOther(button) {
			if (button !== 'showLeftPush') {
				classie.toggle(showLeftPush, 'disabled');
			}
		}
	</script>

</body>
</html>