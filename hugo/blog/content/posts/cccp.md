---
title: "The Continuous Clean Code Process (CCCP)"
date: 2023-12-29T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: false
---

Most software projects end up in a
[_big ball of mud_](https://wiki.c2.com/?BigBallOfMud). The major cause is
neglecting internal quality and focusing on adding features with dirty hacks
because of unrealistic timelines. Code has the natural tendency to erode if you
don't launch countermeasures permanently. This observation applies to all
systems and is also known as the
[the second law of thermodynamics](https://en.wikipedia.org/wiki/Second_law_of_thermodynamics):

> Systems tend to arrive at a state [...] where the entropy is highest [...]

The only way to prevent a big ball of mud is to ingrain continuous refactoring
into the software creation process, i.e. continuously writing clean code.
Refactoring must be a regular task whereby it can happen before or after
implementing a new feature itself:

> **Make it work, make it right, make it fast.** - Kent Beck (refactor
> afterwards)

> **Make the change easy (this can be hard), then make the easy change.** - Kent
> Beck (refactor beforehand)

Often teams code for months or years without touching and restructuring the
existing codebase. They perpetually add features with dirty workarounds and
without thinking about the overall structure. This accumulates and adding new
functionality will become harder, and eventually impossible
[[1]](https://martinfowler.com/articles/is-quality-worth-cost.html)

<p align="center">
    <img src="/img/clean_code_over_time.png" alt="clean_code_over_time" class="medium-zoom-image" width="600">
</p>

It is always better to stick to clean code and avoid shortcuts. Investing in
internal quality is cheaper than adding cruft.
[Cruft](https://martinfowler.com/bliki/TechnicalDebt.html) makes the system
harder to modify and is introduced due to laziness, time pressure or simply lack
of knowledge. Beware of programmers who did not internalize clean code. In order
to make the deadline, they integrate dirty hacks, workarounds or skip tests.
They justify their actions with flimsy arguments. Worse yet, because the
management is not aware of internal quality, the milestone is perceived as a
success and dirty developers are sometimes celebrated as heros. In consequence
of such bad incentives, the codebase will deteriorate quickly since dirty
developers gain the upper hand and quality-focused developers are ignored (and
leave the company). The epitome of such bad developers are
[tactical tornados](https://web.stanford.edu/~ouster/cgi-bin/book.php) -- loved
by the management, hated by fellow team members.

**The big problem with cruft is that it comes silently and sneaks into the
codebase over time.** There will be no single decision which turns a codebase
into a big ball of mud all of a sudden. Instead daily tiny decisions bring the
system slowly into an unmaintainable state and the problem will only be detected
when it is too late and the pain is severe. More often than not, the only rescue
is a complete rewrite of the application.

One of the biggest mistakes developers can make is skipping tests due to time
pressure. If a developer team made the milestone, everybody is happy and the
team is rewarded. This leads to a positive feedback loop which exacerbates the
situation: the developers will regularly skip tests or generally write bad code
since they get rewarded by the clueless management. Bad developers bring up the
idea to skip tests themselves because they believe they are faster without
tests. This is a fallacy! As soon other developers need to make a change, they
will be slowed down immensely and bugs are introduced easily. Even the original
authors will struggle with their own code without tests when they have not
looked into it for some time. **A good test suite act as a safety net and gives
guidance how to use the API. All developers benefit from it, introduce less bugs
and are faster.**

> The only way to go fast, is to go well. - Uncle Bob

### <span style="color: red">Attention!!!</span>

Is refactoring always the right way? _It depends_. Some developers tend to
overdo things like over-engineering, gold-plating and over-refactoring. Be
vigilant, don't fall into the trap doing weeks or months of refactoring without
new features. This is not refactoring but most probably a rewrite of an
application. Refactoring and adding new functionality should be in balance.
Finding a balance is a discussion between
[tactical vs strategic programming](https://web.stanford.edu/~ouster/cgi-bin/book.php).
Investing 10-20% of time into code improvements is a good starting point.

### Final Thoughts

Practicing the _Continuous Clean Code Process_ (CCCP) is critical to prevent a
big ball of mud. Through continuous refactorings, not only codebases stay clean,
they are fun and as a side-effect teams end up with a maintainable codebase
which is a pleasure to work with. Developer happiness will be high. **A clean
codebase builds the foundation for fast developemnt over time and high-quality
products.** Organizations will also profit since happy developers are more
productive and attract even more good developers. Finally there is no excuse to
write bad code :smile: -- but it is still hard.

### Star Wars Fun Facts

CCCP is also known as C3-PO.

### References

1. [Is High Quality Software Worth the Cost? - Martin Fowler](https://martinfowler.com/articles/is-quality-worth-cost.html)
2. [A Philosophy of Software Design - John Ousterhout](https://web.stanford.edu/~ouster/cgi-bin/book.php)
