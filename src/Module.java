
public class Module {

    public static void main(String[] args) {
    	RoutingServer Server = new RoutingServer();
    	
    	try {
    	UDPSocketListener Listener = new UDPSocketListener(Server);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
