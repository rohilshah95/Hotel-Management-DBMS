package src;

import java.sql.*;

public class Bill {

	public static ResultSet calcBill(int custId, String checkOutDate, String modeOfPayment) {
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		 
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE Bill SET Discount=5 WHERE ID IN (SELECT CheckIn.BillID FROM CheckIn JOIN Customer WHERE Customer.hasHotelCard=1 AND Customer.ID=?)");
		    pstmt.setInt(1, custId);
		    pstmt.executeUpdate();
		    
		    PreparedStatement pstmt1 = conn.prepareStatement("UPDATE CHECKIN SET CHECKOUTDATE = CURDATE(), CHECKOUTTIME=CURTIME() WHERE CUSTOMERID =? AND CHECKOUTDATE=NULL");
		    pstmt1.setInt(1, custId);
		    pstmt1.executeUpdate();
		    
		    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE BILL SET ModeOfPayment =? WHERE ID = ?;");
		    pstmt2.setString(1, modeOfPayment);
		    pstmt2.setInt(2, custId);
		    pstmt2.executeUpdate();
		    
		    PreparedStatement pstmt3= conn.prepareStatement("UPDATE BILL SET AMOUNT = (100-discount)/100*" + 
		    		"(SELECT SUM(COST) from(" + 
		    		"(SELECT SERVICE.Cost AS Cost" + 
		    		"FROM CHECKIN JOIN PROVIDES JOIN SERVICE" + 
		    		"WHERE (CHECKIN.CustomerId=? AND" + 
		    		"CHECKIN.CheckOutDate=?))" + 
		    		"UNION" + 
		    		"(SELECT (DATEDIFF(Checkoutdate,checkindate))*Rate AS Cost" + 
		    		"FROM ROOM JOIN CHECKIN" + 
		    		"WHERE (CHECKIN.CUSTOMERID = ? AND" + 
		    		"CHECKIN.CHECKOUTDATE=?) )) as n)" +  
		    		"WHERE BILL.ID = (SELECT BILLID" + 
		    		"FROM CHECKIN" + 
		    		"WHERE CUSTOMERID = ?);");
		    pstmt3.setInt(1, custId);
		    pstmt3.setString(2, checkOutDate);
		    pstmt3.setInt(3, custId);
		    pstmt3.setString(4, checkOutDate);
		    pstmt3.setInt(5, custId);
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

	public static ResultSet generateReceipt(int custId, String checkOutDate) {
	    ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    
		    PreparedStatement pstmt = conn.prepareStatement("(SELECT SERVICE.Cost AS Cost, SERVICE.name as Name" + 
		    		"FROM CHECKIN JOIN PROVIDES JOIN SERVICE" + 
		    		"WHERE (CHECKIN.CustomerId=? AND CHECKIN.CheckOutdate=?))" + 
		    		"UNION" + 
		    		"(SELECT (DATEDIFF(Checkoutdate,checkindate))*Rate AS Cost, 'Room' " + 
		    		"as name" + 
		    		"FROM ROOM JOIN CHECKIN" + 
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
