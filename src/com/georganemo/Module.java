package com.georganemo;

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
    	if(args.length < 1 || !isNumeric(args[0])) {
    		System.out.println("Need one input (port)");
    		return;
    	}
    	
    	// Read in IPBasedID
    	if(args.length >= 2) {
    		if(args[1].equals("0") || args[1].equals("1")) {
    			Player.IPBasedID = args[1].equals("0") ? false : true;
    		} else {
    			System.out.println("IPBasedID should be 0 or 1");
    		}
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
