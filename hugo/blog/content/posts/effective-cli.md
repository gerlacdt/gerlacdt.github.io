---
title: "Effective CLI (more productive than an IDE!)"
date: 2023-01-14T11:00:00+02:00
tags: ["programming", "softwareengineering", "linux", "shell"]
draft: true
---

## TL;DR

1. Modern Terminal with nice (Nerd) Fonts
1. ZSH
1. oh-my-zsh
1. Modern Prompt with starship
1. Modern CLI tools (fzf, ripgrep, bat, tmux, jq)
1. Fast editor (Emacs, Neovim, Helix)
1. Organize your dotfiles with GNU Stow

The _Unix shell/Command-Line Interface (CLI)_ is an ancient tool. Despite being
around forever, the _shell_ is highly customizable and with a good
configuration, it is on par with modern graphical IDEs. Because it is so
configurable, you can make it truly your own, thereby surpassing out-of-the-box
experience of IDEs. The _shell_ has existed for 50+ years, and since it's so
adaptable, it's not going anywhere which makes it a great knowledge investment
for the future. No need to re-learn your editing toolset every few years.
However, it comes with a catch, a full _shell_ configuration takes time and
effort. In this article, I want to show you how I configured my _shell_ for
ultimate productivity, so you don't have to go same tiresome learning process as
I did.

### Use a modern Terminal

All starts with a good _terminal application_ which is the door to your _shell
environment_. A good terminal is crucial for a top-notch developer experience.
Recommended terminals are:

- [Gnome Terminal](https://help.gnome.org/users/gnome-terminal/3.40/)
- [KDE Konsole](https://apps.kde.org/konsole/)
- [Alacritty](https://alacritty.org/) (minimal, blazing fast, written in Rust)
- [iTerm2](https://iterm2.com/) (MacOS)

The standard MacOS terminal app does not offer TrueColor support and is not
suitable.

For a modern look-and-feel, I suggest [Nerd Fonts](https://www.nerdfonts.com/).
It comes with icons for Kubernetes, Python, Java, Golang, Rust which pretty up
the CLI. Install Nerd Fonts in Ubuntu with:

```bash
# download a specific font (or choose your favorite font)
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

Congrats, we have configured our terminal. Most Linux distribution come with
_bash_ as the default shell, but we want to switch to **zsh** which is more
powerful than _bash_, but yet fully compatible to it. For newer MacOS versions
_zsh_ is the default shell. For Ubuntu you have to install _zsh_:

```bash
# install zsh
sudo apt install zsh

# make zsh the default shell
chsh -s /usr/bin/zsh
```

### oh-my-zsh

With _zsh_ installed, we paved the way for our customization endeavours. Now we
can install [oh-my-zsh](https://ohmyz.sh/), a delightful, extensible,
pre-configured _zsh environment_ with sane defaults. It builds the base for
further tailoring and provides a cheerful prompt with a nicer color theme.
_oh-my-zsh_ predefines a lot of
[useful aliases for git](https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins/git)
and [other CLI tools](https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins).

The main zsh configuration file is `~/.zshrc`. There you can enable and disable
_oh-my-zsh_ plugins but also write your own aliases or activate subcommand
completion for `kubectl`, `git` or `terraform`. You can go even further and
write your own bash functions which are then available in you shell sessions.
For example, I use a function to toggle between different java versions
`toggle_java8` and `toggle_java17`. Below you find a small section from my
`.zshrc`:

```bash
# file: ~/.zshrc  (selected parts from my .rc file)

# oh-my-zsh location
export ZSH="/home/gerlacdt/.oh-my-zsh"

# default oh-my-zsh prompt
ZSH_THEME="robbyrussell"

# enable plugins which provide aliases etc.
plugins=(git)

# enable oh-my-zsh
source $ZSH/oh-my-zsh.sh

# general stuff
export LC_ALL=en_US.UTF-8 # set default language settings
export LANG=en_US.UTF-8
export TERM=xterm-256color # enable true color support

# environment variables, used by CLI tools like git
export EDITOR="emacsclient -t"
export VISUAL="emacsclient -c"

# git will use emacs for commit messages, you can replace this with your favorite editor
export GIT_EDITOR="emacsclient -c"
export PAGER=less  # used by git commands in order to print git log, git diff outputs

# aliases
alias e='emacsclient -t'  # emacs is my favorite editor, so the alias is very short :)
alias ecc='emacsclient -c' # use emacs server instead of starting a new emacs process
alias ew='emacs -nw -q' # start clean emacs without loading any configuration
alias edd='emacs --daemon' # start emacs server in background -> instant emacs startup time
alias ag='ag --path-to-ignore ~/.agignore'
alias k='kubectl'         # short k8s commands
alias kd='kubectl describe'
alias t='terraform'       # short terraform commands
alias gdiff='git diff --no-index' # use git diff for non-versioned files

# kubectl subcommand completion
source <(kubectl completion zsh)

# terraform subcommand completion
autoload -U +X bashcompinit && bashcompinit
complete -o nospace -C /home/gerlacdt/.local/bin/terraform terraform

# example function which shows memory usage of a process
# USAGE:
# $> mem emacs # shows the memory consumption of the emacs process
mem()
{
    ps -eo rss,pid,euser,args:100 --sort %mem | grep -v grep | grep -i $@ | awk '{printf $1/1024 "MB"; $1=""; print }'
}
```

### starship

In the next step we configure our shell prompt. By default the prompt only shows
you the current working directory. _oh-my-zsh_ adds the current git branch. As a
cloud infrastructure engineer I work with multiple Kubernetes clusters and Azure
Accounts. I like to have such information directly in my shell, so that I don't
coincidentally execute commands in the wrong environment :scream:.

The best prompt, in my opinion, is [starship](https://starship.rs/). It's
stable, fast and easily configurable via a single `starhship.toml` file.

```bash
# file ~/.zshrc

# prerequisite: install starship, see installation instructions at webpage

# enable starship by putting this line at the end of the file
eval "$(starship init zsh)"
```

```toml
# file ~/.config/starship.toml

# set the color for current working directory
[directory]
style = "cyan bold"

# configure k8s context: the current cluster and the namespace will be shown in green
[kubernetes]
format = '[☸ ($cluster)\($namespace\)](green) '
disabled = false

# contexts for git, Azure/AWS/GCP are dected automatically
# starship even detects the progrmamming language in your project directory
# you only need to add a section if you want to customize the defaults

```

My current prompt shows the local K8s cluster with the corresponding namespace,
the current git branch, the current active Python and the Rust versions with
icons:

<p align="left">
    <img src="/img/starship.png" alt="starship_shell_prompt" class="medium-zoom-image">
</p>

### Modern CLI commands

We already made it very far and your terminal experience should have improved a
lot by now. For being productive in the terminal you must master the
pre-installed tools like `find`, `grep`, `sed`, `ls`, `cd`, `cat`, `less` etc.
Additionally to the classic tools, there exist modern alternatives which
provides a contemporary user experience. I want to present the ones I used the
most and which have the biggest impact on my daily workflow:

- [fzf](https://github.com/junegunn/fzf), an interactive shell command history
  search tool on steroids, it completely changed the way how I use `CTRL-r`
- [delta](https://dandavison.github.io/delta/), a much nicer git diff, it not
  only highlights which line changed but also the word location in the line
- [ripgrep](https://github.com/BurntSushi/ripgrep) or
  [silver searcher ag](https://github.com/ggreer/the_silver_searcher), both are
  `grep` alternatives, they are faster and provide a convenient API

- [bat](https://github.com/sharkdp/bat), a `cat` alternative with syntax
  highlighting and pager features
- [zoxide](https://github.com/ajeetdsouza/zoxide), a smarter way to change
  directories, zoxides remembers nested paths and you can easily switch into
  them with a few keystrokes

- [tmux](https://github.com/tmux/tmux/wiki) or
  [zellij](https://zellij.dev/documentation) are terminal multiplexers. If you
  want to run long running terminal sessions or you work on remote machine,
  these tools are highly recommended.
- [kubectx/kubens](https://github.com/ahmetb/kubectx), `kubectl` commands can
  get very long, with kubectx/kubens you can pin the cluster and namespace for
  all future commands which saves a lot of typing.

#### jq / yq

As a developer, you often consume JSON Rest-APIs. Writing a test for
experimenting in your programming language can be cumbersome. A faster way is
using `curl` and `jq`:

```bash
# curl | jq example

# curl makes an HTTP request

# jq consumes the response, formats it and provides mapping and filtering features for inspection
curl -s "https://jsonplaceholder.typicode.com/todos" \
| jq ".[]| {new_id: .id, new_title: .title}"
```

[yq](https://github.com/mikefarah/yq) is the same as `jq` but for yaml files. A
great facilitation if you work often with Kubernetes. `yq` can also convert json
to yaml.

#### Shell command tips and tricks

The shell is a whole universe and it takes time to get familiar with it. But
eventually, it is a rewarding experience which will be beneficial for the rest
of your life. Unfortunately, there is no shortcut of learning the CLI but in
then ext code block, I want to show you the commands I used the most to give you
a headstart:

```bash
# run you last commands again with !!
ls -l
!!

# goto your $HOME directory
cd

# jump back to your last directory
cd -

# use the shell history with CTRL-r, fzf will give you a superior experience

# search-replace all occurences in all rust files recusively
find . -iname "*.rs" | xargs sed -i "s/mod/foobar/g"

# for MacOS, it's a bit different since MacOS does not use GNU Tools but the BSD Tools
find . -iname "*.rs" | xargs sed -i "" "s/mod/foobar/g"

# combine search-replace with git, i.e.
# 1. start from a clean git commit
# 2. execute search-replace
# 3. check the changes with git diff
# 4. if you don't like the result, do a git reset

# CTRL-k delete to the end of line

# CTRL-u delete line

# CTRL-a jump to start of line

# use TAB to trigger auto-completion

# cycle through all commands with CTRL-p and CTRL-n
```

### Your Editor, choose wisely

Fist of all, I don't want to trigger another _editor war_. Everyone can use
which editor she prefers and make her most productive.

Anyway, the editor is the main workhorse for developers and they spend many
hours per day in it. Thus a good editor is essential. For me a good editor must
be able to run inside the shell, so I don't need to switch between applications
during my work session. Besides that, an editor should be fast. Contemporary
editors should offer an instant experience opening and changing files. If you
want to go down the rabbit whole, you can also start configuring your editor to
your needs. My **opinionated** editor recommendations are:

#### [Emacs](https://www.gnu.org/software/emacs/)

My favorite editor. I used it for almost everything: programming, note taking,
visual git user interface, writing etc. It's extremely configurable via
[Emacs Lisp](https://www.gnu.org/software/emacs/manual/html_node/eintr/).
Sophisticated, stable plugins exist for every conceivable scenario, for example
LSP support, Tree-sitter, Git etc.

#### [Neovim](https://neovim.io/)

If you don't like Emacs, Neovim is an alternative on eye level. As a member of
the VIM family, Neovim provides the classic modal-editing experience. Neovim is
also highly configurable via Lua. You can extend the editor via plugins, write
your own config or just use a pre-configured distribution like
[AstroNvim](https://astronvim.github.io/) which gives you an IDE-like feeling
without much config tinkering.

#### [Helix](https://helix-editor.com/)

Helix is a blazing fast terminal editor written in
[Rust](https://www.rust-lang.org/). Syntax-Highlighting and LSP support works
out-of-the-box. I use it for short edits and Rust development. The great thing
about Helix is that you get eighty percent of the Emacs or Neovim user
experience with almost no customization effort.

### gnu stow

Manage dotfiles in github repository

https://venthur.de/2021-12-19-managing-dotfiles-with-stow.html
https://www.jakewiesler.com/blog/managing-dotfiles
https://dr563105.github.io/blog/manage-dotfiles-with-gnu-stow/

### Conclusion

The shell is ultimately customizable - use it to your advantages. It will make
you more productive and work will be more enjoyable. Thereby the configuration
process is never ending, you can always squeeze out more and optimize your
environment. I hope this article gave you some valuable insights and ideas how
to improve your productivity. And maybe you open your IDE less often
:upside_down:
