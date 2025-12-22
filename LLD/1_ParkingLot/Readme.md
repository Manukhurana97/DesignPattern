<h1>Parking Lot</h1>
<hr/>

<h2>ProblemStatement</h2>
<p>
	Design and implement a parking lot system where uses come and park, unpark his vehicle. During entry, we'll generate a ticket (hardcopy/virtual) and at the time of exit based on the hours and vehicle type we will calculate the total undue amount. Our prking lot has multiple floors and multiple spots of different types and user can add the floor and spot in future.
</p>
<hr/>

<h2>Requirements</h2>
<p>
	<ul>
		<li>Multiple floors</li>
		<li>Parking spot</li>
		<li>Vehicle type</li>
		<li>Ticking system</li>
		<li>Fee calculator</li>
		<li>Spot Allocation</li>
		<li>Extendability</li>
	</ul>
</p>
<hr/>

<h2>Core Entity</h2>
<p>
	<ul>
		<li><b>ParkingLot</b>: Main class managing everything(floor, spot, entry, exit).</li>
		<li><b>ParkingFloor</b>: Represent a single floor in parking lot and manager all the spots</li>
		<li><b>ParkingSpot</b>: Represent the parling spot, vehicleType, park and unpark.</li>
		<li><b>Ticket</b></li>: Represent a parking ticket issued to user.
		<li><b>VehileType</b></li>: small, medium, large
		<li><b>Fee calculator</b>: classes for calculating the amount based on hours and type.</li>
	</ul>
</p>
<hr/>



```

                          +----------------------+
                          |   ParkingLotService  |
                          |----------------------|
                          | - instance           |
                          | - parkingStrategy    |
                          | - feeStrategy        |
                          | - floors             |
                          | - tickets            |
                          |----------------------|
                          | + getInstance()      |
                          | + entry(vehicle)     |
                          | + exit(vehicle)      |
                          | + showAvailability() |
                          +----------+-----------+
                                     |
          uses (Strategy Pattern)     |
        +----------------------------+-----------------------------+
        |                                                            |
+---------------------+                               +---------------------+
|   ParkingStrategy   |                               |    FeeStrategy      |
| (interface)         |                               |  (interface)        |
|---------------------|                               |---------------------|
| + findParkingSpot() |                               | + calculateFee()    |
+----------+----------+                               +----------+----------+
           |                                                             |
+---------------------+                               +---------------------+
|  NearestParking     |                               |    FlatRate         |
|---------------------|                               |---------------------|
| + findParkingSpot() |                               | + calculateFee()    |
+---------------------+                               +---------------------+

       has-a (composition)
+---------------------+
|    ParkingFloor     |
|---------------------|
| - floorNumber       |
| - parkingSpots      |
|---------------------|
| + addSpot()         |
| + getAvailableSpot()|
+----------+----------+
           |
           | contains
+---------------------+
|    ParkingSpot      |
|---------------------|
| - spotId            |
| - vehicleType       |
| - isOccupied        |
|---------------------|
| + parkVehicle()     |
| + unparkVehicle()   |
+----------+----------+
           |
           | creates
+---------------------+
|   ParkingTicket     |
|---------------------|
| - vehicle           |
| - spot              |
| - entryTime         |
|---------------------+
           ^
           |
+---------------------+
|      Vehicle        |
|---------------------|
| - vehicleNumber     |
| - vehicleType       |
+---------------------+


Design Pattern used:
1. Singleton
2. Strategy : (public interface ParkingStategy )
```