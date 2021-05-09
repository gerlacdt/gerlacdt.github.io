---
title: "Database Integration Tests with Docker Sidecar in Jenkins"
date: 2021-05-09T08:00:00+02:00
tags: ["programming", "testing", "docker", "jenkins"]
draft: false
---

More often than not, developers want to test the source code against a
real database instead of Fakes or Test Doubles during a CI job in
order to verify the code works as expected. Therefore a clean database
is needed for every CI-job. One solution is to use a shared instance
of the database in your existing test-environment which get cleaned
before and after a job. But this suffers from being "shared" and you
never can run multiple CI-jobs in parallel without jobs interfering
with each other.

A much better solution is to use the
[Sidecar-Pattern](https://docs.microsoft.com/en-us/azure/architecture/patterns/sidecar)
with [Docker](https://docs.docker.com/).

A sidecar is a specific docker container starting with the main CI-job
and provides specific functionality like a fresh database during the
test run. In a `Jenkinsfile` this is accomplished with:


`docker.image(<imagename>.withRun(<docker run parameters>) {<run your
tests>}`

For more info about Docker, sidecars and Jenkins, see [Use Docker in
Jenkins
Pipelines](https://www.jenkins.io/doc/book/pipeline/docker/#running-sidecar-containers)

A full example of a Jenkinsfile with a Postgres-DB sidecar container:

``` groovy
// inside Jenkinsfile

       stage('Build and Unit Tests') {
            steps {
                script {
                    docker.image('postgres:12.6')
                          .withRun('-P -v `pwd`/scripts/sql_init:/docker-entrypoint-initdb.d -e POSTGRESQL_PASSWORD=postgres') { c ->

                        // get random port from postgres-docker image
                        // random ports are important for running multiple jobs in parallel because the jobs must not use the same port on the host
                        def port = sh(returnStdout: true, script: "docker inspect --format='{{(index (index .NetworkSettings.Ports \"5432/tcp\") 0).HostPort}}' ${c.id}").trim()

                        // Wait until postgres is up
                        sh "while ! curl http://localhost:${port}/ 2>&1 | grep '52'; do echo 'waiting for postgres'; sleep 1; done;"

                        // access DB on localhost:${port}
                        // e.g. run some database migrations
                        sh "mvn -Dflyway.user=springboot -Dflyway.password=springboot \
-Dflyway.url=jdbc:postgresql://localhost:${port}/<dbname> flyway:migrate"


                        // or run some tests
                        sh "SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:${port}/<dbname> mvn test"
                    }
                }
            }
        }
```


This pattern is so successful that many libraries exist using this
pattern in order to provide Databases, Message Brokers and even Web
Browser as sidecars, e.g the Java library
[TestContainers](https://www.testcontainers.org/).
