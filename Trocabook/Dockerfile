# 1. Imagem Base: Comece com o Java 23
FROM openjdk:23-slim

# 2. Argumento para o caminho do arquivo JAR
ARG JAR_FILE=target/*.jar

# 3. Copiar o JAR compilado para dentro da imagem
COPY ${JAR_FILE} app.jar

# 4. Comando de Execução
ENTRYPOINT ["java", "-jar", "/app.jar"]