package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Workout;
import model.Trainer;
import model.Likey;
import model.Review;
import model.User;
import model.dao.jdbc.WorkoutDAO;
import model.dao.jdbc.LikeyDAO;
import model.dao.jdbc.ReviewDAO;
import model.dao.jdbc.UserDAO;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스. UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록
 * 하며, 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다. 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는
 * 클래스를 별도로 둘 수 있다.
 */
public class UserManager {
	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	private ReviewDAO reviewDAO;
	private WorkoutDAO workoutDAO;
	private LikeyDAO likeyDAO;
	private UserAnalysis userAanlysis;

	private UserManager() {
		try {
			userDAO = new UserDAO();
			reviewDAO = new ReviewDAO();
			workoutDAO = new WorkoutDAO();
			likeyDAO = new LikeyDAO();
			userAanlysis = new UserAnalysis(userDAO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UserManager getInstance() {
		return userMan;
	}

	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId()) == true) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		return userDAO.create(user);
	}

	public int update(User user) throws SQLException, UserNotFoundException {
		return userDAO.update(user);
	}

	public int remove(String userId) throws SQLException, UserNotFoundException {
		return userDAO.remove(userId);
	}

	public User findUser(String userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);

		if (user == null) {
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
		}
		return user;
	}

	public List<User> findUserList() throws SQLException {
		return userDAO.findUserList();
	}

	public List<User> findUserList(int currentPage, int countPerPage) throws SQLException {
		return userDAO.findUserList(currentPage, countPerPage);
	}

	public boolean login(String userId, String password)
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);

		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}

	public List<User> makeFriends(String userId) throws Exception {
		return userAanlysis.recommendFriends(userId);
	}

	public UserDAO getUserDAO() {
		return this.userDAO;
	}

	public Review createReview(Review review) throws SQLException {
		return reviewDAO.create(review);
	}

	public List<Review> findReviewList(int workoutId, String orderBy, String searchContent) throws SQLException {
		if( workoutId != -1 && !searchContent.equals("-")) {
			return reviewDAO.findReviewList(workoutId, orderBy, searchContent);
		}
		else if( workoutId == -1 && !searchContent.equals("-"))
			return reviewDAO.findReviewList(orderBy, searchContent);
		else if(workoutId != -1 && searchContent.equals("-")) {
			return reviewDAO.findReviewList(workoutId, orderBy);
		}
		else
			return reviewDAO.findReviewList(orderBy);
	}

	public boolean removeReview(int rId) throws SQLException, FailRemoveException {
		if(reviewDAO.remove(rId) != 1) {
			throw new FailRemoveException("정상적으로 삭제되지 않았습니다.다시 시도해주세요.");
		}
		return true;

	}

	public List<Workout> findWorkoutList() {
		return workoutDAO.findWorkoutList();
	}

	public List<Likey> findLikeyList(String userId) throws SQLException {
		return likeyDAO.findLikeyById(userId);
	}

	public boolean createLikey(Likey likey) throws SQLException, AlreadyLikeException {

		if (likeyDAO.create(likey) != 1) {
			throw new AlreadyLikeException("이미 추천한 글입니다.");
		}
		reviewDAO.updateLikeCount(likey.getReviewId());

		return true;
	}
}
