---
title: "Install Emacs on MacOS"
date: 2019-04-13T10:34:42+01:00
tags: ["emacs"]
draft: false
---

Yesterday Emacs 26.2 was released, see: https://www.gnu.org/software/emacs/news/NEWS.26.2

In this short guide i want to show how i install Emacs on my MacBook.
I use the vanilla Emacs build from https://emacsformacosx.com/.
It is just a simple *.dmg file*.

After installing the *dmg file*, Emacs is now available on your Mac as
an application but not in the terminal-app. The terminal still opens
the pre-install Emacs version. To make the new version available you
have to create a bash-script and put it in your `$PATH`.

```bash
# filename ~/bin/emacs

#!/bin/sh
/Applications/Emacs.app/Contents/MacOS/Emacs "$@"
```

Put the *emacsclient* command in your `$PATH` too. Just run the
following line:

```bash
ln -s /Applications/Emacs.app/Contents/MacOS/bin/emacsclient /usr/local/bin
```

For further information see: https://emacsformacosx.com/tips


##  My Emacs startup workflow

When i start a session i run the emacs-server with `emacs
--daemon`. This runs emacs in the background. Afterwards you can use
`emacsclient` to open files. From now on *emacsclient* does not
startup a full Emacs instance but utilises the running
emacs-server. Basically emacs opens instantly!

Additionally i created some bash aliases in my `~/.zshrc` or
`~/.bashrc` in order to shorten the emacs commands.

```bash
# filename ~/.zshrc or ~/.bashrc

alias edd='emacs --daemon'  # start emacs server in background
alias e='emacsclient -t'  # open emacs in terminal
alias ecc='emacsclient -c' # open GUI-emacs
alias ew='emacs -nw -q'   # opens vanilla emacs in the terminal
```

Finally i made Emacs the standard-editor in the shell. This makes
other terminal-applications use Emacs, e.g. git for editing
git-commit-messages.

```bash
# filename: ~/.zshrc or ~/.bashrc

export EDITOR="emacs -q -nw"  # make emacs my default editor in the terminal
```
