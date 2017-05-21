<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>注册</title>
<%@ include file="headinclude.html"%>
<script type="text/javascript">
	function register() {
		var username = $("#username").val();
		var pwd = $("#pwd").val();
		var pwdc = $("#pwdcheck").val();
		if (username == '') {
			alert("用户名不能为空");
			return;
		}
		if (pwd == '') {
			alert("密码不能为空");
			return;
		}
		if (pwdc == '') {
			alert("确认密码不能为空");
			return;
		}
		if (pwd !== pwdc) {
			alert("两次密码输入不一致");
			return;
		}
		$.post("ActionServlet?stat=register", {
			username : username,
			password : pwd
		}, function(data) {
			alert(data);
		});
	}
</script>
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<jsp:include page="navetion.html"></jsp:include>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	<!-- //header-ends -->
	<!-- main content start-->
	<div id="page-wrapper">
		<div class="main-page signup-page">
			<h3 class="title1">注册</h3>
			<p class="creating">欢迎注册</p>
			<div class="sign-up-row widget-shadow">
				<h5>用户信息</h5>
				<div class="sign-u">
					<div class="sign-up1">
						<h4>用户名:</h4>
					</div>
					<div class="sign-up2">
						<input type="text" id="username" name="username" required>
					</div>
					<div class="clearfix"></div>
				</div>
				<h6>登录验证:</h6>
				<div class="sign-u">
					<div class="sign-up1">
						<h4>密码:</h4>
					</div>
					<div class="sign-up2">
						<input type="password" id="pwd" name="pwd" required>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="sign-u">
					<div class="sign-up1">
						<h4>确认密码:</h4>
					</div>
					<div class="sign-up2">
						<input type="password" id="pwdcheck" name="pwdcheck" required>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="sub_home">
					<input onclick="return register();" name="submit" type="submit"
						value=" 提交 ">
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--footer-->
	<%@ include file="footinclude.html"%>
	<!--//footer-->
	</div>
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