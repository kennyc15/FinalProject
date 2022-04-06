import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath {
	
	public static double[][] matrix = new double[1200][1200];

	public ShortestPath() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		
	}
	
	public static void createMatrix() throws FileNotFoundException {
		
		
		int transferType;
		double minTransTime;
		
	for (int i = 0; i < matrix.length; i++) {
		for (int j = 0; j< matrix.length; j++) {
			if (i != j) {
				matrix[i][j] = Double.POSITIVE_INFINITY; //diagonals
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
	
	File transfers = new File("transfers.txt");
	scan  = new Scanner(transfers);
	Scanner scanLine = null;
	
	while (scan.hasNextLine()) {
		line = scan.nextLine();
		scanLine = new Scanner(line);
		scanLine.useDelimiter(",");
		departStop = scanLine.nextInt();
		arriveStop = scanLine.nextInt();
		transferType = scanLine.nextInt();
		
		if(transferType == 0) {
			matrix[departStop][arriveStop] = 2;
		}
		else if(transferType ==2) {
			minTransTime = scanLine.nextDouble();
			matrix[departStop][arriveStop] = ( minTransTime/ 100 );
		}
		scanLine.close();
	}
	scan.close();
	}

}
