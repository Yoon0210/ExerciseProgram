package controller.Exercise;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.dao.jdbc.ExerciseDAO;
import model.service.UserManager;

public class ListExerciseController implements Controller {

//	 private static final int countPerPage = 6;	// 한 화면에 출력할 리뷰 수

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 페이지
//    	String currentPageStr = request.getParameter("currentPage");	
//		int currentPage = 1;
//		if (currentPageStr != null && !currentPageStr.equals("")) {
//			currentPage = Integer.parseInt(currentPageStr);
//		}
		
		ExerciseDAO exerciseDao = new ExerciseDAO();
		List<Exercise> exerciseList = null;
		
		try {
			UserManager manager = UserManager.getInstance();
			
			HttpSession session = request.getSession();
			request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(session));
			
			if(request.getServletPath().equals("/exercise/list")) {
//				session.setAttribute("allContent", -1);
//				session.setAttribute("searchContent", "-");
				exerciseList = exerciseDao.allExerciseList();
			}
			
			if(request.getServletPath().equals("exercise/reservation")) {
				
			}
			
//			if(request.getServletPath().equals("/exercise/search")) {
//				if (request.getParameter("allContent") != null) {
//					session.setAttribute("allContent", Integer.parseInt(request.getParameter("allContent")));
//				}
//				if (request.getParameter("searchContent") != null && !request.getParameter("searchContent").equals("")) {
//					session.setAttribute("searchContent", request.getParameter("searchContent"));
//				} else {
//					session.setAttribute("searchContent", "-");
//				}
//				exerciseList = exerciseDao.searchStringExercisename(request.getParameter("searchContent"));
//				
//			}
			
//			List<Exercise> exerciseList = manager.findReviewList(Integer.parseInt(session.getAttribute("workoutType").toString()),
//					session.getAttribute("orderType").toString(),
//					session.getAttribute("searchContent").toString());
			
		
			session.setAttribute("exerciseList", exerciseList);
			
			return "/exercise/exerciseList.jsp";
		} catch(Exception e) {
			return "redirect:/user/login.jsp";
		}
		
		
		
//		if(request.getServletPath().equals("/exercise/delete")) {
//			int reviewId = Integer.parseInt(request.getParameter("exerciseId"));
//			try {
//				manager.removeReview(reviewId);
//			} catch (Exception e) {		
//	            request.setAttribute("removeFailed", true);
//	            request.setAttribute("exception", e);
//				return "/exercise/exerciseList.jsp";
//			}
//			return "redirect:/review/list";
//		}

	}

}
