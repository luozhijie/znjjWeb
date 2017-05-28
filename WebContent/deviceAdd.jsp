<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>设备添加</title>
<%@ include file="headinclude.html"%>
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<jsp:include page="navetion.html"></jsp:include>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<%@ include file="righthead.html"%>
	<div class="clearfix"></div>
	<!-- main content start-->
	<div id="page-wrapper">

		<div class="forms">
			<div class="main-page">
				<h3 class="title1">添加设备：</h3>
				<div class="form-three widget-shadow">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="focusedinput" class="col-sm-2 control-label">设备名称：</label>
							<div class="col-sm-8">
								<input type="text" class="form-control1" id="focusedinput"
									placeholder="请输入设备名称（自取）">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">设备类型</label>
							<div class="col-sm-8">
								<select multiple="" class="form-control1">
									<c:forEach items="${deviceTypeList }" var="deviceType">
										<option value="${deviceType.deviceTypeId }">${deviceType.deviceTypeName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">GPIO</label>
							<div class="col-sm-8">
								<select multiple="" class="form-control1">
									<option>Option 1</option>
									<option>Option 2</option>
									<option>Option 3</option>
									<option>Option 4</option>
									<option>Option 5</option>
								</select>
							</div>
						</div>
						<div class="form-group" align="center">
							<button type="submit" class="btn btn-default">提交</button>
							<button type="submit" class="btn btn-default">重置</button>
						</div>
					</form>
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