package src;

import java.util.Scanner;

public class Login {
	static int user = 0;

	static int getUser() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Login as:\n1. Admin\n2. Manager\n3. Front Desk Staff\n4. Service Staff\n");
			int user = sc.nextInt();
			if (user < 1 || user > 4) {
				System.out.println("Enter correct user... Exiting");
				sc.close();
				break;
			} else
				sc.close();
				return user;
		}
		
		return user;
	}

	public static String getHotelID() {
		// TODO Auto-generated method stub
		return null;
	}
}
