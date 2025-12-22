# 🥤 Vending Machine – Low Level Design (LLD Practice)

## 📌 Problem Statement (In Short)

Design a **Vending Machine system** that supports:

- Displaying available items
- Accepting coins / notes
- Item selection
- Dispensing items
- Returning change
- Handling insufficient money and out-of-stock cases

> ⚠️ This is **LLD practice code**.  
> Databases, REST APIs, and hardware-level integrations are intentionally skipped to focus on **object modeling and design clarity**.

---

## 🧠 One-Glance Feature Summary

| Feature | Status |
|------|------|
| Display Items | ✅ |
| Item Selection | ✅ |
| Accept Money | ✅ |
| Dispense Item | ✅ |
| Change Calculation | ✅ |
| Inventory Management | ✅ |
| State-Based Flow | ✅ |
| In-Memory Storage | ✅ |
| Persistence / Database | ❌ (Out of scope) |
| Hardware Integration | ❌ (Out of scope) |

---

## 🏗️ High-Level Design Overview

The Vending Machine is designed using **state-driven behavior** and clean OOP principles.

The system is divided into:

- **Entities** → Items, inventory, money
- **States** → Machine behavior based on current state
- **Services** → Orchestrate user actions
- **Strategies** → Change calculation logic

This design ensures **clear flow control** and prevents invalid operations.

---

## 📦 Core Domain Entities (Simple Explanation)

| Entity | Responsibility |
|-----|--------------|
| VendingMachine | Central controller of the machine |
| Item | Represents a product (name, price) |
| Inventory | Manages item stock |
| Coin / Note | Represents money inserted |
| Balance | Tracks inserted amount |
| Change | Represents money returned |

---

## 🧩 Design Patterns Used

### ✅ State Pattern (Core of the Design)
- Machine behavior changes based on current state:
  - Idle
  - Money Inserted
  - Item Selected
  - Dispensing
- Prevents invalid actions (e.g., selecting item before inserting money)

### ✅ Strategy Pattern
- Used for **change calculation**.
- Allows different algorithms without changing core logic.

### ✅ Singleton Pattern (Optional / Lightweight)
- Single instance of `VendingMachine` represents one physical machine.
- Simplifies state and inventory management.

---

## 📐 UML Diagram (Conceptual)

```

VendingMachine
│
├── has ──> Inventory ──> Item
│
├── uses ──> State
│ ├── IdleState
│ ├── MoneyInsertedState
│ ├── SelectionState
│ └── DispenseState
│
└── uses ──> ChangeStrategy

```