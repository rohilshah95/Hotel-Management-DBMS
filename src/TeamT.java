package src;

import java.util.Scanner;

public class TeamT {

	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		DBConnection.initialize();
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
				System.out.println("\n ENTER CORRE" + "CT CHOICE! \n");
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
		
		
	}

	public static int selectOption() {
		int user = 0;
		System.out.println("\n\n******************************************************************\n\n");
		// Enter user choice
		System.out.println("Which user would you like to login as?");
		System.out.println("1. Information Processing \n" + "2. Maintaining Service Records \n" + "3. Maintaining Billing Accounts \n" + "4. Reports \n"
						+ "5. Exit");
		System.out.println("Enter the number of your choice:");
		user = Integer.parseInt(readInput());
		return user;
	}

	public static String readInput() {
		String s = "";
		s = stdin.nextLine();
		return s;
	}
}
