import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStops {

	public String stop_id;
	public String stop_code;
	public String stop_name;
	public String stop_desc;
	public double stop_lat;
	public double stop_lon;
	public String zone_id;
	public String stop_url;
	public int location_type;
	public String parent_station;
	
	BusStops(String stopID, String stopCode, String stopName, String stopDesc, double stopLat,
			double stopLon, String zoneID, String stopURL, int locationType, String parentStation) {
		
	this.stop_id = stopID;
	this.stop_code = stopCode;
	this.stop_name = stopName;
	this.stop_desc = stopDesc;
	this.stop_lat = stopLat;
	this.stop_lon = stopLon;
	this.zone_id = zoneID;
	this.stop_url = stopURL;
	this.location_type = locationType;
	this.parent_station = parentStation;
	
	}
	
	public static ArrayList<BusStops> createBusStops(String filename){

		ArrayList<BusStops> stopDetails = new ArrayList<BusStops>();

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
		BusStops bs = new BusStops(stopid, stopcode, stopname, stopDesc, stoplat, stopLon, zoneID, 
				stopURL, location, parent);
		stopDetails.add(bs);

	}
		scan.close();
		return stopDetails;
	} catch (Exception e) {System.out.println(e);
		return stopDetails;
	}
	}
	
	public static void formatStopName(String str) {
		
		String first = str.split(" ")[0];
		String second = str.split(" ")[1];
		if (first.contains("WB")||first.contains("NB")||first.contains("EB")||first.contains("SB")) {
		str = str.replace(first, "").trim();
		str = str + " " + first;
		}
		
		else if (first.contains("FLAGSTOP")) {
			str = str.replace(first, "").trim();
			str = str + " " + first;
			str = str.replace(second, "").trim();
			str = str + " " + second;
		}
		System.out.println(str);
		}
	
	public static void main (String[] args) {
		formatStopName("WB HASTINGS ST FS HOLDOM AVE-") ;
	}
	
	}

	

