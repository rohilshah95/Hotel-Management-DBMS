package src;

import java.sql.*;
import java.text.*;
import java.util.Date;

public class Customer {
	static int billId = 4; // because demo data already has 4 entries
	// int id;
	// String name;
	// String dob;
	// String phoneNumber;
	// String email;
	// String ssn;
	// String address;
	// byte hasHotelCard;

	public static void createCustomer(int id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, Byte hasHotelCard) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CUSTOMER (ID, NAME, DOB, PHONE, EMAIL,SSN, ADDRESS, HASHOTELCARD) VALUES (?,?,?,?,?,?,?,?)");
		    pstmt.setInt(1, id);
		    pstmt.setString(2, name);
		    pstmt.setString(3, dob);
		    pstmt.setString(4, phoneNumber);
		    pstmt.setString(5, email);
		    pstmt.setString(6, ssn);
		    pstmt.setString(7, address);
		    pstmt.setByte(8, hasHotelCard);
		    pstmt.executeUpdate();
		    
		    ResultSet rs = null;
//			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateCustomer(int id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, Byte hasHotelCard) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE CUSTOMER SET Name=?, dob=?, phone=?, email=?, ssn=?, address=?, hashotelcard=? WHERE id=?");
		    pstmt.setString(1, name);
		    pstmt.setString(2, dob);
		    pstmt.setString(3, phoneNumber);
		    pstmt.setString(4, email);
		    pstmt.setString(5, ssn);
		    pstmt.setString(6, address);
		    pstmt.setByte(7, hasHotelCard);
		    pstmt.setInt(8, id);
		    pstmt.executeUpdate();
		    		    
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteCustomer(int id) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    
		    PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE ID=?");
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//billid global variable 
	public static void assignRoom(int customerId, int hotelId, int roomId, int noOfGuests) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    
		    PreparedStatement pstmt= conn.prepareStatement("UPDATE ROOM SET Availability=0 WHERE number=? and hotelid=? ");
		    pstmt.setInt(1, roomId);
		    pstmt.setInt(2, hotelId);
		    pstmt.executeUpdate();
		    
		    PreparedStatement pstmt1= conn.prepareStatement("INSERT INTO BILL (ID, AMOUNT, MODEOFPAYMENT, DISCOUNT) VALUES (?, 0, NULL, 0)");
		    pstmt1.setInt(1, billId);
		    pstmt1.executeUpdate();
		    billId++;
		    
//		    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		    Date date = new Date();
		    PreparedStatement pstmt2= conn.prepareStatement("INSERT INTO CHECKIN (CUSTOMERID, HOTELID, NUMBER, BILLID, CHECKINDATE, CHECKINTIME, CHECKOUTDATE, CHECKOUTTIME, GUESTS) VALUES (?, ?, ?, ?, CURDATE(), CURTIME(), null, null, ?)");
		    pstmt2.setInt(1,  customerId);
		    pstmt2.setInt(2,  hotelId);
		    pstmt2.setInt(3,  roomId);
		    pstmt2.setInt(4,  billId);
//		    pstmt2.setString(5, dateFormat.format(date));
//		    pstmt2.setString(6, timeFormat.format(date));
		    pstmt2.setInt(5,  noOfGuests);
		    pstmt2.executeUpdate();
		    		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ResultSet getCustomer(int id)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from CUSTOMER WHERE ID=?");
		    pstmt.setInt(1, id);
		    rs= pstmt.executeQuery();

		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllCustomers()
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    rs= stmt.executeQuery("SELECT * from CUSTOMER");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}
