---
title: "Top 10 Software Engineering Life Lessons"
date: 2023-01-23T11:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: true
---

## TL;DR

1. Write code
1. Read Code
1. Communication
1. Learn concepts, not technologies
1. Learn Lisp
1. Master your tools
1. Control complexity (Simplicity)
1. Read books (expand your horizon but don't forget to practice)
1. Understand the problem domain
1. Write \*good\* Tests (testing alone is not enough)

### Introduction

I am always pondering what practices, tactics, behaviours or approaches make up
a productive programmer. After 15+ years working as a software engineer, in
small and big teams, with waterfall or agile processes and after writing
thousands of lines of code, this article is my condensed treatment of what is
the best advice for being more effective and productive.

## Write Code

The most important thing in order to excel in programming is to practice
regularly and write a lot of code. I cannot emphasize this enough: _You need to
write a lot of code_. One cannot learn programming by only reading books or blog
posts.

Peter Norvig wrote a seminal article called
[Teach yourself Programming in Ten Years](http://norvig.com/21-days.html) which
is the perfect guide for your journey becoming a professional programmer.
According to Norvig, learning to program is a marathon. To master a craft, it
takes roughly ten thousands hours of practice. Daily exercises and challenging
project work is necessary to climb up to new heights and improve yourself.

Programming is a team sport. So the best way to learn is working on projects
with other programmers. Thereby, be the best programmer in one project and
mentor other team members. In another project, be the worst programmer and learn
from your peers. Talk to other developers and study their practices. How do they
approach problems, how to they structure their code, how do they think and which
behaviours make them successful? Learn to work in different kind of projects,
e.g. start a green-field project and design the system from scratch or maintain
a big, legacy codebase. Different project types will demand from you a different
skill sets. Also strive for direct feedback and be open for criticism from other
developers.

> "The only way to learn a new programming language is by writing programs in
> it." - Dennis Ritchie

> Computer science education cannot make anybody an expert programmer any more
> than studying brushes and pigment can make somebody an expert painter. – Eric
> S. Raymond

While producing new code is important, don't forget to read other peoples code,
see next.

## Read Code

Practicing code reading is as important as code writing. Maybe even more
important because developers spent much more time on reading code than writing
it, [see](https://blog.codinghorror.com/when-understanding-means-rewriting/):

<p align="center">
    <img src="/img/code_reading.png" alt="code_reading" class="medium-zoom-image" width="400">
</p>

Code Reading skills are crucial in order to get familiar with a new codebase, to
do Code Reviews or just to understand the existing codebase to make a change.

Besides that, code can be enjoyable and thrilling like a good book. Nowadays
with Github and other Open-Source platforms, code is ubiquitously available. By
reading it, you get in touch with pragmatic and idomatic code written by
professionals. Hence you will automatically improve your own coding style.
Example of good codebases are:

- [Golang Stdlib](https://pkg.go.dev/std)
- [Java Zookeeper](https://github.com/apache/zookeeper)
- [Rust ripgrep](https://github.com/BurntSushi/ripgrep)

If you have time to dip into your favorite library or framework, you will
definitely gain some knowledge and aha moments . Reading the library code often
can debunk its _magic_ and you can _connect the dots_. (thinking of
[Spring Boot](https://github.com/spring-projects/spring-boot) and
[RubyOnRails](https://github.com/rails/rails))

## Communication

> The craft of programming begins with empathy, not formatting or languages or
> tools or algorithms or data structures.- Kent Beck

Most projects fail not because of the wrong technology or resources but because
of failed communication inside and across teams. Wrongly communicated
requirements, wrongly communicated expectations and missed alignments are
frequent project killers.

Since software engineering is a team sport, communication skills are critical.
Two main types of communication exist: verbal and written communication. Both
are equally important. Communication is day-to-day work and happens in all kinds
of occasions:

- Pair Programming, a good session requires a lot of discipline required of both
  partners

- Code review, good review comments always respect the author

- Mentoring

- Convincing other parties to use a (better) solution, be objective and not
  biased for your _own solution_

- Be a team player. If your team decides for one solution, you can disagree but
  you must commit to the team's decision and bit the bullet (but try hard fist)

- Writing Design Docs

- Writing crystal clear requirements or Pull Request comments in order to avoid
  confusion and double work

Written communication is particularly important, famous companies like
[Amazon](https://www.mad.co/insights/the-mad-six-pager) and
[Hashicorp](https://works.hashicorp.com/articles/writing-practices-and-culture)
have a established a writing culture which is a major pillar of their success.

Not only, we need skills for creating content but also for consuming content.
Today, all of us get swamped with information via Emails or Slack messages.
Highly efficient developers process textual information quickly, organize the
information in an easily accessible way and are able to filter the important
facts. It can be advantage to invest some time into
[_speed reading_](https://en.wikipedia.org/wiki/Speed_reading) or other
techniques which let you consume content faster.

Maybe you think now of counter examples like Linus Torvalds (creator of Linux)
or Guido van Rossum (creator of Python). Either of them pulled off their project
almost alone but this is only true for the inception of their respective
projects. On the long-run they created an highly active and vivid community
which basically was the result of their great communication skills. Guido is
know as the _benevolent dictator_, Linus is famous for his harsh but direct
comments in the Linux Community and his ruthless clean code obsession. One major
part of their success was their unique communication style. For Linus' C code in
the early Linux Kernel spoke for itself and attracted a lot of passionate
developers.

## Learn concepts not technologies or tools

- information hiding and encapsulation
- coupling and cohesion
- object oriented programming, objects, inheritance, polymorphism
- functional programming - learn about immutability, high-order functions, pure
  functions, avoiding side-effect
- concurrency - there a different approaches to this like shared memory
  (old-school), CSP golang style or clojure core.async, Actor based with Scala
  Akka or Erlang, or just avoid it completely with an event-based approach like
  nodejs
- Test Driven Development - can be used in every language even if there is no
  test-framework, you can write your own
- ACID (transactions) - crucial so you know what you do not have to implement in
  your application but is given to you from the SQL-database
- Scaling with sharding/replication not only use AWS Dynamo DB but have some
  background to make reasonable design decision about your data model
- Networking Layers not TCP/IP layer implementation
- Machine learning algorithms not only Tensorflow or pytorch or Sci-Kit
- Math never gets old!

In order to gain a full understanding of these concepts, sure you spend some
efforts to learn an appropriate technology.

## Learn Lisp (even if you do not use at your daily work)

### SICP and PAIP

learn to program in the right way

http://www.catb.org/~esr/faqs/hacker-howto.html "LISP is worth learning for a
different reason — the profound enlightenment experience you will have when you
finally get it. That experience will make you a better programmer for the rest
of your days, even if you never actually use LISP itself a lot."

Lisp is not used widely in the industry nowadays but it teaches you so many
concepts which are essential to became a better programmer. These essentials you
will never learn if you stick to a single programming language like Java or C#.
Lisp expand your horizon. If you know Lisp you discover that all the new fancy
shit in our modern programming language today are not new but were already
available a long time ago in Lisp. But one thing which i really miss is the
REPL-driven development. No language today offers such a great realtime-feedback
experience as Common Lisp or Clojure as a Lisp dialect.

SICP teaches you

- learn programming
- Scheme a Lisp dialect
- computational complexity
- recursion
- functional programming, they introduce assignment at page ~300
- infinite streams (like natural numbers) and corresponding operators (a
  predecessor of it as RXJava or RXjs)
- add Object Oriented Programming OOP with polymorphism to Scheme (this can be
  done by an undergraduate, compare this to the efforts to add OOP to C, the
  outcome is C++ and it to took 5-6 years for Bjarne Stroutstup to finish it...
  Simplicity of Lisp's design shines here )
- concurrency and the problems it comes with
- implement you own interpreter and compiler

PAIP (Honorable Mention) by Peter Norvig: https://github.com/norvig/paip-lisp
"Best programming book ever written."

- build your own programming tools like pattern-matcher,
- search tools like DFS, BFS and beam search and A\*
- Memoization / Caching / compilation
- interpreter
- build a prolog compiler
- build your own OOP into Lisp with a few hundred lines of code

Norvig wrote a lot of great books and is a great teacher:

The seminal textbook about artificial intelligence. Udacity offers a great
course about advanced programming techniques taught by him: "Design of Computer
Programs" for free. Highly recommended.

## Master your tools

Like a good carpenter need to master his daily tools like a hammer and a saw, a
programmer needs to master his tools in order to be productive.

### editor

Emacs (my favorite), (Neo)Vim, Visual Studio Code are great editors

### OS

Unix, MacOS or Windows

### cli tools and string processing tools

They are composable and you get things done very fast

### Keyboard, Mouse proficiency

learn fast touch typing. Muscle Memory ensures you can focus on the problem
instead of wasting brain power for typing correctly etc.

## Control complexity

> Controlling complexity is the essence of computer programming, Software Tools
> (1976), p. 319 (Brian Kernighan, P. J. Plauger).

> The purpose of software engineering is to control complexity, not to create
> it. - Pamela Zave

- A Philosophy of Software design, John Ousterhout
- Big Ball of Mud http://www.laputan.org/mud/mud.html#BigBallOfMud
- Out of the tar pit, Moseley & Marks 2006
  https://blog.acolyer.org/2015/03/20/out-of-the-tar-pit/
- A Plean for Lean Software (Wirth) https://cr.yp.to/bib/1995/wirth.pdf

## Read Books

Books expand your horizon and you learn a lot in a very short time. But reading
is not enough, you need to practice regularly in order to achieve mastery in our
craft. I want to present the books which had the biggest impact on my
programming life and which i think of the most "valuable" to read.

- reference to my [books](../programming-books) and [paper](../classic-papers)
  list

#### books about our craft and software engineering

Book likes The Pragmatic Programmer, Code Complete or GoF Design Patterns
contents is dense and abstract. During your first reading you understand the
basics and the techniques but usually after years of practice, the second
reading will teach you the real essence of the book because you will recognize
many problems you faced in your career and connect the dots with the solutions
in the books. This was for me a great aha-effect.

#### Algorithms and Data Structures

You should own at least one Algorithm and Data structure book. I recommend
"Introduction to Algorithms" Cormen, or the "Algorithm Design Manual" Skiena.
Both are great and it's worth to buy them both.

#### Classics which drove my career

- "Refactoring", Martin Fowler
- "Extreme Programming explained", Kent Beck
- "Domain Driven Design", Evans
- Mythical Man-Month, Fred Brooks
- books of Brian Kernighan The Practice of Programming The C programming
  language (i read it 10 years ago for university studying purposes, back then i
  could not appreciate the genius of him. This is true for a lot of books if you
  read them a second time) The Unix programming environment The Go Programming
  Language

If you want do design and build distributed systems aka meaning all our fancy
new microservice architectures, a modern classic is:

- Designing Data-Intensive Applications, Martin Kleppmann

Not a tech book, but a book which change my life regarding to my learning
process is:

A mindful of numbers by Barbera Oakley

(there is a corresponding coursera course) This books really opens your mind and
it will change your life. An effective learning skill is one of the most
important skill a efficient programmer or any other can human can achieve.

#### Read papers

In comparison to blog post papers went through multiple proof readings and
quality assurance rounds, especially if there are published at a conference.
Hence they are often of a higher quality than internet blog posts.

Google papers like MapReduce, BigTable and other classics like Turing Award
papers of Ken Thomson, Hoare or about functional programming "Why functional
programming matters?" Hughes

My two favorite papers about software engineering in general are:

- A Plea for lean software Niklaus Wirth
- Emperors old clothes, Tony Hoare

## Understand the problem domain

Understand the problem/business domain your are working in. Being the best
programmer in the team is not enough. You be most valuable if you can connect
technology with the problem your trying to solve. Better understanding of the
problem opens up complete new solution strategies.

Always be interested in the business case of your product. Pull the information
from the Product Owner or Manager. With this background information you will be
much more valuable as an engineer to the company. E.g. as an engineer you know
what is possible and what not. So you can turn down "impossible" requirements
immediately instead of wasting days or weeks only to come to the same
conclusion. This goes the other way around to: As an engineer you know what is
possible and you are able to be a highly valuable contributer for new ideas or
requirements. Maybe with a new technology ideas can be implemented much easier
or make requirements possible to implement.

## Write \*good\* Tests

In software projects, tests of any kind like unit, integration, system tests are
now common practice. They are now a a basic building block like version control
systems or good tooling like powerful IDEs etc.

Unfortunately, just writing tests for the sake of tests is not enough. Good
Testing is a delicate endeavour and more often than not, teams end up with an
flaky, brittle and unmaintainable test suite. In such scenarios tests can even
hamper productivity. Such test suites make refactorings hard or even impossible.
As final resolution teams don't refactor anymore out of fear to break too many
tests or just abandon testing completely!

But good tests should enable and encourage refactoring. For example, Refactoring
is a core building block in Test Driven Development. So why test suites are
often a mess. -> overuse of mocks, which expose internal logic in the tests,
which couple tests to this internal logic. Eventually, you cannot change the
internal logic anymore since your tests depend on it. Solution use Fakes! Favor
state-verification over interaction-verification! (see FlamingoBook)

## Final Words

If you have only one take-away, the most important one is **practice
regularly**. Programming and, Learning in general, is a marathon not a sprint.
You will get better but it does not happen in days with cramming but in years
with continuous exercise.
