<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title_employee }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">${title_employee }</h1>
		<div class="section__box">
			<form action="employee_admin-save" method="post">
				<input type="hidden" name="employeeID"
					value="${employeeBean.getEmployeeID() }">
				<div class="form-group">
					<label class="control-label">Họ<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="employeeName" name="employeeLastName" type="text"
							value="${employeeBean.getLastName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Tên<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="employeeName"
							name="employeeFirstName" type="text"
							value="${employeeBean.getFirstName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Ngày sinh<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="employeeDescription"
							name="employeeBirthDate" type="date"
							value="${employeeBean.getBirthDate() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Email<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="employeeDescription"
							name="employeeEmail" type="email"
							value="${employeeBean.getEmail() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Mô tả : </label>
					<div class="control-input">
						<input class="form-control" id="employeeDescription"
							name="employeeNotes" type="text"
							value="${employeeBean.getNotes() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Ảnh<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="employeeDescription"
							name="employeePhoto" type="file"
							value="${employeeBean.getPhoto() }">
					</div>
				</div>

				<div class="form__err">
					<c:if
						test="${employeeLastName_err != null || employeeLastName_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="employeeLastName_err" data-valmsg-replace="true">${employeeLastName_err}</span>
					</c:if>
					<c:if
						test="${employeeFirstName_err != null || employeeFirstName_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="employeeFirstName_err"
							data-valmsg-replace="true">${employeeFirstName_err}</span>
					</c:if>
					<c:if
						test="${employeeBirthDate != null || employeeBirthDate != ''}">
						<span class="form__text-danger"
							data-valmsg-for="employeeBirthDate" data-valmsg-replace="true">${employeeBirthDate}</span>
					</c:if>
					<c:if
						test="${employeeEmail_err != null || employeeEmail_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="employeeEmail_err" data-valmsg-replace="true">${employeeEmail_err}</span>
					</c:if>
					<c:if
						test="${employeePhoto_err != null || employeePhoto_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="employeePhoto_err" data-valmsg-replace="true">${employeePhoto_err}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="employee_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>