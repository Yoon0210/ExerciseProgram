package controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.dao.jdbc.ReportDAO;
import model.dao.jdbc.ReviewDAO;
import model.service.UserManager;

public class ReportPageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form"; // login form 요청으로 redirect
		}
//		if(!UserSessionUtils.getLoginUserId(request.getSession()).equals("admin")) {
//			request.setAttribute("adminException", "관리자가 아닙니다.");
//			return "redirect:/user/login/form";
//		}

		/*
		 * String currentPageStr = request.getParameter("currentPage"); int currentPage
		 * = 1; if (currentPageStr != null && !currentPageStr.equals("")) { currentPage
		 * = Integer.parseInt(currentPageStr); }
		 */

		ReportDAO reportDAO = new ReportDAO();
		UserManager userManager = UserManager.getInstance();
		
		request.getSession().setAttribute("reportList", reportDAO.findReportList());
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		
		if (request.getServletPath().equals("/admin/report/view")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			request.setAttribute("report", reportDAO.findReport(request.getParameter("reportUserId"), reviewId));
			request.setAttribute("review", new ReviewDAO().findReview(reviewId));
			return "/admin/reportView.jsp";
			
		}

		if(request.getServletPath().equals("/admin/report/delete")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			userManager.removeReview(reviewId);
			reportDAO.remove(request.getParameter("reportUserId"), reviewId);
			
			return "redirect:/admin/report";
		}
		if(request.getServletPath().equals("/admin/report/return")) {
			reportDAO.remove(request.getParameter("reportUserId"), Integer.parseInt(request.getParameter("reviewId")));
			return "redirect:/admin/report";
		}

		return "/admin/report.jsp";
	}

}
