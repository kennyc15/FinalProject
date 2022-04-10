

/*
 This method implements classes from the textbook, namely:
 DijkstraSP
 DirectedEdge
 EdgeWeightedDigraph
 In
 Bag
 StdRandom
 IndexMinPQ
 
 All have been adapted and implemented according to my program design
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath extends DijkstraSP{
	
	public ShortestPath(EdgeWeightedDigraph G, int s) {
		super(G, s);
		// TODO Auto-generated constructor stub
	}

	public static double createMatrix(int source, int sink) throws FileNotFoundException {
   
		//Create empty EWD
		EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(12481);
		
		//Obtain ArrayList of DEs from classes Transfers and StopTimes
		//Both classes have methods to create these array lists
		ArrayList<DirectedEdge> de1 = new ArrayList<DirectedEdge>();
		de1 = Transfers.createTransfersGraph();
		ArrayList<DirectedEdge> de2 = new ArrayList<DirectedEdge>();
		de2 = StopTimes.createStopTimesGraph();
		
		//Add edges to EWD
		for(int i = 0; i< de1.size();i++) {
			ewd.addEdge(de1.get(i));
		}
        
		for(int i = 0; i< de2.size();i++) {
			ewd.addEdge(de2.get(i));
		}
		
		//Create SP object with EWD and the source node (as inputed by user)
				double d = Double.POSITIVE_INFINITY;
				if (source > 12481 || source < 0) {
					d = -1;
					   return d;
				   }
				else {
			   DijkstraSP dsp = new DijkstraSP(ewd, source);
			   d = dsp.distTo(sink);
			   //System.out.println(d);
			   return d;
				}
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
			if (cost != Double.POSITIVE_INFINITY && cost > 0) {
			System.out.println("\nDeparture Stop: " + source);
			System.out.println("\nArrival Stop: " + sink);
			System.out.println("\nMinimum Cost: " + cost);
			}
			else {
				System.out.println("\nNo route possible.");
			}
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
