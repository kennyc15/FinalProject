import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ShortestPath {
	
	public static double[][] matrix = new double[12479][12479];
	public static final double INF = Double.POSITIVE_INFINITY;

	public ShortestPath() {
		// TODO Auto-generated constructor stubShortestRoutes() {
		try {
			createMatrix();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void findShortestPath(int a, int b) throws FileNotFoundException {
		
		createMatrix();
		System.out.print(shortestPath(a,b));
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		findShortestPath(646,378);
	}
	
	public static void createMatrix() throws FileNotFoundException {
		
		
		int transferType;
		double minTransTime;
		
	for (int i = 0; i < 12479; i++) {
		for (int j = 0; j< 12479; j++) {
			if (i != j) {
				matrix[i][j] = INF; //diagonals
			}
			else {
				matrix[i][j] = 0;
			}
		}
	}
	
	
	File stopTimes = new File("stop_times.txt");
	Scanner scan = new Scanner(stopTimes);
	scan.useDelimiter(",");
	
	int departStop = 0;
	int arriveStop = 0;
	int prevTripId = 0;
	int nextTripId = 0;
	String line = " ";
	
	while(scan.hasNextLine()) {
		prevTripId = nextTripId;
		nextTripId = scan.nextInt();
	
		for(int i = 0; i< 2;i++) {
			scan.next();
		}
		departStop = arriveStop;
		arriveStop = scan.nextInt();
		
		if(prevTripId == nextTripId) {
			matrix[departStop][arriveStop] = 1;
		}
		scan.nextLine();
	}
	scan.close();
	
	FileReader transfers = new FileReader("transfers.txt");
	Scanner scanner = new Scanner(transfers);
	Scanner scanLine = null;
	
	while (scanner.hasNextLine()) {
		line = scanner.nextLine();
		scanLine = new Scanner(line);
		scanLine.useDelimiter(",");
		
		//Problem here with an input mismatch 
		if(scanLine.hasNextInt()) {
		
		departStop = scanLine.nextInt();
		//System.out.println(departStop);
		arriveStop = scanLine.nextInt();
		transferType = scanLine.nextInt();
		
		if(transferType == 0) {
			matrix[departStop][arriveStop] = 2;
		}
		else if(transferType ==2) {
			minTransTime = scanLine.nextDouble();
			matrix[departStop][arriveStop] = ( minTransTime/ 100 );
		}
		}
		scanLine.close();
	}
	scanner.close();
	
	}
	
	public static String shortestPath(int depart, int arrive) {
		
		String output = " ";
		int[] visited = new int[12479];
		int[] edgeTo = new int[12479];
		double[] distTo = new double[12479];
		
		if(depart == arrive) {
			output = "Departure Stop: " + depart +
					"\nArrival Stop: " + arrive +
					"\nCost: " + matrix[depart][arrive] +
					"\nNo other stops on this route.";
			return output;
		}
		
		int n = distTo.length;
		
		for(int i = 0; i < n; i++) {
    		if(depart != i)
    		{
    			distTo[i] = INF;
    		}
    	}
		
		visited[depart] = 1;
    	distTo[arrive] = 0; 
    	int node = depart;
    	int stopsVisited = 0;
		
    	while(stopsVisited < n) {
    		for(int i = 0; i < matrix[node].length;i++) 
    		 {
    			if((visited[i] ==0) && (matrix[node][i]!=INF) ) {
    				relaxEdge(node, i, distTo, edgeTo);
    			}
    		}
    		visited[node] = 1;
    		
    		double minDist = INF;
    		for(int i = 0; i < n; i++) {
    			if(visited[i] != 1 && minDist > distTo[i]) {
    				node = i;
    				minDist = distTo[i];
    			}
    		}
    		stopsVisited = stopsVisited + 1;
    	}
		
    	if(distTo[arrive] == INF) {
			output = "No possible route.";
    		return output;
    	}
    	
    	int x = depart;
    	int y = arrive;
    	String path = "";
    	while(x!=y) {
    		path =  edgeTo[y] + ","+path;
			y = edgeTo[y];
    	}
    	path = path + ", " + arrive;
    	output = "Departure Stop: " + depart +
				"\nArrival Stop: " + arrive +
				"\nCost: " + Double.toString(distTo[arrive]) +
				"\nVia Stop: " + path;
		return output;
	}

	private static void relaxEdge(int node, int dest, double[] distTo, int[] edgeTo) {
		// TODO Auto-generated method stub
		if(distTo[dest] > distTo[node] + matrix[node][dest]) {
    		distTo[dest] = distTo[node] + matrix[node][dest];
    		edgeTo[dest] = node;
	}
}
	
}
