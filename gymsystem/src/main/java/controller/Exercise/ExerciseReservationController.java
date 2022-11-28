package controller.Exercise;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.dao.jdbc.ReservationDAO;

public class ExerciseReservationController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservationDAO scheDao = new ReservationDAO();
    	HttpSession session = request.getSession();
    	String user_id = (String) session.getAttribute("userId");
    	int exercise_id = Integer.parseInt(request.getParameter("exerciseReservation"));
    	
		scheDao.itemReservationByUser(exercise_id, user_id);
		return "redirect:/user/main.jsp";
	}

}