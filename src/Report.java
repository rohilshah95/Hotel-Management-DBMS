package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Report {
	
	/*
	 * This function is used for generating the report for hotel occupancy.
	 * Input Parameters: N/A
	 * Return Value: Hotel ID, Number of Rooms occupied, Total Rooms in Hotel and Percentage of Rooms occupied in the hotel.
	 */
	public static ResultSet hotelOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery(" SELECT SUM(AVAILABILITY=0) AS OCCUPANCY, COUNT(*) AS TOTAL, (100*SUM(AVAILABILITY=0)/COUNT(*)) AS PERCENTAGE, HOTELID  FROM ROOM GROUP BY  HOTELID");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * This function is used for generating the report for room occupancy.
	 * Input Parameters: N/A
	 * Return Value: Hotel ID, Number of Rooms occupied and Category of Rooms occupied in the hotel.
	 */
	public static ResultSet roomOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT HotelID, COUNT(*) AS OCCUPIED, CATEGORY FROM ROOM WHERE Availability=0 GROUP BY Category ORDER BY HOTELID");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * This function is used for generating the report for hotel by date range occupancy.
	 * Input Parameters: Start date, End Date
	 * Return Value: Hotel ID, Number of Rooms occupied occupied in the hotel during that period.
	 */
	public static ResultSet dateRangeOccupancy(String dateStart, String dateEnd) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT HotelID, COUNT(*) FROM CHECKIN WHERE (CheckIndate >= ? OR CheckOutdate <= ?) AND NOT (CheckIndate <= ? OR CheckOutdate >= ?) GROUP BY HotelID");
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
	
	/*
	 * This function is used for generating the report for city occupancy.
	 * Input Parameters: N/A
	 * Return Value: Number of Rooms occupied, Total Rooms, City in Hotel in each City.
	 */
	public static ResultSet cityOccupancy() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT  SUM(AVAILABILITY=0) AS OCCUPIED, COUNT(*) AS TOTAL, City FROM ROOM JOIN HOTEL ON (ROOM.HOTELID=HOTEL.ID) GROUP BY City");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * This function is used for generating the report for Staff by Role.
	 * Input Parameters: N/A
	 * Return Value: Hotel ID, Number of Staff of a particular Role and Staff Title ordered by Hotel.
	 */
	public static ResultSet groupStaffByRole() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    rs = stmt.executeQuery("SELECT HIRES.HOTELID, COUNT(*), STAFF.Title FROM STAFF JOIN HIRES ON (STAFF.ID=HIRES.STAFFID) GROUP BY STAFF.TITLE, HIRES.HOTELID ORDER BY HIRES.HOTELID");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * This function is used for generating the report for Staff service a particular Customer.
	 * Input Parameters: Customer ID
	 * Return Value: Staff ID, Staff Name, Customer Checkin Date, Customer Checkout Date of a Particular Customer Stay
	 */
	public static ResultSet staffServingCustomer(int customerId) {
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
	
	/*
	 * This function is used for generating the report for revenue of a particular hotel given a date range.
	 * Input Parameters: HotelID, Start Date, End Date.
	 * Return Value: Revenue made by hotel during that particular period.
	 */
	public static ResultSet revenueReport(int hotelId, String checkInTime, String checkOutTime) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(BILL.Amount) AS REVENUE FROM BILL JOIN CHECKIN ON (BILL.ID=CHECKIN.BILLID) WHERE (CHECKIN.CheckOutdate >= ? AND CHECKIN.CheckOutdate <= ? AND CHECKIN.HotelID=?)");
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
