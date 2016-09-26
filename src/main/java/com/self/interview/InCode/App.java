package com.self.interview.InCode;

import com.self.interview.ControlSystemImpl;
import com.self.interview.RequestListener;


public class App {
	
	
    public static void main( String[] args ) {
    	
    	if(args.length != 2)
    		throw new IllegalArgumentException("\nUsage java -jar InCode-1.0.0-SNAPSHOT.jar <noOfElevators> <noOfFloors>");
    	
    	String noOfElevators = args[0];
    	String noOfFloors = args[1];
    	
    	if(Integer.parseInt(noOfElevators) != 1)
    		throw new IllegalArgumentException("Current simulation only works for a single elevator");
    	
    	
    	ControlSystemImpl cs = new ControlSystemImpl(Integer.parseInt(noOfElevators), Integer.parseInt(noOfFloors));
    	cs.operate();
    	
    	Thread requestListenerThread = new Thread(new RequestListener(cs), "RequestListenerThread");
    	requestListenerThread.start();
    }
}
