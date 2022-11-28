package model.dao.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Workout;

public class WorkoutDAO {
private JDBCUtil jdbcUtil = null;
	
	public WorkoutDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	public List<Workout> findWorkoutList() {
		String sql = "SELECT workoutId, name " 
     		   + "FROM Workout "
     		   + "ORDER BY workoutId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Workout> sList = new ArrayList<Workout>();	// User들의 리스트 생성
			while (rs.next()) {
				Workout workout = new Workout(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("workoutId"),
					rs.getString("name")
					);
				sList.add(workout);				// List에 User 객체 저장
			}		
			return sList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
}
