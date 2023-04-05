### Difference between BeanFactoryPostProcessor and BeanPostProcessor

+ If using Java config (i.e. `@Configuration` and `@Bean` definitions), both must be declared as static to avoid Spring 
lifecycle conflicts. This does noy apply when using Spring stereotyped annotations such as `@Component`.
+ `BeanFactoryPostProcessor` executes before anything else, more precisely when all bean definitions are loaded, but not 
yet **initialized** → called at start-up of the Spring IoC container.
It can manipulate **any** loaded bean.
+ `BeanPostProcessor` operates in between the bean initialization allowing to change **only the underlying bean** →
called when the Spring IoC container initializes a bean (i.e. after constructor).
