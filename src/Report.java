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
	void hotelOccupancy() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT HotelID, COUNT(*) FROM ROOM WHERE Availability=0 GROUP BY HotelID");
		    TeamT.outputResult(rs);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void roomOccupancy() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery("SELECT HotelID, COUNT(*) FROM ROOM WHERE Availability=0 GROUP BY Category");
		    TeamT.outputResult(rs);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	
	void dateRangeOccupancy(String dateStart, String dateEnd) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT HotelID, COUNT(*) FROM CHECKIN WHERE (CheckInTime >= ? OR CheckOutTime <= ?) AND NOT (CheckInTime >= ? OR CheckOutTime <= ?) GROUP BY HotelID;");
		    ResultSet rs = pstmt.executeQuery();
		    TeamT.outputResult(rs);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void cityOccupancy() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void groupStaffByRole() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void staffServingCustomer() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void revenueReport() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
