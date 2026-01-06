# 📣 Publisher–Subscriber (Pub–Sub) System – Low Level Design (LLD)

## 1️⃣ Problem Statement

Design a **Publisher–Subscriber (Pub–Sub) system** where:

- Publishers publish messages to topics
- Subscribers subscribe to topics of interest
- Messages published to a topic are delivered to all its subscribers
- Publishers and subscribers are loosely coupled

The goal is to model an **event-driven system** using clean object-oriented design,
focusing on **decoupling, extensibility, and clarity**, not on infrastructure or scale.

> ⚠️ This is an **LLD learning exercise**, not a full messaging system like Kafka or SNS.

---

## 2️⃣ Requirements

### Functional Requirements
- Create topics
- Allow subscribers to subscribe/unsubscribe to topics
- Allow publishers to publish messages to topics
- Deliver messages to all subscribed consumers

### Non-Functional Requirements
- Loose coupling between publishers and subscribers
- Extensible design
- Simple in-memory implementation
- Clean separation of responsibilities

---

## 3️⃣ Core Entities

| Entity | Responsibility |
|------|----------------|
| **Publisher** | Publishes messages to a topic |
| **Subscriber** | Consumes messages from a topic |
| **Topic** | Logical channel for messages |
| **Message** | Data being published |
| **Broker / PubSubManager** | Routes messages from publishers to subscribers |

---

## 4️⃣ Design Overview

The system follows a **broker-based Pub–Sub model**:

- Publishers send messages to a **broker**
- Broker maintains topic → subscriber mapping
- Subscribers receive messages without knowing the publisher
- Publishers do not know who consumes the message

This ensures **low coupling and high extensibility**.

---

## 5️⃣ Design Principles & Patterns Used

### ✅ Publisher–Subscriber Pattern
- Decouples message producers from consumers
- Allows dynamic subscription and unsubscription

### ✅ Single Responsibility Principle (SRP)
- Publisher only publishes
- Subscriber only consumes
- Broker only routes messages

### ✅ Dependency Inversion
- Publishers and subscribers depend on abstractions, not concrete implementations

---

## 6️⃣ UML Diagram (Conceptual – Copy Friendly)

``` 
+---------------+
|   Publisher   |
+---------------+
| + publish()   |
+-------+-------+
        |
        | publishes to
        v
+---------------+
|     Topic     |
+---------------+
| - name        |
+-------+-------+
        |
        | managed by
        v
+-------------------+
|      Broker       |
+-------------------+
| - topics          |
+-------------------+
| + subscribe()     |
| + unsubscribe()   |
| + publish()       |
+--------+----------+
         |
         | delivers
         v
+-------------------+
|    Subscriber     |
+-------------------+
| + consume(msg)    |
+-------------------+


```