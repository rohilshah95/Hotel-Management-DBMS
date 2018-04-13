package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hotel {

	/*
	 * Data - id, managerId, long phoneNumber, address, city;
	 */
	void createHotel(String id, String address, String phoneNumber, String managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void editHotel(String id, String address, String phoneNumber, String managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void deleteHotel(String id, String address, String phoneNumber, String managerId) {
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
