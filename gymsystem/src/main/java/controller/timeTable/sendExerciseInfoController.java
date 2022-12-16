package controller.timeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.manager.util.SessionUtils;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.dao.jdbc.ExerciseDAO;
import model.Exercise;

public class sendExerciseInfoController  implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("sendExerciseInfo Controller내부");
		
		//로그인 여부 확인 : 로그인 안 했다면 로그인 폼으로 리다이렉트
    	if (!UserSessionUtils.hasLogined(request.getSession())) { 
    		return "redirect:/user/login/form"; 
        }
    	
    	try {
    		//운동 스케줄 검색할 유저 아이디 받아오기 
        	String userId = UserSessionUtils.getLoginUserId(request.getSession());

			//운동 스케줄 데이터 받아오기 
	    	ExerciseDAO exercisedao = new ExerciseDAO();
	    	List<Exercise> exercise = new ArrayList<Exercise>();
	    	Exercise exercisedto = new Exercise();
	    	
	    	//user면 
	    	if(request.getSession().getAttribute("userType").equals("user")) {
	    		exercise =  exercisedao.findExerciseScheduleByUserId(userId);
	    	}
	    	else { //트레이너면 
	    		exercise =  exercisedao.findExerciseScheduleByUserId(userId);
	    	}
	    	
	    	int times = 13;
	    	int days = 7;
	    	
	    	String exerDay = null; //user가 추가한 운동정보의 요일 담는 배열
	    	String exerTime = null; //user가 추가한 운동정보의 시간 담는 배열 
	    	
	    	int dayIndex = 0;
	    	int timeIndex = 0;
	    	String[][] sche = new String[times][days];
	    	
	    	for(int i=0; i<exercise.size(); i++){
	    		exerDay = ((Exercise) exercise).getExerciseDay();  // ex)금
		    	exerTime = ((Exercise) exercise).getExerciseTime(); // ex)16시 
		    	
		    	System.out.println(exerDay);
		    	System.out.println(exerTime);
		    	
	    		dayIndex = exercisedto.getTime(exerDay);
	    		timeIndex = exercisedto.getWeekday(exerTime);
	    		sche[timeIndex][dayIndex] = exercise.get(i).getExerciseName()+"\n"
						+exercise.get(i).getDifficulty()+"\n"+exercise.get(i).getTrainerName();
	    	}
	    	
	    	request.setAttribute("sche",sche);
	    	return "/timetable/timetable.jsp";
	    	
		} catch (Exception e) {		
           
		}

		return null;
	}

}
