
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RoutingServer {

	class RegionIndex {
		public int XIndex;
		public int YIndex;
		
		@Override
		public int hashCode() {
			int hashCode = 0;
		    hashCode = (hashCode * 397) ^ XIndex;
		    hashCode = (hashCode * 397) ^ YIndex;
		    return hashCode;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			
			if(getClass() != obj.getClass()) {
				return false;
			}
			
			RegionIndex OtherRegIndex = (RegionIndex)obj;
			
			return XIndex == OtherRegIndex.XIndex && YIndex == OtherRegIndex.YIndex;
		}
	}
	
	// map of regions, along with routing registry
	private Map<RegionIndex, Region> RegionsByIndex;
	private Map<Integer, Region> RegionsByID;
	private Map<Integer, Player> PlayerMap;
	
	private final int MapSize = 20;
	private Random RandGenerator;


	public RoutingServer() {
		RandGenerator = new Random();
		RegionsByIndex = new HashMap<RegionIndex, Region>();
		RegionsByID = new HashMap<Integer, Region>();
		PlayerMap = new HashMap<Integer, Player>();
		
		// Create Regions
		for(int RegionX = 0; RegionX < MapSize; ++RegionX) {
			for(int RegionY = 0; RegionY < MapSize; ++RegionY) {
				Region NewReg = new Region();
				
				RegionIndex Index = new RegionIndex();
				Index.XIndex = RegionX;
				Index.YIndex = RegionY;
				
				NewReg.RegionID = Index.hashCode();
				
				RegionsByIndex.put(Index, NewReg);
				RegionsByID.put(NewReg.RegionID, NewReg);
			}
		}
	}
	
	Player startPlaying(InetAddress IncomingIP, int IncomingPort) {
		Player Player = new Player(IncomingIP, IncomingPort);
		
		// Check if player exists
		if(PlayerMap.containsKey(Player)) {
			return null;
		}
		
		Region SelectedRegion = selectRegion();
		
		if(SelectedRegion == null) {
			return null;
		}
		
		if(SelectedRegion.addPlayer(Player)) {
			PlayerMap.put(Player.PlayerID, Player);
			return Player;
		}
		
		return null;
	}
	
	boolean stopPlaying(int PlayerID) {
		
		Player Player = PlayerMap.get(PlayerID);
		
		// no player remembered
		if(Player == null) {
			return false;
		}
		
		Region Reg = RegionsByID.get(Player.RegionID);
		
		// invalid region
		if(Reg == null) {
			return false;
		}
		
		return Reg.removePlayer(Player);
	}
	
	boolean transferRegion(int PlayerID, int NewRegionID) {
		
		Player Player = PlayerMap.get(PlayerID);
		
		// no player remembered
		if(Player == null) {
			return false;
		}
		
		Region PrevReg = RegionsByID.get(Player.RegionID);
		Region NewReg = RegionsByID.get(NewRegionID);
		
		if(PrevReg == null || NewReg == null) {
			return false;
		}
		
		if(!PrevReg.removePlayer(Player)) {
			return false;
		}
		
		if(!NewReg.addPlayer(Player)) {
			return false;
		}
		
		return true;
	}
	
	Region selectRegion() {
		int SelectedEdge = RandGenerator.nextInt(4);
		int RandomIndex = RandGenerator.nextInt(MapSize);
		
		RegionIndex Index = new RegionIndex();
		
		// Left = 0, Top = 1, Right = 2, Bottom = 3
		switch(SelectedEdge) {
		case 0:
			Index.XIndex = 0;
			Index.YIndex = RandomIndex;
			break;
		case 1:
			Index.XIndex = RandomIndex;
			Index.YIndex = MapSize-1;
			break;
		case 2:
			Index.XIndex = MapSize-1;
			Index.YIndex = RandomIndex;
			break;
		case 3:
			Index.XIndex = RandomIndex;
			Index.YIndex = 0;
			break;
		}
		
		return RegionsByIndex.get(Index);
	}
	
	void KickAllPlayers() {
		
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
        // HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        // server.createContext("/test", new MyHandler());
        // server.setExecutor(null); // creates a default executor
        // server.start();

    // static class MyHandler implements HttpHandler {
        // @Override
        // public void handle(HttpExchange t) throws IOException {
            // String response = "This is the response";
            // t.sendResponseHeaders(200, response.getBytes().length);
            // OutputStream os = t.getResponseBody();
            // os.write(response.getBytes());
            // os.close();
        // }
    // }
	
	

