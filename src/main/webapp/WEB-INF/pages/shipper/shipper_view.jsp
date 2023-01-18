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
		<h1 class="section__title">Người giao hàng</h1>
		<div class="section__box">
			<div class="section__nav">
				<form class="section__search" action="shipper_admin" method="get">
					<input class="search__input" type="text"
						placeholder="nhập mặt hàng cần tìm kiếm ..."
						name="shipper_search" value="${shipper_model.getSearchValue() }"
						autofocus>
					<button class="search__btn" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</form>
				<a href="shipper_admin-create" class="section__add-link"> <i
					class="fa-solid fa-plus"></i> Bổ sung
				</a>
			</div>
			<p class="section__notification">
				Có <strong>${shipper_model.getRowCount() }</strong> khách hàng
				trong tổng số <strong>${shipper_model.pageCount() }</strong> trang
			</p>
			<c:choose>
				<c:when
					test="${shipper_model.data == null || shipper_model.data.size() == 0}">
					<p>không có dữ liệu</p>
				</c:when>
				<c:otherwise>
					<table class="section__table">
						<thead class="table__header">
							<tr>
								<th>Tên người giao hàng</th>
								<th>Số điện thoại</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="table__body">
							<c:forEach var="i" items="${shipper_model.data}">
								<tr>
									<td>${i.getShipperName() }</td>
									<td>${i.getPhone() }</td>
									<td class="table_option"><a
										href="shipper_admin-update?shipperID=${i.getShipperID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a
										href="shipper_admin-delele?shipperID=${i.getShipperID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${shipper_model.pageCount() > 1 }">

				<ul class="section__paging">
					<c:forEach var="i" begin="1" end="${shipper_model.pageCount()}">
						<c:choose>
							<c:when test="${i == shipper_model.getPage()  }">
								<li class="paging__active"><a
									href="shipper_admin?shipper_page=${i }&shipper_search=${shipper_model.getSearchValue()}"
									class="paging__link">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a
									href="shipper_admin?shipper_page=${i }&shipper_search=${shipper_model.getSearchValue()}"
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