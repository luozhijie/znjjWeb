<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>登录界面</title>
<%@ include file="headinclude.html"%>
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<jsp:include page="navetion.html"></jsp:include>
		<!--//end-search-box-->
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	</div>
	<!-- //header-ends -->
	<!-- main content start-->
	<div id="page-wrapper">
		<div class="main-page login-page ">
			<h3 class="title1">登录</h3>
			<div class="widget-shadow">
				<div class="login-top">
					<h4>
						欢迎来到家庭管理<br> 没有账号？ <a href="signup.html"> 注册 »</a>
					</h4>
				</div>
				<div class="login-body">
					<form>
						<input type="text" class="user" name="email" placeholder="请输入账号"
							required=""> <input type="password" name="password"
							class="lock" placeholder="请输入密码"> <input type="submit"
							name="Sign In" value="登录">
						<div class="forgot-grid">
							<label class="checkbox"><input type="checkbox"
								name="checkbox" checked=""><i></i>记住密码</label>
							<div class="forgot">
								<a href="#">忘记密码？</a>
							</div>
							<div class="clearfix"></div>
						</div>
					</form>
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