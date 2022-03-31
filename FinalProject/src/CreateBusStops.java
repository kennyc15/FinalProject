	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.util.ArrayList;
	import java.util.Scanner;
	
public class CreateBusStops {

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
		
	
		public static void main(String args[]) throws FileNotFoundException {
			
			
		
		}
	}
