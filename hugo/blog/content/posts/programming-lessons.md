---
title: "Programmming lessons, I learned in 15 years"
date: 2022-09-29T11:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: true
---

## TL;DR

- Practice practice practice / You can't learn programming in 21 days
- Practice your code reading skill / Read code / Code reading is as important of
  even more important then code writing
- Communication (processing textual information, writing/reading/communicate
  well)
- Strive for fast Feedback loops (practice to proficiency)
- Master your tools
- Reduce complexity at any cost
- Learn concepts not technologies or tools
- Read books (expand you horizon but don't forget to practice)
- Read Code
- Learn Lisp

Some teasers

## You can't learn programming in 21 days

Teach yourself programming in ten years http://norvig.com/21-days.html

- be the best programmer in one project, Mentor other team members,

- be the worst programmer in another,Learn their best practices (see also The
  Pragmatic Programmer or Code Complete). But also the _process_ how they get to
  the solution

- work on projects with other programmers in a team
- work on projects after other programmers. Like maintaining a legacy but
  stilled used codebase. See code reading skills.
- Talk with other programmers; read other programs. This is more important than
  any book or training course.

According to Norvig (link) learning to program is a marathon. To master a craft
it normally takes roughly ten thousands hours of practice. Daily exercises and
challenging project work is necessary to climb up to new heights and improve
yourself.

## Read Books

Books expand your horizon and you learn a lot in a very short time. But reading
is not enough, you need to practice regularly in order to achieve mastery in our
craft. I want to present the books which had the biggest impact on my
programming life and which i think of the most "valuable" to read.

### books about our craft and software engineering

Book likes The Pragmatic Programmer, Code Complete or GoF Design Patterns
contents is dense and abstract. During your first reading you understand the
basics and the techniques but usually after years of practice, the second
reading will teach you the real essence of the book because you will recognize
many problems you faced in your career and connect the dots with the solutions
in the books. This was for me a great aha-effect.

### Algorithms and Data Structures

You should own at least one Algorithm and Data structure book. I recommend
"Introduction to Algorithms" Cormen, or the "Algorithm Design Manual" Skiena.
Both are great and it's worth to buy them both.

### Classics which drove my career

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

\*\* Read papers

In comparison to blog post papers went through multiple proof readings and
quality assurance rounds, especially if there are published at a conference.
Hence they are often of a higher quality than internet blog posts.

Google papers like MapReduce, BigTable and other classics like Turing Award
papers of Ken Thomson, Hoare or about functional programming "Why functional
programming matters?" Hughes

My two favorite papers about software engineering in general are:

- A Plea for lean software Niklaus Wirth
- Emperors old clothes, Tony Hoare

## Code reading skills

https://blog.codinghorror.com/when-understanding-means-rewriting/ Reading code
is one of the most underrated skill in programming. Working as programming means
50% reading time and only 20-30% writing code. Code-Reading skill is very
important.

This means how fast you get familiar with a unknown codebase. What if there are
not tests? What if there are only out-dated architecture diagrams and design
docs? What if there are no docs at all? How do you approach such problems? How
to collaborate with your team members in order to get going? Are you comfortable
with code reviews or pair programming?

## (Written) Communication

The craft of programming begins with empathy, not formatting or languages or
tools or algorithms or data structures.- Kent Beck

Most projects fail not because of the technology or resources but because of
failed communication in teams, across teams and because of wrongly communicated
requirements or wrongly communicated expectations.

Communications is the second underrated core skill of a good programmer.

Empathy Mentoring Code reviews Pair programming (a lot of discipline required of
both partners) Writing Design Docs Discussion about new ideas Convince other
parties of your (better) solution/approach - but do not be stubborn you need to
accept better solution too. Be a team player. If your team decides for one
solution and you failed to convince the team. You still can disagree but you
need to fully commit to the team's decision. Write crystal clear Jira tickets
like requirements or PR comments in order to avoid confusion or double work

Always be interested in the business case of your product. Pull the information
from the Product Owner or Manager. With this background information you will be
much more valuable as an engineer to the company. E.g. as an engineer you know
what is possible and what not. So you can turn down "impossible" requirements
immediately instead of wasting days or weeks only to come to the same
conclusion. This goes the other way around to: As an engineer you know what is
possible and you are able to be a highly valuable contributer for new ideas or
requirements. Maybe with a new technology ideas can be implemented much easier
or make requirements possible to implement.

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

Emacs (my favorite), Vim, Sublime are great editors

### OS

Unix, MacOS or Windows

### cli tools and string processing tools

They are composable and you get things done very fast

## Reduce complexity at any cost -> drive for simplicity always

The purpose of software engineering is to control complexity, not to create
it. - Pamela Zave

A Philosophy of Software design, John Ousterhout Big Ball of Mud
http://www.laputan.org/mud/mud.html#BigBallOfMud Out of the tar pit, Moseley &
Marks 2006 https://blog.acolyer.org/2015/03/20/out-of-the-tar-pit/

Controlling complexity is the essence of computer programming, Software Tools
(1976), p. 319 (Brian Kernighan, P. J. Plauger).

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

With all this do not forget to write code on regular basis :) This is the most
important take-away...

“The only way to learn a new programming language is by writing programs in it.”
Dennis Ritchie, inventor of the C programming language
