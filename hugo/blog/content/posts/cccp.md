---
title: "The Continuous Clean Code Process (CCCP)"
date: 2023-06-09T10:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

# Introduction

Many software projects end up in a
[_big ball of mud_](https://wiki.c2.com/?BigBallOfMud). One of the main causes
is the neglect of internal quality and focusing on adding new features with
unrealistic timelines. Code has the natural tendency to erode if you don't
launch countermeasures permanently. This observation is also known as the [the
second law of thermodynamics]:

> Systems tend to arrive at a state [...] where the entropy is highest [...]

In such projects, internal quality suffers permanently because no
countermeasures are launched. The only way to prevent a big ball of mud is to
ingrain continuous refactoring into your software engineering process, i.e. you
continuously write clean code.

Make refactoring a daily regular task in your software development process.

- add the feature or make a change, make it work, then clean up your code base
  (boy scout rule)
- or the other way around, if a feature is hard to had, first refactor the code
  so the feature is easy addable and then add the feature

Both versions work. But never forget to clean up afterwards. Ingrain this
process in your habits. It's the key to prevent a "big ball of mud". Often teams
code for months or years without touching and restructuring existing code, they
just add new features often with dirty workarounds. This accumulates and adding
new functionality will become harder, and eventually impossible, see: Martin
Fowler Internal Quality, diagrams
https://martinfowler.com/articles/is-quality-worth-cost.html

<p align="center">
    <img src="/img/clean_code_over_time.png" alt="clean_code_over_time" class="medium-zoom-image" width="600">
</p>

Always stick to clean code, and avoid shortcuts. Internal Quality is always
cheaper than adding cruft into the codebase. Beware of programmers who did not
internalize clean code, in order to make the milestone, they often come with
dirty hacks, workarounds or my favorite excuse: they want to skip tests. This is
total non-sense, and shows clearly that many programmers do not understand their
own job. For their excuse, often they are driven by management towards these
decisions and rewarded... So why not repeatedly do it, if the management
provides this incentives.. Don't fall into this treacherous trap. The big
problem with cruft and "big ball of mud" codebases is that they come silently
and sneak into the codebase over time. There is not one single big decision
which turns your codebase into a "big ball of mud" but all these tiny decisions
you make everyday as a team, as a single developer or as an architect, which
bring the systems into an unmaintainable state. The worst thing, you only detect
it when it's too late or the pain is very high.

One of the biggest mistakes developers can make is giving in to (irrational)
management timelines and then skipping tests. This completely is driven by wrong
incentives. First if the development team made the milestone, everybody is happy
and the dev team is rewarded. Second the dev team skipping tests shows them,
that the management is not interested in internal quality, so they will
regularly skip tests. Both leads to positive feedback loop which exacerbates the
situation: the developers will regularly skip tests because the management does
not care which worsen internal quality of the codebase and since the milestone
was made successfully, the management will get the feeling they can make it
again and again with the same approach :(.

So practice the CCCP and prevent "big ball of mud" code for your greater good.
Further, refactorings are fun and as a side-effect you end up with a
maintainable codebase which is nice to interact with! Win-Win. The organization
will also profit, since motivated developers are much more productive. And
hopefully discussions about the need of refactorings end soon.

Attention! Sometime developers tend to overdo thinks like over-engineering,
gold-plating and over-refactoring. Refactoring and adding new functionality
should be in balance. Be vigilant, don't fall into the trap to do weeks or
months of refactoring without new features. This is not refactoring but most
probably a re-write of your application!

Kent Beck is a goldmine of quotes:

> Make the change easy (this can be hard), then make the easy change. - Kent
> Beck

> Make it work, make it right, make it fast. - Kent Beck

#### Additiosn

For Star Wars Fans CCCP is also known as C3-PO :)
