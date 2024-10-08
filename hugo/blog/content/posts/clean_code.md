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
- In good _unit testing_ tests should be FIRST (Fast, Independent, Repeatable
  Self-validated, Timely) and should assert a single behaviour
- Classes should comply to the _Single Responsibility Principle_
- _Functions_ should do one thing and all statements should have the same level
  of abstraction
- _pure functions_ should be preferred without side-effects
- Functions should comply to the _Command Query Separation_ pattern. On the one
  hand functions returning something should have no side effects, on the other
  hand functions returning nothing have side effects
- Keep your code _DRY_
- Don't comment code, delete it - there is version control
- Try to have good _boundaries_ in your codebase. Don't depend too much on 3rd
  party libs, hide them behind interfaces. This will make your code testable
- Good _Data Abstractions_ enabled by encapsulation/information hiding will
  preserve class invariants and will guarantee convenient and easy-to-use class
  APIs.

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
concurrency languages like Go and Rust. These languages offer more robust and
efficient approaches to concurrency. Furthermore, the book primarily uses Java
with Object-Oriented Programming examples, which may not adequately represent
the diversity of modern programming styles today. Especially it lacks a
comprehensive exploration of functional programming concepts, including
immutability, referential transparency, high-order functions, and the emphasis
on avoiding side effects. These principles are occasionally mentioned but they
are not consistently applied in the book's examples.

Second, **the book's dogmatic presentation of coding principles can be a
double-edged sword.** While it offers valuable advice, its lack of nuanced
explanations and context can lead to misunderstandings, particularly for newer
developers. Overzealous adherence to rules like "small functions" or the "DRY
principle" without seeing the costs can actually hinder code quality.

For example, blindly applying the "DRY principle" can result in excessive
abstraction, leading to a proliferation of tiny, overly complex classes. This
can make the code harder to understand and maintain. A better approach,
recognizing that sometimes duplication is acceptable to improve readability or
performance, is essential for effective software development. Even more
important is that we should not place simple coding practices above higher
design principles like coupling, cohesion, information hiding etc.

More mantra-like examples can be found in the book directly:

> The first rule of function is that they should be small. The second rule of
> functions is that they should be smaller than that. - _Clean Code, chapter 3,
> p34_

> Everytime you see a duplication in the code, it represents a missed
> opportunity for abstraction. - _Clean Code, chapter 17, p289_

**This writing style can also contribute to cargo-cult programming,** where
developers blindly follow rules without understanding the underlying reasons.
More often than not, it leads to heated debates and misunderstandings, as people
cling to their interpretations of the book's advice without considering the
specific context of their projects. I admit though that it is not the book's
fault that people are not able to judge about their situations but the
mantra-like writing style does not really encourage reasoning. Funnily the book
sometimes diverges from the mantra-like writing style and condemns dogmatism:

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

#### bad examples

- examples disregard the books own advice
- wordy and hard to comprehend code examples
- side-effect are described and considered as bad but the book uses side-effects
  in many coding examples. It seems Robert Martin does not consider changing
  instance variables as side-effects (I think it still make the code hard to
  read and forces me to jump around inside the class file)
- side effect definition
  https://en.wikipedia.org/wiki/Side_effect_(computer_science)
- I know from my own experience come up with good code examples is hard!

The code example often are very verbose and don't hold on what the book is
telling you. The examples show sometimes the opposite!

- same is true for command query separation, he explain it but completely
  disregards in many code examples

#### culmination is the prime generator

side-effect definition

> a function is said to have a side effect if it modifies some state variable
> outside its local environment.

> Example side effects include modifying a non-local variable, modifying a
> static local variable, modifying a mutable argument passed by reference,
> performing I/O or calling other functions with side-effects.

https://gist.github.com/gerlacdt/41cf41c1f32093ca2866d35dffc88481

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

good primary number generator
https://gist.github.com/gerlacdt/772c86b2f592a16ea6303defaf74974f

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

    # collect all primes, the numbers not crossed out
    return [i for i, v in enumerate(prime) if v]
```

One can argument that this Java-style was good or popular back in the days but
honestly it was always bad style - in any time period[1].

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
