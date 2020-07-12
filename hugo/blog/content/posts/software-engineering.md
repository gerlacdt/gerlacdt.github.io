---
title: "Daniel's Test to write better Code"
date: 2020-05-30T16:38:45+02:00
tags: ["programming", "softwareengineering", ]
draft: true
---


## TL;DR

1. Do you use source control?
2. Can you make a build in one step?
3. Do you make daily builds?
4. Do you have a bug database?
5. Do you fix bugs before writing new code?
6. Do you have an up-to-date schedule?
7. Do you have a spec?
8. Do programmers have quiet working conditions?
9. Do you use the best tools money can buy?
10. Do you have testers?
11. Do new candidates write code during their interview?
12. Do you do hallway usability testing?

***

13. Do you enforce a common code styleguide?
14. Do you write tests?
15. Do you do code reviews?
16. Do you focus on code health?
17. Do you practice Continuous Integration?
18. Do you have a Coaching/Mentoring program?
19. Is you infrastructure reproducible (Infrastrucure as Code)?
20. Is hiring one of the most important process in your company?
21. Do you provide the best technology to your developers?
22. Do you focus on the four key metrics?

In Joel Spolsky's blog post ["The Joel Test: 12 Steps to better
Code"](https://www.joelonsoftware.com/2000/08/09/the-joel-test-12-steps-to-better-code/),
he describes a simple test composed of twelve simple yes-no
questions. For a **yes** you get one point and for a **no** you get no
point. 10 points are acceptable and 12 are perfect. If you have less
than 10 points, you will get in trouble with your software -- sooner
or later.

For a quick self-check, these are the original questions:

1. Do you use source control?
2. Can you make a build in one step?
3. Do you make daily builds?
4. Do you have a bug database?
5. Do you fix bugs before writing new code?
6. Do you have an up-to-date schedule?
7. Do you have a spec?
8. Do programmers have quiet working conditions?
9. Do you use the best tools money can buy?
10. Do you have testers?
11. Do new candidates write code during their interview?
12. Do you do hallway usability testing?


All these points are still highly relevant and they are essential for
today's software development.

Although Joel's Test is still an excellent indicator for good software
development and engineering, 20 years have past and many game changing
technologies have emerged like mobile apps and the public cloud with
AWS, Azure and GCP. Further **open source** projects, bolstered by the
success of [git](https://git-scm.com/) and
[github](https://github.com/), took over and are even better than
their proprietary counterparts, e.g. PostgreSQL, Kubernetes, Docker.


In this article i want to extend Joel's questions for the modern world
with my own:

13. Do you enforce common code styleguides and rules?
14. Do you write tests?
15. Do you do code reviews?
16. Do you focus on code health?
17. Do you practice Continuous Integration?
18. Do you have a Coaching/Mentoring program?
19. Is you infrastructure reproducible (Infrastrucure as Code)?
20. Is hiring one of the most important process in your company?
21. Do you provide the best technology to your developers?
22. Do you focus on the four key metrics?




All these points heavily contribute to a sustainable and healthy
codebase which is the foundation for a successful long-term project.

What is sustainable code? Lessons in Sustainablity
https://youtu.be/zW-i9eVGU_k

> Your organization's codebase is sustainable when you are able to
change all of the things that you ought to change, safety, and can do
so for the lifetime of your codebase.


* Software engineering KPIs
 based on https://www.oreilly.com/library/view/software-engineering-at/9781492082781



#### Rating/Scoring

* <= 21 you must improve
* 22 high-performer
* 23 high-performer
* 24 state-of-the-art


#### Do you enforce a common code styleguide?

* better readability
* enforce consistency with linters, static code analyzers and Code Reviews (we talk later more about reviews)
* enforce readablity, autoformatting, code styleguides (if you not a digital company and do not wnat to put the big effort and create you own guideline, just pick an existing one from Google, Facebook or other company which open source their code style guides.
* KPI: new developers can write consistent code according to the guidelines/rules without looking at them
* KPI: no bikeshedding, how much time do your developers waste on styleguides, intendation, formatting discussions?


#### Do you write tests?

Live a Testing Culture.

* KPI: code coverage for unit tests
* KPI: tests are fast, < 10 secs
* KPI: prevent brittle, flaky tests, otherwise developers loose trust
* no more changes without a corresponding test
* no bugs fixed without a tests demonstrating the bug is indeed fixed
  (previously failed, now passes)
* tests need to easy to run (no extra effort for developers, otherwise
  nobody will do it)

* Reverting expectations: Titus Winters, Beyonce Rule "If you liked it, you shoulda  put a test on it!"

#### Do you do code reviews?

* KPI: reviews are done for ALL code changes
* KPI: reviews are done regularly, < 1 workday
* uphold high quality



### Version Control (1. Do you use source control?)

* https://martinfowler.com/articles/branching-patterns.html
* KPI: trunk-based development. How often do you merge to trunk?
* KPI: everything in VC: code, infrastructure, configuration, documentation, architecture, No Confluence!
* single branch == trunk with feature toggles, always releasable



### Continuous Integration/Delivery

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


### Coaching/Mentoring
* consistent IT philosophy
* software methodologies, documented architecture/design, coding
standards, dev tools, reusable artifacts, QA processes, Testing
Culture, Code Reviews, coding practices like Version Control, CI/CD
* Teach them the rules of the game, molding the new recruits into a
team.

### Hiring (11. Do new candidates write code during their interview?)

Engineering Manager should know coding. He should be able to talk the
same language like developers.

> I've built a lot of my success off finding these "truly gifted"
> people, and not settling for 'B" and "C" players, but really going
> for the "A" players. ... I found that when you get enough "A"
> players together, when you go through the incredible work to find
> five of these "S" players, they REALLY like working with each other
> because they have never had a chance to do that before. And, they
> don't want to work with "B" and "C" players, and so it becomes
> self-policing, and they only want to hire more "A" players, so you
> build up these pockets of "A" players, and it propagates ..."

Steve Jobs

### Tooling (9. Do you use the best tools money can buy?)

- public cloud AWS, Azure, GCP
- collaboration tools like github for Version Control
- google docs for parallel working on one document
- ...


### Focus on Code health

https://testing.googleblog.com/2016/08/hackable-projects.html
* continuously reduce technical debt
* no new features before fixing existing bugs



#### Documentation

* KPI: new developer is ready to start developing,  < 1 day of joining the company or a new project
* KPI: doc is up to-date and complete (architecture/design, development, scripts, infrastructure are in version control)



#### established these policies and coach them you new employees

Newcomers should be introduced on their very first days. It's good to
seek new perspectives/ideas from new employees. But you need a working
foundation first. Otherwise all new senior developers will drive
change. Eventually different teams have different policies, dependent
who was in the teams. This quickly leads to heterogeneity.


### Four key metrics for software delivery performance

In [Accelerate](https://itrevolution.com/book/accelerate/)/[DevOps Report 2019 p18](https://services.google.com/fh/files/misc/state-of-devops-2019.pdf) four key metrics are defined which differentiate from low and high performances?
1. lead time
2. deployment frequency
3. mean time to restore (MTTR)
4. change fail percentage


### Conclusion

**Digital** companies like Google, Amazon, Facebook, Microsoft or
Netflix have these traits in their blood and they live by these
standards already for decades. For them "software" is their core
product, they cannot afford piling up technical debt since they would
cripple and risk their own future. This is well understood by them. In
contrary to non-digital companies with a oldschool enterprise IT
departments and outsourced IT.

These enterprises never considered "software" as a competitive
advantage. But slowly even they understand that ["software eats the
world"](https://a16z.com/2011/08/20/why-software-is-eating-the-world/)
and they want to do develop software in house again. In such
situations, missing know-how and "wrong" people with lack of skills
are common.  This article wants to be a simple and easy applicable
guideline for them to establish a good coding mentality with good
practices.
