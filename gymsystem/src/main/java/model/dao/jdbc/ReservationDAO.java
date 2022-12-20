package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;

public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReservationDAO() {	
		jdbcUtil = new JDBCUtil();
	}
	
	//resid로 필요한  reservation요소 받아오기
	public Reservation searchReservationByResId(int resid){
		String sql = "SELECT r.resId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, u.userName, e.exerciseName "
				+"FROM reservation r, Userinfo u, Exercise e "
				+"WHERE r.exerciseId = e.exerciseId and e.trainerId = u.userId AND resid = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {resid});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("resId"),
						rs.getString("userId"),
						rs.getInt("exerciseId"),
						rs.getString("reservationDate"),
						rs.getString("status"),
						rs.getString("userName"),
						rs.getString("exerciseName")
						);
				return reservation;
				
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}
	
	public List<Reservation> searchReservationByTrainer(String trainerId){
		String sql = "SELECT r.resId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, u.userName, e.exerciseName, e.exerciseType "
				+"FROM reservation r, Userinfo u, Exercise e "
				+"WHERE r.exerciseId = e.exerciseId and e.userId = u.userId AND resid = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {trainerId});
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("resId"),
						rs.getString("userId"),
						rs.getInt("exerciseId"),
						rs.getString("reservationDate"),
						rs.getString("status"),
						rs.getString("userName"),
						rs.getString("exerciseName")
						);
				reservations.add(reservation);
				
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return reservations;
	}
	public List<Reservation> searchReservationByUser(String userid){
		String sql = "SELECT r.resId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, u.userName, e.exerciseName, e.exerciseType "
				+"FROM reservation r, Userinfo u, Exercise e "
				+"WHERE r.exerciseId = e.exerciseId and e.trainerId = u.userId AND resid = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userid});
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("resId"),
						rs.getString("userId"),
						rs.getInt("exerciseId"),
						rs.getString("reservationDate"),
						rs.getString("status"),
						rs.getString("userName"),
						rs.getString("exerciseName"),
						rs.getString("exerciseType")
						);
				reservations.add(reservation);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return reservations;
	}

	//exerciseId랑 userId받아서 운동 예약하기
	public int create(int exerciseid, String userid) {
		String sql = "INSERT INTO reservation "
					+"VALUES (reservationId_seq.nextval, ?, ?, SYSDATE, ?)";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userid, exerciseid,"대기"});
		
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
	
	//예약정보 업데이트
	public int updateStatus(int resid, String status) {
		String sql = "UPDATE reservation "
				+ "SET status= ? "
				+ "WHERE resid=?";
		Object[] param = new Object[] {status,resid};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
}
}

	