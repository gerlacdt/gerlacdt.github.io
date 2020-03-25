---
title: "Emacs Delete Lines"
date: 2020-04-06T10:42:05+01:00
tags: ["emacs"]
draft: true
---

Sometimes when i write small scripts i tend to insert `print`
statements for debugging. After i verified the correctness of my
program i want to get rid of all unnecessary debug statements. With
Emacs you can delete all lines which match a specific *regular
expression* with `M-x flush-lines` or with its alias `M-x
delete-matching-lines`. Interactively you can enter your regular
expression and all matching lines will be deleted from you cursor
position downwards.
