package controller.trainer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.dao.jdbc.ExerciseDAO;

public class AddReservationController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AddReservationController내부");
		//로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) { //if (!UserSessionUtils.hasLogined(request.getSession()))
    		return "redirect:/user/main";
        }
    	
    	HttpSession session = request.getSession();
    	request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
    	//SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

    	//int itemId = Integer.parseInt( request.getParameter("id") );
    	//System.out.println("상품 id: " + itemId);
    	
    	String name = request.getParameter("name");
    	System.out.println("운동 이름: " + name);
    	
    	String strength = request.getParameter("strength");
    	System.out.println("운동 강도: " + strength);
    	
    	
    	Date startTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startTime"));
    	//String departTime = request.getParameter("departTime");
    	System.out.println("운동 시작 날짜: " + startTime);
    	
    	Date endTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("endTime"));
    	//String arrTime = request.getParameter("arrTime");
    	System.out.println("운동 종료 날짜: " + endTime);
    	
    	String trainerId = (String) session.getAttribute("userId");
    	System.out.println("트레이너 id: " + trainerId);
    	
    	String category = request.getParameter("category");
    	System.out.println("운동 카테고리: " + category);
    	
    	Exercise newExercise = new Exercise(0, name, strength,startTime, endTime, trainerId, category);
    	
		try {
			ExerciseDAO exerciseDao = new ExerciseDAO();
			int r = exerciseDao.createExerciseByGuide(newExercise);
			System.out.println("트레이너 운동 추가 성공, 메인으로 리다이렉션");
			if(r == 1)
				return "redirect:/user/main";
			else
				return "addReservation.jsp";
		} catch(Exception e) {
			System.out.println("트레이너 운동 추가 실패, 등록다시");
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			//request.setAttribute("newItem", newItem);
			return "/trainer/addReservation.jsp";
		}
    	
	}

}