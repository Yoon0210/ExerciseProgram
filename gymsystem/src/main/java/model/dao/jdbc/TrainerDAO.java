package model.dao.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class TrainerDAO {
private JDBCUtil jdbcUtil = null;
	
	public TrainerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	public User findTrainer(String trainerId) {
		String sql = "SELECT * " 
	     		   + "FROM User "
	     		  + "WHERE userId = ? AND userType=?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { trainerId, "trainer" });		// JDBCUtil에 query문 설정
						
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
				if (rs.next()) {
					User trainer = new User(			// Trainer 객체를 생성하여 현재 행의 정보를 저장
						rs.getString("trainerId"),
						rs.getString("trainerPWD"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getString("name")
						);
					return trainer;
				}					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource 반환
			}
			return null;
	}

	public List<User> findTrainerList() {
		String sql = "SELECT userId, name " 
     		   + "FROM User "
			   + "WHERE userType=? "
     		   + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {"trainer"});		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<User> trList = new ArrayList<User>();	// User들의 리스트 생성
			while (rs.next()) {
				User trainer = new User(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("trainerId"),
					null,
					rs.getString("name")
					);
				trList.add(trainer);				// List에 User 객체 저장
			}		
			return trList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//top3 강사 검색
	public List<User> findTop3Trainer() {
		String sql = "SELECT ROWNUM, trainerName "
				+ "	FROM "
				+ "	( SELECT u.userName AS trainerName, COUNT(*) "
				+ "	FROM EXERCISE e, USERINFO u, RESERVATION r "
				+ "	WHERE r.exerciseId = e.exerciseId AND e.trainerId = u.userId "
				+ "	group by u.userName "
				+ "	ORDER BY COUNT(*) DESC) "
				+ "	WHERE ROWNUM < 4";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<User> trList = new ArrayList<User>();	// User들의 리스트 생성
			while (rs.next()) {
				User trainer = new User(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("trainerName")
					);
				trList.add(trainer);				// List에 User 객체 저장
			}		
			return trList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

}
