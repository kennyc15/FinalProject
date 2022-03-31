import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateTransfers {
	
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

		public static void main(String args[]) throws FileNotFoundException {
		
		
	}
}
