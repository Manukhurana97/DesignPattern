# ☕ Coffee Vending Machine – Low Level Design (LLD)

## 1️⃣ Problem Statement

Design a **Coffee Vending Machine** that allows users to:

- Select different types of coffee
- Add optional extra ingredients (condiments)
- Insert money
- Dispense coffee
- Handle out-of-stock scenarios gracefully

The system should model the **real-world behavior of a vending machine** using proper
object-oriented design and design patterns.

> ⚠️ This is **LLD practice code**, not a production system.  
> Focus is on **design, state management, and extensibility**, not infrastructure.

---

## 2️⃣ Requirements

### Functional Requirements
- Support multiple coffee types (Latte, Cappuccino, etc.)
- Allow extra ingredients (e.g. extra coffee beans, syrup, cream)
- Check ingredient availability before processing
- Accept money via predefined notes
- Dispense coffee when sufficient money is inserted
- Show **Out of Stock** when ingredients are insufficient

### Non-Functional Requirements
- Clean separation of responsibilities
- Extensible design (easy to add new coffee types or states)
- State-driven flow (prevent invalid operations)
- In-memory inventory management (no DB)

---

## 3️⃣ Core Entities

| Entity | Responsibility |
|-----|----------------|
| **VendingMachine** | Central orchestrator of user actions |
| **VendingMachineState** | Controls behavior based on current state |
| **SelectCoffeeState** | Handles coffee selection |
| **InsertMoneyState** | Handles money insertion |
| **DispenseState** | Handles coffee preparation and dispensing |
| **Coffee** | Represents a coffee type and preparation logic |
| **CoffeeFactory** | Creates coffee objects |
| **Inventory** | Manages ingredient stock |
| **Account** | Tracks inserted money |
| **Ingredient** | Enum representing ingredients |
| **Note** | Enum representing money denominations |

---

## 4️⃣ Design Patterns Used

### ✅ State Pattern
- Controls valid actions at each stage:
    - Selecting coffee
    - Inserting money
    - Dispensing coffee
- Prevents invalid operations (e.g. dispensing without payment)

### ✅ Factory Pattern
- `CoffeeFactory` creates different coffee objects
- Makes it easy to add new coffee types

### ✅ Singleton Pattern
- Used for:
    - `VendingMachine`
    - `Inventory`
    - `Account`
- Ensures single shared instance across the system

---

## 5️⃣ UML Diagram (Conceptual)

```
                 +------------------+
             |  VendingMachine  |
             +------------------+
             | - state          |
             | - inventory      |
             | - account        |
             | - selectedCoffee |
             +--------+---------+
                      |
                      | uses
                      v
          +------------------------+
          |  VendingMachineState   |
          +------------------------+
          | selectCoffee()         |
          | insertMoney()          |
          | dispenseCoffee()       |
          +-----------+------------+
                      |
    ---------------------------------------------
    |                   |                       |
    +------------------+ +------------------+ +------------------+
| SelectCoffeeState| | InsertMoneyState | | DispenseState |
+------------------+ +------------------+ +------------------+
                        +------------------+
                        |  CoffeeFactory   |
                        +------------------+
                        | getCoffee(type)  |
                        +--------+---------+
                                 |
                                 v
                             +--------+
                             | Coffee |
                             +--------+

                        +------------------+
                        |   Inventory      |
                        +------------------+
                        | addStock()       |
                        | consume()        |
                        | doWeHave()       |
                        +------------------+


