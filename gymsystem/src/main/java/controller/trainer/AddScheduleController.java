package controller.trainer;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Schedule;
import model.dao.jdbc.ExerciseDAO;

public class AddScheduleController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AddScheduleController내부");
		//로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) { //if (!UserSessionUtils.hasLogined(request.getSession()))
    		return "redirect:/user/main";
        }
    	
    	HttpSession session = request.getSession();
    	request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
    	
    	int scheId = Integer.valueOf( request.getParameter("scheId") );
    	System.out.println("scheId: " + scheId);
    	
    	String scheName = request.getParameter("scheName");
    	System.out.println("scheName: " + scheName);
    	
    	Date scheTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("scheTime"));
    	System.out.println("scheTime: " + scheTime);
    	
    	String scheDescription = request.getParameter("scheDescription");
    	System.out.println("scheDescription: " + scheDescription);
    	
		int exerciseId = Integer.valueOf(request.getParameter("clickId"));
		System.out.println("이게 여기까지 걸쳐 넘어오는지 모르겠음(클릭한 아이디값): " + exerciseId);
		
		Schedule newSchedule = new Schedule(scheId, scheName, scheTime, scheDescription, exerciseId);
		
		try {
			ExerciseDAO exerciseDao = new ExerciseDAO();
			int r = exerciseDao.createScheduleByTrainer(newSchedule);
			
			if(r == 1) {
				System.out.println("트레이너 스케줄 추가 성공, 운동관리창으로 이동");
				return "/trainer/addSchedule.jsp";
			}
			else
				return "redirect:/user/main";
		} catch(Exception e) {
			System.out.println("스케줄 추가 실패");
			request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			return "redirect:/user/main";
		}
	}

}