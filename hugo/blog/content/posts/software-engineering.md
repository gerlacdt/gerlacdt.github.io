---
title: "Key Metrics for good Software Engineering"
date: 2020-05-30T16:38:45+02:00
tags: ["programming", "softwareengineering", ]
draft: true
---

[Joel Spolsky 12 steps to write better code](https://www.joelonsoftware.com/2000/08/09/the-joel-test-12-steps-to-better-code/)

* Four key metrics for software delivery performance (or in general organizational performance)

In [Accelerate](https://itrevolution.com/book/accelerate/)/[DevOps Report 2019 p18](https://services.google.com/fh/files/misc/state-of-devops-2019.pdf) four key metrics are defined which differentiate from low and high performances?
1. lead time
2. deployment frequency
3. mean time to restore (MTTR)
4. change fail percentage

I wonder if we can define some key metrics which defines good,
sustainable software engineering.

I contemplate about if we can established metrics that define a good
software engineering process. Eventually these metrics should give
insight if the written code today is sustainable in the future.

What is sustainable code? Lessons in Sustainablity
https://youtu.be/zW-i9eVGU_k

> Your organization's codebase is sustainable when you are able to
change all of the things that you ought to change, safety, and can do
so for the lifetime of your codebase.


* Software engineering KPIs
 based on https://www.oreilly.com/library/view/software-engineering-at/9781492082781
### Code styleguides/rules
* enforce consistency with linters, static code analyzers and Code Reviews (we talk later more about reviews)
* enforce readablity, autoformatting, code styleguides (if you not a digital company and do not wnat to put the big effort and create you own guideline, just pick an existing one from Google, Facebook or other company which open source their code style guides.
* KPI: new developers can write consistent code according to the guidelines/rules without looking at them
* KPI: no bikeshedding, how much time do your developers waste on styleguides, intendation, formatting discussions?



### Code reviews
* KPI: reviews are done for ALL code changes
* KPI: reviews are done regularly, < 1 workday

### Documentation
* KPI: new developer is ready to start developing,  < 1 day of joining the company or a new project
* KPI: doc is up to-date and complete (architecture/design, development, scripts, infrastructure are in version control)


### Testing (Culture)
* KPI: code coverage for unit tests
* KPI: tests are fast, < 10 secs
* KPI: prevent brittle, flaky tests, otherwise developers loose trust
* no more changes without a corresponding test
* no bugs fixed without a tests demonstrating the bug is indeed fixed
  (previously failed, now passes)
* tests need to easy to run (no extra effort for developers, otherwise
  nobody will do it)


### Version Control

* https://martinfowler.com/articles/branching-patterns.html
* KPI: trunk-based development. How often do you merge to trunk?
* KPI: everything in VC: code, infrastructure, configuration, documentation, architecture, No Confluence!


### Continuous Integration
* Fixing bugs has highest priority. How long is you build red in average? < 1 hour
* how often do you release you product? (daily, weekly, monthly, quarterly, yearly)

### Use best technology
* to run tests and get fast feedback to the developers CI/CD
* strive for a homogenous technology stack, otherwise you have to
support too many Getting to the sweet spot is hard, because developers
tend to always use the bleeding edge technology but then it's hard to
build proficiency in one tech-stack. The knowledge of you staff will
be diversified and many teams do need to reinvent the wheel,
e.g. different testing-frameworks, different deployment tools,
different infrastructure-stack like Kubernetes/Consul/AWS ECS,
different programming languages means all web-frameworks differ and
reusability is not possible between different teams

### Infrastructure as Code

Version Control, Code Reviews -  all this applies for configuration code also.
Kubernetes/Helm yamls, Terraform modules, AWS Cloudformation, or whatever you use


### Healthy codebase

https://testing.googleblog.com/2016/08/hackable-projects.html

Unit tests are not for the author but for all future readers of the
code and future changers of the code. Reading/Changing happen X-times
more often than writing the code. So always keep your focus on
maintainability and readability.


### Coaching
* consistent IT philosophy
* software methodologies, documented architecture/design, coding
standards, dev tools, reusable artifacts, QA processes, Testing
Culture, Code Reviews, coding practices like Version Control, CI/CD
* Teach them the rules of the game, molding the new recruits into a
team.

#### established these policies and coach them you new employees

Newcomers should be introduced on their very first days. It's good to
seek new perspectives/ideas from new employees. But you need a working
foundation first. Otherwise all new senior developers will drive
change. Eventually different teams have different policies, dependent
who was in the teams. This quickly leads to heterogeneity.
