package com.self.interview.InCode;

import com.self.interview.ControlSystem;
import com.self.interview.ControlSystemImpl;
import com.self.interview.RequestListener;
import com.self.interview.enums.RequestEnum;

/**
 * Hello world!
 *
 */
public class App {
	
	
    public static void main( String[] args ) {
    	
    	if(args.length != 2)
    		throw new IllegalArgumentException("<exception message here");
    	
    	String noOfElevators = args[0];
    	String noOfFloors = args[1];
    	
    	ControlSystem cs = new ControlSystemImpl(Integer.parseInt(noOfElevators), Integer.parseInt(noOfFloors));
    	cs.operate();
    	
    	Thread requestListenerThread = new Thread(new RequestListener(cs), "RequestListenerThread");
    	requestListenerThread.start();
    }
}
