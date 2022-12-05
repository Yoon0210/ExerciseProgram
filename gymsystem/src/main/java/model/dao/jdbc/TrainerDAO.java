package model.dao.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Trainer;

public class TrainerDAO {
private JDBCUtil jdbcUtil = null;
	
	public TrainerDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}

	public List<Trainer> findTrainerList() {
		String sql = "SELECT trainerId, name " 
     		   + "FROM trainer "
     		   + "ORDER BY trainerId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Trainer> trList = new ArrayList<Trainer>();	// User들의 리스트 생성
			while (rs.next()) {
				Trainer trainer = new Trainer(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getString("trainerId"),
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
}
