package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Room {

 /* Data -
 * hotelID, number, maxOccupancy, nightlyRate, category, availaility;
 */
	
	void createRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	void editRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	void deleteRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void checkRoomAvailability() {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void checkRoomAvailability(String category) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	void assignRoom(String customerId, String hotelId, String roomNumber) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Create entry in check-in table
		// Create a bill in bill table
	}
	
	void releaseRoom(String hotelId, String number) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	void addServiceToRoom(String hotelId, String number, String serviceId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	void addStaffToPresidential(String hotelId, String number) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		// To create an entry in the provides table, here we have a common serviceId for the service to provide to presidential suite. 
	}
	
}