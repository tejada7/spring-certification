package com.ftm.vcp.bootexamples.infrastructure.driving.rest;

import com.ftm.vcp.bootexamples.infrastructure.driven.config.SecurityConfig;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.FooRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Example controller")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@WebMvcTest(ExampleController.class)
@Import(SecurityConfig.class) // https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#migrating-from-websecurityconfigureradapter-to-securityfilterchain
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooRepository fooRepository;

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
}
