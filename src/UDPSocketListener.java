import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.json.JSONObject;

public class UDPSocketListener {

	private RoutingServer Server;
	
	private DatagramSocket Socket;
	private boolean Running;
	
	private byte[] buf = new byte[256];
	private final String FailureMessage = "Player Already Exists";
	private byte[] FailureMessageBytes;
	
	private int DestinationSocket;
	
	public UDPSocketListener(RoutingServer InServer, String InDestinationSocket) {
		Server = InServer;
		DestinationSocket = Integer.parseInt(InDestinationSocket);
		FailureMessageBytes = FailureMessage.getBytes();
	}
	
	public void start() throws IOException {
		System.out.println("Opening Socket" + DestinationSocket);
		
		// Attempt to open socket
		Socket = new DatagramSocket(DestinationSocket);
		
		System.out.println("Socket Sucessfully Opened");
		
		Running = true;
		
		while (Running) {
			// Get a packet
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            Socket.receive(packet);
            
            // Decode message
            String received = new String(packet.getData(), 0, packet.getLength());
            
            // Make sure it is a valid message
            if(!ValidateMessage(received)) {
            	continue;
            }
            
            // Get info
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            
            // Try to register the player
            Player NewPlayer = Server.startPlaying(address, port);
            
            // Make the response
            String JSONString = makeJSONResponse(NewPlayer);
            
            // Create reception message
            byte[] SendBytes = JSONString.getBytes();
            packet = new DatagramPacket(SendBytes, SendBytes.length, address, port);
            
            // Try to send message back
            Socket.send(packet);
        }
		
		Socket.close();
	}
	
	private String makeJSONResponse(Player player) {
		JSONObject json = new JSONObject();
		
		if(player != null) {
			json.put("bSuccess", true);
			
            json.put("PlayerID", player.PlayerID);
            json.put("RegionID", player.RegionID);
        } else {
        	json.put("bSuccess", false);
        }
		
		return json.toString();
	}
	
	public void stop() {
		Running = false;
		Socket.close();
		Socket = null;
	}
	
	private boolean ValidateMessage(String Message) {
		System.out.println("Received Message: " + Message);
		return true;
	}
}
