package src;

import java.sql.*;

public class Bill {

	public static ResultSet calcBill(int custId, String checkOutDate, String modeOfPayment) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		 
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE BILL SET Discount=5 WHERE ID = (SELECT CHECKIN.BillID FROM CHECKIN JOIN CUSTOMER WHERE CUSTOMER.hasHotelCard=1 AND CUSTOMER.ID=? AND CHECKIN.CHECKOUTDATE=CURDATE())");
		    pstmt.setInt(1, custId);
		    pstmt.executeUpdate();
		    
//		    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CHECKIN SET CHECKOUTDATE = CURDATE(), CHECKOUTTIME=CURTIME() WHERE CUSTOMERID =? AND CHECKOUTDATE=NULL");
//		    pstmt1.setInt(1, custId);
//		    pstmt1.executeUpdate();
		    
		    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE BILL SET ModeOfPayment =? WHERE ID = ?;");
		    pstmt2.setString(1, modeOfPayment);
		    pstmt2.setInt(2, custId);
		    pstmt2.executeUpdate();
		    
		    PreparedStatement pstmt3= conn.prepareStatement("UPDATE BILL SET AMOUNT = (100-discount)/100*" + 
    		"(SELECT SUM(COST) from( " + 
    		"(SELECT SERVICE.Cost AS Cost " + 
    		"FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN SERVICE ON (SERVICE.ID=PROVIDES.SERVICEID) " + 
    		"WHERE (CHECKIN.CustomerId=? AND PROVIDES.TIMESTAMP<=CHECKIN.CHECKOUTDATE AND PROVIDES.TIMESTAMP>=CHECKIN.CHECKINDATE AND " + 
    		"CHECKIN.CheckOutDate=?)) " + 
    		"UNION " + 
    		"(SELECT (DATEDIFF(Checkoutdate,checkindate))*Rate AS Cost " + 
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

			// create entry
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return rs;
	}
	
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

	public static ResultSet generateReceipt(int custId, String checkOutDate) {
	    ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    
		    PreparedStatement pstmt = conn.prepareStatement("(SELECT SERVICE.Cost AS Cost, SERVICE.name as Name " + 
		    		"FROM (CHECKIN NATURAL JOIN PROVIDES) JOIN SERVICE ON (SERVICE.ID=PROVIDES.SERVICEID) " + 
		    		"WHERE (CHECKIN.CustomerId=? AND CHECKIN.CheckOutdate=? AND PROVIDES.TIMESTAMP<=CHECKIN.CHECKOUTDATE AND PROVIDES.TIMESTAMP>=CHECKIN.CHECKINDATE)) " + 
		    		"UNION " + 
		    		"(SELECT (DATEDIFF(Checkoutdate,checkindate))*Rate AS Cost, 'Room' " + 
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
