---
title: "Install Postgres on Ubuntu"
date: 2019-07-26T10:15:34+01:00
tags: ["linux", "postgres"]
draft: false
---

Recently i decided to switch from MacOS to Linux. I was already an
Ubuntu user from 2006 till 2015. So i am used to Linux. Nevertheless i
struggled a little bit with the installation of
[PostgreSQL](https://www.postgresql.org). For MacOS there is a nice
all-in-one package which i recommend:
[Postgres.app](https://postgresapp.com). Everything works
out-of-the-box there. In Ubuntu, on the other hand, we need to adjust
the default installation a little bit in order to start application
development.

In this little guide i want to give you a short description how to
setup PostgreSQL for [Ubuntu 19.04](https://ubuntu.com) for
development. For the impatient i compiled all important commands at
the end of this article. So if you are already familiar with Linux you
can skip the main part.  Keep in mind this guide is not for production
usage!


## Installation instructions

First install the deb-package via apt-get:

```bash
sudo apt-get install postgresql
```

Afterwards PostgreSQL is available on your system. During the
installation a postgres OS-user with database admin permissions is
added to your system. And here is the problem.  For local users [peer
authentication](https://www.postgresql.org/docs/11/auth-peer.html) is
activated by default. This means Postgres relies on OS-users for
authentication. You have to issue all commands with the **postgres**
OS-user from the shell. For development purposes this is cumbersome
and you want a normal [user/password
authentication](https://www.postgresql.org/docs/11/auth-password.html).
Hence you need to add a password to your existing postgres user:

```
# login with the postgres OS-user (this user has db-admin permissions and can create new users and databases)
sudo -u postgres psql

# now you can add a password in the psql-shell
psql> \password postgres
```

Now you can switch from "peer" authentication to "md5" authentication
which is the Postgres version of password authentication.  Change all
occurrences of "peer" to "md5" in the "Method"-column in
[/etc/postgresql/11/main/pg_hba.conf](https://www.postgresql.org/docs/11/auth-pg-hba-conf.html). Your
**pg_hba.conf** file should look like this:

```bash
##### file: /etc/postgresql/11/main/pg_hba.conf #####

local   all             postgres             md5

# TYPE  DATABASE        USER  ADDRESS        METHOD

# "local" is for Unix domain socket connections only
local   all             all                  md5

# IPv4 local connections:
host    all             all   127.0.0.1/32   md5

# IPv6 local connections:
host    all             all   ::1/128        md5

# Allow replication connections from localhost, by
a user with the replication privilege.
local   replication     all                  md5
host    replication     all   127.0.0.1/32   md5
host    replication     all   ::1/128       md5
##### file: /etc/postgresql/11/main/pg_hba.conf #####

```

To activate the changes restart the postgres server:

```bash
sudo service postgresql restart
```

You are almost ready to go! Login with your new password:

```bash
psql -U postgres
# alternatively PGPASSWORD=<your_password> psql -U postgres
# -> this version is better for scripting

# create your application user with a password. Never use the admin user for application development!
psql> CREATE USER myuser WITH ENCRYPTED PASSWORD 'foobar';

# create a new database
psql> CREATE DATABASE mydb;

# add privileges to your new db-user, so he is allowed to create,change and delete tables etc.
psql> GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;
```

You can create your first table and run a sql-file:

```bash
PGPASSWORD=foobar psql -U myuser -d mydb -f create-tables.sql

# file: create-tables.sql
CREATE TABLE users (
       id serial PRIMARY KEY,
       firstname text,
       surname text,
       email text NOT NULL,
       age smallint
)
```

You made it! Finally you can start your application development with
your new and shiny PostgreSQL database. Do not forget this
configuration is for development purpose only. For production you
would use a cloud-provider DB-Service like [AWS
RDS](https://aws.amazon.com/rds/) or [Google Cloud
SQL](https://cloud.google.com/sql/) with different security settings.


## TL;DR

```bash
# install postgres
sudo apt-get install postgresql

# login with the postgres OS-user
sudo -u postgres psql

# add a password to the postgres user in the psql-shell
psql> \password postgres

# change /etc/postgresql/11/main/pg_hba.conf
# auth Method should be everywhere "md5", example file see above

# restart postgres in order to apply changes
sudo service postgresql restart

# login with the postgres user and create your application user and a new database with correct privileges
PGPASSWORD=<your_password> psql -U postgres
psql> CREATE USER myuser WITH ENCRYPTED PASSWORD 'foobar';
psql> CREATE DATABASE mydb;
psql> GRANT ALL PRIVILEGES ON DATABASE mydb TO myuser;

# create a table in your new database with the new user
> PGPASSWORD=foobar psql -U myuser -d mydb -f create-tables.sql

# file: create-tables.sql
CREATE TABLE users (
       id serial PRIMARY KEY,
       firstname text,
       surname text,
       email text NOT NULL,
       age smallint
)
```
