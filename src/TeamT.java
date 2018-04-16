package src;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TeamT {

	static Scanner stdin = new Scanner(System.in);
	static boolean loggedIn = false;

	public static void main(String[] args) {
		DBConnection.initialize();

		while (true) {
			System.out.println("1. Populate Database with Demo Data\n2. Continue without populating");
			int options = readInt();
			switch (options) {
			case 1:
				DBDemo.initializeDB();
				break;
			case 2:
				break;
			default:
				System.out.println("Enter valid input");
			}
			break;
		}
		// DBDemo.initializeDB();
		// Customer.createCustomer(1008, "David", "1980-01-30", "123",
		// "david@gmail.com", "593-9846", "980 TRT St, Raleigh NC", (byte)0);
		while (true) {
			int user = Login.getUser();
			loggedIn = true;
			int hotelID = Login.getHotelID();
			while (loggedIn) {
				switch (selectOption()) {
				case 1:
					informationProcessing(user, hotelID);
					break;
				case 2:
					serviceRecords(user, hotelID);
					break;
				case 3:
					billingAccounts(user, hotelID);
					break;
				case 4:
					reports(user, hotelID);
					break;
				case 5:
					loggedIn = false;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Enter valid Input.");
				}
			}
		}

	}

	public static void reports(int user, int hotelID) {
		boolean previous = false;
		while (loggedIn && !previous) {
			System.out.println(
					"Make changes into:\n1. Hotel Occupancy\n2. Room Occupancy\n3. Occupancy between a Date Range\n4. City Occupancy\n5. Staff by Role\n6. Staff Serving Customer\n7. Revenue Report\n8. Previous Menu\n9. Logout");
			int option = readInt();
			ResultSet rs = null;
			switch (option) {
			case 1:
				// get hotel occupancy details
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				rs = Report.hotelOccupancy();
				outputResult(rs);
				break;
			case 2:
				// get room occupancy
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				rs = Report.roomOccupancy();
				outputResult(rs);
				break;
			case 3:
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("enter the start date:");
				String dateStart = readInput();
				System.out.print("enter the end date:");
				String dateEnd = readInput();
				rs = Report.dateRangeOccupancy(dateStart, dateEnd);
				outputResult(rs);
				break;
			case 4:
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				rs = Report.cityOccupancy();
				outputResult(rs);
				break;
			case 5:
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				rs = Report.groupStaffByRole();
				outputResult(rs);
				break;
			case 6:
				if (user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("enter the customer id for whom staff info is needed: ");
				int customerId = readInt();
				rs = Report.staffServingCustomer(customerId);
				outputResult(rs);
				break;
			case 7:
				if (user == 3 || user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("Enter the start date: ");
				String checkInTime = readInput();
				System.out.print("Enter the end date: ");
				String checkOutTime = readInput();
				rs = Report.revenueReport(hotelID, checkInTime, checkOutTime);
				outputResult(rs);
				break;
			case 8: // Previous Menu
				previous = true;
				break;
			case 9:
				loggedIn = false;
				break;
			default:
				System.out.println("enter the correct option!");
			}
		}
	}

	public static void billingAccounts(int user, int hotelID) {
		boolean previous = false;
		while (loggedIn && !previous) {
			System.out.println("1. Bill\n2. Generate Receipt\n3. Checkout customer\n4. Previous  Menu\n5. Logout");
			int option = readInt();
			int id = 0;
			ResultSet rs = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			switch (option) {
			case 1:
				if (user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("Enter Customer ID: ");
				id = readInt();
				System.out.println("Enter Mode of payment:\n1. Hotel Card\n2. Credit/Debit Card\n3. Cash");
				int op = readInt();
				String modeOfPayment = "cash";
				switch (op) {
				case 1:
					modeOfPayment = "hotel credit";
					break;
				case 2:
					modeOfPayment = "credit";
					break;
				case 3:
					modeOfPayment = "cash";
					break;
				default:

				}
				String card = "0";
				if (!modeOfPayment.equals("cash")) {
					System.out.print("Enter card number: ");
					card = readInput();
				}
				rs = Bill.calcBill(id, dateFormat.format(date), modeOfPayment, card);
				outputResult(rs);
				break;
			case 2:
				if (user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("Enter Customer ID: ");
				id = readInt();
				System.out.print("Enter Checkout Date: ");
				String date1 = readInput();
				rs = Bill.getAmount(id, date1);
				outputResult(rs);
				System.out.println("----Itemized Receipt----");
				rs = Bill.generateReceipt(id, date1);
				outputResult(rs);
				break;
			case 3:
				if (user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("Enter Customer ID: ");
				id = readInt();
				System.out.println("Enter the Room Numer: ");
				int number = readInt();
				Room.releaseRoom(hotelID, number, id);

				System.out.println("Enter Mode of payment:\n1. Hotel Card\n2. Credit/Debit Card\n3. Cash");
				op = readInt();
				modeOfPayment = "cash";
				switch (op) {
				case 1:
					modeOfPayment = "hotel credit";
					break;
				case 2:
					modeOfPayment = "credit";
					break;
				case 3:
					modeOfPayment = "cash";
					break;
				default:

				}
				card = "0";
				if (!modeOfPayment.equals("cash")) {
					System.out.print("Enter card number: ");
					card = readInput();
				}

				System.out.println("Checked Out!\n");

				rs = Bill.calcBill(id, dateFormat.format(date), modeOfPayment, card);
				outputResult(rs);

				rs = Bill.getAmount(id, dateFormat.format(date));
				outputResult(rs);
				System.out.println("----Itemized Receipt----");
				rs = Bill.generateReceipt(id, dateFormat.format(date));
				outputResult(rs);

				break;
			case 4:
				previous = true;
				break;

			case 5:
				loggedIn = false;
				break;
			default:
				System.out.println("Enter valid input.");

			}
		}
	}

	public static void serviceRecords(int user, int hotelID) {
		boolean previous = false;
		while (loggedIn && !previous) {
			System.out.println("\n1. Assign room to customer\n2. Add service to Room\n3. Previous  Menu\n4. Logout");
			int option = readInt();
			switch (option) {
			case 1:
				if (user == 4) {
					System.out.println("You are not authorised to perform this operation.");
					break;
				}
				System.out.print("Enter the customer ID: ");
				int customerId = readInt();
				System.out.print("Enter the number of guests: ");
				int noOfGuests = readInt();

				// get a list of the available
				System.out.println("The available rooms are: ");
				ResultSet rs = Room.checkRoomAvailability(hotelID);
				outputResult(rs);

				System.out.print("Enter room from the above list: ");
				int roomId = readInt();

				// assign room to the customer
				Customer.assignRoom(customerId, hotelID, roomId, noOfGuests);

				// check if the room is presidential
				rs = Room.getRoom(hotelID, roomId);
				String category = "";
				try {
					while (rs.next()) {
						category = rs.getString(3);
					}
				} catch (Exception e) {
					System.out.println(e);
				}

				if (category.equals("Presidential")) {
					rs = Staff.getAvailableStaff(hotelID);
					outputResult(rs);
					System.out.print("enter the staff to assign to room: ");
					int staffId = readInt();
					Room.addStaffToPresidential(hotelID, roomId, staffId);
				}
				System.out.println("Room assigned!\n\n");
				break;
			case 2:
				System.out.println("Enter staff id: ");
				int staffId = readInt();

				System.out.print("Enter room number: ");
				roomId = readInt();

				System.out.println("The available services are: ");
				rs = Service.getAllServices();
				outputResult(rs);

				System.out.print("Enter service id: ");
				int serviceId = readInt();

				Room.addServiceToRoom(hotelID, roomId, staffId, serviceId);

				break;
			case 3: // Previous Menu
				previous = true;
				break;
			case 4:
				loggedIn = false;
				break;
			default:
				System.out.println("enter the correct option");
			}
		}

	}

	public static void informationProcessing(int user, int hotelID) {
		boolean previous = false;
		while (loggedIn && !previous) {
			System.out.println(
					"Make changes into:\n1. Customer\n2. Staff\n3. Room\n4. Hotel\n5. Service\n6. Previous Menu\n7. Logout\n");
			int option = readInt();
			int op = 0;
			switch (option) {
			case 1: // Customer
				crud();
				System.out.println("5. Back to previous menu.");
				op = readInt();
				if (op == 1) { // Create
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					String[] params = { "Name", "DOB", "Phone Number", "Email", "SSN", "Address", "Has Hotel Card?" };
					List<String> send = create(params);
					ResultSet rs = Customer.createCustomer(send.get(0), send.get(1), send.get(2), send.get(3),
							send.get(4), send.get(5), Byte.valueOf(send.get(6)));
					outputResult(rs);
				} else if (op == 2) { // Read
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("1. All customers\n2. By customer ID");
					int query = readInt();
					if (query == 2) {
						System.out.println("Enter Customer ID");
						int id = readInt();
						ResultSet rs = Customer.getCustomer(id);
						outputResult(rs);
					} else {
						ResultSet rs = Customer.getAllCustomers();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Customer ID");
					int id = readInt();
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
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Customer ID");
					int id = readInt();
					Customer.deleteCustomer(id);
					System.out.println("Customer with ID " + id + " deleted");
				} else {

				}
				break;
			case 2: // Staff
				crud();
				op = readInt();
				if (op == 1) { // Create
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					String[] params = { "Name", "Title", "Department", "Address", "Phone", "Availability" };
					List<String> send = create(params);
					ResultSet rs = Staff.createStaff(send.get(0), send.get(1), send.get(2), send.get(3), send.get(4),
							Byte.valueOf(send.get(5)), hotelID);
					outputResult(rs);
				} else if (op == 2) { // Read
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("1. All staff\n2. By staff ID");
					int query = readInt();
					if (query == 2) {
						System.out.println("Enter Staff ID");
						int id = readInt();
						ResultSet rs = Staff.getStaff(id);
						outputResult(rs);
					} else {
						ResultSet rs = Staff.getAllStaff(hotelID);
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Staff ID");
					int id = readInt();
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
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Staff ID");
					int id = readInt();
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
				System.out.println("8. Release Room (Equivalent to checkout but no bill generated)");

				op = readInt();
				if (op == 1) { // Create
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					String[] params = { "HotelID", "Number", "Category", "Rate", "Availability", "MaxOccupancy" };
					List<String> send = create(params);
					Room.createRoom(Integer.parseInt(send.get(0)), Integer.parseInt(send.get(1)), send.get(2),
							Integer.parseInt(send.get(3)), Integer.parseInt(send.get(4)),
							Integer.parseInt(send.get(5)));
				} else if (op == 2) { // Read
					System.out.println("1. All rooms\n2. By Room Number");
					int query = readInt();
					if (query == 2) {
						System.out.println("Enter Room Number");
						int id = readInt();
						ResultSet rs = Room.getRoom(hotelID, id);
						outputResult(rs);
					} else {
						ResultSet rs = Room.getAllRooms(hotelID);
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Room Number");
					int id = readInt();
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
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Room Number");
					int id = readInt();
					Room.deleteRoom(hotelID, id);
					System.out.println("Room number " + id + " of hotel with ID " + hotelID + " deleted");
				} else if (op == 5) {
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					ResultSet rs = Room.checkRoomAvailability(hotelID);
					outputResult(rs);
				} else if (op == 6) {
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter the room category:");
					String category = readInput();
					ResultSet rs = Room.checkRoomAvailability(hotelID, category);
					outputResult(rs);
				} else if (op == 7) {
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.print("Enter the customer ID: ");
					int customerId = readInt();
					System.out.print("Enter the number of guests: ");
					int noOfGuests = readInt();

					// get a list of the available
					System.out.println("The available rooms are: ");
					ResultSet rs = Room.checkRoomAvailability(hotelID);
					outputResult(rs);

					System.out.print("Enter room from the above list: ");
					int roomId = readInt();

					// assign room to the customer
					Customer.assignRoom(customerId, hotelID, roomId, noOfGuests);

					// check if the room is presidential
					rs = Room.getRoom(hotelID, roomId);
					String category = "";
					try {
						while (rs.next()) {
							category = rs.getString(3);
						}
					} catch (Exception e) {
						System.out.println(e);
					}

					if (category.equals("Presidential")) {
						rs = Staff.getAvailableStaff(hotelID);
						outputResult(rs);
						System.out.print("enter the staff to assign to room: ");
						int staffId = readInt();
						Room.addStaffToPresidential(hotelID, roomId, staffId);
					}
					System.out.println("Room assigned!\n\n");
					break;
				} else if (op == 8) {
					if (user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Customer ID: ");
					int custId = readInt();
					System.out.println("Enter the Room Numer: ");
					int number = readInt();
					Room.releaseRoom(hotelID, number, custId);
					System.out.println("Room released!\n");
				} else {
				}
				break;
			case 4: // Hotel
				crud();
				op = readInt();
				if (op == 1) { // Create
					if (user == 2 || user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					String[] params = { "Name", "Address", "City", "Phone Number", "ManagerID" };
					List<String> send = create(params);
					ResultSet rs = Hotel.createHotel(send.get(0), send.get(1), send.get(2), send.get(3),
							Integer.parseInt(send.get(4)));
					outputResult(rs);
				} else if (op == 2) { // Read
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("1. All hotels\n2. By Hotel ID");
					int query = readInt();
					if (query == 2) {
						ResultSet rs = Hotel.getHotel(hotelID);
						outputResult(rs);
					} else {
						ResultSet rs = Hotel.getAllHotels();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					if (user == 2 || user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					ResultSet rs = Hotel.getHotel(hotelID);
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
					if (user == 2 || user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					Hotel.deleteHotel(hotelID);
					System.out.println("Hotel with ID " + hotelID + " deleted");
				} else {

				}
				break;
			case 5: // Service
				crud();
				op = readInt();
				if (op == 1) { // Create
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					String[] params = { "Name", "Cost" };
					List<String> send = create(params);
					ResultSet rs = Service.createService(send.get(0), Integer.parseInt(send.get(1)));
					outputResult(rs);
				} else if (op == 2) { // Read
					System.out.println("1. All services\n2. By service ID");
					int query = readInt();
					if (query == 2) {
						System.out.println("Enter Service ID");
						int id = readInt();
						ResultSet rs = Service.getService(id);
						outputResult(rs);
					} else {
						ResultSet rs = Service.getAllServices();
						outputResult(rs);
					}
				} else if (op == 3) { // Update
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Service ID");
					int id = readInt();
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
					if (user == 3 || user == 4) {
						System.out.println("You are not authorised to perform this operation.");
						break;
					}
					System.out.println("Enter Service ID");
					int id = readInt();
					Service.deleteService(id);
					System.out.println("Service with ID " + id + " deleted");
				} else {

				}
				break;
			case 6: // Previous Menu
				previous = true;
				break;
			case 7: // Logout
				loggedIn = false;
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
			int change = readInt();
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
		System.out.println("Enter operation:\n1. Create\n2. Read\n3. Update\n4. Delete");
		return;
	}

	public static int selectOption() {
		int option = 0;
		System.out.println("\n\n******************************************************************\n\n");
		// Enter user choice
		System.out.println("Select Operation:");
		System.out.println("1. Information Processing \n" + "2. Maintaining Service Records \n"
				+ "3. Maintaining Billing Accounts \n" + "4. Reports \n" + "5. Logout");
		option = readInt();
		return option;
	}

	public static String readInput() {
		String s = "";
		s = stdin.nextLine();
		return s;
	}

	public static int readInt() {
		int s = 0;
		s = stdin.nextInt();
		stdin.nextLine();
		return s;
	}

	public static void outputResult(ResultSet rs) {
		try {
			if (rs == null) {
				System.out.println("Sorry, something went wrong while fetching the data.");
				return;
			}
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
