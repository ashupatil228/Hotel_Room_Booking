/**
 * 
 */

/**
 * @author IcyMe
 *
 */
import java.util.*;

public class Reservations {

	/**
	 * @param args
	 */
	static List<Integer> hotel_size = new ArrayList<>();
	static public boolean status = false;
	// In the map variable first integer value for room numbers and second map for storing booking id and days.
	static public Map<Integer,Map<Integer,List<Integer>>> room_bookings = new HashMap<Integer, Map<Integer,List<Integer>>>();
	static public int bookingId= 0;
	
	//Function to set the size of the hotel
	public static void setHotelSize(int size) {
		for(int count = 0;count< size; count++) {
			hotel_size.add(count + 1);
		}
	}
	
	//function to book room which accepts start and end day
	public static int room_booking(int start, int end){

		int selected_room = -1;
		// booking_days list is to store all the days between start and end day
		List<Integer> booking_days = new ArrayList<>();
		for(int i=start;i<=end; i++) {
			booking_days.add(i);
		}

		for(int roomNo : hotel_size) {

			if(room_bookings.keySet().contains(roomNo)) {
				for (Map.Entry<Integer, Map<Integer,List<Integer>>> entry : room_bookings.entrySet()) {

					List<Integer> booked_days_for_a_room = new ArrayList<Integer>();
					if(roomNo == entry.getKey()) {
						entry.getValue().forEach((bookingId, dates)->{
							booked_days_for_a_room.addAll(dates);
						});
						List<Integer> check_elements = new ArrayList<Integer>();
						for(int element : booking_days) {
							if(booked_days_for_a_room.contains(element)) {
								status = false;
								break;
							}
							else {
								check_elements.add(element);
								selected_room = roomNo;
								status = true;
							}
						}
						if(status) {
							selected_room = roomNo;
							return selected_room;
						}
					}
				}
			}
			else {
				selected_room = roomNo;
				status = true;
				return selected_room;
			}
		}
		return selected_room;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int input;
		System.out.println("Welcome to hotel Booking");
		System.out.println("please enter size of hotel");
		Scanner sObj = new Scanner(System.in);
		int size = sObj.nextInt();
		if(size> 1000 ||size<0) {
			System.out.println("please enter correct size of hotel");
			return;
		}
		else {
			setHotelSize(size);
			do{
				System.out.println("please enter start and end day");
				int start = sObj.nextInt();
				int end = sObj.nextInt();

				if(start<0 || start>365) {
					System.out.println("Invalid Start Date");
					return;
				}
				if(end<start || end>365) {
					System.out.println("Invalid end Date");
					return;
				}

				int room_number = room_booking(start,end);

				if(room_number >0 && room_number <= size) {
					List<Integer> booking_days = new ArrayList<>();
					Map<Integer,List<Integer>> booking_details = new HashMap<Integer,List<Integer>>();
					for(int i=start;i<=end; i++) {
						booking_days.add(i);
					}
					booking_details.put(bookingId++,booking_days);
					room_bookings.put(room_number,booking_details);
					System.out.println("Accept");
				}
				else {
					System.out.println("Decline");
				}

				System.out.println("Press 1 to continue to check room availablity or 0 to exit");
				input = sObj.nextInt();
			}while(input==1); 
		}
	}
}
