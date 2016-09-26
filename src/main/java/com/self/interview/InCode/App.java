package com.self.interview.InCode;

import com.self.interview.ControlSystem;
import com.self.interview.ControlSystemImpl;
import com.self.interview.RequestListener;
import com.self.interview.RequestProcessor;
import com.self.interview.enums.RequestEnum;

/**
 * Hello world!
 *
 */
public class App {
	
	
    public static void main( String[] args ) {
    	String noOfElevators = args[0];
    	String noOfFloors = args[1];
    	
    	ControlSystem cs = new ControlSystemImpl(Integer.parseInt(noOfElevators), Integer.parseInt(noOfFloors));
    	cs.requestElevator(RequestEnum.REQUEST_UP, 5);
    	cs.requestElevator(RequestEnum.REQUEST_UP, 3);
    	cs.requestElevator(RequestEnum.REQUEST_DOWN, 7);
    	
    	//Thread requestListenerThread = new Thread(new RequestListener(cs), "RequestListenerThread");
    	//requestListenerThread.start();
    	
    	Thread requestProcessorThread = new Thread(new RequestProcessor(cs), "RequestProcessorThread");
    	requestProcessorThread.start();
    }
}
