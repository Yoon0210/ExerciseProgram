package controller.timetable;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Exercise;
import model.dao.jdbc.ExerciseDAO;

public class SendExerciseInfoController  implements Controller{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("sendExerciseInfo Controller내부");
		
		//로그인 여부 확인 : 로그인 안 했다면 로그인 폼으로 리다이렉트
    	if (!UserSessionUtils.hasLogined(request.getSession())) { 
    		return "redirect:/user/login/form"; 
        }
    	
    	try {
    		HttpSession session = request.getSession(); //세션 정보 받아오기 
    		
    		//운동 스케줄 검색할 유저 아이디 받아오기 
        	String userId = UserSessionUtils.getLoginUserId(session);
        	//System.out.println(userId); 제대로 들어옴 확인 

			//운동 스케줄 데이터 받아오기 
	    	ExerciseDAO exercisedao = new ExerciseDAO();
	    	List<Exercise> exercise = new ArrayList<Exercise>();
	    	Exercise exercisedto = new Exercise();
	    	
	    	//user면 
	    	if(UserSessionUtils.getLoginUserType(session).equals("user")) {
	    		exercise =  exercisedao.findExerciseScheduleByUserId(userId);
	    		//System.out.println(exercise.get(0).getExerciseDay()); 
	    		//System.out.println(exercise.get(0).getExerciseTime()); 
	    	}
	    	else { //트레이너면 
	    		exercise =  exercisedao.findExerciseByTrainer(userId);
	    		System.out.println(exercise);
	    	}
	    	
	    	int times = 13;
	    	int days = 7;
	    	
	    	String exerDay = null; //user가 추가한 운동정보의 요일 담는 str
	    	String exerTime = null; //user가 추가한 운동정보의 시간 담는 str
	    	
	    	int dayIndex = 0;
	    	int timeIndex = 0;
	    	String[][] sche = new String[times][days]; //운동 정보 담는 배열 
	    	
	    	for (int i = 0; i < 13; i++){ //times 행

	  		  for(int j=0; j<7; j++){ //days 열 
	  			sche[i][j] = "<td></td>";
	  		  }
	  	}
	    	
	    	for(int i=0; i<exercise.size(); i++){
	    		exerDay = exercise.get(i).getExerciseDay();  // ex)금
		    	exerTime = exercise.get(i).getExerciseTime(); // ex)21 
		    	
	    		dayIndex = Exercise.getWeekday(exerDay);
	    		timeIndex = Exercise.getTime(exerTime);
	    		sche[timeIndex][dayIndex] = "<td>"+exercise.get(i).getExerciseName()+"<br>"
						+exercise.get(i).getDifficulty()+"<br>"+exercise.get(i).getTrainerName()+"</td>";
	    	}
	    	
	    	request.setAttribute("sche",sche);
	    	return "/timetable/timetable.jsp";
	    	
		} catch (Exception e) {		
           
		}

		return null;
	}

}