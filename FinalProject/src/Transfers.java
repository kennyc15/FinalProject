
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Transfers {
		
		public String from_stop_id;
		public String to_stop_id;
		public String transfer_type;
		public int min_transfer_time;
		
		
		Transfers(String fromStopId, String toStopId, String transferType, int minTransferTime) {
			
		this.from_stop_id = fromStopId;
		this.to_stop_id = toStopId;
		this.transfer_type = transferType;
		this.min_transfer_time = minTransferTime;
	
		
		}
		
		public Transfers() {
			// TODO Auto-generated constructor stub
		}

		public static void main(String[] args) {
			createTransfersGraph();
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
				scan.nextLine();
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
			System.out.println(transferDetails);
			return transferDetails;
			
		} 
			catch (Exception e) {
		   System.out.println(e);
		   return transferDetails;
		}
	}
		public static ArrayList<DirectedEdge> createTransfersGraph(){
			
			ArrayList<Transfers> trans = new ArrayList<Transfers>();
			ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
			trans = createTransfers("transfers.txt");
			for (int i = 0; i< trans.size()-1;i++) {
				if(trans.get(i+1).transfer_type.equalsIgnoreCase("0")) {
					//System.out.println(trans.get(i+1).from_stop_id);
				DirectedEdge de = new DirectedEdge(Integer.parseInt(trans.get(i+1).from_stop_id), 
						Integer.parseInt(trans.get(i+1).to_stop_id),1);
				edges.add(de);
				
			}
				else if(trans.get(i+1).transfer_type.equalsIgnoreCase("2")) {
					DirectedEdge de = new DirectedEdge(Integer.parseInt(trans.get(i+1).from_stop_id), 
							Integer.parseInt(trans.get(i+1).to_stop_id),trans.get(i+1).min_transfer_time/100);
					edges.add(de);
					//System.out.println(de);
				}
			}
			return edges;
		}
		
		}


