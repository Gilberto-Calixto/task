
# Usa JDK 17
FROM amazoncorretto:21

# Cria diretório de trabalho
WORKDIR /app

# Copia o JAR gerado
COPY build/libs/task-all.jar app.jar

# Expõe a porta usada pelo Ktor
EXPOSE 8080

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]

