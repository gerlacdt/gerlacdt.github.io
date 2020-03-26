---
title: "Emacs Test Regexps"
date: 2019-04-06T10:39:15+01:00
tags: ["emacs"]
draft: false
---


Replacing or searching text with *regular expressions* is very
common. But sometimes it is hard to get them right and you need some
playground to try them out. In order to verify regular expressions i
used to visit webpages like:

* https://regexr.com/
* https://regex101.com/

But these webpages are optimised for perl, javascript or bash
expressions and do not support Emacs regular expressions directly. In
Emacs you have to consider some peculiarities. For example you have to
escape parentheses.

Recently i learnt about `M-x regexp-builder` with which you can test
your regular-expressions interactively. Just enter the regular
expression and the text in your buffer will be highlighted
immediately. It even considers different capture groups and highlights
them in different colours. Very nice!

![emacs_regexp_builder](/img/emacs_regexp_builder.png)
