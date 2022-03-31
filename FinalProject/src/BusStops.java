
 public class BusStops {

	public String stop_id;
	public String stop_code;
	public String stop_name;
	public String stop_desc;
	public double stop_lat;
	public double stop_lon;
	public String zone_id;
	public String stop_url;
	public int location_type;
	public String parent_station;
	
	BusStops(String stopID, String stopCode, String stopName, String stopDesc, double stopLat,
			double stopLon, String zoneID, String stopURL, int locationType, String parentStation) {
		
	this.stop_id = stopID;
	this.stop_code = stopCode;
	this.stop_name = stopName;
	this.stop_desc = stopDesc;
	this.stop_lat = stopLat;
	this.stop_lon = stopLon;
	this.zone_id = zoneID;
	this.stop_url = stopURL;
	this.location_type = locationType;
	this.parent_station = parentStation;
	
	}
	
	public static String getStopId(BusStops bs ) {
		
		return bs.stop_id;
		
	}
	
	public static String getStopCode(BusStops bs ) {
		
		return bs.stop_code;
		
	}
	
	
	public static String getStopName(BusStops bs) {
		
		return bs.stop_name.toString();
		
	}

