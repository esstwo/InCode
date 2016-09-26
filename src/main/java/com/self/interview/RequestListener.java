package com.self.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.self.interview.enums.RequestEnum;

public class RequestListener implements Runnable {
	
	private ControlSystemImpl csi;
	
	public RequestListener(ControlSystemImpl csi) {
		this.csi = csi;
	}

    @Override
    public void run() {
        while (true) {
            String floorAndDirection = null;
            try {
                // Read input from console
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(System.in));
                floorAndDirection = bufferedReader.readLine();
                String[] split = floorAndDirection.split("&");
                if(split.length < 2)
                	continue; //just ignore the input and continue.
                
                String floor = split[0];
                String direction = split[1];
                if(Integer.parseInt(floor) > csi.getNoOfFloors())
                	throw new IllegalArgumentException("Invalid Floor Number. Max. no. of floors: " + csi.getNoOfFloors());
                
                if("UP".equalsIgnoreCase(direction))
                	csi.requestElevator(RequestEnum.REQUEST_UP, Integer.parseInt(floor));
                if("DOWN".equalsIgnoreCase(direction))
                	csi.requestElevator(RequestEnum.REQUEST_DOWN, Integer.parseInt(floor));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}