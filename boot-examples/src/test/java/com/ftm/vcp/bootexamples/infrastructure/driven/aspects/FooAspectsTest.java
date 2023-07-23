package com.ftm.vcp.bootexamples.infrastructure.driven.aspects;

import com.ftm.vcp.bootexamples.infrastructure.driven.async.AsynchronousService;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.EncapsulatedFooRepository;
import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.util.AopTestUtils;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayNameGeneration(ReplaceUnderscores.class)
class FooAspectsTest {

    private final EncapsulatedFooRepository repository = mock(EncapsulatedFooRepository.class);
    private final AsynchronousService service = mock(AsynchronousService.class);

    @Test
    void should_trigger_aspect() {
        final var factory = new AspectJProxyFactory(repository);
        factory.addAspect(new FooAspects(service));
        final EncapsulatedFooRepository proxy = factory.getProxy();

        proxy.create(new FooEntity(UUID.randomUUID().toString(), "foo1"));

        then(AopTestUtils.<EncapsulatedFooRepository>getUltimateTargetObject(proxy)).isEqualTo(repository);
        verify(service).doSomethingMethod();
    }
}
