---
title: "ag the_silver_searcher, a grep replacement"
date: 2019-04-13T10:04:01+01:00
tags: ["linux", "bash"]
draft: false
---


As a programmer you often search for a specific strings or regexps in
multiple files. Previously i used to do this with the well-known
GNU-tools `find` and `grep`.

The following command searches for `main` in all `*.go` files in the
current directory and:

```bash
find . -iname '*.go' | xargs grep -inH  "main"
# -i ignore case, -n print line number, -H print filename
```

Recently i discovered [ag the silver
searcher](https://github.com/ggreer/the_silver_searcher). `ag` is
much faster than `grep` and searches directories recursively by
default. `ag` respects out-of-the-box your `.gitignore` files.

You can install `ag` on a Mac with [brew](https://brew.sh/), `brew
install the_silver_searcher`.  The following command searches all
files in your current directory for the string "main".

```bash
ag main
```

![ag_silver_searcher](/blog/img/ag_silver_searcher.png)


`ag` considers your `.gitignore` automatically but you can tweak it
even more with your own `.agignore` file. I put my `.agignore` file in
my `$HOME` folder so `ag` can find it everywhere.

```bash
# filename: ~/.agignore

node_modules/
build/
target/
your-custom-folder-to-ignore/
*.bak
```

And if you miss your regular grep-output format, just use:

`ag main --vimgrep`
