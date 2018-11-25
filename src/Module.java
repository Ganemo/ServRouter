
public class Module {

	static UDPSocketListener Listener;
	static RoutingServer Server;
	
	static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
	}
	
    public static void main(String[] args) {
    	
    	// Make sure inputs are valid
    	if(args.length != 1 || !isNumeric(args[0])) {
    		System.out.println("Need one input (port)");
    		return;
    	}
    	
    	// Add shutdown
    	Runtime.getRuntime().addShutdownHook(new Thread()
    	{
    	    @Override
    	    public void run()
    	    {
    	    	Listener.stop();
    	    	Server.KickAllPlayers();
    	    }
    	});
    	
    	System.out.println("Starting Server");
    	Server = new RoutingServer();
    	
    	try {
    		System.out.println("Starting Listener at port: " + args[0]);
    		Listener = new UDPSocketListener(Server, args[0]);
    		Listener.start();
    	} catch (Exception e) {
    		System.out.println("Listener Failed");
    		e.printStackTrace();
    	}
    }
}
