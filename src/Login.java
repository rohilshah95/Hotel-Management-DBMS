package src;

public class Login {
	static int user = 0;

	static int getUser() {
		while (true) {
			System.out.println("Login as:\n1. Admin\n2. Manager\n3. Front Desk Staff\n4. Service Staff\n");
			int user = Integer.parseInt(TeamT.readInput());
			if (user < 1 || user > 4) {
				System.out.println("Enter correct user");
			} else {
				return user;
			}
		}
	}

	public static int getHotelID() {
		System.out.print("Enter HotelID: ");
		int hotelID = Integer.parseInt(TeamT.readInput());
		return hotelID;
	}
}
