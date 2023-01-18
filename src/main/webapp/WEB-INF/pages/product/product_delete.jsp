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
		<h1 class="section__title">Xóa sản phẩm</h1>
		<div class="section__box">
			<form action="product_admin-delele" method="post">
				<input type="hidden" value="${productBean.getProductID() }"
					name="productID">
				<div class="section__item">
					<label class="section-item__title">Tên mặt hàng :</label>
					<div class="section__dsec">
						<p>${productBean.getProductName()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Đơn vị :</label>
					<div class="section__dsec">
						<p class="section__txt">${productBean.getUnit()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Giá :</label>
					<div class="section__dsec">
						<p class="section__txt">${productBean.getPrice()}</p>
					</div>
				</div>
				
				<div class="section__item">
					<label class="section-item__title">Ảnh :</label>
					<div class="section__dsec">
						<p class="section__txt">${productBean.getPhoto()}</p>
					</div>
				</div>

				<!-- tùy chọn -->
				<div class="section__option">
					<c:choose>
						<c:when test="${ productBo.inUsed(productBean.getProductID())}">
							<p class="text-danger">không được xóa loại sẩn phẩm này.</p>
						</c:when>
						<c:otherwise>
							<button type="submit" class="form__btn--delete">
								<i class="fa-solid fa-trash-can"></i> Xóa
							</button>
						</c:otherwise>
					</c:choose>
					<a href="category_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>