---
title: "Production Readiness Checklist"
date: 2023-11-13T08:00:00+01:00
tags: ["programming", "softwareengineering", "devops"]
draft: false
---

Modern applications run typically in the cloud. As good residents, distributed
applications must fulfill many requirements in order to enable reliable
operations and maintenance. This article summarizes the most important points in
order to go live and to keep applications healthy over their lifetime.

### Twelve-factor app

All modern cloud applications should comply with the
[12-factor app](https://12factor.net/) principles:

1. **Codebase** should be tracking in a version control system, the same code
   will be deployed in different environments
1. **Dependencies** are shipped with the deployable artifact
1. **Backing Services** can be detached and re-attached without code changes
   (e.g. database, message broker)
1. **Config** remains in environment variables
1. **Separate stages** for Build, Release, Run
1. An application runs as a **single stateless process**. Data must be stored in
   a database.
1. **Port Binding**: applications export their service via a port binding
1. **Concurrency**, services are horizontally scalable
1. **Disposability**, applications are disposable, startup fast and support
   graceful shutdown (handles OS signals)
1. **Dev/Prod parity**, environments are as similar as possible
1. **Logs** are treated as a **continuous stream of events** (no log-file
   rotation)
1. **Admin processes**, e.g. database migrations are done as one-off admin
   process within the same environment as the long-running application

### Development

- [ ] build with one command, complex logic should be abstracted away inside a
      script
- [ ] integrate auto-formatting into the CI build
      ([spotless](https://github.com/diffplug/spotless/),
      [black](https://github.com/psf/black),
      [rustfmt](https://github.com/rust-lang/rustfmt),
      [gofmt](https://github.com/golang/go/tree/master/src/cmd/gofmt))
- [ ] integrate static-code analyzers, linters into the CI build
      ([errorprone](https://errorprone.info/), [infer](https://fbinfer.com/),
      [sonarqube](https://www.sonarsource.com/products/sonarqube/),
      [eslint](https://eslint.org/), [ruff](https://github.com/astral-sh/ruff),
      [clippy](https://github.com/rust-lang/rust-clippy),
      [go-staticcheck](https://staticcheck.dev/))

- [ ] integrate unit tests into the CI build

- [ ] add integration tests, load tests, only add minimalistic end-2-end tests
      because they are costly to maintain

- [ ] [**don't forget the fallacies of distributed systems**](https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing),
      e.g. networks are reliable (add retries with backoffs for idempotent
      requests, circuit breakers, load-shedding)

- [ ] know the application's resource limits - set them accordingly!

- [ ] add health and readiness checks to the applications

- [ ] use feature flags instead of spinning up whole environments
      (enable/disable features for a selected userbase)
- [ ] consider multi-tenancy from the start (regions, users)

### Deployment

- [ ] use a modern CI/CD pipeline with a simple branching strategy like
      [github flow](https://docs.github.com/en/get-started/quickstart/github-flow),
      avoid [gitflow](https://nvie.com/posts/a-successful-git-branching-model/)
- [ ] keep the CI pipeline simple, move complex logic into scripts

### Operations

- [ ] backup strategy for databases
- [ ] regular firedrills (restore backup)
- [ ] keep the number of environments low (preferably Dev and Prod)
- [ ] for an healthy DevOps culture, developers should do operations too. They
      should feel the pain during an outage. Afterwards they will write more
      robust code :smile:

### Infrastructure

- [ ] the whole infrastructure is available as code
      [IaC](https://en.wikipedia.org/wiki/Infrastructure_as_code)
      ([terraform](https://www.terraform.io/),
      [AWS CDK](https://aws.amazon.com/cdk/),
      [Azure Bicep](https://github.com/Azure/bicep))
- [ ] infrastructure is treated as normal code with the same process (kept in
      version control, pull requests, CI/CD pipelines)
- [ ] optionally, use GitOps tools ([flux](https://fluxcd.io/),
      [ArgoCD](https://argoproj.github.io/cd/))

### Observability

Keeping a comprehensible overview over distributed systems is much more complex
than with monolithic architectures. Therefore good observability is critical.

#### Logging

- [ ] log to STDOUT/STDERR
- [ ] _structured logging_ with JSON
- [ ] add _request-ids_ to all log-events
- [ ] logs are ephemeral - don't use logs as persistent data store!

#### Metrics

- [ ] track at least the four golden signals
- [ ] RED pattern (Request Rate, Error Rate, Duration)
- [ ] USE pattern (Utilization, Saturation, Errors)
- [ ] define
      [SLIs, SLOs, SLAs](https://sre.google/sre-book/service-level-objectives/)
      and monitor them
- [ ] alert on reasonable signals (don't over-alert - otherwise people will
      start to ignore the alerts)
- [ ] up-time availability checks
- [ ] dashboards for the most relevant metrics

#### Tracing

- [ ] track where distributed requests spend their time
      ([Datadog](https://www.datadoghq.com/),
      [OpenTelemetry](https://opentelemetry.io/), [Zipkin](https://zipkin.io/),
      [Jaeger](https://www.jaegertracing.io/))

### Documentation

Technical documentation is the gateway for a better understanding and helps to
build a mental model of the complex, intertwined parts of big distributed
systems. Good documentation is crucial for new team members and for your future
self.

- [ ] architecture diagrams visualize the IT landscape and help to create a
      mental model of the system
- [ ] `README.md` in root directory
  - project overview and purpose
  - development instructions (build commands, how to setup the project locally)
  - references to other helpful documentation and related git-repositories
- [ ] playbook/runbook with helpful instructions (how handle incidents, useful
      logging/metrics queries, dashboard links)
- [ ] architecture decisions records [ADRs](https://adr.github.io/)

### Costs

- [ ] tag infrastructure (project, department, team, contact persons, cost
      center)
- [ ] quick dashboard for monthly costs

# References

- [Github Production Readiness Checklist](https://github.com/kgoralski/microservice-production-readiness-checklist)
