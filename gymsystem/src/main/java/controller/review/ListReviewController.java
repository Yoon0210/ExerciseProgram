package controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.Workout;
import model.Trainer;
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
		int workoutType = -1;
		if (request.getParameter("workoutType") != null)
			workoutType = Integer.parseInt(request.getParameter("workoutType"));

		String orderBy;
		if(request.getParameter("orderType") != null && !request.getParameter("orderType").equals(""))
			orderBy = request.getParameter("orderType");
		else orderBy = "reviewId DESC";
		
		String searchContent;
		if (request.getParameter("searchContent") != null && !request.getParameter("searchContent").equals("")) {
			searchContent = request.getParameter("searchContent");
		}
		else searchContent = "-";

		UserManager manager = UserManager.getInstance();

		List<Review> reviewList = manager.findReviewList(workoutType, orderBy, searchContent);
//		List<Review> reviewList = manager.findReviewList(currentPage, countPerPage, request.getParameter("orderType"));

		List<Trainer> trList = (manager).findTrainerList();
		List<Workout> wList = manager.findWorkoutList();
//		List<Likey> lList = manager.findLikeyList(UserSessionUtils.getLoginUserId(request.getSession()));

		request.setAttribute("reviewList", reviewList);
		request.setAttribute("trList", trList);
		request.setAttribute("wList", wList);
		request.setAttribute("orderType", orderBy);
		request.setAttribute("workoutType", workoutType);

		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));

		return "/review/reviewList.jsp";
	}

}
