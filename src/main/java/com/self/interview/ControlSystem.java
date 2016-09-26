package com.self.interview;

import com.self.interview.enums.RequestEnum;

public interface ControlSystem {
	
	public void requestElevator(RequestEnum request, Integer floor);
	
	public void assignElevatorToRequest();
	
	public void operate();
	
	

}
