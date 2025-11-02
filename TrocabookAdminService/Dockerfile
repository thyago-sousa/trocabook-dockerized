# 1. Imagem Base
FROM openjdk:21-jdk-slim

# 2. Instalar bibliotecas necessárias para AWT/FontManager
RUN apt-get update && apt-get install -y \
    fontconfig \
    libfreetype6 \
    libx11-6 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    && rm -rf /var/lib/apt/lists/*

# 3. Argumento para o caminho do arquivo JAR
ARG JAR_FILE=target/*.jar

# 4. Copiar o JAR compilado para dentro da imagem
COPY ${JAR_FILE} /app.jar

# 5. Comando de Execução
ENTRYPOINT ["java", "-jar", "/app.jar"]
