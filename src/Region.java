import java.util.ArrayList;
import java.util.Random;

public class Region {

	static Random Rand = new Random();
	
	public int RegionID;
	public Player Host;
	public ArrayList<Player> Players;


	public Region() {
		RegionID = -1;
		Host = null;
		Players = new ArrayList<Player>();
	}
	
	public boolean hasHost() {
		return Host != null;
	}
	
	public boolean addPlayer(Player NewPlayer) {
		if(!hasHost() ) {
			Host = NewPlayer;
		}
		
		Players.add(NewPlayer);
		
		NewPlayer.RegionID = RegionID;
		
		System.out.println("Player " + NewPlayer.PlayerID + " Added To Region " + RegionID);
		
		return true;
	}

	public boolean removePlayer(Player Player) {
		if(Players.remove(Player)) {
			
			// Deal with new main host
			if(Host.equals(Player)) {
				// Get another player to be the new main host
				if(!Players.isEmpty()) {
					Host = Players.get(Rand.nextInt(Players.size()));
				} else {
					Host = null;
				}
			}
			
			return true;
		}
		
		return false;
	}
}