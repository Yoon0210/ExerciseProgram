package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Review;

public class ReviewDAO {
	private JDBCUtil jdbcUtil = null;

	public ReviewDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public Review create(Review rev) throws SQLException {
		String sql = "INSERT INTO Review VALUES (reviewId_seq.nextval, ?, ?, ?, ?, ?, ?, DEFAULT)";
		Object[] param = new Object[] { rev.getUserId(), rev.getWorkoutId(), rev.getTitle(),
				rev.getContent(), rev.getScore(), 0 };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		String key[] = { "reviewId" }; // PK 컬럼의 이름
		try {
			jdbcUtil.executeUpdate(key); // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				int generatedKey = rs.getInt(1); // 생성된 PK 값
				rev.setReviewId(generatedKey); // id필드에 저장
			}
			return rev;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public Review findReview(String reviewId) throws SQLException {
		String sql = "SELECT r.reviewId, r.userid, r.workoutId, r.title, r.content, r.score, r.likecount, r.posteddate, e.exerciseName, t.username "
				+ "FROM Review r, Exercise e, UserInfo t " 
				+ "WHERE r.workoutId = e.exerciseId AND e.trainerId = t.userId AND reviewId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { reviewId }); // JDBCUtil에 query문과 매개 변수 설정
		Review comm = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				comm = new Review( // Review 객체를 생성하여 커뮤니티 정보를 저장
						rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"),
						rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"), rs.getString("postedDate") , rs.getString("exerciseName"), rs.getString("username"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return comm;
	}

	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
	 */

	public List<Review> findReviewList(String orderBy) throws SQLException {

		String sql = "SELECT r.reviewId, r.userid, r.workoutId, r.title, r.content, r.score, r.likecount, r.posteddate, e.exerciseName, t.username "
				+ "FROM Review r, Exercise e, UserInfo t " 
				+ "WHERE r.workoutId = e.exerciseId AND e.trainerId = t.userId "
				+ "ORDER BY " + orderBy;
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Review> revList = new ArrayList<Review>(); // Review들의 리스트 생성
			while (rs.next()) {
				Review review = new Review( // Review 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"),
						rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"), rs.getString("postedDate"), rs.getString("exerciseName"), rs.getString("username"));
				revList.add(review); // List에 Review 객체 저장
			}
			return revList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public List<Review> findReviewList(String orderBy, String searchContent) throws SQLException {

		String sql = "SELECT r.reviewId, r.userid, r.workoutId, r.title, r.content, r.score, r.likecount, r.posteddate, e.exerciseName, t.username "
				+ "FROM Review r, Exercise e, UserInfo t " 
				+ "WHERE r.workoutId = e.exerciseId AND e.trainerId = t.userId AND ( (Title LIKE ?) OR (Content LIKE ?) ) "
				+ "ORDER BY " + orderBy;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { "%" + searchContent + "%", "%" + searchContent + "%" }); // JDBCUtil에
																													// query문
																													// 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Review> revList = new ArrayList<Review>(); // Review들의 리스트 생성
			while (rs.next()) {
				Review review = new Review( // Review 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"),
						rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"), rs.getString("postedDate")
						, rs.getString("exerciseName"), rs.getString("username"));
				revList.add(review); // List에 Review 객체 저장
			}
			return revList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public List<Review> findReviewList(int workoutId, String orderBy, String searchContent) throws SQLException {

		String sql =  "SELECT r.reviewId, r.userid, r.workoutId, r.title, r.content, r.score, r.likecount, r.posteddate, e.exerciseName, t.username "
				+ "FROM Review r, Exercise e, UserInfo t " 
				+ "WHERE r.workoutId = e.exerciseId AND e.trainerId = t.userId AND WorkoutId=? AND ( (Title LIKE ?) OR (Content LIKE ?) ) "
					+ "ORDER BY " + orderBy;
		jdbcUtil.setSqlAndParameters(sql,
				new Object[] { workoutId, "%" + searchContent + "%", "%" + searchContent + "%" }); // JDBCUtil에 query문
																									// 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Review> revList = new ArrayList<Review>(); // Review들의 리스트 생성
			while (rs.next()) {
				Review review = new Review( // Review 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"),
						rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"), rs.getString("postedDate") , rs.getString("exerciseName"), rs.getString("username"));
				revList.add(review); // List에 Review 객체 저장
			}
			return revList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public List<Review> findReviewList(int workoutId, String orderBy) throws SQLException {

		String sql = "SELECT r.reviewId, r.userid, r.workoutId, r.title, r.content, r.score, r.likecount, r.posteddate, e.exerciseName, t.username "
					+ "FROM Review r, Exercise e, UserInfo t " 
					+ "WHERE r.workoutId = e.exerciseId AND e.trainerId = t.userId AND WorkoutId=? "
					+ "ORDER BY " + orderBy;
		jdbcUtil.setSqlAndParameters(sql, new Object[] { workoutId }); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<Review> revList = new ArrayList<Review>(); // Review들의 리스트 생성
			while (rs.next()) {
				Review review = new Review( // Review 객체를 생성하여 현재 행의 정보를 저장
						rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"),
						rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"), rs.getString("postedDate"), rs.getString("exerciseName"), rs.getString("username") );
				revList.add(review); // List에 Review 객체 저장
			}
			return revList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

//	public List<Review> findReviewList(int currentPage, int countPerPage, String orderBy) throws SQLException {
//		
//		String sql = "SELECT * " + "FROM Review " + "ORDER BY " + orderBy;
//		jdbcUtil.setSqlAndParameters(sql, null, // JDBCUtil에 query문 설정
//				ResultSet.TYPE_SCROLL_INSENSITIVE, // cursor scroll 가능
//				ResultSet.CONCUR_READ_ONLY);
//
//		try {
//			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
//			int start = ((currentPage - 1) * countPerPage) + 1; // 출력을 시작할 행 번호 계산
//			if ((start >= 0) && rs.absolute(start)) { // 커서를 시작 행으로 이동
//				List<Review> revList = new ArrayList<Review>(); // Review들의 리스트 생성
//				do {
//					Review review = new Review( // Review 객체를 생성하여 현재 행의 정보를 저장
//							rs.getInt("reviewId"), rs.getString("userId"), rs.getInt("workoutId"), rs.getInt("trainerId"),
//							rs.getString("title"), rs.getString("content"), rs.getInt("score"), rs.getInt("likeCount"));
//					revList.add(review); // 리스트에 UserInfo 객체 저장
//				} while ((rs.next()) && (--countPerPage > 0));
//				return revList;
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close(); // resource 반환
//		}
//		return null;
//	}

	public int remove(int reviewId) throws SQLException {
		String sql = "DELETE FROM Review WHERE reviewId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { reviewId }); // JDBCUtil에 delete문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public int updateLikeCount(int reviewId) {
		String sql = "UPDATE Review SET likeCount=likeCount+1 WHERE reviewId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { reviewId }); // JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}
}
