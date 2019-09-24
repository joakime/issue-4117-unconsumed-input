# Jetty / jersey / apache httpclient connection pooling problems

Simple spring boot + jersey + jetty projects with following versions:

* Spring Boot: 2.1.8.RELEASE
* Jetty: 9.4.19.v20190610 (via spring)
* Jersey: 2.27 (via spring)

## To reproduce:

* start server

    $ ./gradlew bootRun

* start client

    $ ./gradlew bootRun

* generate load on client

    $ ab -n 10000 -c 5 http://localhost:6800/v1/test

The client should receive multiple broken pipes during execution because the server jetty
closes the pooled connections after a few requests.

    [qtp801284613-18] o.apache.http.impl.execchain.RetryExec   : I/O exception (java.net.SocketException) caught when processing request to {}->http://localhost:6700: Broken pipe (Write failed)

With debug logging, the server projects jetty seems to think there is uncosumed input.

## Fixes / bandaids

### Enable filter

Uncomment `@Component` from the server projects `test.jetty.config.Filter`. This filter was a suggestion
from https://github.com/eclipse/jetty.project/issues/2867.

### Submit empty arrays from client to server

If you comment out

    params.add("ARR" + i);

from `test.jetty.rest.TestEndpoint` in the client project the problem goes away. Any array size
of >0 seems to cause the problem to happen.