package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Likey;

public class LikeyDAO {

	private JDBCUtil jdbcUtil = null;

	public LikeyDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Likey likey) throws SQLException {
		String sql = "INSERT INTO Likey VALUES (?, ?)";
		Object[] param = new Object[] { likey.getUserId(), likey.getReviewId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
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

	public int remove(String userId, int reviewId) throws SQLException {
		String sql = "DELETE FROM Likey WHERE userId=? AND reviewId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId, reviewId }); // JDBCUtil에 delete문과 매개 변수 설정

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

	public List<Likey> findLikeyById(String userId) throws SQLException {
		String sql = "SELECT reviewId " + "FROM Likey " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

		List<Likey> likeyList = new ArrayList<Likey>();
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				Likey likey = new Likey( // User 객체를 생성하여 학생 정보를 저장
						userId, rs.getInt("reviewId"));
				likeyList.add(likey);
				return likeyList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

}
