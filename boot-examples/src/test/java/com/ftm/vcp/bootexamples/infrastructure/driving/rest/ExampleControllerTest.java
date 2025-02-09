package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.application.CreatorApi;
import com.ftm.vcp.bootexamples.application.MultipleFinderApi;
import com.ftm.vcp.bootexamples.domain.Foo;
import com.ftm.vcp.bootexamples.infrastructure.driven.config.DefaultOneTimeTokenProviderImpl;
import com.ftm.vcp.bootexamples.infrastructure.driven.config.LoggingConfig;
import com.ftm.vcp.bootexamples.infrastructure.driven.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Example controller")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@WebMvcTest(value = ExampleController.class, properties = "logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG")
@Import({SecurityConfig.class, LoggingConfig.class, DefaultOneTimeTokenProviderImpl.class}) // https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#migrating-from-websecurityconfigureradapter-to-securityfilterchain
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreatorApi creator;

    @MockitoBean
    private MultipleFinderApi multipleFinder;

    @MockitoBean
    private H2ConsoleProperties h2ConsoleProperties;

    @Test
    @WithMockUser
    void should_reply_ok_if_authenticated_user() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_CSRF_FOOS_URL))
            .andExpect(status().isOk());
    }

    @Test
    void should_reply_unauthorized_when_non_authenticated_user() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_CSRF_FOOS_URL))
            .andExpect(status().isUnauthorized());

        mockMvc.perform(get(ExampleController.PROTECTED_HTTP_BASIC_FOOS_URL))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "HTTP_BASIC_USER")
    void should_reply_ok_when_valid_role() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_HTTP_BASIC_FOOS_URL))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ANOTHER_ROLE")
    void should_reply_forbidden_when_invalid_role() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_HTTP_BASIC_FOOS_URL))
            .andExpect(status().isForbidden());
    }

    @Test
    void should_reply_ok_when_using_real_user_password() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_HTTP_BASIC_FOOS_URL)
                .with(httpBasic("user", "password")))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void should_reply_created_when_foo_created() throws Exception {
        given(creator.create(any())).willReturn(new Foo("123", "a new foo"));

        final var xsrfCookie = mockMvc.perform(post(ExampleController.PROTECTED_CSRF_FOOS_URL))
            .andExpect(status().isForbidden())
            .andExpect(cookie().exists("XSRF-TOKEN"))
            .andReturn().getResponse().getCookie("XSRF-TOKEN");

        mockMvc.perform(post(ExampleController.PROTECTED_CSRF_FOOS_URL)
                .header("X-XSRF-TOKEN", xsrfCookie.getValue())
                .cookie(xsrfCookie))
            .andExpect(status().isCreated())
            .andExpect(header().string(HttpHeaders.LOCATION, containsString("/protected/csrf/foos/123")))
            .andDo(MockMvcResultHandlers.print());
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
        user, password, /
        unknown, password, /login?error
        user, invalid, /login?error
        """)
    void should_authenticate_using_login_form(String username, String password, String expectedRedirectionPath) throws Exception {
        mockMvc.perform(formLogin().user(username).password(password))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string(HttpHeaders.LOCATION, equalTo(expectedRedirectionPath)));
    }
}
