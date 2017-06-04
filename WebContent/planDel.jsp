<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>删除计划任务</title>
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
									<td>${plan.pStat }</td>
									<td>${plan.pIsOpen }</td>
									<td>
										<button id="delete${plan.pid }" class="btn btn-danger">删除</button>
									</td>
								</tr>
								<script>
									$("#delete${plan.pid }").click(function(){
										$.get("ActionServlet?stat=planDel&pid=${plan.pid}",function(data,status){
											if(data == "删除成功"){
												$("#tr${plan.pid }").hide();
											}else{
												alert(data);
											}
										});
									});
								</script>
							</c:forEach>
						</tbody>
					</table>
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