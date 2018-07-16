<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html>
<head>
<title>4396商城管理中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Baxster Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	addEventListener("load", function() {
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
							<li><a href="#"><i class="fa fa-th-large nav_icon"></i>用户管理
									<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li><a
										href="${pageContext.request.contextPath}/userAdmin_findAll.action?page=1"
										target="mainFrame">用户管理</a></li>
								</ul> <!-- /nav-second-level --></li>
							<li><a href="#"><i class="fa fa-th-large nav_icon"></i>一级分类管理
									<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li><a
										href="${pageContext.request.contextPath}/adminCategory_findAll.action"
										target="mainFrame">一级分类管理</a></li>
								</ul> <!-- /nav-second-level --></li>
							<li><a href="#"><i class="fa fa-th-large nav_icon"></i>二级分类管理
									<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li><a
										href="${pageContext.request.contextPath}/adminCategorySecond_findAll.action?page=1"
										target="mainFrame">二级分类管理</a></li>
								</ul> <!-- /nav-second-level --></li>
							<li><a href="#"><i class="fa fa-th-large nav_icon"></i>店铺管理<span
									class="fa arrow"></span></a>
								<ul class="nav nav-second-level collapse">
									<li><a
										href="${pageContext.request.contextPath}/adminStore_findAllAudit.action?page=1"
										target="mainFrame">开店审核</a></li>
									<li><a
										href="${pageContext.request.contextPath}/adminStore_findAll.action?page=1"
										target="mainFrame">店铺管理</a></li>
								</ul> <!-- /nav-second-level --></li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="sticky-header header-section ">
			<div class="header-left">
				<div class="logo">
					<a href="index.html">
						<ul>
							<li><img src="images/logo1.png" /></li>
							<li><h1>4396商城</h1></li>
							<div class="clearfix"></div>
						</ul>
					</a>
				</div>


				<div class="clearfix"></div>
			</div>
			<div class="header-right">

				<!--notification menu end -->
				<div class="profile_details">
					<ul>
						<li class="dropdown profile_details_drop">
							<div class="profile_img">
								<div class="clearfix"></div>
								<h4 style="margin:5px">
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

	<script type="text/javascript" src="js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/dev-loaders.js"></script>
	<script type="text/javascript" src="js/dev-layout-default.js"></script>
	<script type="text/javascript" src="js/jquery.marquee.js"></script>

	<!-- candlestick -->
	<script type="text/javascript" src="js/jquery.jqcandlestick.min.js"></script>
	<!-- //candlestick -->


	<!--scrolling js-->
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/scripts.js"></script>
	<!--//scrolling js-->
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
