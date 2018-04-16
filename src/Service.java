package src;

import java.sql.*;

public class Service {
	/*
	 * This function is responsible for creating the entries in the Service table when new Service is added.
	 * It takes the parameters required and inserts into the database.
	 * Input Parameters: Name, Cost
	 * Return Value: Service ID
	 */
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
	
	/*
	 * This function deletes the Service with the specified ID.
	 * Input Parameters: Service ID
	 * Return Value: N/A
	 */
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

	/*
	 * This function is responsible for updating everything in the Service Table other than the Service ID. It uses the Service ID in the where clause to update the row.
	 * Input Parameters: Service ID, Name, Cost 
	 * Return Value: N/A
	 */
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
	
	/*
	 * This function returns all the information of a particular Hotel when the Service ID is specified.
	 * Input Parameters: Service ID
	 * Return Value: All information about Service
	 */
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
	
	/*
	 * This function returns a list of all Services and all their information.
	 * Input parameters: N/A
	 * Return Value: All information about all Services.
	 */
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
