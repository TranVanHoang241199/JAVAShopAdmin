<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title_attribute }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">${title_attribute }</h1>
		<div class="section__box">
			<form action="product_admin-save_attribute" method="post">
				<input type="hidden" name="attributeID"
					value="${attributeBean.getAttributeID() }"> <input
					type="hidden" name="productID"
					value="${attributeBean.getProductID() }">
				<div class="form-group">
					<label class="control-label">Tên thuật tính<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="attributeName" name="attributeName" type="text"
							value="${attributeBean.getAttributeName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Giá trị thuộc tính<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="attributeValue"
							name="attributeValue" type="text"
							value="${attributeBean.getAttributeValue() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Thứ tự hiển thi<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="displayOrder" name="displayOrder"
							type="text" value="${attributeBean.getDisplayOrder() }">
					</div>
				</div>
				<div class="form__err">
					<c:if
						test="${attributeName_err != null || attributeName_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="attributeName_err" data-valmsg-replace="true">${attributeName_err}</span>
					</c:if>
					<c:if
						test="${attributeValue_err != null || attributeValue_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="attributeValue_err" data-valmsg-replace="true">${attributeValue_err}</span>
					</c:if>
					<c:if test="${displayOrder_err != null || displayOrder_err != ''}">
						<span class="form__text-danger" data-valmsg-for="displayOrder_err"
							data-valmsg-replace="true">${displayOrder_err}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a
						href="product_admin-update?productID=${attributeBean.getProductID()}"
						class="form__btn--back"> <i class="fa-solid fa-ban"></i> Quay
						lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>