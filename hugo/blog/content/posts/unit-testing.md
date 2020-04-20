---
title: "Writing better Unit Tests"
date: 2020-03-31T20:08:51+02:00
tags: ["programming", "testing"]
draft: true
---



### Introduction / Motivation

TDD and test engineering culture is considered best practice these
days. Still I often encounter projects without tests or with bad
tests. **Brittle** tests are bad. Maybe your experienced this
yourself, you made some changes in a specific part of the system and
suddenly a lot of other tests fail which have nothing to do with this
specific part of the system. Basically a test is brittle if the test
fails when a developer makes unrelated changes to the production
code. Tests can also fail because they are **flaky**. Flaky tests are
non-deterministic due to relying on remote systems, making network
calls or accessing remote databases. This causes tests to randomly
succeed or fail even when production code has not changed.

Both, brittle and flaky, tests are a serious problems in a codebase
and should be avoided. Otherwise they will succumb progressively more
and more time and effort of your developers. Finally developers are
kept busy with repairing broken tests and productivity suffers
enormously. This can happen in disguise and developers are not even
aware of the problem. In some companies test coverage is treated as a
key metric. Hence if the coverage high, nobody will question the
current state. Maybe all people involved are very proud of the high
coverage, not recognizing their real problem.

Maintainable, fast and deterministic unit tests build the foundation
of a sustainable codebase. But unit tests alone are not enough to
guarantee a usable, successful and bug-free application. Additionally
you need system and UI tests in order to verify production
readiness. The **test pyramid** visualizes this.

<img src="/img/test_pyramid.png" alt="https://stackoverflow.com/questions/56696132/why-is-ui-testing-at-the-top-of-the-test-pyramid" class="medium-zoom-image" width="300">


System tests and UI tests are good to check your product as a whole,
but these tests are slow, flaky, not repeatable and often are
conducted manually which makes them bad candidates for [continuous
integration and
delivery](https://martinfowler.com/books/continuousDelivery.html). If
UI tests or system tests fail, developers have a hard time to locate
the problem. It is an onerous task to trace the bug which triggered a
UI test to fail. If a project relies heavily on manual regression
testing instead of an exhaustive unit test suite is an anti-pattern,
the **test ice cone**. It is the inverse of the test pyramid and leads
to an unsustainable codebase because failing system and UI tests leave
developers in the dark about the root cause.

<img src="/img/ice_cone.jpg" alt="https://watirmelon.blog/testing-pyramids/" class="medium-zoom-image" width="300">

In this article I will focus on unit tests. Good unit tests should be
clean, maintainable and most notably "useful" for developers. The main
purpose of tests is to save time for developers and keep the code
quality high. But careless use of testing can have also negative
effects on productivity and code quality. E.g if developers loose a
majority of their time fixing tests instead of building new
features. "Wrong" testing can result in a system that requires more
effort to maintain than without tests and takes more effort to change
without actually improving confidence in the next production
release. It is crucial to identify bad tests and to know how to write
good tests.


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


A useful test suite will be run frequently, sometimes multipe times a
minute, and therefore must be fast, i.e. a few seconds at
most. Loosing focus during the run is unwanted because it decreases
productivity and breaks the flow. Further if unit tests are slow,
developers will not run tests regularly or skip running them
completely. Then tests will loose their purpose to provide fast
feedback. Martin Fowler speaks of a **[compile suite and a commit
suite](https://martinfowler.com/bliki/UnitTest.html)**. Normally
developers work on a specific part or unit of a system like a single
file or class. With every compilation, they only run the related tests
to get feedback as fast as possible. Hence this group of tests is the
"compile suite". The compile suite comprises the smallest set of tests
which verify the correctness of the unit of the system which is
currently worked on. After finishing a feature or bugfix, before
committing, all unit tests are run to check if nothing else is
broken. Hence this group of tests is called the "commit suite".


The optimal duration of a unit test is in the nanosecond range. A few
hundred milliseconds sounds fast for a single unit test but it is too
slow if you think about a project with multiple thousand tests. All
tests would take minutes to complete. A developer will only
reluctantly wait or even worse he will skip running the tests.


Tests, relying on network calls, database queries or time related
logic, are inherently slow. **Test Doubles** are a method to make
tests fast and reliable. With a Test Double you *inject* a fake
implementation replacing the database or http call. This technique is
well know as [Dependency
Injection](https://martinfowler.com/articles/injection.html#InversionOfControl).

In the code block below the UserService uses the UserRepository to
carry out the intended business logic. A real UserRepository talks
naturally to a database and is too slow for a unit test. Here the real
database implementation is substituted with a FakeUserRepository.

``` java
public class UserServiceTest {

    // test double
    UserRepository fakeUserRepository = new FakeUserRepository();

    // dependency injection
    UserService sut = new UserService(fakeUserRepository);

    @Test
    public void registerUser_validUser_success() {
        // arrange
        User user = new User("foobar");

        // act
        sut.registerUser(user);

        // assert
        User u = sut.getUser(user.getId());
        assertNotNull(u);
    }
}
```

##### Test should be isolated
isolated from each other, i.e. they are independent from each other
and can run in any order. It should be not problem to run tests
concurrently and in parallel. The workload of big test suites can be
distributed across different machines. Every unit test should be able
to run alone without any dependencies (other unit tests, File or
network I/O, database)


``` java
// GOOD
// tests can run in random order and concurrently

@TestMethodOrder(MethodOrderer.Random.class)
public class BankAccountServiceTest {

    BankAccountService sut = new BankAccountService();

    @BeforeEach
    public void beforeEach() {
        sut.resetAll();
    }

    @Test
    public void deposit_100Dollars_ok() {
        // arrange
        User user = new User("userId");
        sut.createAccount(user);
        Integer amount = 100;

        // act
        Integer actual = sut.deposit(user, amount);

        // assert
        Integer expected = 100;
        assertEquals(expected, actual);
    }

    @Test
    public void withdraw_50Dollars_ok() {
        // arrange
        User user = new User("userId");
        sut.createAccount(user);
        sut.deposit(user, 100);
        Integer amount = 50;

        // act
        Integer actual = sut.withdraw(user, amount);

        // assert
        Integer expected = 50;
        assertEquals(expected, actual);
    }
}
```


``` java

// BAD EXAMPLE
// tests depend on each other and cannot run in random order or concurrently

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAccountServiceOrderedTest {

    BankAccountService sut = new BankAccountService();
    User user = new User("userId");

    @Test
    @Order(1)
    public void createAccount_validUser_ok() {
        // act
        sut.createAccount(user);

        // assert
        boolean actual = sut.hasAccount(user.getId());
        assertTrue(actual);
    }

    @Test
    @Order(2)
    public void deposit_100Dollars_ok() {
        // arrange
        Integer amount = 100;

        // act
        Integer actual = sut.deposit(user, amount);

        // assert
        Integer expected = 100;
        assertEquals(expected, actual);
    }
}
```

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




#### Use Test Doubles wisely (or the fallacy of mocking)

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


The fallacy of mocking -- Mocks make your tests stable and fast. They
prevent flaky tests because mocks replace unstable and slow network
calls with predefined, hard-coded behaviour.

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
