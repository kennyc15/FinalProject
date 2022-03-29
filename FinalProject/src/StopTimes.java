
public class StopTimes {

	public int trip_id;
	public String arrival_time;
	public String departure_time;
	public int stop_id;
	public String stop_sequence;
	public String stop_headsign;
	public String pickup_type;
	public String drop_off_type;
	public String shape_dist_traveled;
	
	StopTimes(int tripId, String arrivalTime, String departureTime, int stopId, String stopSeq, 
			String stopHead, String pickup, String dropOff, String shapeDist ) {
		
		this.trip_id = tripId;
		this.arrival_time = arrivalTime;
		this.departure_time = departureTime;
		this.stop_id = stopId;
		this.stop_sequence = stopSeq;
		this.stop_headsign = stopHead;
		this.pickup_type = pickup;
		this.drop_off_type = dropOff;
		this.shape_dist_traveled = shapeDist;
	}
	
}
