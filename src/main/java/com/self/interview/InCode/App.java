package com.self.interview.InCode;

import com.self.interview.ControlSystem;
import com.self.interview.ControlSystemImpl;
import com.self.interview.RequestListener;


public class App {
	
	
    public static void main( String[] args ) {
    	
    	if(args.length != 2)
    		throw new IllegalArgumentException("need to pass 2 arguments, no. of elevators and total floors");
    	
    	String noOfElevators = args[0];
    	String noOfFloors = args[1];
    	
    	ControlSystem cs = new ControlSystemImpl(Integer.parseInt(noOfElevators), Integer.parseInt(noOfFloors));
    	cs.operate();
    	
    	Thread requestListenerThread = new Thread(new RequestListener(cs), "RequestListenerThread");
    	requestListenerThread.start();
    }
}
