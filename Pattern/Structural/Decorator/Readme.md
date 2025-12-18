The <b>Decorator design pattern</b> is a <a href="../../../Readme.md#structural">structural design pattern </a> that let you dynamically add new behaviour or responsibility to objects without modifying underlying code.


* Decorator = Wrap an object to add extra features dynamically
* Decorator dynamically adds responsibilities to objects without modifying their structure.

```

            +------------------+
            |   <<interface>>  |
            |     Coffee       |
            +------------------+
            | + cost():double  |
            | + description()  |
            +------------------+
                    ^
        -------------|----------------
        |                              |
+--------------------+     +------------------------+
|  SimpleCoffee      |     |   CoffeeDecorator      |
+--------------------+     +------------------------+
                           | - coffee: Coffee       |
                           +------------------------+
                                   ^
                         ----------|----------
                         |                     |
               +-------------------+   +-------------------+
               |   MilkDecorator   |   |  SugarDecorator   |
               +-------------------+   +-------------------+



```