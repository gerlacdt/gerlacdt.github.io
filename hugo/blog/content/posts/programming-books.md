---
title: "Best Books For The Inquisitive Software Engineer"
date: 2022-11-04T10:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: false
---

This article contains a list of my favorite books with a major impact on my
Software Engineering career. The books are categorized in:

- [_Software Engineering_](#software-engineering)
- [_Programming_](#programming)
- [_Brian Kernighan’s Books_](#brian-kernighans-books)
- [_Modern Classics_](#modern-classics)
- [_Learning_](#learning)
- [_Management and Leadership_](#management-and-leadership)
- [_Unix Books From Stevens_](#unix-books-from-stevens)

## Software Engineering

### A Philosophy of Software Design

by John Ousterhout

[Link to book](https://web.stanford.edu/~ouster/cgi-bin/book.php)

<p align="left">
    <img src="/blog/img/books/philosophy_of_software_design.jpg" alt="philosophy_of_software_design" class="medium-zoom-image" width="200">
</p>

A fabulous book to level up your software design skills as an experienced
engineer. The book is full of great advice. You often will relate to your own
experiences (and mistakes). It mainly speaks about how to manage **complexity**
with _information hiding_, _low coupling_ and _high cohesion_.

Highlights:

- shallow vs deep modules
- tactical vs strategic programming
- Design it twice
- Importance of good naming
- Importance of good code comments
- Avoid _Classitis_
- Define errors out of existence

### The Pragmatic Programmer

by Dave Thomas and Andrew Hunt

[Link to book](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/)

<p align="left">
    <img src="/blog/img/books/pragmatic_programmer.jpg" alt="pragmatic_programmer" class="medium-zoom-image" width="200">
</p>

_The Pragmatic Programmer_ is a whirlwind of highly effective programming
practices and a great addition to _A Philosophy of Software Design_. It focuses
more on practical programming skills and teaches how to approach programming
problems in general. Even after twenty years, it is highly relevant today.

Highlights:

- DRY principle
- Orthogonal design
- Rubber Ducking
- Broken window theory
- Programming by Coincidence
- Design by Contract
- Importance of Tooling (shell, text manipulation, editor, IDE, version control
  etc.)

### Code Complete

by Steve McConnell

[Link to book](https://en.wikipedia.org/wiki/Code_Complete)

<p align="left">
    <img src="/blog/img/books/code_complete.jpg" alt="code_complete" class="medium-zoom-image" width="200">
</p>

An all-time classic about all facets of programming. In the 90s and the early
2000s, _Code Complete_ was the undisputed **programming bible**. If you want to
learn about the ins and outs of programming, this book is for you.

Highlights:

- High-Quality Functions
- High-Quality Classes
- High-Quality Software Design
- Good Comments
- Code Tunings
- Testing
- Debugging
- **Pseudo Programming Process (PPP)**, an almost forgotten alternative to TDD

### Clean Code

by Robert C. Martin

[Link to book](https://www.oreilly.com/library/view/clean-code-a/9780136083238/)

<p align="left">
    <img src="/blog/img/books/clean_code.jpg" alt="clean_code" class="medium-zoom-image" width="200">
</p>

_Clean Code_ will teach you good programming techniques and styles. It coined
the term _clean code_ and is a great read for junior and medium programmers.
Unfortunately, it aged quite heavily and is has it quirks. What bugged me the
most are the lengthy and hard to comprehend code examples. My personal pet peeve
is the
[Clean Code prime generator](https://gist.github.com/gerlacdt/41cf41c1f32093ca2866d35dffc88481)
. Compare this with my
[pragmatic implementation](https://gist.github.com/gerlacdt/772c86b2f592a16ea6303defaf74974f).
On top of that, the mantra-like writing style can cause furious discussions at
work if teammates treat the book as their "bible". Nevertheless _Clean Code_
taught generations of programmers and is rightly considered a classic.

Highlights:

- Naming things
- Good functions
- Good Classes
- Good/Bad Comments
- TDD flow and Unit Tests
- **SOLID principles** (contained in the book but the catchy name was invented
  later)

### Refactoring

by Martin Fowler

[Link to book](https://martinfowler.com/books/refactoring.html)

<p align="left">
    <img src="/blog/img/books/refactoring.jpg" alt="refactoring" class="medium-zoom-image" width="200">
</p>

Martin Fowler is a giant in modern Software Engineering. He was involved in
countless seminal contributions in the last decades. For me _Refactoring_ had
the most impact. The biggest part of the book is a catalog of refactoring
strategies classified into categories like encapsulation, API, inheritance etc.
But the most important thing is: The book teaches _how to think as an effective
programmer_. More so, _Refactoring_ pinpoints a clear way of writing code
efficiently, always focusing on keeping code complexity low.

Highlights:

- Refactoring as a design tool
- Writing code efficiently and pragmatically
  - small code changes which are easily reversible
  - If you cannot add a feature conveniently, refactor first
  - If code is never touched, refactoring is probably a waste of time
  - refactoring first, performance tunings later (it's much easier to tune clean
    code)
  - "Always leave the code base healthier than when you found it."
  - "If someone says their code was broken for couple of days while they are
    refactoring, you can be pretty sure they were not refactoring."
- Teaches the coherence of Refactoring and TDD
  - refactoring without tests is **not** refactoring
  - changing tests during refactoring is a sign of bad design or brittle tests
- Detect and Eliminate Code Smells

### Test-Driven-Development

by Kent Beck

[Link to book](https://www.oreilly.com/library/view/test-driven-development/0321146530/)

<p align="left">
    <img src="/blog/img/books/tdd.jpg" alt="tdd" class="medium-zoom-image" width="200">
</p>

The best introduction into one of the game-changing programming techniques of
the last decades. It is an outstanding guide for TDD. Still, TDD is a big topic
and a thoughtless use of TDD could end up in a maintenaince burden. For an
outright view, you should consult other resources which consider fallacies like
brittle and flaky tests, overuse of mocks and long-running tests.

Highlights:

- Real Life Unit Test example (Money Class)
- xUnit Test Framework
- test patterns
  - test isolation
  - test first
  - test fixtures
  - learning an API or a framework with tests

### Design Patterns

by GoF aka Erich Gamma, John Vlissides, Ralph Johnson, Richard Helm

[Link to book](https://en.wikipedia.org/wiki/Design_Patterns)

<p align="left">
    <img src="/blog/img/books/gof.jpg" alt="gof" class="medium-zoom-image" width="200">
</p>

A seminal textbook from the 90s. Patterns build the foundation for well designed
OOP systems. The biggest achievement of design patterns is the facilitated
communication between engineers. It is no longer necessary to drone on
programming details but sharing the pattern name is enough.

Highlights:

- OOP design
- Recurring design patterns explained
  - Singleton
  - Visitor
  - Adapter
  - Builder
  - Command
  - Facade
  - Iterator
  - Observer

### Extreme Programming Explained

by Kent Beck

[Link to book](https://www.oreilly.com/library/view/extreme-programming-explained/0321278658/)

<p align="left">
    <img src="/blog/img/books/xp.jpg" alt="xp" class="medium-zoom-image" width="200">
</p>

The real book about **agile**. All commercial agile frameworks like Scrum,
Kanban and SAFe are based on _Extreme Programming (XP)_. The practices and
principles presented here are everlasting and can be applied in any contemporary
project.

> XP can exist without Scrum. Scrum cannot exist without XP.

Highlights:

- Pair Programming
- Continuous Integration CI/CD
- Daily Deployment
- Cross-functional teams
- Incremental Builds
- Testing early, often and automated (TDD)
- Customer Involvement
- Sustainable Pace

### Domain Driven Design

by Eric Evans

[Link to book](https://www.domainlanguage.com/ddd/)

<p align="left">
    <img src="/blog/img/books/ddd.jpg" alt="ddd" class="medium-zoom-image" width="200">
</p>

A classic from the early 2000s. It coined the term Domain Driven Design (DDD)
and is still the best way to structure medium- to large-sized business
application. With the rise of microservices, the most influential concepts like
_Strategic Design_, _Ubiquitous Language_ and _Bounded Context_ are even more
relevant today. Besides the standard tactical design notions like Entities,
Aggregates, Services and Repositories, you can find great refactoring advice
too, e.g. _Intention-Revealing Interfaces_, _Side-Effect-Free functions_,
_domain class invariants with explicit constraints_ etc. Alas, DDD is verbose,
luckily a more condensed version with the essential ideas can be found
[here](https://matfrs2.github.io/RS2/predavanja/literatura/Avram%20A,%20Marinescu%20F.%20-%20Domain%20Driven%20Design%20Quickly.pdf).

Highlights:

- Strategic Design
  - Bounded Context
- Ubiquitous language
- Layered Architecture
  - Modules
  - Entities, Value Objects, Aggregates
  - Services
  - Repositories
  - Factories
- refactorings:
  - intention-revealing interfaces
  - domain class invariants

### The Mythical Man-Month

by Fred Brooks

[Link to book](https://en.wikipedia.org/wiki/The_Mythical_Man-Month)

<p align="left">
    <img src="/blog/img/books/myth_month.jpg" alt="myth_month" class="medium-zoom-image" width="200">
</p>

The last book in the _Software Engineering_ category but the most influential
one. Brooks wrote down his experience of leading the development of IBM's
gigantic OS/360 operating system. The project effort was roughly
[5000 man years](https://about.sourcegraph.com/blog/the-ibm-system-360-the-first-modular-general-purpose-computer).
The book is full of famous quotes, contains seminal _Software Engineering_ ideas
and is clearly a must-read for anyone working in the IT area. After forty years,
highly relevant today!

Highlights:

- _Adding manpower to a late software project makes it later._
- _Nine women can't make a baby in one month._
- Second System Effect (aka over-engineering)
- No Silver Bullet
- Plan to throw one away (aka prototyping)
- Surgical Team (aka small cross-functional teams)

## Programming

### Structure and Interpretation of Computer Programs (SICP)

by Harold Abelson and Gerald Jay Sussman with Julie Sussman

[Link to book](https://mitp-content-server.mit.edu/books/content/sectbyfn/books_pres_0/6515/sicp.zip/index.html)

<p align="left">
    <img src="/blog/img/books/sicp.gif" alt="sicp" class="medium-zoom-image" width="400">
</p>

**The best programming book ever written**, period. The book is the best
introduction to programming and will teach you all basic concepts a programmer
must know. This includes expressions, procedures, recursion, evaluation and lot
more. Advanced concepts are covered too, like lazy streams, memoization and
concurrency. Exercises at the end of all chapters help you to revise and deepen
the acquired knowledge. A highlight is the implementation of some OOP concepts
like polymorphism and encapsulation from scratch. Experienced Java or C++
developers will be fascinated how simple it can be!

Nowadays there is also a contemporary
[Javascript edition](https://mitpress.mit.edu/9780262543231/structure-and-interpretation-of-computer-programs/),
but I myself prefer Scheme.

Highlights

- Great way to learn programming
- Learn timeless programming practices that stick with you forever
  - Pure functions, the assignment statement is introduced at page 220!
- Lisp expands your horizon
- Write an Interpreter and Compiler (understand how a programming language
  works)

> LISP is worth learning for a different reason — the profound enlightenment
> experience you will have when you finally get it. - Eric S. Raymond

### Paradigms of AI Programming (PAIP)

by Peter Norvig

[Link to book](https://github.com/norvig/paip-lisp)

<p align="left">
    <img src="/blog/img/books/paip.jpg" alt="paip" class="medium-zoom-image" width="200">
</p>

**The second best programming book ever written** and in a way a continuation of
SICP for experienced developers. It contains a lot of code, mainly solving AI
problems. The code is full of great advice applicable to modern general
programming. You will learn Common Lisp which is eye-opening for itself. Further
you are guided how a software developer builds his own tools like logging,
debugging and even more important how a developer tackles bigger, complex tasks.
Norvig himself is a terrific coder. His code is the most elegant I ever read. He
also maintains a Github repository called
[pytudes](https://github.com/norvig/pytudes) where he collects nifty Python
programs. For example you can find there Norvig's exquisite
[AdventOfCode](https://adventofcode.com/) solutions.

Highlights:

- Enjoy one of the most elegant codebase ever written
- Learn Lisp
- Create an OOP system from scratch in a few hundred lines of code!
- Write famous classic AI systems: Eliza, Search Algorithms, Solving algebraic
  equations
- Advanced programming techniques:
  - write a Scheme interpreter
  - make it fast as a Compiler
  - Performance optimizations like Memoization, Compilation

### The Little Schemer

by Daniel P. Friedman and Matthias Felleisen

[Link to book](https://mitpress.mit.edu/9780262560993/the-little-schemer/)

<p align="left">
    <img src="/blog/img/books/little_schemer.avif" alt="little_schemer" class="medium-zoom-image" width="200">
</p>

A book about _recursion_. Or let me rephrase: **The** book about recursion.
After you get used to the tutorial question-and-answer style, _The Little
Schemer_ is the best resource to understand recursion. At the very end, you will
derive the [Y-Combinator](https://catonmat.net/derivation-of-ycombinator), which
allows anonymous function recursion. A mind-bending experience!

> In order to understand recursion, one must first understand recursion. -
> Anonymous

### Introduction to Functional Programming 1st Edition

by Bird Wadler

[Link to book PDF](https://doc.lagout.org/programmation/Functional%20Programming/Richard_Bird%2C_Philip_Wadler-Introduction_to_Functional_Programming_%28Prentice_Hall_International_Series_in_Computing_Science%29-Prentice_Hall%281988%29.pdf)

<p align="left">
    <img src="/blog/img/books/intro_func.jpg" alt="intro_func" class="medium-zoom-image" width="200">
</p>

According to
[Erik Meijer](https://twitter.com/headinthebox/status/1511478467421884418), the
best introduction to _functional programming_. For learning purposes, the purely
functional programming language Miranda is used. Miranda is an ancestor of
_Haskell_, both belong to the
[ML family](<https://en.wikipedia.org/wiki/ML_(programming_language)>). Once you
get the hang of it, it makes you a better programmer in any language.

Highlights:

- first class function, high-order functions
- map/filter/reduce
- Recursive Data Types (Lists, Trees)
- Currying
- Pattern Matching
- Lazy Evaluation/Data Structures

### Programming Pearls

by Jon Bentley

[Link to book](https://www.oreilly.com/library/view/programming-pearls-second/9780134498058/)

<p align="left">
    <img src="/blog/img/books/prog_pearls.jpg" alt="prog_pearls" class="medium-zoom-image" width="200">
</p>

Timeless programming lessons packaged in short essays about excellently selected
programming topics like data structures, algorithms, sorting, searching,
performance. Bentley's writings are from a time when cpu and memory were scarce
and performance optimization were mandatory. But such circumstances make the
solutions even more fascinating. The problem treatments are eye-opening and most
importantly they are entertaining. The best thing is that all chapters end with
crispy exercises in order to intensify your knowledge.

Fun fact: Bentley writes about creating a _test harness_ -- long before TDD was
a thing.

### The Algorithm Design Manual

by Steven Skiena

[Link to book](https://www.algorist.com/)

<p align="left">
    <img src="/blog/img/books/algo_design_manual.jpg" alt="algo_design_manual" class="medium-zoom-image" width="200">
</p>

Data Structures and Algorithms are the bread and butter for every programmer.
Hence a good reference book is essential. I chose _The Algorithm Design Manual_
because it provides the best combination of theory and practice.

The book is structured in two parts. The first one is an introduction to
algorithm design, _Big O notation_ and standard topics like data structures,
sorting, searching, recursion, dynamic programming and graphs. For good measure,
each chapter ends with exercises perfectly suited for your coding interview
preparation. The second part is the real gem: a wide and well-structured catalog
of algorithms and data structures with graphical representations that make
searching a breeze. It is actually fun to just browse the catalog because you
encounter interesting things at every turn.

A good alternative is
[Introduction to Algorithms](https://mitpress.mit.edu/9780262046305/introduction-to-algorithms/).

### The Art Of Programming

By Don Knuth

[Link to book](https://en.wikipedia.org/wiki/The_Art_of_Computer_Programming)

<p align="left">
    <img src="/blog/img/books/art_of_programming.png" alt="art_of_programming" class="medium-zoom-image" width="200">
</p>

No programming book list is complete without Knuth's magnum opus. Knuth himself
received the Turing Award in 1974 and he is generally considered one of the
greatest Computer Scientist ever. If you want to go deeper than _The Algorithm
Design Manual_, _The Art Of Programming_ is the way to go.

Historic fun fact: Because Knuth was not satisfied with the typesetting systems
in the 70s, he invented [TeX](https://en.wikipedia.org/wiki/TeX) as a
by-product. To the present day, [LaTeX](https://en.wikipedia.org/wiki/LaTeX), a
descendant of TeX, is the prevalent typesetting system in academia for math,
physics, chemistry and computer science.

## Brian Kernighan's Books

Kernighan is a master in technical writing. I would go so far to claim that
without his outstanding documentation about Unix and the C programming language,
both technologies would not become so successful so fast. Good documentation is
crucial for computer programs to thrive and to build up a community. He calls
[Ken Thomson](https://en.wikipedia.org/wiki/Ken_Thompson) a
["singularity in programming"](https://www.youtube.com/watch?v=O9upVbGSBFo), I
call Kernighan a "singularity in technical writing".

### The C Programming Language

By Brian W. Kernighan, Dennis Richie

[Link to book](https://www.cs.princeton.edu/~bwk/cbook.html)

<p align="left">
    <img src="/blog/img/books/cbook.jpg" alt="cbook" class="medium-zoom-image" width="200">
</p>

The book has a legendary status and needs no introduction. It offers a concise
and terse tour through the complete C programming language and sets the standard
for programming language reference manuals.

Fun fact: The famous **Hello, world!** program originates from this book.

### The Practice Of Programming

By Brian W. Kernighan, Rob Pike

[Link to book](https://www.cs.princeton.edu/~bwk/tpop.webpage/)

<p align="left">
    <img src="/blog/img/books/practice_programming.jpg" alt="practice_programming" class="medium-zoom-image" width="200">
</p>

A compendium of ageless programming practices, relevant for any programming
language and for any skill level.

Highlights:

- Coding Style
- Data Structures and Algorithms
- Designing Programs
- Traits of good APIs/Interfaces
- Debugging practices
- Testing practices
- Performance, Profiling

### The Unix Programming Environment

By Brian W. Kernighan, Rob Pike

[Link to book](https://www.cs.princeton.edu/~bwk/upe/upe.html)

<p align="left">
    <img src="/blog/img/books/unix_env.jpg" alt="unix_environment" class="medium-zoom-image" width="200">
</p>

Old but Gold! A great guide into the Unix Programming Environment. If you use
the shell regularly, it is a fantastic way to get more productive. And if you
don't use the shell, it will convince you that you do!

### Software Tools in Pascal

By Brian W. Kernighan, P.J. Plauger

[Link to book](https://www.amazon.com/Software-Tools-Pascal-Brian-Kernighan/dp/0201103427)

<p align="left">
    <img src="/blog/img/books/sw_tools.jpg" alt="software_tools_pascal" class="medium-zoom-image" width="200">
</p>

A little forgotten and underrated book, but one of my favorites. The advice in
the book is priceless. It explains how to think as a productive programmer, how
to approach larger programming tasks from the ground up and how to maintain an
ever-growing codebase by using modularization and clear API design based on the
[Unix philosophy](https://en.wikipedia.org/wiki/Unix_philosophy). Working
through the book, you will build common Unix tools from scratch. I sometimes use
this book in order to learn a new programming language because the exercises are
universally applicable and the writing is so enjoyable.

Highlights:

- Build your own Unix tools
  - cat
  - find
  - grep
  - a complete editor

## Modern Classics

### Software Engineering At Google

by Titus Winters, Tom Manshreck and Hyrum Wright

[Link to book](https://abseil.io/resources/swe-book)

<p align="left">
    <img src="/blog/img/books/swe_at_google.jpg" alt="swe_at_google" class="medium-zoom-image" width="200">
</p>

A must-read if you work in the software engineering field. The book contains it
all: engineering principles, practices, culture, tools, technologies and even
management topics for **building and maintaining modern software of any scale
efficiently and effectively over time**.

And the best, it's [free!](https://abseil.io/resources/swe-book)

Highlights:

- Gives a glimpse how Google builds software

### Designing Data-Intensive Applications

by Martin Kleppmann

[Link to book](https://dataintensive.net/)

<p align="left">
    <img src="/blog/img/books/dataintensive.png" alt="dataintensive" class="medium-zoom-image" width="300">
</p>

You work in a highly distributed IT landscape with microservices? Then this is a
mandatory read for you. Kleppmann magically connects dry distributed systems
theory with practical examples and lies down a lucid plan how to pick the best
solutions for building real-world applications.

Highlights:

- Query-Languages
- Data Storage Engines
- Encoding Formats (JSON, Protocol Buffers, Thrift, Avro)
- Data Replication and Partitioning
- Transactions, ACID
- Consensus
- Big Data, Batch/Stream Processing

### The Software Architect Elevator

by Gregor Hohpe

[Link to book](https://architectelevator.com/book/)

<p align="left">
    <img src="/blog/img/books/architectelevator.jpg" alt="architectelevator" class="medium-zoom-image" width="200">
</p>

With the rise of _Agile_ and evolutionary architecture, architects are not
needed anymore. Wrong! UML diagrams and PowerPoint slides are obsolete, but
modern architects ride the _Software Architect elevator_, i.e. this new kind of
architect connects the penthouse (C-levels) with the engine room (software
engineers) and all the levels in between. The book is worth reading just because
of Gregor's awesome metaphors. You find a great teaser in his awesome
[introductory talk](https://www.youtube.com/watch?v=Zq2VcRZmz78).

Besides metaphors, architects will be equipped with tools to successfully drive
digital transformations in large enterprises, detect organizational bottlenecks
and unfold complexity in systems of any kind like IT systems or an organization
itself. That said, it is a rewarding book for everyone working in IT, not only
for (future) architects.

### Accelerate

by Nicole Forsgren, Jez Humble, Gene Kim

[Link to book](https://itrevolution.com/accelerate-book/)

<p align="left">
    <img src="/blog/img/books/accelerate.webp" alt="accelerate" class="medium-zoom-image" width="200">
</p>

The **DevOps** book. Based on a four-year research, _Accelerate_ presents the
results on what makes up an efficient software company. You learn about
practices to improve software development and delivery -- both main factors
boosting productivity, profitability and market share. At the core, the whole
book is directed towards building an high-performance DevOps culture.

Highlights:

- **Four Key Metrics**
  - _Lead Time_
  - _Deployment Frequency_
  - _Mean Time to Restore_
  - _Change Failure Rate_
- Strategies and concrete practices to become a more efficient software company
  - Automation
  - CI/CD
  - Automatic Testing
  - Customer Feedback
  - Value Stream
  - Working in small batches
  - WIP limits
  - Westrum organisational culture, Learning culture

## Learning

### A Mind For Numbers

by Barbara Oakley

[Link to book](https://barbaraoakley.com/books/a-mind-for-numbers/)

<p align="left">
    <img src="/blog/img/books/mind_for_numbers.jpg" alt="mind_for_numbers" class="medium-zoom-image" width="200">
</p>

My personal favorite. The book massively changed the way I approach and learn
new topics. I grasp them faster and more deeply. With the methodologies from the
book, learning is fun! The corresponding online course
[Learning how to lean](https://www.coursera.org/learn/learning-how-to-learn) is
freely available. As engineers, we never stop learning and this book is your
best companion for your learning endeavours.

Highlights:

- Short Term vs Long Term Memory
- Focused vs Diffused learning
- Chunking
- Spaced Repetition
- Pomodoro Technique
- Self-Testing

## Management and Leadership

### The Manager's Path

by Camille Fournier

[Link to book](https://www.oreilly.com/library/view/the-managers-path/9781491973882/)

<p align="left">
    <img src="/blog/img/books/manager_path.jpg" alt="manager_path" class="medium-zoom-image" width="200">
</p>

You want to take the next step in your career and want to approach a new
position as _engineering manager_? Or you are already in a leading position and
want to improve? Then this book is for you. It is a must-read for every manager.
_The Manager's Path_ provides clear consultation about effective team management
with tangible practices and real-world scenarios.

You want to stay an engineer? Fine!_The Manager's Path_ is a great for you too.
It illustrates what you can expected from good managers, helps you detect and
solve team-related problems or to enable other people. The extended perspective
will make you a more precious employee for your organization, e.g with
_mentoring_ you can make a big difference:

> How to be a 10x engineer: help ten other engineers be twice as good. -
> [Peter Seibel](https://twitter.com/peterseibel/status/512615519934230528?lang=en)

Highlights:

- 1-1s
- Mentoring
- Conflict Management
- Decision Making
- Debugging dysfunctional teams
- Dealing problematic team members (brilliant jerk, non-communicator)
- Covers the whole carrier path from managers to tech leads to directors to CTOs

### Leadership Strategy and Tactics: Field Manual

by Jocko Willink

[Link to book](https://www.amazon.com/Leadership-Strategy-Tactics-Field-Manual/dp/1250226848)

<p align="left">
    <img src="/blog/img/books/leadership_strategy.jpg" alt="leadership_strategy" class="medium-zoom-image" width="200">
</p
>

Jocko Willink is a former Navy Seal and mastered many life-and-death war
situations. His leadership strategies are based on military operations but they
are transferable to everyday situations. Leadership is not about a role, but
about action and behaviour. Everyone can lead and push his team to better
results.

Like _The Manager's Path_, this book focuses on soft skills, especially on
leading people in the most successful way to achieve a common goal. Technical
hard skills are important but to be most valuable for your team, you need to
take ownership and start to lead. _Leadership Strategy and Tactics_ gives you
the tools and the guidance for taking over leadership.

> Overly focusing on tech is how you end up with the skills of a senior engineer
> and the experience of a junior human being. -
> [Kelsey Hightower](https://twitter.com/kelseyhightower/status/1393194153681440780?lang=en)

## Unix Books from Stevens

If you want to deep dive into Unix programming, Network/Socket programming or
learn about Network Protocols and their implementations, look no further. The
following mentions are all seminal textbooks in their field and will serve you
as learning material as well as reference manuals.

### Advanced Programming in the UNIX Environment

by W. Richard Stevens

[Link to book](http://www.apuebook.com/)

<p align="left">
    <img src="/blog/img/books/apue.jpg" alt="apue" class="medium-zoom-image" width="200">
</p>

### Unix Network Programming: The Sockets Networking API Vol 1

by W. Richard Stevens

[Link to book](http://www.kohala.com/start/)

<p align="left">
    <img src="/blog/img/books/network_prog.jpg" alt="network_prog" class="medium-zoom-image" width="200">
</p>

### TCP/IP Illustrated: The Protocols, Volume 1

by W. Richard Stevens

[Link to book](http://www.kohala.com/start/)

<p align="left">
    <img src="/blog/img/books/network_protocol.gif" alt="network_protocol" class="medium-zoom-image" width="200">
</p>
