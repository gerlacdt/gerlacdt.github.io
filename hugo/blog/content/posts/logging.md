---
title: "Logging, the sensible defaults"
date: 2024-12-20T09:00:00+01:00
tags: ["programming", "softwareengineering"]
draft: true
---

Logging plays an essential part of well-designed applications. It is a major
tool for failure analysis and debugging. Good logging can make up the difference
between good and bad observability. Nonetheless, I often encounter codebases
that completely miss a concrete logging strategy. This manifests in useless
logging missing essential information or in over-logging thereby bringing down
whole systems - logging is expensive. In the following I want to summarize the
good most important logging traits.

- Logs are a stream of text formatted events, most often in STDOUT
- one log event corresponds to one line (never use multi-line logs)
- use structured logging, best a unified structure (JSON with pre-defined
  required fields) correlate logs with Request-ID, code example log entry
- use appropriate log-levels, prefer silent logs
  [unix philosophy](http://www.catb.org/esr/writings/taoup/html/ch01s06.html)

  > 11. Rule of Silence: When a program has nothing surprising to say, it should
  >     say nothing.

- log errors with as most context as possible (stracktrace, context information
  like user-id, transaction-id, request-ids are very important in order to
  collect all relevant logs for a HTTP-request that can span over multiple
  services.
- aggregate logs in a central log indexing system, e.g. ElasticSearch or Datadog
  (don't look into app-process to investigate logs), such a system must support
  sophisticated queries based on time, keywords, tags, request-ids and other
  discriminators, logs with good context are easily queryable

#### No Gos

- DO NOT log debug in production, the sheer log volume can bring down whole
  systems. Worse yet, with too much you kill you log indexing system and with it
  your possibility to debug!
- DO NOT log sensitive data, user-related data and secret data like passwords
  etc. must not be logged
- DO NOT use logs as persistent data

## References

- [Twelve Factor App](https://12factor.net/logs)
