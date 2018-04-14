package src;

import java.sql.*;

public class Room {

 /* Data -
 * hotelID, number, maxOccupancy, nightlyRate, category, availaility;
 */
	
	public static void createRoom(int id, int number, String category, int nightlyRate, int availability , int maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ROOM (HotelID, Number, Category, Rate, Availability, MaxOccupancy) VALUES (?,?,?,?,?,?)");
		    pstmt.setInt(1, id);
		    pstmt.setInt(2, number);
		    pstmt.setString(3, category);
		    pstmt.setInt(4, nightlyRate);
		    pstmt.setInt(5, availability);
		    pstmt.setInt(6, maxOccupancy);
		    pstmt.executeUpdate();
//			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	public static void updateRoom(int hotelID, int number, String category, int nightlyRate, int availability, int maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE ROOM SET category=?, rate=?, availability=?, maxOccupancy=? WHERE hotelid=? AND number=?");
		    pstmt.setString(1, category);
		    pstmt.setInt(2, nightlyRate);
		    pstmt.setInt(3, availability);
		    pstmt.setInt(4, maxOccupancy);
		    pstmt.setInt(5, hotelID);
		    pstmt.setInt(6, number);
		    pstmt.executeUpdate();
		    
//		    stmt.executeUpdate("UPDATE CUSTOMER SET Name='"+name+"', dob='"+dob+"', phone='"+phoneNumber+"', email='"+email+"', ssn='"+ssn+"', address='"+address+"', hashotelcard="+hasHotelCard +"WHERE ID="+id+")");
		    
		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	static void deleteRoom(String hotelID, int number) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ROOM WHERE hotelId=? AND number=?");
		    pstmt.setString(1, hotelID);
		    pstmt.setInt(2, number);
		    pstmt.executeUpdate();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	static void checkRoomAvailability(int hotelId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
			// query
		    stmt.executeUpdate("SELECT * FROM ROOM WHERE availability=1 AND hotelId="+hotelId);
		    
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	static void checkRoomAvailability(int hotelId, String category) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate("SELECT * FROM ROOM WHERE availability=1 AND hotelId="+hotelId + " AND category='"+category+"'");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	static void releaseRoom(int hotelId, int number) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE ROOM SET availability=1 WHERE hotelid=? AND number=?");
		    pstmt.setInt(1, hotelId);
		    pstmt.setInt(2, number);
		    pstmt.executeUpdate();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	static void addServiceToRoom(int hotelId, int number, int staffId,int  serviceId) {
		try {
			Connection conn = DBConnection.getConnection();
			// query
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PROVIDES (HotelID, Number, StaffID, ServiceID, Timestamp) VALUES (?,?,?,?,NOW())");
		    pstmt.setInt(1, hotelId);
		    pstmt.setInt(2, number);
		    pstmt.setInt(3, staffId);
		    pstmt.setInt(4, serviceId);
		    pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	static void addStaffToPresidential(int hotelId, int number, int staffId) {
		try {
			Connection conn = DBConnection.getConnection();
			// query
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ASSIGNED (HotelID, Number, StaffID) VALUES (?,?,?)");
		    pstmt.setInt(1, hotelId);
		    pstmt.setInt(2, number);
		    pstmt.setInt(3, staffId);
		    pstmt.executeUpdate();
		    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE STAFF SET availability=0 WHERE hotelid=? AND staffId=?");
		    pstmt2.setInt(1, hotelId);
		    pstmt2.setInt(2, staffId);
		    pstmt2.executeUpdate();
		    
		} catch (Exception e) {
			System.out.println(e);
		}
		// To create an entry in the provides table, here we have a common serviceId for the service to provide to presidential suite. 
	}
	
	public static ResultSet getRoom(int hotelId, int number)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from ROOM WHERE number=? AND hotelid=?");
		    pstmt.setInt(1, number);
		    pstmt.setInt(2, hotelId);
		    rs= pstmt.executeQuery();

		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllRoomsAllHotels()
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    rs= stmt.executeQuery("SELECT * from ROOM");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllRooms(int hotelId)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from ROOM WHERE number=? AND hotelid=?");
		    pstmt.setInt(1, hotelId);
		    rs= pstmt.executeQuery();
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
}