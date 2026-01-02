# ❌⭕ Tic-Tac-Toe – Low Level Design (LLD)

## 1️⃣ Problem Statement

Design a **Tic-Tac-Toe game** that allows two players to:

- Play turn by turn
- Place their symbol on a board
- Detect a win or a draw
- End the game when conditions are met

The goal is to model the game using **clean object-oriented design**, focusing on
**separation of responsibilities and extensibility**, rather than UI or input handling.

> ⚠️ This is an **LLD learning exercise**, not a UI or algorithm-heavy implementation.

---

## 2️⃣ Requirements

### Functional Requirements
- Two players play alternately
- Each player has a symbol (X / O)
- Moves are validated
- Game detects:
  - Win
  - Draw
  - Ongoing state

### Non-Functional Requirements
- Clean separation of concerns
- Easy to extend (NxN board, AI player, strategies)
- Simple, readable code
- In-memory game state

---

## 3️⃣ Core Entities

| Entity | Responsibility |
|------|----------------|
| **Game** | Controls game flow and turns |
| **Board** | Maintains grid state |
| **Player** | Represents a player and their symbol |
| **Move** | Represents a player’s action |
| **Symbol** | Enum for X / O |
| **Cell** | Represents a board position (optional) |

---

## 4️⃣ Design Overview

The design follows a **controller-style flow**:

- `Game` orchestrates turns
- `Board` owns the grid and validates placements
- `Player` represents identity and symbol
- Win and draw conditions are checked after each move

This avoids:
- God classes
- Large conditional blocks
- Tight coupling between components

---

## 5️⃣ Design Principles Applied

### ✅ Single Responsibility Principle (SRP)
- Board handles board state
- Game handles turn management
- Player holds player-specific data

### ✅ Open–Closed Principle (Conceptual)
- Board size can be extended (NxN)
- Winning logic can be abstracted into strategies
- AI player can be added without changing core game flow

### ✅ Encapsulation
- Internal board representation is hidden
- Moves are validated through controlled methods

---

## 6️⃣ UML Diagram (Conceptual – Copy Friendly)

+------------------+
|       Game       |
+------------------+
| - board          |
| - players        |
| - currentTurn    |
+------------------+
| + start()        |
| + makeMove()     |
| + switchTurn()   |
| + checkResult()  |
+--------+---------+
         |
         | uses
         v
+------------------+
|      Board       |
+------------------+
| - grid           |
| - size           |
+------------------+
| + placeSymbol()  |
| + isFull()       |
| + checkWin()     |
+------------------+

+------------------+
|      Player      |
+------------------+
| - name           |
| - symbol         |
+------------------+
| + getSymbol()    |
+------------------+

+------------------+
|      Symbol      |
+------------------+
| X                |
| O                |
+------------------+
