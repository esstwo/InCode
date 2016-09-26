package com.self.interview;

public class RequestProcessor implements Runnable {
	
	private ControlSystem csi;
	
	public RequestProcessor(ControlSystem csi) {
		this.csi = csi;
	}
	

    @Override
    public void run() {
        while (true) {
            csi.operate();
        }
    }
}
