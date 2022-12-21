package controller.Exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;

import model.Exercise;

import model.User;
import model.dao.jdbc.UserDAO;
import model.dao.mybatis.ExerciseSessionRepository;

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


		ExerciseSessionRepository exerciseSessionRepository = new ExerciseSessionRepository();

		HttpSession session = request.getSession();

		Map<String, Object> condition = new HashMap<String, Object>();

		condition.put("trainerId", null);
		condition.put("difficultyType", null);
		condition.put("exerciseType", null);

		if (request.getServletPath().equals("/exercise/list")) {
			session.setAttribute("trainerId", null);
			session.setAttribute("difficultyType", null);
			session.setAttribute("exerciseType", null);
		}
		if (request.getServletPath().equals("/exercise/search")) {
			if (request.getParameter("trainerId") != null && !request.getParameter("trainerId").equals("")) {
				session.setAttribute("trainerId", request.getParameter("trainerId"));
				condition.put("trainerId", request.getParameter("trainerId"));
			} else {
				session.setAttribute("trainerId", null);
				condition.put("trainerId", null);
			}

			if (request.getParameter("difficultyType") != null && !request.getParameter("difficultyType").equals("")) {
				session.setAttribute("difficultyType", request.getParameter("difficultyType"));
				condition.put("difficultyType", request.getParameter("difficultyType"));
			} else {
				session.setAttribute("difficultyType", null);
				condition.put("difficultyType", null);
			}

			if (request.getParameter("exerciseType") != null && !request.getParameter("exerciseType").equals("")) {
				session.setAttribute("exerciseType", request.getParameter("exerciseType"));
				condition.put("exerciseType", request.getParameter("exerciseType"));
			} else {
				session.setAttribute("exerciseType", null);
				condition.put("exerciseType", null);
			}
		}
		try {
			List<Exercise> exerciseList = exerciseSessionRepository.findExerciseByCondition(condition);
			session.setAttribute("exerciseList", exerciseList);

			List<User> trainerList = new UserDAO().FindTrainerListByType("trainer");
			session.setAttribute("trainerList", trainerList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/exercise/exerciseList.jsp";

	}

}
