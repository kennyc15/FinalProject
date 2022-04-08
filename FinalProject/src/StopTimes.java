import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
	
	
	public StopTimes() {
		// TODO Auto-generated constructor stub
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
		
		Scanner input = new Scanner(System.in);
		boolean end = false;
		while(!end) {
		System.out.println("Enter desired arrival time (hh:mm:ss): ");
		if (input.hasNext()) {
			String inputTime = input.next();
			if (validTime(inputTime)) {
				printStopTimes(searchByArrivalTime(inputTime));
				System.out.println("Would you like to enter a new time? ");
				if (input.next().equalsIgnoreCase("yes")) {
					end = false;
				}
				else {
					end = true;
					System.out.println("Search has been terminated.");
					Interface.main(args);
				}
			}
			else {
				System.out.println("Please enter a valid time to continue.");
			}
		}
		}
	
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
	 
	 //method to find Stop Times object with matching arrival time

	public static ArrayList<StopTimes> searchByArrivalTime(String arrTime) {
		 
		 ArrayList<StopTimes> st = new ArrayList<StopTimes>();
		 ArrayList<StopTimes> st2 = new ArrayList<StopTimes>();
		 if (arrTime != null && validTime(arrTime)) {
		 ArrayList<StopTimes> stopTimes = createStopTimes("stop_times.txt");
		 for (int i = 0; i<stopTimes.size();i++) {
			 if (getArrivalTime(stopTimes.get(i)).equals(arrTime) ||
				("0" + getArrivalTime(stopTimes.get(i)).trim()).equals(arrTime)) {
				 st.add(stopTimes.get(i));
			 }
		 }
		 int[] tripids =new int[st.size()];
		 for (int i = 0; i< st.size();i++) {
			 tripids[i] = st.get(i).trip_id;
		 }
 
		 sortTripIds(tripids);
		 
		 //Creates new Array List of Stop Times sorted by Trip ID
		 for (int i = 0; i< st.size();i++) {
			 for(int j = 0; j<st.size();j++) {
				 if (st.get(j).trip_id == tripids[i]) {
					 st2.add(st.get(j));
				 }
			 }
		 }
		 }
		 
		 else { System.out.println("Please enter valid time as HH:MM:SS");
		 }
	
		 return st2;
		 
	 }
	 
	 //Method will print details of a list of Stop Times Objects
	 public static void printStopTimes(ArrayList<StopTimes> st) {
		 if (st != null) {
			 for (int i = 0; i<st.size();i++) {
				 System.out.printf("*********\n Trip ID:%d\n Arrival Time:%s\n Departure Time:%s\n"
				 		+ " Stop ID:%d\n Stop Sequence:%s\n Stop Head:%s\n"
				 		+ " PickUp:%s\n Drop Off:%s\n\n",st.get(i).trip_id,st.get(i).arrival_time,
				 		st.get(i).departure_time, st.get(i).stop_id, st.get(i).stop_sequence,
				 		st.get(i).stop_headsign,st.get(i).pickup_type, st.get(i).drop_off_type);
			 }
		 }
		 else {
			 System.out.println("No trips match this search.");
		 }
	 }
	 
	 public static void sortTripIds(int[] trips) {

	    	int temp;
	    	for (int i = 1; i < trips.length; i++) {
	    		for(int j = i ; j > 0 ; j--){
	    			if(trips[j] < trips[j-1]){
	    				temp = trips[j];
	    				trips[j] = trips[j-1];
	    				trips[j-1] = temp;
	    			}
	    		}
	    	}
	 }
	 
	 static boolean isEmpty(double a[])
		{
			if(a == null)
			{
				return true;
			}
			else{return false; }
		}
	 
	 public static ArrayList<DirectedEdge> createStopTimesGraph(){
		 
		 ArrayList<StopTimes> st = new ArrayList<StopTimes>();
			ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
			st = createStopTimes("stop_times.txt");
			
			for (int i = 0; i<(st.size()-1); i++) {
				if (st.get(i).trip_id==st.get(i+1).trip_id) {
					DirectedEdge de = new DirectedEdge(st.get(i).stop_id,st.get(i+1).stop_id,
							1);
					edges.add(de);
				}
			}
			return edges;
	 }


}

