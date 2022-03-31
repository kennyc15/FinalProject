/*import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateStopTimes {
	
	
	public static ArrayList<StopTimes> createStopTimes(String filename){
		
		ArrayList<StopTimes> stopTimesDetails = new ArrayList<StopTimes>();
		
		try {
			FileReader fr = new FileReader(filename);
			Scanner scan = new Scanner(fr);
			
			
		while (scan.hasNextLine()) {
			
		String[] line = scan.nextLine().split(",");
		
			
		int tripid = Integer.parseInt(line[0]);

		//validate time to correct format hh:mm:ss
		String arrival_time = "-1";
		if (validTime(line[1])) {
		arrival_time = line[1];
		}
		
		//validate time to correct format hh:mm:ss
		String departure_time = "-1";
		if (validTime(line[2])) {
		departure_time = line[2];
		}
		
		int stop_id = Integer.parseInt(line[3]);
		String stop_sequence= line[4];
		String stopHead = line[5];
		String pickUp = line[6];
		String dropOff = line[7];
		String shape_dist_traveled  = line[line.length - 1];
		
		StopTimes st = new StopTimes(tripid, arrival_time, departure_time, stop_id, stop_sequence,
				stopHead, pickUp, dropOff, shape_dist_traveled);
		stopTimesDetails.add(st);
		
	}
		scan.close();
		return stopTimesDetails;
		
	} catch (Exception e) {
		System.out.println(e);
		return stopTimesDetails;
	}
	}

	public static void main(String args[]) throws FileNotFoundException {
		
		//System.out.println(getArrivalTime(createStopTimes("stop_times.txt").get(0)));
	
	}
	
	 public static boolean validTime(String time) {
		 
		 String format = "((\\s?)[0-9]|[01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		 Pattern p = Pattern.compile(format);
		 if (time == null) {
	            return false;
	   }
		 Matcher m1 = p.matcher(time);
	
	        return m1.matches();	
	}
	 
	 //Find matching Stop Times objects for given arrival time
	 public static ArrayList<StopTimes> searchByArrivalTime(String input){
		 
		 ArrayList<StopTimes> st = new ArrayList<StopTimes>();
		 ArrayList<StopTimes> allSt = createStopTimes("stop_times.txt");
		 
		 String arrTime = input;
		 
		 for(int i = 0; i < allSt.size();i++) {
			 
			 String arrivalTime = getArrivalTime(allSt.get(i));
			
			// System.out.println(arrivalTime);
			 //System.out.println(input);
			 if (arrivalTime == arrTime) {
				 st.add(allSt.get(i));
				 //System.out.println(allSt.get(i).toString());
				
			 }
		 }
		 
		return st;
	 }
	 
	// public static ArrayList<StopTimes> sortByStopId(ArrayList<StopTimes> st){
		 
		 
	 //}
	 
}*/


