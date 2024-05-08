Analytical Data Query Service:

This service is used to query analytical data in paginated way using Restful API convention from relational database(Postgresql).
Application service/code is stateless with db connection as stateful.
After particular limit we can't vertically scale Postgres to any limit then either using tools like Vitess we can horizontally scale or
we can move to more scalable database apache druid cluster.

As of now i have created a single container instance of db for high availability we need to setup Leader replica(Master Replica) Architecture.

Also there is no authorization and authentication setup for this so we need some kind of API Gateway for authentication and service routing
if this is only service exposed as API then we can provide authentication and authorization in this service as well



To provide rest api's documentation we can use swagger

Prerequisites for running this service:
Postgresql should be running locally on port 5432 with druid as schema/database name with username and passwd druid and FoolishPassword resp.
if you try to setup postgresql on remote server then we need to spring.datasource properties in application.properties


How to test this service using rest API's ?
I will be adding sample testcases screenshot in resources/test-screenshots folder
But port number is different in different screenshots because of dynamic port selection:
As i was executing on remote server from IDE.

