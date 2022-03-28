import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class createBusStops {

	public static ArrayList<busStops> busStopDetails(String filename){
		
		ArrayList<busStops> stopDetails = new ArrayList<busStops>();
	
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String word = null;
			
		while ((word = br.readLine()) != null) {
			
		String line[] = word.split(",");

		//int stopid = Integer.parseInt(line[0]);
		int stopid = 0;
		int stopcode = 0;
		if(!line[1].contains(" ")){
			stopcode = Integer.parseInt(line[1]);
		}
		String stopname = line[2];
		String stopDesc = line[3];
		double stoplat = Double.parseDouble(line[4]);
		double stopLon = Double.parseDouble(line[5]);
		String zoneID = line[6];
		String stopURL = line[7];
		int location = Integer.parseInt(line[8]);
		int parent  = 0;
		busStops bs = new busStops(stopid, stopcode, stopname, stopDesc, stoplat, stopLon,
				zoneID, stopURL, location, parent);
		stopDetails.add(bs);
		
	}
		br.close();
		return stopDetails;
	} catch (Exception e) {System.out.println(e);
		return stopDetails;
	}
	}
	
	public static int getStopId(busStops bs ) {
		
		return bs.stop_id;
		
	}
	
	public static int getStopCode(busStops bs ) {
		
		return bs.stop_code;
		
	}
	
	
public static String getStopName(busStops bs) {
		
		return bs.stop_name.toString();
		
	}
	
	public static void main(String args[]) throws FileNotFoundException {
		
		System.out.println(busStopDetails("stops.txt").size());
	
	}
}