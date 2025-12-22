# 📘 StackOverflow – Low Level Design (LLD Practice)

## 📌 Problem Statement (In Short)

Design a **StackOverflow-like system** where users can:

- Ask questions
- Answer questions
- Comment on questions and answers
- Upvote / Downvote
- View ranked answers

> ⚠️ This is **LLD practice code**.  
> Databases, REST APIs, and scalability concerns are intentionally skipped to focus on **object modeling and design clarity**.

---

## 🧠 One-Glance Feature Summary

| Feature | Status |
|------|------|
| User Management | ✅ |
| Ask Questions | ✅ |
| Answer Questions | ✅ |
| Comments | ✅ |
| Voting (Up/Down) | ✅ |
| Answer Ranking | ✅ |
| In-Memory Storage | ✅ |
| Database / Persistence | ❌ (Out of scope) |
| Concurrency Handling | ❌ (Not required for LLD) |

---

## 🏗️ High-Level Design Overview

This design focuses on **clean domain modeling** and follows a layered structure:

- **Entities** → Core domain objects
- **Services** → Business rules & orchestration
- **Strategies** → Pluggable behaviors (ranking, voting rules)
- **Repositories** → In-memory storage abstraction

---

## 📦 Core Domain Entities (Simple Explanation)

| Entity | Responsibility |
|-----|--------------|
| User | Represents a platform user |
| Question | Stores question details, answers, and votes |
| Answer | Represents an answer to a question |
| Comment | Comment on a question or answer |
| Vote | Represents upvote/downvote by a user |
| Tag | Categorizes questions |

---

## 🧩 Design Patterns Used

### ✅ Singleton Pattern
- Central service acts as a single coordinator for StackOverflow operations.
- Represents a single logical system instance.

### ✅ Strategy Pattern
- Used for **answer ranking** (score-based, time-based).
- Allows changing algorithms without modifying core logic.

### ✅ Repository Pattern (Lightweight)
- Abstracts data storage.
- Current implementation is **in-memory**, but easily replaceable.

---

## 📐 UML Diagram (Conceptual)

User
│
├── asks ──> Question
│ │
│ ├── has ──> Answer
│ │ │
│ │ └── has ──> Vote
│ │
│ └── has ──> Comment
│
└── votes ──> Vote


