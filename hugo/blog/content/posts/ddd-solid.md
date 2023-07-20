---
title: "DDD and SOLID: A refactoring story"
date: 2023-07-19T08:00:00+01:00
tags: ["programming", "softwareengineering", "ddd", "solid"]
draft: false
---

## TL;DR

1. Command-Query Separation
1. Extract Use Cases (Clean/Hexagonal Architecture)
1. Domain Events (with Eventhandlers and Composite Pattern)
1. Cross-Cutting Concerns (with Decorator Pattern)

Many backend applications end up as a _big ball of mud_ where small changes
ripple through the whole codebase. The root causes are _high coupling_ and _low
cohesion_. The best remedies against those are
[Domain Driven Design](https://martinfowler.com/bliki/DomainDrivenDesign.html)
(DDD) and the [SOLID](https://en.wikipedia.org/wiki/SOLID) principles which are
well-known, but often wrongly applied.

Even if teams think they practice DDD, they mostly use
[transaction scripts](https://martinfowler.com/eaaCatalog/transactionScript.html)
with an
[anemic domain model](https://martinfowler.com/bliki/AnemicDomainModel.html).
Services containing these transaction scripts evolve into god classes with
thousand lines of code and dozen of dependencies. Such code is hard to test,
hard to maintain and hard to comprehend. Does the following code snippet look
familiar to you?

<p align="left">
    <img src="/img/codinghorror.png" alt="codinghorror" class="medium-zoom-image" width="70">
</p>

```java
// BAD CODE!!!

public class OrderService {

private OrderRepository orderRepository;
private CardService cardService;
private DiscountCalculatorService discountCalculatorService;
private CustomerService customerService;
private EmailService emailService;
private ShippingService shippingService;

// many more dependencies...

public Order createOrder(params: OrderParms) {}
public Order getOrder(orderId: UUID) {}
public List<Order> filterOrdersByDate(id: UserId, range: DateRange)
public void updateOrder(id: OrderId) {}
public void addToCard(id: OrderId) {}
public void addDiscount(id: OrderId) {}
public void addDiscountForPremiumCustomer(id: OrderId) {}
public void shipOrder(id: OrderId) {}
public OrderStatus getOrderStatus(id: OrderId) {}
public void cancelOrder(id: OrderId) {}
public List<Order> getCanceledOrders(id: UserId)

// many more methods...
}

```

Surrounding all behaviour around one domain object in a single class is an OOP
anti-pattern and leads to unsustainable code. In this article, we'll refactor
the code above into a maintainable and decoupled solution with the help of DDD
and the SOLID principles. Let's first find out what SOLID principles are
violated and why.

#### Single-Responsibility Principle (SRP)

> A class should only have one reason to change.

The `OrderService` has many reasons to change. For example, it must be adopted
when there a changes for the discount calculation logic, the shipping logic, the
order creation logic etc.. Worse yet, due to the numerous dependencies the
`OrderService` suffers from low cohesion which also causes the class to be prone
to unrelated changes.

#### Open-Closed Principle

> A class should be open for extension but closed for modification.

The `OrderService`'s business logic is impossible to adapt without touching the
existing code. This makes changes error-prone and is a violation of the
Open-Closed Principle. I know it's hard to imagine how to change behaviour
without touching the code but we'll see later what is meant by that and how to
resolve the problem.

#### Interface-Segregation Principle

> Clients should not be forced to depend upon interfaces that they do not use.

A consumer of `OrderService` is overwhelmed by all the methods provided. If a
consumer only wants to use a single method like `createOrder()`, he must depend
on the complete `OrderService` interface. Such big interfaces lead to testing
nightmares because in order to create a viable fake, one must implement all
methods even if only one is needed.

#### Cohesion and Coupling

The SOLID violations above lead to low cohesion and high coupling. As a result,
simple changes will ripple through the whole codebase and are also error-prone
because we have to modify and touch existing code. This leads to high
maintenance effort. In the next paragraphs, we refactor the `OrderService`
step-by-step and fix the above issues.

## Refactorings

### Command-Query Separation

Collecting all functionality around one domain class is bad practice. In order
to improve and slim down the big `OrderService`, we will split the functionality
into commands and queries. The
[Command-Query Separation Principle](https://martinfowler.com/bliki/CommandQuerySeparation.html)
from Bertrand Meyer classifies functions into two types:

- A command performs side-effects but does not return a value.

- A query returns a value but has no side-effects.

The principle helps to write _intention-revealing interfaces_ since readers will
be able to detect the type of a function by just looking at its declaration.
Thus the code is easier to understand. Don't confuse it with the similar
sounding architecture pattern
[CQRS](https://learn.microsoft.com/en-us/azure/architecture/patterns/cqrs).

The restructured `OrderService` will be split up into two classes:

<p align="center">
    <img src="/img/command-query-separation.png" alt="command-query-separation" class="medium-zoom-image" width="800">
</p>

The god class is stripped down to half its size but it still violates the
Single-Responsibility and Interface-Segregation Principle. Both classes,
`OrderCommandService` and `OrderQueryService`, need to be changed because of
multiple reasons and a consumer would still depend on methods which are
potentially not needed.

## Extract Use Cases

To fix the SRP and ISP violations, we extract every single method of the
`OrderCommandService` and `OrderQueryService` into its own Use-Case interface.
Use-Cases are a concept from
[Clean-](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
and [Hexagonal Architecture](https://reflectoring.io/spring-hexagonal/). Maybe
it sounds over-engineered but for an ever-growing enterprise application it will
soon pay off. The new consistent structure makes it obvious for developers where
to add new functionality, keeps the code extensible and testable, and prevents
god classes.

<p align="center">
<img src="/img/use-cases.png" alt="use-cases" class="medium-zoom-image" width="800">
</p>

## Domain Events

The Use-Case classes became smaller and more structured now, but sometimes even
a single Use-Case can grow too big, especially when there are a lot of related
side-effects involved:

```java
public class CreateOrderUseCase {

public Order createOrder(params: OrderParams) {
  // register order

  // charge customer

  // send confirmation email

  // notify warehouse

  // more logic and side-effects ...

  // ...
}
```

A great way to extract such side-effects is to publish _DomainEvents_ which are
processed by _EventHandlers_. With a generic Interface `EventHandler<TEvent>`,
it is possible to register multiple EventHandlers for a single DomainEvent,
whereby each handler has its own responsibility. For example:

- register order in system
- charge customer account
- send a confirmation email
- notify warehouse to prepare order for shipping

By moving the side-effect logic into corresponding EventHandlers, the size of
the `CreateOrderUseCase` class will be reduced drastically. Additionally,
testability also improves because EventHandlers can be tested in isolation.

The `CreatedOrderUseCase` will depend on the `EventHandler<CreatedOrderEvent>`
interface. At runtime the `CompositeOrderCreatedEventHandler` will be injected
which calls all EventHandlers for the `OrderCreatedEvent`. The class diagram:

<p align="center">
    <img src="/img/domain_event_hierachy.png" alt="domain_event_hierachy" class="medium-zoom-image" width="800">
</p>

The Use-Case logic does not look like a _Transaction Script_ any longer and
feels like clean OOP:

```java
// the generic interface
public interface EventHandler<TEvent> {

  void handle(TEvent event);
}

```

```java
@Service
public class CreateOrderUseCase implements ICreateOrderUseCase {

  // an "composite" EventHandler which executes multiple EventHandlers
  private final EventHandler<OrderCreatedEvent> eventHandler;

  private final OrderRepository orderRepository;

  @Autowired
  // Spring needs a @Qualifier in order to locate the right EventHandler
  // because there are multiple EventHandler implementations for the same interface
  public CreateOrderUseCase(
      @Qualifier("compositeOrderCreatedEventHandler")
          EventHandler<OrderCreatedEvent> eventHandler,
          OrderRepository orderRepository) {
    this.eventHandler = eventHandler;
    this.orderRepository = orderRepository;
  }

  @Override
  public Order createOrder(OrderParams params) {
    String id = "randomOrderId";  // simplified for the example
    Order order = new Order(id, params.getItemId(), params.getAmount());
    orderRepository.save(order);

    // side-effects are hidden inside the EventHandler
    // all side-effects are processed with one call!
    eventHandler.handle(new OrderCreatedEvent(order));

    return order;
  }
}
```

The grouping of all EventHandlers for one specific DomainEvent is done via the
[_Composite Pattern_](https://en.wikipedia.org/wiki/Composite_pattern):

```java
// single EventHandler

@Component
public class ConfirmEmailHandler implements EventHandler<OrderCreatedEvent> {
  @Override
  public void handle(OrderCreatedEvent orderCreatedEvent) {
    System.out.println("[EVENT] sent confirmation email to customer");
  }
}
```

```java
// single EventHandler

@Component
public class NotifyWarehouseHandler implements EventHandler<OrderCreatedEvent> {
  @Override
  public void handle(OrderCreatedEvent orderCreatedEvent) {
    System.out.println("[EVENT] notify warehouse of new order");
  }
}
```

```java
// single EventHandler

@Component
public class ChargeCustomerHandler implements EventHandler<OrderCreatedEvent> {
  private final PaymentGateway paymentGateway;

  @Autowired
  public ChargeCustomerHandler(PaymentGateway paymentGateway) {
    this.paymentGateway = paymentGateway;
  }

  @Override
  public void handle(OrderCreatedEvent orderCreatedEvent) {
    this.paymentGateway.chargeCustomer(orderCreatedEvent.getOrder());
  }
}
```

```java
// composite EventHandler includes all EventHandlers

@Service
@Qualifier("compositeOrderCreatedEventHandler")
public class CompositeOrderCreatedEventHandler implements EventHandler<OrderCreatedEvent> {

  // all available event handlers without a @Qualifier will be injected automatically
  private final List<EventHandler<OrderCreatedEvent>> eventHandlers;

  @Autowired
  public CompositeOrderCreatedEventHandler(List<EventHandler<OrderCreatedEvent>> eventHandlers) {
    this.eventHandlers = eventHandlers;
  }

  @Override
  public void handle(OrderCreatedEvent orderCreatedEvent) {

    // call all registered event handler for OrderCreatedEvent
    for (EventHandler<OrderCreatedEvent> handler : this.eventHandlers) {
      handler.handle(orderCreatedEvent);
    }
  }
}
```

The restructuring enables us to add new functionality without modifying existing
code. Therefore we comply with the _Open-Closed principle_! If we want to add a
new side-effect, we only have to add a new implementation of
`EventHandler<OrderCreatedEvent>`. It is not necessary to touch the original
`CreateOrderUseCase` class anymore.

Some intrigued readers may wonder how to test this setup.
[Spring Boot](https://spring.io/projects/spring-boot) offers an easy way to
replace the EventHandlers with Test Doubles:

```java
@TestConfiguration // this will take precedence in tests
public class FakeConfig {

  // example how to manually setup the EventHandlers for tests

  @Bean
  @Qualifier("compositeOrderCreatedEventHandler")
  public EventHandler<OrderCreatedEvent> eventHandler(PaymentGateway paymentGateway) {
    var warehouseHandler = new NotifyWarehouseHandler();
    var chargeCustomerHandler = new ChargeCustomerHandler(paymentGateway);
    List<EventHandler<OrderCreatedEvent>> handlers =
        List.of(warehouseHandler, chargeCustomerHandler);
    var compositeHandler = new CompositeOrderCreatedEventHandler(handlers);
    return compositeHandler;
  }
}
```

```java
@SpringBootTest
@Import(FakeConfig.class) // replace dependencies with test doubles
public class CreateOrderUseCaseFakeTest {

  @Autowired CreateOrderUseCase sut;

  @Test
  public void createOrderTest_validInput_ok() {
    // ...
  }
}
```

## Cross-Cutting Concerns

We are able to add new functionality without touching existing code but we are
restricted to prescribed _Domain Events_. What if we want to add general
functionality like _Cross Cutting Concerns_:

- Logging
- Transactions
- Metrics (e.g. duration profiling)

The naive approach would be to add the new code into the Use-Case class itself
but we already know that this violates the Open-Closed Principle. Maybe there is
another approach? And yes there is. We can utilize the
[_Decorator Pattern_](https://en.wikipedia.org/wiki/Decorator_pattern) to enrich
the existing Use-Case with logging, transactional or profiling behaviour. The
following example shows decorators wrapping the `CreateOrderUseCase` with
logging and profiling:

```java
// Duration Decorator
// Measures the duration of the UseCase method

public class DurationCreateOrderUseCase implements ICreateOrderUseCase {

  private static Logger log = LoggerFactory.getLogger(DurationCreateOrderUseCase.class);

  // the original UseCase
  private ICreateOrderUseCase inner;

  public DurationCreateOrderUseCase(ICreateOrderUseCase inner) {
    this.inner = inner;
  }

  @Override
  public Order createOrder(OrderParams params) {
    var start = System.nanoTime();

    // execute original logic
    var order = this.inner.createOrder(params);

    var end = System.nanoTime();
    var durationMicros = (end - start) / 1000;
    log.info("[DURATION] createOrder() => {} micro seconds", durationMicros);

    return order;
  }
}
```

```java
// Logging Decorator
// logs the input parameter of the UseCase method

public class LoggingCreateOrderUseCase implements ICreateOrderUseCase {

  private static Logger log = LoggerFactory.getLogger(LoggingCreateOrderUseCase.class);

  private ICreateOrderUseCase inner;

  public LoggingCreateOrderUseCase(ICreateOrderUseCase inner) {
    this.inner = inner;
  }

  @Override
  public Order createOrder(OrderParams params) {
    // log params
    log.info("[LoggingDecorator] function params: {}", params);

    // executes original logic
    return inner.createOrder(params);
  }
}
```

The next step is to make the decorated UseCase available as a Spring Bean.
Because we have multiple implementations of the UseCase interface, we need a
`@Qualifer` annotation for the original and for the fully decorated UseCase.

```java
@Service
// the original UseCase needs a @Qualifier
// because Spring must distinguish between multiple implementations
@Qualifier("plainCreateOrderUseCase")
public class CreateOrderUseCase implements ICreateOrderUseCase {
  // ...
}
```

```java
// wire the Decorators and the original UseCase together

@Configuration
public class AppConfig {

  @Bean
  @Qualifier("decoratedCreateOrderUseCase")
  public ICreateOrderUseCase decoratedCreateOrderUseCase(
      @Qualifier("plainCreateOrderUseCase") ICreateOrderUseCase createOrderUseCase) {
    LoggingCreateOrderUseCase loggingCreateOrderUseCase =
        new LoggingCreateOrderUseCase(createOrderUseCase);
    DurationCreateOrderUseCase decoratedUseCase =
        new DurationCreateOrderUseCase(loggingCreateOrderUseCase);
    return decoratedUseCase;
  }
}
```

```java
// in order to inject the decorated UseCase, we must use a @Qualifier

@RestController
public class Controller {

  @Autowired
  @Qualifier("decoratedCreateOrderUseCase")
  private ICreateOrderUseCase useCase;

  public void createOrder() {
    useCase.createOrder();
  }
}
```

We added Cross-Cutting concerns to the existing `CreateOrderUseCase`. We comply
to the Open-Closed Principle once more! However, the main downside of the
described approach is that we have to write specific decorators for every
Use-Case. This violates the
[DRY Principle](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself). One way
to fix this is to find a common interface for all Use-Cases. Then you can write
one decorator and cover all Use-Cases at once. At the beginning we introduced
the _Command-Query Separation_. It is possible to classify all methods into
commands and queries. For commands, a unifying interface would look like this:

```java
public interface CommandHandler<TCommand> {

  void handle(TCommand command);
}
```

If your Use-Cases implement the `CommandHandler<TCommand>` interface, you can
write one Logging Decorator and it will cover all CommandHandlers aka Use-Cases.
You can find an in-depth elaboration for the
[_Command Handler Pattern_](https://blogs.cuttingedge.it/steven/posts/2011/meanwhile-on-the-command-side-of-my-architecture/)
and even for the
[_Query Handler Pattern_](https://blogs.cuttingedge.it/steven/posts/2011/meanwhile-on-the-query-side-of-my-architecture/)
in Steven van Deursen's blog.

# Final Thoughts

Phew! That was a long refactoring journey but I truly believe, every step was
very valuable and showed how to combine SOLID principles and Design Patterns in
order to write clean and extensible code. I hope you got inspired and you use
the new insights in your own projects.

Finally, I want to mention that this article was heavily inspired by the book
[Dependency Injection Principles, Practices, and Patterns ](https://www.manning.com/books/dependency-injection-principles-practices-patterns).
Basically it is a rewrite of chapter 10 and ports the C# examples to Java with
Spring Boot.

#### One more thing

Please don't be dogmatic about the given advice. Use your own judgement when to
apply predefined principles and patterns! Don't be the guy who rewrites the
whole codebase with Decorator- and Composite patterns tomorrow. Every situation
is different, every scenario requires its own assessment. Never forget:

> It depends. - Kent Beck

DDD and SOLID makes sense for an medium to large projects. A simple CRUD
application won't benefit from over-engineered abstractions. They will be even
counter-productive and introduce accidental complexity.

# References

- [Dependency Injection Principles, Practices, and Patterns ](https://www.manning.com/books/dependency-injection-principles-practices-patterns)

- [Command Handler Pattern](https://blogs.cuttingedge.it/steven/posts/2011/meanwhile-on-the-command-side-of-my-architecture/)

- [Query Handler Pattern](https://blogs.cuttingedge.it/steven/posts/2011/meanwhile-on-the-query-side-of-my-architecture/)
