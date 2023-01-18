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
			<form action="product_admin-save_photo" method="post">
				<input type="hidden" name="photoID"
					value="${photoBean.getPhotoID() }"> <input type="hidden"
					name="productID" value="${photoBean.getProductID() }">
				<div class="form-group">
					<label class="control-label">Ảnh cá nhân<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control" id="Photo"
							name="Photo" type="text" value="${photoBean.getPhoto() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Mô tả<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="description" name="description"
							type="text" value="${photoBean.getDescription() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Thứ tự hiển thị<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="displayOrder" name="displayOrder"
							type="number" value="${photoBean.getDisplayOrder() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Ẩn ảnh<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="isHidden" name="isHidden"
							type="text" value="${photoBean.isHidden() }">
					</div>
				</div>

				<div class="form__err">
					<c:if test="${Photo_err != null || Photo_err != ''}">
						<span class="form__text-danger" data-valmsg-for="Photo_err"
							data-valmsg-replace="true">${Photo_err}</span>
					</c:if>
					<c:if test="${description_err != null || description_err != ''}">
						<span class="form__text-danger" data-valmsg-for="description_err"
							data-valmsg-replace="true">${description_err}</span>
					</c:if>
					<c:if test="${displayOrder_err != null || displayOrder_err != ''}">
						<span class="form__text-danger" data-valmsg-for="displayOrder_err"
							data-valmsg-replace="true">${displayOrder_err}</span>
					</c:if>
					<c:if test="${isHidden_err != null || isHidden_err != ''}">
						<span class="form__text-danger" data-valmsg-for="isHidden_err"
							data-valmsg-replace="true">${isHidden_err}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a
						href="product_admin-update?productID=${photoBean.getProductID()}"
						class="form__btn--back"> <i class="fa-solid fa-ban"></i> Quay
						lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>