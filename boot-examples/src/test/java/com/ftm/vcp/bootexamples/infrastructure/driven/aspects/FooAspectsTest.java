package com.ftm.vcp.bootexamples.infrastructure.driven.aspects;

import com.ftm.vcp.bootexamples.application.CreatorApi;
import com.ftm.vcp.bootexamples.domain.FooIdentifier;
import com.ftm.vcp.bootexamples.infrastructure.driven.async.AsynchronousService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.util.AopTestUtils;

import static org.assertj.core.api.BDDAssertions.and;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;

@DisplayNameGeneration(ReplaceUnderscores.class)
class FooAspectsTest {

    private final CreatorApi application = mock(CreatorApi.class);
    private final AsynchronousService service = mock(AsynchronousService.class);

    @Test
    void should_trigger_aspect() {
        final var factory = new AspectJProxyFactory(application);
        factory.addAspect(new FooAspects(service));
        final CreatorApi proxy = factory.getProxy();

        proxy.create("foo1");

        then(service).should().doSomethingMethod();
        and.then(AopTestUtils.<CreatorApi>getUltimateTargetObject(proxy)).isEqualTo(application);
    }
}
