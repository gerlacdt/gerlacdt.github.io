---
title: "The Continuous Clean Code Process (CCCP)"
date: 2023-12-29T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

# Introduction

Most software projects end up in a
[_big ball of mud_](https://wiki.c2.com/?BigBallOfMud). The major cause is
neglecting internal quality and focusing on adding features with dirty hacks
because of unrealistic timelines. Code has the natural tendency to erode if you
don't launch countermeasures permanently. This observation is also known as the
[the second law of thermodynamics](https://en.wikipedia.org/wiki/Second_law_of_thermodynamics):

> Systems tend to arrive at a state [...] where the entropy is highest [...]

The only way to prevent a big ball of mud is to ingrain continuous refactoring
into the software engineering process, i.e. you continuously write clean code.
Refactorings must be a regular task whereby they can happen after or before
implementing the feature itself:

> **Make it work, make it right, make it fast.** - Kent Beck (refactor
> afterwards)

> **Make the change easy (this can be hard), then make the easy change.** - Kent
> Beck (refactor beforehand)

The best developers ingrained continuous refactoring as an habit. It is the main
key to prevent a big ball of mud. Often teams code for months or years without
touching and restructuring existing code, they continuously add new features
with dirty workarounds. This accumulates and adding new functionality will
become harder, and eventually impossible
[[1]](https://martinfowler.com/articles/is-quality-worth-cost.html)

<p align="center">
    <img src="/img/clean_code_over_time.png" alt="clean_code_over_time" class="medium-zoom-image" width="600">
</p>

Always stick to clean code and avoid shortcuts. Choosing internal quality is
always cheaper than adding cruft into the codebase. Beware of programmers who
did not internalize clean code. In order to make the deadline, they integrate
dirty hacks, workarounds or skip tests. This is non-sense, and shows clearly
that many programmers do not understand their job. They justify their actions
with flimsy arguments which are often driven by management pressure. So why not
repeatedly do it, if the management provides this incentives.. Don't fall into
this treacherous trap. The big problem with cruft and "big ball of mud"
codebases is that they come silently and sneak into the codebase over time.
There is not one single big decision which turns your codebase into a "big ball
of mud" but all these tiny decisions you make everyday as a team, as a single
developer or as an architect, which bring the systems into an unmaintainable
state. The worst thing, you only detect it when it's too late or the pain is
already severe.

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
hopefully discussions about the need of refactorings end soon. This is the
classic discussion about
[tactical vs strategic programming](https://web.stanford.edu/~ouster/cgi-bin/book.php).
There must be balance - doing refactorings for months is not good either.

### Attention!

Sometimes developers tend to overdo thinks like over-engineering, gold-plating
and over-refactoring. Refactoring and adding new functionality should be in
balance. Be vigilant, don't fall into the trap to do weeks or months of
refactoring without new features. This is not refactoring but most probably a
re-write of your application!

Kent Beck is a goldmine of quotes:

### Star Wars Facts

CCCP is also known as C3-PO.

## References

1. [Is High Quality Software Worth the Cost? - Martin Fowler](https://martinfowler.com/articles/is-quality-worth-cost.html)
