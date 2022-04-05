import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath {
	
	public final int LAST_STOP_NUMBER = 12034;
	public final int INF = Integer.MAX_VALUE;
	double[][] matrix = new double[LAST_STOP_NUMBER][LAST_STOP_NUMBER];
	int stopTimesCost = 1;
	int transferType0Cost = 2;
	
	public ShortestPath() {
		// TODO Auto-generated constructor stub
	}

	
	public void createMatrix() {
		for(int i = 0; i < 12479; i++)
		{
			for(int j = 0; j < 12479; j++) 
			{
				if(i != j)
				{
					matrix[i][j] = INF;
				}
				else {
					matrix[i][j] = 0;
				}
			}
		}
		
		File stop_times = new File("stop_times.txt");
		File transfers = new File("transfers.txt");
		
		int src = 0; 
		int dest = 0; 
		int prevTripId = 0;
		int nextTripId = 0;
		double cost = stopTimesCost ;
		double minTime;
		int indirect; 
		String currentLine;
		
		try {
			Scanner scanStopTimes = new Scanner(stop_times);
			Scanner scanTransfers = new Scanner(transfers);
			
			while(scanStopTimes.hasNextLine()) {
				
				currentLine = scanStopTimes.nextLine();
				Scanner line = new Scanner(currentLine);
				line.split(",");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
