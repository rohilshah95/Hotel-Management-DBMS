package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Staff {

	/*
	 * This function is responsible for creating the entries in the Staff table when new Staff is added.
	 * It takes the parameters required and inserts into the database.
	 * Input Parameters: Name, Title, Department, Address, Phone, Availability, Hotel ID
	 * Return Value: Result Set containing Staff ID
	 */	
	public static ResultSet createStaff(String name, String title, String department, String address, String phone,
			Byte availability, int hotelID) {
		ResultSet rs = null;
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

			PreparedStatement pstmt1 = conn
					.prepareStatement("INSERT INTO HIRES(HOTELID, STAFFID) VALUES (?,(SELECT MAX(ID) FROM STAFF))");
			pstmt1.setInt(1, hotelID);
			pstmt1.executeUpdate();

			rs = conn.createStatement().executeQuery("SELECT MAX(ID) AS NEW_STAFF_ID FROM STAFF");

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}


	/*
	 * This function is responsible for updating the entries in the Staff table for the given Staff ID.
	 * It takes the parameters required and updates the entries of the database.
	 * Input Parameters: Staff ID, Name, Title, Department, Address, Phone, Availability
	 * Return Value: N/A
	 */
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
			pstmt.setByte(6, availability);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	/*
	 * This function is responsible for deleting a Staff entry from the Database.
	 * It takes the given Staff ID and deletes it from the database.
	 * Input Parameters: Staff ID
	 * Return Value: N/A
	 */
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

	/*
	 * This function is responsible for getting all the information from the Staff table for the provided Staff ID.
	 * Input Parameters: Staff ID
	 * Return Value: Result Set containing all information about Staff with the given ID
	 */
	public static ResultSet getStaff(int id) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * from STAFF WHERE ID=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * This function is responsible for getting all the Staff entries in the database.
	 * Input Parameters: N/A
	 * Return Value: Result Set containing all Staff Information from all Hotels.
	 */
	public static ResultSet getAllStaffAllHotels() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();

			rs = stmt.executeQuery("SELECT * from STAFF");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	

	/*
	 * This function is responsible for getting all the Staff entries from a given Hotel.
	 * It takes the Hotel ID and returns all Staff from that Hotel.
	 * Input Parameters: HotelID
	 * Return Value: Result Set containing all information about all Staff of a given Hotel.
	 */
	public static ResultSet getAllStaff(int hotelId) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"SELECT STAFF.ID, STAFF.NAME, STAFF.TITLE, STAFF.DEPARTMENT, STAFF.ADDRESS, STAFF.PHONE, STAFF.AVAILABILITY, HIRES.HOTELID FROM STAFF JOIN HIRES ON (STAFF.ID=HIRES.STAFFID) WHERE HIRES.HOTELID=?");
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	

	/*
	 * This function is responsible for getting available staff in a Hotel.
	 * It takes the Hotel ID and returns information about available staff.
	 * Input Parameters: Hotel ID
	 * Return Value: Result Set containing information about available staff of a given Hotel.
	 */
	public static ResultSet getAvailableStaff(int HotelId) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * from STAFF, HIRES WHERE availability=1 AND HIRES.HOTELID=" + HotelId
							+ " AND HIRES.STAFFID = STAFF.ID");
			rs = pstmt.executeQuery();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}
