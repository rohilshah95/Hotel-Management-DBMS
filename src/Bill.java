package src;

import java.sql.*;

public class Bill {

	/*
	 * This function is responsible for calculating the Bill for a given Customer Stay.
	 * It adds the charges for Room and Services for the given customer stay.
	 * Input Parameters: Customer ID, Check Out Date, Mode of Payment, CreditDebit/Hotel Card Number
	 * Return Value: Result Set containing all Bill Information of the Customer for the given stay.
	 */
	public static ResultSet calcBill(int custId, String checkOutDate, String modeOfPayment, String cardNumber) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();

			try
			{
				conn.setAutoCommit(false);
			    PreparedStatement pstmt = conn.prepareStatement("UPDATE BILL SET Discount=5 WHERE ID = (SELECT CHECKIN.BillID FROM CHECKIN JOIN CUSTOMER ON (CHECKIN.CUSTOMERID=CUSTOMER.ID) WHERE CUSTOMER.hasHotelCard=1 AND CUSTOMER.ID=? AND CHECKIN.CHECKOUTDATE=CURDATE())");
			    pstmt.setInt(1, custId);
			    pstmt.executeUpdate();

	//		    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CHECKIN SET CHECKOUTDATE = CURDATE(), CHECKOUTTIME=CURTIME() WHERE CUSTOMERID =? AND CHECKOUTDATE=NULL");
	//		    pstmt1.setInt(1, custId);
	//		    pstmt1.executeUpdate();
//			    System.out.println(cardNumber);
			    if (cardNumber.equals("0")) {
			    	PreparedStatement pstmt2 = conn.prepareStatement("UPDATE BILL SET ModeOfPayment =?, CARDNUMBER=NULL WHERE ID = (SELECT CHECKIN.BillID FROM CHECKIN JOIN CUSTOMER ON (CHECKIN.CUSTOMERID=CUSTOMER.ID) WHERE CUSTOMER.ID=? AND CHECKIN.CHECKOUTDATE=CURDATE()) ");
				    pstmt2.setString(1, modeOfPayment);
				    pstmt2.setInt(2, custId);
				    pstmt2.executeUpdate();
			    }

			    else
			    {
			    	PreparedStatement pstmt2 = conn.prepareStatement("UPDATE BILL SET ModeOfPayment =?, CARDNUMBER=? WHERE ID = (SELECT CHECKIN.BillID FROM CHECKIN JOIN CUSTOMER ON (CHECKIN.CUSTOMERID=CUSTOMER.ID) WHERE CUSTOMER.ID=? AND CHECKIN.CHECKOUTDATE=CURDATE()) ");
				    pstmt2.setString(1, modeOfPayment);
				    pstmt2.setString(2, cardNumber);
				    pstmt2.setInt(3, custId);
				    pstmt2.executeUpdate();
			    }


			    PreparedStatement pstmt3= conn.prepareStatement("UPDATE BILL SET AMOUNT = (100-discount)/100*" +
	    		"(SELECT SUM(COST) from( " +
	    		"(SELECT SERVICE.Cost AS Cost " +
	    		"FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN SERVICE ON (SERVICE.ID=PROVIDES.SERVICEID) " +
	    		"WHERE (CHECKIN.CustomerId=? AND (PROVIDES.DATE>=CHECKIN.CHECKINDATE OR (PROVIDES.DATE=CHECKIN.CHECKINDATE AND PROVIDES.TIME>=CHECKIN.CHECKINTIME) AND (PROVIDES.DATE<=CHECKIN.CHECKOUTDATE OR (PROVIDES.DATE=CHECKIN.CHECKOUTDATE AND PROVIDES.TIME<=CHECKIN.CHECKOUTTIME))   ) AND " +
	    		"CHECKIN.CheckOutDate=?)) " +
	    		"UNION " +
	    		"(SELECT (IF (DATEDIFF(Checkoutdate,checkindate)=0, 1, DATEDIFF(Checkoutdate,checkindate)))*Rate AS Cost " +
	    		"FROM ROOM JOIN CHECKIN ON (ROOM.NUMBER=CHECKIN.NUMBER AND ROOM.HOTELID=CHECKIN.HOTELID) " +
	    		"WHERE (CHECKIN.CUSTOMERID = ? AND " +
	    		"CHECKIN.CHECKOUTDATE=?) )) as n) " +
	    		"WHERE BILL.ID = (SELECT BILLID " +
	    		"FROM CHECKIN " +
	    		"WHERE CUSTOMERID = ? AND CHECKIN.CHECKOUTDATE=?);");
	//		    PreparedStatement pstmt3= conn.prepareStatement("UPDATE BILL SET AMOUNT = (100-discount)/100* (SELECT SUM(COST) from((SELECT SERVICE.Cost AS Cost FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN SERVICE ON (SERVICE.ID=PROVIDES.SERVICEID) WHERE (CHECKIN.CustomerId=? AND PROVIDES.TIMESTAMP<=CHECKIN.CHECKOUTDATE AND PROVIDES.TIMESTAMP>=CHECKIN.CHECKINDATE AND CHECKIN.CHECKOUTDATE=CURDATE())) UNION (SELECT (DATEDIFF(Checkoutdate,checkindate))*Rate AS Cost FROM ROOM JOIN CHECKIN ON (ROOM.NUMBER=CHECKIN.NUMBER AND ROOM.HOTELID=CHECKIN.HOTELID WHERE (CHECKIN.CUSTOMERID = ? AND CHECKIN.CHECKOUTDATE=CURDATE()) )) as n) WHERE BILL.ID = (SELECT BILLID FROM CHECKIN WHERE CUSTOMERID = ? AND CHECKOUTDATE=CURDATE())");
			    pstmt3.setInt(1, custId);
			    pstmt3.setString(2, checkOutDate);
			    pstmt3.setInt(3, custId);
			    pstmt3.setString(4, checkOutDate);
			    pstmt3.setInt(5, custId);
			    pstmt3.setString(6, checkOutDate);
			    pstmt3.executeUpdate();

			    PreparedStatement pstmt4= conn.prepareStatement("SELECT * from BILL JOIN CHECKIN WHERE CHECKIN.BILLID=BILL.ID AND CHECKIN.CUSTOMERID=? AND CHECKIN.CHECKOUTDATE=?");
			    pstmt4.setInt(1, custId);
			    pstmt4.setString(2, checkOutDate);
			    rs = pstmt4.executeQuery();
			    //output
	//		    +----+--------+---------------+----------+------------+---------+--------+--------+-------------+-------------+--------------+--------------+--------+
	//		    | ID | AMOUNT | MODEOFPAYMENT | DISCOUNT | CUSTOMERID | HOTELID | NUMBER | BILLID | CHECKINDATE | CHECKINTIME | CHECKOUTDATE | CHECKOUTTIME | GUESTS |
	//		    +----+--------+---------------+----------+------------+---------+--------+--------+-------------+-------------+--------------+--------------+--------+
			}
			catch(SQLException sqle)
			{
				conn.rollback();
				System.out.println("Rolling back\nError in query exceution: " + sqle);
			}
			conn.commit();
			// create entry
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	/*
	 * This function is responsible for getting Bill Amount for a given customer stay.
	 * Input Parameters: Customer ID, Check Out Date
	 * Return Value: Result Set containing Bill Amount for a given customer stay.
	 */
	public static ResultSet getAmount(int custId, String checkOutDate) {
		ResultSet rs= null;
		try {
			Connection conn = DBConnection.getConnection();

			PreparedStatement pstmt4= conn.prepareStatement("SELECT * from BILL JOIN CHECKIN WHERE CHECKIN.BILLID=BILL.ID AND CHECKIN.CUSTOMERID=? AND CHECKIN.CHECKOUTDATE=?");
		    pstmt4.setInt(1, custId);
		    pstmt4.setString(2, checkOutDate);
		    rs = pstmt4.executeQuery();
			// create entry
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	/*
	 * This function is responsible for generating itemised receipt for a given customer stay.
	 * Input Parameters: Customer ID, Check Out Date
	 * Return Value: Result Set containing itemised receipt for a given customer stay.
	 */
	public static ResultSet generateReceipt(int custId, String checkOutDate) {
	    ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();

		    PreparedStatement pstmt = conn.prepareStatement("(SELECT SERVICE.Cost AS Cost, SERVICE.name as Name " +
		    		"FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN SERVICE ON (SERVICE.ID=PROVIDES.SERVICEID) " +
		    		"WHERE (CHECKIN.CustomerId=? AND CHECKIN.CheckOutdate=? AND (PROVIDES.DATE>=CHECKIN.CHECKINDATE OR (PROVIDES.DATE=CHECKIN.CHECKINDATE AND PROVIDES.TIME>=CHECKIN.CHECKINTIME) AND (PROVIDES.DATE<=CHECKIN.CHECKOUTDATE OR (PROVIDES.DATE=CHECKIN.CHECKOUTDATE AND PROVIDES.TIME<=CHECKIN.CHECKOUTTIME))   ) )) " +
		    		"UNION " +
		    		"(SELECT ( IF (DATEDIFF(Checkoutdate,checkindate)=0, 1, DATEDIFF(Checkoutdate,checkindate)))*Rate AS Cost, 'Room' " +
		    		"as name " +
		    		"FROM ROOM NATURAL JOIN CHECKIN " +
		    		"Where (CHECKIN.CUSTOMERID = ? AND CHECKIN.CHECKOUTDATE = ?) );");
		    pstmt.setInt(1, custId);
		    pstmt.setString(2, checkOutDate);
		    pstmt.setInt(3, custId);
		    pstmt.setString(4, checkOutDate);
		    rs= pstmt.executeQuery();
			// create entry
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

}
