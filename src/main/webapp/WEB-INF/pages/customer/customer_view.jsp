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
		<h1 class="section__title">Khách hàng</h1>
		<div class="section__box">
			<div class="section__nav">
				<form class="section__search" action="customer_admin" method="get">
					<input class="search__input" type="text"
						placeholder="nhập mặt hàng cần tìm kiếm ..."
						name="customer_search" value="${customer_model.getSearchValue() }"
						autofocus>
					<button class="search__btn" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</form>
				<a href="customer_admin-create" class="section__add-link"> <i
					class="fa-solid fa-plus"></i> Bổ sung
				</a>
			</div>
			<p class="section__notification">
				Có <strong>${customer_model.getRowCount() }</strong> khách hàng
				trong tổng số <strong>${customer_model.pageCount() }</strong> trang
			</p>
			<c:choose>
				<c:when
					test="${customer_model.data == null || customer_model.data.size() == 0}">
					<p>không có dữ liệu</p>
				</c:when>
				<c:otherwise>
					<table class="section__table">
						<thead class="table__header">
							<tr>
								<th>Tên khách hàng</th>
								<th>Tên giao dịch</th>
								<th>Địa chỉ</th>
								<th>Quốc gia</th>
								<th>Thành phố</th>
								<th>Mã bưu điện</th>
								<th>Email</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="table__body">
							<c:forEach var="i" items="${customer_model.data}">
								<tr>
									<td>${i.getCustomerName() }</td>
									<td>${i.getContactName() }</td>
									<td>${i.getAddress() }</td>
									<td>${i.getCountry() }</td>
									<td>${i.getCity() }</td>
									<td>${i.getPostalCode() }</td>
									<td>${i.getEmail() }</td>
									<td class="table_option"><a
										href="customer_admin-update?customerID=${i.getCustomerID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a
										href="customer_admin-delele?customerID=${i.getCustomerID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${customer_model.pageCount() > 1 }">

				<ul class="section__paging">
					<c:forEach var="i" begin="1" end="${customer_model.pageCount()}">
						<c:choose>
							<c:when test="${i == customer_model.getPage()  }">
								<li class="paging__active"><a
									href="customer_admin?customer_page=${i }&customer_search=${customer_model.getSearchValue()}"
									class="paging__link">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a
									href="customer_admin?customer_page=${i }&customer_search=${customer_model.getSearchValue()}"
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