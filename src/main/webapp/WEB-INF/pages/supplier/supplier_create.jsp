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
		<h1 class="section__title">${title_supplier }</h1>
		<div class="section__box">
			<form action="supplier_admin-save" method="post">

				<!-- Nhập dữ liệu -->
				<input type="hidden" name="supplierID"
					value="${supplierBean.getSupplierID() }">
				<div class="form-group">
					<label class="control-label">Tên nhà cung cấp<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="supplierName" name="supplierName" type="text"
							value="${supplierBean.getSupplierName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Tên giao dịch<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="supplierContact"
							name="supplierContact" type="text"
							value="${supplierBean.getContactName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Địa Chỉ<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="supplierAddress"
							name="supplierAddress" type="text"
							value="${supplierBean.getAddress() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Quốc gia<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="supplierCountry"
							name="supplierCountry">
							<option value="">--Chọn quốc gia--</option>
							<c:forEach var="i" items="${listCountry}">
								<c:choose>
									<c:when
										test="${ i.getCountryName() == supplierBean.getCountry() }">
										<option value="${i.getCountryName() }" selected="selected">${i.getCountryName() }</option>
									</c:when>
									<c:otherwise>
										<option value="${i.getCountryName() }">${i.getCountryName() }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<%-- <input class="form-control" id="supplierCountry"
							name="supplierCountry" type="text"
							value="${supplierBean.getCountry() }"> --%>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Thành phố<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="supplierCity" name="supplierCity"
							type="text" value="${supplierBean.getCity() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Số điện thoại<small
						class="text-danger"> * </small> :
					</label>
					<div class="control-input">
						<input class="form-control" id="supplierPhone"
							name="supplierPhone" type="number"
							value="${supplierBean.getPhone() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Mã Bưu chính<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="supplierPostalCode"
							name="supplierPostalCode" type="text"
							value="${supplierBean.getPostalCode() }">
					</div>
				</div>

				<!-- Báo lỗi -->
				<div class="form__err">
					<c:if test="${supplierName_err != null || supplierName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="supplierName_err"
							data-valmsg-replace="true">${supplierName_err}</span>
					</c:if>
					<c:if
						test="${supplierContact_err != null || supplierContact_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="supplierContact_err" data-valmsg-replace="true">${supplierContact_err}</span>
					</c:if>
					<c:if
						test="${supplierAddress_err != null || supplierAddress_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="supplierAddress_err" data-valmsg-replace="true">${supplierAddress_err}</span>
					</c:if>
					<c:if
						test="${supplierCountry_err != null || supplierCountry_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="supplierCountry_err" data-valmsg-replace="true">${supplierCountry_err}</span>
					</c:if>
					<c:if test="${supplierCity_err != null || supplierCity_err != ''}">
						<span class="form__text-danger" data-valmsg-for="supplierCity_err"
							data-valmsg-replace="true">${supplierCity_err}</span>
					</c:if>
					<c:if
						test="${supplierPostalCode_err != null || supplierPostalCode_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="supplierPostalCode_err"
							data-valmsg-replace="true">${supplierPostalCode_err}</span>
					</c:if>
					<c:if
						test="${supplierPhone_err != null || supplierPhone_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="supplierPostalCode_err"
							data-valmsg-replace="true">${supplierPhone_err}</span>
					</c:if>
				</div>

				<!-- chức năng -->
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="supplier_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>