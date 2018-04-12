package src;

public class Room {

 /* Data -
 * hotelID, number, maxOccupancy, nightlyRate, category, availaility;
 */
	
	void createRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		// Query
	}
	void editRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		// Query
	}
	void deleteRoom(String hotelID, String number, String category, String nightlyRate, String availability, String maxOccupancy) {
		// Query
	}
	
	void checkRoomAvailability() {
		// Query
	}
	
	void checkRoomAvailability(String category) {
		// Query
	}
	
	void assignRoom(String customerId, String hotelId, String roomNumber) {
		// Query
		
		// Create entry in check-in table
		// Create a bill in bill table
	}
	
	void releaseRoom(String hotelId, String number) {
		// Query
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	void addServiceToRoom(String hotelId, String number, String serviceId) {
		// Query
		// *********************************** MAKE CHANGES IN REPORT 1, TAKE HOTEL ID AS A PARAMETER IN THE FUNCTION **************************
	}
	
	void addStaffToPresidential(String hotelId, String number) {
		// Query
		// To create an entry in the provides table, here we have a common serviceId for the service to provide to presidential suite. 
	}
	
}