import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath {
	
	public static double[][] matrix = new double[1200][1200];

	public ShortestPath() {
		// TODO Auto-generated constructor stub
	}
	
	public static void createMatrix() throws FileNotFoundException {
		
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
	Scanner scanST = new Scanner(stopTimes);
	
		
	}

}
