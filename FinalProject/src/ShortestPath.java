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

	public static double createMatrix(int source, int sink) throws FileNotFoundException {
   
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(12481);
		ArrayList<DirectedEdge> de1 = new ArrayList<DirectedEdge>();
		de1 = Transfers.createTransfersGraph();
		ArrayList<DirectedEdge> de2 = new ArrayList<DirectedEdge>();
		de2 = StopTimes.createStopTimesGraph();
		
		for(int i = 0; i< de1.size();i++) {
			ewd.addEdge(de1.get(i));
		}
        
		for(int i = 0; i< de2.size();i++) {
			ewd.addEdge(de2.get(i));
		}
	   DijkstraSP dsp = new DijkstraSP(ewd, source);
	   double d = dsp.distTo(sink);
	   return d;
}
	
	public static void main(String args[]) {
		
		Scanner input = new Scanner(System.in);
		boolean end = false;
		while(!end) {
		System.out.println("Enter departure Stop ID: ");
		int source = input.nextInt();
		System.out.println("Enter arrival Stop ID: ");
		int sink = input.nextInt();
		try {
			double cost = createMatrix(source, sink);
			System.out.println("\nDeparture Stop: " + source);
			System.out.println("\nArrival Stop: " + sink);
			System.out.println("\nMinimum Cost: " + cost);
			System.out.println("\n\nWould you like to calculate the minimum cost of "
					+ "another route? (yes/ no) ");
			if (input.next().equalsIgnoreCase("yes")) {
				end = false;
			}
			else {
				end = true;
				Interface.main(args);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		input.close();
	}
	
}

