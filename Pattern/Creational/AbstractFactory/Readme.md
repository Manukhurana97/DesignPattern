The <b>Abstract Factory</b> is a <a href="../../../README.md#creational">creational design pattern</a> pattern that provide a interface to create family of related objects or dependents objects.

<p> ⚡ In short: The Factory pattern let you create objects (EmailNotification/SMSNotification) without exposing the instanciation logic to the client. The cliebnt only interact with the <code>Shape</code> interface. </p>

** Inheritance is not allowed

👉 Creates families of related objects without specifying their concrete classes.


```

                +----------------------+
                |   <<interface>>      |
                |        Button        |
                +----------------------+
                | + paint(): void      |
                | + onClick(): void    |
                +----------------------+
                           ^
             ---------------|----------------
             |                                |
   +-----------------------+      +-----------------------+
   |   WindowsButton       |      |     MacButton          |
   +-----------------------+      +-----------------------+
   | + paint(): void       |      | + paint(): void       |
   | + onClick(): void     |      | + onClick(): void     |
   +-----------------------+      +-----------------------+


                +----------------------+
                |   <<interface>>      |
                |      Checkbox        |
                +----------------------+
                | + paint(): void      |
                | + onSelect(): void   |
                +----------------------+
                           ^
             ---------------|----------------
             |                                |
   +-----------------------+      +-----------------------+
   |   WindowCheckbox      |      |     MacCheckbox        |
   +-----------------------+      +-----------------------+
   | + paint(): void       |      | + paint(): void       |
   | + onSelect(): void    |      | + onSelect(): void    |
   +-----------------------+      +-----------------------+


                +-------------------------------+
                |       <<interface>>           |
                |          UIFactory            |
                +-------------------------------+
                | + createButton(): Button      |
                | + createCheckBox(): Checkbox  |
                +-------------------------------+
                           ^
             ---------------|----------------
             |                                |
   +-----------------------+      +-----------------------+
   |     WindowFactory     |      |      MacFactory        |
   +-----------------------+      +-----------------------+
   | + createButton()      |      | + createButton()      |
   | + createCheckBox()    |      | + createCheckBox()    |
   +-----------------------+      +-----------------------+


                +-------------------------------+
                |         Application           |
                +-------------------------------+
                | - button: Button              |
                | - checkbox: Checkbox          |
                +-------------------------------+
                | + renderUI(): void            |
                +-------------------------------+


                +-------------------------------+
                |             Main              |
                +-------------------------------+
                | + main(args: String[]): void  |
                +-------------------------------+


Relationships:
------------

- `Button` and `Checkbox` are product interfaces
- `WindowsButton`, `MacButton` implement `Button`
- `WindowCheckbox`, `MacCheckbox` implement `Checkbox`
- `UIFactory` is the Abstract Factory
- `WindowFactory` and `MacFactory` create families of related UI components
- `Application` depends only on abstractions
- `Main` decides which factory to use at runtime


```