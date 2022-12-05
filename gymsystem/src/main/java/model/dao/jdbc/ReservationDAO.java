package model.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reservation;

public class ReservationDAO {
	
	public ReservationDAO() {	//생성자
		// JDBC 드라이버 로딩 및 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
	}
	
	private static Connection getConnection() {
		//string url 새로 정의 필요
		String url =  "jdbc:oracle:thin:@202.20.119.117:1521:orcl";	
		String user = "dbpro0345";
		String passwd = "dbpro0345";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	//멤버id로  전체 res_id 받아오기
	public List<Integer> searchResIdByUser(String user_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		String query = "SELECT res_id "
					+ "FROM Reservation "
					+ "WHERE user_id =?";
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, user_id);
			rs = pStmt.executeQuery();

			List<Integer> resIdList = new ArrayList<Integer>();
			while(rs.next()) {
				resIdList.add(rs.getInt("res_id"));
			}
			return resIdList;
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return null;
	}
	
	//res_id로 필요한  reservation요소 받아오기
	public Reservation searchReservationByUser(int res_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		//int resId, exercisePrice;
		//String exerciseName, payStatus, resStatus;
		
		String query = "SELECT res_id, exercisename, res_status, pay_status, price "
				+ "FROM reservation r, item i "
				+ "WHERE r.item_id = i.item_id and res_id = ?";
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, res_id);
			rs = pStmt.executeQuery();
			
			if(rs.next()) {
				Reservation reservation = new Reservation(
						rs.getInt("res_id"),
						rs.getString("trainerId"),
						rs.getString("exercisename"),
						rs.getString("resStatus"));
				return reservation;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return null;
	}

	//member_id받아서 상품예약하기
	public int itemReservationByUser(int exercise_id, String user_id) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		int r = 0;
		
		String query = "INSERT INTO reservation "
					+"VALUES (seq_res_id.nextval, '대기', '대기', ?, ?)";
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, user_id);
			pStmt.setInt(2, exercise_id);
			r = pStmt.executeUpdate();
			return r;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return r;
	}

	//item_id에 해당하는 모든 res_id 받아오기
	public List<Integer> searchResIdByGuide(int exercise_id){
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		ArrayList<Integer> resIdList = new ArrayList<Integer>();
		
		String query = "SELECT res_id "
				+ "FROM Reservation "
				+ "WHERE exercise_id = ?";
		
		try {
			conn = getConnection();
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, exercise_id);
			rs = pStmt.executeQuery();
			
			while(rs.next()) {
				resIdList.add(rs.getInt("res_id"));
			}
			return resIdList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
		return null;
	}
}

	