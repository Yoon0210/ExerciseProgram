package controller.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.User;
import model.dao.jdbc.ExerciseDAO;
import model.dao.jdbc.TrainerDAO;

public class MainPageController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form"; // login form 요청으로 redirect
		}


		ExerciseDAO exerciseDAO = new ExerciseDAO();
		TrainerDAO trainerDAO = new TrainerDAO();
		
		List<Exercise> eList =exerciseDAO.findTop3Exercise();
		List<User> trList = trainerDAO.findTop3Trainer();
		
		System.out.println(eList);
		
		
		//메인페이지에 top 3 운동강좌/강사 표시
		request.setAttribute("topEList", eList);
		request.setAttribute("topTrList", trList);

		return "/main.jsp"; //메인페이지로 연결
	}

}
