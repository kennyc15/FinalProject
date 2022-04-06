import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath 

{
    private final int MATRIX_SIZE = 12479;
	private double matrix[][] = new double[MATRIX_SIZE][MATRIX_SIZE];
	double stopTimesCost = 1;
	public final double inf = Double.POSITIVE_INFINITY;


	public static void main(String[] args) throws FileNotFoundException 
	{

	}

	private void matrixBuilder() throws FileNotFoundException {

		for(int i = 0; i < MATRIX_SIZE; i++)
		{
			for(int j = 0; j < MATRIX_SIZE; j++) 
			{
				if(i == j)
				{

					matrix[i][j] = 0;
				}
				else if (i != j){
					matrix[i][j] = inf;
				}
			}
		}


		int src = 0; 
		int dest = 0; 
		String prevTrip = " ";
		String thisTrip = " ";
		int indirect; 
		double minTime;

		File stFile = new File("stop_times.txt");
		Scanner scanST = new Scanner(stFile);

		while (scanST.hasNextLine()) {
			
			String[] line = scanST.nextLine().split(",");
			prevTrip = thisTrip;
			thisTrip = line[0];
			
			src = dest;
			dest = Integer.parseInt(line[3]);
			
				if(prevTrip.equalsIgnoreCase(thisTrip)) 
	
				{
					matrix[src][dest] = stopTimesCost;
	
				}
			
		scanST.close();

}
	}