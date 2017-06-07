<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>情景模式激活</title>
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
			<h3 class="title1">情景模式激活</h3>
			<div class="panel-body widget-shadow">
				<h4>情景模式:</h4>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>情景模式名称</th>
							<th>情景模式动作查看</th>
							<th>激活</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${profileList }" var="profile">

							<tr>
								<th scope="row">${profile.pid }</th>
								<td>${profile.pName }</td>
								<td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#lookProfileAction${profile.pid }">查看</button></td>
								<td><button id="active${profile.pid }" type="button" class="btn btn-danger">激活</button></td>
							</tr>
							<script>
							$("#active${profile.pid }").click(function(){
								$.get("ActionServlet?stat=activeProfile&pid=${profile.pid }",function(data,status){
									alert(data);
								})
							});
							</script>
							
						</c:forEach>
						
					</tbody>
				</table>
				
				<c:forEach items="${profileList }" var="profile">
					<div class="modal fade" id="lookProfileAction${profile.pid }" tabindex="-1" role="dialog" aria-labelledby="lookProfileAction${profile.pid }">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="gridSystemModalLabel">动作查看</h4>
									</div>
									<div class="modal-body">
										
										<div class="panel-body widget-shadow">
						<h4>动作列表:</h4>
						<table class="table">
							<thead>
								<tr>
								  <th>#</th>
								  <th>设备名称</th>
								  <th>动作</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${profile.profileActionList }" var="profileAction">
								<tr>
								  <th scope="row">${profileAction.aid }</th>
								  <td>${profileAction.pDeviceName }</td>
								  <td>${profileAction.a_action==0?'关':'开' }</td>
								</tr>
							</c:forEach>
								
							</tbody>
						</table>
					</div>  
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
				</c:forEach>
				
				
				
				
				
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