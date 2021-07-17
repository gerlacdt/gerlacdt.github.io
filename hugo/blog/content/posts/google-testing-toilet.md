---
title: "Google Testing Toilet Collection"
date: 2020-05-23T08:00:00+02:00
tags: ["programming", "testing"]
draft: false
---

In my one of my previous posts i wrote about [Good Unit
Tests](http://gerlacdt.github.io/posts/unit-testing/). During my
research i discovered the famous [Google Testing on the
Toilet](https://testing.googleblog.com/) posts which provide great
advice. The Testing-on-the-Toilet articles are short (~3min reads) and
give hands-on examples, ready to be applied immediately. Here are my
favorites:

### Testing on the Toilet

* [Tests Too DRY? Make Them
  DAMP!](https://testing.googleblog.com/2019/12/testing-on-toilet-tests-too-dry-make.html)

* [Exercise Service Call Contracts in Tests](https://testing.googleblog.com/2018/11/testing-on-toilet-exercise-service-call.html)

* [Only Verify Relevant Method Arguments](https://testing.googleblog.com/2018/06/testing-on-toilet-only-verify-relevant.html)

* [Keep Tests Focused](https://testing.googleblog.com/2018/06/testing-on-toilet-keep-tests-focused.html)

* [Cleanly Create Test Data](https://testing.googleblog.com/2018/02/testing-on-toilet-cleanly-create-test.html)

* [Only Verify State-Changing Method Calls](https://testing.googleblog.com/2017/12/testing-on-toilet-only-verify-state.html)

* [Keep Cause and Effect Clear](https://testing.googleblog.com/2017/01/testing-on-toilet-keep-cause-and-effect.html)

* [What Makes a Good End-to-End Test?](https://testing.googleblog.com/2016/09/testing-on-toilet-what-makes-good-end.html)

* [What Makes a Good Test?](https://testing.googleblog.com/2014/03/testing-on-toilet-what-makes-good-test.html)

* [Know Your Test Doubles](https://testing.googleblog.com/2013/07/testing-on-toilet-know-your-test-doubles.html)

* [Just Say No to More End-to-End Tests](https://testing.googleblog.com/2015/04/just-say-no-to-more-end-to-end-tests.html)

* [Don't overuse Mocks](https://testing.googleblog.com/2013/05/testing-on-toilet-dont-overuse-mocks.html)

* [Test Public APIs over implementation details](https://testing.googleblog.com/2015/01/testing-on-toilet-prefer-testing-public.html)

* [Test behaviors, not methods](https://testing.googleblog.com/2014/04/testing-on-toilet-test-behaviors-not.html)


### Code Health

[Code
Health](https://testing.googleblog.com/2017/04/code-health-googles-internal-code.html)
is a subseries of Googles Testing blog and contains fundamental wisdom
about keeping large codebases maintainable, readable and
"healthy". Following these practices reduces complexity and
facilitates simplicity. As a consequence programming stays enjoyable
and developers productivity does not decay over time.


* [Code Health: Respectful Reviews == Useful
  Reviews](https://testing.googleblog.com/2019/11/code-health-respectful-reviews-useful.html)

* [Code Health: Make Interfaces Hard to
  Misuse](https://testing.googleblog.com/2018/07/code-health-make-interfaces-hard-to.html)

* [Code Health: Obsessed With
  Primitives?](https://testing.googleblog.com/2017/11/obsessed-with-primitives.html)

* [Code Health:
  IdentifierNamingPostForWorldWideWebBlog](https://testing.googleblog.com/2017/10/code-health-identifiernamingpostforworl.html)

* [Code Health: Providing Context with Commit Messages and Bug
  Reports](https://testing.googleblog.com/2017/09/code-health-providing-context-with.html)

* [Code Health: Eliminate YAGNI
  Smells](https://testing.googleblog.com/2017/08/code-health-eliminate-yagni-smells.html)

* [Code Health: To Comment or Not to
  Comment?](https://testing.googleblog.com/2017/07/code-health-to-comment-or-not-to-comment.html)

* [Code Health: Too Many Comments on Your Code
  Reviews?](https://testing.googleblog.com/2017/06/code-health-too-many-comments-on-your.html)

* [Code Health: Reduce Nesting, Reduce
  Complexity](https://testing.googleblog.com/2017/06/code-health-reduce-nesting-reduce.html)


### Hackable projects

Google defines "hackable" code as code which is "nice to work on". In
this subseries they define the traits of healthy and likable projects.

* [Hackable Projects - Pillar 1: Code
  Health](https://testing.googleblog.com/2016/08/hackable-projects.html)

* [Hackable Projects - Pillar 2:
  Debuggability](https://testing.googleblog.com/2016/10/hackable-projects-pillar-2-debuggability.html)

* [Hackable Projects - Pillar 3:
  Infrastructure](https://testing.googleblog.com/2016/11/hackable-projects-pillar-3.html)


### More on testing and code health

Are you interested in even more Google testing practices? I recommend
the great talk ["All Your Tests are
Terrible..."](https://www.youtube.com/watch?v=u5senBJUkPc) from [Titus
Winters](https://twitter.com/tituswinters) and [Hyrum
Wright](https://twitter.com/hyrumwright), both are co-authors of the
[#FlamingoBook](https://www.oreilly.com/library/view/software-engineering-at/9781492082781/)
which gives deep insights in Google's software engineering
practices. Hyrum has even his own law named after him -- [Hyrum's
Law](https://www.hyrumslaw.com/).
