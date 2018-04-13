package src;

import java.sql.*;
public class Customer {
	Connection conn = DBConnection.getConnection();
    Statement stmt = null;
    ResultSet rs = null;
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
			
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateCustomer(String id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, String hasHotelCard) {
		try {
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void deleteCustomer(String id) {
		try {
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void assignRoom(int customerId, int hotelId, int roomId) {
		try {
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
