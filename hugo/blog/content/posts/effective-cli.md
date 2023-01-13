---
title: "Effective CLI (better IDEs)"
date: 2023-01-13T11:00:00+02:00
tags: ["programming", "softwareengineering", "linux", "shell"]
draft: true
---

## TL;DR

1. Modern Terminal with nice (Nerd) Fonts
1. ZSH
1. oh-my-zsh
1. Modern Prompt like starship
1. Modern CLI alternatives (fzf, ripgrep, bat)
1. Fast editor (Emacs, (Neo)Vim, Helix)
1. Organize your dotfiles in git with GNU Stow

- Command Line setup (more productive than an IDE!)

### Use a modern terminal

- Gnome Terminal
- KDE Konsole
- Alacritty (minimal, blazing fast, written in Rust)
- iTerm2 (if you are on MacOS) https://iterm2.com/

- adopt a nice and modern font with great icon support
  https://www.nerdfonts.com/ install example on ubuntu, MacOS

### Switch to ZSH

- make ZSH your default shell, with examples for Linux and MacOS
- more powerful than bash
- compatible with bash

### oh-my-zsh

https://github.com/ohmyzsh/ohmyzsh

- preconfigured shell with great defaults
- easily customizable to your needs
- aliases (git, kubernetes etc.)
- zsh completion (kubectl, terraform etc.)
- set common environment variables (EDITOR, VISUAL, GIT_EDITOR, PAGER etc.)
- write your own shell function like mem(), toggle_java8(), toggle_java17()

### starship

https://starship.rs/

- an even better prompt as the default oh-my-zsh
- show context in your shell prompt like:
  - current k8s cluster and namespace
  - current git branch with state, e.g.dirty or clean
  - current cloud subscription for Azure/AWS/GCP

### Modern CLI commands

- fzf, command history search, file/directory search, search on fire
  https://github.com/junegunn/fzf

- bat (cat alternative)
- z (smart change directory)
- delta (better diffs for git)
- ripgrep or silver searcher ag (grep alternative)
- tmux or zellij (terminal multiplexer, if your terminal does not support tabs)
- easier kubectl, kubectx, kubens, https://github.com/ahmetb/kubectx
- jq

  jq curl -s "https://jsonplaceholder.typicode.com/todos" | jq ".[]| {new_id:
  .id, new_title: .title}"

- yq, yq same as jq but for yaml files

### gnu stow

Manage dotfiles in github repository

https://venthur.de/2021-12-19-managing-dotfiles-with-stow.html
https://www.jakewiesler.com/blog/managing-dotfiles
https://dr563105.github.io/blog/manage-dotfiles-with-gnu-stow/

### editor: Emacs or neovim or helix (must be usable in terminal)

### Conclusion

- Shell is ultimately customizable
- Adopt the shell to your needs in order to be more productive
