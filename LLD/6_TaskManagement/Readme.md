# ✅ Task Management System – Low Level Design (LLD)

## 1️⃣ Problem Statement

Design a **Task Management System** that allows users to:

- Create tasks
- Assign tasks to users
- Update task status
- View tasks based on different criteria

The goal is to model a **simple but extensible task system** using
clean object-oriented design suitable for **LLD interviews**.

> ⚠️ This is **LLD practice code**, not a production system.  
> Focus is on **design clarity, responsibility separation, and extensibility**.

---

## 2️⃣ Requirements

### Functional Requirements
- Create a task with title and description
- Assign a task to a user
- Update task status (e.g. TODO → IN_PROGRESS → DONE)
- Fetch tasks by status or user

### Non-Functional Requirements
- Clean object-oriented design
- Easy to extend with new task states or rules
- In-memory storage (no database)
- Simple and readable code (LLD focus)

---

## 3️⃣ Core Entities

| Entity | Responsibility |
|-----|--------------|
| **Task** | Represents a unit of work |
| **User** | Represents a system user |
| **TaskStatus** | Enum representing task state |
| **TaskService / TaskManager** | Handles task operations |
| **TaskRepository** (implicit) | Stores tasks in memory |

---

## 4️⃣ Design Overview

The design follows a **layered approach**:

- **Entities** → Hold state and basic behavior
- **Service layer** → Manages task creation, assignment, and updates
- **Enums** → Restrict valid task states

This avoids:
- God classes
- Excessive conditionals
- Over-engineering

---

## 5️⃣ Design Patterns & Principles Used

### ✅ Single Responsibility Principle (SRP)
- `Task` only represents task data
- `TaskManager` handles operations
- `User` represents ownership

### ✅ Enum-based State Modeling
- Task lifecycle is represented via `TaskStatus`
- Prevents invalid string-based states

### ✅ Open for Extension
- New task statuses can be added
- Assignment or prioritization logic can be extended later

---

## 6️⃣ UML Diagram (Conceptual)

```
+------------------+
|      User        |
+------------------+
| - id             |
| - name           |
+------------------+

        assigns
           |
           v

+------------------+
|      Task        |
+------------------+
| - id             |
| - title          |
| - description    |
| - status         |
| - assignedUser   |
+------------------+

           |
           | has
           v

+------------------+
|   TaskStatus     |
+------------------+
| TODO             |
| IN_PROGRESS      |
| DONE             |
+------------------+

+------------------------+
|     TaskManager        |
+------------------------+
| + createTask()         |
| + assignTask()         |
| + updateTaskStatus()   |
| + getTasksByUser()     |
| + getTasksByStatus()   |
+------------------------+

```