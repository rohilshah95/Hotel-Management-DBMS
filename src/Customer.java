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

	public void createCustomer(String id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, String hasHotelCard) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateCustomer(String id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, String hasHotelCard) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteCustomer(String id) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void assignRoom(int customerId, int hotelId, int roomId) {
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
