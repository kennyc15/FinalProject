
public class Transfers {
		
		public String from_stop_id;
		public String to_stop_id;
		public String transfer_type;
		public int min_transfer_time;
		
		
		Transfers(String fromStopId, String toStopId, String transferType, int minTransferTime) {
			
		this.from_stop_id = toStopId;
		this.to_stop_id = toStopId;
		this.transfer_type = transferType;
		this.min_transfer_time = minTransferTime;
	
		
		}
		
		public static String getFromStop(Transfers trans) {
			return trans.from_stop_id;
		}
		
		public static int getMinTransTime(Transfers trans) {
			return trans.min_transfer_time;
		}
		
		
		}


