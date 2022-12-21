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

		ReportDAO reportDAO = new ReportDAO();
		UserManager userManager = UserManager.getInstance();
		
		request.getSession().setAttribute("reportList", reportDAO.findReportList());
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		
		//신고된 리뷰 내용 보기
		if (request.getServletPath().equals("/admin/report/view")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			request.setAttribute("report", reportDAO.findReport(request.getParameter("reportUserId"), reviewId));
			request.setAttribute("review", new ReviewDAO().findReview(reviewId));
			return "/admin/reportView.jsp"; //신고 리뷰 창으로 연결
			
		}

		//신고된 리뷰 삭제 처리
		if(request.getServletPath().equals("/admin/report/delete")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			userManager.removeReview(reviewId);
			reportDAO.remove(request.getParameter("reportUserId"), reviewId);
			
			return "redirect:/admin/report"; //신고 리스트로 리다이렉트
		}
		
		//신고 취소
		if(request.getServletPath().equals("/admin/report/return")) {
			reportDAO.remove(request.getParameter("reportUserId"), Integer.parseInt(request.getParameter("reviewId")));
			return "redirect:/admin/report";
		}

		return "/admin/report.jsp";//신고 리스트 페이지로 이동
	}

}
