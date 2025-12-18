The <b>Observer design pattern</b> is a <a href="../../../Readme.md#behavioural">behavioural design pattern </a> that define a one-to-many dependency b/w objects so that when one object changes it state, all its dependents(observers) are automatically notified and updated.

* Observer = Publish–Subscribe mechanism


```

                +----------------------+
                |     <<interface>>    |
                |       Observer       |
                +----------------------+
                | + update(service)    |
                +----------------------+
                          ^
            --------------|--------------
            |                             |
+---------------------+        +---------------------+
|  EmailNotifications |        |  SMSNotifications   |
+---------------------+        +---------------------+
| + update()          |        | + update()          |
+---------------------+        +---------------------+


                +----------------------+
                |     <<interface>>    |
                |        Subject       |
                +----------------------+
                | + register()         |
                | + unregister()       |
                | + notifyObservers()  |
                +----------------------+
                          ^
                          |
                +----------------------+
                | NotificationService  |
                +----------------------+
                | - message            |
                | - observers          |
                +----------------------+
                | + sendMessage()      |
                | + getMessage()       |
                +----------------------+



```
