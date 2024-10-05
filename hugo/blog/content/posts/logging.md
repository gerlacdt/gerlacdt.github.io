---
title: "Logging, the sensible defaults"
date: 2024-10-04T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

- aggregate logs in a central logging system (don't look into app-process to
  investigate logs) make logs queryable
- use structured logging, best a unified structure (JSON with pre-defined
  required fields) correlate logs with Request-ID, code example log entry
- silent logs 11. Rule of Silence: When a program has nothing surprising to say,
  it should say nothing.
  [unix philosophy](http://www.catb.org/esr/writings/taoup/html/ch01s06.html)

- log errors with as most context as possible (stracktrace, information like
  user, transaction-id and other context information)

1. DO NOT log debug in production
1. DO NOT use logs as persistent data
1. DO NOT log sensitive data

## References

- https://betterstack.com/community/guides/logging/logging-best-practices/
- https://daily.dev/blog/12-logging-best-practices-dos-and-donts
- https://12factor.net/logs
