package src;

import java.sql.*;

public class Hotel {
	static int id=4;
	/*
	 * Data - id, managerId, long phoneNumber, address, city;
	 */
	public static void createHotel(String name, String address, String city, String phoneNumber, int managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    id++;
		    PreparedStatement pstmt= conn.prepareStatement("INSERT INTO HOTEL(ID, NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES (?,?,?,?,?,?)");
		    pstmt.setInt(1, id);
		    pstmt.setString(2, name);
		    pstmt.setString(3,  address);
		    pstmt.setString(4,  city);
		    pstmt.setString(5, phoneNumber);
		    pstmt.setInt(6, managerId);
		    pstmt.executeUpdate();
		    
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateHotel(int id,String name, String address, String city, String phoneNumber, int managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt= conn.prepareStatement("UPDATE HOTEL SET NAME=?, ADDRESS=?, CITY=?, PHONE=?, MANAGERID=? WHERE ID=?");
		    pstmt.setString(1, name);
		    pstmt.setString(2,  address);
		    pstmt.setString(3,  city);
		    pstmt.setString(4, phoneNumber);
		    pstmt.setInt(5, managerId);
		    pstmt.setInt(6, id);
		    pstmt.executeUpdate();
		    
		    
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteHotel(int id) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt=conn.prepareStatement("DELETE FROM HOTEL WHERE ID=?");
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ResultSet getHotel(int id) {
		ResultSet rs= null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM HOTEL WHERE ID=?");
		    pstmt.setInt(1, id);
		    rs= pstmt.executeQuery();
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllHotels() {
		ResultSet rs= null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM HOTEL");
		    rs= pstmt.executeQuery();
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
}
