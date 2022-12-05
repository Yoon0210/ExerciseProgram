package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Report;

public class ReportDAO {

	private JDBCUtil jdbcUtil = null;

	public ReportDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(Report report) throws SQLException {
		String sql = "INSERT INTO Report VALUES (?, ?, ?)";
		Object[] param = new Object[] { report.getUserId(), report.getReviewId(), report.getReportReason() };
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
		String sql = "DELETE FROM Report WHERE userId=? AND reviewId=?";
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

	public List<Report> findReportById(String userId) throws SQLException {
		String sql = "SELECT reviewId " + "FROM Report " + "WHERE userId=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

		List<Report> reportList = new ArrayList<Report>();
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) { // 학생 정보 발견
				Report report = new Report( // User 객체를 생성하여 학생 정보를 저장
						userId, rs.getInt("reviewId"), rs.getString("reportReason"));
				reportList.add(report);
				return reportList;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}
	
	public List<Report> findReportList() throws SQLException {
		String sql = "SELECT * " + "FROM Report ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { }); // JDBCUtil에 query문과 매개 변수 설정

		List<Report> reportList = new ArrayList<Report>();
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			while (rs.next()) { // 학생 정보 발견
				Report report = new Report( // User 객체를 생성하여 학생 정보를 저장
						rs.getString("userId"), rs.getInt("reviewId"), rs.getString("reportReason"));
				reportList.add(report);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return reportList;
	}
}
