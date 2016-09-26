# InCode - Elevator Simulator Problem
This repository is used to create a simulation for an elevator. 

##Assumptions
1. Passenger object is ignored in this simulation. Any attributes such as weight and no. of passengers that can fit in an elevator are not considered.
2. Simulation only happens for a single elevator.
3. Ignoring some validation checks when the user enters the input. 
4. Some delay (1 second for each floor traversal and 5 seconds for a stop) is added during the elevator processing, only so that the simulation can be seen/read  

##Steps to execute
Download the source code from Git.
Assuming you have maven setup, run `mvn package`
Go to the target directory, and run `java -jar InCode-1.0.0-SNAPSHOT.jar <noOfElevators> <noOfFloors>`


## Main Classes

* Elevator.java
* ControlSystemImpl.java

###Elevator Class
The Elevator class is used as an instance of an elevator.
It consists of 2 queues reperesented in the form of a TreeSet. 
The `TreeSet` is used as a data-structure coz it keeps a sorted list of entries.

Entries in the `upRequestQueue` point to the requests moving upwards (from inside the elevator and outside)
Entries in the `downRequestQueue` point to the requests moving downwards (from inside the elevator and outside)

####Populating Lists from Outside - `addToUpQueue` and `addToDownQueue`
This is pretty simple. The requests that come from outside also come with a direction note attached to it. This is similar to someone pressing the **UP** and **DOWN** buttons on a floor. Depending on that direction note, the floor is added to the appropriate list.

####Populating Lists from Inside the Elevator - `addToQueue`
This is also straightforward. 
The requests for floors below the `currentFloor` are added to the `downRequestQueue`
The requests for floors above the `currentFloor` are added to the `upRequestQueue`

####Getting the Next Stop - `getNextStop`
This is the main algorithm that gets the next stop from the list.
The algorithm is based on the direction (`ElevatorDirection`) and the two lists (`upRequestQueue` and `downRequestQueue`)

**ALGORITHM**
If the elevator is going up (`ElevatorDirection.GOING_UP`) and the `upRequestQueue`is not empty, then return the lowest element in the queue which is greater than the `currentElement`
If no such element exists or the `upRequestQueue` is empty, then change the direction and return the highest element in the `downRequestQueue` 


If the elevator is going down (`ElevatorDirection.GOING_DOWN`) and the `downRequestQueue`is not empty, then return the highest element in the queue which is lower than the `currentElement`
If no such element exists or the `downRequestQueue` is empty, then change the direction and return the lowest element in the `upRequestQueue` 

If both the queues are empty, then change the direction to `ElevatorDirection.STOPPED` and return null

This results in an algorithm, which will fulfill all requests in one direction in order and then switch directions.
Let's take an example, 
In a 10 floor building, you are on floor 3 and get the following requests to go up - Floor 8, Floor 6, Floor 7, Floor 3 and following requests to go down - Floor 4, Floor 5.
Since these are stored in a `TreeSet`, and we are looking at the ceiling w.r.t the `currentFloor`, the order of execution will be Floor 6,7,8 and then reverse to go to floor 5 and floor 4 and then finally floor 3 to go up.

The algorithm may result in some starvation or delay in processing requests for top/bottom floors but since I am reversing directions every time i reach the end, it shouldn't be much of a delay.

####Operation - `operate`
This method determines whether to go up or go down or sit idle, depending on what the `direction` is and whats the next stop.


###ControlSystem
This class was written to handle requests that come from outside the elevator and direct them to an elevator.
In this simulation, we assume that there is only a single elevator but we can modify one of the methods and possibly get it to work with multiple elevators.

####Request Elevator - `requestElevator
This method is called from the outside, when someone is requesting an elevator. Depending on the input, all it does is store the input in the appropriate queue. 

####Assign Elevator - `assignElevatorToRequest`
In the current submission, this is just assigning all requests to elevator 0.
It will need modification to assign it to the nearest elevator in a multi-elevator scenario.



