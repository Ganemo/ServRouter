package com.georganemo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyPair;

import com.georganemo.encryption.EncryptionHandler;

public class TestingMain {
	
	static class EchoClient {
	    private DatagramSocket socket;
	    private InetAddress address;
	 
	    private byte[] buf;
	 
	    public EchoClient() throws UnknownHostException, SocketException {
	        socket = new DatagramSocket();
	        address = InetAddress.getByName("52.34.210.130");
	    }
	 
	    public String sendEcho(String msg) throws IOException {
	        buf = msg.getBytes();
	        
	        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 5000);
	        
	        socket.send(packet);
	        
	        buf = new byte[256];
	        
	        packet = new DatagramPacket(buf, buf.length);
	        
	        socket.receive(packet);
	        
	        String received = new String(packet.getData(), 0, packet.getLength());
	        
	        return received;
	    }
	 
	    public void close() {
	        socket.close();
	    }
	}
	
	static class CreatedGameObject {
		public int PlayerID = -1;
		public int RegionID = -1;
	}
	
	static String createJoinRequest(String Name) {
		return Name;
	}
	
	public static void main(String[] args) throws Exception {
		KeyPair pair = EncryptionHandler.buildKeyPair();
		
		System.out.println(pair.getPublic().toString());
		System.out.println(pair.getPrivate().toString());
		
//		// sign the message
//        byte [] signed = encrypt(privateKey, "This is a secret message");     
//        System.out.println(new String(signed));  // <<signed message>>
//        
//        // verify the message
//        byte[] verified = decrypt(pubKey, encrypted);                                 
//        System.out.println(new String(verified)); 
//		CreatedGameObject Players[] = new CreatedGameObject[100];
//		
//		for(int x=0; x<100; x++) {
//			EchoClient Client = new EchoClient();
//		
//			return json.toString();
//			
//			String MessageBack = Client.sendEcho(createJoinRequest(Integer.toString(x)));
//			
//			JSONObject reception = new JSONObject(MessageBack);
//			
//			if(!reception.getBoolean("bSuccess")) {
//				System.out.println("Failed to register");
//				continue;
//			}
//			
//			// Remember player info
//			CreatedGameObject Object = new CreatedGameObject();
//			Object.PlayerID = reception.getInt("PlayerID");
//			Object.RegionID = reception.getInt("RegionID");
//			Players[x] = Object;
//			
//			System.out.println("I am: " + Object.PlayerID + " in region: " + Object.RegionID);
//    	}
    }
}
