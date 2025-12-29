# 🏧 ATM System – Low Level Design (LLD)

## 1️⃣ Problem Statement

Design an **ATM System** that allows users to:

- Insert a card
- Authenticate using a PIN
- Perform basic banking operations (e.g. balance check, cash withdrawal)
- Dispense cash using available denominations
- Handle invalid PIN attempts and card blocking
- Safely handle concurrent access and failures

The goal is to model a **real-world ATM workflow** using clean object-oriented design
and appropriate design patterns.

> ⚠️ This is **LLD practice code**, not a production banking system.  
> Focus is on **design clarity, state flow, and extensibility**.

---

## 2️⃣ Requirements

### Functional Requirements
- Validate card and PIN
- Block card after **3 invalid PIN attempts**
- Allow cash withdrawal if sufficient balance exists
- Dispense cash using available denominations (100, 500, etc.)
- Handle insufficient balance and invalid operations

### Non-Functional Requirements
- Thread-safe account operations
- Extensible cash dispensing logic
- Clear separation of responsibilities
- In-memory data storage (no database)

---

## 3️⃣ Core Entities

| Entity | Responsibility |
|-----|--------------|
| **ATM** | Orchestrates user interaction flow |
| **ATMService** | Handles ATM-level operations |
| **Account** | Maintains user balance |
| **Card** | Represents ATM card and PIN state |
| **User** | Represents account holder |
| **Transaction** | Represents a banking operation |
| **CashDispenser** | Dispenses cash using denominations |
| **DenominationDispenser** | Handles individual note types (100, 500) |

---

## 4️⃣ Design Patterns Used

### ✅ Singleton Pattern
Used for:
- `ATMService`
- `AccountService`

Ensures a single shared instance and centralized control.

---

### ✅ Chain of Responsibility Pattern (Cash Dispensing)

Cash dispensing is modeled using **Chain of Responsibility**:

- Each denomination handler (500, 100, etc.) tries to dispense as much as possible
- Remaining amount is passed to the next handler
- Makes it easy to add new denominations without changing existing code

**Example**  
For amount `9300`:
- 500 × 18 = 9000
- 100 × 3 = 300

---

### ✅ State-Driven Workflow (Implicit)

ATM flow follows a clear sequence:
- Card Inserted
- PIN Validation
- Operation Selection
- Transaction Processing
- Card Ejection

State transitions are enforced through service logic.

---

## 5️⃣ Thread Safety & Concurrency

- Account withdrawal operations are synchronized
- Shared data structures use thread-safe collections (`ConcurrentHashMap`)
- Singleton services ensure consistent access points

> This ensures **atomic balance updates** and prevents race conditions.

---

## 6️⃣ UML Diagram (Conceptual)
+------------------+
|       ATM        |
+------------------+
| - atmService     |
| - cashDispenser  |
+------------------+
| + insertCard()   |
| + enterPin()     |
| + withdraw()    |
| + ejectCard()   |
+--------+---------+
         |
         | uses
         v
+------------------+
|   ATMService     |
+------------------+
| + validatePin()  |
| + withdrawCash() |
+--------+---------+
         |
         | operates on
         v
+------------------+
|     Account      |
+------------------+
| - balance        |
+------------------+
| + withdraw()    |
| + getBalance()  |
+------------------+

+------------------+
|      Card        |
+------------------+
| - pin            |
| - retryCount     |
| - blocked        |
+------------------+
| + validatePin()  |
| + blockCard()    |
+------------------+

+---------------------------+
|     CashDispenser         |
+---------------------------+
| + dispense(amount)        |
+-------------+-------------+
              |
      -------------------------
      |                       |
+---------------+     +---------------+
| 500Dispenser  |     | 100Dispenser  |
+---------------+     +---------------+
| dispense()    |     | dispense()    |
+---------------+     +---------------+


