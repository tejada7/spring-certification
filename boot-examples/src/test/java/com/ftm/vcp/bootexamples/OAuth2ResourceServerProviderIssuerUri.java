package com.ftm.vcp.bootexamples;

import org.springframework.core.annotation.AliasFor;
import org.springframework.experimental.boot.test.context.DynamicPortUrl;

import java.lang.annotation.*;

/// Equivalent to :
/// ```java
/// @DynamicProperty(name = "spring.security.oauth2.resource-server.jwt.issuer-uri", value = "'http://127.0.0.1:' + port")
/// @Bean
/// static CommonsExecWebServerFactoryBean authzServer() {
/// ...
/// }
/// ```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@DynamicPortUrl(name = "spring.security.oauth2.resource-server.jwt.issuer-uri")
public @interface OAuth2ResourceServerProviderIssuerUri {

    @AliasFor(annotation = DynamicPortUrl.class)
    String host() default "127.0.0.1";

    @AliasFor(annotation = DynamicPortUrl.class)
    String contextRoot() default "";

}
