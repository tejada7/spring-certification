package com.ftm.vcp.bootexamples;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Four different types of web environments can be specified using the webEnvironment attribute of the @SpringBootTest annotation:
 * • MOCK - Loads a web ApplicationContext and provides a mock web environment. Does not start a web server.
 * • RANDOM_PORT - Loads a WebServerApplicationContext, provides a real web environment and starts an embedded
 * web server listening on a random port. The port allocated can be obtained using the @LocalServerPort annotation
 * or @Value("${local.server.port}"). Web server runs in a separate thread and server-side transactions will not be
 * rolled back in transactional tests.
 * • DEFINED_PORT - Loads a WebServerApplicationContext, provides a real web environment and starts an embedded web
 * server listening on the port configured in the application properties, or port 8080 if no such configuration exists.
 * Web server runs in a separate thread and server-side transactions will not be rolled back in transactional tests.
 * • NONE - Loads an ApplicationContext without providing any web environment.
 * @see <link>https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications</link>
 */
@SpringBootTest
class BootExamplesApplicationTests {

    @Test
    void contextLoads() {
    }

}
