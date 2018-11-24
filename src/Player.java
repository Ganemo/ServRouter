import java.util.Map;

public class Player {
	
	public int PlayerID;
	public String IP;
	public int RegionID;
	
	public Player(String IP) {		
		this.IP = IP;

		PlayerID = IP.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		
		if(getClass() != obj.getClass()) {
			return false;
		}
		
		Player OtherPlayer = (Player)obj;
		
		return OtherPlayer.PlayerID == PlayerID;
	}
}	