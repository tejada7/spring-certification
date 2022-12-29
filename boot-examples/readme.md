### Enabling SpringBoot devtools reload on IntelliJ
1. import the dependency:
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```
2. 

ℹ️ Devtools restart can be easily enabled/disabled by using the property:
```yaml
spring:
  devtools:
    restart:
      enabled: true | false
```
When applying changes, don't forget to rebuild the project (cmd + F9 on Mac OS). 

⚠️ If you're using JPMS, the reload is unfortunately not possible, further details [here](https://stackoverflow.com/questions/54022668/spring-boot-configure-devtools-in-modular-java).
