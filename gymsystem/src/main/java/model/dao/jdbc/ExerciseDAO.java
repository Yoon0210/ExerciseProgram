package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Exercise;
import model.Schedule;

public class ExerciseDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public ExerciseDAO(){
		jdbcUtil = new JDBCUtil();
	}
	
	//item 테이블에 새로운 상품 추가
	public int createItemByGuide(Exercise exercise) throws SQLException {
		String sql = "INSERT INTO item "
					+ "VALUES (item_id_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {
				exercise.getName(), 
				exercise.getPrice(), 
				new java.sql.Date(exercise.getStartTime().getTime()), 
				new java.sql.Date(exercise.getEndTime().getTime()), 
				exercise.getStrength(),
				exercise.getTrainerId(), 
				exercise.getCategory()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		try {				
			int result = jdbcUtil.executeUpdate();	// insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;			
	}
	
	//item 삭제
	public int deleteExerciseByTrainer(int exerciseId) throws SQLException{
		String sql = "DELETE FROM exercise "
					+ "WHERE item_id = ?";
		Object[] param = new Object[] {exerciseId};
		jdbcUtil.setSqlAndParameters(sql, param);

			try {
				int r = jdbcUtil.executeUpdate();
				return r;
			} catch(Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 반환
			}
			return 0;	
	}

	
	//가이드가 맡은 상품리스트 반환
	public List<Exercise> findItemByTrainer(String trainerId) throws SQLException {
		String sql = "SELECT exercie_id, name, price, strength,starttime, endtime, category "
					+ "FROM exercise "
		    		+ "WHERE trainer_id=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {trainerId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Exercise> exerciseList = new ArrayList<Exercise>();
			while (rs.next()) {
				Exercise Exercise = new Exercise(
					rs.getInt("exercise_id"),
					rs.getString("name"),
					rs.getInt("price"),
					rs.getString("Strength"),
					rs.getDate("starttime"),
					rs.getDate("eddtime"),
					trainerId,
					rs.getString("category"));
				exerciseList.add(Exercise);	
			}		
			return exerciseList;
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//아이템 아이디로 상품정보 검색
	public Exercise searchexerciseById(int exerciseId)throws SQLException {
		String query = "SELECT exercise_id, name, price, strength,startTime, endTime, trainer_id, category "
					+ "FROM exercise "
					+ "WHERE exercise_id = ?";
		
		Object[] param = new Object[] {exerciseId};
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getInt("price"));
				System.out.println(rs.getDate("startTime"));
				System.out.println(rs.getDate("endTime"));
				System.out.println(rs.getString("strength"));
				System.out.println(rs.getString("trainer_id"));
				System.out.println(rs.getString("category"));
				Exercise Exercise = new Exercise(
					exerciseId,
					rs.getString("name"),
					rs.getInt("price"),
					rs.getString("strength"),
					rs.getDate("endTime"),
					rs.getDate("startTime"),
					rs.getString("tainer_id"),
					rs.getString("category"));
				return Exercise;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close();
		}
		return null;
	}

	//아이템 아이디로 세부일정 검색
	public List<Schedule> searchScheduleByID(int exerciseId) throws SQLException{
		String query = "SELECT sche_id, sche_name, sche_time, SCHE_DESCRIPTION, sche_exerciseId "
				+ "FROM schedule "
				+ "WHERE exercise_id = ?";
	
	Object[] param = new Object[] {exerciseId};
	jdbcUtil.setSqlAndParameters(query, param);
	ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
	
	try {
		ResultSet rs = jdbcUtil.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("sche_id"));
			System.out.println(rs.getString("sche_name"));
			System.out.println(rs.getDate("sche_time"));
			System.out.println(rs.getString("SCHE_DESCRIPTION"));
			System.out.println(rs.getInt("sche_exerciseId"));
	
			Schedule sche = new Schedule(
				rs.getInt("sche_id"),
				rs.getString("sche_name"),
				rs.getDate("sche_time"),
				rs.getString("SCHE_DESCRIPTION"),
				rs.getInt("sche_exerciseId")	);
			scheduleList.add(sche);
		}
		return scheduleList;
		
	} catch(Exception e) {
		e.printStackTrace();
	}finally {
		jdbcUtil.close();
	}
	return null;
	}

	//아이템에 스케줄 추가
	public int createScheduleByTrainer(Schedule sche)throws SQLException{
		String query = "INSERT INTO schedule "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
	
		Object[] param = new Object[] {
				sche.getScheId(),
				sche.getName(), 
				new java.sql.Date(sche.getTime().getTime()),
				sche.getDescription(), 
				sche.getexerciseId()};
		
		System.out.println(sche.getScheId()+","+ sche.getName()+ "," + sche.getTime() + ","
								 + ","+ sche.getDescription()+","+ sche.getexerciseId());
		jdbcUtil.setSqlAndParameters(query, param);
	
		try {
			int r = jdbcUtil.executeUpdate();
			return r;
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return 0;
	}
	
	//문자열이 들어간 상품 반환
	public List<Integer> searchStringexercise(String search) throws SQLException{
		String query = "SELECT exercise_id "
					+"FROM exercise "
					//+"WHERE name= ?";
					+ "WHERE name LIKE ?";
	
	String temp = "%"+search+"%";
	Object[] param = new Object[] {temp};
	jdbcUtil.setSqlAndParameters(query, param);
	
	try {
		ResultSet rs = jdbcUtil.executeQuery();
		System.out.println("111111111111");
		ArrayList<Integer> idList = new ArrayList<Integer>();
		System.out.println("2222");
		while(rs.next()) {
			System.out.println("33333333" );
			System.out.println(rs.getInt("exercise_id"));
			idList.add((Integer)rs.getInt("exercise_id"));
		}
		System.out.println("받은 String으로 아이템 아이디 반환완료");
		return idList;
		
	} catch(Exception e) {
		e.printStackTrace();
	}finally {
		jdbcUtil.close();
	}
	return null;
	}
}