import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class UDPSocketListener {

	private RoutingServer Server;
	
	private DatagramSocket Socket;
	private boolean Running;
	
	private byte[] buf = new byte[256];
	private final String FailureMessage = "Could Not Add Player";
	private byte[] FailureMessageBytes;
	
	public UDPSocketListener(RoutingServer InServer) {
		Server = InServer;
		FailureMessageBytes = FailureMessage.getBytes();
	}
	
	public void start() throws IOException {
		// Attempt to open socket
		Socket = new DatagramSocket(8000);
		
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
            byte[] SendBytes;
            Player NewPlayer = Server.startPlaying(address, port);
            if(NewPlayer != null) {
            	SendBytes = ByteBuffer.allocate(4).putInt(NewPlayer.RegionID).array();
            } else {
            	SendBytes = FailureMessageBytes;
            }
            
            // Create reception message
            packet = new DatagramPacket(SendBytes, SendBytes.length, address, port);
            
            // Try to send message back
            Socket.send(packet);
        }
		
		Socket.close();
	}
	
	public void stop() {
		Running = false;
		Socket = null;
	}
	
	private boolean ValidateMessage(String Message) {
		System.out.println("Received Message: " + Message);
		return true;
	}
}
