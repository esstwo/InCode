package com.self.interview.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.self.interview.enums.ElevatorDirection;
import com.self.interview.enums.ElevatorState;

public class ElevatorTest {
	
	Elevator elevator;

	@Before
	public void setUp() throws Exception {
		elevator = new Elevator(1,1,ElevatorState.IDLE, ElevatorDirection.GOING_UP, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddingToUpQueue() {
		assertEquals(0, elevator.getUpRequestQueue().size());
		elevator.addToUpQueue(8);
		elevator.addToUpQueue(5);
		elevator.addToUpQueue(3);
		elevator.addToUpQueue(10);
		//validating size
		assertEquals(4, elevator.getUpRequestQueue().size());
		assertEquals(0, elevator.getDownRequestQueue().size());
		//validating sorting
		assertEquals((Integer) 3, elevator.getUpRequestQueue().first());
		assertEquals((Integer) 10, elevator.getUpRequestQueue().last());
	}
	
	@Test
	public void testAddingToDownQueue() {
		assertEquals(0, elevator.getDownRequestQueue().size());
		elevator.addToDownQueue(6);
		elevator.addToDownQueue(4);
		elevator.addToDownQueue(1);
		elevator.addToDownQueue(9);
		//validating size
		assertEquals(4, elevator.getDownRequestQueue().size());
		assertEquals(0, elevator.getUpRequestQueue().size());
		//validating sorting
		assertEquals((Integer) 1, elevator.getDownRequestQueue().first());
		assertEquals((Integer) 9, elevator.getDownRequestQueue().last());
	}
	
	@Test
	public void testGoUp() {
		Integer currentFloor = elevator.getCurrentFloor();
		elevator.goUp();
		elevator.goUp();
		elevator.goUp();
		Integer expectedFloor = currentFloor + 3;
		assertEquals(expectedFloor, elevator.getCurrentFloor());
	}
	
	@Test
	public void testAddToQueue() {
		Integer currentFloor = elevator.getCurrentFloor();
		Integer upSize = elevator.getUpRequestQueue().size();
		Integer downSize = elevator.getDownRequestQueue().size();
		elevator.addToQueue(5);
		
		if(5 > currentFloor)
			assertEquals(upSize + 1, elevator.getUpRequestQueue().size());
		else if(5 < currentFloor)
			assertEquals(downSize + 1, elevator.getDownRequestQueue().size());
		else {
			assertEquals(upSize, (Integer) elevator.getUpRequestQueue().size());
			assertEquals(downSize, (Integer) elevator.getDownRequestQueue().size());
			
		}

	}
	
	
	
	

}
