package src;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Staff {

	public static void createStaff(int id, String name, String title, String department, String address, String phone,
			Byte availability) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO STAFF (ID, Name, Title, Department, Address, Phone, Availability) VALUES (?,?,?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, title);
			pstmt.setString(4, department);
			pstmt.setString(5, address);
			pstmt.setString(6, phone);
			pstmt.setByte(7, availability);
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
}
