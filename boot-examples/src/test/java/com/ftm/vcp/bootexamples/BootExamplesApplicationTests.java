package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.BootExamplesApplicationTests.TestConfig.Oauth2Client;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
import org.springframework.experimental.boot.test.context.DynamicProperty;
import org.springframework.experimental.boot.test.context.EnableDynamicProperty;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.assertj.core.api.BDDAssertions.then;

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
 *
 * @see <link>https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications</link>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DisplayNameGeneration(ReplaceUnderscores.class)
class BootExamplesApplicationTests {

    @Test
    void context_loads() {
    }

    @Test
    void authz_server_using_test_jars(@Autowired Oauth2Client oauth2Client) {
        then(oauth2Client.getMetadata()).isNotEmpty()
                .contains("issuer", "authorization_endpoint", "token_endpoint");
    }

    @TestConfiguration
    @EnableDynamicProperty
    static class TestConfig {

        @Bean
        @DynamicProperty(name = "spring.security.oauth2.resource-server.jwt.issuer-uri",
                value = "'http://localhost:' + port")
        static CommonsExecWebServerFactoryBean authzServer() {
            return CommonsExecWebServerFactoryBean.builder()
                    .mainClass("org.springframework.boot.loader.launch.JarLauncher")
                    .classpath(cp -> cp
                            .files("libs/authz-server-0.0.1-SNAPSHOT.jar")
                    );
        }

        @Bean
        Oauth2Client oauth2Client(OAuth2ResourceServerProperties resourceServerProperties) {
            final var factory = HttpServiceProxyFactory.builderFor(
                            RestClientAdapter.create(RestClient.builder()
                                    .baseUrl(resourceServerProperties.getJwt().getIssuerUri())
                                    .build()))
                    .build();
            return factory.createClient(Oauth2Client.class);
        }

        interface Oauth2Client {
            @GetExchange("/.well-known/openid-configuration")
            String getMetadata();
        }
    }
}
