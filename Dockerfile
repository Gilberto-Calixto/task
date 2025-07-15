
# Stage 1: Build com GradleFROM gradle:8.2.1-jdk17 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew clean shadowJar --no-daemon

# Stage 2: RuntimeFROM amazoncorretto:21

WORKDIR /app

COPY --from=builder /app/build/libs/task-all.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]

