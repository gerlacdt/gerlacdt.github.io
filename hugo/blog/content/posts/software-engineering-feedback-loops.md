---
title: "Software Engineering and Feedback Loops"
date: 2021-01-17T18:38:45+02:00
tags: ["programming", "softwareengineering", ]
draft: true
---



## Introduction

* fast feedback is **THE** major driver for developer productivity and
  therefore developer happiness. Only a happy develop is highly
  productive in the long term.


* everything is about FAST feedback, the faster the better


So everything we do is about feedback. The faster and earlier we get
valuable, critical feedback the better. It saves tons of money and
steers us in the right direction. Without feedback build system which
work by change or are not useful for our customers. The same is true
for software development, without critical and continuous feedback
cycles.


- remember the ancient times when computers were programmed with punch
  cards, you had to hand in your card and the next day you got your
  compile feedback/error.

## faster feedback loops
* developer productivity
* developer happiness
* faster product discovery and delivery

## 4 Key Metrics

* lead time
* deployment frequency
* MTTR -> good Monitoring, Log Analytics required, the better the
  faster you detect error and the faster you can react on it, the
  faster you repair your system
* change failure rate


## Agile Product Management

Release often on a regularly in order to get fast feedback from customers/users.

* Agile is not about developing faster but getting faster feedback in
  order to reduce waste. The biggest waste is if you build something
  which nobody needs.


* Lean startup: Build - Measure - Learn feedback loop in order to
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


* After product release: user feedback is the most valuable. Most
  users are ruthless and give honest and critical feedback. Better to
  listen to them in order to keep them happy. If you don't, you will
  end up with Windows 8 and a a new fancy UI with tiles ;) or a new
  Menubar called "Ribbon". Microsoft learned a lot back then!




## Agile Software Engineering Practices

* TDD (fast unit tests) -> feedback to check if new code is correct and nothing is broken

* unit tests (I run them multiple times minute to check if it works or if i broke sth.)

* CI/CD pipelines (integration tests, deployments to test
  environments) -> feedback for developers if changes work on test
  stages

* trunk development (feedback of integration, avoid commit conflicts)

* CI/CD builds every commit, fast feedback about if we broke sth.

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

* Microservice architecture. We need feedback from our internal
  service consumers, e.g. from contract tests. So we can be confident
  that we did not broke the published API.



### Conclusion

The faster you collect feedback, the more productive you are as an
individual, as a team, as a department and as a whole company! So if
you want to improve it's always good to shorten the feedback loops.



## Links

https://martinfowler.com/articles/developer-effectiveness.html
