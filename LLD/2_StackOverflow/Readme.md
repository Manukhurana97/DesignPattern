
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>StackOverflow LLD – Design Overview</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
            background-color: #f9f9f9;
        }
        h1, h2, h3 {
            color: #2c3e50;
        }
        h1 {
            border-bottom: 3px solid #2c3e50;
            padding-bottom: 5px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin: 15px 0;
            background: white;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th {
            background-color: #ecf0f1;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        code {
            background-color: #eef;
            padding: 2px 6px;
            border-radius: 4px;
        }
        pre {
            background-color: #272822;
            color: #f8f8f2;
            padding: 15px;
            border-radius: 6px;
            overflow-x: auto;
        }
        .note {
            background: #fff3cd;
            padding: 10px;
            border-left: 5px solid #f39c12;
            margin: 15px 0;
        }
    </style>
</head>
<body>

<h1>📘 StackOverflow – Low Level Design (LLD Practice)</h1>

<h2>📌 Problem Statement (In Short)</h2>
<p>
Design a <strong>StackOverflow-like system</strong> where users can:
</p>
<ul>
    <li>Ask questions</li>
    <li>Answer questions</li>
    <li>Comment on questions and answers</li>
    <li>Upvote / Downvote</li>
    <li>View ranked answers</li>
</ul>

<div class="note">
    <strong>Note:</strong> This is <b>LLD practice code</b>. It intentionally avoids databases,
    REST APIs, and scalability concerns to focus on <b>object modeling and design clarity</b>.
</div>

<h2>🧠 One-Glance Feature Summary</h2>

<table>
    <tr>
        <th>Feature</th>
        <th>Implemented</th>
    </tr>
    <tr><td>User Management</td><td>✅</td></tr>
    <tr><td>Ask & Answer Questions</td><td>✅</td></tr>
    <tr><td>Comments</td><td>✅</td></tr>
    <tr><td>Voting System</td><td>✅</td></tr>
    <tr><td>Answer Ranking</td><td>✅</td></tr>
    <tr><td>In-Memory Storage</td><td>✅</td></tr>
    <tr><td>Persistence / Database</td><td>❌ (Out of scope)</td></tr>
    <tr><td>Concurrency Handling</td><td>❌ (Not required for LLD)</td></tr>
</table>

<h2>🏗️ High-Level Design Overview</h2>
<p>
The system follows a clean layered design:
</p>
<ul>
    <li><b>Entities</b> – Represent core domain objects</li>
    <li><b>Services</b> – Handle business rules and orchestration</li>
    <li><b>Strategies</b> – Allow pluggable behaviors</li>
    <li><b>Repositories</b> – Abstract data storage (in-memory)</li>
</ul>

<h2>📦 Core Domain Entities (Simple Explanation)</h2>

<table>
    <tr>
        <th>Entity</th>
        <th>Responsibility</th>
    </tr>
    <tr><td>User</td><td>Represents a platform user</td></tr>
    <tr><td>Question</td><td>Stores question details, answers, and votes</td></tr>
    <tr><td>Answer</td><td>Represents an answer to a question</td></tr>
    <tr><td>Comment</td><td>Comment on a question or answer</td></tr>
    <tr><td>Vote</td><td>Captures upvote/downvote by a user</td></tr>
    <tr><td>Tag</td><td>Categorizes questions</td></tr>
</table>

<h2>🧩 Design Patterns Used</h2>

<h3>✅ Singleton Pattern</h3>
<p>
The main service acts as a single coordinator for all StackOverflow operations.
This ensures a single logical system instance during execution.
</p>

<h3>✅ Strategy Pattern</h3>
<p>
Used for <b>pluggable behaviors</b> such as:
</p>
<ul>
    <li>Answer ranking (score-based, time-based)</li>
    <li>Future extensions without modifying core logic</li>
</ul>

<h3>✅ Repository Pattern (Lightweight)</h3>
<p>
Repositories abstract data storage. Current implementation uses in-memory collections,
but can be replaced with database-backed storage without changing business logic.
</p>

<h2>📐 UML Diagram (Conceptual)</h2>

<pre>
User
 │
 ├── asks ──> Question
 │             │
 │             ├── has ──> Answer
 │             │              │
 │             │              └── has ──> Vote
 │             │
 │             └── has ──> Comment
 │
 └── votes ──> Vote
</pre>

<h2>🧪 Scope & Limitations</h2>
<ul>
    <li>No database or persistence layer</li>
    <li>No REST APIs</li>
    <li>No concurrency handling</li>
</ul>

<p>
These are <b>intentionally skipped</b> to keep the solution aligned with
<strong>LLD interview expectations</strong>.
</p>

<h2>🎯 Interview Readiness</h2>
<p>
This design demonstrates:
</p>
<ul>
    <li>Strong object modeling</li>
    <li>Clear responsibility ownership</li>
    <li>Extensibility without over-engineering</li>
    <li>Clean separation of concerns</li>
</ul>

<h3>⭐ Target Use Case</h3>
<p>
Perfect for:
</p>
<ul>
    <li>Low-Level Design interviews</li>
    <li>Machine coding rounds</li>
    <li>Whiteboard design discussions</li>
</ul>

</body>
</html>
