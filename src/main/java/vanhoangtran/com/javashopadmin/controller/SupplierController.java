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
	 * t???o m???i m???t lo???i s???n ph???m
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

			String title = "Th??m m???i m???t nh?? cung c???p.";

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
	 * c???p nh???t m???t s???n ph???m d???a v??o id
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

			String title = "C???p nh???t th??ng tin nh?? cung c???p.";

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
	 * l??u s???n ph???m
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
			 * l???y d??? li???u t??? view create ki???m tra d??? li???u ???? t???n t???i hay kh??ng n???u c?? th??
			 * g??n v??o SupplierBean c??n kh??ng th?? tr??? v??? m???t th??ng b??o err
			 */
			if (request.getParameter("supplierID") != "" && request.getParameter("supplierID") != null)
				supplierBean.setSupplierID(Integer.parseInt(request.getParameter("supplierID")));
			else {
				response.sendRedirect("supplier_admin");
				model.addAttribute("supplierID_err", "id nh?? cung c???p kh??ng t???n t???i");
			}

			if (request.getParameter("supplierName") != "" && request.getParameter("supplierName") != null)
				supplierBean.setSupplierName(request.getParameter("supplierName"));
			else {
				model.addAttribute("supplierName_err", "T??n nh?? cung c???p kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierContact") != "" && request.getParameter("supplierContact") != null)
				supplierBean.setContactName((request.getParameter("supplierContact")));
			else {
				model.addAttribute("supplierContact_err", "t??n giao d???ch kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierAddress") != "" && request.getParameter("supplierAddress") != null)
				supplierBean.setAddress((request.getParameter("supplierAddress")));
			else {
				model.addAttribute("supplierAddress_err", "?????a ch??? kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierCountry") != "" && request.getParameter("supplierCountry") != null)
				supplierBean.setCountry((request.getParameter("supplierCountry")));
			else {
				model.addAttribute("supplierCountry_err", "Qu???c gia kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierCity") != "" && request.getParameter("supplierCity") != null)
				supplierBean.setCity((request.getParameter("supplierCity")));
			else {
				model.addAttribute("supplierCity_err", "Th??nh ph??? kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierPostalCode") != "" && request.getParameter("supplierPostalCode") != null)
				supplierBean.setPostalCode((request.getParameter("supplierPostalCode")));
			else {
				model.addAttribute("supplierPostalCode_err", "M?? b??u ??i???n kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("supplierPhone") != "" && request.getParameter("supplierPhone") != null)
				supplierBean.setPhone(request.getParameter("supplierPhone"));
			else {
				model.addAttribute("supplierPhone_err", "S??? ??i???n tho???i kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			// ki???m tra v?? tr??? l???i create ????? c???p nh???t l???i cho ????ng th??ng tin
			if (isValid == true) {
				if (supplierBean.getSupplierID() > 0)
					title = "C???p nh???t m???t nh?? cung c???p.";
				else
					title = "T???o m???i m???t nh?? cung c???p";

				ArrayList<CountryBean> listCountry = new CountryBo().gets();

				model.addAttribute("title_supplier", title);
				model.addAttribute("listCountry", listCountry);
				model.addAttribute("supplierBean", supplierBean);
				return new ModelAndView("supplier/supplier_create");
			}

			// ki???m tr??? v?? th??m ho???c c???t nh???t d??? li???u
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
	 * x??a m???t s???n ph???m d???a v??o id
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
