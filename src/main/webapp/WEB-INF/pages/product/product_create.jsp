<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo mới một sản phẩm.</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">Tạo mới một sản phẩm.</h1>
		<div class="section__box">
			<form action="product_admin-save" method="post">
				<input type="hidden" name="productID"
					value="${productBean.getProductID() }">
				<div class="form-group">
					<label class="control-label">Tên sản phẩm<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control" id="productName"
							name="productName" type="text"
							value="${productBean.getProductName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Đơn vị<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="productUnit" name="productUnit"
							type="text" value="${productBean.getUnit() }">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Giá<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="productPrice" name="productPrice"
							type="number" step="0.01" value="${productBean.getPrice() }">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Loại sản phẩm<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="categoryID" name="categoryID">
							<option value="">--Chọn Loại sản phẩm--</option>
							<c:forEach var="i" items="${listCategorys}">
								<c:choose>
									<c:when
										test="${  i.getCategoryID() == productBean.getCategoryID() }">
										<option value="${i.getCategoryID() }" selected="selected">${i.getCategoryName() }</option>
									</c:when>
									<c:otherwise>
										<option value="${i.getCategoryID() }">${i.getCategoryName() }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Nhà cung cấp<small
						class="text-danger"></small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="supplierID" name="supplierID">
							<option value="">--Chọn Nhà cung cấp--</option>
							<c:forEach var="i" items="${listSuppliers}">
								<c:choose>
									<c:when
										test="${ i.getSupplierID() == productBean.getSupplierID() }">
										<option value="${i.getSupplierID() }" selected="selected">${i.getSupplierName() }</option>
									</c:when>
									<c:otherwise>
										<option value="${i.getSupplierID() }">${i.getSupplierName() }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Ảnh<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="productPhoto" name="productPhoto"
							type="text" value="${productBean.getPhoto() }">
					</div>

				</div>
				<img alt="ảnh" src="">

				<!-- Lỗi -->
				<div class="form__err">
					<c:if test="${productName_err != null || productName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="productName_err"
							data-valmsg-replace="true">${productName_err}</span>
					</c:if>
					<c:if test="${productUnit_err != null || productUnit_err != ''}">
						<span class="form__text-danger" data-valmsg-for="productUnit_err"
							data-valmsg-replace="true">${productUnit_err}</span>
					</c:if>
					<c:if test="${productPrice_err != null || productPrice_err != ''}">
						<span class="form__text-danger" data-valmsg-for="productPrice_err"
							data-valmsg-replace="true">${productPrice_err}</span>
					</c:if>
					<c:if test="${categoryID_err != null || categoryID_err != ''}">
						<span class="form__text-danger" data-valmsg-for="categoryID_err"
							data-valmsg-replace="true">${categoryID_err}</span>
					</c:if>
					<c:if test="${productPhoto_err != null || productPhoto_err != ''}">
						<span class="form__text-danger" data-valmsg-for="productPhoto_err"
							data-valmsg-replace="true">${productPhoto_err}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="product_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>

</body>
</html>