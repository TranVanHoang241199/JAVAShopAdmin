package vanhoangtran.com.javashopadmin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vanhoangtran.com.javashopadmin.model.bean.CategoryBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductAttributeBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductBean;
import vanhoangtran.com.javashopadmin.model.bean.ProductPhotoBean;
import vanhoangtran.com.javashopadmin.model.bean.SupplierBean;
import vanhoangtran.com.javashopadmin.model.bo.CategoryBo;
import vanhoangtran.com.javashopadmin.model.bo.ProductBo;
import vanhoangtran.com.javashopadmin.model.bo.SupplierBo;
import vanhoangtran.com.javashopadmin.model.helps.ProductPaginationResultModel;
import vanhoangtran.com.javashopadmin.model.helps.ProductUpdateModel;

@Controller
public class ProductController {
	/**
	 * hiển thị danh sách product có phân trang tìm kiếm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin")
	public ModelAndView gets(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			int supplierID = 0;
			int productID = 0;
			String searchValue = "";

			try {
				if (request.getParameter("product_page") != "" && request.getParameter("product_page") != null)
					page = Integer.parseInt(request.getParameter("product_page"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {

				if (request.getParameter("productsupplier") != "" && request.getParameter("productsupplier") != null)
					supplierID = Integer.parseInt(request.getParameter("productsupplier"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (request.getParameter("productproduct") != "" && request.getParameter("productproduct") != null)
					productID = Integer.parseInt(request.getParameter("productproduct"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (request.getParameter("product_search") != null)
				searchValue = request.getParameter("product_search");

			rowCount = new ProductBo().count(searchValue, productID, supplierID);
			ArrayList<ProductBean> data = new ProductBo().gets(page, pageSize, searchValue, productID, supplierID);

			ProductPaginationResultModel productmodel = new ProductPaginationResultModel(page, pageSize, rowCount,
					searchValue, data, productID, supplierID);

			ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
			ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

			model.addAttribute("listCategorys", listCategorys);
			model.addAttribute("listSuppliers", listSuppliers);
			model.addAttribute("product_model", productmodel);

//			session.setAttribute("product_SEARCH", input);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * tạo mới một loại sản phẩm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_create";

			ProductBean productBean = new ProductBean();
			productBean.setProductID(0);

			ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
			ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

			model.addAttribute("listCategorys", listCategorys);
			model.addAttribute("listSuppliers", listSuppliers);
			model.addAttribute("productBean", productBean);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * cập nhật một sản phẩm dựa vào id
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_edit";

			ProductUpdateModel updateModel = new ProductUpdateModel();

			int id = 0;

			try {
				if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
					id = Integer.parseInt(request.getParameter("productID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("product_admin");
			}

			updateModel.setData(new ProductBo().get(id));
			updateModel.setDataOfAttributes(new ProductBo().getOfAttributes(id));
			updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(id));

			if (updateModel.getData() == null) {
				response.sendRedirect("product_admin");
			}

			ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
			ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

			model.addAttribute("listCategorys", listCategorys);
			model.addAttribute("listSuppliers", listSuppliers);
			model.addAttribute("updateModel", updateModel);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * lưu sản phẩm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_view";

			ProductUpdateModel updateModel = new ProductUpdateModel();
			ProductBean productBean = new ProductBean();

			boolean isValid = false;

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào productBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
				productBean.setProductID(Integer.parseInt(request.getParameter("productID")));
			else {
				response.sendRedirect("product_admin");
				model.addAttribute("productID_err", "id nhân viên không hợp lệ");
			}

			if (request.getParameter("productName") != "" && request.getParameter("productName") != null)
				productBean.setProductName(request.getParameter("productName"));
			else {
				model.addAttribute("productName_err", "Họ không được để trống.");
				isValid = true;
			}

			if (request.getParameter("productUnit") != "" && request.getParameter("productUnit") != null)
				productBean.setUnit(request.getParameter("productUnit"));
			else {
				model.addAttribute("productUnit_err", "Tên không được để trống.");
				isValid = true;
			}

			if (request.getParameter("productPrice") != "" && request.getParameter("productPrice") != null)
				productBean.setPrice(Double.parseDouble(request.getParameter("productPrice")));
			else {
				model.addAttribute("productPrice_err", "Ngày sinh không được để trống.");
				isValid = true;
			}

			if (request.getParameter("categoryID") != "" && request.getParameter("categoryID") != null)
				productBean.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
			else {
				model.addAttribute("categoryID_err", "Ảnh không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierID") != "" && request.getParameter("supplierID") != null)
				productBean.setSupplierID(Integer.parseInt(request.getParameter("supplierID")));

			if (request.getParameter("productPhoto") != "" && request.getParameter("productPhoto") != null)
				productBean.setPhoto(request.getParameter("productPhoto"));
			else {
				model.addAttribute("productPhoto_err", "Ảnh không được để trống.");
				isValid = true;
			}

			updateModel.setData(productBean);

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {

				if (updateModel.getData().getProductID() != 0) {
					updateModel.setData(new ProductBo().get(updateModel.getData().getProductID()));
					updateModel
							.setDataOfAttributes(new ProductBo().getOfAttributes(updateModel.getData().getProductID()));
					updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(updateModel.getData().getProductID()));

					if (updateModel.getData() == null) {
						response.sendRedirect("product_admin");
					}

					model.addAttribute("updateModel", updateModel);
				} else
					model.addAttribute("productBean", updateModel.getData());

				ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
				ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

				model.addAttribute("listCategorys", listCategorys);
				model.addAttribute("listSuppliers", listSuppliers);

				if (updateModel.getData().getProductID() > 0)
					return new ModelAndView("product/product_edit");
				else
					return new ModelAndView("product/product_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (updateModel.getData().getProductID() > 0) {
				new ProductBo().update(updateModel.getData());
				response.sendRedirect("product_admin");
			} else {
				new ProductBo().add(updateModel.getData());
				response.sendRedirect("product_admin");
			}

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * xóa một sản phẩm dựa vào id
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_delete";

			int id = 0;

			try {
				if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
					id = Integer.parseInt(request.getParameter("productID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("product_admin");
			}

			if (request.getMethod() == "POST")
				new ProductBo().delete(id);

			ProductBean productBean = new ProductBo().get(id);

			if (productBean == null || productBean.getProductID() <= 0)
				response.sendRedirect("product_admin");

			model.addAttribute("productBo", new ProductBo());
			model.addAttribute("productBean", productBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	// --------------------------------------------

	/**
	 * tạo mới một loại sản phẩm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-photo")
	public ModelAndView photo(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_photo";

			String title = "";

			ProductPhotoBean photoBean = new ProductPhotoBean();

			int productID = 0;

			int photoID = 0;

			String method = request.getParameter("method");

			switch (method) {

			case "add":
				title = "Bổ sung ảnh";

				try {
					if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
						productID = Integer.parseInt(request.getParameter("productID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				photoBean.setPhotoID(0);
				photoBean.setProductID(productID);
				break;

			case "edit":
				title = "chỉnh sửa ảnh";

				try {
					if (request.getParameter("photoID") != "" && request.getParameter("photoID") != null)
						photoID = Integer.parseInt(request.getParameter("photoID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}
				System.out.println(photoID);
				photoBean = new ProductBo().getOfPhoto(photoID);

				if (photoBean == null)
					response.sendRedirect("product_admin");
				break;

			case "delete":
				title = "Bổ sung ảnh";
				path = "product_admin-update";
				try {
					if (request.getParameter("photoID") != "" && request.getParameter("photoID") != null)
						photoID = Integer.parseInt(request.getParameter("photoID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				try {
					if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
						productID = Integer.parseInt(request.getParameter("productID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				if (new ProductBo().deleteOfPhoto(photoID)) {
					// ----------
					path = "product/product_edit";
					System.out.println("update");
					ProductUpdateModel updateModel = new ProductUpdateModel();

					updateModel.setData(new ProductBo().get(productID));
					updateModel.setDataOfAttributes(new ProductBo().getOfAttributes(productID));
					updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(productID));

					if (updateModel.getData() == null) {
						response.sendRedirect("product_admin");
					}

					ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
					ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

					model.addAttribute("listCategorys", listCategorys);
					model.addAttribute("listSuppliers", listSuppliers);
					model.addAttribute("updateModel", updateModel);

					return new ModelAndView(path);

					// -----------
				} else
					response.sendRedirect("product_admin");
				break;

			default:
				response.sendRedirect("product_admin");
			}

			model.addAttribute("title_photo", title);
			model.addAttribute("photoBean", photoBean);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * cập nhật một sản phẩm dựa vào id
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-attribute")
	public ModelAndView attribute(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_attribute";

			String title = "";

			ProductAttributeBean attributeBean = new ProductAttributeBean();

			int productID = 0;

			int attributeID = 0;

			String method = request.getParameter("method");

			switch (method) {

			case "add":
				title = "Bổ sung ảnh";

				try {
					if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
						productID = Integer.parseInt(request.getParameter("productID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				attributeBean.setAttributeID(0);
				attributeBean.setProductID(productID);
				break;

			case "edit":
				title = "chỉnh sửa ảnh";

				try {
					if (request.getParameter("attributeID") != "" && request.getParameter("attributeID") != null)
						attributeID = Integer.parseInt(request.getParameter("attributeID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				attributeBean = new ProductBo().getOfAttribute(attributeID);

				if (attributeBean == null)
					response.sendRedirect("product_admin");
				break;

			case "delete":
				try {
					if (request.getParameter("attributeID") != "" && request.getParameter("attributeID") != null)
						attributeID = Integer.parseInt(request.getParameter("attributeID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				try {
					if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
						productID = Integer.parseInt(request.getParameter("productID"));
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("product_admin");
				}

				if (new ProductBo().deleteOfAttribute(attributeID)) {
					// ----------
					path = "product/product_edit";
					System.out.println("update");
					ProductUpdateModel updateModel = new ProductUpdateModel();

					updateModel.setData(new ProductBo().get(productID));
					updateModel.setDataOfAttributes(new ProductBo().getOfAttributes(productID));
					updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(productID));

					if (updateModel.getData() == null) {
						response.sendRedirect("product_admin");
					}

					ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
					ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

					model.addAttribute("listCategorys", listCategorys);
					model.addAttribute("listSuppliers", listSuppliers);
					model.addAttribute("updateModel", updateModel);

					return new ModelAndView(path);

					// -----------
				} else
					response.sendRedirect("product_admin");
				break;

			default:
				response.sendRedirect("product_admin");
			}

			model.addAttribute("title_attribute", title);
			model.addAttribute("attributeBean", attributeBean);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * lưu sản phẩm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-save_photo")
	public ModelAndView savePhoto(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_edit";

			ProductPhotoBean photoBean = new ProductPhotoBean();
			
			ProductUpdateModel updateModel = new ProductUpdateModel();
			
			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào productBean còn không thì trả về một thông báo err
			 */
			try {
				if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
					photoBean.setProductID(Integer.parseInt(request.getParameter("productID")));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("product_admin");
			}
			
			if (request.getParameter("photoID") != "" && request.getParameter("photoID") != null)
				photoBean.setPhotoID(Integer.parseInt(request.getParameter("photoID")));
			else {
				model.addAttribute("photoID_err", "id ảnh không tồn tại");
				response.sendRedirect("product_admin");
			}

			if (request.getParameter("Photo") != "" && request.getParameter("Photo") != null)
				photoBean.setPhoto(request.getParameter("Photo"));
			else {
				model.addAttribute("Photo_err", "Ảnh không được để trống.");
				isValid = true;
			}

			if (request.getParameter("description") != "" && request.getParameter("description") != null)
				photoBean.setDescription(request.getParameter("description"));
			else {
				model.addAttribute("description_err", "Mô tả không được để trống.");
				isValid = true;
			}

			if (request.getParameter("displayOrder") != "" && request.getParameter("displayOrder") != null)
				photoBean.setDisplayOrder(Integer.parseInt(request.getParameter("displayOrder")));
			else {
				model.addAttribute("displayOrder_err", "Thứ tự hiển thị không được để trống.");
				isValid = true;
			}
			
			if (request.getParameter("isHidden") != "" && request.getParameter("isHidden") != null)
				photoBean.setHidden(Boolean.parseBoolean(request.getParameter("isHidden")));
			else {
				model.addAttribute("isHidden_err", "Ẩn ảnh không được để trống không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (photoBean.getPhotoID() > 0)
					title = "Cập nhật một loại sản phẩm.";
				else
					title = "Tạo mới một loại sản phẩm";

				model.addAttribute("title_photo", title);
				model.addAttribute("photoBean", photoBean);
				return new ModelAndView("product/product_photo");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (photoBean.getPhotoID() > 0) {
				new ProductBo().updateOfPhoto(photoBean);

			} else {
				new ProductBo().addOfPhoto(photoBean);
			}

			// tra du lieu
			updateModel.setData(new ProductBo().get(photoBean.getProductID()));
			updateModel.setDataOfAttributes(new ProductBo().getOfAttributes(photoBean.getProductID()));
			updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(photoBean.getProductID()));

			if (updateModel.getData() == null) {
				response.sendRedirect("product_admin");
			}

			ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
			ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

			model.addAttribute("listCategorys", listCategorys);
			model.addAttribute("listSuppliers", listSuppliers);
			model.addAttribute("updateModel", updateModel);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * lưu sản phẩm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/product_admin-save_attribute")
	public ModelAndView saveattribute(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "product/product_edit";
			
			ProductAttributeBean attributeBean = new ProductAttributeBean();

			ProductUpdateModel updateModel = new ProductUpdateModel();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào productBean còn không thì trả về một thông báo err
			 */
			try {
				if (request.getParameter("productID") != "" && request.getParameter("productID") != null)
					attributeBean.setProductID(Integer.parseInt(request.getParameter("productID")));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("product_admin");
			}

			if (request.getParameter("attributeID") != "" && request.getParameter("attributeID") != null)
				attributeBean.setAttributeID(Integer.parseInt(request.getParameter("attributeID")));
			else {
				response.sendRedirect("product_admin");
				model.addAttribute("attributeID_err", "id thuộc tính không tồn tại");
			}

			if (request.getParameter("attributeName") != "" && request.getParameter("attributeName") != null)
				attributeBean.setAttributeName(request.getParameter("attributeName"));
			else {
				model.addAttribute("attributeName_err", "tên thuộc tính không được để trống.");
				isValid = true;
			}

			if (request.getParameter("attributeValue") != "" && request.getParameter("attributeValue") != null)
				attributeBean.setAttributeValue(request.getParameter("attributeValue"));
			else {
				model.addAttribute("attributeValue_err", "Giá trị thuộc tính không được để trống.");
				isValid = true;
			}

			if (request.getParameter("displayOrder") != "" && request.getParameter("displayOrder") != null)
				attributeBean.setDisplayOrder(Integer.parseInt(request.getParameter("displayOrder")));
			else {
				model.addAttribute("displayOrder_err", "thứ tự hiển thị không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (attributeBean.getAttributeID() > 0)
					title = "Cập nhật một loại sản phẩm.";
				else
					title = "Tạo mới một loại sản phẩm";

				model.addAttribute("title_attribute", title);
				model.addAttribute("attributeBean", attributeBean);
				return new ModelAndView("product/product_attribute");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (attributeBean.getAttributeID() > 0) {
				new ProductBo().updateOfAttribute(attributeBean);

			} else {
				new ProductBo().addOfAttribute(attributeBean);
			}

			// tra du lieu
			updateModel.setData(new ProductBo().get(attributeBean.getProductID()));
			updateModel.setDataOfAttributes(new ProductBo().getOfAttributes(attributeBean.getProductID()));
			updateModel.setDataOfPhotos(new ProductBo().getOfPhotos(attributeBean.getProductID()));

			if (updateModel.getData() == null) {
				response.sendRedirect("product_admin");
			}

			ArrayList<CategoryBean> listCategorys = new CategoryBo().gets(0, 0, "");
			ArrayList<SupplierBean> listSuppliers = new SupplierBo().gets(0, 0, "");

			model.addAttribute("listCategorys", listCategorys);
			model.addAttribute("listSuppliers", listSuppliers);
			model.addAttribute("updateModel", updateModel);

			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}

}