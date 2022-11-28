package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class MainController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 확인
    	if (UserSessionUtils.hasLogined(request.getSession())) { //if (!UserSessionUtils.hasLogined(request.getSession()))
            //return "redirect:/user/login/form";		// login form 요청으로 redirect
    		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
        }
    	
    	request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));		
		
    	return "/user/main.jsp";
	}

}