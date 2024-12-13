---
title: "Clean Code: The good, the Bad and the Ugly"
date: 2024-08-04T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

_Clean Code_ by Robert C. Martin is a seminal programming book. A whole
generation of developers, including myself, became better programmers by the
Uncle Bob's advice. But after almost twenty years, does the book still hold up
to its high standards? Was some advice given by _Clean Code_ questionable or
even wrong? Are there better alternatives nowadays?

To be fair, the author himself claims in the preface that "_some recommendations
in the book are controversial and people might disagree_". In this article I try
to disagree. Let's go through to the **good, bad and ugly** parts of Clean Code.

## The Good

Besides the book's programming guidance, I think the greatest achievement of the
book is coining the term **Clean Code** and creating a general awareness about
code quality:

> _The only way to go fast is to go well_ - Uncle Bob

The impact of the book in the programming world is second to none. All of a
sudden code quality, readability and maintainability were in the center of
discussion for developer teams. I truly believe this made the developers' world
a better place.

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
- Functions should comply to the _Command-Query Separation_ pattern. On the one
  hand functions returning something should have no side effects, on the other
  hand functions returning nothing have side effects
- Keep your code _DRY_
- Don't comment code, delete it - there is version control
- Try to have good _boundaries_ in your codebase. Don't depend too much on 3rd
  party libs, hide them behind interfaces. This will keep your code testable
- Good _Data Abstractions_ enabled by encapsulation/information hiding will
  preserve class invariants and will guarantee convenient and comprehensible
  class APIs.

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
modern programming practices. Additionally, the concurrency topics covered are
shallow and too low-level, especially considering the rise of first-class
concurrency languages like Go and Rust. These new languages offer more robust
and efficient approaches to concurrency. Furthermore, the book primarily uses
Java with Object-Oriented Programming examples, which may not adequately
represent the diversity of modern programming styles today. Especially it lacks
a comprehensive exploration of functional programming concepts, including
immutability, referential transparency, high-order functions, and the emphasis
on avoiding side effects. Such principles are occasionally mentioned but they
are not consistently applied in the book's examples.

Second, **the book's dogmatic presentation of coding principles can be a
double-edged sword.** While it offers valuable advice, its lack of nuanced
explanations and context can lead to misunderstandings, particularly for newer
developers. Overzealous adherence to rules like "small functions" or the "DRY
principle" without seeing the costs is harmful to code quality.

For example, blindly applying the "DRY principle" can result in excessive
abstraction, leading to a proliferation of tiny, overly complex classes. This
can make the code harder to understand and maintain. A better approach,
recognizing that sometimes duplication is acceptable to improve readability or
performance, is essential for effective software development. Even more
important is that we should not place tactical coding practices above higher
design principles like coupling, cohesion, information hiding etc.

More mantra-like examples can be found in the book:

> The first rule of function is that they should be small. The second rule of
> functions is that they should be smaller than that. - _Clean Code, chapter 3,
> p34_

> Every time you see a duplication in the code, it represents a missed
> opportunity for abstraction. - _Clean Code, chapter 17, p289_

**This writing style can also contribute to cargo-cult programming,** where
developers blindly follow rules without understanding the underlying reasons.
More often than not, it leads to heated debates and misunderstandings, as people
cling to their interpretations of the book's advice without considering the
specific context of their projects. I admit though that it is not the book's
fault that people are not able to judge about their situations but the
mantra-like writing style does not really encourage deeper reasoning. Funnily
the book sometimes diverges from the mantra-like writing style and condemns
dogmatism:

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
possible to use practices adequately. Rules and guidelines are only tools in our
toolbox but still we need to reason and judge about them again and again since
every situation is unique. That's why I like Kent Beck's quote so much:

> It depends. - _Kent Beck_

Basically, this tiny quote covers the soul of software engineering.

## The Ugly

The examples are the Achilles' heel of the book. **The main problem with the
examples is that they often disregard the book's own advice and even promote the
exact opposite**. Examples are more verbose than necessary which makes it hard
to comprehend especially for new programmers because a lot of Java background
knowledge is required, e.g. some code examples are built up on
[FitNesse](https://fitnesse.org/) or the [Junit](https://junit.org/junit5/)
framework internals, others are just too lengthy (tens of pages) like the
Argument Parser. More often than not, the examples apply the book's advice
dogmatically which ends up in bad code. As a programmer journey man, the target
audience of the book, it is hard to filter out the critical information from the
noise. Let's go through a concrete example: the _prime generator_.

#### Prime Generator

The
[Prime Generator](https://gist.github.com/gerlacdt/41cf41c1f32093ca2866d35dffc88481)
example is my personal pet peeve and the epitome of applying good practices
dogmatically but ending up with poor code. So what is so bad about it? Many
things. First of all, the functions are too small. You have to hop around from
function to function multiple times in order to understand the code. Because
there are so many functions, it's very hard to find good intuitive names for
them that's why they get longer and longer. Their meaning is obscured too.
Reading the two or three lines of code is more comprehensive than reading the
function name itself. This is a sign of bad abstractions. For example, are you
able to deduce what's behind _isLeastRelevantMultipleOfNextLargerPrimeFactor()_?

Now it's time to focus on where the prime generator applies exactly the opposite
of clean code. The book clearly consults against side-effects but the Prime
Generator is full of them! A short primer what a
[side effect](<https://en.wikipedia.org/wiki/Side_effect_(computer_science)>)
is:

> a function is said to have a side effect if it modifies some state variable
> outside its local environment.

> Example side effects include modifying a non-local variable, modifying a
> static local variable, modifying a mutable argument passed by reference,
> performing I/O or calling other functions with side-effects.

Unfortunately, almost all `private static` functions have a side-effect because
they change internal static class variables. Worse yet, the functions are so
small and highly nested, sometimes the side-effect is hidden because it happens
in a nested function.
[Command-Query Separation(CQS)](https://en.wikipedia.org/wiki/Command%E2%80%93query_separation)
is a great way of writing intention-revealing APIs but it is also disregarded in
the Prime Generator. A short recap:

> It (CQS) states that every method should either be a command that performs an
> action, or a query that returns data to the caller, but not both.

Whereby a command has a side-effect but not return value and a query returns a
value but has no side-effect. Consistently applying CQS in a codebase hugely
helps to write intention-revealing interface and maintainable code. The function
`boolean isPrime(int candidate)` though returns a value but also has a
side-effect. It changes a static class variable and thereby the internal class
state. Even worse the function name `isPrime()` confuses the reader even more
since it gives the wrong intention that there is no side-effect.

Last but not least, the whole example is written in non-pragmatic Java style.
The use of static class variables and functions is a strange choice and causes
multiple problems. First the code is unnecessarily thread-unsafe. Second, the
main function `generate(int n)` returns the static class variable
`int[] primes`. By doing this, every API consumer has access to the internals of
the `PrimeGenerator` class and can destroy the class invariants by overriding
`int[] primes` from outside. Using `protected` visiblity for the main function
`generate(int n)` in combination with `static` is also questionable. The worst
part is that with examples junior programmers will copy this coding style and
apply their work projects :scream:!

The whole examples give the impression that this good Java style, although it's
very bad. Hopefully nobody write Java code like this in his daily work projects.
One still can argument that this Java-style was popular back in the days but
honestly it was always bad style - in any time period[1].

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

In comparison,
[my primary number generator](https://gist.github.com/gerlacdt/772c86b2f592a16ea6303defaf74974f)
closely based on the Sieve of Eratosthenes algorithm with some supporting
comments:

```python
# Program collects all primes less than or equal n.
# It uses the Sieve of Eratosthenes algorithm, see
# https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes


def primes(n):
    # Initializes start array with n-entries, all numbers are primes initially
    prime = [True for i in range(n + 1)]

    # 0 and 1 are no primes
    prime[0] = False
    prime[1] = False

    # start from 2 as first prime
    p = 2
    while p * p <= n:
        # number not crossed out yet, it must be a prime
        if prime[p] == True:
            # Cross out all multiples of p
            for i in range(p ** 2, n + 1, p):
                prime[i] = False
        p += 1

    # collect all primes aka the numbers not crossed out
    return [i for i, v in enumerate(prime) if v]
```

Although I know from my own writing experience, coming up with good code
examples is hard, the examples of _Clean Code_ are not worthy of a seminal book.

## conclusion

Would I recommend _Clean Code_ for new programmers? Yes, but not anymore as the
first Software Engineering book. In my opinion there are better alternatives:

- [A Philosophy of Software Design](https://web.stanford.edu/~ouster/cgi-bin/book.php)
- [Code That Fits in Your Head](https://www.oreilly.com/library/view/code-that-fits/9780137464302/)
- [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/)
- for a deep-dive you can find more books in my
  [reading list](/posts/programming-books/)

The above books contain great engineering wisdom and in my opinion they are
written in more a open-minded way. The readers have some leeway for their own
thinking. The books give more background about the practices, trade-offs and
**why** these practices are useful. Although they miss the catchy name - _Clean
Code_ is matchless. However _Clean Code_ is a classic and still a required read
because it contains timeless advice. Just make sure you don't apply the
practices without judgement and reasoning.

Last but not least, I want to point out that _Clean Code_ is an brilliant name
and a great foundation for other software engineering inventions. I came up with
one of my own :grin::

- [**_The Continuous Clean Code Process (CCCP)_**](/posts/cccp/) - cleaning
  up/refactoring **perpetually**

# References

1. [Star Trek The Next Generation, All Good Things](https://www.imdb.com/title/tt0111281/),
   Picard: "Mr. Data, you are a clever man - in any time period."
