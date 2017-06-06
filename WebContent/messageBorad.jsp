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
				
				
				
				
				
				
					<div class="activity-row activity-row1 activity-right">
						<div class="col-xs-3 activity-img">
							<img src="images/1.png" class="img-responsive" alt="">
						</div>
						<div class="col-xs-9 activity-img1">
							<div class="activity-desc-sub">
								<p>你好</p>
								<span>10:00 PM</span>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="activity-row activity-row1 activity-left">
						<div class="col-xs-9 activity-img2">
							<div class="activity-desc-sub1">
								<p>What about our next meeting?</p>
								<span class="right">9:55 PM</span>
							</div>
						</div>
						<div class="col-xs-3 activity-img">
							<img src="images/3.png" class="img-responsive" alt="">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="activity-row activity-row1 activity-right">
						<div class="col-xs-3 activity-img">
							<img src="images/1.png" class="img-responsive" alt="">
						</div>
						<div class="col-xs-9 activity-img1">
							<div class="activity-desc-sub">
								<p>Consectetur adipisicing elit. Atque ea mollitia pariatur
									porro quae se</p>
								<span>9:52 PM</span>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="activity-row activity-row1 activity-left">
						<div class="col-xs-9 activity-img2">
							<div class="activity-desc-sub1">
								<p>Quae sed sequi sint tenetur Atque ea mollitia pariatu</p>
								<span class="right">8:00 PM</span>
							</div>
						</div>
						<div class="col-xs-3 activity-img">
							<img src="images/3.png" class="img-responsive" alt="">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="activity-row activity-row1 activity-left">
						<div class="col-xs-9 activity-img2">
							<div class="activity-desc-sub1">
								<p>Quae sed sequi sint tenetur Atque ea mollitia pariatu</p>
								<span class="right">7:00 PM</span>
							</div>
						</div>
						<div class="col-xs-3 activity-img">
							<img src="images/2.png" class="img-responsive" alt="">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="activity-row activity-row1 activity-right">
						<div class="col-xs-3 activity-img">
							<img src="images/1.png" class="img-responsive" alt="">
						</div>
						<div class="col-xs-9 activity-img1">
							<div class="activity-desc-sub">
								<p>Consectetur adipisicing elit. Atque ea mollitia pariatur
									porro quae se</p>
								<span>6:52 PM</span>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="chat-bottom">
					<form>
						<input type="text" placeholder="Reply" required="">
					</form>
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