import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath 

{

	private static double matrix[][];
	private static double distTo[];
	private static int[] edgeTo;
	static int visited[];
	private int stopCost = 1;
	private int immediateTransCost = 2;
	public static double inf = Double.POSITIVE_INFINITY;

	//cannot yet initialize type 2 cost as need minTime
	
	
	public static void main(String[] arg) {
		
		shortestPath(646,379);
		
	}

	
	public void createMatrix() throws FileNotFoundException {
		
		//crate initial matrix
		int transferType;
		int prevTrip = 0;
		int currTrip = 0;
		double minTime;
		
		for (int i = 0; i< matrix.length; i++) {
			for (int j = 0; j< matrix.length; j++) {
				
				if (i != j) {
					matrix[i][j] = inf; //diagonals
				}
				else {
					matrix[i][j] = 0; //non-diagonals
				}
			}
			
		}
		
		File stopTimes = new File("stop_times.txt");
		Scanner scanST = new Scanner(stopTimes);
		scanST.useDelimiter(",");
		
		int depart = 0;
		int arrive = 0;
	
		String line;
		
		while(scanST.hasNextLine()) {
			
			prevTrip = currTrip;
			currTrip = scanST.nextInt();
			
			//skip next two elements to obtain stop id
			scanST.next();
			scanST.next();
			
			if(prevTrip == currTrip) {
				matrix[depart][arrive] = stopCost;
			}
			
			scanST.nextLine();
		}
		scanST.close();
		
		//to read in transfer routes
		File transfers = new File("transfers.txt");
		Scanner scanTransfers = new Scanner(transfers);
		
		while(scanTransfers.hasNextLine()) {
			line = scanTransfers.nextLine();
			Scanner scanLine = new Scanner(line);
			scanLine.useDelimiter(",");
			
			depart = scanLine.nextInt();
			arrive = scanLine.nextInt();
			transferType = scanLine.nextInt();
			
			if (transferType == 2) {
				
				minTime = scanLine.nextInt();
	
				matrix[depart][arrive] = (minTime/ (100));
			}
			
			else if (transferType == 0) {
				matrix[depart][arrive] =  immediateTransCost ;
			}
			scanLine.close();
	}
		scanTransfers.close();
}
	
	public static String shortestPath(int depart, int arrive){
		
		String output = " ";
		visited[depart] = 1;
		distTo[depart] = 0;
		int current = depart;
		int stopsVisited = 0;
		double minDist = inf;
		
		if (depart == arrive) {
			
			output = "Departure Stop: " + depart
					+ "\nArrival Stop: " + arrive
					+ "Minimum cost: " + matrix[depart][arrive]
					+ "No other stops on this route.";
			return output;
		}
		
		else {
			int n  = distTo.length;
			for(int i = 0; i < n; i++) {
	    		if(i != depart)
	    		{
	    			distTo[i] = inf;
	    		}
	    	}
			
			while (stopsVisited < n) {
				
				for(int i = 0; i < n; i++) 
				{
					if(visited[i] != 1 && distTo[i] < minDist) 
					{
						current = i;
						minDist = distTo[i];
					}
				}
				stopsVisited++;
				
				for(int i = 0; i < matrix[current].length; i ++) 
				{

					if(visited[i] == 0) 
					{
						relaxEdge(current, i, distTo, edgeTo);
					}

				}
				visited[current] = 1;
			}
			
			if(distTo[arrive] ==inf) 
			{
				return "No route possible.";
			}
			
			int x = depart;
			int y = arrive;
			String via = "";
			
			while(x != y) 
			{
				via =  edgeTo[y] + via;
				y = edgeTo[y];
			}
			
			output = "Departure Stop: " + depart
					+ "\nArrival Stop: " + arrive
					+ "Minimum cost: " + matrix[depart][arrive]
					+ "Route via: " + via;

			return output;
		}

	}
	
	 private static void relaxEdge(int depart, int arrive, double[] distTo, int[] edgeTo) {
	    	if(distTo[arrive] > matrix[depart][arrive]+ distTo[depart] ) {
	    		distTo[depart] = matrix[depart][arrive] + distTo[depart] ;
	    		edgeTo[arrive] = depart;
	    	}
	    }

}
