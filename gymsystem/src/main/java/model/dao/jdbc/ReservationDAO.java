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
				+ "r.status, u.userName, e.exerciseName, e.exerciseType "
				+ "FROM reservation r, Userinfo t, Exercise e, UserInfo u "
				+ "WHERE r.exerciseId = e.exerciseId AND e.trainerId = t.userId AND r.status='대기' AND r.userId = u.userId AND e.trainerId = ? "
				+ "ORDER BY r.reservationDate";
		
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
	
	//userid로 신청 목록 반환 (name=강사이름)
	public List<Reservation> searchReservationByUser(String userid){
		String sql = "SELECT r.reservationId, r.userId, r.exerciseId, r.reservationDate, "
				+ "r.status, t.userName, e.exerciseName, e.exerciseType, e.exerciseDay, e.exerciseTime "
				+ "FROM reservation r, Userinfo t, Exercise e, UserInfo u "
				+ "WHERE r.exerciseId = e.exerciseId AND e.trainerId = t.userId AND  r.userId = u.userId AND r.userId = ? "
				+ "ORDER BY r.reservationDate";
		
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
		
		String sql1 = "SELECT COUNT(*) "
				+ "FROM RESERVATION "
				+ "WHERE exerciseId = ? "
				+ "AND userId = ? AND (status='대기' OR status='승인')"; 
				
		String sql2 = "INSERT INTO reservation "
					+"VALUES (reservationId_seq.nextval, ?, ?, SYSDATE, ?)";
		
		
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {exerciseid, userid});
		
		try {
			ResultSet rs =jdbcUtil.executeQuery();
			int result = 0;
			if (rs.next()) {
				if (rs.getInt(1) == 0) {
					jdbcUtil.setSqlAndParameters(sql2, new Object[] { userid, exerciseid, "대기" });
					result = jdbcUtil.executeUpdate(); // insert 문 실행
				}
			}
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
	public int delete(int resid) {
		String sql = "DELETE FROM reservation "
				+ "WHERE reservationId=?";
		Object[] param = new Object[] {resid};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		int result = 0;
		
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
		
		return 0;
	}

	public int updateStatusAccept(int resId, String resUserId, int resExerId) {
		int result = 0;
		String sql;
		String resExerDay;
		String resExerTime;
		try {
			String sql3 = "SELECT exerciseDay, exerciseTime "
					+ "FROM exercise "
					+ "WHERE exerciseId = ?";
			jdbcUtil.setSqlAndParameters(sql3, new Object[] { resExerId });
			ResultSet rs = jdbcUtil.executeQuery(); // update 문 실행
			
			if(rs.next()) {
				resExerDay = rs.getString("exerciseDay");
				resExerTime = rs.getString("exerciseTime");
				
				String sql2 = "SELECT COUNT(*) AS \"count\" FROM schedule s, exercise e, userinfo u "
						+ "WHERE u.userId = s.userId AND e.exerciseDay = ? AND e.exerciseTime = ? "
						+ "AND s.exerciseId = e.exerciseId AND s.userId = ?";
				
				jdbcUtil.setSqlAndParameters(sql2, new Object[] { resExerDay, resExerTime, resUserId });
				rs = jdbcUtil.executeQuery(); // update 문 실행
				
				if(rs.next()) {
					if(rs.getInt("count") == 0) {
						System.out.println("count");
						sql = "INSERT INTO SCHEDULE VALUES (?, ?)";
						jdbcUtil.setSqlAndParameters(sql, new Object[] { resUserId, resExerId });
						result = jdbcUtil.executeUpdate();
						if(result == 1) {
							sql = "UPDATE reservation " + "SET status= ? " + "WHERE reservationId=?";
							Object[] param = new Object[] { "승인" ,resId };
							jdbcUtil.setSqlAndParameters(sql, param);
							result = jdbcUtil.executeUpdate();
						}
					}
					else {
						System.out.println("false");
						sql = "UPDATE reservation SET status='취소' WHERE reservationId = ?";
						jdbcUtil.setSqlAndParameters(sql, new Object[] { resId });
						result = jdbcUtil.executeUpdate();
						return 0;
					}
				}
			}
			
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			return 0;
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
	}
}

	