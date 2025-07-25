package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.BootExamplesApplicationTests.TestConfig.Oauth2Client;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.experimental.boot.server.exec.CommonsExecWebServerFactoryBean;
import org.springframework.experimental.boot.test.context.EnableDynamicProperty;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.experimental.boot.server.exec.MavenClasspathEntry.springBootStarter;

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
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.testcontainers.dynamic-property-registry-injection=warn"
)
@DisplayNameGeneration(ReplaceUnderscores.class)
@Import(BootExamplesApplicationTests.TestConfig.class)
class BootExamplesApplicationTests {

    @Test
    void context_loads() {
    }


    @Nested
    @TestPropertySource(properties = "oauth.authz.server.test.source=custom-jar")
    class UsingCustomJar {
        @Test
        void authz_server_using_test_jars_from_a_custom_jar(@Autowired Oauth2Client oauth2Client) {
            then(oauth2Client.getMetadata()).isNotEmpty()
                    .contains("issuer", "authorization_endpoint", "token_endpoint");
        }
    }

    @Nested
    @TestPropertySource(properties = "oauth.authz.server.test.source=sb-auto-started")
    class UsingSpringBootAutoConfigJar {
        @Test
        void authz_server_using_test_jars_from_spring_boot_auto_configuration(@Autowired Oauth2Client oauth2Client) {
            then(oauth2Client.getMetadata()).isNotEmpty()
                    .contains("issuer", "authorization_endpoint", "token_endpoint");
        }
    }

    @TestConfiguration(proxyBeanMethods = false)
    @EnableDynamicProperty
    static class TestConfig {

        @ConditionalOnProperty(name = "oauth.authz.server.test.source", havingValue = "custom-jar")
        @Bean
        @OAuth2ResourceServerProviderIssuerUri
        static CommonsExecWebServerFactoryBean authzServerFromJar() {
            return CommonsExecWebServerFactoryBean.builder()
                    .mainClass("org.springframework.boot.loader.launch.JarLauncher")
                    .classpath(cp -> cp
                            .files("libs/authz-server-0.0.1-SNAPSHOT.jar")
                    );
        }

        @ConditionalOnProperty(name = "oauth.authz.server.test.source", havingValue = "sb-auto-started")
        @Bean
        @OAuth2ResourceServerProviderIssuerUri
        /// if need to customize the jar, a folder with the same name of this bean in classpath /testjars/<beanName>/...
        static CommonsExecWebServerFactoryBean authzServerFromSbAutoConfig() {
            return CommonsExecWebServerFactoryBean.builder()
                    .useGenericSpringBootMain()
                    .classpath(classpath -> classpath
                            // Add spring-boot-starter-authorization-server & transitive dependencies
                            .entries(springBootStarter("oauth2-authorization-server"))
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
