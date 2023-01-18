<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../shared/header_view.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title_shipper }</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/home.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/create.css'/>">
</head>
<body>
	<section>
		<h1 class="section__title">${title_shipper }</h1>
		<div class="section__box">
			<form action="shipper_admin-save" method="post">
				<input type="hidden" name="shipperID"
					value="${shipperBean.getShipperID() }">

				<div class="form-group">
					<label class="control-label">Tên người giao hàng<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input autofocus="autofocus" class="form-control" id="shipperName"
							name="shipperName" type="text"
							value="${shipperBean.getShipperName() }">
					</div>
				</div>

				<div class="form-group">
					<label class="control-label">số điện thoại<small
						class="text-danger"> * </small>:
					</label>
					<div class="control-input">
						<input class="form-control"
							id="shipperPhone" name="shipperPhone" type="text"
							value="${shipperBean.getPhone() }">
					</div>
				</div>

				<div class="form__err">
					<c:if test="${shipperName_err != null || shipperName_err != ''}">
						<span class="form__text-danger" data-valmsg-for="shipperName_err"
							data-valmsg-replace="true">${shipperName_err}</span>
					</c:if>
					<c:if test="${shipperPhone_err != null || shipperPhone_err != ''}">
						<span class="form__text-danger" data-valmsg-for="shipperPhone"
							data-valmsg-replace="true">${shipperPhone}</span>
					</c:if>
				</div>
				<div class="form__option">
					<button type="submit" class="form__btn--save">
						<i class="fa-solid fa-floppy-disk"></i> Lưu dữ liệu
					</button>
					<a href="shipper_admin" class="form__btn--back"> <i
						class="fa-solid fa-ban"></i> Quay lại
					</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>