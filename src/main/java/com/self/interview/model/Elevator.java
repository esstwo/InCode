package com.self.interview.model;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import com.self.interview.enums.ElevatorDirection;
import com.self.interview.enums.ElevatorState;

public class Elevator {

	private int id;
	private ElevatorState state;
	private ElevatorDirection direction = ElevatorDirection.GOING_UP;
	private Integer currentFloor = 1;
	private Integer totalFloors;
	
	public Integer getCurrentFloor() {
		return currentFloor;
	}

	private TreeSet<Integer> upRequestQueue = new TreeSet<>();
	public TreeSet<Integer> getUpRequestQueue() {
		return upRequestQueue;
	}

	public TreeSet<Integer> getDownRequestQueue() {
		return downRequestQueue;
	}

	private TreeSet<Integer> downRequestQueue = new TreeSet<>();

	
	public Elevator(int id, Integer currentFloor, ElevatorState state, ElevatorDirection direction, Integer totalFloors){
		this.id = id;
		this.currentFloor = currentFloor;
		this.state = state;
		this.direction = direction;
		this.totalFloors = totalFloors;
	}

	public ElevatorState getState() {
		return state;
	}

	public void setState(ElevatorState state) {
		this.state = state;
	}

	public ElevatorDirection getDirection() {
		return direction;
	}

	public void setDirection(ElevatorDirection direction) {
		this.direction = direction;
	}

	/**
	 * Algorithm to get the next stop based on the direction
	 * @return
	 */
	private Integer getNextStop() {
		//if going up, always pick the first entry from the up queue. unless it is empty. 
		if(ElevatorDirection.GOING_UP.equals(getDirection())) {
			if(! upRequestQueue.isEmpty()) {
				Integer nextHighestDestination = upRequestQueue.ceiling(this.currentFloor);
				if (nextHighestDestination != null) {
					return nextHighestDestination;
				} else {
					if(! downRequestQueue.isEmpty()) {
						this.direction = ElevatorDirection.GOING_DOWN;
						return downRequestQueue.last();
					} else {
						return upRequestQueue.first();
					}
				}					
			} else {
				if(!downRequestQueue.isEmpty()) {
					this.direction = ElevatorDirection.GOING_DOWN;
					return downRequestQueue.last();
				} else {
					this.direction = ElevatorDirection.STOPPED;
					return null;
				}
			}
		} 
		if (ElevatorDirection.GOING_DOWN.equals(getDirection())) {
			if(! downRequestQueue.isEmpty()) {
				Integer nextLowestDestination = downRequestQueue.floor(this.currentFloor);
				if(nextLowestDestination != null) {
					return nextLowestDestination;
				} else {
					if(! upRequestQueue.isEmpty()) {
						this.direction = ElevatorDirection.GOING_UP;
						return upRequestQueue.first();
					} else {
						return downRequestQueue.last();
					}
				}
			} else {
				if(! upRequestQueue.isEmpty()) {
					this.direction = ElevatorDirection.GOING_UP;
					return upRequestQueue.first();
				} else {
					this.direction = ElevatorDirection.STOPPED;
					return null;
				}
			}

		} 
		
		if (ElevatorDirection.STOPPED.equals(getDirection())){
			//do nothing for now
		}
		return null;
	}

	/**
	 * Removes the floor from the queue.
	 */
	private void removeCurrentStopFromQueue() {
		if(ElevatorDirection.GOING_UP.equals(getDirection())) {
			upRequestQueue.remove(currentFloor);
		} else if (ElevatorDirection.GOING_DOWN.equals(getDirection())) {
			downRequestQueue.remove(currentFloor);
		} 
	}



	public void operate() throws InterruptedException {
		while(getNextStop() != null) {
			while(this.currentFloor != getNextStop()) {
				Integer nextFloor = getNextStop();
				System.out.println("Current floor: " + currentFloor + " Next stop: " + nextFloor);
				if(ElevatorDirection.GOING_UP.equals(direction) && currentFloor < nextFloor) {
					goUp();
				} else if (ElevatorDirection.GOING_UP.equals(direction) && currentFloor > nextFloor) {
					int diff = currentFloor - nextFloor;
					for(int i=0; i<diff;i++)
						goDown();

				} else if(ElevatorDirection.GOING_DOWN.equals(direction) && currentFloor > nextFloor) {
					goDown();
				} else if(ElevatorDirection.GOING_DOWN.equals(direction) && currentFloor < nextFloor) {
					int diff = nextFloor - currentFloor;
					for(int i=0; i<diff;i++)
						goUp();
				}
			}
			removeCurrentStopFromQueue(); 
			openDoor();
			Thread.sleep(5000); //giving users 5 secs to get out of the elevator :)
			closeDoor(); 
		}

		this.direction = ElevatorDirection.STOPPED; 
		this.state = ElevatorState.IDLE;
	}

	/**
	 * Just for simulation purposes
	 */
	private void openDoor() {
		System.out.println("******Stopped on floor: " + currentFloor + " ******");
		this.state = ElevatorState.STOPPED_ON_FLOOR;
	}

	/**
	 * Just for simulation purposes
	 */
	private void closeDoor() {
		System.out.println("Closing elevator door and ready to move again");
		this.state = ElevatorState.ACTIVE;
	}

	/**
	 * Adds to the up queue, if someone requests an elevator to go up (from outside)
	 * @param floor
	 */
	public void addToUpQueue(Integer floor) {
		System.out.println("Button pressed on Floor: " + floor + " to go in the up direction");
		System.out.println("Adding it to the queue for elevator: " + id);
		upRequestQueue.add(floor);
		if(ElevatorDirection.STOPPED.equals(this.direction))
			this.direction = ElevatorDirection.GOING_UP;
	}

	/**
	 * Adds to the down queue, if someone requests an elevator to go down (from outside)
	 * @param floor
	 */
	public void addToDownQueue(Integer floor) {
		System.out.println("Button pressed on Floor: " + floor + " to go in the down direction");
		System.out.println("Adding it to the queue for elevator: " + id);
		downRequestQueue.add(floor);
		if(ElevatorDirection.STOPPED.equals(this.direction))
			this.direction = ElevatorDirection.GOING_DOWN;
	}

	/**
	 * Adds to the appropriate queue when a button is pressed from the inside
	 * 
	 * @param floor
	 */
	public void addToQueue(Integer floor) {
		System.out.println("Inside the elevator:" + id + " , someone requested to go to the floor: " + floor);
		if(floor < this.currentFloor) {
			downRequestQueue.add(floor);
		}
		if(floor > this.currentFloor) {
			upRequestQueue.add(floor);
		}
	}


	/**
	 * Go up one floor at a time
	 * Adding a time of 1 sec to traverse each floor. (for readability of output purposes only)
	 */
	public void goUp() {
		currentFloor++;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Elevator id: " + id + " Current Floor: " + currentFloor);
	}

	/**
	 * Go down one floor at a time
	 * Adding a time of 1 sec to traverse each floor. (for readability of output purposes only)
	 */
	public void goDown() {
		currentFloor--;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Elevator id: " + id + " Current Floor: " + currentFloor);
	}
}
