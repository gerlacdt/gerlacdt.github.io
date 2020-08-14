---
title: "Writing better Code"
date: 2020-05-30T16:38:45+02:00
tags: ["programming", "softwareengineering", ]
draft: true
---

In Joel Spolsky's blog post ["The Joel Test: 12 Steps to better
Code"](https://www.joelonsoftware.com/2000/08/09/the-joel-test-12-steps-to-better-code/),
he describes a test composed of twelve simple yes-no questions. For a
**yes** you get one point. 10 points are acceptable and 12 are
perfect. If you have less than 10 points, you will get in trouble with
your software -- sooner or later.

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
technologies have emerged like mobile apps, the public cloud and in
general better tooling is available. The success of
[git](https://git-scm.com/) and [github](https://github.com/) changed
how we develop software. In this article i want to extend Joel's test
with contemporary questions:

13. Do you enforce a common code styleguide?
14. Do you write tests?
15. Do you conduct code reviews?
16. Do your developers write documentation?
17. Do you focus on code health?
18. Do you practice continuous integration?
19. Do you have a mentoring program?
20. Is your infrastructure reproducible?
21. Are you doing your best to keep your engineers?
22. Do you provide the best technology to your developers?
23. Do you focus on the four key metrics?
24. Do you empower your developers?

The extended test consists of 24 yes-no questions. As with Joel's
Test, for a **yes** you get one point. The ranking is:

* <= 20 points, you must improve
* 21 points, you are ok
* 22 points, you are a high-performer
* 23 points, you are a high-performer
* 24 points, you are best-in-class

Further I want to emphasis that **sustainablity** is my main intention
for the test. Many questions contribute directly or indirectly to a
sustainable and healthy codebase which is crucial for a successful
long-term software project and in general for a successful software
company. [Titus Winters](https://youtu.be/zW-i9eVGU_k) defines a
sustainable codebase as:


> Your organization's codebase is sustainable when you are able to
change all of the things that you ought to change, safety, and can do
so for the lifetime of your codebase.


#### Do you enforce a common code styleguide?

**Consistency** is one of the most important properties of a codebase.
It bolsters readability and maintainability which are essential for
sustainable code. A consistent codebase is easier to grasp and makes
onboarding new developers faster. New programmers are guided by the
prevailing style and can adapt quickly to it. Consistency is also an
indicator for coder's discipline, clearly you don't want to have dead
code, unused imports, wrong indentations, and other intricacies in
your codebase. The desired consistency can be achieved by a code
styleguide.

At best you enforce the rules of the styleguide via tooling like
static code analyzers, linters and autoformatting tools. Often these
tools are integrated into the build or they are run even before a
commit. Further there are also manually measures like [code
reviews](#codereview) to enforce a common code style.

A consistent code style increases productivity, e.g. linters prevent
sloppy programming errors, autoformatters leave no room for useless
(sometimes religious) discussions about indentation and formatting
rules. All code looks the same. Developer's taste and ego take a back
seat.


#### Do you write tests?


Writing automatic test is a major trait of a sustainable
codebase. There are many kind of tests but the best known
classification comes with the [Test
Pyramid](https://martinfowler.com/articles/practical-test-pyramid.html).

* Unit Tests
* Service Tests
* User Interface Tests

Particularly **unit tests** build the foundation and give developers
confidence to move fast and not to break existing functionality. Unit
testing is a major pillar of a fast feedback loop. This keeps
developers happy and the quality high. In general, tests act as a
safety net, prevent new bugs from being introduced and old bugs from
reoccurrence.

Without automatic tests your codebase will erode and only long-term
developers will be capable to make changes. Onboarding new developers
will take months or will never succeed at all. Over time developer
speed will slow down and finally come to a complete halt. Heavily
relying on manual testing before a release is a clear indicator of
missing automatic tests and extends the release cycle by days or
weeks. High performers deploy on a daily basis which is not possible
with manual testing phases. Therefore manual testing should be reduced
to a minimum or completely avoided.

Establishing a good testing culture is especially important. E.g.

* no code changes without a corresponding test
* no bugfix without a test demonstrating the bug is indeed fixed
* unit test should be fast, so developers run them continuously
* unit test code coverage should be at a reasonable level, ~70%, don't
  strive for 100%

At Google, they practice the [Beyonce Rule "If you liked it, you
shoulda put a test on
it!"](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
This rule inverts responsibility, e.g. if someone breaks a feature and
there was no test, the original author of the broken feature "shoulda
put a test on it!".

#### Do you conduct code reviews? {#codereview}

Code reviews are a critical step in your software engineering
process. Not only they prevent entering bugs into your mainline but
they are a major tool for knowledge transfer, learning and mentoring.
The code review process fosters a common understanding between
reviewers and author and offers a platform for discussions about
trade-offs and design decisions. Reviews are not only focused on
correctness but also on readability, performance and other
non-functional properties.

All of that will lead to better solutions. Further reviewers practice
their code reading skill which is as important as code
writing. Besides compiling, linting and running tests, code reviews
form a major step in a developers feedback loop. Code should never be
committed into mainline without a proper code review.

Because code reviews can conjure up heated discussions, reviewers
should comply to some [code review
guidelines](https://google.github.io/eng-practices/review/reviewer/)
in order to guarantee a flawless experience.

#### Do developers write documentation?

Documentation starts with the code. Code comments or a good
description of a pull request are good examples. Thereby good
documentation focuses on **why** something was done. An extensive
`README.md` acts as the "front-page" of a project and should contain
its purpose and instructions for developers to set up their local
environment for development, e.g installing prerequisites, building
the project, running the test.

Additionally a variety of documents with different purposes exist:

* Design Docs (showing alternative solutions, why was one approach
  chosen over the others?)
* Architecture Diagrams (System overview, showing coherence between
  components)
* Operational Playbooks for [Software Reliability Engineers
  (SREs)](https://landing.google.com/sre/workbook/chapters/on-call/)
  (operational instructions fighting outages)

All these documents should be written by developers, operators or
other technical people. Living, up-to-date documentation makes a
project more understandable and long-term project members are capable
of answering questions why things were done in the past -- in the
majority of projects, the top answer is "this is historically grown".
The only way to get real insights is conducting time consuming
face-to-face interviews. Documentation helps to keep an overview over
an ever-growing project, to facilitate the start for new developers
and to build a searchable knowledge base. Past decisions should be
transparent through good documentation and not hidden in people's
heads.


### Do you focus on code health?

Developer happiness is major criteria for high quality code. If your
developers working on a shitty codebase, they quickly adapt to the
poor quality or leave. The existing codebase act as a **role
model**. It is important to continuously focus on code health which
bolster developer happiness and therefore aims for better code
quality. The best coders are repelled by bad code and attracted by
healthy code. But what is a healthy codebase?

A codebase is healthy when:

* you have fast builds
* you have an easy development setup
* you have fast and maintainable tests
* you have clean, readable, loosely coupled and consistent code
* you can easily debug the system
* you continuously tackle technical debt

You can find a much more exhaustive explanation of code health in
[Google's Testing Blog about Code
Health](https://testing.googleblog.com/2016/08/hackable-projects.html).

A sign of bad code is:

* complicated developer setup
* hard to debug, missing monitoring, noisy garbage logs
* long build times
* inconsistent code (dead code, unused imports, different formatting
  styles, no code styleguide)
* large merge conflicts due to long running feature branches, broken mainline
* no tests, flaky tests, hard-maintainable tests because of mocking overuse

Never trade dirty code or workarounds due to time or release pressure
for code health. You will end up very badly in the long run. Worse
yet, you get in a vicious cycle because bad code slows you down and in
order to fulfil the next release you add more dirty workarounds. So
always prioritize code health, even when it looks counterintuitive at
first sight.

### Do you practice continuous integration?

Nowadays Continuous Integration is hopefully commonplace. At best, you
work with trunk-based development and your mainline is always
releasable, preferably with feature toggles. Highest priority is to
keep the mainline green and a broken build should be fixed
immediately. Small and frequent releases prevent bugs or even outages
which happen when large releases are done intermittently.

CI helps to prevent tedious merge conflict resolutions because your
developers regularly commit into mainline. Additionally you will get
rid of time consuming integration problems at the end of your
implementation phases.

"Agile"'s main goal is to identify risks as early as possible and not
to postpone them till the end of a project. CI supports exactly
that. With CI, you will detect integration, design problems
early. This allows you to make course correction on the way.


### Do you have a mentoring program?

Mentoring is a great way to coach new employees. With a mentoring
program, you not only foster a consistent engineering culture with
best common practices like software methodologies, code styleguides,
code reviews, testing culture and other policies across the whole
organization but it is mandatory for molding new recruits into a
productive team.

Mentored developers are more likely to stay. They feel approved and
treated as first-class employees because you invest time and effort in
them from the beginning. It is a fantastic way to give raise to great
and loyal engineers in the long-run.


### Is your infrastructure reproducible?

Today, Public Cloud Providers like AWS and Azure provide APIs to
create, update and manage your infrastructure.  With the usage of
APIs, it is natural to retain your whole [Infrastructure as Code
(IaC)](https://en.wikipedia.org/wiki/Infrastructure_as_code).

Since we are speaking about code, all previous points apply for
infrastructure too. You should keep it under version control, conduct
code reviews, run tests in you CI pipeline and keep your codebase
clean via formating and linting tools. Known tools for infrastructure
are [Kubernetes
manifest](https://kubernetes.io/docs/concepts/cluster-administration/manage-deployment/),
[Helm charts](https://helm.sh/),
[Terraform](https://www.terraform.io/) or [AWS
Cloudformation](https://aws.amazon.com/cloudformation/).

A good indicator if your infastructure is reproducable is how you
treat your servers, like cattle or pets? Recreation of a failing
server should be easier than duct-taping the failing one. Beware of
[snowflake
servers](https://martinfowler.com/bliki/SnowflakeServer.html)!


### Are you doing your best to keep your engineers? {#keep}

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

Hiring good engineers is very important but keeping them is too!  The
best engineering teams share a common mindset. Engineers want to work
with other engineers sharing the same interests and approaching the
same goals. This creates a virtuous circle, engineers motivate each
other in order to achieve the best possible outcomes. Be careful
though, a single new hire can disturb an A-player team's
balance. That's why the hiring process is very important.

You should treat your engineers as first-class employees. Companies,
recognizing their value, provide tech-specific careers with
compensations comparable to high-management salary level.

Further, engineering managers play an essential role to keep your
engineers (happy). They should know how to code and share the same
engineering mindset. If your engineering managers are not technical
skilled, your engineers will not take them seriously. Worse yet, they
will leave at some point in time because they feel misunderstood.

You should strive to hire A players and to keep them.


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

The best way to measure if your engineering department is top-notch,
 are the four key metrics, defined in fantastic book
 [Accelerate](https://itrevolution.com/book/accelerate/).

High performers strive for:

1. Short lead time
2. High deployment frequency
3. Short mean time to restore (MTTR)
4. Low change fail percentage

Some of the above metrics seem contradictory like high deployment
frequency and low change fail percentage. But according to the studies
of the [DevOps
Report](https://services.google.com/fh/files/misc/state-of-devops-2019.pdf)
the best companies perform in **all** key metrics excellently.

If you look for improvements, it is critical to focus on these four
key points permanently. Don't fall prey to expensive agile
transformation methodologies like SAFe or other IT
consultants. Usually they promise a magical and fast performance boost
but often they turn out as insignificant.

### Do you empower your developers?

Many companies treat their developers as pure delivery teams,
i.e. they are used to implement features based on roadmaps defined by
leadership, stakeholders for product management departments. Why this
is a bad idea, see [Marty Cagan's
post](https://svpg.com/product-fail/). With this approach you only
getting half of their value. Further companies think developer teams
can be easily replaced or even outsourced. They are treated as
mercenaries but as we know from [John
Doerr](https://svpg.com/missionaries-vs-mercenaries/)

> we need teams of missionaries, not teams of mercenaries.

But for an outsourced IT, it is impossible to work in missionary mode.

In order to get the most of you developer teams, you must hire
missionaries and empower them. Empowered developers are first-class
employees. Regarding digitization, developers are the best source of
innovation because they know the enabling technology. But it is hard
to attract, identify and to keep top talent. See, how to hire
(11. from Joel's test) and [keep the best](#keep).


Empowered developers should not only implement predefined features
from stakeholders but are invited to come up with their own solutions
to satisfy the customer needs. Therefore we should give developers
**problems** to oslve not features to implement. It is mandatory that
developers understand the business context. A quick self-check if you
have empowered developers:

* Can developers identify themselves with the customers?
* Do they attend user tests regularly?
* At best, do your developers use the application themselves?
* Do they know the pains of the customers or are they shielded away by
  a wall of stakeholders, business departments, program managers and
  never have the chance to get in contact with customers?
* Do your developers drive the own features to implement and not only
  getting them at the sprint-planning or from a roadmap?

Finally empowered engineers means a mindset change in the whole
company, see [Marty
Cagan](https://svpg.com/keys-to-successful-transformation/):

> (the company is) moving from a model where the technology teams
> exist to “serve the business” to one where they exist to “serve the
> customers, in ways that work for the business.

### Conclusion

**Digital** companies like Google, Amazon, Facebook, Microsoft or
Netflix have these traits in their blood and they live by these
standards already for decades. For them, "software" is their core
product, they cannot afford piling up technical debt since they would
cripple and risk their own future. This is well understood by them. In
contrary to non-digital companies with oldschool enterprise IT
departments and outsourced IT.

These enterprises never considered "software" as a competitive
advantage. But slowly even they understand that ["software eats the
world"](https://a16z.com/2011/08/20/why-software-is-eating-the-world/)
and they want to do develop software in-house again. In such
situations, missing know-how and "wrong" people with lack of skills
are common.  This article wants to be a simple and easy applicable
guideline for them to establish a good coding mentality and culture
with good practices. I hope you can apply the full test and gain some
insights about strengths, weaknesses and possible improvements in your
company.
