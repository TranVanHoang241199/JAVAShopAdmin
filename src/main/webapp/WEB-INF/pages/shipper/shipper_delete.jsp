<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xóa khách hàng</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/delete.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">Xóa khách hàng</h1>
		<div class="section__box">
			<form action="shipper_admin-delele" method="post">
				<input type="hidden" value="${shipperBean.getShipperID() }"
					name="shipperID">
				<div class="section__item">
					<label class="section-item__title">Tên người giao hàng :</label>
					<div class="section__dsec">
						<p>${shipperBean.getShipperName()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Số điện thoại :</label>
					<div class="section__dsec">
						<p class="section__txt">${shipperBean.getPhone()}</p>
					</div>
				</div>

				<!-- tùy chọn -->
				<div class="section__option">
					<c:choose>
						<c:when test="${ shipperBo.inUsed(shipperBean.getShipperID())}">
							<p class="text-danger">không được xóa loại sẩn phẩm này.</p>
						</c:when>
						<c:otherwise>
							<button type="submit" class="form__btn--delete">
								<i class="fa-solid fa-trash-can"></i> Xóa
							</button>
						</c:otherwise>
					</c:choose>
					<a href="shipper_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>