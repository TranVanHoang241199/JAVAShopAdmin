<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang Chủ</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
</head>
<body>
	<section>
		<h1>đây là trang chủ</h1>
	</section>
	<footer> 
		<div class="footer__dsec">
			<p class="dsec__license">Bản quyền © 2022</p>
			<a href="" class="dsec__name-shop--link">Shop</a>
			<p class="dsec__reserved">reserved.</p>
		</div>
		<div class="footer__version">
		<p class="version__name">Version</p>
		<p class="version__number">2.4.0</p>
		</div>
	</footer>
</body>
</html>