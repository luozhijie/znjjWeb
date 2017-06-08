<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>情景模式添加</title>
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
			<h3 class="title1">情景模式添加</h3><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addProfile">添加情景模式</button>
			
			<div class="modal fade" id="addProfile" tabindex="-1" role="dialog" aria-labelledby="addProfile">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title">情景组添加</h4>
									</div>
									<div class="modal-body">
									<div class="form-group">
									<label for="focusedinput" class="col-sm-2 control-label">情景组名</label>
									<div class="col-sm-8">
										<input id="profileAddName" type="text" class="form-control1" placeholder="请输入情景模式组名">
									</div>
								</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
										<button id="addPrifileButton" type="button" class="btn btn-primary">添加情景组</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
			
			
			
			
			<div class="panel-body widget-shadow">
				<h4>情景模式:</h4>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>情景模式名称</th>
							<th>情景模式动作查看</th>
							<th>添加动作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${profileList }" var="profile">
							<tr>
								<th scope="row">${profile.pid }</th>
								<td>${profile.pName }</td>
								<td><button type="button" class="btn btn-primary"
										data-toggle="modal"
										data-target="#lookProfileAction${profile.pid }">查看</button></td>
								<td>
									<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAction${profile.pid }">添加动作</button>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				
				<c:forEach items="${profileList }" var="profile">
					<div class="modal fade" id="addAction${profile.pid }" tabindex="-1" role="dialog" aria-labelledby="addAction${profile.pid }">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="gridSystemModalLabel">添加动作</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
									<div class="col-sm-8">
										<select id="lessDeviceChoose${profile.pid }" multiple="" class="form-control1">
											
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-8">
										<select id="onoffaction${profile.pid }" multiple="" class="form-control1">
											<option value="1">开</option>
											<option value="0">关</option>
											
										</select>
									</div>
								</div>
									
									
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
										<button id="addProfileButton${profile.pid }" type="button" class="btn btn-primary">添加</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal-dialog -->
						</div><!-- /.modal -->
							
							<script>
							$(document).ready(function(){
								$.get("GetInfoServlet?stat=lessProfileDevice&pid=${profile.pid }",function(data,status){
									$("#lessDeviceChoose${profile.pid }").html(data);
								});								
							});
							
							$("#addProfileButton${profile.pid }").click(function(){
								$.get("ActionServlet?stat=addProfileAction&pid=${profile.pid }&did="+$("#lessDeviceChoose${profile.pid }").val()+"&aid="+$("#onoffaction${profile.pid }").val(),function(data, status){
									if(data == "添加成功"){
										history.go(0);
									}else{
										alert(data);
									}
								});
							});
							</script>
				</c:forEach>
				
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
		$("#addPrifileButton").click(function(){
			$.get("ActionServlet?stat=addProfile&pname="+$("#profileAddName").val(),function(data,status){
				if(data == "添加成功"){
					history.go(0);
				}else{
					alert(data);
				}
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