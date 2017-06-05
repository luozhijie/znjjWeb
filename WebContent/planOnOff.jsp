<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>计划任务开关控制</title>
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
				<h3 class="title1">设备删除列表</h3>
				<div class="panel-body widget-shadow">
					<h4>设备删除列表：</h4>
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>任务名称</th>
								<th>任务执行时间</th>
								<th>任务执行动作</th>
								<th>任务状态</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${planList }" var="plan">
								<tr id="tr${plan.pid }">
									<td>${plan.pid }</td>
									<td>${plan.pName }</td>
									<td>${plan.pTime }</td>
									<td>${plan.pStat eq 0 ? "关":"开" }</td>
									<td>${plan.pIsOpen eq 0 ? "未激活" : "正在监听" }</td>
									<td>
										<div class="switch">
											<input id="onoff${plan.pid }" type="checkbox"
												${plan.pIsOpen == 0 ? '' : 'checked' } />
										</div>
									</td>
								</tr>
								<script>
									$("#onoff${plan.pid }").bootstrapSwitch();
									$("#onoff${plan.pid }").on(
											'switchChange.bootstrapSwitch',
											function(e, state) {
												if (state) {
													$.get("ActionServlet?stat=planOnOff&tag=1&pid=${plan.pid}", function(data) {
													});
												} else {
													$.get("ActionServlet?stat=planOnOff&tag=0&pid=${plan.pid}", function(data) {
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