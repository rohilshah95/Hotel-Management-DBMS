package src;

import java.sql.*;

public class Room {

	/*
	 * create an entry into the rooms table and if the ROOM is a presidential suite then add it to the PRESIDENTIAL table as well.
	 * 
	 * Input Parameters: Hotel_id, room number, category, nightlyRate, availability, maxOccupancy
	 * Output Parameters: N/A
	 */

	public static void createRoom(int id, int number, String category, int nightlyRate, int availability,
			int maxOccupancy) {
		ResultSet rs=null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO ROOM (HotelID, Number, Category, Rate, Availability, MaxOccupancy) VALUES (?,?,?,?,?,?)");
			pstmt.setInt(1, id);
			pstmt.setInt(2, number);
			pstmt.setString(3, category);
			pstmt.setInt(4, nightlyRate);
			pstmt.setInt(5, availability);
			pstmt.setInt(6, maxOccupancy);
			pstmt.executeUpdate();
			
			if (category.equalsIgnoreCase("presidential"))
			{
				PreparedStatement pstmt1=conn.prepareStatement("INSERT INTO PRESIDENTIAL(NUMBER, HOTELID) VALUES (?, ?)");
				pstmt1.setInt(1, number);
				pstmt1.setInt(2, id);
				pstmt1.executeUpdate();
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Update information about a particular room in a hotel
	 * Input Parameters: Hotel_id, room number, category, nightlyRate, availability, maxOccupancy
	 * Output Paramters: N/A
	 */
	public static void updateRoom(int hotelID, int number, String category, int nightlyRate, int availability,
			int maxOccupancy) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ROOM SET category=?, rate=?, availability=?, maxOccupancy=? WHERE hotelid=? AND number=?");
			pstmt.setString(1, category);
			pstmt.setInt(2, nightlyRate);
			pstmt.setInt(3, availability);
			pstmt.setInt(4, maxOccupancy);
			pstmt.setInt(5, hotelID);
			pstmt.setInt(6, number);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Delete a room entry from the database. If the room is a presidential suite then it is deleted from the
	 * presidential table by a cascade operation.
	 * 
	 * Input Parameters: Hotel_id, room number, category, nightlyRate, availability, maxOccupancy
	 * Output Paramters: N/A
	 */
	public static void deleteRoom(int hotelID, int number) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ROOM WHERE hotelId=? AND number=?");
			pstmt.setInt(1, hotelID);
			pstmt.setInt(2, number);
			pstmt.executeUpdate();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Get all the rooms information for a given hotel which are not occupied.
	 * 
	 * Input Parameters: Hotel_id
	 * Output Paramters: All the details of available rooms.
	 */
	public static ResultSet checkRoomAvailability(int hotelId) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			// query
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM ROOM WHERE AVAILABILITY=1 AND HOTELID=?");
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	/*
	 * Get all the rooms information for a given hotel and category which are not occupied.
	 * 
	 * Input Parameters: Hotel_id, category
	 * Output Paramters: All the details of available rooms for the given category.
	 */
	public static ResultSet checkRoomAvailability(int hotelId, String category) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("SELECT * FROM ROOM WHERE AVAILABILITY=1 AND HOTELID=? AND CATEGORY=?");
			pstmt.setInt(1, hotelId);
			pstmt.setString(2, category);
			rs = pstmt.executeQuery();
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * Update the entry for the customer's current checkin and set the checkout time and date.
	 * Release room by making it available for further use.
	 * 
	 * Input Parameters: Hotel_id, room_number, customer_id
	 * Output Paramters: N/A
	 */
	public static void releaseRoom(int hotelId, int number, int custId) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt1 = conn.prepareStatement(
					"UPDATE CHECKIN SET CHECKOUTDATE = CURDATE(), CHECKOUTTIME=CURTIME() WHERE CUSTOMERID =? AND CHECKOUTDATE IS NULL");
			pstmt1.setInt(1, custId);
			pstmt1.executeUpdate();

			PreparedStatement pstmt = conn
					.prepareStatement("UPDATE ROOM SET availability=1 WHERE hotelid=? AND number=?");
			pstmt.setInt(1, hotelId);
			pstmt.setInt(2, number);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Add service to a particular room for the given time. This entry comes in the provides table
	 * 
	 * Input Parameters: hotelId, room number, staffId, serviceId
	 * Output Paramters: N/A
	 */
	public static void addServiceToRoom(int hotelId, int number, int staffId, int serviceId) {
		try {
			Connection conn = DBConnection.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO PROVIDES (HotelID, Number, StaffID, ServiceID, Date, Time) VALUES (?,?,?,?,CURDATE(),CURTIME())");
			pstmt.setInt(1, hotelId);
			pstmt.setInt(2, number);
			pstmt.setInt(3, staffId);
			pstmt.setInt(4, serviceId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Add a aprticular dedicated staff to a presidential suite
	 * 
	 * Input Parameters: hotelId, room number, staffId
	 * Output Paramters: N/A
	 */
	public static void addStaffToPresidential(int hotelId, int number, int staffId) {
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO ASSIGNED (HotelID, Number, StaffID) VALUES (?,?,?)");
			pstmt.setInt(1, hotelId);
			pstmt.setInt(2, number);
			pstmt.setInt(3, staffId);
			pstmt.executeUpdate();
			PreparedStatement pstmt2 = conn
					.prepareStatement("UPDATE STAFF SET availability=0 WHERE Id=?");
			pstmt2.setInt(1, staffId);
			pstmt2.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Get all the details of a particular room in the hotel
	 * 
	 * Input Parameters: hotelId, room number
	 * Output Paramters: all the details of the room
	 */
	public static ResultSet getRoom(int hotelId, int number) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * from ROOM WHERE number=? AND hotelid=?");
			pstmt.setInt(1, number);
			pstmt.setInt(2, hotelId);
			rs = pstmt.executeQuery();

			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * Get all the details of all the rooms in the room table
	 * 
	 * Input Parameters: N/A
	 * Output Paramters: all the details in the room table
	 */
	public static ResultSet getAllRoomsAllHotels() {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();

			rs = stmt.executeQuery("SELECT * from ROOM");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * Get all the details of all rooms in the hotel
	 * 
	 * Input Parameters: hotelId
	 * Output Paramters: all the rooms information for the hotel
	 */
	public static ResultSet getAllRooms(int hotelId) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * from ROOM WHERE hotelid=?");
			pstmt.setInt(1, hotelId);
			rs = pstmt.executeQuery();

			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}