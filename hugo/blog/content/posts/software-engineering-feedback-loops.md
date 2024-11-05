---
title: "Software Engineering and Feedback Loops"
date: 2022-09-29T10:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: false
---

## Introduction

Most of the breakthrough inventions in historic and modern Software Engineering
are related to _feedback loops_. They are essential for productive software
development,
[Developer Effectiveness](https://martinfowler.com/articles/developer-effectiveness.html#FeedbackLoops)
and Developer Happiness. Remember the dawn of computing when computers were
programmed with punch cards, programmers had to turn in their cards over night
and the next day they got the result -- often with a compile error
:disappointed:. Unlike back then, today's computers compile whole codebases in
seconds. That is an insane productivity booster caused by faster feedback!

Feedback Loops not only have a massive impact on software development but also
on digital product management and on whole team structures. Speaking of the
agile and lean startup movements, both of them focus heavily on shorting
feedback loops.

## Feedback Loops in Software Engineering

Feedback is strongly interwoven with a programmer's everyday life and his
productivity. On a typical workday a developer has dozen of contact points with
feedback loops which are incorporated into his work. The most obvious one is the
_write - compile_ loop. Every programmer is familiar with it and the compiler is
one of the most used and valuable programming tool. In the last decades the
_write-compile_ feedback times were reduced tremendously. Clever IDEs even
compile the code during typing which provides instant feedback.

There are many other feedback loops, programmers come in touch with. For
example, the purpose of _Code Reviews_ is to gain feedback from colleagues to
prevent errors from entering the mainline. Furthermore, a code review is a place
to share thoughts, give feedback about good or bad design and act as a learning
platform where seniors and juniors can interact with one another. Reviews are
not bound to executable code alone, but can be conducted for specifications or
design docs too. The earlier errors are caught, the cheaper is the cost to
repair.

Other programmers' daily routines are also connected to feedback loops.
[Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development)
(TDD) gives programmers an almost addictive _red-green-refactor_ loop.
Techniques like
[Continuous Integration](https://martinfowler.com/articles/continuousIntegration.html)
or [Continuous Delivery](https://martinfowler.com/bliki/ContinuousDelivery.html)
(CI/CD) minimize the time for developers to get notified when code changes
induce bugs into the system.

The table below shows more software engineering feedback loops.

| Technology / Technique                                        | Feedback Time                           | Comment                                                                                                                                   |
| ------------------------------------------------------------- | --------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| <span style="color: blue">Code Compilation</span>             | seconds or<br />minutes in Java :smile: | compile multiple times a minute or even JIT (just-in-time) compilation for every code change in IDE, instant feedback about syntax errors |
| <span style="color: blue">Static Code Analysis/Linters</span> | minutes                                 | more feedback about code quality, potential bugs and security issues                                                                      |
| <span style="color: blue">Trunk-Based Development</span>      | minutes                                 | fast integration feedback, avoid long running branches and merge conflicts                                                                |
| <span style="color: blue">TDD</span>                          | seconds                                 | fast test suite, feedback if a local code change works                                                                                    |
| <span style="color: blue">Unit Tests</span>                   | seconds                                 | ran regularly during development, used in conjunction with TDD                                                                            |
| <span style="color: blue">Contract Tests</span>               | minutes to hours                        | feedback if code changes did not break the API contract                                                                                   |
| <span style="color: blue">Code Reviews/Inspections</span>     | minutes to days                         | 4-eye principle, feedback from colleagues                                                                                                 |
| <span style="color: blue">CI/CD pipeline</span>               | minutes                                 | build every commit, deploy on staging/prod environments, feedback if deployed code works                                                  |
| <span style="color: blue">Monitoring/Observability<span>      | minutes                                 | Feedback about system state, detection of outages, anomalies and errors, reduced Mean Time To Repair (MTTR)                               |

## Feedback Loops in Agile

Building software is more than programming and technology alone but also
includes product management, teamwork and work processes. Feedback loops are
also crucial for these aspects. The whole **agile** movement is mainly a
collection of principles about speeding up feedback cycles. E.g. getting
feedback from the customer as early as possible, deploying and releasing often,
delivering working software frequently , business people and developers working
tightly together. Another critical agile principle is _reducing waste_ which is
accomplished by releasing often and getting customer feedback. Namely, the
biggest waste in software product development is building a product nobody
wants.

Customer feedback is so important that a whole new methodology, the _lean
startup_, has evolved around it. Its center is the _build-measure-learn_ loop
which helps shortening the development cycles, briskly discovering viable
products and reducing uncertainty. With the _build-measure-learn_ loop,
companies are enabled to understand early if a product will be
[valuable, viable, usable, and feasible](https://www.svpg.com/value-and-viability/).
Based on such data, organizations can make much better decisions regarding
future roadmaps and plans. The _lean startup_ methodology heavily promotes
building a Minimal Viable Product (MVP) and releasing it to customers, so
feedback can be collected even before the final product is built with high
costs. With continuous customer feedback, it is possible to make course
correction early on, so the product direction can be changed and adopted to the
market needs.

<p align="center">
    <img src="/blog/img/lean_cycle.png" alt="lean_cycle" class="medium-zoom-image" width="700">
</p>

Customer thoughts are indispensable since they have the best insights. A good
example of ignoring customers is Microsoft Windows 8 and its newly introduced
tiles UI/UX. It was a complete
[disaster](https://finance.yahoo.com/news/windows-10-undoes-the-disaster-of-windows-8-mostly-98835840904.html)
which could have been prevented. The result, most of the users stayed with
Windows 7 and Windows 8 was a big flop.

Agile frameworks like SCRUM, Kanban, SAFe etc. draw on shortening feedback loops
too. SCRUM, for instance, uses Sprints and Sprint Reviews to ensures regular
delivery of working software at a consistent pace. Stakeholders or real
customers can see the progress of a product and assess its state in a much more
tangible way than with lifeless wireframes or dry PowerPoint slides. Normally
such product presentations happen on a weekly basis in contrast to traditional
software development processes like the
[Waterfall Model](https://en.wikipedia.org/wiki/Waterfall_model) in which
working software was delivered only after months or even years.

Key-Performance-Indicators (KPIs) are another piece which play a vital role in
agile product development. They provide all kind of clues which should lead to
better decision making, e.g. which product features are used in general, how
often they are used and are they used as indented. With such data, companies can
determine if a feature is successful, which feature needs more tinkering or
which feature can be removed from the product. Feature validation is essential
and a good indicator if a company takes customer feedback seriously. The best
companies take a few weeks to validate a feature, in contrast to traditional
companies that are never able to validate a feature because of an absent
feedback channel.

Feedback loops can also be organizational or teamwork specific, e.g. how long
does it take for a developer to be productive, how long is the
[lead time](https://cloud.google.com/blog/products/devops-sre/using-the-four-keys-to-measure-your-devops-performance)
for a feature, or simply how long does it take to get qualified support for an
internal IT problem. More feedback loops can be found in the great
[Developer Effectiveness](https://martinfowler.com/articles/developer-effectiveness.html#FeedbackLoops)
article.

## Regulate Systems with Feedback Loops

Feedback loops do not only play a dominant role for IT teams but are also used
to regulate complex (IT) systems. Two kinds of regulating feedback loops exist:

**Positive feedback loops** enhance or amplify changes; this tends to move a
system away from its equilibrium state and make it more unstable.

**Negative feedback loops** tend to dampen or buffer changes; this tends to hold
a system to some equilibrium state making it more stable.

Negative feedback loops keep systems in a desired state, e.g. a thermostat
measures the temperature in a room and reacts based on the measuring. It turns
the heating up or down. The
[Kubernetes Control loop](https://kubernetes.io/docs/concepts/architecture/controller/)
is a prominent implementation of a negative feedback loop in the IT field. For
example, a Kubernetes controller observes the number of running replicas and
starts or stops them when the current number of replicas deviate from the
desired number.

Positive feedback loops, however, lead often to wrong conclusions and actions.
They come often in disguise and are hard to identify, a practical example from
[The Software Architect Elevator](https://architectelevator.com/book/): A
government detects there are too many cars on the roads causing permanent
traffic jams. As a reaction, the government decides to build more roads
indicating to people to buy more cars which will cause even more traffic jams.

## Conclusion

I hope, I could provide you a good intuition where feedback loops are used and
that they are fundamental for many leaps in modern software engineering.

The faster the feedback, the more productive you are as an individual, as a
team, as a department and as a whole company! If you want to improve, it's
always beneficial to look for possibilities to shorten feedback loops.
