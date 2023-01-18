<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value='resources/css/header.css'/>">
</head>
<body>
	<header>
		<a href="" class="logo__link"> <span class="logo_txt">AdminLTE</span>
		</a> <div class="account__link account__link--hover"> <img class="account__img"
			alt="img_account"
			src="<c:url value='resources/img/login/hoang_img.jpg'/>"></img> <span
			class="account__name">${session__adminUser }</span>
			<div class="account__profile">
				<div class="profile__background">
					<img class="profile__img" alt="" src="<c:url value='resources/img/login/hoang_img.jpg'/>">
					<p class="profile__name">${session__adminUser }</p>
				</div>
				<div class="profile__option">
					<a href="" class="profile__btn">Đổi mật khẩu</a>
					<a href="dangXuatadmin" class="profile__btn">Đăng xuất</a>
				</div>
			</div>
		</div>
	</header>

	
	
	<footer style="align-self: flex-end;"> 
		<div class="footer__dsec">
			<p class="dsec__license">Bản quyền © 2022</p>
			<a href="" class="dsec__name-shop--link">Shop</a>
			<p class="dsec__reserved">reserved.</p>
		</div>
		<div class="footer__version">
		<p class="version__name">Version</p>
		<p class="version__number">2.4.0</p>
		</div>
	</footer>
	
	<div id="optional">
		<ul class="optional__list">
			<li class="optional__item"><a href="home_admin" class="item__link">Trang Chủ</a></li>
			<li class="optional__item"><a href="" class="item__link">Quản lí dữ liệu
					<i class="item__icon"></i>
			</a>
				<ul class="manage__list">
					<li class="manage__item"><a href="supplier_admin" class="manage__link"> Nhà cung
							cấp</a></li>
					<li class="manage__item"><a href="customer_admin" class="manage__link"> Khách
							hàng</a></li>
					<li class="manage__item"><a href="shipper_admin" class="manage__link"> Người
							giao hàng</a></li>
					<li class="manage__item"><a href="Employee_admin" class="manage__link"> Nhân
							viên</a></li>
					<li class="manage__item"><a href="category_admin?category_search=" class="manage__link"> Loại
							hàng</a></li>
					<li class="manage__item"><a href="Product_admin" class="manage__link"> Mặt hàng</a></li>
				</ul></li>
			<li class="optional__item"><a href="" class="item__link">Bán Hàng <i
					class="item__icon"></i>
			</a></li>
		</ul>
	</div>
</body>
</html>