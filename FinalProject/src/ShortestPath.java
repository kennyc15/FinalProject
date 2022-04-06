import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath 

{

	private double matrix[][];
	private double distTo[][];
	private int[][] edgeTo[][];
	private int stopCost = 1;
	private int immediateTransCost = 2;
	public double inf = Double.POSITIVE_INFINITY;

	//cannot yet initialize type 2 cost as need minTime

	
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
}
