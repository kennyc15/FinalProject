import java.util.ArrayList;
import java.util.Arrays;

public class SearchingBuses extends StopTimes{
 
	SearchingBuses(int tripId, String arrivalTime, String departureTime, int stopId, String stopSeq, String stopHead,
			String pickup, String dropOff, String shapeDist) {
		super(tripId, arrivalTime, departureTime, stopId, stopSeq, stopHead, pickup, dropOff, shapeDist);
		// TODO Auto-generated constructor stub
	}

	static ArrayList<StopTimes> stopTimes = createStopTimes("stop_times.txt");
	
	
	static boolean isEmpty(int[] a)
	{
		if(a == null)
		{
			return true;
		}
		else{return false; }
	}
	
	/*public static ArrayList<StopTimes> sortStopTimesByTripId(ArrayList<StopTimes> st){
		
		int[] sortIds = null;
		ArrayList<StopTimes> sortedByTripId = new ArrayList<StopTimes>();
		int n = st.size();

		for (int i = 0; i <n; i++) {
			sortIds[i] = getTripId(st.get(i));
		}
		Arrays.sort(sortIds);
		for(int j = 0; j<n; j++) {
			if (sortIds[0] == getTripId(st.get(j))){
				
			}
		}
		}*/
		
	
	//method implements insertion sort
	public static int[] sortTripId(int[] tripIds) {
		
		  int n = tripIds.length;
	      int [] a = new int [n];
	    
		if(isEmpty(a)) {
    		return null;
    	}
    	else {
    	int temp;
    	for (int i = 1; i < tripIds.length; i++) {
    		for(int j = i ; j > 0 ; j--){
    			if(a[j] < a[j-1]){
    				temp = a[j];
    				tripIds[j] = tripIds[j-1];
    				a[j-1] = temp;
    			}
    		}
    	}
    	}
    	return a;
    }
		
	}


