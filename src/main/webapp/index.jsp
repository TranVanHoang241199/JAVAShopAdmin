<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Đăng Nhập</title>
	<!-- link css -->
	<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/login.css'/>">
</head>
<body>
	<header>
		<div class="header__logo">
			<a href="" class="logo__link">
				<img class="logo__img" alt="" src="<c:url value='resources/img/login/logo_lagi_8_resize.jfif'/>">
			</a>
			<p class="logo__txt">Đăng Nhập</p>		
		</div>

		<a href="" class="header__help">Bạn cần giúp đỡ?</a>
	</header>
	<section>
		<form action="login_admin" method="post">
			<div class="login">
				<p class="login__title">Đăng Nhập</p>
				<input class="login__email" type="text" placeholder="email / số điện thoại" name="admin_email" required autofocus="autofocus">
				<input class="login__password" type="password" placeholder="mật khẩu" name="admin_password" required>
				<button class="login__btn" type="submit">Đăng Nhập</button>
				<div class="login__help">
					<a href="" class="login__change-pass">Quên Mật Khẩu</a>
					<a href="home_admin" class="login__home">Quay Lại</a>
				</div>
				<p class="login__crossbar">----------------- Hoặc ---------------</p>
				<div class="login__register">
					<p class="login__txt">Bạn mới biết đến tôi?</p>
					<a href="" class="login__link">Đăng Ký</a>
				</div>
			</div>
		</form>
	</section>
	<footer>
		
	</footer>
</body>
</html>