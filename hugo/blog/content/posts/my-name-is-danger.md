---
title: "My Nickname Is Danger"
date: 2019-03-15T08:51:40+01:00
tags: ["javascript"]
draft: true
---

A few years ago one of my colleagues "discovered" an algorithm which
led to my nickname:

* take the 3 first characters of my first name and surname
* concatenate them: DANiel + GERlach => Danger

The algorithm can be implemented as a **pure function** in Javascript:

```javascript
function getNickname(fullname) {
  return fullname
    .split(/\s+/)
    .map(n => n.substring(0,3))
    .join('');
}

const result = getNickname('Daniel Gerlach');
console.log(result);   // => DanGer
```
