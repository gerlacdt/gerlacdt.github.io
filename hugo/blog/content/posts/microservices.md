---
title: "Things to consider with microservices"
date: 2019-03-30T09:53:54+01:00
tags: ["programming", "microservices"]
draft: true
---

## Introduction

Some years ago i was involved in migrating a big IBM Websphere
monolith into a microservice landscape. We had a lot of problems with
the monolith. Our development speed slowed down. We had many merge
conflicts because of too many dependencies in the codebase. We outgrew
the monolithic design and decided to introduce microservices. We
extracted different domains like payment, booking, user and
search. The teams were restructured into two-pizza teams.

The whole migration was very structured and well planned. We migrated
incrementally with the [strangler
pattern](https://docs.microsoft.com/en-us/azure/architecture/patterns/strangler).
As long we were ~20 developers in 5 different teams, everything was
fine. Teams worked independently in their domain. They could scale,
deploy independently and followed their own release cycles.

But microservices bring new technical challenges. Maybe you know the saying:

> Microservices solve organizational problems.
>
> Microservices cause technical problems.

[Peter Bourgon](https://speakerdeck.com/peterbourgon/go-plus-microservices-equals-go-kit?slide=15)

Developers tend to ignore, forget or just simply do not know about
these challenges. They are overwhelmed by the new opportunities in the
shiny microservice world. In the following i want to present my
experience what we underestimated or forgot in our
microservice-migration.


## Avoid a heterogeneous IT-landscape

Microservices give you a lot of freedom which technology to choose
like programming language, database or web-framework. This is both
boon and bane. On the one side every team can choose the technology
which fits best, on the other side developers are adventurous and try
out new fancy programming languages and other bleeding edge
technology. And soon you have a dozen different tech stacks to
support. Some teams build their own tech-knowledge silos. And don't
get me wrong here, it is totally fine if a team has its
domain-knowledge silo, but it is not fine if the team is the only one
in the company who uses a fancy programming language.  At google, they
have thousands of engineers and they try to stick to only 4 languages
in the backend. The supported languages are C++, Java, Python and Go,
see [Software Engineering at Google, Fergus
Henderson](https://arxiv.org/pdf/1702.01715.pdf).  If a backend team
wants to start with another language they need to argument very hard
why they cannot fulfil their job with the company-wide supported
languages. There is a clear decision-process in place how to introduce
new a technology. This restricts the developers from using the newest
bleeding-edge technology.  Another advantage is that one can focus on
business problems and avoid discussions about tech exploration
etc. Your internal libraries need to be written and supported only in
a few languages. Developers will also have an easy time when they want
to switch teams. Team rotation is encouraged, facilitates
knowledge-transfer and pushes developers out of their comfort zone
which makes them more valuable.

A [technology radar](https://www.thoughtworks.com/de/radar) serves as
an overview for the supported tools, programming languages and
platforms. A radar helps to keep track of all technology used in your
company.


## Have a good Monitoring/Tracing/Logging in place

We suffered sometime from logging problems because our ~ElasticSearch
cluster was overloaded or some indices had conflicts. Logging is
hard. Make sure you have clear logging-guildlines. Do not log too
much, at best you have [silent
services](https://peter.bourgon.org/blog/2016/02/07/logging-v-instrumentation.html),
i.e. only log if a manual intervention is needed in case of an
error. Logging is fine for debugging and error tracking in the
development-Stage but it should not be overused in production. Be
careful if your monitoring and alarming is based on logging. If your
log-cluster is flooded, your monitoring will not work either!

Have strict rules for monitoring. No service should go into production
without monitoring. With microservices you need transparency in order
to do failure analysis or get notified if something is wrong. At least
monitor the [four golden
signals](https://landing.google.com/sre/sre-book/chapters/monitoring-distributed-systems/#xref_monitoring_golden-signals):

1. Latency. How high is the response time? Make sure you distinguish
   failed requests from successful requests. Failed requests can be
   fast and screw up your metrics.

2. Traffic. How many Requests per second (RPS) do we serve?

3.  Errors. How high is the error rate? Number of error response /
     Number of successful responses

4. Saturation. How full is your service? CPU utilisation, memory
    consumption.


## Don' forget the 8 fallacies of distributed computing

[Fallacies of distributed computing by Peter
Deutsch](https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing)

1. The network is reliable.

   Your HTTP-calls will fail from time to time. So make sure you have
   some retry-mechanism in place. But do not retry naively!
   E.g. retries make not sense if the http-response-code is 400
   BAD_REQUEST, no matter how often you try the request will never be successful.
   Neither you should do retries on POST requests, HTTP POST requests
   are by definition not idempotent and you could accidentally create a
   lot of resources like orders or trigger payments. Your customers will not
   be happy.

   You should also limit your retries, do not retry indefinitely,
   otherwise you can cause overload on other services, see
   [cascading failures](https://landing.google.com/sre/sre-book/chapters/addressing-cascading-failures/)

   Do not forget to protect your services with timeouts. Missing timeouts
   make I/O calls wait too long and if they pile up they consume all
   your memory or other resources like a thread pool. In the worst case you service will be killed by your docker scheduler because the memory limit is exceeded.

   For very high-load services you should consider [load shedding or
graceful degradation](https://landing.google.com/sre/sre-book/chapters/addressing-cascading-failures/#xref_cascading-failure_load-shed-graceful-degredation)
in order to protect these service from going down.

2. Latency is zero.

   Network can be slow. You should clearly separate internal function
   calls from network calls. E.g. do not use network calls in
   loops. Better fetch everything with one network call.

```javascript
// bad practice! Doing multiple network calls
function getUsers(userIds) {
  const users = [];
  for (id of userIds) {
    const user = http.fetchSingleUser(id);
    users.push(user);
  }
  return users;
}

// good, only one network call
function getUsers(userIds) {
  return http.fetchUsers(userIds);
}
```

3. Bandwidth is infinite.

   Use your bandwidth sparingly. E.g logging every http request and
   response is definitely too much. Do not use  sql-statements like this:
`SELECT * from  table`, always select only required columns.

4. The network is secure.

5. Topology doesn't change.

   Especially with self-healing services and docker random port usage,
   IP-addresses and ports change often. Do not use static IP-addresses
   or static ports. Use sophisticated service-discovery
   mechanisms. Many Docker-Scheduler provide them out-of-the-box like
   [Kubernetes](https://kubernetes.io/) or [Hashicorp's
   Nomad](https://www.nomadproject.io/). With [AWS
   ECS](https://aws.amazon.com/ecs/) you can use an [AWS Application
   Load Balancer](https://docs.aws.amazon.com/elasticloadbalancing/latest/application/introduction.html) (ALB) for service discovery.

6. There is one administrator.

   Distributed systems are complex. Do not expect that there is
   one person who knows it all. For bug analysis you need multiple
   people/teams.

7. Transport cost is zero.

   Network calls are expensive. You need to establish a connection,
   you need to serialize and deserialize the message body which cost
   CPU. The less you send the better. If you notice that you
   services are chatty, consider restructuring the domain
   boundaries. Most probably your domains do overlap or have multiple
   responsibilities.


8. The network is homogeneous

   It's not. Before docker, almost all applications ran on customized,
   "snowlflaky" servers configured via non-reproducible
   ssh-session. With Kubernetes or AWS ECS you can have clusters with
   thousands of servers which are configured exactly the same. A
   single ~DevOps guy can operate thousands of servers! You should
   also agree on a single data transfer format like
   [JSON](https://www.json.org/) or [Protocol
   Buffers](https://developers.google.com/protocol-buffers/) with
   [gRPC](https://grpc.io/). You should strive for as much homogeneity
   as possible to reduce operation and maintenance costs. In my
   opinion the homogeneity provided by Docker is its biggest
   advantage.


## Other things to consider

- Keep your domain boundaries clear and separated. Teams should always
  be able to deploy independently and without consulting other
  teams. You ever experienced a deployment where 3 or 4 services were
  involved and had to be deployed synchronously because of
  incompatibilities of API-versions or dependency on the same database.
  Congratulations you built a distributed monolith.

- Have a good CI/CD pipeline with automatic tests and deployments.
  Make sure all teams use the same
  deployment and glue scripts. Use one Docker-Cluster
  (Kubernetes, AWS ECS, Hashicorp Nomad) for the whole company (as
  long as you do not exceed hundreds of servers). This will keep the
  maintenance effort low.

- If you have reusable modules/libraries, make sure you have
  transitive CI-builds

- Adhere to good coding cloud-native practices, see
  [[12-factor-app|https://12factor.net]] and
  [[Site Reliability Engineering|https://landing.google.com/sre/books/]]


## Conclusion

Microservices are hard. Do not forget this. In the web you hear mainly
about the big success-stories like Google, Amazon and the like. But
for the most of us there are serious disadvantages which are normally
not mentioned. And do not get me wrong, microservices are great. It
was definitely the way to go for us. Today we are more than 100
developers and it gives me the creeps when i imagine that all of them
would work on single monolithic git-repository.  It is a long and hard
way to a productive microservice landscape but definitively worth
it. Just do not underestimate monitoring, automation and the fallacies
of distributed systems!
