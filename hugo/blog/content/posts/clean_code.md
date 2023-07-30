---
title: "Clean Code: The good, the Bad and the Ugly"
date: 2023-07-30T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

_Clean Code_ by Robert C. Martin is a seminal programming book. A whole
generation of developers, including myself, became better programmers by
following the book's advice. But after almost twenty years, does the book still
hold up to its high standards, are there better alternatives nowadays - or was
some advice given by _Clean Code_ bad?

To be fair, the author himself claims in the preface that the "some
recommendations in the book are controversial and people might disagree". In
this article I try to disagree :smile: and give my opinion about the high and
low points.

## The Good

Besides the book's programming recommendations, I think the greatest achievement
of the book is coining the term **Clean Code** and creating awareness about code
quality:

> _The only way to go fast is to go well_ - Uncle Bob

Especially young developers like me profited heavily from this insight and I
truly believe this made the developers' world a better place. The impact of the
book in the programming world is second to none.

- general, foundational tips

  - good names for functions, variables (searchable, intention-revealing names)
  - good comments

- comes up with great thoughts/ideas:

  - the boy scout rule
  - command query separation
  - DRY (Don't Repeat Yourself), no duplication

- exception handling (don't pass null, don't return null, use unchecked
  exceptions)
- unit testing (FIRST, fast, independent, repeatable, self-validation, timely)
  - one assert per test
- good topic like Single Responsibility Principle, Cohesion and Coupling
  (unfortunately the books fails to deliver good explanations and does not
  provide good examples) -> I know from my own experience come up with good code
  examples is hard!
- SOLID principles

## The Bad

- lenghty and hard to comprehend code examples

- function should be small and even smaller

- completely missing of Functional Programming Design:

  - immutability
  - referential transparency
  - high-order functions
  - focus on no side-effects

- SOLID principle are introduced like SRP but with very bad examples people
  cannot relate to

- side-effect are described and considered as bad but the book over-uses
  side-effect in many coding examples. It seems Robert Martin does not consider
  changing instance variables as side-effects (I think it still make the code
  hard to read and forces me to jump around inside the class file)
- same is true for command query separation, he explain it but completely
  disregards in many code examples

- obsolete topics: Formatting, Java-Centric with EJB, Aspect does not belong in
  a general Programming Book anymore, Concurrency with traditional Java tooling
  is not out-of-date, nowadays there are first-class concurrency languages like
  Go or Rust, they changed the approach a lot. Java coding-style without
  Lambdas, functional Interfaces, Streams etc.

## The Ugly

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

## conclusion

- It depends :) For Junior and programming beginners there are better modern
  alternatives about general software engineering:

- A Philosophy of Software Design (for medium to experienced developers)
- The Pragmatic Programmer
- Code Complete
- Code that fits in your head
  https://www.oreilly.com/library/view/code-that-fits/9780137464302/

For even more books and deeper insight I recommend my
[reading list](/posts/programming-books/)

Maybe for a Junior Developer, it's a good start - it's better than nothing. But
often Clean Code's advice needs to be judged very carefully if it really applies
to the situation. But still Clean Code is a classic and should be read by every
serious (senior) developer since it still holds timeless advice, but you need to
be careful filtering out the right ones. Further as a senior, you need to be
know Clean Code just in order to discuss about it and in case to argue why
advice is good, deprecated or bad.

To be fair in chapter 1, the author warns about being controversial and many
will also disagree with the book's recommendation. Unfortunately, the book has
such a great community and often is treated the "bible" which leads to
non-fruitful and heated discussions where no situational reasoning and judgement
is allow - just like a religion, people just follow the book without thinking
(it's often the most convenient solution) - sure this is not Uncle Bob's fault
to have written such a well-received book.

## Final Words

> The only way to go fast, is to go well. -- Uncle Bob

Robert's quote heavily inspired me and I try to live and code by it everyday.
Therefore I also want to coin a term :)

Anyhow I think the greatest achievement of the book is coining the term **Clean
Code** and making all programmers aware that:

> _The only way to go fast is to go well_ - Uncle Bob

- reading the book is essential as a senior software developer because you need
  to know it in order to discuss with your peers
- also only read the book with a good portion of your own judgement, a lot of
  advice is outdated or just dreadful.
- also the book caused a lot of heated discussion because the book is treated as
  the "bible" with dogmas
- especially for junior developers, I cannot recommend it because the wrong
  advice it contains could mislead young aspiring developers because they lack
  their own judgement -> better alternative exist and after reading them _Clean
  Code_ can be a good complement to the other books. After reading them you are
  prepared to judge what is good and what is bad advice

**_The Continuous Clean Code Process (CCCP)_**

Try to clean up on a regular base, comply with your own high coding standard,
don't deviate to the dark side and continuously clean up your code bases.
