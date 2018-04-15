package src;

public class Login {
	static int user = 0;

	static int getUser() {
		while (true) {
			System.out.println("Login as:\n1. Admin\n2. Manager\n3. Front Desk Staff\n4. Service Staff\n5. Exit");
			int user = Integer.parseInt(TeamT.readInput());
			if (user < 1 || user > 5) {
				System.out.println("Enter valid input");
			} else if (user == 5) {
				System.exit(0);
			} else {
				return user;
			}
		}
	}

	public static int getHotelID() {
		System.out.print("Enter HotelID: ");
		int hotelID = TeamT.readInt();
		return hotelID;
	}
}
