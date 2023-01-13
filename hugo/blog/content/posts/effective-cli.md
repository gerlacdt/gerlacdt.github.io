---
title: "Effective CLI (more producive than an IDE!)"
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

The _Unix shell_ or Command-Line Interface (CLI) is an ancient tool. Despite be
around forever, the _shell_ is highly customizable and with a good
configuration, it is on par with modern graphical IDEs. Because it is so
configurable, you can make it truly your own, thereby surpassing out-of-the-box
experience of IDEs. The _shell_ has existed for 50+ years, and since it's so
adaptable, it's not going anywhere which makes it a great knowledge investment
for the future. No need to re-learn your editing toolset every few years.
However, it comes with a catch, a full _shell_ configuration takes time and
effort. In this article, I want to show you how I configured my _shell_ for
ultimate productivity and in minimal time, so you don't have to go same process
as I did.

### Use a modern terminal

All starts with a good _terminal application_ which is the door to your _shell
environment_. A good terminal is crucial for a top-notch developer experience.
Recommended terminals are:

- [Gnome Terminal](https://help.gnome.org/users/gnome-terminal/3.40/)
- [KDE Konsole](https://apps.kde.org/konsole/)
- [Alacritty](https://alacritty.org/) (minimal, blazing fast, written in Rust)
- [iTerm2](https://iterm2.com/) (MacOS)

For a modern look-and-feel, I suggest [Nerd Fonts](https://www.nerdfonts.com/).
It comes with icons for Kubernetes, Python, Java, Golang, Rust which pretty up
you CLI. You can install Nerd Fonts in Ubuntu with:

```bash
# download a specific font (or choose a different font to your liking)
wget https://github.com/ryanoasis/nerd-fonts/releases/download/v2.2.2/DejaVuSansMono.zip

# unzip file
unzip DejaVuSansMono.zip

# move all files in fonts directory
mv *.ttf ~/.local/share/fonts/

# rebuild font cache, so that font will be available
fc-cache -fv

# afterwards you need to set the new font in you terminal application
# for Gnome Terminal, you can do it in the Menu Settings
# for Alacritty you need to adjust the config file, see below
```

```yaml
# file ./config/alacritty/alacritty.yaml

font:
  normal:
    # Font family
    family: DejaVuSansMono NF
  # Point size
  size: 12.0
```

### Switch to ZSH

Great, you have configured your terminal. Most Linux distribution come with
_bash_ as the default shell, but we want to switch to **zsh** which is more
powerful than _bash_, but yet fully compatible to it. For newer MacOS versions
_zsh_ is the default shell. For Ubuntu you have to install _zsh_:

```bash
# install zsh (often it's already there, so this command is optional)
sudo apt install zsh

# make zsh the default shell
chsh -s /usr/bin/zsh
```

### oh-my-zsh

With _zsh_ installed, we paved the way for our customization endeavours. Now we
can install [oh-my-zsh](https://ohmyz.sh/), a delightful, extensible,
pre-configured _zsh environment_ with sane defaults. It builds the base for
further tailoring.

- aliases (git, kubernetes etc.)
- zsh completion (kubectl, terraform etc.)
- set common environment variables (EDITOR, VISUAL, GIT_EDITOR, PAGER etc.)
- write your own shell function like mem(), toggle_java8(), toggle_java17()

```bash
# file: ~/.zshrc  (selected parts from my .rc file)

# nice looking shell, e.g. enable true color support
export LC_ALL=en_US.UTF-8
export LANG=en_US.UTF-8
export TERM=xterm-256color

# environment variables, used by CLI tools like git
export EDITOR="emacsclient -t"
export VISUAL="emacsclient -c"
export GIT_EDITOR="emacsclient -c" # git will use now emacs for commit messages, you can replace this with your favorite editor
export PAGER=less

# aliases
alias e='emacsclient -t'  # emacs is my favorite editor, so the alias is very short :)
alias ecc='emacsclient -c'
alias ew='emacs -nw -q'
alias edd='emacs --daemon'
alias ag='ag --path-to-ignore ~/.agignore'
alias k='kubectl'         # same for k8s
alias kd='kubectl describe'
alias t='terraform'       # and terraform
alias gdiff='git diff --no-index'
```

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
