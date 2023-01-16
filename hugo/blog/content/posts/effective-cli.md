---
title: "Effective CLI (more productive than an IDE!)"
date: 2023-01-16T11:00:00+02:00
tags: ["programming", "softwareengineering", "linux", "shell"]
draft: false
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
configuration, it is on par with modern graphical IDEs. Because it is
configurable, you can make it truly your own, thereby surpassing out-of-the-box
experience of IDEs. The _shell_ has existed for 50+ years, and since it's highly
adaptable, it's not going anywhere which makes it a great knowledge investment
for the future. No need to re-learn your toolset every few years. However, it
comes with a catch, a full _shell_ configuration takes time and effort. In this
article, I want to show you how I configured my _shell_ for ultimate
productivity, so you don't have to go same tiresome learning process as I did.

### Use a modern Terminal

A good _terminal application_ is the main entrance to your _shell environment_
and crucial a top-notch developer experience. Recommended terminals are:

- [Gnome Terminal](https://help.gnome.org/users/gnome-terminal/3.40/)
- [KDE Konsole](https://apps.kde.org/konsole/)
- [Alacritty](https://alacritty.org/) (minimal, blazing fast, written in Rust)
- [iTerm2](https://iterm2.com/) (MacOS)

For a modern look-and-feel, I suggest [Nerd Fonts](https://www.nerdfonts.com/).
It comes with icons for Kubernetes, Python, Java, Golang, Rust which pretty up
the CLI. For Ubuntu, you can install and enable Nerd Fonts with the following
commands:

```bash
# download a specific font (or choose your favorite font)
wget https://github.com/ryanoasis/nerd-fonts/releases/download/v2.2.2/DejaVuSansMono.zip

# unzip file
unzip DejaVuSansMono.zip

# move all files in fonts directory
mv *.ttf ~/.local/share/fonts/

# rebuild font cache, so that font will be available
fc-cache -fv

# afterwards you need to set the new font in you terminal app
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

Most Linux distribution use _bash_ as the default shell. We want to switch to
**zsh** which is more powerful than _bash_, but yet fully compatible to it.
Newer MacOS versions come with _zsh_ enabled. For Ubuntu you have to install
_zsh_:

```bash
# install zsh
sudo apt install zsh

# make zsh the default shell
chsh -s /usr/bin/zsh
```

### oh-my-zsh

With _zsh_ installed, we paved the way for our customization endeavours. We are
ready to install [oh-my-zsh](https://ohmyz.sh/), a delightful, extensible,
pre-configured _zsh environment_ with reasonable defaults. It builds the base
for further tailoring and provides a cheerful prompt with a lovely color theme.
_oh-my-zsh_ also defines a lot of
[useful aliases for git](https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins/git)
and [other CLI tools](https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins)
which save

The main zsh configuration file is `~/.zshrc`. There you can enable and disable
_oh-my-zsh_ plugins but also write your own aliases or activate subcommand
completions for `kubectl`, `git` or `terraform`. You can go even further and
write your own bash functions which are then available in you shell sessions.
For example, I use functions to toggle between different java versions
`toggle_java8` and `toggle_java17`. Below you find a small section of my
`~/.zshrc`:

```bash
# file: ~/.zshrc  (selected parts)

# oh-my-zsh location
export ZSH="$HOME/.oh-my-zsh"

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
complete -o nospace -C $HOME/.local/bin/terraform terraform

# example function which shows memory usage of a process
# USAGE:
# $> mem emacs # shows the memory consumption of the emacs process
mem()
{
    ps -eo rss,pid,euser,args:100 --sort %mem | grep -v grep | grep -i $@ | awk '{printf $1/1024 "MB"; $1=""; print }'
}
```

### starship

In the next step we configure our shell prompt. The default prompt only shows
you the current working directory. As a cloud infrastructure engineer though, I
work with _Git_, multiple K8s clusters, Azure Accounts and other different
contexts. For that reason, I like to have all such information in my shell
prompt. That proactively prevents executing commands in the wrong environment
:scream:.

The best prompt these days is [starship](https://starship.rs/). It's stable,
fast and easily configurable via a single `starhship.toml` file.

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
# starship even detects the programming language in your project directory
# you only need to add a section if you want to customize the defaults
```

My current prompt shows the current K8s cluster and namespace, the current git
branch, the current active Python and the Rust versions with icons:

<p align="left">
    <img src="/img/starship.png" alt="starship_shell_prompt" class="medium-zoom-image">
</p>

### Modern CLI commands

We already made it very far and your terminal experience should have improved a
lot by now. To get most out of the _shell_, you should master the pre-installed
tools like `find`, `grep`, `sed`, `ls`, `cd`, `cat`, `less` etc. Additionally to
the classic tools, modern alternatives exist which provides a contemporary user
experience. Below you can find my favorite tools which have the biggest impact
on my daily workflow:

- [fzf](https://github.com/junegunn/fzf), an interactive shell command history
  search tool on steroids, it completely changed the way how I use the shell
  command history with `CTRL-r`
- [delta](https://dandavison.github.io/delta/), a much nicer git diff, _delta_
  not only highlights which lines changed but also the exact location in a
  specific line
- [ripgrep](https://github.com/BurntSushi/ripgrep) or
  [ag](https://github.com/ggreer/the_silver_searcher), both are `grep`
  alternatives, they are faster and provide a more convenient API, e.g they
  ignore common folders by default like `.git` or `node_modules`

- [bat](https://github.com/sharkdp/bat), a `cat` alternative with syntax
  highlighting and pager features
- [zoxide](https://github.com/ajeetdsouza/zoxide), a smarter way to change
  directories, _zoxide_ remembers nested paths and makes switching directories a
  breeze

- [tmux](https://github.com/tmux/tmux/wiki) or
  [zellij](https://zellij.dev/documentation) are terminal multiplexers. If you
  run long running terminal sessions or you work on remote machines, these tools
  are highly recommended
- [kubectx/kubens](https://github.com/ahmetb/kubectx), `kubectl` commands can
  get very long, with kubectx/kubens you can pin the cluster and namespace for
  all future commands which saves a lot of typing

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
great facilitation if you work often with Kubernetes.

#### Shell command tips and tricks

The shell is a whole universe and it takes time to get familiar with it.
Eventually, it is a rewarding experience which will be beneficial for the rest
of your life. Unfortunately, there is no shortcut for learning the CLI. Anyway,
I want to show you my most useful commands in order to give you an head start:

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

Fist of all, I don't want to trigger another
[_editor war_](https://en.wikipedia.org/wiki/Editor_war). Everyone can use the
editor she prefers and makes her most productive.

Nevertheless, one's editor is the main workhorse for developers who spend many
hours per day in it. Thus a good editor is essential. For me a good editor must
be able to run inside the shell, so I don't need to switch between applications
during my work session. Besides that, an editor should be fast. Contemporary
editors should offer an instant experience opening and changing files. If you
want to go down the rabbit whole, you can also start configuring your editor to
your needs. My **opinionated** editor recommendations are:

#### [Emacs](https://www.gnu.org/software/emacs/)

My favorite editor. I use it for almost everything: programming, note taking,
visual git user interface, writing etc. It's extremely configurable via
[Emacs Lisp](https://www.gnu.org/software/emacs/manual/html_node/eintr/).
Sophisticated, stable plugins exist for every conceivable scenario, for example
LSP support, Tree-sitter, Git etc. I run the awesome
[Emacs Prelude](https://github.com/bbatsov/prelude) distribution. It builds the
foundation for my own modifications.

#### [Neovim](https://neovim.io/)

If you don't like Emacs, Neovim is an alternative on eye level. As a member of
the VIM family, Neovim provides the classic modal-editing experience. Neovim is
also highly configurable via Lua. You can extend the editor via plugins, write
your own config or just use a pre-configured distribution like
[AstroNvim](https://astronvim.github.io/) which gives you an IDE-like feeling
without much tinkering.

#### [Helix](https://helix-editor.com/)

Helix is a blazing fast terminal editor written in
[Rust](https://www.rust-lang.org/). Syntax-Highlighting and LSP support works
out-of-the-box. I use it for short edits and Rust development. The great thing
about Helix is that you get 80% of the Emacs or Neovim features with almost no
customization effort.

A normal Emacs or Neovim config consists of thousand lines of code. In contrast,
this is my complete helix configuration:

```toml
theme = "onedark"

[editor]
line-number = "relative"
mouse = false

[editor.cursor-shape]
insert = "bar"
normal = "block"
select = "underline"

[editor.file-picker]
hidden = false

[editor.auto-pairs]
'(' = ')'
'{' = '}'
'[' = ']'
'"' = '"'
'`' = '`'
'<' = '>'

```

### GNU Stow

Although we are done with the shell configuration, we have now a lot of dotfiles
lying around in the `$HOME` directory. A good way to manage them would be `git`.
Unluckily, we cannot put our `$HOME` directory in a git repository. Here
[GNU Stow](https://www.gnu.org/software/stow/) comes into play. _GNU Stow_ uses
symbolic links to manage a group of dotfiles inside a single folder. This is
exactly what we want. Let's go through a simple example:

```bash
# create dotfiles folder somewhere
mkdir $HOME/dotfiles

cd dotfiles/

# move our .gitconfig into the new dotfiles/ folder
mkdir git
mv $HOME/.gitconfig git/

# inside dotfiles/, it should look like this:
tree git -a
git
└── .gitconfig

# lets create a symlink with GNU Stow
stow --verbose --target=$HOME git

# .gitconfig is now available at $HOME/.gitconfig

# deleting the symbolic link is also possible
stow --verbose --target=$HOME --delete git
```

If you want to learn how to symlink whole nested directory structures or just
dive deeper, you can look into this great
[step-by-step guide](https://venthur.de/2021-12-19-managing-dotfiles-with-stow.html).

### Conclusion

The shell is ultimately customizable - use it to your advantages. It will make
you more productive and work will be more enjoyable. Thereby the configuration
process is never ending, you can always squeeze out more and optimize your
environment. I hope this article gave you some valuable insights and ideas how
to improve your productivity. And maybe you open your IDE less often
:upside_down:

### References

[The Unix Programming Environment](https://www.cs.princeton.edu/~bwk/upe/upe.html)
