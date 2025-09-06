The <b>Observer Design pattern</b> is a <a href="../../README.md#behavioural">behavioural pattern</a> where an object (subject) maintains a list of dependents (observer) and notify them automatically when its state change.

<p> ⚡ In short: Observer pattern let object (subscriber) to listen to changes in another object (Youtube channel) without being tightly couple</p>

## UML Diagram


 ```               +-----------------+
                |   <<interface>> |
                |     Observer    |
                +-----------------+
                | + update(message: String): void |
                +-----------------+
                          ^
                          |
                +-----------------+
                |   Subscriber    |
                +-----------------+
                | - name: String  |
                +-----------------+
                | + update(message: String): void |
                +-----------------+

                +-----------------+
                |   <<interface>> |
                |     Subject     |
                +-----------------+
                | + subscribe(observer: Observer): void   |
                | + unSubscribe(observer: Observer): void |
                | + notifyUser(message: String): void     |
                +-----------------+
                          ^
                          |
                +-----------------+
                |     Youtube     |
                +-----------------+
                | - subscriberList: List<Observer> |
                +-----------------+
                | + subscribe(observer: Observer): void   |
                | + unSubscribe(observer: Observer): void |
                | + notifyUser(message: String): void     |
                | + uploadVideo(title: String): void      |
                +-----------------+

                +-----------------+
                |      Main       |
                +-----------------+
                | + main(args: String[]): void |
                +-----------------+

Relationships:
------------
- `Youtube` implements `Subject`.
- `Subscriber` implements `Observer`.
- `Youtube` → maintains a list of `Observer`s.
- `Main` → uses `Youtube` and `Subscriber`.

```


