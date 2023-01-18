package vanhoangtran.com.javashopadmin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vanhoangtran.com.javashopadmin.model.bean.CountryBean;
import vanhoangtran.com.javashopadmin.model.bean.SupplierBean;
import vanhoangtran.com.javashopadmin.model.bo.CountryBo;
import vanhoangtran.com.javashopadmin.model.bo.SupplierBo;
import vanhoangtran.com.javashopadmin.model.helps.SupplierPaginationResultModel;
@Controller
public class SupplierController {
	@RequestMapping("/supplier_admin")
	public ModelAndView getSupplier(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "supplier/supplier_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			String searchValue = "";
			
			SupplierPaginationResultModel  supplierModel = new SupplierPaginationResultModel ();

			rowCount = new SupplierBo().count(searchValue);
			ArrayList<SupplierBean> data = new SupplierBo().gets(page, pageSize, searchValue);

			if (session.getAttribute("SUPPLIER_SESSION") != null) {
				supplierModel = (SupplierPaginationResultModel) session.getAttribute("SUPPLIER_SESSION");

				try {
					if (request.getParameter("supplier_page") != "" && request.getParameter("supplier_page") != null) {
						page = Integer.parseInt(request.getParameter("supplier_page"));
						supplierModel.setPage(page);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (request.getParameter("supplier_search") != null) {
					searchValue = request.getParameter("supplier_search");
					supplierModel.setSearchValue(searchValue);
					supplierModel.setPage(page);
				}

				supplierModel.setRowCount(new SupplierBo().count(supplierModel.getSearchValue()));

				supplierModel.setData(
						new SupplierBo().gets(supplierModel.getPage(), pageSize, supplierModel.getSearchValue()));

			} else {
				supplierModel = new SupplierPaginationResultModel(page, pageSize, rowCount, searchValue, data);
			}

			model.addAttribute("supplier_model", supplierModel);

			session.setAttribute("SUPPLIER_SESSION", supplierModel);
			return new ModelAndView(path);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
	@RequestMapping("/supplier_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "supplier/supplier_create";

			String title = "Thêm mới một nhà cung cấp.";

			SupplierBean supplierBean = new SupplierBean();
			supplierBean.setSupplierID(0);

			ArrayList<CountryBean> listCountry = new CountryBo().gets();

			model.addAttribute("title_supplier", title);
			model.addAttribute("listCountry", listCountry);
			model.addAttribute("supplierBean", supplierBean);

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
	@RequestMapping("/supplier_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "supplier/supplier_create";

			String title = "Cập nhật thông tin nhà cung cấp.";

			int id = 0;

			try {
				if (request.getParameter("supplierID") != "" && request.getParameter("supplierID") != null)
					id = Integer.parseInt(request.getParameter("supplierID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("supplier_admin");
			}

			SupplierBean supplierBean = new SupplierBo().get(id);

			ArrayList<CountryBean> listCountry = new CountryBo().gets();

			if (supplierBean == null) {
				response.sendRedirect("supplier_admin");
			}

			model.addAttribute("title_supplier", title);
			model.addAttribute("listCountry", listCountry);
			model.addAttribute("supplierBean", supplierBean);

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
	@RequestMapping("/supplier_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "supplier/supplier_view";

			SupplierBean supplierBean = new SupplierBean();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào SupplierBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("supplierID") != "" && request.getParameter("supplierID") != null)
				supplierBean.setSupplierID(Integer.parseInt(request.getParameter("supplierID")));
			else {
				response.sendRedirect("supplier_admin");
				model.addAttribute("supplierID_err", "id nhà cung cấp không tồn tại");
			}

			if (request.getParameter("supplierName") != "" && request.getParameter("supplierName") != null)
				supplierBean.setSupplierName(request.getParameter("supplierName"));
			else {
				model.addAttribute("supplierName_err", "Tên nhà cung cấp không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierContact") != "" && request.getParameter("supplierContact") != null)
				supplierBean.setContactName((request.getParameter("supplierContact")));
			else {
				model.addAttribute("supplierContact_err", "tên giao dịch không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierAddress") != "" && request.getParameter("supplierAddress") != null)
				supplierBean.setAddress((request.getParameter("supplierAddress")));
			else {
				model.addAttribute("supplierAddress_err", "Địa chỉ không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierCountry") != "" && request.getParameter("supplierCountry") != null)
				supplierBean.setCountry((request.getParameter("supplierCountry")));
			else {
				model.addAttribute("supplierCountry_err", "Quốc gia không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierCity") != "" && request.getParameter("supplierCity") != null)
				supplierBean.setCity((request.getParameter("supplierCity")));
			else {
				model.addAttribute("supplierCity_err", "Thành phố không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierPostalCode") != "" && request.getParameter("supplierPostalCode") != null)
				supplierBean.setPostalCode((request.getParameter("supplierPostalCode")));
			else {
				model.addAttribute("supplierPostalCode_err", "Mã bưu điện không được để trống.");
				isValid = true;
			}

			if (request.getParameter("supplierPhone") != "" && request.getParameter("supplierPhone") != null)
				supplierBean.setPhone(request.getParameter("supplierPhone"));
			else {
				model.addAttribute("supplierPhone_err", "Số điện thoại không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (supplierBean.getSupplierID() > 0)
					title = "Cập nhật một nhà cung cấp.";
				else
					title = "Tạo mới một nhà cung cấp";

				ArrayList<CountryBean> listCountry = new CountryBo().gets();

				model.addAttribute("title_supplier", title);
				model.addAttribute("listCountry", listCountry);
				model.addAttribute("supplierBean", supplierBean);
				return new ModelAndView("supplier/supplier_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (supplierBean.getSupplierID() > 0) {
				new SupplierBo().update(supplierBean);
				response.sendRedirect("supplier_admin");
			} else {
				new SupplierBo().add(supplierBean);
				response.sendRedirect("supplier_admin");
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
	@RequestMapping("/supplier_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "supplier/supplier_delete";

			int id = 0;

			try {
				if (request.getParameter("supplierID") != "" && request.getParameter("supplierID") != null)
					id = Integer.parseInt(request.getParameter("supplierID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("supplier_admin");
			}

			if (request.getMethod() == "POST")
				new SupplierBo().delete(id);

			SupplierBean supplierBean = new SupplierBo().get(id);

			if (supplierBean == null || supplierBean.getSupplierID() <= 0)
				response.sendRedirect("supplier_admin");

			model.addAttribute("supplierBo", new SupplierBo());
			model.addAttribute("supplierBean", supplierBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}
}
