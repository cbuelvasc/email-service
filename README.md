# Email Service

## Useful Commands

| Gradle Command	                 | Description                                                  |
|:-----------------------------------|:-------------------------------------------------------------|
| `./gradlew bootRun`                | Run the application.                                         |
| `./gradlew build`                  | Build the application.                                       |
| `./gradlew build -x test`          | Build the application and omit test.                         |
| `./gradlew test`                   | Run tests.                                                   |
| `./gradlew bootJar`                | Package the application as a JAR.                            |
| `./gradlew bootBuildImage`         | Package the application as a container image.                |
| `./gradlew bootBuildImage -x test` | Package the application as a container image and omit test.  |

After building the application, you can also run it from the Java CLI:

```bash
java -jar build/libs/email-service-0.0.1-SNAPSHOT.jar
```
