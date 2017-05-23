<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<%@ include file="headinclude.html"%>
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<jsp:include page="navetion.html"></jsp:include>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<div class="header-right">
		<div class="profile_details_left">
			<!--notifications of menu start -->
			<ul class="nofitications-dropdown">

				<li class="dropdown head-dpdn"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"><i class="fa fa-bell"></i><span
						class="badge blue">N</span></a>
					<ul class="dropdown-menu">
						<li>
							<div class="notification_header">
								<h3>你有${fn:length(warningList) }条警告信息</h3>
							</div>
						</li>
						<li><a href="#">
								<div class="notification_desc">
									<p>煤气泄漏</p>
									<p>
										<span>一小时以前</span>
									</p>
								</div>
								<div class="clearfix"></div>
						</a></li>

						<div class="clearfix"></div>
						<li>
							<div class="notification_bottom">
								<a href="#">查看所有信息</a>
							</div>
						</li>
					</ul>
					<div class="clearfix"></div>
		</div>
		<!--notification menu end -->
		<div class="profile_details">
			<ul>
				<li class="dropdown profile_details_drop"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false">
						<div class="profile_img">
							<span class="prfil-img"><img src="images/a.png" alt="">
							</span>
							<div class="user-name">
								<p>昵称</p>
								<span>${userObj.userName }</span>
							</div>
							<i class="fa fa-angle-down lnr"></i> <i
								class="fa fa-angle-up lnr"></i>
							<div class="clearfix"></div>
						</div>
				</a>
					<ul class="dropdown-menu drp-mnu">
						<li><a href="#"><i class="fa fa-cog"></i> 设置</a></li>
						<li><a href="#"><i class="fa fa-user"></i> 个人中心</a></li>
						<li><a href="#"><i class="fa fa-sign-out"></i> 注销</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	<!-- main content start-->
	<div id="page-wrapper">
		<div class="main-page">
			<div class="row-one">
				<div class="col-md-4 widget">
					<div class="stats-left ">
						<h5>今天</h5>
						<h4>监控</h4>
					</div>
					<div class="stats-right">
						<label> 45</label>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-4 widget states-mdl">
					<div class="stats-left">
						<h5>今天</h5>
						<h4>传感器</h4>
					</div>
					<div class="stats-right">
						<label>${fn:length(userObj.deviceList) }</label>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-4 widget states-last">
					<div class="stats-left">
						<h5>今天</h5>
						<h4>警告</h4>
					</div>
					<div class="stats-right">
						<label>${fn:length(warningList) }</label>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="charts">
				<div class="charts-grids states-mdl">
					<h4 class="title">温湿度记录</h4>
					<canvas id="line" height="300" width="800"> </canvas>
				</div>
				<div class="clearfix"></div>
				<script>
					var lineChartData = {
						labels : [ "Jan", "Feb", "March", "April", "May",
								"June", "July", "Jan", "Feb", "March", "April",
								"May", "June", "July" ],
						datasets : [
								{
									fillColor : "rgba(242, 179, 63, 1)",
									strokeColor : "#F2B33F",
									pointColor : "rgba(242, 179, 63, 1)",
									pointStrokeColor : "#fff",
									data : [ 70, 60, 72, 61, 75, 59, 80, 70,
											60, 72, 61, 75, 59, 80 ]

								},
								{
									fillColor : "rgba(97, 100, 193, 1)",
									strokeColor : "#6164C1",
									pointColor : "rgba(97, 100, 193,1)",
									pointStrokeColor : "#9358ac",
									data : [ 50, 65, 51, 67, 52, 64, 100, 50,
											65, 51, 67, 52, 64, 50 ]
								} ]
					};
					new Chart(document.getElementById("line").getContext("2d"))
							.Line(lineChartData);
				</script>

			</div>
			<div class="row">
				<table class="table stats-table">
					<thead>
						<tr>
							<th>设备ID</th>
							<th>设备名称</th>
							<th>状态<br></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">1</th>
							<td>开关</td>
							<td><span class="label label-success">在线</span></td>
						</tr>
						<tr>
							<th scope="row">2</th>
							<td>检测</td>
							<td><span class="label label-danger">离线</span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="row">
			<div class="clearfix"></div>
		</div>
		<div class="row calender widget-shadow">
			<h4 class="title">Calender</h4>
			<div class="cal1"></div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!--footer-->
	<%@ include file="footinclude.html"%>
	<!--//footer-->
	<!-- Classie -->
	<script src="js/classie.js"></script>
	<script>
		var menuLeft = document.getElementById('cbp-spmenu-s1'), showLeftPush = document
				.getElementById('showLeftPush'), body = document.body;

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
	<!--scrolling js-->
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.js">
		
	</script>
</body>
</html>