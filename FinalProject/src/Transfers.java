import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Transfers {
		
		public String from_stop_id;
		public String to_stop_id;
		public String transfer_type;
		public int min_transfer_time;
		
		
		Transfers(String fromStopId, String toStopId, String transferType, int minTransferTime) {
			
		this.from_stop_id = toStopId;
		this.to_stop_id = toStopId;
		this.transfer_type = transferType;
		this.min_transfer_time = minTransferTime;
	
		
		}
		
		public static String getFromStop(Transfers trans) {
			return trans.from_stop_id;
		}
		
		public static int getMinTransTime(Transfers trans) {
			return trans.min_transfer_time;
		}
		
		public static ArrayList<Transfers> createTransfers(String filename){
			
			ArrayList<Transfers> transferDetails = new ArrayList<Transfers>();
		
			try {
				FileReader fr = new FileReader(filename);
				Scanner scan = new Scanner(fr);
				
				while (scan.hasNextLine()) {
					
				String[] line = scan.nextLine().split(",");
				
				String fromStopid = line[0];
				String toStopid = line[1];
				String transferType = line[2];
				int minTranstime =  Integer.parseInt((line[line.length - 1]));
				
				Transfers transfer = new Transfers(fromStopid, toStopid,transferType, minTranstime);
				transferDetails.add(transfer);
			
		}
			scan.close();
			return transferDetails;
			
		} 
			catch (Exception e) {
		   System.out.println(e);
		   return transferDetails;
		}
	}
		
		
		}


