The <b>Factory Design pattern</b> is an <a href="../../README.md#creational">creational design pattern</a> that provide an interface for creating objects but let the subclass or a factory class decide which class to instantiate.


<p> ⚡ In short: The Factory pattern let you create objects (Circle/Rectangle) without exposing the instanciation logic to the client. The cliebnt only interact with the <code>Shape</code> interface. </p>

## UML Diagram


```
                +----------------+
                |  <<interface>> |
                |     Shape      |
                +----------------+
                | + draw(size: int): void |
                +----------------+
                       ^
           -------------|--------------
           |                           |
   +---------------+          +----------------+
   |    Circle     |          |   Rectangle    |
   +---------------+          +----------------+
   | + draw(size)  |          | + draw(size)   |
   +---------------+          +----------------+

                +-----------------+
                |  ShapeFactory   |
                +-----------------+
                | + getShape(input: String): Shape |
                +-----------------+

                +-----------------+
                |      Main       |
                +-----------------+
                | + main(args: String[]): void |
                +-----------------+

Relationships:
------------
- `Shape` is an **interface**.  
- `Circle` and `Rectangle` **implement** `Shape`.  
- `ShapeFactory` **creates** objects of type `Shape`.  
- `Main` uses `ShapeFactory` to get a `Shape` and call `draw()`.
```