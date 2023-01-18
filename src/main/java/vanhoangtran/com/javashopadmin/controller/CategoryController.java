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
import vanhoangtran.com.javashopadmin.model.bo.CategoryBo;
import vanhoangtran.com.javashopadmin.model.helps.CategoryPaginationResultModel;

@Controller
public class CategoryController {

	/**
	 * hiển thị danh sách category có phân trang tìm kiếm
	 * 
	 * @param model
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/category_admin")
	public ModelAndView gets(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "category/category_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			String searchValue = "";
			
			CategoryPaginationResultModel categorymodel = new CategoryPaginationResultModel();
			rowCount = new CategoryBo().count(searchValue);
			ArrayList<CategoryBean> data = new CategoryBo().gets(page, pageSize, searchValue);
			
			if (session.getAttribute("CATEGORY_SESSION") != null) {
				categorymodel = (CategoryPaginationResultModel) session.getAttribute("CATEGORY_SESSION");
				
				try {
					if (request.getParameter("category_page") != "" && request.getParameter("category_page") != null) {						
						page = Integer.parseInt(request.getParameter("category_page"));
						categorymodel.setPage(page);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (request.getParameter("category_search") != null) {					
					searchValue = request.getParameter("category_search");
					categorymodel.setSearchValue(searchValue);
					categorymodel.setPage(page);
				}
				
				categorymodel.setRowCount(new CategoryBo().count(categorymodel.getSearchValue()));	
				
				categorymodel.setData(new CategoryBo().gets(categorymodel.getPage(), pageSize, categorymodel.getSearchValue()));
			} else {
				categorymodel = new CategoryPaginationResultModel(page, pageSize, rowCount, searchValue, data);
			}

			model.addAttribute("category_model", categorymodel);
			
			session.setAttribute("CATEGORY_SESSION", categorymodel);
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
	@RequestMapping("/category_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "category/category_create";

			String title = "Tạo mới một loại sản phẩm.";

			CategoryBean categoryBean = new CategoryBean();
			categoryBean.setCategoryID(0);

			model.addAttribute("title_category", title);
			model.addAttribute("categoryBean", categoryBean);

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
	@RequestMapping("/category_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "category/category_create";

			String title = "Cập nhật một loại sản phẩm.";

			int id = 0;

			try {
				if (request.getParameter("categoryID") != "" && request.getParameter("categoryID") != null)
					id = Integer.parseInt(request.getParameter("categoryID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("category_admin");
			}

			CategoryBean categoryBean = new CategoryBo().get(id);

			if (categoryBean == null) {
				response.sendRedirect("category_admin");
			}

			model.addAttribute("title_category", title);
			model.addAttribute("categoryBean", categoryBean);

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
	@RequestMapping("/category_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "category/category_view";

			CategoryBean categoryBean = new CategoryBean();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào categoryBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("categoryID") != "" && request.getParameter("categoryID") != null)
				categoryBean.setCategoryID(Integer.parseInt(request.getParameter("categoryID")));
			else {
				response.sendRedirect("category_admin");
				model.addAttribute("categoryID_err", "id sản phẩm không tồn tại");
			}

			if (request.getParameter("categoryName") != "" && request.getParameter("categoryName") != null)
				categoryBean.setCategoryName(request.getParameter("categoryName"));
			else {
				model.addAttribute("categoryName_err", "tên không được để trống.");
				isValid = true;
			}

			if (request.getParameter("categoryDescription") != ""
					&& request.getParameter("categoryDescription") != null)
				categoryBean.setDescription((request.getParameter("categoryDescription")));
			else {
				model.addAttribute("categoryDescription_err", "mô tả không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (categoryBean.getCategoryID() > 0)
					title = "Cập nhật một loại sản phẩm.";
				else
					title = "Tạo mới một loại sản phẩm";

				model.addAttribute("title_category", title);
				model.addAttribute("categoryBean", categoryBean);
				return new ModelAndView("category/category_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (categoryBean.getCategoryID() > 0) {
				new CategoryBo().update(categoryBean);
				response.sendRedirect("category_admin");
			} else {
				new CategoryBo().add(categoryBean);
				response.sendRedirect("category_admin");
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
	@RequestMapping("/category_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "category/category_delete";

			int id = 0;

			try {
				if (request.getParameter("categoryID") != "" && request.getParameter("categoryID") != null)
					id = Integer.parseInt(request.getParameter("categoryID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("category_admin");
			}

			if (request.getMethod() == "POST")
				new CategoryBo().delete(id);

			CategoryBean categoryBean = new CategoryBo().get(id);

			if (categoryBean == null || categoryBean.getCategoryID() <= 0)
				response.sendRedirect("category_admin");

			model.addAttribute("categoryBo", new CategoryBo());
			model.addAttribute("categoryBean", categoryBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}
}
