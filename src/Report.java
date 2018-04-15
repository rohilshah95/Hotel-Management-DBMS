package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report {
	/*
	 * hotel occupancy
	 * room occupancy
	 * date range occupancy
	 * city occupancy
	 * groupStaff by role
	 * staff servicing customer
	 * revenue report
	 */
	static ResultSet hotelOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT HotelID, COUNT(*) FROM ROOM WHERE Availability=0 GROUP BY HotelID");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet roomOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT HotelID, COUNT(*) FROM ROOM WHERE Availability=0 GROUP BY Category");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet dateRangeOccupancy(String dateStart, String dateEnd) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT HotelID, COUNT(*) FROM CHECKIN WHERE (CheckInTime >= ? OR CheckOutTime <= ?) AND NOT (CheckInTime >= ? OR CheckOutTime <= ?) GROUP BY HotelID;");
		    pstmt.setString(1, dateStart);
		    pstmt.setString(2, dateEnd);
		    pstmt.setString(3, dateStart);
		    pstmt.setString(4, dateEnd);
		    rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet cityOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT HotelID, COUNT(*), Address FROM ROOM JOIN HOTEL Where Availability=0 GROUP BY Address");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet groupStaffByRole() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM STAFF GROUP BY Title");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet staffServingCustomer(int customerId) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(" SELECT STAFF.ID, STAFF.Name, CHECKIN.CHECKINDATE, CHECKIN.CHECKOUTDATE FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN STAFF ON STAFF.ID=PROVIDES.staffID WHERE ID IN (SELECT DISTINCT StaffID FROM PROVIDES NATURAL JOIN CHECKIN WHERE CustomerID = ?) AND CHECKIN.CUSTOMERID=? GROUP BY CHECKIN.CHECKOUTDATE");
		    pstmt.setInt(1, customerId);
		    pstmt.setInt(2, customerId);
		    rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	static ResultSet revenueReport(int hotelId, String checkInTime, String checkOutTime) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(BILL.Amount) FROM BILL JOIN CHECKIN WHERE (CHECKIN.CheckOutTime >= ? AND CHECKIN.CheckOutTime <= ? AND CHECKIN.HotelID=?)");
		    pstmt.setString(1, checkInTime);
		    pstmt.setString(2, checkOutTime);
		    pstmt.setInt(3, hotelId);
		    rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
}
