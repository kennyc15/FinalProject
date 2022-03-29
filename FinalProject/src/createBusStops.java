import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class createBusStops {

	public static ArrayList<busStops> busStopDetails(String filename){
		
		ArrayList<busStops> stopDetails = new ArrayList<busStops>();
	
		try {
			FileReader fr = new FileReader(filename);
			Scanner scan = new Scanner(fr);
				
		while (scan.hasNextLine()) {
			
		String line[] = scan.nextLine().split(",");

		String stopid = line[0];
		String stopcode = "";
		if(!line[1].contains(" ")){
			stopcode = line[1];
		}
		String stopname = line[2];
		String stopDesc = line[3];
		double stoplat = Double.parseDouble(line[4]);
		double stopLon = Double.parseDouble(line[5]);
		String zoneID = line[6];
		String stopURL = line[7];
		int location = Integer.parseInt(line[8]);
		String parent  = line[line.length - 1];
		busStops bs = new busStops(stopid, stopcode, stopname, stopDesc, stoplat, stopLon,
				zoneID, stopURL, location, parent);
		stopDetails.add(bs);
		
	}
		scan.close();
		return stopDetails;
	} catch (Exception e) {System.out.println(e);
		return stopDetails;
	}
	}
	
	public static String getStopId(busStops bs ) {
		
		return bs.stop_id;
		
	}
	
	public static String getStopCode(busStops bs ) {
		
		return bs.stop_code;
		
	}
	
	
	public static String getStopName(busStops bs) {
		
		return bs.stop_name.toString();
		
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		
		System.out.println(getStopName(busStopDetails("stops.txt").get(2)));
	
	}
}