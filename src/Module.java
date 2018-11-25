
public class Module {

	static UDPSocketListener Listener;
	static RoutingServer Server;
	
//    public static void main(String[] args) {
//    	
//    	Runtime.getRuntime().addShutdownHook(new Thread()
//    	{
//    	    @Override
//    	    public void run()
//    	    {
//    	    	Listener.stop();
//    	    	Server.KickAllPlayers();
//    	    }
//    	});
//    	
//    	System.out.println("Starting Server");
//    	Server = new RoutingServer();
//    	
//    	try {
//    		System.out.println("Starting Listener");
//    		Listener = new UDPSocketListener(Server);
//    		Listener.start();
//    	} catch (Exception e) {
//    		System.out.println("Listener Failed");
//    		e.printStackTrace();
//    	}
//    }
}
