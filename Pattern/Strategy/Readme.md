The <b>Strategy Design Pattern</b> is a <a href="../../README.md#behavioural">behavioural pattern</a> that allows you to desgine a family of algo put each of them in a seperate class, and make them intrerchange at runtime.


⚡ In short:
<p>The Strategy Pattern lets you change the way an operation is performed (e.g., Payment method) without modifying the context class (Shoping). Each payment type is a separate strategy. </p>


## UML Diagram

```
                +------------------+
                |  <<interface>>   |
                |  PaymentSystem   |
                +------------------+
                | + makePayment(amount: int): void |
                +------------------+
                       ^
       -----------------|----------------------------
       |                |                           |
+---------------+   +-------------+          +-------------+
|   CreditCard  |   |  Netbaking  |          |     UPI     |
+---------------+   +-------------+          +-------------+
| - cardNumber  |   | - bank      |          | - upiId     |
+---------------+   +-------------+          +-------------+
| + makePayment(amount: int): void |          | + makePayment(amount: int): void |
+---------------+   +-------------+          +-------------+

                +--------------------+
                |      Shoping       |
                +--------------------+
                | - paymentSystem: PaymentSystem |
                +--------------------+
                | + setPaymentStrategy(paymentSystem: PaymentSystem): void |
                | + checkout(amount: int): void                            |
                +--------------------+

                +------------------+
                |       Main       |
                +------------------+
                | + main(args: String[]): void |
                +------------------+

Relationships:
------------
- `Shoping` → `PaymentSystem` (association, uses a strategy).
- `PaymentSystem` is an **interface**.
- `CreditCard`, `Netbaking`, `UPI` all **implement** `PaymentSystem`.
- `Main` **uses** `Shoping`.
