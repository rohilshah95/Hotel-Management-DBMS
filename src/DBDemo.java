package src;

import java.sql.*;

public class DBDemo {
//	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/rshah8";

	public static void initializeDB() {
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String user = "rshah8";
//			String passwd = "200204305";
//			Connection conn = null;
//			Statement stmt = null;
//			ResultSet rs = null;
			try {
				Connection conn = DBConnection.getConnection();
			    Statement stmt = conn.createStatement();
			 
				// Drop Tables
				stmt.executeUpdate("DROP TRIGGER IF EXISTS addpresidential");
				stmt.executeUpdate("Drop TABLE IF EXISTS PRESIDENTIAL");
				stmt.executeUpdate("Drop TABLE IF EXISTS CHECKIN");
				stmt.executeUpdate("Drop TABLE IF EXISTS PROVIDES");
				stmt.executeUpdate("Drop TABLE IF EXISTS ASSIGNED");
				stmt.executeUpdate("Drop TABLE IF EXISTS HIRES");
				stmt.executeUpdate("Drop TABLE IF EXISTS BILL");
				stmt.executeUpdate("Drop TABLE IF EXISTS ROOM");
				stmt.executeUpdate("Drop TABLE IF EXISTS HOTEL");
				stmt.executeUpdate("Drop TABLE IF EXISTS SERVICE");
				stmt.executeUpdate("Drop TABLE IF EXISTS STAFF");
				stmt.executeUpdate("Drop TABLE IF EXISTS CUSTOMER");

				// create tables

				stmt.executeUpdate(
						"CREATE TABLE CUSTOMER (ID INT(4) PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, DOB DATE, PHONE VARCHAR(32) NOT NULL, EMAIL VARCHAR(32), SSN VARCHAR (32) NOT NULL, ADDRESS VARCHAR(64), HASHOTELCARD TINYINT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE STAFF (ID INT(3) PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, TITLE VARCHAR(32) NOT NULL, DEPARTMENT VARCHAR(32), ADDRESS VARCHAR(64) NOT NULL, PHONE VARCHAR(32) NOT NULL, AVAILABILITY TINYINT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE SERVICE (ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, COST FLOAT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE HOTEL (ID INT(4) ZEROFILL PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(64) NOT NULL, CITY VARCHAR(64) NOT NULL, PHONE VARCHAR(32) NOT NULL, MANAGERID INT(3), CONSTRAINT FOREIGN KEY(MANAGERID) REFERENCES STAFF(ID) ON DELETE CASCADE ON UPDATE CASCADE)");
				stmt.executeUpdate(
						"CREATE TABLE ROOM (NUMBER INT(2) ZEROFILL, HOTELID INT(4) ZEROFILL, CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(NUMBER, HOTELID), CATEGORY VARCHAR(32) NOT NULL, RATE FLOAT NOT NULL, AVAILABILITY TINYINT NOT NULL, MAXOCCUPANCY INT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE BILL (ID INT PRIMARY KEY AUTO_INCREMENT, AMOUNT FLOAT NOT NULL, MODEOFPAYMENT VARCHAR(32) NULL, CARDNUMBER INT(4) NULL, DISCOUNT FLOAT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE HIRES (HOTELID INT(4) ZEROFILL, STAFFID INT(3), CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(STAFFID) REFERENCES STAFF(ID) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(HOTELID, STAFFID))");
				stmt.executeUpdate(
						"CREATE TABLE ASSIGNED (HOTELID INT(4) ZEROFILL, NUMBER INT(2) ZEROFILL, STAFFID INT(3), CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(STAFFID) REFERENCES STAFF(ID) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(HOTELID, NUMBER, STAFFID))");
				stmt.executeUpdate(
						"CREATE TABLE PROVIDES (HOTELID INT(4) ZEROFILL, NUMBER INT(2) ZEROFILL, STAFFID INT(3), SERVICEID INT, CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(STAFFID) REFERENCES STAFF(ID), CONSTRAINT FOREIGN KEY(SERVICEID) REFERENCES SERVICE(ID), PRIMARY KEY(HOTELID, NUMBER, STAFFID, SERVICEID), DATE DATE NOT NULL, TIME TIME NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE CHECKIN (CUSTOMERID INT, HOTELID INT(4) ZEROFILL, NUMBER INT(2) ZEROFILL, BILLID INT, CONSTRAINT FOREIGN KEY(CUSTOMERID) REFERENCES CUSTOMER(ID) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(BILLID) REFERENCES BILL(ID) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(CUSTOMERID, HOTELID, NUMBER, BILLID), CHECKINDATE DATE NOT NULL, CHECKINTIME TIME NOT NULL, CHECKOUTDATE DATE, CHECKOUTTIME TIME, GUESTS INT NOT NULL)");
				stmt.executeUpdate(
						"CREATE TABLE PRESIDENTIAL (NUMBER INT(2) ZEROFILL, HOTELID INT(4) ZEROFILL, CONSTRAINT FOREIGN KEY(NUMBER) REFERENCES ROOM(NUMBER) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT FOREIGN KEY(HOTELID) REFERENCES HOTEL(ID) ON DELETE CASCADE ON UPDATE CASCADE, PRIMARY KEY(NUMBER, HOTELID))");

				stmt.executeUpdate("ALTER TABLE CUSTOMER AUTO_INCREMENT=1001");
				stmt.executeUpdate("ALTER TABLE STAFF AUTO_INCREMENT=100");
				stmt.executeUpdate("ALTER TABLE HOTEL AUTO_INCREMENT=0001");
				
				//customer, staff, service, bill, hotel
				//create trigger for presidential suite
				
//				stmt.executeUpdate("CREATE TRIGGER addpresidential AFTER INSERT ON ROOM REFERENCING NEW ROW AS newRoom FOR EACH ROW WHEN(newRoom.category='Presidential') INSERT INTO PRESIDENTIAL (number, HotelID) VALUES (newRoom.number, newRoom.hotelId)");
				
				
				
				
				stmt.executeUpdate(
						"INSERT INTO CUSTOMER (NAME, DOB, PHONE, EMAIL, SSN, ADDRESS, HASHOTELCARD) VALUES ( 'David', '1980-01-30', '123', 'david@gmail.com', '593-9846', '980 TRT St , Raleigh NC', 0)");
				stmt.executeUpdate(
						"INSERT INTO CUSTOMER (NAME, DOB, PHONE, EMAIL, SSN, ADDRESS, HASHOTELCARD) VALUES ('Sarah', '1971-01-30', '465', 'sarah@gmail.com', '777-8352', '7720 MHT St , Greensboro NC', 1)");
				stmt.executeUpdate(
						"INSERT INTO CUSTOMER (NAME, DOB, PHONE, EMAIL, SSN, ADDRESS, HASHOTELCARD) VALUES ('Joseph', '1987-01-30', '789', 'joseph@gmail.com', '858-9430', '231 DRY St , Rochester NY 78', 0)");
				stmt.executeUpdate(
						"INSERT INTO CUSTOMER (NAME, DOB, PHONE, EMAIL, SSN, ADDRESS, HASHOTELCARD) VALUES ('Lucy', '1985-01-30', '213', 'lucy@gmail.com', '440-9328', '24 BST Dr , Dallas TX 14', 0)");

				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Mary', 'Manager', 'Management', '90 ABC St , Raleigh NC 27', '654', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'John', 'Manager', 'Management', '798 XYZ St , Rochester NY 54', '564', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Carol', 'Manager', 'Management', '351 MH St , Greensboro NC 27', '546', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Emma', 'Front Desk Staff', 'Management', '49 ABC St , Raleigh NC 27', '546', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Ava', 'Catering Staff', 'Catering', '425 RG St , Raleigh NC 27', '777', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Peter', 'Manager', 'Management', '475 RG St , Raleigh NC 27', '724', 1)");
				stmt.executeUpdate(
						"INSERT INTO STAFF (NAME, TITLE, DEPARTMENT, ADDRESS, PHONE, AVAILABILITY) VALUES ( 'Olivia', 'Front Desk Staff', 'Management', '325 PD St , Raleigh NC 27', '799', 1)");

				stmt.executeUpdate("INSERT INTO SERVICE (NAME, COST) VALUES ( 'Phone bills', 5)");
				stmt.executeUpdate("INSERT INTO SERVICE (NAME, COST) VALUES ('Dry Cleaning', 16)");
				stmt.executeUpdate("INSERT INTO SERVICE (NAME, COST) VALUES ('Gyms', 15)");
				stmt.executeUpdate("INSERT INTO SERVICE (NAME, COST) VALUES ('Room service', 10)");
				stmt.executeUpdate("INSERT INTO SERVICE (NAME, COST) VALUES ('Special requests', 20)");

				stmt.executeUpdate(
						"INSERT INTO HOTEL (NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES  ( 'Hotel A', '21 ABC St , Raleigh NC 27', 'Raleigh','919', 100)");
				stmt.executeUpdate(
						"INSERT INTO HOTEL (NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES  ( 'Hotel B', 'Boston25 XYZ St , Rochester NY 54', 'Rochester','718', 101)");
				stmt.executeUpdate(
						"INSERT INTO HOTEL (NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES  ('Hotel C', '29 PQR St , Greensboro NC 27', 'Greensboro', '984', 102)");
				stmt.executeUpdate(
						"INSERT INTO HOTEL (NAME, ADDRESS, CITY, PHONE, MANAGERID) VALUES  ( 'Hotel D', '28 GHW St , Raleigh NC 32', 'Raleigh', '920', 105)");

				stmt.executeUpdate("INSERT INTO ROOM VALUES (01, 0001, 'Economy', 100, 1, 1)");
				stmt.executeUpdate("INSERT INTO ROOM VALUES (02, 0001, 'Deluxe', 200, 1, 2)");
				stmt.executeUpdate("INSERT INTO ROOM VALUES (03, 0002, 'Economy', 100, 1, 1)");
				stmt.executeUpdate("INSERT INTO ROOM VALUES (02, 0003, 'Executive', 1000, 0, 3)");
				stmt.executeUpdate("INSERT INTO ROOM VALUES (01, 0004, 'Presidential', 5000, 1, 4)");
				stmt.executeUpdate("INSERT INTO ROOM VALUES (05, 0001, 'Deluxe', 200, 1, 2)");

				// card number needs to be taken for CUSTOMER

				stmt.executeUpdate("INSERT INTO BILL (AMOUNT, MODEOFPAYMENT, CARDNUMBER, DISCOUNT) VALUES (0, 'credit', 1052, 0)");
				stmt.executeUpdate("INSERT INTO BILL (AMOUNT, MODEOFPAYMENT, CARDNUMBER, DISCOUNT) VALUES (0, 'hotel credit', 3020, 5)");
				stmt.executeUpdate("INSERT INTO BILL (AMOUNT, MODEOFPAYMENT, CARDNUMBER, DISCOUNT) VALUES (0, 'credit', 2497, 0)");
				stmt.executeUpdate("INSERT INTO BILL (AMOUNT, MODEOFPAYMENT, CARDNUMBER, DISCOUNT) VALUES (0, 'cash', NULL, 0)");

				stmt.executeUpdate("INSERT INTO HIRES VALUES (0001, 100)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0002, 101)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0003, 102)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0001, 103)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0001, 104)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0004, 105)");
				stmt.executeUpdate("INSERT INTO HIRES VALUES (0004, 106)");

				// stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 201, 3)");
				// stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 202, 4)");
				// stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (3, 203, 2)");
				// stmt.executeUpdate("INSERT INTO ASSIGNED VALUES (4, 201,7)");

				stmt.executeUpdate("INSERT INTO PROVIDES VALUES (0001, 01, 103, 2, '2017-05-11', '00:00:00')");
				stmt.executeUpdate("INSERT INTO PROVIDES VALUES (0001, 01, 103, 3, '2017-05-12', '00:00:00')");
				stmt.executeUpdate("INSERT INTO PROVIDES VALUES (0001, 02, 103, 3, '2017-05-12', '00:00:00')");
				stmt.executeUpdate("INSERT INTO PROVIDES VALUES (0002, 03, 101, 4, '2016-05-13', '00:00:00')");
				stmt.executeUpdate("INSERT INTO PROVIDES VALUES (0003, 02, 102, 1, '2018-05-11', '00:00:00')");

				// check in and check out time also, not just date

				stmt.executeUpdate(
						"INSERT INTO CHECKIN VALUES (1001, 0001, 01, 1, '2017-05-10','15:17:00', '2017-05-13','10:22:00', 1)");
				stmt.executeUpdate(
						"INSERT INTO CHECKIN VALUES (1002, 0001, 02, 2, '2017-05-10', '16:11:00', '2017-05-13','09:27:00', 2)");
				stmt.executeUpdate(
						"INSERT INTO CHECKIN VALUES (1003, 0002, 03, 3, '2016-05-10', '15:45:00', '2016-05-14', '11:10:00', 1)");
				stmt.executeUpdate(
						"INSERT INTO CHECKIN VALUES (1004, 0003, 02, 4, '2018-05-10', '14:30:00', '2018-05-12', '10:00:00' ,2)");

				stmt.executeUpdate("INSERT INTO PRESIDENTIAL VALUES (01, 0004)");
				
				Bill.calcBill(1001, "2017-05-13", "credit", "1052" );
				Bill.calcBill(1002, "2017-05-13", "hotel credit","3020");
				Bill.calcBill(1003, "2016-05-14", "credit","2497");
				Bill.calcBill(1004, "2018-05-12", "cash","0");
				
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
