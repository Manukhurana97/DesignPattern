The <b>Factory Design pattern</b> is an <a href="../../../README.md#creational">creational design pattern</a> that provide an interface for creating objects but let the subclass or a factory class decide which class to instantiate.


<p> ⚡ In short: The Factory pattern let you create objects (EmailNotification/SMSNotification) without exposing the instanciation logic to the client. The cliebnt only interact with the <code>Shape</code> interface. </p>

👉 Creates one object and lets subclasses decide which class to instantiate.

## UML Diagram

```

                +----------------------+
                |   <<interface>>      |
                |    Notification      |
                +----------------------+
                | + send(message: String): void |
                +----------------------+
                           ^
             ---------------|----------------
             |                                |
   +---------------------+        +---------------------+
   |  EmailNotification  |        |   SMSNotification   |
   +---------------------+        +---------------------+
   | + send(message)     |        | + send(message)     |
   +---------------------+        +---------------------+


                +------------------------------+
                |   <<abstract>>               |
                |   NotificationCreator        |
                +------------------------------+
                | + createNotification(): Notification |
                | + send(message: String): void        |
                +------------------------------+
                           ^
             ---------------|----------------
             |                                |
+-------------------------------+   +-------------------------------+
| EmailNotificationCreator      |   | SMSNotificationCreator        |
+-------------------------------+   +-------------------------------+
| + createNotification()        |   | + createNotification()        |
+-------------------------------+   +-------------------------------+


                +----------------+
                |      Main      |
                +----------------+
                | + main(args: String[]): void |
                +----------------+



Relationships:
------------


- `Notification` is an interface.
- `EmailNotification` and `SMSNotification` implement `Notification`.
- `NotificationCreator` is an abstract class that declares the Factory Method.
- `EmailNotificationCreator` and `SMSNotificationCreator` extend `NotificationCreator`.
- `Main` uses NotificationCreator` to send notifications without knowing the concrete class.

```