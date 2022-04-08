import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShortestPath extends DijkstraSP{
	
	public ShortestPath(EdgeWeightedDigraph G, int s) {
		super(G, s);
		// TODO Auto-generated constructor stub
	}

	public static void createMatrix() throws FileNotFoundException {
   
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(12479);
		ArrayList<DirectedEdge> de1 = createTransfersGraph();
		ArrayList<DirectedEdge> de2 = createStopTimesGraph();
		for(int i = 0; i< de1.size();i++) {
			ewd.addEdge(de1.get(i));
		}
		for(int i = 0; i< de2.size();i++) {
			ewd.addEdge(de2.get(i));
		}
	   DijkstraSP dsp = new DijkstraSP(ewd, 12479);
	   System.out.println(dsp.distTo(379));
}
	
	public static void main(String args[]) {
		try {
			createMatrix();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	
	public static ArrayList<DirectedEdge> createTransfersGraph(){
		
		ArrayList<Transfers> trans = new ArrayList<Transfers>();
		ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
		trans = createTransfers("transfers.txt");
		for (int i = 0; i< trans.size();i++) {
			if(trans.get(i).transfer_type.equalsIgnoreCase("0")) {
			DirectedEdge de = new DirectedEdge(Integer.parseInt(trans.get(i).from_stop_id), 
					Integer.parseInt(trans.get(i).to_stop_id),1);
			edges.add(de);
		}
			else if(trans.get(i).transfer_type.equalsIgnoreCase("2")) {
				DirectedEdge de = new DirectedEdge(Integer.parseInt(trans.get(i).from_stop_id), 
						Integer.parseInt(trans.get(i).to_stop_id),trans.get(i).min_transfer_time/100);
				edges.add(de);
			}
		}
		return edges;
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

public static boolean validTime(String time) {
	 
	 String format = "((\\s?)[0-9]|[01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
	 Pattern p = Pattern.compile(format);
	 if (time == null) {
           return false;
  }
	 Matcher m1 = p.matcher(time);

       return m1.matches();	
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
