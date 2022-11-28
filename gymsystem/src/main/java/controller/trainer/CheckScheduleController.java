package controller.trainer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Schedule;
import model.dao.jdbc.ExerciseDAO;

public class CheckScheduleController implements Controller{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부
		if(!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";
		}
		
		String itemName = request.getParameter("clickName");
		itemName = new String(itemName.getBytes("ISO8859_1"), "UTF-8");
		//itemName = new String(itemName.getBytes("ISO8859_1"), "UTF-8");
		System.out.println("이름이 이상하게 뜬다: " + itemName);
		int itemId = Integer.parseInt(request.getParameter("clickId"));
		System.out.println("아이디 이름: " + itemId);
		try {
			ExerciseDAO itemDao = new ExerciseDAO();
			List<Schedule> scheList = new ArrayList<Schedule>();
			scheList = itemDao.searchScheduleByID(itemId);
			request.setAttribute("checkItemList", scheList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("curUserId", UserSessionUtils.getLoginUserId(request.getSession()));	
		request.setAttribute("clickId", itemId);
		request.setAttribute("clickName", itemName);

		return "/guide/checkSchedule.jsp";
	}

}