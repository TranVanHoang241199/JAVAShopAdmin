<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loại sản phẩm</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/category.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/content.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">Sản Phẩm</h1>
		<div class="section__box">
			<div class="section__nav">
				<form class="section__search" action="product_admin" method="get">
					<!--begin: Loại hàng -->
					<select class="select__option" id="productCategory"
						name="productCategory">
						<option value="">--Loại hàng--</option>
						<c:forEach var="i" items="${listCategorys}">
							<c:choose>
								<c:when
									test="${ i.getCategoryID() == product_model.getCategoryID() }">
									<option value="${i.getCategoryID() }" selected="selected">${i.getCategoryName() }</option>
								</c:when>
								<c:otherwise>
									<option value="${i.getCategoryID() }">${i.getCategoryName() }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<!-- end: Loại hàng -->

					<!-- begin: Nhà cung cấp -->
					<select class="select__option" id="productsupplier"
						name="productsupplier">
						<option value="">--Nhà cung cấp--</option>
						<c:forEach var="i" items="${listSuppliers}">
							<c:choose>
								<c:when
									test="${ i.getSupplierID() == product_model.getSupplierID() }">
									<option value="${i.getSupplierID() }" selected="selected">${i.getSupplierName() }</option>
								</c:when>
								<c:otherwise>
									<option value="${i.getSupplierID() }">${i.getSupplierName() }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
					<!-- end: nhà cung cấp -->
					<input class="search__input" type="text"
						placeholder="nhập mặt hàng cần tìm kiếm ..." name="product_search"
						value="${product_model.getSearchValue() }" autofocus>
					<button class="search__btn" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</form>
				<a href="product_admin-create" class="section__add-link"> <i
					class="fa-solid fa-plus"></i> Bổ sung
				</a>
			</div>
			<p class="section__notification">
				Có <strong>${product_model.getRowCount() }</strong> khách hàng trong
				tổng số <strong>${product_model.pageCount() }</strong> trang
			</p>
			<c:choose>
				<c:when
					test="${product_model.data == null || product_model.data.size() == 0}">
					<p>không có dữ liệu</p>
				</c:when>
				<c:otherwise>
					<table class="section__table">
						<thead class="table__header">
							<tr>
								<th>Ảnh</th>
								<th>Tên mặt hàng</th>
								<th>Đơn vị giá</th>
								<th>Giá</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="table__body">
							<c:forEach var="i" items="${product_model.data}">
								<tr>
									<td>${i.getPhoto() }</td>
									<td>${i.getProductName() }</td>
									<td>${i.getUnit() }</td>
									<td>${i.getPrice() }</td>
									<td class="table_option"><a
										href="product_admin-update?productID=${i.getProductID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a href="product_admin-delele?productID=${i.getProductID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${product_model.pageCount() > 1 }">

				<ul class="section__paging">
					<c:forEach var="i" begin="1" end="${product_model.pageCount()}">
						<c:choose>
							<c:when test="${i == product_model.getPage()  }">
								<li class="paging__active"><a
									href="product_admin?product_page=${i }&productCategory=${product_model.getCategoryID()}&productsuppl=${product_model.getSupplierID()}&product_search=${product_model.getSearchValue()}"
									class="paging__link">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a
									href="product_admin?product_page=${i }&productCategory=${product_model.getCategoryID()}&productsuppl=${product_model.getSupplierID()}&product_search=${product_model.getSearchValue()}"
									class="paging__link">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</section>
</body>
</html>