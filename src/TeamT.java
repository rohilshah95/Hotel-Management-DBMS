package src;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TeamT {

	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		DBConnection.initialize();
//		Customer.createCustomer(1008, "David", "1980-01-30", "123", "david@gmail.com", "593-9846", "980 TRT St, Raleigh NC", (byte)0);
		while (true) {
			int user = Login.getUser();
			switch (selectOption()) {
			case 1:
				informationProcessing(user);
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

	}

	public static void billingAccounts(int user) {
		// TODO Auto-generated method stub

	}

	public static void serviceRecords(int user) {
		// TODO Auto-generated method stub

	}

	public static void informationProcessing(int user) {
		while (true) {
			System.out.println(
					"Make changes into:\n1. Customer\n2. Staff\n3. Room\n4. Hotel\n5. Service\n6. Bill\n7. Logout\n");
			int option = Integer.parseInt(readInput());
			int op = 0;
			switch (option) {
			case 1: // Customer
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 2: // Staff
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 3: // Room
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 4: // Hotel
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 5: // Service
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 6: // Bill
				crud();
				op = Integer.parseInt(readInput());
				if(op == 1){ //Create
					String [] params = {"ID", "Name", "DOB", "Phone Number", "Email","SSN", "Address", "Has Hotel Card?"};
					List<String> send = create(params);
					Customer.createCustomer(Integer.parseInt(send.get(0)),send.get(1), send.get(2), send.get(3), send.get(4), send.get(5), send.get(6),Byte.valueOf(send.get(7)));
				}else if(op == 2){ //Read
					
				}else if(op == 3){ //Update
					
				}else if(op == 4){ //Delete
					
				}else{
					
				}
				break;
			case 7: // Logout

				break;
			default:
				System.out.println("\n ENTER CORRECT CHOICE! \n");
			}
		}
	}

	private static List<String> create(String[] params) {
		System.out.println("Enter the following details:");
		List<String> send = new LinkedList<String>();
		for(String i : params){
			System.out.print(i+": ");
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
		   System.out.println("querying SELECT * FROM XXX");
		   int columnsNumber = rsmd.getColumnCount();
		   while (rs.next()) {
		       for (int i = 1; i <= columnsNumber; i++) {
		           if (i > 1) System.out.print(",  ");
		           String columnValue = rs.getString(i);
		           System.out.print(columnValue + " " + rsmd.getColumnName(i));
		       }
		       System.out.println("");
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
