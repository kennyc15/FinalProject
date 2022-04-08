import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interface {

	public Interface() {
	
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Thank you for choosing Vancouver Buses.");
		boolean quit = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select an option below to plan your journey.");
		System.out.println("- If you would like to search by arrival time, enter '1': ");
		System.out.println("- If you would like to search by stop name, enter '2': ");
		System.out.println("- If you would like to calculate the mimimum cost of a journey,"
				+ " enter '3': ");
		System.out.println("- If you would like to exit the program, enter '4': ");
		while(!quit) {
			if (scan.hasNext()) {	
				String input = scan.nextLine();
				if(input.equalsIgnoreCase("1")){
					StopTimes.main(args);
				}
				else if (input.equalsIgnoreCase("2")) {
					BusStops.main(args);
				}
				else if (input.equalsIgnoreCase("3")) {
					ShortestPath.main(args);
				}
				else if (input.equalsIgnoreCase("4")){
					quit = true;
					System.out.println("Thank you for travelling with us,");
					System.out.println("Have a safe trip!");	
				}
				else {
					System.out.println("Please select an option by entering a number (1-4).");
				}
			}
			else {
				quit = true;
				break;
			}
				
		}
		scan.close();
	}

}

