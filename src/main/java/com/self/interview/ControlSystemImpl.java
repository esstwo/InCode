package com.self.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.self.interview.enums.ElevatorDirection;
import com.self.interview.enums.ElevatorState;
import com.self.interview.enums.RequestEnum;
import com.self.interview.model.Elevator;

public class ControlSystemImpl implements ControlSystem {
	
	private volatile TreeSet<Integer> outsideUpRequestQueue = new TreeSet<>();
	private volatile TreeSet<Integer> outsideDownRequestQueue = new TreeSet<>();

	int noOfElevators = 1;
	int noOfFloors = 10;
	List<Elevator> elevators;
		
	public ControlSystemImpl(int noOfElevators, int noOfFloors) {
		this.noOfElevators = noOfElevators;
		this.noOfFloors = noOfFloors;
		initializeElevators();
	}

	public void initializeElevators() {
		elevators = new ArrayList<Elevator>();
		for(int i =0; i <noOfElevators; i++) {
			elevators.add(new Elevator(i, 1, ElevatorState.IDLE, ElevatorDirection.STOPPED, noOfFloors));
		}
	}
	
	@Override
	public void operate() {
		Thread startControlSystem = new Thread(new Runnable() {
            @Override
            public void run() {
            	while (true) {
        			assignElevatorToRequest();
        			for(Elevator elevator : elevators)
        				try {
        					elevator.operate();
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        		}	
            }
		});
		
		startControlSystem.start();
	}

	@Override
	public void requestElevator(RequestEnum request, Integer floor) {
		if(RequestEnum.REQUEST_UP.equals(request))
			outsideUpRequestQueue.add(floor);
		if(RequestEnum.REQUEST_DOWN.equals(request))
			outsideDownRequestQueue.add(floor);
	}

	
	/**
	 * In a single elevator system, it will just add it to the 0th elevator.
	 * In a multiple elevator system, this will be modified with logic to find the nearest elevator to add the request to.
	 */
	@Override
	public void assignElevatorToRequest() {
		//for a single elevator system, we can just add it to its up/down queue
		while(! outsideDownRequestQueue.isEmpty()) {
			elevators.get(0).addToDownQueue(outsideDownRequestQueue.pollFirst());
		}
		while(! outsideUpRequestQueue.isEmpty()) {
			elevators.get(0).addToUpQueue(outsideUpRequestQueue.pollFirst());
		}
	}
}
