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


*Reference: https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898

👉 **High-level modules** = Business logic  
👉 **Low-level modules** = Conversions (e.g., Java ↔ JSON), writing to disk  

---

# 🎨 Design Patterns

Design patterns provide **proven solutions** to common software design problems.  

<a name="creational"></a> 
## *[1. Creational Patterns](./Pattern/Creational)  
Deal with object creation mechanisms.  

- *[Builder](./Pattern/Creational/Builder)  
- *[Factory Method](./Pattern/Creational/FactoryMethord)  
- *[Abstract Factory](./Pattern/Creational/AbstractFactory)  
- *[Singleton](./Pattern/Creational/Singleton)  

<a name="structural"></a> 
## *[2. Structural Patterns](./Pattern/Structural) 
Deal with **class and object composition**.  

- [Adaptor](./Pattern/Structural/Adaptor)
- [Facade](./Pattern/Structural/Facade)
- [Proxy](./Pattern/Structural/Proxy)
- [Decorator](./Pattern/Structural/Decorator)
- [Composite](./Pattern/Structural/Composite) 

<a name="behavioural"></a> 
## *[3. Behavioral Patterns](./Pattern/Behavioral) 
Deal with **object interaction and communication**.  


- *[Iterator](./Pattern/Behavioral/Iterator)
- *[Observer](./Pattern/Behavioral/Observer)
- *[Strategy](./Pattern/Behavioral/Strategy)
- *[Command](./Pattern/Behavioral/Command)
- *[State](./Pattern/Behavioral/State)




# refer: 
<ol> 
<li>https://github.com/ashishps1/awesome-low-level-design</li>
<li>https://github.com/donnemartin/system-design-primer</li> 
<li>https://newsletter.systemdesign.one/?r=a1ck9</li>
</ol>
