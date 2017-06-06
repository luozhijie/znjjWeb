<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设置</title>
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
				<h3 class="title1">家庭组</h3>
				<div class="panel-body widget-shadow">
					<h4>家庭组管理:</h4>
					<button type="button" class="btn btn-primary btn-lg"
						data-toggle="modal" data-target="#gridSystemModal">Demo
						modal</button>

					<div class="modal fade" id="gridSystemModal" tabindex="-1"
						role="dialog" aria-labelledby="gridSystemModalLabel">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="gridSystemModalLabel">新增家庭组</h4>
								</div>
								<div class="modal-body">
								
								
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button type="button" class="btn btn-primary">保存</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->


					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>家庭组名称</th>
								<th>成员1</th>
								<th>成员2</th>
								<th>成员3</th>
								<th>成员4</th>
								<th>成员5</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${familyGroupList }" var="familyGroup">
								<tr>
									<th scope="row">${familyGroup.fid }</th>
									<td>${familyGroup.fimaryName }</td>
									<td>${familyGroup.familyPersonId1 }</td>
									<td>${familyGroup.familyPersonId2 }</td>
									<td>${familyGroup.familyPersonId3 }</td>
									<td>${familyGroup.familyPersonId4 }</td>
									<td>${familyGroup.familyPersonId5 }</td>
									<td>
										<button id="edit${familyGroup.fid }" class="btn btn-primary">编辑</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
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