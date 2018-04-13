package src;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	static int id = 4; // because demo data already has 4 entries

	void createBill(String amount, String modeOfPayment, String discount) {
		try {
			id++;
			Connection conn = DBConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			// create entry

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void calcBill(String custId, String checkOutDate) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// create entry
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void generateReceipt(String custId, String checkOutDate) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
			// create entry
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
