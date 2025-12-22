# 🚗 Parking Lot – Low Level Design (LLD Practice)

## 📌 Problem Statement (In Short)

Design a **Parking Lot system** that supports:

- Multiple parking floors
- Different types of vehicles
- Parking and un-parking vehicles
- Parking ticket generation
- Parking fee calculation

> ⚠️ This is **LLD practice code**.  
> Databases, REST APIs, and scalability concerns are intentionally skipped to focus on **object modeling and design clarity**.

---

## 🧠 One-Glance Feature Summary

| Feature | Status |
|------|------|
| Multiple Parking Floors | ✅ |
| Multiple Vehicle Types | ✅ |
| Parking Spot Allocation | ✅ |
| Entry & Exit Flow | ✅ |
| Ticket Generation | ✅ |
| Fee Calculation | ✅ |
| Pluggable Parking Strategy | ✅ |
| In-Memory Storage | ✅ |
| Database / Persistence | ❌ (Out of scope) |
| Concurrency Handling | ❌ (Not required for LLD) |

---

## 🏗️ High-Level Design Overview

The Parking Lot system is designed using **clean OOP principles** and focuses on:

- Clear separation of responsibilities
- Pluggable strategies for parking and pricing
- Extensibility for future requirements

The design is divided into:

- **Entities** → Core parking objects
- **Services** → Entry/Exit orchestration
- **Strategies** → Parking allocation & fee calculation
- **Repositories** → In-memory state management

---

## 📦 Core Domain Entities (Simple Explanation)

| Entity | Responsibility |
|-----|--------------|
| ParkingLotService | Central coordinator for parking operations |
| ParkingFloor | Represents a floor in the parking lot |
| ParkingSpot | Represents an individual parking spot |
| Vehicle | Represents a vehicle entering the parking lot |
| ParkingTicket | Stores parking entry details |
| Fee | Represents calculated parking fee |

---

## 🧩 Design Patterns Used

### ✅ Singleton Pattern
- `ParkingLotService` is implemented as a Singleton.
- Ensures a single logical Parking Lot system managing all floors and tickets.

### ✅ Strategy Pattern
Used in two places:

#### Parking Strategy
- Determines how a parking spot is selected.
- Example: Nearest available spot.
- Easily extensible for other strategies.

#### Fee Strategy
- Determines how parking fees are calculated.
- Example: Flat rate pricing.
- Can be extended for hourly or dynamic pricing.

### ✅ Repository Pattern (Lightweight)
- Parking state and tickets are stored in in-memory collections.
- Storage logic is abstracted for easy replacement.

---

## 📐 UML Diagram (Conceptual)
ParkingLotService
│
├── manages ──> ParkingFloor
│ │
│ └── contains ──> ParkingSpot
│
├── issues ──> ParkingTicket ──> Vehicle
│
└── uses ──> ParkingStrategy
FeeStrategy

