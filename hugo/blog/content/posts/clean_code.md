---
title: "Clean Code: The Good, the Bad and the Ugly"
date: 2024-12-13T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: false
---

_Clean Code_ by Robert C. Martin is a seminal programming book. A whole
generation of developers, including myself, became better programmers by Uncle
Bob's advice. But after almost twenty years, does the book still hold up to its
high standards? Was some advice given by _Clean Code_ questionable or even
wrong? Are there better alternatives nowadays?

To be fair, the author himself claims in the preface that "_some recommendations
in the book are controversial and people might disagree_". In this article I try
to disagree. Let's go through to the **good, bad and ugly** parts of Clean Code.

## The Good

Besides the book's programming guidance, **I think the greatest achievement of
the book is coining the term Clean Code and creating a general awareness about
code quality**:

> _The only way to go fast is to go well_ - Uncle Bob

The impact of the book in the programming world is second to none. All of a
sudden code quality, readability and maintainability were in the developers'
focus. I truly believe this made the programmers' world a better place.

The book itself is full of great programming advice, mainly written in Java but
applicable in other programming languages as well. It's especially valuable for
new programmers, starting the craft. You will find foundational tips regarding
function and variable names that they should be intention-revealing and
searchable. Other timeless ideas like the _DRY_ principle (Don't Repeat
Yourself), _Command-Query separation_ and noteworthy metaphors like the _boy
scout rule_ are also presented.

Additionally, numerous useful programming practices are given. Senior developers
take them for granted but junior developers need to learn them first, e.g.:

- Good _naming_ uses problem-domain names for functions, classes and variables.
  This is also a main principle in DDD, namely the ubiquitous language.
- Good _exception handling_ uses unchecked exceptions and provides as much
  context as possible
- Good _unit tests_ should be FIRST (Fast, Independent, Repeatable
  Self-validated, Timely) and should assert a single behaviour
- Classes should comply to the _Single Responsibility Principle_
- _Functions_ should do one thing and all statements should have the same level
  of abstraction
- _pure functions_ without side-effects should be preferred
- Functions should comply to the _Command-Query Separation_ pattern
- Keep your code _DRY_
- Don't comment code, delete it - there is version control
- Good _boundaries_ are important. Don't depend too much on 3rd party libs, hide
  them behind interfaces. This will keep your code testable
- Good _Data Abstractions_ enabled by encapsulation/information hiding will
  preserve class invariants and will guarantee error-resistant and
  comprehensible APIs.

These practices are timeless for all programmers - new and old. I keep finding
myself giving these tips over and over to team members in pair programming
sessions, discussions or other kind of talks.

Another highlight of the book is the extensive code smells compendium where the
book describes the most common code smells with their corresponding
countermeasures. **It's not only important to know how to write good code but
also how to detect bad code.**

## The Bad

Even a seminal book is not perfect. In the following, I try to describe my two
main points of criticism. First, while _Clean Code_ remains a classic resource
for software engineering, **some of its content is obsolete**. For example the
heavy Java focus, the reliance on
[EJBs](https://en.wikipedia.org/wiki/Jakarta_Enterprise_Beans) and
[AspectJ](https://en.wikipedia.org/wiki/AspectJ) limits the applicability for
modern programming practices. Additionally, the covered concurrency topics are
shallow and too low-level, especially considering the rise of first-class
concurrency languages like Go and Rust. These new languages offer more robust
and efficient approaches to concurrency. Furthermore, the book primarily uses
Java with Object-Oriented Programming examples, which may not adequately
represent the diversity of modern programming styles today. Especially it lacks
a comprehensive exploration of functional programming concepts, including
immutability, referential transparency, high-order functions, and the emphasis
on avoiding side effects. Such principles are occasionally mentioned but not
consistently applied.

Second, **the book's dogmatic presentation of coding principles can be a
double-edged sword.** While it offers valuable advice, its lack of nuanced
explanations and context can lead to misunderstandings, particularly for newer
developers. Overzealous adherence to rules like "small functions" or the "DRY
principle" without seeing the costs is harmful to code quality. For example,
blindly applying the "DRY principle" can result in excessive abstraction,
leading to a proliferation of tiny, overly complex classes. This can make the
code harder to understand and maintain. A better approach, recognizing that
sometimes duplication is acceptable to improve readability or performance, is
essential for effective software development. Even more important is that we
should not place tactical coding practices above higher design principles like
coupling, cohesion, information hiding etc. Some quotes directly from the book:

> The first rule of function is that they should be small. The second rule of
> functions is that they should be smaller than that. - _Clean Code, chapter 3,
> p34_

> Every time you see a duplication in the code, it represents a missed
> opportunity for abstraction. - _Clean Code, chapter 17, p289_

**This writing style can also contribute to cargo-cult programming,** where
developers blindly follow rules without understanding the underlying reasons.
More often than not, it leads to heated debates and misunderstandings, as people
cling to their interpretations of the book's advice without considering their
specific project context. I admit though that it is not the book's fault that
people are not able to judge about their situations but the mantra-like writing
style does not really encourage deeper reasoning. Funnily the book sometimes
diverges from the this writing style and condemns dogmatism:

> In an effort to make our classes and methods small, we might create too many
> tiny classes and methods. So this rule suggest that that we also keep our
> function and class counts lows.

> High class and method counts are sometimes the result of pointless dogmatism.

> Is there a set of simple practices that can replace experience? Clearly not.

> _Clean Code_, chapter 12, p176

These passages are rare and I wish the book would contain much more of such
diligent statements that triggers the reader's thinking process and nudges him
to use his own judgement. It's important to remember that software development
is not a one-size-fits-all endeavor. Good developers understand the principles
outlined in _Clean Code_ but also possess the judgment to apply them
appropriately in different scenarios. Good advice and practices always come with
trade-offs. These trade-offs must be transparent and known, otherwise it's not
possible to use them adequately. Rules and guidelines are only tools in our
toolbox but we need to reason and judge about them for every problem anew.
That's why I like Kent Beck's quote so much:

> It depends. - _Kent Beck_

Basically, with this tiny quote Kent covers the soul of software engineering.
It's also my most used sentence at work :smile:.

## The Ugly

The book's examples, particularly the infamous _prime generator_, often
contradict the advice it offers. These examples prioritize verbosity over
clarity, making them difficult to understand, especially for beginners. They
frequently rely on Java-specific frameworks like
[FitNesse](https://fitnesse.org/) and [JUnit](https://junit.org/junit5/)
internals, creating a barrier for programmers unfamiliar with those tools.
Additionally, some examples, like the Argument Parser, sprawl across tens of
pages, overwhelming readers. The overemphasis on dogma leads to convoluted code,
hindering the learning process for "programmer journeymen," the intended
audience of the book. **That's why the code examples are the ugly part of the
book.**

```java
// from Clean Code chapter 10
// listing 10-8

package literatePrimes;

import java.util.ArrayList;

public class PrimeGenerator {
  private static int[] primes;
  private static ArrayList<Integer> multiplesOfPrimeFactors;

  protected static int[] generate(int n) {
    primes = new int[n];
    multiplesOfPrimeFactors = new ArrayList<Integer>();
    set2AsFirstPrime();
    checkOddNumbersForSubsequentPrimes();
    return primes;
  }

  private static void set2AsFirstPrime() {
    primes[0] = 2;
    multiplesOfPrimeFactors.add(2);
  }

  private static void checkOddNumbersForSubsequentPrimes() {
    int primeIndex = 1;
    for (int candidate = 3;
         primeIndex < primes.length;
         candidate += 2) {
      if (isPrime(candidate))
        primes[primeIndex++] = candidate;
    }
  }

  private static boolean isPrime(int candidate) {
    if (isLeastRelevantMultipleOfNextLargerPrimeFactor(candidate)) {
      multiplesOfPrimeFactors.add(candidate);
      return false;
    }
    return isNotMultipleOfAnyPreviousPrimeFactor(candidate);
  }

  private static boolean isLeastRelevantMultipleOfNextLargerPrimeFactor(int candidate) {
    int nextLargerPrimeFactor = primes[multiplesOfPrimeFactors.size()];
    int leastRelevantMultiple = nextLargerPrimeFactor * nextLargerPrimeFactor;
    return candidate == leastRelevantMultiple;
  }

  private static boolean isNotMultipleOfAnyPreviousPrimeFactor(int candidate) {
    for (int n = 1; n < multiplesOfPrimeFactors.size(); n++) {
      if (isMultipleOfNthPrimeFactor(candidate, n))
        return false;
    }
    return true;
  }

  private static boolean isMultipleOfNthPrimeFactor(int candidate, int n) {
   return
     candidate == smallestOddNthMultipleNotLessThanCandidate(candidate, n);
  }

  private static int smallestOddNthMultipleNotLessThanCandidate(int candidate, int n) {
    int multiple = multiplesOfPrimeFactors.get(n);
    while (multiple < candidate)
      multiple += 2 * primes[n];
    multiplesOfPrimeFactors.set(n, multiple);
    return multiple;
  }
}
```

The
[prime generator](https://gist.github.com/gerlacdt/41cf41c1f32093ca2866d35dffc88481)
example is an excellent candidate for demonstrating the pitfalls of overly
dogmatic adherence to good practices. The primary issue lies in its excessive
function granularity. This fragmentation forces readers to constantly jump
between functions, hindering comprehension. The sheer number of tiny functions
necessitates overly verbose names, further obfuscating the code's intent. For
instance, consider the following function names:

- _smallestOddNthMultipleNotLessThanCandidate()_
- _isLeastRelevantMultipleOfNextLargerPrimeFactor()_

These convoluted names highlight the failure of abstraction. In this case a few
lines of code are more easily understood than the function name itself.

Regarding side-effects, the prime generator example applies exactly the opposite
of Clean Code. The book clearly consults to avoid side-effects but the prime
generator is full of them! A short primer what a
[side effect](<https://en.wikipedia.org/wiki/Side_effect_(computer_science)>)
is:

> a function is said to have a side effect if it modifies some state variable
> outside its local environment.

> Example side effects include modifying a non-local variable, modifying a
> static local variable, modifying a mutable argument passed by reference,
> performing I/O or calling other functions with side-effects.

Unfortunately, almost all `private static` functions of the prime generator have
a side-effect because they change internal static class variables. Worse yet,
some side-effects are hidden in nested functions making the code even harder to
understand.

[Command-Query Separation(CQS)](https://en.wikipedia.org/wiki/Command%E2%80%93query_separation)
is highly recommended in the book and is a great way of writing
intention-revealing APIs but it is also disregarded in the prime generator
example. A short description of CQS:

> It (CQS) states that every method should either be a command that performs an
> action, or a query that returns data to the caller, but not both.

Whereby a command has a side-effect but no return value and a query returns a
value but has no side-effect. Consistently applied, CQS hugely helps to write
intention-revealing interfaces and maintainable code. The function
`boolean isPrime(int candidate)` though returns a value but also has a
side-effect. It changes a static class variable, thereby giving the wrong
intention to the API user.

Lastly, the Prime Generator example employs a non-pragmatic Java style. The use
of static class variables and functions introduces several issues. Firstly, it
compromises thread safety. Secondly, the
`protected static int[] generate(int n)` function returns a static class
variable, exposing class internals and potentially allowing external
modifications that can violate the class's invariants.

Unfortunately, this style might mislead junior programmers into adopting these
practices in their projects. The inclusion of such examples in a book like Clean
Code could inadvertently promote this suboptimal coding style. While it could be
true that this style was acceptable in the past, it has always been considered
poor practice - in any time period[1].

For a comparison, you can look up my
[primary number generator](https://gist.github.com/gerlacdt/772c86b2f592a16ea6303defaf74974f)
implementation closely based on the Sieve of Eratosthenes algorithm with some
supporting comments - yes sometimes comments are more helpful than refactoring
everything into tiny functions!

## Conclusion

Would I recommend _Clean Code_ for new programmers? Yes, but not anymore as the
first Software Engineering book. In my opinion there are better alternatives:

- [A Philosophy of Software Design](https://web.stanford.edu/~ouster/cgi-bin/book.php)
- [Code That Fits in Your Head](https://www.oreilly.com/library/view/code-that-fits/9780137464302/)
- [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/)
- more books on my [personal reading list](/blog/posts/programming-books/)

The above books contain great engineering wisdom and in my opinion they are
written in a open-minded way. The readers have some leeway for their own
thinking. The books give more background about the practices, trade-offs and
**why** these practices are useful. Although they miss the catchy name - _Clean
Code_ is matchless. However _Clean Code_ is a classic and still a required read
because it contains timeless advice. Just make sure you don't apply the
practices blindly and without judgement.

Last but not least, I want to point out that _Clean Code_ is an brilliant name
and a great foundation for other software engineering inventions. I came up with
one of my own :grin::

- [**_The Continuous Clean Code Process (CCCP)_**](/blog/posts/cccp/) - cleaning
  up/refactoring **perpetually**

# References

1. [Star Trek The Next Generation, All Good Things](https://www.imdb.com/title/tt0111281/),
   Picard: "Mr. Data, you are a clever man - in any time period."
