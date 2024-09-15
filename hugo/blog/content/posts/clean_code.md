---
title: "Clean Code: The good, the Bad and the Ugly"
date: 2024-08-04T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

_Clean Code_ by Robert C. Martin is a seminal programming book. A whole
generation of developers, including myself, became better programmers by the
book's advice. But after almost twenty years, does the book still hold up to its
high standards? Was some advice given by _Clean Code_ questionable or even
wrong? Are there better alternatives nowadays?

To be fair, the author himself claims in the preface that "_some recommendations
in the book are controversial and people might disagree_". In this article I try
to disagree. Let's go through to the **good, bad and ugly** parts of Clean Code!

## The Good

#### Great Programming Advice

Besides the book's programming guidance, I think the greatest achievement of the
book is coining the term **Clean Code** and creating a general awareness about
code quality:

> _The only way to go fast is to go well_ - Uncle Bob

The impact of the book in the programming world is second to none. All of a
sudden code quality, readability and maintainability were subject of discussion
for developer teams. I truly believe this made the developers' world a better
place.

The book itself is full of great programming advice, mainly written in Java but
applicable in other programming languages as well. It's especially valuable for
new programmers, starting the craft. You will find foundational tips regarding
functions and variable names that they should be intention-revealing and
searchable. Or explanations what makes a good comment and when should you better
rewrite the code. Other timeless ideas like the _DRY_ principle (Don't Repeat
Yourself), _Command-Query separation_ and the nice metaphor with the _boy scout
rule_ are also presented.

Additionally, numerous useful programming practices are given. Senior developers
take them for granted but junior developers need to learn them first, e.g.:

- _naming_: use problem-domain names for functions, classes and variables (DDD
  ubiquitous language)
- _exception handling_: don't pass null or return null, use unchecked
  exceptions, provide context in exceptions
- _unit testing_: tests should be FIRST(Fast, Independent, Repeatabl,e
  Self-validated, Timely) and should assert a single behaviour
- _Single Responsibility Principle_
- _functions_: do one thing, statements are all the same level of abstraction
- prefer _pure functions_ with no side-effects
- _Command Query Separation_: functions should return something and have no side
  effect. Or return nothing but have a side effect
- _DRY_
- don't comment code, delete it - there is version control
- _boundaries_, don't depend too much on 3rd party libs, hide them with
  interfaces - this will make your code testable
- _Law Of Demeter_
- _Data Abstraction_, use encapsulation/information hiding to preserve
  invariants (DDD)

These practices are timeless for all programmers - new and old. I keep finding
myself giving these tips over and over to team members in pair programming
sessions, discussions or other kind of talks.

#### Code Smells Compendium

Another highlight of the book comes at the very end: an extensive list of common
code smells. **It's not only important to know how to write good code but also
how to detect bad code.**

## The Bad

2 main points:

#### obsolete topics

Formatting, Java-Centric with EJB, Aspect does not belong in a general
Programming Book anymore, Concurrency topics are shallow and deprecated,
nowadays there are first-class concurrency languages like Go or Rust, they
changed the approach a lot, even Java has nowadays structured concurrency
capabilities. Java coding-style without Lambdas, functional Interfaces, Streams
etc.

only Java examples, missing new modern programming styles like functional
programming

- completely missing of Functional Programming Design:
  - immutability
  - referential transparency
  - high-order functions
  - focus on no side-effects (although this is sometimes mentions but not
    consistently applied)

#### mantra-like writing style

The topics are presented in a mantra-like way. More often than not there is no
explanation given why the specific advice is useful, when to use the presented
practices and in which concrete scenario to apply them. This is especially
insufficient for new programmers who have no good judgement yet.

Overusing any practice is bad. I made experience where new developers have read
the Design Pattern book and the next day they started to replace all if-else
conditions with Strategy Pattern :thinking_face:

For example advice like:

> The first rule of function is that they should be small. The second rule of
> functions is that they should be smaller than that. - _Clean Code, chapter 3,
> p34_

or

> Everytime you see a duplication in the code, it represents a missed
> opportunity for abstraction. - _Clean Code, chapter 17, p289_

are not _always_ beneficial. Sometimes such a coding style hurts more than it
helps, particularly when a pattern is applied consistently without reasoning. I
stand with Kent Beck's advice which is _always_ applicable:

> It depends. - _Kent Beck_

Good advice and practices always come with trade-offs and costs. These costs
must be transparent and known, otherwise it's not possible to use practices
adequately and in the right situations.

Software developer maturity levels:

Level 0 ignores advice and invents own uncommon coding style and practices

Level 1 No sense of code cleanliness

Level 2 Adheres religiously to code style advice in a book in all situations

Level 3 Can appreciate the book's advice and apply it correctly when applicable

Level 4 Writes and refactors code to make sound advice applicable in more
situations

The mantra-like writing-style leads to cargo-cult programming where people shout
out: "This is not clean code" although they do not really understand what this
means. Often these people overzealous adhere to the _Clean Code_ practices.
Actually this hurts me the most, because it always ends up in heated discussions
where people think they are right because it's written in the book without
considering the special circumstances. It's not the book's fault that people are
not able to judge about their situations though.

## The Ugly

#### bad examples and the culmination is the prime generator

- examples disregard the books own advice
- wordy and hard to comprehend code examples
- side-effect are described and considered as bad but the book uses side-effects
  in many coding examples. It seems Robert Martin does not consider changing
  instance variables as side-effects (I think it still make the code hard to
  read and forces me to jump around inside the class file)
- side effect definition
  https://en.wikipedia.org/wiki/Side_effect_(computer_science)
- I know from my own experience come up with good code examples is hard!

> a function is said to have a side effect if it modifies some state variable
> outside its local environment.

> Example side effects include modifying a non-local variable, modifying a
> static local variable, modifying a mutable argument passed by reference,
> performing I/O or calling other functions with side-effects.

- same is true for command query separation, he explain it but completely
  disregards in many code examples

clean code primary generator?!?

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

```java
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

The culmination of bad taste is the comparison of: related to Primes: Comparing
Knuth's Literature Programming Prime Generator C output with CWEB to a Java
solution without the "literature comments" is a complete disaster. The main part
of "Literature Programming" is the weaving comments into the code so that it
reads like novel, that's the beauty of it. Basically he compares the output of
the CWEB compiler with his Java example. The compiler output is not what should
humans read...

The code example often are very verbose and don't hold on what the book is
telling you. The examples show sometimes the opposite! This comparison is really
inappropriate and baffles me.

One can argument that this Java-style was good or popular back in the days but
honestly it was always bad style - in any time period[1].

## conclusion

Would I recommend _Clean Code_ for new programmers? Not anymore as the first
Software Engineering book. In my opinion there are better alternatives:

- [A Philosophy of Software Design](https://web.stanford.edu/~ouster/cgi-bin/book.php)
- [Code That Fits in Your Head](https://www.oreilly.com/library/view/code-that-fits/9780137464302/)
- [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/)
- you can find more books in my [reading list](/posts/programming-books/)

The above books contain great engineering wisdom and in my opinion they are
written in more a open-minded way. The readers have some leeway for their own
thinking. The books give more background about the practices, trade-offs and
**why** these practices are useful. Although they miss the catchy name - _Clean
Code_ is matchless. However _Clean Code_ is a classic and still a required read
because it contains timeless advice. Just make sure you don't apply the
practices without judgement and reasoning.

I don't know what's more annoying: the ugly parts I discovered when I reread the
book or the errant discussions with team members who only know buzzwords or
short memorized statements without recognizing the deeper background. In such
discussions, situational reasoning and judgement are ignored. Sure this is not
Uncle Bob's fault but the mantra-like writing style does not really encourage
trade-offs and judgement. Here I miss a bit more elaboration in the book. One of
my favorite parts of the book is when the book diverges from the mantra-like
writing style:

> In an effort to make our classes and methods small, we might create too many
> tiny classes and methods. So this rule suggest that that we also keep our
> function and class counts lows.

> High class and method counts are sometimes the result of pointless dogmatism.

> Is there a set of simple practices that can replace experience? Clearly not.

> _Clean Code_, chapter 12, p176

I wish the book would contain much more of such diligent statements that
triggers the reader's thinking process and nudges him to use his own judgement.

Last but not least, I want to point out that _Clean Code_ is an brilliant name
and a great foundation for other software engineering inventions (like mine
:smile:):

- [**_The Continuous Clean Code Process (CCCP)_**](/posts/cccp/) - cleaning
  up/refactoring regularly

# References

1. [Star Trek The Next Generation, All Good Things](https://www.imdb.com/title/tt0111281/),
   Picard: "Mr. Data, you are a clever man - in any time period."
