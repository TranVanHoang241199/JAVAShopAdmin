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
		<h1 class="section__title">Xóa Nhân viên</h1>
		<div class="section__box">
			<form action="employee_admin-delele" method="post">
				<input type="hidden" value="${employeeBean.getEmployeeID() }"
					name="employeeID">
				<div class="section__item">
					<label class="section-item__title">họ và tên :</label>
					<div class="section__dsec">
						<p>${employeeBean.getLastName()} ${employeeBean.getFirstName()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">ngày sinh :</label>
					<div class="section__dsec">
						<p class="section__txt">${employeeBean.getBirthDate()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Email :</label>
					<div class="section__dsec">
						<p class="section__txt">${employeeBean.getEmail()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Mô tả :</label>
					<div class="section__dsec">
						<p class="section__txt">${employeeBean.getNotes()}</p>
					</div>
				</div>
				<div class="section__item">
					<label class="section-item__title">Ảnh :</label>
					<div class="section__dsec">
						<img alt="" src="<c:url value='${i.getPhoto() }'/>">
					</div>
				</div>
				<!-- tùy chọn -->
				<div class="section__option">
					<c:choose>
						<c:when test="${ employeeBo.inUsed(employeeBean.getEmployeeID())}">
							<p class="text-danger">không được xóa loại sẩn phẩm này.</p>
						</c:when>
						<c:otherwise>
							<button type="submit" class="form__btn--delete">
								<i class="fa-solid fa-trash-can"></i> Xóa
							</button>
						</c:otherwise>
					</c:choose>
					<a href="employee_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>