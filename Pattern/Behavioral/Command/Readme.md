The <b>Command design pattern</b> is a <a href="../../../Readme.md#behavioural">behavioural design pattern </a> that turns an request into a standalone object, allowing you to parameterize action, queue them log them - all while decoupling the sender from the receiver.


```

                +----------------------+
                |   <<interface>>      |
                |        Command       |
                +----------------------+
                | + execute(): void    |
                | + undo(): void       |
                +----------------------+
                          ^
            --------------|-----------------------------
            |               |               |
+--------------------+ +--------------------+ +---------------------------+
| LightOnCommand     | | LightOffCommand    | | SetTemperatureCommand     |
+--------------------+ +--------------------+ +---------------------------+
| - light: Light     | | - light: Light     | | - thermostat: Thermostat  |
+--------------------+ +--------------------+ | - newTemperature: int     |
| + execute()        | | + execute()        | | - prevTemperature: int    |
| + undo()           | | + undo()           | +---------------------------+
+--------------------+ +--------------------+ | + execute()               |
                                               | + undo()                  |
                                               +---------------------------+


                +----------------------+
                |        Light         |
                +----------------------+
                | + on(): void         |
                | + off(): void        |
                +----------------------+

                +----------------------+
                |      Thermostat      |
                +----------------------+
                | - currentTemperature |
                +----------------------+
                | + getCurrentTemperature(): int |
                | + setCurrentTemperature(temp): void |
                +----------------------+


                +----------------------+
                |     SmartButton      |
                +----------------------+
                | - currentCommand     |
                | - history: Stack     |
                +----------------------+
                | + setCommand(cmd)    |
                | + press(): void      |
                | + undo(): void       |
                +----------------------+

                +----------------------+
                |        Main          |
                +----------------------+
                | + main(args): void   |
                +----------------------+



| Role            | Class                                                        |
| --------------- | ------------------------------------------------------------ |
| Command         | `Command`                                                    |
| ConcreteCommand | `LightOnCommand`, `LightOffCommand`, `SetTemperatureCommand` |
| Receiver        | `Light`, `Thermostat`                                        |
| Invoker         | `SmartButton`                                                |
| Client          | `Main`                                                       |


```