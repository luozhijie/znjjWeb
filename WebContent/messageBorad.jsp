<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>留言</title>
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

			<!-- 留言板开始 -->
			<div class="widget-shadow chat-mdl-grid">
				<h4 class="title3">留言板</h4>
				<div class="scrollbar scrollbar1">

					<c:forEach items="${messageBoardList }" var="messageBoard">
						<c:if test="${messageBoard.uid != userObj.userId }">
							<div class="activity-row activity-row1 activity-right">
								<div class="col-xs-3 activity-img">
									<img src="user_icon/${messageBoard.iconName }"
										class="img-responsive" alt="">
								</div>
								<div class="col-xs-9 activity-img1">
									<div class="activity-desc-sub">
										<p>${messageBoard.content }</p>
										<span>${messageBoard.date }</span>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</c:if>

						<c:if test="${messageBoard.uid == userObj.userId }">
							<div class="activity-row activity-row1 activity-left">
								<div class="col-xs-9 activity-img2">
									<div class="activity-desc-sub1">
										<p>${messageBoard.content }</p>
										<span>${messageBoard.date }</span>
									</div>
								</div>
								<div class="col-xs-3 activity-img">
									<img src="user_icon/${messageBoard.iconName }"
										class="img-responsive" alt="">
								</div>
								<div class="clearfix"></div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="chat-bottom">
					<input id="addMessage" type="text" placeholder="留言" required="">
				</div>
			</div>
			<!-- 留言板结束 -->
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
		$("#addMessage").bind("keydown",function(e){
			var theEvent = e || window.event;    
			var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
			if (code == 13) {
				$.get("ActionServlet?stat=addMessage&content="+$("#addMessage").val()+"&uid="+${userObj.userId},function(data,status){
					if(data == "留言成功"){
						history.go(0);
					}else{
						alert(data);
					}
				});
			}
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