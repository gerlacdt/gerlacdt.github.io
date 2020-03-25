---
title: "Find and Replace in multiple files"
date: 2019-04-13T10:13:14+01:00
tags: ["linux", "bash"]
draft: true
---

As a software programmer you often have to search and replace a
specific string in multiple files because of a refactoring or just
improving a variable or function name.

Normally you can use your IDE or your editor for this. But in bigger
codebases with thousands or hundred thousands of lines of code it can
be very slow. Neither IDEs work for remote SSH-sessions. In such
situations the command-line is pretty handy. It is available
everywhere and it is fast.

The following command replaces `old` with `new` in all `*.go` files in
the current directory:

```bash
find . -iname '*.go' | xargs sed -i '' -e 's/old/new/g'
```

I use this often in combination with
[git](https://git-scm.com/). First i commit my lastest changes, so i
have a clear state. Then i issue the above command. Afterwards i check
the changes in the files with `git diff`. If i do not like them,
reverting is possible with `git reset`.
