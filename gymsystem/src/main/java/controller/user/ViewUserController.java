package controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserManager;
import model.service.UserNotFoundException;
import model.Reservation;
import model.User;
import model.dao.jdbc.ReservationDAO;

public class ViewUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		UserManager manager = UserManager.getInstance();
		String userId;// = request.getParameter("userId");
		
		ReservationDAO reservationdao = new ReservationDAO();
	    List <Reservation> reservation = new ArrayList<Reservation>();
		
		//여기 컨트롤러로 잘 들어왔는지 확인점..
		System.out.println("뷰 유저 컨트롤러 내부");

		if(request.getServletPath().equals("/user/mypage")) {
			userId = UserSessionUtils.getLoginUserId(request.getSession());

		}
		else {
			userId = request.getParameter("userId");
		}
		
    	User user = null;
		try {
			user = manager.findUser(userId);	// 사용자 정보 검색
			reservation = reservationdao.searchReservationByUser(userId); //사용자의 운동 정보 검색
			System.out.println(reservation.size());
		} catch (UserNotFoundException e) {				
	        return "redirect:/user/list";
		}	
		
		request.setAttribute("reservation", reservation);
		request.setAttribute("user", user);		// 사용자 정보 저장				
		return "/user/view.jsp";				// 사용자 보기 화면으로 이동
    }
}
