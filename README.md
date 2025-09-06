# 📘 SOLID Principles

The **SOLID principles** are a set of guidelines for writing clean, maintainable, and scalable code.  

1. **Single Responsibility Principle (SRP)**  
   A class/function should have **only one reason to change** — it must serve a single objective.

2. **Open/Closed Principle (OCP)**  
   Software entities should be **open for extension but closed for modification**.

3. **Liskov Substitution Principle (LSP)**  
   Subtypes must be substitutable for their base types **without altering the correctness** of the program.

4. **Interface Segregation Principle (ISP)**  
   Clients should not be forced to depend on methods they **do not use**. (Avoid interface pollution.)

5. **Dependency Inversion Principle (DIP)**  
   - High-level modules should not depend on low-level modules.  
   - Both should depend on **abstractions**.  
   - Abstractions should not depend on details; **details should depend on abstractions**.

👉 **High-level modules** = Business logic  
👉 **Low-level modules** = Conversions (e.g., Java ↔ JSON), writing to disk  

---

# 🎨 Design Patterns

Design patterns provide **proven solutions** to common software design problems.  

## 1. Creational Patterns  
Deal with object creation mechanisms.  

- Builder  
- Simple Factory  
- Factory Method  
- Prototype  
- Singleton  
- Abstract Factory  
- Object Pool  

## 2. Structural Patterns  
Deal with **class and object composition**.  

## 3. Behavioral Patterns  
Deal with **object interaction and communication**.  

---

## 🔨 Creational Patterns in Detail

### 1. Builder Pattern  
Used when we need an **immutable class with many parameters**.

```java
class Product {
    private String a;
    private String b;
    private String c;

    Product(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
```

As parameters grow, constructors become **hard to use**. The Builder pattern solves this by moving construction logic to a **separate builder class**.

**Design considerations**:  
- A separate *Director* class is rarely needed — usually the client plays this role.  
- An abstract builder is not required unless the `Product` is part of an inheritance hierarchy.  

---

### 2. Simple Factory  
Moves instantiation logic to a **static method** in a separate class.  

**Implementation notes**:  
- Can be a static method in an existing class.  
- Should not maintain state (keep it stateless).  
- Example: `NumberFormat.getInstance()` in Java.  

---

### 3. Factory Method  
Provides an interface for creating objects but lets subclasses decide which class to instantiate.  

**When to use**:  
- When you don’t know the exact type of objects beforehand.  
- When you want to allow new classes to be added without modifying client code.  

**Implementation**:  
- Create an abstract `Creator` with a factory method returning a product.  
- Subclasses override the factory method to return the correct type.  

**Example**:  

- `Message` (abstract class/interface)  
  - `JsonMessage`  
  - `TextMessage`  

- `MessageCreator` (abstract creator)  
  - `JsonMessageCreator`  
  - `TextMessageCreator`  
