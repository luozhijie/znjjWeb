<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>警告处理</title>
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


			<div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">警告处理</h3>
				</div>
				<div class="panel-body">
					<div class="panel-body widget-shadow">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>设备ID</th>
									<th>警告ID</th>
									<th>设备类型</th>
									<th>时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${warningList }" var="warning">
									<tr>
										<td>${warning.did }</td>
										<td>${warning.wid }</td>
										<td>${warning.deviceName }</td>
										<td>${warning.deviceType.deviceTypeName }</td>
										<td>${warning.times }</td>
										<td>
											<button id="read${warning.wid }and${warning.did }" type="button" class="btn btn-primary">确认</button>
										</td>
									</tr>
									<script>
										$("#read${warning.wid }and${warning.did }").click(function(){
											$.get("",function(data,status){
												
											});
										});
									</script>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>



			<div class="clearfix"></div>
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