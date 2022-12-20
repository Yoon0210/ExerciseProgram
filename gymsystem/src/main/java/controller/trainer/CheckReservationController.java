package controller.trainer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.Reservation;
import model.dao.jdbc.ExerciseDAO;
import model.dao.jdbc.ReservationDAO;

public class CheckReservationController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("여기는 예약추가 눌렀을 시 나오는 checkReservation컨트롤러임");
		// 로그인 여부
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}

		HttpSession session = request.getSession();
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
		String trainerId = (String) session.getAttribute("userId");
		ExerciseDAO exerciseDao = new ExerciseDAO();
		ReservationDAO reservationDAO = new ReservationDAO();

		if (request.getServletPath().equals("/trainer/delete")) {
			int exerciseId = Integer.parseInt(request.getParameter("exerciseReservation"));
			try {
				exerciseDao.deleteExerciseByTrainer(exerciseId);
			} catch (Exception e) {
				request.setAttribute("deleteFailed", true);
				request.setAttribute("exception", e);
				return "/trainer/checkReservation.jsp";
			}
			return "redirect:/trainer/check";
		}

		if (request.getServletPath().equals("/trainer/check")) {
			List<Exercise> exerciseList = exerciseDao.findExerciseByTrainer(trainerId);
//		exerciseList = ExerciseDao.findExerciseByTrainer(TrainerId); //가이드가 맡은 상품객체 리스트 반환
			
			List<Reservation> reservations = reservationDAO.searchReservationByTrainer(trainerId);
			
			request.setAttribute("exerciseList", exerciseList);
			request.setAttribute("resList", reservations);
			
		}

		return "/trainer/checkReservation.jsp";
	}

}