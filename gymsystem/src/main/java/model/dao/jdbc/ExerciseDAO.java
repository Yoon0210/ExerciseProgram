package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Exercise;

//추가해야할 부분이 운동종목으로 검색하거나 강사이름으로 검색하거나 두개로 둘다도 되어야하고 만들어야함.
public class ExerciseDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public ExerciseDAO(){
		jdbcUtil = new JDBCUtil();
	}
	
	public boolean existingTrainerSchedule(String id, String day, String time) {
		String sql = "SELECT count(*) AS \"count\" FROM exercise WHERE trainerid=? AND exerciseDay=? AND exerciseTime=?";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] { id, day, time } );	// JDBCUtil에 query문과 매개 변수 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt("count");
				System.out.println(count);
				return (count >= 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return true;
		
	}
	
	//exercise 테이블에 새로운 운동 추가 //ok
	public int createExerciseByTrainer(Exercise exercise) throws SQLException {
		
		String sql = "INSERT INTO exercise "
					+ "VALUES (exerciseId_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		Object[] param = new Object[] {
				exercise.getTrainerId(),
				exercise.getExerciseName(), 
				exercise.getExerciseDay(),
				exercise.getExerciseTime(),
				exercise.getDifficulty(),
				exercise.getExerciseType()
				};	
		
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
	
	//exercise 삭제 //ok
	public int deleteExerciseByTrainer(int exerciseId) throws SQLException{
		String sql = "DELETE FROM exercise "
					+ "WHERE exerciseId = ?";
		
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

	
	//트레이너가 등록한(맡은) 운동정보 반환 //ok
	public List<Exercise> findExerciseByTrainer(String trainerId) throws SQLException {
		String sql = "SELECT e.exerciseId, e.trainerId, e.exerciseName, e.exerciseDay,"
				+ " e.exerciseTime, e.difficulty, e.exerciseType, u.username "
					+ "FROM exercise e, userinfo u "
					+ "WHERE e.trainerid = u.userid "
		    		+ "AND e.trainerid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {trainerId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Exercise> exerciseList = new ArrayList<Exercise>();
			while (rs.next()) {
				Exercise Exercise = new Exercise(
					rs.getInt("exerciseId"),
					rs.getString("trainerId"),
					rs.getString("exerciseName"),
					rs.getString("exerciseDay"),
					rs.getString("exerciseTime"),
					rs.getString("difficulty"),
					rs.getString("exerciseType"),
					rs.getString("trainerName")
			);
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
	
	//exerciseId로 운동정보 검색 //ok
	public Exercise searchExerciseByexerciseId(int exerciseId) throws SQLException {
		String sql = "SELECT e.exerciseId, e.trainerId, e.exerciseName, e.exerciseDay,"
				+ " e.exerciseTime, e.difficulty, e.exerciseType, u.username "
					+ "FROM exercise e, userinfo u "
					+ "WHERE e.trainerid = u.userid "
		    		+ "AND e.exerciseId=? ";         
		
		Object[] param = new Object[] {exerciseId};
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				Exercise Exercise = new Exercise(
						exerciseId,
						rs.getString("trainerId"),
						rs.getString("exerciseName"),
						rs.getString("exerciseDay"),
						rs.getString("exerciseTime"),
						rs.getString("difficulty"),
						rs.getString("exerciseType"),
						rs.getString("trainerName")
						);
				return Exercise;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	
	//운동이름으로 운동 정보 검색
	public List<Exercise> searchStringExercisename(String exerciseName) throws SQLException{
		String sql = "SELECT e.exerciseId, e.trainerId, e.exerciseName, e.exerciseDay,"
				+ " e.exerciseTime, e.difficulty, e.exerciseType, u.username "
					+ "FROM exercise e, userinfo u "
					+ "WHERE e.trainerid = u.userid "
		    		+ "AND e.exerciseName LIKE ? ";         
					
	String temp = "%"+exerciseName+"%";
	Object[] param = new Object[] {temp};
	jdbcUtil.setSqlAndParameters(sql, param);
	
	try {
		ResultSet rs = jdbcUtil.executeQuery();
		List<Exercise> exerciseList = new ArrayList<Exercise>();
		if(rs.next()) {
			Exercise Exercise = new Exercise(
					rs.getInt("exerciseId"),
					rs.getString("trainerId"),
					rs.getString("exerciseName"),
					rs.getString("exerciseDay"),
					rs.getString("exerciseTime"),
					rs.getString("difficulty"),
					rs.getString("exerciseType"),
					rs.getString("trainerName")
					);
			exerciseList.add(Exercise);	
		}
		return exerciseList;
		
	} catch(Exception e) {
		e.printStackTrace();
	}finally {
		jdbcUtil.close();
	}
	return null;
	}
	
	 //유저id로 운동 스케쥴 찾는거
	public List<Exercise> findExerciseScheduleByUserId(String userid){
		String sql = "SELECT e.exerciseId, e.trainerId, e.exerciseName, e.exerciseDay, "
				+ " e.exerciseTime, e.difficulty, e.exerciseType, u.username "
					+ "FROM exercise e, schedule s, userinfo u "
					+ "WHERE e.exerciseid = s.exerciseid AND e.userid = u.userid "
		    		+ "AND s.userid= ? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userid});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Exercise> exerciseList = new ArrayList<Exercise>();
			while (rs.next()) {
				Exercise Exercise = new Exercise(
					rs.getInt("exerciseId"),
					rs.getString("trainerId"),
					rs.getString("exerciseName"),
					rs.getString("exerciseDay"),
					rs.getString("exerciseTime"),
					rs.getString("difficulty"),
					rs.getString("exerciseType"),
					rs.getString("trainerName")
			);
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
	
	//모든 운동리스트 반환
	public List<Exercise> allExerciseList() throws SQLException {
		String sql = "SELECT e.exerciseId, e.trainerId, e.exerciseName, e.exerciseDay, "
				+ " e.exerciseTime, e.difficulty, e.exerciseType, u.username "
					+ "FROM exercise e, userinfo u "
					+ "WHERE e.trainerid = u.userid";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			List<Exercise> exerciseList = new ArrayList<Exercise>();
			while (rs.next()) {
				Exercise Exercise = new Exercise(
					rs.getInt("exerciseId"),
					rs.getString("trainerId"),
					rs.getString("exerciseName"),
					rs.getString("exerciseDay"),
					rs.getString("exerciseTime"),
					rs.getString("difficulty"),
					rs.getString("exerciseType"),
					rs.getString("username")
			);
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
	
	//미경 make
	public List<Exercise> findExerciseName() {
		String sql = "SELECT e.exerciseId, e.exercisename, t.userId, t.username " 
     		   + "FROM Exercise e, UserInfo t "
			   + "WHERE e.trainerId = t.userId "
     		   + "ORDER BY exerciseId";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
			List<Exercise> sList = new ArrayList<Exercise>();	// User들의 리스트 생성
			while (rs.next()) {
				Exercise exercise = new Exercise(			// User 객체를 생성하여 현재 행의 정보를 저장
					rs.getInt("exerciseId"),
					rs.getString("exercisename"), rs.getString("userId"), rs.getString("username")
					);
				sList.add(exercise);				// List에 User 객체 저장
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