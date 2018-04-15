package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staff {

	public static void createStaff(String name, String title, String department, String address, String phone,
			Byte availability) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO STAFF (Name, Title, Department, Address, Phone, Availability) VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, department);
			pstmt.setString(4, address);
			pstmt.setString(5, phone);
			pstmt.setByte(6, availability);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateStaff(int id, String name, String title, String department, String address, String phone,
			Byte availability) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE STAFF SET Name = ?, Title = ?, Department = ?, Address = ?, Phone = ?, Availability = ? WHERE ID = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, department);
			pstmt.setString(4, address);
			pstmt.setString(5, phone);
			pstmt.setString(6, address);
			pstmt.setByte(7, availability);
			pstmt.setInt(8, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteStaff(int id) {
		try {
			Connection conn = DBConnection.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM STAFF WHERE ID=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ResultSet getStaff(int id)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from STAFF WHERE ID=?");
		    pstmt.setInt(1, id);
		    rs= pstmt.executeQuery();

		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllStaffAllHotels()
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    rs= stmt.executeQuery("SELECT * from STAFF");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllStaff(int hotelId)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    PreparedStatement pstmt = conn.prepareCall("SELECT STAFF.ID, STAFF.NAME, STAFF.TITLE, STAFF.DEPARTMENT, STAFF.ADDRESS, STAFF.PHONE, STAFF.AVAILABILITY, HIRES.HOTELID FROM STAFF JOIN HIRES WHERE STAFF.ID=HIRES.STAFFID and HIRES.HOTELID=?");
		    pstmt.setInt(1, hotelId);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
}
