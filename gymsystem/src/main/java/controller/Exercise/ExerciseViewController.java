package controller.Exercise;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Exercise;
import model.Schedule;
import model.dao.jdbc.ExerciseDAO;

public class ExerciseViewController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("운동뷰 컨트롤러 내부");
		
		ExerciseDAO ExerciseDao = new ExerciseDAO();
		
		int viewId = Integer.parseInt(request.getParameter("viewId"));
		System.out.println("선택한 운동 아이디: " + viewId);
		
		try {
			Exercise Exercise = ExerciseDao.searchexerciseById(viewId);
//			ArrayList<Schedule> scheList = (ArrayList<Schedule>) ExerciseDao.searchScheduleByID(viewId);
			
//			request.setAttribute("scheList", scheList);
//			System.out.println(scheList.size());
			request.setAttribute("viewExercise", Exercise);
			return "exercise/view.jsp";
		} catch(Exception e) {
			return "redirect:/user/login.jsp";
		}
	}

}