package com.ftm.vcp.bootexamples.domain;

import com.ftm.vcp.bootexamples.testutils.DomainRecordFieldProvider;
import com.ftm.vcp.bootexamples.testutils.SetContextExtension;
import com.ftm.vcp.bootexamples.testutils.SetGenericContext;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.DisplayNameGenerator.IndicativeSentences;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("A Foo")
@DisplayNameGeneration(IndicativeSentences.class)
@IndicativeSentencesGeneration(separator = " ", generator = ReplaceUnderscores.class)
@SetGenericContext(
    value = Foo.class,
    errorMessagePattern = "%s cannot be null"
)
class FooTest {

//    @RegisterExtension
//    private static final SetContextExtension customExtension = new SetContextExtension(
//        Foo.class, "%s cannot be null");

    @ParameterizedTest(name = "{0}=null, expectedErrorMessage={1}")
    @ArgumentsSource(DomainRecordFieldProvider.class)
    void should_not_contain_invalid_values(String field, String expectedMessage) {
        then(catchThrowable(() -> Instancio.of(Foo.class)
            .ignore(Select.field(field))
            .create())
        )
            .hasRootCauseInstanceOf(NullPointerException.class)
            .hasRootCauseMessage(expectedMessage);
        ;
    }

}
