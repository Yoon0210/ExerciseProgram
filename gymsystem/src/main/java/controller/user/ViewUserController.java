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
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		
		ReservationDAO reservationdao = new ReservationDAO();
	    List <Reservation> reservation = new ArrayList<Reservation>();
		
		System.out.println("뷰 유저 컨트롤러 내부");

		//마이페이지로 들어온 경우 userId==현재 유저 아이디로 설정
		if(request.getServletPath().equals("/user/mypage")) {
			userId = UserSessionUtils.getLoginUserId(request.getSession());

		}
		else {
			userId = request.getParameter("userId");
		}
		
		int resId;
		String resUserId;
		int resExerId;

		//신청 취소
		if(request.getServletPath().equals("/user/mypage/cancel")) {
			resId = Integer.parseInt(request.getParameter("reservationId"));
			resUserId = request.getParameter("resUserId");
			resExerId = Integer.parseInt(request.getParameter("resExerId"));
			int success = reservationdao.updateStatus(resId, resUserId, resExerId, "취소");
			
			if(success == 0) {
				System.out.print("취소 실패");
			}
			return "redirect:/user/mypage";
		}
		
		//신청 취소/거절 상태인 목록 삭제
		if(request.getServletPath().equals("/user/mypage/delete")) {
			resId = Integer.parseInt(request.getParameter("reservationId"));
			int success = reservationdao.delete(resId);
			
			if(success == 0) {
				System.out.print("삭제 실패");
			}
			return "redirect:/user/mypage";
		}
		
    	User user = null;
		try {
			user = manager.findUser(userId);	// 사용자 정보 검색
			reservation = reservationdao.searchReservationByUser(userId); //사용자의 운동 정보 검색
			//System.out.println(reservation.size());
		} catch (UserNotFoundException e) {				
	        return "redirect:/admin/user";
		}	
		
		request.setAttribute("reservation", reservation);
		request.setAttribute("user", user);		// 사용자 정보 저장				
		return "/user/view.jsp";				// 사용자 보기 화면으로 이동
    }
}
