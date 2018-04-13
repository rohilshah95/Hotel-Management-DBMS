package src;

import java.util.Scanner;

public class TeamT {

	static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		DBConnection.initialize();
		while (true) {
			switch (selectOption()) {
			case 1:
				informationProcessing();
				break;
			case 2:
				serviceRecords();
				break;
			case 3:
				billingAccounts();
				break;
			case 4:
				reports();
				break;
			case 5:
				System.out.println("Exiting!");
				System.exit(0);
				break;
			default:
				System.out.println("\n ENTER CORRE" + "CT CHOICE! \n");
			}
		}
		
//		while (true) {
//			switch (selectUser()) {
//			case 1:
//				admin();
//				break;
//			case 2:
//				manager();
//				break;
//			case 3:
//				frontDesk();
//				break;
//			case 4:
//				serviceStaff();
//				break;
//			case 5:
//				System.out.println("Exiting!");
//				System.exit(0);
//				break;
//			default:
//				System.out.println("\n ENTER CORRE" + "CT CHOICE! \n");
//			}
//		}
	}
	
	public static void reports() {
		// TODO Auto-generated method stub
		
	}

	public static void billingAccounts() {
		// TODO Auto-generated method stub
		
	}

	public static void serviceRecords() {
		// TODO Auto-generated method stub
		
	}

	public static void informationProcessing() {
		// TODO Auto-generated method stub
		
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

	public static int selectUser() {
		int user = 0;
		System.out.println("\n\n******************************************************************\n\n");
		// Enter user choice
		System.out.println("Which user would you like to login as?");
		System.out.println("1. Admin \n" + "2. Hotel Manager \n" + "3. Front Desk Representative \n" + "4. Service Staff \n"
						+ "5. Exit");
		System.out.println("Enter the number of your choice:");
		user = Integer.parseInt(readInput());
		return user;
	}

	public static void admin() {
		System.out.println("Admin");
	}

	public static void manager() {
		System.out.println("Manager");
	}

	public static void frontDesk() {
		System.out.println("Front Desk Representative");
	}

	public static void serviceStaff() {
		System.out.println("Service Staff");
	}

	public static String readInput() {
		String s = "";
		s = stdin.nextLine();
		return s;
	}
}
