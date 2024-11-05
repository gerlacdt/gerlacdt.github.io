---
title: "Emacs as RSS reader"
date: 2020-10-10T10:00:00+01:00
tags: ["emacs"]
draft: false
---

Do you have a list of websites you visit regularly? Just to find out
nothing new was added. This manual scraping is often a waste of
time. Worse yet, scanning and filtering these websites for new
interesting content is even more time consuming.

Subscribing to an [RSS feed](https://en.wikipedia.org/wiki/RSS) can
reduce your effort immensely. An RSS feed "pushes" the newest updates
to subscribers and they will never miss updates. In order to make use
of RSS feeds you need a "feed reader" or "feed aggregator". Such a
feed reader will save you from visiting your favorite websites again
and again, so you can use your precious time more productively.

[Emacs](https://www.gnu.org/software/emacs/) is the epitome of
extensibility, and it's no surprise that Emacs makes also a great RSS
reader!  The [elfeed](https://github.com/skeeto/elfeed) package turns
Emacs into a full-fledged feed reader which support multiple feeds,
searching, filtering and more.

The following shows a sample elfeed configuration with some
programming news subscriptions. elfeed will only download the last two
days of updates and mark them as unread.

``` common-lisp
;; data is stored in ~/.elfeed
(setq elfeed-feeds
      '(
        ;; programming
        ("https://news.ycombinator.com/rss" hacker)
        ("https://www.heise.de/developer/rss/news-atom.xml" heise)
        ("https://www.reddit.com/r/programming.rss" programming)
        ("https://www.reddit.com/r/emacs.rss" emacs)

        ;; programming languages
        ("https://www.reddit.com/r/golang.rss" golang)
        ("https://www.reddit.com/r/java.rss" java)
        ("https://www.reddit.com/r/javascript.rss" javascript)
        ("https://www.reddit.com/r/typescript.rss" typescript)
        ("https://www.reddit.com/r/clojure.rss" clojure)
        ("https://www.reddit.com/r/python.rss" python)

        ;; cloud
        ("https://www.reddit.com/r/aws.rss" aws)
        ("https://www.reddit.com/r/googlecloud.rss" googlecloud)
        ("https://www.reddit.com/r/azure.rss" azure)
        ("https://www.reddit.com/r/devops.rss" devops)
        ("https://www.reddit.com/r/kubernetes.rss" kubernetes)
))

(setq-default elfeed-search-filter "@2-days-ago +unread")
(setq-default elfeed-search-title-max-width 100)
(setq-default elfeed-search-title-min-width 100)

```

A usual workflow looks like this:

* start `elfeed`
* `G` fetches the newest RSS feed updates
* `s` filter for a specific topic like `java`, `golang` or `kubernetes`
* `c` clears the search filter and shows everything again
* navigate up and down with `n` (next line) and `p` (previous line)
* `b` open current url in your browser


For a complete documentation visit the official elfeed page:
https://github.com/skeeto/elfeed

### Screen record

![elfeed](/blog/img/elfeed.gif)
