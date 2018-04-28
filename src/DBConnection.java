package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String user = "username here ";
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user;  //enter your mariadb URL
	private static String passwd = "password here";
	private static Connection conn = null;

	/*
	 * Initialize the connection with the database
	 */
	public static void initialize() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				
				conn = DriverManager.getConnection(jdbcURL, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Close the connection
	 */
	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Throwable whatever) {
			}
		}
	}

	/*
	 * Get the connection to be used in all classes
	 */
	public static Connection getConnection() {
		return conn;
	}
}
