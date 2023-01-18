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
		<h1 class="section__title">Loại sản phẩm</h1>
		<div class="section__box">
			<div class="section__nav">
				<form class="section__search" action="category_admin" method="get">
					<input class="search__input" type="text"
						placeholder="nhập mặt hàng cần tìm kiếm ..."
						name="category_search" value="${category_model.getSearchValue() }"
						autofocus>
					<button class="search__btn" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</form>
				<a href="category_admin-create" class="section__add-link"> <i
					class="fa-solid fa-plus"></i> Bổ sung
				</a>
			</div>
			<p class="section__notification">
				Có <strong>${category_model.getRowCount() }</strong> loại sản phẩm
				trong tổng số <strong>${category_model.pageCount() }</strong> trang
			</p>
			<c:choose>
				<c:when
					test="${category_model.data == null || category_model.data.size() == 0}">
					<p>không có dữ liệu</p>
				</c:when>
				<c:otherwise>
					<table class="section__table">
						<thead class="table__header">
							<tr>
								<th>Tên loại hàng</th>
								<th>Mô tả</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="table__body">
							<c:forEach var="i" items="${category_model.data}">
								<tr>
									<td>${i.getCategoryName()}</td>
									<td>${i.getDescription()}</td>
									<td class="table_option"><a href="category_admin-update?categoryID=${i.getCategoryID() }" class="table__edit"><i
											class="fa-solid fa-pen-to-square"></i></a> <a href="category_admin-delele?categoryID=${i.getCategoryID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${category_model.pageCount() > 1 }">

				<ul class="section__paging">
					<c:forEach var="i" begin="1" end="${category_model.pageCount()}">
						<c:choose>
							<c:when test="${i == category_model.getPage()  }">
								<li class="paging__active"><a
									href="category_admin?category_page=${i }&category_search=${category_model.getSearchValue()}"
									class="paging__link">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a
									href="category_admin?category_page=${i }&category_search=${category_model.getSearchValue()}"
									class="paging__link">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>

			</c:if>
		</div>


	</section>
	<!-- <footer>
		<div class="footer__dsec">
			<p class="dsec__license">Bản quyền © 2022</p>
			<a href="" class="dsec__name-shop--link">Shop</a>
			<p class="dsec__reserved">reserved.</p>
		</div>
		<div class="footer__version">
			<p class="version__name">Version</p>
			<p class="version__number">2.4.0</p>
		</div>
	</footer> -->
</body>
</html>