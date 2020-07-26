---
title: "Writing better Code"
date: 2020-05-30T16:38:45+02:00
tags: ["programming", "softwareengineering", ]
draft: true
---

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


All points are still relevant and essential for today's software
development.

Although Joel's Test is still an excellent indicator for good software
development and engineering, 20 years have past and many game changing
technologies have emerged like mobile apps, the public cloud with AWS,
Azure and GCP and in general better tooling is available. The success
of [git](https://git-scm.com/) and [github](https://github.com/)
changed how we develop software. In this article i want to extend
Joel's test with contemporary questions:

13. Do you enforce a common code styleguide?
14. Do you write tests?
15. Do you do code reviews?
16. Do developers write documentation?
17. Do you focus on code health?
18. Do you practice continuous integration?
19. Do you have a mentoring program?
20. Is you infrastructure reproducible?
21. Is hiring the most important process in your company?
22. Do you provide the best technology to your developers?
23. Do you focus on the four key metrics?


All these points heavily contribute to a sustainable and healthy
codebase which is the foundation for a successful long-term project.

What is sustainable code? Lessons in sustainability
https://youtu.be/zW-i9eVGU_k

> Your organization's codebase is sustainable when you are able to
change all of the things that you ought to change, safety, and can do
so for the lifetime of your codebase.

Titus Winters

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

knowledge transfer and learning/mentoring, prevent bugs, foster common
understanding, platform for discussions for trade-offs, better
solutions, keep everybody in the loop, practice your code reading
skill (more important than writing code!)

One of the most important steps in the feedback loop for developers
after compiling, unit testing and linting.

In order to guarantee a flawless experience developers and reviewer
should comply to some [code review
guidelines](https://google.github.io/eng-practices/review/reviewer/).

#### Do developers write documentation?

* KPI: new developer is ready to start developing,  < 1 day of joining the company or a new project
* KPI: doc is up to-date and complete (architecture/design, development, scripts, infrastructure are in version control)


Good documentation describres **why** something was done.

Why a thread-model was chosen in favor for a event-driven model?
Why did you chose a monotlith over a microservice architecture?
Why did you skip caching for some endpoints?
All these question needs to be answered.

- Design Docs (showing alternative solution, argument why was one approach chose over the others)
- Architecture Diagrams
- Code comments (good docs start with good comments)
- PR (describe why, we need this change, and the purpose), maybe reference the Design Doc


Good, up-to-date documents are foundation of a sustainable software
project. They help to keep an overview over an ever-growing project,
give new developers a good start and build a searchable knowledge base
and keep track of made decision in the past.

However, in old software projects, i often encounter a gazillion of
documents, deprecated, scatter around in thousand places like
powerpoints, Excel and Word documents stored in Sharepoint, not
indexable and thus not searchable. Sometimes these worthless documents
lie in company wikis like Confluence, but they are still deprecated
and the provided search is often primitive. Worse yet, these docs
contain sometimes false information and reading them is only a waste
of time for new project members.

Good documentation makes a project more understandable and long-term
project members are capable of answering critical questions why things
were done in the past. In the majority of projects you only get "this
is historically grown" and the only way to find the real answer is
doing a lot of time consuming face-to-face interviews.

There are documents for different purposes. Code comments are the
first documentation a developer writes. Alas, many devs don't write
good comments, they often describe what the codes does which is often
obvious if you know the programming language. But good comments
explain **why** the code was written and explain why decisions were
made.

For Software Reliability Engineers (SREs), [on-call
playbooks](https://landing.google.com/sre/workbook/chapters/on-call/)
are important. They contain how to operate a system and response
failure. E.g. where can one find the logs, how can one access the
monitoring system, what are critical monitor values, how to
restart/scale the system, how to resolve a specific incident etc.

A README is the "front-page" of a project. It should contain an
overview about the project, its purpose, instructions for developers
to set up their dev environment and should answer questions like how
to build the project, run tests etc.


### Do you focus on code health?

https://testing.googleblog.com/2016/08/hackable-projects.html
* continuously reduce technical debt
* no new features before fixing existing bugs

Unit tests are not for the author but for all future readers of the
code and future changers of the code. Reading/Changing happen X-times
more often than writing the code. So always keep your focus on
maintainability and readability.

### Do you practice continuous integration?
* how often do you release you product? (daily, weekly, monthly, quarterly, yearly)
* https://martinfowler.com/articles/branching-patterns.html
* KPI: trunk-based development. How often do you merge to trunk?
* KPI: everything in VC: code, infrastructure, configuration, documentation, architecture, No Confluence!
* single branch == trunk with feature toggles, always releasable


### Do you have a mentoring program?
* consistent IT philosophy
* software methodologies, documented architecture/design, coding
standards, dev tools, reusable artifacts, QA processes, Testing
Culture, Code Reviews, coding practices like Version Control, CI/CD
* Teach them the rules of the game, molding the new recruits into a
team.

**established these policies and coach them you new employees**

Newcomers should be introduced on their very first days. It's good to
seek new perspectives/ideas from new employees. But you need a working
foundation first. Otherwise all new senior developers will drive
change. Eventually different teams have different policies, dependent
who was in the teams. This quickly leads to heterogeneity.

- great way to transfer the company culture to the new employees from the start


### Is your infrastructure reproducible?

Infrastructure as Code

Version Control, Code Reviews -  all this applies for configuration code also.
Kubernetes/Helm yamls, Terraform modules, AWS Cloudformation, or whatever you use


### Is hiring the most important process in you company?

(11. Do new candidates write code during their interview?)

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



### Do you provide the best technology to your developers?

Joel's 9. Do you use the best tools money can buy?

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

Tooling

- public cloud AWS, Azure, GCP
- collaboration tools like github for Version Control
- google docs for parallel working on one document


Processes

- reducing overhead
- no bullshit finance approval process (anti agile) Deciding about
  millions for AWS or Azure but need approval 150$ for a domestic
  flight...
- on-premise clouds are often enterprise clouds with all the approval
  chain, ticket process with manual workprocess in the background and
  fake self-services. Developers want the convenience of a public cloud!


enterprise non-cloud
https://architectelevator.com/cloud/enterprise-non-cloud/


### Do you focus on the four key metrics?

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
practices. I hope you can apply the full test for your development
department and gain some insights about weaknesses and possible
improvements.
