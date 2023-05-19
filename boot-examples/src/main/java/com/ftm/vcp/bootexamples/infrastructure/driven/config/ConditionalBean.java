package com.ftm.vcp.bootexamples.infrastructure.driven.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Here are all of the possible auto-configuration condition annotations:
 * @ConditionalOnClass - Presence of class on classpath.
 * @ConditionalOnMissingClass - Absence of class on classpath.
 * @ConditionalOnBean - Presence of Spring bean or bean type (class).
 * @ConditionalOnMissingBean - Absence of Spring bean or bean type (class).
 * @ConditionalOnProperty - Presence of Spring environment property.
 * @ConditionalOnResource - Presence of resource such as file.
 * @ConditionalOnWebApplication - If the application is considered to be a web application, that is uses the Spring WebApplicationContext, defines a session scope or has a StandardServletEnvironment.
 * @ConditionalOnNotWebApplication - If the application is not considered to be a web application.
 * @ConditionalOnExpression - Bean or configuration active based on the evaluation of a SpEL expression.
 * @ConditionalOnCloudPlatform - If specified cloud platform, Cloud Foundry, Heroku or SAP, is active.
 * @ConditionalOnEnabledEndpoint - Specified endpoint is enabled.
 * @ConditionalOnEnabledHealthIndicator - Named health indicator is enabled.
 * @ConditionalOnEnabledInfoContributor - Named info contributor is enabled.
 * @ConditionalOnEnabledResourceChain - Spring resource handling chain is enabled.
 * @ConditionalOnInitializedRestarter - Spring DevTools RestartInitializer has been applied with non-null URLs.
 * @ConditionalOnJava - Presence of a JVM of a certain version or within Condition Annotation Condition Factor a version range.
 * @ConditionalOnJndi - Availability of JNDI InitialContext and specified JNDI locations exist.
 * @ConditionalOnManagementPort - Spring Boot Actuator management port is either: Different from server port, same as server port or disabled.
 * @ConditionalOnRepositoryType - Specified type of Spring Data repository has been enabled.
 * @ConditionalOnSingleCandidate - Spring bean of specified type (class) contained in bean factory and single candidate can be determined.
 */
@ConditionalOnJava(JavaVersion.SEVENTEEN)
@ConditionalOnProperty(value = "feature-flag", havingValue = "true")
@Lazy
@Component
public class ConditionalBean {
}
