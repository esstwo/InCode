package com.self.interview.InCode;

import com.self.interview.ControlSystemImpl;
import com.self.interview.RequestListener;


public class App {
	
	
    public static void main( String[] args ) {
    	
    	if(args.length != 2)
    		throw new IllegalArgumentException("need to pass 2 arguments, no. of elevators and total floors");
    	
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
