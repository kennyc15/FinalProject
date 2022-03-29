import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateStopTimes {
	
	public static ArrayList<StopTimes> createStopTimes(String filename){
		
		ArrayList<StopTimes> stopTimesDetails = new ArrayList<StopTimes>();
		
		try {
			FileReader fr = new FileReader(filename);
			Scanner scan = new Scanner(fr);
			
			
		while (scan.hasNextLine()) {
			
		String[] line = scan.nextLine().split(",");
			
		int tripid = Integer.parseInt(line[0]);
		String arrival_time = line[1];
		String departure_time = line[2];
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
	
	public static int getTripId(StopTimes st) {
		
		return st.trip_id;
		
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		
		System.out.println(createStopTimes("stop_times.txt").get(3));
	
	}
	
	
	}


