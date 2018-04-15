package src;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TeamT {

	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		DBConnection.initialize();
//		 DBDemo.initializeDB();
		// Customer.createCustomer(1008, "David", "1980-01-30", "123",
		// "david@gmail.com", "593-9846", "980 TRT St, Raleigh NC", (byte)0);
		while (true) {
			int user = Login.getUser();
			int hotelID = Login.getHotelID();
			switch (selectOption()) {
			case 1:
				informationProcessing(user, hotelID);
				break;
			case 2:
				serviceRecords(user);
				break;
			case 3:
				billingAccounts(user);
				break;
			case 4:
				reports(user);
				break;
			case 5:
				System.out.println("Exiting!");
				System.exit(0);
				break;
			default:
				System.out.println("\n ENTER CORRECT CHOICE! \n");
			}
		}
	}

	public static void reports(int user) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Make changes into:\n1. hotelOccupancy\n2. roomOccupancy\n3. date range occupancy\n4. city occupancy\n5. group staff by role\n6. staff serving customer\n7. Revenue Report\n");
			int option = Integer.parseInt(readInput());
			switch (option) {
				case 1:
				{
					// get hotel occupancy details
					ResultSet rs = Report.hotelOccupancy();
					outputResult(rs);
					break;
				}
				case 2:
				{
					//get room occupancy
					ResultSet rs = Report.roomOccupancy();
					outputResult(rs);
					break;
				}
				case 3:
				{
					System.out.print("enter the start date:\t");
					String dateStart = sc.next();
					System.out.print("enter the end date:\t");
					String dateEnd = sc.next();
					ResultSet rs = Report.dateRangeOccupancy(dateStart,dateEnd);
					outputResult(rs);
					break;
				}
				case 4:
				{
					ResultSet rs = Report.cityOccupancy();
					outputResult(rs);
					break;
				}
				case 5:
				{
					ResultSet rs = Report.groupStaffByRole();
					outputResult(rs);
					break;
				}
				case 6:
				{
					System.out.print("enter the customer id for whom staff info is needed: ");
					int customerId = sc.nextInt();
					ResultSet rs = Report.staffServingCustomer(customerId);
					outputResult(rs);
					break;
				}
				case 7:
				{
					int hotelId = Login.getHotelID();
					System.out.print("Enter the checkin time: ");
					String checkInTime = sc.next();
					System.out.print("Enter the checkout time: ");
					String checkOutTime = sc.next();
					
					ResultSet rs = Report.revenueReport(hotelId, checkInTime, checkOutTime);
					outputResult(rs);
					break;
				}
				default:
				{
					System.out.println("enter the correct option!");
				}
			}
		}
	}

	public static void billingAccounts(int user) {
		// TODO Auto-generated method stub

	}

	public static void serviceRecords(int user) {
		// TODO Auto-generated method stub

	}

	public static void informationProcessing(int user, int hotelID) {
		while (true) {
			System.out.println(
					"Make changes into:\n1. Customer\n2. Staff\n3. Room\n4. Hotel\n5. Service\n6. Logout\n");
			int option = Integer.parseInt(readInput());
			int op = 0;
			switch (option) {
			case 1: // Customer
				crud();
				op = Integer.parseInt(readInput());
				if (op == 1) { // Create
					String[] params = { "Name", "DOB", "Phone Number", "Email", "SSN", "Address",
							"Has Hotel Card?" };
					List<String> send = create(params);
					Customer.createCustomer(send.get(0), send.get(1), send.get(2),
							send.get(3), send.get(4), send.get(5), Byte.valueOf(send.get(6)));
				} else if (op == 2) { // Read
					System.out.println("1. All customers\n2. By customer ID");
					int query = Integer.parseInt(readInput());
					if (query == 2) {
						System.out.println("Enter Customer ID");
						int id = Integer.parseInt(readInput());
						ResultSet rs = Customer.getCustomer(id);
						outputResult(rs);
					} else {
						ResultSet rs = Customer.getAllCustomers();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					System.out.println("Enter Customer ID");
					int id = Integer.parseInt(readInput());
					ResultSet rs = Customer.getCustomer(id);
					List<String> check = new LinkedList<String>();
					try {
						while (rs.next()) {
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								check.add(rs.getString(i));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String[] params = { "ID", "Name", "DOB", "Phone Number", "Email", "SSN", "Address",
							"Has Hotel Card?" };
					List<String> send = update(params, check);
					Customer.updateCustomer(Integer.parseInt(send.get(0)), send.get(1), send.get(2), send.get(3),
							send.get(4), send.get(5), send.get(6), Byte.valueOf(send.get(7)));
				} else if (op == 4) { // Delete
					System.out.println("Enter Customer ID");
					int id = Integer.parseInt(readInput());
					Customer.deleteCustomer(id);
					System.out.println("Customer with ID " + id + " deleted");
				} else {

				}
				break;
			case 2: // Staff
				crud();
				op = Integer.parseInt(readInput());
				if (op == 1) { // Create
					String[] params = { "Name", "Title", "Department", "Address", "Phone", "Availability" };
					List<String> send = create(params);
					Staff.createStaff(send.get(0), send.get(1), send.get(2), send.get(3),
							send.get(4), Byte.valueOf(send.get(5)));
				} else if (op == 2) { // Read
					System.out.println("1. All staff\n2. By staff ID");
					int query = Integer.parseInt(readInput());
					if (query == 2) {
						System.out.println("Enter Staff ID");
						int id = Integer.parseInt(readInput());
						ResultSet rs = Staff.getStaff(id);
						outputResult(rs);
					} else {
						ResultSet rs = Staff.getAllStaff(hotelID);
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					System.out.println("Enter Staff ID");
					int id = Integer.parseInt(readInput());
					ResultSet rs = Staff.getStaff(id);
					List<String> check = new LinkedList<String>();
					try {
						while (rs.next()) {
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								check.add(rs.getString(i));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String[] params = { "ID", "Name", "Title", "Department", "Address", "Phone", "Availability" };
					List<String> send = update(params, check);
					Staff.updateStaff(Integer.parseInt(send.get(0)), send.get(1), send.get(2), send.get(3), send.get(4),
							send.get(5), Byte.valueOf(send.get(6)));

				} else if (op == 4) { // Delete
					System.out.println("Enter Staff ID");
					int id = Integer.parseInt(readInput());
					Staff.deleteStaff(id);
					System.out.println("Staff with ID " + id + " deleted");
				} else {

				}
				break;
			case 3: // Room
				crud();
				System.out.println("5. Check rooms available in the hotel");
				System.out.println("6. Check rooms available of a category in the hotel");
				System.out.println("7. Assign room to customer");
				op = Integer.parseInt(readInput());
				if (op == 1) { // Create
					String[] params = { "HotelID", "Number", "Category", "Rate", "Availability", "MaxOccupancy" };
					List<String> send = create(params);
					Room.createRoom(Integer.parseInt(send.get(0)), Integer.parseInt(send.get(1)), send.get(2),
							Integer.parseInt(send.get(3)), Integer.parseInt(send.get(4)),
							Integer.parseInt(send.get(5)));
				} else if (op == 2) { // Read
					System.out.println("1. All rooms\n2. By Room Number");
					int query = Integer.parseInt(readInput());
					if (query == 2) {
						System.out.println("Enter Room Number");
						int id = Integer.parseInt(readInput());
						ResultSet rs = Room.getRoom(hotelID, id);
						outputResult(rs);
					} else {
						ResultSet rs = Room.getAllRooms(hotelID);
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					System.out.println("Enter Room Number");
					int id = Integer.parseInt(readInput());
					ResultSet rs = Room.getRoom(hotelID, id);
					List<String> check = new LinkedList<String>();
					try {
						while (rs.next()) {
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								check.add(rs.getString(i));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String[] params = { "HotelID", "Number", "Category", "Rate", "Availability", "MaxOccupancy" };
					List<String> send = update(params, check);
					Room.updateRoom(Integer.parseInt(send.get(0)), Integer.parseInt(send.get(1)), send.get(2),
							Integer.parseInt(send.get(3)), Integer.parseInt(send.get(4)),
							Integer.parseInt(send.get(5)));
				} else if (op == 4) { // Delete
					System.out.println("Enter Room Number");
					int id = Integer.parseInt(readInput());
					Room.deleteRoom(hotelID, id);
					System.out.println("Room number " + id + " of hotel with ID " + hotelID + " deleted");
				} else if(op == 5 ) {
					ResultSet rs = Room.checkRoomAvailability(hotelID);
					outputResult(rs);
				} else if(op == 6 ) {
					System.out.println("Enter the room category:");
					String category = readInput();
					ResultSet rs = Room.checkRoomAvailability(hotelID, category);
					outputResult(rs);
				} else if(op == 7 ) {
					System.out.println("Enter Customer's ID: ");
					int customerId = Integer.parseInt(readInput());
					System.out.println("Enter the number of guests: ");
					int noOfGuests = Integer.parseInt(readInput());
					System.out.println("Enter the roomId");
					int roomId = Integer.parseInt(readInput());
					Customer.assignRoom(customerId, hotelID, roomId, noOfGuests);
					System.out.println("Room has been assigned!");
				} else if(op == 8){
					System.out.println("Enter Customer ID: ");
					int custId = Integer.parseInt(readInput());
					System.out.println("Enter the Room Numer: ");
					int number = Integer.parseInt(readInput());
					Room.releaseRoom(hotelID, number, custId);
				} else {	
				}
				break;
			case 4: // Hotel
				crud();
				op = Integer.parseInt(readInput());
				if (op == 1) { // Create
					String[] params = { "Name", "Address", "City", "Phone Number", "ManagerID" };
					List<String> send = create(params);
					Hotel.createHotel(send.get(0), send.get(1), send.get(2), send.get(3),
							Integer.parseInt(send.get(4)));
				} else if (op == 2) { // Read
					System.out.println("1. All hotels\n2. By Hotel ID");
					int query = Integer.parseInt(readInput());
					if (query == 2) {
						System.out.println("Enter Hotel ID");
						int id = Integer.parseInt(readInput());
						ResultSet rs = Hotel.getHotel(id);
						outputResult(rs);
					} else {
						ResultSet rs = Hotel.getAllHotels();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					System.out.println("Enter Hotel ID");
					int id = Integer.parseInt(readInput());
					ResultSet rs = Hotel.getHotel(id);
					List<String> check = new LinkedList<String>();
					try {
						while (rs.next()) {
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								check.add(rs.getString(i));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String[] params = { "ID", "Name", "Address", "City", "Phone Number", "ManagerID" };
					List<String> send = update(params, check);
					Hotel.updateHotel(Integer.parseInt(send.get(0)), send.get(1), send.get(2), send.get(3), send.get(4),
							Integer.parseInt(send.get(5)));
				} else if (op == 4) { // Delete
					System.out.println("Enter Hotel ID");
					int id = Integer.parseInt(readInput());
					Hotel.deleteHotel(id);
					System.out.println("Hotel with ID " + id + " deleted");
				} else {

				}
				break;
			case 5: // Service
				crud();
				op = Integer.parseInt(readInput());
				if (op == 1) { // Create
					String[] params = { "Name", "Cost" };
					List<String> send = create(params);
					Service.createService(send.get(0), Integer.parseInt(send.get(1)));
				} else if (op == 2) { // Read
					System.out.println("1. All services\n2. By service ID");
					int query = Integer.parseInt(readInput());
					if (query == 2) {
						System.out.println("Enter Service ID");
						int id = Integer.parseInt(readInput());
						ResultSet rs = Service.getService(id);
						outputResult(rs);
					} else {
						ResultSet rs = Service.getAllServices();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					System.out.println("Enter Service ID");
					int id = Integer.parseInt(readInput());
					ResultSet rs = Service.getService(id);
					List<String> check = new LinkedList<String>();
					try {
						while (rs.next()) {
							for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
								check.add(rs.getString(i));
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					String[] params = { "Service ID", "Name", "Cost" };
					List<String> send = update(params, check);
					Service.updateService(Integer.parseInt(send.get(0)), send.get(1), Integer.parseInt(send.get(2)));
				} else if (op == 4) { // Delete
					System.out.println("Enter Service ID");
					int id = Integer.parseInt(readInput());
					Service.deleteService(id);
					System.out.println("Service with ID " + id + " deleted");
				} else {

				}
				break;
			case 6: // Logout
				break;
			default:
				System.out.println("\n ENTER CORRECT CHOICE! \n");
			}
		}
	}

	private static List<String> update(String[] params, List<String> check) {
		System.out.println("Enter fields to update: (Enter any other value to execute update)");
		for (int i = 1; i < params.length; i++) {
			System.out.print(i + ". " + params[i] + "\n");
		}
		while (true) {
			int change = Integer.parseInt(readInput());
			if (change >= 1 && change < params.length) {
				System.out.println(params[change] + ": ");
				check.set(change, readInput());
				System.out.println(params[change] + " changed. Enter another attribute to change, press 0 to exit.");
			} else {
				break;
			}
		}
		return check;
	}

	private static List<String> create(String[] params) {
		System.out.println("Enter the following details:");
		List<String> send = new LinkedList<String>();
		for (String i : params) {
			System.out.print(i + ": ");
			String str = readInput();
			send.add(str);
		}
		return send;
	}

	private static void crud() {
		System.out.println("Enter operation:\n1. Create\n2. Read\n3. Update\n4. Delete\n");
		return;
	}

	public static int selectOption() {
		int user = 0;
		System.out.println("\n\n******************************************************************\n\n");
		// Enter user choice
		System.out.println("Select Operation:");
		System.out.println("1. Information Processing \n" + "2. Maintaining Service Records \n"
				+ "3. Maintaining Billing Accounts \n" + "4. Reports \n" + "5. Exit");
		System.out.println("Enter the number of your choice:");
		user = Integer.parseInt(readInput());
		return user;
	}

	public static String readInput() {
		String s = "";
		s = stdin.nextLine();
		return s;
	}

	public static void outputResult(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			// System.out.println("querying SELECT * FROM XXX");
			int columnsNumber = rsmd.getColumnCount();
			System.out.println(
					"\n**************************************************************************************************");
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1)
					System.out.print(",  ");
				System.out.print(rsmd.getColumnName(i));
			}
			System.out.println(
					"\n**************************************************************************************************");
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue);
				}
				System.out.println();
			}
			System.out.println(
					"**************************************************************************************************\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
