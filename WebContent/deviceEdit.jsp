<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改设备</title>
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
								<th>设备名称</th>
								<th>设备类型</th>
								<th>设备最后在线时间</th>
								<th>设备GPIO</th>
								<th>修改</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${deviceList }" var="device">
								<tr id="tr${device.deviceId }">
									<th scope="row">${device.deviceId }</th>
									<td id="tdDeviceName${device.deviceId }">${device.deviceName }</td>
									<td id="tdDeviceType${device.deviceId }">${device.deviceType.deviceTypeName }</td>
									<td>${device.device_onLine }</td>
									<td id="tdDeviceGPIO">${device.device_gpio }</td>
									<td>
										<!-- 按钮触发模态框 -->
										<button class="btn btn-primary btn-sm" data-toggle="modal"
											data-target="#edit${device.deviceId }">编辑</button>
									</td>
								</tr>
								<!-- 模态框（Modal） -->
								<div class="modal fade" id="edit${device.deviceId }"
									tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">${device.deviceName }</h4>
											</div>
											<div class="modal-body">




												<div class="form-three widget-shadow">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="focusedinput" class="col-sm-2 control-label">设备名称：</label>
															<div class="col-sm-8">
																<input id="deviceName${device.deviceId }" type="text"
																	class="form-control1" placeholder="请输入设备名称（自取名）"
																	value="${device.deviceName }" />
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">设备类型</label>
															<div class="col-sm-8">
																<select id="deviceType${device.deviceId }" multiple=""
																	class="form-control1">
																	<c:forEach items="${deviceTypeList }" var="deviceType">
																		<c:if
																			test="${deviceType.deviceTypeId==device.deviceType.deviceTypeId }">
																			<option selected="selected"
																				value="${deviceType.deviceTypeId }">${deviceType.deviceTypeName }</option>
																		</c:if>
																		<c:if
																			test="${deviceType.deviceTypeId!=device.deviceType.deviceTypeId }">
																			<option value="${deviceType.deviceTypeId }">${deviceType.deviceTypeName }</option>
																		</c:if>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">GPIO</label>
															<div class="col-sm-8">
																<select id="gpio${device.deviceId }" multiple=""
																	class="form-control1">
																	<option selected="selected"
																		value="${device.device_gpio }">${device.device_gpio }</option>
																	<c:forEach items="${gpioLessList }" var="gpioLess">
																		<option value="${gpioLess }">${gpioLess }</option>
																	</c:forEach>
																</select>
															</div>
														</div>
													</div>
												</div>





											</div>
											<div class="modal-footer">
												<button id="close" type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button id="editbutton${device.deviceId }" type="button"
													class="btn btn-primary">提交更改</button>
											</div>
											<script>
												$("#editbutton${device.deviceId }").click(
												function() {
													$.get("ActionServlet?stat=deviceEdit&deviceName="+$("#deviceName${device.deviceId }").val()+"&deviceType="+$("#deviceType${device.deviceId }").val()+"&gpio="+$("#gpio${device.deviceId }").val()+"&did="+${device.deviceId },function(data,status){
														if(data == "更改成功"){
															//$("#tdDeviceName${device.deviceId }").val($("#deviceName${device.deviceId }").val());
															//$("#tdDeviceType${device.deviceId }").val($("#deviceType${device.deviceId }").val());
															//$("#tdDeviceGPIO${device.deviceId }").val($("#gpio${device.deviceId }").val());
															history.go(0);
														}else{
															alert(data);
														}
													});
												});
											</script>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal -->
								</div>

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