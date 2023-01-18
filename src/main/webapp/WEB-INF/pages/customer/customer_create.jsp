<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title_customer }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">${title_customer }</h1>
		<div class="section__box">
			<form action="customer_admin-save" method="post">

				<!-- Nhập dữ liệu -->
				<input type="hidden" name="customerID"
					value="${customerBean.getCustomerID() }">
				<div class="form-group">
					<label class="control-label">Tên loại hàng<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control"
							id="customerName" name="customerName" type="text"
							value="${customerBean.getCustomerName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Tên Khách hàng<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="customerContact"
							name="customerContact" type="text"
							value="${customerBean.getContactName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Địa Chỉ<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="customerAddress"
							name="customerAddress" type="text"
							value="${customerBean.getAddress() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Quốc gia<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<select class="select__option" id="customerCountry"
							name="customerCountry">
							<option value="">--Chọn quốc gia--</option>
							<c:forEach var="i" items="${listCountry}">
								<c:choose>
									<c:when
										test="${ i.getCountryName() == customerBean.getCountry() }">
										<option value="${i.getCountryName() }" selected="selected">${i.getCountryName() }</option>
									</c:when>
									<c:otherwise>
										<option value="${i.getCountryName() }">${i.getCountryName() }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<%-- <input class="form-control" id="customerCountry"
							name="customerCountry" type="text"
							value="${customerBean.getCountry() }"> --%>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Thành phố<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="customerCity" name="customerCity"
							type="text" value="${customerBean.getCity() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Mã Bưu chính<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control" id="customerPostalCode"
							name="customerPostalCode" type="text"
							value="${customerBean.getPostalCode() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">Email : </label>
					<div class="control-input">
						<input class="form-control" id="customerEmail"
							name="customerEmail" type="email"
							value="${customerBean.getEmail() }">
					</div>
				</div>

				<!-- Báo lỗi -->
				<div class="form__err">
					<c:if test="${customerName_err != null || customerName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="customerName_err"
							data-valmsg-replace="true">${customerName_err}</span>
					</c:if>
					<c:if
						test="${customerContact_err != null || customerContact_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="customerContact_err" data-valmsg-replace="true">${customerContact_err}</span>
					</c:if>
					<c:if
						test="${customerAddress_err != null || customerAddress_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="customerAddress_err" data-valmsg-replace="true">${customerAddress_err}</span>
					</c:if>
					<c:if
						test="${customerCountry_err != null || customerCountry_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="customerCountry_err" data-valmsg-replace="true">${customerCountry_err}</span>
					</c:if>
					<c:if test="${customerCity_err != null || customerCity_err != ''}">
						<span class="form__text-danger" data-valmsg-for="customerCity_err"
							data-valmsg-replace="true">${customerCity_err}</span>
					</c:if>
					<c:if
						test="${customerPostalCode_err != null || customerPostalCode_err != ''}">
						<span class="form__text-danger"
							data-valmsg-for="customerPostalCode_err"
							data-valmsg-replace="true">${customerPostalCode_err}</span>
					</c:if>
				</div>

				<!-- chức năng -->
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="customer_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>