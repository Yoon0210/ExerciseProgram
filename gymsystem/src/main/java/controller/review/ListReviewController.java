package controller.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.Report;
import model.Review;
import model.dao.mybatis.ReviewSessionRepository;
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
		ReviewSessionRepository reviewRepository = new ReviewSessionRepository();
		
		HttpSession session = request.getSession();
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(session));
		
		Map<String, Object> condition = new HashMap<String, Object>();
		
		condition.put("workoutType", null);
		condition.put("orderType", "r.reviewId");
		condition.put("searchContent", null);
		
		//전체 리뷰 검색
		if(request.getServletPath().equals("/review/list")) {
			session.setAttribute("orderType", "r.reviewId");
			session.setAttribute("workoutType", null);
			session.setAttribute("searchContent", null);
			condition.put("orderType", session.getAttribute("orderType"));
		}
		
		//조건 검색
		if(request.getServletPath().equals("/review/search")) {
			if (request.getParameter("workoutType") != null && !request.getParameter("workoutType").equals("전체")) {
				session.setAttribute("workoutType", Integer.parseInt(request.getParameter("workoutType")));
				condition.put("workoutType", Integer.parseInt(request.getParameter("workoutType")));
			} else {
				session.setAttribute("workoutType", null);
				condition.put("workoutType", null);
			}

			if(request.getParameter("orderType") != null && !request.getParameter("orderType").equals("")) {
				session.setAttribute("orderType", request.getParameter("orderType"));
				condition.put("orderType", request.getParameter("orderType"));
			} else {
				session.setAttribute("orderType", "r.reviewId");
				condition.put("orderType", "r.reviewId");
			}
			
			if (request.getParameter("searchContent") != null && !request.getParameter("searchContent").equals("")) {
				session.setAttribute("searchContent", request.getParameter("searchContent"));
				condition.put("searchContent", request.getParameter("searchContent"));
			} else {
				session.setAttribute("searchContent", null);
				condition.put("searchContent", null);
			}
		}
		
		//리뷰 삭제
		if(request.getServletPath().equals("/review/delete")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			try {
				manager.removeReview(reviewId);
			} catch (Exception e) {		
	            request.setAttribute("removeFailed", true);
	            request.setAttribute("exception", e);
				return "/review/reviewList.jsp"; // 삭제 실패시 리뷰 페이지로 오류메세지 전달
			}
			return "redirect:/review/list"; //삭제 후 리뷰 페이지로 리다이렉트
		}
		
		//리뷰 신고
		if(request.getServletPath().equals("/review/report")) {
			Report report = new Report(
					UserSessionUtils.getLoginUserId(request.getSession()), Integer.parseInt(request.getParameter("reportReviewId")), request.getParameter("reportReason")
					);
			
			manager.createReport(report);
		}
		
		try {
			List<Review> reviewList = reviewRepository.findReviewByCondition(condition);
			List<Exercise> wList = manager.findExerciseName();
			session.setAttribute("reviewList", reviewList);
			session.setAttribute("wList", wList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
//		List<Review> reviewList = manager.findReviewList(currentPage, countPerPage, request.getParameter("orderType"));
		return "/review/reviewList.jsp"; //리뷰 페이지로 이동
	}

}
