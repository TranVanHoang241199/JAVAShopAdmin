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
		<h1 class="section__title">Nhân viên</h1>
		<div class="section__box">
			<div class="section__nav">
				<form class="section__search" action="employee_admin" method="get">
					<input class="search__input" type="text"
						placeholder="nhập mặt hàng cần tìm kiếm ..."
						name="employee_search" value="${employee_model.getSearchValue() }"
						autofocus>
					<button class="search__btn" type="submit">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>
				</form>
				<a href="employee_admin-create" class="section__add-link"> <i
					class="fa-solid fa-plus"></i> Bổ sung
				</a>
			</div>
			<p class="section__notification">
				Có <strong>${employee_model.getRowCount() }</strong> nhân viên trong
				tổng số <strong>${employee_model.pageCount() }</strong> trang
			</p>
			<c:choose>
				<c:when
					test="${employee_model.data == null || employee_model.data.size() == 0}">
					<p>không có dữ liệu</p>
				</c:when>
				<c:otherwise>
					<table class="section__table">
						<thead class="table__header">
							<tr>
								<th>Ảnh</th>
								<th>Họ và tên</th>
								<th>Ngày sinh</th>
								<th>Email</th>
								<th>Mô tả</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="table__body">
							<c:forEach var="i" items="${employee_model.data}">
								<tr>
									<td><img alt="img_employee" src="<c:url value='${i.getPhoto() }'/>"> </td>
									<td><p> ${i.getLastName() } ${i.getFirstName() }</p></td>
									<td>${i.getBirthDate() }</td>
									<td>${i.getEmail() }</td>
									<td>${i.getNotes() }</td>
									<td class="table_option"><a
										href="employee_admin-update?employeeID=${i.getEmployeeID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a
										href="employee_admin-delele?employeeID=${i.getEmployeeID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
			<c:if test="${employee_model.pageCount() > 1 }">

				<ul class="section__paging">
					<c:forEach var="i" begin="1" end="${employee_model.pageCount()}">
						<c:choose>
							<c:when test="${i == employee_model.getPage()  }">
								<li class="paging__active"><a
									href="employee_admin?employee_page=${i }&employee_search=${employee_model.getSearchValue()}"
									class="paging__link">${i }</a></li>
							</c:when>
							<c:otherwise>
								<li class=""><a
									href="employee_admin?employee_page=${i }&employee_search=${employee_model.getSearchValue()}"
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