package controller.timeTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	    	
	    	int times = 7;
	    	int days = 5;
	    	
	    	String exerDay = null;
	    	String exerTime = null;
	    	for(Exercise e : exercise) {
	    		exerDay = e.getExerciseDay(); //금
		    	exerTime = e.getExerciseTime();	//16시 
	    	}
	    	
	    	int dayIndex = exercisedto.getTime(exerDay);
	    	int timeIndex = exercisedto.getWeekday(exerTime);
	    	
	    	String[][] sche = new String[times][days];
	    	
	    	//2차원 배열에 해당 값 넣기 
	    	for(int i=0; i<times; i++) {
	    		for(int j=0; j<days; j++) {
	    			sche[timeIndex][dayIndex] = exercise.get(i).getExerciseName()+"\n"
	    								+exercise.get(i).getDifficulty()+"\n"+exercise.get(i).getTrainerName();
	    		}
	    	}
	    	
	    	return "/timetable/timetable.jsp";
	    	
		} catch (Exception e) {		
           
		}

		return null;
	}

}
