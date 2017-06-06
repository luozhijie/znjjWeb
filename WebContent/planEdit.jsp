<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改计划任务</title>
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
								<tr>
									<td>${plan.pid }</td>
									<td>${plan.pName }</td>
									<td>${plan.pTime }</td>
									<td>${plan.pStat eq 0 ? "关":"开" }</td>
									<td>${plan.pIsOpen eq 0 ? "未激活" : "正在监听" }</td>
									<td>
										<button class="btn btn-primary btn-sm" data-toggle="modal"
											data-target="#edit${plan.pid }">修改</button>
									</td>
								</tr>
								<!-- 模态框 -->
								<div class="modal fade" id="edit${plan.pid }" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">${plan.pName }</h4>
											</div>
											<div class="modal-body">
												<div class="form-three widget-shadow">
													<div class="form-horizontal">
														<div class="form-group">
															<label for="focusedinput" class="col-sm-2 control-label">计划名称：</label>
															<div class="col-sm-8">
																<input id="planName${plan.pid }" type="text"
																	class="form-control1" id="focusedinput"
																	placeholder="请输入计划任务名称（自取名）" value="${plan.pName }">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">计划任务执行时间</label>
															<div class="col-sm-2">
																<select id="hour${plan.pid }" multiple=""
																	class="form-control1">
																	<c:forEach begin="0" end="23" var="hour">
																		<c:if test="${fn:substring(plan.pTime,0,2) == hour }">
																			<option selected="selected" value="${hour }">${hour }</option>
																		</c:if>
																		<c:if test="${fn:substring(plan.pTime,0,2) != hour }">
																			<option value="${hour }">${hour }</option>
																		</c:if>
																	</c:forEach>
																</select>
															</div>
															<div class="col-sm-2">
																<select id="min${plan.pid }" multiple=""
																	class="form-control1">
																	<c:forEach begin="0" end="59" var="min">
																		<c:if test="${fn:substring(plan.pTime,3,5) == min }">
																			<option selected="selected" value="${min }">${min }</option>
																		</c:if>
																		<c:if test="${fn:substring(plan.pTime,3,5) != min }">
																			<option value="${min }">${min }</option>
																		</c:if>

																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">计划任务对应设备/情景模式</label>
															<div class="col-sm-8">
																<select id="deviceIdOrProfile${plan.pid }" multiple=""
																	class="form-control1">
																	<c:forEach items="${deviceList }" var="device">
																		<c:set var="tag">1,${device.deviceId }</c:set>
																		<c:if test="${tag == plan.deviceIdOrProfile}">
																			<option selected="selected"
																				value="1,${device.deviceId }">${device.deviceName }</option>
																		</c:if>
																		<c:if test="${tag != plan.deviceIdOrProfile}">
																			<option value="1,${device.deviceId }">${device.deviceName }</option>
																		</c:if>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">计划任务对应设备/情景模式</label>
															<div class="col-sm-8">
																<select id="pStat${plan.pid }" multiple=""
																	class="form-control1">
																	<c:if test="${plan.pStat == 1 }">
																		<option selected="selected" value="1">开</option>
																	</c:if>
																	<c:if test="${plan.pStat != 1 }">
																		<option value="1">开</option>
																	</c:if>
																	<c:if test="${plan.pStat == 0 }">
																		<option selected="selected" value="0">关</option>
																	</c:if>
																	<c:if test="${plan.pStat != 0 }">
																		<option value="0">关</option>
																	</c:if>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button id="close" type="button" class="btn btn-default"
													data-dismiss="modal">关闭</button>
												<button id="editbutton${plan.pid }" type="button"
													class="btn btn-primary">提交更改</button>
											</div>
											<script>
												$("#editbutton${plan.pid }")
														.click(
																function() {
																	$
																			.get(
																					"ActionServlet?stat=planEdit&pid=${plan.pid }&planName="
																							+ $(
																									"#planName${plan.pid }")
																									.val()
																							+ "&hour="
																							+ $(
																									"#hour${plan.pid }")
																									.val()
																							+ "&min="
																							+ $(
																									"#min${plan.pid }")
																									.val()
																							+ "&deviceIdOrProfile="
																							+ $(
																									"#deviceIdOrProfile${plan.pid }")
																									.val()
																							+ "&pStat="
																							+ $(
																									"#pStat${plan.pid }")
																									.val(),
																					function(
																							data,
																							status) {
																						if (data == "修改成功") {
																							history
																									.go(0);
																						} else {
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