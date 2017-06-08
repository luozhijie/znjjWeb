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
					<button type="button" class="btn btn-primary"
						data-toggle="modal" data-target="#addFimaryGroup">新增家庭组</button>
					<div class="modal fade" id="addFimaryGroup" tabindex="-1"
						role="dialog" aria-labelledby="addFimaryGroup">
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
									<div class="form-three widget-shadow">
										<div class="form-horizontal">
											<div class="form-group">
												<label for="focusedinput" class="col-sm-2 control-label">家庭组名称：</label>
												<div class="col-sm-8">
													<input id="addFamilyGroupName" type="text"
														class="form-control1" id="focusedinput"
														placeholder="请输入家庭组名称（自取名）">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">用户1ID</label>
												<div class="col-sm-8">
													<input id="" type="text" class="form-control1"
														id="focusedinput" placeholder="请输入用户ID（自取名）"
														value="${userObj.userId }">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">用户2ID</label>
												<div class="col-sm-8">
													<input id="addFamily2ID" type="text" class="form-control1"
														id="focusedinput" placeholder="请输入用户ID（自取名）">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">用户3ID</label>
												<div class="col-sm-8">
													<input id="addFamily3ID" type="text" class="form-control1"
														id="focusedinput" placeholder="请输入用户ID（自取名）">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">用户4ID</label>
												<div class="col-sm-8">
													<input id="addFamily4ID" type="text" class="form-control1"
														id="focusedinput" placeholder="请输入用户ID（自取名）">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">用户5ID</label>
												<div class="col-sm-8">
													<input id="addFamily5ID" type="text" class="form-control1"
														id="focusedinput" placeholder="请输入用户ID（自取名）">
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">关闭</button>
									<button id="submitFamilyGroup" type="button"
										class="btn btn-primary">添加家庭组</button>
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
									<td>${familyGroup.user1.userName }</td>
									<td>${familyGroup.user2.userName }</td>
									<td>${familyGroup.user3.userName }</td>
									<td>${familyGroup.user4.userName }</td>
									<td>${familyGroup.user5.userName }</td>
									<td>
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#edit${familyGroup.fid }">编辑家庭组</button>
									</td>
								</tr>
								<div class="modal fade" id="edit${familyGroup.fid }"
									tabindex="-1" role="dialog"
									aria-labelledby="edit${familyGroup.fid }">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
												<h4 class="modal-title" id="gridSystemModalLabel">编辑家庭组</h4>
											</div>
											<div class="modal-body">
												<div class="form-three widget-shadow">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="focusedinput" class="col-sm-2 control-label">家庭组名称：</label>
															<div class="col-sm-8">
																<input id="familyGroupName${familyGroup.fid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入家庭组名称（自取名）"
																	value="${familyGroup.fimaryName }">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">用户1ID</label>
															<div class="col-sm-8">
																<input id="" type="text" class="form-control1"
																	id="focusedinput" placeholder="请输入用户ID（自取名）"
																	value="${userObj.userId }">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">用户2ID</label>
															<div class="col-sm-8">
																<input id="addFamily2ID${familyGroup.fid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入用户ID（自取名）"
																	value="${familyGroup.user2.userId}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">用户3ID</label>
															<div class="col-sm-8">
																<input id="addFamily3ID${familyGroup.fid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入用户ID（自取名）"
																	value="${familyGroup.user3.userId}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">用户4ID</label>
															<div class="col-sm-8">
																<input id="addFamily4ID${familyGroup.fid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入用户ID（自取名）"
																	value="${familyGroup.user4.userId}">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">用户5ID</label>
															<div class="col-sm-8">
																<input id="addFamily5ID${familyGroup.fid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入用户ID（自取名）"
																	value="${familyGroup.user5.userId}">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button id="delFamilyGroup${familyGroup.fid }" type="button" class="btn btn-danger"
													data-dismiss="modal">删除</button>
												<button id="editFamilyGroup${familyGroup.fid }"
													type="button" class="btn btn-primary">提交修改</button>
											</div>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
								<script>
									$("#editFamilyGroup${familyGroup.fid }").click(function(){
										$.post("ActionServlet?stat=editFamilyGroup",{
											familyGroupName : $("#familyGroupName${familyGroup.fid }").val(),
											fid : "${familyGroup.fid }" ,
											uid2 : $("#addFamily2ID${familyGroup.fid }").val(),
											uid3 : $("#addFamily3ID${familyGroup.fid }").val(),
											uid4 : $("#addFamily4ID${familyGroup.fid }").val(),
											uid5 : $("#addFamily5ID${familyGroup.fid }").val()
										},function(data , status){
											if(data == "修改成功"){
												history.go(0);
											}else{
												alert(data);
											}
										});
									});
									
									$("#delFamilyGroup${familyGroup.fid }").click(function(){
										$.get("ActionServlet?stat=delFamilyGroup&fid=${familyGroup.fid }",function(data,status){
											if(data == "删除成功"){
												history.go(0);
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
		$("#submitFamilyGroup").click(function() {
			$.post("ActionServlet?stat=addFamilyGroup", {
				familyGroupName : $("#addFamilyGroupName").val(),
				uid2 : $("#addFamily2ID").val(),
				uid3 : $("#addFamily3ID").val(),
				uid4 : $("#addFamily4ID").val(),
				uid5 : $("#addFamily5ID").val()
			}, function(data, status) {
				if (data == "添加成功") {
					history.go(0);
				} else {
					alert("添加失败");
					history.go(0);
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