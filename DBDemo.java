// Acknowledgments: This example is a modification of code provided
// by Dimitri Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support
// Relpace all $USER$ with your unity id and $PASSWORD$ with your 9 digit student id or updated password (if changed)

import java.sql.*;

public class DBDemo {

    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/rshah8";

    public static void main(String[] args) {
        try {

            // Load the driver. This creates an instance of the driver
	    // and calls the registerDriver method to make MariaDB Thin
	    // driver, available to clients.

        Class.forName("org.mariadb.jdbc.Driver");

        String user = "rshah8";
        String passwd = "200204305";

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {

		// Get a connection from the first driver in the
		// DriverManager list that recognizes the URL jdbcURL

		conn = DriverManager.getConnection(jdbcURL, user, passwd);

		// Create a statement object that will be sending your
		// SQL statements to the DBMS

		stmt = conn.createStatement();

		// Create the BOOKS table

		stmt.executeUpdate("CREATE TABLE CUSTOMER (ID INT PRIMARY KEY, NAME VARCHAR(32) NOT NULL, DOB VARCHAR(32), PHONE VARCHAR(32) NOT NULL, EMAIL VARCHAR(32), SSN VARCHAR (32) NOT NULL, ADDRESS VARCHAR(64), HASHOTELCARD TINYINT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE STAFF (ID INT PRIMARY KEY, NAME VARCHAR(32) NOT NULL, TITLE VARCHAR(32) NOT NULL, DEPARTMENT VARCHAR(32), ADDRESS VARCHAR(64) NOT NULL, PHONE VARCHAR(32) NOT NULL, AVAILABILITY TINYINT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE SERVICE (ID INT PRIMARY KEY, NAME VARCHAR(32) NOT NULL, COST FLOAT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE HOTEL (ID INT PRIMARY KEY, NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(64) NOT NULL, PHONE VARCHAR(32) NOT NULL, MANAGERID INT, FOREIGN KEY(MANAGERID) REFERENCES STAFF(ID))");
        stmt.executeUpdate("CREATE TABLE ROOM (NUMBER INT, HOTELID INT, FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), PRIMARY KEY(NUMBER, HOTELID), CATEGORY VARCHAR(32) NOT NULL, RATE FLOAT NOT NULL, AVAILABILITY TINYINT NOT NULL, MAXOCCUPANCY INT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE BILL (ID INT PRIMARY KEY, AMOUNT FLOAT NOT NULL, MODEOFPAYMENT VARCHAR(32) NOT NULL, DISCOUNT FLOAT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE HIRES (HOTELID INT, STAFFID INT, FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), FOREIGN KEY(STAFFID) REFERENCES STAFF(ID), PRIMARY KEY(HOTELID, STAFFID))");
        stmt.executeUpdate("CREATE TABLE ASSIGNED (HOTELID INT, NUMBER INT, STAFFID INT, FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER), FOREIGN KEY(STAFFID) REFERENCES STAFF(ID), PRIMARY KEY(HOTELID, NUMBER, STAFFID))");
        stmt.executeUpdate("CREATE TABLE PROVIDES (HOTELID INT, NUMBER INT, STAFFID INT, SERVICEID INT, FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER), FOREIGN KEY(STAFFID) REFERENCES STAFF(ID), FOREIGN KEY(SERVICEID) REFERENCES SERVICE(ID), PRIMARY KEY(HOTELID, NUMBER, STAFFID, SERVICEID), TIMESTAMP DATE NOT NULL)");
        stmt.executeUpdate("CREATE TABLE CHECKIN (CUSTOMERID INT, HOTELID INT, NUMBER INT, BILLID INT, FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID), FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER), FOREIGN KEY(BILLID) REFERENCES BILL(ID), PRIMARY KEY(CUSTOMERID, HOTELID, NUMBER, BILLID), CHECKINTIME DATE NOT NULL, CHECKOUTTIME DATE, GUESTS INT NOT NULL)");
        stmt.executeUpdate("CREATE TABLE PRESIDENTIAL (NUMBER INT, HOTELID INT, FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER), FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID), PRIMARY KEY(NUMBER, HOTELID))");

		// Populate the BOOKS table

		stmt.executeUpdate("INSERT INTO CUSTOMER VALUES (1, 'Rohil Shah', '06-10-1995', '9199043952', 'rshah8@ncsu.edu', '12345678', 'abc, raleigh, nc', 1)");
		stmt.executeUpdate("INSERT INTO CUSTOMER VALUES (2, 'Uddhav Bhosle', '06-11-1995', '9199043955', 'ubhosle@ncsu.edu', '12445678', 'abcd, raleigh, nc', 1)");
        stmt.executeUpdate("INSERT INTO CUSTOMER VALUES (3, 'Tanay Kothari', '06-12-1995', '9199673952', 'tkothari@ncsu.edu', '52345678', 'bc, raleigh, nc', 0)");
        stmt.executeUpdate("INSERT INTO CUSTOMER VALUES (4, 'Rahul Coutinho', '06-11-1994', '9199047775', 'rcoutin@ncsu.edu', '12468678', 'a, raleigh, nc', 0)");

        stmt.executeUpdate("INSERT INTO STAFF VALUES (1, 'Sid', 'Manager', NULL, 'raleigh', '9199345678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (2, 'VJ', 'Front Desk Representative', 'Desk', 'raleigh', '9499345678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (3, 'PD', 'Room Service Staff', 'Service', 'raleigh', '9188345678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (4, 'Siddesh', 'Service Staff', 'Service', 'raleigh', '9190345678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (5, 'Hell', 'Manager', NULL, 'raleigh', '9199665678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (6, 'Udhs', 'Manager', NULL, 'raleigh', '9199775678', 1)");
        stmt.executeUpdate("INSERT INTO STAFF VALUES (7, 'Dev', 'Manager', NULL, 'raleigh', '9199885678', 1)");

        stmt.executeUpdate("INSERT INTO SERVICE VALUES (1, 'Massage', 55)");
        stmt.executeUpdate("INSERT INTO SERVICE VALUES (2, 'Laundry', 11)");
        stmt.executeUpdate("INSERT INTO SERVICE VALUES (3, 'Spa', 23)");
        stmt.executeUpdate("INSERT INTO SERVICE VALUES (4, 'Gym', 10)");

        stmt.executeUpdate("INSERT INTO HOTEL VALUES (1, 'Raleigh Hotel', 'Raleigh', '9199345678', 1)");
        stmt.executeUpdate("INSERT INTO HOTEL VALUES (2, 'Boston Hotel', 'Boston', '9199345600', 5)");
        stmt.executeUpdate("INSERT INTO HOTEL VALUES (3, 'NYC Hotel', 'NYC', '9199345600', 6)");
        stmt.executeUpdate("INSERT INTO HOTEL VALUES (4, 'LA Hotel', 'LA', '9199345633', 7)");

        stmt.executeUpdate("INSERT INTO ROOM VALUES (101, 1, 'Deluxe', 50, 0, 4)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (102, 2, 'Deluxe', 55, 1, 4)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (201, 3, 'Presidential', 100, 1, 6)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (202, 3, 'Presidential', 100, 1, 6)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (203, 3, 'Presidential', 100, 1, 6)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (204, 3, 'Presidential', 100, 1, 6)");
        stmt.executeUpdate("INSERT INTO ROOM VALUES (201, 4, 'Standard', 22, 1, 4)");

        stmt.executeUpdate("INSERT INTO BILL VALUES (1, 250, 'Cash', 0)");
        stmt.executeUpdate("INSERT INTO BILL VALUES (2, 150, 'Card', 5)");
        stmt.executeUpdate("INSERT INTO BILL VALUES (3, 200, 'Cash', 5)");
        stmt.executeUpdate("INSERT INTO BILL VALUES (4, 220, 'Cash', 5)");

        stmt.executeUpdate("INSERT INTO HIRES VALUES (1, 1)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (2, 2)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (3, 3)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (4, 4)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (2, 6)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (3, 6)");
        stmt.executeUpdate("INSERT INTO HIRES VALUES (4, 7)");

        stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 201, 3)");
        stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 202, 4)");
        stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 203, 2)");
        stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (4, 201,7)");

        stmt.executeUpdate("INSERT INTO PROVIDES VALUES (1, 101, 1, 1, '2019-01-23')");
        stmt.executeUpdate("INSERT INTO PROVIDES VALUES (2, 102, 2, 1, '2019-01-23')");
        stmt.executeUpdate("INSERT INTO PROVIDES VALUES (3, 201, 3, 1, '2019-01-23')");
        stmt.executeUpdate("INSERT INTO PROVIDES VALUES (4, 201, 4, 1, '2019-01-23')");

        stmt.executeUpdate("INSERT INTO CHECKIN VALUES (1, 1, 101, 1, '2019-01-23', '2019-01-26', 2)");
        stmt.executeUpdate("INSERT INTO CHECKIN VALUES (2, 2, 102, 2, '2019-01-23', '2019-01-26', 4)");
        stmt.executeUpdate("INSERT INTO CHECKIN VALUES (3, 3, 201, 3, '2019-01-23', '2019-01-26', 4)");
        stmt.executeUpdate("INSERT INTO CHECKIN VALUES (4, 4, 201, 4, '2019-01-23', '2019-01-26', 4)");

        stmt.executeUpdate("INSERT INTO PRESIDENTIAL VALUES (201, 3)");
        stmt.executeUpdate("INSERT INTO PRESIDENTIAL VALUES (202, 3)");
        stmt.executeUpdate("INSERT INTO PRESIDENTIAL VALUES (203, 3)");
        stmt.executeUpdate("INSERT INTO PRESIDENTIAL VALUES (204, 3)");
		// stmt.executeUpdate("INSERT INTO BOOKS VALUES ('Queen Lucia', 72, 5.99, 0)");
		// stmt.executeUpdate("INSERT INTO BOOKS VALUES ('A Calendar of Sonnets', 101, 3.49, 15)");
		// stmt.executeUpdate("INSERT INTO BOOKS VALUES ('Napoleon and Blucher', 5, 9.99, 0)");

            } finally {
                close(rs);
                close(stmt);
                close(conn);
            }
        } catch(Throwable oops) {
            oops.printStackTrace();
        }
    }

    static void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    static void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }
}
