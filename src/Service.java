package src;

import java.sql.*;

public class Service {
	public static ResultSet createService( String name, int cost) {
		ResultSet rs=null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SERVICE( NAME, COST) VALUES(?,?)");
		    pstmt.setString(1, name);
		    pstmt.setInt(2, cost);
		    
		    rs=conn.createStatement().executeQuery("SELECT MAX(ID) AS NEW_SERVICE_ID FROM SERVICE"); 

		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	public static void deleteService(int id) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt=conn.prepareStatement("DELETE FROM SERVICE WHERE ID=?");
		    pstmt.setInt(1, id);
		    pstmt.executeUpdate();
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateService(int serviceId, String name, int cost) {
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE SERVICE SET NAME=?, COST=? WHERE ID=?");
		    pstmt.setString(1, name);
		    pstmt.setInt(2, cost);
		    pstmt.setInt(3, serviceId);
		    pstmt.executeUpdate();
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static ResultSet getService(int serviceId)
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement("SELECT * from SERVICE WHERE ID=?");
		    pstmt.setInt(1, serviceId);
		    rs= pstmt.executeQuery();

		    // query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
	public static ResultSet getAllServices()
	{
		ResultSet rs = null;
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
	
		    rs= stmt.executeQuery("SELECT * from SERVICE");
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}
	
}
