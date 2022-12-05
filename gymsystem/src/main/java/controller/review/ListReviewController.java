package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.Report;
import model.Review;
import model.service.UserManager;

public class ListReviewController implements Controller {

//	 private static final int countPerPage = 6;	// 한 화면에 출력할 리뷰 수

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form"; // login form 요청으로 redirect
		}

		// 페이지
//    	String currentPageStr = request.getParameter("currentPage");	
//		int currentPage = 1;
//		if (currentPageStr != null && !currentPageStr.equals("")) {
//			currentPage = Integer.parseInt(currentPageStr);
//		}		
		
		UserManager manager = UserManager.getInstance();
		
		HttpSession session = request.getSession();
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(session));
		
		if(request.getServletPath().equals("/review/list")) {
			session.setAttribute("workoutType", -1);
			session.setAttribute("orderType", "reviewId DESC");
			session.setAttribute("searchContent", "-");
		}
		
		if(request.getServletPath().equals("/review/search")) {
			if (request.getParameter("workoutType") != null) {
				session.setAttribute("workoutType", Integer.parseInt(request.getParameter("workoutType")));
			}

			if(request.getParameter("orderType") != null && !request.getParameter("orderType").equals("")) {
				session.setAttribute("orderType", request.getParameter("orderType"));
			}
			
			if (request.getParameter("searchContent") != null && !request.getParameter("searchContent").equals("")) {
				session.setAttribute("searchContent", request.getParameter("searchContent"));
			} else {
				session.setAttribute("searchContent", "-");
			}
		}
		
		if(request.getServletPath().equals("/review/delete")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			try {
				manager.removeReview(reviewId);
			} catch (Exception e) {		
	            request.setAttribute("removeFailed", true);
	            request.setAttribute("exception", e);
				return "/review/reviewList.jsp";
			}
			return "redirect:/review/list";
		}
		
		if(request.getServletPath().equals("/review/report")) {
			Report report = new Report(
					UserSessionUtils.getLoginUserId(request.getSession()), Integer.parseInt(request.getParameter("reportReviewId")), request.getParameter("reportReason")
					);
			
			manager.createReport(report);
		}
		
		

		

		List<Review> reviewList = manager.findReviewList(Integer.parseInt(session.getAttribute("workoutType").toString()),
				session.getAttribute("orderType").toString(),
				session.getAttribute("searchContent").toString());
//		List<Review> reviewList = manager.findReviewList(currentPage, countPerPage, request.getParameter("orderType"));

//		List<Trainer> trList = (manager).findTrainerList();
		List<Exercise> wList = manager.findExerciseName();


		session.setAttribute("reviewList", reviewList);
		session.setAttribute("wList", wList);

		
		

		return "/review/reviewList.jsp";
	}

}
