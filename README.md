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

####Populating Lists from Outside
This is pretty simple. The requests that come from outside also come with a direction note attached to it. This is similar to someone pressing the **UP** and **DOWN** buttons on a floor. Depending on that direction note, the floor is added to the appropriate list.

####Populating Lists from Inside the Elevator
This is also straightforward. 
The requests for floors below the `currentFloor` are added to the `downRequestQueue`
The requests for floors above the `currentFloor` are added to the `upRequestQueue`
