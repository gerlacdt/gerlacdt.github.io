---
title: "ESlint + Prettier + Typescript = Consistency"
date: 2020-03-24T10:28:27+01:00
tags: ["programming"]
draft: true
---


Consistency is one of the most important factors in a codebase. It
gives new developers an orientation and direction how to write new
code, it keeps the code understandable, clear and free from
bikeshedding, so developers can focus on real problems instead of
indulging in discussions of their preferred codestyle and individual
taste.

Consistency can be pertained by strict discipline or you can enforce
good style and formatting via opinionated code formatters like
[prettier](https://prettier.io) which makes development and
collaboration a breeze. One of the forerunners of code formatters was
[gofmt](https://golang.org/cmd/gofmt). Prior to gofmt opinionated
formatters were not so popular (at least not to my
knowledge). Basically gofmt defines the de facto Go formatting
style. Almost all serious Go code is formatted that way. That's why
all Go code looks familiar and is easy to work with. With prettier you
can achieve the same "feeling" with Javascript, Typescript and many
other supported languages. The same is true for linters. They help you
to stick to common best practices and to avoid silly and potential
problems. Long story short, linters and code formatters are essential
tools for ''state-of-the-art software engineering''.


In the article i show you how to setup [eslint](https://eslint.org/)
for [Typescript](https://www.typescriptlang.org/) with prettier
support. So let's start.

First you need to install eslint in your (Typescript) project:

```bash
# install eslint with typescript support
npm install --save-dev eslint @typescript-eslint/parser @typescript-eslint/eslint-plugin

# install prettier and eslint plugin with for prettier support
npm install --save-dev prettier eslint-config-prettier eslint-plugin-prettier
```

Next we have to configure eslint so that it works together with prettier. If you don't do that, eslint will not be compatible with the opinionated prettier formatting. The eslint configuration is done via two files `.eslintrc.js` and `.eslintignore`. You need both files in your project root.

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
# eslintignore

# you should ignore irrelevant *.js and *.ts files which do not belong to you project

# do not lint node_modules/
node_modules
# do not lint build/ or dist/ folder
dist
# do not lint coverage/ folder
coverage
```

The prettier configuration needs to be created in `.prettierrc`:

```json
{
  "printWidth": 80,
  "trailingComma": "all",
}
```

Here we define a max line length of 80. I am also an advocate of trailing commas. This setting keeps the git diffs clean and as small as possible.

With all this in place you can lint and format your code base:

```bash
# lint src files and list all errors
node_modules/.bin/eslint src/ --ext .ts

# same as above but fixes problems automatically if possible
node_modules/.bin/eslint src/ --ext .ts --fix

# format codebase in place
node_modules/.bin/prettier --trailing-comma all --write 'src/**/*.ts'

# i used above the full path to the local /node_modules folder
# you can also install prettier and eslint as global npm modules, then you can use them directly
# this is recommended so editors can also leverage prettier and eslint

# install globally
npm install -g prettier eslint

# now you can use
prettier --trailing-comma all --write 'src/**/*.ts'
eslint src/ --ext .ts --fix
```

It's good practice to enforce these practices. You can do this with a git pre-commit hook. Just install [[husky|https://www.npmjs.com/package/husky]] and [[lint-staged|https://www.npmjs.com/package/lint-staged]]. With these packages it is easy to configure the hooks.

```bash
# install npm packages to enable pre-commit hooks
npm install  --save-dev lint-staged husky
```

After the installation you need configure husky and lint-staged modules in the `package.json`.

```json
{
  "scripts": {
    "lint": " eslint src/ --ext .ts",
    "lint-fix": " eslint src/ --ext .ts --fix",
    "pretty": "prettier --trailing-comma all --write 'src/**/*.ts'"
  },
  "dependencies": {
    ....
  },
  "devDependencies": {
     ....
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
