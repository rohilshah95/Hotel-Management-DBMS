package src;

public class Login {
	static int user = 0;

	/*
	 * This function is responsible for taking the login details from the user so that we can grant permissions through java.
	 * Input Parameters: N/A
	 * Return Value: ID of User to be used internally in the application
	 */
	public static int getUser() {
		while (true) {
			System.out.println("Login as:\n1. Admin\n2. Manager\n3. Front Desk Staff\n4. Service Staff\n5. Exit");
			int user = Integer.parseInt(TeamT.readInput());
			if (user < 1 || user > 5) {
				System.out.println("Enter valid input");
			} else if (user == 5) {
				System.out.println("Thanks for using WolfIns - Team T");
				System.exit(0);
			} else {
				return user;
			}
		}
	}
	
	/*
	 * This function is responsible for taking the Hotel ID from the user to be used all over the program.
	 * Input Parameters: N/A
	 * Return Value: Hotel ID
	 */
	public static int getHotelID() {
		System.out.print("Enter HotelID: ");
		int hotelID = TeamT.readInt();
		return hotelID;
	}
}
