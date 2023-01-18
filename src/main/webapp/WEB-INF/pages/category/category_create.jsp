<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title_category }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">${title_category }</h1>
		<div class="section__box">
			<form action="category_admin-save" method="post">
				<input type="hidden" name="categoryID"
					value="${categoryBean.getCategoryID() }">
				<div class="form-group">
					<label class="control-label">Tên loại hàng<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="categoryName" name="categoryName" type="text"
							value="${categoryBean.getCategoryName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Mô tả<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control"
							id="categoryDescription" name="categoryDescription" type="text"
							value="${categoryBean.getDescription() }">
					</div>

				</div>
				<div class="form__err">
					<c:if test="${categoryName_err != null || categoryName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="categoryName_err"
							data-valmsg-replace="true">${categoryName_err}</span>
					</c:if>
					<c:if
						test="${categoryDescription_err != null || categoryDescription_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="categoryDescription_err"
							data-valmsg-replace="true">${categoryDescription_err}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="category_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>