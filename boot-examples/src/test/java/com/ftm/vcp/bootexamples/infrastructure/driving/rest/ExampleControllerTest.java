package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.LoggingConfig;
import com.ftm.vcp.bootexamples.infrastructure.driven.config.SecurityConfig;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.EncapsulatedFooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Example controller")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@WebMvcTest(value = ExampleController.class, properties = "logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG")
@Import({SecurityConfig.class, LoggingConfig.class}) // https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#migrating-from-websecurityconfigureradapter-to-securityfilterchain
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EncapsulatedFooRepository fooRepository;

    @MockBean
    private H2ConsoleProperties h2ConsoleProperties;

    @Test
    @WithMockUser
    void should_reply_ok_if_authenticated_user() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_DEFAULT_FOOS_URL))
                .andExpect(status().isOk());
    }

    @Test
    void should_reply_unauthorized_when_non_authenticated_user() throws Exception {
        mockMvc.perform(get(ExampleController.PROTECTED_DEFAULT_FOOS_URL))
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
        given(fooRepository.create(any())).willReturn(new FooEntity("123", "a new foo"));

        final var xsrfCookie = mockMvc.perform(post(ExampleController.PROTECTED_DEFAULT_FOOS_URL))
                .andExpect(status().isForbidden())
                .andExpect(cookie().exists("XSRF-TOKEN"))
                .andReturn().getResponse().getCookie("XSRF-TOKEN");

        mockMvc.perform(post(ExampleController.PROTECTED_DEFAULT_FOOS_URL)
                        .header("X-XSRF-TOKEN", xsrfCookie.getValue())
                        .cookie(xsrfCookie))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, containsString("/protected/default/foos/123")))
                .andDo(MockMvcResultHandlers.print());
    }
}
