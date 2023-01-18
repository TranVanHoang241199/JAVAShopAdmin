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
import vanhoangtran.com.javashopadmin.model.bean.CustomerBean;
import vanhoangtran.com.javashopadmin.model.bo.CountryBo;
import vanhoangtran.com.javashopadmin.model.bo.CustomerBo;
import vanhoangtran.com.javashopadmin.model.helps.CustomerPaginationResultModel;

@Controller
public class CustomerController {
	@RequestMapping("/customer_admin")
	public ModelAndView getCustomer(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "customer/customer_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			String searchValue = "";

			CustomerPaginationResultModel customermodel = new CustomerPaginationResultModel();
			
			rowCount = new CustomerBo().count(searchValue);
			ArrayList<CustomerBean> data = new CustomerBo().gets(page, pageSize, searchValue);

			if (session.getAttribute("CUSTOMER_SESSION") != null) {
				customermodel = (CustomerPaginationResultModel) session.getAttribute("CUSTOMER_SESSION");

				try {
					if (request.getParameter("customer_page") != "" && request.getParameter("customer_page") != null) {
						page = Integer.parseInt(request.getParameter("customer_page"));
						customermodel.setPage(page);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (request.getParameter("customer_search") != null) {
					searchValue = request.getParameter("customer_search");
					customermodel.setSearchValue(searchValue);
					customermodel.setPage(page);
				}

				customermodel.setRowCount(new CustomerBo().count(customermodel.getSearchValue()));
				
				customermodel.setData(
						new CustomerBo().gets(customermodel.getPage(), pageSize, customermodel.getSearchValue()));

			} else {
				customermodel = new CustomerPaginationResultModel(page, pageSize, rowCount, searchValue, data);
			}

			model.addAttribute("customer_model", customermodel);
			
			session.setAttribute("CUSTOMER_SESSION", customermodel);
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
	@RequestMapping("/customer_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "customer/customer_create";

			String title = "Thêm mới một khách hàng.";

			CustomerBean customerBean = new CustomerBean();
			customerBean.setCustomerID(0);

			ArrayList<CountryBean> listCountry = new CountryBo().gets();

			model.addAttribute("title_customer", title);
			model.addAttribute("listCountry", listCountry);
			model.addAttribute("customerBean", customerBean);

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
	@RequestMapping("/customer_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "customer/customer_create";

			String title = "Cập nhật một thông tin khách hàng.";

			int id = 0;

			try {
				if (request.getParameter("customerID") != "" && request.getParameter("customerID") != null)
					id = Integer.parseInt(request.getParameter("customerID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("customer_admin");
			}

			CustomerBean customerBean = new CustomerBo().get(id);
			ArrayList<CountryBean> listCountry = new CountryBo().gets();

			if (customerBean == null) {
				response.sendRedirect("customer_admin");
			}

			model.addAttribute("title_customer", title);
			model.addAttribute("listCountry", listCountry);
			model.addAttribute("customerBean", customerBean);

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
	@RequestMapping("/customer_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "customer/customer_view";

			CustomerBean customerBean = new CustomerBean();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào customerBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("customerID") != "" && request.getParameter("customerID") != null)
				customerBean.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
			else {
				response.sendRedirect("customer_admin");
				model.addAttribute("customerID_err", "id khách hàng không tồn tại");
			}

			if (request.getParameter("customerName") != "" && request.getParameter("customerName") != null)
				customerBean.setCustomerName(request.getParameter("customerName"));
			else {
				model.addAttribute("customerName_err", "Tên khách hàng không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerContact") != "" && request.getParameter("customerContact") != null)
				customerBean.setContactName(request.getParameter("customerContact"));
			else {
				model.addAttribute("customerContact_err", "tên giao dịch không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerAddress") != "" && request.getParameter("customerAddress") != null)
				customerBean.setAddress(request.getParameter("customerAddress"));
			else {
				model.addAttribute("customerAddress_err", "Địa chỉ không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerCountry") != "" && request.getParameter("customerCountry") != null)
				customerBean.setCountry(request.getParameter("customerCountry"));
			else {
				model.addAttribute("customerCountry_err", "Quốc gia không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerCity") != "" && request.getParameter("customerCity") != null)
				customerBean.setCity(request.getParameter("customerCity"));
			else {
				model.addAttribute("customerCity_err", "Thành phố không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerPostalCode") != "" && request.getParameter("customerPostalCode") != null)
				customerBean.setPostalCode(request.getParameter("customerPostalCode"));
			else {
				model.addAttribute("customerPostalCode_err", "Mã bưu điện không được để trống.");
				isValid = true;
			}

			if (request.getParameter("customerEmail") != "" && request.getParameter("customerEmail") != null)
				customerBean.setEmail(request.getParameter("customerEmail"));
			/*
			 * else { model.addAttribute("customerEmail_err",
			 * "Mã bưu điện không được để trống."); isValid = true; }
			 */

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (customerBean.getCustomerID() > 0)
					title = "Cập nhật một khách hàng.";
				else
					title = "Tạo mới một khách hàng";

				ArrayList<CountryBean> listCountry = new CountryBo().gets();

				model.addAttribute("title_customer", title);
				model.addAttribute("listCountry", listCountry);
				model.addAttribute("customerBean", customerBean);
				return new ModelAndView("customer/customer_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (customerBean.getCustomerID() > 0) {
				new CustomerBo().update(customerBean);
				response.sendRedirect("customer_admin");
			} else {
				new CustomerBo().add(customerBean);
				response.sendRedirect("customer_admin");
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
	@RequestMapping("/customer_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "customer/customer_delete";

			int id = 0;

			try {
				if (request.getParameter("customerID") != "" && request.getParameter("customerID") != null)
					id = Integer.parseInt(request.getParameter("customerID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("customer_admin");
			}

			if (request.getMethod() == "POST")
				new CustomerBo().delete(id);

			CustomerBean customerBean = new CustomerBo().get(id);

			if (customerBean == null || customerBean.getCustomerID() <= 0)
				response.sendRedirect("customer_admin");

			model.addAttribute("customerBo", new CustomerBo());
			model.addAttribute("customerBean", customerBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}
}
