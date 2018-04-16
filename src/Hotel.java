package src;

import java.sql.*;

public class Hotel {
	/*
	 * Data - name, id, managerId, phoneNumber, address, city;
	 */
	/*
	 * This functions is responsible for creating a new entry in the Hotel Table and returns the ID it just created. 
	 */
	public static ResultSet createHotel(String name, String address, String city, String phoneNumber, int managerId) {
		ResultSet rs=null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt= conn.prepareStatement("INSERT INTO HOTEL(NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES (?,?,?,?,?)");
		    pstmt.setString(1, name);
		    pstmt.setString(2,  address);
		    pstmt.setString(3,  city);
		    pstmt.setString(4, phoneNumber);
		    pstmt.setInt(5, managerId);
		    pstmt.executeUpdate();
		    
		    rs=conn.createStatement().executeQuery("SELECT MAX(ID) AS NEW_HOTEL_ID FROM HOTEL"); 
		    
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * This function is responsible for updating everything in the hotel other than the Hotel ID. It uses the Hotel ID in the where clause to update the row.
	 */
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

	/*
	 * This function deletes the hotel with the specified ID. The ID is taken at login.
	 */
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
	
	/*
	 * This function returns all the information of a particular Hotel when the Hotel ID is specified.
	 */
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
	
	/*
	 * This function returns a list of all hotels and all their information.
	 */
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
