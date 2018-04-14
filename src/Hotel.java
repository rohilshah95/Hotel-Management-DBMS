package src;

import java.sql.Connection;
import java.sql.Statement;

public class Hotel {

	/*
	 * Data - id, managerId, long phoneNumber, address, city;
	 */
	void createHotel(String id, String address, String phoneNumber, String managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate("CREATE TABLE HOTEL (ID INT PRIMARY KEY,NAME VARCHAR(32) NOT NULL,ADDRESS VARCHAR(64) NOT NULL,PHONE VARCHAR(32) NOT NULL,MANAGERID INT,FOREIGN KEY(MANAGERID) REFERENCES STAFF(ID));");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void editHotel(String id,String name, String address, String phoneNumber, String managerId) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate("UPDATE HOTEL SET Name = '"+name+"', phone='"+phoneNumber+"', address='"+address+"', managerid="+managerId+" WHERE ID= "+id);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void deleteHotel(String id) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    stmt.executeUpdate("DELETE FROM HOTEL WHERE ID="+id);
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
