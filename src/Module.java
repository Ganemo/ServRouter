
public class Module {

    public static void main(String[] args) {
    	System.out.println("Starting Server");
    	
    	RoutingServer Server = new RoutingServer();
    	
    	System.out.println("Starting Listener");
    	try {
    		UDPSocketListener Listener = new UDPSocketListener(Server);
    	} catch (Exception e) {
    		System.out.println("Listener Failed");
    		e.printStackTrace();
    	}
    	System.out.println("Listener Success!");
    }
}
