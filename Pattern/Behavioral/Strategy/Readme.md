The <b>Strategy design pattern</b> is a <a href="../../../Readme.md#behavioural">behavioural design pattern </a> that let you define a family of algorithms, put each one into a seperate class, and makes their object interchange — allowing the algorithm to vary indepdendntly from the clints that uses it.

The Strategy Design Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

*Select an algorithm at runtime


```

                +---------------------------+
                |   <<interface>>           |
                |     PaymentStrategy       |
                +---------------------------+
                | + pay(amount): void       |
                +---------------------------+
                           ^
            ---------------|--------------------
            |                |                 |
+------------------+ +------------------+ +------------------+
| CardPayment      | | UPIPayment       | | WalletPayment    |
+------------------+ +------------------+ +------------------+
| + pay()          | | + pay()          | | + pay()          |
+------------------+ +------------------+ +------------------+

                +---------------------------+
                |     PaymentContext        |
                +---------------------------+
                | + pay(strategy, amount)   |
                +---------------------------+



```