The <b>Proxy design pattern</b> is a <a href="../../../Readme.md#structural">structural design pattern </a> that provide a placeholder / serrogate for the another object, allowing you to control access to it.

```

            +------------------+        +-------------------+        
            |   <<interface>>  | <----  |		 Main	    |
            |      Image       |		+-------------------+
            +------------------+		|+ main(args): void |
            | + display():void |		+-------------------+
            +------------------+		
                    ^
        -------------|--------------
        |                            |
+--------------------+     +--------------------+
|    RealImage       |     |    ImageProxy      |
+--------------------+     +--------------------+
| + display()        |     | - realImage        |
+--------------------+     | + display()        |
                           +--------------------+


```

