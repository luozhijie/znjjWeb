<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加计划任务</title>
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
		<div class="forms">
			<div class="main-page">
				<h3 class="title1">添加计划任务：</h3>
				<div class="form-three widget-shadow">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="focusedinput" class="col-sm-2 control-label">计划名称：</label>
							<div class="col-sm-8">
								<input id="planName" type="text" class="form-control1"
									id="focusedinput" placeholder="请输入计划任务名称（自取名）">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">计划任务执行时间</label>
							<div class="col-sm-2">
								<select id="hour" multiple="" class="form-control1">
									<c:forEach begin="0" end="23" var="hour">
										<option value="${hour }">${hour }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2">
								<select id="min" multiple="" class="form-control1">
									<c:forEach begin="0" end="59" var="min">
										<option value="${min }">${min }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">计划任务对应设备/情景模式</label>
							<div class="col-sm-8">
								<select id="deviceIdOrProfile" multiple="" class="form-control1">
									<c:forEach items="${deviceList }" var="device">
										<option value="1,${device.deviceId }">${device.deviceName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">计划任务对应设备/情景模式</label>
							<div class="col-sm-8">
								<select id="onoff" multiple="" class="form-control1">
									<option value="1">开</option>
									<option value="0">关</option>
								</select>
							</div>
						</div>
						<div class="form-group" align="center">
							<button id="submit" type="submit" class="btn btn-default">提交</button>
							<button type="submit" class="btn btn-default">重置</button>
						</div>
					</div>
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
		$("#submit").click(function() {
			alert($("#hour").val());
			$.post("ActionServlet?stat=planAdd", {
				planName : $("#planName").val() + "",
				hour : $("#hour").val() + "",
				min : $("#min").val() + "",
				deviceIdOrProfile : $("#deviceIdOrProfile").val() + "",
				onoff : $("#onoff").val() + ""
			}, function(data, status) {
				if (data == "添加成功") {

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