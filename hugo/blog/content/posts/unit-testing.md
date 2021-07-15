---
title: "Why and How to write better Unit Tests"
date: 2020-05-04T08:08:00+02:00
tags: ["programming", "testing"]
draft: false
---

<!-- [tldr](#tldr)  this is cross-reference inside the document-->

### TL;DR {#tldr}

* Good unit tests build the foundation of a maintainable and high-quality
  codebase
* Unit Tests should help developers to be productive
* Unit Tests should be fast
* Unit Tests should be isolated
* Unit Tests should be deterministic
* Unit Tests should focus on a single unit
* Unit Tests should be enduring
* Unit Tests should be clear, concise and complete
  * Avoid complex control flow logic like nested ifs or loops
  * Unit Tests should follow a consistent naming pattern like *UnitName_StateUnderTest_ExpectedBehavior*
  * Unit Tests should comply to a consistent structure
  * Unit Tests should be [DAMP not
    DRY](https://testing.googleblog.com/2019/12/testing-on-toilet-tests-too-dry-make.html)
* Unit Tests should give developers confidence to deploy and to refactor
* Test Doubles help to make tests fast and deterministic
* The overuse of Mocking makes test hard to read and brittle
* Prefer state verification over interaction verification
* Prevent brittle tests
* Prevent flaky tests
* Read more:
  * [Software Engineering at Google](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
  * [Kent Becks's Programmer Test
 Principles](https://medium.com/@kentbeck_7670/programmer-test-principles-d01c064d7934)
  * [Microsoft's Best Practices for Unit
 testing](https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-best-practices)


### Why good unit tests are important

TDD and test engineering culture is considered best practice these
days. Alas, I often encounter projects without tests or with bad
tests. Maybe you experienced this yourself, you changed something in a
specific part of the system and suddenly a lot of unrelated tests
fail. Congratulations, you made the acquaintance of **brittle** tests.
This is not only annoying for developers but also a time killer.
Another kind of bad tests are **flaky** tests. They are
non-deterministic due to relying on remote systems, making network
calls or accessing remote databases. This causes tests to randomly
succeed or fail even when production code has not changed.

Both, brittle and flaky, tests are a serious problems in a codebase
and should be shunned. Otherwise they will succumb progressively more
and more time and effort of the developers. Finally developers are
kept busy with repairing broken tests and productivity suffers. This
can happen in disguise and developers are not even aware of the
problem. In some companies test coverage is treated as a key
metric. Hence if the coverage high, nobody will question the current
situation. Maybe the whole involved team is proud of the high
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
system tests fail, it is hard for developers to locate the problem
because the scope of a system test is very broad and not as focused as
in a unit test. Relying heavily in a project on manual regression
testing instead of an exhaustive, automatic test suite is an
anti-pattern, the **test ice cone**. It is the inverse of the test
pyramid and leads to an unsustainable codebase because failing system
tests leave developers in the dark about the root cause and drain
their productivity. Did you ever work in a project with a Jira Board,
cluttered with unresolved Bugs in the backlog? -- all bugs found by
so-called manual QAs or testers.

<img src="/img/ice_cone.jpg" alt="https://watirmelon.blog/testing-pyramids/" class="medium-zoom-image" width="300">

In this article I will focus on unit tests. Good unit tests should be
clean, maintainable and most notably "useful" for developers. The main
purpose of tests is to save time for developers and keep the code
quality high. But careless use of testing can have negative effects on
productivity and code quality. E.g if developers loose a majority of
their time fixing tests instead of building new features. "Wrong"
testing can result in a system that requires even more effort to
maintain than without tests and takes more effort to change without
actually improving confidence in the next production release. It is
crucial to identify bad tests and to know how to write good tests.


This blog post is built upon the shoulder of giants. Basically I draw
 from the fantastic book [Software Engineering at
 Google](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
 . Especially from Chapter 12 "Unit Testing". I was also heavily
 inspired by the article about [Programmer Test
 Principles](https://medium.com/@kentbeck_7670/programmer-test-principles-d01c064d7934)
 from Kent Beck, the father of TDD.


### Test Doubles

**Test Doubles** play a crucial part in writing good unit tests, we'll
see soon. But first, let's define what a Test Double is. A Test Double
is a replacement of a production code class or function. Multiple
types of Test Doubles exist. In my opinion, the three most important
ones are:

* A **Fake** is a simplified implementation of a given interface, for
  example a *FakeUserRepository* "fakes" the real database repository
  behaviour with an in-memory Hashmap. A Fake is used interchangeably
  with a real implementation and is typically applied via dependency
  injection.
* A **Stub** returns predefined, hard-coded values to specific calls
  which are internally needed by the **SUT** (System Under Test) to
  fulfill the tested behaviour.
* A **Mock** is a stub, but additionally checks the interactions of
  the mock with its environment. For example, was the mock called with
  the expected parameters or was the mocked method called an expected
  number of times? Checking the interactions of mocks is also called
  [**interaction
  testing**](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/).
  Naturally both, Stubbing and Mocking, is realized via
  mock-frameworks.

An exhaustive list of Test Double types can be found in [Martin
Fowler's article](https://martinfowler.com/bliki/TestDouble.html).

The following is a fake implementation of a UserRepository. An
in-memory Hashmap replaces a real database. The Fake can be used by a
unit test via dependency injection. There will be a relevant example
with an example usage later on.

``` java
// Fake example

public interface UserRepository {
    public void save(User user);
    public User findById(String id);
}


// Fakes the UserRepository interface with a HashMap implementation

public class FakeUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }
}
```

The next example shows a simple mock which stubs a method of the
UserRepository. Without the interaction verification at the end of the
test, the mock would be a simple stub.

``` java
// Mock example

import static org.mockito.Mockito.*;

public class UserServiceMockTest {

    UserRepository mockUserRepository = mock(UserRepository.class);

    // dependency injection
    UserService sut = new UserService(mockUserRepository);

    @Test
    public void getUser_ReturnUser() {
        // arrange
        User user = new User("userId");
        when(mockUserRepository.findById(anyString())).thenReturn(user); // stubbing

        // act
        User actual = sut.getUser("userId");

        // assert
        assertEquals("userId", actual.getId());

        // verify interaction
        // check that findById() was called with the correct parameter and exactly once
        verify(mockUserRepository, times(1)).findById("userId");
    }
}
```


### What makes a good unit test?


There are a lot of opinions floating around about how to write good
unit tests. In the following I list the traits which I consider most
important.

##### Tests should be fast


A useful test suite will be run frequently, sometimes multiple times a
minute, and therefore must be fast, i.e. a few seconds at
most. Loosing focus during the run is unwanted because it decreases
productivity and breaks the flow. Further if unit tests are slow,
developers will not run tests regularly or skip running them
completely. Then tests will loose their purpose, namely providing fast
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


The optimal duration of a unit test is in the nanosecond
range. Hundred milliseconds sounds fast for a single unit test but it
is too slow if you think about a project with multiple thousand
tests. All tests would take minutes to complete. A developer will only
reluctantly wait or even worse he will skip running the tests.


Tests, relying on network calls, database queries or time related
logic, are inherently slow. Test Doubles are a mechanism to make tests
fast and reliable. With a Test Double you *inject* a fake
implementation replacing the database or HTTP call. This technique is
well known as [Dependency
Injection](https://martinfowler.com/articles/injection.html#InversionOfControl).

In the code block below the UserService uses the UserRepository to
carry out the intended business logic. A real UserRepository talks
naturally to a database and is too slow for a unit test. Here the real
database implementation is substituted with a FakeUserRepository from
above.

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

##### Tests should be isolated

Tests should be independent from each other. It must be possible to
run the tests in any order, concurrently and in parallel. This becomes
especially important when the project is big and contains thousand of
tests. In order to speed up the build, the workload can be distributed
across different machines. The distribution logic is very simple if
the tests are isolated. Contrary, it would be very hard or even
impossible to distribute tests which make up a complex dependency
graph. Further any single unit test should be able to run alone
without depending on other unit tests, files, network I/O, and
databases.

Below there is an example of a bad unit test. The second test depends
on the first one and it will fail if the first test did not run
before or failed. Not only this eradicates the possibility to
distribute the tests but also developers will have a hard time to
figure out the root cause of the potential error. Did the second test
fail because of itself or because of the test before?

``` java
// BAD
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


The second example shows two independent tests. The small cost of
adding 2-3 lines per tests is more than acceptable.

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

##### Tests should be deterministic

A deterministic test never changes its outcome when there was no
change of behaviour. A test switching from green to red or the other
way around without any change is called flaky. Test Doubles are a good
way to get rid of flaky dependencies like external network or database
calls. The earlier example with the FakeUserRepository demonstrates
this.

##### Tests should focus on a single unit of the system

A unit test should focus on a single part of the system. If a unit
tests breaks, it should be easy to find the root cause. The other way
around is also true: If someone changes a unit, only corresponding
unit tests should possibly break. If you need to start a debugger to
figure out what went wrong, the chance is high that your tests are too
diffuse and include much more than a single unit.

Unit tests which not focus on a single unit tend to be brittle because
they will fail if some other part of the system changes. Brittle tests
are a serious problem because developers loose trust in the test suite
and they neglect badly needed refactorings. This hampers
maintainability and causes the quality of the codebase to
degrade. More often than not, in many projects existing unit tests are
more a burden than a backing for the developers.


Often there is a misunderstanding of what "focus on one unit" exactly
means. Here the two types of testing, the [classic testing and the
mockist
testing](https://martinfowler.com/articles/mocksArentStubs.html#ClassicalAndMockistTesting),
come into play. The "mockists" are very strict and mock all
dependencies. The mock-everything approach isolates the test from the
rest of the world but comes with major disadvantages. First, the unit
tests are polluted with various mock-statements which makes the real
test logic hard to understand. And second, with mocking the
dependencies, you expose the internals of the unit. This is a major
bummer because once the internals are exposed, future refactorings are
impossible without breaking a majority of existing tests. The "classic
tester" avoid mocks and use Fakes or real implementations for
dependencies. Hence the internals are kept hidden and refactorings are
still possible.

You can find a great definition about "focus on a single unit" in the
book [Software Engineering at
Google](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/):


> It’s important to note that when we talk about unit tests as being narrowly scoped, we’re referring to the code that is being validated, not the code that is being executed.



##### Tests should be enduring

Strive for unchangeable tests. A test should be written once and never
be touched except there is a change of behaviour in the corresponding
unit. Changes of internals should never break a test if the behaviour
stays the same. Like I mentioned earlier these are brittle tests. We
should prevent them at any cost.

Brittle tests can creep into the codebase because of the overuse of
mocks. Mocks verify if specific methods get called. Hence mocks know
about the internal implementation which makes the tests prone to
failures. If the internal implementation changes, you need to adjust
all tests which use related mocks. In large codebases this means a lot
of effort.

Test only *public* methods. I often see developers who test private
methods. Therefore they make them public or protected. This
contradicts the concept of information hiding and low coupling. Then
you expose the internals of a unit which makes it impossible to switch
the internal implementation without breaking a majority of
tests. Additionally, you crippled the module's contract and swamped
its interface with confusing public methods which should be private.



##### Tests should be clear, concise and complete

A test should be clear, concise and complete. What does that mean
exactly? A clear test is easy to read and to understand. Tests should
not include complex logic like nested if-conditions or complicated
loops. A clear test is a simple sequence of expressions without any
branching. Consistency is critical. All tests in a project should
comply to a common structure like *arrange-act-assert* or
*given-when-then*. A consistent structure reduces cognitive load and
gives developers, unfamiliar with the codebase, a prescriptive model
how a test should look like. A consistent naming pattern adds clarity
and bolsters readability. A collection of common patterns can be found
[here](https://dzone.com/articles/7-popular-unit-test-naming). My
preferred one is `UnitName_StateUnderTest_ExpectedBehavior`.  Rich
failure messages are important too. A message with a detailed context
where and why the test failed, reduces debugging effort
immensely. According to
[Microsoft](https://docs.microsoft.com/en-us/dotnet/core/testing/unit-testing-best-practices)
you should create a separate test for each assertion. In case of a
test failure, a single assertion helps to demystify the error. Most
testing-frameworks anyway stop during the first assertion error per
test. At best, a visible correlation from the test name to the
assertion statement exist. Multiple assertions could be useful though
for asserting multiple attributes of a complex object or checking
expected side-effects.

The following test shows one good assertion and some
unnecessary ones:


``` java
public class PaymentServiceTest {

    PaymentService sut = new PaymentService(new PaymentGateway());

    @Test
    @DisplayName("payment with valid user")
    void pay_validUser_success() {
        // arrange
        User user = new User("userId");
        user.setHasValidCreditCard(true);
        int amount = 750;
        PaymentRequest request = new PaymentRequest(user, amount);

        // act
        PaymentResponse actual = sut.pay(request);

        // assert
        // GOOD one assert is enough to cover the test case
        boolean expected = true;
        assertEquals(expected, actual.isSuccess());

        // BAD
        // unnecessary over-assertion
        assertEquals(expectedReason, actual.getReason());
        assertEquals(expectedStateOfSUT, sut.getState());
        assertEquals(expectedOther, otherStuffNonRelatedToTest);
    }
}
```

Completeness and conciseness contradict themselves. A good test finds
a balance of both. The highest priority is readability though which is
fostered by completeness. A *complete* test contains all dependencies,
pre-configured objects and data needed to run the test. The apt
developer must resist the urge to make the test too DRY (Don't Repeat
Yourself). DRY code scatters important shared logic and hurts
readability. Relying too much on shared helper classes and functions
reduces the amount of code but increases coupling and makes tests
brittle. [Google's Testing on the Toilet
Blog](https://testing.googleblog.com/2019/12/testing-on-toilet-tests-too-dry-make.html)
favors the **DAMP** principle (*Descriptive And Meaningful Phrases*)
over the DRY principle for tests. A little duplication improves
comprehension and should be preferred over uniqueness. If you think
complete tests are too verbose or your tests require lots of setup
code, it could be an indicator that your production code is flawed and
you should rethink your overall design.

The following examples show a test in a DRY and a DAMP version. The
DRY version uses shared helper functions like `createUsers()` or
`validate()` with unclear semantics. The DAMP version replaces these
magical functions with a simple constructor call and an assertion
statement.


``` java

// BAD

// test is too DRY

// What kind of users will be created by the createUsers() function?
// What will be asserted by the validate() function?

class PaymentServiceDRYTest {

    // magical helper function
    List<User> users = createUsers(); // how many users will be created?

    @Test
    @DisplayName("payment with valid user")
    void pay_validUser_success() {
        // arrange
        PaymentService sut = new PaymentService(new PaymentGateway());

        // BAD
        // which user in the list is a valid one?
        PaymentRequest request = new PaymentRequest(users.get(0), 750);

        // act
        PaymentResponse actual = sut.pay(request);

        // assert
        // magical helper function
        validate(actual, true, null);  // what are these parameters?

        // validate() is used in other contexts too
        // the three parameters are: validate(actual, expected, errorMessage)
    }
}
```

``` java
// GOOD

// Test is DAMP

// all information needed for the test are inside the test

public class PaymentServiceDAMPTest {

    PaymentService sut = new PaymentService(new PaymentGateway());

    @Test
    @DisplayName("payment with valid user")
    void pay_validUser_success() {
        // arrange
        User user = new User("userId");
        user.setHasValidCreditCard(true);
        int amount = 750;
        PaymentRequest request = new PaymentRequest(user, amount);

        // act
        PaymentResponse actual = sut.pay(request);

        // assert
        boolean expected = true;
        assertEquals(expected, actual.isSuccess());
    }
}
```

Nevertheless conciseness is important too and must not be neglected. A
deliberate usage of shared helpers makes this possible. For example,
after calling the helper function you could explicitly set the needed
properties for the test on the returned object. So you utilize the
shared helper function but also decouple the test from it. Further the
relevant properties, needed by the test, are made prominent and the
test will be stable even if someone changes the helper logic. The
example below illustrates that.


``` java
// BAD use of shared helper function.

// We don't know about the properties of the created user.

// If someone changes createDefaultUser(), the test could break.

    @Test
    void pay_validUser_success3() {
        // arrange
        User user = createDefaultUser();
        int amount = 750;
        PaymentRequest request = new PaymentRequest(user, amount);

        // act
        PaymentResponse actual = sut.pay(request);

        // assert
        boolean expected = true;
        assertEquals(expected, actual.isSuccess());
    }
```

``` java

// GOOD use of shared helper function.

// Set properties which matter for the test explicitly.

// Test is complete.
// You understand the test without checking createDefaultUser().
// Even if someone changes createDefaultUser(), the test will be ok.

    @Test
    void pay_validUser_success2() {
        // arrange
        User user = createDefaultUser();
        user.setHasValidCreditCard(true);
        int amount = 750;
        PaymentRequest request = new PaymentRequest(user, amount);

        // act
        PaymentResponse actual = sut.pay(request);

        // assert
        boolean expected = true;
        assertEquals(expected, actual.isSuccess());
    }
```

##### Tests should give you confidence (and a good feeling)

Finally your test suite should give you confidence that your code
changes are correct and you did not break anything. A green suite is
an indicator that you can deploy to production without worries. Green
tests should give the individual developer a good feeling about his
code changes. Thereby tests act as a productivity booster so you can
make changes faster and deliver features in less time -- always
feeling good at it.


#### The Fallacy Of Mocking

We learned that mocks make unit tests fast and deterministic. They
prevent flaky tests because they replace unstable calls or slow
network calls with predefined, hard-coded behaviour. There is a catch
though. The overuse of mocking or stubbing has a negative effect on
your test code quality:

1. Tests become unclear because mock statements bloat the code and
   make the test hard to comprehend. The maintainability of your test
   code suffers.

2. Tests become brittle. The more you mock, the more internals of the
   SUT are leaked. Changing the internals, even without changing the
   behaviour of the SUT, could make the test fail which contradicts
   the principle of enduring tests.

3. A need of too many mocks could be an indicator of bad design. Most
   probably the SUT has too many dependencies and responsibilities and
   should be divided.

 [Google](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
 also warns about the overuse of mock-frameworks and interaction
 testing. Nevertheless interaction testing is sometimes the only way
 to check the code correctness. For example in order to check a
 caching logic, you need to call a function twice. First to get object
 and to fill the cache, second to get the object from the cache. Both
 returned objects are indistinguishable. The only way to verify that
 the second object was retrieved from the cache is to check if the
 cache was called. Another insight from Google is that they prefer
 *Fakes over Mocks*. Fakes are not that intrusive and the test code is
 not swamped by stubbing-behaviour statements.


#### Final words

I know some presented traits are idealistic. Ideals are often hard or
even impossible to achieve. Nevertheless these "ideals" should serve
you as a guide star. I hope you gained some new insights and
understand that good unit tests play a crucial part for a successful
software project. Especially identifying good and bad tests is
important, so you do not fall into the trap of brittle or flaky
tests. Eventually tests exist to make the life of developers
easier. When tests do not increase productivity, confidence and code
quality, they failed their purpose. I also hope that I could eliminate
some fallacies about mocking or that chasing a 100% code coverage is
nonsense. As long developers feel confident about their codebase and
refactorings are done regularly everything is fine.
