🅿️ Parking Lot System

A simulation of a multi-floor parking lot system supporting cars, bikes, and trucks. The system handles vehicle entry/exit, assigns and frees up parking slots, tracks real-time availability, and calculates parking fees.

🚀 Requirements

Parking lot can have multiple floors, and each floor can have n number of slots.

Each slot supports a specific vehicle type (Bike, Car, Truck).

On vehicle entry, the system assigns a slot. On exit, the slot is released.

The system tracks real-time availability and provides it to customers.

Supports multiple entries/exits and concurrent access.

⚙️ Subsystems
1️⃣ Vehicle

Car → Medium slot

Bike → Small slot

Truck → Large slot

2️⃣ Parking Strategy

Decides which slot to assign when multiple options exist:

NearestFirst → Assigns the first available slot from the nearest floor.

BestFit → Assigns the smallest possible slot that can fit the vehicle.

LeastFirst → Assigns a slot from the floor with the most available spots. (extendable)

3️⃣ Fee Structure

Decides how fees are calculated:

FlatBased → Fixed fee per hour per vehicle type.

VehicleBased → Fee varies based on vehicle type (Car, Bike, Truck). (extendable)

🧩 Core Components

ParkingFloor → Represents each floor with multiple slots.

ParkingSpot → Represents an individual parking spot.

ParkingTicket → Issued on entry, tracks vehicle, spot, entry/exit time.

ParkingLot → Central controller for the entire system (Singleton).



classDiagram
class ParkingLot {
- List<ParkingFloor> parkingFloors
- Map<String, ParkingTicket> parkingTickets
- FeeStrategy feeStrategy
- ParkingStrategy parkingStrategy
+ static getInstance()
+ setFeeStrategy(FeeStrategy)
+ setParkingStrategy(ParkingStrategy)
+ addFloor(ParkingFloor)
+ entry(Vehicle) ParkingTicket
+ exit(Vehicle) double
+ showAvailability()
}

    class ParkingFloor {
        - int floorNumber
        - Map<String, ParkingSpot> spots
        + addSpot(ParkingSpot)
        + getSpot(Vehicle) Optional<ParkingSpot>
        + displayAvailability()
    }

    class ParkingSpot {
        - String spotNumber
        - boolean isOccupied
        - Vehicle parkedVehicle
        - VehicleType spotType
        + parkVehicle(Vehicle)
        + unParkVehicle(Vehicle)
        + isSpotAvailable() boolean
        + canParkCar(Vehicle) boolean
    }

    class ParkingTicket {
        - String ticketNumber
        - long entryTime
        - long exitTime
        - Vehicle vehicle
        - ParkingSpot spot
        + setExitTime()
    }

    class Vehicle {
        <<abstract>>
        - String vehicleNumber
        - VehicleType type
        + getVehicleNumber()
        + getType()
    }
    class Car
    class Bike
    class Truck

    class VehicleType {
        <<enum>>
        SMALL
        MEDIUM
        LARGE
    }

    class FeeStrategy {
        <<interface>>
        + calculateFee(ParkingTicket) double
    }
    class FlatRateStrategy
    class VehicleBasedFeeStrategy

    class ParkingStrategy {
        <<interface>>
        + findParkingSpot(List<ParkingFloor>, Vehicle) Optional<ParkingSpot>
    }
    class NearestParking
    class BestFit
    class LeastFirst

    ParkingLot --> ParkingFloor
    ParkingFloor --> ParkingSpot
    ParkingSpot --> Vehicle
    ParkingSpot --> VehicleType
    ParkingLot --> ParkingTicket
    ParkingTicket --> Vehicle
    ParkingTicket --> ParkingSpot
    Vehicle <|-- Car
    Vehicle <|-- Bike
    Vehicle <|-- Truck
    FeeStrategy <|.. FlatRateStrategy
    FeeStrategy <|.. VehicleBasedFeeStrategy
    ParkingStrategy <|.. NearestParking
    ParkingStrategy <|.. BestFit
    ParkingStrategy <|.. LeastFirst


