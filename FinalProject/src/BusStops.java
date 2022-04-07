import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStops extends TST{

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
		String stopname = formatStopName(line[2]);
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
	
	public static String formatStopName(String str) {
		
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
		return str;
		}
	
	//Prints details of multiple stops
	public static void printBusStops(ArrayList<BusStops> bs) {
		 if (bs != null) {
			 for (int i = 0; i<bs.size();i++) {
				 System.out.printf("\n*********\n Stop ID:%s\n Stop Code:%s\n Stop Name:%s\n"
				 		+ " Stop Desc:%s\n Stop Lat:%.6f\n Stop Lon:%.6f\n"
				 		+ " Zone ID:%s\n\n",bs.get(i).stop_id,bs.get(i).stop_code,
				 		bs.get(i).stop_name, bs.get(i).stop_desc, bs.get(i).stop_lat,
				 		bs.get(i).stop_lon,bs.get(i).zone_id);
			 }
		 }
	}
	
	//Prints details of one stop
	public static void printBusStop(BusStops bs) {
		 if (bs != null) {
				 System.out.printf("*********\n Stop ID:%s\n Stop Code:%s\n Stop Name:%s\n"
				 		+ " Stop Desc:%s\n Stop Lat:%.6f\n Stop Lon:%.6f\n"
				 		+ " Zone ID:%s\n\n",bs.stop_id,bs.stop_code,
				 		bs.stop_name, bs.stop_desc, bs.stop_lat,
				 		bs.stop_lon,bs.zone_id);
		 }
		 else {
			 System.out.println("No such stop exists.");
		 }
	}
	

	public static void main (String[] args) throws FileNotFoundException {
		
		 ArrayList<BusStops> bs = createBusStops("stops.txt");
		 
		 TST<BusStops> tst = new TST<BusStops>();
		 //System.out.println("This search allows you to searh for a bus stop by stop name.");
		 //System.out.println("All matching stops found will be displayed.");
		
		 Scanner input = new Scanner(System.in);
		 boolean end = false;
		 
			while(!end) {
				 System.out.println("Please enter the name of the stop you wish to search (in block capitals e.g. 'HASTINGS'): ");
				 if (input.hasNext()) {
					 String searchStop = input.next();
		 for (int i = 0; i< bs.size();i++) {
			 tst.put(bs.get(i).stop_name, bs.get(i));
		 }
		 
		 Iterable<String> ItStr = tst.keysWithPrefix(searchStop);
		 //System.out.println(ItStr);
		 // Print each stop on a new line
		 if (ItStr != null) {
		 for (String s: ItStr) {
			 for(int i = 0; i< bs.size();i++) {
			    if(s.equals(bs.get(i).stop_name)){
			    	printBusStop(bs.get(i));
			    }
			}
		 }
		 System.out.println("Would you like to search for another stop (yes/ no)?");
		 if(input.next().equalsIgnoreCase("yes")) {end = false;}
		 else {end = true;
		 System.out.println("This search has been terminated.\n");
		 Interface.main(args);}
		 }
		 else {
			 System.out.println("Please enter a valid stop name (in block capitals e.g. 'HASTINGS')");
			 end = false;
		 }
	  }
	}
			input.close();
	}
}

	

	

