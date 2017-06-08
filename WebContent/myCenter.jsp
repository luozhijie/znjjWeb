<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人中心</title>
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

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">个人中心(${userObj.userName })</h3>
				</div>
				<div id="alertPasswordSuccess" class="alert alert-success"
					role="alert" style="display: none;">
					<strong>成功</strong><br>恭喜，您的密码修改成功
				</div>
				<div class="panel-body">
					<div class="panel-group tool-tips widget-shadow" id="accordion"
						role="tablist" aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne"> 用户头像 </a>
								</h4>
							</div>
							<div id="collapseOne" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">
									<span class="prfil-img"
										style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"><img
										hight="150" width="150" src="user_icon/${userObj.iconName}"
										alt=""> </span>
									<div class="clear"></div>
									<button type="button" class="btn btn-primary btn-lg"
										data-toggle="modal" data-target="#gridSystemModal">更改头像</button>
									<div class="modal fade" id="gridSystemModal" tabindex="-1"
										role="dialog" aria-labelledby="gridSystemModalLabel">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
													<h4 class="modal-title" id="gridSystemModalLabel">头像更改</h4>
												</div>
												<div class="modal-body">
													<form id="userIconFileForm" method="post"
														enctype="multipart/form-data">
														<div class="form-group">
															<label for="exampleInputFile">选择头像</label> <input
																type="file" id="uploadFile">
														</div>
													</form>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">关闭</button>
													<button type="button" class="btn btn-primary"
														id="checkIconChange">确认更改</button>
												</div>
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->
									</div>
									<!-- /.modal -->





								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo"> 用户密码修改
									</a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo">
								<div class="form-body">
									<div class="form-group">
										<label for="exampleInputEmail1">密码：</label> <input
											type="password" class="form-control" id="pwd"
											placeholder="请输入密码">
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">确认密码：</label> <input
											type="password" class="form-control" id="cpwd"
											placeholder="请再次输入密码">
									</div>
									<button id="submitPasswordChange" type="button"
										class="btn btn-default">提交更改</button>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingThree">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseThree"
										aria-expanded="false" aria-controls="collapseThree">用户资料</a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingThree">
								<div class="panel-body">
								这是资料
								<br><button type="submit" class="btn btn-default">提交更改</button></div>
							</div>
						</div>
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
		$("#checkIconChange").click(
				function() {
					//判断是否有选择上传文件
					var imgPath = $("#uploadFile").val();
					if (imgPath == "") {
						alert("请选择上传图片！");
						return;
					}
					//判断上传文件的后缀名
					var strExtension = imgPath
							.substr(imgPath.lastIndexOf('.') + 1);
					if (strExtension != 'jpg' && strExtension != 'gif'
							&& strExtension != 'png' && strExtension != 'bmp') {
						alert("请选择图片文件");
						return;
					}
					var name = $("#uploadFile").val();
					var formData = new FormData();
					formData.append("file", $("#uploadFile")[0].files[0]);
					formData.append("name", name);
					$.ajax({
						url : 'UploadServlet',
						type : 'POST',
						data : formData,
						// 告诉jQuery不要去处理发送的数据
						processData : false,
						// 告诉jQuery不要去设置Content-Type请求头
						contentType : false,
						beforeSend : function() {
							console.log("正在进行，请稍候");
						},
						success : function(data) {
							if (data == "上传成功") {
								history.go(0);
							} else {
								alert(data);
							}
						},
						error : function(data) {
							alert(data);
						}
					});
				});
		$("#submitPasswordChange").click(function() {
			var password = $("#pwd").val();
			var passwordcheck = $("#cpwd").val();
			if (password !== passwordcheck) {
				alert("两次密码不一致");
				return;
			}
			$.post("ActionServlet?stat=passwordChange", {
				pwd : password
			}, function(data, status) {
				if (data == "更改成功") {
					$("#alertPasswordSuccess").show();
					$("#pwd").val("");
					$("#cpwd").val("");
				} else {
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