# InCode - Elevator Simulator Problem
This repository is used to create a simulation for an elevator. 

## Main Classes

* Elevator.java
* ControllerSystem.java

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

**ALGORITH**
If the elevator is going up (`ElevatorDirection.GOING_UP`) and the `upRequestQueue`is not empty, then return the lowest element in the queue which is greater than the `currentElement`
If no such element exists or the `upRequestQueue` is empty, then change the direction and return the highest element in the `downRequestQueue` 


If the elevator is going down (`ElevatorDirection.GOING_DOWN`) and the `downRequestQueue`is not empty, then return the highest element in the queue which is lower than the `currentElement`
If no such element exists or the `downRequestQueue` is empty, then change the direction and return the lowest element in the `upRequestQueue` 

If both the queues are empty, then change the direction to `ElevatorDirection.STOPPED` and return null






