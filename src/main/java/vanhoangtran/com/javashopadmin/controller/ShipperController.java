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
	 * t???o m???i m???t lo???i s???n ph???m
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

			String title = "T???o m???i m???t lo???i s???n ph???m.";

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
	 * c???p nh???t m???t s???n ph???m d???a v??o id
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

			String title = "C???p nh???t m???t lo???i s???n ph???m.";

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
	 * l??u s???n ph???m
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
			 * l???y d??? li???u t??? view create ki???m tra d??? li???u ???? t???n t???i hay kh??ng n???u c?? th??
			 * g??n v??o ShipperBean c??n kh??ng th?? tr??? v??? m???t th??ng b??o err
			 */
			if (request.getParameter("shipperID") != "" && request.getParameter("shipperID") != null)
				shipperBean.setShipperID(Integer.parseInt(request.getParameter("shipperID")));
			else {
				response.sendRedirect("shipper_admin");
				model.addAttribute("shipperID_err", "id s???n ph???m kh??ng t???n t???i");
			}

			if (request.getParameter("shipperName") != "" && request.getParameter("shipperName") != null)
				shipperBean.setShipperName(request.getParameter("shipperName"));
			else {
				model.addAttribute("shipperName_err", "t??n kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			if (request.getParameter("shipperPhone") != "" && request.getParameter("shipperPhone") != null)
				shipperBean.setPhone(request.getParameter("shipperPhone"));
			else {
				model.addAttribute("shipperPhone_err", "s??? ??i???n tho???i kh??ng ???????c ????? tr???ng.");
				isValid = true;
			}

			// ki???m tra v?? tr??? l???i create ????? c???p nh???t l???i cho ????ng th??ng tin
			if (isValid == true) {
				if (shipperBean.getShipperID() > 0)
					title = "C???p nh???t m???t lo???i s???n ph???m.";
				else
					title = "T???o m???i m???t lo???i s???n ph???m";

				model.addAttribute("title_shipper", title);
				model.addAttribute("shipperBean", shipperBean);
				return new ModelAndView("shipper/shipper_create");
			}

			// ki???m tr??? v?? th??m ho???c c???t nh???t d??? li???u
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
	 * x??a m???t s???n ph???m d???a v??o id
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
