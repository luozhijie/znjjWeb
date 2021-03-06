<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开关控制</title>
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
			<div class="tables">
				<h3 class="title1">开关控制</h3>
				<div class="panel-body widget-shadow">
					<h4>开关列表:</h4>
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>开关名称</th>
								<th>GPIO</th>
								<th>设备在线时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userObj.deviceList }" var="device">
								<tr>
									<th scope="row">${device.deviceId }</th>
									<td>${device.deviceName }</td>
									<td>${device.device_gpio }</td>
									<td>${device.device_onLine }</td>
									<td>
										<div class="switch">
											<input id="onoff${device.deviceId }" type="checkbox"
												${device.deviceStat == 0 ? '' : 'checked' } />
										</div>
									</td>
								</tr>
								<script>
								$("#onoff${device.deviceId }").bootstrapSwitch();
								$("#onoff${device.deviceId }").on('switchChange.bootstrapSwitch', function (e, state){
									if(state){
										$.get("ActionServlet?stat=onoff&isoff=1&deviceId=${device.deviceId}",function(data){
										});
									}else{
										$.get("ActionServlet?stat=onoff&isoff=0&deviceId=${device.deviceId}",function(data){
										});
									}
								});
								</script>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
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