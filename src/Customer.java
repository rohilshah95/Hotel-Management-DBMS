package src;

import java.sql.*;

public class Customer {
	/*
	 * Create a customer by passing all the attributes that are required for the customer table.
	 * On inserting the row into the table, get the id of the customer created by finding the last id created.
	 * Return this id for further use
	 * Input Parameters:  name, dob, phoneNumber, email, ssn, address, hasHotelCard
	 * Output Parameters: customerId
	 */
	public static ResultSet createCustomer(String name, String dob, String phoneNumber, String email, String ssn,
			String address, Byte hasHotelCard) {
		ResultSet rs=null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO CUSTOMER (NAME, DOB, PHONE, EMAIL,SSN, ADDRESS, HASHOTELCARD) VALUES (?,?,?,?,?,?,?)");
		    pstmt.setString(1, name);
		    pstmt.setString(2, dob);
		    pstmt.setString(3, phoneNumber);
		    pstmt.setString(4, email);
		    pstmt.setString(5, ssn);
		    pstmt.setString(6, address);
		    pstmt.setByte(7, hasHotelCard);
		    pstmt.executeUpdate();
		    
		    rs=conn.createStatement().executeQuery("SELECT MAX(ID) AS NEW_CUST_ID FROM CUSTOMER"); 
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * Update a particular entry in the customer based on the customer ID. This function sets the details for this row as 
	 * per the passed parameters.
	 * Input Parameters:  customer_id, name, dob, phoneNumber, email, ssn, address, hasHotelCard
	 * Output Paramters: N/A
	 */
	public static void updateCustomer(int id, String name, String dob, String phoneNumber, String email, String ssn,
			String address, Byte hasHotelCard) {
		try {
			Connection conn = DBConnection.getConnection();
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


		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 * Delete customer entry from the table by giving the customer id.
	 * Input Paramters: customer_id
	 * Output Paramters: N/A
	 */
	public static void deleteCustomer(int id) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("DELETE FROM CUSTOMER WHERE ID=?");
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/*
	 *  Assign room to a customer. For this we need to pass the customer id, hotel id, room id and no of guests that will occupy the room.
	 *  Firstly, we set the  availability of that room to 0, thus making it unavailable for occupancy.
	 *  Then we are adding an entry into the bill table for this checkin of the customer. 
	 *  This will initialize the entry which will be updated on checkout.
	 *  After doing that we get the bill id of this entry int the bill. 
	 *  Then suing this bill id and the earlier details passed in the function we created a new entry into the 
	 *  CHECKSIN table.
	 *  
	 *	Input Paramters: customerId, hotelId, roomId, noOfGuests
	 *  Output Paramters: N/A
	 *  
	 *  
	 *  Here we have implemented rollback feature. If there is an error in any of the MySQL executions then we rollback
	 *  all the changes that we have made before that error.
	 */
	public static void assignRoom(int customerId, int hotelId, int roomId, int noOfGuests) {
		ResultSet rs=null;
		try {
			
		Connection conn = DBConnection.getConnection();
		try {
			// disable auto commit
			conn.setAutoCommit(false);
								
		    PreparedStatement pstmt= conn.prepareStatement("UPDATE ROOM SET Availability=0 WHERE number=? and hotelid=? ");
		    pstmt.setInt(1, roomId);
		    pstmt.setInt(2, hotelId);
		    pstmt.executeUpdate();

		    PreparedStatement pstmt1= conn.prepareStatement("INSERT INTO BILL (AMOUNT, MODEOFPAYMENT, DISCOUNT) VALUES (0, NULL, 0)");
		    pstmt1.executeUpdate();

		    PreparedStatement pstmt3= conn.prepareStatement("SELECT MAX(id) from BILL");
		    rs=pstmt3.executeQuery();
			
			
		    int billId=0;
			while (rs.next()) {
				billId=rs.getInt(1);
			}

		    PreparedStatement pstmt2= conn.prepareStatement("INSERT INTO CHECKIN (CUSTOMERID, HOTELID, NUMBER, BILLID, CHECKINDATE, CHECKINTIME, CHECKOUTDATE, CHECKOUTTIME, GUESTS) VALUES (?, ?, ?, ?, CURDATE(), CURTIME(), null, null, ?)");
		    pstmt2.setInt(1,  customerId);
		    pstmt2.setInt(2,  hotelId);
		    pstmt2.setInt(3,  roomId);
		    pstmt2.setInt(4,  billId);
		    pstmt2.setInt(5,  noOfGuests);
		    pstmt2.executeUpdate();

			// commit the changes
		    conn.commit();
		} 
		catch (Exception e) {
			System.out.println("Exception!! rolling back\n" + e);
			// rollback on error in execution of any query
			conn.rollback();
		}
		// set autocommit back to true
		conn.setAutoCommit(true);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	/*
	 * Get all the details for a particular customer.
	 * Input Parameters: customer_id
	 * Output Parameters: All the details of the customer
	 */
	public static ResultSet getCustomer(int id)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from CUSTOMER WHERE ID=?");
		    pstmt.setInt(1, id);
		    rs= pstmt.executeQuery();

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * Get all the details in the customer tables. i.e. info of all the customers.
	 * Input Parameters: N/A
	 * Output Parameters: The entire customer table
	 * 
	 */
	public static ResultSet getAllCustomers()
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();

		    rs= stmt.executeQuery("SELECT * from CUSTOMER");
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}
