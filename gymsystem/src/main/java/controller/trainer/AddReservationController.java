package controller.trainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.dao.jdbc.ExerciseDAO;
import model.service.UserManager;

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
    	
    	String exerciseName = request.getParameter("exerciseName");
    	System.out.println("운동 이름: " + exerciseName);
    	
    	String difficulty = request.getParameter("difficulty");
    	System.out.println("운동 강도: " + difficulty);
    	
    	
     	String exerciseDay = request.getParameter("exerciseDay");
    	//String departTime = request.getParameter("departTime");
    	System.out.println("운동 요일: " + exerciseDay);
    	
    	String exerciseTime = request.getParameter("exerciseTime");
    	//String arrTime = request.getParameter("arrTime");
       	System.out.println("운동 시작 시간: " + exerciseTime);
    	
    	String trainerId = (String) session.getAttribute("userId");
    	System.out.println("트레이너 id: " + trainerId);
    	
    	String exerciseType = request.getParameter("exerciseType");
    	System.out.println("운동 카테고리: " + exerciseType);
    	
    	Exercise newExercise = new Exercise(trainerId, exerciseName, exerciseDay, exerciseTime, difficulty, exerciseType);
    	
		try {
			UserManager userManager = UserManager.getInstance();
			int r = userManager.createExercise(newExercise);
			if (r == 1) {
				System.out.println("트레이너 운동 추가 성공");
			}
			
		} catch(Exception e) {
			System.out.println("트레이너 운동 추가 실패, 등록다시");
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			//request.setAttribute("newItem", newItem);
			return "/trainer/addReservation.jsp";
		}
		return "redirect:/trainer/exercise/search";
	}

}
