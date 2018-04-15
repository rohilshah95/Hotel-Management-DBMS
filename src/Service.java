package src;

import java.sql.*;

public class Service {
	static int serviceId=5;
	public static void createService( String name, int cost) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    serviceId++;
		    ResultSet rs = null;
		    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SERVICE(ID, NAME, COST) VALUES(?,?,?)");
		    pstmt.setInt(1,  serviceId);
		    pstmt.setString(2, name);
		    pstmt.setInt(3, cost);
		    
			// query
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void deleteService(int id) {
		try {
			Connection conn = DBConnection.getConnection();
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    
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
		    Statement stmt = conn.createStatement();
		    ResultSet rs = null;
		    PreparedStatement pstmt = conn.prepareStatement("UPDATE SERVICE SET NAME=?, COST=? WHERE ID=?");
		    pstmt.setString(1, name);
		    pstmt.setInt(2, cost);
		    pstmt.setInt(3, serviceId);
		    
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
		    Statement stmt = conn.createStatement();
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
