The <b>Adaptor design pattern</b> is a <a href="../../../Readme.md#structural">structural design pattern </a> that allow incompatible interfaces to work togerther by converting the interface of one class into another class that the client expects.

*Adapter = Translator between two incompatible interfaces
*Adapter allows incompatible interfaces to work together without modifying existing code.

```

            +---------------------+             +---------------------+
            |  <<interface>>      |	< --------- |         Main        |
            |  PaymentProcessor   |				+---------------------+	
            +---------------------+				| + main(args: String[]): void |
            | + pay(amount): void |				+---------------------+
            +---------------------+				
                      ^
                      |
              +--------------------+
              |   PaymentAdapter   |
              +--------------------+
              | - oldGateway       |
              +--------------------+
              | + pay(amount)      |
              +--------------------+
                      |
                      v
            +----------------------+
            |  OldPaymentGateway   |
            +----------------------+
            | + makePayment()      |
            +----------------------+


```