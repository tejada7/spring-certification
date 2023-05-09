## Bean configuration's lite vs full mode

`@Configuration` leverages cglib's proxy to ensure that the underlying beans declared in the annotated class are unique
(if theis scope is defaulted to singleton). That is the reason why classes stereotyped with `@Configuration` aren't allowed
to be `final`.

That said, **full mode** is when `@Configuration` along with `cglib proxy` are used.

Conversely, beans in **lite mode** can be defined in any other stereotype (including none and `@Configuration(proxyBeanMethods = false)`)
that can incidentally be a final class.

⚠️ Mind that bean method calls inside the declaring class do will create as many new instances as calls.

```java
import org.springframework.context.annotation.Bean;

class LiteModeConfig {

    @Bean
    Foo foo() {
        return new Foo(foo1()); // This will create a new instance of foo1
    }

    @Bean
    Foo foo1() {
        return new Foo1();
    }
}
```

thus, the correct way yo handle with this would be to leverage parameter injection:

```java
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
final class LiteModeConfig {

    @Bean
    Foo foo(Foo1 foo1) {
        return new Foo(foo1); // using unique instance of foo1
    }

    @Bean
    Foo foo1() {
        return new Foo1();
    }
}
```
