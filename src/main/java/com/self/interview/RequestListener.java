package com.self.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.self.interview.enums.RequestEnum;

public class RequestListener implements Runnable {
	
	private ControlSystem csi;
	
	public RequestListener(ControlSystem csi) {
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
                String floor = split[0];
                String direction = split[1];
                
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