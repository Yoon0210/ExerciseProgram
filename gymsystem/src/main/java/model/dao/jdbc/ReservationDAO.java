package model.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Exercise;
import model.Reservation;

public class ReservationDAO {
	private JDBCUtil jdbcUtil = null;
	
	public ReservationDAO() {	
		jdbcUtil = new JDBCUtil();
	}
	
	//resid로 필요한  reservation요소 받아오기
	public Reservation searchReservationByResId(int resid){
		String sql = "SELECT r.reservationId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, u.userName, e.exerciseName "
				+"FROM reservation r, Userinfo u, Exercise e "
				+"WHERE r.exerciseId = e.exerciseId and e.trainerId = u.userId AND reservationId = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {resid});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("reservationId"),
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
	
	//대기상태인 신청 목록 반환 (name=신청자이름)
	public List<Reservation> searchReservationByTrainer(String trainerId){
		String sql = "SELECT r.reservationId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, u.userName, e.exerciseName "
				+"FROM reservation r, Userinfo t, Exercise e, UserInfo u "
				+"WHERE r.exerciseId = e.exerciseId AND e.trainerId = t.userId AND r.status='대기' AND r.userId = u.userId AND e.trainerId = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {trainerId});
		
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("reservationId"),
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
	
	//userid로 신청 목록 반환 (name=강사이름)
	public List<Reservation> searchReservationByUser(String userid){
		String sql = "SELECT r.reservationId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, t.userName, e.exerciseName, e.exerciseType, e.exerciseDay, e.exerciseTime "
				+"FROM reservation r, Userinfo t, Exercise e, UserInfo u "
				+"WHERE r.exerciseId = e.exerciseId AND e.trainerId = t.userId AND  r.userId = u.userId AND r.userId = ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userid});
		List<Reservation> reservations = new ArrayList<Reservation>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			while(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("reservationId"),
						rs.getString("userId"),
						rs.getInt("exerciseId"),
						rs.getString("reservationDate"),
						rs.getString("status"),
						rs.getString("userName"),
						rs.getString("exerciseName")+" - "+rs.getString("exerciseDay")+"/"+rs.getString("exerciseTime"),
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
	public int updateStatus(int resid, String userId, int exerciseId, String status) {
		String sql = "UPDATE reservation "
				+ "SET status= ? "
				+ "WHERE reservationId=?";
		Object[] param = new Object[] {status,resid};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		int result = 0;
		if (status.equals("거절")) {
			try {
				result = jdbcUtil.executeUpdate(); // update 문 실행
				return result;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close(); // resource 반환
			}
		}
		else if(status.equals("승인")) {
			try {
				result = jdbcUtil.executeUpdate(); // update 문 실행
				
				if(result==1) {
					sql = "INSERT INTO SCHEDULE VALUES (?, ?)";
					jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, exerciseId});
					result = jdbcUtil.executeUpdate();
				}
				return result;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close(); // resource 반환
			}
			
		} else if(status.equals("취소")) {
			try {
				result = jdbcUtil.executeUpdate(); // update 문 실행
				return result;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {
				jdbcUtil.commit();
				jdbcUtil.close(); // resource 반환
			}
		}
		return 0;
}
}

	