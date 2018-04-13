package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String user = "rshah8";
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/" + user;
	private static String passwd = "200204305";
	private static Connection conn = null;

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

	public static void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Throwable whatever) {
			}
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}