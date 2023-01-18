<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chỉnh sửa sản phẩm</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/productedit.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">Chỉnh sửa sản phẩm</h1>
		<div class="section__box">
			<form action="product_admin-save" method="post">
				<input type="hidden" name="productID"
					value="${updateModel.getData().getProductID() }">
				<div class="form-group">
					<label class="control-label">Tên sản phẩm<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="productName" name="productName" type="text"
							value="${updateModel.getData().getProductName() }">
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label">Đơn vị<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control"
							id="productUnit" name="productUnit" type="text"
							value="${updateModel.getData().getUnit() }">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Giá<small class="text-danger">
							* </small>:
					</label>
					<div class="control-input">
						<input class="form-control"
							id="productPrice" name="productPrice" type="number" step="0.01"
							value="${updateModel.getData().getPrice() }">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label">Loại sản phẩm<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="categoryID"
							name="categoryID">
							<option value="">--Chọn Loại sản phẩm--</option>
							<c:forEach var="i" items="${listCategorys}">
								<c:choose>
									<c:when
										test="${  i.getCategoryID() == updateModel.getData().getCategoryID() }">
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
						class="text-danger"> </small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="supplierID"
							name="supplierID">
							<option value="">--Chọn Nhà cung cấp--</option>
							<c:forEach var="i" items="${listSuppliers}">
								<c:choose>
									<c:when
										test="${ i.getSupplierID() == updateModel.getData().getSupplierID() }">
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
						<input class="form-control"
							id="productPhoto" name="productPhoto" type="text" value="${updateModel.getData().getPhoto() }">
					</div>

				</div>
				<img alt="ảnh" src="">
				<div class="form__err">
					<c:if test="${productName_err != null || productName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="productName_err"
							data-valmsg-replace="true">${productName_err}</span>
					</c:if>
					<c:if
						test="${productUnit != null || productUnit != ''}">
						<span class="form__text-danger"
							data-valmsg-for="productUnit"
							data-valmsg-replace="true">${productUnit}</span>
					</c:if>
					<c:if
						test="${productPrice_err != null || productPrice_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="productPrice_err"
							data-valmsg-replace="true">${productPrice_err}</span>
					</c:if>
					<c:if
						test="${productPhoto_err != null || productPhoto_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="productPhoto_err"
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
		<div class="section__box section__photo">
			<h3>Thử viện ảnh</h3>

			<table class="section__table">
				<thead class="table__header">
					<tr>
						<th>Ảnh</th>
						<th>Mô tả/tiêu đề</th>
						<th>Thứ tự hiển thị</th>
						<th>Ẩn ảnh</th>
						<th><a href="product_admin-photo?method=add&productID=${updateModel.getData().getProductID() }" class="btn__add"><i class="fa-solid fa-circle-plus"></i></a></th>
					</tr>
				</thead>
				<c:choose>
					<c:when
						test="${updateModel.getDataOfPhotos() == null || updateModel.getDataOfPhotos().size() == 0}">
						<div>

							<p>không có dữ liệu</p>
						</div>
					</c:when>
					<c:otherwise>
						<tbody class="table__body">
							<c:forEach var="i" items="${updateModel.getDataOfPhotos()}">
								<tr>
									<td>${i.getPhoto() }</td>
									<td>${i.getDescription() }</td>
									<td>${i.getDisplayOrder() }</td>
									<td>${i.isHidden() }</td>
									<td class="table_option"><a
										href="product_admin-photo?method=edit&photoID=${i.getPhotoID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a href="product_admin-photo?method=delete&productID=${i.getProductID() }&photoID=${i.getPhotoID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>

					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<div class="section__box section__af">
			<h3>chi tiết mặt hàng</h3>

			<table class="section__table">
				<thead class="table__header">
					<tr>
						<th>Tên thuộc tính</th>
						<th>Gía trị thuộc tính</th>
						<th>Thứ tự hiển thị</th>
						<th><a href="product_admin-attribute?method=add&productID=${updateModel.getData().getProductID() }" class="btn__add"><i class="fa-solid fa-circle-plus"></i></a></th>
					</tr>
				</thead>
				<c:choose>
					<c:when
						test="${updateModel.getDataOfAttributes() == null || updateModel.getDataOfAttributes().size() == 0}">
						<p>không có dữ liệu</p>
					</c:when>
					<c:otherwise>
						<tbody class="table__body">
							<c:forEach var="i" items="${updateModel.getDataOfAttributes()}">
								<tr>
									<td>${i.getAttributeName() }</td>
									<td>${i.getAttributeValue() }</td>
									<td>${i.getDisplayOrder() }</td>
									<td class="table_option"><a
										href="product_admin-attribute?method=edit&attributeID=${i.getAttributeID() }"
										class="table__edit"><i class="fa-solid fa-pen-to-square"></i></a>
										<a href="product_admin-attribute?method=delete&productID=${i.getProductID() }&attributeID=${i.getAttributeID() }"
										class="table__delete"><i class="fa-solid fa-trash-can"></i></a></td>
								</tr>
							</c:forEach>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>