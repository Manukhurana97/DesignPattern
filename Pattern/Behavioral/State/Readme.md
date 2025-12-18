The <b>State design pattern</b> is a <a href="../../../Readme.md#behavioural">behavioural design pattern </a> that lets an object change its behaviour when its internal state changes, as if it were switching to a different class to runtime.

* State = behavior changes automatically based on current 
State allows an object to alter its behavior when its internal state changes.

```

                +----------------------+
                |   <<interface>>      |
                |     OrderState       |
                +----------------------+
                | + next(order)        |
                | + printStatus()      |
                +----------------------+
                          ^
            --------------|----------------
            |         |         |          |
+--------------+ +--------------+ +--------------+ +--------------+
| CreatedState | |  PaidState   | | ShippedState | | DeliveredState |
+--------------+ +--------------+ +--------------+ +--------------+

                +----------------------+
                |     OrderContext     |
                +----------------------+
                | - state: OrderState  |
                +----------------------+
                | + setState()         |
                | + next()             |
                | + printStatus



| Strategy            | State              |
| ------------------- | ------------------ |
| Chosen by client    | Changes internally |
| Algorithm selection | State transitions  |
| External switching  | Internal switching |


```