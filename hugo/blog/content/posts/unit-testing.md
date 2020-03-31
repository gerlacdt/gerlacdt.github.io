---
title: "Writing better Unit Tests"
date: 2020-03-31T20:08:51+02:00
tags: ["programming", "testing"]
draft: true
---



### Introduction / Motivation

TDD and test engineering culture is considered best practice these
days. Still I often encounter projects without tests or with bad
tests. **Brittle** tests are bad, e.g. when a developer made some
changes in a specific part of the system and suddenly a lot of tests
fail which have nothing to do with this specific part of the
system. Basically a test is brittle if the test fails when a developer
makes unrelated changes to the production code. Tests can also fail
because they are **flaky**. Flaky tests are non-deterministic due to
relying on remote systems, making network calls or accessing remote
databases which data is not owned by the test. This causes tests to
randomly succeed or fail even when production code has not changed.

Both, brittle and flaky, tests are a serious problems in a codebase
and should be avoided at all cost. Otherwise they will succumb
progressively more and more time and effort of your
developers. Finally developers are kept busy to repair broken tests
and productivity suffers enormously. This can happen in disguise and
developers are not even aware of the problem. In some companies the
test coverage is treated as a key metric. Hence if the coverage high,
nobody will question the current state. Maybe all people involved are
very proud of the high coverage, not recognizing their real problem.

Maintainable, fast and deterministic unit tests build the foundation
of a sustainable codebase. But unit tests alone are not enough to
guarantee a usable, successful and bug-free application. Additionally
you need system and UI tests in order to verify production
readiness. The **test pyramid** visualizes this.

<img src="/img/test_pyramid.png" alt="https://stackoverflow.com/questions/56696132/why-is-ui-testing-at-the-top-of-the-test-pyramid" class="medium-zoom-image" width="300">


System tests and UI tests are good enough to check your product as a
whole, but these tests are slow, flaky, not repeatable and often are
conducted manually which makes them bad candidates for daily releases
and continuous integration. If UI tests or system tests fail, they
will not tell developers where the problem lies. Therefore developers
have to make big efforts in order to trace the bug which triggered a
UI test to fail. If you rely heavily on manual regression testing
instead of having a exhaustive unit test suite in place is an
anti-pattern, the **test ice cone**. It is the inverse of the test
pyramid and leads to an unsustainable codebase because failing system
and UI tests leave developers in the dark about the root cause of the
failing test.

<img src="/img/ice_cone.jpg" alt="https://watirmelon.blog/testing-pyramids/" class="medium-zoom-image" width="300">

In this article I will focus on unit tests. Good unit tests should be
clean, maintainable and most notably "useful" for developers. The main
purpose of tests is to save time for developers and keep the code
quality high. A negative example is having a high code coverage, but
developers are busy by fixing 90% of their precious time failing
tests, is a pretty bad situation. Careless use of unit testing can
result in a system that requires more effort to maintain than without
tests and takes more effort to change without actually improving
confidence in the next production release. It is crucial to identify
bad tests and to know how to write good tests.


This blog post is built upon the shoulder of giants. Basically I draw
 from the fantastic book [Software Engineering at
 Google](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
 . Especially from Chapter 12 "Unit Testing". I was also heavily
 inspired by this article about [Programmer Test
 Principles](https://medium.com/@kentbeck_7670/programmer-test-principles-d01c064d7934)
 from Kent Beck, the father of TDD.


### What makes a good unit test?


There are a lot of opinions floating around about how to write good
unit tests. In the following I list the traits which I consider most
important.

##### Tests should be fast

https://martinfowler.com/bliki/UnitTest.html
* compile suite and commit suite (from Martin Fowler)
* compile suite: run it multiple times a minute (can be a single test or test file, no need to run the complete test suite)
* commit suite: run before I am ready to commit (finished the feature, fixed the bug)

* fast (few seconds), at best you need not loose your focus
  (compile/commit suite), run them frequently (during compilation or
  commit)

##### Test should be isolated
* isolated from each other, i.e. they are independent from each other
  and can run in any order. It should be not problem to run tests
  concurrently and in parallel. The workload of big test suites can be
  distributed across different machines.


##### Test should be deterministic
* deterministic (they should be green if the behavior of the
  production code did not change, on the other side tests should only
  fail if the behavior has changed) NOT FLAKY

##### Test should focus on a single unit of the system
* Focus on a single unit/part of the system. If a unit tests breaks it
  should be clear where the problem lies. The other way around is also
  true: If someone changes code of a unit, only corresponding unit
  tests should break. If dozen of other tests fail you probably have
  **brittle** tests. If this happens often to new developers, this
  will be a serious problem because developers loose trust in the test
  suite and they neglect badly needed future refactorings. This
  hampers maintainability and causes the quality of the codebase to
  degrade. More often than not, this happens to many projects, and the
  existing unit test are rather a burden than backing for the
  developers. This makes it easy to understand where the problem lies.
  If you need to start a debugger to understand a failing, the chance
  is high your tests is testing a single unit.

##### Test should be stable (avoid brittle and flaky tests)
* prevent brittle tests (if internal details change, tests should not
  break)

##### Test should be clear, concise and complete
* test should be clear, concise and complete (DAMP over DRY), no
  if/loop or other complicated logic, simple sequence of statements
  Cognitive load should be reduced

  * How to prevent brittle tests
  * How to write clear tests
  * Better Design: Input parameters should comply to a minimum
    interface. Only used properties should be included. Easier stubbing,
    test doubling!
  * Tests are an indicator if the production code design is OK! If you
    have to write complicated tests with a lot of setup code, maybe
    something is wrong.



##### Test should be enduring
* an enduring test is written once. you should never need to change
  the test except the behavior of the corresponding unit changed. ->
  Avoid brittle tests


##### Tests should give you confidence

Finally your test suite should give you confidence that your code
changes are correct and you did not break anything. Further a green
suite should be an indicator that you can deploy confidently to
production. Green tests should give the individual developer a good
feeling about his code changes. Tests should facilitate
maintainability and make the code more comprehensible. Above I gave
some examples when tests do more harm than support the developers. At
this point you need to reconsider your testing strategy. Tests should
improve productivity so you can make changes faster and deliver more
features in less time. If this is not the case, something is wrong
with your test suite.




#### Use Test Doubles wisely

* classical and mockist testing
https://martinfowler.com/articles/mocksArentStubs.html

* test doubles
https://martinfowler.com/bliki/TestDouble.html
Fakes, Stubs, Spies, Mocks

mocking != test doubles
dangerous to overuse mocking frameworks
* seams
* mocking frameworks
* kinds of Test Doubles
 * faking (in-memory DB)
 * stubbing (often done with mock framework)
Dangers of stubbing:
tests become unclear (too many stub statements complicate the test code)
tests become brittle (leaked implementation details of SUT)
tests become less effective (no way to check that stub behaves like real implementation)

* interaction testing (mocking and verifying calls)
prefer state testing over interaction testing
when appropriate: caching feature, check if cache was called

* Test Doubles vs real implementations
- prefer real implementation over test double aka classical testing
- Faking preferred over other test doubles
- overuse of stubbing leads to unclear and brittle tests
- interaction testing should be avoided when possible: it leadss to
  tests that are brittle because of exposes implementation details of
  the SUT.


#### The fallacy of mocking

Mocks make your tests stable and fast. They prevent flaky tests because
mocks replace unstable and slow network calls with predefined,
hard-coded behaviour.

But there is a catch. Overusing mocks is dangerous. There are many
reason why mocks contribute to worsening the codebase:

1. Tests become brittle. With mocks you focus on interaction
   testing. Hence your tests focus on the **internals** of your System
   Under Test(SUT).

2. Using too many mocks/stubs make your tests complicated and hard to
   comprehend. The maintainability of your test code suffers and other
   developers will have a hard to time extend or change the tests.




### TL;DR

* prevent brittle tests
  * strive for unchanging tests
  * test only public API, never make private functions public because you want to test them!
  * test state, not interactions
  * prefer state verification rather than interaction verification
    * interaction verification, e.g. check if a cache gets hit or not (cannot be told by state verification)

* write clear tests
  * make your tests complete and concise
    * [DAMP over DRY when sharing code for tests](https://testing.googleblog.com/2019/12/testing-on-toilet-tests-too-dry-make.html)
    * be consistent, naming should be a defined naming standard
      * arrange-act-assert or given-when-then
      * one common naming standards is:
        * UnitName_StateUnderTest_ExpectedBehavior
    * avoid (complex) logic (no ifs or loops)
    * Only use one assert, a unit test has a small scope, you should
      only test the minimum which verifies the behavior
    * shared variables (good naming CLOSED_ACCOUNT)
    * shared setup (beforeEach, afterEach)
    * avoid shared helpers (magic validate() function which validate a bunch of properties)
    * reduce cognitive load
    * write clear failure messages (test should state the problem, no debugging needed, "ERROR got %actual, want %expected")
