---
title: "The Non-Productive Programmer (NPP)"
date: 2021-12-18T10:00:00+01:00
tags: ["programming", "engineering"]
draft: false
---


We all know them: programmers with long term experience stuck on a
low-level of our craft not corresponding to their quantity of
experience. Great programmers are creative workers and problem-solvers
and one of their most important traits is: **never stop learning** --
but getting stuck in its own comfort zone and becoming reluctant to
change is deeply wired into human nature ([Humans are hard-wired to
follow the path of least
resistance](https://www.sciencedaily.com/releases/2017/02/170221101016.htm)).
In the following, I will show examples of non-productive programmers
(NPPs), how to detect them, what are their characteristics and how to
pro-actively counterattack our nature in order to learn continuously.


### Stuck in the Comfort Zone

Never leaving their own comfort zone or never thinking outside the box
is one major reason why programmers get stuck. They rely on deprecated
rituals, fall prey to old habits which they cannot strip off or adhere
to technologies to which they are often heavily attached. Sometimes
NPPs identify themselves with a special technology. This is visible
when they introduce themselves as Spring or ReactJS developer.

A good example are Java/Websphere NPPs. They have ten years of
experience and don't know any other programming language or server
technology. With Websphere, 10+ minutes builds and server startup
times are normal. Since these programmers never experienced anything
else, they are satisfied with their current environment. They adjusted
their whole workflow around long waiting times, e.g. go for a "compile
coffee break" or simply "check emails". In rare cases NPPs have the
courage to make some babysteps out of their universe. For instance
they switch from Websphere to the "lightweight" Spring Framework which
reduces the wait times enormously -- e.g. from ten to three
minutes. But the real big leaps in productivity are not considered,
speaking of new programming languages like Go and NodeJS which provide
build times in the range of seconds. NPPs hardly leave their comfort
zone and when they do, they make only baby steps. The previous example
served demonstrative purposes, please don't rewrite your project
because of faster build times!


### Quantity of Experience


The quantity of experience is only one dimension. More important is
the quality and deepness of experience. Especially NPPs suffer from
superficial knowledge because in their work life, they never went deep
into details. They apply their accumulated knowledge again and again
without gaining a deeper understanding in their used technologies or
problem domains.

The quality of experience can vary strongly. NPPs often end up in a
**vicious cycle**.  They move from project to project and the
acquired knowledge of the *first year* is enough for accomplishing the
next one. Due to the similarity of other projects, NPPs are never
exposed to harder problems or forced to go out of their comfort
zone. E.g. Java NPPs will never learn deeper foundational topics which
are necessary to grow like the internals of the JVM, Profiling or
Concurrency.

A presumable Java NPP's vicious cycle could look like this:


![vicious_cycle](/img/vicious_cycle.drawio.png)


[Kelsey Hightower](https://twitter.com/kelseyhightower/status/1281086164514963457?s=20) brought it to the point:

> There are many people with 15 years of 1 year experience.

> 5 years running bash scripts is not the same as building a startup
from the ground up


### "It works, is enough" Attitude

One of my personal programming pet peeves is the *It works, so why
change?* attitude. Typical crime scenes are daily Pull Requests or
Software Design Reviews. NPPs are reluctant to do the simplest changes
like variable/function renamings or other code
restructurings.

> Leaving the camp ground cleaner than before.

The famous boy scout rule also applies to code, but cross-functional
requirements like maintainability, comprehensibility or better code
quality in general do not count for NPPs. *It works, so why put more
effort in it* - that's their attitude. This behaviour is even
encouraged by so-called agile teams, not experienced in software
engineering. Such teams work heavily feature-driven. Developers
pushing out the most features, in a bad quality and with
unmaintainable hacks or workarounds, are the company heros. The
internal quality suffers, in the long term the product architecture
becomes a [Big Ball of Mud](http://www.laputan.org/mud/), the velocity
slows down and eventually progress comes to a complete halt. Thereby
[internal quality is the most critical factor to keep a product healthy
and maintainable](https://martinfowler.com/articles/is-quality-worth-cost.html#VisualizingTheImpactOfInternalQuality). Worse
yet, even with good developers in the team, NPPs will undermine their
efforts because the *it works* mentality wins for the management.


In the contrast to NPPs, great developers write maintainable code
readable by humans, test their code rigorously with unit tests,
refactor their code regularly and most importantly they are willing to
go the extra mile in Pull Requests or Design Reviews and strive for a
healthy codebase.




### Satisfied with Shallow Understanding


The NPP's biggest weakness though, is his deeply rooted behavioral
pattern to be easily satisfied. As soon something is working, there is
no aspiration anymore to investigate further. The result is **shallow
understanding**. NPPs don't understand their tools and their own
solutions fully -- they accept *magic*.

The acceptance of magic inhibits NPPs from getting to the bottom of
the problems. For example the Spring Framework is a powerful Java Web
Framework and a big productivity booster. As long you don't step out
of the framework's scope, everything is fine. But if you want to do
something unconventional, not anticipated by Spring, out of sudden the
framework is an impediment. Since the NPP accepted Spring Framework's
magic, he is screwed. The NPP's shallow understanding is exposed, but:

*Eventually, there is no "magic", just code*.

Sometimes, the only way is digging into the framework's or library's
code. Ultimately one discovers there is no magic or that the library's
code is ugly, hard to read and understand or even faulty. Then you
learn something new and expand your horizon. Unfortunately, NPPs don't
read Open Source code, it is not in their comfort zone.

One more example of a NPP's *shallow understanding* is his missing
deeper knowledge about his daily tools, e.g. `git`. More often than
not, NPPs usually use IDEs for their daily tasks because it's
convenient. Although, it turns out that NPPs only can accomplish basic
tasks like `git push/pull/commit`, anything more mature such as
resolving merge conflicts, squashing or re-order commits is beyond
their understanding because they never went out of their cozy IDE
zone. Using tools without knowing what's behind them, leads to an
ability which helps to accomplish regular tasks but as soon something
"unexpected" needs to be done, one hits a wall.

*The natural desire to understand the things deeper is one of the most
underestimated traits of an outstanding engineer*.


### Blindly follow Mantras

Another kind of NPP is eager to read blogs and books in order to learn
new technologies, platforms and processes, e.g. [GoF Design
Patterns](https://springframework.guru/gang-of-four-design-patterns/),
[TDD](http://wiki.c2.com/?TestDrivenDevelopment),
[DDD](https://martinfowler.com/bliki/DomainDrivenDesign.html), [SOLID
Principles](https://en.wikipedia.org/wiki/SOLID),
[DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
etc.. Alas, NPPs don't grok them entirely, follow them blindly and
eventually doing more harm than good. They only see the top of the
iceberg, memorize only buzzwords and don't comprehend in which
situation to apply the lately acquired knowledge. Unexpectedly, they
overuse mantras and bask themselves in self-congratulation.

In my career, I experienced following examples personally from
different co-workers:

* After reading [GoF Design
Patterns](https://springframework.guru/gang-of-four-design-patterns/):
if-else statements are considered evil and must be replaced by
Strategy Pattern.

* After reading about the [SRP
  principle](https://en.wikipedia.org/wiki/Single-responsibility_principle):
  functions cannot be longer than a few lines. NPPs confuse "a
  function must not do more than one thing" with "a function should
  have only one reason to change". I must acknowledge that the naming,
  "Single Responsibility Principle", is vague and in order to
  understand it completely, one needs already a lot of programming
  experience.

* After reading [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself): every single duplication must be extracted, no
  matter how small and insignificant. But the wise programmer knows:

  > Duplication is far cheaper than the wrong abstraction

  [Sandi Metz](https://sandimetz.com/blog/2016/1/20/the-wrong-abstraction)

* After reading about *Microservices*, NPPs fall for the
  hype. Microservices are considered as a holy grail but come with
  serious
  [trade-offs](https://martinfowler.com/articles/microservice-trade-offs.html)
  which are usually forgotten or not known. For example,
  [microservices solve organizational scaling problems and cause
  technology
  problems](https://speakerdeck.com/peterbourgon/go-plus-microservices-equals-go-kit?slide=15)
  but only [one percent of all applications will ever reach such a
  scale](https://www.slideshare.net/RandyShoup/scaling-your-architecture-for-the-long-term). In
  most circumstances the [majestic
  monolith](https://m.signalvnoise.com/the-majestic-monolith/) is the
  better solution.



NPPs memorize the benefits of the new technologies and are able to
enumerate their *values* but they don't understand their
*cost*. Trade-offs are neglected and freshly learned approaches are
applied in wrong places where they don't make sense. Often solutions
are mixed up and situations are misjudged. A famous quote describes this
perfectly:

> Programmers know the value of everything and the cost of nothing.

Alan Perlis


Convincing NPPs of the opposite is hard and often ends up in harsh
disputes , because NPPs think what they read in a seminal book is
"always" true. Actually this is a good differentiation between good
and non-productive developers. NPPs follow mantras blindly and rely on
their shallow understanding. The best developers know when to use
specific techniques and **always use their own judgement**. Kent
Beck's quote *"It depends"* is not famous for nothing. Every complex
situation deserves a deeper reasoning whereby programming is one of
humanity's most complex endeavors.


## Becoming Productive

Becoming productive is easy. Do exactly the opposite of the points
mention above:

* Get out of your comfort zone as often as possible and accept new
  challenges

* Don't be satisfied with "*it works*", go the extra mile, make the
  code better than before. Take your time to discuss other solutions,
  take new approaches seriously, accept feedback from your peers.

* Don't be satisfied with magic. Whenever you have the time, dive
  deeper into the topics and try to understand the magic.

* When you learn something from books, learn it thoroughly, discuss it
  with your peers or your mentor in order to make sure you grokked the
  material and its trade-offs. Don't apply the newly acquired
  knowledge blindly!

Changing your attitude and applying the above points make you a better
programmer. But how to become interested in the deeper things or how
to train programmers to look behind the curtain? Well, everyone is
different but from my experience following things work:

* You need a natural drive to get to the bottom of something and not
  accept shallow understanding.

* You need an analytical mindset, question the status-quo, never accept
  the existing state as optimal. There is always room to improve!

* Gather information. You cannot know it all: reading books,
  conversations with colleagues, listening to technical talks are good
  sources to grow. Even if you never use the gathered knowledge
  directly, it will give you good intuition for future problems,
  e.g. knowing about Functional Programming makes you a better (Java)
  Programmer.


**Finally, real proficiency and productivity comes from working and
training everyday**. There is no shortcut, humans' best teacher is
learning from experiences and failures. Failures will stick, whereby
the passive knowledge like books and talks will slowly fade away if
not practiced regularly.
