package controller.likey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.review.CreateReviewController;
import controller.user.UserSessionUtils;
import model.Likey;
import model.service.TrainerLikeyException;
import model.service.UserManager;

public class ClickLikeyController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(CreateReviewController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
		int reviewId = Integer.parseInt(request.getParameter("reviewId"));
		
		Likey likey = new Likey(userId, reviewId);
		
		if (!UserSessionUtils.hasLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
		
		if(UserSessionUtils.getLoginUserType(request.getSession()).equals("trainer")) {
			request.setAttribute("trainerLikeException", response);
		}
		try {
			UserManager manager = UserManager.getInstance();
			manager.createLikey(likey, (String) request.getSession().getAttribute("userType"));
			
	    	log.debug("Create Likey : {}", likey);
	        return "redirect:/review/search";	// 성공 시 리뷰 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 reviewList로 forwarding
            request.setAttribute("likeFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));
			return "/review/reviewList.jsp";
		}

	}

}
