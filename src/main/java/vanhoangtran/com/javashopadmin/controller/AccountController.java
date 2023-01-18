package vanhoangtran.com.javashopadmin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vanhoangtran.com.javashopadmin.model.dao.AccountDao;

@Controller
public class AccountController {
	@RequestMapping("/login_admin")
	public ModelAndView login(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {
		try {
			String path = "account/login_view";

			AccountDao eBo = new AccountDao();

			String email = request.getParameter("admin_email");
			String pass = request.getParameter("admin_password");

			if (session.getAttribute("session__adminUser") != null)
				session.removeAttribute("session__adminUser");

			if (eBo.login(email, pass)) {
				session.setAttribute("session__adminUser", email);
				response.sendRedirect("home_admin");
			} else {
				model.addAttribute("model_tb_admin", "đăng nhập thất bại");
				model.addAttribute("jsp_email", email);
			}

			return new ModelAndView(path);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping("/dangXuatadmin")
	public ModelAndView logout(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session) {

		session.invalidate();
		return new ModelAndView("account/login_view");
	}

}
