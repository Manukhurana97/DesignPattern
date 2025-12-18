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
  <li>📉 Reduces side effects</li>
</ul>

<h2>2️⃣ Open / Closed Principle (OCP)</h2>
<p>
Software entities should be <b>open for extension</b> but <b>closed for modification</b>.
</p>
<ul>
  <li>➕ Add new behavior without changing existing code</li>
  <li>🧩 Achieved using abstraction & polymorphism</li>
</ul>

<h2>3️⃣ Liskov Substitution Principle (LSP)</h2>
<p>
Subtypes must be substitutable for their base types <b>without breaking correctness</b>.
</p>
<ul>
  <li>🔁 Child classes must honor parent behavior</li>
  <li>⚠️ Avoid strengthening preconditions</li>
</ul>

<h2>4️⃣ Interface Segregation Principle (ISP)</h2>
<p>
Clients should not be forced to depend on methods they <b>do not use</b>.
</p>
<ul>
  <li>🧼 Avoid interface pollution</li>
  <li>🪜 Prefer small, focused interfaces</li>
</ul>

<h2>5️⃣ Dependency Inversion Principle (DIP)</h2>
<ul>
  <li>⬆️ High-level modules should not depend on low-level modules</li>
  <li>🔌 Both should depend on <b>abstractions</b></li>
  <li>🔄 Details should depend on abstractions, not vice-versa</li>
</ul>

<h3>🧠 Clarification</h3>
<ul>
  <li><b>High-level modules</b> → Business logic</li>
  <li><b>Low-level modules</b> → DB, IO, JSON/XML, file system</li>
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
Design patterns provide <b>proven, reusable solutions</b> to common software design problems.
</p>

<!-- ===================== CREATIONAL ===================== -->

<h2 id="creational">🏗️ Creational Patterns</h2>
<p>Deal with <b>object creation mechanisms</b>.</p>

<ul>
  <li>
    🧱 <a href="./Pattern/Creational/Builder"><b>Builder</b></a><br/>
    <small>Create complex immutable objects step-by-step.</small>
  </li>
  <br/>
  <li>
    🏭 <a href="./Pattern/Creational/FactoryMethod"><b>Factory Method</b></a><br/>
    <small>Create one object and let subclasses decide which class to instantiate.</small>
  </li>
  <br/>
  <li>
    🏗️ <a href="./Pattern/Creational/AbstractFactory"><b>Abstract Factory</b></a><br/>
    <small>Create families of related objects without specifying concrete classes.</small>
  </li>
  <br/>
  <li>
    🔒 <a href="./Pattern/Creational/Singleton"><b>Singleton</b></a><br/>
    <small>Ensure only one instance of a class exists.</small>
  </li>
</ul>

<!-- ===================== STRUCTURAL ===================== -->

<h2 id="structural">🧱 Structural Patterns</h2>
<p>Deal with <b>class and object composition</b>.</p>

<ul>
  <li>
    🔌 <a href="./Pattern/Structural/Adapter"><b>Adapter</b></a><br/>
    <small>Make incompatible interfaces work together.</small>
  </li>
  <br/>
  <li>
    🎭 <a href="./Pattern/Structural/Facade"><b>Facade</b></a><br/>
    <small>Provide a simplified interface to a complex subsystem.</small>
  </li>
  <br/>
  <li>
    🛡️ <a href="./Pattern/Structural/Proxy"><b>Proxy</b></a><br/>
    <small>Control access to an object.</small>
  </li>
  <br/>
  <li>
    🎁 <a href="./Pattern/Structural/Decorator"><b>Decorator</b></a><br/>
    <small>Add behavior dynamically without modifying the original object.</small>
  </li>
  <br/>
  <li>
    🌳 <a href="./Pattern/Structural/Composite"><b>Composite</b></a><br/>
    <small>Treat individual objects and object groups uniformly.</small>
  </li>
</ul>

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
    📣 <a href="./Pattern/Behavioral/Observer"><b>Observer</b></a><br/>
    <small>One-to-many dependency between objects.</small>
  </li>
  <br/>
  <li>
    🎯 <a href="./Pattern/Behavioral/Strategy"><b>Strategy</b></a><br/>
    <small>Choose an algorithm at runtime.</small>
  </li>
  <br/>
  <li>
    ⌨️ <a href="./Pattern/Behavioral/Command"><b>Command</b></a><br/>
    <small>Encapsulate a request as an object (supports undo/redo).</small>
  </li>
  <br/>
  <li>
    🔄 <a href="./Pattern/Behavioral/State"><b>State</b></a><br/>
    <small>Change behavior when internal state changes.</small>
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
