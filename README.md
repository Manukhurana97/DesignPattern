

<!-- ===================== SOLID PRINCIPLES ===================== -->

<h1>📘 SOLID Principles</h1>

<p>
<b>SOLID</b> principles are a set of guidelines for writing
<b>clean, maintainable, and scalable</b> object-oriented code.
</p>

<hr/>

<h2>1️⃣ Single Responsibility Principle (SRP)</h2>

<p>
A class or function should have <b>only one reason to change</b>.
</p>

<ul>
  <li>🎯 One class = one responsibility</li>
  <li>📉 Reduces side effects and improves readability</li>
  <li>🛠️ Easier to test and maintain</li>
  <li>👉 A class should have only one reason to change</li>
</ul>

<table>
  <thead>
    <tr>
      <th>❌ Incorrect Approach</th>
      <th>✅ Correct Approach</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
<pre><code>
class Invoice {

    void calculateTotal() {
        System.out.println("Calculating total");
    }

    void printInvoice() {
        System.out.println("Printing invoice");
    }
}
</code></pre>
</td>
<td>
<pre><code>
class Invoice {

    void calculateTotal() {
        System.out.println("Calculating total");
    }
}

class InvoicePrinter {

    void printInvoice() {
        System.out.println("Printing invoice");
    }
}
</code></pre>
      </td>
    </tr>
  </tbody>
</table>

<hr/>

<h2>2️⃣ Open / Closed Principle (OCP)</h2>

<p>
Software entities should be <b>open for extension</b> but <b>closed for modification</b>.
</p>

<ul>
  <li>➕ Add new behavior without changing existing code</li>
  <li>🧩 Achieved using abstraction and polymorphism</li>
  <li>👉 Open for extension, closed for modification</li>
</ul>

<table>
  <thead>
    <tr>
      <th>❌ Incorrect Approach (requires modification)</th>
      <th>✅ Correct Approach</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
<pre><code>
class Discount {

    double calculate(String type) {
        if (type.equals("FESTIVAL")) return 10;
        if (type.equals("SEASONAL")) return 20;
        return 0;
    }
}
</code></pre>
</td>
<td>
<pre><code>
interface Discount {
    
    double calculate();
}

class FestivalDiscount implements Discount {
    
    public double calculate() {
        return 10;
    }
}

class SeasonalDiscount implements Discount {
    
    public double calculate() {
        return 20;
    }
}
</code></pre>
      </td>
    </tr>
  </tbody>
</table>

<hr/>

<h2>3️⃣ Liskov Substitution Principle (LSP)</h2>

<p>
Subtypes must be substitutable for their base types
<b>without breaking program correctness</b>.
</p>

<ul>
  <li>🔁 Child classes must honor parent behavior</li>
  <li>⚠️ Avoid strengthening preconditions or weakening postconditions</li>
  <li>👉 Child classes must be usable wherever the parent is used</li>
</ul>

<table>
  <thead>
    <tr>
      <th>❌ Incorrect Approach</th>
      <th>✅ Correct Approach</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
<pre><code>
class Bird {

    void fly() {
        System.out.println("Flying");
    }
}

class Penguin extends Bird {

    void fly() {
        throw new RuntimeException("Penguins can't fly");
    }
}
</code></pre>
</td>
<td>
<pre><code>
interface Bird {
}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {

    public void fly() {
        System.out.println("Flying");
    }
}

class Penguin implements Bird {
}
</code></pre>
      </td>
    </tr>
  </tbody>
</table>

<hr/>

<h2>4️⃣ Interface Segregation Principle (ISP)</h2>

<p>
Clients should not be forced to depend on methods they
<b>do not use</b>.
</p>

<ul>
  <li>🧼 Avoid large, bloated interfaces</li>
  <li>🪜 Prefer small, focused interfaces</li>
  <li>👉 Don’t force classes to implement methods they don’t need</li>
</ul>

<table>
  <thead>
    <tr>
      <th>❌ Incorrect Approach</th>
      <th>✅ Correct Approach</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
<pre><code>
interface Worker {

    void work();
    void eat();
}

class Robot implements Worker {

    public void work() {}
    public void eat() {} // ❌ Robot doesn't eat
}
</code></pre>
</td>
<td>
<pre><code>
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {

    public void work() {}
    public void eat() {}
}

class Robot implements Workable {

    public void work() {}
}
</code></pre>
      </td>
    </tr>
  </tbody>
</table>

<hr/>

<h2>5️⃣ Dependency Inversion Principle (DIP)</h2>

<ul>
  <li>⬆️ High-level modules should not depend on low-level modules</li>
  <li>🔌 Both should depend on <b>abstractions</b></li>
  <li>🔄 Details should depend on abstractions, not vice versa</li>
  <li>👉 Depend on abstractions, not concrete implementations</li>
</ul>

<table>
  <thead>
    <tr>
      <th>❌ Incorrect Approach</th>
      <th>✅ Correct Approach</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
<pre><code>
class Keyboard {
}

class Computer {

    private Keyboard keyboard = new Keyboard();
}
</code></pre>
</td>
<td>
<pre><code>
interface InputDevice {
}

class Keyboard implements InputDevice {
}

class Computer {

    private InputDevice inputDevice;

    Computer(InputDevice inputDevice) {
        this.inputDevice = inputDevice;
    }
}
</code></pre>
      </td>
    </tr>
  </tbody>
</table>

<hr/>

<h3>OOP Fundamentals</h3>
<ul>
    <li><b>Encapsulation</b> -> keep the data private + expose behaviour safely</li>
    <li><b>Abstraction</b> -> Hiding complexity + showing essentials</li>
    <li><b>Inheritance</b> -> Code Reusability + IS-A Relationship</li>
    <li><b>Polymorphism</b> -> Same interface / method name + Different behavior</li>
    <ul>
        <li>Compile Time: Method Overloading (same name different parameters)</li>
        <li>Run Time: Method Overriding (parent -> child)</li>    
    </ul>
</ul>

| Encapsulation                | Abstraction                                |
| ---------------------------- | ------------------------------------------ |
| *How data is protected*      | *What behavior is exposed*                 |
| Data hiding + access control | Hide implementation complexity             |
| Achieved using `private`     | Achieved using interfaces/abstract classes |
| Focus: **inside object**     | Focus: **outside view**                    |

<hr/>

<h3>Class Relations</h3>
<ul>
    <li><b>Association</b> -> A relationship between two classes where one object uses, refers to, or communicates with another</li>
    <ul>
        <li><b>Aggregation(Weak Association)</b> -> (Has-A, but not own)</li>
        <pre><code>
            class Department {}
            class Employee {
                Department dept;   // aggregation
            }
        </code></pre>
✔ Department can exist without Employee
        <li><b>Composition(Strong Association))</b> -> (Is-A, but owns)</li>
        <pre><code>
            class Engine {}
            class Car {
                private Engine engine = new Engine()
            }
        </code></pre>
        ✔ Engine’s lifecycle depends on Car
    </ul>
</ul>

<h3>🧠 Clarification</h3>

<ul>
  <li><b>High-level modules</b> → Business logic</li>
  <li><b>Low-level modules</b> → Database, IO, file system, JSON/XML, frameworks</li>
</ul>

<p>
📖 Reference:
<a href="https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898">
The SOLID Principles in Pictures
</a>
</p>

<hr/>

<!-- ===================== DESIGN PATTERNS ===================== -->

<h1>🎨 Design Patterns</h1>

<p>
Design patterns provide <b>proven and reusable solutions</b>
to common software design problems.
</p>

<!-- ===================== CREATIONAL ===================== -->

<h2 id="creational">🏗️ Creational Patterns</h2>

<p>Deal with <b>object creation mechanisms</b>.</p>

<ul>
  <li>
    🧱 <a href="./Pattern/Creational/Builder"><b>Builder*</b></a><br/>
    <small>Create complex immutable objects step by step.</small>
  </li>
  <br/>
  <li>
    🏭 <a href="./Pattern/Creational/FactoryMethod"><b>Factory Method**</b></a><br/>
    <small>Let subclasses decide which class to instantiate.</small>
  </li>
  <br/>
  <li>
    🏗️ <a href="./Pattern/Creational/AbstractFactory"><b>Abstract Factory**</b></a><br/>
    <small>Create families of related objects without specifying concrete classes.</small>
  </li>
  <br/>
  <li>
    🔒 <a href="./Pattern/Creational/Singleton"><b>Singleton**</b></a><br/>
    <small>Ensure only one instance of a class exists.</small>
  </li>
  <br/>
  <li>
    🔒 <a href="./Pattern/Creational/Proto"><b>Proto</b></a><br/>
    <small>Create new object by cloning existing objects instead of creating the from scratch</small>
  </li>
</ul>

<hr/>

<!-- ===================== STRUCTURAL ===================== -->

<h2 id="structural">🧱 Structural Patterns</h2>

<p>Deal with <b>class and object composition</b>.</p>

<ul>
  <li>
    🔌 <a href="./Pattern/Structural/Adapter"><b>Adapter*</b></a><br/>
    <small>Make incompatible interfaces work together.</small>
  </li>
  <br/>
  <li>
    🎭 <a href="./Pattern/Structural/Facade"><b>Facade*</b></a><br/>
    <small>Provide a simplified interface to a complex subsystem.</small>
  </li>
  <br/>
  <li>
    🛡️ <a href="./Pattern/Structural/Proxy"><b>Proxy</b></a><br/>
    <small>Control access to an object.</small>
  </li>
  <br/>
  <li>
    🎁 <a href="./Pattern/Structural/Decorator"><b>Decorator*</b></a><br/>
    <small>Add behavior dynamically without modifying the original object.</small>
  </li>
  <br/>
  <li>
    🌳 <a href="./Pattern/Structural/Composite"><b>Composite</b></a><br/>
    <small>Treat individual objects and object groups uniformly.</small>
  </li>
</ul>

<hr/>

<!-- ===================== BEHAVIORAL ===================== -->

<h2 id="behavioral">🔄 Behavioral Patterns</h2>

<p>Deal with <b>object interaction and communication</b>.</p>

<ul>
  <li>
    🔁 <a href="./Pattern/Behavioral/Iterator"><b>Iterator</b></a><br/>
    <small>Traverse a collection without exposing its internal structure.</small>
  </li>
  <br/>
  <li>
    📣 <a href="./Pattern/Behavioral/Observer"><b>Observer**</b></a><br/>
    <small>Define a one-to-many dependency between objects.</small>
  </li>
  <br/>
  <li>
    🎯 <a href="./Pattern/Behavioral/Strategy"><b>Strategy*</b></a><br/>
    <small>Select an algorithm at runtime.</small>
  </li>
  <br/>
  <li>
    ⌨️ <a href="./Pattern/Behavioral/Command"><b>Command</b></a><br/>
    <small>Encapsulate a request as an object (supports undo/redo).</small>
  </li>
  <br/>
  <li>
    🔄 <a href="./Pattern/Behavioral/State"><b>State</b></a><br/>
    <small>Change behavior when the internal state changes.</small>
  </li>
</ul>

<hr/>

<!-- ===================== REFERENCES ===================== -->

<h2>📚 References</h2>

<ol>
  <li>
    <a href="https://github.com/ashishps1/awesome-low-level-design">
      Awesome Low Level Design
    </a>
  </li>
  <li>
    <a href="https://github.com/donnemartin/system-design-primer">
      System Design Primer
    </a>
  </li>
  <li>
    <a href="https://newsletter.systemdesign.one/?r=a1ck9">
      System Design Newsletter
    </a>
  </li>
</ol>
