import java.net.InetAddress;

public class Player {
	
	public int PlayerID;
	public InetAddress IP;
	public int Port;
	public int RegionID;
	
	public static boolean IPBasedID = true;
	
	public Player(InetAddress IP, int Port) {		
		this.IP = IP;
		this.Port = Port;

		if(IPBasedID) {
			PlayerID = IP.toString().hashCode();
		} else {
			PlayerID = (int)(Math.random() * 100000);
		}
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