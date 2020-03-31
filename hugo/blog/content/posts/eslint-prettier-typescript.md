---
title: "ESlint + Prettier + Typescript = Consistency"
date: 2020-03-24T10:28:27+01:00
tags: ["programming"]
draft: false
---


Consistency is one of the most important factors in a codebase. It
gives new developers an orientation and direction how to write new
code. It keeps the code understandable, clear and free from
bikeshedding, so developers can focus on real problems instead of
indulging in discussions on their preferred codestyle and individual
taste.

Consistency can be pertained by strict discipline but if possible you
should enforce good style and formatting via opinionated code
formatters like [Prettier](https://prettier.io) which makes
development and collaboration a breeze. One of the forerunners of code
formatters was [gofmt](https://golang.org/cmd/gofmt). Prior to gofmt
opinionated formatters were not so popular (at least not to my
knowledge). Basically gofmt defines the de facto Go formatting
style. The result of *gofmt*: almost all serious Go code is formatted
the same way. That's why all Go code looks familiar and is easy to
work with. With Prettier you can achieve the same "feeling" with
Javascript, Typescript and many other supported languages.

The same is true for linters. They help you to comply with common best
practices and to avoid potential problems. Linters and code formatters
are essential tools for **state-of-the-art software engineering**.


In this article I show you how to setup [ESlint](https://eslint.org/)
for [Typescript](https://www.typescriptlang.org/) with Prettier
support. So let's start.

First you need to install ESlint in your (Typescript) project:

```bash
# install eslint with typescript support
npm install --save-dev eslint @typescript-eslint/parser @typescript-eslint/eslint-plugin

# install prettier and eslint plugin with for prettier support
npm install --save-dev prettier eslint-config-prettier eslint-plugin-prettier
```

Next we have to configure ESlint so that it works together with
Prettier. Some ESlint rules are not compatible with the Prettier
formatting by default. ESlint is configured via two files
`.eslintrc.js` and `.eslintignore`. Both files need to be created in
your project root.

```javascript
// .eslintrc.js

// configures eslint rules
module.exports = {
  env: {
    node: true,
  },
  parser: "@typescript-eslint/parser",
  extends: [
    "plugin:@typescript-eslint/recommended", // recommended eslint rules for typescript
    "prettier/@typescript-eslint", // make eslint compatible with prettier formatting
    "plugin:prettier/recommended", // eslint shows prettier formatting warnings. Must be always the last configuration.
  ],
  parserOptions: {
    ecmaVersion: 2018,
    sourceType: "module",
  },
  plugins: ["@typescript-eslint"],
  rules: {},
};
```

```bash
# .eslintignore

# you should ignore irrelevant *.js and *.ts files which do not belong to you project

# do not lint node_modules/
node_modules
# do not lint build/ or dist/ folder
dist
# do not lint coverage/ folder
coverage
```

The Prettier configuration file is `.prettierrc`:

```json
{
  "printWidth": 80,
  "trailingComma": "all",
}
```

Here we define a maximum line length of 80. I am also an advocate of
trailing commas. This setting keeps the git diffs clean and as small
as possible. You can find more in-depth information about Prettier
configuration [here](https://prettier.io/docs/en/configuration.html).

With all this in place you can lint and format your code base:

```bash
# lint src files and list all errors
node_modules/.bin/eslint src/ --ext .ts

# same as above but fixes problems automatically if possible
node_modules/.bin/eslint src/ --ext .ts --fix

# format codebase in place
node_modules/.bin/prettier --trailing-comma all --write 'src/**/*.ts'
```

In the above code block, we had to use the path to the local
*node_modules/* folder, otherwise the ESlint and prettier binaries
would not be found. I recommend to install Prettier and ESlint as
global npm modules, then you can use them directly. This enables
editors to leverage prettier and ESlint too. You are then able to
incorporate both tools in your usual workflow. For example, i
configured my editor to show ESlint errors during typing and
formatting is done automatically when I save the current file.

``` bash
# install globally
npm install -g prettier eslint

# now you can use
prettier --trailing-comma all --write 'src/**/*.ts'
eslint src/ --ext .ts --fix
```

It's good practice to enforce these rules in order to preserve
consistency with a git pre-commit hook. Just install
[husky](https://www.npmjs.com/package/husky) and
[lint-staged](https://www.npmjs.com/package/lint-staged) for this
purpose. They facilitate the configuration of git hooks, so you do not
have to write the corresponding `pre-commmit` hook manually.

```bash
# install npm packages to manage git commit hooks
npm install --save-dev lint-staged husky
```

After the installation you need configure husky and lint-staged
modules in your local project's `package.json`. In this example, I run
`eslint --fix` and `prettier --write` during a pre-commit for all
changed files. This makes sure that no inconsistent code will ever be
committed to your codebase.

```json
{
  "scripts": {
    "lint": "eslint src/ --ext .ts",
    "lint-fix": "eslint src/ --ext .ts --fix",
    "pretty": "prettier --trailing-comma all --write 'src/**/*.ts'"
  },
  "dependencies": {
    ...
  },
  "devDependencies": {
     ...
  },
  "husky": {
    "hooks": {
      "pre-commit": "lint-staged"
    }
  },
  "lint-staged": {
    "src/**/*.ts": [
      "eslint --fix",
      "prettier --write"
    ]
  }
}
```

You can find a fully configured project template on my
[github](https://github.com/gerlacdt/graphql-example).


### Final words

By now, you went through a lot of project setup effort but you have a
stable scaffold on which you can build upon which can be used for your
future projects. I hope your codebase could benefit from the newly
gained consistency. Do not worry if you encounter hundreds of errors
or warnings the first time you run a linter on your project. This is
normal if you already have an existing project and never used static
code analyzers like linters before.

I can only emphasize once more that consistency is one of the most
important characteristics of a good codebase. Especially if your
project has multiple contributors but even if you work
alone. Consistency keeps the code maintainable and helps to keep up
high quality.

When I start working in a new team on a existing codebase, the first
thing I do is checking the codebase regarding consistency. Is the
formatting always the same? Is immutability preferred? Are threads or
asynchronous programming with callbacks used? Or promises? Or
*async-await*? Can I see re-emergent patterns? Are class, function and
variable names consistent? Is Domain Driven Design used?

The main point is that consistency should be apparent across the whole
codebase and that new team members are able to deduct how code should
look like. *The existing codebase acts like a model*. For example if
you are in doubt how to write a new controller with a repository, the
following question should be answerable from the existent code: Do
other controllers exist which act as a template?  Do all controllers
comply to the same patterns?  Do they have the same annotations?  Do
they use the same dependency injection methods like constructors,
setters, or field injection?  Are all controllers tested the same way?
Do controllers have the same tests like unit and component tests? If
the model is inconsistent or askew, new team members are left in
uncertainty. They never know if they comply to existing coding
guidelines because the guidelines are not visible in the source code
itself. Even if they are visible but there are exceptions, it is hard
for an outsider to decide what is right. In bad projects, i
encountered lengthy coding guidelines in Confluence and other wikis
but the real code diverged a long time ago from these written
guidelines. The best guidelines and rules are often deducible from the
existing code. Another thing you want to avoid due to lacking
consistency is time consuming and exhausting discussions in pull
requests. To avoid senseless work, be consistent all the time with
your rules and guidelines and stick to them forcefully. Whenever you
can enforce them or check them automatically, do it. It will save you
a lot of time.

I hope I could convince you that consistency matters. And that the
winning trio with ESlint, Prettier and Typescript improves your
codebase.
