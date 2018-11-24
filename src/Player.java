import java.net.InetAddress;

public class Player {
	
	public int PlayerID;
	public InetAddress IP;
	public int Port;
	public int RegionID;
	
	public Player(InetAddress IP, int Port) {		
		this.IP = IP;
		this.Port = Port;

		PlayerID = IP.toString().hashCode() + Integer.hashCode(Port);
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