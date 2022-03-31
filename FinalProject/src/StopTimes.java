import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StopTimes {

	public int trip_id;
	public String arrival_time;
	public String departure_time;
	public int stop_id;
	public String stop_sequence;
	public String stop_headsign;
	public String pickup_type;
	public String drop_off_type;
	public String shape_dist_traveled;
	
	StopTimes(int tripId, String arrivalTime, String departureTime, int stopId, String stopSeq, 
			String stopHead, String pickup, String dropOff, String shapeDist ) {
		
		this.trip_id = tripId;
		this.arrival_time = arrivalTime;
		this.departure_time = departureTime;
		this.stop_id = stopId;
		this.stop_sequence = stopSeq;
		this.stop_headsign = stopHead;
		this.pickup_type = pickup;
		this.drop_off_type = dropOff;
		this.shape_dist_traveled = shapeDist;
	}
	
	
	public static String getStopHead(StopTimes st) {
		
		return st.stop_headsign;
		
	}
	
	public static String getStopSequence(StopTimes st) {
		
		return st.stop_sequence;
		
	}
	
	public static int getTripId(StopTimes st) {
		
		return st.trip_id;
		
	}
	
	public static String getArrivalTime(StopTimes st) {
		
		return st.arrival_time;
		
	}
	
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
	 

	 

}

