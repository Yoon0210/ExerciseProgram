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

		//운동 강좌 삭제
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

		int resId;
		String resUserId;
		int resExerId;
		
		//신청 거절
		if (request.getServletPath().equals("/reservation/reject")) {
			resId = Integer.parseInt(request.getParameter("reservationId"));
			resUserId = request.getParameter("resUserId");
			resExerId = Integer.parseInt(request.getParameter("resExerId"));
			reservationDAO.updateStatus(resId, resUserId, resExerId, "거절");
			return "redirect:/trainer/check";
		}
		
		//신청 승인
		if (request.getServletPath().equals("/reservation/accept")) {
			resId = Integer.parseInt(request.getParameter("reservationId"));
			resUserId = request.getParameter("resUserId");
			resExerId = Integer.parseInt(request.getParameter("resExerId"));
			reservationDAO.updateStatusAccept(resId, resUserId, resExerId);
			return "redirect:/trainer/check";

		}

		//내가 개설한 강좌, 내 강좌 신청자 보기
		if (request.getServletPath().equals("/trainer/check")) {
			try {
				List<Exercise> exerciseList = exerciseDao.findExerciseByTrainer(trainerId);

				List<Reservation> reservations = reservationDAO.searchReservationByTrainer(trainerId);
				request.setAttribute("exerciseList", exerciseList);
				request.setAttribute("resList", reservations);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "/trainer/checkReservation.jsp";
	}

}