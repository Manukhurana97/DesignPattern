
The <b>State design pattern</b> is a <a href="../../README.md#behavioural">behavioural pattern</a> that allows an object to change its behaviour when its internal state changes.
Instead of using conditional statement (like if / else), the stat-specific behaviour isa encapsulated into separate state class

** In simple words : It helps an object behave differently based on its current state, while keeping state-specific logic in separate class.
<p>⚡ In short:The State Pattern lets the Fan behave differently (Off → Low → Medium → High → Off) without messy conditionals, by encapsulating each behavior in separate classes.


## UML Diagram
```
               +----------------+
                |    <<interface>>|
                |      State      |
                +----------------+
                | + pressButton(fan: Fan): void |
                +----------------+
                       ^
     -------------------|-------------------------
     |                  |                        |
+-----------+    +------------+           +------------+
| OffState  |    | LowState   |           | MediumState|
+-----------+    +------------+           +------------+
| + pressButton(fan: Fan):void|           | + pressButton(fan: Fan):void|
+-----------+    +------------+           +------------+
       |
       |                                  
       |                                  
+-----------+                                
| HighState |                                
+-----------+                                
| + pressButton(fan: Fan): void |             
+-----------+                                

                +-----------------+
                |      Fan        |
                +-----------------+
                | - state: State  |
                +-----------------+
                | + nextState(state: State): void |
                | + pressButton(): void           |
                +-----------------+

                +-----------------+
                |      Main       |
                +-----------------+
                | + main(args: String[]): void |
                +-----------------+

Relationships:
------------
- `Fan` → `State` (association, uses a State).
- `State` is an **interface**.
- `OffState`, `LowState`, `MediumState`, `HighState` all **implement** `State`.
- `Main` **uses** `Fan`.
```