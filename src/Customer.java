package src;

import java.sql.*;

public class Customer {
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
		    
		    
//		    stmt.executeUpdate("UPDATE CUSTOMER SET Name='"+name+"', dob='"+dob+"', phone='"+phoneNumber+"', email='"+email+"', ssn='"+ssn+"', address='"+address+"', hashotelcard="+hasHotelCard +"WHERE ID="+id+")");
		    
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteCustomer(String id) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    stmt.executeUpdate("DELETE FROM CUSTOMER WHERE id='"+id);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//billid global variable 
	public static void assignRoom(String customerId, String hotelId, String roomId, String billID) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    
		    stmt.executeUpdate("ASSIGN");
		    stmt.executeUpdate("INSERT INTO BILL(ID, Amount, ModeOfPayment, Discount) VALUES ("+billID+","+"0, null, 0");
		    stmt.executeUpdate("CHECKIN"+roomId);
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ResultSet getCustomer(String id)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    rs= stmt.executeQuery("SELECT * from CUSTOMER WHERE id="+id+")");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}
