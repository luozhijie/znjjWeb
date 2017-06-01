<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<%@ include file="navetion.html"%>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<%@ include file="righthead.html"%>
	<!-- main content start-->
	<div id="page-wrapper">
		<div class="main-page">
			<div class="row-one">
				<div class="col-md-4 widget">
					<div class="stats-left ">
						<h5>今天</h5>
						<h4>操作</h4>
					</div>
					<div class="stats-right">
						<label>45</label>
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
			<div id="charts" class="charts">

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
						<c:forEach items="${userObj.deviceList }" var="device">
							<tr>
								<th scope="row">${device.deviceId }</th>
								<td>${device.deviceName }</td>
								<td><span class="label label-success">在线</span></td>
							</tr>
						</c:forEach>

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
		$(document).ready(function() {
			$.get("GetInfoServlet?stat=temp", function(data) {
				$("#charts").html(data);
			});
		});
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