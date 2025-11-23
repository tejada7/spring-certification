package com.ftm.vcp.authzserver;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.oauth2.server.authorization.autoconfigure.servlet.OAuth2AuthorizationServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import tools.jackson.databind.annotation.JsonNaming;

import java.time.Instant;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.assertj.core.api.BDDSoftAssertions.thenSoftly;
import static tools.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(ReplaceUnderscores.class)
@AutoConfigureWebTestClient
class AuthzServerApplicationTests {

    @Test
    void should_issue_jwt_using_client_credentials_grant_type(@Autowired WebTestClient httpClient,
                                                              @Autowired OAuth2AuthorizationServerProperties authzProperties) {
        final var multipartBodyBuilder = new MultipartBodyBuilder();

        multipartBodyBuilder.part(OAuth2ParameterNames.SCOPE, OidcScopes.PROFILE);
        multipartBodyBuilder.part(OAuth2ParameterNames.GRANT_TYPE, AuthorizationGrantType.CLIENT_CREDENTIALS.getValue());
        final var exampleClient = authzProperties.getClient().get("example-client").getRegistration();
        multipartBodyBuilder.part(OAuth2ParameterNames.CLIENT_ID, exampleClient.getClientId());
        multipartBodyBuilder.part(OAuth2ParameterNames.CLIENT_SECRET, "secret");

        final var actualToken = httpClient.post()
            .uri("/oauth2/token")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(TokenResponseAdapter.class)
            .returnResult().getResponseBody()
            .asOauth2AccesTokenResponse()
            .getAccessToken();

        thenSoftly(softly -> {
            softly.then(actualToken.getScopes()).containsExactly(OidcScopes.PROFILE);
            softly.then(actualToken.getExpiresAt()).isAfter(Instant.now());
        });
    }

    @JsonNaming(SnakeCaseStrategy.class)
    record TokenResponseAdapter(String accessToken, String scope) {

        OAuth2AccessTokenResponse asOauth2AccesTokenResponse() {
            return OAuth2AccessTokenResponse.withToken(accessToken)
                .tokenType(OAuth2AccessToken.TokenType.BEARER)
                .scopes(stream(scope.split(" "))
                    .collect(toUnmodifiableSet()))
                .build();
        }
    }
}
