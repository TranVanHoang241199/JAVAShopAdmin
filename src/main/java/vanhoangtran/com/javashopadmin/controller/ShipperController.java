package vanhoangtran.com.javashopadmin.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vanhoangtran.com.javashopadmin.model.bean.ShipperBean;
import vanhoangtran.com.javashopadmin.model.bo.ShipperBo;
import vanhoangtran.com.javashopadmin.model.helps.ShipperPaginationResultModel;

@Controller
public class ShipperController {
	@RequestMapping("/shipper_admin")
	public ModelAndView getShipper(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "shipper/shipper_view";

			int pageSize = 10;
			int rowCount = 0;
			int page = 1;
			String searchValue = "";

			ShipperPaginationResultModel  shipperModel = new ShipperPaginationResultModel ();

			rowCount = new ShipperBo().count(searchValue);
			ArrayList<ShipperBean> data = new ShipperBo().gets(page, pageSize, searchValue);

			if (session.getAttribute("SHIPPER_SESSION") != null) {
				shipperModel = (ShipperPaginationResultModel) session.getAttribute("SHIPPER_SESSION");

				try {
					if (request.getParameter("shipper_page") != "" && request.getParameter("shipper_page") != null) {
						page = Integer.parseInt(request.getParameter("shipper_page"));
						shipperModel.setPage(page);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (request.getParameter("shipper_search") != null) {
					searchValue = request.getParameter("shipper_search");
					shipperModel.setSearchValue(searchValue);
					shipperModel.setPage(page);
				}

				shipperModel.setRowCount(new ShipperBo().count(shipperModel.getSearchValue()));

				shipperModel.setData(
						new ShipperBo().gets(shipperModel.getPage(), pageSize, shipperModel.getSearchValue()));

			} else {
				shipperModel = new ShipperPaginationResultModel(page, pageSize, rowCount, searchValue, data);
			}

			model.addAttribute("shipper_model", shipperModel);

			session.setAttribute("SHIPPER_SESSION", shipperModel);
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
	@RequestMapping("/shipper_admin-create")
	public ModelAndView create(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "shipper/shipper_create";

			String title = "Tạo mới một loại sản phẩm.";

			ShipperBean shipperBean = new ShipperBean();
			shipperBean.setShipperID(0);

			model.addAttribute("title_shipper", title);
			model.addAttribute("shipperBean", shipperBean);

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
	@RequestMapping("/shipper_admin-update")
	public ModelAndView update(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "shipper/shipper_create";

			String title = "Cập nhật một loại sản phẩm.";

			int id = 0;

			try {
				if (request.getParameter("shipperID") != "" && request.getParameter("shipperID") != null)
					id = Integer.parseInt(request.getParameter("shipperID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("shipper_admin");
			}

			ShipperBean shipperBean = new ShipperBo().get(id);

			if (shipperBean == null) {
				response.sendRedirect("shipper_admin");
			}

			model.addAttribute("title_shipper", title);
			model.addAttribute("shipperBean", shipperBean);

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
	@RequestMapping("/shipper_admin-save")
	public ModelAndView save(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "shipper/shipper_view";

			ShipperBean shipperBean = new ShipperBean();

			boolean isValid = false;

			String title = "";

			/*
			 * lấy dữ liệu từ view create kiểm tra dữ liệu đó tồn tại hay không nếu có thì
			 * gán vào ShipperBean còn không thì trả về một thông báo err
			 */
			if (request.getParameter("shipperID") != "" && request.getParameter("shipperID") != null)
				shipperBean.setShipperID(Integer.parseInt(request.getParameter("shipperID")));
			else {
				response.sendRedirect("shipper_admin");
				model.addAttribute("shipperID_err", "id sản phẩm không tồn tại");
			}

			if (request.getParameter("shipperName") != "" && request.getParameter("shipperName") != null)
				shipperBean.setShipperName(request.getParameter("shipperName"));
			else {
				model.addAttribute("shipperName_err", "tên không được để trống.");
				isValid = true;
			}

			if (request.getParameter("shipperPhone") != "" && request.getParameter("shipperPhone") != null)
				shipperBean.setPhone(request.getParameter("shipperPhone"));
			else {
				model.addAttribute("shipperPhone_err", "số điện thoại không được để trống.");
				isValid = true;
			}

			// kiểm tra và trả lại create để cập nhật lại cho đúng thông tin
			if (isValid == true) {
				if (shipperBean.getShipperID() > 0)
					title = "Cập nhật một loại sản phẩm.";
				else
					title = "Tạo mới một loại sản phẩm";

				model.addAttribute("title_shipper", title);
				model.addAttribute("shipperBean", shipperBean);
				return new ModelAndView("shipper/shipper_create");
			}

			// kiểm trả và thêm hoặc cật nhật dữ liệu
			if (shipperBean.getShipperID() > 0) {
				new ShipperBo().update(shipperBean);
				response.sendRedirect("shipper_admin");
			} else {
				new ShipperBo().add(shipperBean);
				response.sendRedirect("shipper_admin");
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
	@RequestMapping("/shipper_admin-delele")
	public ModelAndView delete(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "shipper/shipper_delete";

			int id = 0;

			try {
				if (request.getParameter("shipperID") != "" && request.getParameter("shipperID") != null)
					id = Integer.parseInt(request.getParameter("shipperID"));
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect("shipper_admin");
			}

			if (request.getMethod() == "POST")
				new ShipperBo().delete(id);

			ShipperBean shipperBean = new ShipperBo().get(id);

			if (shipperBean == null || shipperBean.getShipperID() <= 0)
				response.sendRedirect("shipper_admin");

			model.addAttribute("shipperBo", new ShipperBo());
			model.addAttribute("shipperBean", shipperBean);
			return new ModelAndView(path);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return null;
		}
	}
}
