---
title: "Reading List for the mindful Software Engineer"
date: 2021-09-10T10:04:01+01:00
tags: ["programming"]
draft: false
---


Abstractions are essential in order to build complex systems and keep
complexity under control. Especially in software development we rely
on many abstractions to make our life easier. Although good
abstractions do not "leak", it's always helpful to understand the
background and the details behind them.

The following list (in chronological order) of classic computer
science papers will give you insights of inventions of the last
decades which are considered nowadays common sense. But back then,
even the most basic concepts needed to be invented or discovered. I
find it fascinating how much you can learn from the discovery of such
breakthroughs and I hope you can learn from them as much as I
did. Further it is quite satisfactory when one is able to connect the
dots from the past to the present. Knowing the history prevent you
from reinventing the wheel and from making same failures over and over
again:

> Those that fail to learn from history are doomed to repeat it.

by Winston Churchill


###### <span style="color: blue">Recursive Functions of Symbolic Expressions and Their Computations by Machine, Part I</span>

by John McCarthy 1960

[Link to paper](http://www-formal.stanford.edu/jmc/recursive.pdf)

This paper is special in it's own ways and is widely considered as a
seminal work of computer science.

John McCarthy defines the complete LISP programming language in
roughly 30 pages. The design of Lisp with **S-expressions** is
ingenious because it's so simple. 1960 most of the programming
language concepts, which we consider basic today, needed to be
defined. McCarthy does this on the go. He introduces **conditional
expressions** like `if-then-else`. Those are needed for **recursive
functions** in order to formulate the recursion termination
condition. Casually he also introduces the first
**garbage-collection** algorithm with a `free-storage-list`. All in
one paper, groundbreaking!

Unfortunately the paper is hard to "digest" due to it's age. A
contemporary and more comprehensible essay was written by Paul Graham,
[The Roots of
Lisp](http://www.paulgraham.com/rootsoflisp.html). Graham picks up
McCarthy's paper and brings it in a more readable form.


###### <span style="color: blue">On the Criteria To Be Used in Decomposing Systems into Modules </span>

by David Parnas 1972

[Link to paper](https://www.win.tue.nl/~wstomv/edu/2ip30/references/criteria_for_modularization.pdf)


Today, we know modularization is a keystone for building large
software systems. Independent and autonomous teams are critical. To
achieve autonomy and smooth collaboration, modules must hide their
internal implementation, i.e. they should only expose the least
information which is absolutely necessary. The concept advocated by
Parnas is **information hiding**. It is crucial to *encapsulate* the
module's internals, so consumers do not have to deal with the module's
internal complexity. Parnas summarizes:

> The benefits expected of modular programming are: (1)
managerial-development time should be shortened because separate
groups would work on each module with little need for communication:
(2) product flexibility - it should be possible to make drastic
changes to one module without a need to change others; (3)
comprehensibility - it should be possible to study the system one
module at a time. The whole system can therefore be better designed
because it is better understood.


The principle of information hiding enables us to replace an existing
implementation but to keep the exposed interface. Hence existing
consumers remain untouched. Further modules can be reused and provide
their functionality safely. Basically Parnas paved the way for
**APIs** which are one of the most influential and powerful concepts
of software engineering. APIs act as contracts between consumer and
producer. This is true in the small scope for software libraries but
also in a large scope for REST-APIs, gRPC and the like. Modularization
is a precondition for **Microservices** whose functionality is hidden
between a well-defined and documented API. Even the Unix Philosophy is
a "just" a description for a good module:
  * Do one thing well
  * Write programs that work together
  * Write programs to handle text streams, because that is a
   *universal interface*.


Historical fact: In the seventies information hiding was
controversial. Even the most renowned IT people like Fred Brooks who
managed the development of IBM's System/360 with thousands of people
and a budget of 5 billion dollars did not believe in it. Years later
Brooks admitted (after bitter learnings):

> David Parnas Was Right, and I Was Wrong About Information Hiding.


###### <span style="color: blue">The Mythical Man-Month: Essays on Software Engineering</span>

by Fred Brooks 1975

[Link to book](https://www.oreilly.com/library/view/mythical-man-month-the/0201835959/)


Fred Brooks "Essays on Software Engineering" are from the seventies
but many of his insights still hold up today. 50 years are an eternity
in the IT field and it speaks for the quality of this seminal book.


In his essay *The Mythical Man-Month*, he directly addresses software
project management fallacies and bad estimations. Developers are not
line workers but creative problem solvers - increasing their numbers
will not positively impact the project's progress.

> Adding manpower to a late software project makes it later.

> Nine women can't make a baby in one month

Many other terms are coined in his essays. He muses that there is *No
Silver Bullet*, i.e. no new technology or process that will improve
productivity by an order of magnitude. He also addresses the tendency
towards **over-engineering** in *the Second System effect* and warns
about its consequences.

> The second system is most dangerous system a man ever designs
> ... The general tendency is to over-design the second system, using
> all ideas and frills that were cautiously sidetracked on the first
> one.

Further, he advocates for small, independent teams. *The surgical
team*, is a team with 5-10 people and cross-functional skills. Sounds
familiar Agile community?


###### <span style="color: blue">Time, Clocks, and the Ordering of Events in a Distributed System</span>

by Leslie Lamport 1978

[Link to paper](https://lamport.azurewebsites.net/pubs/time-clocks.pdf)

The seminal paper about **logical clocks** or *Lamport
clocks*. *Vector clocks*, a descendent of logical clocks, are one of
the main building blocks of today's distributed systems. They provide
means for *event ordering and synchronization* which are prerequisites
for modern NoSQL databases like [Amazon
DynamoDB](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf). You
can find a modern treatment about the topic from [Martin
Fowler](https://martinfowler.com/articles/patterns-of-distributed-systems/lamport-clock.html).

###### <span style="color: blue">The Emperor's Old Clothes</span>

by C.A.R Hoare 1980

[Link to paper](https://dl.acm.org/doi/pdf/10.1145/358549.358561)

In Hoare's Turing lecture, he shares his experiences of designing and
implementing programming languages. He muses about his first Algol 60
compiler which was a great success. But his second project failed
miserably (maybe because of the *second system effect*?). It was never
delivered, even after years of delay. Failure was caused by well-known
product management issues: "lack of software knowledge outside of the
programming group, interference from higher managers who imposed
decisions,... overoptimism in the face of pressure from customers and
the Sales Department". Eventually the project was reestablished and
saved by implementing agile principles like *early customer feedback*
and *incremental builds* - in the sixties :joy: :

> We assigned to each group of customers a small team of programmers
> and told the team leader to visit the customers and find out what
> they wanted; to select the easiest request to fulfil...

At last, Hoare speaks about his frustrating experience with
programming languages committees and the never ending story of feature
bloat and negligence of simplicity which leads us to his most famous
quote:

> I conclude that there are two ways of constructing a software
> design: One way is to make it so simple that there are obviously no
> deficiencies and the other way is to make it so complicated that
> there are no obvious deficiencies.

I find it fascinating that his insights about building compilers are
not only apparent today but also apply to software product development
in general.


###### <span style="color: blue">Reflections on Trusting Trust</span>

by Ken Thomson 1984

[Link to paper](https://www.cs.cmu.edu/~rdriley/487/papers/Thompson_1984_ReflectionsonTrustingTrust.pdf)

In his Turing Lecture Ken Thomson talks about trust:

> You can't trust code that you did not totally create
> yourself. (Especially code from companies that employ people like me
> (Ken Thomson).

*You can't trust code*, this applies especially to software libraries and tools like
compilers. In three stages he describes how to inject a *Trojan Horse*
into a compiler without leaving any traces in the source code. This is
possible due to re-compiling the compiler and removing the offensive
code but the binary is still infected and will inject the offensive
code for new compilations. It is hard to grasp but once you have it,
it is mind-bending.


Casually, Ken shows a beautiful
[Quine](https://en.wikipedia.org/wiki/Quine_(computing)), a program
that prints its own source code. Have you ever written one? Try it out
without looking to solutions - it's a enlightening experience.


###### <span style="color: blue">A Note on Distributed Computing</span>

by Jim Waldo, Geoff Wyant, Ann Wollrath, Sam Kendall 1994

[Link to paper](https://scholar.harvard.edu/files/waldo/files/waldo-94.pdf)

Abstractions are not for free, often they are leaky, inappropriate or
just do more harm than good. This paper gives great insights why you
should not treat distributed computing as local computing. Hiding
distributed computing under local interfaces is a bad idea. Many
technologies failed, trying exactly that - remember SOAP Web Services, Corba, Java
EJBs and Java RMI.

Conclusion: one cannot hide the inherent issues of distributed
systems, namely *latency, concurrency, partial failure* etc. behind an
abstraction. Developers must always have those in mind and use
appropriate techniques in order to build robust and resilient
systems. It's good to see that old fashioned technologies like Corba
or Java RMI are fading away and that
[REST](https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design#what-is-rest)
and [gRPC](https://grpc.io/) via HTTP gained ascendancy.


###### <span style="color: blue">A Plea for Lean Software</span>

by Niklaus Wirth 1995

[Link to paper](https://cr.yp.to/bib/1995/wirth.pdf)

Wirth elaborates about embracing **simplicity** and **fighting
complexity**, both traits often forgotten by today's developers and
customers. He makes a clear differentiation between *inherent*
complexity and *self-inflicted* complexity. The later is the main
reason for bulky software.

His insights about iterative software development, modularization and
the decomposition of complex systems are delightful. Especially
because those insights transition so well into the modern world with
microservices.

He proves his points with the [Oberon
OS](https://en.wikipedia.org/wiki/Oberon_(operating_system)), a
complete system written by him and his colleague in less then three
years. Compare this to [IBM
OS/360](https://en.wikipedia.org/wiki/IBM_System/360), a project with
five thousand man-years budget but infected with self-inflicted
complexity and feature bloat. Both projects had a "similar" scope,
namely an OS with additional tools like compiler, editor etc.

It is one of the best paper's ever written and makes you a better
programmer, simply because it changes your way of approaching big
software projects and makes you honor simplicity more than ever.

Wirth's paper is so full of gems, a selection of quotes (all before the agile revolution):


> Truly good solutions emerge, after iterative improvements of after
> redesigns that explicit new insights, and the most rewarding
> iterations are those that result in programming simplifications.

> The belief that complex systems require armies of designers and
> programmers is wrong. A system that is not understood in its
> entirety, or at least to a significant degree of detail by a single
> individual, should probably not be built.

> Communication problems grow as the size of the design team
> grows. Whether they are obvious or not, when communication problems
> dominate, the team the project are both in deep trouble.

> To gain experience, there is no substitute for one's own programming
> effort.


###### <span style="color: blue">Introduction to Functional Programming</span>

by Richard Bird, Philip Wadler 1998

[Link to book](https://docs.google.com/viewer?a=v&pid=sites&srcid=ZGVmYXVsdGRvbWFpbnx0aGVhbnVqbWVodGF8Z3g6NzJiOTQ3YTVhODNlY2YyNg)

Alongside Fred Brooks *Mythical Man-Month*, this is the second book on
the list and it is the best introduction to functional programming,
period. In a way, it complements
[SICP](https://mitpress.mit.edu/sites/default/files/sicp/full-text/book/book.html),
another classic and the best introduction to programming in general.

The book touches functional core concepts like immutable and lazy data
structures, pure functions, function composition
and high-order functions like *map, filter, fold*. After reading the
book, you are well prepared for solving real world problems in a
functional way. With these new tools in your toolbox, you will be better
programmer and see the world differently.  For example, avoiding state
and side-effects, will make your code much more readable, more
composable, better testable and easier maintainable.



###### <span style="color: blue">Out Of The Tar Pit</span>

by Ben Moseley, Peter Marks 2006

[Link to paper](http://curtclifton.net/papers/MoseleyMarks06a.pdf)

This paper is a great elaboration about complexity, its causes and how
to manage it.

Like Wirth, the authors distinguish between two types of complexity: *essential
complexity* and *accidental complexity*. The latter is the main reason
why systems are much more complex than necessary. Further they expose
**state** as another major cause for complexity, but also code volume
and the negligence of simplicity are main drivers.

Rings a bell? Today *avoiding state* is tantamount in Computer Science
to good, simple and scalable system design. Thinking of functional
programming, stateless microservices and HTTP as a stateless protocol.

###### <span style="color: blue">On Designing and Deploying Internet-Scale Services</span>

by James Hamilton 2007

[Link to paper](https://www.usenix.org/legacy/event/lisa07/tech/full_papers/hamilton/hamilton.pdf)


Today, distributed systems are everywhere. With the raise of
ever-growing systems and new tools like Kubernetes, Containers and
Microservices, new best practices were needed. This paper is whirlwind
tour about best practices to build robust distributed systems in an
*operating-friendly* way.

The paper describes keystones like *redundancy, fault tolerance, build
for failure, avoid single point of failures, geo-distribution,
firedrills, fail fast* and many more. Without knowing it, he basically
describes the contemporary **DevOps** mindset (and his observations
gives us the reason why it makes sense):

> The trend we've seen when looking across many services is that
> low-cost administration correlates highly with how closely the
> development, test and operations teams work together.

> If development is frequently called in the middle of the night,
> automation is the likely outcome. If operations is frequently
> called, the usual reaction is to grow the operations team.

I like his tendency to "testing in production" with permanent
firedrills and his meticulous attitude leaving nothing to chance:

> The general rule is that nothing works if it isn't tested
> **frequently**.

It is a great read, especially, if you consider to build
"internet-scale" systems. You find most of the advice in other
distributed system books but the paper provides them in a condensed
and terse way.



##### More

Interested in more? You can find a great and free collection
of literature at *[github/papers-
we-love](https://github.com/papers-we-love/papers-we-love)*.
