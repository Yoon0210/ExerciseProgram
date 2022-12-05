package controller.review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Review;
import model.service.UserManager;

public class CreateReviewController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(CreateReviewController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Review review = new Review(null,
				Integer.parseInt(request.getParameter("workoutId")),
				request.getParameter("reviewTitle"),
				request.getParameter("reviewContent"),
				Integer.parseInt(request.getParameter("score")), 0
				);
		
		try {
			String userId = UserSessionUtils.getLoginUserId(request.getSession());
			review.setUserId(userId);
			
			UserManager manager = UserManager.getInstance();
			manager.createReview(review);
			
	    	log.debug("Create Review : {}", review);
	        return "redirect:/review/list";	// 성공 시 커뮤니티 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("review", review);
			return "/review/reviewList.jsp";
		}
	}

}
