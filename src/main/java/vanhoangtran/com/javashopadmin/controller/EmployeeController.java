package vanhoangtran.com.javashopadmin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vanhoangtran.com.javashopadmin.model.bean.EmployeeBean;
import vanhoangtran.com.javashopadmin.model.bo.EmployeeBo;
import vanhoangtran.com.javashopadmin.model.helps.EmployeePaginationResultModel;

@Controller
public class EmployeeController {
	@RequestMapping("/employee_admin")
	public ModelAndView getEmployee(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "employee/employee_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			String searchValue = "";

			EmployeePaginationResultModel  employeeModel = new EmployeePaginationResultModel ();

			rowCount = new EmployeeBo().count(searchValue);
			ArrayList<EmployeeBean> data = new EmployeeBo().gets(page, pageSize, searchValue);

			if (session.getAttribute("EMPLOYEE_SESSION") != null) {
				employeeModel = (EmployeePaginationResultModel) session.getAttribute("EMPLOYEE_SESSION");

				try {
					if (request.getParameter("employee_page") != "" && request.getParameter("employee_page") != null) {
						page = Integer.parseInt(request.getParameter("employee_page"));
						employeeModel.setPage(page);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (request.getParameter("employee_search") != null) {
					searchValue = request.getParameter("employee_search");
					employeeModel.setSearchValue(searchValue);
					employeeModel.setPage(page);
				}

				employeeModel.setRowCount(new EmployeeBo().count(employeeModel.getSearchValue()));

				employeeModel.setData(
						new EmployeeBo().gets(employeeModel.getPage(), pageSize, employeeModel.getSearchValue()));

			} else {
				employeeModel = new EmployeePaginationResultModel(page, pageSize, rowCount, searchValue, data);
			}

			model.addAttribute("employee_model", employeeModel);

			session.setAttribute("EMPLOYEE_SESSION", employeeModel);
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
	@RequestMapping("/employee_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "employee/employee_create";

			String title = "Thêm mới một nhân viên.";

			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeID(0);

			model.addAttribute("title_employee", title);
			model.addAttribute("employeeBean", employeeBean);

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
	@RequestMapping("/employee_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "employee/employee_create";

			String title = "Cập nhật thông tin nhân viên.";

			int id = 0;

			try {
				if (request.getParameter("employeeID") != "" && request.getParameter("employeeID") != null)
					id = Integer.parseInt(request.getParameter("employeeID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("employee_admin");
			}

			EmployeeBean employeeBean = new EmployeeBo().get(id);

			if (employeeBean == null) {
				response.sendRedirect("employee_admin");
			}

			System.out.println(id);
			model.addAttribute("title_employee", title);
			model.addAttribute("employeeBean", employeeBean);

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
	@RequestMapping("/employee_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "employee/employee_view";

			EmployeeBean employeeBean = new EmployeeBean();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào employeeBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("employeeID") != "" && request.getParameter("employeeID") != null)
				employeeBean.setEmployeeID(Integer.parseInt(request.getParameter("employeeID")));
			else {
				response.sendRedirect("employee_admin");
				model.addAttribute("employeeID_err", "id nhân viên không hợp lệ");
			}

			if (request.getParameter("employeeLastName") != "" && request.getParameter("employeeLastName") != null)
				employeeBean.setLastName(request.getParameter("employeeLastName"));
			else {
				model.addAttribute("employeeLastName_err", "Họ không được để trống.");
				isValid = true;
			}

			if (request.getParameter("employeeFirstName") != "" && request.getParameter("employeeFirstName") != null)
				employeeBean.setFirstName(request.getParameter("employeeFirstName"));
			else {
				model.addAttribute("employeeFirstName_err", "Tên không được để trống.");
				isValid = true;
			}

			if (request.getParameter("employeeBirthDate") != "" && request.getParameter("employeeBirthDate") != null)
				employeeBean.setBirthDate(request.getParameter("employeeBirthDate"));
			else {
				model.addAttribute("employeeBirthDate_err", "Ngày sinh không được để trống.");
				isValid = true;
			}

			if (request.getParameter("employeeEmail") != "" && request.getParameter("employeeEmail") != null)
				employeeBean.setEmail(request.getParameter("employeeEmail"));
			else {
				model.addAttribute("employeeEmail_err", "Email không được để trống.");
				isValid = true;
			}

			if (request.getParameter("employeeNotes") != "" && request.getParameter("employeeNotes") != null)
				employeeBean.setNotes(request.getParameter("employeeNotes"));
			/*
			 * else { model.addAttribute("employeeNotes_err", "mô tả không được để trống.");
			 * isValid = true; }
			 */

			if (request.getParameter("employeePhoto") != "" && request.getParameter("employeePhoto") != null)
				employeeBean.setPhoto(request.getParameter("employeePhoto"));
			else {
				model.addAttribute("employeePhoto_err", "Ảnh không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (employeeBean.getEmployeeID() > 0)
					title = "Cập nhật thông tin nhân viên.";
				else
					title = "Thêm mới một nhân viên.";

				model.addAttribute("title_employee", title);
				model.addAttribute("employeeBean", employeeBean);
				return new ModelAndView("employee/employee_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (employeeBean.getEmployeeID() > 0) {
				new EmployeeBo().update(employeeBean);
				response.sendRedirect("employee_admin");
			} else {
				new EmployeeBo().add(employeeBean);
				response.sendRedirect("employee_admin");
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
	@RequestMapping("/employee_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "employee/employee_delete";

			int id = 0;

			try {
				if (request.getParameter("employeeID") != "" && request.getParameter("employeeID") != null)
					id = Integer.parseInt(request.getParameter("employeeID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("employee_admin");
			}

			if (request.getMethod() == "POST")
				new EmployeeBo().delete(id);

			EmployeeBean employeeBean = new EmployeeBo().get(id);

			if (employeeBean == null || employeeBean.getEmployeeID() <= 0)
				response.sendRedirect("employee_admin");

			model.addAttribute("employeeBo", new EmployeeBo());
			model.addAttribute("employeeBean", employeeBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}
}
