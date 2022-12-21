package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Exercise.ListExerciseController;
import controller.admin.ReportPageController;
import controller.likey.ClickLikeyController;
import controller.review.CreateReviewController;
import controller.review.ListReviewController;
import controller.trainer.AddReservationController;
import controller.trainer.CheckReservationController;
import controller.timetable.SendExerciseInfoController;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
//      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
//      mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/register", new RegisterUserController());

        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
//      mappings.put("/user/update/form", new UpdateUserFormController());
//      mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/view", new ViewUserController());
        mappings.put("/user/mypage", new ViewUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/mypage/cancel", new ViewUserController());
        mappings.put("/user/mypage/delete", new ViewUserController());
        
        mappings.put("/user/delete", new DeleteUserController());
        
        mappings.put("/review/list", new ListReviewController());
        mappings.put("/review/create/form", new CreateReviewController());
        mappings.put("/review/delete", new ListReviewController());
        mappings.put("/review/search", new ListReviewController());
        mappings.put("/review/like", new ClickLikeyController());
        mappings.put("/review/report", new ListReviewController());
        
        mappings.put("/admin", new ForwardController("/admin/adminPage.jsp"));
        mappings.put("/admin/report", new ReportPageController());
        mappings.put("/admin/report/view", new ReportPageController());
        mappings.put("/admin/report/delete", new ReportPageController());
        mappings.put("/admin/report/return", new ReportPageController());
        mappings.put("/admin/user", new ListUserController());
        
        mappings.put("/exercise/search", new ListExerciseController());
        mappings.put("/exercise/list", new ListExerciseController());
        mappings.put("/exercise/reservation", new ListExerciseController());
        mappings.put("/exercise/timetable", new SendExerciseInfoController());
        
        mappings.put("/trainer/add", new AddReservationController());
        mappings.put("/trainer/exercise/form", new ForwardController("/trainer/addReservation.jsp"));
        
        mappings.put("/trainer/check", new CheckReservationController());
        mappings.put("/trainer/delete", new CheckReservationController());
        
        mappings.put("/reservation/accept", new CheckReservationController());
        mappings.put("/reservation/reject", new CheckReservationController());
        
        
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}