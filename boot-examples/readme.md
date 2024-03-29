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

### Generating api model classes from open api contract definition
```shell
mvn compile -P generate-api-model
```

### Building Docker image
```shell
# Using existing dockerfile
docker build -t boot-examples:1.0.0 .

# Leveraging Spring Maven plugin
mvn spring-boot:build-image \
-Dspring-boot:build-image.imageName=boot-examples:1.0.0 

# Leveraging Spring Gradle plugin
gradle bootBuildImage \
--imageName=boot-examples:1.0.0
```

### Running image in container
```shell
docker run boot-examples:1.0.0 
```
