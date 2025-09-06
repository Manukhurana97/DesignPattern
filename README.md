<h2>SOLID Principle</h2>
1. <b>Single Responsibility :-</b> Class / Function must have only one objective. <br>
2. <b>Open & Close Principle :-</b> Object must be open for enhancement, close for modification. <br>
3. <b>Liskov Substitution :-</b> Class must be override by it subtype without changing its behaviour. <br>
4. <b>Interface Generation :-</b> Class should not depend on interface method which they dont use. (Interface pollution).
5. <b>Dependency Inversion :-</b> High level module should not depend on low level module, both should depend on abstraction. / Abstraction should depend on depend on details, rather details should depend on abstraction (interface)

<br><br>
<b>High level module :</b> any business logic<br>
<b>Low level module :</b> convertion from Java to Json or writing to disk.<br>


<hr></hr>


<h2> Design Pattern </h2>

<ol>
    <li><b> Creational Design Pattern :</b> It deal with the process of creating of object of class. </li>
    <ol>
        <li>Builder</li>
        <li>Simple Factory</li>
        <li>Factory Method</li>
        <li>Proto Method</li>
        <li>Singleton</li>
        <li>Abstract Factory</li>
        <li>Object pool</li>
    </ol>
    <li><b> Structural Design Pattern :</b> It deal with how the classes and object are arranged or composed</li>
    <a name="behavioural"></a>
<li><b> Behavioural Design Pattern :</b> It deal with how the classes and object interact and communicate with each other.</li>
</ol>

<br>

<h3>1. Creational</h3>
<h4>1. Builder:</h4>
Suppose we need a immutable class with lots of arguments.<br>

<pre><code>class product {
    String a;
    String b;
    String c;
    ..

    Product(String a, String b, String c ..){}
}
</code></pre>

as the number of arguments increase, it will became difficult for other developer to use. Builder design pattern can help us in this.

* In Builder , we remove the logic related object construction from code & abstract it in seperate class.

<h5>Design Consideration:</h5>
<ol>
<li> Director roles is rarely implemented as seperate class, typically the comsumer of the object instance or the client handle this role.</li>
<li> Abstract builder is also not required if "product" itself is not part of any inheritance.</li>
<li> The director role is rarely implemented as a seperate class, typically the consumer of the object instance or the client handle the role.</li>
<li> Abstract builder is also not required if "product" itself is not part of any instance hierarchy.</li>
</ol>


<br>


<h4>2. Simple Factory:</h4>
<ol>
    <li>Move the instantation logic to a seperate class : to a static methord</li>
    <li>Implementation</li>
    <ol>
        <li>It can be just a methord is existing class, adding in a seperate class allow clode to be in simple factory more easily use/assessible</li>
        <li>Simple factor itself doesnt need any state tracting. So its best to have / keep this as a static methord.</li>
        <li>JAVA.textNumberFactory has getFactory()</li>
    </ol>
</ol>


<br>

<h4>3. Factory Methord:</h4>
<ol>
    <li>Move the object creational logic to other class similar to simple factory.</li>
    <li>We use this when we dont know the typoe of the class in advance which we may need to instantiate before hand ans also to allow new claases to have added to system and handle their creational wuth effecting client code.</li>
    <li>Implementation:</li>
    <ol>
        <li>We can start by creating a class for a constructor</li>
        <li>Creator itself can be concreate if it can provide default object or it can be abstract.</li>
        <li>Implementation will override the methord ans return the object</li>
    </ol>
</ol>

<br>
* Message (ha one abstract methord) -> (json extend message, String extend message)<br>
* MessageCreator (has an abstract methord returning message -> (JSONMessageCreator extend MessageCreator, StringMessageCreator extend MessageCreator)<br>
