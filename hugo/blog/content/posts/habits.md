---
title: "Top 10 Practices Of Effective Software Engineers"
date: 2023-04-07T11:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: true
---

## TL;DR

1. [Write code](#write-code)
1. [Read Code](#read-code)
1. [(Written) Communication](#written-communication)
1. [Learn concepts -- not technologies](#learn-concepts----not-technologies)
1. [Learn Lisp](#learn-lisp)
1. [Master your tools](#master-your-tools)
1. [Control complexity (Simplicity)](#control-complexity)
1. [Read books (but don't forget to practice)](#read-books)
1. [Understand the problem domain](#understand-the-problem-domain)
1. [Learn to write good tests (or how to design well and write decoupled code)](#learn-to-write-good-tests-or-how-to-design-well-and-write-decoupled-code)

### Introduction

I am always pondering what practices, tactics, behaviours and approaches make up
the most successful software engineers. After 15 years working in the industry,
in small and big teams, with waterfall and agile processes, and after thousands
of written lines of code, I will present here an opinionated way of how to
become an effective engineer.

## Write Code

The most important thing in order to excel in programming is to practice
regularly and write a lot of code. One cannot learn programming by only reading
books or manuals.

Peter Norvig's seminal article _Teach yourself Programming in Ten Years_
[[1]](http://norvig.com/21-days.html) is the perfect guide for your journey to
become a well-versed programmer. According to Norvig, learning to program is a
marathon. To master a craft, it takes roughly ten thousands hours of practice.
Daily exercises and challenging projects are necessary to improve oneself.

Programming is a team sport. Consequently the best way to learn is working on
projects with other programmers. Thereby, be the best programmer in one project
and mentor other team members. In another project, be the worst programmer and
learn from your peers. Talk to other developers and study their practices. How
do they approach problems, how do they structure their code, how do they think
and what manners make them successful? Learn to work in different kinds of
projects, e.g. start a green-field project and design the system from scratch or
dive into a big, legacy codebase and maintain it for some time. Different
project types will demand different skill sets from you. Also strive for direct
feedback and be open for criticism from other developers.

> _The only way to learn a new programming language is by writing programs in
> it._
>
> **Dennis Ritchie**

> _Computer science education cannot make anybody an expert programmer any more
> than studying brushes and pigment can make somebody an expert painter._
>
> **Eric S. Raymond**

## Read Code

Code reading is as important as code writing. Maybe even more important
[[2]](https://blog.codinghorror.com/when-understanding-means-rewriting/) because
developers spent much more time on reading code than writing code.

<p align="center">
    <img src="/img/code_reading.png" alt="code_reading" class="medium-zoom-image" width="400">
</p>

Code Reading skills are crucial in order to get familiar with a new codebase, to
perform code reviews or just to understand the existing codebase to make a
change.

Besides that, code can be enjoyable and thrilling like a good book. Nowadays
with Github and other Open-Source platforms, code is ubiquitously available. By
reading open-source, you get in touch with idiomatic code written by world-class
engineers and you will automatically improve your own coding style. Some
excellent codebases are:

- [Golang Stdlib](https://pkg.go.dev/std)
- [Java Zookeeper](https://github.com/apache/zookeeper)
- [Rust ripgrep](https://github.com/BurntSushi/ripgrep)

If you have time to dip into your favorite library or framework, you will
definitely gain some knowledge and aha moments . Reading the library code can
debunk its _magic_ and you can connect the dots. (thinking of
[Spring Boot](https://github.com/spring-projects/spring-boot) and
[RubyOnRails](https://github.com/rails/rails))

## (Written) Communication

> _The craft of programming begins with empathy, not formatting or languages or
> tools or algorithms or data structures._ - **Kent Beck**

Most projects fail, not because of the wrong technology or resources, but
because of failed communication inside and across teams. Wrongly communicated
requirements, wrongly communicated expectations and missed alignments are
frequent project killers.

Since software engineering is a team sport. For that reason verbal and written
communication skills are critical. Communication is daily business and happens
in all kinds of occasions:

- Pair Programming, a good session requires a lot of discipline by all
  participants

- Code review, good review comments express intentions clearly and respect the
  author

- Mentoring

- Convincing/Advocating

- Decision-Making process

- Writing Design Docs

- Writing software requirements

Written communication is particularly important, famous companies like
[Amazon](https://www.mad.co/insights/the-mad-six-pager) and
[Hashicorp](https://works.hashicorp.com/articles/writing-practices-and-culture)
have an established writing culture which play a major role in their success
stories.

Not only, we need skills for creating content but also for consuming content.
Today, all of us get swamped with information via Emails or Slack messages.
Highly efficient developers process textual information quickly, organize the
information in an accessible way and are able to filter the important facts. It
is beneficial to invest some time into
[_speed reading_](https://en.wikipedia.org/wiki/Speed_reading) or other
techniques which let you consume content faster.

Maybe you think now of counter examples like Linus Torvalds (creator of Linux)
or Guido van Rossum (creator of Python). Either of them pulled off his
respective project almost alone but this is only true for the inception phase.
On the long-run they created an highly active and vivid community which
basically was the result of their great communication skills. Guido is known as
the _benevolent dictator_, Linus is famous for his harsh but direct comments in
the Linux Community and his ruthless clean code obsession.

## Learn concepts -- not technologies

New programming languages emerge all the time, the Javascript ecosystem breeds
more and more npm-packages and all kind of technologies spring into life. We can
never keep up with everything - and it also make no sense. On the contrary, it
is very valuable to put in an effort to master the concepts behind technologies.
**By understanding the foundational concepts, you grasp the problems more
thoroughly and hence it enables you to learn related technologies much faster**.
Without a good understanding of the primary concepts, one cannot utilize
corresponding technologies adequately. A good example is Node.js, I encountered
a lot of codebases where it was clear that _async programming_ and the _Event
Loop_ were misunderstood because CPU-intensive tasks were executed inside the
single main-thread which led to blocked requests and service outages.

_Software Engineering_ concepts are timeless and can be applied to a certain
extent in any programming language. _Functional programming_ is my favorite
because it makes your code more readable, testable and maintainable.
Incidentally with a bit of effort, one can write functional code in most
programming languages. Knowing such a powerful concept, ultimately improves your
coding style in general since you are aware of high-order functions, pure
functions and immutable (inherently thread-safe) data structures.

Highly recommended concepts:

<p align="center">
    <img src="/img/concepts.png" alt="concepts_to_learn" class="medium-zoom-image" width="800">
</p>

Learning and grasping underlying concepts is crucial but you cannot gain a full
understanding without practicing and experimenting. Therefore you must choose a
suitable technology and use it accordingly. For programming languages concepts,
_Lisp_ is a perfect candidate :smiling_face_with_hearts:

## Learn Lisp

> _LISP is worth learning for a different reason — the profound enlightenment
> experience you will have when you finally get it. That experience will make
> you a better programmer for the rest of your days, even if you never actually
> use LISP itself a lot._
>
> **Eric Steven Raymond** [[3]](http://www.catb.org/~esr/faqs/hacker-howto.html)

Lisp empowers you to learn the most important concepts in computer programming
with a single programming language. For example, Lisp supports _procedural,
functional and object-oriented programming_. You learn _recursion_ and get in
touch with the power of _interactive programming with a REPL (Read-eval-print
loop)_. Further Lisp offers the most terrific _meta-programming_ experience with
its macro-system enabled by _homoiconicity with S-expressions_. Homoiconicity
means that Lisp code is constructed of Lisp data-structures. Hence you are able
to manipulate Lisp code via Lisp code. A mind-bending experience, if you only
programmed in Java or C# all along. With this power at your fingertips, you can
implement your own _OOP system_ with objects, classes, inheritance and
information hiding in a few hundred lines of Lisp code. For traditional
programming languages like C, adding Object Orientation was an enormous PhD task
which took multiple years (remember C++). In Lisp it is an undergrad assignment
which takes a few days! :exploding_head: Implementing concepts yourself is the
best way to learn them, that is where Lisp really shines.

> _What I cannot build. I do not understand._ - **Richard Feynman**

If we dig a bit in history, we'll find out that Lisp is the second oldest
programming language, invented in 1958. But Lisp was the first programming
language which introduced ground-breaking features like: if-then-else (even
if-clauses had to be invented back then), recursive functions, garbage
collection, REPL interactive programming, macros, and modifying code at runtime
from million miles away [[4]](https://youtu.be/_gZK0tW8EhQ). You will figure out
that many "new" features of Java or C# are just copied from Lisp.

<p align="center">
    <img src="/img/lisp_cycles.png" alt="lisp_cycles" class="medium-zoom-image" width="600">
</p>

Unfortunately, Lisp's powerful abstractions are bane and boon. The ease to
implement an OOP system, a package manager or a unit test library led to
hundreds of implementations whereby none of them established a standard. Lisp
focuses on the highest productivity for an individual. Modern _engineering
languages like Go or Rust_ are optimized for team productivity and put their
focus on less abstractions but maintainability.

Last but not least, two of the best programming books use Lisp: _Structure and
Interpretation of Computer Programs_
[(SICP)](http://groups.csail.mit.edu/mac/classes/6.001/abelson-sussman-lectures/)
and _Paradigms of Artificial Intelligence Programming_
[(PAIP)](https://github.com/norvig/paip-lisp). SICP is the best way to learn
programming and with PAIP you will enjoy one of the most elegant codebases ever
written.

## Master your tools

Just like a carpenter must be proficient with a hammer and a saw, a software
engineer must be proficient with his tools. The most prominent developer tools
are the operating system (OS), the programming environment composed of an
IDE/editor, a compiler, a build system, a version control system and the
programming language itself. Even an Internet browser and "knowing how to
google" are parts of a well rounded toolset. Some example no-brainer tasks:

- search-and-replace in files
- navigate fast through a codebase
- branching, merging, resolving conflicts with a version control system
- building and testing your code
- writing idiomatic code in your favorite programming language
- understanding compiler error-stacktraces
- administrating your local machine with our favorite OS
- googling a programming question

Software tools are important for an apt programmer, but he should also be
trained to use any kind of hardware judiciously, e.g. touch typing with a
keyboard. Not only touch typing makes you a faster typist, but you will also
detect typos faster on the screen which will lead to a faster feedback loop.
Muscle Memory ensures you can focus on the problem instead of wasting brain
power for typing.

Mastering all these tools is vital for becoming productive. Thereby it is not
relevant which specific OS (Linux, Windows) or editor (Emacs, Vim, or an IDE)
you adapt. Nevertheless I recommend to invest your efforts in perpetuating tools
such as the terminal or classic editors like Vim and Emacs. These tools exist
since the "dawn" of computing and probably will be supported forever. Even if
they died, modern tools would build upon their legacy and your learning efforts
would not be wasted. For instance, Vim keybindings are now supported by most of
the popular IDEs. Learning such everlasting tools will prevent you from
time-consuming tool switching.

## Control complexity

A software engineer must always strive for **simplicity**. Continuously managing
and controlling complexity is the only way to keep ever-growing projects
maintainable. It is a crucial aspect of our craft:

> _Controlling complexity is the essence of computer programming._
>
> **Brian Kernighan, P. J. Plauger, Software Tools (1976)**

> _The purpose of software engineering is to control complexity, not to create
> it._
>
> **Pamela Zave**

> _The art of programming is the art of organizing complexity, of mastering
> multitude and avoiding its bastard chaos as effectively as possible._
>
> **Edsger W. Dijkstra, Notes on Structured Programming (1970)**

Unfortunately, simple solutions are hard, often much harder than easy
workarounds or shortcuts which are near at hand - simple is not easy
[[5]](https://www.youtube.com/watch?v=SxdOUGdseq4). Software engineers are
permanently tainted by "easy" solutions due to time pressure or "it just works"
mentality. They must resist the urge for such solutions by all means. Complexity
is a treacherous beast which does not come with one big bad decision but creeps
into the project by hundreds of tiny decisions made on a daily basis. It also
boils down to work ethic: are you satisfied with any working solution or do you
go the extra mile and make it _simple_?

> _The definition of genius is taking the complex and making it simple._
>
> **Albert Einstein**

Our main job as software engineers is to retain a project's maintainability
forever which plays nicely together with controlling complexity:

> _Software Engineering encompasses not just the act of writing code, but all of
> the tools and processes an organization uses to build and maintain that code
> over time._
>
> **Software Engineering At Google**

Complexity comes as in two flavours: _essential complexity_ and _accidental
complexity_. Essential complexity is the complexity of the problem itself and
accidental complexity is everything else we add on top with our solution, e.g.
solving the wrong problem, wrong technology used, over-engineering, bad software
design etc.

In _A Philosophy of Software Design_
[[6]](https://web.stanford.edu/~ouster/cgi-bin/book.php) John Ousterhout defines
the main symptoms and causes of software complexity. There are three symptoms.
First _change amplification_: a simple change causes changes in a lot of
different places. Second _high cognitive load_: one must know a lot about the
whole system in order to make a small change. Third and the worst symptom is
_unknown unknowns_: one does not even know all places where changes are
necessary to implement a feature. All these symptoms are caused by _uncontrolled
code dependencies_ and _obscure code_. Simplicity is the opposite: good
modularization restricts information sharing with information hiding and
encapsulation, **obvious** code is easy to comprehend. Alas, most projects
accidentally end up as a _big ball of mud_
[[7]](http://www.laputan.org/mud/mud.html#BigBallOfMud). A result of reckless
information sharing between dependencies and hard to understand, obscure code.

<p align="center">
    <img src="/img/complexity-meme.jpg" alt="complexity-meme" class="medium-zoom-image" width="400">
</p>

Other causes of complexity are any form of **state**
[[8]](https://curtclifton.net/papers/MoseleyMarks06a.pdf) and feature bloat.
_Stateless Design_ is so desirable and successful because it is much easier to
reason about, e.g. stateless microservices or stateless network protocols like
HTTP. Feature bloat has two main reasons. First, product management forces tech
teams to implement useless features [[9]](https://cr.yp.to/bib/1995/wirth.pdf)
which is usually the consequence of roadmap- or output-driven development. And
second, _gold-plating_ developers who implement potential, artificial
requirements which do not exist yet or over-engineer their design with too
flexible and complicated abstractions.

In conclusion, developers must manage complexity all the time. The only way to
hold complexity in check is continuous and unrelenting _refactoring_
[[10]](https://martinfowler.com/books/refactoring.html). Constantly clean up
your code after adding new features. I allow myself to "invent" my own acronym
for that process: **Continuous Clean Code Process (CCCP)** :rocket:

> The only way to go fast is to go well. - **Uncle Bob**

## Read Books

Writing is human's greatest invention to retain and share knowledge. Books are
the epitome of writing and the fastest way to absorb knowledge in a short time.
More often than not, book authors are the most experienced engineers who share
their wisdom, their solutions and last but not least their mistakes. The later
is very important, so you don't have to repeat them yourself:

> Those that fail to learn from history are doomed to repeat it. - **Winston
> Churchill**

Since books can be large, they can be time consuming to read that's why you have
to choose wisely. I recommend to focus on
[concepts and general principles](#learn-concepts----not-technologies). But
don't follow this advice too strictly. If you learn a new technology like a
programming language, surely it makes sense to read respective books. Although a
good foundational knowledge about programming concepts will massively facilitate
and accelerate your learning endeavours. For the curious reader, I shared my
favorite [books](../programming-books) and [papers](../classic-papers) in
previous articles.

_A Mind For Numbers_ [[11]](https://barbaraoakley.com/books/a-mind-for-numbers/)
is my all-time favorite book about the most important concept itself:
**learning**. It will change how you approach new material. By working your way
through it, you will become a more potent and motivated learner. An accompanying
[Coursera course](https://www.coursera.org/learn/learning-how-to-learn) is
freely available. Learning is a lifelong undertaking and every engineer should
embrace that.

<p align="center">
    <img src="/img/learning-too-old.jpg" alt="learning-too-old" class="medium-zoom-image" width="400">
</p>

## Understand the problem domain

Being the best programmer in the team is not enough. You will be most effective
as an engineer if you are able to connect technology with the business problem
your trying to solve. A comprehensive view over the problem scope generally
reveals completely different solution strategies.

Be excited about the business itself and related areas like marketing, sales,
product design etc. Try to speak the same language like the people in different
departments instead of bothering them with tech-jargon. It's not coincidence
that an _ubiquitous language_ is one of the most influential concepts from
Domain Driven Design [(DDD)](https://www.domainlanguage.com/ddd/blue-book/).

With the newly acquired background information you will be much more valuable
for your organization. For example, as an engineer you know what is possible and
what not, so you can turn down "impossible" requirements immediately instead of
wasting days or weeks only to come to the same conclusion. Vice versa, as an
engineer you know what is technologically feasible thus you can contribute to
breakthrough ideas nobody else is able to.

## Learn to write good tests

In modern software engineering automatic testing is now common practice like
version control or continuous integration. Unfortunately, just writing tests for
the sake of testing is not enough. Writing good tests is a delicate endeavour
and more often than not, typical test suites turn out to be flaky, brittle and
unmaintainable. In these situations, code changes break many tests which leads
to high maintenance effort. As a final resolution, teams do not refactor anymore
or just abandon tests completely! In contrast, good tests should enable and
encourage refactoring. We know already that refactoring is inevitable in order
to control complexity and to prevent a big ball of mud.

Brittle tests are caused by coupling the tests with the _system under test_
(SUT). This violates the main principles of good software design: _information
hiding_ and _decoupling_. Particularly, this is the fault of overusing mocks.
Mocking frameworks are affluently available in all programming languages and
very easy to integrate. But what most of the people do not know: **mocks couple
tests with the SUT implementation and make refactorings impossible**
[[12]](https://abseil.io/resources/swe-book). Worse yet, mocks require a lot of
setup code within tests themselves which pollutes them and makes them hard to
read. Instead of mocks you should use _Fakes_
[[13]](https://martinfowler.com/articles/mocksArentStubs.html#TheDifferenceBetweenMocksAndStubs)
whenever possible. By using fakes, the SUT implementation will not be exposed.
Tests remain decoupled and refactorings possible.

The corner pillars of good software design [[14]](https://www.poodr.com/) also
apply to tests: Decoupling, achieved by information hiding, encapsulation and
modularization, is crucial for a maintainable, coherent and long-living
codebase.

## Final Words

You made it! I hope you can relate to my selection of the most effective
engineering habits. It is a long list, but if you only have one take-away, the
most important point is: **practice regularly**. Programming, and learning in
general, is a marathon not a sprint. You will merely improve by continuous
exercising.

### References

1. [Teach yourself Programming in Ten Years - Peter Norvig](http://norvig.com/21-days.html)
1. [When Understanding means Rewriting - Jeff Atwood, Coding Horror Blog](https://blog.codinghorror.com/when-understanding-means-rewriting/)
1. [How to become a Hacker - Eric Steven Raymond](http://www.catb.org/~esr/faqs/hacker-howto.html)
1. [Lisp Remote Debugging From 60 Million Miles Away](https://youtu.be/_gZK0tW8EhQ)
1. [Simple Made Easy - Rich Hickey](https://www.youtube.com/watch?v=SxdOUGdseq4)
1. [A Philosophy of Software Design - John Ousterhout](https://web.stanford.edu/~ouster/cgi-bin/book.php)
1. [Big Ball Of Mud](http://www.laputan.org/mud/mud.html#BigBallOfMud)
1. [Out Of The Tar Pit - Ben Mosley, Peter Marks](https://curtclifton.net/papers/MoseleyMarks06a.pdf)
1. [A Plea for Lean Software - Niklaus Wirth](https://cr.yp.to/bib/1995/wirth.pdf)
1. [Refactoring - Martin Fowler](https://martinfowler.com/books/refactoring.html).
1. [A Mind For Numbers - Barbara Oakley](https://barbaraoakley.com/books/a-mind-for-numbers/)
1. [Software Engineering at Google - Titus Winters, Hyrum Wright and more](https://abseil.io/resources/swe-book)
1. [Mocks aren't stubs - Martin Fowler](https://martinfowler.com/articles/mocksArentStubs.html#TheDifferenceBetweenMocksAndStubs)
1. [Practical Object-Oriented Design - Sandi Metz](https://www.poodr.com/)
