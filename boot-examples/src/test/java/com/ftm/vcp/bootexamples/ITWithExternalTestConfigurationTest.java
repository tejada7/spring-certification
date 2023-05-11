package com.ftm.vcp.bootexamples;

import com.ftm.vcp.bootexamples.application.FinderApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static com.ftm.vcp.bootexamples.ExternalTestConfiguration.getJohnDoe;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Import(ExternalTestConfiguration.class)
class ITWithExternalTestConfigurationTest {

    @Test
    void contextLoads(@Autowired final FinderApi finderApi) {
        then(finderApi.apply("123")).isEqualTo(getJohnDoe());
    }

}
