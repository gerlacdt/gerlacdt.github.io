---
title: "Software Engineering and Feedback Loops"
date: 2022-04-16T10:00:00+02:00
tags: ["programming", "softwareengineering"]
draft: false
---

## Introduction

Most of the breakthrough inventions in historic and modern Software
Engineering are related to *feedback loops*. They are essential for
productive software development, [Developer
Effectiveness](https://martinfowler.com/articles/developer-effectiveness.html#FeedbackLoops)
and Developer Happiness. The faster the feedback the more productive
developers are. Remember the dawn of computing when computers were
programmed with punch cards, programmers had to turn in their cards
over night and only the next day they got the result -- often with a
compile error :disappointed:. Unlike back then, today's computers
compile whole codebases in seconds. Now that is a big improvement and
an insane productivity booster!

Feedback Loops not only have a massive impact on programming or
technical areas but also on digital product management. Speaking of
the agile and lean startup movements, both of them focus heavily on
shorting feedback loops, e.g. the lean startup method strives for fast
customer feedback by building an Minimal Viable Product(MVP). This is
similar to the fundamental agile principle: "reducing waste". In agile
terms "waste" can be: building extra or incomplete features, handoffs,
context switches, defects etc.. But the biggest waste is building the
wrong product which can be prevented by early customer feedback.

But Feedback Loops are even present in other areas, they a crucial for
our learning in general and affect our whole life. Don't believe me?
Let's dive into it.



## Feedback Loops in Software Engineering

- remember the ancient times when computers were programmed with punch
  cards, you had to hand in your card and the next day you got your
  compile feedback/error.

* Code Reviews / Code inspections purpose is to gain feedback from
  colleagues to prevent errors. Common wisdom here is the earlier the
  errors are caught the cheaper the cost to repair. I.e. the faster we
  get feedback for our code, the less waste/maintainence effort we'll
  have later.

* compilation / static code analysis is the most used tool during
  development. We use it a hundred times a day to check if our code
  can be understood by a machine. Years ago we used to compile
  manually but nowadays our IDEs compile our code during typing in
  order to provide instant feedback and compile time errors.


|Technology   |Comment   |
|---|---|
|<span style="color: blue">Code Compilation</span>| compile multiple times a minute or even JIT (just-in-time) compilation for every code change in IDE, instant feedback about syntax errors|
|<span style="color: blue">Static Code Analysis</span>| more feedback about code quality, potential bugs and security issues|
|<span style="color: blue">Trunk-Based Development</span>| fast integration feedback, avoid long running branches and merge conflicts |
|<span style="color: blue">TDD</span>   | fast unit tests, feedback if local code change works |
|<span style="color: blue">Unit Tests</span> | ran multiple times per minutes, check local changes |
|<span style="color: blue">Code Reviews/Inspections</span>| 4-eye principle, feedback from colleagues |
|<span style="color: blue">CI/CD pipeline</span>| build every commit, deploy on dev/prod environments, verify with smoke tests|
|<span style="color: blue">Monitoring/Observability<span> | Feedback about system state, fast detection of errors, reduced MTTR (MeanTimeToRepair)
|<span style="color: blue">Infrastructure as Code, well-defined Runbooks</span>| faster reaction to outages based on *Observability*|
|<span style="color: blue">Microservices, Contract Tests</span>| feedback if code changes did not break the API contract|


### Kubernetes Control (Feedback) Loop
Kubernetes uses [Control
Loops](https://kubernetes.io/docs/concepts/architecture/controller/)
in order to keep the system as a whole as close as possible to a
desired state.

Observe the current state (check number of replicas) -> check
differences, compare current state with desired state (one replica is
missing) -> take action (start one more pod).




## Feedback Loops in Agile Processes and Product Management

Digital Product Discovery and Delivery

Building software is not only about programming and technology alone
but also include product management, teamwork and work
processes. Feedback loops are also crucial for those aspects. The
whole **agile** movement with the [agile
manifesto](https://agilemanifesto.org/) is mainly about getting better
and faster feedback, e.g. get early feedback from the customer, deploy
and release often (continuous delivery), deliver working software
frequently (sprints), business people and developers must work together. All
mentioned principles are optimized to produce faster feedback. Another
critical agile principle is *reducing waste* - this is accomplished by
releasing often and getting customer product feedback as early as
possible. The biggest waste in software product development is
building the wrong thing and it can be prevented by early feedback.

Out of the original *agile principles*, another branch has evolved
*the lean startup*.


<p align="center">
    <img src="/img/lean_cycle.png" alt="lean_cycle" class="medium-zoom-image" width="700">
</p>



* After product release: user feedback is the most valuable. Most
  users are ruthless and give honest and critical feedback. Better to
  listen to them in order to keep them happy. If you don't, you will
  end up with Windows 8 and a a new fancy UI with tiles ;) or a new
  Menubar called "Ribbon". Microsoft learned a lot back then and
  listens much more to user feedback nowadays!

Release often on a regularly in order to get fast feedback from customers/users.

* Agile is not about developing faster but getting faster feedback in
  order to reduce waste. The biggest waste is if you build something
  which nobody needs.


* Lean startup: Build - Measure - Learn (*TODO add Picture*) feedback loop in order to
  eliminate uncertainty like will the product be usable,
  understandable, legal and feasible?

* Lean startup: build MVP, release as fast as possible, if it it not
  embarrassing you invested to much time. Real customer feedback is
  more valuable!

* agile software development processes like SCRUM, Extreme Programming (XP), Kanban

  * release every Sprint or even more often, to get feedback from customers

  * system integration per commit or at least and of Sprint to get
    feedback if the system works. We want to avoid BigBang (which
    basically results of working in isolation without feedback of
    customers, other teams, other systems)

  * Tight communication of product owners with developers. Fast
    feedback, will prevent implementing the wrong features

  * Measure KPIs of production systems to get feedback which features
    are used, which can be removed!


## Negative and Positive Feedback Loops

Feedback loops are also used to keep a system in a correct state,
e.g. the thermostat measures the temperature and reacts based on the
measurings - increases the heating or turning it off


[The Software Architect Elevator](https://architectelevator.com/book/)

Positive feedback loops enhance or amplify changes; this tends to move
a system away from its equilibrium state and make it more unstable.

Negative feedbacks tend to dampen or buffer changes; this tends to
hold a system to some equilibrium state making it more stable.


The previously mentioned Kubernetes Control loop is a so-called
Negative Feedback loop. -> keep a desired state

Positive Feedback loops -> Nuclear Chain reaction,, fire gets bigger
with more Oxygen, or simply a possible conclusion of to many cars on
the roads is to build more roads which leads to even more cars. One
must identify such negative loops and prevent them, for example invest
in public transport.


## Feedback Loops in everyday life

- Learning is a continuous process which is best when we fail and
  learn from the feedback. It start as a toddler starting to walk. All
  of us went to a lot of feedback loops aka falls and
  stand-up. (falling hurts)


## Conclusion

The faster you collect feedback, the more productive you are as an
individual, as a team, as a department and as a whole company! So if
you want to improve it's always good to shorten the feedback loops.
